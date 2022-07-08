# 스프링 MVC : 날짜 값 변환, @PathVariable, 익셉션 처리

## 프로젝트 준비

- 스프링 MVC(세션, 인터셉터, 쿠키에서 사용한 [예제 소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/%ED%95%99%EC%8A%B5%20%EC%98%88%EC%A0%9C) 를 이어서 사용

## 날짜를 이용한 회원 검색 기능

- 회원 가입 일자를 기준으로 검색하는 기능을 구현하면서 몇 가지 스프링 MVC의 특징을 설명할 것이다. 이를 위해 MemberDao 클래스에 selectByRegdate()메서드를 추가하자.

#### src/main/java/spring/MemberDao.java

```java
package spring;

... 생략

import java.time.LocalDateTime;

... 생략 

public class MemberDao {

	... 생략
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to) {
		List<Member> results = jdbcTemplate.query(
					"SELECT * FROM member WHERE regDate BETWEEN ? and ? ORDER BY REGDATE DESC",
					new RowMapper<Member>() {
						@Override
						public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
							Member member = new Member(
									rs.getString("email"),
									rs.getString("password"),
									rs.getString("name"),
									rs.getTimestamp("regDate").toLocalDateTime());
							member.setId(rs.getLong("id"));
							return member;
						}
					},
					from, to);
		return results;
	}
}
```

- selectByRegdate() 메서드는 REGDATE 값이 두 파라미터로 전달받은 from 과 to 사이에 있는 Member 목록을 구한다. 
- 이 메서드를 이용해서 특정 기간 동안에 가입한 회원목록을 보여주는 기능을 구현할 것이다.

## 커맨드 객체 Date 타입 프로퍼티 변환 처리 : @DateTimeFormat

- 회원이 가입한 일시를 기준으로 회원을 검색하기 위해 시작 시간 기준과 끝 시간 기준을 파라미터로 전달받는다고 하자. 검색 기준 시간을 표현하기 위해 다음과 같이 커맨드 클래스를 구현해 사용한다.

#### src/main/java/controller/ListCommand.java

```java
package controller;

import java.time.LocalDateTime;

public class ListCommand {
	
	private LocalDateTime from;
	private LocalDateTime to;
	
	public LocalDateTime getFrom() {
		return from;
	}
	
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
}
```

- 검색을 위한 입력 폼은 다음처럼 이름이 from과 to인 \<input\> 태그를 정의한다.

```html
<input type="text" name="from" />
<input type="text" name="to" />
```

- 여기서 문제는 \<input\>에 입력한 문자열을 LocalDateTime 타입으로 변환해야 한다는 것이다. 
- \<input\>에 2018년 3월 1일 오후 3시를 표현하기 위해 "2018030115"로 입력한다고 해 보자. "2018030115" 문자열을 알맞게 LocalDateTime 타입으로 변환해야 한다.
- 스프링은 Long이나 int와 같은 기본 데이터 타입으로의 변환은 기본적으로 처리해주지만 LocalDateTime 타입으로의 변환은 추가 설정이 필요하다 앞서 작성한 ListCommand 클래스의 두 필드에 다음과 같이 @DateTimeFormat 애노테이션을 적용하면 된다.

```java
package controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime from;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime to;
		
	... 생략
}
```

- 커맨드 객체에 @DateTimeFormat 애노테이션이 적용되어 있으면 @DateTimeFormat에서 지정한 형식을 이용해서 문자열을 LocalDateTime 타입으로 변환한다. 
- 예를 들어 위 코드는 pattern 속성값으로 "yyyyMMddHH"를 주었는데 이 경우 “2018030115”의 문자열을 "2018년 3월 1일 15시" 값을 갖는 LocalDateTime 객체로 변환해준다.
- 컨트롤러 클래스는 다음과 같이 별도 설정 없이 ListCommand 클래스를 커맨드 객체로 사용하면 된다.

#### src/main/java/controller/MemberListController.java

```java
package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;

@Controller
public class MemberListController {
	
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@RequestMapping("/members")
	public String list(@ModelAttribute("cmd") ListCommand listCommand, Model model) {
		if (listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members", members);
		}
		return "member/memberList";
	}
}
```

- 새로운 컨트롤러 코드를 작성했으니 ControllerConfig 설정 클래스에 관련 빈 설정을 추가한다.

#### src/main/java/controller/ControllerConfig.java

```java
... 생략

import controller.MemberListController;
import spring.MemberDao;

@Configuration
public class ControllerConfig {
	
	... 생략
	
	@Autowired
	private MemberDao memberDao;
	
	... 생략 
	
	@Bean
	public MemberListController memberListController() {
		MemberListController controller = new MemberListController();
		controller.setMemberDao(memberDao);
		return controller;
	}
}
```

- 폼에 입력한 문자열이 커맨드 객체의 LocalDateTime 타입 프로퍼티로 잘 변환되는지 확인하기 위해 뷰 코드를 작성할 차례이다. 
- 먼저 LocalDateTime 값을 원하는 형식으로 출력해주는 커스텀 태그 파일을 작성하자. 
- JSTL이 제공하는 날짜 형식 태그는 아쉽게도 자바 1.8의 LocalDateTime 타입은 지원하지 않는다. 그래서 다음과 같은 태그파일을 사용해서 LocalDateTime 값을 지정한 형식으로 출력할 것이다.

#### src/main/webapp/WEB-INF/tags/formatDateTime.tag

```jsp
<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ tag import ="java.time.format.DateTimeFormatter" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" required="true" type="java.time.temporal.TemporalAccessor" %>
<%@ attribute name="pattern" type="java.lang.String" %>
<%
    if (pattern == null) pattern = "yyyy-MM-dd";
%>
<%=DateTimeFormatter.ofPattern(pattern).format(value) %>
```

- MemberListController 클래스의 List() 메서드는 커맨드 객체로 받은 ListCommand의 from 프로퍼티와 to 프로퍼티를 이용해서 해당 기간에 가입한 Member 목록을 구하고, 뷰에 "members" 속성으로 전달한다. 
- 뷰 코드는 이에 맞게 ListCommand 객체를 위한 폼을 제공하고 members 속성을 이용해서 회원 목록을 출력하도록 구현하면 된다. 

#### src/main/webapp/WEB-INF/view/member/memberList.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 조회</title>
</head>
<body>
    <form:form modelAttribute="cmd">
    <p>
        <label>from: <form:input path="from" /></label>
        ~ 
        <label>to: <form:input path="to" /></label>
        <input type="submit" value="조회">
    </p>
    </form:form>
    
    <c:if test="${! empty members}">
    <table>
        <tr>
            <th>아이디</th>
            <th>이메일</th>
            <th>이름</th>
            <th>가입일</th>
        </tr>
        <c:forEach var="mem" items="${members}">
        <tr>
            <td>${mem.id}</td>
            <td>
                <a href="<c:url value="/members/${mem.id}"/>">
                    ${mem.email}
                </a>
            </td>
            <td>${mem.name}</td>
            <td>
                <tf:formatDateTime value="${mem.registerDateTime}" pattern="yyyy-MM-dd" />
            </td>
        </tr>
        </c:forEach>
    </table>
    </c:if>
</body>
</html>
```

- \<form:input\> 태그를 이용해서 커맨드 객체의 from 프로피티와 to 프로퍼티를 위한 \<input\> 태그를 생성한다. 
- 스프링 폼 태그는 커맨드 객체의 프로퍼티 값을 출력할 때 @DateTimeFormat 애노테이션에 선정한 패턴을 사용해서 값을 출력한다. 
- from, to 프로퍼티는 모두 @ DateTimeformat(pattern="yyyyMMddHH") 애노테이션이 적용되어 있으므로 \<input\> 태그에 사용한 값을 생성할 때 "yyyyMMddHH" 형식으로 값을 출력한다.

- 웹 브라우저에서 "http://localhost:8080/.../member/list" 주소를 입력하면from 파라미터와 to 파라미터가 존재하지 않는다. 때문에 커맨드 객체의 from 프로퍼티와 to 프로퍼티 값은 null이 된다. 
- 앞서 MemberListController 코드를 보면 다음과 같이 listCommand의 from 프로퍼티와 to 프로퍼티가 둘 다 null이 아닌 때만 Member 데이터를 읽어오게 했다.

```java
@RequestMapping("/members")
public String list(@ModelAttribute("cmd") ListCommand listCommand, Model model) {
	if (listCommand.getFrom() != null && listCommand.getTo() != null) {
		List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
		model.addAttribute("members", members);
	}
	return "member/memberList";
}
```

- 따라서 "http://localhost:8080/.../members"를 실행하면 다음과 같이 시간을 입력할 수 있는 폼만 출력된다


- 입력 폼에서 from과 to 부분에 각각 '2022070209'와 '2022073018'을 입력하고(각각 2022년 07월 02일 9시와 2022년 07월 30일 18시를 의미) '조회' 버튼을 눌러보자. 
- regDate가 해당 기간에 속하는 Member 목록이 출력되는 것을 확인할 수 있다.


- 이 결과를 보면 폼에 입력한 '2022070809' 값이 LoginCommand 객체의 LocalDateTime 타입 프로퍼티인 from으로 알맞게 변환된 것을 알 수 있다.
- 또한 입력폼에 표시된 문자열을 보면 LocalDateTime 타입 프로퍼티 값도 지정한 형식으로 출력한 것을 알 수 있다.

> @DateTimeFormat은 java.time.LocalDateTime, java.time.LocalDate와 같이 자바 1.8에 추가된 시간 타입과 java.util.Date와 java.util.Calendar 타입을 지원한다.



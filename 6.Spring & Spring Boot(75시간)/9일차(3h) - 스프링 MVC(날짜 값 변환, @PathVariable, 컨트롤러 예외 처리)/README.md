# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1RckW-zMpluNhsKng9TGuB-QN1zEWuwLF?usp=sharing)

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

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image1.png)

- regDate가 해당 기간에 속하는 Member 목록이 출력되는 것을 확인할 수 있다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image2.png)

- 이 결과를 보면 폼에 입력한 '2022070209' 값이 LoginCommand 객체의 LocalDateTime 타입 프로퍼티인 from으로 알맞게 변환된 것을 알 수 있다.
- 또한 입력폼에 표시된 문자열을 보면 LocalDateTime 타입 프로퍼티 값도 지정한 형식으로 출력한 것을 알 수 있다.

> @DateTimeFormat은 java.time.LocalDateTime, java.time.LocalDate와 같이 자바 1.8에 추가된 시간 타입과 java.util.Date와 java.util.Calendar 타입을 지원한다.

### 변환 에러 처리

- 폼에서 from이나 to에 '20220702'을 입력해보자. 원래 지정한 형식은 "yyyyMMddHH"이기 때문에 "yyyMMdd" 부분만 입력하면 지정한 형식과 일치하지 않게 된다. 
- 형식에 맞지 않은 값을 폼에 입력한 뒤 '조회'를 실행하면 다음과 같은 400 에러가 발생한다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image3.png)


- 잘못 입력했을 때 위와 같은 에러 화면을 보고 싶은 사용자는 없다. 
- 400 에러 대신 폼에 알맞은 에러 메시지를 보여주고 싶다면 다음처럼 Errors 타입 파라미터를 요청매핑 애노테이션 적용 메서드에 추가하면 된다.
- Error 타입 파라미터를 listCommand 파라미터 바로 뒤에 위치시킨 것에 유의한다.

#### src/main/java/controller/MemberListController.java

```java
... 생략 

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;

... 생략 

@Controller
public class MemberListController {
	
	... 생략
	
	@RequestMapping("/members")
	public String list(@ModelAttribute("cmd") ListCommand listCommand, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "member/memberList";
		}
		
		if (listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members", members);
		}
		return "member/memberList";
	}
}
```

- 요청 매핑 애노테이션 적용 메서드가 Errors 타입 파라미터를 가질 경우 @DateTimeFormat에 지정한 형식에 맞지 않으면 Errors 객체에 "typeMismatch” 에러 코드를 추가한다. 따라서 다음 코드처럼 에러 코드가 존재하는지 확인해서 알맞은 처리를 할 수있다.

```java 
if (errors.hasErrors()) {
	return "member/memberList";
}
```

- 에러 코드로 "typeMismatch"를 추가하므로 메시지 프로퍼티 파일에 해당 메시지를 추가하면 에러 메시지를 보여줄 수 있다. 
- 에러 코드에 해당하는 메시지 코드 중 "typeMismatch.java.time. LocalDateTime" 코드를 label.properties에 추가한다.

#### src/main/resources/message/label.properties

```
... 생략 

typeMismatch.java.time.LocalDateTime=잘못된 형식
```

- memberList.jsp에 \<form:errors\> 태그를 사용해서 에러 메시지를 출력하는 코드를 추가한다.

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
        <form:errors path="from" />
        ~ 
        <label>to: <form:input path="to" /></label>
        <form:errors path="to" />
        <input type="submit" value="조회">
    </p>
    </form:form>
	
	... 생략
	
</body>
</html>
```
- 코드를 수정했으니 서버를 재시작하고 다시 잘못된 형식의 값을 입력해보자. 그러면 400 에러 대신 알맞은 에러 메시지가 보일 것이다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image4.png)


## 변환 처리에 대한 이해

- @DateTimeFormat 애노테이션을 사용하면 지정한 형식의 문자열을 LocalDateTime타입으로 변환해준다는 것을 예제를 통해 확인했다. 
- 여기서 궁금증이 하나 생긴다. 누가문자열을 LocalDateTime 타입으로 변환하는지에 대한 것이다. 답은 WebDataBinder에있다. 
- WebDataBinder는 이미 서 로컬 범위 Validator를 설명할 때 언급한 바 있는데 이 WebDataBinder가 값 변환에도 관여한다.


![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image5.png)

- WebDataBinder는 직접 타입을 변환하지 않고 위 그림에서 보는 것처럼 ConversionService에 그 역할을 위임한다. 스프링 MVC를 위한 설정인 @EnableWebMvc 애노테이션을 사용하면 DefaultFormattingConversionService를 ConversionService로 사용한다.

- DefaultFormattingConversionService는 int, long과 같은 기본 데이터 타입뿐만 아니라 @DateTimeFormat 애노테이션을 사용한 시간 관련 타입 변환 기능을 제공한다. 이런 이유로 커맨드로 사용할 클래스에 @DateTimeFormat 애노테이션만 붙이면 지정한형식의 문자열을 시간 타입 값으로 받을 수 있는 것이다.

- WebDataBinder는 \<form:input\>에도 사용된다. \<form:input\> 태그를 사용하면 다음과 같이 path 속성에 지정한 프로퍼티 값을 String으로 변환해서 \<input\> 태그의 value 속성값으로 생성한다. 이때 프로퍼티 값을 String으로 변환할 때 WebDataBinder의 ConversionService를 사용한다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image6.png)

## MemberDao 클래스 중복 코드 정리 및 메서드 추가

- MemberDao 코드를 보면 다음과 같이 RowMapper 객체를 생성하는 부분의 코드가 중복되어 있다. 중복되어 있는 코드를 다음과 같이 개선해 보자.

#### src/main/java/spring/MemberDao.java

```java
package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<Member> memRowMapper = new RowMapper<Member>() {
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
	};
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?", memRowMapper, email);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
						"values (?, ?, ?, ?)",
						new String[] { "ID" });
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4,
						Timestamp.valueOf(member.getRegisterDateTime()));
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER", memRowMapper);
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to) {
		List<Member> results = jdbcTemplate.query(
					"SELECT * FROM member WHERE regDate BETWEEN ? and ? ORDER BY regDate DESC",
					memRowMapper,
					from, to);
		return results;
	}
	
	public Member selectById(Long memId) {
		List<Member> results = jdbcTemplate.query(
				"SELECT * FROM member WHERE id = ?",
				memRowMapper, memId);
		return results.isEmpty() ? null : results.get(0);
	}
}
```

-  selectById() 메서드도 새롭게 추가했다. 이어지는 @PathVariable 애노테이션 관련 코드에서 selectById() 메서드를 사용할 것이므로 추가해 넣도록 하자.


## @PathVariable을 이용한 경로 변수 처리

- 다음은 id가 2인 회원의 정보를 조회하기 위한 URL이다.

```
http://localhost:8080/.../members/1
```

- 이 형식의 URL을 사용하면 각 회원마다 경로의 마지막 부분이 달라진다. 
- 이렇게 경로의 일부가 고정되어 있지 않고 달라질 때 사용할 수 있는 것이 @PathVariable 애노테이션이다. 
- @PathVariable 애노테이션을 사용하면 다음과 같은 방법으로 가변 경로를 처리할 수 있다.

#### src/main/java/controller/MemberDetailController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
	
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@GetMapping("/members/{id}")
	public String detail(@PathVariable("id") Long memId, Model model) {
		Member member = memberDao.selectById(memId);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
}
```

- 매핑 경로에 '경로변수'와 같이 중괄호로 둘러 쌓인 부분을 경로 변수라고 부른다. "경로변수"에 해당하는 값은 같은 경로 변수 이름을 지정한 @PathVariable 파라미터에전달된다
- "/members/{id}"에서 {id}에 해당하는 부분의 경로 값을 @PathVariable("id") 애노테이션이 적용된 memId 파라미터에 전달한다.
- 예를 들어요청 경로가 "/members/1"이면 {id}에 해당하는 "1"이 memld 파라미터에 값으로 전달된다. 
- memId 파라미터의 타입은 Long인데 이 경우 String 타입 값 "0"을 알맞게 Long타입으로 변환한다.

- 간단하게 테스트를 해보자. MemberDetailController를 다음과 같이ControllerConfig 설정 클래스에 빈으로 등록한다. 결과를 보여줄 JSP 코드는 다음과 같이 작성하자.

```java
package config;

... 생략
import controller.MemberDetailController;
import controller.MemberListController;
... 생략

@Configuration
public class ControllerConfig {
	
	... 생략 
	
	@Autowired
	private MemberDao memberDao;
	
	... 생략
		
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController controller = new MemberDetailController();
		controller.setMemberDao(memberDao);
		return controller;
	}
}
```

#### src/main/webapp/WEB-INF/view/member/memberDetail.jsp

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 정보</title>
</head>
<body>
    <p>아이디: ${member.id}</p>
    <p>이메일: ${member.email}</p>
    <p>이름: ${member.name}</p>
    <p>가입일: <tf:formatDateTime value="${member.registerDateTime}"
                    pattern="'yyyy-MM-dd HH:mm" /></p>
</body>
</html>
```

- 서버를 시작하고 웹 브라우저에 "http://localhost:8080/.../members/1" 와 같은 주소를 입력하자. 아이디가 1인 회원이 존재하면 다음과 같이 @PathVariable을 통해 전달받은 경로 변수값을 사용해서 회원 정보를 읽어와 뷰에 전달한다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image7.png)

## 컨트롤러 익셉션 처리하기

- 없는 ID를 경로변수로 사용다면 다음처럼 MemberNotFoundException이 발생한다(회원 데이터가 존재하지 않을 경우 MemberDetailController가 익셉션을 발생시키도록 구현했다).

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image8.png)

- MemberDetailController가 사용하는 경로 변수는 Long 타입인데 실제 요청 경로에 숫자가 아닌 문자를 입력해보자.
- "http://localhost:8080/.../members/a" 주소를 입력하면 "a"를 Long 타입으로 변환할 수 없기 때문에 다음과 같은 400 에러가 발생한다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image9.png)

- 익셉션 화면이 보이는 것보다 알맞게 익셉션을 처리해서 사용자에게 더 적합한 안내를해 주는 것이 더 좋다. 
- MemberNotFoundException은 try-catch로 잡은 뒤 안내 화면을 보여주는 뷰를 보여주면 될 것 같다. 
- 그런데 타입 변환 실패에 따른 익셉션은 어떻게 해야 에러 화면을 보여줄 수 있을까? 이럴 때 유용하게 사용할 수 있는 것이 바로@ExceptionHandler 애노테이션이다.
- 같은 컨트롤러에 @ExceptionHandler 애노테이션을 적용한 메서드가 존재하면 그 메서드가 익셉션을 처리한다. 
- 따라서 컨트롤러에서 발생한 익셉션을 직접 처리하고 싶다면 @ExceptionHandler 애노테이션을 적용한 메서드를 구현하면 된다. 다음은 MemberDetailController 클래스에 @ExceptionHandler 적용 메서드를 추가한 예를 보여주고 있다.

#### src/main/java/controller/MemberDetailController.java

```java
package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

... 생략

@Controller
public class MemberDetailController {
	
	... 생략
	
	@GetMapping("/members/{id}")
	public String detail(@PathVariable("id") Long memId, Model model) {
		Member member = memberDao.selectById(memId);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException() {
		return "member/invalidId";
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleNotFoundException() {
		return "member/noMember";
	}
}
```


-  @ExceptionHandler의 값으로 TypeMismatchException.class를 주었는데, 이 익셉션은 경로 변수값의 타입이 올바르지 않을 때 발생한다. 이 익셉션이 발생하면 에러 응답을 보내는 대신 handleTypeMismatchException() 메서드를 실행한다. 
- 비슷하게 detail() 메서드를 실행하는 과정에서 MemberNotFoundException이 발생하면 35행의 handleNotFoundException() 메서드를 이용해서 익셉션을 처리한다.

- @ExceptionHandler 애노테이션을 적용한 메서드는 컨트롤러의 요청 매핑 애노테이션적용 메서드와 마찬가지로 뷰 이름을 리턴할 수 있다. 각각 서로 다른 뷰 이름을 리턴했다. 이 두 뷰 코드를 다음과 같이 작성한다.

#### src/main/webapp/WEB-INF/view/member/invalidId.jsp

```html
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>에러</title>
</head>
<body>
    잘못된 요청입니다.
</body>
</html>
```

#### src/main/webapp/WEB-INF/view/member/noMember.jsp

```html
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>에러</title>
</head>
<body>
    존재하지 않는 회원입니다.
</body>
</html>
```

- invalidId.jsp와 noMember.jsp를 작성했다면 "/members/a"(타입 오류)와 “/members/0”(없는 회원 ID)을 웹 브라우저에 입력해보자. 
- 그러면 앞서 봤던 에러 화면이 아니라 다음과 같이 직접 지정한 뷰를 응답으로 사용한다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image10.png)

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/images/image11.png)

- 익셉션 객체에 대한 정보를 알고 싶다면 메서드의 파라미터로 익셉션 객체를 전달받아사용하면 된다.

```java
@ExceptionHandler(TypeMismatchException.class)
public String handleTypeMismatchException(TypeMismatchException ex) {
	// ex 사용해서 로그 남기는 등 작업
	return "member/invalidId";
}
```

### @ControllerAdvice를 이용한 공통 익셉션 처리

- 컨트롤러 클래스에 @ExceptionHandler 애노테이션을 적용하면 해당 컨트롤러에서 발생한 익셉션만을 처리한다. 
- 다수의 컨트롤러에서 동일타입의 익셉션이 발생할 수도 있다. 이때 익셉션 처리 코드가 동일하다면 어떻게 해야 할까? 각 컨트롤러 클래스마다 익셉션 처리 메서드를 구현하는 것은 불필요한 코드 중복을 발생시킨다.

- 여러 컨트롤러에서 동일하게 처리할 익셉션이 발생하면 @ControllerAdvice 애노테이션을 이용해서 중복을 없앨 수 있다. 
- 다음은 @ControllerAdvice 애노테이션의 사용 예이다.

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("spring")
public class CommonExceptionHandler {
	@ExceptionalHandler(RuntimeException.class)
	public String handleRuntimeException() {
		return "error/commonException";
	}
}
```

- @ControllerAdvice 애노테이션이 적용된 클래스는 지정한 범위의 컨트롤러에 공통으로사용될 설정을 지정할 수 있다. 위 코드는 "spring" 패키지와 그 하위 패키지에 속한 컨트롤러 클래스를 위한 공통 기능을 정의했다. 
- spring 패키지와 그 하위 패키지에 속한 컨트롤러에서 RuntimeException이 발생하면 handleRuntimeException() 메서드를 통해서 익셉션을 처리한다.
- @ControllerAdvice 적용 클래스가 동작하려면 해당 클래스를 스프링에 빈으로 등록해야 한다.

### @Exception Handler 적용 메서드의 우선 순위

- @ControllerAdvice 클래스에 있는 @ExceptionHandler 메서드와 컨트롤러 클래스에있는 @ExceptionHandler 메서드 중 컨트롤러 클래스에 적용된 @ExceptionHandler메서드가 우선한다. 
- 즉 컨트롤러의 메서드를 실행하는 과정에서 익셉션이 발생하면 다음의 순서로 익셉션을 처리할 @ExceptionHandler 메서드를 찾는다.
	- 같은 컨트롤러에 위치한 @ExceptionHandler 메서드 중 해당 익셉션을 처리할 수 있는 메서드를 검색
	- 같은 클래스에 위치한 메서드가 익셉션을 처리할 수 없을 경우 @ControllerAdvice 클래스에위치한 @ExceptionHandler 메서드를 검색

- @ControllerAdvice 애노테이션은 공통 설정을 적용할 컨트롤러 대상을 지정하기 위해[표 14.1]과 같은 속성을 제공한다.

#### AOP 주요 용어

|속성|타입|설명|
|-----|------|------|
|value<br>basePackages|String[]|공통 설정을 적용할 컨트롤러가 속하는 기준 패키지|
|annotations|Class\<? extends Annotation\>[]|특정 애노테이션이 적용된 컨트롤러 대상|
|assignableTypes|Class\<?\>[]|특정 타입 또는 그 하위 타입인 컨트롤러 대상|

### @ExceptionHandler 애노테이션 적용 메서드의 파라미터와 리턴 타입

- @ExceptionHandler 애노테이션을 붙인 메서드는 다음 파라미터를 가질 수 있다.
	- HttpServletRequest, HttpServletResponse, HttpSession
	- Model
	- 익셉션

- 리턴 가능한 타입은 다음과 같
	- ModelAndView
	- String (이름)
	- (@ResponseBody 애노테이션을 붙인 경우) 임의 객체
	- ResponseEntity 
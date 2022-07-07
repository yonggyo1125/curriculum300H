# 스프링 MVC : 메시지, 커맨드 객체 검증

## 프로젝트 준비

- 스프링 MVC : 요청 매핑, 커맨드 객체, 리다이렉트, 폼 태그, 모델에서 사용한 [예제 소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%9A%94%EC%B2%AD%EB%A7%A4%ED%95%91%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%2C%20%ED%8F%BC%20%ED%83%9C%EA%B7%B8%2C%20%EB%AA%A8%EB%8D%B8)/%EC%98%88%EC%A0%9C%20%EC%86%8C%EC%8A%A4) 를 이어서 사용 

## \<spring:message\> 태그로 메시지 출력하기

- 사용자 화면에 보일 문자열은 JSP에 직접 코딩한다. 예를 들어 로그인 폼을 보여줄 때'아이디', '비밀번호' 등의 문자열을 다음과 같이 뷰 코드에 직접 삽입한다.

```html
<label>이메일</label>
<input type="text" name="email">
```

- '이메일'과 같은 문자열은 로그인 폼, 회원 가입 폼, 회원 정보 수정 폼에서 반복해서 사용된다. 이렇게 문자열을 직접 하드 코딩하면 동일 문자열을 변경할 때 문제가 있다. 예를 들어 폼에서 사용할 '이메일'을 '이메일 주소'로 변경하기로 했다면 각 폼을 출력하는 JSP를 찾아서 모두 변경해야 한다.

- 문자열이 뷰 코드에 하드 코딩 되어 있을 때의 또 다른 문제점은 다국어 지원에 있다. 전 세계를 대상으로 서비스를 제공해야 하는 경우 사용자의 언어 설정에 따라 '이메일', 'E-mail'과 같이 각 언어에 맞게 문자열을 표시해야 한다. 그런데 뷰 코드에 ‘이메일'이라고 문자열이 하드 코딩되어 있으면 언어별로 뷰 코드를 따로 만드는 상황이 발생한다.

- 두 가지 문제를 해결하는 방법은 뷰 코드에서 사용할 문자열을 언어별로 파일에 보관하고 뷰 코드는 언어에 따라 알맞은 파일에서 문자열을 읽어와 출력하는 것이다. 스프링은 자체적으로 이 기능을 제공하고 있기 때문에 약간의 수고만 들이면 각각의 언어별로 알맞은 문자열을 출력하도록 JSP 코드를 구현할 수 있다.

- 문자열을 별도 파일에 작성하고 JSP 코드에서 이를 사용하려면 다음 작업을 하면 된다
	- 문자열을 담은 메시지 파일을 작성한다.
	- 메시지 파일에서 값을 읽어오는 MessageSource 빈을 설정한다.
	- JSP 코드에서 \<spring:message\> 태그를 사용해서 메시지를 출력한다.
	

- 먼저 메시지 파일을 작성해보자. 메시지 파일은 자바의 프로퍼티 파일 형식으로 작성한다. 메시지 파일을 보관하기 위해 src/main/resources에 message 폴더를 생성하고 이 폴더에 label.properties 파일을 생성한다.

- UTF-8 인코딩을 사용해서 label.properties 파일을 작성한다. 이를 위해 label.properties 파일을 열 때 Text Editor를 사용해서 연다. Properties File Editor를 이용하면 '이메일'과 같은 한글 문자가 '\uC774\uBA54\uC77C'와 같은 유니코드값으로 표현되어 알아보기 힘들다.
	- label.properties 파일에서 마우스 오른쪽 버튼을 클릭해서 [Properties] 메뉴를 실행한 뒤 Resource에서 Text File Encoding 값을 other의 'UTF-8'로 변경하면 된다

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image2.png)

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image1.png)


#### src/main/resources/message/label.properties

```
member.register=회원가입

term=약관
term.agree=약관동의
next.btn=다음단계

member.info=회원정보
email=이메일
name=이름
password=비밀번호
password.confirm=비밀번호 확인
register.btn=가입 완료

register.done=<strong>{0}님</strong>, 회원 가입을 완료했습니다.

go.main=메인으로 이동
```

- 다음으로 MessageSource 타입의 빈을 추가하자. 
- 스프링 설정 중 한 곳에 추가하면 된다. 예제에서는 MvcConfig 설정 클래스에 추가한다.

#### src/main/java/config/MvcConfig.java
```java
package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

... 생략

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	... 생략

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
```

- basenames 프로퍼티 값으로 “message.label"을 주었다. 이는 message 패키지에 속한 label 프로퍼티 파일로부터 메시지를 읽어온다고 설정한 것이다. 
- src/main/resources 폴더도 클래스 패스에 포함되고 message 폴더는 message 패키지에 대응한다. 따라서 이 설정은 앞서 작성한 label.properties 파일로부터 메시지를 읽어온다. 
- setBasenames() 메서드는 가변 인자이므로 사용할 메시지 프로퍼티 목록을 전달할 수 있다.

```java
@Bean
public MessageSource messageSource() {
	ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
	ms.setBasenames("message.label", "message.error");
	ms.setDefaultEncoding("UTF-8");
	return ms;
}
```

- 앞서 작성한 label properties 파일은 UTF-8 인코딩을 사용하므로 defaultEncoding 속성의 값으로 "UTF-8"을 사용했다.
- 위 코드에서 주의할 점은 빈의 아이디를 “messageSource"로 지정해야 한다는 것이다. 다른 이름을 사용할 경우 정상적으로 동작하지 않는다. 

#### src/main/webapp/WEB-INF/view/register/step1.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="member.register" /></title>
    </head>
    <body>
        <h2><spring:message code="term" /></h2>
        <p>약관 내용</p>
        <form action="step2" method="post">
            <label>
                <input type="checkbox" name="agree" value="true">
                <spring:message code="term.agree" />
            </label>
            <input type="submit" value="<spring:message code="next.btn" />"  />
        </form>
    </body>
</html>
```

#### src/main/webapp/WEB-INF/view/register/step2.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="member.register" /></title>
    </head>
    <body>
        <h2><spring:message code="member.info" /></h2>
        <form:form action="step3" modelAttribute="registerRequest">
            <p>
                <label>
                    <spring:message code="email" />:<br>
                    <form:input path="email" />
                </label>
            </p>
            <p>
                <label>
                    <spring:message code="name" />:<br>
                    <form:input path="name" />
                </label>
            </p>
            <p>
                <label>
                    <spring:message code="password" />:<br>
                    <form:password path="password" />
                </label>
            </p>
            <p>
                <label>
                    <spring:message code="password.confirm" />:<br>
                    <form:password path="confirmPassword" />   
                </label>
            </p>
            <input type="submit" value="<spring:message code="register.btn" />">
        </form:form>
    </body>
</html>
```

#### src/main/webapp/WEB-INF/view/register/step3.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="member.register" /></title>
</head>
<body>
    <p>
        <spring:message code="register.done"
            arguments="${registerRequest.name}" />
    </p>
    <p>
        <a href="<c:url value='/main' />">
            [<spring:message code="go.main" />]
        </a>
    </p>
</body>
</html>
```

- 수정한 코드를 보면 다음 공통점이 있다.
	- \<spring:message\> 커스텀 태그를 사용하기 위해 태그 라이브러리 설정 추가
	```
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %〉
	```
	- \<spring:message\> 태그를 이용해서 메시지 출력

- \<spring:message\> 태그의 code 값은 앞서 작성한 프로퍼티 파일의 프로퍼티 이름과 일치한다. 
- \<spring:message\> 태그는 code와 일치하는 값을 가진 프로퍼티 값을 출력한다. 
- 예를 들어 step1.jsp에서  “member.register”코드값을 사용하는 \<spring:message\> 태그는 "회원가입"을 출력한다. 동일하게 “term"코드값을 사용하는 \<spring:message\> 태그는 "약관"을 출력한다.


- \<spring:message\> 태그는 MessageSource로부터 코드에 해당하는 메시지를 읽어온다. 
- 앞서 설정한 MessageSource는 label.properties 파일로부터 메시지를 읽어오므로 다음과 같이 \<spring:message\> 태그의 위치에 label.properties에 설정한 프로퍼티의 값이 출력된다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image4.png)

### 다국어 지원 위한 메시지 파일

- 다국어 메시지를 지원하려면 각 프로퍼티 파일 이름에 언어에 해당하는 로케일 문자를 추가한다. 예를 들어 한국어와 영어에 대한 메시지를 지원하려면 다음의 두 프로퍼티 파일을 사용하면 된다.

	- label_ko.properties
	- label_en.properties

- label 뒤에 '언어' 형식의 접미시가 붙었다. 언어는 두 글자 구분자로 한국어 ko', 영어는 en'이다. 각 언어를 위한 두 글자 구분자가 존재한다. 특정 언어에 해당하는 메시지 파일이 존재하지 않으면 언어 구분이 없는 label.properties 파일의 메시지를 사용한다.

- 브라우저는 서버에 요청을 전송할 때 Accept-Language 헤더에 언어 정보를 담아 전송한다. 예를 들어 브라우저의 언어 설정이 한글인 경우 브라우저는 Accept-Language 헤더의 값으로 "ko"를 전송한다.

- 스프링 MVC는 웹 브라우저가 전송한 Accept-Language 헤더를 이용해서 Locale을 구한다. 이 Locale을 MessageSource에서 메시지를 구할 때 사용한다.


### 메시지 처리를 위한 MessageSource와 \<spring:message\> 태그

- 스프링은 로케일(지역)에 상관없이 일관된 방법으로 문자열(메시지)을 관리할 수 있는 MessageSource 인터페이스를 정의하고 있다. 
- MessageSource 인터페이스는 다음과 같이 정의되어 있다. 
- 특정 로케일에 해당하는 메시지가 필요한 코드는 MessageSource의 getMessage() 메서드를 이용해서 필요한 메시지를 가져와서 사용하는 식이다.

```java
package org.springframework.context;

import java.util.Locale;

public interface MessageSource {
	String getMessage(String code, Object[] args, String defaultMessage, Locale locale);
	
	String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException;
	
	... 생략
}
```

- getMessage() 메서드의 code 파라미터는 메시지를 구분하기 위한 코드이고 locale 파라미터는 지역을 구분하기 위한 Locale이다. 
- 같은 코드라 하더라도 지역에 따라 다른 메시지를 제공할 수 있도록 MessageSource를 설계했다. 
- 이 기능을 사용하면 국내에서 접근하면 한국어로 메시지를 보여주고 해외에서 접근하면 영어로 메시지를 보여주는 처리를 할 수 있다.

- MessageSource의 구현체로는 자바의 프로퍼티 파일로부터 메시지를 읽어오는 ResourceBundleMessageSource 클래스를 사용한다. 이 클래스는 메시지 코드와 일치하는 이름을 가진 프로퍼티의 값을 메시지로 제공한다

- ResourceBundleMessageSource 클래스는 자바의 리소스번들(ResourceBundle)을 사용하기 때문에 해당 프로퍼티 파일이 클래스 패스에 위치해야 한다. 
- 앞서 예제에서도 클래스 패스에 포함되는 src/main/resources에 프로퍼티 파일을 위치시켰다. 보통 관리 편의성을 위해 프로퍼티 파일을 한곳에 모은다. 예제에서는 message라는 패키지에 프로퍼티 파일을 위치시켰다.

- \<spring:message\> 태그는 스프링 설정에 등록된 'messageSource' 빈을 이용해서 메시지를 구한다. 즉 \<spring:message\> 태그를 실행하면 내부적으로 MessageSource의 getMessage() 메서드를 실행해서 필요한 메시지를 구한다.  \<spring:message\> 태그의 code 속성이 코드값으로 사용된다.

- \<spring:message\> 태그의 code 속성에 지정한 메시지가 존재하지 않으면 다음과 같이 익셉션이 발생한다. 이와 유사한 익셉션이 발생하면 code 값과 프로퍼티 파일의 프로퍼티 이름이 올바른지 확인해야 한다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image5.png)


### \<spring:message\> 태그의 메시지 인자 처리

- 앞서 작성한 label properties 파일을 보면 다음과 같은 프로퍼티를 포함하고 있다.

```
register.done=<strong>{0}님</strong>, 회원 가입을 완료했습니다.
```

- 이 프로퍼티는 값 부분에 {0}을 포함한다. {0}은 인덱스 기반 변수 중 0번 인덱스(첫 번째인덱스)의 값으로 대치되는 부분을 표시한 것이다.
- MessageSource의 getMessage() 메서드는 인덱스 기반 변수를 전달하기 위해 다음과 같이 Object 배열 타입의 파라미터를 사용한다.

```java
String getMessage(String code, Object[] args, String defaultMessage, Locale locale);
String getMessage(String code, Object[] args, Locale locale);
```

- 위 메서드를 사용해서 MessageSource 빈을 직접 실행한다면 다음과 같이 Object 배열을 생성해서 인덱스 기반 변수값을 전달할 수 있다.

```java
Object[] args = new Object[1];
args[0] = "자바";
messageSource.getMessage("register.done", args, Locale.KOREA);
```
- \<spring:message\> 태그를 사용할 때에는 arguments 속성을 사용해서 인덱스 기반 변수값을 전달한다. 
- step3.jsp을 보면 다음과 같이 arguments 속성을 사용해서 register.done 메시지의 {0} 위치에 삽입할 값을 설정했다.

```
<spring:message code="register.done" arguments="${registerRequest.name}" />
```

- label.properties 파일의 register.done 프로퍼티에 {1}을 추가해보자.

```
register.done=<strong>{0}님 ({1})</strong>, 회원 가입을 완료했습니다.
```

- 이 메시지를 사용하려면 두 개의 인자를 전달해야 한다. 두 개 이상의 값을 전달해야 할경우 다음 방법 중 하나를 사용한다.
	- 콤마로 구분한 문자열
	- 객체 배열
	- \<spring:argument\> 태그 사용

- 다음은 콤마로 구분한 예를 보여준다. arguments에 전달한 값을 보면 두 표현식을 콤마로 구분하고 있다.

```
<spring:message code="register.done" 
	arguments="${registerRequest.name},${registerRequest.email}" />
```

- 다음은 \<spring:argument\> 태그를 사용한 예이다.

```html
<spring:message code="register.done">
	<spring:argument value="${registerRequest.name}" />
	<spring:argument value="${registerRequest.email}" />
</spring:message>
```

* * * 
## 커맨드 객체의 값 검증과 에러 메시지 처리

- 앞서 작성한 회원 가입 처리 코드가 동작은 하지만 비정상 값을 입력해도 동작하는문제가 있다. 올바르지 않은 이메일 주소를 입력해도 가입 처리가 되고 이름을 입력하지않아도 가입할 수 있다. 즉 입력한 값에 대한 검증 처리를 하지 않는다.

- 또 다른 문제는 중복된 이메일 주소를 입력해서 다시 폼을 보여줄 때 왜 가입에 실패했는지 이유를 알려주지 않는다. 가입이 실패한 이유를 보여주지 않기 때문에 사용자는 혼란을 겪게 될 것이다.

- 지금 언급한 두 가지 문제, 즉 몸 값 검증과 에러 메시지 처리는 어플리케이션을 개발할때 놓쳐서는 안 된다. 폼에 입력한 값을 검증하지 않으면 잘못된 값이 시스템에 입력되어 어플리케이션이 비정상 동작할 수 있다. 또한 에러 메시지를 제대로 보여주지 않으면 사용자는 서비스를 제대로 이용할 수 없게 된다.

- 스프링은 이 두 가지 문제를 처리하기 위해 다음 방법을 제공하고 있다.
	- 커맨드 객체를 검증하고 에러 코드로 저장
	- JSP에서 에러 코드로부터 메시지를 출력
	


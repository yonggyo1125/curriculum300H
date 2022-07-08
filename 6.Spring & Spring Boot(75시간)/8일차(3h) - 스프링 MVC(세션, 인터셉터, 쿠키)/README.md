# 스프링 MVC : 세션, 인터셉터, 쿠키

## 프로젝트 준비 

스프링 MVC : 메시지, 커맨드 객체 검증에서 사용한 예제 소스 중 [general](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/%ED%95%99%EC%8A%B5%20%EC%98%88%EC%A0%9C/general) 내용을 이어서 사용

## 로그인 처리를 위한 코드 준비

- 세션, 인터셉터, 쿠키에 관한 내용을 설명한다. 로그인 기능을 이용해서 이 내용을 설명한 것이므로 로그인과 관련된 몇 가지 필요한 코드를 만들어보자. 
- 먼저 로그인 성공 후 인증 상태 정보를 세션에 보관할 때 사용한 Authinfo 클래스를 다음과 같이 작성한다.

#### src/main/spring/AuthInfo.java

```java
package spring;

public class AuthInfo {
	private Long id;
	private String email;
	private String name;
	
	public AuthInfo(Long id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
}
```

- 암호 일치 여부를 확인하기 위한 matchPassword() 메서드를 Member 클래스에 추가한다. 

#### src/main/java/spring/Member.java

```java
package spring;

import java.time.LocalDateTime;

public class Member {

	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	... 생략
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
}
```

- 이메일과 비밀번호가 일치하는지 확인해서 AuthInfo 객체를 생성하는 AuthService 클래스는 다음과 같이 작성한다.

#### src/main/java/spring/AuthService.java

```java
package spring;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new WrongIdPasswordException();
		}
		if (!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}
```

- 이제 AuthService를 이용해서 로그인 요청을 처리하는 LoginController 클래스를 작성하자. 
- 폼에 입력한 값을 전달받기 위한 LoginCommand 클래스와 폼에 입력된 값이 올바른지 검사하기 위한 LoginCommandValidator 클래스를 각각 다음과 같이 작성한다.

#### src/main/java/spring/LoginCommand.java

```
package controller;

public class LoginCommand {

	private String email;
	private String password;
	private boolean rememberEmail;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isRememberEmail() {
		return rememberEmail;
	}
	
	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
}
```

#### src/main/java/controller/LoginCommandValidator.java

```java
package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginCommandValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
	}
}
```

- 로그인 요청을 처리하는 LoginController 클래스는 다음과 같이 작성한다. 코드에서 주석 처리한 코드는 뒤에서 완성한다.

#### src/main/java/controller/LoginController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping("/login")
public class LoginController {
	public AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			// TODO 세션에 authInfo 저장해야 함
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
```

- LoginController 클래스는 로그인 폼을 보여주기 위해 "login/loginForm" 뷰를 사용하고 로그인 성공 결과를 보여주기 위해 "loginSuccess" 뷰를 사용한다. 두 뷰를 위한 JSP코드를 각각 다음과 같이 작성한다.

#### src/main/webapp/WEB-INF/view/login/loginForm.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title" /></title>
</head>
<body>
    <form:form modelAttribute="loginCommand">
        <form:errors />
        <p>
            <label>
                <spring:message code="email" />:<br>
                <form:input path="email" />
                <form:errors path="email" />
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password" /><br>
                <form:password path="password" />
                <form:errors path="password" />
            </label>
        </p>
        <input type="submit" value="<spring:message code="login.btn" />">
    </form:form>
</body>
</html>
```

#### src/main/webapp/WEB-INF/view/login/loginSuccess.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title" /></title>
</head>
<body>
    <p>
        <spring:message code="login.done" />
    </p>
    <p>
        <a href="<c:url value='/main' />">
            [<spring:message code="go.main" />]
        </a>
    </p>
</body>
</html>
```

#### src/main/resources/message/label.properties

```
... 생략

login.title=로그인
login.btn=로그인하기
idPasswordNotMatching=아이디와 비밀번호가 일치하지 않습니다.
login.done=로그인에 성공했습니다.
```

- 이제 남은 작업은 컨트롤러와 서비스를 스프링 빈으로 등록하는 것이다. 
- MemberConfig 설정 파일과 ControllerConfig 설정 파일에 앞서작성한 AuthService 클래스와 LoginController 클래스를 빈으로 등록한다.

#### src/main/java/config/MemberConfig.java

```java
package config;

.. 생략

import spring.AuthService;
import spring.ChangePasswordService;

... 생략

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	... 생략
	
	@Bean
	public AuthService authService() {
		AuthService authService = new AuthService();
		authService.setMemberDao(memberDao());
		return authService;
	}
}
```

#### src/main/java/config/ControllerConfig.java

```java
package config;

... 생략
import controller.LoginController;
import spring.AuthService;


@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	
	@Autowired
	private AuthService authService;
	
	... 생략
	
	@Bean
	public LoginController lgoinController() {
		LoginController controller = new LoginController();
		controller.setAuthService(authService);
		return controller;
	}
}
```

- 로그인 처리에 필요한 기반 코드를 모두 만들었다. 지금까지 작성한 코드가 제대로 동작하는지 확인해보자. 
- 서버를 시작하고 웹 브라우저에서 "http://localhost:8080/.../login"을 입력하자. 다음과 같은 로그인 폼이 출력될 것이다.




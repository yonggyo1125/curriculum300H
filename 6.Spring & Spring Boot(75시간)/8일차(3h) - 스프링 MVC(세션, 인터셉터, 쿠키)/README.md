# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1Ac-NOCJ2YnfnaQA7I118gE9THAzhPMgG?usp=sharing)

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

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image2.png)


- 로그인 폼에서 올바른 이메일과 비밀번호를 입력하면 다음과 같은 성공 화면이 출력된다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image3.png)

* * * 
## 컨트롤러에서 HttpSession 사용하기

- 로그인 기능을 구현했는데 한 가지 빠진 것이 있다. 그것은 바로 로그인 상태를 유지하는 것이다. 
- 로그인 상태를 유지하는 방법은 크게 HttpSession을 이용하는 방법과 쿠키를 이용하는 방법이 있다. 
- 외부 데이터베이스에 세션 데이터를 보관하는 방법도 사용하는데 큰 틀에서 보면 HttpSession과 쿠키의 두 가지 방법으로 나뉜다. 

- 컨트롤러에서 HttpSession을 사용하려면 다음의 두 가지 방법 중 한 가지를 사용하면 된다.
	- 요청 매핑 애노테이션 적용 메서드에 HttpSession 파라미터를 추가한다.
	- 요청 매핑 애노테이션 적용 메서드에 HttpServletRequest 파라미터를 추가하고 HttpServletRequest를 이용해서 HttpSession을 구한다.

- 다음은 첫 번째 방법을 사용한 코드 예이다.

```java
@PostMaping
public String form(LoginCommand loginCommand, Errors errors, HttpSession session) {
	... // session을 사용하는 코드
}
```

- 요청 매핑 애노테이션 적용 메서드에 HttpSession 파라미터가 존재할 경우 스프링MVC는 컨트롤러의 메서드를 호출할 때 HttpSession 객체를 파라미터로 전달한다.
- HttpSession을 생성하기 전이면 새로운 HttpSession을 생성하고 그렇지 않으면 기존에존재하는 HttpSession을 전달한다.



- 두 번째 방법은 다음 코드처럼 HttpServletRequest의 getSession() 메서드를 이용하는것이다.

```java
@PostMapping
public String submit(LoginCommand loginCommand, Errors errors, HttpServletRequest req) {
	HttpSession session = req.getSession();
	... // session을 사용하는 코드
}
```

- 첫 번째 방법은 항상 HttpSession을 생성하지만 두 번째 방법은 필요한 시점에만HttpSession을 생성할 수 있다.

- LoginController 코드에서 인증 후에 인증 정보를 세션에 담도록 submit() 메서드의 코드를 다음과 같이 수정해보자.

#### src/main/java/controller/LoginController.java

```java
package controller;

import javax.servlet.http.HttpSession;

... 생략

@Controller
@RequestMapping("/login")
public class LoginController {
	... 생략 
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			session.setAttribute("authInfo", authInfo);
			
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
```

- 로그인에 성공하면  HttpSession의 "authInfo" 속성에 인증 정보 객체(authInfo)를 저장하도록 코드를 추가했다.

```
public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {
	... 생략
	
	session.setAttribute("authInfo", authInfo);
	
	... 생략
}
```

- 로그아웃을 위한 컨트롤러 클래스는 HttpSession을 제거하면 된다. 다음과 같이 로그아웃 처리를 위한 LogoutController를 구현해보자.

#### src/main/java/controller/LogoutController.java

```java
package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
}
```

- 새로운 컨트롤러를 구현했으므로 스프링 설정에 빈을 추가하자. 이 예제에서는 ControllerConfig 설정 클래스에 추가하면 된다.

#### src/main/java/config/ControllerConfig.java

```java
package config;

... 생략

import controller.LogoutController; 

@Configuration
public class ControllerConfig {
	
	... 생략 
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
}
```

- main.jsp 파일을 다음과 같이 수정해서 HttpSession을 제대로 사용하는지 확인해보자.

#### src/main/webapp/WEB-INF/view/main.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>메인</title>
    </head>
    <body>
        <c:if test="${empty authInfo}">
        <p>환영합니다.</p>
        <p>
            <a href="<c:url value="/register/step1" />">[회원 가입하기]</a>
            <a href="<c:url value="/login" />">[로그인]</a>
        </p>
        </c:if>
        
        <c:if test="${! empty authInfo}">
        <p>${authInfo.name}님, 환영합니다.</p>
        <p>
            <a href="<c:url value="/edit/changePassword" />">[비밀번호 변경]</a>
            <a href="<c:url value="/logout" />">[로그아웃]</a>
        </p>
        </c:if>
    </body>
</html>
```

- 서버를 시작하고 웹 브라우저에서 로그인을 시도해보자. 로그인에 성공한 뒤 메인으로 이동하면 다음과 같이 HttpSession에 저장된 "authInfo" 속성을 이용해서 로그인한 사용자의 정보를 출력한 것을 확인할 수 있다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image4.png)

- 로그아웃하거나 로그인 전이라면 HttpSession에 "authInfo" 속성이 존재하지 않으므로 다음과 같은 화면이 출력된다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image5.png)

## 비밀번호 변경 기능 구현

- 비밀번호 변경 기능을 위한 코드는 다음과 같다.
	- ChangePwdCommand
	- ChangePwdCommandValidator
	- ChangePwdController
	- changePwdForm.jsp
	- label.properties에 메시지 추가
	- ControllerConfig 설정 클래스에 빈 설정 추가
	
- 먼저 비밀번호 변경에 사용할 커맨드 객체와 Validator 클래스를 작성하자. 비밀번호를 변경할 때 현재 비밀번호와 새 비밀번호를 입력받도록 구현하므로 currentPassword와 newPassword의 두 가지 파라미터가 필요하다. 이를 위한 ChangePwdCommand 클래스는 다음과 같다.

#### src/main/java/controller/ChangePwdCommand.java

```java
package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
	}
}
```

- 비밀번호 변경 요청을 처리하는 컨트롤러 클래스는 다음과 같이 작성한다. 현재 로그인한 사용자 정보를 구하기 위해 HttpSession의 "authInfo" 속성을사용하고 있다.

#### src/main/java/controller/ChangePwdController.java

```java
package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.ChangePasswordService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd, errors);
		if (errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(),
					pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			return "edit/changedPwd";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
```

- 컨트롤러의 처리 결과를 보여줄 changePwdForm 뷰와 changedPwd 뷰 코드는 각각 다음과 같이 작성한다.

#### src/main/webapp/WEB-INF/view/edit/changePwdForm.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="change.pwd.title" /></title>
</head>
<body>
    <form:form>
    <p>
        <label>
            <spring:message code="currentPassword" />:<br>
            <form:input path="currentPassword" />
            <form:errors path="currentPassword" />
        </label>
    </p>
    <p>
        <label>
            <spring:message code="newPassword" />:<br>
            <form:password path="newPassword" />
            <form:errors path="newPassword" />
        </label>
    </p>
    <input type="submit" value="<spring:message code="change.btn" />">
    </form:form>
</body>
</html>
```

#### src/main/webapp/WEB-INF/view/edit/changedPwd.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="change.pwd.title" /></title>
</head>
<body>
    <p>
        <spring:message code="change.pwd.done" />
    </p>
    <p>
        <a href="<c:url value='/main' />">
            [<spring:message code="go.main" />]
        </a>
    </p>
</body>
</html>
```

- 뷰 코드에서 사용할 메시지를 label properties 파일에 다음과 같이 추가하자.

#### src/main/resources/message/label.properties

```
... 생략

change.pwd.title=비밀번호 변경
currentPassword=현재 비밀번호
newPassword=새 비밀번호
change.btn=변경하기
notMatching.currentPassword=비밀번호를 잘못 입력했습니다.
change.pwd.done=비밀번호를 변경했습니다.
```

- 마지막 남은 것은 ControllerConfig 설정에 ChangePwdController를 빈으로 등록하는 것이다. 다음과 같이 ControllerConfig.java 파일에 빈 설정을 추가한다.

#### src/main/java/config/ControllerConfig.java

```java
package config;

... 생략

import spring.ChangePasswordService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	... 생략
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController controller = new ChangePwdController();
		controller.setChangePasswordService(changePasswordService);
		return controller;
	}
}
```

- 이제 확인할 차례이다. 비밀번호 변경 기능을 테스트하려면 다음 순서대로 실행하면 된다.
	- http://localhost:8080/.../login으로 로그인 실행
	- 메인 화면(http://localhost:8080/.../main)에서 로그인 확인
	- 메인 화면에서 비밀번호 변경 링크를 눌러서 변경 몸으로 이동
	- 비밀번호 변경
	- 메인 화면에서 로그아웃 후 다시 로그인 시도
	- 비밀번호가 변경되었는지 확인
	
- 이 과정에서 주의할 점이 하나 있다. 그것은 바로 서버를 재시작하면 로그인부터 다시 해야 한다는 것이다. 
- 서버를 재시작하면 세션 정보가 유지되지 않기 때문에 세션에보관된 "authInfo" 객체 정보가 사라진다. 즉 서버를 재시작하면 ChangePwdController 클래스의 다음코드는 null을 리턴한다.

```java
// 서버를 재시작하면 세션이 삭제되기 때문에 아래 코드는 null을 리턴한다.
AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
```

- 위 코드에서 authInfo가 null이 되면 비밀번호 변경 기능을 실행하는 과정에서NullPointerException이 발생하게 된다. 기능을 테스트하는 도중에 코드를 수정해서 서버를 재시작했다면 로그인 과정부터 다시 시작해야 한다.

- 실제로 비밀번호 변경 기능을 실행해보자. 다음과 같이 입력폼에서 현재 비밀번호와 새 비밀번호를 입력하면 비밀번호가 변경될 것이다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image6.png)


![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image7.png)


* * * 
## 인터셉터 사용하기


- 로그인하지 않은 상태에서 http://localhost:8080/.../edit/changePassword 주소를 웹 브라우저에서 입력해보자. 비밀번호 변경 폼이 출력된다. 로그인하지 않았는데 변경 폼이 출력되는 것은 이상하다. 그것보다는 로그인하지 않은 상태에서 비밀번호 변경 폼을 요청하면 로그인 화면으로 이동시키는 것이 더 좋은 방법이다.

- 이를 위해 다음과 같이 HttpSession에 "authInfo" 객체가 존재하는지 검사하고 존재하지 않으면 로그인 경로로 리다이렉트하도록 ChangePwdController 클래스를 수정할 수 있다.

```java
@GetMapping
public String form(@ModelAtrribute("command") ChangePwdCommand pwdCmd, HttpSession session) {
	AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
	if (authInfo == null) {
		return "redirect:/login";
	}
	return "edit/changePwdForm";
}
```

- 그런데 실제 웹 어플리케이션에서는 비밀번호 변경 기능 외에 더 많은 기능에 로그인 여부를 확인해야 한다. 각 기능을 구현한 컨트롤러 코드마다 세션 확인 코드를 삽입하는 것은 많은 중복을 일으킨다.

- 이렇게 다수의 컨트롤러에 대해 동일한 기능을 적용해야 할 때 사용할 수 있는 것이 HandlerInterceptor이다.

### HandlerInterceptor 인터페이스 구현하기

- org.springframework.web.HandlerInterceptor 인터페이스를 사용하면 다음의 세 시점에 공통 기능을 넣을 수 있다(org.springframework.web 패키지는 이후 o.s.w로 표현하겠다).
	- 컨트롤러(핸들러) 실행 전
	- 컨트롤러(핸들러) 실행 후, 아직 뷰를 실행하기 전
	- 뷰를 실행한 이후

- 세 시점을 처리하기 위해 HandlerInterceptor 인터페이스는 다음 메서드를 정의하고있다.
	- boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
	- void postHandle(HttpServletRequest request,  HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;
	- void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;

- preHandle() 메서드는 컨트롤러(핸들러) 객체를 실행하기 전에 필요한 기능을 구현할때 사용한다. handler 파라미터는 웹 요청을 처리할 컨트롤러(핸들러) 객체이다. 이 메서드를 사용하면 다음 작업이 가능하다.
	- 로그인하지 않은 경우 컨트롤러를 실행하지 않음
	- 컨트롤러를 실행하기 전에 컨트롤러에서 필요로 하는 정보를 생성
	
- preHandle() 메서드의 리턴 타입은 boolean이다. preHandle() 메서드가 false를 리턴하면 컨트롤러(또는 다음 HandlerInterceptor)를 실행하지 않는다.
- postHandle() 메서드는 컨트롤러(핸들러)가 정상적으로 실행된 이후에 추가 기능을 구현할 때 사용한다. 컨트롤러가 익셉션을 발생하면 postHandle() 메서드는 실행하지 않는다.
- afterCompletion() 메서드는 뷰가 클라이언트에 응답을 전송한 뒤에 실행된다. 컨트롤러 실행 과정에서 익셉션이 발생하면 이 메서드의 네 번째 파라리터로 전달된다. 익셉션이 발생하지 않으면 네 번째 파라미터는 null이 된다. 따라서 컨트롤러 실행 이후에 예기치 않게 발생한 익셉션 로그로 남긴다거나 실행 시간을 기록하는 등의 후처리를 하기에 적합한 메서드이다

- HandlerInterceptor와 컨트롤러의 실행 흐름을 다음 그림으로 보면 다음과 같이 정리할 수 있다. HandlerMapping, ViewResolver, HandlerAdapter 등과의 흐름은 생략했다. 앞의 설명을 그림과 비교하면서 다시 읽어보면 이해가 더 잘 될 것이다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image1.png)


- HandlerInterceptor 인터페이스의 각 메서드는 아무 기능도 구현하지 않은 자바 1.8의 디폴트 메서드이다. 따라서 HandlerInterceptor 인터페이스의 메서드를 모두 구현할 필요가 없다. 이 인터페이스를 상속받고 필요한 메서드만 재정의하면 된다.

- 비밀번호 변경 기능에 접근할 때 HandlerInterceptor를 사용하면 로그인 여부에 따라 로그인 폼으로 보내거나 컨트롤러를 실행하도록 구현할 수 있다. 여기서 만들 HandlerInterceptor 구현 클래스는 preHandle() 메서드를 사용한다. HttpSession에 "authinfo" 속성이 존재하지 않으면 지정한 경로로 리다이렉트하도록 구현하면 된다. 구현 코드는 다음과 같다

#### src/main/java/interceptor/AuthCheckInterceptor.java

```java
package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if (authInfo != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}
```

- preHandle() 메서드는 HttpSession에 "authInfo" 속성이 존재하면 true를 리턴한다. 존재하지 않으면 다음코드와 같이 리다이렉트 응답을 생성한 뒤 false를 리턴한다. preHandle()메서드에서 true를 리턴하면 컨트롤러를 실행하므로 로그인 상태면 컨트롤러를 실행한다. 반대로 false를 리턴하면 로그인 상태가 아니므로 지정한 경로로 리다이렉트한다.

```java
response.sendRedirect(request.getContextPath() + "/login");
```

- 참고로 request.getContextPath()는 현재 컨텍스트 경로를 리턴한다. 예를 들어 웹 어플리케이션 경로가 "http://localhost:8080/mvc2" 이면 컨텍스트 경로는 "/mvc2" 이 된다. 따라서 23행은 "/mvc2/login"으로 리다이렉트하라는 응답을 전송한다.


### HandlerInterceptor 설정하기

- HandlerInterceptor를 구현하면 HandlerInterceptor를 어디에 적용할지 설정해야 한다. 관련 설정은 다음과 같다. 굵게 표시한 부분을 MvcConfig 설정 클래스에 추가하자.

#### src/main/java/config/MvcConfig.java

```java
package config;

... 생략
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import interceptor.AuthCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	... 생략
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
			.addPathPatterns("/edit/**");
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
}
```

- WebMvcConfigurer#addInterceptors() 메서드는 인터셉터를 설정하는 메서드이다.

- InterceptorRegistry#addInterceptor() 메서드는 HandlerInterceptor 객체를 설정한다. 위 코드에서는 AuthCheckInterceptor 객체를 인터셉터로 설정한다.
- InterceptorRegistry#addInterceptor() 메서드는 InterceptorRegistration 객체를 리턴하는데 이 객체의 addPathPatterns() 메서드는 인터셉터를 적용할 경로 패턴을 지정한다. 이 경로는 Ant 경로 패턴을 사용한다. 두 개 이상 경로 패턴을 지정하려면 각 경로 패턴을 콤마로 구분해서 지정한다. 위 코드는 "/edit/"로 시작하는 모든 경로에 인터셉터를 작용한다.

#### Ant 경로 패턴

- Ant 패턴은 \* \*\*, ?의 세 가지 특수 문자를 이용해서 경로를 표현한다. 각 문자는 다음의 의미를 갖는다.
	- \* : 0개 또는 그 이상의 글자
	- ? : 1개 글자
	- \*\* 0개 또는 그 이상의 폴더 경로

- 이들 문자를 사용한 경로 표현 예는 다음과 같다.
	- @RequestMapping("/member/?\*.info") : "/member"로 시작하고 확장자가 .info로 끝나는 모든 경로
	- @RequestMapping("/faq/f?00.fq") : "/faq/f"로 시작하고, 1글자가 사이에 위치하고 00.fq로 끝나는 모든 경로
	- @RequestMapping("/folders/\*\*\/files") : "/folders/"로 시작하고, 중간에 0개 이상의 중간 경로가 존재하고 "/files"로 끝나는 모든 경로, 예를 들어 "/folders/files", "/folders/1/2/3/files" 등이 일치한다.
	
- HandlerInterceptor가 실제로 적용되는지 확인해보자. addPathPatterns() 메서드에 "/edit/\*\*"를 주었으므로 "/edit/changePassword" 경로에 AuthCheckInterceptor가 적용된다. 
- 따라서 로그인하지 않은 상태에서 "/edit/changePassword"에 접근하면 로그인 폼으로 리다이렉트되어야 한다. 실제로 로그인하지 않은 상태에서 웹 브라우저에 "http://localhost:8080/.../edit/changePassword"를 입력해보자. 그러면 login 경로로 리다이렉트되어 로그인 폼을 볼 수 있을 것이다.

- addPathPatterns() 메드에 지정한 경로 패턴 중 일부를 제외하고 싶다면 excludePathPatterns() 메서드를 사용한다.

```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
			.addPathPatterns("/edit/**")
			.excludePathPatterns("/edit/help/**");
	}
}
```
- 제외할 경로 패턴은 두 개 이상이면 각 경로 패턴을 콤마로 구분하면 된다.

* * * 
## 컨트롤러에서 쿠키 사용하기

- 사용자 편의를 위해 아이디를 기억해 두었다가 다음에 로그인할 때 아이디를 자동으로 넣어주는 사이트가 많다. 이 기능을 구현할 때 쿠키를 사용한다. 다음의 예제에도 쿠키를 사용해서 이메일 기억하기 기능을 추가해보자.

- 이메일 기억하기 기능을 구현하는 방식은 다음과 같다.
	- 로그인 폼에 '이메일 기억하기' 옵션을 추가한다.
	- 로그인 시에 '이메일 기억하기' 옵션을 선택했으면 로그인 성공 후 쿠키에 이메일을 저장한다. 이때 쿠키는 웹 브라우저를 닫더라도 삭제되지 않도록 유효시간을 길게 설정한다.
	- 이후 로그인 폼을 보여줄 때 이메일을 저장한 쿠키가 존재하면 입력 몸에 이메일을 보여준다.

- 앞서 로그인과 관련해서 작성했던 LoginCommand 클래스에는 이미 rememberEmail필드가 존재한다. 이 필드를 사용하도록 LoginController와 loginForm.jsp를 알맞게 수정해서 이메일 기억하기 기능을 구현할 것이다.

```java
public class LoginCommand {
	private String email;
	private String password;
	private boolean rememberEmail;
}
```

- 이메일 기억하기 기능을 위해 수정할 코드는 다음의 네 곳이다.
	- loginForm.jsp: 이메일 기억하기 선택 항목을 추가한다.
	- LoginController의 form() 메서드 : 쿠키가 존재할 경우 몸에 전달할 커맨드 객체의 email 프로퍼티를 쿠키의 값으로 설정한다.
	- LoginController의 submit() 메서드 : 이메일 기억하기 옵션을 선택한 경우 로그인 성공 후에 이메일을 담고 있는 쿠키를 생성한다.
	- label.properties : 메시지를 추가한다.

- 먼저 loginForm.jsp를 수정해서 이메일 기억하기를 선택할 수 있도록 체크박스를 추가한다.

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
		...  생략
		
        <p>
            <label>
                <spring:message code="rememberEmail" />:
                <form:checkbox path="rememberEmail" />
            </label>
        </p>
        <input type="submit" value="<spring:message code="login.btn" />">
    </form:form>
</body>
</html>
```

- 새로운 메시지를 사용했으므로 label.properties에 다음과 같이 메시지를 추가한다.

#### src/main/resources/message/label.properties

```
... 생략
login.done=로그인에 성공했습니다.
rememberEmail=이메일 기억하기

change.pwd.title=비밀번호 변경
... 생략
```

- 다음 수정할 코드는 LoginController의 form() 메서드이다. form() 메서드는 이메일 정보를 기억하고 있는 쿠키가 존재하면 해당 쿠키의 값을 이용해서 LoginCommand 객체의 email 프로퍼티 값을 설정하면 된다.

- 스프링 MVC에서 쿠키를 사용하는 방법 중 하나는 @CookieValue 애노테이션을 사용하는 것이다. @CookieValue 애노테이션은 요청 매핑 애노테이션 적용 메서드의 Cookie 타입 파라미터에 적용한다. 이를 통해 쉽게 쿠키를 Cookie 파라미터로 전달받을 수 있다. @CookieValue 애노테이션을 사용하면 form() 메서드를 다음과 같이 구현할 수 있다.

#### src/main/java/controller/LoginController.java

```java
package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

... 생략 
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

... 생략 

@Controller
@RequestMapping("/login")
public class LoginController {
	... 생략 
	
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
		if (rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	... 생략
}
```
- @CookieValue 애노테이션의 value 속성은 쿠키의 이름을 지정한다. 20행 코드는 이름이 REMEMBER인 쿠키를 Cookie 타입으로 전달받는다. 지정한 이름을 가진 쿠키가 존재하지 않을 수도 있다면 required 속성값을 false로 지정한다.

- 이 예제의 경우 이메일 기억하기를 선택하지 않을 수도 있기 때문에 required 속성값을 false로 지정했다. required 속성의 기본 값은 true이다. required true인 상태에서 지정한 이름을 가진 쿠키가 존재하지 않으면 스프링 MVC는 익셉션을 발생시킨다.

- REMEMBER 쿠키가 존재하면 쿠키의 값을 읽어와 커맨드 객체의 email 프로퍼티 값을 설정한다. 커맨드 객체를 사용해서 폼을 출력하므로 REMEMBER 쿠키가 존재하면 입력 폼의 email 프로퍼티에 쿠키값이 채워져서 출력된다.

- 실제로 REMEMBER 쿠키를 생성하는 부분은 로그인을 처리하는 submit() 메서드이다. 쿠키를 생성하려면 HttpServletResponse 객체가 필요하므로 submit() 메서드의 파라미터로 HttpServletResponse 타입을 추가한다. 수정한 코드는 다음과 같다. 

#### src/main/controller/LoginController.java

```java
package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

... 생략 

@Controller
@RequestMapping("/login")
public class LoginController {
	... 생략
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		... 생략
		
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
```

- 로그인에 성공하면 이메일 기억하기를 선택했는지 여부에 따라(30), 30일동안 유지되는 쿠키를 생성하거나(31) 바로 삭제되는(33) 쿠키를 생성한다.

> 예제 코드이므로 쿠키값으로 이메일 주소를 저장할 때 평문 그대로 저장했다. 하지만 이메일 주소는 민감한 개인 정보이므로 실제 서비스에서는 암호화해서 보안을 높여야 한다.

- 모든 준비가 끝났으니 이메일 기억하기 기능을 테스트할 시간이다. 로그인할 때 '이메일 기억하기' 체크박스를 선택해보자. 로그인에 성공했다면 로그아웃을 한 뒤 다시 로그인 폼으로 이동해보자. 그러면 그림처럼 이전에 입력한 이메일 주소가 폼에 출력되는 것을 확인할 수 있을 것이다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/8%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%B8%EC%85%98%2C%20%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%2C%20%EC%BF%A0%ED%82%A4)/images/image8.png)



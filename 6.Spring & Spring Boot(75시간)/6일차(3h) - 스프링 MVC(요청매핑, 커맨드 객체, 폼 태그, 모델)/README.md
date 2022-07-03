# 스프링 MVC : 요청 매핑, 커맨드 객체, 리다이렉트, 폼 태그, 모델
- 스프링 MVC를 사용해서 웹 어플리케이션을 개발한다는 것은 결국 컨트롤러와 뷰 코드를 구현한다는 것을 뜻한다.
- 대부분의 설정은 개발 초기에 완성된다. 개발이 완료될 때까지 개발자가 만들어야 하는 코드는 컨트롤러와 뷰 코드이다. 
- 어떤 컨트롤러를 이용해서 어떤 요청 경로를 처리할지 결정하고 웹 브라우저가 전송한 요청에서 필요한 값을 구하고, 처리 결과를 JSP를 이용해서 보여주면 된다.

## 프로젝트 준비
- 프로젝트 생성

```
mvn archetype:generate
```
- groupId, artifactId는 적절하게 입력해 줍니다.
- 프로젝트를 위한 폴더가 생성되었다면 그 폴더 하위에 다음 폴더를 생성한다.
	- src/main/java
	- src/main/webapp
	- src/main/webapp/WEB-INF
	- src/main/webapp/WEB-INF/view
	
- pom.xml : 자바 실습 버전을 최신버전(17)로 변경합니다 
- pom.xml : javax.servlet-api, javax.servlet.jsp-api, jstl, spring-webmvc, spring-jdbc, tomcat-jdbc, mysql-connector-java 의존성을 [mvnrepository](https://mvnrepository.com/) 에서 검색하여 다음과 같이 추가합니다.

```xml
... 생략
<properties>
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	<maven.compiler.source>17</maven.compiler.source>
	<maven.compiler.target>17</maven.compiler.target>
</properties>
... 생략
<dependencies>
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>javax.servlet-api</artifactId>
		<version>4.0.1</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>javax.servlet.jsp-api</artifactId>
		<version>2.3.3</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-webmvc</artifactId>
		<version>5.3.21</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-jdbc</artifactId>
		<version>5.3.21</version>
	</dependency>
	<dependency>
		<groupId>org.apache.tomcat</groupId>
		<artifactId>tomcat-jdbc</artifactId>
		<version>9.0.64</version>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.29</version>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-api</artifactId>
		<version>1.7.36</version>
	</dependency>
	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-classic</artifactId>
		<version>1.2.11</version>
	</dependency>
... 생략
</dependencies>
```

- 이클립스에서 Import - Existing Maven Projects를 클릭하여 생성된 폴더를 선택하여 생성해 줍니다.
- 프로젝트가 추가되면 생성된 프로젝트에서 마우스 오른쪽 키 -> Properties -> Project Facets -> Dynamic Web Module을 체크한다.



- 다음의 코드를 복사한다 : 학습용 소스 > spring
	- spring 패키지를 생성한 뒤 복사하면 된다.
		- ChangePasswordService.java
		- DuplicateMemberException.java
		- Member.java
		- MemberDao.java
		- MemberNotFoundException.java
		- MemberRegisterService.java
		- RegisterRequest.java
		- WoringIdPasswordException.java
		
- [JdbcTemplate, 트랜잭션, 마이바티스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20JdbcTemplate%2C%20%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%2C%20%EB%A7%88%EC%9D%B4%EB%B0%94%ED%8B%B0%EC%8A%A4) 에서 생성한 데이터베이스를 그대로 사용한다. 생성하지 않았다면 해당 페이지로 이동하여 데이터베이스와 테이블을 생성한다.

> 톰캣에서 Maven Dependency를 인식하지 못하여 실행이 안되는 경우<br>프로젝트 폴더 -> 마우스 오른쪽 버튼 -> Properties -> Deplolyment Assembly -> Add 버튼 -> Maven Dependency 선택 -> Apply 또는 Apply and Close 버튼을 클릭하여 적용한다.

- 두 개의 스프링 설정 파일과 web.xml파일을 작성할 것이다. 먼저 서비스 클래스와 DAO 클래스를 위한 스프링 설정 클래스를 다음과 같이 작성한다. DataSource와 트랜잭션 관련 설정도 포함되어 있다.

- 로그 출력 설정
#### src/main/resources/logback.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<configuration>
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d %5p %c{2} - %m%n</pattern>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="stdout" />
    </root>
    
    <logger name="org.springframework.jdbc" level="DEBUG" />
</configuration>
```

#### src/main/java/config/MemberConfig.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
}
```

#### src/main/java/config/MvcConfig.java

```java
package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:/static/");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
}
```

#### src/main/webapp/WEB-INF/web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextClass</param-name>
            <param-value>
                org.springframework.web.context.support.AnnotationConfigWebApplicationContext
            </param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                config.MemberConfig
                config.MvcConfig
                config.ControllerConfig
            </param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>
            org.springframework.web.filter.CharacterEncodingFilter
        </filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/**</url-pattern>
    </filter-mapping>
    
</web-app>
```

## 요청 매핑 애노테이션을 이용한 경로 매핑
- 웹 어플리케이션을 개발하는 것은 다음 코드를 작성하는 것이다.
	- 특정 요청 URL을 처리할 코드
	- 처리 결과를 HTML과 같은 형식으로 응답하는 코드

- 이 중 첫 번째는 @Controller 애노테이션을 사용한 컨트롤러 클래스를 이용해서 구현한다.
- 컨트롤러 클래스는 요청 매핑 애노테이션을 사용해서 메서드가 처리할 요청 경로를 지정한다. 요청 매핑 애노테이션에는 @RequestMapping, @GetMapping, @PostMapping등이 있다. 
- 앞서 HelloController 클래스는 다음과 같이 @GetMapping 애노테이션을 사용해서 "/hello" 요청 경로를 hello() 메서드가 처리하도록 설정했다.

```java
import org.springframework.sterotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name);
		return "hello";
	}
}
```

- 요청 매핑 애노테이션을 적용한 메서드를 두 개 이상 정의할 수 도 있다. 
- 예를 들어 회원가입 과정은 "약관동의 -> 회원 정보 입력 -> 가입완료"인데 각 과정을 위한 URL을 다음과 같이 정할수 있을 것이다.
	- 약관 동의 화면 요청 : /register/step1
	- 회원 정보 입력 화면 : /register/step2
	- 가입 처리 결과 화면 : /register/step3
	
```java
@Controller
public class RegisterController {
	@RequestMapper("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@RequestMapping("/register/step2")
	public String handleStep2() {
		...
	}
	
	@RequestMapping("/register/step3")
	public String handleStep3() {
		...
	}
}
```

- 이 코드를 보면 각 요청 애노테이션의 경로가 "/register"로 시작한다. 이 경우 다음 코드 처럼 공통되는 부분의 경로를 담은 @RequestMapping 애노테이션을 클래스에 적용하고 각 메서드는 나머지 경로를 값으로 갖는 요청 맻핑 애노테이션을 적용할 수 있다.

```java
@Controller
@RequestMapping("/register")  // 각 메서드에 공통되는 경로
public class RegistController {
	
	@RequestMapping("/step1") // 공통 경로를 제외한 나머지 경로
	public String handleStep1() {
		return "register/step1";
	}
	
	@RequestMapping("/step2")
	public String handleStep2() {
		...
	}
}
```
- 스프링 MVC는 클래스에 적용한 요청 매핑 애노테이션의 경로와 메서드에 적용한 요청 매핑 애노테이션의 경로를 합쳐서 경로를 찾기 때문에 위 코드에서 handleStep1() 메서드가 처리하는 경로는 "/step1"이 아닌 "/register/step1"이 된다.


#### src/main/java/controller/RegisterController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
}
```

### src/main/webapp/WEB-INF/view/register/step1.jsp

```html
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>회원가입</title>
    </head>
    <body>
        <h2>약관</h2>
        <p>약관 내용</p>
        <form action="step2" method="post">
            <label>
                <input type="checkbox" name="agree" value="true">약관 동의
            </label>
            <input type="submit" value="다음 단계" />
        </form>
    </body>
</html>
```

- 남은 작업은 Controller.java 파일을 작성하고 이 파일에 RegisterController 클래스를 빈으로 등록하는 것이다. 설정 파일을 같이 작성한다.

#### src/main/java/config/ControllerConfig.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;

@Configuration
public class ControllerConfig {
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController();
	}
}
```

- 실행 결과

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%9A%94%EC%B2%AD%EB%A7%A4%ED%95%91%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%2C%20%ED%8F%BC%20%ED%83%9C%EA%B7%B8%2C%20%EB%AA%A8%EB%8D%B8)/images/image1.png)


## GET과 POST 구분 : @GetMapping, @PostMapping

- HTML 폼 코드를 보면 전송 방식을 POST로 지정했다. 주로 폼을 전송할 때 POST 방식을 사용하는데, 스프링 MVC는 별도 설정이 없으면 GET과 POST 방식에 상관없이 @RequestMapping에 지정한 경로와 일치하는 요청을 처리한다. 
- 만약 POST 방식 요청만 처리하고 싶다면 다음과 같이 @PostMapping 애노테이션을 사용해서 제한할 수 있다.

```java
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	@PostMapping("/register/step2")
	public String handleStep2() {
		return "register/step2";
	}
}
```

- 위와 같이 설정하면 handleStep2() 메서드는 POST 방식의 "/register/step2" 요청 경로만 처리하며 GET 방식의 "/register/step2" 요청 경로는 처리하지 않는다. 
- 동일하게 @GetMapping 애노테이션을 사용하면 GET 방식만 처리하도록 제한할 수 있다.이 두 애노테이션을 사용하면 다음 코드 처럼 같은 경로에 대해 GET과 POST 방식을 각각 다른 메서드가 처리하도록 설정할 수 있다.

```
@Controller
public class LoginController {
	@GetMapping("/member/login")
	public String form() {
		...
	}
	
	@PostMapping("/member/login")
	public String login() {
		... 
	}
}
```

- @GetMapping 애노테이션과 @PostMapping 애노테이션은 스프링 4.3 버전에 추가된 것으로 이전 버전까지는 다음 코드 처럼 @RequestMapping 애노테이션의 method 속성을 사용해서 HTTP 방식을 제한했다.

```java
@Controller
public class LoginController {
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String form() {
		...
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login() {
		...
	}
}
```

> @GetMapping 애노테이션, @PostMapping 애노테이션뿐만 아니라 @PutMapping 애노테이션, @DeleteMapping 애노테이션, @PatchMapping 애노테이션을 제공하므로  HTTP의 GET, POST, PUT, DELETE, PATCH에 대한 매핑을 제한할 수 있다.

## 요청 파라미터 접근

- 약관 동의 화면을 생성하는 step1.jsp 코드를 보면 다음처럼 약관에 동의할 경우 값이 true인 'agree' 요청 파라미터의 값을 POST 방식으로 전송한다.
- 따라서 폼에서 지정한 agree 요청 파라미터의 값을 이용해서 약관 동의 여부를 확인할 수 있다.

```html
<form action="step2" method="post">
	<label>
		<input type="checkbox" name="agree" value="true"> 약관 동의
	</label>
	<input type="submit" value="다음 단계" />
</form>
```
- 컨트롤러 메서드에서 요청 파라미터를 사용하는 첫 번째 방법은 HttpServletRequest를 직접 이용하는 것이다. 
- 예를 들면 다음과 같이 컨트롤러 처리 메서드의 파라미터로 HttpServletRequest 타입을 사용하고 HttpServletRequest와 getParameter() 메서드를 이용해서 파라미터의 값을 구하면 된다.

```java
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(HttpServletRequest request) {
		String agreeParam = request.getParameter("agree");
		if (agreeParam == null || !agreeParam.equals("true")) {
			return "register/step1";
		}
		return "register/step2";
	}
}
```

- 요청 파라미터에 접근하는 또 다른 방법은 @RequestParam 애노테이션을 사용하는 것이다.
- 요청 파라미터 개수가 몇 개 안되면 이 애노테이션을 사용해서 간단하게 요청 파라미터의 값을 구할 수 있다.

```java
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

	@PostMapping("/register/step2")
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agreeVal) {
		if (!agree) {
			return "register/step1";
		}
		return "register/step2";
	} 
}
```

- @RequestParam 애노테이션은 다음의 속성을 제공한다. 
- 다음 표에 따르면 위 코드는 agree 요청 파라미터의 값을 읽어와 agreeVal 파라미터에 할당한다. 요청 파라미터의 값이 없으면 "false" 문자열을 값으로 사용한다.

#### @RequestParam 애노테이션의 속성

|속성|타입|설명|
|-----|----|--------|
|value|String|HTTP 요청 파라미터의 이름을 지정한다.|
|required|boolean|필수 여부를 지정한다. 이 값이 true이면서 해당 요청 파라미터에 값이 없으면 익셉션이 발생한다. 기본값은 true이다.|
|defaultValue|String|요청 파라미터가 값이 없을 때 사용할 문자열 값을 지정한다. 기본값은 없다.|

- @RequestParam 애노테이션을 사용한 코드를 보면 다음과 같이 agreeVal 파라미터의 타입이 Boolean이다.

```java
@ReqeustParam(value="agree", defaultValue="false") Boolean agreeVal
```

- 스프링 MVC는 파라미터 타입에 맞게 String 값을 변환해 준다. 위 코드는 agree 요청 파라미터의 값을 읽어와 Boolean 타입으로 변환해서 agreeVal 파라미터에 전달한다. 
- Boolean 타입 외에 int, long, Integer, Long 등 기본 데이터 타입과 래퍼 타입에 대한 변환을 지원한다.

-/register/step2 경로를 처리하기 위한 코드 추가

#### src/main/java/controller/RegisterController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	
	... 생략
	
	@PostMapping("/register/step2")
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
			if (!agree) {
				return "register/step1";
			}
			
			return "register/step2";
	}
}
```

- handleStep2() 메서드는 agree 요청 파라미터의 값이 true가 아니면 다시 약관 동의 폼을 보여주기 위해 "register/step1" 뷰 이름을 리턴한다.
- 약관에 동의했다면 입력 폼을 보여주기 위해 "register/step2"를 뷰 이름으로 리턴한다.

#### src/main/webapp/WEB-INF/view/register/step2.jsp

```html
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>회원가입</title>
    </head>
    <body>
        <h2>회원 정보 입력</h2>
        <form action="step3" method="post">
            <p>
                <label>
                    이메일:<br>
                <input type="text" name="email" id="email">
                </label>
            </p>
            <p>
                <label>
                    이름:<br>
                    <input type="text" name="name" id="name">
                </label>
            </p>
            <p>
                <label>
                    비밀번호:<br>
                    <input type="password" name="password" id="password">
                </label>
            </p>
            <p>
                <label>
                    비밀번호 확인:<br>
                    <input type="password" name="confirmPassword" id="confirmPassword">
                </label>
            </p>
            <input type="submit" value=" 가입완료">
        </form>
    </body>
</html>
```

- 실행 화면

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%9A%94%EC%B2%AD%EB%A7%A4%ED%95%91%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%2C%20%ED%8F%BC%20%ED%83%9C%EA%B7%B8%2C%20%EB%AA%A8%EB%8D%B8)/images/image2.png)

## 리다이렉트 처리

- 컨트롤러에서 특정 페이지로 리다이렉트 시키는 방법은 "redirect:경로"를 뷰 이름으로 리턴하면 된다.
- /register/step2 경로를 GET 방식으로 접근할 때 약관 동의 화면인 /register/step1 경로를 리다이렉트 시키고 싶다면 다음과 같이 handleStep2Get() 메서드를 추가하면 된다.

#### src/main/java/controller/RegisterController.java

```java 
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
.. 생략


@Controller
public class RegisterController {
	
	... 생략
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
}
```

- @RequestMapping, @GetMapping 등 요청 매핑 관련 애노테이션을 적용할 메서드가 "redirect:"로 시작하는 경로를 리턴하면 나머지 경로를 이용해서 리다이렉트할 경로를 구한다.
- "redirect:" 뒤의 문자열이 "/"로 시작하면 웹 어플리케이션을 기준으로 이동 경로를 생성한다. 예를 들어 뷰 값으로 "redirect:/register/step1"을  사용했는데 이동 경로가 "/"로 시작하므로 실제 리다이렉트할 경로는 웹 어플리케이션 경로인(현재 컨텍스트 경로가 /mvc2로 간주하면) "/mvc2"와 "/register/step1"을 연결한 "/mvc2/register/step1"이 된다.

- "/"로 시작하지 않으면 현재 경로를 기준으로 상대 경로를 사용한다. 예를 들어 "redirect:step1"을 리턴했으면 현재 요청 경로인 "http://localhost:8080/mvc2/register/step2"를 기준으로 상대 경로인 "http://localhost:8080/mvc2/register/step1"을 리다이렉트 경로로 사용한다.
- "redirect:http://localhost:8080/mvc2/register/step1"과 같이 완전한 URL을 사용하면 해당 경로로 리다이렉션한다.

## 커맨드 객체를 이용해서 요청 파라미터 사용하기
- step2.jsp가 생성하는 폼은 다음 파라미터를 이용해서 정보를 서버에 전송한다.
	- email
	- name
	- password 
	- confirmPassword
	
- 폼 전송 요청을 처리하는 컨트롤러 코드는 각 파라미터의 값을 구하기 위해 다음과 같은 코드를 사용할 수 있다.

```java
@PostMapping("/register/step3")
public String handleStep3(HttpServletRequest request) {
	String email = request.getParameter("email");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String confirmPassword = request.getParameter("confirmPassword");
	
	RegisterRequest regReq = new RegisterRequest();
	regReq.setEmail(email);
	regReq.setName(name);
	...
}
```

- 위 코드가 올바르게 동작하지만, 요청 파라미터 개수가 증가할 때마다 handleStep3() 메서드의 코드 길이도 함께 길어지는 단점이 있다. 
- 파라미터가 20개가 넘는 복잡한 폼은 파라미터의 값을 읽어와 설정하는 코드만 40줄 이상 작성해야 한다.

- 스프링은 이런 불편함을 줄이기 위해 요청 파라미터의 값을 커맨드(command)객체에 담아주는 기능을 제공한다.
- 예를 들어 이름이 name인 요청 파라미터의 값을 커맨드 객체의 setName() 메서드를 사용해서 커맨드 객체에 전달하는 기능을 제공한다.
- 커맨드 객체라고 해서 특별한 코드를 작성해야 하는 것은 아니다. 요청 파라미터의 값을 전달받을 수 있는 세터 메서드를 포함하는 객체를 커맨드 객체로 사용하면 된다.
- 커맨드 객체는 다음과 같이 요청 매핑 애노테이션이 적용된 메서드의 파라미터에 위치한다.

```java
@PostMapping("/register/step3")
public String handleStep3(RegisterRequest regReq) {
	...
}
```

- RegisterRequest 클래스에는 setEmail(), setName(), setPassword(), setConfirmPassword() 메서드가 있다.
- 스프링은 이들 메서드를 사용해서 email, name, password, confirmPassword 요청 파라미터의 값을 커맨드 객체에 복사한 뒤 regReq 파라미터로 전달한다.
- 즉, 스프링 MVC가 handleStep3() 메서드에 전달할 RegisterRequest 객체를 생성하고 그 객체의 세터 메서드를 이용해서 일치하는 요청 파라미터의 값을 잔달한다.

- 폼에 입력한 값을 커맨드 객체로 전달받아 회원 가입을 처리하는 코드

#### src/main/java/controller/RegisterController.java

```java
... 생략
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	... 생략
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) {
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			return "register/step2";
		}
	}
}
```

- handleStep3() 메서드는 MemberRegisterService를 이용해서 회원 가입을 처리한다. 
- 회원 가입에 성공하면 뷰 이름으로 "register/step3"을 리턴하고, 이미 동일한 이메일 주소를 가진 회원 데이터가 존재하면 뷰 이름으로 "register/step2"를 리턴해서 다시 폼을 보여준다.

- RegisterController 클래스는 MemberRegisterService타입의 빈을 의존하므로 ControllerConfig.java 파일에 다음과 같이 의존 주입을 설정한다. 
- memberRegSvc 필드에 주입받는 MemberRegisterService 타입 빈은 MemberConfig 설정 클래스에 정의되어 있다.

#### src/main/java/config/ControllerConfig.java

```java
package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;
import spring.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	
	@Bean
	public RegisterController registerController() {
		RegisterController controller =  new RegisterController();
		controller.setMemberRegisterService(memberRegSvc);
		return controller;
	}
}
```

- 회원 가입에 성공 했을 때 결과를 보여줄 step3.jsp는 다음과 같이 작성한다.

#### src/main/webapp/WEB-INF/view/register/step3.jsp

```html
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <p>회원가입을 완료 했습니다.</p>
    <p><a href="<c:url value='/main' />">[첫 화면 이동]</a></p>
</body>
</html>
```
# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1If0UBN3tlYqO5dyFSmXApJ9_Al-ZE6iL?usp=sharing)

# 타임리프(Thymeleaf)

## 프로젝트 생성

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
	- src/main/webapp : webapp은 HTML, CSS, JS, JSP 등 웹 애플리케이션을 구현하는데 필요한 코드가 위치한다.

- pom.xml : 자바 실습 버전을 최신버전(17)로 변경합니다
- pom.xml : javax.servlet-api, javax.servlet.jsp-api, jstl, spring-webmvc 의존성을 mvnrepository 에서 검색하여 다음과 같이 추가합니다.

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
... 생략
</dependencies>
```

### src/main/webapp/WEB-INF/web.xml 설정

```
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
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

## 스프링 MVC와 타임리프 연동 설정

- 스프링 MVC에서 타임리프는 뷰 영역에 해당한다. 스프링 MVC의 구성 요소에 대해 설명할 때 뷰 영역과 관련된 구성 요소는 ViewResolver와 View였다. 타임리프는 thymeleaf-spring5 모듈을 제공하는데 이 모듈에 타임리프 연동을 위한 ViewResolver와 View 구현 클래스가 존재한다. 스프링 MVC에서 타임리프가 제공하는 ViewResolver를 사용하도록 설정하면 결과를 타임리프 템플릿을 이용해서 생성할 수 있다.

- 스프링 MVC와 타임리프를 연동하려면 먼저 타임리프의 스프링 연동 모듈을 의존에 추가해야 한다. 다음의 세 가지(thymeleaf-spring5, thymeleaf-extras-java8time, thymeleaf-layout-dialect) 의존 설정을 추가한다.

```xml
<dependency>
	<groupId>org.thymeleaf</groupId>
	<artifactId>thymeleaf-spring5</artifactId>
	<version>3.0.15.RELEASE</version>
</dependency>
<dependency>
	<groupId>org.thymeleaf.extras</groupId>
	<artifactId>thymeleaf-extras-java8time</artifactId>
	<version>3.0.4.RELEASE</version>
</dependency>
<dependency>
	<groupId>nz.net.ultraq.thymeleaf</groupId>
	<artifactId>thymeleaf-layout-dialect</artifactId>
	<version>3.1.0</version>
</dependency>
```

- thymeleaf-spring5 모듈은 스프링 MVC에서 타임리프를 뷰로 사용하기 위한 기능을 제공한다. 
- thymeleaf-extras-javaStime은 LocalDateTime과 같은 자바 8의 시간 타입을 위한 추가 기능을 제공한다.
- thymeleaf-layout-dialect은 페이지 레이아웃 기능을 사용하기 위한 추가 기능을 제공합니다.
- 의존을 추가했다면 스프링 MVC가 타임리프를 사용하도록 ViewResolver를 설정한다. 이를 위한 설정 코드는 다음과 같다.

#### src/main/java/MvcConfig.java

```java
package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/view/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new Java8TimeDialect());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setContentType("text/html");
		resolver.setCharacterEncoding("utf-8");
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
		
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(thymeleafViewResolver());
	}
}
```


를 true로 지정해야 한다. 캐시 옵션은 테스트와 운영 환경에 따라 값이 다르므로 프로필이나 프로퍼티 파일을 이용해서 이 설정 옵션을 변경할 수 있도록 하는 것이 일반적이다.

- 다음코드는 타임리프의 템플릿 엔진을 선정한다. 템플릿 파일을 읽어올 때 선언한 TemplateResolver를 사용한다. 또한 자바 8 시간 타입을 지원하기 위한 Dialect를 추가한다. 그리고 개선된 레이아웃 추가 기능을 위해 LayoutDialect 역시 추가한다.

```
@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new Java8TimeDialect());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
```

## 타임리프 소개
 
- 화면을 동적으로 만들려면 템플릿 엔진을 사용해야 합니다. 미리 정의된 템플릿(Template)을 만들고동적으로 HTML 페이지를 만들어서 클라이언트에 전달하는 방식입니다. 요청이 올 때마다서버에서 새로운 HTML 페이지를 만들어 주기 때문에 서버 사이드 렌더링 방식이라고 합니다.

- 서버 사이드 템플릿 엔진으로는 Thymeleaf, JSP, Freemarker, Groovy, Mustache 등이 있습니다. 어떤 것을 사용해도 만들 수 있지만 스프링에서는 Thymeleaf를 권장합니다.
-  Thymeleaf의 가장 큰 장점은 'natural templates' 입니다. JSP를 사용해 보신 분들은 알겠지만 JSP 파일의 확장자명은 JSP 입니다. 웹 브라우저에 파일을 띄우면 JSP 문법이 화면에 나타나는 것을 볼 수 있습니다.
- 즉, 서버 사이드 렌더링을 하지 않으면 정상적인 화면 출력 결과를 볼 수 없습니다. Thymeleaf를 사용할 때 Thymeleaf 문법을 포함하고 있는 html 파일을 서버 사이드 렌더링을 하지 않고 브라우저에띄워도 정상적인 화면을 볼 수 있습니다. Thymeleaf의 확장자명은 .html이며, Thymeleaf의 문법은html 태그 안쪽에 속성으로 사용됩니다. 예제 코드로 한번 살펴보겠습니다.

#### src/main/java/config/ControllerConfig.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.*;

@Configuration
public class ControllerConfig {
		
	@Bean
	public BasicController basicController() {
		return new BasicController();
	}
}
```

#### src/webapps/WEB-INF/view/ex01.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
    <p th:text="${data}">Hello Thymeleaf!!</p>
</body>
</html>
```

#### src/main/java/controller/BasicController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	@GetMapping("/ex01")
	public String ex01(Model model) {
		model.addAttribute("data", "타임리프 예제입니다.");
		return "ex01";
	}
}
```

- 웹 브라우저를 이용해서 ex01.html 파일을 열면 다음과 같은 화면이 나타납니다. \<p\>태그 안에 th:text="${data}" 라는 Thymeleaf 문법이 들어갔지만 html 파일이 깨지지 않고 정상적으로 출력되는 것을 확인할 수 있습니다.

![image1](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image1.png)

- 애플리케이션 실행 후 해당 /tpl/ex01 URL을 열면 "Hello Thymeleaf!" 대신 "타임리프 예제입니다!" 라는 문구가 나타나는 것을 볼수 있습니다.

![image2](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image2.png)

- 이것이 바로 Thymeleaf가 지향하는 "natural templates" 입니다. 디자이너 또는 퍼블리션는 자신이 작업한 내용을 html파일로 바로 열어서 확인할 수 있으며, 개발자는 디자이너 또는 퍼블리셔로부터 html 파일을 받아서 html 태그 안에 Thymeleaf 문법을 추가하는 것만으로 동적으로 html 파일을 생성할 수 있습니다. 
- html파일을 JSP 파일로 변경하는 작업은 실수할 확률도 높고 많은 시간이 걸립니다.

## 타임리프 기본문법

### 타임리프의 주요 식(expression)
- 타임리프는 크게 변수식, 페이지식, 링크 식의 세 가지 식과 선택 변수 식을 제공한다.

- <b>변수 식: </b> ${OGNL}
- <b>메시지 식:</b> #{코드}
- <b>링크 식 :</b> @{링크}
- <b>선택 변수 식 :</b> \*{OGNL}

- 변수 식은 OGNL에 해당하는 변수를 값으로 사용한다. 타임리프에는 템플릿을 변환할 때 필요한 데이터를 가진 컨텍스트가 존재하는데 변수명을 사용해서 이 컨텍스트에 보관된 객체에 접근한다. 스프링 MVC에 연동할 경우 <b>컨트롤러에서 생성한 모델 속성 이름이 변수명이 된다.</b>

```
<p>아이디: <span th:text="${member.id}">id</span></p>
```

- 메시지 식은 외부 메시지 자원에서 코드에 해당하는 문자열을 읽어와 출력한다. 지정한 경로에 위치한 프로퍼티 파일을 메시지 자원으로 사용한다. 스프링 MVC 연동을 하면 \<spring:message\>와 동일하게 스프링이 제공하는 MessageSource로 부터 코드에 해당하는 메시지를 읽어온다. 

```
<title th:text="#{message.register}">title</title>
```

- 링크 식은 링크 문자열을 생성한다. 링크 식이 절대 경로면 JSTL의 \<c:url\>태그와 동일하게 웹 어플리케이션 컨텍스트 경로를 기준으로 링크를 생성한다.

```
<a href="#" th:href="@{/members}">목록</a>
```

- 링크의 일부를 식으로 변경하고 싶다면 경로에 {변수}를 사용할 수 있다. 

```
<a href="#" th:href="@{/members/{memId}(memId=${mem.id})}">상세</a>
```

- 위 코드에서 링크 식의 {memId}는 경로 변수이다. 경로 변수 memId에 넣을 값을 뒤에 붙인 괄호 안에 지정한다. 위 코드에서 뒤에 붙인 (memId\=${memId})는 경로 변수 memId의 값으로 ${mem.id}를 사용한다는 것을 뜻한다. 


- 선택 변수식 th:object 속성과 관련되어 있다. th:object 속성은 특정 객체를 선택하는데 선택 변수식은 th:object로 선택한 객체를 기준으로 나머지 경로를 값으로 사용한다. 
- 예를 들어 아래 코드에서 \*{name}은 \<div\> 태그의 th:object에서 선택한 member 객체를 기준으로 name경로를 선택한다. 따라서 \*{name}은 ${member.name}과 같은 경로가 된다.

```
<div th:object="${member}">
	<span th:text="*{name}">name</span>
</div>
```


### 타임리프 식 객체
- 타임리프는 식에서 사용할 수 있는 객체를 제공한다. 이 식 객체를 이용하면 문자열 처리나 날짜 형식 변환 등의 작업을 할 수 있다. "#객체명"을 사용해서 식 객체를 사용한다. 
- 다음은 dates, 식 객체를 이용해서 Date 타입 변수 값을 형식에 맞게 출력하는 예이다.

```
<span th:text="${#dates.format(date, 'yyyy-MM-dd')}">date</span>
```

- 각 식 객체는 기능이나 속성을 제공한다. dates 식 객체의 경우 format을 비롯해 날짜 형식 포맷팅을 위한 다양한 기능을 제공한다. 

#### 타임리프가 제공하는 주요 식 객체

- #strings : 문자열 비교, 문자열 추출 등 String 타입을 위한 기능 제공
- #numbers : 포맷팅 등 숫자 타입을 위한 기능 제공
- #dates, #calendars, #temporals : Date타입과 Calendar 타입, LocalDateTIme 타입을 위한 기능 제공
- #lists, #sets, #maps : List, Set, Map을 위한 기능 제공



### th:text

- 뷰 영역에서 사용할 MemberDto(커맨드 객체, 데이터 전달용 객체 Data Transfer Object)를 생성해서 사용합니다.

- th:text : 식의 값을 태그 몸페로 출력한다. '\<'나 '&'와 같은 HTML 특수 문자를 '&lt;'과 '&amp;'와 같은 엔티티 형식으로 변환한다.
- th:utext : 식의 값을 태그 몸체로 출력한다. '\<'나 '&'와 같은 HTML 특수 문자를 그대로 출력한다.

#### src/main/java/dto/MemberDto.java

```java
package dto;

import java.time.LocalDateTime;

public class MemberDto {
	
	private Long memNo;
	private String memId;
	private String memNm;
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	
	public Long getMemNo() {
		return memNo;
	}
	
	public void setMemNo(Long memNo) {
		this.memNo = memNo;
	}
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemNm() {
		return memNm;
	}
	
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}
	
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	
	public LocalDateTime getModDt() {
		return modDt;
	}
	
	public void setModDt(LocalDateTime modDt) {
		this.modDt = modDt;
	}
}
```

#### src/main/java/controller/BasicController.java

```
package controller;

import java.time.LocalDateTime;
... 생략

import dto.MemberDto;

@Controller
@RequestMapping("/tpl")
public class BasicController {
	... 생략
	
	@GetMapping("/ex02")
	public String ex02(Model model) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemNo(Long.valueOf(1));
		memberDto.setMemId("user1");
		memberDto.setMemNm("이름1");
		memberDto.setRegDt(LocalDateTime.now());
		
		model.addAttribute("memberDto", memberDto);
		return "ex02";
	}
}
```

#### src/webapps/WEB-INF/view/ex02.html

```html
<!DOCTYPE html>
<html xmlns:th="'http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <h1>회원정보 출력 예제</h1>
        <div>
            회원번호 : <span th:text="${memberDto.memNo}"></span>
        </div>
        <div>
            아이디 : <span th:text="${memberDto.memId}"></span>
        </div>
        <div>
            이름 : <span th:text="${memberDto.memNm}"></span>
        </div>
        <div>
            가입일시 : <span th:text="${#temporals.format(memberDto.regDt, 'yyyy-MM-dd HH:mm:ss')}"></span>
        </div>
    </body>
</html>
```

- 실행 결과

![image3](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image3.png)

### th:each : 반복문

#### src/main/java/controller/BasicController.java
```
package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex03")
	public String ex03(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex03";
	}
}
```

#### src/webapps/WEB-INF/view/ex03.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>회원 목록 출력 예제</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>순번</th>
                <th>회원번호</th>
                <th>아이디</th>
                <th>회원명</th>
                <th>가입일</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="memberDto, status : ${memberDtoList}">
                <td th:text="${status.index}"></td>
                <td th:text="${memberDto.memNo}"></td>
                <td th:text="${memberDto.memId}"></td>
                <td th:text="${memberDto.memNm}"></td>
                <td th:text="${#temporals.format(memberDto.regDt, 'yyyy.MM.dd')}"></td>
            </tr>
        </tbody>
    </table>
</body>
</html>
```

- 실행 결과

![image4](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image4.png)


### th:if, th:unless : 조건문
- 순번이 짝수이면 '짝수'를 출력하고, 짝수가 아니면 '홀수'를 출력해주는 예제입니다.

#### src/main/java/controller/BasicController.java

```java

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex04")
	public String ex04(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex04";
	}
}
```

#### src/webapps/WEB-INF/view/ex04.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>회원 목록 출력 예제</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>순번</th>
                <th>회원번호</th>
                <th>아이디</th>
                <th>회원명</th>
                <th>가입일</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="memberDto, status : ${memberDtoList}">
                <td th:if="${status.even}" th:text="짝수"></td>
                <td th:unless="${status.even}" th:text="홀수"></td>
                <td th:text="${memberDto.memNo}"></td>
                <td th:text="${memberDto.memId}"></td>
                <td th:text="${memberDto.memNm}"></td>
                <td th:text="${#temporals.format(memberDto.regDt, 'yyyy.MM.dd')}"></td>
            </tr>
        </tbody>
    </table>
</body>
</html>
```

> status에는 현재 반복에 대한 정보가 존재합니다. 인덱스가 짝수일 경우 status.even은 true가 됩니다. 즉, 현재 인덱스가 짝수라면 순번에 '짝수'를 출력해줍니다.

![image5](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image5.png)

#### th:switch, th:case : 선택문

#### src/main/java/controller/BasicController.java

```java

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex05")
	public String ex05(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex05";
	}
}
```

#### src/webapps/WEB-INF/view/ex05.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>회원 목록 출력 예제</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>순번</th>
                <th>회원번호</th>
                <th>아이디</th>
                <th>회원명</th>
                <th>가입일</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="memberDto, status : ${memberDtoList}">
                <td th:switch="${status.even}">
                    <span th:case=true>짝수</span>
                    <span th:case=false>홀수</span>
                </td>
                <td th:text="${memberDto.memNo}"></td>
                <td th:text="${memberDto.memId}"></td>
                <td th:text="${memberDto.memNm}"></td>
                <td th:text="${#temporals.format(memberDto.regDt, 'yyyy.MM.dd')}"></td>
            </tr>
        </tbody>
    </table>
</body>
</html>
```

> ${status.even}의 값이 true일 경우는 '짝수'를 출력하고 false일 경우는 홀수이므로 '홀수'를 출력합니다.

![image6](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image6.png)


### th:href 
- Thymeleaf에서는 링크를 처리하는 문법으로 th:href가 있습니다. 
- Absolute URL :  이동할 서버의 URL을 입력해주는 Absolute URL 방식은 http:// 또는 https:// 로 시작합니다.
- Context-relative URL : 가장 많이 사용되는 URL 형식이며 우리가 실행하는 애플리케이션의 서버 내부를 이동하는 방법이라고 생각하면 됩니다. 
- 웹 애플리케이션 루트에 상대적인 URL을 입력합니다. 상대경로는 URL의 프로토콜이나 호스트 이름을 지정하지 않습니다.

#### src/main/java/controller/BasicController.java

```java 

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	@GetMapping("/ex06")
	public String ex06() {
		return "ex06";
	}
}
```


#### src/webapps/WEB-INF/view/ex06.html

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.ofg">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Thymeleaf 링크처리 예제</h1>
    <div>
        <a th:href="@{/tpl/ex01}">예제1 페이지 이동</a>
    </div>
    <div>
        <a th:href="@{https://www.thymeleaf.ofg/}">thymeleaf 공식페이지 이동</a>
    </div>
</body>
</html>
```

- 링크로 이동 시 파라미터 값을 전달해야 하는 경우도 처리할 수 있습니다.

#### src/webapps/WEB-INF/view/ex06.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.ofg">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Thymeleaf 링크처리 예제</h1>
    <div>
        <a th:href="@{/tpl/ex01}">예제1 페이지 이동</a>
    </div>
    <div>
        <a th:href="@{https://www.thymeleaf.ofg/}">thymeleaf 공식페이지 이동</a>
    </div>
    <div>
        <a th:href="@{/tpl/ex07(param1 = '파라미터 데이터1', param2='파라미터 데이터2')}">thymeleaf 파라미터 전달</a>
    </div>
</body>
</html>
```

#### src/main/java/controller/BasicController.java

```java 

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex07")
	public String ex07(String param1, String param2, Model model) {
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		return "ex07";
	}
}
```

#### src/webapps/WEB-INF/view/ex07.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>파라미터 전달 예제</h1>
    <div th:text="${param1}"></div>
    <div th:text="${param2}"></div>
</body>
</html>
```

- 실행 결과

![image7](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image7.png)

![image8](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image8.png)


### th:object : 선택 변수식

#### src/main/java/controller/BasicController.java

```java

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex08")
	public String ex08(Model model) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemNo(Long.valueOf(1));
		memberDto.setMemId("user1");
		memberDto.setMemNm("이름1");
		memberDto.setRegDt(LocalDateTime.now());
		
		model.addAttribute("memberDto", memberDto);
		return "ex08";
	}
}
```

#### src/webapps/WEB-INF/view/ex08.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
        <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body th:object="${memberDto}">
        <h1>회원정보 출력 예제</h1>
        <div>
            회원번호 : <span th:text="*{memNo}"></span>
        </div>
        <div>
            아이디 : <span th:text="*{memId}"></span>
        </div>
        <div>
            이름 : <span th:text="*{memNm}"></span>
        </div>
        <div>
            가입일시 : <span th:text="${#temporals.format(memberDto.regDt, 'yyyy-MM-dd HH:mm:ss')}"></span>
        </div>
    </body>
</html>
```

### 스프링 MVC 폼과 에러 메시지 연동
- JSP가 \<form:form\> 태그를 이용해서 커맨드 객체와 폼을 연동하는 것처럼 타임리프도 몇 가지 속성과 식 객체를 이용해서 폼을 연동할 수 있다. 

#### pom.xml

```xml
<dependency>
	<groupId>javax.validation</groupId>
	<artifactId>validation-api</artifactId>
	<version>2.0.1.Final</version>
</dependency>
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-validator</artifactId>
	<version>6.2.3.Final</version>
</dependency>
```

#### src/main/java/dto/LoginDto.java

```java
package dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {
	
	@NotBlank(message="아아디를 입력하세요.")
	private String memId;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memPw;
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
}
```

#### src/main/java/controller/LoginController.java

```java
package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LoginDto;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	@GetMapping("/login") 
	public String form(Model model) {
		LoginDto loginDto = new LoginDto();
		model.addAttribute("loginDto", loginDto);
		
		return "member/login";
	}
	
	@PostMapping("/login")
	public String process(@Valid LoginDto loginDto, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "member/login";
		}
		
		// 로그인 처리 
		
		return "redirect:/";
	}
}
```

#### src/main/java/config/ControllerConfig.java

```java
... 생략

@Configuration
public class ControllerConfig {
	
	... 생략 
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
}
```

#### src/main/webapp/WEB-INF/view/member/login.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
    </head>
    <body>
        <form method="post" action="#" th:action="@{/member/login}" th:object="${loginDto}">
            <dl>
                <dt>아이디</dt>
                <dd>
                    <input type="text" name="memId" th:field="*{memId}">
                    <span th:each="err : ${#fields.errors('memId')}" th:text="${err}"></span>
                </dd>
            </dl>
            <dl>
                <dt>비밀번호</dt>
                <dd>
                    <input type="text" name="memPw" th:field="*{memPw}">
                    <span th:each="err : ${#fields.errors('memPw')}" th:text="${err}"></span>
                </dd>
            </dl>
            <button type="submit">로그인</button>
        </form>
    </body>
</html>
```

![image9](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image9.png)


## 타임리프 페이지 레이아웃 

- 보통 웹사이트를 만들려면 header, footer, menu 등 공통적인 페이지 구성 요소들이 있습니다. 이런 영역들을 각각의 페이지마다 같은 소스코드를 넣는다면 변경이 일어날 때마다 이를 포함하고 있는 모든 페이지를 수정해야 할 것입니다. 
- Thyeleaf의 페이지 레이아웃 기능을 사용한다면 공통 요소 관리를 쉽게 할 수 있습니다.


### Thymeleaf Layout Dialect dependency 추가하기

#### pom.xml

- Thymeleaf Layout Dialect를 이용하면 하나의 레이아웃을 여러 페이지에 똑같이 적용할 수 있습니다. 공통적으로 적용되는 레이아웃을 미리 만들어놓고 현재 작성 중인 페이지만 레이아웃에 끼워넣으면 됩니다. 
- [mvnrepository](https://mvnrepository.com/) 에서 thymeleaf-layout-dialect을 검색하여 다음과 같이 추가합니다.

```xml
<dependency>
	<groupId>nz.net.ultraq.thymeleaf</groupId>
	<artifactId>thymeleaf-layout-dialect</artifactId>
	<version>3.1.0</version>
</dependency>
```

#### src/main/java/config/MvcConfig.java

- 템플릿 엔진 설정에 다음과 같이 설정을 추가합니다.\

```java
templateEngine.addDialect(new LayoutDialect());
```

```java
package config;
... 생략

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	... 생략
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new Java8TimeDialect());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
	
	... 생략
}
```

- thymeleaf-layout-dialect 라이브러리 설치가 완료됐다면 views 아래에 outlines 폴더 생성후 footer.html, header.html 파일을 생성합니다. 마찬가지로 views 폴더 아래에 layouts 폴더를만들고 main.html 파일을 생성합니다.

![image10](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image10.png)

#### src/main/webapp/WEB-INF/view/outlines/footer.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <footer th:fragment="footer">
        footer 영역 입니다.
    </footer>
</html>
```

#### src/main/webapp/WEB-INF/view/outlines/header.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <header th:fragment="header">
        header 영역 입니다.
    </header>
</html>
```

#### src/main/webapp/WEB-INF/view/layouts/main.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
          xmlns:layout="http:'//www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    
    <th:block layout:fragment="script"></th:block>
    <th:block layout:fragment="css"></th:block>
</head>
<body>
    <header th:replace="outlines/header::header"></header>
    
    <main layout:fragment="content"></main>
    
    <footer th:replace="outlines/footer::footer"></footer>
</body>
</html>
```

#### src/main/java/controller/BasicController.java

```java

... 생략

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	... 생략
	
	@GetMapping("/ex09")
	public String ex09() {
		return "ex09";
	}
}
```

#### src/main/webapp/WEB-INF/view/ex09.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
          xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
          layout:decorate="~{layouts/main}">
          
          <main layout:fragment="content">
                본문영역 입니다.
          </main>
</html>
```

![image11](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image11.png)

![image12](https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/15~16%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84(Thymeleaf)/images/image12.png)


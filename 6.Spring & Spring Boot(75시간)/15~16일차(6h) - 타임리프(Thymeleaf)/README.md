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


- 애플리케이션 실행 후 해당 /tpl/ex01 URL을 열면 "Hello Thymeleaf!" 대신 "타임리프 예제입니다!" 라는 문구가 나타나는 것을 볼수 있습니다.

- 이것이 바로 Thymeleaf가 지향하는 "natural templates" 입니다. 디자이너 또는 퍼블리션는 자신이 작업한 내용을 html파일로 바로 열어서 확인할 수 있으며, 개발자는 디자이너 또는 퍼블리셔로부터 html 파일을 받아서 html 태그 안에 Thymeleaf 문법을 추가하는 것만으로 동적으로 html 파일을 생성할 수 있습니다. 
- html파일을 JSP 파일로 변경하는 작업은 실수할 확률도 높고 많은 시간이 걸립니다.



## 타임리프 기본문법

## 타임리프 페이지 레이아웃

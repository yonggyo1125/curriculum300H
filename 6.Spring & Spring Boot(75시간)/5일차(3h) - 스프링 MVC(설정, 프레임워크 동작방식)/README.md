# 스프링 MVC 시작하기

- 스프링을 사용하는 여러 이유가 있지만 한 가지 이유를 꼽자면 스프링이 지원하는 웹 MVC 프레임워크 때문이다. 
- 스프링 MVC의 설정 방법만 익혀두면 웹 개발에 필요한 다양한 기능을 구현할 수 있게 된다.

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

- src/main/webapp :  webapp은 HTML, CSS, JS, JSP 등 웹 애플리케이션을 구현하는데 필요한 코드가 위치한다. 
- src/main/webapp/WEB-INF : web.xml 파일이 위치한다.

> 서블릿 스펙에 따르면 WEB-INF 폴더의 하위 폴더로 lib 폴더와 classes 폴더를 생성하고 각각의 폴더에 필요한 jar 파일과 컴파일 된 클래스 파일이 위치해야 한다. 하지만 메이븐이나 그레이들 프로젝트의 경우 필요한 jar 파일은 pom.xml/build.gradle 파일의 의존을 통해 지정하고 컴파일된 결과는 target 폴더나 build 폴더에 위치한다. 때문에 WEB-INF 폴더 밑에 lib 폴더나 classes 폴더를 생성할 필요가 없다.

- 자바 실습 버전을 최신버전(17)로 변경합니다 
- javax.servlet-api, javax.servlet.jsp-api, jstl, spring-webmvc 의존성을 [mvnrepository](https://mvnrepository.com/) 에서 검색하여 다음과 같이 추가합니다.

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

- 이클립스에서 Import - Existing Maven Projects를 클릭하여 생성된 폴더를 선택하여 생성해 줍니다.
- 서블릿 4.0, JSP 2.3, JSTL 1.2에 대한 의존을 추가했고 스프링 MVC를 사용하기 위해 spring-webmvc 모듈에 대한 의존을 추가했다.
- 프로젝트가 추가되면 생성된 프로젝트에서  마우스 오른쪽 키 -> Properties -> Project Facets -> Dynamic Web Module을 체크한다.
- 다음과 같은 설정 및 폴더가 생성이 되는데, WEB-INF 하위에 view 폴더를 생성한다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image1.png)

## 이클립스 톰캣 설정

[서블릿 & JSP Tomcat 설치 및 설정 참조](https://github.com/yonggyo1125/curriculum300H/tree/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)#tomcat-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%84%A4%EC%A0%95)

## 스프링 MVC를 위한 설정
- 스프링MVC의 주요 설정(HandlerMapping, ViewResolver 등)
- 스프링의 DispatcherServlet 설정 

### 스프링 MVC 설정

#### src/main/java/config/MvcConfig.java 

```
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
- 위 설정을 간단하게 설명하면 다음과 같다.
	- <b>@EnableWebMvc 애노테이션</b>은 스프링 MVC 설정을 활성화한다. 스프링 MVC를 사용하는데 필요한 다양한 설정을 생성한다.
	- <b>configureDefaultServletHandling</b> : DispatcherServlet의 매핑 경로를 '/'로 주었을 때, JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정을 추가한다.
	- <b>configureViewResolvers</b> : JSP를 이용해서 컨트롤러 실행 결과를 보여주기 위한 설정을 추가한다.
	- <b>addResourceHandlers</b> : CSS, JS, 이미지 등등 정적인 지원들을 저장할 경로를 지정한다(src/main/resources/static 경로 생성).

- 스프링 MVC를 사용하려면 다양한 구성 요소를 설정해야 한다. 이 요소를 처음부터 끝까지 직접 구성하려면 설정이 매우 복잡해진다.실제로 스프링 2.5나 3 버전에서 스프링 MVC를 사용하려면 상황에 맞는 설정을 일일이 구성해야 했다. 이런 복잡한 설정을 대신 해 주는 것이 바로 <b>@EnableWebMvc</b>애노테이션이다.
- @EnableWebMvc 애노테이션이 스프링 MVC를 사용하는데 필요한 기본적인 구성을 설정해준다면 <b>WebMvcConfigurer 인터페이스</b>는 스프링 MVC의 개별 설정을 조정할 떄 사용한다. 
- configureDefaultServletHandling() 메서드와 configureViewResolvers() 메서드는 WebMvcConfigurer 인터페이스에 정의된 메서드로 각각 디폴트 서블릿과 ViewResolver와 관련된 설정을 조정한다. 이 두 메서드가 내부적으로 생성한 설정의 경우에도 관련 빈을 직접 설정하면 20~30여 줄의 코드를 작성해야 한다.
- 위 설정이면 스프링 MVC를 이용해서 웹 어플리케이션을 개발하는데 필요한 최소한의 설정이 끝난다.

### web.xml 파일에 DispatcherServlet 설정

- 스프링 MVC가 웹 요청을 처리하려면 DispatcherServlet을 통해서 웹 요청을 받아야 한다. 
- 이를 위해 web.xml 파일에 DispatcherServlet을 등록한다. src/main/webapp/WEB-INF 폴더에 web.xml 파일을 작성하면 된다.

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

- DispatcherServlet을 등록하는데, 각 행은 다음의 의미를 갖는다. 
	- DispatcherServlet을 dispatcher라는 이름으로 등록한다.
	
		```xml
		 <servlet-name>dispatcher</servlet-name>
		```
	
	- contextClass 초기화 파라미터를 설정한다. 자바 설정을 사용하는 경우 AnnotationConfigWebApplicationContext 클래스를 사용한다. 이 클래스는 자바 설정을 이용하는데 웹 어플리케이션 용 스프링 컨테이너 클래스이다.
	
		```xml
		<servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
		```
		
	- contextConfigLocation 초기화 파라미터값을 지정한다. 이 파라미터에는 스프링 설정 클래스 목록을 지정한다. 각 설정 파일의 경로는 줄바꿈이나 콤마로 구분한다.
		
		```xml
		<init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                config.MvcConfig
                config.ControllerConfig
            </param-value>
        </init-param>
		```
		
	- 톰캣과 같은 컨테이너가 웹 어플리케이션을 구동할 때 이 서블릿을 함께 실행하도록 설정한다.
		
		```xml
		<load-on-startup>1</load-on-startup>
		```
		
	- 모든 요청을 DispatcherServlet이 처리하도록 서블릿 매핑을 설정했다.
		
		```xml
		<servlet-mapping>
			<servlet-name>dispatcher</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		```
	
	- HTTP 요청 파라미터의 인코딩 처리를 위한 서블릿 필터를 등록한다. 스프링은 인코딩 처리를 위한 필터인 CharacterEncodingFilter 클래스를 제공한다. encoding 초기화 파라미터를 설정해서 HTTP 요청 파라미터를 읽어올 떄 사용할 인코딩을 지정한다.
	
		```xml
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
		```
- DispatcherServlet은 초기화 과정에서 contextConfigLocation 초기화 파라미터에 지정한 설정 파일을 이용해서 스프링 컨테이너를 초기화 한다. 
- 즉, 위의 설정은 MvcConfig 클래스와 ControllerConfig 클래스를 이용해서 스프링 컨테이너를 생성한다.

## 코드 구현
- 클라이언트 요청을 알맞게 처리할 컨트롤러
- 처리 결과를 보여줄 JSP

### 컨트롤러 구현

#### src/main/java/controller/HelloController.java

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name);
		return "hello";
	}
}
```
- HelloController 코드에서 각 줄은 다음과 같은 의미를 지닌다. 
	- @Controller 애노테이션을 적용한 클래스는 스프링 MVC에서 컨트롤러로 사용한다.
	- @GetMapping 애노테이션은 메서드가 처리할 요청 경로를 지정한다. 위 코드의 경우 "/hello" 경오로 들어온 요청을 hello() 메서드를 이용해서 처리한다고 설정했다. 이름에서 알 수 있듯이 HTTP 요청 메서드 중 GET 메서드에 대한 매핑을 설정한다.
	- Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달할 때 사용한다.
	- @RequestParam 애노테이션은 HTTP 요청 파라미터의 값을 메서드의 파라미터로 전달할 떄 사용된다. 위 코드의 경우 name 요청 파라미터의 값을 name 파라미터에 전달한다.
	- "greeting"이라는 모델 속성에 값을 설정한다. 값으로는 "안녕하세요"와 name 파라미터의 값을 연결한 문자열을 사용한다. 뒤에서 작성할 JSP 코드는 이 속성을 이용해서 값을 출력한다.
	
	```java
	model.addAttribute("greeting", "안녕하세요, " + name);
	```
	- 컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello"를 사용한다.
	
	```java
	return "hello";
	```
	
- 스프링 MVC 프레임워크에서 컨트롤러(Controller)란 간단히 설명하면 웹 요청을 처리하고 그 결과를 뷰에 전달하는 스프링 빈 객체이다.
- 스프링 컨트롤러로 사용될 클래스는 @Controller 애노테이션을 붙여야 하고, @GetMapping 애노테이션이나 @PostMapping 애노테이션과 같은 요청 매핑 애노테이션을 이용해서 처리할 경로를 지정해 주어야 한다.

#### 요청 경로 및 파라미터와 애노테이션의 관계

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image2.png)

- @RequestParam 애노테이션은 HTTP 요청 파라미터를 메서드의 파라미터로 전달받을 수 있게 해 준다. 
- @RequestParam 애노테이션의 value 속성은 HTTP 요청 파라미터의 이름을 지정하고 required 속성은 필수 여부를 지정한다. 위 그림의 경우 name 요청 파라미터의 값인 "bk"가 hello() 메서드의 name 파라미터에 전달된다.
- 파라미터로 전달받은 Model 객체의 addAttribute() 메서드는 뷰에 전달할 데이터를 지정하기 위해 사용된다. 

```java
model.addAttribute("greeting", "안녕하세요, " + name);
```

- Model@addAttribute() 메서드의 첫 번쨰 파라미터는 데이터를 식별하는데 사용되는 속성 이름이고 두 번째 파라미터는 속성 이름에 해당하는 값이다. 뷰 코드는 이 속성의 이름을 사용해서 컨트롤러가 전달한 데이터에 접근하게 된다.
- @GetMapping이 붙은 메서드는 컨트롤러의 실행 결과를 보여줄 뷰 이름을 리턴한다. 예제 코드에서는 "hello"를 뷰 이름으로 리턴했다. 이 뷰 이름은 논리적인 이름이며 실제로 뷰 이름에 해당하는 뷰 구현을 찾아주는 것은 ViewResolver가 처리한다.

- 컨트롤러를 구현했다면 컨트롤러를 스프링 빈으로 등록합니다.

#### src/main/java/config/ControllerConfig.java
 
```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.HelloController;

@Configuration
public class ControllerConfig {
	
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
```

### JSP 구현 
- 뷰 코드는 JSP를 이용해서 구현한다.
- 프로젝트 생성 과정에서 src/main/webapp/WEB-INF 폴더에 view 폴더를 만들었는데, 이 폴더에 hello.jsp 파일을 추가한다.

#### src/main/webapp/WEB-INF/view/hello.jsp

```java
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello</title>
    </head>
    <body>
        인사말 : ${greeting}
    </body>
</html>
```

- HelloController의 hello() 메서드가 리턴한 뷰 이름은 "hello" 였는데 JSP 파일의 이름을 보면 "hello.jsp"이다. 
- 뷰 이름과 JSP 파일과의 연결은 MvcConfig 클래스의 다음 설정을 통해서 이루어진다.

```java
@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
	registry.jsp("/WEB-INF/view/", ".jsp");
}
```

- registry.jsp() 코드는  JSP를 뷰 구현으로 사용할 수 있도록 해주는 설정이다. 
- jsp() 메서드의 첫 번째 인자는 JSP 파일 경로를 찾을 때 사용하는 접두어이며, 두 번째 인자는 접미사이다. 
- 뷰 이름의 앞과 뒤에 각각 접두어와 접미사를 붙여서 최종적으로 사용할 JSP 파일의 경로를 결정한다.
- 예를 들어 뷰 이름이 "hello"인 경우 사용하는 JSP 파일 경로는 다음과 같이 결정된다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image3.png)

- hello.jsp 코드를 보면 다음과 같은 JSP EL(Expression Language)을 사용했다.

```java
인사말 : ${greeting}
```

- 이 표현식의 "greeting"은 컨트롤러 구현에서 Model에 추가한 속성의 이름인 "greeting"과 동일하다. 
- 컨트롤러에서 설정한 속성을 뷰 JSP 코드에서 접근할 수 있는 이유는 스프링 MVC 프레임워크가 다음 처럼 모델에 추가한 속성을 JSP 코드에서 접근할 수 있게 HttpServletRequest에 옮겨주기 때문이다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image4.png)

- 따라서 JSP 뷰 코드를 구현할 경우 컨트롤러에서 추가한 속성의 이름을 이용해서 속성값을 응답 결과에 출력하게 된다.

## 실행하기

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image5.png)


> 톰캣에서 Maven Dependency를 인식하지 못하 실행이 안되는 경우<br>프로젝트 폴더 -> 마우스 오른쪽 버튼 -> Properties -> Deplolyment Assembly ->  Add 버튼 -> Maven Dependency 선택 -> Apply 또는 Apply and Close 버튼을 클릭하여 적용한다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image6.png)


* * * 
# 스프링 MVC 프레임워크 동작 방식


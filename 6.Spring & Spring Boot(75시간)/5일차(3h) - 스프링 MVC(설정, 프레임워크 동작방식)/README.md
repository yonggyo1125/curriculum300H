# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1mukZXBDsNe-SJQ5bcxtvJqQhI8TwsSQj?usp=sharing)


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

- pom.xml : 자바 실습 버전을 최신버전(17)로 변경합니다 
- pom.xml : javax.servlet-api, javax.servlet.jsp-api, jstl, spring-webmvc 의존성을 [mvnrepository](https://mvnrepository.com/) 에서 검색하여 다음과 같이 추가합니다.

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


> 톰캣에서 Maven Dependency를 인식하지 못하여 실행이 안되는 경우<br>프로젝트 폴더 -> 마우스 오른쪽 버튼 -> Properties -> Deplolyment Assembly ->  Add 버튼 -> Maven Dependency 선택 -> Apply 또는 Apply and Close 버튼을 클릭하여 적용한다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image6.png)


* * * 
# 스프링 MVC 프레임워크 동작 방식

```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configure.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
}
```

- 위 설정을 하면 남은 작업은 컨트롤러와 뷰 생성을 위한 JSP 코드를 작성하는 것이다.
- 개발자는 스프링 MVC가 어떻게 컨트롤러를 실행하고 뷰를 찾는지 자세히 알지 못해도 어느 정도 스프링 MVC를 이용해서 웹 어플리케이션을 개발해 나갈수 있다.

- 단순해 보이는 이 설정은 실제로 백여 줄에 가까운 설정을 대신 만들어주는데 이것 모두를 알 필요는 없다. 하지만 스프링 MVC를 구성하는 주요 요소가 무엇이고 각 구성 요소들이 서로 연결되는지 이해하면 다양한 환경에서 스프링 MVC를 빠르게 적용하는데 많은 도움이 된다.

## 스프링 MVC의 핵심 구성 요소

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image7.png)

- 그림에서\<\<spring bean\>\>이라고 표시한 것은 스프링 빈으로 등록해야 한다는 것을 의미한다. 회색 배경을 가진 구성 요소는 개발자가 구현해야 하는 요소이다. 예를 들어 컨트롤러 구성 요소는 개발자가 직접 구현해야 하고 스프링 빈으로 등록해야 한다. 앞서 구현한 HelloController가 컨트롤러에 해당한다.

- 중앙에 위치한 DispatcherServlet은 모든 연결을 담당한다. 웹 브라우저로부터 요청이 들어오면 DispatcherServlet은 그 요청을 처리하기 위한 컨트롤러 객체를 검색한다. 이때 DispatcherServlet은 직접 컨트롤러를 검색하지 않고 <b>HandlerMapping이라는 빈 객체에게 컨트롤러 검색을 요청</b>한다(2번 과정에 해당).

- HandlerMapping은 클라이언트의 요청 경로를 이용해서 이를 처리할 <b>컨트롤러 빈 객체를 DispatcherServlet에 전달</b>한다. 예를 들어 웹 요청 경로가 '/hello'라면 등록된 컨트롤러 빈 중에서 '/hello' 요청 경로를 처리할 컨트롤러를 리턴한다.

- 컨트롤러 객체를 DispatcherServlet이 전달받았다고 해서 바로 컨트롤러 객체의 메서드를 실행할 수 있는 것은 아니다. DispatcherServlet은 @Controller 애노테이션을 이용해서 구현한 컨트롤러뿐만 아니라 스프링 2.5까지 주로 사용됐던 Controller 인터페이스를 구현한 컨트롤러, 그리고 특수목적으로 사용되는 HttpRequestHandler 인터페이스를 구현한 클래스를 동일한 방식으로 실행할 수 있도록 만들어졌다.
- <b>@Controller, Controller 인터페이스, HttpRequestHandler 인터페이스를 동일한 방식으로 처리</b>하기 위해 중간에 사용되는 것이 바로 <b>HandlerAdapter 빈</b>이다.

- DispatcherServlet은 HandlerMapping이 찾아준 컨트롤러 객체를 처리할 수 있는 HandlerAdapter 빈에게 요청 처리를 위임한다(3번 과정) HandlerAdapter는 컨트롤러에 알맞은 메서드를 호출해서 요청을 처리하고(4~5번 과정) 그 결과를 DispatcherServlet에 리턴한다(6번 과정). 이때 HandlerAdapter는 컨트롤러의 처리 결과를 ModelAndView라는 객체로 변환해서 DispatcherServlet에 리턴한다.

- HandlerAdapter로부터 컨트롤러의 요청 처리 결과를 ModelAndView로 받으면 DispatcherServlet은 결과를 보여줄 뷰를 찾기 위해 ViewResolver 빈 객체를 사용한다(7번 과정). ModelAndView는 컨트롤러가 리턴한 뷰 이름을 담고 있는데 ViewResolver는 이 뷰 이름에 해당하는 View 객체를 찾거나 생성해서 리턴한다. 응답을 생성하기 위해 JSP를 사용하는 ViewResolver는 매번 새로운 View 객체를 생성해서 DispatcherServlet에 리턴한다.

- DispatcherServlet은 ViewResolver가 리턴한 View 객체에게 응답 결과 생성을 요청한다(8번 과정). JSP를 사용하는 경우 View 객체는 JSP를 실행함으로써 웹 브라우저에 전송할 응답 결과를 생성하고 이로써 모든 과정이 끝난다.

- 처리 과정을 보면 DispatcherServlet을 중심으로 HandlerMapping, HandlerAdapter, 컨트롤러, ViewResolver, View, JSP가 각자 역할을 수행해서 클라이언트의 요청을 처리하는 것을 알 수 있다. 이 중 하나라도 어긋나면 클라이언트의 요청을 처리할 수 없게 되므로 각 구성 요소를 올바르게 설정하는 것이 중요하다.

### 컨트롤러와 핸들러
- 클라이언트의 요청을 실제로 처리하는 것은 컨트롤러이고 DispatcherServlet,은 클라이언트의 요청을 전달받는 창구 역할을 한다. DispatcherServlet은 클라이언트의 요청을 처리할 컨트롤러를 찾기 위해 HandlerMapping을 사용한다. 컨트롤러를 찾아주는 객체는 ControllerMapping 타입이어야 할 것 같지만 실제로는 HandlerMapping이다.  
- 스프링 MVC는 웹 요청을 처리할 수 있는 범용 프레임워크이다. @Controller 애노테이션을 붙인 클래스를 이용해서 클라이언트의 요청을 처리하지만 원한다면 직접 만든 클래스를 이용해서 클라이언트의 요청을 처리할 수도 있다. 즉, DispatcherServlet 입장에서는 클라이언트 요청을 처리하는 객체 타입이 반드시 @Controller를 적용한 클래스일 필요는 없다. 실제로 스프링이 클라이언트의 요청을 처리하기 위해 제공하는 타입 중에는 HttpRequestHandler로 존재한다. 

- 이런 이유로 스프링 MVC에서는 웹 요청을 실제로 처리하는 객체를 핸들러(Handler)라고 표현하고 있으며 @Controller 적용 객체나 Controller 인터페이스를 구현한 객체는 모두 스프링 MVC 입장에서는 핸들러가 된다. 따라서 특정 요청 경로를 처리해주는 핸들러를 찾아주는 객체를 HandlerMapping이라고 부른다.

- DispatcherServlet은 핸들러 객체의 실제 타입에 상관없이 실행 결과를 ModelAndView라는 타입으로만 받을 수 있으면 된다. 그런데 핸들러의 실제 구현 타입에 따라 ModelAndView를 리턴하는 객체도(Controller 인터페이스를 구현한 클래스의 객체) 있고, 그렇지 않은 객체도(앞서 구현한 HelloController)있다. 따라서 핸들러의 처리 결과를 ModelAndView로 변환해주는 객체가 필요하며 HandlerAdapter가 이 변환을 처리해준다.

- 핸들러 객체의 실제 타입마다 그에 알맞은 HandlerMapping과 HandlerAdapter가 존재하기 때문에, 사용할 핸들러의 종류에 따라 해당 HandlerMapping과 HandlerAdapter를 스프링 빈으로 등록해야 한다.  물론 <b>스프링이 제공하는 설정 기능을 사용하면 이 두 종류의 빈을 직접 등록하지 않아도 된다.</b>

## DispatcherServlet과 스프링 컨테이너
- web.xml 파일을 보면 다음과 같이 DispatcherServlet의 contextConfigLocation 초기화 파라미터를 이용해서 스프링 설정 클래스 목록을 전달했다.

```xml
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
```
- DispatcherServlet은 전달받은 설정파일을 이용해서 스프링 컨테이너를 생성하는데 앞에서 언급한 HandlerMapping, HandlerAdapter 컨트롤러, ViewResolver 등의 빈은 다음처럼 DispatcherServlet이 생성한 스프링 컨테이너에서 구한다. 
- 따라서 DispatcherServlet이 사용하는 설정 파일에 이들 빈에 대한 정의가 포함되어 있어야 한다.

#### DispatcherServlet은 스프링 컨테이너를 생성하고 그 컨테이너로 부터 필요한 빈 객체를 구한다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%84%A4%EC%A0%95%2C%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20%EB%8F%99%EC%9E%91%EB%B0%A9%EC%8B%9D)/images/image8.png)

## @Controller를 위한 HandlerMapping과 HandlerAdapter
- @Controller 적용 객체는 DispatcherServlet 입장에서 보면 한 종류의 핸들러 객체이다. 
- DispatcherServlet은 웹 브라우저의 요청을 처리할 핸들러 객체를 찾기 위해 HandlerMapping을 사용하고 핸들러를 실행하기 위해 HandlerAdapter를 사용한다. 
- DispatcherServlet은 스프링 컨테이너에서 HandlerMapping과 HandlerAdapter 타입의 빈을 사용하므로 핸들러에 알맞은 HandlerMapping 빈과 HandlerAdapter 빈이 스프링 설정에 등록되어 있어야 한다. 그런데 앞서 작성한 예제를 보면 HandlerMapping이나 HandlerAdapter 클래스를 빈으로 등록하는 예제는 보이지 않는다. 단지 <b>@EnableWebMvc 애노테이션</b>만 추가 했다.

```java
@Configuration
@EnableWebMvc
public class MvcConfig {
	...
}
```

- 위 설정은 매우 다양한 스프링 빈 설정을 추가해준다. 이 설정을 사용하지 않고 설정 코드를 직접 작성하려면 백 여줄에 가까운 코드를 입력해야 한다. 
- 이 태그가 빈으로 추가해주는 클래스 중에는 @Controller 타입의 핸들러 객체를 처리하기 위한 다음의 두 클래스가 포함되어 있다(패키지 이름이 너무 길어서 org.springframework.web 부분을 o.s.w로 표현했다).
	- o.s.w.servlet.mvc.method.annotation.RequestMappingHandlerMapping
	- o.s.w.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
	
- RequestMappingHandlerMapping 애노테이션은 @Controller 애노테이션이 적용된 객체의 요청 매핑 애노테이션(@GetMapping)값을 이용해서 웹 브라우저의 요청을 처리할 컨트롤러 빈을 찾는다. 
- RequestMappingHandlerAdapter 애노테이션은 컨트롤러의 메서드를 알맞게 실행하고 그 결과를 ModelAndView 객체로 변환해서 DispatcherServlet에 리턴한다.

```java
@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name);
		return "hello";
	}
}
```
- RequestMappingHandlerAdapter 클래스는 "/hello" 요청 경로에 대해 hello() 메서드를 호출한다. 이때 Model 객체를 생성해서 첫 번째 파라미터로 전달한다. 비슷하게 이름이 "name"인 HTTP 요청 파라미터의 값을 두 번째 파라미터로 전달한다.
- RequestMappingHandlerAdapter는 컨트롤러 메서드 결과 값이 String 타입이면 해당 값을 뷰 이름으로 갖는 ModelAndView 객체를 생성해서 DispatcherServlet에 리턴한다. 이 때 첫 번째 파라미터로 전달한 Model 객체에 보관된 값도 ModelAndView에 함께 전달한다. 예제 코드는 "hello"를 리턴하므로 뷰 이름으로 "hello"를 사용한다.

## WebMvcConfigurer 인터페이스와 설정

- @EnableWebMvc 애노테이션을 사용하면 @Controller 애노테이션을 붙인 컨트롤러를 위한 설정을 생성한다. 
- 또한 @EnableWebMvc, 애노테이션을 사용하면 WebMvcConfigurer 타입의 빈을 이용해서 MVC 설정을 추가로 생성한다. 

```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
}
```
- 여기에서 설정 클래스는 WebMvcConfigurer 인터페이스를 상속하고 있다.
- @Configuration 애노테이션을 붙인 클래스 역시 컨테이너에 빈으로 등록되므로 MvcConfig 클래스는 WebMvcConfigurer 타입의 빈이 된다.
- @EnableWebMvc 애노테이션을 사용하면 WebMvcConfigurer 타입인 빈 객체의 메서드를 호출해서 MVC 설정을 추가한다.
- 예를 들어 ViewResolver 설정을 추가하기 위해 WebMvcConfigurer 타입인 빈 객체의 ConfigurerViewResolvers() 메서드를 호출한다. 따라서 WebMvcConfigurer 인터페이스를 구현한 설정 클래스는 configureViewResolvers() 메서드를 재정의해서 알맞은 뷰 관련 설정을 추가하면 된다. 

- 스프링5 버전은 자바 1.8부터 지원하는 디폴트 메서드를 사용해서 WebMvcConfigurer 인터페이스의 메서드에 기본 구현을 제공하고 있다. 
- 다음은 스프링5 버전이 제공하는 WebMvcConfigurer 인터페이스의 일부 구현 코드이다.

```java
public interface WebMvcConfigurer {
	default void configurePathMatch(PathMatchConfigurer configurer) {
	}
	
	default void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	}
	
	default void addFormatters(FormatterRegistry registry) {
	}
	
	default void addInterceptors(InterceptorRegistry registry) {
	}
	
	default void configureViewResolvers(ViewResolverRegistry registry) {
	}
	
	... 생략
}
```

- 기본 구현은 모두 빈 구현이다. 이 인터페이스를 상속한 설정 클래스는 재정의가 필요한 메서드만 구현하면 된다. 
- 앞서 예제도 모든 메서드가 아닌 두 개의 메서드만 재정의했다.


> 스프링 4.x 버전은 자바 6을 기준으로 한다. 자바 1.7 버전 까지는 인터페이스에 디폴트 메서드가 없기 때문에 설정 클래스에서 WebMvcConfigurer 인터페이스를 상속하면 인터페이스에 정의되어 있는 모든 메서드를 추가해야 했다. 이런 이유로 WebMvcConfigurer 인터페이스 대신 이 인터페이스의 기본 구현을 제공하는 WebMvcConfigurerAdapter 클래스를 상속해서 필요한 메서드만 재정의하는 방식으로 설정했다.

## JSP를 위한 ViewResolver
- 컨트롤러 처리 결과를 JSP를 이용해서 생성하기 위해 다음 설정을 사용한다.

```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {{
			registry.jsp("/WEB-INF/view/", ".jsp");
	}
}
```

- WebMvcConfigurer 인터페이스에 정의된 configureViewResolvers() 메서드는 ViewResolverRegistry 타입의 registry 파라미터를 갖는다. 
- ViewResolverRegistry#jsp() 메서드를 사용하면 JSP를 위한 ViewResolver를 설정할 수 있다.
- 위 설정은 o.s.w.servlet.view.InternalResourceViewResolver 클래스를 이용해서 다음 설정과 같은 빈을 등록한다.

```java
@Bean
public ViewResolver viewResolver() {
	InternalResouceViewResolver vr = new InternalResourceViewResolver();
	vr.setPrefix("/WEB-INF/view/")l
	vr.setSuffix(".jsp");
	return vr;
}
```

- 컨트롤러의 실행 결과를 받은 DispatcherServlet은 ViewResolver에게 뷰 이름에 해당하는 View 객체를 요청한다.
- 이때 InternalResourceViewResolver는 "prefix + 뷰 이름 + surffix"에 해당하는 경로를 뷰 코드를 사용하는 InternalResourceView 타입의 View 객체를 리턴한다. 예를 들어 뷰 이름이 "hello"라면 "/WEB-INF/view/hello.jsp" 경로를 뷰 코드로 사용하는 InternalResourceView 객체를 리턴한다. DispatcherServlet이 InternalResourceView 객체에 응답 생성을 요청하면 InternalResourceView 객체는 경로에 지정한 JSP 코드를 실행해서 응답 결과를 생성한다.

- DispatcherServlet은 컨트롤러의 실행 결과를 HandlerAdapter를 통해서 ModelAndView 형태로 받는다고 했다. Model에 담긴 값은 View 객체에 Map 형식으로 전달된다. 예를 들어 HelloController 클래스는 다음과 같이 Model에 "greeting" 속성을 설정했다.

```java
@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name);
		return "hello";
	}
}
```

- 이 경우 DispatcherServlet은 View 객체에 응답 생성을 요청할 때 greeting 키를 갖는 Map 객체를 View 객체에 전달한다. 
- View 객체는 전달받은 Map 객체에 담긴 값을 이용해서 알맞은 응답 결과를 출력한다. 
- InternalResourceView는 Map 객체에 담겨 있는 키 값을 request.setAttribute()를 이용해서 request 속성에 저장한다. 그런 뒤 해당 경로의 JSP를 실행한다.
- 결과적으로 컨트롤러에 지정한 Model 속성은 request 객체 속성으로 JSP에 전달되기 때문에 JSP는 다음과 같이 모델에 지정한 속성 이름을 사용해서 값을 사용할 수 있게 된다.

```java
<%-- JSP 코드에서 모델의 속성 이름을 사용해서 값 접근 --%>
인사말 : ${greeting}
```

## 디폴트 핸들러와 HandlerMapping의 우선순위

- web.xml 설정을 보면 DispatcherServlet에 대한 매핑 경로를 다음과 같이 '/'로 주었다.

```xml
<servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>
		org.springframework.web.servlet.DispatcherServlet
	</servlet-class>
	...  생략
</servlet>

<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

- 매핑 경로가 '/'인 경우 .jsp로 끝나는 요청을 제외한 모든 요청을 DispatcherServlet이 처리한다. 즉 /index.html이나 /css/bootstreap.css와 같이 확장자가 .jsp가 아닌 모든 요청을 DispatcherServlet이 처리하게 된다.
- 그런데 @EnableWebMvc 애노테이션이 등록하는 HandlerMapping은 @Controller 애노테이션을 적용한 빈 객체가 처리할 수 있는 요청 경로만 대응할 수 있다.
- 예를 들어 등록된 컨트롤러가 한 개 이고 그 컨트롤러가 @GetMapping("/hello") 설정을 사용한다면, /hello 경로만 처리할 수 있게 된다. 따라서 "/index.html" 이나 "/css/bootstrap.css"와 같은 요청을 처리할 수 있는 컨트롤러 객체를 찾지 못해 DispatcherServlet은 404 응답을 전송한다.

- "/index.html"이나 "/css/bootstrap.css"와 같은 경로를 처리하기 위한 컨트롤러 객체를 직접 구현할 수도 있지만, 그보다는 WebMvcConfigurer의 configureDefaultServletHandling() 메서드를 사용하는 것이 편리하다.

```java
@Configuraion
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configurerServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
```

- 위 설정에서 DefaultServletHandlerConfigurer#enable() 메서드는 다음의 두 빈 객체를 추가한다.
	- DefaultServletHttpRequestHandler
	- SimpleUrlHandlerMapping

- DefaultServletHttpRequestrHandler는 클라이언트의 모든 요청을 WAS(웹 어플리케이션 서버, 톰캣이나 웹로직 등)가 제공하는 디폴트 서블릿에 전달한다. 예를 들어 "/index.html"에 대한 처리나 DefaultServletHttpRequestHandler에 요청하면 이 요청을 다시 디폴트 서블릿에 전달해서 처리하도록 한다. 그리고 SimpleUrlHandlerMapping을 이용해서 모든 경로("/\*\*")를 DefaultServletHttpRequestHandler를 이용해서 처리하도록 설정한다.

- @EnableWebMvc 애노테이션이 등록하는 RequestMappingHandlerMapping의 적용 우선순위가 DefaultServletHandlerConfigurer@enable() 메서드가 등록하는 SimpleUrlHandlerMapping의 우선순위보다 높다. 때문에 웹 브라우저의 요청이 들어오면 DispatcherServlet은 다음과 같은 방식으로 요청을 처리한다.
	- (1) RequestMappingHandlerMapping을 사용해서 요청을 처리할 핸들러를 검색한다.
		- 존재하면 해당 컨트롤러를 이용해서 요청을 처리한다.
	- (2) 존재하지 않으면 SimpleUrlHandlerMapping을 사용해서 요청을 처리할 핸들러를 검색한다.
		- DefaultServletHandlerConfigurer#enable() 메서드가 등록한 SimpleUrlHandlerMapping은 "/\*\*" 경로(즉 모든 경로)에 대해 DefaultServletHttpRequestHandler를 리턴한다.
		- DispatcherServlet은 DefaultServletHttpRequestHandler에 처리를 요청한다.
		- DefaultServletHttpRequestHandler는 디폴트 서블릿에 처리를 위임한다.
	
- 예를 들어 "/index.html" 경로로 요청이 들어오면 (1)번 과정에서 해당하는 컨트롤러를 찾지 못하므로 (2)번 과정을 통해 디폴트 서블릿이 /index.html 요청을 처리하게 된다.

- DefaultServletHandlerConfigurer#enable() 외에 몇몇 설정도 SimpleUrlHandlerMapping을 등록하는데 DefaultServletHandlerConfigurer#enable()이 등록하는 SimpleUrlHandlerMapping의 우선순위가 가장 낮다.
- 따라서 DefaultServletHandlerConfigurer#enable()을 설정하면 별도 설정이 없는 모든 요청 경로를 디폴트 서블릿이 처리하게 된다.


## 직접 설정 예
- @EnableWebMvc 애노테이션을 사용하지 않아도 스프링 MVC를 사용할 수 있다. 
- 차이는 @EnableWebMvc 애노테이션과 WebMvcConfigurer 인터페이스를 사용할 때보다 설정해야 할 빈이 많은 것뿐이다.

```java
@Configuration
public class MvcConfig {
	@Bean 
	public HandlerMapping handlerMapping() {
		RequestMappingHandlerMapping hm = new RequestMappingHandlerMapping();
		hm.setOrder(0);
		return hm;
	}
	
	@Bean
	public HandlerAdapter handlerAdapter() {
		RequestMappingAdapter ha = new RequestMappingAdapter();
		return ha;
	}
	
	@Bean
	public HandleMapping simpleHandlerMapping() {
		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		Map<String, Object> pathMap = new HashMap<>();
		pathMap.put("/**", defaultServletHandler());
		hm.setUrlMap(pathMap);
		return hm;
	}
	
	@Bean
	public HttpRequestHandler defaultServletHandler() {
		DefaultServletHttpRequestHandler handler = new DefaultServletHttpRequestHandler();
		return handler;
	}
	
	@Bean
	public HandlerAdapter requestHandlerAdapter() {
		HttpRequestHandlerAdapter ha = new HttpRequestHandlerAdapter();
		return ha;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/view/");
		vr.setSuffix(".jsp");
		return vr;
	}
}
```


## 정리
- <b>DispatcherServlet</b> : 웹 브라우저의 요청을 받기 위한 창구 역할을 하고, 다른 주요 구성 요소들을 이용해서 요청 흐름을 제어하는 역할을 한다.
- <b>HandlerMapping</b> : 클라이언트의 요청을 처리할 핸들러 객체를 찾아준다. 핸들러(커맨드) 객체는 클라이언트의 요청을 실제로 처리한 뒤 뷰 정보와 모델을 설정한다.
- <b>HandlerAdapter</b> : DispatcherServlet과 핸들러 객체 사이의 변환을 알맞게 처리해 준다.
- <b>ViewResolver</b> : 요청 처리 결과를 생성할 View를 찾아주고 View는 최종적으로 클라이언트에 응답을 생성해서 전달한다. 
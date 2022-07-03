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
                config.memberConfig
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
	

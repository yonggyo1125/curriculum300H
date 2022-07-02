# 스프링 MVC 

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


## 스프링 MVC 프레임워크 동작 방식
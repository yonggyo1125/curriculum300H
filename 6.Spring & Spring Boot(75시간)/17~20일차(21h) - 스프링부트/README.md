# 개발환경 구축

## 스프링 부트의 특징

- 내장 서버를 이용해 별도의 설정 없이 독립 실행이 가능한 스프링 애플리케이션
- 톰캣, 제티 또는 언더토우와 같은 웹 애플리케이션(WAS) 자체 내장

	- 스프링 부트는 디폴트 내장 서버로 톰캣 Tomcat을 사용하고 있습니다. 
	- 내장 웹 서버에 대한 설정을 자동으로 처리하기 때문에 스프링 부트 사용자는 웹 서버와 관련된 설정을 하지 않아도 프로젝트 내부에 포함하게 됩니다. 
	- 제티(Jetty)나 언더토우(Undertow)와 같은 내장 웹 서버를 사용하기 위해서는 pom.xml에 설정 값을 작성하는 것만으로 쉽게 변경이 가능합니다.
	
- 빌드 구성을 단순화하기 위한 <b>Spring Boot Starter</b> 의존성 제공

	- 스프링 부트에서 스타터(stater) 설정을 자동화해주는 모듈을 의미합니다. 
	- 프로젝트에서 설정해야 하는 다양한 의존성을 사전에 미리 정의해서 제공합니다. 
	- 프로젝트에서 설정해야 하는 다수의 의존성들을 스타터가 이미 포함하고 있기 때문에 스타터에 대한 의존성만 추가하면 프로젝트를 쉽게진행할 수 있습니다.

- XML 설정 없이 단순 자바 수준의 설정 방식 제공
	- 스프링 부트는 XML에 설정을 작성할 필요 없이 자바 코드로 설정할 수 있습니다. 
	- XML은 문법이틀리거나 선언이 선언을 잘못하면 원인을 찾기 힘듭니다. 
	- 자바 코드는 컴파일러의 도움을 받기 때문에 오타 등의 설정 정보 오류를 미리 알 수 있습니다. 
	- 또한 클래스 단위로 설정하기 때문에 쉽게 관리할 수 있습니다.
	
- JAR를 이용해 자바 옵션만으로 배포 가능
- 애플리케이션의 모니터링 및 관리를 위한 스프링 액추에이터 제공
	- 서비스를 운영하려면 서비스 개발뿐 아니라 서비스가 정상적으로 동작하고 있는지 상태를 모니터링해야 합니다. 
	- 스프링 액추에이터(Spring Actuator)는 스프링 부트 애플리케이션에서 제공하는 여러 가지정보를 손쉽게 모니터링 할 수 있도록 도와주는 라이브러리입니다. 

## JDK 설치

- [자바설치하기](https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95#%EC%9E%90%EB%B0%94-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0)

## MySQL 설치 

- [SQL 실습 환경 구성](https://github.com/yonggyo1125/curriculum300H/tree/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)#sql-%EC%8B%A4%EC%8A%B5-%ED%99%98%EA%B2%BD-%EA%B5%AC%EC%84%B1)


## STS(Spring Tool Suite)

- STS(Spring Tool Suite)는 이클립스기반의 스프링에 최적화된 IDE입니다.

- [다운로드](https://spring.io/tools)

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image1.png)


## 스프링 프레임워크 API 문서

- [스프링 프레임워크 API 문서](https://docs.spring.io/spring-framework/docs/current/javadoc-api/)

* * * 
# 기본 설정 하기

##  애플리케이션 실행하기

###  Spring Boot Project 생성하기

- File -> New -> Spring Starter Project를 선택합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image2.png)

- Name에는 적절한 프로젝트 명을 입력합니다. 여기에 입력한 명칭이 기본적으로 Artifact로 완성이 되는데, ArtifactId는 변경이 가능합니다.

- Group, Description, Package를 적절하게 입력합니다. Package는 일반적으로 groupId + artifactId를 합친 형태로 설정을 많이 합니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image3.png)

- 적절한 Spring Boot 버전을 설정하고(기본적으로 중간 버전으로 선택이 됩니다)
- 초기 프로젝트 생성시에 함께 설치할 의존성을 Available에서 검색하여 체크 합니다.
- web을 선택하여 Spring Web을 선택합니다(더 필요한 것이 있다면 더 체크하여 함께 설치 합니다).

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image4.png)

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image5.png)

- Spring Boot 프로젝트가 생성되면 다음과 같이 디렉토리 구조가 생성이 됩니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image6.png)


### 빌드 도구
- 메이븐이란 자바 프로젝트의 빌드를 자동화해주는 빌드 툴입니다. 개발 과정 중에 많은 라이브러리들이 필요한데 pom.xml 파일에 필요한 라이브러리를 적어주면 메이븐이 알아서 네트워크를 통해서 다운로드하고 경로를 지정해 줍니다.
- 메이븐 같은 빌드 툴이 없었다면 필요한 jar 파일들을 일일이 받아서 직접 프로젝트에 넣어줘야 했을 것입니다.

#### pom.xml

- 스프링부트 최상위 모듈로서 스프링부트에 필요한 의존성(dependency)를 자동으로 추가

```xml
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.7.2</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>
```

- 웹 애플리케이션에 필요한 라이브러리

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- Spring Test Framework 라이브러리

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

- Maven Dependencies를 클릭하면 pom.xml에 추가한 의존성과 연관된 의존성이 모두 설치된 것을 확인할 수 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image7.png)


### 설정 파일(application.properties)

- 스프링 부트 애플리케이션 실행 시 사용하는 여러가지 설정값들을 정의하는 파일입니다.
- <b>src/main/resources</b> 폴더 아래 자동으로 생성되며 바로 설정 파일로 이용이 가능합니다. 만약 자동으로 생성되지 않았다면 직접 생성해도 됩니다.\

- 개발 환경, 테스트 환경, 운영 환경에 따라서 연결해야 할 데이터베이스, port, debug, level 등을 나눠야 한다면 다음 명명 규칙으로 설정 파일을 만듭니다.

```
application-{profile}.properties
```

- 예를 들면, 개발 환경 설정 파일은 application-dev.properties로 만들고 운영 환경의 설정 파일은 application-prod.properties로 만듭니다.
- 실행되는 환경에 따라서 어떤 설정 파일을 사용할지 jar 파일 실행 시 VM 옵션 등을 통해 지정할 수 있습니다.
- 또한 application.properties에 설정해 둔 값을 자바 코드에 사용해야 한다면 @Value 애노테이션을 통해서 읽어올 수 있습니다.

#### application.properties 설정하기
```
server.port=80
application.name=project1
```

- 애플리케이션을 실행할 포트를 설정합니다. 따로 설정하지 않으면 기본 포트는 8080입니다. 80포트는 url 뒤에 포트 번호를 생략할 수 있습니다.
- 애플리케이션의 이름을 "project1"로 설정했습니다. 설정해둔 애플리케이션의 값을 읽어와서 자바 코드에서 사용해야 한다면 @Value 애노테이션을 통해서 읽어올 수 있습니다.

### Hello World 출력하기

#### src/java/main/com/codefty/project1/Project1Application.java

```java
package com.codefty.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World";
	}
}
```

#### @RestController

- @RestController는 Restful Web API를 좀 더 쉽게 만들기 위해 스프링 프레임워크 4.0에 도입된 기능입니다. 
- @Controller와 @ResponseBody를 합쳐 놓은 애노테이션입니다. 
- 클래스 이름 위에 @Controller 애노테이션을 선언하면 해당 클래스를 요청을 처리하는 컨트롤러로 사용합니다. 
- @ResponseBody 애노테이션은 자바 객체를 HTTP 응답 본문의 객체로 변환해 클라이언트에게 전송합니다. 이를 통해 따로 html 파일을 만들지 않아도 웹 브라우저에 "Hello World" 라는 문자열을 출력할 수 있습니다.


#### @GetMapping

- 컨트롤러 클래스에 @GetMapping 어노테이션을 이용해 클라이언트의 요청을 처리할 URL을 매핑합니다. 
- 현재는 서버의 루트로 오는 요청을 처리할 수 있도록 "/"로 선언했습니다.


#### 웹서버 시작 및 프로젝트 실행

- STS 오른쪽 하단 Boot Dashboard의 local을 더보기 버튼을 클릭하여 열고 project1을 선택합니다.
- 시작 버튼을 클릭하고 시작이 완료 되면 웹브라우저에 <b>localhost</b>라고 입력합니다. 

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image8.png)

- 실행 결과

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image9.png)

## Lombok 라이브러리

- Lombok 라이브러리는 반복적인 Getter/Setter, ToString과 같은 반복적인 자바 코드를 컴파일할 때 자동으로 생성해주는 라이브러리 입니다.
- Lombok 라이브러리를 사용하면 반복적인 소스코드를 제거할 수 있으므로 코드를 좀 더 깔끔하게 작성할 수 있습니다.

- Lombok을 사용하기 위해서 [mvnrepository](https://mvnrepository.com/) 에서 lombok을 검색하여 pom.xml에 의존성을 다음과 같이 추가합니다.

#### pom.xml

```xml
<dependencies>
... 생략 
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
	</dependency>
... 생략 
</dependencies>
```

#### Lombok 라이브러리에서 자주 사용하는 애노테이션

|애노테이션|설명|
|----|-------|
|@Getter/Setter|코드를 컴파일할 때 속성들에 대한 Getter/Setter 메서드 생성|
|@ToString|toString() 메서드 생성|
|@ToString(exclude={"변수명"})|원하지 않은 속성을 제외한 toString() 메소드 생성|
|@NonNull|해당 변수 null 체크, NullPointException 예외 발생|
|@EqualsAndHashCode|equals()와 hashCode() 메서드 생성|
|@Builder|빌더 패턴을 이용한 객체 생성|
|@NoArgsConstructor|파라미터가 없는 기본 생성자 생성|
|@AllArgsConstructor|모든 속성에 대한 생성자 생성|
|@RequiredArgsConstructor|초기화되지 않은 @NonNull 애노테이션이 붙은 필드에 대한 생성자 생성|
|@Log|log 변수 자동 생성|
|@Value|불변(immutable)클래스 생성|
|@Data|@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor를 합친 애노테이션|

### Lombok 라이브러리 적용하기

#### src/java/main/com/codefty/project1/MemberDto.java

```java
package com.codefty.project1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
	private int memNo;
	private String memId;
	private String memNm;
}
```

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image10.png)

#### src/java/main/com/codefty/project1/TestController.java

```java
package com.codefty.project1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public MemberDto test() {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemNo(Long.valueOf(1));
		memberDto.setMemId("user1");
		memberDto.setMemNm("사용자명");
		
		return memberDto;
	}
}
```

- 실행 결과

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image11.png)

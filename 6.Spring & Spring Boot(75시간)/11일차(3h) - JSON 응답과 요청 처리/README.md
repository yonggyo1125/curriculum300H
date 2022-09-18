# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1xgCfkaBhtddM-tMvDL915c3qIhxKVRKk?usp=sharing)

# JSON 응답과 요청 처리

- 웹 페이지에서 Ajax를 이용해서 서버 API를 호출하는 사이트가 많다. 이들 API는 웹 요청에 대한 응답으로 HTML 대신 JSON이나 XML을 사용한다.
- 웹 요청에도 쿼리 문자열대신에 JSON이나 XML을 데이터로 보내기도 한다. 
- GET이나 POST만 사용하지 않고PUT, DELETE와 같은 다른 방식도 사용한다. 
- 스프링 MVC를 사용하면 이를 위한 웹 컨트롤러를 쉽게 만들 수 있다. 

## 프로젝트 준비
- 스프링 MVC : 날짜 값 변환, @PathVariable, 컨트롤러 예외 처리 에서 사용한 [예제소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/%ED%95%99%EC%8A%B5%20%EC%98%88%EC%A0%9C) 를 이어서 사용


## JSON 개요
- JSON(JavaScript Object Notation)은 간단한 형식을 갖는 문자열로 데이터 교환에 주 로 사용한다. 다음은 JSON 형식으로 표현한 데이터의 예이다.

```
{
	"name": "유관순",
	"birthday": "1902-12-16",
	"age": 17,
	"related": ["남동순", " 류예도"]
}
```

- JSON 규칙은 간단하다. 중괄호를 사용해서 객체를 표현한다. 객체는 (이름, 값) 쌍을 갖는다. 이때 이름과 값은 콜론(:)으로 구분한다. 위 예의 경우 이름이 name인 데이터의 값은 "유관순"이다. 값에는 다음이 올 수 있다.

	- 문자열, 숫자, 불리언, null
	- 배열
	- 다른 객체

- 문자열은 큰따옴표나 작은따옴표 사이에 위치한 값이다. 문자열은 "(큰따옴표), \n(뉴라인), \r(캐리지 리턴), \t(탭)과 같이 역슬래시를 이용해서 특수 문자를 표시할 수 있다.

- 숫자는 10진수 표기법(예, 1.5 또는 101)이나 지수 표기법(예, 1.07e2)을 따른다. 불리언 타입 값은 true와 false가 있다.

- 배열은 대괄호로 표현한다. 대괄호 안에 콤마로 구분한 값 목록을 갖는다. 위 예에서 related 배열은 문자열 값 목록을 갖고 있고 edu 배열은 객체를 값 목록으로 갖고 있다.

- JSON에 대한 더 정확한 문법은 https://www.json.org/json-ko.html 사이트를 참고한다.

## Jackson 의존 설정

- Jackson은 자바 객체와 JSON 형식 문자열 간 변환을 처리하는 라이브러리이다. 
- 스프링 MVC에서 Jackson 라이브러리를 이용해서 자바 객체를 JSON으로 변환하려면 클래스패스에 Jackson 라이브러리를 추가하면 된다. 
- 이를 위해 pom.xml 파일에 Jackson 관련 의존을 추가한다.

- pom.xml : jackson-databind, jackson-datatype-jsr310 의존성을 [mvnrepository](https://mvnrepository.com/) 에서 검색하여 다음과 같이 추가합니다.

```java
... 생략 
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
	<version>2.13.3</version>
</dependency>
<dependency>
	<groupId>com.fasterxml.jackson.datatype</groupId>
	<artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.13.3</version>
</dependency>
<dependency>
	<groupId>javax.validation</groupId>
	<artifactId>validation-api</artifactId>
	<version>2.0.1.Final</version>
</dependency>
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-validator</artifactId>
	<version>5.4.3.Final</version>
</dependency>
```
- jackson-databind : Jackson core와 Jackson Annotation 의존 추가
- jackson-datatype-jsr310 : Java1.8 data/time 지원 위한 Jackson 모듈


> Jackson에 대한 자세한 설명은 https://github.com/FasterXML/jackson-docs 사이트를 참고한다

- Jackson은 자바 객체와 JSON 간의 변환을 처리한다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image1.png)

- Jackson은 프로퍼티(get 메서드 또는 설정에 따라 필드)의 이름과 값을 JSON 객체의 (이름, 값) 쌍으로 사용한다. 
- 위 그림에서 Person 객체의 name 프로퍼티 값이 “이름”이라고 할 때 생성되는 JSON 형식 데이터는 이름이 "name"이고 값이 "이름"인 데이터를 갖는다. 프로퍼티 타입이 배열이나 List인 경우 JSON 배열로 변환된다. 

## @RestController로 JSON 형식 응답

- 스프링 MVC에서 JSON 형식으로 데이터를 응답하는 것은 매우 간단하다. 
- @Controller 애노테이션 대신 @RestController 애노테이션을 사용하면 된다. 

#### src/main/java/controller/RestMemberController.java

```java
package controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;

@RestController
public class RestMemberController {
	private MemberDao memberDao;
	private MemberRegisterService registerService;
	
	@GetMapping("/api/members")
	public List<Member> members() {
		return memberDao.selectAll();
	}
	
	@GetMapping("/api/members/{id}")
	public Member member(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Member member = memberDao.selectById(id);
		if (member == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return member;
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setRegisterService(MemberRegisterService registerService) {
		this.registerService = registerService;
	}
}
```

- 기존 컨트롤러 코드와 다른 점은 다음과 같다.
	- @Controller 애노테이션 대신 @RestController 애노테이션 사용
	- 요청 매핑 애노테이션 적용 메서드의 리턴 타입으로 일반 객체 사용

- @RestController 애노테이션을 붙인 경우 스프링 MVC는 요청 매핑 애노테이션을 붙인 메서드가 리턴한 객체를 알맞은 형식으로 변환해서 응답 데이터로 전송한다. 
- 이때 클래스 패스에 Jackson이 존재하면 JSON 형식의 문자열로 변환해서 응답한다. 예를 들어 members() 메서드는 리턴 타입이 List\<Member\> 인데 이 경우 해당 List 객체를 JSON 형식의 배열로 변환해서 응답한다.

- RestMemberController 클래스를 ControllerConfig 클래스에 추가하자.

```
... 생략

import controller.RestMemberController;

@Configuration
public class ControllerConfig {
	
	... 생략
	
	@Bean
	public RestMemberController restApi() {
		RestMemberController cont = new  RestMemberController();
		cont.setMemberDao(memberDao);
		cont.setRegisterService(memberRegSvc);
		return cont;
	}
}
```

- 톰캣을 실행하고 웹 브라우저에서 "http://localhost:8080/.../api/members" 주소를 입력해서 결과를 확인해 보자. 인터넷 익스플로러에서 실행하면 파일 다운로드가 뜰 수도 있으니 크롬이나 파이어폭스를 사용하자.
- 크롬 브라우저에 json-formatter 확장 프로그램을 설치한 뒤에 결과를 보면 다음과 같이 보기 좋게 JSON 데이터를 표시해준다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image2.png)

#### @RestController 애노테이션과 @ResponstBody 애노테이션

- @RestController 애노테이션이 추가되기 전에는 다음과 같이 @Controller 애노테이션과 @ResponseBody 애노테이션을 사용했다.

```java
@Controller
public class RestMemberController {
	private MemberDao memberDao;
	private MemberRegisterService registerService;
	
	@RequestMapping(path="/api/members", method = RequestMethod.GET)
	@RequestBody
	public List<Member> members() {
		return memberDao.selectAll();
	}
}
```

- 스프링4 버전 부터 @RequestController 애노테이션이 추가되면서 @ResponseBody 애노테이션의 사용 빈도가 줄었다.

### @Jsonlgnore를 이용한 제외 처리

- 위 코드의 응답 결과에 password가 포함되어 있다. 보통 암호와 같이 민감한 데이터는 응답 결과에 포함시키면 안되므로 password 데이터를 응답 결과에서 제외시켜야 한다. 
- Jackson이 제공하는 @JsonIgnore 애노테이션을 사용하면 이를 간단히 처리할 수 있다. 다음과 같이 JSON 응답에 포함시키지 않을 대상에 @JsonIgnore 애노테이션을 붙인다.

#### src/main/java/spring/Member.java

```java
... 생략

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Member {
	
	... 생략
	
	@JsonIgnore
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	... 생략 
}
```

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image3.png)


### 날짜 형식 변환 처리: @JsonFormat 사용

- registerDateTime의 값은 [2018, 3, 1, 11, 7, 49]이다. Member 클래스의 registerDateTime 속성은 LocalDateTime 타입인데 JSON 값은 배열로 바뀌었다.
- 만약 registerDateTime 속성이 java.util.Date 타입이면 다음과 같이 유닉스 타임 스탬프로 날짜 값을 표현한다.

```
{
	"id": 1,
	"email": "yonggyo00@naver.com",
	"name": "테스트",
	"registerDateTime": 1519870069000
}
```
> 유닉스 타임 스탬프는 1970년 1월 1일 이후 흘러간 시간을 말한다. 보통 초 단위로 표현하나 Jackson은 별도 설정이 없으면 밀리초 단위로 값을 변환한다. System.currentTimeMillis() 메서드가 리턴하는 정수도 유닉스 타임 스탬프 값이다.

- 보통 날짜나 시간은 배열이나 숫자보다는 "2022-07-04 23:24:15"와 같이 특징 형식을 갖는 문자열로 표현하는 것을 선호한다. 
- Jackson에서 날짜나 시간 값을 특징한 형식으로 표현하는 가장 쉬운 방법은 @JsonFormat 애노테이션을 사용하는 것이다. 
- 예를 들어 ISO-8601 형식으로 변환하고 싶다면 다음과 같이 shape 속성 값으로 Shape STRING을 갖는 @JsonFormat 애노테이션을 변환 대상에 적용하면 된다.

#### src/main/java/spring/Member.java

```java

... 생략

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Member {

	... 생략 
	
	@JsonFormat(shape = Shape.STRING)  // ISO-8601 형식으로 변환
	private LocalDateTime registerDateTime; 
	
	... 생략
}
```

- 다음 코드는 위 애노테이션을 사용했을 때 출력 형식을 보여준다. ISO-8601 형식을 사용해서 registerDateTime을 문자열로 표시하고 있다.

```json
{
	"id": 1,
	"email": "yonggyo00@naver.com",
	"name": "테스트",
	"registerDateTime": "2022-07-04T23:24:15"
}
```

- ISO-8601 형식이 아닌 원하는 형식으로 변환해서 출력하고 싶다면 @JsonFormat 애노테이션의 pattern 속성을 사용한다. 

#### src/main/java/spring/Member.java

```java

... 생략 

import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Member {

	... 생략
	
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime registerDateTime; 
	
	... 생략
}
```

- 위 설정을 적용한 결과로 생성되는 JSON 응답은 다음과 같다.

```json
{
"id": 1,
"email": "yonggyo00@naver.com",
"name": "테스트",
"registerDateTime": "20220704232415"
},
```

- pattern 속성은 java.time.format.DateTimeFormatter 클래스나 java.text.SimpleDateFormat 클래스의 API 문서에 정의된 패턴을 따른다.

### 날짜 형식 변환 처리 : 기본 적용 설정

- 날짜 형식을 변환할 모든 대상에 @JsonFormat 애노테이션을 붙여야 한다면 상당히 귀찮다. 
- 이런 귀찮음을 피하려면 날짜 타입에 해당하는 모든 대상에 동일한 변환 규칙을 적용할 수 있어야 한다. 
- @JsonFormat 애노테이션을 사용하지 않고 Jackson의 변환 규칙을 모든 날짜 타입에 적용하려면 스프링 MVC 설정을 변경해야 한다.

- 스프링 MVC는 자바 객체를 HTTP 응답으로 변환할 때 HttpMessageConverter라는 것을 사용한다. 예를 들어 Jackson을 이용해서 자바 객체를 JSON으로 변환할 때에는 MappingJackson2HttpMessageConverter를 사용하고 Jaxb를 이용해서 XML로 변환할 때에는 Jaxb2RootElementHttpMessageConverter를 사용한다. 
- 따라서 JSON으로 변환할 때 사용하는 MappingJackson2HttpMessageConverter를 새롭게 등록해서 날짜 형식을 원하는 형식으로 변환하도록 설정하면 모든 날짜 형식에 동일한 변환 규칙을 적용할 수 있다.

#### MappingJackson2HttpMessageConverter를 설정하도록 수정한 MvcConfig 클래스

```java
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faterxml.jackson.databind.SerializationFeature;
... 생략

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	... 생략
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
			.json()
			.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
}
```

- extendMessageConverters() 메서드는 WebMvcConfigurer 인터페이스에 정의된 메서드로서 HttpMessageConverter를 추가로 설정할 때 사용한다.@EnableWebMvc 애노테이션을 사용하면 스프링 MVC는 여러 형식으로 변환할 수 있는 HttpMessageConverter를 미리 등록한다. extendMessageConverters()는 등록된HttpMessageConverter 목록을 파라미터로 받는다. 


- 미리 등록된 HttpMessageConverter에는 Jackson을 이용하는 것도 포함되어 있기 때문에 새로 생성한 HttpMessageConverter는 목록의 제일 앞에 위치시켜야 한다. 그래야가장 먼저 적용된다. 이를 위해 새로운 HttpMessageConverter를 0번 인덱스에 추가했다.

- 설정 코드에서 주의깊게 볼 점은 다음 코드이다. 이 코드는 JSON으로 변환할 때 사용할 ObjectMapper를 생성한다. 참고로 19행의 Jackson2ObjectMapperBuilder는ObjectMapper를 보다 쉽게 생성할 수 있도록 스프링이 제공하는 클래스이다. 위 설정코에서는 Jackson이 날짜 형식을 출력할 때 유닉스 타임 스탬프로 출력하는기능을 비활성화한다. 이 기능을 비활성화하면 ObjectMapper는 날짜 타입의 값을 ISO-8601 형식으로 출력한다.

```java
ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
	.json()
	.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
	.build();
```

- 새로 생성한 ObjectMapper를 사용하는 MappingJackson2HttpMessageConverter 객체를 converters의 첫 번째 항목으로 등록하면 설정이 끝난다.
- 앞서 Member 클래스에 적용했던 @JsonFormat 코드를 삭제하고 서버를 재시작한 뒤 웹 브라우저에서 읽어보자. 날짜 타입 값이 ISO-8601 형식으로 출력되는 것을 확인할 수 있을 것이다.

- 모든 java.util. Date 타입의 값을 원하는 형식으로 출력하도록 설정하고 싶다면 Jackson20bjectMapperBuilder#simpleDateFormat() 메서드를 이용해서 패턴을 지정한다.

```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	... 생략
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?> converters) {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
			.json()
			.simpleDateFormat("yyyyMMddHHmmss")  // Date를 위한 변환 패턴
			.build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
}
```
- Jackson2ObjectMapperBuilder#simpleDateFormat() Date 타입을 변환할 때 사용할 패턴을 지정해도 LocalDateTime 타입 변환에는 해당 패턴을 사용하지 않는다. 대신 LocalDateTime 타입은 ISO-8601 형식으로 변환한다.

- 모든 LocalDateTime 타입에 대해 ISO-8601 형식 대신 원하는 패턴을 설정하고 싶다면 다음과 같이 serializerByType() 메서드를 이용해서 LocalDateTime 타입에 대한JsonSerializer를 직접 설정하면 된다.

```java
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	... 생략
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverters<?>> converters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
				.json()
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
				.build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
}
```
> MappingJackson2HttpMessageConverler가 사용할 ObjectMapper 자체에 시간 타입을 위한 변환 설정을 추가해도 개별 속성에 적용한 @JsonFormat 애노테이션 설정이 우선한다.

### 응답 데이터의 컨텐츠 형식

- 크롬 브라우저에서 개발자도구를 실행하고(단축키: Ctrl + Shift + J 또는 F12) JSON 응답을 제공하는 API를 호출해보자. 개발자 도구의 네트워크 탭을 보면 다음과 같이 응답 헤더의 Content-Type이 application/json인 것을 알 수 있다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image4.png)

- 또한 문자 인코딩으로는 UTF-8을 사용한 것도 확인할 수 있다.

## @RequestBody JSON 요청 처리

- 지금까지 응답을 JSON으로 변환하는 것에 대해 살펴봤다. 이제 반대로 JSON 형식의 요청 데이터를 자바 객체로 변환하는 기능에 대해 살펴보자. 
- POST 방식이나 PUT 방식을 사용하면 name=이름&age=17과 같은 쿼리 문자열 형식이 아니라 다음과 같은 JSON 형식의 데이터를 요청 데이터로 전송할 수 있다.

```json
{ "name": "이름", "age": 17 }
```

- JSON 형식으로 전송된 요정 데이터를 커맨드 객체로 전달받는 방법은 매우 간단하다. 커맨드 객체에 @RequestBody 애노테이션을 붙이기만 하면 된다. 

#### src/main/java/controller/RestMemberController.java

```java
package controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

... 생략

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

... 생략

import spring.RegisterRequest;
import spring.DuplicateMemberException;

@RestController
public class RestMemberController {
	
	... 생략
	
	@PostMapping("/api/members")
	public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {
		try {
			Long newMemberId = registerService.regist(regReq);
			response.setHeader("Location", "/api/members/" + newMemberId);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (DuplicateMemberException dupEx) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
	}
	
	... 생략 
}
```

- @RequestBody 애노테이션을 커맨드 객체에 붙이면 JSON 형식의 문자열을 해당 자바 객체로 변환한다. 예를 들어 다음과 같은 JSON 데이터를 RegisterRequest 타입 객체로 변환할 수 있다.

```
{
	"email": "yonggyo00@naver.com",
	"password": "1234",
	"confirmPassword": "1234",
	"name": "테스트"
}
```

- 스프링 MVC가 JSON 형식으로 전송된 데이터를 올바르게 처리하려면 요청 컨텐츠 타입이 application/json이어야 한다. 
- 보통 POST 방식의 폼 데이터는 쿼리 문자열인 "p1=v1&p2=v2" 로 전송되는데 이때 컨텐츠 타입은 application/x-www-form-urlencoded이다. 
- 쿼리 문자열 대신 JSON 형식을 사용하려면 application/json 타입으로 데이터를 전송할 수 있는 별도 프로그램이 필요하다. 
- 크롬 브라우저에는 [Advanced REST client 확장 프로그램](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo) 이나 Postman 등 JSON 형식의 데이터를 보낼 수 있는 확장 프로그램이 존재한다. 명령행 프롬프트에서는 httpie와 같은 콘솔 프로그램을 설치해서 확인할 수도 있다. 


- "http://localhost:8080/.../api/members" 에 POST 방식으로 알맞은 JSON 데이터를 전송하자. 다음은 전송 결과를 보여준다.


![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image5.png)

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image6.png)

- RestMemberController 클래스의 newMember() 메서드는 회원 가입을 정상적으로 처리하면 응답 코드로 201(CREATED)을 전송한다.

- 처리결과를 보면 응답 상태 코드가 201 인 것을 알 수 있다. 또한 "Location" 헤더를 응답에 추가하는데 Location 헤더가 응답 결과에 포함되어 있다.

```java
@PostMapping("/api/members")
public void newMember(@RequestBody RegisterRequest regReq, HttpServletResponse response) throws IOException {
	try {
		Long newMemberId = registerService.regist(regReq);
		response.setHeader("Location", "/api/members/" + newMemberId);
		response.setStatus(HttpServletResponse.SC_CREATED);
	} catch (DuplicateMemberException dupEx) {
		response.sendError(HttpServletResponse.SC_CONFLICT);
	}
}
```

- 중복된 ID를 전송한 경우 응답 상태 코드로 409(CONFLICT)를 리턴한다. JSON 데이터의 email 속성 값으로 이미 존재하는 이메일 주소를 입력해서 POST 요청을 전송해보자. 다음과 같이 응답 상태 코드가 409 인 것을 알 수 있다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image7.png)

### JSON 데이터의 날짜 형식 다루기

- JSON 형식의 데이터를 날짜 형식으로 변환하는 방법을 살펴보자. 별도 설정을 하지 않으면 다음 패턴(시간대가 없는 JSR-8601 형식)의 문자열을 LocalDateTime과 Date로 변환한

```
yyyy-MM-ddTHH:mm:ss
```

- 특정 패턴을 가진 문자열을 LocalDateTime이나 Date 타입으로 변환하고 싶다면 @JsonFormat 애노테이션의 pattern 속성을 사용해서 패턴을 지정한다.

```java
@JsonFormat(pattern = "yyyyMMddHHmmss")
private LocalDateTime birthDateTime;

@JsonFormat(pattern = "yyyyMMdd HHmmss")
private Date birthDate;
```

- 특정 속성이 아니라 해당 타입을 갖는 모든 속성에 적용하고 싶다면 스프링 MVC 설정을 추가하면 된다. 다음은 설정 예이다.

```java
@Configuration
@EnableWebMvc
public class McvConfig implements WebMvcConfigurer {
	... 생략
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverters<?>> converters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
			.json()
			.featureToEnable(SerializationFeature.INDENT_OUTPUT)
			.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserialize(formatter))
			.simpleDateFormat("yyyyMMdd HHmmss")
			.build();
		
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
}
```

- deserializerByType()는 JSON 데이터를 LocalDateTime 타입으로 변환할 때 사용할 패턴을 지정하고 simpleDateFormat()은 Date 타입으로 변환할 때 사용할 패턴을 지정한다.
- simpleDateFormat()은 Date 타입을 JSON 데이터로 변환할 때에도 사용된다는 점에 유의한다.

### 요청 객체 검증하기

-  newMember() 메서드를 다시 보자. 자세히 보면 regReq 파라미터에@Valid 애노테이션이 붙어 있다.

```java
@PostMapping("/api/members")
public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {
	... 생략
}
```

- JSON 형식으로 전송한 데이터를 변환한 객체도 동일한 방식으로 @Valid 애노테이션이나 별도 Validator를 이용해서 검증할 수 있다. @Valid 애노테이션을 사용한 경우 검증에 실패하면 400(Bad Request) 상태 코드를 응답한다.

- Validator를 사용할 경우 다음과 같이 직접 상태 코드를 처리해야 한다.

```java
@PostMapping("/api/members")
public void newMember(@RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response) throws IOException {
	try {
		new RegisterRequestValidator().validate(regReq, errors);
		if (errors.hasErrors()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		... 
	} catch (DuplicateMemberException dupEx) {
		response.sendError(HttpServletResponse.SC_CONFLICT);
	}
}
```

## ResponseEntity로 객체 리턴하고 응답 코드 지정하기

- 지금까지 예제 코드는 상태 코드를 지정하기 위해 HttpServletResponse의 setStatus()메서드와 sendError() 메서드를 사용했다.

```java

```

- 문제는 위와 같이 HttpServletResponse를 이용해서 404 응답을 하면 JSON 형식이 아닌 서버가 기본으로 제공하는 HTML을 응답 결과로 제공한다는 점이다. 예를 들어 위 코드는 ID에 해당하는 Member가 존재하면 해당 객체를 리턴하고 존재하지 않으면 404 응답을 리턴한다. 존재할 때는 다음과  같이 JSON 결과를 응답하는데, 존재하지 않을 때는 오른쪽과 같이 HTML 결과를 응답한다.


![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image9.png)


![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image10.png)

- API를 호출하는 프로그램 입장에서 JSON 응답과 HTML 응답을 모두 처리하는 것은 부담스럽다. 404나 500과 같이 처리에 실패한 경우 HTML 응답 데이터 대신에 JSON 형식의 응답 데이터를 전송해야 API 호출 프로그램이 일관된 방법으로 응답을 처리할 수있을 것이다.

### ResponseEntity를 이용한 응답 데이터 처리

- 정상인 경우와 비정상인 경우 모두 JSON 응답을 전송하는 방법은 ResponseEntity를사용하는 것이다.
- 먼저 에러 상황일 때 응답으로 사용할 ErrorResponse 클래스를 다음과 같이 작성하자.

#### src/main/java/controller/ErrorResponse.java

```java
package controller;

public class ErrorResponse {
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
```

- ResponseEntity를 이용하면 member() 메서드를 다음과같이 구현할 수 있다.

```java

... 생략

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import spring.Member;

... 생략

@RestController
public class RestMemberController {
	
	... 생략
	
	@GetMapping("/api/members/{id}")
	public ResponseEntity<Object> member(@PathVariable Long id) {
		Member member = memberDao.selectById(id);
		if (member == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse("no member"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
	@PostMapping("/api/members")
	
	... 생략 
}
```

- 스프링 MVC는 리턴 타입이 ResponseEntity이면 Responserintity 의 body로 지정한 객체를 사용해서 변환을 처리한다. 예를 들어 다음 코드에서 구한 member를 body로 지정했는데, 이 경우 member 객체를 JSON으로 변환한다. 

```java
return ResponseEntity.status(HttpStatus.OK).body(member);
```

- ErrorResponse 객체를 body로 지정했으므로 member가 null이면 ErrorResponse를 JSON으로 변환한다.

```java
if (member == null) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
		.body(new ErrorResponse("no member"));
}
```

- ResponseEntity의 status로 지정한 값을 응답 상태 코드로 사용한다. 404(NOT_FOUND) 상태 코드로 응답, 200(OK)을 상태 코드로 응답한다.
- 존재하지 않는 ID를 이용해서 실행해보자. 다음과 같이 404 상태 코드와 함께 JSON 형식으로 응답 데이터를 전송한 것을 알 수 있다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20JSON%20%EC%9D%91%EB%8B%B5%EA%B3%BC%20%EC%9A%94%EC%B2%AD%20%EC%B2%98%EB%A6%AC/images/image11.png)

- ResponseEntity를 생성하는 기본 방법은 status와 body를 이용해서 상태 코드와 JSON으로 변환할 객체를 지정하는 것이다.

```java
ResponseEntity.status(상태코드).body(객체)
```

- 상태 코드는 HttpStatus 열거 타입에 정의된 값을 이용해서 정의한다
- 200(OK) 응답 코드와 몸체 데이터를 생성할 경우 다음과 같이 ok() 메서드를 이용해서 생성할 수도 있다.

```java
ReponseEntity.ok(member)
```

- 만약 몸체 내용이 없다면 다음과 같이 body를 지정하지 않고 build()로 바로 생성한다.

```java
ResponseEntity.status(HttpStatus.NOT_FOUND).build();
```

- 몸체 내용이 없는 경우 status() 메서드 대신에 다음과 같이 관련 메서드를 사용해도 된다.

```java
ReponseEntity.notFound().build()
```

- 몸체가 없을 때 status() 대신 사용할 수 있는 메서드는 다음과 같다.
	- noContent() : 204 
	- badRequest() : 400
	- notFound() : 404
	
- newMember() 메서드는 다음과 같이 201 (Created) 상태 코드와 Location 헤더를 함께 전송했다.

```java
response.setHeader("Location", "/api/members/" + newMemberId);
response.setStatus(HttpServletResponse.SC_CREATED);
```

- 같은 코드를 ResponseEntity로 구현하면 다음과 같다. 
- ResponseEntity.created() 메서드에 Location 헤더로 전달할 URI를 전달하면 된다.

#### src/main/java/controller/RestMemberController.java

```java
package controller;

import java.net.URI;

... 생략

@RestController
public class RestMemberController {
	
	... 생략
	
	@PostMapping("api/members")
	public ResponseEntity<Object> newMember(@ResponseBody @Valid RegisterRequest regReq) {
		try {
			Long newMemberId = registerService.regist(regReq);
			URI uri = URI.create("/api/members/" + newMemberId);
			return ResponseEntity.created(uri).build();
		} catch (DuplicateMemberException dupEx) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	... 생략
}
```

### @ExceptionHandler 적용 메서드에서 ResponseEntity로 응답하기

- 한 메서드에서 정상 응답과 에러 응답을 ResponseBody로 생성하면 코드가 중복될 수 있다. 예를 들어 다음 코드를 보자.

```java
@GetMapping("/api/members/{id}")
public ResponseEntity<Object> member(@PathVariable Long id) {
	Member member = memberDao.selectById(id);
	if (member == null) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse("no member"));
	}
	return ResponseEntity.ok(member);
}
```

- 이 코드는 member가 존재하지 않을 때 기본 HTML 에러 응답 대신에 JSON 응답을 제공하기 위해 ResponseEntity를 사용했다. 그런데 회원이 존재하지 않을 때 404 상태 코드를 응답해야 하는 기능이 많다면 에러 응답을 위해 ResponseEntity를 생성하는 코드가 여러 곳에 중복된다.

- 이럴 때 @ExceptionHandler 애노테이션을 적용한 메서드에서 에러 응답을 처리하도록 구현하면 중복을 없앨 수 있다. 다음은 구현 예이다.


#### src/main/java/controller/RestMemberController.java
```
@GetMapping("/api/members/{id}")
public Member member(@PathVariable Long id) {
	Member member = memberDao.selectById(id);
	if (member == null) {
		throw new MemberNotFoundException();
	}
	return member;
}
	
@ExceptionHandler(MemberNotFoundException.class)
public ResponseEntity<ErrorResponse> handleNoData() {
	return ResponseEntity
		.status(HttpStatus.NOT_FOUND)
		.body(new ErrorResponse("no member"));
}
```

- 이 코드에서 member() 메서드는 Member 자체를 리턴한다. 회원 데이터가 존재하면 Member 객체를 리턴하므로 JSON으로 변환한 결과를 응답한다.
- 회원 데이터가 존재하지 않으면 MemberNotFoundException을 발생한다. 이 익셉션이 발생하면 @ExceptionHandler 애노테이션을 사용한 handleNoData() 메서드가 에러를 처리한다. 
- handleNoData()는 404 상태 코드와 ErrorResponse 객체를 몸체로 갖는 ResponseEntity를 리턴한다. 따라서 MemberNotFoundException이 발생하면 상태 코드가 404 이고 몸체가 JSON 형식인 응답을 전송한다.

- @RestControllerAdvice 애노테이션을 이용해서 에러 처리 코드를 별도 클래스로 분리할 수도 있다. @RestControllAdvice 애노테이션은 @ControllerAdvice 애노테이션과 동일하다. 차이라면 @RestController 애노테이션과 동일하게 응답을 JSON이나 XML과 같은 형식으로 변환한다는 것이다. 

#### src/main/java/controller/ApiExceptionAdvice.java

```java
package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spring.MemberNotFoundException;

@RestControllerAdvice("controller")
public class ApiExceptionAdvice {
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData() {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse("no member"));
	}
}
```

#### src/main/java/config/ControllerConfig.java 
```java

... 생략

import controller.ApiExceptionAdvice;

@Configuration
public class ControllerConfig {

	... 생략
	
	@Bean
	public ApiExceptionAdvice apiExceptionAdvice() {
		return new ApiExceptionAdvice();
	}
}
```

- @RestControllerAdvice 애노테이션을 사용하면 에러 처리 코드가 한 곳에 모여 효과적으로 에러 응답을 관리할 수 있다.


### @Valid 에러 결과를 JSON으로 응답하기

- @Valid 애노테이션을 붙인 커맨드 객체가 값 검증에 실패하면 400 상태 코드를 응답한다. 예를 들어 다음 코드를 보자.

 ```java
 @PostMapping("/api/members")
 public ResponseEntity<Object> newMember(@RequestBody @Valid RegReq) {
	try {
		Long newMemberId = registerService.regist(regReq);
		URI uri = URI.create("/api/members/" + newMemberId);
		return ResponseEntity.created(uri).build();
	} catch (DuplicateMemberException dupEx) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
 }
 ```
 
 - 문제는 HttpServletResponse를 이용해서 상태 코드를 응답했을 때와 마찬가지로HTML 응답을 전송한다는 점이다.  @Valid 애노테이션을 이용한 검증에 실패했을 때 HTML 응답 데이터 대신에 JSON 형식 응답을 제공하고 싶다면 다음과 같이 Errors 타입 파라미터를 추가해서 직접 에러 응답을 생성하면 된다
 
```java
@PostMapping("/api/members")
public RersponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, Errors errors) {
	if (errors.hasErrors()) {
		String errorCodes = errors.getAllErrors()  // List<ObjectError>
			.stream()
			.map(error -> error.getCodes()[0]) // error는 ObjectError
			.collect(Collectors.joining(","));
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponse("errorCodes = " + errorCodes));
	}
	... 생략 
}
```

- 이 코드는 hasErrors() 메서드를 이용해서 검증 에러가 존재하는지 확인한다. 검증 에러가 존재하면 getAllErrors() 메서드로 모든 에러 정보를 구하고(ErrorObject 타입의 객체 목록), 각 에러의 코드 값을 연결한 문자열을 생성해서 errorCodes 변수에 할당한다.

- 위와 같이 코드를 수정한 뒤에 검증에 실패하는 데이터를 전송하면 HTML 대신 JSON 응답이 오는 것을 확인할 수 있다.

- @RequestBody 애노테이션을 붙인 경우 @Valid 애노테이션을 붙인 객체의 검증에 실패했을 때 Errors 타입 파라미터가 존재하지 않으면 MethodArgumentNot ValidException이 발생한다. 따라서 다음과 같이 @ExceptionHandler 애노테이션을 이용해서 검증 실패시 에러 응답을 생성해도 된다.

#### src/main/java/controller/ApiExceptionAdvice.java 

```java
package controller;

import java.util.stream.Collectors;

... 생략 
import org.springframework.web.bind.MethodArgumentNotValidException;

import spring.MemberNotFoundException;

@RestControllerAdvice("controller")
public class ApiExceptionAdvice {
	
	... 생략
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentNotValidException ex) {
		String errorCodes = ex.getBindingResult().getAllErrors()
					.stream()
					.map(error -> error.getCodes()[0])
					.collect(Collectors.joining(","));
			
		return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse("errorCodes = " + errorCodes));
	}
}
```
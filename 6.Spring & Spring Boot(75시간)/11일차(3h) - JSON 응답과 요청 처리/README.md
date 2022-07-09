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
```
- jackson-databind : Jackson core와 Jackson Annotation 의존 추가
- jackson-datatype-jsr310 : Java1.8 data/time 지원 위한 Jackson 모듈


> Jackson에 대한 자세한 설명은 https://github.com/FasterXML/jackson-docs 사이트를 참고한다

- Jackson은 자바 객체와 JSON 간의 변환을 처리한다.


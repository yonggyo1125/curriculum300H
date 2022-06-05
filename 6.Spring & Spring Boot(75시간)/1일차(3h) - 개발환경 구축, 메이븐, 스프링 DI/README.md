# 스프링 프레임워크란?
스프링(Spring)은 매우 방대한 기능을 제공하고 있어서 스프링을 한마디로 정의하기는 힘들다. 흔히 스프링이라고 하면 스프링 프레임워크를 말하는데, 스프링 프레임워크의 주요 특징은 다음과 같다.
- <b>의존 주입(Dependency Inject : DI)</b> 지원
- <b>AOP(Aspect-Oriented Programming)</b> 지원
- <b>MVC 웹 프레임워크</b> 제공
- <b>JDBC, JPA 연동, 선언적 트랜잭션 처리 등 DB 연동</b> 지원<br><br>

이 외에도 스케줄링, 메시지 연동(JMS), 이메일 발송, 테스트 지원 등 자바 기반 어플리케이션을 개발하는데 필요한 다양한 기능을 제공한다.<br><br>

실제로 스트링 프레임워크를 이용해서 웹 어플리케이션을 개발할 때에는 스프링 프레임워크만 단독으로 사용하기 보다는 여러 스프링 관련 프로젝트를 함께 사용한다. 현재 스프링을 주도적으로 개발하고 있는 피보탈(Pivotal)은 스프링 프레임워크뿐만 아니라 어플리케이션 개발에 필요한 다양한 프로젝트를 진행하고 있다. 이들 프로젝트 중 자주 사용되는 것은 다음과 같다.
- **스프링 데이터** : 적은 양의 코드로 데이터 연동을 처리할 수 있도록 도와주는 프레임워크이다. JPA, 몽고DB, 레디스 등 다양한 저장소 기술을 지원한다.
- **스프링 시큐리티** : 인증/인가와 관련된 프레임워크로서 웹 접근 제어, 객체 접근 제어, DB - 오픈 ID - LDAP 등 다양한 인증 방식, 암호화 기능을 제공한다.
- **스프링 배치** : 로깅/추적, 작업 통계, 실패 처리 등 배치 처리에 필요한 기본 기능을 제공한다. <br><br>
이 외에도 스프링 인티그레이션, 스프링 하둡, 스프링 소셜 등 다양한 프로젝트가 존재한다. 각 프로젝트에 대한 내용은 [https://spring.io](https://spring.io) 사이트를 참고하기 바란다.

* * *
# 개발환경 구축하기
- JDK 설치 및 JAVA_HOME 환경 변수 설정 : [1.JAVA(1일차) - 실습환경 구축 참고](https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95#%EC%9E%90%EB%B0%94-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0)

- 이클립스 설치 : [4.Servlet & JSP1(21시간)/1일차(3h) - 개발환경 구축, 웹 기초, 서블릿(Servlet) 참고](https://github.com/yonggyo1125/curriculum300H/tree/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)#%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EA%B5%AC%EC%B6%95)

* * *
# 메이븐 설치 및 사용방법 알아보기

## 메이븐 설치

- [http://maven.apache.org/](http://maven.apache.org/) 사이트에 방문한 뒤 \[Download\] 메뉴를 클릭하여 메이븐 최신 버전을 다운로드 한다. 메이븐을 다운로드 한 후 원하는 폴더에 압축을 풀면 설치가 끝난다.
- 압축을 풀면 \[메이븐 설치폴더\]\bin 폴더에 mvn.bat 파일이 존재할 것이다.
- 명령 프롬프트에서 메이븐을 실행 할 수 있도록 PATH 환경변수를 설정한다. <br>예) C:\devtool\apache-maven-3.8.4\bin

![maven1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EB%A9%94%EC%9D%B4%EB%B8%90%2C%20%EC%8A%A4%ED%94%84%EB%A7%81%20DI/images/maven1.png)<br>

![maven2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EB%A9%94%EC%9D%B4%EB%B8%90%2C%20%EC%8A%A4%ED%94%84%EB%A7%81%20DI/images/maven2.png)<br>

![maven3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EB%A9%94%EC%9D%B4%EB%B8%90%2C%20%EC%8A%A4%ED%94%84%EB%A7%81%20DI/images/3.png)

- 명령 프롬프트(\[시작\]->\[모든 프로그램\]->\[보조 프로그램\]->\[명령 프롬프트\])를 실행한 뒤 다음과 같이 mvn 명령어를 입력한다.(시작 메노의 검색창에 cmd라고 입력하여 명령 프롬프트를 실행해도 된다.)

```
mvn -version
```

![maven4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EB%A9%94%EC%9D%B4%EB%B8%90%2C%20%EC%8A%A4%ED%94%84%EB%A7%81%20DI/images/maven4.png)

## 메이븐 아키타입을 이용한 프로젝트 생성하기

메이븐이 제공하는 아키타입을 사용하면 미리 정의된 폴더 구조와 기반이 되는 pom.xml 파일을 사용해서 메이븐 프로젝트를 생성할 수도 있다.

```
mvn archetype:generate
```
![maven5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EB%A9%94%EC%9D%B4%EB%B8%90%2C%20%EC%8A%A4%ED%94%84%EB%A7%81%20DI/images/maven5.png)<br><br>

위 과정에서 실제로 입력하는 값은 다음과 같다.
- **groupId** : 프로젝트가 속하는 그룹 식별자. 회사, 본부, 또는 단체를 의미하는 값을 입력한다. 패키지 형식으로 계층을 표현한다. 위에서는 kr.codefty를 groupId로 입력했다.
- **artifactId** : 프로젝트 결과물의 식별자. 프로젝트나 모듈을 의미하는 값이 온다. 위에서는 sample을 artifactId로 입력했다.
- **version** : 결과물의 버전을 입력한다. 위에서는 기본 값인 1.0-SNAPSHOT을 사용했다.
- **package** : 생성할 패키지를 입력한다. 별도로 입력하지 않을 경우 groupId와 동일한 구조의 패키지를 생성한다.

## 메이븐 프로젝트의 기본 디렉토리 구조



* * * 
# 스프링 프로젝트 생성하기

* * *
# 스프링 DI(Dependency Injection - 의존주입)

## 의존이란?

## DI를 통한 의존 처리

## 객체 조립기
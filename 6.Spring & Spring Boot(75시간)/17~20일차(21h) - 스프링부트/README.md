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

* * * 
# 기본 설정 하기

## JDK 설치

- [자바설치하기](https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95#%EC%9E%90%EB%B0%94-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0)

## MySQL 설치 

- [SQL 실습 환경 구성](https://github.com/yonggyo1125/curriculum300H/tree/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)#sql-%EC%8B%A4%EC%8A%B5-%ED%99%98%EA%B2%BD-%EA%B5%AC%EC%84%B1)


## STS(Spring Tool Suite)

- STS(Spring Tool Suite)는 이클립스기반의 스프링에 최적화된 IDE입니다.

- [다운로드](https://spring.io/tools)

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image1.png)

##  애플리케이션 실행하기

###  Spring Boot Project 생성하기

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image2.png)

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image4.png)

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image5.png)

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/images/image6.png)


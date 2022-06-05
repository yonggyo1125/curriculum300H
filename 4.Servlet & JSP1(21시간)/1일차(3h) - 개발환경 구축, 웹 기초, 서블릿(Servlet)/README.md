# 개발환경 구축

### Eclipse EE IDE 설치
- 무설치 zip 파일 형태로 제공되는 IDE가 설치의 번거로움 없이 바로 사용가능하므로 다음 주소를 클릭하여 다운로드 받습니다.<br>[다운로드](https://www.eclipse.org/downloads/packages/)

![images1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images1.png)

#### Tomcat 설치 및 설정

- [tomcat9 다운로드](https://tomcat.apache.org/download-90.cgi)
	- tomcat 최신 버전은 현재 10버전 입니다. 최신버전과 기존 버전의 큰 차이점은 제공되는 API에 있는데 과거 버전과 패키지 체계가 다릅니다. 9버전까지는 javax.\*로 시작하는 패키지이나 10버전 부터는 jakarta.\*로 변경이 되었습니다. 세세한 클래스는 패키지명만 다르고 거의 차이점은 없으나 추후에 공부하게될 스프링에서는 아직까지는 지원하지 않으므로 tomcat9버전으로 실습을 진행합니다.
	
	- [tomcat9 다운로드](https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.63/bin/apache-tomcat-9.0.63-windows-x64.zip)
	- [tomcat9 API 문서 다운로드](https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.63/bin/apache-tomcat-9.0.63-fulldocs.tar.gz)
	
	![images2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images2.png)
	
	- [Java EE8 API 문서](https://javaee.github.io/javaee-spec/javadocs/overview-summary.html)
	
- Tomcat9 Server 이클립스에 설정하기
	- File -> New -> Others -> Server -> Apache 선택 
	
	![images3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images3.png)
	
	![images4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images4.png)
	
	- Tomcat v9.0 Server 선택 -> Next 클릭
	![images5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images5.png)
	
	- Tomcat Installation directory에 다운로드 받은 tomcat9 폴더를 선택 -> Finish 클릭 
	
	![images6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images6.png)
	
	- 정상적으로 Tomcat9 서버가 이클립스에 등록이 되면 하기 이미지와 같이 노출이 됩니다.
	
	![images7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images7.png)
	
	
	- Servers 탭의 Tomcat v9.0 Server at localhost를 더블클릭 하시면 세부 설정이 나오는데 **Server Options**의 **Server module without publishing**에 체크 하고 저장해 줍니다.
	![images8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images8.png)
	
		> 이클립스에 등록한 Server의 contextPath의 기본값이 웹프로젝트의 경로가 아닌 이클립스 임시 경로가 되므로 파일 업로드시에 경로 문제가 발생할 수 있습니다. 상기 옵션을 체크하여 웹프로젝트 경로고 변경합니다.
		
### JSP 웹 프로젝트 생성
- File -> New -> Dynamic Web Project 클릭

![images9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images9.png)

- Project name에 적절한 웹 프로젝트 명을 입력하고 Finish 클릭
![images10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images10.png)

- Servers 탭의 Tomcat v9.0 Server at localhost에 마우스 오른쪽 버튼을 클릭 -> Add and Remove 선택

![images12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images12.png)

- available에 있는 등록한 웹프로젝트를 선택한 후 Add 버튼을 클릭한 후 finish를 클릭한다. (등록된 톰캣에서 실행할 웹 프로젝트 등록)

![images13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images13.png)

![images14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images14.png)




	
* * *
# 웹 기초

* * *
## HTTP

## 요청헤더(Request Header), 응답헤더(Response Header)

## GET방식

## POST방식

* * *
# 서블릿(Servlet)

## 서블릿(Servlet)의 개요

## web.xml 파일에서 서블릿(Servlet) 구성하기

## 서블릿(Servlet) 클래스 생성하기

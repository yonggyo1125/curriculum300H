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
	
	![images3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images3.png)<br>
	
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

![images13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images13.png)<br>

![images14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images14.png)



### JSP 프로젝트 폴더 구조

![images15](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images15.png) <br>


- **webapp** : HTML(.html), JSP, Javascript(.js), CSS(.css) 및 이미지와 같은 파일들이 위치한다. 해당 위치에 놓이는 파일들은 웹 애플리케이션이 배치 될 떄 그대로 옮겨진다.
- **WEB-INF** : 웹 애플리케이션의 설정 파일들이 해당 디렉토리 아래에 포함된다. WEB-INF 내에 있는 파일들은 클라이언트에서 요청할 수 없다. 따라서 html, js와 같은 정적 자원은 바로 읽을 수 없다.

- **WEB-INF/classes** : 컴파일된 자바 클래스(class) 파일이 있으며 src에 선언된 package가 동일하게 생성된다.

- **WEB-INF/lib** : 애플리케이션 실행에 필요한 라이브러리 즉, jar 파일들을 모아두는 디렉토리이다. jar은 자바 아카이브 파일이란 의미로 java + ARchive의 합성어로 jar로 사용된다.

- **WEB-INF/web.xml** : 웹 애플리케이션 배치 설명서 파일이다. 애플리케이션 컴포넌트들의 배치 정보들을 해당 파일에 작성한다. 서블릿 컨테이너는 web.xml 배치 설명서를 참조해서 서블릿, 필터, 매개변수 등을 찾거나 실행한다.

* * *
# 웹 기초

## 요청과 응답 이해하기

![images20](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images20.png)

- 서버는 클라이언트가 있기에 동작합니다. 클라이언트에서 서버로 요청(request)을 보내고, 서버에서는 요청의 내용을 읽고 처리한 뒤 클라이언트에 응답(response)을 보냅니다.
- 따라서 서버에는 요청을 받는 부분과 응답을 보내는 부분이 있어야 합니다. 요청과 응답은 이벤트 방식이라고 생각할 수 있습니다.


## HTTP
- HTTP는 HTML 문서와 같은 리소스들을 가져올 수 있도록 해주는 프로토콜입니다. HTTP는 웹에서 이루어지는 모든 데이터 교환의 기초이며, 클라이언트-서버 프로토콜이기도 합니다. 클라이언트-서버 프로토콜이란 (보통 웹브라우저인) 수신자 측에 의해 요청이 초기화되는 프로토콜을 의미합니다. 하나의 완전한 문서는 텍스트, 레이아웃 설명, 이미지, 비디오, 스크립트 등 불러온(fetched) 하위 문서들로 재구성됩니다.

- 클라이언트와 서버들은 (데이터 스트림과 대조적으로) 개별적인 메시지 교환에 의해 통신합니다. 보통 브라우저인 클라이언트에 의해 전송되는 메시지를 요청(requests)이라고 부르며, 그에 대해 서버에서 응답으로 전송되는 메시지를 응답(responses)이라고 부릅니다.

![images16](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images16.png)

## 헤더(header)
HTTP 헤더는 클라이언트와 서버가 요청 또는 응답으로 부가적인 정보를 전송할 수 있도록 해줍니다. HTTP 헤더는 대소문자를 구분하지 않는 이름과 콜론 ':' 다음에 오는 값(줄 바꿈 없이)으로 이루어져있습니다. 값 앞에 붙은 빈 문자열은 무시됩니다.

- <b>일반 헤더(General header)</b> : 요청과 응답 모두에 적용되지만 바디에서 최종적으로 전송되는 데이터와는 관련이 없는 헤더.
- <b>요청헤더(Request header)</b>: 페치될 리소스나 클라이언트 자체에 대한 자세한 정보를 포함하는 헤더.
- <b>응답헤더(Response header)</b>: 위치 또는 서버 자체에 대한 정보(이름, 버전 등)와 같이 응답에 대한 부가적인 정보를 갖는 헤더.
- <b>엔티티 헤더(Entity header)</b>: 컨텐츠 길이나 MIME 타입과 같이 엔티티 바디에 대한 자세한 정보를 포함하는 헤더.

### HTTP 요청/응답

![images17](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images17.png)

- 요청과 응답은 모두 **헤더와 본문**을 가지고 있습니다. **헤더는 요청 또는 응답에 대한 정보를 가지고 있는 곳**이고 **본문은 서버와 클라이언트 간에 주고받을 실제 데이터를 담아두는 공간**입니다.
- 개발자 도구의 Network 탭에서 요청 중 하나를 클릭해보면 더 상세하게 요청과 응답을 살펴볼 수 있습니다.

#### 요청의 헤더와 본문

![images18](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images18.png)

- General은 공통 헤더이고, Request Headers는 요청의 헤더, Response Headers는 응답의 헤더입니다.
- Request Payload가 요청의 본문입니다. 응답의 본문은 Preview나 Response 탭에서 확인할 수 있습니다.

#### 응답의 본문

![images19](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images19.png)<br>

#### HTTP 상태 코드
브라우저는 서버에서 보내주는 상태 코드를 보고 요청이 성공했는지 실패했는지를 판단합니다. 
- **2XX** : 성공을 알리는 상태코드 입니다. 대표적으로 200(성공), 201(작성됨)이 많이 사용됩니다.
- **3XX** : 리다이렉션(다른 페이지로 이동)을 알리는 상태 코드 입니다. 어떤 주소를 입력했는데 다른 주소의 페이지로 넘어갈 때 이 코드가 사용됩니다. 대표적으로 301(영구이동), 302(임시 이동)가 있습니다. 304(수정되지 않음)는 요청의 응답으로 캐시를 사용했다는 뜻 입니다.
- **4XX** : 요청 오류를 나타냅니다. 요청 자체에 오류가 있을 때 표시됩니다. 대표적으로 400(잘못된 요청), 401(권한없음), 403(금지됨), 404(찾을 수 없음)가 있습니다.
- **5XX** : 서버오류를 나타냅니다. 요청은 제대로 왔지만 서버에 오류가 생겼을 때 발생합니다. 이 오류가 뜨지 않게 주의해서 프로그래밍 해야 합니다. 500(내부 서버 오류), 501(불량 게이트웨이), 503(서비스를 사용할 수 없음)이 자주 사용됩니다.

## HTTP 요청 메서드
클라이언트가 서버에 데이터를 전송하여 응답을 요청할때 사용하는 방식

- GET : 서버 자원을 가져오고자 할 때 사용합니다. 요청의 본문에 데이터를 넣지 않습니다. 데이터를 서버로 보내야 한다면 쿼리스트링을 사용합니다. 

- POST : 서버에 자원을 새로 등록하고자 할 때 사용합니다. 요청의 본문에 새로 등록할 데이터를 넣어 보냅니다.

- PUT : 서버의 자원을 요청에 들어 있는 자원으로 치환하고자 할 때 사용합니다. 요청의 본문에 치환할 데이터를 넣어 보냅니다.

- PATCH : 서버 자원의 일부만 수정하고자 할 때 사용합니다. 요청의 본문에 일부 수정할 데이터를 넣어 보냅니다.

- DELETE : 서버의 자원을 삭제하고자 할 때 사용합니다. 요청의 본문에 데이터를 넣지 않습니다.

- OPTIONS : 요청을 하기 전에 통신 옵션을 설명하기 위해 사용합니다.

> GET 메서드 같은 경우네는 브라우저에서 캐싱(기억)할 수도 있으므로 같은 주소로 GET 요청을 할 때 서버에서 가져오는 것이 아니라 캐시에서 가져올 수도 있습니다.

* * *
# 서블릿(Servlet)
서블릿(servlet)이란 자바를 기반으로 하는 웹 어플리케이션 프로그래밍 기술입니다. 처음에는 썬마이크로시스템즈사(현 오라클로 변경)에 의해 개발되었지만, 그 후에 자바 표준 개발을 전담하는 기관인 JCP(Java Community Process)로 이관되어 발전을 계속해왔습니다. 이 기술에서는 자바 클래스 형태로 웹 애플리케이션을 작성하는데, 그 클래스를 서블릿 클래스라고 합니다.

## 서블릿(Servlet)의 개요
- 서블릿 기술에서 웹 애플리케이션을 구현하기 위해 작성해야 하는 코드는 '서블릿 클래스' 입니다.
- 이 클래스는 클래스 상태 그대로 실행되는 것이 아니라 일단 '서블릿'으로 만들어진 다음에 실행됩니다. 즉, 서블릿은 서블릿 클래스로부터 만들어진 객체입니다.
- 하지만 모든 서블릿 객체가 서블릿이라고 할 수 는 없습니다. 웹 컨테이너는 서블릿 클래스를 가지고 서블릿 객체를 만든 다음에 그 객체를 초기화해서 웹 서비스를 할 수 있는 상태로 만드는데 이런 작업을 거친 서블릿 객체만 서블릿이라고 할 수 있습니다.




## web.xml 파일에서 서블릿(Servlet) 구성하기


## 서블릿(Servlet) 클래스 생성하기

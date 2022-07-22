
# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1mjfCjZ6rWKSz2_R-acb1OAB7M1xIi9a8?usp=sharing)

# 개발환경 구축

### Eclipse EE IDE 설치
- 무설치 zip 파일 형태로 제공되는 IDE가 설치의 번거로움 없이 바로 사용가능하므로 다음 주소를 클릭하여 다운로드 받습니다.<br>[다운로드](https://www.eclipse.org/downloads/packages/)

![images1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images1.png)

- 이클립스 디본 인코딩 변경<br>\[Windows\]->\[Preferences\]->\[General/Workspace\]설정 화면에서 **Text file encoding** 항목의 값을 **UTF-8**로 설정한다.

![images25](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images25.png)

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

![images21](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images21.png)

- 위 그림에서 볼수 있는 것처럼 서블릿 클래스가 서블릿이 되기 위해서는 두 단계를 거쳐야 합니다. 하지만 이 두 단계는 모두 웹 컨터이너에 의해 수행되기 때문에 따로 해야할 것은 없습니다.
- 단지 해야 할 것은 서블릿 클래스를 작성해서 컴파일하고, 웹 컨테이너에 설치하고, 등록하면 됩니다. 
- 단, 서블릿 클래스를 올바르게 작성하기 위해서는 웹 서버(웹 컨테이너)가 서블릿을 운영하는 방법에 대해 몇 가지 알아두어야 할 것이 있습니다.
	- 웹 서버가 여러 사용자로부터(정확하게 말하면 여러 웹 브라우저로부터) 동시에 접속을 받을 수 있고, 여러 서블릿이 동시에 실행될 수도 있다는 점입니다. 이런 이유로 모든 웹 서버는 멀티쓰레드 방식으로 작동합니다. 멀티스레드(multithread)란 프로그램의 실행 흐름이 여러 갈래(thread)로 나눠져서 동시에 실행되는 것을 말합니다. 그런데 그렇게 되면 여러 쓰레드가 동시에 같은 데이터를 사용할 수 있고, 그로 인해 여러가지 문제가 발생할 수 있습니다. 그래서 경험이 많은 프로그래머라도 멀티쓰레드 프로그램의 작성을 어려워하는 경우가 많습니다. 하지만 큰 걱정을 할 필요가 없습니다. 웹서버를 만든 프로그래머가 그에 관련된 복잡한 로직을 이미 구현해 놓았기 때문에 몇 가지 주의사항 만 잘 따르기만 하면 큰 문제가 없습니다.
	
	- 멀티쓰레드 방식의 서블릿에서는 필요한 서블릿의 수가 적기 떄문에 서블릿을 만들기 위해 필요한 시스템 자원과 서블릿이 차지하는 메모리를 절약할 수 있습니다. 
	- 하지만 여러 쓰레드가 동시에 한 서블릿을 사용하기 때문에 데이터 공유 문제에 신경을 써야 합니다.  이 방식에서 일어날 가능성이 있는 데이터 공유 문제는 서블릿 클래스의 인스턴스 변수로 인한 것인데, 이런 변수를 선언해서 사용하지 않으면 됩니다.
	
	```java
	public class HundredServlet extends HttpServlet {
		//private int total;  
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			...
		}
	}
	```
	- private int total : 멀티쓰레드 모델에서는 이런 인스턴스 변수를 선언해서 사용하면 안됩니다.


## 서블릿(Servlet) 클래스 작성하기

### 서블릿 클래스를 작성할 때 지켜야할 규칙
```
- 서블릿 클래스는 javax.servlet.http.HttpServlet 클래스를 상속하도록 만들어야 합니다.
- doGet 또는 doPost 메서드 안에 웹 브라우저로부터 요청이 왔을 떄 해야 할 일을 기술해야 합니다.
- HTML 문서는 doGet, doPost 메서드의 두번째 파라미터를 이용해서 출력해야 합니다.
```
- javax.servlet.http.HttpServlet 클래스는 javax.servlet.Servlet 인터페이스를 구현합니다. 사실 이 클래스는 Servlet 인터페이스를 직접 구현하는 것이 아니라, 상위 클래스인 javax.servlet.GenericServlet 클래스를 통해 간접적으로 구현합니다. 

![images22](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images22.png)

- 상속/구현 관계를 알아야 하는 이유는 서블릿 클래스를 작성하면서 이런 클래스의 인터페이스의 API 규격서를 찾아봐야 할 경우가 종종 있기 때문입니다.
- 이 클래스의 인터페이스는 JDK의 표준 라이브러리가 아니라 J2EE(Java Platform Enterprise Edition)라는 확장 라이브러리에 속합니다. [Java EE8 API 문서](https://javaee.github.io/javaee-spec/javadocs/overview-summary.html)



### 서블릿 클래스 작성하기 
- 서블릿 클래스는 javax.servlet.http.HttpServlet 클래스를 상속받도록 만들어야 합니다. 
- 서블릿 클래스는 public으로 만들어야 합니다. public으로 선언해야 하는 이유는 웹 컨테이너가 서블릿 객체를 만들 때 이 접근 권한이 필요하기 때문입니다.

```java
public class HundredServlet extends HttpServlet {

}
```
- 서블릿 클래스 안에 doGet 또는 doPost 메서드를 선언해야 합니다. 이 두 메서드는 javax.servlet.http.HttpServletResponse와 javax.servlet.http.HttpServletResponse 타입의 매개변수를 받아야 하고 메서드 밖으로 javax.servlet.ServletException과 java.io.IOException을 던질 수 있도록 선언해야 합니다.
- 그런데 이 이름은 너무 길고 복잡해서 실무에서 프로그램을 작성하다가 생각나지 않을 수 있습니다. 이럴 때는 HttpServlet 클래스의 API 문서를 찾아보면 됩니다. [HttpServlet 클래스 API 문서](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServlet.html) <br>**작업시 또는 학습시에는 꼭 문서를 열어 놓고 수시로 참고해 주세요.**

![images23](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images23.png) <br>


![images24](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%20%EC%9B%B9%20%EA%B8%B0%EC%B4%88%2C%20%EC%84%9C%EB%B8%94%EB%A6%BF(Servlet)/images/images24.png)

```java
public class HundredServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		...
	}
}
```
> doGet 메서드를 public으로 선언해야 하는 이유는 나중에 웹 컨테이너가 웹 브라우저로부터 요청을 받아서 이 메서드를 호출할 때 필요하기 때문입니다.

-  1부터 100까지 합을 구해서 결과를 출력하는 예
```java 
public class HundredServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int total = 0;
		for (int i = 0; i < 101; i++) {
			total += i;
		}
	}
}
```
- 위 결과를 출력하는 코드를 작성해야 하는데 doGet 메서드의 두 번째 매개변수를 이용해서 작성할 수 있습니다. 이 매개변수는 javax.servlet.http.HttpServletResponse 인터페이스 타입인데, 이 매개변수의 객체에서 getWriter 메서드를 호출하여 PrintWriter 객체를 구할 수 있습니다.
```java
PrintWriter writer = response.getWriter();
```
- PrintWriter는 일반 자바 프로그램에서는 파일로 텍스트를 출력할 때 사용하는 java.io 패키지의 PrintWriter 클래스입니다. 하지만 response.getWriter 메서드가 반환하는 PrintWriter 객체는 파일이 아니라 웹 브라우저로 데이터를 출력합니다. 
- 즉, 다음과 같은 print, println, printf 메서드는 매개변수로 넘겨준 HTML 코드를 웹 브라우저로 전송합니다.
```java
writer.print("<head>");
writer.println("<body>");
writer.printf("TOTAL = %d", total);
```
> 여기에서 호출한 print, println, printf 메서드의 사용 방법은 일반 자바 프로그램에서 많이 사용하는 System.out.print, System.out.println, System.out.printf 메서드의 사용방법과 동일합니다.

#### src/main/java/myservlet/HundredServlet.java
```java
package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class HundredServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int total = 0;
		for(int i = 1; i < 101; i++)
			total += i;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.printf("1부터 100까지 합은 = %d", total);
		out.println("</body>");
		out.println("</html>");
	}
}
```
- response.setContentType("text/html; charset=utf-8"); : 서블릿에서 한글은 출력되지 않고 깨져 보이게 됩니다. 이때 인코딩(charset=utf-8)과 출력될 문서 타입(text/html)을 추가하면 한글이 제대로 출력이 됩니다.


## web.xml 파일에 서블릿(Servlet) 등록하기
- 서블릿 클래스는 JSP 페이지와 달리 설치뿐만 아니라 등록을 하는 과정도 필요합니다. 
- 등록은 웹 애플리케이션 배치 설명서(web application deployment descriptor) 파일에 등록해야 합니다. 
- 웹 애플리케이션 배치 설명서 파일이란 웹 애플리케이션 디렉토리의 WEB-INF 하위 디렉토리에 있는 web.xml이라는 이름의 파일을 말합니다.

> 설정의 예시는 다운로드 받은 tomcat9 소스의 webapps/examples/WEB-INF/web.xml을 참고 하실 수 있습니다.

- web.xml 파일은 웹 애플리케이션 디렉토리마다 딱 하나씩만 만들수 있습니다. 경로는 webapps/WEB-INF/web.xml 입니다.
- web.xml 파일을 새로 만들 때는 루트 요소인 \<web-app\>을 만드는 일부터 시작하는 것이 좋습니다. 
```xml
<web-app>

</web-app>
```
- 이 안에 웹 서버가 웹 브라우저로부터 URL을 받았을 때 서블릿 클래스를 찾아서 호출하기 위해 필요한 정보를 기록해야 합니다.
```xml
<web-app>
	<servlet>
		<!-- 서블릿 클래스의 이름이 들어갈 부분 -->
	</servlet>
	<servlet-mapping>
		<!-- 서블릿 클래스를 호출할 때 사용할 URL이 들어갈 부분 -->
	</servlet-mapping>
</web-app>
```

- 작성 예시
```xml
<web-app>
	<servlet>
		<servlet-name>hundred-servlet</servlet>
		<servlet-class>myservlet.HundredServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>hundred-servlet</servlet-name>
		<url-pattern>/hundred</url-pattern>
	</servlet-mapping>
</web-app>
```

- 이것으로 서블릿 클래스의 등록에 필요한 기본적인 코드는 만들어졌습니다. 하지만 이 코드는 아직 온전한 web.xml 파일이 될수 없습니다. 
- tomcat9 소스 폴더의 예시 web.xml을 살펴보면 \<web-app\> 시작 태그 안에 상당히 복잡한 내용이 들어가 있습니다. 모든 web.xml파일 안에는 반드시 써 넣어야 하는 두 가지 정보가 포함되어 있는데, 하나는 web.xml 파일의 작성에 사용된 문법의 식발자이고, 다른 하나는 그 문법의 버전입니다.
```xml
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
...
</web-app>
```
- xmlns="http://xmlns.jcp.org/xml/ns/javaee" : web.xml 파일의 작성에 사용되는 문법의 식별자
- version="4.0" : 그 문법의 버전

#### src/main/webapp/WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
	<servlet>
		<servlet-name>hundred-servlet</servlet-name>
		<servlet-class>myservlet.HundredServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>hundred-servlet</servlet-name>
		<url-pattern>/hundred</url-pattern>
	</servlet-mapping>
</web-app>
```
- Servers 탭의 tomcat v9.0 Server at localhost를 클릭 하고 오른쪽 끝에 위치한 실행 버튼을 클릭하면 웹서버가 실행이 됩니다.
- http://localhost:8080/jspWeb/hundred을 입력하여 결과 확인
- 실행 결과
```
1부터 100까지 합은 = 5050
```

- 서블릿 3.0 버전 이후 부터 **@WebServlet** 애너테이션을 사용할 수 있으며 web.xml에 servlet 등록 설정을 간소화 할 수 있습니다.

#### src/main/java/myservlet/HundredServlet2.java
```java
package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/hundred2")
public class HundredServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int total = 0;
		for(int i = 1; i < 101; i++)
			total += i;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.printf("1부터 100까지 합은 = %d", total);
		out.println("</body>");
		out.println("</html>");
	}
}
```

### XML 문법의 기초
- XML은 HTML과 마찬가지로 텍스트 내용에 태그(또는 마크업)를 첨가하기 위해서 사용되는 문법입니다. 이 언어는 언뜻 보기에 HTML과 비슷해 보입니다. 태그가 \<로 시작해서 \>로 끝나는 것도 그렇고, 주석이 \<!--로 시작해서 --\>로 끝나는 것도 그렇습니다. 하지만 좀 더 자세히 살펴보면 서로 다른 점이 상당히 많습니다. 
- 첫째. XML 문서의 제일 앞에는 **XML 선언**이 올 수 있습니다. XML 선언은 XML 문서 작성에 사용된 XML 규격서의 버전과 XML 문서를 저장하는 데 사용된 문자 코드의 인코딩 방식을 표시하는 역할을 합니다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
```
- 하지만 XML 문서에서 XML 선언이 생략될 수도 있습니다. 예를 들어 XML 문서의 내용이 ASCII 문자로만 구성되었을 경우에는 XML 선언을 생략해도 됩니다.
- 둘째, HTML에서는 모든 문서의 작성 방법이 동일하지만, XML에서는 문서의 종류에 따라 문서 작성 방법이 달라질 수 있습니다. 예를 들어 web.xml 문서의 루트 요소는(root element, 문서의 최상위 요소)는 \<web-app\>여야 하지만, 톰캣의 server.xml과 tomcat-users.xml 문서의 루트 요소sms 각각 \<server\>와 \<tomcat-users\>여야 합니다.
- 셋째, HTML에서는 요소 이름과 속성(애트리뷰트) 이름에 있는 대소문자를 구분하지 않지만, XML에서는 엄격하게 구분합니다.

### 웹 브라우저로부터 데이터 입력 받기
- \<form\> 요소를 통해 입력된 데이터는 doGet, doPost 메서드의 첫 번째 매개변수인 HttpServletRequest 객체에서 getParameter 메서드를 호출해서 가져올 수 있습니다. 
- 이 메서드는 입력된 모든 데이터를 한꺼번에 가져오는 것이 아니라, 파라미터로 넘겨준 이름(input 요소의 name 속성 값)에 해당하는 데이터 하나만 가져옵니다.
```java
String str = request.getParameter("num1");
```
- 위 코드에서 볼 수 있는 것 처럼 이 메서드가 반환하는 값은 수치 타입이 아니라 문자열 타입 입니다. 그러므로 수치 연산을 하기 위해서는 이 데이터를 수치 타입으로 변환해야 합니다.
```java
int num = Integer.parseInt(str);
```

#### src/main/java/myservlet/AdderServlet.java
```java
package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class AdderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String str1 = request.getParameter("num1");
		String str2 = request.getParameter("num2");
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		int sum = num1 + num2;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<head><title>덧셈 프로그램 - 결과화면</title></head>");
		out.println("<body>");
		out.printf("%d + %d = %d", num1, num2, sum);
		out.println("</body>");
		out.println("</html>");
	}
}
```
#### src/main/webapps/WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
	...
	<servlet>
		<servlet-name>adder-servlet</servlet-name>
		<servlet-class>myservlet.AdderServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>adder-servlet</servlet-name>
		<url-pattern>/adder</url-pattern>
	</servlet-mapping>
</web-app>
```

- URL에 다음과 같이 입력 - http://localhost:8080/jspWeb/adder?num1=10&num2=20
- 실행결과
```
10 + 20 = 30
```

#### src/main/webapp/day01/BBSInput.html
```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<h2>글쓰기</h2>
		<form action="/jspWeb/bbs-post" method="post">
			이름 : <input type="text" name="name"><br>
			제목 : <input type="text" name="title"><br>
			<textarea cols="50" rows="5" name="content"></textarea><br>
			<input type="reset" value="취소">
			<input type="submit" value="저장">
		</form>
	</body>
</html>
```

#### src/main/java/myservlet/BBSPostServlet.java
```java
package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class BBSPostServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>게시판 글쓰기 - 결과화면</title></head>");
		out.println("<body>");
		out.printf("이름: %s <br>",name);
		out.printf("제목: %s <br>", title);
		out.println("-------------<br>");
		out.printf("<pre>%s</pre>", content);
		out.println("-------------<br>");
		out.println("저장되었습니다.");
		out.println("</body>");
		out.println("</html>");
	}
}
```
- request.setCharacterEncoding("UTF-8"); : \<form\>을 통해서 POST 방식으로 요청 받은 데이터가 한글인 경우 깨짐 문제가 발행하는데 HttpServletRequest 객체의 setCharacterEncoding 메서드를 통해서 적절한 인코딩을 설정하여 문제를 해결할 수 있습니다.

#### src/main/webapps/WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
	...
	<servlet>
		<servlet-name>bbs-post-servlet</servlet-name>
		<servlet-class>myservlet.BBSPostServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>bbs-post-servlet</servlet-name>
		<url-pattern>/bbs-post</url-pattern>
	</servlet-mapping>
</web-app>
```


- <select 요소에서 multiple과 size 속성을 추가하여 두 항목 이상 선택 가능한 선택 상자를 만들수 있는데, 이럴 경우 동일한 name 파라미터로 넘어오는 값이 여러개가 될 수 있다. 이때 getParameter 메서드를 사용하면 그 중 1개만 선택이 가능한데, 이럴 땐 getParameterValues() 라는 메서드를 사용하시면 됩니다.

```xml
<select name="fruits" multiple size=4>
	<option value="사과">사과</option>
	<option value="포도">포도</option>
	<option value="복숭아">복숭아</option>
	<option value="오렌지">오렌지</option>
	<option value="딸기">딸기</option>
</select>
```

```java
String[] names = request.getParameterValues("fruits");
```
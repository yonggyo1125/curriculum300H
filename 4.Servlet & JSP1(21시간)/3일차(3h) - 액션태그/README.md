## 액션태그
- 액션 태그는 서버나 클라이언트에게 어떤 행동을 하도록 명령하는 태그로 스크립트 태그, 주석, 디렉티브 태그와 함게 JSP 페이지를 구성하는 태그입니다.
- 액션 태그는 JSP 페이지에서 페이지와 페이지 사이를 제어하거나(forward), 다른 페이지의 실행 결과 내용을 현재 페이지에 포함하거나(include), 자바빈즈(javaBeans)등의 다양한 기능을 제공
- XML 형식 <jsp:... />를 사용합니다. 액션 태그는 반드시 끝나는 태그 />로 마무리해야 합니다.

### 액션태그의 종류
|액션태그|형식|설명|
|----|-----|---------|
|forward|<jsp:forward .. />|다른 페이지로의 이동과 같은 페이지 흐름을 제어합니다.|
|include|<jsp:include ... />|외부 페이지의 내용을 포함하거나 페이지를 모듈화합니다.|
|useBean|<jsp:useBean ... />|JSP 페이지에 자바빈즈를 설정합니다.|
|setProperty|<jsp:setProperty ... />|자바빈즈의 프로퍼티 값을 설정합니다.|
|getProperty|<jsp:getProperty ... />|자바빈즈의 프로퍼티 값을 얻어옵니다.|
|param|<jsp:param ... />|<jsp:forward>, <jsp:include>, <jsp:plugin>태그에 인자를 추가|
|plugin|<jsp:plugin ... />|웹브라우저에 자바 애플릿을 실행합니다. 자바 플러그인에 대한 OBJECT 또는 EMBED 태그를 만드는 브라우저별 코드를 생성|
|element|<jsp:element ... />|동적 XML 요소를 설정합니다.|
|attribute|<jsp:attribute ... />|동적으로 정의된 XML 요소의 몸체를 설정합니다.|
|body|<jsp:body ,,, />|동적으로 정의된 XML 요소의 동체를 설정합니다.|
|text|<jsp:text ... />|JSP 페이지 및 문서에서 템플릿 텍스트를 작성합니다.|


### forward 액션 태그의 기능과 사용법
- 현재 JSP 페이지에서 다른 페이지로 이동하는 태그
- JSP 컨테이너는 JSP 페이지에서 forward 액션 태그를 만나면 그 전까지 출력 버퍼에 저장되어 있던 내용을 모두 삭제하고 forward 액션 태그에 설정된 페이지로 프로그램 제어가 이동합니다.
```
<jsp:forward page=“파일명” />    반드시 끝나는 태그가 있어야 함
또는 
<jsp:forward page=“파일명”> </jsp:forward>
```
> forward 액션 태그 사용 시 주의점<br>웹 서버는 forward 액션 태그를 수행할 때 출력 버퍼를 지우므로 현재 페이지에서 forward 액션 태그가 선언된 지점 이전까지 HTML 코드가 손실됩니다. 그러나 현재 페이지가 이미 전달 버퍼로 채워진 경우에는 전달이 중단될 때까지 해당 내용을 웹서버에 응답으로 보냅니다. 이렇게 하면 잘못된 페이지가 클라이언트로 전송될 수 있습니다. 따라서 큰 출력을 생성하는 페이지에서 forward 액션 태그를 호출할 때는 신중을 기해야 합니다.

```
예제)
first.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
  <body>
	<h3>이 파일은 first.jsp입니다.</h3>
        <jsp:forward page=“second.jsp” />
        <p>=== first.jsp의 페이지 ====</p>
  </body>
</html>

second.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
   <body>
  	<h3>이 파일은 second.jsp입니다.</h3>	Today is : <%=java.util.Calendar.getInstance().getTime()%>
   </body>
</html>
```

#### day03/forward.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h2>forward 액션 태그</h2>
	<jsp:forward page="forward_date.jsp" />
	<p>-------------------------------</p>
</body>
</html>
```

#### day03/forward_date.jsp 
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<p>오늘의 날짜 및 시각
	<p><%=(new java.util.Date()).toLocaleString()%>
</body>
</html>
```

### include 액션 태그의 기능과 사용법
- include 액션 태그는 include 디렉티브 태그처럼 JSP 페이지의 특정 영역에 외부 파일의 내용을 포함하는 태그
- JSP 페이지에 포함할 수 있는 외부파일은 HTML, JSP, 서블릿 페이지 등입니다.
```
<jsp:include page=“파일명” flush=“false” />
```
- flush 속성 값은 설정한 외부 파일로 제어가 이동할 때 현재 JSP 페이지가 지금까지 출력 버퍼에 저장한 결과를 처리합니다. 기본 값은 false이고, true로 설정하면 외부 파일로 제어가 이동할 때 현재 JSP 페이지가 지금까지 출력 버퍼에 저장된 내용을 웹 브라우저에 출력하고 출력 버퍼를 비웁니다.
- include 액션 태그는 forward 액션 태그처럼 외부 파일을 포함한다는 점이 비슷하지만 포함된 외부 파일이 실행된 후 현재 JSP 페이지로 제어를 반환한다는 것이 가장 큰 차이점
- JSP 컨테이너는 현재 JSP 페이지에서 include 액션 태그를 만나면 include 액션 태그에 설정된 외부 파일의 실행 내용을 현재 JSP 페이지의 출력 버퍼에 추가 저장되어 출력

>flush 속성 값<br>일반적으로 flush 속성은 false로 지정하는 것이 좋습니다. true로 지정하면 일단 출력 버퍼를 웹 브라우저에 전송하는데 이때 헤더 정보도 같이 전송됩니다. 헤더 정보가 웹 브라우저에 전송되고 나면 헤더 정보를 추가해도 결과가 반영되지 않습니다.

#### include 액션 태그와 include 디렉티브 태그의 차이
|구분|include 액션 태그|include 디렉티브 태그|
|----|-------|-------|
|처리시간|요청 시 자원을 포함합니다.|번역 시 자원을 포함합니다.|
|기능|별도의 파일로 요청 처리 흐름을 이동합니다.|현재 페이지에 삽입합니다.|
|데이터 전달방법|request 기본 내장 객체나 param 액션 태그를 이용하여 파라미터를 전달합니다.|페이지 내의 변수를 선언한 후 변수에 값을 저장합니다.|
|용도|화면 레이아웃의 일부분을 모듈화할 때 주로 사용합니다.|다수의 JSP 웹 페이지에서 공통으로 사용되는 코드나 저작권과 같은 문장을 포함하는 경우에 사용합니다.|
|기타|동적 페이지에 사용합니다.|정적 페이지에 사용합니다.|

```
예제)
first.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
  <h3>이 파일은 first.jsp입니다.</h3>
  <jsp:include page=“second.jsp” flush=“false” />
  <p>Java Server Page</p>
</body>
</html>

second.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<h3>이 파일은 second.jsp입니다.</h3>
Today is : <%=java.util.Calendar.getInstance().getTime()%>
```

#### day03/include.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h2>include 액션 태그</h2>
	<jsp:include page="include_date.jsp" flush="true" />
	<p>-------------------------------</p>
</body>
</html>
```

#### day03/include_date.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<p>오늘의 날짜 및 시각</p>
	<p><%=(new java.util.Date()).toLocaleString()%></p>
</body>
</html>
```

### param 액션 태그의 기능과 사용법 


### 자바빈즈 액션 태그의 기능과 사용법


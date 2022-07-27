# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1brsIrMUdMi1ZLQgY5Urz73Cgvq1F_VZx?usp=sharing)

## JSP 내장객체
- 내장 객체는 JSP 페이지에서 사용할 수 있도록 JSP 컨테이너에 미리 정의된 객체입니다.
- JSP 페이지가 서블릿 프로그램으로 번역될 때 JSP 컨테이너가 자옫으로 내장 객체를 멤버 변수, 메서드 매개변수 등 각종 참조 변수(객체)로 포함합니다.
- JSP 페이지에 별도로 import 문 없이 자유롭게 사용할 수 있습니다.
- 내장 객체는 서블릿 프로그램에서 모두 \_jspService() 메서드 내부에 있습니다. 

### 내장객체의 종류
|내장객체|반환유형|설명|
|----|-------|--------|
|request|javax.servlet.http.HttpServletRequest|웹브라우저의 HTTP 요청 정보를 저장합니다.|
|response|javax.servlet.http.HttpServletResponse|웹브라우저의 HTTP 요청에 대한 응답정보를 저장합니다.|
|out|javax.servlet.jsp.jspWriter|JSP 페이지에 출력할 내용을 담고 있는 출력 스트림입니다.|
|session|javax.servlet.http.HttpSession|웹브라우저의 정보를 유지하기 위한 세션 정보를 저장합니다.|
|application|javax.servlet.ServletContext|웹 애플리케이션의 콘텍스트 정보를 저장합니다.|
|pageContext|javax.servlet.jsp.PageContext|JSP 페이지의 정보를 저장합니다.|
|page|java.lang.Object|JSP 페이지를 구현한 자바 클래스로 JSP 페이지 자체를 나타냅니다.|
|config|javax.servlet.ServletConfig|JSP 페이지의 설정 정보를 저장합니다.|
|exception|java.lang.Throwable|JSP 페이지의 예외 발생을 처리합니다.|

- 모든 내장 객체는 JSP 컨테이너가 관리하는 객체로 이 중 request, session, application, pageContext를 이용하여 속성을 관리할 수 있습니다.
- 속성은 각각의 내장 객체가 존재하는 동안 JSP 페이지 사이에서 정보를 주고받거나 공유하는 데 사용

### 속성 처리 메서드의 종류 
|메서드|반환유형|설명|
|-----|-----|--------|
|setAttribute(String name, Object value)|void|해당 내장 객체의 속성 이름이 name인 속성 값을 value로 저장합니다.|
|getAttrubute(String name)|Object|해당 내장 객체의 속성 이름이 name인 속성 값을 가져옵니다.|
|removeAttribute(String name)|void|해당 내장 객체의 속성 이름이 name인 속성을 삭제합니다.|
|getAttributeNames()|java.util.Enumeration|해당 내장 객체의 모든 속성 이름을 가져옵니다(단 pageContext 내장 객체는 이 메서드를 제공하지 않습니다.)|


### request 내장 객체의 기능과 사용법
- JSP 페이지에서 가장 많이 사용되는 기본 내장 객체
- 웹 브라우저에서 서버의 JSP 페이지로 전달하는 정보를 저장
- JSP 컨테이너는 웹브라우저에서 서버로 전달되는 정보를 처리하기 위해 javax.servlet.http.HttpServletRequest 객체 타입의 request 내장 객체를 사용

#### 요청 파라미터 관련 메서드 종류 

|요청파라미터 관련 메서드|반환유형|설명|
|-------|-----|--------|
|getParameter(String name)|String|요청 파라미터 이름이 name인 값을 전달받습니다. 요청 파라미터 값이 없으면 null 값을 반환합니다.|
|getParameterValues(String name)|String[]|모든 요청 파라미터 이름이 name인 값을 배열 형태로 전달받습니다. 요청 파라미터 값이 없으면 null 값을 반환합니다.|
|getParameterNames()|java.util.Enumeration|모든 요청 파라미터의 이름과 값을 Enumeration 객체 타입으로 전달받습니다.|
|getParameterMap()|java.util.Map|모든 요청 파라미터 이름과 값을 Map 객체 타입으로 전달받습니다.|

```
예제)
request.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	<form action=“process.jsp” method=“post”>
		이 름 : <input type=“text” name=“name”> 
 		<input type=“submit“ value=“전송”>
	</form>
</body>
</html>

process.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
<%
	request.setCharacterEncoding(“utf-8”);
	String name = request.getParameter(“name”);
%>
</body>
</html>
```

#### day04/request01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<form action="request01_process.jsp" method="post">
		<p>	아 이 디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="text" name="passwd">
		<p>	<input type="submit" value="전송" />
	</form>
</body>
</html>
```

#### day04/request01_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("id");
		String password = request.getParameter("passwd");
	%>
	<p>	아이디 : <%=userid%>
	<p>	비밀번호 : <%=password%>
</body>
</html>
```

#### 요청 HTTP 헤더 관련 메서드
|요청HTTP헤더 관련 메서드|반환유형|설명|
|-------|-----|--------|
|getHeader(String name)|String|설정한 name의 헤더 값을 가져옵니다.|
|getHeaders(String name)|Enumeration|설정한 name의 헤더 목록 값을 가져옵니다.|
|getHeaderNames()|Enumeration|모든 헤더 이름을 가져옵니다.|
|getIntHeader(String name)|int|설정한 name의 헤더 값을 정수로 가져옵니다.|
|getDateHeader(String name)|long|설정한 name의 헤더 값을 시간 값으로 가져옵니다.|
|getCookies()|javax.servlet.http.Cookie|모든 쿠키 값을 가져옵니다.|

```
예제)
<%@ page import=“java.util.Enumeration” %>
<html>
<body>
<%
	Enumeration<String> en = request.getHeaderNames();
	while(en.hasMoreElements()) {
		String headerName = en.nextElement();
		String headerValue = request.getHeader(headerName);
		out.println(headerName + “ : ” + headerValue + “<br>”);
	}
%>
</body>
</html>
```

#### 웹브라우저/서버 관련 메서드
|웹브라우저/서버 관련 메서드|반환유형|설명|
|-------|-----|--------|
|getRemoteAddr()|String|웹브라우저의 IP 주소를 가져옵니다.|
|getContentLength()|long|웹브라우저의 요청 파라미터 길이를 가져옵니다.|
|getCharacterEncoding()|String|웹브라우저의 문자 인코딩을 가져옵니다.|
|getContentType()|String|웹브라우저의 콘텐츠 유형을 가져옵니다.|
|getProtocol()|String|웹브라우저의 요청 프로토콜을 가져옵니다.|
|getMethod()|String|웹브라우저의 HTTP요청 메서드(GET, POST)를 가져옵니다.|
|getRequestURI()|String|웹브라우저가 요청한 URI 경로를 가져옵니다.|
|getContextPath()|String|현재 JSP 페이지의 웹 애플리케이션 콘텍스트 경로를 가져옵니다.|
|getServerName()|String|서버 이름을 가져옵니다.|
|getServerPort()|int|실행 중인 서버 포트 번호를 가져옵니다.|
|getQueryString()|String|웹브라우저의 전체 요청 파라미터 문자열[물음표(?) 다음 URL에 할당된 문자열]을 가져옵니다.|

```
예제)
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	클라이언트 IP : <%=request.getRemoteAddr()%><br>
	요청 정보 길이 : <%=request.getContentLength()%><br>
	요청 정보 인코딩 : <%=request.getCharacterEncoding() %><br>
	요청 정보 콘텐츠 유형 : <%=request.getContentType() %><br>
	요청 정보 프로토콜 : <%=request.getProtocol() %><br>
	요청 정보 전송방식 : <%=request.getMethod() %><br>
	요청 URI : <%=request.getRequestURI() %><br>
	콘텍스트 경로 : <%=request.getContextPath() %><br>
	서버 이름 : <%=request.getServerName() %><br>
	서버 포트 : <%=request.getServerPort() %><br>
        쿼리문 : <%=request.getQueryString() %>
</body>
</html>
```
> 참고) 속성을 공유할 수 있는 유효범위<br>내장 객체가 존재하는 동안 사용할 수 있는 속성의 영역(scope)은 page, request, session, application입니다.

|영역|내장객체|속성의 유효 범위|
|----|-----|--------|
|page|pageContext|해당 페이지가 클라이언트에게 서비스를 제공하는 동안 유효합니다.|
|request|request|클라이언트 요청이 처리되는 동안 유효합니다.|
|session|session|세션이 유지되는 동안 유효합니다.|
|application|application|웹 애플리케이션이 실행되고 있는 동안 유효합니다.|


### response 내장 객체의 기능과 사용법 
- response 내장 객체는 사용자의 요청을 처리한 결과를 서버에서 웹 브라우저로 전달하는 정보를 저장
- 서버는 응답 헤어와 요청 처리 결과 데이터를 웹 브라우저로 보냅니다.
- JSP 컨테이너는 서버에서 웹 브라우저로 응답하는 정보를 처리하기 위해 javax.servlet.http.HttpServletResponse 객체 타입의 response 내장 객체를 사용

#### 페이지 이동 관련 메서드
|페이지 이동 관련 메서드|반환유형|설명|
|------|-----|--------|
|sendRedirect(String url)|void|설정한 URL 페이지로 강제 이동합니다.|

#### 페이지 이동 방법
- 포워드(forward) 방식<br>
현재 JSP 페이지에서 이동할 URL 로 요청 정보를 그대로 전달하므로 사용자가 최초로 요청한 정보가 이동된 URL에서도 유효합니다. 그러나 이동된 URL이 웹브라우저의 주소 창에 나타나지 않고 처음 요청한 URL이 나타나기 때문에 이동 여부를 사용자가 알 수 없습니다.<br>
**<jsp:forward page=“이동할 페이지” />**


- 리다이렉트(redirect) 방식<br>
처음 요청받은 현재 JSP 페이지로부터 이동할 URL을 웹브라우저로 반환합니다. 이때 웹 브라우저에서는 새로운 요청을 생성하여 이동할 URL에 다시 요청을 전송하므로 처음 보낸 요청 정보가 이동된 URL에서는 유효하지 않습니다. 즉, 클라이언트가 새로 페이지를 요청한 것과 같은 방식으로 페이지가 이동합니다. 따라서 이동된 URL이 웹 브라우저의 주소창에 보이는 것입니다.<br>
**response.sendRedirect(“이동할 페이지”)**

#### day04/response01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<form action="response01_process.jsp" method="post">
		<p>	아 이 디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="text" name="passwd">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### day04/response01_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("id");
		String password = request.getParameter("passwd");

		if (userid.equals("관리자") && password.equals("1234")) {
			response.sendRedirect("response01_success.jsp");
		} else {
			response.sendRedirect("response01_failed.jsp");
		}
	%>
</body>
</html>
```

#### day04/response01_success.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	로그인을 성공했습니다!!
</body>
</html>
```

#### day04/response01_failed.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<p>로그인을 실패했습니다.
	<p><a href="./response01.jsp"> 로그인가기</a>
</body>
</html>
```

#### 응답 HTTP 헤더 관련 메서드
- 응답 HTTP 헤더 관련 메서드는 서버가 웹 브라우저에 응답하는 정보에 헤더를 추가하는 기능을 제공
- 헤더 정보에는 주로 서버에 대한 정보가 저장되어 있습니다.

|응답 HTTP 헤더 관련 메서드|반환유형|설명|
|-------|-----|--------|
|addCookie(Cookie cookie)|void|쿠키를 추가합니다.|
|addDateHeader(String name, long date)|void|설정한 헤더 이름 name에 날짜/시간을 추가합니다.|
|addHeader(String name String value)|void|설정한 헤더 이름 name에 value를 추가합니다.|
|addIntHeader(String name, int value)|void|설정한 헤더 이름 name에 정수 값 value를 추가|
|setDateHeader(String name, long date)|void|설정한 헤더 이름 name에 날짜/시간을 설정|
|setHeader(String name, String value)|void|설정한 헤더 이름 name에 문자열 값 value 설정|
|setIntHeader(String name, int value)|void|설정한 헤더 이름 name에 정수 값 value를 설정|
|containsHeader(String name)|boolean|설정한 헤더 이름 name이 HTTP 헤더에 포함되었는지 여부를 확인|
|getHeader(String name)|String|설정한 헤더 이름 name 값을 가져옵니다.|

```
예제)
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	<%
		response.setHeader(“Cache-control”, “use_cache”);
		response.addHeader(“contentType”, “text/html; charset=utf-8”);
		response.setDateHeader(“date”, 1L);
	%>
	Cache-control : <%=response.getHeader(“Cache-control”)%><br>
	contentType : <%=response.getHeader(“contentType”)%><br>
	date : <%=response.getHeader(“date”)%>
</body>
</html>
```

#### day04/response02.jsp
```
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	이 페이지는 5초마다 새로고침 됩니다.<br>
	<%
		response.setIntHeader(“Refresh”, 5);
	%>
	<%=java.util.Calendar.getInstance().getTime()%>
</body>
</html>
```

#### 응답 콘텐츠 관련 메서드 
|응답 콘텐츠 관련 메서드|반환유형|설명|
|-------|-----|--------|
|setContentType(String type)|void|웹브라우저에 응답할 MIME 유형을 설정합니다.|
|getContentType()|String|웹브라우저에 응답할 MIME 유형을 가져옵니다.|
|setCharacterEncoding(String charset)|void|웹브라우저에 응답할 문자 인코딩을 설정합니다.|
|getCharacterEncoding()|String|웹브라우저에 응답할 문자 인코딩을 가져옵니다.|
|sendError(int status_code, String message)|void|웹브아루저에 응답할 오류(코드 및 오류메세지)를 설정|
|setStatus(int statuscode)|void|웹브라우저에 응답할 HTTP 코드를 설정합니다.|

```
예제)
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	<%
		response.setCharacterEncoding(“utf-8”);
		response.setContentType(“text/html; charset=utf-8”);
	%>
	문자 인코딩 : <%=response.getCharacterEncoding()%><br>
	콘텐츠 유형 : <%=response.getContentType()%>
</body>
</html>
```


#### day04/response03.jsp
```
<%@ page contentType=“text/html; charset=utf-8”%>
<html>
<body>
	<%
		response.sendError(404, “요청 페이지를 찾을 수 없습니다.”);
	%>
</body>
</html>
```


### out 내장 객체의 기능과 사용법
- out 내장 객체는 웹브라우저에 데이터를 전송하는 출력 스트림 객체입니다.
- JSP 컨테이너는 JSP 페이지에 사용되는 모든 표현문 태그와 HTML, 일반 텍스트 등을 out 내장 객체를 통해 웹브라우저에 그대로 전달합니다.
- out 내장 객체는 스크립틀릿 태그에 사용하여 단순히 값을 출력하는 표현문 태그 <%= ... %>와 같은 결과를 얻을 수 있습니다.

#### out 내장 객체 메서드의 종류

|out 내장 객체 메서드|반환유형|설명|
|-------|-----|---------|
|print(String str)|void|설정된 str 값을 웹브라우저에 출력합니다.|
|println(String str)|void|설정된 str 값을 웹 브라우저에 출력합니다. 이때 줄바꿈(\r\n 또는 \n)이 적용됩니다.|
|newLine()|void|줄바꿈(\r\n 또는 \n)을 출력합니다.|
|getBufferSize()|int|현재 출력 버퍼의 크기를 가져옵니다.|
|getRemaining()|int|현재 남아 있는 출력 버퍼의 크기를 가져옵니다.|
|clear()|void|현재 출력 버퍼에 저장되어 있는 내용을 웹브라우저에 전송하지 않고 비웁니다. 만약 버퍼가 이미 플러쉬되었다면 IOException이 발생합니다.|
|clearBuffer()|void|현재 출력 버퍼에 저장되어 있는 내용을 웹브라우저에 전송하지 않고 비웁니다. 만약 버퍼가 이미 플러쉬되었다면 IOException이 발생하지 않습니다.|
|flush()|void|현재 출력 버퍼에 저장되어 있는 내용을 웹브라우저에 전송하고 비웁니다.|
|isAutoFlush()|boolean|출력버퍼가 채워졌을 때의 처리를 결정합니다. 자동으로 플러쉬하는 경우 true를 반환하고, 그렇지 않은 경우 false를 반환|



#### day04/out01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<%
		out.println("오늘의 날짜 및 시각 " + "<br>");		
		out.println(java.util.Calendar.getInstance().getTime());
	%>
</body>
</html>
```

#### day04/out02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<form action="out02_process.jsp" method="post">
		<p> 아 이 디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="text" name="passwd">
		<p>	<input type="submit" value="전송" />
	</form>
</body>
</html>
```

#### day04/out02_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("id");
		String password = request.getParameter("passwd");
	%>
	<p>	아 이 디 : <% out.println(userid); %>
	<p>	비밀번호 : <% out.println(password); %>	
</body>
</html>
```
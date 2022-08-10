# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1yyeSZdiTZYUKrnjAHr_oODz4lfR39s_j?usp=sharing)

# 쿠키

- 세션과 마찬가지로 클라이언트와 웹 서버 간의 상태를 지속적으로 유지하는 방법입니다.
- 세션과 달리 상태 정보를 웹서버가 아닌 클라이언트에 저장합니다.
- 쿠키는 클라이언트의 정보를 웹 브라우저에 저장하므로 이후에 웹 서버로 전송되는 요청에는 쿠키가 가지고 있는 정보가 포함됩니다.
- 웹 브라우저가 접속했던 웹 사이트에 관한 정보와 개인 정보가 기록되기 때문에 보안에 문제가 있습니다.

## 쿠키의 동작과정

- **쿠키 생성단계** : 쿠키를 사용하려면 먼저 쿠키를 생성해야 합니다. 쿠키는 주로 웹 서버 측에서 생성합니다. 생성된 쿠키는 응답 데이터와 함께 저장되어 웹 브라우저에 전송됩니다.
- **쿠키 저장단계** : 웹브라우저는 응답 데이터에 포함된 쿠키를 쿠키 저장소에 보관합니다. 쿠키는 종류에 따라 메모리나 파일로 저장됩니다.
- **쿠키 전송단계** : 웹브라우저는 한 번 저장된 쿠키를 요청이 있을 때마다 웹 서버에 전송합니다. 웹 서버는 웹 브라우저가 전송한 쿠키를 사용하여 필요한 작업을 수행할 수 있습니다.

> 일단 웹 브라우저에 쿠키가 저장되면 웹 브라우저는 쿠키가 삭제되기 전까지 웹 서버에 쿠키를 전송합니다

#### Cookie 클래스의 메서드 종류

|메서드|반환 유형|설명|
|-----|----|----------|
|getComment()|String|쿠키에 대한 설명을 반환합니다.|
|getDomain()|String|쿠키에 대한 유효한 도메인 정보를 반환합니다.|
|getMaxAge()|int|쿠키의 사용 가능 기간에 대한 정보를 반환합니다.|
|getName()|String|쿠키의 이름을 반환합니다.|
|getPath()|String|쿠키의 유효한 디렉토리 정보를 반환합니다.|
|getSecure()|boolean|쿠키의 보안 설정을 반환합니다.|
|getValue()|String|쿠키에 설정된 값을 반환합니다.|
|getVersion()|int|쿠키의 버전을 반환합니다.|
|setComment(String)|void|쿠키에 대한 설명을 설정합니다.|
|setDomain(String)|void|쿠키에 유효한 도메인을 설정합니다.|
|setMaxAge(int)|void|쿠키의 유효기간을 설정합니다.|
|setPath(String)|void|쿠키의 유효기간을 설정합니다.|
|setSecure(boolean)|void|쿠키의 보안을 설정합니다.|
|setValue(String)|void|쿠키의 값을 설정합니다.|
|setVersion(int)|void|쿠키의 버전을 설정합니다.|

#### 쿠키와 세션의 차이

|구분|쿠키|세션|
|----|------|-------|
|사용 클래스|Cookie 클래스|HttpSessin 인터페이스|
|저장 형식|텍스트 형식|Object 형|
|저장 장소|클라이언트|서버(세션 아이디만 클라이언트에 저장)|
|종료 시점|쿠키 저장 시 설정(설정하지 않을 경우 브라우저 종료 시 소멸)|정확한 시점을 알 수 없음|
|리소스|클라이언트의 리소스 사용|서버의 리소스 사용|
|보안|클라이언트에 저장되므로 사용자의 변경이 가능하여 보안에 취약|서버에 저장되어 있어 상대적으로 안정적|

## 쿠키 생성

- 쿠키를 사용하려면 먼저 Cookie 클래스를 사용하여 쿠키를 생성
- 쿠키를 생성하는 데이는 Cookie() 생성자를 사용합니다.
- 쿠키를 생성한 후에는 반드시 response 내장 객체의 addCookie() 메서드로 쿠키를 설정해야 합니다.

```java
Cookie Cookie(String name, String value)
```

#### Cookie() 생성자 사용 예
```java
Cookie Cookie = new Cookie(“memberId”, “admin”);
response.addCookie(cookie);
```
#### source/cookie01.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Cookie</title>
</head>
<body>
	<form action="cookie01_process.jsp" method="POST">
		<p>	아 이 디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="text" name="passwd">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/cookie01_process.jsp
```java
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Cookie</title>
</head>
<body>
	<%
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("passwd");

		if (user_id.equals("admin") && user_pw.equals("1234")) {
			Cookie cookie_id = new Cookie("userID", user_id);
			Cookie cookie_pw = new Cookie("userPW", user_pw);
			response.addCookie(cookie_id);
			response.addCookie(cookie_pw);
			out.println("쿠기 생성이 성공했습니다<br>");
			out.println(user_id + "님 환영합니다");
		} else {
			out.println("쿠티 생성이 실패했습니다");
		}
	%>
</body>
</html>
```

## 쿠키 정보 

### 쿠키 객체 얻기 
- 클라이언트에 저장된 모든 쿠키 객체를 가져오려면 request 내장 객체의 getCookies() 메서드를 사용합니다.
- 쿠키 객체가 여러 개 일때는 배열 형태로 가져옵니다.
```java
Cookie[] requst.getCookies()
```

#### getCookies() 메서드 사용 예
```java
Cookie[] cookies = request.getCookies();
```

### 쿠키 객체의 정보 얻기
```java
String getName() - 쿠키 이름
String getValue() - 쿠키 값 
```

#### getName(), getValue() 메서드 사용 예
```
Cookie[] cookies = request.getCookies();

for(int i = 0; i < cookies.length; i++) {
   out.println(cookies[i].getName() + “ : ” + cookies[i].getValue() + “<br>”);
}
```

#### source/cookie02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Cookie</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		out.println("현재 설정된 쿠키의 개수 => " + cookies.length + "<br>");
		out.println("==========================<br>");
		for (int i = 0; i < cookies.length; i++) {
			out.println("설정된 쿠티의 속성 이름 [ " + i + " ] : " + cookies[i].getName() + "<br>");
			out.println("설정된 쿠키의 속성 값 [ " + i + " ] : " + cookies[i].getValue() + "<br>");
			out.println("---------------------------------------------<br>");
		}
	%>
</body>
</html>
```

## 쿠키 삭제
- Cookie 클래스는 쿠키를 삭제하는 기능을 별도로 제공하지 않는다.
- 쿠키를 더 유지할 필요가 없으면 쿠키의 유효기간을 만료하면 됩니다.
- setMaxAge() 메서드에 유효 기간을 0으로 설정하여 쿠키를 삭제할 수 있습니다.

```java
void setMaxAge(int age)
```

#### setMaxAge() 메서드 사용 예
```java
Cookie cookie = new Cookie(“memberId”, “admin”);
cookie.setMaxAge(0);
response.addCookie(cookie);
```

#### source/cookie03.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Cookie</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setMaxAge(0);
			response.addCookie(cookies[i]);
		}
		response.sendRedirect("cookie02.jsp");
	%>
</body>
</html>
```

* * *
# 세션

- 세션은 클라이언트와 웹 서버 간의 상태를 지속적으로 유지하는 방법2) 세션은 웹서버에서만 접근이 가능하므로 보안유지에 유리하며 데이터를 저장하는 데 한계가 적습니다.
- 세션은 오직 웹 서버에 존재하는 객체로 웹 브라우저마다 하나씩 존재하므로 웹 서버의 서비스를 제공받는 사용자를 구분하는 단위가 됩니다.
- 세션을 사용하면 클라이언트가 웹 서버의 세션에 의해 가상으로 연결된 상태가 되며, 웹브라우저를 닫기 전까지 웹 페이지를 이동하더라도 사용자의 정보가 웹 서버에 보관되어 있어 사용자 정보를 잃지 않습니다.
- JSP 페이지는 세션 기능을 사용할 수 있도록 session 내장 객체를 제공합니다.

#### session 내장객체 메서드 종류

|메서드|반환유형|설명|
|------|----|----------|
|getAttribute(String name)|Object|세션 속성 이름이 name인 속성 값을 Object형으로 반환합니다. 해당되는 속성 이름이 없을 때는 null을 반환합니다. 반환값이 Object 형이므로 반드시 형 변환을 하여 사용해야 합니다.|
|getAttributeNames()|Enumeration|세션 속성 이름을 Enumeration 객체 타입으로 반환|
|getCreationTime()|long|세션이 생성된 시간을 반환합니다. 1970년 1월 1일 0시 0초부터 현재 세션이 생성된 시간까지 경과한 시간을 1/1000초 값으로 반환|
|getId()|String|세션에 할당된 고유아이디를 String형으로 반환|
|getLastAccessedTime()|long|해당 세션에 클라이언트가 마지막으로 request를 보낸 시간을 반환합니다.|
|getMaxInactiveInterval(int interval)|int|해당 세션을 유지하기 위해 세션 유지 시간을 반환합니다. 기본 값은 1,800초(30분)입니다.|
|isNew()|boolean|해당 세션의 생성여부를 반환합니다. 처음 생성된 세션이면 true를 반환하고 이전에 생성된 세션이면 false를 반환합니다.|
|removeAttribute(String name)|void|세션 속성 이름이 name인 속성을 제거합니다.|
|setAttribute(String name, Object value)|void|세션 속성 이름이 name인 속성에 value를 할당합니다.|
|setMaxInactiveInterval(int interval)|void|해당 세션을 유지하기 위한 세션 유지 시간을 초 단위로 설정|
|invalidate()|void|현재 세션에 저장된 모든 세션 속성을 제거합니다.|

## 세션 생성
- 세션 속성값은 Object 객체 타입만 가능하기 때문에 int, double, char 등의 기본 타입은 사용할 수 없습니다.
- 만약 동일한 세션의 속성이름으로 세션을 설정하면 마지막에 설장한 것이 세션 속성 값이 됩니다.
```java
void setAttribute(String name, Object value)
```

#### source/session01.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<form action="session01_process.jsp" method="POST">
		<p>	아 이 디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="text" name="passwd">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/session01_process.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("passwd");

		if (user_id.equals("admin") && user_pw.equals("1234")) {
			session.setAttribute("userID", user_id);
			session.setAttribute("userPW", user_pw);
			out.println("세션 설정이 성공했습니다<br>");
			out.println(user_id+"님 환영합니다");
		} else {
			out.println("세션 설정이 실패했습니다");
		}
	%>
</body>
</html>
```

## 세션 정보
생성된 세션의 정보를 얻어오려면 session 내장 객체의 getAttribute() 또는 getAttributeNames() 메서드를 사용합니다.

### 단일 세션 정보 얻기
getAtrribute() 메서드는 반환 유형이 Object 형이므로 반드시 형 변환을 하여 사용해야 합니다.

```java
Object getAttribute(String name)
```
사용 예) 
```
String id = (String)session.getAttribute(“memberId”);
```

#### source/session02.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%
		String user_id = (String) session.getAttribute("userID");
		String user_pw = (String) session.getAttribute("userPW");

		out.println("설정된 세션의 속성 값 [1] : " + user_id + "<br>");
		out.println("설정된 세션의 속성 값 [2] : " + user_pw);
	%>
</body>
</html>
```

### 다중 세션 정보 얻기
- getAttributeNames() 메서드를 사용
- getAttributeNames() 메서드는 반환 유형이 Enumeration 객체 타입이르모 모든 세션 정보를 얻어오는 데 유용
```
Enumeration getAttributeNames();
```

#### getAttributeNames() 메서드 사용 예
```java
Enumeration<String> enum = session.getAttributeNames();

while(enum.hasMoreElements()) {
   String name = enum.nextElement();
   String value = (String)session.getAttribute(name);
}
```

#### source/session03.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Enumeration"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%
		String name;
		String value;

		Enumeration en = session.getAttributeNames();
		int i = 0;

		while (en.hasMoreElements()) {
			i++;
			name = en.nextElement().toString();
			value = session.getAttribute(name).toString();
			out.println("설정된 세션의 속성 이름 [ " + i + " ] : " + name + "<br>");
			out.println("설정된 세션의 속성 값 [ " + i + " ] : " + value + "<br>");
		}
	%>
</body>
</html>
```

## 세션 삭제 
session 내장 객체의 removeAttribute() 또는 invalidate() 메서드를 사용하여 삭제

#### 단일 세션 삭제하기
```java
void removeAttribute(String name)
```
- remoteAttribute() 메서드 사용 예
```java
session.removeAttribute(“memberId”);
```

#### 다중 세션 삭제하기
```java
void invalidate()
```
- invalidate() 메서드 사용 예

```
session.invalidate();
```

#### source/session06.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>Session</title>
</head>
<body>
	<p> <h4>----- 세션을 삭제하기 전 -----</h4>
	<%
		String user_id = (String) session.getAttribute("userID");
		String user_pw = (String) session.getAttribute("userPW");
		
		out.println("설정된 세션 이름 userID : " + user_id + "<br>");
		out.println("설정된 세션 값 userPW : " + user_pw + "<br>");
		
		if (request.isRequestedSessionIdValid() == true) {
			out.print("세션이 유효합니다.");
		}else {

			out.print("세션이 유효하지 않습니다.");
		}		

		session.invalidate();
	%>
	<p> <h4>----- 세션을 삭제한 후 -----</h4>
	<%		
		if (request.isRequestedSessionIdValid() == true) {
			out.print("세션이 유효합니다.");
		}else {

			out.print("세션이 유효하지 않습니다.");
		}
	%>
</body>
</html>
```

## 세션 유효 시간 설정 
- 세션 유효 시간은 세션을 유지하기 위한 세션의 일정 시간을 말합니다.
- 웹 브라우저에 마지막 접근한 시간부터 일정 시간 이내에 다시 웹 브라우저에 접근하지 않으면 자동으로 세션이 종료 됩니다.
- session 내장 객체의 setMaxInactiveInterval() 메서드를 사용합니다.
	```java
	void setMaxInactiveInterval(int interval)
	```
- interval은 세션 유효시간이며, 기본값은 1,800초입니다. 초단위로 설정
- 세션 유효시간을 0이나 음수로 설정하면 세션 유효시간이 없는 상태가 된다.
- 이 경우 세션을 삭제했을 때 session.invalidate() 메서드를 호출하지 않으면 생성된 세션 속성이 웹 서버에서 제거되지 않고 유지

- setMaxInactiveInterval() 메서드 사용 예
	```java
	session.setMaxInactiveInterval(60 * 60);
	```
#### source/session07.jsp 
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<p> <h4>----- 세션 유효 시간 변경 전 -----</h4>
	<%
		int time = session.getMaxInactiveInterval() / 60;

		out.println("세션 유효 시간  : " + time + "분<br>");
	%>
	<p>	<h4>----- 세션 유효 시간 변경 후 -----</h4>
	<%
		session.setMaxInactiveInterval(60 * 60);
		time = session.getMaxInactiveInterval() / 60;

		out.println("세션 유효 시간  : " + time + "분<br>");
	%>
</body>
</html>
```

#### source/ssession08.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%
		String sessin_id = session.getId();

		long last_time = session.getLastAccessedTime();

		long start_time = session.getCreationTime();

		long used_time = (last_time - start_time) / 60000;

		out.println("세션 아이디 : " + sessin_id + "<br>");
		out.println("요청 시작 시간  : " + start_time + "<br>");
		out.println("요청 마지막 시간  : " + last_time + "<br>");
		out.println("웹 사이트에서 경과 시간  : " + used_time + "<br>");
	%>
</body>
</html>
```
* * *

# 게시판 프로젝트(회원인증, 인가 처리하기 - 로그인, 로그아웃)

- [예제 소스](https://github.com/yonggyo1125/Board_JSP/tree/login_logout)


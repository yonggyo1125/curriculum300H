# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1xbA6zXrtJJPWvSl-bA8K04gKVr4GWie_?usp=sharing)

## JSP의 특징
- JSP는 서블릿 기술의 확장 
- JSP는 유지 관리가 용이
- JSP는 빠른 개발이 가능
- JSP로 개발하면 코드 길이를 줄일 수 있다.

## JSP의 페이지 처리과정

![JSP 페이지 처리과정](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSP%EC%9D%98%20%ED%8A%B9%EC%A7%95%2C%20JSP%EC%9D%98%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%B2%98%EB%A6%AC%EA%B3%BC%EC%A0%95%2C%20JSP%EC%9D%98%20%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%2C%20%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%ED%83%9C%EA%B7%B8%2C%20%EB%94%94%EB%A0%89%ED%8B%B0%EB%B8%8C%20%ED%83%9C%EA%B7%B8%2C%20%EC%A3%BC%EC%84%9D%EC%B2%98%EB%A6%AC/images/JSP%ED%8E%98%EC%9D%B4%EC%A7%80_%EC%B2%98%EB%A6%AC_%EA%B3%BC%EC%A0%95.png)

1. 웹 브라우저가 웹 서버에 JSP를 요청합니다. 웹 서버는 요청된 Hello.jsp에서 jsp 확장자를 발견하여 JSP 페이지임을 확인하고 웹 서버에 있는 JSP 컨테이너에 전달합니다.
2. JSP 컨테이너는 JSP 페이지를 서블릿 프로그램인 Hello_jsp.java로 변환합니다.
3. JSP 컨테이너가 서블릿 프로그램을 컴파일하여 Hello_jsp.class로 만들고 이를 웹 서버에 전달합니다.
4. 웹 서버는 정적 웹페이지처럼 \*.class의 실행 결과를 웹브라우저에 응답으로 전달하므로 웹 브라우저는 새로 가공된 HTML 페이지를 동적으로 처리한 결과를 보여줍니다.

## JSP 생명 주기
JSP페이지를 컴파일한 \*.class에는 \_jspInit(), \_jspService(), \_jspDestroy() 메서드가 존재하며, JSP 생성부터 파괴까지의 과정에서 다음과 같은 역할을 수행합니다.

![JSP 생명 주기](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSP%EC%9D%98%20%ED%8A%B9%EC%A7%95%2C%20JSP%EC%9D%98%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%B2%98%EB%A6%AC%EA%B3%BC%EC%A0%95%2C%20JSP%EC%9D%98%20%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%2C%20%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%ED%83%9C%EA%B7%B8%2C%20%EB%94%94%EB%A0%89%ED%8B%B0%EB%B8%8C%20%ED%83%9C%EA%B7%B8%2C%20%EC%A3%BC%EC%84%9D%EC%B2%98%EB%A6%AC/images/JSP_%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0.png)

1. 번역(translation)단계
JSP 컨테이너가 JSP 소스 파일을 자바 코드(서블릿)로 변환합니다. 번역 단계에서는 JSP 컨테이너는 JSP 파일을 읽고 구문을 분석합니다. JSP 컨테이너는 JSP 페이지와 페이지에 사용된 태그 라이브러리를 참조하는 사용자 정의 태그 표준 디렉티브, 액션 태그의 구문 정확성을 검증합니다.

2. 컴파일(compilation)단계
JSP 컨테피너가 번역 단계에서 생성된 자바 코드인 서블릿을 컴파일하여 클래스 파일을 생성합니다. 컴파일 단계에서는 자바 코드의 모든 구문을 검사합니다. 즉, JSP 페이지 내의 선언문, 처리문, 표현문 등의 스크립트 태그를 사용하여 삽입된 자바 코드의 구문 오류를 검사합니다.

3. 로딩(loading) 및 초기화(initialization) 단계
JSP 컨테이너가 앞의 두 단계에서 생성된 \*.class를 로딩하고 클래스의 인스턴스를 작성합니다. 이때 인수가 없는 생성자를 사용합니다. Hell_jsp.class가 로딩되고 인스텬스가 만들어집니다. 이제 JSP 컨테이너는 서블릿의 init() 메서드, 즉 jspInit()을 호출하여 인스턴스가 된 객체를 초기화합니다. 일반적으로 초기화는 한 번만 수행이되고 데이터베이스 연결, 파일 열기, 룩업 테이블 생성 등을 초기화합니다.

4. 실행(execution) 단계
각 클라이언트의 요청에 대해 JSP 컨테이너가 요청 및 응답 객체를 전달하는 \_jspService()메서드를 실행합니다. 웹 브라우저가 JSP 페이지를 요청하여 페이지가 로딩 및 초기화돌 때마다 JSP 컨테이너는 JSP에서 \_jspService() 메서드를 호출하여 응답 객체를 전달합니다. 이 단계는 JSP 생명 주기가 끝날 때까지 모든 클라이언트의 요청에 대해 상호 작용을 합니다.

5. 소멸(destruction) 단계
JSP 생명 주기를 완료합니다. JSP 컨터에너는 실행되고 있는 JSP를 \_jspDestroy() 메서드를 사용하여 제거합니다. \_jspDestroy() 메서드를 서블릿의 destory() 메서드에 해당합니다. 이 메서드는 데이터베이스 연결 해제 또는 열려 있는 파일 닫기 등을 수행해야 할 때 jspDestroy() 메서드를 오버라이딩 합니다. JSP 컨테이너가 해당 서블릿 인스턴스를 제거할 때 어떤 활동을 정리하기 위해 \_jspDestory() 메서드를 호출합니다.

> \_jspInit(), \_jspDestory() 메서드는 컨테이너가 기본 기능을 제공하기 때문에 오버라이딩이 선택 사항이지만 기본적으로 \_jspService() 메서드는 컨테이너가 추가하기 때문에 오버라이딩 할 수 없습니다.


## 스크립트 태그

### 스크립트 태그의 종류

|스크립트 태그|형식|설명|
|----|---|----------|
|선언문(declaration)|<%! ... %>|자바 변수나 메서드를 정의하는 데 사용합니다.|
|스크립틀릿(scriptlet)|<% .... %>|자바 로직 코드를 작성하는데 사용합니다.|
|표현문(expression)|<%= ... %>|변수, 계산식, 메서드 호출 결과를 문자열 형태로 출력하는 데 사용합니다.|

### 선언문(declaration)

- 선언문(declaration)태그는 변수나 메서드 등을 선언하는 태그
- 선언문 태그에 선언된 변수와 메서드는 서블릿 프로그램으로 번역될 때 _jspService() 메서드 외부에 배치되므로 JSP 페이지의 임의의 위치에서 선언할 수 있습니다.
- 스크립틀릿 태그보다 나중에 선언해도 스크립틀릿 태그에서 사용할 수 있습니다.
- 선언문 태그로 선언된 변수는 서블릿 프로그램으로 번역될 때 클래스 수준의 멤버 변수가 되므로 전역 변수로 사용됩니다.
- 선언문 태그로 선언된 메서드는 전역변수처럼 전역 메서드로 사용됩니다.

#### day02/declaration01.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!int data = 50;%>
	<%
		out.println("Value of the variable is:" + data);
	%>
</body>
</html>
```

#### day02/declaration02.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!int sum(int a, int b) {
		return a + b;
	}%>
	<%
		out.println("2 + 3 = " + sum(2, 3));
	%>
</body>
</html>
```

#### day02/declaration03.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!String makeItLower(String data) {
		return data.toLowerCase();
	}%>
	<%
		out.println(makeItLower("Hello World"));
	%>
</body>
</html>
```

### 스크립틀릿(scriptlet)
문법 (각 행이 세미콜론으로 끝나야 함)
```
<% 자바 코드; %>
```
- 스크립틀릿 태그에 작성된 자바 코드는 서블릿 프로그램으로 변환될 때 \_jspService() 메서드 내부에 복사 됩니ᅟᅡᆮ. 
- \_jspService() 메서드 내부에 복사되므로 지역변수가 되어 이 태그에 선언된 변수는 스크립틀릿 태그 내에서만 사용할 수 있습니다.


#### 선언문 태그와 스크립틀릿 태그의 비교

|선언문 태그|스크립틀릿 태그|
|----|----|
|변수뿐만 아니라 메서드를 선언할 수 있습니다.|스크립틀릿 태그는 메서드 없이 변수만을 선언할 수 있습니다.|
|서블릿 프로그램으로 변환될 때 \_jspService() 메서드 외부에 배치됩니다.|서블릿 프로그램으로 변환될 때 \_jspService() 메서드 내부에 배치됩니다.|


#### day02/scriptlet01.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<% 
		int a = 2;
		int b = 3;
		int sum = a + b;
		out.println("2 + 3 = " + sum);
	%>
</body>
</html>
```

#### day02/scriptlet02.jsp
```
<html>
<head>
<title>Scripting tag</title>
</head>
<body>
	<%
		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 0)
				out.println(i + "<br>");
		}
	%>
</body>
</html>
```

### 표현문(expression)
- <%=와 %>를 사용하여 웹브라우저에 출력할 부분을 표현합니다.
- 표현문 태그는 스크립틀릿 태그에서 사용할 수 없으므로 이 경우네는 out.print() 메서드를 사용해야 합니다.
```
<%=자바 코드%>     각 행을 세미콜론으로 종료할 수 없음
```

#### day02/expression01.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<p> Today's date: <%=new java.util.Date()%></p>
</body>
</html>

```

#### day02/expression02.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%
		int a = 10;
		int b = 20;
		int c = 30;
	%>
	<%=a + b + c%>
</body>
</html>
```

## 디렉티브 태그
- 디렉티브 태그는 JSP 페이지를 어떻게 처리할 것인지 설정하는 태그입니다. 
- 디렉티브 태그는 JSP 페이지가 서블릿 프로그램에서 서블릿 클래스로 변환될 때 JSP 페이지와 관련된 정보를 JSP 컨테이너에 지시하는 메시지입니다. 
- 디렉티브 태그는 JSP 페이지를 수정하여 다시 컴파일 하는 경우에만 역할을 수행하기 때문에 개별 HTML 응답에 특별한 영향을 미치지 않습니다.
- 디렉티브 태그는 세 종류이며 모두 <%@ ... %>을 사용합니다.

### 디렉티브 태그의 종류
|디렉티브 태그| 형식|설명|
|----|----|---------|
|page|<%@ page ...%>|JSP 페이지에 대한 정보를 설정합니다.|
|include|<%@ include ... %>|JSP 페이지의 특정 영역에 다른 문서를 포함합니다.|
|taglib|<%@ taglib ... %>|JSP 페이지에서 사용할 태그 라이브러리를 설정합니다.|

### page 디렉티브 태그의 기능과 사용법
- JSP 페이지에 대한 정보를 설정하는 태그
- JSP 페이지가 생성할 콘텐츠 유형의 문서, 사용할 자바 클래스, 오류 페이지 설정, 세션 사용 여부, 출력 버퍼의 존재 유무 등과 같이 JSP 컨테이너가 JSP 페이지를 실행하는 데 필요한 정보를 설정할 수 있습니다.
- JSP 페이지의 어디에서든 선언할 수 있지만 일반적으로 JSP 페이지의 최상단에 선언하는 것을 권장
- 하나의 page 디렉티브 태그에 하나 또는 여러 개의 속성을 설정할 수 있습니다. 또는 여러 개의 속성마다 개별적으로 page 디렉티브 태그를 선언할 수 있습니다.
- import 속성을 제외한 속성은 JSP 페이지에 한 번씩만 설정할 수 있습니다.
```
<%@ page 속성1=“값” [속성2=“값2” .. ] %>    
<%과 @사이에 공백이 없어야 함
```
#### page 디렉티브 태그의 속성
|속성|설명|기본값|
|----|---------------|----|
|language|현재 JSP 페이지가 사용할 프로그래밍 언어를 설정합니다.<br><% page language=“java” %>|java|
|contentType|현재 JSP 페이지가 생성할 문서의 콘텐츠 유형을 설정합니다.<br>(text/html, text/xml , text/plain)<br><%@ page contentType=“text/html%><br>constentType은 문자열 세트를 설정 할 수 있음<br><%@ page contentType=“text/html; charset=utf-8” %>|text/html|
|pageEncoding|현재 JSP 페이지의 문자 인코딩을 설정합니다.<br><%@ page pageEncoding=“ISO-8859-1” %><br>contentType 속성의 문자열 세트로도 설정 할 수 있음<br><%@ page contentType=“text/html; charset=ISO-8859-1” %>|ISO-8859-1|
|import|현재 JSP 페이지가 사용할 자바 클래스를 설정합니다.<br><%@ page import=“java.io.*” %><br><%@ page import=“java.io.*, java.lang.*” %><br><%@ page import=“java.io.*” %><br><%@ page import=“java.lang.*” %>||
|session|현재 JSP 페이지의 세션 사용여부를 설정합니다.<br><%@ page session=“true” %>|true|
|buffer|현재 JSP 페이지의 출력 버퍼의 크기를 설정합니다.<br>속성값을 none으로 설정하면 출력 버퍼를 채우지 않고 웹 브라우저로 직접 전송 <%@ page buffer=“none” %><br>출력 버퍼 크기를 32KB로 설정 : <% page buffer=“32KB” %>|8KB|
|autoFlush|자동으로 출력 버퍼를 비우는 것을 제어<br><%@ page autoFlush=“true” %>|true|
|isThreadSafe|현재 JSP 페이지의 멀티스레드 허용 여부를 설정합니다.<br>true – JSP 페이지에 대해 수신된 여러 요청이 동시에 처리<br>false – JSP 페이지에 대한 요청이 순차적으로 처리<br><%@ page isThreadSafe=“true” %>|true|
|info|현재 JSP 페이지에 대한 설명을 설정합니다.(서블릿 인터페이스 getServletInfo() 메서드 사용)<br><%@ page info=“Home Page JSP” %>||
|errorPage|현재 JSP 페이지에 오류가 발생했을 때 보여줄 오류페이지를 설정합니다.<br><%@ page errorPage=“MyErrorPage.jsp” %>||
|isErrorPage|현재 JSP 페이지가 오류 페이지인지 여부를 설정합니다.<br>true로 설정하면 내장 객체인 exception 변수를 사용할 수 있습니다.<br><%@ page isErrorPage=“true” %>|false|
|isELIgnored|현재 JSP 페이지의 표현언어(EL) 지원 여부를 설정합니다.<br><%@ isELIgnored=“true” %>|false|
|isScriptingEnabled|현재 JSP 페이지의 스크립트 태그(선언문, 스크립틀릿, 표현문) 사용 여부를 설정합니다.<br><%@ page isScriptingEnabled=“false” %>||

#### day02/page_contentType01.jsp
```
<%@ page contentType="application/msword"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	Today is: <%=new java.util.Date()%>
</body>
</html>
```

#### day02/page_contentType02.jsp
```
<%@ page contentType="text/xml; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<h2>contentType 디렉티브 태그</h2>
	<h4>text/html : HTML 출력</h4>
	<h4>charset=utf-8 : 문자 인코딩</h4>
</body>
</html>
```

#### day02/page_import.jsp
```
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%@ page import="java.util.Date"%>
	Today is <%=new Date()%>
</body>
</html>
```

#### day02/page_buffer.jsp
```
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%@ page buffer="16kb"%>
	Today is: <%= new java.util.Date() %>
</body>
</html>
```

#### day02/page_info.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%@ page info="Date 클래스를 이용한 날짜 출력하기"%>
	Today is <%=new java.util.Date()%>
</body>
</html>
```

#### day02/page_errorPage.jsp
```
<%@ page errorPage="page_errorPage_error.jsp"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%
		String str = null;
		out.println(str.toString());
	%>
</body>
</html>
```

#### day02/page_errorPage_error.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<h4>errorPage 디렉티브 태그</h4>
	에러가 발생했습니다
</body>
</html>
```

#### day02/page_isErrorPage.jsp
```
<%@ page errorPage="page_isErrorPage_error.jsp"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%
		String str = null;
		out.println(str.toString());
	%>
</body>
</html>
```

#### day02/page_isErrorPage_error.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<h4>에러가 발생되었습니다.</h4>
	<h5>exception 내장 객체 변수</h5>
	<%
		exception.printStackTrace(new java.io.PrintWriter(out));
	%>
</body>
</html>
```

#### day02/page_isELIgnored.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="true"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%
		request.setAttribute("RequestAttribute", "request 내장 객체");
	%>
	${requestScope.RequestAttribute}
</body>
</html>
```

### include 디렉티브 태그의 기능과 사용법
- JSP 페이지의 특정 영역에 외부 파일의 내용을 포함하는 태그
- 포함할 수 있는 외부 파일은 HTML, JSP, 텍스트 파일 등이 있습니다.
- include 디렉티브 태그는 JSP 페이지 어디에서든 선언할 수 있습니다.
- include 디렉티브 태그는 서블릿 프로그램으로 번역될 때 현재 JSP 페이지와 설정된 다른 외부 파일의 내용이 병합되어 번역됩니다.

```
<% include file=“파일명” %>
```

#### include 디렉티브 태그 사용 예
```
<html>
<body>
<%@ include file=“header.jsp” %>
Today is : <%= java.util.Calendar.getInstance().getTime() %>
<%@ include file=“footer.jsp” %>
</body>
</html>
```

#### day02/include01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%@ include file="include01_header.jsp"%>
	<h4>---------- 현재 페이지 영역 -------------</h4>
</body>
</html>
```

#### day02/include01_header.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<h4>헤더 페이지 영역입니다</h4>
</body>
</html>
```

#### day02/include02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Directives Tag</title>
</head>
<body>
	<%@ include file="include02_header.jsp"%>
	<p>방문해 주셔서 감사합니다.</p>
	<%@ include file="include02_footer.jsp"%>
</body>
</html>
```

#### day02/include02_header.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%!
	int pageCount = 0;
	void addCount() {
		pageCount++;
	}
%>
<%
	addCount();
%>
<p>	이 사이트 방문은	<%=pageCount%>번째 입니다.</p>

```

#### day02/include02_footer.jsp
```
Copyright MySite.com

```

### taglib 디렉티브 태그의 기능과 사용법
taglib 디렉티브 태그는 현재 JSP 페이지에 표현언어, JSTL, 사용자 정의 태그(custom tag)등 태그 라이브러리를 설정하는 태그입니다.
```
<% taglib prefix=“태그 식별자” uri=“경로” %>
```
- uri 속성은 사용자가 정의한 태그의 설정 정보를 가진 경로 주소
- prefix 속성은 uri에 설정한, 사용자가 정의한 태그를 식별하기 위한 고유 이름입니다. 해당 JSP 페이지 내에서 uri 속성값을 그대로 사용하면 복잡하므로 prefix 속성 값이 대신 식별할 수 있게 해주는 것 
- uri 속성 값은 JSP 컨테이너에 사용자가 정의한 태그 라이브러리의 위치를 알려줍니다.

> JSTL 태그<br>일반적으로 웹 애플리케이션에서 쉽게 접할 수 있는 것은 JSTL 태그 라이브러리입니다. 유용한 JSP 태그의 모음인 JSTL은 자주 사용되는 핵심 기능을 제공합니다. 반복문, 조건문과 같은 논리적 구조 작업. XML 문서 조작, 국제화 태그 조작, SQL 조작 수행을 위한 태그 등을 지원합니다.<br><br>STL을 사용하려면 WebContent/WEB-INF/lib/ 태그 디렉터리의 위치에 jstl.jar 라이브러리 파일이 있어야 합니다. 이 파일은 Apache Standard Taglib 페이지에서 다운로드할 수 있습니다.

#### day02/taglib.jsp
```
<%@ taglib prefix=“c”uri=“http://java.sun.com/jsp/jstl/core” %>
<html>
<body>
	<c:forEach var=“k” begin=“1” end=“10” step=“1”>
		<c:out value=“${k}” />
	</c:forEach>
</body>
</html>
```

## JSP의 주석 처리
```
<%-- JSP 주석 처리 내용 --%>
```
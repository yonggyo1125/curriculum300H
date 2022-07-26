# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1vrtz1bk9WJmssVLZBEaTZ8txvNm2svem?usp=sharing)

## 액션태그
- 액션 태그는 서버나 클라이언트에게 어떤 행동을 하도록 명령하는 태그로 스크립트 태그, 주석, 디렉티브 태그와 함게 JSP 페이지를 구성하는 태그입니다.
- 액션 태그는 JSP 페이지에서 페이지와 페이지 사이를 제어하거나(forward), 다른 페이지의 실행 결과 내용을 현재 페이지에 포함하거나(include), 자바빈즈(javaBeans)등의 다양한 기능을 제공
- XML 형식 <jsp:... />를 사용합니다. 액션 태그는 반드시 끝나는 태그 />로 마무리해야 합니다.

### 액션태그의 종류
|액션태그|형식|설명|
|----|-------|---------|
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

- param 액션 태그는 현재 JSP 페이지에서 다른 페이지에 정보를 전달하는 태그
- 이 태그는 단독으로 사용되지 못하며 <jsp:forward>나 <jsp:include> 태그의 내부에 사용
- 다른 페이지에 여러 개의 정보를 전송해야 할 때는 다중의 param 액션 태그를 사용할 수 있다.
- 전달한 정보는 request내장 객체의 getParameter() 메서드로 접근할 수 있습니다.

```
<jsp:forward page=“파일명”>
    <jsp:param name=“매개변수명1” value=“매개변수값1” />
    [<jsp:param name=“매개변수명2” value=“매개변수값2” />]
 </jsp:forward>
```

```
예제)
[jsp:param 액션 태그 사용 예]
first.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
    <h3>이 파일은 first.jsp입니다.</h3>
    <jsp:include page=“second.jsp”>
       <jsp:param name=“date” value=“<%=java.util.Calendar.getInstance().getTime()%>” />
    </jsp:include>
</body>
</html>

second.jsp
<%@ page contentType=“text/html; charset=utf-8” %>
Today is : <%=request.getParameter(“date”);
```

#### day03/param01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h3>param 액션 태그</h3>
	<jsp:forward page="param01_data.jsp">
		<jsp:param name="id" value="admin" />
		<jsp:param name="name" value='<%=java.net.URLEncoder.encode("관리자")%>' />
	</jsp:forward>
	<p>Java Server Page
</body>
</html>
```

#### day03/param01_data.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<p>	아이디 : <%=request.getParameter("id")%>
	<%
		String name = request.getParameter("name");
	%>
	<p>	이 름 : <%=java.net.URLDecoder.decode(name)%>
</body>
</html>
```

#### day03/param02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h3>param 액션 태그</h3>
	<jsp:include page="param02_data.jsp">
		<jsp:param name="title" value='<%=java.net.URLEncoder.encode("오늘의 날짜와 시각")%>' />
		<jsp:param name="date" 	value="<%=java.util.Calendar.getInstance().getTime()%>" />
	</jsp:include>
</body>
</html>
```

#### day03/param02_data.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<%
		String title = request.getParameter("title");
	%>
	<h3><%=java.net.URLDecoder.decode(title)%></h3>
	Today is :<%=request.getParameter("date")%>
</body>
</html>
```

### 자바빈즈 액션 태그의 기능과 사용법
- 자바빈즈는 동적 콘텐츠 개발을 위한 자바 코드를 사용하여 자바 클래스로 로직을 작성하는 방법입니다.
- JSP 페이지에서 화면을 표현하기 위한 계산식이나 자료의 처리를 담당하는 자바코드를 따로 분리하여 작성하는 것
- 자바빈즈는 데이터 표현을 목적으로 하는 자바 클래스이므로 기존의 자바 클래스를 작성하는 방법과 동일하게 작성합니다.
- 자바빈즈는 데이터를 담은 멤버 변수인 프로퍼티(property)와 데이터를 가져오거나 저장하는 메소드로 구성됩니다.

#### 자바빈즈 작성 규칙
- 자바 클래스는 java.io.Serializable 인터페이스를 구현해야 합니다.
- 인수가 없는 기본 생성자가 있어야 합니다.
- 모든 멤버 변수인 프로퍼티디는 private 접근 지정자로 설정해야 합니다.
- 모든 멤버 변수인 프로퍼티는 Getter/Setter() 메서드가 존재해야 합니다. 
- JSP 페이지에서 useBean, setProperty, getProperty등 자바빈즈 액션 태그와 스크립트 태그에 자바 코드와 같이 사용할 수 있습니다.
- 폼 페이지의 입력 데이터나 HTML 페이지에서 넘어오는 데이터를 쉽게 자바빈즈 객체로 저장할 수 있습니다.

> java.io.Serializable 인터페이스는 생략 가능하나 자바빈즈 규약에 명시된 내용으로, 자바빈즈에 저장된 프로퍼티를 포함한 채로 파일 시스템에 저장되거나 네트워크로 전송될 수 있도록 객체 직렬화를 제공해야 하므로 implement 해야 합니다.

```
작성 예)
package com.dto;
import java.io.Serializable;

public class MemberBean implements java.io.Serializable {
	private int id;
	private String name;
	
	public MemberBean() {}

	public int getId() {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
```

### useBean 액션 태그로 자바빈즈 사용하기
```
<jsp:useBean id=“자바빈즈 식별이름” class=“패키지명을 포함한 자바빈즈 이름” scope=“범위” />
```

### useBean 액션 태그의 속성

|속성|설명|
|----|-------|
|id|자바빈즈를 식별하기 위한 이름입니다.|
|class|패키지 이름을 포함한 자바빈즈 이름입니다. 자바빈즈는 인수가 없는 기본 생성자가 있어야 하며 추상클래스를 사용할 수 없습니다.|
|scope|자바빈즈가 저장되는 영역을 설정합니다. page(기본값), request, session, application 중 하나의 값을 사용합니다.|

```
<jsp:useBean id=“member” class=“com.dto.MemberBean” scope=“page” />
```

위의 예는 다음 자바 코드와 동일
```
MemberBean member = (MemberBean) request.getAttribute(“member”);
if (member == null) {
   member = new MemberBean();
   request.setAttribute(“member”, member);
}
```

#### day03/useBean01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<p><%
			out.print("오늘의 날짜 및 시각");
		%>	
	<p><%=date%>
</body>
</html>
```

#### day03/src/main/java/day03/com/dao/Calculator.java
```
package day03.com.dao;

public class Calculator {
	public int process(int n) {
		return n * n * n;
	}
}
```

#### day03/useBean02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="bean" class="day03.com.dao.Calculator" />
	<%
		int m = bean.process(5);
		out.print("5의 3제곱 :  " + m);
	%>
</body>
</html>
```

#### day03/src/main/java/day03/com/dao/Person.java
```
package day03.com.dao;

public class Person {
	private int id = 20181004;
	private String name = "홍길순";

	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

#### day03/useBean03.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<p>	아이디 : <%=person.getId()%>
	<p>	이 름 : <%=person.getName()%>
</body>
</html>
```

#### day03/useBean04.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<p>	아이디 : <%=person.getId()%>
	<p>	이 름 : <%=person.getName()%>
		<%
			person.setId(20182005);
			person.setName("홍길동");
		%>
		<jsp:include page="useBean03.jsp"/>
</body>
</html>

```

### setProperty 액션 태그로 프로퍼티 값 저장하기
- setProperty 액션 태그는 useBean 액션 태그와 함께 자바빈즈의 Setter() 메서드에 접근하여 자바빈즈 멤버변수인 프로퍼티 값을 저장하는 태그
- setProperty 태그는 폼 페이지로부터 전달되는 요청 파라미터의 값을 직접 저장하거나 자바빈즈의 프로퍼티로 변경하여 값을 저장할 수 있습니다.
- 모든 자바빈즈 프로퍼티 이름과 동일하게 요청 파라미터를 설정할 수 있습니다.

#### setProperty 액션 태그의 속성 

|속성|설명|
|----|-------|
|name|useBean 태그에 id 속성 값으로 설정된 자바빈즈를 식별하기 위한 이름입니다.|
|property|자바빈즈의 프로퍼티 이름입니다. 만약 프로퍼티 이름에 ‘\*’를 사용하면 모든 요청 파라미터가 자바빈즈 프로퍼티의 Setter() 메서드에 전달됨을 의미합니다.|
|value|변경할 자바빈즈의 프로퍼티 값입니다. 만약 프로퍼티 값이 null이거나 존재하지 않는 요청 파라미터인 경우에는 setProperty 액션 태그가 무시됩니다.|
|param|자바빈즈의 프로퍼티 값을 전달하는 요청 파라미터의 이름입니다. param과 value를 동시에 모두 사용할 수 없으며 하나를 선택하여 사용하는 것만 가능합니다.|

```
<jsp:setProperty name=“자바빈즈 식별 이름” property=“프로퍼티 이름” value=“값” />
```

#### setProperty 액션 태그 사용 예 
```
<jsp:setProperty name=“member” property=“id” value=“admin” />
```

#### 자바빈즈 프로퍼티 값 출력 예 
```
<% out.println(“아이디 :＂＋member.getId()); %>
또는 
<jsp:getProperty name=“member” property=“id” />
```

**요청 파라미터 이름과 자바빈즈의 프로퍼티 이름이 일치하는 경우** : 다음은 폼 페이지에서 요청 파라미터의 이름이 자바빈즈의 프로퍼티 이름과 동일하여 id로 값이 전달되는 예

```
// 폼페이지
<form method=“post” action=“memberProcess.jsp”>
    <input name=“id” value=“admin” />
</form>

// jsp 페이지
<jsp:setProperty name=“member” property=“id” />
```

**요청 파라미터 이름과 자바빈즈 프로퍼티 이름이 일치하지 않는 경우** : 다음은 폼 페이지에서 요청 파라미터 이름이 자바빈즈의 프로퍼티 이름과 동일하지 않아 id가 아닌 userId로 값이 전달되는 예
```
// 폼페이지
<form method=“post” action=“memberProcess.jsp”>
    <input name=“userId” value=“admin” />
</form>

// jsp 페이지
<jsp:setProperty name=“member” property=“id” param=“userId” />
```


**요청 파라미터 이름과 자바빈즈의 프로퍼티 이름이 모두 일치하는 경우** : 다음은 폼 페이지에서 모든 요청 파라미터 이름이 자바빈즈의 모든 프로퍼티 이름과 동일하게 값이 전달되는 예
```
// 폼페이지
<form method=“post” action=“memberProcess.jsp”>
    <input name=“id” value=“admin” />
    <input name=“name” value=“관리자” />
</form>

// jsp 페이지
<jsp:setProperty name=“member” property=“*” />
```

#### day03/src/main/java/day03/com/dao/Person.java
```
package day03.com.dao;

public class Person {
	private int id = 20181004;
	private String name = "홍길순";

	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

#### day03/setProperty.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<jsp:setProperty name="person" property="id" value="20182005" />
	<jsp:setProperty name="person" property="name" value="홍길동" />
	<p>	아이디 : <% out.println(person.getId()); %>
	<p>	이 름 : <% out.println(person.getName()); %>
</body>
</html>
```

### getProperty 액션 태그로 프로퍼티의 값 가져오기
getProperty 액션 태그는 useBean 액션 태그와 함께 자바빈즈의 Getter() 메서드로 접근하여 자바빈즈의 멤버 변수인 프로퍼티 값을 가져오는 태그

#### getProperty 액션 태그의 속성 
|속성|설명|
|----|-------|
|name|useBean 태그에 id 속성 값으로 설정된 자바빈즈를 식별하기 위한 이름입니다.|
|property|자바빈즈의 프로퍼티 이름입니다. 만약 프로퍼티 이름에 ‘\*’를 사용하면 모든 요청 파라미터가 자바빈즈 프로퍼티의 Getter() 메서드에 전달됨을 의미합니다.|

#### getProperty 액션 태그 사용 예
```
<jsp:getProperty name=“자바빈즈 식별이름” property=“프로퍼티 이름” />
```

#### 자바빈즈 프로퍼티 값 출력 예
```
<% out.println(member.getName()); %>
```

#### day03/src/main/java/day03/com/dao/Person.java
```
package day03.com.dao;

public class Person {
	private int id = 20181004;
	private String name = "홍길순";

	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

#### day03/getProperty01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<p>	아이디 : <jsp:getProperty name="person" property="id" />
	<p>	이 름 : <jsp:getProperty name="person" property="name" />
</body>
</html>
```

#### day03/getProperty02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person"></jsp:useBean>
	<jsp:setProperty name="person" property="id" value="20182005" />
	<jsp:setProperty name="person" property="name" value="홍길동" />
	<p>	아이디 : <jsp:getProperty property="id" name="person" />
	<p>	이 름 : <jsp:getProperty property="name" name="person" />
</body>
</html>

```
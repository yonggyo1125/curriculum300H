# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/12ek-tGtc6IEKPTwUYoKMps8qGJlJwwoR?usp=sharing)

# 익스프레션 언어(EL 표현식)
- 익스프레션(expression)이라는 단어를 사전에서 찾아보면 여러가지 뜻이 있지만, 프로그래밍에서는 대개 '식(式)'이라는 뜻으로 사용됩니다.
- 식이란 수학시간에 배우는 3+4, 2x+7처럼 어떤 값을 산출하는 연산자와 피연산자의 조합을 말합니다. 익스프레션 언어는 이 같은 식을 중심으로 코드를 기술하는 언어입니다.
- 하지만 수학에서 사용하는 식과는 달리 연산자와 피연산자의 조합을 다음과 같이 ${ .. } 로 둘러싸서 표현합니다.

```
${cnt+1} 
```
- 위와 같은 형태의 식을 **EL 식**이라고 합니다.
- EL 식에 포함된 데이터 이름은 기본적으로 **애트리뷰트**의 이름으로 해석됩니다. 
- 애트리뷰트란 **setAttribute, getAttribute, removeAttribute** 메서드를 통해 저장되고 관리되는 데이터를 의미합니다.

#### 애트리뷰트 형태로 전달되는 데이터
```
<% 
	int sum = 0;
	for (int cnt = 1; cnt <= 100; cnt++) 
		sum += cnt;
	request.setAttribute("RESULT", Integer.valueOf(sum));
	RequestDispatcher dispatcher = request.getRequestDispatcher("HundredResult.jsp");
	dispatcher.forward(request, response);
%>
```

#### 애트리뷰트 값을 출력하는 EL식 
```
<%@page contentType="text/html; charset=utf-8"%>
<html>
	<body>
		1부터 100까지 더한 결과는? ${RESULT}
	</body>
</html>
```

- 익스프레션 언어의 단점으로는 익스프레션 언어로는 복잡한 데이터 로직을 구사할 수 없습니다. 그러므로 이 언어는 데이터 처리 결과를 출력하는 JSP 페이지에 주로 사용됩니다.
- 그렇다고 해서 익스프레션 언어가 애트리뷰트 값만 가져다가 출력하는 일만 할 수 있다는 것은 아닙니다. 이 언어를 이용하면 몇 개의 데이터를 가지고 간단한 연산을 수행해서 그 결과를 출력하는 일도 할 수 있습니다.

```
${RESULT + 101}
```

- 익스프레션 언어에는 이 밖에도 산술 연산자인 -, \*, /, %, 관계(비교)연산자인 \<,\>, \>=,\<=, !=, 논리연산자인 &&, || 등을 사용할 수 있습니다.

## 익스프레션 언어의 기초문법
- EL식의 문법
```
${식}
```
- 위의 '식'의 위치에는 데이터 이름 하나로만 구성된 식이 들어갈 수도 있고, 
- 연산자를 포함하는 식이 들어갈 수도 있습니다. 

```
// 데이터 이름 하나로만 구성된 EL식 
 
${RESULT}
```
```
// 연산자를 포함하는 EL 식

${RESULT + 101}
```

### 데이터 이름 하나로만 구성된 EL 식
- 데이터 이름 하나로 구성된 EL식은 가장 간단한 형태의 EL 식입니다. 이런 EL식 안에 기술되는 데이터 이름은 앞 절에서 언급했던 것 처럼 **애트리뷰트 이름**으로 해석됩니다.
```
${RESULT}   // 애트리뷰트 이름 
```

- 서블릿과 JSP 기술에서 사용할 수 있는 setAttribute, getAttribute, removeAttribute 메서드는 4세트가 있고 그래서 애트리뷰트 종류도 네가지 입니다.
- JSP 페이지에서는 이런 메서드를 호출할 떄 각각 pageContext, request, session, application 내장 객체를 사용합니다.

#### JSP/서블릿 기술에서 사용되는 네 종류의 애트리뷰트

|애트리뷰트의 종류|호출할 때 사용하는 내장 변수|메서드의 소속|
|-----|-----|-------|
|page 애트리뷰트|pageContext 내장 객체|javax.servlet.jsp.JspContext 클래스|
|request 애트리뷰트|request 내장 객체|javax.servlet.ServletRequest 인터페이스|
|session 애트리뷰트|session 내장 객체|javax.servlet.http.HttpSession 인터페이스|
|application 애트리뷰트|application 내장 객체|javax.servlet.ServletContextg 인터페이스|

- 여기에서 주의해야 할 점은 EL 식 안에 있는 데이터 이름이 이 네 종류의 애트리뷰트 중 어느것으로도 해석될 수 있다는 것 입니다. 
- 예를 들어 ${RESULT}라는 EL 식은 RESULT라는 이름을 갖는 request 애트리뷰트 값을 출력하라는 의미가 될 수도 있지만 RESULT라는 이름을 갖는 page 애트리뷰트 값을 출력하라는 의미도 될 수 있습니다.

1부터 100까지의 합을 구하는 JSP 페이지
```
<%
	int sum = 0;
	for(int cnt = 1; cnt <= 100; cnt++)
		sum += cnt;
		
	request.setAttribute("RESULT", Integer.valueOf(sum));
	//pageContext.setAttribute("RESULT", Integer.valueOf(sum));
	RequestDispatcher dispatcher = request.getRequestDispatcher("HundredResult.jsp");
	dispatcher.forward(request, response);
%>
```
```
<%@page contentType="text/html;  charset=utf-8"%>
<html>
	<body>
		1부터 100까지 더한 결과는? ${RESULT}
	</body>
</html>
```
- 위 예제를 통해 알아본것 처럼 데이터 이름 하나로만 구성된 EL식을 이용하면 여러 종류의 애트리뷰트 값을 출력할 수 있습니다.
- 이런 형태는 가장 간단한 EL 식이기 때문에 여러가지 용도로 사용되면 그만큼 많은 코드를 간결하게 만들 수 있어서 개발 효율에 큰 도움이 됩니다.
- 하지만 이런 간결함이 혼란을 일으킬 수 있는데, JSP 페이지 안에 이름이 같고 종류가 다른 둘 이상의 애트리뷰트가 있다면 혼동이 발생할 것 입니다.
- 이런 문제를 해결하기 위해 익스프레션 언어에서는 EL 식에 있는 데이터 이름을 해석하는 순서를 다음과 같이 정해 놓았습니다. 이 순서는 사용 범위가 좁은 애트리뷰트부터 점점 더 사용 범위가 넓은 애트리뷰트 순으로 진행됩니다.

#### EL 식 안에 있는 데이터 이름이 해석되는 순서
page 애트리뷰트 -> request 애트리뷰트 -> session 애트리뷰트 -> application 애트리뷰트

- 그런데 어떤 경우에는 이 순서에 상관없이 특정한 종류의 애트리뷰트를 꼭 집어서 출력하고 싶을 때도 있을 것입니다.
- 그럴 때는 데이터 이름 앞에 그 데이터 영역에 해당하는 pageScope, requestScope, sessionScope, applicationScope라는 단어를 다음과 같이 표시하시면 됩니다.
	- ${pageScope.SUM} : page 애트리뷰트임을 표시
	- ${requestScope.RESULT} : request 애트리뷰트임을 표시
	- ${sessionScope.CART} : session 애트리뷰트임을 표시
	- ${applicationScope.DB_NAME} : application 애트리뷰트임을 표시 
	
- 위 EL 식에서 사용된 pageScope, requestScope, sessionScope, applicationScope라는 이름은 익스프레션 언어의 내장 객체 이름입니다.


### 익스프레션 언어의 내장 객체
- JSP 페이지의 스크립팅 요소 안에서 사용할 수 있는 내장 객체가 있고 이와 비슷하게 EL 식 안에서 사용할 수 있는 내장 객체도 있습니다. 하지만 이 둘은 엄연히 별개의 것 입니다.
- EL 식 안에서 아용되는 내장 객체는 익스프레션 언어에 속하는 것이고, 스트립팅 요소 안에서는 사용할 수 없습니다.

#### 익스프레션 언어의 내장 객체

|내장 객체 이름|표현하는 데이터|객체의 타입|
|-----|---------|----|
|pageScope|page 애트리뷰트의 집합|Map|
|requestScope|request 애트리뷰트의 집합|Map|
|sessionScope|session 애트리뷰트의 집합|Map|
|applicationScope|application 애트리뷰트의 집합|Map|
|param|웹 브라우저로부터 입력된 데이터의 집합|Map|
|paramValues|웹 브라우저로부터 입력된 데이터의 집합<br>(똑같은 이름의 데이터가 여럿일 때 사용)|Map|
|header|HTTP 요청 메세지에 있는 HTTP 헤더의 집합|Map|
|headerValues|HTTP 요청 메시지에 있는 HTTP 헤더의 집합<br>(똑같은 이름의 HTTP 헤더가 여럿일 때 사용)|Map|
|cookie|웹 브라우저로부터 전송된 쿠키의 집합|Map|
|initParam|웹 애플리케이션의 초기화 파라미터의 집합|Map|
|pageContext|JSP 페이지의 환경 정보의 집합|PageContext|

#### param 내장객체
- param은 웹브라우저에서 \<form\> 요소를 통해 입력된 데이터를 가져올 때 사용하는 내장 객체 입니다. 이 객체의 사용방법은 2가지 입니다.
	- ${param.NUM} : 객체의 이름 뒤에 마침표를 찍고, 그 다음에 해당 데이터 이름을 쓰는 것입니다.
	- ${param\["Color"\]} : 두번째 방법은 이 객체의 이름 뒤에 대괄호를 입력하고, 그 안에 작은 따옴표나 큰 따옴표로 묶은 데이터 이름을 쓰는 것입니다.

- 때로는 \<form\> 요소를 통해 똑같은 이름의 데이터가 여러 개 입력될 경우도 있습니다. 예를 들어 checkbox나 select 요소를 통해 데이트가 입력될 때 주로 그런일 이 생깁니다. 
- 그럴 때는 param 내장 객체 대신 paramValues라는 내장 객체를 사용하시면 됩니다.
	 - ${paramValues.ANIMAL\[0\]} : 객체의 이름 뒤에 마침표를 찍고, 그 다음에 데이터 이름을 쓰고, 그 다음에 대괄호 안에 데이터 값의 인덱스를 표시하는 것 입니다.
	 - ${paramValues\["ANIMAL"\]\[1\]} : 객체의 이름 뒤에 두 개의 대괄호를 입력하고, 그 안에 각각 따옴표로 묶은 데이터의 이름과 인덱스를 쓰는 것 입니다.

#### PetsInput.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<html>
	<body>
		<form action="PetsResult.jsp">
			아이디 : <input type='text' name='ID'><br><br>
			다음 중 회원님이 키우고 있는 애완동물을 선택하십시오.<br><br>
			<input type='checkbox' name='ANIMAL' value='개'>개
			<input type='checkbox' name='ANIMAL' value='고양이'>고양이
			<input type='checkbox' name='ANIMAL' value='금붕어'>금붕어<br><br>
			
			<input type='reset' value='취소'>
			<input type='submit' value='확인'>
		</form>
	</body>
</html>
```

#### PetsResult.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<html>
	<body>
		아이디 : ${param.ID} <br>
		선택한 동물 : ${paramValues.ANIMAL[0]}
			      ${paramValues.ANIMAL[1]}
			      ${paramValues.ANIMAL[2]}
	</body>
</html>
```

#### header 내장객체
- HTTP 요청 메시지에 포함된 HTTP 헤더 값을 가져올 때 사용하는 내장 객체입니다.
	- ${header.Host} : 객체의 이름 뒤에 마침표를 찍고, 그 다음에 해당 헤더의 이름을 쓰는 것
	- ${header\["User-Agent"\]} : 객체의 이름 뒤에 대괄호를 입력하고, 그 안에 작은 따옴표나 큰 따옴표로 묶은 헤더 이름을 쓰는 것

- 그런데 이중 첫 번째 방법의 사용에는 한 가지 제약사항이 있습니다. HTTP 헤더 이름이 자바의 식별자 명명 규칙을 따르지 않을 때는 사용할 수 없다는 제약입니다.
- 예를 들어 헤더 이름에 영문자, 숫자, $나 \_가 아닌 특수 문자가 포함되어 있을 경우에는 이 방법을 사용할 수 없으므로 반드시 두 번때 방법을 사용해야 합니다.
	- ${header.User-Agent} : 잘못된 EL 식(X)
	- ${header\["User-Agent"]} - 올바른 EL 식(O)
	
- HTTP 요청 메세지 안에 똑같은 이름의 HTTP 헤더가 둘 이상 있을 때 header 내장 객체 대신 headerValues 내장 객체를 사용해야 합니다. 
- 이 내장 객체의 이름 뒤에는 마침표나 대괄호를 이용하여 헤더이름을 표시하고, 그 다음에 대괄호로 묶은 인덱스를 표시해야 합니다.
	- ${headerValues.Accept\[0\]}
	- ${headerValues\["User-data"\]\[1\]}
	
#### cookie 내장 객체
- 웹브라우저가 웹 서버로 보낸 쿠키를 가져올 때 사용하는 내장객체 입니다.
	- ${cookie.CART} : 마침표 이용 
	- ${cookie\["USER_NAME"\]} : 대괄호 이용 
	
- 그런데 위의 EL 식이 가져오는 것은 쿠키의 값이 아니라 쿠키 객체입니다. 그러므로 이런 EL 식을 JSP페이지 안에 써 놓으면 사용자에게 아무 의미도 없는 쿠키 객체의 참조 값만 출력될 것입니다.
- 쿠키의 값을 출력하기 위해서는 식 뒤에 마침표를 찍고 value라고 쓰거나 대괄호를 치고 그 안에 "value" 또는 'value'라고 쓰면 됩니다.
	- ${cookie.CART.value} 
	- ${cookie\["CART"\]\["value"\]}
	- ${cookie.CART\["value"\]}
	- ${cookie\["CART"\].value}

- 쿠키 객체 안에는 쿠키 값 외에도 쿠키가 속하는 도메인 이름이나 URL 경로명, 쿠키의 수명 같은 중요한 정보들이 들어있습니다.
- 이런 정보를 출력하기 위해서는 위와 같은 형태의 EL 식에서 value라는 이름을 빼고 그 대신 다음과 같이 domain, path, maxAge라는 이름을 써 넣으면 됩니다.
	- ${cookie.CART.domain} : 쿠키의 도메인 이름을 가져오라는 표시
	- ${cookie.CART\["path"\]} : 쿠키의 URL 경로명을 가져오라는 표시
	- ${cookie\["CART"\]\["maxAge"\]} : 쿠키의 수명을 가져오라는 표시

#### CookieDataWriter.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%>
	Cookie cookie = new Cookie("NAME", "John");
	request.addCookie(cookie);
%>
```

#### CookieDataReader.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		NAME 쿠키 데이터의 값은? ${cookie.NAME.value}
	</body>
</html>
```

#### initParam 내장 객체
- initParam은 웹 애플리케이션의 초기화 파라미터 값을 가져다가 출력할 때 사용하는 내장 객체이다.
	- ${initParam.DB_NAME}
	- ${initParam\["DB_NAME"\]}

#### web.xml 
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
	<context-param>
		<param-name>DB_NAME</param-name>
		<param-value>malldb</param-value>
	</context-param>
	... 
</web-app>
```
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		DB_NAME 초기화 파라미터의 값은? ${initParam.DB_NAME}
	</body>
</html>
```

#### pageContext 내장객체
- pageContext 내장 객체는 JSP 페이지의 주변 환경에 대한 정보를 제공하는 객체입니다. 이 내장 객체의 사용 방법은 다소 독특합니다. 그 이유는 이 내장 객체의 타입이 다른 내장 객체와 다르기 때문입니다.
- pageContext의 내장 객체의 타입은 PageContext이고 이것은 자바의 java.servlet.jsp.PageContext 클래스의 이름인데, 이 객체를 이용하면 이 클래스에 속하는 get으로 시작하는 이름의 메서드를 호출할 수 있습니다.
- 그러므로 pageContext 내장객체를 사용할땐 먼저 PageContext 클래스의 API 규격서를 찾아서 어떤 get메서드가 있는지 살펴보세요.

![pageContext](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9D%B5%EC%8A%A4%ED%94%84%EB%A0%88%EC%85%98%20%EC%96%B8%EC%96%B4(EL%20%ED%91%9C%ED%98%84%EC%8B%9D)/images/pageContext.png)

- 위 그림에서 볼 수 있는 것처럼 이 클래스는 8개의 get메서드가 있습니다. 
- EL 식을 이용해서 이 메서드들을 호출하려면 메서드 이름 제일 앞에 있는 get이라는 단어를 떼고, 그 다음에 있는 첫 문자를 소문자로 고친 이름을 사용하면 됩니다.
- 예를 들어 getRequest라는 메서드을 호출하기 위해서는 request라는 이름을 이용해서 다음과 같은 EL 식을 만들면 됩니다.
	- ${pageContext.request}
	- ${pageContext\["request"\]}
	
- 사용 예
	- ${pageContext.request.requestURI} : getRequestURI 메서드의 리턴값 가져옴
	- ${pageContext\["request"\]\["requestURI"\]} : getRequestURI 메서드의 리턴값 가져옴
	- ${pageContext.request\["requestURI"\]} : getRequestURI 메서드의 리턴값 가져옴
	- ${pageContext\["request"\].requestURI} : getRequestURI 메서드의 리턴값 가져옴

```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		요청 URL: ${pageContext.request.requestURI}
	</body>
</html>
```

## 익스프레션 언어의 연산자
익스프레션 언어를 이용하면 간단한 연산을 해서 그 결과를 출력할 수도 있습니다.

#### 익스프레션 언어의 연산자

|구분|연산자|설명|
|----|--------|-----|
|산술 연산자|\+ - \* / % div mod|덧셈, 뺄셈, 곱셈, 나눗셈, 나머지|
|비교 연산자|\< \> \<= \>= == != lt gt le ge eq ne|크기와 동등 여부 비교|
|논리 연산자|&& \|\| ! and or not|논리적인 AND와 OR|
|조건 연산자|? :|조건에 따라 두 값 중 하나를 선택|
|엠프티 연산자|empty|데이터의 존재 유무 여부|
|대괄호와 마침표 연산자|\[\] .|집합 데이터에 있는 한 항목을 선택|
|괄호|()|연산자의 우선순위 지정|


#### Operators.jsp
```
URL - Operators.jsp?NUM1=20&NUM2=4

<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		X = ${param.NUM1}, Y = ${param.NUM2} <br><br>
		X + Y = ${param.NUM1} + param.NUM2} <br>
		X - Y = ${param.NUM1 - param.NUM2} <br>
		X * Y = ${param.NUM1 * param.NUM2} <br>
		X / Y = ${param.NUM1 / param.NUM2} <br>
		X % Y = ${param.NUM1 % param.NUM2} <br><br>
		X가 더 큽니까? ${param.NUM1 - param.NUM2 > 0} <br>
		Y가 더 큽니까? ${param.NUM1 - param.NUM2 < 0} <br><br>
		X와 Y가 모두 양수입니까? ${ (param.NUM1 > 0) && (param.NUM2 > 0) } <br><br>
		X와 Y가 같습니까? ${param.NUM1 == param.NUM2 ? "예" : "아니오"} <br><br>
	</body>
</html>
```

```
<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		${param.NUM1}을 ${param.NUM2}로 나눈 몫은? 
				${param.NUM1 div param.NUM2} <br>
		나머지는? ${param.NUM1 mod param.NUM2} <br><br>
		둘 다 양수입니까? ${ (param.NUM1 gt 0) AND (param.NUM2 gt 0) } <br>
		둘 다 음수입니까? ${ (param.NUM1 lt 0) AND (param.NUM2 lt 0) } <br> 
	</body>
</html>
```

#### StringOperators.jsp
```
URL - StringOperators.jsp?STR1=DOG&STR2=CAT

<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		입력 문자열 : ${param.STR1}, ${param.STR2} <br><br>
		두 문자열이 같습니까? ${param.STR1 == param.STR2} <br>
	</body>
</html>
```

### 엠프티 연산자
- ${empty NAME} : 값이 비어 있는지를 체크

#### EmptyOperators.jsp
```
URL1 - EmptyOperators.jsp?ID=rose
URL2 - EmptyOperators.jsp

<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		안녕하세요, ${empty param.ID ? "guest" : param.ID}님
	</body>
</html>
```
### 대괄호 연산자와 마침표 연산자
- 자바에서는 배열 항목과 객체 멤버를 가리키기 위해 사용되지만, 익스프레션 언어에서는 그와 비슷하면서도 다른 용도로 사용됩니다.
- 이 두 연산자는 다음과 같은 데이터 항목을 가리키기 위해 사용됩니다.
	- 배열 항목
	- java.util.List 객체의 데이터 항목
	- java.util.Map 객체의 데이터 항목
	- 자바빈(javaBean) 프로퍼티
	
- 배열과 java.util.List 객체의 데이터 항목은 반드시 대괄호 연산자를 이용해서 가리켜야 합니다.
- java.util.Map 객체의 데이터 항목과 자바빈 프로퍼티는 대괄호 연산자의 마침표 연산자 중 어느 것을 사용해서도 가리킬 수 있습니다.

```
${ARR[0]}
```

- 배열 예제

#### Winners.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%
	String winners[] = new String[3];
	winners[0] = "이수현";
	winners[1] = "정세훈";
	winners[2] = "김진희";
	request.setAttribute("WINNERS", winners);
	RequestDispatcher dispatcher = request.getRequestDispatcher("WinnersView.jsp");
	dispatcher.forward(request, response);
%>
```

#### WinnersView.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		<h3>우승자 명단</h3>
		1등. ${WINNERS[0]} <br>
		2등. ${WINNERS[1]} <br>
		3등. ${WINNERS[2]} <br>
	</body>
</html>
```

- java.util.List 예제

#### Fruits.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%
	ArrayList<String> items = new ArrayList<String>();
	items.add("딸기");
	items.add("오렌지");
	items.add("복숭아");
	request.setAttributes("FRUITS", items);
	RequestDispatcher dispatcher = request.getRequestDispatcher("FruitsView.jsp");
	dispatcher.forward(request, response);
%>
```

#### FruitsView.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		1위. ${FRUITS[0]} <br>
		2위. ${FRUITS[1]} <br>
		3위. ${FRUITS[2]} <br>
	</body>
</html>
```

- java.util.Map 예제

#### Address.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("Edgar", "보스턴");
	map.put("Thomas", "오하이오");
	map.put("John", "워싱턴");
	request.setAttribute("ADDRESS", map);
%>
```

#### AddressView.jsp
```
URL - AddressView.jsp?NAME=Thomas

<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		${param.NAME}의 주소는? ${ADDRESS[param.NAME]}
	</body>
</html>
```

- 자바빈(JavaBean) 예제

#### mall.ProductInfo.java
```
package mall;
public class ProductInfo {
	private String name;
	private int value;
	
	public ProductInfo() {}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(int price) {
		value = price;
	}
	
	public int getPrice() {
		return value;
	}
}
```

#### ProductInfo.jsp 
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="mall.ProductInfo" %>
<%
	ProductInfo product = new ProductInfo();
	product.setName("초코케이크 3호");
	product,setPrice(20000);
	request.setAttribute("PRODUCT", product);
	RequestDispatcher dispatcher = request.getRequestDispatcher("ProductInfoView.jsp");
	dispatcher.forward(request, response);
%>
```

#### ProductInfoView.jsp 
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		상품명 : ${PRODUCT.name} <br>
		가격 : ${PRODUCT.price}원 <br>
	</body>
</html>
```
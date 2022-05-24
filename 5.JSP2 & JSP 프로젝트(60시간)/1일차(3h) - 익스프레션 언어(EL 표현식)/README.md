# 익스프레션 언어(EL 표현식)
- 익스프레션(expression)이라는 단어를 사전에서 찾아보면 여러가지 뜻이 있지만, 프로그래밍에서는 대개 '식(式)'이라는 뜻으로 사용됩니다.
- 식이란 수핛히간에 배우는 3+4, 2x+7처럼 어떤 값을 산출하는 연산자와 피연산자의 조합을 말합니다. 익스프레션 언어는 이 같은 식을 중심으로 코드를 기술하는 언어입니다.
- 하지만 수학에서 사용하는 식과는 잘리 연산자와 피연산자의 조합을 다음과 같이 ${ .. } 로 둘러싸서 표현합니다.

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
- 연산자를 포함하는 식이 들어갈 수도 있으며, 
- 자바의 정적 메서드를 호출하는 식이 들어갈 수도 있습니다. 

```
// 데이터 이름 하나로만 구성된 EL식 
 
${RESULT}
```
```
// 연산자를 포함하는 EL 식

${RESULT + 101}
```

```
// 자바의 정적 메서드를 호출하는 EL 식

${m:sqrt(100)}
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

- param은 웹브라우저에서 \<form\> 요소를 통해 입력된 데이터를 가져올 때 사용하는 내장 객체 입니다. 이 객체의 사용방법은 2가지 입니다.
	- ${param.NUM} : 객체의 이름 뒤에 마침표를 찍고, 그 다음에 해당 데이터 이름을 쓰는 것입니다.
	- ${param\["Color"\]} : 두번째 방법은 이 객체의 이름 뒤에 대괄호를 입력하고, 그 안에 작은 따옴표나 큰 따옴표로 묶은 데이터 이름을 쓰는 것입니다.

- 때로는 \<form\> 요소를 통해 똑같은 이름의 데이터가 여러 개 입력될 경우도 있습니다. 예를 들어 checkbox나 select 요소를 통해 데이트가 입력될 때 주로 그런일 이 생깁니다. 
- 그럴 때는 param 내장 객체 대신 paramValues라는 내장 객체를 사용하시면 됩니다.
	 - ${paramValues.ANIMAL\[0\]} : 객체의 이름 뒤에 마침표를 찍고, 그 다음에 데이터 이름을 쓰고, 그 다음에 대괄호 안에 데이터 값의 인덱스를 표시하는 것 입니다.
	 - ${paramValues\["ANIMAL"\]\[1\]} : 객체의 이름 뒤에 두 개의 대괄호를 입력하고, 그 안에 각각 따옴표로 묶은 데이터의 이름과 인덱스를 쓰는 것 입니다.

```
```


## 익스프레션 언어의 연산자

##익스프레션 언어로 자바 정적 메서드 호출하기
# JSTL

## 설치하기

![JSTL1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSTL/images/JSTL1.png)

- [JSTL1.2 다운로드](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2)
- 다운로드 받은 jstl1.2.jar 파일을 src/webapp/WEB-INF/lib에 복사해 준다.

## 코어(core) 라이브러리
- JSTL의 코어 라이브러리는 글자 그대로 가장 핵심적인 기능을 제공하는 라이브러리 입니다.
- 이 라이브러리에서 제공하는 커스텀 액션을 이용하면 일반 프로그래밍 언어에서 제공하는 변수 선언, 조건 분기, 반복 수행 등의 로직을 구사할 수 있고, 다른 JSP 페이지를 호출할 수도 있습니다.

### <c:set> 커스텀 액션
- \<c:set\>은 변수를 선언하고 나서 그 변수에 초기값을 대입하는 기능의 커스텀 액션입니다.
- \<c:set var="num" value="100" /\>
	- num : 변수의 이름
	- 100 : 초기값

- 이렇게 선언한 변수는 익스프레션 언어의 EL 식 안에서 사용할 수 있습니다. 하지만 JSP 스트립팅 요소 안에서 사용할 수는 없습니다.
- \<c:set\> 커스텀 액션을 이용해서 선언한 변수는 자바 변수가 되는 것이 아니라 page 데이터 영역의 애트리뷰트가 되기 때문입니다.

```
<c:set var="num" value="100" />
...
${num}
```
>page 데이터 영역이란 setAttribute, getAttribute 메서드를 이용해서 데이터를 저장할 수 있는 데이터 영역(page, request, session, application) 중 가장 작은 범위의 영역을 말합니다.
- \<c:set\> 커스텀 액션의 value 애트리뷰트 값에는 EL 식을 쓸 수도 있습니다. 이런 특성을 활용하면 \<c:set\>액션을 이용해서 선언한 변수의 값을 또 다른 변수의 초기값으로 사용할 수도 있습니다.

```
<c:set var="sum" value="${num1+num2}" />  // value 애트리뷰트 값으로 EL 식을 쓸 수도 있습니다.
```

#### <c:set> 액션과 스크립팅 요소
\<c:set\> 액션을 이용해서 선언한 변수를 스크립팅 요소 안에서 사용하는 것은 불가능하지만, 스크립팅 요소 안에서 선언한 변수를 \<c:set\> 액션의 value 애트리뷰트에 사용하는 것은 가능합니다. 그러므로 다음과 같은 코드 작성도 가능합니다.

```
<% int num1 = 10, num2 = 20; %>

<c:set var="sum" value="<%=num1+num2%>" />
 ```
- 하지만 커스텀 액션과 스크립팅 요소를 섞어서 사용하는 것은 그다지 바람직한 일이 아니고, 위 코드에서 볼 수 있는 것처럼 꺽쇠 괄호가 겹쳐서 사용되면 코드의 가독성이 떨어지므로 꼭 필요한 경우가 아니라면 이런 방법은 사용하지 않는 것이 좋습니다.

#### Multiply.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="num1" value="7" />
<c:set var="num2" value="9" />
<c:set var="result" value="${num1*num2}" />
<html>
	<body>
		${num1}과 ${num2}의 곱은? ${result}
	</body>
</html>
```

- \<c:set\>액션을 이용하면 page 데이터 영역뿐만 아니라 request, session, application 데이터 영역에 애트리뷰트를 저장하는 것도 가능합니다.
- 그렇게 하기 위해서는 \<c:set\> 태그에 scope라는 애트리뷰트를 추가하고, page, request, session, application 중 하나를 애트리뷰트 값으로 지정하면 됩니다.

```
<c:set var="PRICE" value="15000" scope="request" />
```
- scope="request" : 변수가 저장될 데이터 영역 
- scope 애트리뷰트에 request라는 값을 지정하고 나서 forward 메서드를 통해 다른 JSP 페이지를 호출하면 그 JSP 페이지 안에서도 선언된 변수를 사용할 수 있습니다.

#### ProductInfo.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CODE" value="80012" scope="request" />
<c:set var="NAME" value="온습도계" scope="request" />
<c:set var="PRICE" value="15000" scope="request" />
<jsp:forward page="ProductInfoView.jsp" />
```

#### ProductInfoView.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		상품코드: ${CODE} <br>
		상품명 : ${NAME} <br>
		단가 : ${PRICE}원 <br>
	</body>
</html>	
```

### <c:remove> 커스텀 액션 사용 방법
- \<c:set\> 액션을 이용해서 선언한 변수는 자바 변수와 달리 page, request, session, application 데이터 영역에 애트리뷰트로 저장되기 때문에 때로는 인위적으로 삭제해야 할 필요가 있습니다.
- 그럴 경우에는 \<c:remove\> 커스텀 액션을 사용하면 됩니다.
	
```
<c:remove var="num" />
```

- 그러나 위 코드는 page, request, session, application 데이터 영역에 저장되어 있는 num이라는 이름의 애트리뷰트를 모두 찾아서 제거합니다. 
- 만약 특정 데이터 영역에 있는 변수를 제거하기 위해서는 \<c:remove\> 커스텀 액션에 scope 애트리뷰트를 추가하고, 그 값으로 page, request, session, application 중 하나를 지정하면 됩니다.

```
<c:remove var="code" scope="request" />
```
- 위 코드는 request 데이터 영역에 저장되어 있는 code라는 이름의 애트리뷰트를 찾아서 삭제할 것 입니다.

### <c:if> 커스텀 액션 사용 방법
- \<c:if\> 커스텀 액션은 자바 프로그램의 if문과 비슷한 역할을 하는 커스텀 액션입니다.
- 자바와는 달리 조건식을 괄호 안에 쓰는 것이 아니라 test라는 이름의 애트리뷰트 값으로 지정해야 합니다.

```
<c:if test="${num1 > num2}">
	num1이 더 큽니다.
</c:if>
```

#### Maximum.jsp
```
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:if test="${param.NUM1 - param.NUM2 >= 0}">
			${param.NUM1}
		</c:if>
		<c:if test="${param.NUM1 - param.NUM2 < 0}">
			${param.NUM2}
		</c:if>
	</body>
</html>
```

#### <c:if> 커스텀 액션의 조건식에 직접 쓸 수 있는 상수
\<c:if\> 커스텀 액션의 test 애트리뷰트에 쓰이는 조건식은 EL 식 형태로 써야 하는 것이 기본이지만 다음과 같이 true와 false라는 상수 값을 직접 쓸 수도 있습니다.

```
<c:if test="true">
	이 문장은 항상 출력됩니다.
</c:if>
```

```
<c:if test="false">
	이 문장은 절대 출력되지 않습니다.
</c:if>
```

#### <c:choose> 커스텀 액션 사용 방법
- 자바 프로그램에 switch 문이 있다면, JSTL 코어 라이브러리에는 <c:choose> 커스텀 액션이 있습니다.
- \<c:when\>, \<c:otherwise\>라는 커스텀 액션과 함께 사용되는데, 이 두 액션은 각각 switch 문의 case, default 절과 비슷한 역할을 합니다.

#### Greeting.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:choose>
			<c:when test="${param.NUM == 0}">
				처음 뵙겠습니다. <br>
			</c:when>
			<c:when test="${param.NUM == 1}">
				반갑습니다. <br>
			</c:when>
			<c:otherwise>
				안녕하세요. <br>
			</c:otherwise>
		</c:choose>
	</body>
</html>
```

#### <c:forEach> 커스텀 액션 사용방법
- \<c:forEach\> 커스텀 액션은 자바 프로그램의 for 문에 해당하는 기능을 제공하는 커스텀 액션입니다.
- 즉, 이 액션을 이용하면 특정 HTML 코드를 일정 횟수만큼 반복해서 출력할 수 있습니다.

```
<c:forEach begin="1" end="10">
	야호<br>   <!-- 반복 출력할 명령문 -->
</c:forEach>
```
- begin : 시작 값
- end : 끝 값

```
<c:forEach var="cnt" begin="1" end="10">
	${cnt} <br>
</c:forEach>
```
- cnt : 카운터 변수

```
<c:forEach var="cnt" begin="1" end="10" step="2">
	${cnt} <br>
</c:forEach>
```
- step : 증가치

#### Echo.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:forEach var="cnt" begin="1" end="5">
			<font size=${cnt}>야~호~</font> <br>
		</c:forEach>
	</body>
</html>
```

```
<c:forEach var="str" items="${arr}">
	${str} <br>
</c:forEach>
```
- str : 배열의 각 항목을 저장할 변수
- arr : 배열 이름 

#### \<c:forEach\>액션의 items 애트리뷰트를 이용해서 처리할 수 있는 데이터 
- 배열
- java.util.Collection 객체
- java.util.Iterator 객체
- java.util.Enumeration 객체
- java.util.Map 객체
- 콤마(,)로 구분된 항목들을 포함한 문자열

#### LunchMenu.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%
	String[] arr = { "불고기 백반", "오므라이스", "콩국수" };
	request.setAttribute("MENU", arr);
%>
<jsp:forward page="LunchMenuView.jsp" />
```

#### LunchMenuView.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<ul>
			<c:forEach var="dish" items="${MENU}">
				<li>${dish}</li>
			</c:forEach>
		</ul>
	</body>
</html>
```

#### <c:forTokens> 커스텀 액션 사용 방법
- \<c:forTokens\>커스텀 액션은 자바의 for문과 java.util.StringTokenizer 클래스의 기능을 합친 것과 같은 기능을 제공합니다.
- 다시 말해서 문자열에 포함된 토큰을 분리해서 각각의 토큰에 대해 반복 처리를 수행하도록 만드는 기능을 말합니다.
- 이 액션에는 items, delims, var라는 3개의 애트리뷰트를 써야 합니다.
	- items : 토큰을 포함하는 문자열을 지정
	- delims : 토큰을 분리하는 데 사용할 구획문자를 기술 
	- var : 분리된 토큰을 대입할 변수의 이름을 써야 합니다.
	
```
<c:forTokens var="pet" items="햄스터 이구아니 소라게" delims=" ">
	${pet}<br>
</c:forTokens>
```

```
<c:forTokens var="fruit" items="딸기*키위/체리-참외" delims="*/-">
	${fruit}<br>
</c:forTokens>
```
#### WildKingdom.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:set var="guests" value="토끼^^거북이~사슴" />
		<c:forTokens var="animal" items="${guests}" delims="^~">
			${animal} <br>
		</c:forTokens>
	</body>
</html>
```

#### <c:catch> 커스텀 액션 사용 - 방법
- 자바의 프로그래밍 언어의 try문과 비슷한 역항을 하는 커스텀 액션입니다.
- \<c:catch\> 커스텀 액션은 try 문의  try 블록과 마찬가지의 일을 합니다.
- 이 액션의 시작 태그와 끝 태그 사이에서 에러가 발생하면 실행의 흐름이 곧바로 \<c:catch\>액션 다음에 있는 코드로 넘어갑니다.
- 그리고 이때 에러에 대한 정보를 담은 익셉션 객체가 \<c:catch\>의 시작 태그에 있는 var 애트리뷰트에 저장한 이름의 변수에 대입됩니다.

#### Divide.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglibn prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String str1 = request.getParameter("NUM1");
	String str2 = request.getParameter("NUM2");
	int num1 = Integer.parseInt(str1);
	int num2 = Integer.parseInt(str2);
%>
<html>
	<body>
		<c:catch var="e">
			<% int result = num1 / num2; %>
			나눗셈의 결과는? <%= result %>
		</c:catch>
		<c:if test="${e != null}">
			에러 메시지 : ${e.message}
		</c:if>
	</body>
</html>
```

#### <c:redirect> 커스텀 액션 사용 방법
- JSP 페이지를 호출하는 \<jsp:forward\> 표준 액션에 대해서 배웠습니다. 이 표준 액션은 RequestDispatcher 인터페이스의 forward 메서드와 동일한 방법으로 JSP 페이지를 호출합니다.
- \<c:redirect\> 커스텀 액션도 이와 비슷한 일을 합니다. 하지만 이 메서드는 forward 메서드가 아니라 sendRedirect 메서드와 동일한 방법으로 작동합니다. 
- 그렇기 때문에 이 액션을 이용하면 JSP 페이지가 아닌 웹 자원과 다른 웹 서버에 있는 웹 자원도 호출할 수 있습니다.

```
<c:redirect url="http://www.yonggyo.com:3001" />
```

- 때로는 다른 웹 자원을 호출하면서 데이터를 넘겨주어야 할 경우도 있습니다. 자바 코드에서는 이런 일을 하기 위해 해당 데이터를 쿼리 스트링 형태로 만들어서 URL 뒤에 덧붙여야 하지만 <c:redirect> 커스텀 액션을 이용하면 훨씬 더 간편하고 구조적인 방법으로 이런 일을 할 수 있습니다. 
- 다음과 같이 <c:redirect>의 시작 태그와 끝 태그 사이에 <c:param>이라는 커스텀 액션을 쓰고 name과 value라는 애트리뷰트를 이용해서 데이터 이름과 데이터 값을 각각 지정하면 됩니다.

```
<c:redirect url="Buy.jsp">
	<c:param name="code" value="75458" />
	<c:param name="num" value="2" />
</c:redirect>
```
- 위 코드가 실행되면 Buy.jsp?code=75458&num=2

#### Redirect.jsp
```
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:redirect url="Multiply.jsp">
	<c:param name="NUM1" value="5" />
	<c:param name="NUM2" value="25" />
</c:redirect>
```

#### <c:import> 커스텀 액션 사용 방법
- \<c:import\> 커스텀 액션은 8장에서 배웠던 \<jsp:include\> 표준 액션과 비슷한 일을 합니다. 다시 말해서 현재의 JSP 페이지에 다른 페이지의 결과를 포함시키는 일을 합니다.
- 하지만 다른 점은 이 액션을 이용하면 다른 웹 서버에 있는 JSP 페이지도 불러올 수 있고, JSP 페이지가 이닌 다른 종류의 웹 자원도 불러올 수 있다는 점입니다.

```
<c:import url="Add.jsp" />
```
- url : 호출할 웹 자원의 URL 

- 이 액션을 이용해서 호출하는 웹 자원에 데이터를 넘겨주어야 할 경우 시작 태그와 끝 태그 사이에 다음과 같이 \<c:param\> 커스텀 액션을 쓰면 됩니다. 
```
<c:import url="adScrap.jsp">
	<c:param name="product" value="TV" />
	<c:param name="ad_index" value="007" />
</c:import>
```

#### <c:url> 커스텀 액션 사용 방법
- \<c:url\> 커스텀 액션은 앞에서 배웠던 \<c:set\> 커스텀 액션과 마찬가지로 변수의 선언에 사용되는 액션이지만, URL을 저장하기 위한 변수의 선언에 사용됩니다.
- 그래서 이 액션에서는 URL을 쉽게 다룰 수 있는 방법도 제공하고 있습니다.

```
<c:url var="myUrl" value="Add.jsp" />
```

- URL 뒤에 때로는 스트링 형태로 데이터를 덧붙여야 할 필요가 있습니다. 그럴 때는 이 액션의 시작 태그와 끝 태그 사이에 \<c:param\> 커스텀 액션을 쓰고, name과 value 애트리뷰트 값으로 각각 데이터 이름과 데이터 값을 지정하면 됩니다.

```
<c:url var="myUrl" value="Add.jsp">
	<c:param name="NUM1" value="999" />
	<c:param name="NUM2" value="1" />
</c:url>
```
- Add.jsp?NUM1=999&NUM2=1

- \<c:url\> 커스텀 액션은 바로 앞에서 배운 \<c:redirect\>, \<c:import\> 커스텀 액션과 함께 유용하게 사용될 수도 있습니다.
#### NewRedirect.jsp
```
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="next" value="Divide.jsp">
	<c:param name="NUM1" value="100" />
	<c:param name="NUM2" value="25" />
</c:url>
<c:redirect url="${next}" />
```

#### <c:out> 커스텀 액션 사용 방법
- \<c:out\> 커스텀 액션은 데이터를 출력할 떄 사용하는 커스텀 액션입니다. 
- JSP 페이지에서는 데이터를 출력할 수 있는 다른 방법이 많이 있지만, 웹 브라우저에 의해 특수문자로 해석될 가능성이 있는 \<,\>,&,'," 문자를 포함한 데이터는 이 액션을 이용해서 출력하는 것이 좋습니다.
- 왜냐하면 이 액션은 이런 특수 문자를 자동으로 이스케이프 시퀀스(escape sequence)로 바꿔주는 기능이 있기 때문입니다.

```
<c:out value="<INPUT>은<FORM>의 서브엘리먼트 입니다." />
```

#### XML의 특수 문자와 이스케이프 시퀀스

|특수 문자|이스케이프 시퀀스|
|----|-----|
|\<|\&lt;|
|\>|\&gt;|
|&|\&amp;|
|'|\&#039;|
|"|\&0#034;|

- 하지만 이와 반대로 출력할 데이터에 포함된 이런 기호가 태그의 일부로서 제기능을 하기를 원할 수 있습니다.
- 그럴 때는 다음과 같이 escapeXml이라는 애트리뷰트를 추가하고, 그곳에 false 값을 지정하면 됩니다.  그렇게 하면 특수 문자가 이스케이프 시퀀스로 변환되지 않을 것입니다.

```
<c:out value="<H1>오늘의 과제</H1>" escapeXml="false" />
```
- \<H1\> 태그는 웹브라우저에 의해 태그로 인식됩니다.

#### HtmlUsage.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:out value="<font size='7'>커다란 글씨</font>는 다음과 같은 출력을 합니다." /><br><br>
		<c:out value="<font size='7'>커다란 글씨</font>" escapeXml="false" />
	</body>
</html>
```

- \<c:out\> 커스텀 액션에는 출력할 데이터의 디폴트 값(기본 값)을 지정할 수도 있습니다.

```
<c:out value="${str}" default="No Data" />
```
- str 값이 없으면 No Data를 출력합니다.

#### Hello.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		안녕하세요, <c:out value="${param.ID}" default="guest" />
	</body>
</html>
```


## 포매팅(fmt) 라이브러리

- 날짜를 '2010년 1월 3일'이라고 표시해야 할 때도 있고 '2009/1/3'이라고 표시해야 할 때도 있습니다. 숫자의 경우에도 12500이라는 수를 12,500이라고 표시해야 할 때도 있고, 12500.00이라고 표시해야 할 때도 있습니다.
- JSTL의 포매팅 라이브러리(formatting library)를 이용하면 이런 다양한 포맷 요구 사항을 충족시킬 수 있습니다.
- 이 라이브러리에는 국제화를 지원하는 커스텀 액션도 다수 포함되어 있습니다. 그 중에는 지구상의 여러 시간대에 따라 다른 시각을 계산해서 표시하는 커스텀 액션도 있고, 하나의 JSP 페이지를 가지고 여러 나라의 언어로 구성된 웹페이지를 생성할 수 있도록 만드는 커스텀 액션도 있습니다.

### <fmt:formatDate> 커스텀 액션
- \<fmt:formatDate\>는 날짜와 시각을 포맷하는 커스텀 액션입니다. 
- 이 액션에는 출력할 날짜와 시각을 java.util.Date 클래스 타입의 객체로 넘겨줘야 하기 때문에, 먼저 이 클래스의 객체를 만들어야 합니다. 
```
Date date = new Date();
```

- Date 객체를 만든 다음에는 \<fmt:formatDate\> 커스텀 액션을 이용해서 이 객체가 담고 있는 날짜와 시각을 출력할 수 있습니다.

```
<fmt:formatDate value="${date}" />
```
- date : Date 객체
- 날짜만 출력, type의 default 값은 date

```
<fmt:formatDate value="${date}" type="time" />
```
- time : 시각을 출력하라고 지시하는 애트리뷰트 값 

```
<fmt:formatDate value="${date}" type="both" />
```
- both : 날짜와 시각을 모두 출력하라고 지시하는 애트리뷰트 값

#### Now.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%=new Date() %>" />
<html>
	<body>
		[오늘의 날짜] <fmt:formatDate value="${date}" /><br>
		[현재의 시각] <fmt:formatDate value="${date}" type="time" /> 
	</body>
</html>
```

- dateStyle 애트리뷰트에 넘겨줄 수 있는 값은 full, long, medium, short 네 가지 입니다.
```
<fmt:formatDate type="date" value="${date}" dateStyle="long" />
```

```
<fmt:formatDate type="time" value="${date}" timeStyle="full" />
```

```
<fmt:formatDate type="both" value="${date}" dateStyle="long" timeStyle="short" />
```

#### DateTimeFormat.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%= new Date()%>" />
<html>
	<body>
		[S] <fmt:formatDate value="${date}" type="both" dateStyle="short" timeStyle="short" /><br>
		[M] <fmt:formatDate value="${date}" type="both" dateStyle="medium" timeStyle="medium" /><br>
		[L] <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long" /><br>
		[F] <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full" />
	</body>
</html>
```

- 다른 포맷으로 날짜와 시각을 출력하고 싶다면, 직접 패턴을 지정할 수 도 있습니다. 
- 그 방법은 dateStyle이나 timeStyle 대신 pattern이라는 애트리뷰트를 사용하는 것입니다.

```
<fmt:formatDate value="${date}" type="date" pattern="yyyy/MM/dd (E)" />
```

```
<fmt:formatDate value="${date}" type="time" pattern="(a) hh:mm:ss" />
```

#### DateTimePattern.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%= new Date() %>" />
<html>
	<body>
		[오늘의 날짜] <fmt:formatDate value="${date}" type="date" pattern="yyyy/MM/dd (E)" /><br>
		[현재의 시각] <fmt:formatDate value="${date}" type="time" pattern="(a) hh:mm:ss" />
	</body>
</html>
```

#### <fmt:formatNumber> 커스텀 액션
- \<fmt:formatNumber\> 커스텀 액션을 이용하면 수치를 다양하게 표기할 수 있습니다.
```
<fmt:formatNumber value="10000" />
```
- 위 태그는 10000이라는 수치를 그대로 출력하므로, 굳이 \<fmt:formatNumber\> 태그를 쓸 필요는 없을 것 입니다.

- 세 자리마다 쉼표를 하나씩 첨가하려면 groupingUsed라는 애트리뷰트를 추가하고, 그 값으로 'true'를 지정하면 됩니다.
```
<fmt:formatNumber value="1234500" groupingUsed="true" />
```


```
<fmt:formatNumber value="3.14158" pattern="#.##" />
```
- 주어진 값을 소수점 아래 2자리까지 끊어서 출력하도록 지시합니다.

```
<fmt:formatNumber value="10.5" pattern="#.00" />
```
- pattern 애트리뷰트의 값에서 0이라고 쓴 위치는 표시할 유효숫자가 없으면 0으로 채워집니다.

#### NumberFormat.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<body>
		첫번째 수 : <fmt:formatNumber value="1234500" groupingUsed="true" /><br>
		두번째 수 : <fmt:formatNumber value="3.14158" pattern="#.##" /><br>
		세번쨰 수 : <fmt:formatNumber value="10.5" pattern="#.00" />
	</body>
</html>
```

## 함수(functions) 라이브러리


## SQL 라이브러리
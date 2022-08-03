# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1tGuG_tVDW2PH1pyj-s86N5yN_uy35dB1?usp=sharing)

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

- \<fmt:formatNumber\> 액션을 이용하면 주어진 수치를 퍼센트 단위로 표시할 수도 있습니다. 
- 그렇게 하려면 이 액션에 type 애트리뷰트를 추가하고 그 값으로 "percent"라고 쓰면 됩니다.
- 퍼센트(%)는 1/100을 표시하는 단위이기 때문에 value 애트리뷰트에 주어진 값에 100을 곱하고 그 뒤에 %표시를 붙인 결과가 출력될 것입니다. 

```
<fmt:formatNumber value="0.5" type="percent" />
```

- type 애트리뷰트에 'currency'라는 값을 지정하면 주어진 수치가 금액에 적합한 포맷으로 만들어져서 출력됩니다. 즉, 3자리마다 쉼표가 하나씩 첨가되고, 경우에 따라서는 소수점 아래 2자리까지 표시될 것입니다.
```
<fmt:formatNumber value="2500000" type="currency" />
```

- 화폐 단위를 표시하기 위해서는 currencySymbol이라는 애트리뷰트를 이용하면 됩니다.
```
<fmt:formatNumber value="2500000" type="currency" currencySymbol="₩" />
```

#### NumberType.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<body>
		금액 : <fmt:formatNumber value="1000000" type="currency" currencySymbol="₩" /><br>
		퍼센트 : <fmt:formatNumber value="0.99" type="percent" />
	</body>
</html>
```

#### 지역을 설정하는 <fmt:setLocale> 커스텀 액션
- 날짜와 시간, 수치를 표기하는 방법은 사용하는 언어와 나라에 따라서 달라지기도 합니다.
- 예를 들어 우리나라에서 '2009년 5월 31일 일요일'라고 표시하는 날짜를 영어권에서는 'Sunday, May 31, 2009'라고 표시하고 합니다.
- \<fmt:setLocale\> 커스텀 액션은 그런 차이를 반영해서 특정 지역에 맞게 데이터의 포맷을 설정하고자 할 때 사용하는 액션입니다.

```
<fmt:setLocale value="en" />
```
- en : 언어 코드

```
<fmt:setLocale value="us_en" />
```
- us_en : 국가코드_언어코드

#### WorldFormat.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%=new Date() %>" />
<html>
	<body>
		<h3>우리나라의 포맷</h3>
		<fmt:setLocale value="ko_kr" />
		금액: <fmt:formatNumber value="1000000" type="currency" /><br>
		일시: <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full" /><br>
		
		<h3>미국의 포맷</h3>
		<fmt:setLocale value="en_us" />
		금액: <fmt:formatNumber value="1000000" type="currency" /><br>
		일시: <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full" /><br>
		
		<h3>일본의 포맷</h3>
		<fmt:setLocale value="ja_jp" />
		금액: <fmt:formatNumber value="1000000" type="currency" /><br>
		일시: <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full" /><br>
	</body>
</html>
```

#### 시간대를 설정하는 <fmt:timeZone>과 <fmt:setTimeZone> 커스텀 액션
```
<fmt:timeZone value="America/New_York">
	날짜: <fmt:formatDate value="${date}" type="date" />
	시각: <fmt:formatDate value="${date}" type="time" />
</fmt:timeZone>
```
- date 객체에 들어 있는 날짜와 시각을 뉴욕 시간대에 맞춰서 출력할 것입니다.

#### <fmt:timeZone> 커스텀 액션에 사용하는 지역 이름
- \<fmt:timeZone\> 커스텀 액션의 value 애트리뷰트에는 시간대를 대표하는 지역 이름을 써야 하는데, 아무 이름이나 써도 되는 것은 아닙니다. 
- JDK 라이브러리에 있는 java.util.TimeZone 클래스의 getAvailableIDs 메서드를 호출해서 리턴되는 이름 중 하나를 골라서 써야 합니다. 이 메서드는 정적 메서드이고, 리턴값은 지역 이름을 포함한 문자열 배열이므로 다음과 같은 방법으로 호출해야 합니다.

```
String[] arr = TimeZone.getAvailableIDs();  // 시간대 설정에 사용 가능한 모든 지역 이름을 리턴하는 메서드
```
- 다음은 이 메서드를 이용해서 시간대 지역 이름을 출력하는 JSP 페이지 입니다.
```
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.TimeZone"%>
<html>
	<body>
		<%
			String[] arr = TimeZone.getAvailableIDs();
			for (int cnt = 0; cnt < arr.length; cnt++) {
				out.println(arr[cnt] + "<br>");
			}
		%>
	</body>
</html>
```

#### WorldTime.jsp
```
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%= new Date()%>" />
<html>
	<body>
		서울: <fmt:formatDate value="${date}" type="both" /><br>
		<fmt:timeZone value="Asia/Hong_Kong">
			홍콩: <fmt:formatDate value="${date}" type="both" /><br>
		</fmt:timeZone>
		<fmt:timeZone value="Europe/London">
			런던: <fmt:formateDate value="${date}" type="both" /><br>
		</fmt:timeZone>
		<fmt:timeZone value="America/New_York">
			뉴욕: <fmt:formatDate value="${date}" type="both" /><br>
		</fmt:timeZone>
	</body>
</html>
```

#### <fmt:setTimeZone> 커스텀 액션 
- 이 커스텀 액션도 <fmt:timeZone>과 마찬가지로 시간대를 설정하는 기능을 하지만, 시작 태그와 끝 태그 사이에만 영향을 미치는 것이 아니라, 이 액션이 실행된 다음의 모든 코드에 영향을 미칩니다.

```
<fmt:setTimeZone value="Europe/London" />
```
- 위 코드가 실행되고 난 다음에 날짜와 시간을 출력하면 런던의 시간대에 맞춰서 자동으로 계산된 결과가 출력될 것입니다.

#### NewWorldTime.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%= new Date()%>" />
<html>
	<body>
		서울 : <fmt:formatDate value="${date}" type="both" /><br>
		<fmt:setTimeZone value="Asia/Hong_Kong" />
		홍콩 : <fmt:formatDate value="${date}" type="both" /><br>
		<fmt:setTimeZone value="Europe/London" />
		런던 : <fmt:formatDate value="${date}" type="both" /><br>
		<fmt:setTimeZone value="America/New_York" />
		뉴욕 : <fmt:formatDate value="${date}" type="both" /><br>
	</body>
</html>
```

#### 다국어를 지원하는 <fmt:setBundle>과 <fmt:bundle> 커스텀 액션
- \<fmt:setBundle\>이나 \<fmt:bundle\>, \<fmt:message\> 커스텀 태그는 하나의 JSP 페이지를 가지고 여러 가지 언어의 웹 페이지를 생성하는 기능을 제공합니다.
- 이 액션들은 언어에 따라 서로 다른 파일의 내용을 읽어서 사용하는 방식을 취하므로 그런 파일을 만들어야 합니다. 
- 그런 파일은 자바 기술에서 사용하는 프로퍼티 파일 형태로 만들어야 하는데, <b>프로퍼티 파일(property file)</b>이란 데이터를 '키=값' 형태로 표현해서 모아놓은 텍스트 파일입니다.

#### src/webapp/WEB-INF/classes/Intro_ko.properties
```
TITLE=회사 소개
GREETING=이 사이트를 방문해 주셔서 감사합니다.
BODY=당사는 소프트웨어 개발을 주업무로 하는 회사입니다.
COMPANY_NAME=(주) 듀크 소프트웨어
```

#### src/webapp/WEB-INF/classes/Intro_en.properties
```
TITLE=About Us
GREETING=Thank you for visiting this site.
BODY=We are a dedicated software development company.
COMPANY_NAME=Duke Software Inc.
```

- 프로퍼티 파일을 만든 다음에는 \<fmt:setBundle\> 또는 \<fmt:bundle\>, 그리고 \<fmt:message\> 커스텀 액션을 이용해서 프로퍼티 파일의 데이터를 읽어올 수 있습니다.
- \<fmt:setBundle\> 액션은 사용할 프로퍼티 파일을 지정하는 역할을 합니다. 그런 일을 하기 위해서는 이 액션에 basename이라는 애트리뷰트를 쓰고, 애트리뷰트 값으로 사용할 프로퍼티 파일의 이름을 지정하면 됩니다.
- 이때 주의해야 할 점은 여기에 프로퍼티 파일의 전체 이름을 쓰는 것이 아니라 대표명, 즉 밑줄과 언어 코드 .properties 확장자를 제외한 나머지 부분만 써야한다는 것입니다.

```
<fmt:setBundle basename="Intro" />
```
- Intro : 프로퍼티 파일명의 대표명
- 프로퍼티 파일의 대표명만 지정한다면, 실제로 어떤 프로퍼티 파일을 사용할지는 JSP페이지를 호출하는 웹 브라우저의 기본 언어가 무엇으로 설정되어 있는지에 따라 결정됩니다.
- 예를 들어 한글 윈도우즈에 설치되어 있는 웹 브라우저는 기본적으로 한글을 사용하도록 설정되어 있지만, 이 설정을 우리가 직접 바꿀 수도 있습니다.
- \<fmt:setBundle\> 커스텀 액션을 이용해서 사용할 프로퍼티 파일을 지정한 다음에는 \<fmt:message\> 커스텀 액션을 이용해서 그 프로퍼티 파일 안에 있는 데이터를 가져울 수 있습니다. 
- 그렇게 하라면 \<fmt:message\> 액션에 key라는 애트리뷰트를 쓰고, 그 값으로 해당 데이터의 키를 쓰면 됩니다.
```
<fmt:message key="TITLE" />
```

#### CompanyIntro.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="Intro" />
<html>
	<head><title><fmt:message key="TITLE" /></title></head>
	<body>
		<h3><fmt:message key="TITLE" /></h3>
		<fmt:message key="GREETING" /><br><br>
		<fmt:message key="BODY" /> <br><br>
		<font size="2"><fmt:message key="COMPANY_NAME" /></font>
	</body>
</html>
```

- \<fmt:message\> 커스텀 액션을 조금 다르게 사용하는 방법을 알아보면, 이 액션은 프로퍼티 파일의 데이터를 가져다가 출력하기 위해서도 사용되지만, 프로퍼티 파일의 데이터를 가져다가 변수에 저장하기 위해 사용할 수도 있습니다.
- 그렇게 하려면 이 액션에 var라는 애트리뷰트를 추가하고 애트리뷰트 값으로 변수 이름을 지정하면 됩니다.

```
<fmt:message var="title" key="TITLE" />
```

#### NewCompanyIntro.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="Intro" />
<fmt:message var="title" key="TITLE" />
<fmt:message var="greeting" key="GREETING" />
<fmt:message var="body" key="BODY" />
<fmt:message var="companyName" key="COMPANY_NAME" />
<html>
	<head><title>${title}</title></head>
	<body>
		<h3>${title}</h3>
		${greeting} <br><br>
		${body} <br><br>
		<font size='2'>${companyName}</font>
	</body>
</html>
```

#### src/webapp/WEB-INF/classes/Intro_ko.properties
```
TITLE=회사 소개
GREETING=안녕하세요, {0}님, {1}번째 방문이시군요.
BODY=당사는 소프트웨어 개발을 주업무로 하는 회사입니다.
COMPANY_NAME=(주) 듀크 소프트웨어
```

#### src/webapp/WEB-INF/classes/Intro_en.properties
```
TITLE=About Us
GREETING=Hi, You haave visited this site {1} times.
BODY=We are a dedicated software development company.
COMPANY_NAME=Duke Software Inc.
```

#### Welcome.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%
	request.setAttribute("ID", "Spiderman");
	request.setAttribute("VNUM", Integer.valueOf(3));
%>
```

#### WelcomeView.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="Intro">
	<fmt:message var="title" key="TITLE" />
	<fmt:message var="greeting" key="GREETING">
		<fmt:param>${ID}</fmt:param>
		<fmt:param>${VNUM}</fmt:param>
	</fmt:message>
	<fmt:message var="body" key="BODY" />
	<fmt:message var="companyName" key="COMPANY_NAME" />
</fmt:bundle>
<html>
	<head><title>${title}</title></head>
	<body>
		${greeting} <br><br>
		${body} <br><br>
		<font size='2'>${companyName}</font>
	</body>
</html>
```
### POST 메서드로 전송된 한글 입력 데이터를 받기 위해 필요한 커스텀 액션
-  \<form\> 요소에서 POST메서드를 통해 한글을 입력받기 위해서는 먼저 **request.setCharacterEncoding**이라는 메서드를 호출해야 했습니다. 
- 그렇게 하지 않으면 한글 데이터를 제대로 받을 수 없는데, 이런 일이 익스프레션 언어를 이용해서 한글 데이터를 받을 경우에도 마찬가지로 발생합니다.
- 이 문제를 해결하려면 JSP 페이지에 다음과 같은 스트립틀릿을 추가해야 합니다.
```
<% request.setCharacterEncoding("UTF-8"); %>
```
- 하지만 JSP 페이지의 가독성을 위해 스트립팅 요소를 사용하지 않기로 하였다면 이 방법 보다는 \<fmt:requestEncoding\> 커스텀 액션을 사용하는 것이 좋습니다.
```
<fmt:requestEncoding value="utf-8" />
```
#### Hello.jsp
```
<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		<form action="HelloResult.jsp" method="post">
			한글 아이디를 입력하세요.<br>
			<input type='text' name='ID'><br><br>
			<input type='submit' value='확인'>
		</form>
	</body>
</html>
```

#### HelloResult.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<body>
		안녕하세요, $param.ID}님
	</body>
</html>
```

## 함수(functions) 라이브러리
- JSTL의 함수 라이브러리는 익스프레션 언어의 EL 식 안에서 사용할 수 있는 EL 함수들의 라이브러리 입니다.
- 이 함수들은 주로 문자열을 처리하는 일을 하며, 자바 JDK 라이브러리의 java.lang.String 클래스에 속하는 메서드들과 비슷한 기능을 제공합니다.
- 하지만 이런 함수의 호출 방법이 java.lang.String 클래스의 메서드를 호출하는 방법과 똑같지는 않습니다. 
- 왜냐하면 이 EL 함수들은 String 객체에 대해 호출하는 것이 아니기 때문에 처리해야 할 문자열을 파라미터로 넘겨주어야 합니다.

#### JSTL의 함수 라이브러리에 있는 함수들

|함수|제공하는 기능|
|------|---------|
|substring(str, index1, index2)|str의 index1부터 index2 -1까지의 부분문자열을 반환|
|substringAfter(str1, str2)|str1에서 str2를 찾아서 그 후의 부분문자열을 반환|
|substringBefore(str1, str2)|str1에서 str2를 찾아서 그 전의 부분문자열을 반환|
|toUpperCase(str)|모든 소문자를 대문자로 치환한 값을 반환|
|toLowerCase(str)|모든 대문자를 소문자로 치환한 값을 반환|
|trim(str)|문자열에서 앞뒤 공백 문자를 제거한 결과를 반환|
|replace(str, src, dest)|str 문자열에 포함된 src를 모두 dest로 치환한 결과를 반환|
|indexOf(str, str2)|str1에 포함된 str2의 시작 인덱스를 반환|
|startsWith(str1, str2)|str1이 str2로 시작하면 true, 그렇지 않으면 false를 반환|
|endsWith(str1, str2)|str1이 str2로 끝나면 true, 그렇지 않으면 false를 반환|
|contains(str1, str2)|str1이 str2를 초함하면 true, 그렇지 않다면 false를 반환|
|containsIgnoreCase(str1, str2)|str1이 str2를 포함하면 true, 그렇지 않으면 false를 반환, contains 함수와는 달리 대소문자를 구별하지 않고 비교함|
|split(str1, str2)|str1을 str2를 기준으로 분리해서 만든 부분문자열 배열을 반환|
|join(arr, str2)|arr 배열의 모든 항목을 합쳐서 리턴, 항목 사이에는 str2가 들어감|
|escapeXml(str)|HTML 문법에 의해 특수 문자로 취급되는 모든 문자를 이스케이스 시퀀스로 치환한 결과를 반환|
|length(obj)|obj가 문자열이면 문자열의 길이, List나 Collection이면 항목의 수를 반환|

#### VariousGreeting.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="greeting" value="How Are You?" />
<html>
	<body>
		본래의 문자열: ${greeting} <br>
		모두 대문자로: ${fn:toUpperCase(greeting)} <br>
		모두 소문자로: ${fn:toLowerCase(greeting)} <br>
		Are의 위치는? ${fn:indexOf(greeting, "Are")} <br>
		Are를 Were로 바꾸면? ${fn:replace(greeting, "Are", "Were")} <br>
		문자열의 길이는? ${fn:length(greeting)} <br>
	</body>
</html>
```

## SQL 라이브러리
[4.Servlet & JSP - 5일차 JDBC, JSTL SQL 참고](https://github.com/yonggyo1125/curriculum300H/tree/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20JDBC%2C%20JSTL%20SQL#jstl-sql-%ED%83%9C%EA%B7%B8-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)

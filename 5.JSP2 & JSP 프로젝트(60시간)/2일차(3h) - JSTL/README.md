# JSTL

## 설치하기

![JSTL1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSTL/images/JSTL1.png)

- [JSTL1.2 다운로드](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2)
- 다운로드 받은 jstl1.2.jar 파일을 src/webapp/WEB-INF/lib에 복사해 준다.

## 코어(core) 라이브러리
- JSTL의 코어 라이브러리는 글자 그대로 가장 핵심적인 기능을 제공하는 라이브러리 입니다.
- 이 라이브러리에서 제공하는 커스텀 액션을 이용하면 일반 프로그래밍 언어에서 제공하는 변수 선언, 조건 분기, 반복 수행 등의 로직을 구사할 수 있고, 다른 JSP 페이지를 호출할 수도 있습니다.

### <c:set> 커스텀 액션
- \<c:set\>은 변수를 선언하고 나서 그 변수에 초기값을 대입하는 기능의 커스텀 액션입니다.\
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


## 포매팅(fmt) 라이브러리

## 함수(functions) 라이브러리

## SQL 라이브러리
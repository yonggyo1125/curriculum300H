# 커스텀 액션 
- JSP가 기본적으로 제공하는 표준액션과 JSTL 라이브러리에 포함되어 있는 커스텀 액션이 있지만 이런 액션으로 만족되니 않는 액션이 있다면?
- 이럴 때는 직접 커스텀 액션을 만들어서 사용하면 됩니다.

## 커스텀 액션을 만드는 방법
- 태그 파일을 작성해서 만드는 방법 
- 태그 클래스를 작성해서 만드는 방법 

## 태그파일을 이용해서 커스텀 액션 만들기
- 태그 파일의 문법은 JSP 페이지의 문법과 거의 동일합니다. 파일의 구조가 HTML 코드를 중심으로 이루어지고, 그 HTML의 앞과 뒤, 그리고 사이사이에 지시자, 스크립팅 요소, 익스프레션 언어, 액션이 들어갈 수 있습니다.
- 이 중 HTML은 웹브라우저로 그대로 출력되고, 스트립팅 요소, 익스프레션 언어, 액션은 웹 컨테이너에 의해 실행되며, 지시자는 웹 컨테이너가 태그 파일을 태그 클래스로 변환할 때 사용됩니다.

### 아주 간단한 태그 파일

- tag 지시자는 태그 파일에만 사용할 수 있는 지시자인데, 웹 컨테이너가 태그 파일을 처리할 때 필요한 여러가지 정보를 기술하는 역할을 합니다.
- tag 지시자는 page 지시자와 마찬가지로 <%@으로 시작해서 %>로 끝나야 합니다. 그리고 <%@ 바로 다음에는 지시자의 종류를 표시하는 tag라는 이름이 와야 합니다.
- 그리고 그 다음에 여러가지 정보를 이름="값" 또는 이름='값' 형태로 기술할 수 있습니다. 즉, 애트리뷰트 형태로 기술할 수 있습니다.
- 커스텀 액션이 본체를 갖지 않도록 만들기 위해서는 body-content라는 이름의 애트리뷰트에 'empty'라는 값을 지정하면 됩니다.

```
<%@tag body-content="empty" %>
```

#### 태그 파일에서 사용할 수 있는 지시어

|이름|역할|
|----|---------|
|tag 지시자|웹 컨테이너가 태그 파일을 처리할 떄 필요한 정보를 기술|
|include 지시자|다른 태그 파일을 포함|
|taglib 지시자|태그 파일에서 사용할 다른 커스텀 액션 태그 라이브러리(tag library)에 대한 정보를 기술|
|attribute 지시자|커스텀 액션의 애트리뷰트에 대한 정보를 기술|
|variable 지시자|커스텀 액션의 변수에 대한 정보를 기술|


#### WEB-INF/tags/line.tag
```
<%@tag body-content="empty" %>
--------------------------------------<br>
```

- 우리가 만든 커스텀 액션을 사용할 떄도 이 taglib 지시자를 써야 합니다. 하지만 태그 파일을 이용해서 만든 커스텀 액션의 경우에는 taglib 지시자의 작성 방법이 조금 다릅니다.
- 이 경우에도 prefix 애트리뷰트를 이용해서 태그 라이브러리를 식별해야 합니다. 이 애트리뷰트 대신 tagdir이라는 애트리뷰트를 이용해서 태그 라이브러리를 식별해야 합니다. 이 애트리뷰트에는 태그 파일이 있는 디렉토리 경로명을 지정해야 합니다.

```
<%@taglib prefix="util" tagtdir="/WEB-INF/tags" %>
```

- prefix : 접두어
- /WEB-INF/tags : 태그 파일이 있는디렉토리의 경로명


#### LunchMenu.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags" %>
<html>
	<head><title>오늘의 메뉴</title></head>
	<body>
		<h3>오늘의 점심 메뉴 입니다.</h3>
		<util:line />
		삼계탕 <br>
		볶음밥 <br>
		튀김우동 <br>
		<util:line />
	</body>
</html>
```

> 태그 파일은 웹 애플리케이션의 WEB-INF/tag디렉토리나 그 아래의 서브디렉토리에 저장해야 합니다. 다른 곳에 저장하면 웹 컨테이너가 태그 파일을 찾지 못합니다.

#### 한글을 포함하는 태그 파일
- JSP 페이지에 한글이 포함되어 있을 때는 page 지시자의 contentType 애트리뷰트를 이용해서 한글이 포함되어 있음을 표시해야 합니다. 
- 이와 유사하게 태그 파일에 한글이 포함되어 있을 때는 tag 지시자의 pageEncoding 애트리뷰트를 이용해서 한글이 포함되어 있음을 표시해야 합니다. 
- 단, 이 경우는 애트리뷰트 값을 'text/html; charset=utf-8' 이라고 쓰는 것이 아니라 'utf-8'이라고 써야 합니다.

```
<%@tag pageENcoding='utf-8' %>
<font size='1'>이 문서는 저작ㅇ권이 있습니다.</font><br>
```
#### 애트리뷰트를 지원하는 태그 파일
- 바로 앞에서 작성했던 <util:line /> 커스텀 액션은 출력하는 선의 길이와 색이 정해져 있었습니다. 이번에는 애트리뷰트를 이용해서 선의 길이와 색을 지정할수 있는 커스텀 액션을 만들어 보겠습니다.

```
<util:newLine color="red" size="20" />
```
- 애트리뷰트가 있는 커스텀 액션을 만들기 위해서는 태그 파일에 각각의 애트리뷰트를 위한 attribute 지시자를 써야 합니다.
- attribute 지시자는 다른 지시자와 마찬가지로 <%@으로 시작하고 %>로 끝나야 합니다. 
- 그리고 <%@ 바로 다음에는 attribute라는 지시자 이름을 써야 하고, 그 다음에 애트리뷰트에 대한 여러가지 정보를 이름="값" 또는 이름='값' 형태로 쓸 수 있습니다.

```
<%@attribute name="color" %>
```

- 태그 파일에 이런 attribute 지시자가 있으면 커스텀 액션의 color 애트리뷰트 값은 태그 파일로 전달됩니다. 그러면 태그 파일 안에서는 그 값을 이용해서 필요한 처리를 할 수 있습니다.
- 태그 파일로 전달된 애트리뷰트 값을 사용하는 방법은 두 가지 입니다.
	- \<%= color %> : 스크립팅 요소 안에서 자바 변수처럼 사용할 수 있습니다. 
	- ${color} : 익스프레스 언어의 EL 싱 안에서 데이터 이름으로 사용할 수 있습니다.

- 커스텀 액션 파일로 전달되는 애트리뷰트 값은 기본적으로 문자열 타입입니다. 용도에 따른 적절한 타입으로 만들어야 합니다.
- 그렇게 하라면 attribute 지시자에 type 애트리뷰트를 추가하고, 거기에 원하는 데이터 타입을 지정하면 됩니다. 
- 이때 주의할 점은 기본 자료형 타입(primitive type)은 type 애트리뷰트 값으로 지정할 수 없습니다. 따라서 int 타입이 필요할 때는 그 대신 java.lang.Integer 클래스 타입을 사용해야 합니다.
- <%@attribute name="size" type="java.lang.Integer" %>

#### WEB-INF/tags/util/newLine.tag
```
<%@tag body-content="empty" %>
<%@ attribute name="color" %>
<%@ attribute name="size" type="java.lang.Integer" %>
<font color="${color}">
<%
	for (int cnt = 0; cnt < size; cnt++)
		out.print("-");
%>
</font>
<br>
```

#### SupperMenu.jsp 
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		<util:newLine color="blue" size="25" />
		저녁 메뉴<br>
		<util:newLine color="red" size="20" />
		불고기 덮밥<br>
		카레라이스<br>
		쫄면<br>
		<util:newLine color="blue" size="25" />
	</body>
</html>
```

- \<util:newLine\> 커스텀 액션은 두 개의 애트리뷰트를 모두 썼을 때는 정상적으로 작동하지만 size 애트리뷰트 쓰지 않으면 선이 전혀 출력되지 않을 것입니다. 
- for문이 한 번도 반복 실행되지 않아서 그런 것인데, 이것은 이 커스텀 액션의 사용법을 잘 모르는 경우 이해할 수 없는 오류로 보일 수 있습니다.
- 이런 문제를 해결하기 위해서는 태그파일에서 size 애트리뷰트를 선언할 때 이것을 필수 애트리뷰트로 만들면 됩니다.  방법은 attribute 지시어에 **required 애트리뷰트를 추가하고 그 값으로 "true"**를 지정하면 됩니다.

```
<%@attribute name="size" type="java.lang.Integer" required="true" %>
```

#### 태그 파일의 내장 변수
|변수 이름|변수 타입|설명|
|-----|-------|-------|
|request|javax.servlet.http.HttpServletRequest|웹 브라우저로부터의 요청 처리|
|response|javax.servlet.http.HttpServletResponse|웹 브라우저의 응답 처리|
|out|javax.servlet.jsp.JspWriter|HTML 출력|
|application|javax.servlet.ServletContext|웹 애플리케이션에 관련된 정보|
|session|javax.servlet.http.HttpSession|세션에 관련된 정보|
|config|javax.servlet.ServletConfig|서블릿이나 JSP 페이지의 구성정보|
|jspContext|javax.servlet.jsp.JspContext|커스텀 액션을 사용한 JSP 페이지에 관련된 정보|

> 위 표에 있는 내장 변수는 대부분 JSP의 내장 변수와 동일한 이름, 동일한 타입을 갖고, 동일한 역할을 하지만 다른 경우도 있습니다.


### 동적 애트리뷰트를 지원하는 태그 파일
- 커스텀 액션의 애트리뷰트 각각을 위한 attribute 지시자를 쓰는 대신 모든 애트리뷰트를 한꺼번에 선언하는 방법도 있습니다. 
- 커스텀 액션이 동적 애트리뷰트를 지원하도록 만들면 됩니다.
- 그 방법은 태그 파일의 tag 지시자에 dynamic-attibutes 애트리뷰트를 쓰고, 그 값으로 커스텀 액션의 모든 애트리뷰트의 대표 이름을 쓰는 것입니다.

```
<%@tag dynamic-attributes="attrs" %>
```
- attrs : 동적 애트리뷰트의 대표 이름
- 커스텀 액션은 사용된 모든 애트리뷰트의 \<이름, 값\>을 java.util.Map 객체 안에 저장한 후 그 객체를 태그 파일로 전달합니다.
- 이때 tag 지시자의 dynamic-attributes 애트리뷰트 값이 이 Map 객체의 이름이 됩니다. 그러므로 다음과 같은 EL식을 사용하여 커스텀 액션에서 사용된 애트리뷰트 값을 가져올 수 있습니다.

```
${attrs.color}
```
- 위의 EL 식은 커스텀 액션에 기술된 color 애트리뷰트 값을 출력합니다. 
- 일반 애트리뷰트의 이름과 달리 동적 애트리뷰트의 대표 이름은 자바 변수로 만들어지지 않습니다.
- 커스텀 액션의 애트리뷰트를 담고 있는 Map 객체는 page 데이터 영역을 통해 태그 파일에 전달됩니다. 
- 태그 파일에서 page 영역의 데이터를 가져오려면 jspContext 내장 변수에 대해 getAttribute라는 메서드를 호출하면 됩니다.
- 그러므로 애트리뷰트를 담고 있는 Map 객체를 가져오려면 다음과 같이 getAttribute 메서드에 동적 애트리뷰트의 대표 이름을 넘겨주면 됩니다.
```
Map attrs = (Map) jspContext.getAttribute("attrs");
```
- 이렇게 가져온 Map 객체에 대해서 get 메서드를 호출하면서 애트리뷰트 이름을 넘겨주면 애트리뷰트 값을 가져올 수 있습니다. 
- 이때 주의할 점은 동적 애트리뷰트의 경우 모든 애트리뷰트 값이 문자열로 저장되므로 get 메서드의 리턴값을 String 타입으로 변환해서 String 타입의 변수에 대입해야 한다는 것 입니다.
```
String str = (String) attrs.get("size");
```
- 그 다음에 필요하다면 애트리뷰트 값을 다음과 같이 원하는 데이터 타입으로 변환해서 사용하면 됩니다.
```
int size = Integer.parseInt(str);
```

#### WEB-INF/tags/util/doubleLine.tag
```
<%@tag body-content="empty" %>
<%@tag dynamic-attributes="attrs" %>
<%@ tag import="java.util.Map" %>
<font color="${attrs.color}">
<%
	Map<String, String> attrs = (Map<String, String>)jspContext.getAttribute("attrs");
	String str = attrs.get("size");
	int size = Integer.parseInt(str);
	for(int cnt = 0; cnt < size; cnt++) {
		out.print("=");
	}
%>
</font><br>
```

#### SatMenu.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		<h3>오늘은 토요일이므로 간단한 분식만 제공합니다.</h3>
		<util:doubleLine color="green" size="30" />
		 샌드위치 <br>
		 우동 <br>
		 <util:doubleLine color="purple" size="50" />
	</body>
</html>
```

- 동적 애트리뷰트를 지원하는 커스텀 액션은 어느 애트리뷰트가 유효한 것인지 일일히 확인하지 않습니다.
- 그렇기 때문에 실제로 필요치 않은 애트리뷰트가 사용되더라도 문법 에러가 발생하지 않습니다. 
- 그러므로 프로그래머나 웹 디자이너가 커스텀 액션의 사용 방법을 잘 모르고 엉뚱한 애트리뷰트를 사용했을 때에도 프로그램이 정상적으로 수행될 수 있는 문법적 유연성을 제공합니다.


### 커스텀 액션의 본체를 처리하는 태그 파일
- 커스텀 액션의 시작 태그와 끝 태그 사이에 오는 내용을 커스텀 액션의 본체(body)라고 합니다.
```
<util:box>
	안녕하세요, 여러분!<br>
	홈페이지를 오픈했습니다.
</util:box>
```

- 본체가 있는 커스텀 액션을 만들기 위해서는 태그 파일에서 tag 지시자의 body-content 애트리뷰트에 'empty' 대신 **'scriptless'**나 **'tagdependent'**라는 값을 써야 합니다.
- 이 중 **'scriptless'**라는 값은 커스텀 액션의 본체에 스트립팅 요소를 사용하면 안 된ㄴ다는 의미입니다.
```
<%@tag body-content="scriptless" %>
```
- scriptless : 커스텀 액션의 본체에 스크립틀릿을 쓸 수 없음을 표시합니다.

- 위와 같은 tag 지시자를 이용해서 만든 커스텀 액션의 본체 안에서 스트립팅 요소를 사용하면 에러가 발생합니다.
- body-content 애트리뷰트에 'tagdependent'라는 값을 지정했을 땐 커스텀 액션의 본체에 있는 스트립팅 요소, 익스프레션 언어, 액션이 전혀 처리되지 않고, 있는 그대로의 텍스트가 본체의 내용으로 취급될 것입니다.

```
<%@tag body-content="tagdependent" %>
```
- tagdependent : 커스텀 액션의 본체에 포함된 스크립틀릿, 익스프레션 언어, 액션이 있는 그대로 본체의 일부로 인식됩니다.


- 커스텀 액션은 본체 안에 익스프레션 언어나 액션을 포함할 가능성도 있으므로 body-content 애트리뷰트에 scriptless라는 값을 사용하는 것이 좋습니다.

- 하지만 태그 파일의 body-content 애트리뷰트에 이런 값을 지정해 놓더라도 본체의 내용이 자동으로 출력되는 것은 아닙니다. 
- 커스텀 액션의 본체 내용을 출력하기 위해서는 본체 내용을 출력하고자 하는 위치에 <jsp:doBody> 표준 액션을 쓰면 됩니다.

#### WEB-INF/tags/util/box.tag
```
<%@tag body-content="scriptless" %>
<div style='border: 1px solid red; padding: 10px'>
	<jsp:doBody />
</div>
```

#### Notice1.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		구내 식당에서 알려드립니다.
		<util:box>
			공사 관계로 급식을 일시 중단합니다.<br>
		</util:box>
	</body>
</html>
```

### 변수를 지원하는 커스텀 액션 
- 커스텀 액션의 애트리뷰트는 JSP 페이지로부터 태그파일로 데이터를 넘겨주는 역할을 합니다. 그러면 태그 파일이 JSP 페이지로 데이터를 넘겨주기 위해서는 어떻게 해야 할까요?
- 그럴 때는 태그 파일 안에 variable 지시자를 이용해서 변수를 선언해 놓으면 됩니다.
- 그런 후에 태그 파일에서 이 변수에 어떤 값을 대입하면 JSP 페이지에서 그 변수를 통해 그 값을 사용할 수 있습니다.
- variable 지시자는 다른 지시자와 마찬가지로 \<%@로 시작하고 %\>로 끝나야 합니다. 
- \<%@ 바로 다음에는 variable이라는 지시자 이름이 와야 하고, 그 다음에 변수의 선언에 필요한 여러가지 정보가 애트리뷰트의 형태로 기술될 수 있습니다.
- 변수의 이름은 name-given이라는 애트리뷰트를 이용해서 지정할 수 있습니다.
```
<%@variable name-given="result" %>
```
- 이렇게 선언한 변수는 기본적으로 String 타입이 됩니다.
- 다른 타입의 변수를 선언하기 위해서는 variable-class라는 애트리뷰트를 추가하면 됩니다.
- 이때 주의할 점은 이 애트리뷰트에 기본자료형 타입(primitive type)을 지정할 수 없으므로, 그에 해당하는 래퍼클래스(wrapper class)를 대신 지정해야 합니다. 예를 들어 int 타입의 변수가 필요할 때는 다음과 같이 java.lang.Integer. 타입을 대신 지정해야 합니다.
```
<%@variable name-given="result" variable-class="java.lang.Integer" %>
```
- 이렇게 선언해 놓은 변수는 태그 파일 안에서도 사용할 수 있고, 그 태그 파일을 호출하는 JSP 페이지 안에서도 사용할 수 있습니다. 하지만 JSP 페이지의 모든 위치에서 사용할 수 있는 것은 아닙니다. 그 태그 파일이 구현하는 커스텀 액션의 본체 안에서만 사용할 수 있습니다.
- 때로는 이런 변수를 커스텀 액션 본체 밖에서 사용해야 할 필요가 있는데, 그 때는 variable 지시자에 scope라는 애트리뷰트를 추가할 수 있습니다.
- scope 애트리뷰트는 변수의 사용 범위를 지정하는 역할을 하며, 여기에는 **NESTED, AT_BEGIN, AT_END** 중 한 값을 지정할 수 있습니다. 
- **NESTED** :  커스텀 액션의 본체 안에서만 변수를 사용할 수 있다는 의미
- **AT_BEGIN** : 커스텀 액션의 시작 태그 다음 위치부터 변수를 사용할 수 있다는 의미
- **AT_END** : 커스텀 액션의 끝 태그 다음 위치부터 변수를 사용할 수 있다는 의미

```
<%@variable name-given="result" variable-class="java.lang.Integer" scope="AT_END" %>
```
- 단독으로 사용되는 태그 다음에 변수를 사용할 수 있도록 만드려면 **AT_END**로 지정하는 것이 더 적합

```
<c:set var="result" value="100" />
```
- 이렇게 변수에 저장한 값은 JSP 페이지로 전달될 것이고, JSP 페이지에서는 이 값을 익스프레션 언어의 EL 식을 이용하여 사용할 수 있습니다.
```
${result}
```

#### WEB-INF/tags/util/max.tag
```
<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="num1" type="java.lang.Integer" %>
<%@ attribute name="num2" type="java.lang.Integer" %>
<%@variable name-given="maximum" variable-class="java.lang.Integer" scope="AT_END" %>
<%
	int result;
	if (num1 > num2)
		result = num1;
	else 
		result = num2;
%>
<c:set var="maximum" value="<%=result%>" />
```

#### Maximum.jsp?NUM1=10&NUM2=20
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		<h3>최대값 구하기</h3>
		<util:max num1="${param.NUM1}" num2="${param.NUM2}"  />
		최대값: ${maximum}
 	</body>
</html>
```

- 위 방법은 커스텀 액션의 결과를 리턴하는 maximum 변수의 이름이 태그 파일 안에 고정되어 있다는 단점이 있습니다. 
- 이렇게 되면 커스텀 액션의 사용자가 변수의 이름을 잘못 알고 다른 이름을 사용할 가능성이 있습니다. 그리고 다른 데이터 이름과 충돌할 가능성도 있습니다.

- 이런 문제를 해결하려면 애트리뷰트를 이용해서 다음과 같이 변수의 이름을 지정하면 됩니다.
```
<util:max var="maxinum" num1="37" num2="42" />
```
- 이렇게 애트리뷰트를 이용해서 변수의 이름을 지정할 때는 태그 파일의 작성방법도 변경되어야 합니다.

```
<%@attribute name="var" required="true" rtexprvalue="false" %>
```
- 변수 이름을 지정할 애트리뷰트를 선언할 때 지켜야 할 규칙
	- required="true" : 이 애트리 뷰트를 필수 애트리뷰트로 만들어야 합니다.
	- rtexprvalue="false" : 선언하는 애트리뷰트에는 스크립팅 요소나 익스프레스 언어를 값으로 지정할 수 없도록 만들어야 합니다.

```
<%@variable name-from-attribute="var" alias="maximuim" variable-class="java.lang.Integer" scope="AT_END" %>
```
- 변수를 선언하는 variable 지시자도 다르게 기술해야 합니다.
	- name-given 애트리뷰트를 이용해서 변수의 이름을 지정하는 것이 아니라 name-from-attribute 애트리뷰트를 이용해서 변수의 이름을 지정할 애트리뷰트 이름을 지정해야 합니다.
	- alias="maximum" : 태그파일에서 사용할 변수의 이름을 따로 선언해야 합니다. 그런 일은 alias라는 애트리뷰트를 이용해서 할 수 있습니다.

#### WEB-INF/tags/util/newMax.tag
```
<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="var" required="true" rtexprvalue="false" %>
<%@ attribute name="num1" type="java.lang.Integer" %>
<%@ attribute name="num2" type="java.lang.Integer" %>
<%@ variable name-from-attribute="var" alias="maximum" variable-class="java.lang.Integer" scope="AT_END" %>
<%
	int result;
	if (num1 > num2)
		result = num1;
	else
		result = num2;
%>
<c:set var="maximum" value="<%=result%>" />
```

#### NewMaximum.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		<h3>최대값 구하기</h3>
		<util:newMax var="MAX"  num1="20" num2="30" />
		최대값 : ${MAX}
	</body>
</html>
```

### 커스텀 액션의 본체 안에서 변수를 사용하는 예
#### WEB-INF/tags/util/compute.tag 
```
<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="scriptless" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="var" required="true" rtexprvalue="false" %>
<%@attribute name="start" type="java.lang.Integer" %>
<%@attribute name="end" type="java.lang.Integer" %>
<%@variable name-from-attribute="var" alias="number" variable-class="java.lang.Integer" scope="NESTED" %>
<% for (int i=start; i <= end; i++) { %>
	<c:set var="number" value="<%=i %>" />
	<jsp:doBody />
<% }%>
```

#### Square.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<html>
	<body>
		<h3>1부터 5까지의 제곱표</h3>
		<util:compute var="num" start="1" end="5">
			${num}의 제곱은? ${num * num} <br>
		</util:compute>
	</body>
</html>
```

## 태그 클래스를 이용해서 커스텀 액션 만들기
- SimpleTag 인터페이스를 구현하여 태그 클래스를 작성할 수 있습니다.

### SimpleTag 인터페이스를 구현하는 태그 클래스

![SimpleTag](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/simpleTag.png)

> [SimpleTag 인터페이스](https://docs.oracle.com/javaee/7/api/javax/servlet/jsp/tagext/SimpleTag.html)





## 태그 라이브러리를 만드는 방법

## 커스텀 액션 태그를 이용하여 레이아웃 구성하기
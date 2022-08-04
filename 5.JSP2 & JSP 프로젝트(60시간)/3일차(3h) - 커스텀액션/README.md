# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1bG8Nx5rUDhdO07McGf1c5IK_g4Y7E0ca?usp=sharing)

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
<%@tag pageEncoding='utf-8' %>
<font size='1'>이 문서는 저작권이 있습니다.</font><br>
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
	- ${color} : 익스프레스 언어의 EL식 안에서 데이터 이름으로 사용할 수 있습니다.

- 커스텀 액션 파일로 전달되는 애트리뷰트 값은 기본적으로 문자열 타입입니다. 용도에 따른 적절한 타입으로 만들어야 합니다.
- 그렇게 하려면 attribute 지시자에 type 애트리뷰트를 추가하고, 거기에 원하는 데이터 타입을 지정하면 됩니다. 
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
- 이런 문제를 해결하기 위해서는 태그파일에서 size 애트리뷰트를 선언할 때 이것을 필수 애트리뷰트로 만들면 됩니다.  방법은 attribute 지시어에 <b>required 애트리뷰트를 추가하고 그 값으로 "true"</b>를 지정하면 됩니다.

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

- 본체가 있는 커스텀 액션을 만들기 위해서는 태그 파일에서 tag 지시자의 body-content 애트리뷰트에 'empty' 대신 <b>'scriptless'</b>나 <b>'tagdependent'</b>라는 값을 써야 합니다.
- 이 중 <b>'scriptless'</b>라는 값은 커스텀 액션의 본체에 스트립팅 요소를 사용하면 안 된다는 의미입니다.
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
- scope 애트리뷰트는 변수의 사용 범위를 지정하는 역할을 하며, 여기에는 <b>NESTED, AT_BEGIN, AT_END</b> 중 한 값을 지정할 수 있습니다. 
- <b>NESTED</b> :  커스텀 액션의 본체 안에서만 변수를 사용할 수 있다는 의미
- <b>AT_BEGIN</b> : 커스텀 액션의 시작 태그 다음 위치부터 변수를 사용할 수 있다는 의미
- <b>AT_END</b> : 커스텀 액션의 끝 태그 다음 위치부터 변수를 사용할 수 있다는 의미

```
<%@variable name-given="result" variable-class="java.lang.Integer" scope="AT_END" %>
```
- 단독으로 사용되는 태그 다음에 변수를 사용할 수 있도록 만드려면 <b>AT_END</b>로 지정하는 것이 더 적합

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


- SimpleTag 인터페이스는 모두 5개의 추상메서드가 있습니다. 즉, SimpleTag 인터페이스를 구현하는 태그 클래스를 만들기 위해서는 이 5개의 메서드를 모두 구현해야 합니다. 
- 하지만 이것은 매우 번거로운 작업이고, 어떤 메서드는 항상 똑같은 일을 하기 떄문에 매번 다시 작성하는 것은 비효율적인 일입니다.
- JSP 2.3에서는 이 인터페이스에 있는 모든 메서드를 구현해 놓은 SimpleTagSupport라는 클래스를 제공하고 있습니다.  그러므로 프로그래머는 태그 클래스를 작성할 때 이 클래스를 상속받으면 간접적으로 SimpleTag 인터페이스를 구현할 수 있고, 꼭 필요한 메서드만 작성해도 되니깐 편리합니다.

![SimpleTagSupport](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/simpleTagSupport.png)

> [SimpleTagSupport](https://docs.oracle.com/javaee/7/api/javax/servlet/jsp/tagext/SimpleTagSupport.html)

### SimpleTagSupport 클래스를 이용해서 태그 클래스를 작성하는 방법 
- SimpleTagSupport 클래스를 이용해서 태그 클래스를 만들 떄 반드시 작성해야 하는 메서드는 doTag 메서드입니다, 
- 이 메서드는 커스텀 액션이 실행될 떄 호출되는 것이기 때문에 커스텀 액션이 해야 할 일은 이 메서들 안에 기술해 놓으면 됩니다.

- doTag 메서드는 작성할 떄 지켜야 할 규칙이 있습니다.
	- 이 메서드는 public으로 선언해야 합니다.
	- 매개변수가 없어야 합니다.
	- 외부로 던질 수 있는 예외는 javax.servlet.Jsp.JspException, java.io.IOException 두 가지 입니다.
	
```
public void doTag() throws JspException, IOException {

}
```	

#### src/main/java/tool/StarLineTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class StarLineTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		out.println("*******************************<br>");
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/StarLineTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar StarLineTag.java
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar StarLineTag.java

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	<tlib-version>1.0</tlib-version>
	<short-name>tool</short-name>
	<tag>
		<name>starLine</name>
		<tag-class>tool.StarLineTag</tag-class>
		<body-content>empty</body-content>
	</tag>
</taglib>
```

#### WEB-INF/web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
    <jsp-config>
    	<taglib>
    		<taglib-uri>/taglibs/tools.tld</taglib-uri>
    		<taglib-location>/WEB-INF/tlds/tools.tld</taglib-location>
    	</taglib>
    </jsp-config>
    
</web-app>
```

#### NewBooks.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<h3>이달의 신작도서</h3>
		<tool:starLine />
		멀티프로세서 프로그래밍<br>
		트와일라잇<br>
		회사에서 바로 통하는 관리 회계<br>
		<tool:starLine />
	</body>
</html>
```	


### 애트리뷰트가 있는 커스텀 액션을 만드는 태그 클래스
- 태그 파일의 경우에는 attribute 지시자만 써 넣으면 커스텀 액션에 애트리뷰트를 사용할 수 있었지만, 태그 클래스의 경우에는 각각의 애트리뷰트 값을 받는 메서드를 따로 선언해야 합니다.
- 그리고 메서드는 정해진 규칙에 따라 선언해야 합니다.
	- 애트리뷰트 값을 받는 메서드는 public으로 선언해야 합니다.
	- 메서드의 이름은 set으로 시작해야 하고, 그 다음에 애트리뷰트의 본래 이름에서 첫 번째 문자를 대문자로 바꾼 이름을 붙여서 만들어야 합니다.
	- 그리고 이 메서드에서는 커스텀 액션에서 사용한 애트리뷰트 값을 받기 위한 파라미터 변수도 선언해야 합니다. 
	```
	public void setColor(String color) {
	
	}
	```
	- setColor : color 애트리뷰트 값을 받는 메서드의 이름
	- color : 이 매개변수를 통해 애트리뷰트 값이 전달됩니다.
	
- 파라미터 변수를 통해 받은 애트리뷰트 값은 set메서드 안에서 바로 사용되기 보다는 나중에 호출되는 doTag 메서드 안에서 사용되는 것이 보통입니다. 그러므로 대개의 경우 이 메서드는 매개변수의 값을 멤버변수(field)에 저장하고 끝나는 식으로 작성해도 충분합니다.
```
public class NewLineTag extends SImpleTagSupport {
	private String color;
	...
	
	public void setColor(String color) {
		this.color = color;
	}
}
```

#### src/main/java/tool/NewLineTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class NewLineTag extends SimpleTagSupport {
	private int size;
	private String color;
	
	//size 애트리뷰트를 받는 메서드 
	public void setSize(Integer size) {
		this.size = size;
	}
	
	// color 애트리뷰트를 받는 메서드
	public void setColor(String color) {
		this.color = color;
	}
	
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		out.println("<font color=" + color + ">");
		for(int i = 0; i < size; i++) {
			out.print("*");
		}
		out.println("</font><br>");
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/NewLineTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar NewLineTag.java
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar NewLineTag.java -encoding UTF8 

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
...
	<tag>
		<name>newLine</name>
		<tag-class>tool.NewLineTag</tag-class>
		<body-content>empty</body-content>
		<attribute>
			<name>size</name>
			<type>java.lang.Integer</type>
		</attribute>
		<attribute>
			<name>color</name>
			<type>java.lang.String</type>
		</attribute>
	</tag>
</taglib>
```

#### ElibNotice.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<h3>이달의 신작 자료</h3>
		<tool:newLine color="red" size="40" />
		카르멘 (오페라) DVD<br>
		돈키호테 (발레) DVD<br>
		시간을 달리는 소녀 (애니메이션) DVD<br>
		<tool:newLine color="red" size="30" />
		전자정보실에서만 감상하실 수 있습니다.<br>
		<tool:newLine color="blue" size="50" />
	</body>
</html>
```
### 동적 애트리뷰트 지원하는 태그 클래스
- 동적 애트리뷰트의 이점은 명시적으로 선언되지 않은 애트리뷰트를 사용해도 오류가 발생하지 않는다는 문법적 유연성입니다.
- 태그 클래스를 이용해서도 동적 애트리뷰트를 지원하는 커스텀 액션을 만들 수 있습니다.

- 동적 애트리뷰트를 지원하는 태그 클래스를 만들기 위해서는 각각의 애트리뷰트에 대한 set메서드를 선언할 필요가 없고, 그 대신 setDynamicAttribute라는 이름의 메서드를 하나만 선언하면 됩니다.
- 그렇게 하면 커스팀 액션에서 사용한 애트리뷰트 하나하나에 대해 이 메서드가 한 번씩 호출될 것입니다.
- setDynamicAttibute 메서드는 커스텀 액션에서 사용한 애트리뷰트에 개한 세 가지 정보를 매개변수로 받습니다. 그 중 하나는 애트리뷰트의 이름이고, 다른 하나는 애트리뷰트의 값이며, 나머지 하나는 애트리뷰트의 이름이 속하는 네임스페이스의 URI입니다.
```
public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
	
}
```
- String uri : 애트리뷰트의 이름이 속하는 네임스페이스의 URI
	- 네임스페이스(namespace)란 똑같은 이름의 충돌을 방지하기 위해서 만들어 놓는 이름의 공간을 말합니다. 
	- 하지만 우리가 만드는 커스텀 액션에 속하는 애트리뷰트의 수가 이름 충돌을 걱정해야 할 만큼 많아지는 경우는 드물기 때문에 이런 네임스페이스를 사용해야 할 경우는 거의 없습니다. 
	- 특정한 네임스페이스에 속하지 않는 애트리뷰트일 경우 이 메서드의 첫 번째 파라미터 값은 null이 됩니다.
	- 대개의 경우 setDynamicAttribute 메서드 안에서는 첫 번째 매개변수 값을 무시하고 두 번째와 세 번째 매개변수 값만 사용해도 충분합니다.
- String localName : 애트리뷰트의 이름
- Object value : 애트리뷰트의 값

- 이 메서드가 매개변수로 받은 애트리뷰트의 이름과 값을 나중에 찾아보기 쉽도록 저장해 두어야 합니다. 가장 손쉬운 방법은 태그 클래스에 java.util.Map 타입의 멤버변수를 만들어 놓고, 그 멤버 변수 안에 저장하는 것입니다.

```
public class NewerLineTag extends SimpleTagSupport implements DynamicAttributes {
	private Map<String, Object> attrs = new HashMap<String, Object>();
	... 
	public void setDynamicAttributes(String uri, String localName, Object value) throws JspException {
		attrs.put(localName, value); // 애트리뷰트의 이름과 값을 Map 객체에 저장합니다.
	}
}
```
- 이렇게 하고 나면 나중에 doTag 메서드에서 이 필드에 대해 get메서드를 호출해서 애트리뷰트 값을 가져올 수 있습니다. 
- get 메서드를 호출할 때는 애트리뷰트 이름을 매개변수로 넘겨줘야 합니다.

```
public class NewerLineTag extends SimpleTagSupport implements DynamicAttributes {
	private Map<String, Object> attrs = new HashMap<String, Object>();
	... 
	public void doTag() throws JspException, IOException {
		... 
		String color = (String) attrs.get("color");
		...
	}
	
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/NewerLineTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar NewerLineTag.java
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar NewerLineTag.java -encoding UTF8 

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	...
	
	<tag>
		<name>newerLine</name>
		<tag-class>tool.NewerLineTag</tag-class>
		<body-content>empty</body-content>
		<dynamic-attributes>true</dynamic-attributes>
	</tag>
</taglib>
```
- \<dynamic-attributes\>true\</dynamic-attributes\> : 동적 애트리뷰트를 지원한다는 표시 

#### MovieNotice.jsp 
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<h3>무료 영화상영 안내</h3>
		<tool:newerLine color="red" size="40" background="black" />
		제목 : 폴라 익스프레스<br>
		일시: 2009년 7월 4일 (토) 오후 5:00<br>
		<tool:newerLine color="blue" size="45" height="3" />
	</body>
</html>
```

### 본체가 있는 커스텀 액션을 만드는 태그 클래스
- 태그 파일에서는 커스텀 액션의 본체를 출력하기 위해 \<jsp:doBody /\>라는  표준 액션을 써야 했지만, 태그 클래스에서 해야 할 일은 그보다 훨씬 더 복잡합니다.
- 이때에는 두 가지 단계가 필요합니다.
	- 첫 번째 단계에서는 커스텀 액션의 본체 내용을 가져와야 합니다. 이런 일은 태그 클래스가 상위클래스인 SimpleTagSupport로 부터 상속받은 getJspBody 메서드를 호출해서 할 수 있습니다. 이 메서드의 리턴값은 본체의 내용을 포함하고 있는 JspFragment 타입의 객체이므로, 다음과 같은 방법으로 호출해야 합니다.
		```
		JspFragment body = getJspBody();
		```
	- 두 번째 단계에서는 이 JspFragment 객체를 이용해서 본체의 내용을 출력해야 합니다. 이런 일은 JspFragment 객체에 대한 invoke 메서드를 호출해서 할 수 있습니다. invoke 메서드에서는 본체의 출력에 사용될 출력 스트립을 매개변수로 넘겨줘야 합니다. 예를 들어 out이라는 출력 스트림을 통해 본체 내용을 출력하고자 하면 다음과 같이 호출해야 합니다.
		```
		body.invoke(out);
		```
	- invoke 메서드에는 출력 스트림 대신 null 값을 넘겨줄 수도 있습니다. 그렇게 하면 이 메서드는 JSP 페이지의 출력에 사용되는 출력 스트림을 통해 본체의 내용을 출력할 것 입니다.
		```
		body.invoke(null)
		```
	
#### src/main/java/tool/BoxTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class BoxTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		JspFragment body = getJspBody();
		out.println("<div style='border: 1px solid red; padding: 20px;'>");
		body.invoke(out);
		out.println("</div>");
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/BoxTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar BoxTag.java -encoding UTF8 
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar BoxTag.java -encoding UTF8 

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	... 
	<tag>
		<name>box</name>
		<tag-class>tool.BoxTag</tag-class>
		<body-content>scriptless</body-content>
	</tag>
</taglib>
```
- 본체가 있는 커스텀 액션을 TLD 파일에 등록하기 위해서는 \<tag\> 하위의 \<body-content\>요소에 empty라고 쓰는 대신 scriptless나 tagdependant라고 써야 합니다. 
	- scriptless는 본체가 스트립틀릿을 포함할 수 없음을 의미
	- tagdependent는 본체의 텍스트가 있는 그대로 본체로 인식됨을 의미

#### LibraryNotice.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<tool:box>
			다음달 1일부터 구입 희망 도서를 신청 받습니다.<br>
			많은 참여 바랍니다.<br>
		</tool:box>
	</body>
</html>
```
### 커스텀 액션의 본체 내용을 조작하는 태그 클래스

#### src/main/java/tool/ReplaceTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ReplaceTag extends SimpleTagSupport {
	private String oldWord, newWord;
	
	public void setOldWord(String oldWord) {
		this.oldWord = oldWord;
	}
	
	public void setNewWord(String newWord) {
		this.newWord = newWord;
	}
	
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		JspFragment body = getJspBody();
		StringWriter writer = new StringWriter();
		body.invoke(writer);
		String str = writer.toString();
		String newStr = str.replaceAll(oldWord, newWord);
		out.print(newStr);
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/ReplaceTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar ReplaceTag.java -encoding UTF8 
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar ReplaceTag.java -encoding UTF8 

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	...
	<tag>
		<name>replace</name>
		<tag-class>tool.ReplaceTag</tag-class>
		<body-content>scriptless</body-content>
		<attribute>
			<name>oldWord</name>
			<type>java.lang.String</type>
		</attribute>
		<attribute>
			<name>newWord</name>
			<type>java.lang.String</type>
		</attribute>
	</tag>
</taglib>
```

#### Song.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<tool:replace oldWord="비행기" newWord="제트기">
				떴다 떴다 비행기 날아라 날아라<br>
				높이 높이 날아라 우리 비행기<br>
		</tool:replace>
	</body>
</html>
```

### 변수를 지원하는 커스텀 액션을 만드는 태그 클래스
- 태그 파일의 경우에는 variable 지시자를 이용해서 변수를 선언해야 하지만, 태그 클래스에서는 그런 선언을 할 필요가 없습니다.
- 태그 클래스에서는 단지 해당 변수의 이름과 값을 page 데이터 영역에 저장해 놓기만 하면 됩니다. 그렇게 하면 JSP 페이지에서는 그 데이터를 변수로 인식하고 사용할 수 있습니다.
- 태그 클래스 안에서 page 데이터 영역에 데이터를 저장하려면 JSP 페이지의 컨텍스트 정보를 표현하는 JspContext객체를 이용해서 할 수 있습니다. 
- 이 객체는 태그 클래스의 상위 클래스인 SimpleTagSupport의 getJspContext 메서드를 호출하면 구할 수 있는데, 이 메서드의 호출 방법은 다음과 같습니다.

```
JspContext context = getJspContext();
```
- context : page 데이터 영역에 데이터를 저장할 수 있는 객체
- 이렇게 JspContext 객체를 구한 다음에 이 객체에 setAttribute 메서드를 호출하면 page 데이터 영역에 변수의 이름과 값을 저장할 수 있습니다.
```
context.setAttribute("minimum", num1);
```

#### src/main/java/tool/MinimumTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class MinimumTag extends SimpleTagSupport {
	private int num1, num2;
	
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
			if (num1 < num2)  
				context.setAttribute("minimum", num1);
			else 
				context.setAttribute("minimum", num2);
	}
}
```

- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/MinimumTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar MinimumTag.java -encoding UTF8 
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar MinimumTag.java -encoding UTF8 

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	... 
	<tag>
		<name>min</name>
		<tag-class>tool.MinimumTag</tag-class>
		<body-content>empty</body-content>
		<attribute>
			<name>num1</name>
			<type>java.lang.Integer</type>
			<rtexprvalue>true</rtexprvalue>
		</attribute>
		<attribute>
			<name>num2</name>
			<type>java.lang.Integer</type>
			<rtexprvalue>true</rtexprvalue>
		</attribute>
		<variable>
			<name-given>minimum</name-given>
			<variable-class>java.lang.Integer</variable-class>
			<scope>AT_END</scope>
		</variable>
	</tag>
</taglib>
```
- \<attribute\> 요소 안에 \<rtexprvalue\>라는 하위 요소가 있고 그 안에 true라는 값이 써 있습니다. 
- \<rtexprvalue\> 요소는 커스텀 액션의 애트리뷰트 값에 익스프레션 EL 식을 포함시킬 수 있는지 여부를 결정하는 역할을 하는데, 예제에서는 num1과 num2 애트리뷰트 EL 식을 쓸 것이기 때문에 true라고 써야 합니다.

#### Minimum.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<tool:min num1="1000" num2="2000" />
		최소값 : ${minimum}
	</body>
</html>
```

- 지금까지 만든 커스텀 액션은 한 가지 단점이 있는데, 변수의 이름이 고정되어 있다는 단점입니다.
- 이런 단점을 제거하기 위해서는 다음과 같이 커스텀 액션에서 애트리뷰트를 이용해서 변수의 이름을 지정할 수 있도록 만드는 것이 좋습니다.
```
<tool:min var="minimum" num1="12" num2="34" />
```
- minimum : 변수의 이름
- 이런 식으로 작동하는 커스텀 액션을 만들기 위해서는 태그 클래스를 작성할 때 변수 이름을 담을 애트리뷰트를 위한 set메서드를 선언해야 합니다.

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	...
	<tag>
		<name>newMin</name>
		<tag-class>tool.NewMinimumTag</tag-class>
		<body-content>empty</body-content>
		<attribute>
			<name>num1</name>
			<type>java.lang.Integer</type>
			<rtexprvalue>true</rtexprvalue>
		</attribute>
		<attribute>
			<name>num2</name>
			<type>java.lang.Integer</type>
			<rtexprvalue>true</rtexprvalue>
		</attribute>
		<attribute>
			<name>var</name>
			<type>java.lang.String</type>
			<required>true</required>
			<rtexprvalue>false</rtexprvalue>
		</attribute>
		<variable>
			<name-from-attribute>var</name-from-attribute>
			<variable-class>java.lang.Integer</variable-class>
			<scope>AT_END</scope>
		</variable>
	</tag>
</taglib>
```

#### NewMinimum.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<tool:newMin var="MIN" num1="1000" num2="2000" />
	</body>
</html>
```

### 차일드 커스텀 액션
- 커스텀 액션 안에 또 다른 커스텀 액션이 포함되도록 만드는 방법
```
<tool:list>
	<tool:item>오렌지 쥬스</tool:item>
	<tool:item>키위 스무디</tool:item>
	<tool:item>딸기 아이스크림</tool:item>
</tool:list>
```
- 위와 같이 작동하는 커스텀 액션을 만들기 위해서는 두 개의 태그 클래스를 작성해야 합니다. 
- 하나는 바깥쪽에 있는 \<tool:list\> 커스텀 액션을 위한 태그 클래스이고, 다른 하나는 안쪽에 있는 \<tool:item\>커스텀 액션을 위한 태그 클래스입니다.(이후 바깥쪽 커스텀 액션을 부모 커스텀 액션, 안쪽 커스텀 액션을 자식 커스텀 액션 또는 차일드 커스텀 액션이라고 부르겠습니다).

#### 부모 커스텀 액션을 구현하는 태그 클래스

#### src/main/java/tool/ListTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ListTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspFragment body = getJspBody();
		body.invoke(null);
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/ListTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar ListTag.java -encoding UTF8 
- 예) javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar ListTag.java -encoding UTF8 


#### 자식 커스텀 액션을 구현하는 태그 클래스
- 자식 커스텀 액션이 해야 할 일을 코딩하는 방법은 일반 커스텀 액션과 비슷하지만, 추가로 해야 할 일이 있습니다. 즉, 자식 커스텀 액션이 올바른 부모 커스텀 액션 안에서 사용되고 있는지 확인하는 것 입니다.
- 태그 클래스가 상위 클래스인 SimpleTagSupport로 부터 상속받은 getParent 메서드를 이용하면 됩니다. 이 메서드는 부모 커스텀 액션 태그 클래스 객체를 반환하기 때문입니다.

```
JspTag parent = getParent();
```
- getParent() 메서드가 리턴하는 객체의 타입은 자바 연산자인 instanceof를 사용해서 확인할 수 있습니다. 이때 주의할 점은 부모 커스텀 액션이 아예 없을 경우를 대비하여 getParent 메서드의 반환값이 null인지 부터 확인해야 합니다. 
- 그리고 올바른 부모 커스텀 액션이 아님을 확인했을 떄는 다음 예에서 볼 수 있는 것처럼 예외 객체를 만들어서 던저야 합니다. 이 예외 객체는 doTag 메서드 밖으로 던져져서 웹 컨테이너에 의해 처리되어야 하므로 doTag 메서드의 throws절에 명시된 JspException 타입으로 만들어야 합니다.
```
 if ((parent == null) || !(parent instanceof ListTag))
	throw new JspException("잘못된 부모 커스텀 액션입니다.");
```
#### src/main/java/tool/ItemTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ItemTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspTag parent = getParent();
		if (parent == null || !(parent instanceof ListTag)) throw new JspException("The Parent is not ListTag");
		
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		JspFragment body = getJspBody();
		out.print("-");
		body.invoke(null);
		out.println("<br>");
	}
}
```
- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/ListTag.class, WEB-INF/classes/tool/ItemTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar ListTag.java ItemTag.java -encoding UTF8 
D:\Spring\jspWeb1\src\main\java\tool>javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar ListTag.java ItemTag.java -encoding UTF8

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	...
	<tag>
		<name>list</name>
		<tag-class>tool.ListTag</tag-class>
		<body-content>scriptless</body-content>
	</tag>
	<tag>
		<name>item</name>
		<tag-class>tool.ItemTag</tag-class>
		<body-content>scriptless</body-content>
	</tag>
</taglib>
```

#### FruitShop.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<h3>오늘의 메뉴</h3>
		<tool:list>
			<tool:item>오렌지 쥬스</tool:item>
			<tool:item>키위 스무디</tool:item>
			<tool:item>딸기 아이스크림</tool:item>
		</tool:list>
	</body>
</html>
```

#### 부모 커스텀 액션과 자식 커스텀 액션간의 커뮤니케이션의 예
- 부모 커스텀 액션과 자식 커스텀 액션이 정보를 주고 받는 좀 더 복잡한 커스텀 액션을 만들어 보겠습니다.

```
<tool:newList bullet="@">
	<tool:newItem>얼그레이</tool:newItem>
	<tool:newItem>아쌈</tool:newItem>
	<tool:newItem>잉글리쉬 브렉퍼스트</tool:newItem>
</tool:newList>
```

#### src/main/java/tool/NewListTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class NewListTag extends SimpleTagSupport {
	private char bullet;
	
	public void setBullet(char bullet) {
		this.bullet = bullet;
	}
	
	public char getBullet() {
		return bullet;
	}
	
	public void doTag() throws JspException, IOException {
		JspFragment body = getJspBody();
		body.invoke(null);
	}
}
```

#### src/main/java/tool/NewItemTag.java
```
package tool;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class NewItemTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspTag parent = getParent();
		if (parent == null || !(parent instanceof NewListTag) )
			throw new JspException("The Parent is not NewListTag");
		
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		JspFragment body = getJspBody();
		char bullet = ((NewListTag) parent).getBullet();
		out.print(bullet);
		body.invoke(null);
		out.println("<br>");
	}
}
```

- 컴파일을 수동으로 진행 한 후 WEB-INF/classes/tool/NewListTag.class, WEB-INF/classes/tool/NewItemTag.class 파일을 복사한다.
- javac -cp 톰캣 경로\lib\jsp-api.jar NewListTag.java NewItemTag.java -encoding UTF8 
- javac -cp D:\tomcat\apache-tomcat-9.0.53\lib\jsp-api.jar NewListTag.java NewItemTag.java -encoding UTF8

#### WEB-INF/tlds/tools.tld
```
<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	...
	<tag>
		<name>newList</name>
		<tag-class>tool.NewListTag</tag-class>
		<body-content>scriptless</body-content>
		<attribute>
			<name>bullet</name>
			<type>java.lang.Character</type>
		</attribute>
	</tag>
	<tag>
		<name>newItem</name>
		<tag-class>tool.NewItemTag</tag-class>
		<body-content>scriptless</body-content>
	</tag>
</taglib>
```
#### TeaShop.jsp
```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tool" uri="/taglibs/tools.tld" %>
<html>
	<body>
		<h3>오늘의 메뉴</h3>
		<tool:newList bullet="@">
			<tool:newItem>얼그레이</tool:newItem>
			<tool:newItem>아쌈</tool:newItem>
			<tool:newItem>잉글리쉬 브렉퍼스트</tool:newItem>
		</tool:newList>
	</body>
</html>
```
## 태그 라이브러리를 만드는 방법

### 태그 클래스를 모아서 태그 라이브러리를 만드는 방법

1. 디렉토리 계층 구조를 만들고 파일들을 그곳에 저장합니다.
	- JSP 규격서에 따르면 태그 라이브러리를 구성하는 파일들은 일정한 규칙에 따라 만들어진 디렉토리 계층구조에 저장해야 합니다. 이를 위해서는 작업용 디렉토리가 필요합니다. <br>( 예 - D:\tl-work1)
	- 그 다음에는 이 작업용 디렉토리 아래에 META-INF라는 디렉토리를 만들어야 합니다. 이 디렉토리에는 클래스파일을 제외한 나머지 파일들을 저장해야 합니다. 
	- 그리고 태그 클래스의 컴파일 결과물들은 META-INF 디렉토리와 같은 수준으로 작업용 디렉토리에 저장해야 합니다.
	- 이때 주의할 점은 클래스가 속한 패키지 디렉토리 구조 전체를 그대로 복사해야 합니다.
	
	![태그라이브러리 만들기1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/taglib1.png)
	
2. TLD 파일을 수정합니다.
	- TLD 파일을 열고 \<short-name\> 요소 다음 위치에 \<uri\>라는 요소를 추가하고 그 안에 TLD 파일의 URI를 써 넣으면 됩니다.
	#### tl-work1/META-INF/tools.tld
	```
	<?xml version="1.0" encoding="UTF-8"?>
	<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
		<tlib-version>1.0</tlib-version>
		<short-name>tool</short-name>
		<uri>/taglibs/tools.tld</uri>
		<tag>
			<name>starLine</name>
			<tag-class>tool.StarLineTag</tag-class>
			<body-content>empty</body-content>
		</tag>
		<tag>
			<name>newLine</name>
			<tag-class>tool.NewLineTag</tag-class>
			<body-content>empty</body-content>
			<attribute>
				<name>size</name>
				<type>java.lang.Integer</type>
			</attribute>
			<attribute>
				<name>color</name>
				<type>java.lang.String</type>
			</attribute>
		</tag>
		<tag>
			<name>newerLine</name>
			<tag-class>tool.NewerLineTag</tag-class>
			<body-content>empty</body-content>
			<dynamic-attributes>true</dynamic-attributes>
		</tag>
		<tag>
			<name>box</name>
			<tag-class>tool.BoxTag</tag-class>
			<body-content>scriptless</body-content>
		</tag>
		<tag>
			<name>replace</name>
			<tag-class>tool.ReplaceTag</tag-class>
			<body-content>scriptless</body-content>
			<attribute>
				<name>oldWord</name>
				<type>java.lang.String</type>
			</attribute>
			<attribute>
				<name>newWord</name>
				<type>java.lang.String</type>
			</attribute>
		</tag>
		<tag>
			<name>min</name>
			<tag-class>tool.MinimumTag</tag-class>
			<body-content>empty</body-content>
			<attribute>
				<name>num1</name>
				<type>java.lang.Integer</type>
				<rtexprvalue>true</rtexprvalue>
			</attribute>
			<attribute>
				<name>num2</name>
				<type>java.lang.Integer</type>
				<rtexprvalue>true</rtexprvalue>
			</attribute>
			<variable>
				<name-given>minimum</name-given>
				<variable-class>java.lang.Integer</variable-class>
				<scope>AT_END</scope>
			</variable>
		</tag>
		<tag>
			<name>newMin</name>
			<tag-class>tool.NewMinimumTag</tag-class>
			<body-content>empty</body-content>
			<attribute>
				<name>num1</name>
				<type>java.lang.Integer</type>
				<rtexprvalue>true</rtexprvalue>
			</attribute>
			<attribute>
				<name>num2</name>
				<type>java.lang.Integer</type>
				<rtexprvalue>true</rtexprvalue>
			</attribute>
			<attribute>
				<name>var</name>
				<type>java.lang.String</type>
				<required>true</required>
				<rtexprvalue>false</rtexprvalue>
			</attribute>
			<variable>
				<name-from-attribute>var</name-from-attribute>
				<variable-class>java.lang.Integer</variable-class>
				<scope>AT_END</scope>
			</variable>
		</tag>
		<tag>
			<name>list</name>
			<tag-class>tool.ListTag</tag-class>
			<body-content>scriptless</body-content>
		</tag>
		<tag>
			<name>item</name>
			<tag-class>tool.ItemTag</tag-class>
			<body-content>scriptless</body-content>
		</tag>
		<tag>
			<name>newList</name>
			<tag-class>tool.NewListTag</tag-class>
			<body-content>scriptless</body-content>
			<attribute>
				<name>bullet</name>
				<type>java.lang.Character</type>
			</attribute>
		</tag>
		<tag>
			<name>newItem</name>
			<tag-class>tool.NewItemTag</tag-class>
			<body-content>scriptless</body-content>
		</tag>
	</taglib>
	```
3. 디렉토리 계층 구조 전체를 JAR 파일로 만듭니다.
```
jar cvf0 tool.jar *
```
- cvf0 : c는 새로운 JAR 파일을 생성하라는 의미이고, v는 JAR 파일에 들어가는 파일들의 목록을 보여달라는 의미, f는 옵션 다음에 생성할 JAR 파일의 이름이 온다는 의미 입니다. 그리고 마지막에 있는 숫자 0은 jar명령이 기본적으로 수행하는 ZIP 압축 알고리즘을 사용하지 말라는 의미입니다.
- tool.jar : 생성한 JAR 파일의 이름
- \* : JAR 파일에 들어갈 파일들

- src/webapp/WEB-INF/lib경로에 생성된 tool.jar 복사

### 태그 파일을 모아서 태그 라이브러리를 만드는 방법 
1. 디렉토리 계층 구조를 만들고 파일들을 그곳에 저장합니다.
	-  작업용 디렉토리를 하나 만듭니다.(예 - D:\tl-work2)
	
		![taglib2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/taglib2.png)
		
	- 그런 후에 작성했던 태그 파일들을 이 작업용 디렉토리로 복사해야 합니다. 태그 파일은 META-INF 디렉토리 아래 tags 서브 디렉토리나 그 아래의 서브디렉토리에 저장해야 합니다.
	
		![taglib3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/taglib3.png)
	
2. TLD 파일을 생성합니다.
	- META-INF에 TLD 파일을 생성합니다. 다만 태그파일을 TLD 파일에 등록하는 방법은 태그 클래스의 경우와 상당히 다릅니다.
	- 태그 클래스의 경우에는 각각의 커스텀 액션을 위해 \<tag\> 요소를 기술해야 했지만 태그 파일의 경우에는 각각의 커스텀 액션을 위해 \<tag-file\>이라는 요소를 기술해야 합니다.
	- 그리고 요소 안네 \<name\>과 \<path\>라는 하위 요소를 쓰고, 그 안에 커스텀 액션의 이름과 태그 파일명의 경로명을 각각 기술해야 합니다.
	- 이 경로명은 최상위 디렉토리를 기준으로 한 셩로명으로 기술해야 하고 슬래시(/)로 시작해야 합니다.
	
	```
	<tag-file>
		<name>line</name>
		<path>/META-INF/tags/line.tag</path>
	</tag-file>
	```
	
#### META-INF/util.tld
```
<taglib xmlns="http://java.sun.com/xml/ns/javaee" version="2.4">
	<tlib-version>1.0</tlib-version>
	<short-name>util</short-name>
	<uri>/taglibs/util.tld</uri>
	<tag-file>
		<name>newLine</name>
		<path>/META-INF/tags/util/newLine.tag</path>
	</tag-file>
	<tag-file>
		<name>doubleLine</name>
		<path>/META-INF/tags/util/doubleLine.tag</path>
	</tag-file>
	<tag-file>
		<name>box</name>
		<path>/META-INF/tags/util/box.tag</path>
	</tag-file>
	<tag-file>
		<name>max</name>
		<path>/META-INF/tags/util/max.tag</path>
	</tag-file>
	<tag-file>
		<name>newMax</name>
		<path>/META-INF/tags/util/newMax.tag</path>
	</tag-file>
	<tag-file>
		<name>compute</name>
		<path>/META-INF/tags/util/compute.tag</path>
	</tag-file>
</taglib>
```

3. 디렉토리 계층 구조 전체를 JAR 파일로 만듭니다.
```
jar cvf0 util.jar *
```

4. 생성된 util.jar 파일을 WEB-INF/lib 디렉토리에 복사합니다.

```
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="util" uri="/taglibs/util.tld" %>
<html>
	<body>
		<util:box>
			커스텀 액션 만들기 학습
		</util:box>
	</body>
</html>
```

## 커스텀 액션 태그를 이용하여 레이아웃 구성하기

- 간단한 Wrapper 태그 만들기
#### WEB-INF/tags/wrapper.tag
```
<%@ tag description="간단한 wrapper 태그" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<body>
		<jsp:doBody /> <%-- doBody 태그를 사용하면 태그파일 내에 attribute로 선언하지 않아도 된다  --%>
	</body>
</html>
```

#### webapp/simple_wrapper.jsp 
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:wrapper>
	<h1>간단한 Wrapper 태그 만들기</h1>
</layout:wrapper>
```
- 실행 결과

![layout1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/layout1.png)


- 헤더와 푸터, 내용본문을 일부로서 추가할 수 있는 레이아웃 구성

#### WEB-INF/tags/mainLayout.tag
```
<%@tag description="메인 레이아웃" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer"  fragment="true" %>
<!DOCTYPE html>
<html>
	<head>
		<title>사이트 제목...</title>
	</head>
	<body>
		<header>
			<jsp:invoke fragment="header" />
		</header>
		<main>
			<jsp:doBody />
		</main>
		<footer>
			<jsp:invoke fragment="footer" />
		</footer>
	</body>
</html>
```

#### layout1.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:mainLayout>
	<jsp:attribute name="header">
		<h1>헤더</h1>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<h1>하단</h1>
	</jsp:attribute>
	<jsp:body>
		<h1>본문</h1>
	</jsp:body>	
</layout:mainLayout>
```

- 실행 결과

![layout2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/layout2.png)


- 레이아웃 태그를 이용하여 새로운 레이아웃 만들기

#### WEB-INF/tags/subLayout.tag
```
<%@tag description="서브 페이지" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="userNm" required="true" %><%-- 태그 속성 선언 --%> 
<t:mainLayout>
	<jsp:attribute name="header">
		<h1>${userNm}님 로그인 하셨습니다.</h1>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<p>Copyright ...</p>
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</t:mainLayout>
```

#### layout2.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:subLayout userNm="김이름">
	<h1>본문</h1>
</layout:subLayout>
```

- 실행 결과

![layout3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%BB%A4%EC%8A%A4%ED%85%80%EC%95%A1%EC%85%98/images/layout3.png)

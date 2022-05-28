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



## 태그 클래스를 이용해서 커스텀 액션 만들기

## 태그 라이브러리를 만드는 방법

## 커스텀 액션 태그를 이용하여 레이아웃 구성하기
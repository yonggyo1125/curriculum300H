## 컨트롤러 구성

#### WEB-INF/web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	<servlet>
		<servlet-name>Index</servlet-name>
		<servlet-class>kr.codefty.board.controller.IndexController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>Index</servlet-name>
		<url-pattern>/index.jsp</url-pattern>
	</servlet-mapping>
</web-app>
```

#### src/main/java/kr/codefty/board/controller/IndexController.java
```
package kr.codefty.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

## 레이아웃 구성

#### WEB-INF/tags/layouts/common.tag
- 모든 사이트의 공통이 되는 레이아웃을 구성 
```
<%@tag description="공통 레이아웃" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8' />
		<link rel="stylesheet" type="text/css" href="<c:url value='/public/css/style.css' />" />
		 <script src="<c:url  value='/public/js/common.js' />"></script>
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

#### WEB-INF/tags/layouts/main.tag
- 메인페이지 및 주요 레이아웃의 구성
- 공통 레이아웃(common.tag)을 사용하여 그 하위에 구성 
- 로고 및 메뉴 영역, footer 영역이 고정되며, 본문 형태만 유동적으로 변경되게 구성 
```
<%@tag description="메인 레이아웃" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:common>
	<jsp:attribute name="header">
		<h1>로고</h1>
		<nav>
			<a href='#'>메뉴1</a>
			<a href='#'>메뉴2</a>
			<a href='#'>메뉴3</a>
		</nav>
	</jsp:attribute>
	<jsp:attribute name="footer" >
		COPYRIGHT codefty.kr ALL RIGHT RESERVED.
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</layout:common>
```

#### webapp/main.jsp
```
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:main>
	<h1>본문영역</h1>
</layout:main>
```

- 실행 결과

![layout](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9B%B9MVC%20%2B%20%EC%BB%A4%EC%8A%A4%ED%85%80%20%EC%95%A1%EC%85%98%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EA%B5%AC%EC%84%B1/images/layout.png)


## 게시판 프로젝트 소스(레이아웃 구성)
- [예제 소스](https://github.com/yonggyo1125/Board_JSP/tree/layout)

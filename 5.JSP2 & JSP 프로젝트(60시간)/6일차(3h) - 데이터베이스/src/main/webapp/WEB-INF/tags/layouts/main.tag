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


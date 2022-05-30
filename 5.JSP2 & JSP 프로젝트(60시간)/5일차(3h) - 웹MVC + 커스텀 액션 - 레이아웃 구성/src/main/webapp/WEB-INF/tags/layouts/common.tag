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
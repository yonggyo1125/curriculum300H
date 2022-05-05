<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="bean" class="day03.com.dao.Calculator" />
	<%
		int m = bean.process(5);
		out.print("5의 3제곱 :  " + m);
	%>
</body>
</html>

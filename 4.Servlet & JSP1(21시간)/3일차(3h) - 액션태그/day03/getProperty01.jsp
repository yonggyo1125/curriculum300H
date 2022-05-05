<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<p>	아이디 : <jsp:getProperty name="person" property="id" />
	<p>	이 름 : <jsp:getProperty name="person" property="name" />
</body>
</html>
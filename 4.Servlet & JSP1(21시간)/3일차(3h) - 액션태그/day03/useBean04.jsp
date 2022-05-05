<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="person" class="day03.com.dao.Person" scope="request" />
	<p>	아이디 : <%=person.getId()%>
	<p>	이 름 : <%=person.getName()%>
		<%
			person.setId(20182005);
			person.setName("홍길동");
		%>
		<jsp:include page="useBean03.jsp"/>
</body>
</html>

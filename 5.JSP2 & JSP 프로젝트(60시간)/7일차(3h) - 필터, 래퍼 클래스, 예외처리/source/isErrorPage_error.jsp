<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>오류가 발생하였습니다.
	<p>	예외 유형 :	<%=exception.getClass().getName()%>
	<p>	오류 메시지 : <%=exception.getMessage()%>
</body>
</html>

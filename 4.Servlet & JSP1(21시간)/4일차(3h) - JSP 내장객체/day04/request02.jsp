<%@page import="java.util.Enumeration"%>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
	<%
		Enumeration en = request.getHeaderNames();
		while (en.hasMoreElements()) {
			String headerName = (String) en.nextElement();
			String headerValue = request.getHeader(headerName);
	%>
	<%=headerName%> : <%=headerValue%><br>
	<%
		}
	%>
</body>
</html>
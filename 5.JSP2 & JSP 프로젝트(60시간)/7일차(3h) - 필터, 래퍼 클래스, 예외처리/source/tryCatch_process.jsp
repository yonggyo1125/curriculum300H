<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>	   
<%
	try {
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
	} catch (NumberFormatException e) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("tryCatch_error.jsp");
		dispatcher.forward(request, response);
	}
%>
</body>
</html>
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!int sum(int a, int b) {
		return a + b;
	}%>
	<%
		out.println("2 + 3 = " + sum(2, 3));
	%>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>Session</title>
</head>
<body>
	<p><h4>----- 세션을 삭제하기 전 -----</h4>
	<%
		String user_id = (String) session.getAttribute("userID");
		String user_pw = (String) session.getAttribute("userPW");
		out.println("설정된 세션 이름 userID : " + user_id + "<br>");
		out.println("설정된 세션 값 userPW : " + user_pw + "<br>");

		session.removeAttribute("userID");
	%>
	<p><h4>----- 세션을 삭제한 후 -----</h4>
	<%
		user_id = (String) session.getAttribute("userID");
		user_pw = (String) session.getAttribute("userPW");
		out.println("설정된 세션 이름 userID : " + user_id + "<br>");
		out.println("설정된 세션 값 userPW : " + user_pw + "<br>");
	%>
</body>
</html>
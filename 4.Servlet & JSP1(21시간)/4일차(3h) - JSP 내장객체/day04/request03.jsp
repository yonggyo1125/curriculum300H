<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
   <title> Implicit Objects </title> 
</head>
<body>
   <p>클라이언트 IP : <%=request.getRemoteAddr() %>
   <p>요청 정보 길이 : <%=request.getContentLength() %>
   <p>요청 정보 인코딩 : <%=request.getCharacterEncoding() %>
   <p>요청 정보 콘텐트 유형 : <%=request.getContentType() %>
   <p>요청 정보 프로토콜 : <%=request.getProtocol() %>
   <p>요청 정보 전송방식 : <%=request.getMethod() %>
   <p>요청 URI : <%=request.getRequestURI() %>
   <p>컨텍스트 경로 : <%=request.getContextPath() %>
   <p>서버 이름  : <%=request.getServerName() %>
   <p>서버 포트  : <%=request.getServerPort() %>
   <p>쿼리문  : <%=request.getQueryString() %>
</body>
</html>
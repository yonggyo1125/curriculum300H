# JDBC로 데이터베이스와 JSP 연동
- JDBC(Java DataBase Connectivity)는 자바/JSP 프로그램 내에서 데이터베이스와 관련된 작업을 처리할 수 있도록 도와주는 자바 표준 인터페이스로, 관계형 데이터베이스 시스템에 접근하여 SQL문을 실행하기 위한 자바 API 또는 자바 라이브러리입니다. JDBC API를 사용하면 DBMS 종류에 상관없이 데이터베이스 작업을 처리할 수 있습니다.
- JDBC API는 java.sql.\* 패키지에 의해 구현됩니다.

## MySQL JDBC 드라이버 설치
- [MySQL Connector/J 다운로드](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.29)

![MySQL Connector](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/mysql_connector.png)

- src/webapps/WEB-INF/lib 폴더에 다운받은 jar 파일을 복사해 넣기 


## JDBC를 사용한 JSP와 데이터베이스의 연동은 다음과 같은 단계로 프로그래밍 됩니다.
1. java.sql.\* 패키지 임포트
2. JDBC 드라이버 로딩
3. 데이터베이스 접속을 위한 Connection객체 생성
4. 쿼리문을 실행하기 위한 Statement/PreparedStatement/CallableStatement 객체 생성
5. 쿼리 실행
6. 쿼리 실행 결과 값(int, ResultSet) 사용
7. 사용된 객체(ResultSet, Statement/PreparedStatement/CallableStatement, Connection) 종료

## JDBC 드라이버 로딩 및 DBMS 접속

### JDBC 드라이버 로딩하기
- Class.forName() 메서드를 이용하여 JDBC 드라이버를 로딩합니다.
- JDBC 드라이버가 로딩이 되면 자동으로 객체가 생성되고 DriverManager 클래스에 등록됩니다.

```
Class.forName(String className);
```

- MySQL 드라이버 로딩 예

```
<%
   try {
      Class.forName(“com.mysql.cj.jdbc.Driver”);
   } catch (SQLException ex) {
     // 예외 발생 처리
   }
%>
```

### Connection 객체 생성하기
- DriverManager 클래스의 getConnection() 메서드를 사용합니다.
```
static Connection getConnection(String url)
static Connection getConnection(String url, String user, String password)
static Connection getConnection(String url, Properties info)
```

- Connection 객체 생성 예 : getConnection(String url) 메서드 사용
```
<%
   Connection conn = null;
   try {
      Class.forName(“com.mysql.cj.jdbc.Driver”);
      conn = DriverManager.getConnection(“jdbc:mysql://localhost:3306/JSPBookDB&user=root&password=1234”);
   } catch (SQLException ex) {
      // 예외 발생 처리
   }
%>
```
- Connection 객체 생성 예 : getConnection(String url, Properties info) 메서드 사용
```
<%
   Connection conn = null;
   try {
      Class.forName(“com.mysql.cj.jdbc.Driver”);
      Properties props = new Properties();
      props.put(“user”, “root”);
      props.put(“password”, “1234”);
   conn = DriverManager.getConnection(“jdbc:mysql://localhost:3306/JSPBookDB”, props);
   } catch (SQLException ex) {
      // 예외 발생 처리
   }
%>
```

### 데이터베이스 연결 닫기
- 데이터베이스 연결이 더 이상 필요하지 않으면 데이터베이스와 JDBC 리소스가 자동으로 닫힐 때까지 대기하는 것이 아니라 close() 메소드로 생성한 Connection 객체를 해제합니다. 일반적으로 데이터베이스 리소스를 사용하지 않기 위해 사용을 끝내자마자 리소스를 해제하는 것이 좋습니다.
```
void close() throws SQLException
```
- 데이터베이스의 연결을 종료하는 예

```
<%
   Connection conn = null;
   try {
      // JDBC 드라이버 로딩
      // Connection 객체 생성 
   } catch (SQLException e) {      // 예외 발생 처리   } finally {
      if (conn != null) conn.close();
   }
%>
```

#### connection.jsp 
```
<%@ page contentType=“text/html; charset=utf-8” %>
<%@ page import=“java.sql.*” %>
<html>
<body>
<%
   Connction conn = null;
   try { 
      String url = “jdbc:mysql://localhost:3306/JSPBookDB”;
      String user = “root”;
      String password = “1234”;

      Class.forName(“com.mysql.cj.jdbc.Driver”);
      conn = DriverManager.getConnection(url, user, password);
      out.println(“데이터베이스 연결이 성공했습니다.”);
   } catch (SQLException ex) {
     out.println(“데이터베이스 연결 실패했습니다.<br>”);
     out.println(“SQLException: ” + ex.getMessage());
   } finally {
      if (conn != null)
          conn.close();
   }
%>
</body>
</html>
```

## 데이터베이스 쿼리 실행 
- Connection 객체를 생성하여 데이터베이스가 연결되었다면 쿼리 실행 객체를 이용하여 쿼리를 실행합니다.
- 쿼리 실행 객체는 Statement, PreparedStatement, CallableStatement입니다.
- 쿼리의 실행 성공이나 실패 여부와 상관없이 쿼리 실행 객체와 Connection 객체의 리소스를 해제해야 합니다.

### Statement 객체로 데이터 접근하기 
- 정적인 쿼리에 사용
- 하나의 쿼리를 사용하고 나면 더는 사용할 수 없습니다.
- 하나의 쿼리를 끝내면 close()를 사용하여 객체를 즉시 해제해야 합니다.
- close()를 사용하여 객체를 즉시 해제하지 않으면 무시할 수 없는 공간이 필요하며 페이지가 다른 작업을 수행하는 동안 멈추지 않기 때문입니다.
- Statement 객체는 복잡하지 않은 간단한 쿼리문을 사용하는 경우에 좋습니다.

```
Statement createStatement() throws SQLException
```
#### Statement 객체의 메서드 종류

|메서드|반환유형|설명|
|-----|----|----------|
|executeQuery(String sql)|ResultSet|SELECT 문을 실행할 때 사용합니다.(ResultSet 객체 반환)|
|executeUpdate(String sql)|int|삽입, 수정, 삭제와 관련된 SQL 문 실행에 사용합니다.|
|close()|void|Statement 객체를 반환할 때 사용합니다.|

- executeQuery() 메서드 사용 예 : SELECT 쿼리문 
```
<%
   Connection conn = null;
   ...(생략)...
   Statement stmt = conn.createStatement();
   String sql = “SELECT * FROM Member WHERE id = ‘1’”;
   ResultSet rs = stmt.executeQuery(sql);
  stmt.close();
%>
```
- executeUpdate() 메서드 사용 예(삽입) : INSERT 쿼리문
```
<%
   Connection conn = null;
   ... (생략) ... 
   Statement stmt = conn.createStatement();
   String sql = “INSERT INTO Member(id, name, passwd) VALUES (‘1’, ‘홍길순’, ‘1234’);
   int rs = stmt.executeUpdate(sql);
%>
```
#### source/member.sql
```
drop table member;

CREATE TABLE IF NOT EXISTS member(
   id VARCHAR(20) NOT NULL,
   passwd  VARCHAR(20),
   name VARCHAR(30),    
   PRIMARY KEY (id)
);

select * from member;
```

#### insert01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
	<form method="post" action="insert01_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">
		<p>	이름 : <input type="text" name="name">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### dbconn.jsp
```
<%@ page import="java.sql.*"%>
<%
	Connection conn = null;

	String url = "jdbc:mysql://localhost:3306/JSPBookDB";
	String user = "root";
	String password = "1234";

	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(url, user, password);
%>
```

#### insert01_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");

		Statement stmt = null;

		try {
			String sql = "INSERT INTO Member(id, passwd, name) VALUES('" + id + "','" + passwd + "', '" + name + "')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			out.println("Member 테이블 삽입이 성공했습니다.");
		} catch (SQLException ex) {
			out.println("Member 테이블 삽입이 실패했습니다.<br>");
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```

### PreparedStatement 객체로 데이터 접근하기
- 동적인 쿼리에 사용
- PreparedStatement 객체는 하나의 객체로 여러 번의 쿼리를 실행할 수 있으며, 동일한 쿼리문을 특정 값만 바꾸어서 여러 번 실행해야 할 때, 매개변수가 많아서 쿼리문을 정리해야 할 때 유용합니다.

```
PreparedStatement prepareStatement(String sql) throws SQLException
```
- 매개변수 sql은 데이터베이스에 보낼 쿼리문이며, 쿼리문에 정해지지 않은 값을 물음표(?)로 표시하여 사용합니다. 
- 이 물음표에 값을 할당하기 위해 setXXX() 메서드를 사용하는데, 이 메서드는 2개의 매개변수로 설정한 물음표 위치 값(1부터 시작함)과 실제 할당된 값을 가집니다.
- 이때 Xxx는 필드 데이터형으로, 해당 필드의 데이터형이 문자열이면 setString().이 되고 int이면 setInt()가 됩니다.

#### setXxx() 메서드의 종류

|메서드|반환 유형|설명|
|--------|----|-------|
|setString(int parameterIndex, String x)|void|필드 유형이 문자열인 경우|
|setInt(int parameterIndex, int x)|void |필드 유형이 정수형인 경우|
|setLong(int parameterIndex, long x)|void|필드 유형이 정수형인 경우|
|setDouble(int parameterIndex, double x)|void|필드 유형이 실수형인 경우|
|setFloat(int parameterIndex, float x)|void|필드 유형이 실수형인 경우|
|setObject(int parameterIndex, Object x)|void|필드 유형이 객체형인 경우|
|setDate(int parameterIndex, Date x)|void|필드 유형이 날짜형인 경우|
|seTimestamp(int parameterIndex, Timestamp x)|void|필드 유형이 시간형인 경우|

#### PreparedStatement 객체의 메서드 종류

|메서드|반환 유형|설명|
|-----|----|-------|
|executeQuery()|ResultSet|SELECT 문을 실행할 때 사용합니다(ResultSet 객체 반환)|
|executeUpdate()|int|삽입, 수정 삭제와 관련된 SQL 문 실행에 사용합니다.|
|close()|void|PreparedStatement 객체를 반환할 때 사용합니다.|

- executeQuery() 메서드 사용 예 : SELECT 쿼리문
```
<% 
   Connection conn = null;
   ... (생략) ...
   String sql = “SELECT * FROM Member WHERE id = ?”;
   PreparedStatement pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, “1”);
   ResultSet rs = pstmt.executeQuery(sql);
   ... (생략) ... 
   pstmt.close();
%>
```
- executeUpdate() 메서드 사용 예(삽입) : INSERT 쿼리문
```
<%
   Connection conn = null; 
   ... (생략) ...
   String sql = “INSERT INTO Member(id, name, passwd) VALUES (?,?,?)”;
   PreparedStatement pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, “1”);
   pstmt.setString(2, “홍길순”);
   pstmt.setString(3, “1234”);
   pstmt.executeUpdate();
   ... (생략) ...
   pstmt.close();
%>
```
#### source/insert02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>	
	<form method="post" action="insert02_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">
		<p>	이름 : <input type="text" name="name">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/insert02_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String name = request.getParameter("name");
	
	PreparedStatement pstmt = null;

	try {
		String sql = "insert into member(id, passwd, name) values(?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
		out.println("Member 테이블  삽입이 성공했습니다.");
	} catch (SQLException ex) {
		out.println("Member 테이블 삽입이 실패했습니다.<br>");
		out.println("SQLException: " + ex.getMessage());
	} finally {
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
%>
</body>
</html>
```

## 쿼리문 실행 결과 값 가져오기 
- SELECT 쿼리문 실행 시 executeQuery() 메서드를 사용하면 실행 결과가 java.sql.ResultSet 형으로 반환
- ResultSet 객체는 Statement 또는 PreparedStatement 객체로 SELECT 문을 사용하여 얻어온 레코드 값을 테이블 형태로 가진 객체입니다.
- Statement 객체를 사용하는 경우 
```
ResultSet executeQuery(String sql) throws SQLException
```
- PreparedStatement 객체를 사용하는 경우
```
ResultSet executeQuery() throws SQLException
```
- ResultSet 객체는 SELECT문으로 필드값을 가져오기 위해 getXxx() 메서드를 사용합니다.
- Xxx는 필드의 데이터형과 관련이 있습니다.
- 해당 필드의 데이터형이 문자열이면 getString()이 되고 int이면 getInt()가 됩니다. 
- ResultSet 객체의 getXxx() 메서드를 사용하여 필드 순번으로 필드 값을 가져온다면 첫 번째는 1부터 시작합니다.

#### ResultSet 객체의 메서드 

|메서드|반환 유형|설명|
|-----|----|-------|
|getXxx(int ColumnIndex)|XXX|설정한 ColumnIndex(필드 순번)의 필드 값을 설정한 XXX 형으로 가져옵니다.|
|getXxx(String ColumnName)|XXX|설정한 ColumnName(필드 명)의 필드 값을 설정한 XXX 형으로 가져옵니다.|
|absolute(int row)|boolean|설정한 row 행으로 커서를 이동합니다.|
|beforeFirst()|void|첫 번째 행의 이전으로 커서를 이동합니다.|
|afterLast()|void|마지막 행의 다음으로 커서를 이동합니다.|
|first()|void|첫 번째의 행으로 커서를 이동합니다.|
|last()|void|마지막 행으로 커서를 이동합니다.|
|next()|boolean|다음 행으로 커서를 이동합니다.|
|previous()|boolean|현재 행의 이전 행으로 커서를 이동합니다.|
|close()|void|ResultSet 객체를 반환할 때 사용합니다.|

- executeQuery() 메서드 사용 예 : SELECT 쿼리문
``
<%
   Connection conn = null;
   ... (생략) ...
   Statement stmt = conn.createStatement();
   String sql = “SELECT * FROM Member WHERE id = 1”;
   ResultSet rs = stmt.executeQuery(sql);

   while(rs.next()) {
       out.println(rs.getString(2) + “, ” + rs.getString(3) + “<br>”);
   }
   rs.close();
   stmt.close();
%>
``
#### source/select01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>                   
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
	<table width="300" border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
		</tr>
		<%
			ResultSet rs = null;
			Statement stmt = null;

			try {
				String sql = "select * from member";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("passwd");
					String name = rs.getString("name");
		%>
		<tr>
			<td><%=id%></td>
			<td><%=pw%></td>
			<td><%=name%></td>
		</tr>
		<%
				}
			} catch (SQLException ex) {
				out.println("Member 테이블 호출이 실패했습니다.<br>");
				out.println("SQLException: " + ex.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
		%>
	</table>
</body>
</html>
```

#### source/select02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>                   
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>				
	<table width="300" border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
		</tr>
		<%
			ResultSet rs = null;			
			PreparedStatement pstmt = null;

			try {
				String sql = "select * from member";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("passwd");
					String name = rs.getString("name");
		%>
		<tr>
			<td><%=id%></td>
			<td><%=pw%></td>
			<td><%=name%></td>
		</tr>
		<%
				}
			} catch (SQLException ex) {
				out.println("Member 테이블 호출이 실패했습니다.<br>");
				out.println("SQLException: " + ex.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
		%>
	</table>
</body>
</html>
```

#### source/update01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>	
	<form method="post" action="update01_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">
		<p>	이름 : <input type="text" name="name">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/update01_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		
		ResultSet rs = null;
		Statement stmt = null;
		
		try {			
			String sql = "select id, passwd from member where id = '" + id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");

				if (id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "update member set name = '" + name + "' where id = '" + id + "'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					out.println("Member 테이블을 수정했습니다.");
				} else
					out.println("일치하는 비밀번호가 아닙니다");
			} else
				out.println("Member 테이블에 일치하는 아이디가 없습니다.");
		} catch (SQLException ex) {
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```

#### source/update02.jsp 
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>	
	<form method="post" action="update02_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">
		<p>	이름 : <input type="text" name="name">
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/update02_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
	<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		try {
			String sql = "select id, passwd from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");

				if (id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "update member set name = ? where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
					out.println("Member 테이블을 수정했습니다.");
				} else
					out.println("일치하는 비밀번호가 아닙니다");
			} else
				out.println("Member 테이블에 일치하는 아이디가 없습니다.");
		} catch (SQLException ex) {
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```

#### source/delete01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>	
	<form method="post" action="delete01_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">		
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/delete01_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");

		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			String sql = "select id, passwd from member where id = '" + id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");

				if (id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "delete from member where id = '"+ id +"' and passwd = '"+ passwd + "'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					out.println("Member 테이블을 삭제했습니다.");
				} else
					out.println("일치하는 비밀번호가 아닙니다");
			} else
				out.println("Member 테이블에 일치하는 아이디가 없습니다.");
		} catch (SQLException ex) {
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```

#### source/delete02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>	
	<form method="post" action="delete02_process.jsp">
		<p>	아이디 : <input type="text" name="id">
		<p>	비밀번호 : <input type="password" name="passwd">		
		<p>	<input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/delete02_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "select id, passwd from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");

				if (id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "delete from member where id = ? and passwd = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, passwd);
					pstmt.executeUpdate();
					out.println("Member 테이블을 삭제했습니다.");
				} else
					out.println("일치하는 비밀번호가 아닙니다");
			} else
				out.println("Member 테이블에 일치하는 아이디가 없습니다.");
		} catch (SQLException ex) {
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```
* * * 
# 데이터베이스 커넥션 풀 설치 및 적용
- 웹사이트에 접속하는 사용자 수가 아주 많으면 웹 서버 상에서 동시에 실행되는 웹 애플리케이션 프로그램의 수도 아주 많을 것이고, 그런 프로그램들이 동시에 같은 데이터베이스 연결을 시도하는 경우도 많을 것 입니다. 
- 그런데 많은 상용 DBMS가 데이터베이스에 동시에 연결할 수 있는 수를 제한하고 있으며, 어떤 제품은 연결 수에 따라서 가격을 다르게 매기기도 합니다. 
- 그리고 그런 제한이 없더라도 데이터베이스에 대한 연결은 컴퓨터 지원을 상당히 많이 소모하기 때문에, 동시 사용자의 수가 아주 많은 웹 사이트에서는 웹 애플리케이션 프로그램마다 제각기 데이터베이스로의 연결을 맺는 것이 사실상 불가능합니다.
- 그래서 웹 프로그래밍 분야에서는 이런 문제를 해결하기 위한 장치를 고안해 두었습니다. 
- 그 장치의 이름은 <b>데이터베이스 커넥션 풀(Database Connection Pool)</b>인데, 하드웨어 장치가 아니라 웹 컨테이너 상에 존재하는 일종의 소프트웨어 모듈입니다. 
- 데이터베이스 커넥션 풀이란 데이터베이스에 대한 몇개의 연결(connection)을 미리 맺어놓은 후에 그 것을 한데 모아놓고 웹 애플리케이션 프로그램들이 필요할 때마다 가져가서 사용한 후 반환할 수 있도록 만들어 놓은 장소를 말합니다.

#### 데이터베이스 커넥션 풀의 개념도


- 이렇게 하면 각각의 웹 애플리케이션 프로그램이 데이터베이스에 연결을 맺고 끊을 때보다 컴퓨터 자원을 효율적으로 활용할 수 있고, 
- 많은 시간이 걸리는 데이터베이스 연결 작업이 대부분 생략되기 때문에 사용자의 응답 대기 시간이 짧아진다는 장점이 생깁니다.
- 데이터베이스 커넥션 풀을 사용하기 위해서는 아파치 웹 사이트에서 무상으로 다운로드 받을 수 있는 데이터페이스 커넥션 풀 모듈을 다운받아 설치하여야 합니다.
- 모듈의 이름은 DBCP이고 이 모듈이 작동하기 위해서는 Pool과 Collections라는 두 가지 모듈이 필요합니다.



## DBCP, Pool, Collections 모듈 다운로드 받기
- [Apache Commons DBCP](https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2/2.9.0)
	
	![apache1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/apache_commons1.png)
	
- [Apache Commons Pool](https://mvnrepository.com/artifact/org.apache.commons/commons-pool2/2.11.1)
	
	![apache2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/apache_commons2.png)
	
- [Apache Commons Collections](https://mvnrepository.com/artifact/org.apache.commons/commons-collections4/4.4)
	
	![apache3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/apache_commons3.png)
	
- 다운로드 받은 jar 파일을 src/WEB-INF/lib 폴더에 복사해 주세요.

### 데이터베이스 커넥션 풀을 사용하는 방법

- 데이터페이스를 직접 사용하는 경우 
```
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "1234");
```

- 데이터베이스 커넥션 풀을 통해 데이터베이스를 사용하는 코드
```
Class.forName("org.apache.commons.dbcp.PoolingDriver");
Connection conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webdb_pool");
```
- 데이터베이스 커넥션 풀을 사용하기 위해서는 먼저 해야할 일이 있습니다. 앞서 설치한 것은 단지 데이터베이스 커넥션 풀의 사용에 필요한 소프트웨어 일 뿐, 데이베이스 커넥션 풀 자체는 아닙니다. 
- 그 소프트웨어를 이용해서 데이터베이스 커넥션 풀을 만들고, 그 풀을 웹 컨테이너에 등록하는 일은 직접 해야 합니다.
- 두 가지 방법으로 등록할 수 있습니다.
	- 데이터베이스 커넥션 풀을 생성해서 등록하는 프로그램을 작성하는 방법
	- 그런일을 하는 데 필요한 정보를 기술한 XML 문서를 작성해 놓는 방법입니다.



* * * 
# 마이바티스(mybatis) 프레임워크 설치 및 적용

## 설치
- [MyBatis 다운로드](https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.9)

![MyBatis](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/mybatis.png)

- src/webapp/WEB-INF/lib에 다운로드 받은 jar 파일 복사

## 설정

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <properties>
	  <property name="hostname" value="localhost" />
      <property name="driver" value="com.mysql.cj.jdbc.Driver" />
      <property name="url" value="jdbc:mysql://localhost:3306/spring5fs?characterEncoding=UTF8&amp;serverTimezone=UTC" />
      <property name="username" value="spring5" />
      <property name="password" value="spring5" />
  </properties>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
	<mapper resource="org/mybatis/example/MemberMapper.xml" />
  </mappers>
</configuration>
```


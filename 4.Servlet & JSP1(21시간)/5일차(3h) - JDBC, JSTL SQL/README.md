# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1Kxiy0SLIBO0ORtA6fxtONi0GYv-dTD72?usp=sharing)

## JDBC로 데이터베이스와 JSP 연동 
- JDBC(Java DataBase Connectivity)는 자바/JSP 프로그램 내에서 데이터베이스와 관련된 작업을 처리할 수 있도록 도와주는 자바 표준 인터페이스로, 관계형 데이터베이스 시스템에 접근하여 SQL문을 실행하기 위한 자바 API 또는 자바 라이브러리입니다. JDBC API를 사용하면 DBMS 종류에 상관없이 데이터베이스 작업을 처리할 수 있습니다.
- JDBC API는 java.sql.\* 패키지에 의해 구현됩니다.

### JDBC를 사용한 JSP와 데이터베이스의 연동은 다음과 같은 단계로 프로그래밍 됩니다.
1. java.sql.\* 패키지 임포트
2. JDBC 드라이버 로딩
3. 데이터베이스 접속을 위한 Connection객체 생성
4. 쿼리문을 실행하기 위한 Statement/PreparedStatement/CallableStatement 객체 생성
5. 쿼리 실행
6. 쿼리 실행 결과 값(int, ResultSet) 사용
7. 사용된 객체(ResultSet, Statement/PreparedStatement/CallableStatement, Connection) 종료

### JDBC 드라이버 로딩 및 DBMS 접속

#### JDBC 드라이버 로딩하기
1. Class.forName() 메서드를 이용하여 JDBC 드라이버를 로딩합니다.
2. JDBC 드라이버가 로딩이 되면 자동으로 객체가 생성되고 DriverManager 클래스에 등록됩니다.

```
Class.forName(String className);
```

#### MySQL 드라이버 로딩 예 
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
DriverManager 클래스의 getConnection() 메서드를 사용합니다.

```
static Connection getConnection(String url)
static Connection getConnection(String url, String user, String password)
static Connection getConnection(String url, Properties info)
```

#### Connection 객체 생성 예 : getConnection(String url) 메서드 사용
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

#### Connection 객체 생성 예 : getConnection(String url, String user, String password) 메서드 사용
```
<%
   Connection conn = null;
   try {
      Class.forName(“com.mysql.cj.jdbc.Driver”);
      conn = DriverManager.getConnection(“jdbc:mysq://localhost:3306/JSPBookDB”, “root”, “1234”);
   } catch (SQLException ex) {      // 예외 발생 처리    }
%>
```

#### Connection 객체 생성 예 : getConnection(String url, Properties info) 메서드 사용
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
데이터베이스 연결이 더 이상 필요하지 않으면 데이터베이스와 JDBC 리소스가 자동으로 닫힐 때까지 대기하는 것이 아니라 close() 메소드로 생성한 Connection 객체를 해제합니다. 일반적으로 데이터베이스 리소스를 사용하지 않기 위해 사용을 끝내자마자 리소스를 해제하는 것이 좋습니다.

```
void close() throws SQLException
```

#### 데이터베이스의 연결을 종료하는 예 
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

#### day05/connection.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%> 
<html>
<head>
<title>Database SQL</title>
</head>
<body>
	<%
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/JSPBookDB";
			String user = "root";
			String password = "1234";

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			out.println("데이터베이스 연결이 성공되었습니다.");
		} catch (SQLException ex) {
			out.println("데이터베이스 연결이 실패되었습니다.<br>");
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (conn != null)
				conn.close();
		}
	%>
</body>
</html>
```

### 데이터베이스 쿼리 실행 
- Connection 객체를 생성하여 데이터베이스가 연결되었다면 쿼리 실행 객체를 이용하여 쿼리를 실행합니다.
- 쿼리 실행 객체는 Statement, PreparedStatement, CallableStatement입니다.
- 쿼리의 실행 성공이나 실패 여부와 상관없이 쿼리 실행 객체와 Connection 객체의 리소스를 해제해야 합니다.

#### Statement 객체로 데이터 접근하기
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
|------|----|---------|
|executeQuery(String sql)|ResultSet|SELECT 문을 실행할 때 사용합니다.(ResultSet 객체 반환)|
|executeUpdate(String sql)|int|삽입, 수정, 삭제와 관련된 SQL 문 실행에 사용합니다.|
|close()|void|Statement 객체를 반환할 때 사용합니다.|


#### executeQuery() 메서드 사용 예 : SELECT 쿼리문
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

#### executeUpdate() 메서드 사용 예(삽입) : INSERT 쿼리문
```
<%
   Connection conn = null;
   ... (생략) ... 
   Statement stmt = conn.createStatement();
   String sql = “INSERT INTO Member(id, name, passwd) VALUES (‘1’, ‘홍길순’, ‘1234’);
   int rs = stmt.executeUpdate(sql);
%>
```

#### day05/member.sql
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

#### day05/insert01.jsp
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

#### day05/dbconn.jsp
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

#### day05/insert01_process.jsp
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
이때 Xxx는 필드 데이터형으로, 해당 필드의 데이터형이 문자열이면 setString().이 되고 int이면 setInt()가 됩니다.

#### setXxx() 메서드의 종류

|메서드|반환유형|설명|
|------|----|---------|
|setString(int parameterIndex, String x)|void|필드 유형이 문자열인 경우|
|setInt(int parameterIndex, int x)|void|필드 유형이 정수형인 경우|
|setLong(int parameterIndex, long x)|void|필드 유형이 정수형인 경우|
|setDouble(int parameterIndex, double x)|void|필드 유형이 실수형인 경우|
|setFloat(int parameterIndex, float x)|void|필드 유형이 실수형인 경우|
|setObject(int parameterIndex, Object x)|void|필드 유형이 객체형인 경우|
|setDate(int parameterIndex, Date x)|void|setDate(int parameterIndex, Date x)|
|seTimestamp(int parameterIndex, Timestamp x)|void|필드 유형이 시간형인 경우|


#### PreparedStatement 객체의 메서드 종류

|메서드|반환유형|설명|
|-----|----|---------|
|executeQuery()|ResultSet|SELECT 문을 실행할 때 사용합니다(ResultSet 객체 반환)|
|executeUpdate()|int|삽입, 수정 삭제와 관련된 SQL 문 실행에 사용합니다.|
|close()|void|PreparedStatement 객체를 반환할 때 사용합니다.|

#### executeQuery() 메서드 사용 예 : SELECT 쿼리문
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

#### executeUpdate() 메서드 사용 예(삽입) : INSERT 쿼리문
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

#### day05/insert02.jsp
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

#### day05/insert02_process.jsp
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

### 쿼리문 실행 결과 값 가져오기
- SELECT 쿼리문 실행 시 executeQuery() 메서드를 사용하면 실행 결과가 java.sql.ResultSet 형으로 반환
- ResultSet 객체는 Statement 또는 PreparedStatement 객체로 SELECT 문을 사용하여 얻어온 레코드 값을 테이블 형태로 가진 객체입니다.

#### Statement 객체를 사용하는 경우
```
ResultSet executeQuery(String sql) throws SQLException
```

#### PreparedStatement 객체를 사용하는 경우
```
ResultSet executeQuery() throws SQLException
```

- ResultSet 객체는 SELECT문으로 필드값을 가져오기 위해 getXxx() 메서드를 사용합니다.
- Xxx는 필드의 데이터형과 관련이 있습니다.
- 해당 필드의 데이터형이 문자열이면 getString()이 되고 int이면 getInt()가 됩니다. 
- ResultSet 객체의 getXxx() 메서드를 사용하여 필드 순번으로 필드 값을 가져온다면 첫 번째는 1부터 시작합니다.


### ResultSet 객체의 메서드 

|메서드|반환유형|설명|
|-------|----|---------|
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

#### executeQuery() 메서드 사용 예 : SELECT 쿼리문 
```
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
```

#### day05/select01.jsp
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
#### day05/select02.jsp
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

#### day05/update01.jsp
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

#### day05/update01_process.jsp
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

#### day05/update02.jsp 
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
#### day05/update02_process.jsp
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

#### day05/delete01.jsp
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

#### day05/delete01_process.jsp
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

#### day05/delete02.jsp
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

#### day05/delete02_process.jsp
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

## JSTL SQL 태그 라이브러리 사용하기

### SQL 태그

|태그|설명|
|-----|-------|
|\<sql:setDataSource\>|DataSource를 설정하는 데 사용합니다.|
|\<sql:query\>|조회 쿼리문을 실행하는 데 사용합니다.|
|\<sql:update\>|삽입,수정, 삭제 쿼리문을 실행하는 데 사용합니다.|
|\<sql:param\>|쿼리문에 문자열 형식의 파라미터를 설정하는 데 사용합니다.|
|\<sql:dateParam\>|쿼리문에 날짜 형식의 파라미터를 설정하는 데 사용합니다.|
|\<sql:transaction\>|트랜잭션을 구현하는 데 사용합니다.|

#### day05/member.sql
```
drop table member;

CREATE TABLE IF NOT EXISTS member(
   id VARCHAR(20) NOT NULL,
   passwd  VARCHAR(20),
   name VARCHAR(30),    
   PRIMARY KEY (id)
);
INSERT INTO member VALUES('1', '1234', '홍길순');
INSERT INTO member VALUES('2', '1235', '홍길동');
```

#### day05/sql01.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<sql:setDataSource var="dataSource"
		url="jdbc:mysql://localhost:3306/JSPBookDB"
		driver="com.mysql.cj.jdbc.Driver" user="root" password="1234" />


	<sql:query var="resultSet" dataSource="${dataSource}">
		select * from member
	</sql:query>

	<table border="1">
		<tr>
			<c:forEach var="columnName" items="${resultSet.columnNames}">
				<th width="100"><c:out value="${columnName}" /></th>
			</c:forEach>
		</tr>
		<c:forEach var="row" items="${resultSet.rowsByIndex}">
		<tr>
			<c:forEach var="column" items="${row}" varStatus="i">
			<td>
				<c:if test="${column != null}">
					<c:out value="${column}" />
				</c:if> 
				<c:if test="${column == null}">
					&nbsp;
				</c:if>
			</td>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
```

#### day05/sql02.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<form method="post" action="sql02_process.jsp">
		<p>아이디 : <input type="text" name="id">
		<p>비밀번호 : <input type="password" name="passwd">
		<p>이름 : <input type="text" name="name">
		<p><input type="submit" value="전송">
	</form>
</body>
</html>
```

#### day05/sql02_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
	%>
	<sql:setDataSource var="dataSource"
		url="jdbc:mysql://localhost:3306/JSPBookDB"
		driver="com.mysql.cj.jdbc.Driver" user="root" password="1234" />


	<sql:update dataSource="${dataSource}" var="resultSet">
		INSERT INTO member(id, name, passwd) VALUES (?,?,?)
		<sql:param value="<%=id%>" />
		<sql:param value="<%=name%>" />
		<sql:param value="<%=passwd%>" />
	</sql:update>
	<c:import var="url" url="sql01.jsp"  />
	${url}
</body>
</html>
```

#### day05/sql03.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<form method="post" action="sql03_process.jsp">
		<p>아이디 : <input type="text" name="id">
		<p>비밀번호 : <input type="password" name="passwd">
		<p>이름 : <input type="text" name="name">
		<p><input type="submit" value="전송">
	</form>
</body>
</html>
```

#### day05/sql03_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
	%>
	<sql:setDataSource var="dataSource"
		url="jdbc:mysql://localhost:3306/JSPBookDB"
		driver="com.mysql.jdbc.Driver" user="root" password="1234" />


	<sql:update dataSource="${dataSource}" var="resultSet">
		UPDATE member SET name =?  where id =? and passwd =?
		<sql:param value="<%=name%>" />
		<sql:param value="<%=id%>" />		
		<sql:param value="<%=passwd%>" />		
	</sql:update>
	<c:import var="url" url="sql01.jsp"  />
	${url}
</body>
</html>
```

#### day05/sql04.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<form method="post" action="sql04_process.jsp">
		<p>아이디 : <input type="text" name="id">
		<p>비밀번호 : <input type="password" name="passwd">		
		<p><input type="submit" value="전송">
	</form>
</body>
</html>
```

#### day05/sql04_process.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");		
	%>
	<sql:setDataSource var="dataSource"
		url="jdbc:mysql://localhost:3306/JSPBookDB"
		driver="com.mysql.cj.jdbc.Driver" user="root" password="1234" />


	<sql:update dataSource="${dataSource}" var="resultSet">
		DELETE FROM member where id =? and passwd =?
		<sql:param value="<%=id%>" />		
		<sql:param value="<%=passwd%>" />		
	</sql:update>
	<c:import var="url" url="sql01.jsp"  />
	${url}
</body>
</html>
```


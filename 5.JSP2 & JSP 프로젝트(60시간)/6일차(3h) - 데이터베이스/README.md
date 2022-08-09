# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1CB_QO1tlVVF2DrgaOAS6vcqFpmwyFi6M?usp=sharing)


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
# 마이바티스(mybatis) 프레임워크 설치 및 적용

## 설치
- [MyBatis 다운로드](https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.9)

![MyBatis](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/mybatis.png)

- src/webapp/WEB-INF/lib에 다운로드 받은 jar 파일 복사

## 설정

#### member.sql
```
CREATE TABLE `member` (
  `memNo` int NOT NULL AUTO_INCREMENT,
  `memId` varchar(45) NOT NULL COMMENT '아이디',
  `memNm` varchar(45) NOT NULL COMMENT '회원명',
  `email` varchar(60) NOT NULL COMMENT '이메일 정보',
  `memPw` varchar(60) NOT NULL COMMENT '비밀번호',
  `regDt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modDt` datetime DEFAULT NULL,
  PRIMARY KEY (`memNo`),
  UNIQUE KEY `memId_UNIQUE` (`memId`)
)
```

#### kr/codefty/config/mybatis/MyBatisConnectionFactory.java - 설정 클래스
```
package kr.codefty.config.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	/** 데이터페이스 접속 객체 */
	private static SqlSessionFactory sqlSessionFactory;
	
	/** XML에 명시된 접속 정보를 읽어온다. */
	static {
		// 접속정보를 명시하고 있는 XML의 경로 읽기
		try {
			// mybatis-config.xml 파일의 경로 지정 
			Reader reader = Resources.getResourceAsReader("kr/codefty/config/mybatis/mybatis-config.xml");
			
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 데이터베이스 접속 객체를 통해 DATABASE에 접속한 세션을 리턴한다. */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
```

- 상기 소스는 설정파일을 읽고 데이터베이스의 접속하기 위한 클래스 입니다.
- 데이터베이스 접속 정보와 SQL 실행을 위한  mapper 설정을 mybatis-config.xml에 작성하고 이를 읽어와서 데이터베이스에 접속할 수 있는 객체를 생성합니다.
```
Reader reader = Resources.getResourceAsReader("kr/codefty/config/mybatis/mybatis-config.xml");
			
if (sqlSessionFactory == null) {
	sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
}
```
- 데이터베이스에 접속하여 mapper에 정의된 SQL을 실행할 수 있는 객체를 반환합니다.
```
public static SqlSession getSqlSession() {
	return sqlSessionFactory.openSession();
}
```

> **static { ... }** 으로 정의된 부분 : 클래스가 최초 로딩될때 실행되는 영역, 클래스의 초기화 역할을 담당합니다.


#### kr/codefty/config/mybatis-config.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <properties>
	  <property name="hostname" value="localhost" />
      <property name="driver" value="com.mysql.cj.jdbc.Driver" />
      <property name="url" value="jdbc:mysql://localhost:3306/jsp_board?characterEncoding=UTF8&amp;serverTimezone=UTC" />
      <property name="username" value="jspboard" />
      <property name="password" value="..." />
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
	<mapper resource="kr/codefty/board/mapper/MemberMapper.xml" />
  </mappers>
</configuration>
```

- 데이터베이스 연결 정보를 설정합니다.  dataSource Type을 POOLED로 설정하였는데, 이는 커넥션 풀(Connection Pool)을 사용하여 데이터베이스 접속을 하므로 성능상의 이점이 있습니다.
```
<transactionManager type="JDBC"/>
	<dataSource type="POOLED">
    <property name="driver" value="${driver}"/>
    <property name="url" value="${url}"/>
    <property name="username" value="${username}"/>
    <property name="password" value="${password}"/>
</dataSource>
```

- 데이터 연결정보는 관리상의 편의로 하기와 같이 별도 변수로 등록하여 설정 합니다.
```
<properties>
	<property name="hostname" value="localhost" />
    <property name="driver" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/jsp_board?characterEncoding=UTF8&amp;serverTimezone=UTC" />
    <property name="username" value="jspboard" />
    <property name="password" value="..." />
</properties>
```

- SQL 실행을 위한 mapper xml을 설정 합니다.
```
<mappers>
	<mapper resource="kr/codefty/board/mapper/MemberMapper.xml" />
</mappers>
```

#### kr/codefty/board/member/Member.java - SQL 실행 결과를 저장할 Bean 클래스 정의
```
package kr.codefty.board.member;

public class Member {
	
	private int memNo; // 회원번호 
	private String memId; // 아이디
	private String memNm; // 회원명 
	private String email; // 이메일 
	private String memPw; // 비밀번호
	private String regDt; // 가입일
	private String modDt; // 정보 수정일
	
	public int getMemNo() {
		return memNo;
	}
	
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemNm() {
		return memNm;
	}
	
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	
	public String getRegDt() {
		return regDt;
	}
	
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getModDt() {
		return modDt;
	}
	
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memNm=" + memNm + ", email=" + email + ", memPw="
				+ memPw + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
}

```

#### kr/codefty/board/mapper/MemberMapper.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="MemberMapper">

    <!-- Beans 클래스의 객체이름(id)과 클래스이름(type)을 명시한다. -->
    <resultMap id="memberMap" type="kr.codefty.board.member.Member">
        <!-- Beans의 멤버변수(property)이름과 대상 테이블의 컬럼(column)을 연결한다. -->
        <result property="memNo" column="memNo" />
        <result property="memId" column="memId" />
        <result property="memNm" column="memNm" />
        <result property="email" column="email" />
        <result property="memPw" column="memPw" />
        <result property="regDt" column="regDt" />
        <result property="modDt" column="modDt" />
    </resultMap>

    <!-- 단일행 조회를 위한 기능 정의 -->
    <select id="selectItem"
            parameterType="kr.codefty.board.member.Member"
            resultMap="memberMap">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		SELECT * FROM member WHERE memId=#{memId};
    </select>

    <!-- 다중행 조회를 위한 기능 정의 -->
    <select id="selectList"
            parameterType="kr.codefty.board.member.Member"
            resultMap="memberMap">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		SELECT * FROM member ORDER BY memNo DESC;
    </select>

    <!-- 데이터 수 조회를 위한 기능 정의 -->
    <select id="selectCount"
            parameterType="kr.codefty.board.member.Member"
            resultType="long">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		SELECT COUNT(*) FROM member;
    </select>

    <!-- 데이터 저장을 위한 기능 정의 -->
    <insert id="insertItem"
            parameterType="kr.codefty.board.member.Member"
            useGeneratedKeys="true"
            keyProperty="memNo">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		INSERT INTO member(memId, memNm, email, memPw) VALUES (#{memId},#{memNm},#{email}, #{memPw}); 
    </insert>

    <!-- 데이터 삭제를 위한 기능 정의 -->
    <delete id="deleteItem" parameterType="kr.codefty.board.member.Member">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		DELETE FROM member WHERE memId=#{memId};
    </delete>

    <!-- 데이터 갱신을 위한 기능 정의 -->
    <update id="updateItem" parameterType="kr.codefty.board.member.Member">
        <!-- 이 안에서 처리할 SQL문을 명시한다. -->
		UPDATE member 
			SET 
				memNm=#{memNm},
				email=#{email}
			WHERE memId=#{memId};
    </update>
</mapper>
```
- SQL을 실행 유형별 태그로 나누어 정의합니다. 
	- \<select ... \> : 조회 SQL 
	- \<insert ...\> : 추가 SQL
	- \<delete ...\> : 삭제 SQL
	- \<update ...\> : 수정 SQL
	
- 각 실행 유형별 태그에 지정된 id 값과 mapper namespace를 이용하여 XML에 미리 지정한 SQL 을 실행합니다.
```
List<Member> output = sqlSession.selectList("MemberMapper.selectList", null);
```

## 적용해보기
#### kr/codefty/board/dbtest/MemberInsert.java
```
package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;
import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberInsert {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 저장할 데이터 준비
		Member member = new Member();
		member.setMemId("user1");
		member.setMemNm("사용자1");
		member.setMemPw("123456");
		member.setEmail("user1@test.org");

		// 3) 데이터 저장
		int affectedRows = sqlSession.insert("MemberMapper.insertItem", member);
		
		// 4) 결과 판별
		System.out.printf("%d개의 데이터 저장됨%n", affectedRows);
		
		
		// 신규로 저장된 데이터의 Primary Key는 입력 파라미터로 전달된 빈(beans)에 저장된다.
		System.out.printf("회원번호(memNo) : " + member.getMemNo());
		
		// 5) 변경사항 저장 및 DB 접속 해제
		sqlSession.commit();  // 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.close();
	}

}
```
- 실행 결과
```
1개의 데이터 저장됨
회원번호(memNo) : 1
```

#### kr/codefty/board/dbtest/MemberSelectList.java
```
package kr.codefty.board.dbtest;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberSelectList {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 데이터 조회
		List<Member> members = sqlSession.selectList("MemberMapper.selectList");
		
		// 4) 결과 판별
		if (members == null) {
			System.out.println("조회결과 없음");
		} else {
			for(Member member : members) {
				System.out.println(member);
			}
		}
		
		// 5) DB 접속 해제
		sqlSession.close();
	}
}
```
- 실행 결과
```
Member [memNo=1, memId=user1, memNm=사용자1, email=user1@test.org, memPw=123456, regDt=2022-06-01 11:03:09, modDt=null]
```

#### kr/codefty/board/dbtest/MemberSelectOne.java
```
package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberSelectOne {
	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 조회할 데이터 설정
		Member param = new Member();
		param.setMemId("user1");
		
		// 3) 데이터 조회
		Member member = sqlSession.selectOne("MemberMapper.selectItem", param);
		
		// 4)  결과 판별
		if (member == null) {
			System.out.println("조회결과 없음");
		} else {
			System.out.println(member);
		}

		// 5) DB 접속 해제
		sqlSession.close();
	}
}
```
- 실행 결과
```
Member [memNo=1, memId=user1, memNm=사용자1, email=user1@test.org, memPw=123456, regDt=2022-06-01 11:03:09, modDt=null]
```
#### kr/codefty/board/dbtest/MemberUpdate.java
```
package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberUpdate {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 변경할 데이터 준비
		Member param = new Member();
		param.setMemId("user1");
		param.setMemNm("사용자1_2");
		param.setEmail("user1_2@test.org");
		
		// 3) 데이터 수정 -> 반환값은 수정된 행의 수
		int affectedRows = sqlSession.update("MemberMapper.updateItem", param);
		
		// 4) 결과 판별
		System.out.printf("%d 개의 데이터 수정됨%n", affectedRows);
		
		// 5) 변경사항 저장 및 DB 접속 해제
		// 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.commit();
		sqlSession.close();
	}
}
```
- 실행 결과
```
1 개의 데이터 수정됨
```

#### kr/codefty/board/dbtest/MemberDelete.java
```
package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberDelete {
	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 삭제할 데이터의 조건값 준비
		Member member = new Member();
		member.setMemId("user1");
		
		// 3) 데이터의 삭제 -> 반환값은 삭제된 행의 수
		int affectedRows = sqlSession.delete("MemberMapper.deleteItem", member);
		
		// 4) 결과 판별
		System.out.printf("%d개의 데이터 삭제됨%n", affectedRows);
		
		// 5) 변경사항 저장 및 DB 접속 해제
		// 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.commit();
		sqlSession.close();
	}
}
```
- 실행결과
```
1개의 데이터 삭제됨
```


### MyBatis와 Log4J 연동하기
- MyBatis의 로그 출력 기능을 이용하면 실행하는 SQL문, 파라미터 값, 실행 결과를 실시간으로 확인할 수 있어 디버깅시 유용합니다.

- [log4j 다운로드](https://mvnrepository.com/artifact/log4j/log4j/1.2.17)
	
	![log4j](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/images/log4j.png)

- 다운로드 받은 jar 파일을 WEB-INF/lib에 복사하여 넣어줄 것 

#### kr/codefty/config/mybatis-config.xml
- \<setting name="logImpl" value="LOG4J"/\> 설정 추가  
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	...
	<settings>
		<setting name="logImpl" value="LOG4J"/>  <!-- log4j log setting  -->
	</settings>
</configuration>
```

#### WEB-INF/classes/log4j.properties
```
log4j.rootLogger=DEBUG, stdout

#log4j.logger.kr.codefty.board.mapper=DEBUG

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```
- 로그 레벨 설정 - rootLogger 
	- 먼저 rootLogger = 최상위 로거의 로그 레벨을 설정한다.
	- 하위 로거들은 항상 부모의 로그 레벨을 상속받아서 별도로 설정하지 않으면 rootLogger의 레벨을 똑같이 따라간다.
	- 로그 레벨
		- FATAL : 어플리케이션을 중지해야 할 심각한 오류
		- ERROR : 오류가 발생했지만 어플리케이션은 계속 실행할 수 있는 상태
		- WARN : 잠재적인 위험을 갖고 있는 상태
		- INFO : 어플리케이션의 주요 실행 정보
		- DEBUG : 어플리케이션의 내부 실행 상황을 추적할 수 있는 상세 정보
		- TRACE : DEBUG보다 더 상세한 정보
	- 각 레벨은 위 레벨을 포함해서 ERROR로 설정하면 FATAL 로그를 포함해서 출력하고 INFO로 설정하면 FATAL, ERROR, WARN 로그를 포함해서 출력한다. 따라서 TRACE로 설정하면 모든 로그를 출력한다.

- Appender(출력 담당자) 선언
	- rootLogger의 로그 레벨 설정 다음에는 appender(출력 담당자)의 이름이 온다. appender 이름은 마음대로 지정할 수 있고 이렇게 선언한 이름으로 다음 설정에서 appender를 정의한다. 

- Appender(출력 담당자) 정의
	- log4j.appender.stdout(appender 이름)에 로그를 어디로 출력 할지 정의
	- log4j.appender.stdout.layout에는 로그 출력 형식을 정의 
	- log4j.appender.stdout.layout.ConversionPattern에는 로그를 출력할때 사용할 패턴을 정의
	
	- 로그를 어디로 출력할 것인가
		- 로그를 기본 출력 장치인 모니터로 출력하거나 파일로 출력하거나 네트워크를 이용해서 원격의 서버로 출력할 수 있다. <br> 위와 같이 ConsoleAppender로 설정하면 표준 출력 = 모니터로 출력한다.
		
		- 주요 로그 출력 클래스
		
		|클래스|설명|
		|------|----------|
		|org.apache.log4j.ConsoleAppender|표준 출력 장치인 모니터로 출력한다.<br>System.out 또는 System.err로 로그를 출력한다.<br>기본은 System.out이다.|
		|org.apache.log4j.FileAppender|파일로 로그를 출력한다.|
		|org.apache.log4j.net.SocketAppender|원격 로그 서버에 로그 정보를 담은 LoggingEvent 객체를 보낸다.|
	
	- 로그의 출력 형식
		- 로그는 문자열, XML, HTML 형식이나 특정 패턴으로 출력할 수 있다. 위와 같이 설정하면 특정 패턴 형식에 따라 로그를 출력하도록 하는것이다. 이 경우 추가적으로 ConversionPattern 설정이 필요하다. 
			
		- 주요 출력 형식 클래스 
		
		|클래스|설명|
		|------|----------|
		|org.apache.log4j.SimpleLayout|로그 레벨 - 메시지 형식|
		|org.apache.log4j.HTMLLayout|HTML 테이블 형식|
		|org.apache.log4j.PatternLayout|패턴에 따라 로그 출력|
		|org.apache.log4j.xml.XMLLayout|log4j.dtd 규칙에 따라 XML을 만들어 출력|
	
	- 패턴 정의 : PatternLayout에서 사용할 패턴을 정의한다.
		- %5p : 로그 레벨을 5자리 문자열로 출력하라
		- %t : thread 이름을 출력하라
		- %m : 로그 내용을 출력하라
		- %n : 다음 행
		
- 실행 결과
```
DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - Opening JDBC Connection
DEBUG [main] - Created connection 1860591867.
DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@6ee660fb]
DEBUG [main] - ==>  Preparing: INSERT INTO member(memId, memNm, email, memPw) VALUES (?,?,?, ?);
DEBUG [main] - ==> Parameters: user1(String), 사용자1(String), user1@test.org(String), 123456(String)
```



## 게시판 프로젝트 소스(마이바티스 연동 및 회원가입 구현)

- [예제 소스](https://github.com/yonggyo1125/Board_JSP/tree/database)
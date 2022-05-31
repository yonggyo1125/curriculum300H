# JDBC로 데이터베이스와 JSP 연동
- JDBC(Java DataBase Connectivity)는 자바/JSP 프로그램 내에서 데이터베이스와 관련된 작업을 처리할 수 있도록 도와주는 자바 표준 인터페이스로, 관계형 데이터베이스 시스템에 접근하여 SQL문을 실행하기 위한 자바 API 또는 자바 라이브러리입니다. JDBC API를 사용하면 DBMS 종류에 상관없이 데이터베이스 작업을 처리할 수 있습니다.
- JDBC API는 java.sql.\* 패키지에 의해 구현됩니다.

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



* * * 
# 데이터베이스 커넥션 풀 설치 및 적용

* * * 
# 마이바티스(mybatis) 프레임워크 설치 및 적용


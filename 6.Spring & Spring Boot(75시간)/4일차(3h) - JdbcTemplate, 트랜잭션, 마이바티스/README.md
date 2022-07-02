# 데이터베이스 연동

## JDBC 프로그래밍의 단점을 보완하는 스프링

- JDBC  프로그래밍을 경험한 경우 다음과 같은 코드에 익숙할 것이다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20JdbcTemplate%2C%20%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%2C%20%EB%A7%88%EC%9D%B4%EB%B0%94%ED%8B%B0%EC%8A%A4/images/image1.png)

- JDBC API를 이용하면 상기 코드 처럼 DB 연동에 필요한 Connection을 구한 다음 쿼리를 실행하기 위한 PreparedStatement를 생성한다. 그리고 쿼리를 실행한 뒤에는 finally 블록에서 ResultSet, PreparedStatement, Connection을 닫는다. 

- 여기에서 문제는 점선으로 표시한 부분인데, 점선으로 표시한 코드는 사실상 데이터 처리와는 상관없는 코드지만 JDBC 프로그래밍을 할 때 구조적으로 반복된다. 실제 핵심은 점선으로 표시한 부분을 제외한 나머지 코드로 전체 코드의 절반도 되지 않는다.

- 구조적인 반복을 줄이기 위한 방법은 <b>템플릿 메서드 패턴과 전략 패턴</b>을 함께 사용하는 것이다. 스프링은 바로 이 두 패턴을 엮은 <b> JdbcTemplate 클래스</b>를 제공한다. 기 클래스를 사용하면 다음과 같이 변경할 수 있다.

```java
List<Member> results = jdbcTemplate.query(
		"SELECT * FROM member WHERE email = ?",
		new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(rs.getString("email"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getTimestamp("regDate"));
				member.setId(rs.getLong("id"));
				return mem,ber;
			}
		}, email);
return results.isEmpty() ? null : results.get(0);
```

- 람다식을 사용하면 다음과 같아 코드를 더 줄일 수 있다.

```java
List<Member> results = jdbcTemplate.query(
	"SELECT * FROM member WHERE email = ?", 
	(ResultSet rs, int rowNum) -> {
		Member member = new Member(rs.getString("email"),
			rs.getString("password"),
			rs.getString("name"),
			rs.getTimestamp("regDate"));
		member.setId(rs.getLong("id"));
		return member;
	},		
	email);
return results.isEmpty() ? null : results.get(0);
```

- 스프링이 제공하는 또 다른 장점은 트랜잭션 관리가 쉽다는 것이다. 
- JDBC API로 트랜잭션을 처리하려면 다음과 같이 Connection의 setAutoCommit(false)을 이용해서 자동 커밋을 비활성화하고 commit()과 rollback() 메서드를 이용해서 트랜잭션을 커밋하거나 롤백해야 한다.

```java
public void insert(Member member) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = DriverManager.getConnection(
			"jdbc:mysql://localhost/spring5fs?characterEncoding=utf8",
			"spring5", "spring5");
			
		conn.setAutoCommit(false);
		
		...(DB 쿼리 실행)
		
		conn.commit();
		
	} catch(SQLException ex) {
		if (conn != null) 
			try { conn.rollback(); } catch (SQLException e) {}
	} finally {
		if (pstmt != null)
			try { pstmt.close(); } catch (SQLException e) {}
		if (conn != null)
			try { conn.close(); } catch (SQLException e) {}
	}
}
```

- 스프링을 사용하면 트랜잭션을 적용하고 싶은 메서드에 <b>@Transactional 애노테이션</b>을 붙이기만 하면 된다.

```java
@Transactional
public void insert(Member member) {
	...
}
```

- 커밋과 롤백 처리는 스프링이 알아서 처리하므로 코드를 작성하는 사람은 트랜잭션 처리를 제외한 핵심 코드만 집중해서 작성하면 된다.

## 프로젝트 준비

- 프로젝트 생성

```
mvn archetype:generate
```

- groupId, artifactId는 적절하게 입력해 줍니다.
- 자바 실습 버전을 최신버전(17)로 변경합니다.
- spring-context, spring-jdbc, tomcat-jdbc, mysql-connector-java 의존성을 mvnrepository 에서 검색하여 다음과 같이 추가합니다.

```xml
... 생략
<properties>
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	<maven.compiler.source>17</maven.compiler.source>
	<maven.compiler.target>17</maven.compiler.target>
</properties>
... 생략
<dependencies>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>5.3.21</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-jdbc</artifactId>
		<version>5.3.21</version>
	</dependency>
	<dependency>
		<groupId>org.apache.tomcat</groupId>
		<artifactId>tomcat-jdbc</artifactId>
		<version>9.0.64</version>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.29</version>
	</dependency>
... 생략
</dependencies>
```

- 이클립스에서 Import - Existing Maven Projects를 클릭하여 생성된 폴더를 선택하여 생성해 줍니다.

- 다음은 pom.xml 파일에 새로 추가한 의존 모듈이다.
	- <b>spring-jdbc</b> : JdbcTemplate 등 JDBC 연동에 필요한 기능을 제공한다.
	- <b>tomcat-jdbc</b> : DB 커넥션풀 기능을 제공한다.
	- <b>mysql-connector-java</b> : MySQL 연결에 필요한 JDBC 드라이버를 제공한다.
	
- 스프링이 제공하는 트랜잭션 기능을 사용하려면 spring-tx 모듈이 필요한데, spring-jdbc 모듈에 대한 의존을 추가하면 spring-tx 모듈도 자동으로 포함된다.
- 따라서 위 pom.xml에 spring-tx 모듈을 따로 추가하지 않아도 spring-tx 모듈을 사용할 수 있다.

#### 커넥션 풀이란?

- 실제 서비스 운영 환경에서는 서로 다른 장비를 이용하여 자바 프로그램과 DBMS를 실행한다. 자바 프로그램에서 DBMS로 커넥션을 생성하는 시간은 (컴퓨터 입장에서) 매우 길기 때문에 DB 커넥션을 생성하는 시간은 전체 성능에 영향을 줄 수 있다. 또한 동시에 접속하는 사용자수가 많으면 사용자마다 DB 커넥션을 생성해서 DBMS에 부하를 준다.

- 최초 연결에 따른 응답 속도 저하와 동시 접속자가 많을 때 발생하는 부하를 줄이기 위해 사용하는 것이 커넥션 풀이다. 커넥션 풀은 일정 개수의 DB 커넥션을 미리 만들어두는 기법이다. DB 커넥션이 필요한 프로그램은 커넥션 풀에서 커넥션을 가져와 사용한 뒤 커넥션을 다시 풀에 반납한다. 커넥션을 미리 생성해두기 때문에 커넥션을 사용하는 시점에서 커넥션을 생성하는 시간을 아낄 수 있다. 또한 동시 접속자가 많더라도 커넥션을 생성하는 부하가 적기 때문에 더 많은 동시 접속자를 처리할 수 있다. 커넥션도 일정 개수로 유지해서 DBMS에 대한 부하를 일정 수준으로 유지할 수 있게 해 준다.

- 이런 이유로 실제 서비스 운영환경에서는 매번 커넥션을 생성하지 않고 커넥션 풀을 사용해서 DB 연결을 관리한다. DB 커넥션 풀 기능을 제공하는 모듈로는 Tomcat JDBC, HikariCP, DBCP, e3p0 등이 존재한다. 현시점에서 지속적인 개발, 성능 등을 고려하면 Tomcat JDBC나 HikariCP를 권한다. 여기에서는 Tomcat JDBC 모듈을 사용한다.


#### 다음의 코드를 복사한다 : 학습용 소스 > spring

- spring 패키지를 생성한 뒤 복사하면 된다.
	- ChangePasswordService.java
	- DuplicateMemberException.java
	- Member.java, MemberDao.java
	- MemberInfoPrinter.java
	- MemberListPrinter.java
	- MemberNotFoundException.java
	- MemberPrinter.java
	- MemberRegisterService.java
	- RegisterRequest.java
	- WoringIdPasswordException.java
	
- DB를 사용해서 MemberDao, 클래스를 구현할 것이므로 다음과 같이 MemberDao의 메서드 코드를 삭제하고 저장하자.

#### src/main/java/spring/MemberDao.java

```java
package spring;

import java.util.Collection;

public class MemberDao {

	public Member selectByEmail(String email) {
		return null;
	}
	
	public void insert(Member member) {
		
	}
		
	public void update(Member member) {
			
	}
		
	public Collection<Member> selectAll() {
		return null;
	}
}
```

## DB 테이블 생성

```
create user 'spring5'@'localhost' identified by 'spring5';

create database spring5fs character set=utf8;

grant all privileges on spring5fs.* to 'spring5'@'localhost';

create table spring5fs.member (
    id int auto_increment primary key,
    email varchar(255),
    password varchar(100),
    name varchar(100),
    regDate datetime,
    unique key (email) 
) engine=InnoDB character set = utf8;
```


## DataSource 설정

- JDBC API는 DriverManager 외에 DataSource를 이용해서 DB 연결을 구하는 방법을 정의하고 있다. 
- DataSource를 사용하면 다음 방식으로 Connection을 구할 수 있다.

```java
Connection conn = null;
try {
	// dataSource는 생성자나 설정 메서드를 이용해서 주입받음
	conn = dataSource.getConnection();
	...  생략 
```

- 스프링에 제공하는 DB 연동 기능은 DataSource를 사용해서 DB Connection을 구한다. 
- DB 연동에 사용할 DataSource를 스프링 빈으로 등록하고 DB 연동 기능을 구현한 빈 객체는 DataSource를 주입받아 사용한다.
- Tomcat JDBC 모듈은 javax.sql.DataSource를 구현한 DataSource 클래스를 제공한다.
- 이 클래스를 스프링 빈으로 등록해서 DataSource로 사용할 수 있다.

#### src/main/java/config/AppCtx.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		return ds;
	}
}
```

- DataSource 객체를 생성한다.
- JDBC 드라이버 클래스를 지정한다. MySQL 드라이버 클래스를 사용한다.
- JDBC URL을 지정한다. 데이터베이스의 테이블의 캐릭터셋을 UTF-8로 설정했으므로 characterEncoding 파라미터를 이용해서 MySQL에 연결할 때 사용할 캐릭터셋을 UTF-8로 지정했다. "utf8"에 하이픈이 없음에 유의하자
- DB 연결할 때 사용할 사용자의 계정과 암호를 지정한다.
- destoryMethod 속성값을 close로 설정했다. close 메서드는 커넥션 풀에 보관된 Connection을 닫는다.


### Tomcat JDBC의 주요 프로퍼티

- Tomcat JDBC 모듈의 org.apache.tomcat.jdbc.pool.DataSource 클래스는 커넥션 풀 기능을 제공하는 DataSource 구현 클래스이다.
- DataSource 클래스는 커넥션을 몇 개 만들지 지정할 수 있는 메서드를 제공한다.

|설정 메서드|설명|
|----|----|
|setInitialSize(int)|커넥션 풀을 초기화할 때 생성할 초기 커넥션 개수를 지정한다. 기본값은 10이다.|
|setMaxActive(int)|커넥션 풀에서 가져올 수 있는 최대 커넥션 개수를 지정한다. 기본값은 100이다.|
|setMaxIdle(int)|커넥션 풀에 유지할 수 있는 최대 커넥션 개수를 지정한다. 기본값은 maxActive와 같다.|
|setMinIdle(int)|커넥션 풀에 유지할 최소 커넥션 개수를 지정한다. 기본값은 initialSize에서 가져온다.|
|setMaxWait(int)|커넥션 풀에서 커넥션을 가져올 때 대기할 최대 시간을 밀리초 단위로 지정한다. 기본값은 30000밀리초(30초)이다.|
|setMaxAge(long)|최초 커넥션 연결 후 커넥션의 최대 유효 시간을 밀리초 단위로 지정한다. 기본값은 0이다. 0은 유효시간이 없음을 의미한다.|
|setValidationQuery(String)|커넥션이 유효한지 검사할 때 사용할 쿼리를 지정한다. 언제 검사할지는 별도 설정으로 지정한다. 기본값은 null이다. null이면 검사를 하지 않는다. "select 1"이나 "select 1 from dual"과 같은 쿼리를 주로 사용한다.|
|setValidationQueryTimeout(int)|검사 쿼리의 최대 실행 시간을 초 단위로 지정한다. 이 시간을 초과하면 검사에 실패한 것으로 간주한다. 0 이하로 지정하면 비활성화한다. 기본값은 -1이다.|
|setTimeOnBorrow(boolean)|풀에서 커넥션을 가져올 때 검사 여부를 지정한다. 기본값은 false이다.|
|setTestOnReturn(boolean)|풀에 커넥션을 반환할 때 검사 여부를 지정한다. 기본값은 false이다.|
|setTestWhileIdle(boolean)|커넥션이 풀에 유휴 상태로 있는 동안 검사할지 여부를 지정한다. 기본값은 false이다.|
|setMinEvictableIdleTimeMillis(int)|커넥션 풀에 유효 상태를 유지할 최초 시간을 밀리초 단위로 지정한다. testWhileIdle이 true이면 유휴 시간이 이 값을 초과한 커넥션을 풀에서 제거한다. 기본값은 60000밀리초(60초)이다.|
|setTimeBetweenEvictionRunsMillis(int)|커넥션 풀의 유효 커넥션을 검사할 주기를 밀리초 단위로 지정한다. 기본값은 5000밀리초(5초)이다. 이 값을 1초 이하로 설정하면 안된다.|

- 위 설정을 이해하려면 커넥션의 상태를 알아야 한다. 커넥션 풀은 커넥션을 생성하고 유지한다. 
- 커넥션 풀에 커넥션을 요청하면 해당 커넥션은 활성(active) 상태가 되고, 커넥션을 다시 커넥션 풀에 반환하면 유휴(idle) 상태가 된다. 
- DataSource#getConnection()을 실행하면 커넥션 풀에서 커넥션을 가져와 커넥션이 활성 상태가 된다. 
- 반대로 커넥션을 종료(close)하면 풀로 돌아가 유휴 상태가 된다. 

#### src/main/java/dbquery/DbQuery.java

```java
package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbQuery {
	private DataSource dataSource;
	
	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection(); // 풀에서 구함
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM member")) {
				rs.next();
				return rs.getInt(1);
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close(); // 풀에 반환
				} catch (SQLException e) {}
			}
		}
	}
}
```

- 아래 코드를 실행하면 DataSource에서 커넥션을 구하는데 이때 풀에서 커넥션을 가져온다.
- 이 시점에서 커넥션 conn은 활성 상태이다. 

```java
conn = dataSource.getConnection();
```

- 커넥션 사용이 끝나고 커넥션을 종료하면 실제 커넥션을 끊지 않고 풀에 반환한다. 풀에 반환된 커넥션은 다시 유휴 상태가 된다.

```java
conn.close();
```

- <b>maxActive</b>는 <b>활성 상태가 가능한 최대 커넥션 개수를 지정</b>한다. maxActive를 40으로 지정하면 이는 동시에 커넥션 풀에서 가져올 수 있는 커넥션 개수가 40개라는 뜻이다. 활성 상태 커넥션이 40개인데 커넥션 풀에 다시 커넥션을 요청하면 다른 커넥션이 반환될 때까지 대기한다. 이 대기 시간이 maxWait이다. 대기 시간 내에 풀이 반환된 커넥션이 있으면 해당 커넥션을 구하게 되고, 대기 시간 내에 반환된 커넥션이 없으면 익셉션이 발생한다.

- <b>커넥션 풀을 사용하는 이유는 성능 때문이다.</b> 매번 새로운 커넥션을 생성하면 그때마다 연결 시간이 소모된다. 커넥션 풀을 사용하면 미리 커넥션을 생성했다가 필요할 때에 커넥션을 꺼내 쓰므로 커넥션을 구하는 시간이 줄어 전체 응답 시간도 짧아진다. 그래서 커넥션 풀을 초기화할 때 최소 수준의 커넥션을 미리 생성하는 것이 좋다. 이때 생성할 커넥션 개수를 initialSize로 지정한다.

- 커넥션 풀에 생성된 커넥션은 지속적으로 재사용된다. 그런데 한 커넥션이 영원히 유지되는 것은 아니다. DBMS 설정에 따라 일정 시간 내에 쿼리를 실행하지 않으면 연결을 끊기도 한다. 예를 들어 DBMS에 5분 동안 쿼리를 실행하지 않으면 DB 연결을 끊도록 설정했는데, 커넥션 풀에 특정 커넥션을 5분 넘게 유휴 상태로 존재했다고 하자. 이 경우 DBMS에 해당 커넥션의 연결을 끊지만 커넥션은 여전히 풀 속에 남아 있다. 이 상태에서 해당 커넥션을 풀에서 가져와 사용하면 연결이 끊어진 커넥션이므로 익셉션이 발생하게 된다.

- 이런 문제를 방지하려면 커넥션 풀의 커넥션이 유효한지 주기적으로 검사해야 한다. 이와 관련된 속성이 <b>minEvictableIdleTimeMillis, timeBetweenEvictionRunsMillis, testWhileIdle</b>이다.
- 예를 들어 10초 주기로 유휴 커넥션이 유효한지 여부를 검사하고 최소 유휴시간을 3분으로 지정하고 싶다면 다음과 같이 설정한다.

```java
@Bean(destroyMethod = "close")
public DataSource dataSource() {
	DataSource ds = new DataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	ds.setUsername("spring5");
	ds.setPassword("spring5");
	ds.setInitialSize(2);
	ds.setMaxActive(10);
	ds.setTestWhileIdle(true); // 유휴 커넥션 감시
	ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);  // 최소 유휴 시간 3분
	ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기
}
```

* * * 
## JdbcTemplate을 이용한 쿼리 실행
- 스프링을 사용하면 DataSource나 Connection, Statement, ResultSet을 직접 사용하지 않고 JdbcTemplate을 이용해서 편리하게 쿼리를 실행할 수 있다.

### JdbcTemplate 생성하기

- 가장 먼저 해야 할 작업은 JdbcTemplate 객체를 생성하는 것이다.

#### src/main/java/spring/MemberDao.java

```java
... 생략

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	... 생략
}
```

- MemberDao 클래스에 JdbcTemplate 객체를 생성하는 코드를 추가한 것이다. JdbcTemplate 객체를 생성하려면 DataSource를 생성자에 전달하면 된다. 
- 이를 위해 DataSource를 주입받도록 MemberDao 클래스의 생성자를 구현했다.
- 물론 다음과 같이 설정 메서드 방식을 이용하여 DataSource를 주입받고 JdbcTemplate을 생성해도 된다.

```java
public class MemberDao {
	private jdbcTemplate jdbcTemplate;
	
	public setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
```

- JdbcTemplate을 생성하는 코드를 MemberDao  클래스에 추가했으니 스프링 설정에 MemberDao 빈 설정을 추가한다.

#### src/main/java/config/AppCtx.java

```java
... 생략 

import spring.MemberDao;

@Configuration
public class AppCtx {
	
	... 생략
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
```

### JdbcTemplate을 이용한 조회 쿼리 실행

- JdbcTemplate 클래스는 SELECT  쿼리 실행을 위한 query() 메서드를 제공한다.
	- List<T> query(String sql, RowMapper<T> rowMapper)
	- List<T> query(String sql, Object[] args,  RowMapper<T> rowMapper)
	- List<T> query(String sql, RowMapper<T> rowMapper, Object... args)
	
- query 메서드는 sql 파라미터로 전달받은 쿼리를 실행하고 rowMapper를 이용해서 ResultSet의 결과를 자바 객체로 변환한다.
- sql 파라미터가 아래와 같이 인덱스 기반 파라미터를 가진 쿼리이면 args 파라미터를 이용해서 각 인덱스 파라미터의 값을 지정한다.

```
SELECT * FROM member WHERE email = ?
```

- 쿼리 실행 결과를 자바 객체로 변환할 떄 사용하는 RowMapper 인터페이스는 다음과 같다.

```java
package org.springframework.jdbc.core;

public interface RowMapper<T> {
	T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
```

- RowMapper의 mapRow() 메서드는 SQL 실행 결과로 구한 ResultSet에서 한 행의 데이터를 읽어와 자바 객체로 만드는 매퍼 기능을 구현한다. 
- RowMapper 인터페이스를 구현한 클래스를 작성할 수도 있지만 클래스나 람다식으로 RowMapper의 객체를 생성해서 query() 메서드에 전달할 때도 많다.

#### src/main/java/spring/MemberDao.java
```java
... 생략

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"SELECT * FROM member WHERE email = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate").toLocalDateTime());
						member.setId(rs.getLong("id"));
						return member;
					}
				}, 
				email);
					
		return results.isEmpty() ? null : results.get(0);
	}
	... 생략 
}
```
- JdbcTemplate의 query() 메서드를 이용해서 쿼리를 실행한다. 이 쿼리는 인덱스 파라미터(물음표)를 포함하고 있다. 인덱스 파라미터에 들어갈 값은 email로 지정한다.
- 이 query() 메서드의 세 번째 파라미터는 가변 인자로 인덱스 파라미터가 두 개 이상이면 다음과 같이 인덱스 파라미터 설정에 사용할 각 값을 콤마로 구분한다.

```java
List<Member> results = jdbcTemplate.query(
	"SELECT * FROM member WHERE email = ? and name = ?",
	new RowMapper<Member>() { ... 생략 }, 
	email, name); // 물음표 개수만큼 해당되는 값 전달
```

- 임의 클래스를 이용해서 RowMapper의 객체를 전달하고 있다. 이 RowMapper는 ResultSet에서 데이터를 읽어와 Member 객체로 변환해주는 기능을 제공하므로 RowMapper의 타입 파라미터로 Member를 사용했다(RowMapper<Member>타입)
- mapRow() 메서드는 파라미터로 전달받은 ResultSet에서 데이터를 읽어와 Member 객체를 생성해서 리턴하도록 구현하였다.

- 람다식을 사용하면 임의 클래스를 사용하는 것보다 간결하다.

```java
List<Member> results = jdbcTemplate.query(
	"SELECT * FROM member WHJERE email = ?",
	(ResultSet rs, int rowNum) -> {
		Member member = new Member(
			rs.getString("email"),
			rs.getString("password"),
			rs.getString("name"),
			rs.getTimestamp("regDate").toLocalDateTime());
		member.setId(rs.getLong("id"));
		return member;
	},
	email);
```
- 동일한 RowMapper 구현을 여러 곳에서 사용한다면 아래 코드처럼 RowMapper 인터페이스를 구현한 클래를 만들어서 코드 중복을 막을 수 있다.

```java
// RowMapper를 구현한 클래스를 작성
public class MemberRowMapper implements RowMapper<Member> {
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(
			rs.getString("email"),
			rs.getString("password"),
			rs.getString("name"),
			rs.getTimestamp("regDate").toLocalDateTime());
		member.setId(rs.getLong("id"));
		return member;
	}
}

// MemberRowMapper 객체 생성
List<Member> results = jdbcTemplate(
	"SELECT * FROM member WHERE email = ? AND name = ?", 
	new MemberRowMapper(),	
	email, name);
```

- selectByEmail() 메서드는 지정한 이메일에 해당하는 member 데이터가 존재하면 해당 Member 객체를 리턴하고 그렇지 않다면 null을 리턴하도록 구현했다. 

```java
List<Member> results = jdbcTemplate.query(
	"SELECT * FROM member WHERE email = ?",
	new RowMapper<Member>() { ... 생략 },
	email);
	
return results.isEmpty() ? null : results.get(0); 
```

- query() 메서드는 쿼리를 실행한 결과가 존재하지 않으면 길이가 0인 List를 리턴하므로 List가 비어 있는지 여부로 결과가 존재하지 않는지 확인할 수 있다.
- MemberDao에서 JdbcTemplate의 query()를 사용하는 또 다른 메서드는 selectAll()로 다음 처럼 구현할 수 있다. 참고로 selectAll() 메서드의 리턴 타입을 Collection<Member>에서 List<Member>로 변경했다.

#### src/main/java/spring/MemberDao.java

```java
package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	... 생략
		
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("SELECT * FROM member",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
							Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate").toLocalDateTime());
							member.setId(rs.getLong("id"));
							return member;
						}
				});
		return results;
	}
}
```

- 위 코드는 selectByEmail() 메서드와 동일한 RowMapper 임의 클래스를 사용했다. 
- 다음과 같이 Member를 위한 RowMapper 구현 클래스를 이용하도록 두 메서드를 수정하면 RowMapper 임의 클래스나 람다식 중복을 제거할 수 있다.

```java
public Member selectByEmail(String email) {
	List<Member> results = jdbcTemplate.query(
		"SELECT * FROM member WHERE email = ?",
		new MemberRowMapper(),
		email);
	
	return results.isEmpty() ? null : results.get(0);
}

public Collection<Member> selectAll() {
	List<Member> results = jdbcTemplate.query("SELECT * FROM member", new MemberRowMapper());
	
	return results;
}
```

#### 결과가 1행인 경우 사용할 수 있는 queryForObject() 메서드
- 다음은 member 테이블의 전체 행 개수를 구하는 코드이다. 이 코드는 query() 메서드를 사용했다.

```java
public int count() {
	List<Integer> results = jdbcTemplate.query(
		"SELECT COUNT(*) FROM member",
		new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
	return results.get(0);
}
```

- COUNT(\*) 쿼리는 결과가 한 행 뿐이니 쿼리 결과를 List로 받기보다는 Integer와 같은 정수 타입으로 받으면 편리할 것이다. 
- 이를 위한 메서드가 바로 queryForObject()이다. 
- queryForObject()를 이용하면 COUNT(\*) 쿼리 실행 코드를 다음처럼 구현할 수 있다(MemberDao.java에 count() 메서드를 추가한다).

#### src/main/java/spring/MemberDao.java

```java

... 생략

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	... 생략
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM member", Integer.class);
		return count;
	}
}
```

- queryForObject()  메서드는 쿼리 실행 결과 행이 한 개인 경우에 사용할 수 있는 메서드다.
- queryForObject() 메서드의 두 번째 파라미터는 칼럼을 읽어올 때 사용할 타입을 지정한다.
- 예를 들어 평균을 구한다면 다음처럼 Double 타입을 사용할 수 있다.

```java
double avg = jdbcTemplate.queryForObject(
	"SELECT AVG(height) FROM funiture WHERE type = ? AND status = ?", Double.class, 100, "S");
```

- 위 코드에서 볼 수 있듯이 queryForObject() 메서드도 쿼리에 인덱스 파라미터(물음표)를 사용할 수 있다. 인덱스 파라미터가 존재하면 파라미터 값을 가변 인자로 전달한다.

- 실행 결과 컬럼이 두 개 이상이면 RowMapper를 파라미터로 전달해서 결과를 생성할 수 있다.
- 예를 들어 특정 ID를 갖는 회원 데이터를 queryForObject()로 읽어오고 싶다면 다음 코드를 사용할 수 있다.

```java
Member member = jdbcTemplate.queryForObject(
	"SELECT * FROM member WHERE ID = ?",
	new RowMapper<Member>() {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(rs.getString("email"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getTimestamp("regDate").toLocalDateTime());
			member.setId(rs.getLong("id"));
			return member;
		}
	},
	100);
```
- queryForObject() 메서드를 사용한 위 코드와 기존의 query() 메서드를 사용한 코드의 차이점은 리턴 타입이 List가 아니라 RowMapper로 변환해주는 타입(위 코드에서는 Member)이라는 점이다.
- 주요 queryForObject() 메서드는 다음과 같다.
	- T queryForObject(String sql, Class<T> requiredType)
	- T queryForObject(String sql, Class<T> requiredType, Object... args)
	- T queryForObject(String sql, RowMapper<T> rowMapper)
	- T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
- queryForObject() 메서드를 사용하려면 쿼리 실행 결과는 반드시 한 행이러야 한다. 
- 만약 쿼리 실행 결과 행이 없거나 두 개 이상이면 IncorrectResultSizeDataAccessException이 발생한다. 행의 개수가 0이면 하위 클래스인 EmptyResultDataAccessException이 발생한다.
- 따라서 결과 행이 정확하게 한 개 아니면 queryForObject() 메서드 대신 query() 메서드를 사용해야 한다.

### JdbcTemplate을 이용한 변경 쿼리 실행
- INSERT, UPDATE, DELETE 쿼리는 update() 메서드를 사용한다.
	- int update(String sql)
	- int update(String sql, Object... args)

- update() 메서드는 쿼리 실행 결과로 변경된 행의 개수를 리턴한다. update() 메서드의 사용 예는 다음과 같다.

#### src/main/java/spring/MemberDao.java

```java
... 생략

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	... 생략
	
	public void update(Member member) {
		jdbcTemplate.update(
			"UPDATE member SET name = ?, password = ? WHERE email = ?",
			member.getName(), member.getPassword(), member.getEmail());
	}
	
	... 생략
}
```

### PreparedStatementCreator를 이용한 쿼리 실행
- 지금까지 작성한 코드는 다음과 같이 쿼리에서 사용할 값을 인자로 전달했다.
```java
jdbcTemplate.update(
	"UPDATE member SET name = ?, password = ? WHERE email = ?",
	member.getName(), member.getPassword(), member.getEmail());
```

- 위 코드는 첫 번째 인덱스 파라미터 두 번째 파라미터, 세 번째 파라미터 값으로 각각 member.getName(), member.getPassword(), member.getEmail()을 사용했다. 대 부분 이와 같은 방법으로 쿼리의 인덱스 파라미터의 값을 전달할 수 있다.

- PreparedStatement의 set 메서드를 사용해서 직접 인덱스 파라미터 값을 설정해야 할 때도 있다. 
- 이 경우 PreparedStatement를 생성하고 설정해야 한다.
- PreparedStatementCreator 인터페이스는 다음과 같다.
```java 
package org.springframework.jdbc.corel

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementCreator {
	PreparedStatement createPreparedStatement(Connection conn) throws SQLException;
}
```
- PreparedStatementCreator 인터페이스의 createdPreparedStatement() 메서드는 Connection 타입의 파라미터를 갖는다. 
- PreparedStatementCreator를 구현한 클래스는 createPreparedStatement() 메서드의 파라미터로 전달받는 Connection을 이용해서 PreparedStatement  객체를 생성하고 인덱스 파라미터를 알맞게 설정한 뒤에 리턴하면 된다.

```java
jdbcTemplate.update(new PreparedStatementCreator() {
	@Override
	public PreparedStatement createPreparedStatement(Connection conn) {
		// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
		PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO (email, password, name, regDate) values (?, ?, ?, ?)");
		// 인덱스 파라미터 값 설정
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
		// 생성한 PreparedStatement 객체 리턴
		return pstmt;
	}
});
```
- JdbcTemplate 클래스가 제공하는 메서드 중에서 PreparedStatementCreator 인터페이스를 파라미터로 갖는 메서드는 다음과 같다.

	- List<T> query(PreparedStatementCreator psc, RowMapper<T> rowMapper)
	- int update(PreparedStatementCreator psc)
	- int update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder)

- 위 목록에서 세 번째 메서드는 자동 생성되는 키 값을 구할 떄 사용한다.

### INSERT  쿼리 실행 시 KeyHolder를 이용해서 자동 생성 키값 구하기
- MySQL의 AUTO_INCREMENT 칼럼은 행이 추가되면 자동으로 값이 할당되는 칼럼으로서 주요키 컬럼에 사용된다.
- 앞서 member 테이블을 생성할 때 사용한 쿼리도 다음 코드처럼 주요키 컬럼을 AUTO_INCREMENT 칼럼으로 지정했다.
- AUTO_INCREMENT와 같은 자동 증가 컬럼을 가진 테이블에 값을 삽입하면 해당 컬럼의 값이 자동으로 생성된다. 따라서 아래 코드 처럼 INSERT 쿼리에 자동 증가 컬럼에 해당하는 값은 지정하지 않는다.

```java
// 자동 증가 컬럼인 ID 컬럼의 값을 지정하지 않음
jdbcTemplate.update("INSERT INTO member (email, password, name, regDate") VALUES (?, ?, ?, ?)",
	member.getEmail(), member.getPassword(), member.getName(),
	new Timestamp(member.getRegisterDate().getTime()));
```
- update() 메서드는 변경된 행의 개수를 리턴할 뿐 생성된 키 값을 리턴하지는 않는다.

- JdbcTemplate은 자동으로 생성된 키 값을 구할 수 있는 방법을 제공하고 있다. 그것은 바로 KeyHolder를 사용하는 것이다.
- keyHolder를 사용하면 다음과 같이 MemberDao의 insert() 메서드에 삽입하는 Member 객체의 id 값을 구할 수 있다.

#### src/main/java/spring/MemberDao.java

```java

... 생략

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	... 생략
		
	public void insert(Member member) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO member (email, password, name, regDate) VALUES (?, ?, ?, ?)",
							new String[] { "id" });
					pstmt.setString(1,  member.getEmail());
					pstmt.setString(2,  member.getPassword());
					pstmt.setString(3, member.getName());
					pstmt.setTimestamp(4,  
							Timestamp.valueOf(member.getRegisterDateTime()));
					
					return pstmt;
				}
			}, keyHolder);
			
			Number keyValue = keyHolder.getKey();
			member.setId(keyValue.longValue());
	}
	... 생략 
}
```

* * * 

## 마이바티스(mybatis) 프레임워크 설정하기

* * * 
## 마이바티스(mybatis) 프레임워크 적용하기



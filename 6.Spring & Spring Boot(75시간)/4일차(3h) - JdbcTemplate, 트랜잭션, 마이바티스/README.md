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
- GeneratedKeyHolder 객체를 생성한다. 이 클래스는 자동 생성된 키값을 구해주는 KeyHolder 구현 클래스이다.
- update() 메서드는 PreparedStatementCreator 객체와 KeyHolder 객체를 파라미터로 갖는다.
- PreparedStatementCreator 임의 클래스를 이용하여 PreparedStatement 객체를 직접 생성한다. 
- Connection의 preparedStatement() 메서드를  이용해서 PreparedStatement 객체를 생성하는데 두 번째 파라미터 String 배열인 ["id"]를 주었다. 이 두번째 파라미터는 자동 생성되는 키 컬럼 목록을 지정할 때 사용한다. member 테이블은 id 컬럼이 자동 증가 키 컬럼이므로 두 번째 파라미터 값으로 ["id"]를 주었다.
- JdbcTemplate.update() 메서드의 두 번째 파라미터로 생성한 KeyHolder 객체를 전달한다. 


```java
KeyHolder keyHolder = new GeneratedKeyHolder();
jdbcTemplate.update(new PreparedStatementCreator() { ... 생략 }, keyHolder);
```

- JdbcTemplate의 update() 메서드는 PreparedStatement를 실행한 후 자동 생성된 키값을 KeyHolder에 보관한다. 
- KeyHolder에 보관된 키 값은 getKey() 메서드를 이용해서 구한다. 
- 이 메서드는 java.lang.Number를 리턴하므로 Number의 intValue(), longValue() 등의 메서드를 사용해서 원하는 타입의 값으로 변환할 수 있다. 

```java
Number keyValue = keyHolder.getKey();
member.setId(keyValue.longValue());
```
 
- 람다식을 사용해서 임의 클래스를 이용한 객체 생성 코드를 조금 더 간결하게 작성할 수 있다.

```java
jdbcTemplate.update((Connection conn) -> {
	PreparedStatement pstmt = conn.prepareStatement(
		"INSERT INTO member (email, password, name, regDate) VALUES (?, ?, ?, ?)",
		new String[] {"id"});
	pstmt.setString(1, member.getEmail());
	pstmt.setString(2, member.getPassword());
	pstmt.setString(3, member.getName());
	pstmt.setTimestamp(4, 
		new Timestamp(member.getRegisterDate().getTime()));
	return pstmt;
}, keyHolder);
```


### MemberDao 테스트하기

- member 테이블에 다음과 같이 한 개의 데이터를 추가하자.

```
INSERT INTO member (email, password, name, regDate) 
	VALUES ('yonggyo00@naver.com', '1234', 'lyg', now());
```

#### src/main/java/main/MainForMemberDao.java
```java
package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;

public class MainForMemberDao {
	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
		selectAll();
		updateMember();
		insertMember();
		
		ctx.close();
	}
	
	public static void selectAll() {
		System.out.println("----- selectAll");
		int total = memberDao.count();
		System.out.println("전체 데이터: " + total);
		List<Member> members = memberDao.selectAll();
		for(Member m : members) {
			System.out.println(m.getId() + ":" + m.getEmail() + ":" + m.getName());
		}
	}
	
	private static void updateMember() {
		System.out.println("---- updateMember");
		Member member = memberDao.selectByEmail("yonggyo00@naver.com");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);
		
		memberDao.update(member);
		System.out.println("암호 변경: " + " > " + newPw);
	}
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
	
	private static void insertMember() {
		System.out.println("----- insertMember");
		
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", 
				prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + " 데이터 추가");
	}
}
```

### 스프링의 익셉션 변환 처리

- SQL 문법이 잘못됐을 때 발생한 메시지를 보면 익셉션 클래스가 org.springframework.jdbc 패키지에 속한 BadSqlGrammerException 클래스임을 알 수 있다.
- 에러 메시지를 보면 BadSqlGrammerException이 발생한 이유는 MySQLSyntaxException이 발생했기 때문이다.
- JdbcTemplate의 update() 메서드는 DB 연동을 위해 JDBC API를 사용하는데 JDBC API를 사용하는 과정에서 SQLException이 발생하면 이 익셉션을 알맞은 DataAccessException으로 변환해서 발생한다. 즉 다음과 유사한 방식으로 익셉션을 변환해서 재발생한다.

```java
try {
	... JDBC 사용 코드
} catch (SQLException ex) {
	throw convertSqlToDataException(ex);
}
```

- 예를 들어 MySQL용 JDBC 드라이버는 SQL 문법이 잘못된 경우 SQLException을 상속받은 MySQLSyntaxErrorException을 발생시키는데 JdbcTemplate은 이 익셉션을 DataAccessException을 상속받은 BadSqlGrammerException으로 변환한다.

- DataAccessException은 스프링이 제공하는 익셉션 타입으로 데이터 연결에 문제가 있을 때 스프링 모듈이 발생시킨다. 그렇다면 스프링은 왜 SQLException을 그대로 전파하지 않고 SQLException을 DataAccessException으로 변환할까?
- 주된 이유는 연동 기술에 상관없이 동일하게 익셉션을 처리할 수 있도록 하기 위함이다. 
- 스프링은 JDBC뿐만 아니라 JPA, 하이버네이트 등에 대한 연동을 지원하고 MyBatis는 자체적으로 스프링 연동기능을 제공한다. 그런데 각각의 구현기술마다 익셉션을 다르게 처리해야 한다면 개발자는 기불마다 익셉션 처리 코드를 작성해야 할 것이다. 
- 각 연동 기술에 따라 발생하는 익셉션을 스프링이 제공하는 익셉션으로 변환함으로써 다음과 같이 구현 기술에 상관없이 동일한 코드로 익셉션을 처리할 수 있게 된다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20JdbcTemplate%2C%20%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%2C%20%EB%A7%88%EC%9D%B4%EB%B0%94%ED%8B%B0%EC%8A%A4/images/image2.png)

- 앞에서 BadSqlGrammerException은 DataAccessException을 상속받은 하위 타입이라고 했다. BadSqlGrammerException은 실행할 쿼리가 올바르지 않은 경우에 사용된다.
- 스프링은 이외에도 DuplicateKeyException, QueryTimeoutException 등 DataAccessException을 상속한 다양한 익셉션 클래스를 제공한다.
- 각 익셉션 클래스의 이름은 문제가 발생한 원인을 의미한다. 따라서 익셉션이 발생한 경우 익셉션이 발생한 경우 익셉션 타입의 이름만으로도 어느 정도 문제 원인을 유추할 수 있다.

- DataAccessException은 RuntimeException이다. JDBC를 직접 이용하면 다음과 같이 try~catch를 이용해서 익셉션을 처리해야 하는데(또는 메서드에 throws를 반드시 SQLException을 지정해야 하는데) DataAccessException은 RuntimeException이므로 필요한 경우에만 익셉션을 처리하면 된다.

```java
JDBC를 직접 사용하면 SQLException을 반드시 알맞게 처리해주어야 함.
try {
	pstmt = conn.prepareStatement(someQuery);
	...
} catch(SQLException ex) {
	... // SQLException을 알맞게 처리해 주어야 함 
}

// 스프링을 사용하면 DataAccessException을 필요한 경우에만
// try-catch로 처리해주면 된다.
jdbcTemplate.update(someQuery, param1);
```

### 트랜잭션 처리
- 이메일이 유효한지 여부를 판단하기 위해 실제로 검증 목적의 메일을 발송하는 서비스를 사용한 경험이 있을 것이다. 이들 서비스는 이메일에 함께 보낸 링크를 클릭하면 최종적으로 이메일이 유효하다고 판단하고 해당 이메일을 사용할 수 있도록 한다. 이렇게 이메일 인증 시점에 테이블의 데이터를 변경하는 기능은 다음 코드처럼 회원 정보에서 이메일을 수정하고 인증 상태를 변경하는 두 쿼리를 실행할 것이다.

```java
jdbcTemplate.update("UPDATE member SET email = ?", email);
jdbcTemplate.update("INSERT INTO email_auth VALUES (?, 'T')"', email);
```

- 그런데 만약 첫 번째 쿼리를 실행한 후 두 번째 쿼리를 실행하는 시점에 문제가 발생하면 어떻게 될까? 두 번째 쿼리가 실패했음에도 불구하고 첫 번째 쿼리 실행 결과가 DB에 반영되면 이후 해당 사용자의 이메일 주소는 인증되지 않은 채로 계속 남아 있게 될 것이다.
- 따라서 두 번째 쿼리 실행에 실패하면 첫 번째 쿼리 실행 결과도 취소해야 올바른 상태를 유지한다.
- 이렇게 두 개 이상의 쿼리를 한 작업으로 실행해야 할 떄 사용하는 것이 트랜잭션(transaction)이다. 트랙잭션은 여러 쿼리를 논리적으로 하나의 작업으로 묶어준다.
- 한 트랜잭션으로 묶인 쿼리 중 하나라도 실패하면 전체 쿼리를 실패로 간주하고 실패 이전에 실행한 쿼리를 취소한다. 쿼리 실행 결과를 취소하고 DB를 기존 상태로 되돌리는 것을 롤백(rollback)이라고 부른다. 
- 반면에 트랜잭션으로 묶인 모든 쿼리가 성공해서 쿼리 결과를 DB에 실제로 반영하는 것을 커밋(commit)이라고 한다.
- 트랜잭션을 시작하면 트랜잭션을 커밋하거나 롤백할 때까지 실행한 쿼리들을 하나의 작업단위가 된다. 
- JDBC는 Connection의 setAutoCommit(false)를 이용해서 트랜잭션을 시작하고 commit()과 rollback()을 이용해서 트랜잭션을 시작하고 commit()과 rollback()을 이용해서 트랜잭션을 반영(커밋)하거나 취소(롤백) 한다.

```java
Connection conn = null;
try {
	conn = DriverManager.getConnection(jdbcUrl, user, pw);
	conn.setAutoCommit(false); // 트랜잭션 범위 시작
	... 쿼리 실행
	conn.commit(); // 트랜잭션 범위 종료 : 커밋
} catch (SQLException ex) {
	if (conn != null) {
		// 트랜잭션 범위 종료 : 롤백
		try { conn.rollback(); } catch (SQLException e) {}
	}
} finally {
	if (conn != null) {
		try { conn.close(); } catch (SQLException e) {}
	}
}
```
- 위와 같은 방식은 코드로 직접 트랜잭션 범위를 관리하기 때문에 개발자가 트랜잭션을 커밋하는 코드나 롤백하는 코드를 누락하기 쉽다. 게다가 구조적인 중복이 반복되는 문제도 있다. 
- 스프링이 제공하는 트랜잭션 기능을 사용하면 중복이 없는 매우 간단한 코드로 트랜잭션 범위를 지정할 수 있다.

### @Transactional을 이용한 트랜잭션 처리

- 스프링이 제공하는 @Transactional 애노테이션을 사용하면 트랜잭션 범위를 매우 쉽게 지정할 수 있다.  
- 다음과 같이 트랜잭션 범위에서 실행하고 싶은 메서드에 @Transactional 애노테이션만 붙이면 된다.

```java
import org.springframework.transaction.annotation.Transactional;

@Transactional
public void changePassword(String email, String oldPwd, String newPwd) {
	Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
}
```

- 스프링은 @Transactional 애노테이션이 붙은 changePassword() 메서드를 동일한 트랜잭션 범위에서 실행한다. 
- 따라서 memberDao.selectByEmail()에서 실행하는 쿼리와 member.changePassword()에서 실행하는 쿼리는 한 트랜잭션에 묶인다.

- @Transactional 애노테이션이 제대로 동작하려면 다음의 두 가지 내용을 스프링 설정에 추가해야 한다.
	- 플랫폼 트랜잭션 매니저(PlatformTransactionManager) 빈 설정
	- @Transactional 애노테이션 활성화 설정
	

```java
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppCtx {
	@Bean(destoryMethod = "close")
	public DataSource dataSource() {
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		.. 생략 
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean 
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
```

- PlatformTransactionManager는 스프링이 제공하는 트랜잭션 매니저 인터페이스이다. 
- 스프링은 구현기술에 상관없이 동일한 방식으로 트랜잭션을 처리하기 위해 이 인터페이스를 사용한다. 
- JDBC는 DataSourceTransactionManager 클래스를 PlatformTransactionManager로 사용한다. 
- 위 설정에서 보듯이 dataSource 프로퍼티를 이용해서 트랜잭션 연동에 사용할 DataSource를 지정한다.

- @EnableTransactionManagement 애노테이션은 @Transactional 애노테이션이 붙은 메서드를 트랜잭션 범위에서 실행하는 기능을 활성화한다. 등록된 PlatformTransactionManager 빈을 사용해서 트랜잭션을 적용한다.
- 트랜잭션 처리를 위한 설정을 완료하면 트랜잭션 범위에서 실행하고 싶은 스프링 빈 객체의 메서드에 @Transactional 애노테이션을 붙이면 된다. 
- 예를 들어 ChangePasswordService 클래스의 changePassword() 메서드를 트랜잭션 범위에서 실행하고 싶으면 changePassword() 메서드에 @Transaction 애노테이션을 붙이면 된다.

#### src/main/java/spring/ChangePasswordService.java

```java
package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	private MemberDao memberDao;

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
```

#### src/main/java/config/AppCtx.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.MemberDao;
import spring.ChangePasswordService;

@Configuration
@EnableTransactionManagement
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
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
}
```
- changePwdSvc 빈을 이용해서 암호 변경 기능을 실행하는 메인 클래스는 다음과 같이 작성한다.

#### src/main/java/main/MainForCPS.java

```java
package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {
	public static void main(String[] args) {	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			cps.changePassword("yonggyo00@naver.com", "1234", "1111");
		} catch (MemberNotFoundException e) {
			System.out.println("회원 데이터가 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}
		
		ctx.close();
	}
}
```

- 위 코드를 실행하면 실제로 트랜잭션이 시작되고 커밋이 되는지 확인할 수 없다. 
- 이를 확인하는 방법은 스프링이 출력하는 로그 메시지를 보는 것이다.
- 트랜잭션과 관련 로그 메시지를 추가로 출력하기 위해 Logback을 사용해보자
> 스프링5 버전은 자체 로깅 모듈인 spring-jcl을 사용한다. 이 로깅 모듈은 직접 로그를 남기지 않고 다른 로깅 모듈을 사용해서 로그를 남긴다. 예를 들어 클래스 패스에 Logback이 존재하면 Logback을 이용해서 로그를 남기고 Log4j2가 존재하면 Log4j2를 이용해서 로그를 남긴다. 따라서 사용할 로깅 모듈만 클래스 패스에 추가해주면 된다.

- pom.xml 파일에 slf4j-api, logback-classic 의존성 모듈을 [mvnrepository](https://mvnrepository.com/) 에서 찾아 다음과 같이 적용한다.

```xml
... 생략
<dependencies>
	... 생략
	
	<dependency>
	   <groupId>org.slf4j</groupId>
	   <artifactId>slf4j-api</artifactId>
	   <version>1.7.36</version>
	</dependency>
	<dependency>
	   <groupId>ch.qos.logback</groupId>
	   <artifactId>logback-classic</artifactId>
	   <version>1.2.11</version>
	</dependency>
	
	... 생략
</dependencies>
... 생략
```
- 클래스 패스에 Logback 설정 파일을 위치시켜야 하므로 src/main/resources 폴더도 추가한다.
- 의존 설정과 src/main/resources 폴더를 추가했다면 이클립스에서 인식하도록 프로젝트를 업데이트 해야 한다. 
- 프로젝트에서 마우스 오른쪽 버튼을 클릭한 뒤 [Maven] -> [Update Project] 메뉴를 실행하면 프로젝트 정보를 업데이트 한다.

- Logback은 로그 메시지 형식과 기록 위치를 설정 파일에서 읽어온다. 이 설정 파일을 src/main/resources에 다음과 같이 작성한다. 

#### src/main/resources/logback.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<configuration>
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d %5p %c{2} - %m%n</pattern>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="stdout" />
    </root>
    
    <logger name="org.springframework.jdbc" level="DEBUG" />
</configuration>
```


- 아래 설정은 스프링의 JDBC 관련 모듈에서 출력하는 로그 메시지를 상세하기 ("DEBUG" 레벨) 보기 위한 설정이다.
```xml
<logger name="org.springframework.jdbc" level="DEBUG" />
```

- 실행 결과
```
2022-07-02 22:22:41,954 DEBUG o.s.j.d.DataSourceTransactionManager - Creating new transaction with name [spring.ChangePasswordService.changePassword]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2022-07-02 22:22:43,292 DEBUG o.s.j.d.DataSourceTransactionManager - Acquired Connection [ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@17ae7628]]] for JDBC transaction
2022-07-02 22:22:43,300 DEBUG o.s.j.d.DataSourceTransactionManager - Switching JDBC Connection [ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@17ae7628]]] to manual commit
2022-07-02 22:22:43,324 DEBUG o.s.j.c.JdbcTemplate - Executing prepared SQL query
2022-07-02 22:22:43,325 DEBUG o.s.j.c.JdbcTemplate - Executing prepared SQL statement [SELECT * FROM member WHERE email = ?]
2022-07-02 22:22:43,446 DEBUG o.s.j.d.DataSourceTransactionManager - Initiating transaction rollback
2022-07-02 22:22:43,446 DEBUG o.s.j.d.DataSourceTransactionManager - Rolling back JDBC transaction on Connection [ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@17ae7628]]]
2022-07-02 22:22:43,448 DEBUG o.s.j.d.DataSourceTransactionManager - Releasing JDBC Connection [ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@17ae7628]]] after transaction
암호가 올바르지 않습니다.
```
- 트랜잭션을 롤백했다는 로그 메시지가 찍힌다. 도대체 트랜잭션을 시작하고, 커밋하고, 롤백하는 것ㅎ은 누가 어떻게 처리하는 것인가? 이에 관련된 내용을 이해하려면 프록시를 알아야 한다.

### @Transaction과 프록시

- 여러 빈 객체에 공통으로 적용되는 기능을 구현하는 방법으로 AOP를 설명했는데 트랜잭션도 공통 기능 중 하나이다. 
- 스프링은 @Transactional 애노테이션을 이용해서 트랜잭션을 처리하기 위해 내부적으로 AOP를 사용한다. 
- 스프링에서 AOP는 프록시를 통해서 구현된다는 것을 기억한다면 트랜잭션 처리도 프록시를 통해서 이루어진다고 유추할 수 있을 것이다.

- 실제로 @Transactional 애노테이션을 적용하기 위해 @EnableTransactionManagement 애노테이션을 사용하면 스프링은 @Transaction 애노테이션이 적용된 빈 객체를 찾아서 알맞은 프록시 객체를 생성한다.
- 예를 들어 앞서 MainForCPS 예제의 경우 다음과 같은 구조로 프록시를 사용하게 된다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20JdbcTemplate%2C%20%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%2C%20%EB%A7%88%EC%9D%B4%EB%B0%94%ED%8B%B0%EC%8A%A4/images/image3.png)

- ChangePasswordService 클래스의 메서드에 @Transactional 애노테이션이 적용되어 있으므로 스프링은 트랜잭션 기능을 적용한 프록시 객체를 생성한다. 
- MainForCPS 클래스에서 getBean("changePwdSvc", ChangePasswordService.class) 코드를 실행하면 ChangePasswordService 객체 대신에 트랜잭션 처리를 위해 생성한 프록시 객체를 리턴한다.

- 이 프록시 객체는 @Transactional 애노테이션이 붙은 메서드를 호출하면 PlatformTransactionManager를 사용해서 트랜잭션을 시작한다. 트랜잭션을 시작한 후 실제 객체의 메서드를 호출하고, 성공적으로 실행되면 트랜잭션을 커밋한다.

### @Transactional 적용 메서드의 롤백 처리

- 커밋을 수행하는 주체가 프록시 객체였던 것처럼 롤백을 처리하는 주체 또한 프록시 객체이다. 

```java
try {
	cps.changePassword("yonggyo00@naver.com", "1234", "1111");
	System.out.println("암호를 변경했습니다.");
} catch (MemberNotFoundException e) {
	System.out.println("회원 데이터가 존재하지 않습니다.");
} catch (WrongIdPasswordException e) {
	System.out.println("암호가 올바르지 않습니다.");
}
``` 
- 이 코드의 실행 결과를 보면 WrongIdPasswordException이 발생했을 때 트랜잭션이 롤백된 것을 알 수 있다.
- 실제로 @Transactional을 처리하기 위한 프록시 객체는 원본 객체의 메서드를 실행하는 과정에서 RuntimeException이 발생하면 다음과 같이 트랜잭션을 롤백한다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20JdbcTemplate%2C%20%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%2C%20%EB%A7%88%EC%9D%B4%EB%B0%94%ED%8B%B0%EC%8A%A4/images/image4.png)

- 별도 설정을 추가하지 않으면 발생한 익셉션이 RuntimeException일 때 트랜잭션을 롤백한다. 
- WrongIdPasswordException 클래스를 구현할 때 RuntimeException을 상속한 이유는 바로 트랜잭션 롤백을 염두해 두었기 때문이다.

- JdbcTemplate은 DB연동 과정에서 문제가 있으면 DataAccessException을 발생한다고 했는데 DataAccessException 역시 RuntimeException을 상속받고 있다. 따라서 JdbcTemplate의 기능을 실행하는 도중 익셉션이 발생해도 프록시는 트랜잭션을 롤백한다.

- SQLException은 RuntimeException을 상속하고 있지 않으므로 SQLException이 발생하면 트랜잭션을 롤백하지 않는다. 
- RuntimeException 뿐만 아니라 SQLException이 발생하는 경우에도 트랜잭션을 롤백하고 싶다면 @Transactional의 rollbackFor 속성을 사용해야 한다.

```java
@Transactional(rollbackFor = SQLException.class)
public void someMethod() {
	...
}
```

- 위와 같이 @Transactional의 rollbackFor 속성을 설정하면 RuntimeException 뿐만 아니라 SQLException이 발생하는 경우에도 트랜잭션을 롤백한다. 
- 여러 익셉션 타입을 지정하고 싶다면 {SQLException.class, IOException.class}와 같이 배열로 지정하면 된다.
- rollbackFor와 반대 설정을 제공하는 것이 noRollbackFor 속성이다. 이 속성은 지정한 익셉션이 발생해도 롤백시키지 않고 커밋할 익셉션 타입을 지정할 때 사용한다.

### @Transactional의 주요 속성

- @Transactional 애노테이션의 주요 속성은 다음과 같다. 보통 이들 속성을 사용할 일은 없지만 간혹 필요할 때가 있으니 이런 속성이 있다는 정도는 알고 넘어가도록 하.

|속성|타입|설명|
|----|----|-----------|
|value|String|트랜잭션을 관리할 때 사용할 PlatformTransactionManager 빈의 이름을 지정한다. 기본값은 ""이다.|
|propagation|Propagation|트랜잭션 전파 타입을 지정한다.<br>기본값은 Propagation.REQUIRED이다.|
|isolcation|Isolation|트랜잭션 격리 레벨을 지정한다.<br>기본값은 Isolation.DEFAULT이다.|
|timeout|int|트랜잭션 제한 시간을 지정한다. 기본값은 -1로 이 경우 데이터 베이스의 타입아웃 시간을 사용한다. 초 단위로 지정한다.|

> Propagation과 Isolation 열거 타입은 org.springframework.transaction.annotation 패키지에 정의되어 있다.

- @Transactional 애노테이션의 value 속성값이 없으면 등록된 빈 중에서 타입이 PlatformTransactionalManager인 빈을 사용한다. 앞서 AppCtx 설정 클래스는 DataSourceTransactionManager를 트랜잭션 관리자로 사용했다.

```java
// AppCtx 설정 클래스의 플랫폼 트랜잭션 매니져 빈 설정
@Bean
public PlatformTransactionManager transactionManager() {
	DataSourceTransactionManager tm = new DataSourceTransactionManager();
	tm.setDataSource(dataSource());
	return tm;
}
```

- Propagation 열거 타입에 정의되어 있는 값 목록은 다음과 같다. Propagation의 트랜잭션 전파와 관련된 것으로 이에 대한 내용은 뒤에서 설명한다.

#### Propagation 열거 타입의 주요 값

|값|설명|
|----|--------|
|REQUIRED|메서드를 수행하는 데 트랜잭션이 필요하다는 것을 의미한다. 현재 진행중인 트랜잭션이 존재하면 해당 트랜잭션을 사용한다. 존재하지 않으면 새로운 트랜잭션을 생성한다.|
|MANDATORY|메서드를 수행하는 데 트랜잭션이 필요하는 것을 의미한다. 하지만 REQUIRED와 달리 진행 중인 트랜잭션이 존재하지 않을 경우 익셉션이 발생한다.|
|REQUIRES_NEW|항상 새로운 트랜잭션을 시작한다. 진행 중인 트랜잭션이 존재하면 기존 트랜잭션을 일시 중지하고 새로운 트랜잭션을 시작한다. 새로 시작된 트랜잭션이 종료된 뒤에 기존 트랜잭션이 계속된다.|
|SUPPORTS|메서드가 트랜잭션을 필요로 하지는 않지만, 진행 중인 트랜잭션이 존재하면 트랜잭션을 사용한다는 것을 의미한다. 진행 중인 트랜잭션이 존재하지 않더라고 메서드는 정상적으로 동작한다.|
|NOT_SUPPORTED|메서드가 트랜잭션을 필요로 하지 않음을 의미한다. SUPPORTS와 달리 진행 중인 트랜잭션이 존재할 경우 메섣가 실행되는 동안 트랜잭션은 일시 중지되고 메서드 실행이 종료된 후에 트랜잭션을 계속 진행한다.|
|NEVER|메서드가 트랜잭션을 필요로 하지 않는다. 만약 진행 중인 트랜잭션이 존재하면 익셉션이 발생한다.|
|NESTED|진행 중인 트랜잭션이 존재하면 기존 트랜잭션에 중첩된 트랜잭션에서 메서드를 실행한다. 진행 중인 트랜잭션이 존재하지 않으면 REQUIRED와 동일하게 동작한다.|

#### Isolation 열거 타입에 정의된 값

|값|설명|
|----|--------|
|DEFAULT|기본 설정을 사용한다.|
|READ_UNCOMMITED|다른 트랜잭션이 커밋하지 않은 데이터를 읽을 수 있다.|
|READ_COMMITED|다른 트랜잭션이 커밋한 데이터를 읽을 수 있다.|
|REPEATABLE_READ|처음에 읽어 온 데이터와 두 번째 읽어 온 데이터가 동일한 값을 갖는다.|
|SERIALIZABLE|동일한 데이터에 대해서 동시에 두 개 이상의 트랜잭션을 수행할 수 없다.|

### @EnableTransactionManagement 애노테이션의 주요 속성

|속성|설명|
|----|--------|
|proxyTargetClass|클래스를 이용해서 프록시를 생성할지 여부를 지정한다. 기본값은 false로서 인터페이스를 이용해서 프록시를 생성한다.|
|order|AOP 적용 순서를 지정한다. 기본값은 가장 낮은 우선순위에 해당하는 int의 최대값이다.|

### 트랜잭션 전파
- Propagation 열거 타입 값 목록에서 REQUIRED 값의 설명은 다음과 같다.
	- 메서드를 수행하는데 트랜잭션이 필요하다는 것을 의미한다. 현재 진행 중인 트랜잭션이 존재하면 해당 트랜잭션을 사용한다. 존재하지 않으면 새로운 트랜잭션을 생성한다.


* * * 
## 마이바티스(mybatis) 프레임워크 설정하기

* * * 
## 마이바티스(mybatis) 프레임워크 적용하기



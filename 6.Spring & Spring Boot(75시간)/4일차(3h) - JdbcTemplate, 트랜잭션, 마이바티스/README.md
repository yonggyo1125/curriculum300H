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


## 마이바티스(mybatis) 프레임워크 설정하기


## 마이바티스(mybatis) 프레임워크 적용하기



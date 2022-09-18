# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/11Epd3n6HQrR8LWCJ203G8CE0hW7L7eL0?usp=sharing)

# 프로젝트 준비
- 스프링 MVC : 날짜 값 변환, @PathVariable, 컨트롤러 예외 처리 에서 사용한 [예제소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/%ED%95%99%EC%8A%B5%20%EC%98%88%EC%A0%9C) 를 이어서 사용
 
# 스프링 파일 업로드(MultipartFile)

#### src/main/webapp/WEB-INF/web.xml

```xml
	... 생략 
	
	<servlet>
		... 생략
		
		<multipart-config>
            <max-file-size>20971520</max-file-size> <!--  1MB * 20 -->
            <max-request-size>41943040</max-request-size> <!-- 40MB -->
            <file-size-threshold>20971520</file-size-threshold> <!--  20MB -->
        </multipart-config>
    </servlet>
	
	... 생략
```

#### src/main/java/controller/FileController.java 

```java
package controller;

import java.io.IOException;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.FileService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/upload")
	public String form() {
		return "file/upload";
	}
	
	@ResponseBody
	@PostMapping("/upload")
	public void  process(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		if (file != null) {
			String uploadDir = request.getServletContext().getRealPath("/") + "/../resources/static/upload";
			File _uploadDir = new File(uploadDir);
			if (!_uploadDir.isDirectory()) {
				_uploadDir.mkdir();
			}
			
			fileService.upload(uploadDir, file);
		}
	}
}
```


#### src/main/java/config/ControllerConfig.java 

```java

... 생략

import controller.FileController;

@Configuration
public class ControllerConfig {
	... 생략 
	
	@Bean
	public FileController fileController() {
		return new FileController();
	}
}
```


#### src/main/java/spring/FileService.java

```java
package spring;

import java.io.*;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
		
	/**
	 * 파일 업로드 처리
	 * 
	 */
	public boolean upload(String uploadDir, MultipartFile file)  {
		if (uploadDir.isBlank() || file == null) {
			return false;
		}
		
		/** 업로드 처리 S */
		UUID uuid = UUID.randomUUID();
		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String savedFileName = uuid.toString() + extension;
		String uploadPath = uploadDir + File.separator + savedFileName;
		try (FileOutputStream fos = new FileOutputStream(uploadPath);
			BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		/** 업로드 처리 E */
		
		return true;
	}
}
```

#### src/main/java/config/MvcConfig.java

```java
... 생략

import interceptor.AuthCheckInterceptor;
import spring.FileService;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	... 생략
	
	@Bean
	public FileService fileService() {
		return new FileService();
	}
}
```
#### src/main/webapp/WEB-INF/view/file/upload.jsp

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var='url' value="/file/upload" />
<form:form action="${url}"  method="post" enctype="multipart/form-data">   
    <dl>
        <dt>파일 선택 :</dt> 
        <dd>
            <input type="file" name="file">
        </dd>
    </dl>
    <input type="submit" value="업로드" />
</form:form>
```

- 실행 결과

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/10%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C(MultipartFile)%2C%20%ED%94%84%EB%A1%9C%ED%95%84%EA%B3%BC%20%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0%20%ED%8C%8C%EC%9D%BC/images/image3.png)

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/10%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C(MultipartFile)%2C%20%ED%94%84%EB%A1%9C%ED%95%84%EA%B3%BC%20%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0%20%ED%8C%8C%EC%9D%BC/images/image2.png)


* * * 
# 프로필과 프로퍼티 파일

## 프로필

- 개발을 진행하는 동안에는 실제 서비스 목적으로 운영중인 DB를 이용할 수는 없다. 개발하는 동안에는 개발용 DB를 따로 사용하거나 개발 PC에 직접 DB를 설치해서 사용한다. 지금까지의 예제에서 사용한 커넥션 풀 설정을 봐도 아래 코드처럼 로컬 PC에 설치한 MySQL을 사용했다.

```java
@Bean(destroyMethod = "close")
public DataSource dataSource() {
	DataSource ds = new DataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
	ds.setUsername("spring5");
	ds.setPassword("spring5");
	... 생략
	
	return ds;
}
```

- 실제 서비스 환경에서는 웹 서버와 DB 서버가 서로 다른 장비에 설치된 경우가 많다. 개발 환경에서 사용한 DB 계정과 실 서비스 환경에서 사용할 DB 계정이 다른 경우도 흔하다. 즉 개발을 완료한 어플리케이션을 실제 서버에 배포하려면 실 서비스 환경에 맞는 JDBC 연결 정보를 사용해야 한다.

- 실 서비스 장비에 배포하기 전에 설정 정보를 변경하고 배포하는 방법을 사용할 수도 있지만 이 방법은 너무 원시적이다. 이 방법은 실수하기 쉽다. 실 서비스 환경에 맞는 설정으로 수정하는 과정에서 오타를 입력할 수 있고 개발 환경설정을 실 서비스 환경에 배포할 수도 있다. 반대로 실 서비스 정보를 그대로 두고 개발을 진행할 수도 있다.

- 이런 실수를 방지하는 방법은 처음부터 개발 목적 설정과 실 서비스 목적의 설정을 구분해서 작성하는 것이다. 이를 위한 스프링 기능이 프로필(profile)이다.

- 프로필은 논리적인 이름으로서 설정 집합에 프로필을 지정할 수 있다. 스프링 컨테이너는 설정 집합 중에서 지정한 이름을 사용하는 프로필을 선택하고 해당 프로필에 속한 설정을 이용해서 컨테이너를 초기화할 수 있다. 예를 들어 로컬 개발 환경을 위한 DataSource 설정을 "dev" 프로필로 지정하고 실 서비스 환경을 위한 DataSource 설정을 “real" 프로필로 지정한 뒤, "dev" 프로필을 사용해서 스프링 컨테이너를 초기화할 수 있다. 그러면 스프링은 다음과같이 "dev" 프로필에 정의된 빈을 사용하게 된다.


![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/10%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C(MultipartFile)%2C%20%ED%94%84%EB%A1%9C%ED%95%84%EA%B3%BC%20%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0%20%ED%8C%8C%EC%9D%BC/images/image1.png)

### @Configuration 설정에서 프로필 사용하기

- @Configuration 어노테이션을 이용한 설정에서 프로필을 지정하려면 @Profile 애노테이션을 이용한다.

#### src/main/java/config/DsDevConfig.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DsDevConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
}
```

- @Profile은 "dev"를 값으로 갖는다. 스프링 컨테이너를 초기화할 때 "dev" 프로필을 활성화하면 DsDevConfig 클래스를 설정으로 사용한다.

- "dev"가 아닌 "real" 프로필을 활성화했을 때 사용할 설정 클래스는 다음과 같이@Profile 애노테이션의 값으로 "real"을 지정한다.

#### src/main/java/config/DsRealConfig.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("real")
public class DsRealConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://realdb/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
}
```

#### src/main/java/config/MemberConfig.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;


@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource);
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource);
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public AuthService authService() {
		AuthService authService = new AuthService();
		authService.setMemberDao(memberDao());
		return authService;
	}
}
```

- DsDevConfig 클래스와 DsRealConfig 클래스는 둘 다 이름이 "dataSource"인 DataSource 타입의 빈을 설정하고 있다.
- 두 "dataSource" 빈 중에서 어떤 빈을 사용할지는 활성화한 프로필에 따라 달라진다. "dev” 프로필을 활성화하면 @Profle("dev") 애노테이션을 붙인 설정 클래스의 dataSource 빈을 사용하고 "real" 프로필을 활성화하면 @Profile("real") 애노테이션을 붙인 설정 클래스의 dataSource 빈을 사용한다.

- 특정 프로필을 선택하려면 컨테이너를 초기화하기 전에 setActiveProfiles() 메서드를 사용해서 프로필을 선택해야 한다.

#### src/main/java/config/MainProfile.java

```java
package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DsDevConfig;
import config.DsRealConfig;
import config.MemberConfig;
import spring.Member;
import spring.MemberDao;

public class MainProfile {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev");
		context.register(MemberConfig.class, DsDevConfig.class, DsRealConfig.class);
		context.refresh();
			
		MemberDao dao = context.getBean(MemberDao.class);
			
		List<Member> members = dao.selectAll();
 		members.forEach(m -> System.out.println(m.getEmail()));
 			
		context.close();
	}
}
```

- getEnvironment() 메서드는 스프링 실행 환경을 설정하는데 사용되는 Environment를 리턴한다. 이 Environment의 setActiveProfiles() 메서드를 사용해서 사용할 프로필을 선택할 수 있다. 위 코드는 "dev”를 값으로 주었으므로 "dev" 프로필에 속한 설정이 사용된다. 따라서 DsDevConfig 클래스와 DsRealConfig 클래스에 정의되어 있는 “dataSource" 중에서 "dev" 프로필에 속하는 DsDevConfig에 정의된 “dataSource" 빈을 사용한다.

- 프로필을 사용할 때 주의할 점은 설정 정보를 전달하기 전에 어떤 프로필을 사용할지 지정해야 한다는 점이다. 위 코드를 보면 setActiveProfiles() 메서드로 "dev" 프로필을 사용한다고 설정한 뒤에 register() 메서드로 설정 파일 목록을 지정했다. 그런 뒤 refresh() 메서드를 실행해서 컨테이너를 초기화했다. 이 순서를 지키지 않고 프로필을 선택하기 전에 설정 정보를 먼저 전달하면 프로필을 지정한 설정이 사용되지 않기 때문에 설정을 읽어오는 과정에서 빈을 찾지 못해 익셉션이 발생한다.

- 두 개 이상의 프로필을 활성화하고 싶다면 다음과 같이 각 프로필 이름을 메서드에 파라미터로 전달한다.

```java
context.getEnvironment().setActiveProfiles("dev", "mysql");
```

- 프로필을 선택하는 또 다른 방법은 spring.profiles.active 시스템 프로퍼티에 사용할 프로필 값을 지정하는 것이다. 두 개 이상인 경우 사용할 프로필을 콤마로 구분해서 설정하면 된다. 시스템 프로퍼티는 명령행에서 -D 옵션을 이용하거나 System.setProperty() 를 이용해서 지정할 수 있다. 아래 코드는 -D 옵션을 이용한 설정 예이다.

```java
java -Dspring.profiles.active=dev main.Main
```

- 위와 같이 시스템 프로퍼티로 프로필을 설정하면 setActiveProfiles() 메서드를 사용하지 않아도 "dev" 프로필이 활성화된다.

- 명령행에 -Dspring.profiles.active=dev 옵션을 주거나 System.setProperty("spring.profiles.active", "dev") 코드로 spring.profiles.active 시스템 프로퍼티 값을 "dev"로 설정하면 "dev" 프로파일 활성화한다.

```java

... 생략

public class MainProfile {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		System.setProperty("spring.profiles.active", "dev");
		
		context.register(MemberConfig.class, DsDevConfig.class, DsRealConfig.class);
		context.refresh();
		
		... 생략
	}
}
```

- 자바의 시스템 프로퍼티뿐만 아니라 OS의 "spring. profiles.active" 환경 변수에 값을 설정해도 된다. 프로필 우선 순위는 다음과 같다.
	- setActiveProfiles()
	- 자바 시스템 프로퍼티
	- OS 환경 변수


###  @Configuration을 이용한 프로필 설정

- 중첩 클래스를 이용해서 프로필 설정을 한 곳으로 모을 수 있다. 다음은 그 예이다

#### src/main/java/config/MemberConfigWithProfile.java
```
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfigWithProfile {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource);
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource);
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public AuthService authService() {
		AuthService authService = new AuthService();
		authService.setMemberDao(memberDao());
		return authService;
	}
	
	@Configuration
	@Profile("dev")
	public static class DsDevConfig {
		
		@Bean(destroyMethod = "close")
		public DataSource dataSource() {
			DataSource ds = new DataSource();
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
			ds.setUsername("spring5");
			ds.setPassword("spring5");
			ds.setInitialSize(2);
			ds.setMaxActive(10);
			ds.setTestWhileIdle(true);
			ds.setMinEvictableIdleTimeMillis(60000 * 3);
			ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
			return ds;
		}
	}
	
	@Configuration
	@Profile("real")
	public static class DsRealConfig {
		
		@Bean(destroyMethod = "close")
		public DataSource dataSource() {
			DataSource ds = new DataSource();
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUrl("jdbc:mysql://realdb/spring5fs?characterEncoding=utf8");
			ds.setUsername("spring5");
			ds.setPassword("spring5");
			ds.setInitialSize(2);
			ds.setMaxActive(10);
			ds.setTestWhileIdle(true);
			ds.setMinEvictableIdleTimeMillis(60000 * 3);
			ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
			return ds;
		}
	}
}
```

#### src/main/java/config/MainProfile.java

```java
package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DsDevConfig;
import config.DsRealConfig;
import config.MemberConfig;
import config.MemberConfigWithProfile;
import spring.Member;
import spring.MemberDao;

public class MainProfile {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev");
		context.register(MemberConfigWithProfile.class);
		context.refresh();
			
		MemberDao dao = context.getBean(MemberDao.class);
			
		List<Member> members = dao.selectAll();
 		members.forEach(m -> System.out.println(m.getEmail()));
 			
		context.close();
	}
}
```

- 중첩된 @Configuration 설정을 사용할 때 주의할 점은 중첩 클래스는 static이어야 한다는 점이다.

### 다수 프로필 설정

- 스프링 설정은 두 개 이상의 프로필 이름을 가질 수 있다. 아래 코드는 real과 test 프로필을 갖는 설정 예이다. real 프로필을 사용할 때와 test 프로필을 사용할 때 모두 해당 설정을 사용한다.

```java
@Configuration
@Profile("real.test")
public class DataSourceJndiConfig {
	... 
}
```

- 프로필 값을 지정할 때 다음 코드처럼 느낌표(!)를 사용할 수도 있다.

```java
@Configuration
@Profile("!real")
public class DsDevConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		... 생략
		return ds;
	}
}
```

- "!real" 값은 "real" 프로필이 활성화되지 않을 때 사용한다는 것을 의미한다. 
- 보통 "!프로필" 형식은 특정 프로필이 사용되지 않을 때 기본으로 사용할 설정을 지정하는 용도로 사용된다.

### 어플리케이션에서 프로필 설정하기

- 웹 어플리케이션의 경우에도 spring.profiles.active 시스템 프로퍼티나 환경 변수를 사용해서 사용할 프로필을 선택할 수 있다. 그리고 web.xml에서 다음과 같이 spring.profiles.active 초기화 파라미터를 이용해서 프로필을 선택할 수 있다.

#### src/main/webapp/WEB-INF/web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>spring,profiles.active</param-name>
            <param-value>dev</param-value>
        </init-param>
        <init-param>
            <param-name>contextClass</param-name>
            <param-value>
                org.springframework.web.context.support.AnnotationConfigWebApplicationContext
            </param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                config.DsDevConfig
                config.DsRealConfig
                config.MemberConfig
                config.MvcConfig
                config.ControllerConfig
            </param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>
            org.springframework.web.filter.CharacterEncodingFilter
        </filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
</web-app>
```

## 프로퍼티 파일을 이용한 프로퍼티 설정

스프링은 외부의 프로퍼티 파일을 이용해서 스프링 반을 설정하는 방법을 제공하고 있다. 예를 들어 다음과 같은 db.properties 파일이 있다고 하자.

#### src/main/resources/db.properties
```
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost/spring5fs?characterEncoding=utf8
db.user=spring5
db.password=spring5
```

- 이 파일의 프로퍼티 값을 자바 설정에서 사용할 수 있으며 이를 통해 설정 일부를 외부 프로퍼티 파일을 사용해서 변경할 수 있다.

###  @Configuration 애노테이션 이용 자바 설정에서의 프로퍼티 사용
- 자바 설정에서 프로퍼티 파일을 사용하려면 다음 두 가지를 설정한다.
	- PropertySourcesPlaceholderConfigurer 빈 설정
	- @Value 애노테이션으로 프로퍼티 값 사용

- 먼저 PropertySourcesPlaceholderConfigurer 클래스를 빈으로 등록한다.

#### src/main/java/config/PropertyConfig.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocations(
				new ClassPathResource("db.properties"),
				new ClassPathResource("info.properties"));
		return configurer;
	}
}
```

- PropertySourcesPlaceholderConfigurer#setLocations() 메서드는 프로퍼티 파일 목록을 인자로 전달받는다. 이때 스프링의 Resource 타입을 이용해서 파일 경로를 전달한다. 
- db.properties 파일이 클래스 패스에 위치하고 있다면(예, src/main/resources 폴더) ClassPathResource 클래스를 이용해서 프로퍼티 파일 정보를 전달한다.

#### Resource 인터페이스

- o.s.core.io.Resource 인터페이스는 스프링에서 자원을 표현할 때 사용한다. 대표적인 구현 클래스로 다음의 두 가지가 있다. (o.s.c는 org.springframework.core를 줄여서 표현한 것이다.)
	- o.s.c.io.ClassPathResource: 클래스 패스에 위치한 자원으로부터 데이터를 읽음
	- o.s.c.io.FileSystemResource: 파일 시스템에 위치한 자원으로부터 데이터를 읽음

- 위 코드에서 주의해서 볼 점은 PropertySourcesPlaceholderConfigurer 타입 빈을 설정'하는 메서드가 정적(static) 메서드라는 것이다. 이는 PropertySourcesPlaceholderConfigurer 클래스가 특수한 목적의 빈이기 때문이며 정적 메서드로 지정하지 않으면 원하는 방식으로 동작하지 않는다.

- PropertySourcesPlaceholderConfigurer 타입 빈은 setLocations() 메서드로 전달받은 프로퍼티 파일 목록 정보를 읽어와 필요할 때 사용한다. 이를 위한 것이 @Value 애노테이션이다.

#### src/main/java/config/DsConfigWithProp.java

```java
package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DsConfigWithProp {
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String jdbcUrl;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(jdbcUrl);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000  * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
}
```

- @Value 애노테이션이 구분자 형식의 플레이스홀더를 값으로 갖고 있다. 이 경우 PropertySourcesPlaceholderConfigurer는 플레이스홀더의 값을 일치하는프로퍼티 값으로 치환한다. 위 예의 경우 $db. driver 플레이스홀더를 db.properties에정의되어 있는 "db.driver" 프로퍼티 값으로 치환한다. 
- 따라서 실제 빈을 생성하는 메서드(위 코드에서는 dataSource() 메서드)는 @Value 에노테이션이 붙은 필드를 통해서 해당 프로퍼티의 값을 사용할 수 있다

##  빈 클래스에서 사용하기
- 다음과 같이 빈으로 사용할 클래스에도 @Value 애노테이션을 붙일 수 있다.

#### src/main/java/spring/Info.java

```java
package spring;

import org.springframework.beans.factory.annotation.Value;

public class Info {
	
	@Value("${info.version}")
	private String version;
	
	public void printInfo() {
		System.out.println("version = " + version);
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
}
```

- @Value 애노테이션을 필드에 붙이면 플레이스홀더에 해당하는 프로퍼티를 필드에 할당한다. info, version 프로퍼티에 해당하는 값을 version 필드에 할당한다.

- 다음과 같이 @Value 애노테이션을 set 메서드에 적용할 수도 있다.

```java
package spring;

import org.springframework.beans.factory.annotation.Value;

public class Info {
	
	private String version;
	
	public void printInfo() {
		System.out.println("version = " + version);
	}
	
	@Value("${info.version}")
	public void setVersion(String version) {
		this.version = version;
	}
}
```

#### src/main/java/config/InfoConfig.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Info;

@Configuration
public class InfoConfig {

	@Bean
	public Info info() {
		return new Info();
	}

}
```


#### src/main/java/main/MainWithProp.java

```java
package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DsConfigWithProp;
import config.InfoConfig;
import config.MemberConfig;
import config.PropertyConfig;
import spring.Info;
import spring.Member;
import spring.MemberDao;

public class MainWithProp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(PropertyConfig.class, InfoConfig.class, MemberConfig.class, DsConfigWithProp.class);
		context.refresh();
		
		MemberDao dao = context.getBean(MemberDao.class);
		List<Member> members = dao.selectAll();
		members.forEach(m -> System.out.println(m.getEmail()));
		
		Info info = context.getBean(Info.class);
		info.printInfo();
		
		context.close();
	}
}
```
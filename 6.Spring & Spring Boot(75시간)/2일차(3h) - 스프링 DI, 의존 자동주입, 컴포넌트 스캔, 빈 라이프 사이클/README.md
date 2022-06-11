# 스프링 DI 설정 및 사용
- 앞서 의존이 무었이고 DI를 이용해서 의존 객체를 주입하는 방법에 대해서 알아 보았는데, 스프링 자체가 아닌 의존, DI, 조립기에 대해 먼저 알아본 이유는 스프링이 DI를 지원하는 조립기이기 때문이다.

- 실제로 스프링은 앞서 구현한 조립기와 유사한 기능을 제공한다. 즉, 스프링은 Assembler 클래스의 생성자 코드처럼 필요한 객체를 생성하고 객체에 의존을 주입한다.

- 또한 스프링은 Assembler#getMemberRegisterService() 메서드처럼 객체를 제공하는 기능을 정의하고 있다. 차이점이라면 Assembler는 MemberRegisterService나 MemberDao와 같이 특정 타입의 클래스만 생성한 반면 스프링은 범용 조립기라는 점이다.

## 스프링을 이용한 객체 조립과 사용
앞서 구현한 Assembler 대신 스프링을 사용하는 코드를 작성해보자.

#### src/main/java/config/AppCtx.java
```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
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
}
```
- @Configuration 애노테이션은 스프링 설정 클래스를 의미한다. 이 애노테이션을 붙여야 스프링 설정 클래스로 사용할 수 있다.
- @Bean 애노테이션은 해당 메서드가 생성한 객체를 스프링 빈이라고 설정한다. 위 코드에서는 세 개의 메서드에 @Bean 애노테이션을 붙였는데, 각각의 메서드마다 한 개의 빈 객체를 생성한다. 이때 메서드 이름을 빈 객체의 이름으로 사용한다. 예를 들어 memberDao() 메서드를 이용해서 생성한 빈 객체는 "memberDao"라는 이름으로 스프링에 등록된다.
- MemberRegisterService 생성자를 호출할 떄 memberDao() 메서드를 호출한다. 즉, memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입한다.
- ChangePasswordService 타입의 빈을 설정한다. 이 메서드는 세터(setMemberDao() 메서드)를 이용해서 의존 객체를 주입한다.
- 설정 클래스를 만들었다고 해서 끝난 것은 아니다. 객체를 생성하고 의존 객체를 주입하는 것은 스프링 컨테이너이므로 설정 클래스를 이용해서 컨테이너를 생성해야 한다. 앞서 사용한 AnnotationConfigApplicationContext 클래스를 이용해서 스프링 컨테이너를 생성할 수 있다.
```java
AnnotationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
``` 
 
- 컨테이너를 생성하면 getBean() 메서드를 이용해서 사용할 객체를 구할 수 있다.
```java
// 컨테이너에서 이름이 memberRegSvc인 빈 객체를 구한다.
MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
```
- 위 코드는 스프링 컨테이너(ctx)로 부터 이름이 "memberRegSvc"인 빈 객체를 구한다. 앞서 자바 설정을 보면 다음 코드처럼 이름이 "memberRegSvc"인 @Bean 메서드를 설정했다. 이 메서드는 MemberRegisterService  객체에 생성자를 통해 memberDao를 주입한다. 따라서 위 코드에서 구한 MemberRegisterService 객체는 내부에서 memberDao 빈 객체를 사용한다.
```
@Bean
public MemberDao memberDao() {
	return new MemberDao();
}

@Bean
public MemberRegisterService memberRegSvc() {
	return new MemberRegisterService(memberDao());
}
```

#### src/main/java/main/MainForSpring.java
```java
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import assembler.Assembler;
import config.AppCtx;
import spring.*;


public class MainForSpring {
	
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}

		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}
```
MainForSpring 클래스가 MainFormAssembler 클래스와 다른 점은 Assembler 클래스 대신 스프링 컨테이너인 ApplicationContext를 사용했다는 것뿐이다. 차이가 나는 부분은 다음과 같다.

- AnnotationConfigApplicationContext를 사용해서 스프링 컨테이너를 생성한다. 스프링 컨테이너는 Assembler와 동일하게 객체를 생성하고 의존 객체를 주입한다. Assembler는 직접 객체를 생성하는 반면에 AnnotationConfigApplicationContext는 설정 파일 AppCtx 클래스로부터 생성할 객체의 주입 대상을 정한다.
```java
private static ApplicationContext ctx = null;
...
ctx = new AnnotationConfigApplicationContext(AppCtx.class);
```

- 스프링 컨테이너로부터 이름이 "memberRegSvc"인 빈 객체를 구한다.
```java
MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
```

- 스프링 컨테이너로부터 이름이 "changePwdSvc"인 빈 객체를 구한다.
```java
ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
```

### DI 방식 1 : 생성자 방식
앞서 작성한 MemberRegisterService 클래스를 보면 아래 코드처럼 생성자를 통해 의존 객체를 주입받아 필드(this.memberDao)에 할당했다.
```java
public class MemberRegisterService {
	private MemberDao memberDao;
	
	// 생성자를 통해 의존 객체를 주입 받음.
	public MemberRegisterService(MemberDao memberDao) {
		// 주입 받은 의존 객체의 메서드를 사용
		this.memberDao  = memberDao;
	}
	
	public long regist(RegisterRequest req) {
		// 주입 받은 의존 객체의 메서드를 사용
		Member member = memberDao.selectByEmail(req.getEmail());
		...
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
```
스프링 자바 설정에서는 생성자를 이용해서 의존 객체를 주입하기 위해 해당 설정을 담은 메서드를 호출했다.

```java
@Bean
public MemberDao memberDao() {
	return new MemberDao();
}

@Bean
public MemberRegisterService memberRegSvc() {
	return new MemberRegisterService(memberDao());
}
```

생성자 매개변수가 두 개인 예제를 살펴보기 전에 예제를 실행하는데 필요한 코드를 추가하자. 추가할 코드는 MemberDao 클래스의 selectAll() 메서드이다. 아래 코드에서 \*\*로 표기한 곳을 추가한다.
```java
**import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {
	... 
   **public Collection<Member> selectAll() {
		return map.values();
	}
}
```

#### src/main/java/spring/MemberPrinter.java
```java
package spring;

public class MemberPrinter {
	
	public void print(Member member) {
		System.out.printf("회원 정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF%n", 
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
		
	}
}
```

#### src/main/java/spring/MemberListPrinter.java
```java
package spring;

import java.util.Collection;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
}
```

#### src/main/java/config/AppCtx.java
```java
package config;
...
import spring.MemberRegisterService;
import spring.MemberListPrinter;
import spring.MemberPrinter;

@Configuration
public class AppCtx {
	...
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
}
```
#### src/main/java/main/MainForSpring.java
```java
...
public class MainForSpring {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
		
		...
		
		} else if (command.startsWith("change ")) {
			processChangeCommand(command.split(" "));
			continue;
		} else if (command.equals("list")) {
			processListCommand();
			continue;
		}
		printHelp();
	}
	...
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	...
}
```

### DI 방식2 : 세터 메서드 방식
생성자 이외 세터 메서드를 통해 객체를 주입받기도 한다. 일반적인 세터(setter) 메서드는 자바빈 규칙에 따라 다음과 같이 작성한다.
- 메서드 이름이 set으로 시작한다.
- set 뒤에 첫 글자는 대문자로 시작한다.
- 파라미터가 1개이다.
- 리턴 타입이 void 이다.

> 자바빈에서는 게터와 세터를 이용해서 프로퍼티를 정의한다. 예를 들어 Spring getName() 메서드의 void setName(String name) 메서드는 값을 읽고 쓸 수 있는 name 멤버변수 된다. 메서드 이름은 get이나 set으로 시작하고, get과 set 뒤에는 사용할 프로퍼티 이름의 첫 글자를 대문자로 바꾼 글자를 사용한다. age 멤버변수가  있으면 이 프로퍼티를 읽기 위한 일기 메서드는 getAge가 되고 쓰기 메서드는 setAge가 된다.<br><br>set과 get메서드를 각각 세터(setter)와 게터(getter)라고 부르며, setAge와 같은 쓰기 메서드는  멤버 변수 값을 변경하므로 설정 메서드라고 부른다.

#### src/main/java/spring/MemberInfoPrinter.java
```java
package spring;

public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
} 
```

#### src/main/java/config/AppCtx.java
```java
...
import spring.MemberInfoPrinter;
...

@Configuration
public class AppCtx {
	...
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
```

#### src/main/java/main/MainForSpring.java
```java
...
public class MainForSpring {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			...
			
			} else if (command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	
	...
	
	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	...
}
```
- 실행 결과
```
명령어를 입력하세요:
new yonggyo00@kakao.com 이용교 1234 1234
등록했습니다.

명령어를 입력하세요:
info yonggyo00@kakao.com
회원 정보 : 아이디=1, 이메일=yonggyo00@kakao.com, 이름=이용교, 등록일=2022-06-11

명령어를 입력하세요:
```


#### 생성자 방식 또는 세터 메서드
- 생성자 방식은 생성자의 매개변수의 개수가 많을 경우 각 인자가 어떤 의존 객체를 설정하는지 알아내려면 생성자의 코드를 확인해야 한다. 하지만 설정 메서드 방식은 메서드 이름만으로도 어떤 의존 객체를 설정하는지 쉽게 유추할 수 있다.
- 반면에 생성자 방식은 빈 객체를 생성하는 시점에 필요한 모든 의존 객체를 주입받기 때문에 객체를 사용할 때 완전한 상태로 사용할 수 있다. 하지만 세터 메서드 방식은 세터 메서드를 사용해서 필요한 의존 객체를 전달하지 않아도 빈 객체가 생성되기 때문에 객체를 사용하는 시점에 NullPointerException이 발생할 수 있다.


### 기본 데이터 타입 값 설정

#### src/main/java/spring/VersionPrinter.java
```
package spring;

public class VersionPrinter {
	private int majorVersion;
	private int minorVersion;
	
	public void print() {
		System.out.printf("이 프로그램의 버전은 %d, %d입니다. %n%n", majorVersion, minorVersion);
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
}
```
#### src/main/java/config/AppCtx.java
```
...
import spring.VersionPrinter;

@Configuration
public class AppCtx {
	...
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
```
#### src/main/java/main/MainForSpring.java
```
public class MainForSpring {
	
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			...
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}
	...
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}
}
```

- 실행 결과
```
명령어를 입력하세요:
version
이 프로그램의 버전은 5, 0입니다. 

명령어를 입력하세요:
```

## @Configuration 설정 클래스와 @Bean 설정과 싱글톤
```java
@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
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
	...
}
```
- 스프링 컨테이너는 @Bean이 붙은 메서드에 대해 한 개의 객체만 생성한다. 이는 다른 설정 메서드에서 memberDao()를  몇 번을 호출하더라도 항상 같은 객체를 반환한다는 것을 의미한다.
- 스프링은 설정 클래스를 그대로 사용하지 않는다. 대신 설정 클래스를 상속한 새로운 설정 클래스를 만들어서 사용한다. 스프링이 런타임에 생성한 설정 클래스는 다음과 유사한 방식으로 동작한다(다음 코드는 어디까지나 이해를 돕기 위한 가상의 코드일 뿐 실제 스프링 코드는 이보다 훨씬 더 복잡하다).
```
public class AppCtxExt extends AppCtx {
	private Map<String, Object> beans = ...;
	
	@Override
	public MemberDao memberDao() {
		if (!beans.containsKey("memberDao")) 
			beans.put("memberDao", super.memberDao());
			
		return (MemberDao) beans.get("memberDao");
	}
}
```
- 스프링이 런타입에 생성한 설정 클래스의 memberDao() 메서드는 매번 새로운 객체를 생성하지 않는다. 대신 한 번 생성한 객체를 보관했다가 이후에는 동일한 객체를 반환한다. 따라쇼서 memberRegSvc() 메서드와 changePwdSvc() 메서드에서 memberDao() 메서드를 각각 실행해도 동일한 MemberDao 객체를 사용한다.


## 두 개 이상의 설정 파일 사용하기
- 스프링을 이용해서 어플리케이션을 개발하다보면 수십 개에서 많게는 수백여 개 이상의 빈을 설정하게 된다. 설정하는 빈의 개수가 증가하면 한 개의 클래스 파일에 설정하는 것보다 영역별로 설정 파일을 나누면 관리하기 편해진다.

- 스프링은 한 개 이상의 설정 파일을 이용해서 컨테이너를 생성할 수 있다. 

#### src/main/java/config/AppConf1.java
```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
public class AppConf1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
```

#### src/main/java/config/AppConf2.java
```java
package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.*;

@Configuration
public class AppConf2 {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberPrinter memberPrinter;
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao);
		infoPrinter.setPrinter(memberPrinter);
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
```

```
@Autowired
private MemberDao memberDao;
```
- @Autowired 애노테이션은 스프링의 자동주입 기능을 위한 것이다. 이 설정은 의존 주입과 관련있다. 스프링 설정 클래스의 필드에 @Autowired 애노테이션을 붙이면 해당 타입의 빈을 찾아서 필드에 할당한다. 위 설정의 경우 스프링 컨테이너는 MemberDao 타입의 빈을 memberDao 필드에 할당한다. 
- AppConf1 클래스에 MemberDao 타입의 빈을 설정했으므로 AppConf2 클래스의 memberDao 필드에는 AppConf1 클래스에서 설정한 빈이 할당된다.

- @Autowired 애노테이션을 이용해서 다른 설정 파일에 정의한 빈을 필드에 할당했다면 설정 메서드에서 이 필드를 사용해서 필요한 빈을 주입하면 된다. 

```java
@Autowired
private MemberDao memberDao;
	
@Autowired
private MemberPrinter memberPrinter;

@Bean
public MemberListPrinter listPrinter() {
	return new MemberListPrinter(memberDao, memberPrinter);
}
```

설정 클래스가 두 개 이상이어도 스프링 컨테이너를 생성하는 코드는 크게 다르지 않다. 다음과 같이 매개변수로 설정 클래스를 추가로 전달하면 된다.
```java
ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
```
- AnnotationConfigApplicationContext의 생성자의 인자는 가변 인자이기 때문에 설정 클래스의 목록을 콤마로 구분해서 전달하면 된다.

### @Configuration 애노테이션, 빈, @Autowired 애노테이션
@Autowired 애노테이션은 스프링 빈에 의존하는 다른 빈을 자동으로 주입하고 싶을 때 사용한다.
```java
import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memDao;
	
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(Spring email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	...
}
```
두 필드에 @Autowired 애노테이션을 붙였다. 이렇게 @Autowired 애노테이션을 의존 주입 대상에 붙이면 다음 코드처럼 스프링 설정 클래스의 @Bean 메서드에서 의존 주입을 위한 코드를 작성하지 않아도 된다.
```java
@Bean
public MemberInfoPrinter infoPrinter() {
	MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
	// 세터 메서드를 사용해서 의존 주입을 하지 않아도
	// 스프링 컨터네이가 @Autowired를 붙인 필드에 
	// 자동으로 해당 타입의 빈 객체를 주입
	return infoPrinter;
}
```

```java
@Configuration
public class AppConf2 {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberPrinter memberPrinter;
	...
}
```
- 스프링 컨테이너는 설정 클래스에서 사용한 @Autowired에 대해서도 자동 주입을 처리한다. 실제로 스프링은 @Configuration 애노테이션이 붙은 설정 클래스를 내부적으로 스프링 빈으로 등록한다. 그리고 다른 빈과 마찬가지로 @Autowired가 붙은 대상에 대해 알맞은 빈을 자동으로 주입한다.
- 즉, 스프링 컨테이너는 AppConf2 객체를 빈으로 등록하고 @Autowired 애노테이션이 붙은 두 필드 memberDao와 memberPrinter에  해당 타입의 빈 객체를 주입한다. 실제 다음 코드를 실행하면 스프링 컨테이너가 @Configuration 애노테이션을 붙인 설정 클래스를 스프링 빈으로 등록한다는 것을 확인할 수 있다.
```java
AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);

// @Configuration 설정 클래스도 빈으로 등록함
AppConf1 appConf1 = ctx.getBean(AppConf1.class);
System.out.println(AppConf1 != null); // true 출력
```


* * *
# 의존 자동 주입

* * *
# 컴포넌트 스캔

* * *
# 빈 라이프 사이클과 범위

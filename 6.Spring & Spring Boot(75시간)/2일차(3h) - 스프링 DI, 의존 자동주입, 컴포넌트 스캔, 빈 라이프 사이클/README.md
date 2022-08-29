# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1GQ6ssuV_sGAHpPWHw6nOiubhazIqqbNr?usp=sharing)

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
### @Import 애노테이션 사용
두 개 이상의 설정 파일을 사용하는 또 다른 방법은 @Import 애노테이션을 사용하는 것이다. @Import 애노테이션은 함께 사용할 설정 클래스를 지정한다.

#### src/main/java/config/AppConfImport.java
```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf2.class)
public class AppConfImport {
	
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
AppConfImport 설정 클래스를 사용하면, @Import 애노테이션으로 지정한 AppConf2 설정 클래스도 함께 사용하기 때문에 스프링 컨테이너를 생성할 때 AppConf2 설정 클래스를 지정할 필요가 없다.

#### src/main/java/config/MainForSpring.java
```java
public class MainForSpring {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppConfImport.class);
		...
	}
	...
}
```
@Import 애노테이션은 다음과 같이 배열을 이용해서 두 개 이상의 설정 클래스도 지정할 수 있다.
```
@Configuration
@Import({ AppConf1.class, AppConf2.class })
public class AppConfImport {

}
```
> **다중 @Import**<br>MainForSpring에서 AppConfImport를 사용하도록 수정했다면, 이후 다른 설정 클래스를 추가해도 MainForSpring을 수정할 필요가 없다. @Import를 사용해서 포함한 설정 클래스가 다시 @Import를 사용할 수 있다. 이렇게 하면 설정 클래스를 변경해도 AnnotationConfApplicationContext를 생성하는 코드는 최상위 설정 클래스 한 개만 사용하면 된다.

## getBean 메서드 사용
지금까지 생성한 예제는  getBean() 메서드를 이용해서 사용할 빈 객체를 구했다.
```java
VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
```
- getBean() 메서드의 첫 번째 인자는 빈의 이름이고 두 번째 인자는 빈의 타입이다. 
- getBean() 메서드를 호출할 때 존재하지 않는 빈 이름을 사용하거나 타입이 다르면 익셉션이 발생한다.
	- 없는 빈 이름 사용시 - NoSuchBeanDefinitionException
	- 타입이 다른 경우 - BeanNotOfRequiredTypeException
- 빈 이름을 지정하지 않고 타입만으로 빈을 구할 수 있다.
```
VersionPrinter versionPrinter = ctx.getBean(MemberPrinter.class);
```
- 이 때 해당 타입의 빈 객체가 한 개만 존재하면 해당 빈을 구해서 반환한다. 해당 타입의 빈 객체가 존재하지 않으면 익셉션이 발생한다(NoSuchBeanDefinitionException).
- 같은 타입의 빈 객체가 두개 이상 존재할 수도 있다. 이 경우 NoUniqueBeanDefinitionException이 발생한다.

> getBean() 메서드는 BeanFactory 인터페이스에 정의되어 있다. getBean() 메서드를 실제 구현한 클래스는 AbstractApplicationContext이다.

## 주입 대상 객체를 모두 빈 객체로 설정해야 하는가?
- 주입할 객체가 꼭 스프링 빈이어야 할 필요는 없다.  다만 객체를 스프링 빈으로 등록할 때와 등록하지 않을 때의 차이는 스프링 컨테이너가 객체를 관리하는지 여부이다.
- 스프링 컨테이너는 자동 주입, 라이프사이클 관리 등 단순 객체 생성 외에 객체 관리를 위한 다양한 기능을 제공하는데 빈으로 등록한 객체에만 기능을 적용한다.
- 스프링 컨테이너가 제공하는 관리 기능이 필요 없고 getBean() 메서드로 구현할 필요가 없다면 빈 객체로 꼭 등록해야 하는 것은 아니다.
- 최근에는 의존 자동 주입 기능을 프로젝트 전반에 걸쳐 사용하는 추세이기 때문에 의존 주입 대상은 스프링 빈으로 등록하는 것이 보통이다.


* * *
# 의존 자동 주입
- 의존 대상을 설정 코드에서 직접 주입하기 않고 스프링이 자동으로 의존하는 빈 객체를 주입해주는 기능도 있다. 이를 자동 주입이라 한다.
- 스프링에서 의존 자동 주입을 설정하려면 @Autowired 애노테이션이나 @Resource 애노테이션을 사용하면 된다. 스프링에서는 주고 @Autowired를 많이 사용한다.

> @javax.annotation.Resource 애노테이션은 자바에서 제공하는 애노테이션으로 스프링은 @Resource 애노테이션뿐만 아니라 자바EE에서 제공하는 @javax.inject.Inject 애노테이션을 지원한다. 스프링은 @Autowired 애노테이션과 유사하게 이 두 애노테이션에 대해 자동 주입을 적용한다. 

## @Autowired 애노테이션을 이용한 의존 자동 주입
- 자동 주입 기능을 사용하면 스프링이 알아서 의존 객체를 찾아서 주입한다.
- 자동 주입 기능을 사용하는 방법은 의존을 주입할 대상에 @Autowired 애노테이션을 붙이기만 하면 된다.

#### src/main/java/spring/ChangePasswordService.java
```java
package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	
	@Autowired
	private MemberDao memberDao;

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

```java
@Autowired
private MemberDao memberDao;
```
- memberDao 필드에 @Autowired 애노테이션을 붙였다. @Autowired  애노테이션을 붙이면 설정 클래스에서 의존을 주입하지 않아도 된다. 
- 필드에 @Autowired 애노테이션이 붙어 있으면 스프링이 해당 타입의 빈 객체를 찾아서 필드에 할당한다.
<br>
- @Autowired 애노테이션을 memberDao 필드에 붙였으므로 다음과 같이 AppCtx 클래스의 @Bean 설정 메서드에서 의존을 주입하는 코드를 삭제하면 된다.  
- changePwdSvc() 메서드에서 생성한 ChangePasswordService 객체의 setMemberDao() 메서드를 호출하지 않는다. setMemberDao()를 호출하서 MemberDao 빈 객체를 주입하지 않아도 스프링이 MemberDao 타입의 빈 객체를 주입하기 때문이다.

#### src/main/java/config/AppCtx.java

```java
...
@Configuration
public class AppCtx {
	...
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		return pwdSvc;
	}
	...
}
```
의존을 주입하지 않아도 스프링이 @Autowired를 붙인 필드에 해당 타입의 빈 객체를 찾아서 주입한다.
<br>
<br>
- @Autowired 애노테이션은 메서드에도 붙일 수 있다.

#### src/main/java/spring/MemberInfoPrinter.java

```java
package spring;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
```

#### src/main/java/config/AppCtx.java

```java
...
@Configuration
public class AppCtx {
	...
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		return infoPrinter;
	}
	...
}
```

- MemberInfoPrinter 객체의 두 세터 메서드를 호출 하지 않도록 수정했으며 정상적으로 동작한다.
- 빈 객체의 메서드에 @Autowired 애노테이션을 붙이면 스프링은 해당 메서드를 호출한다. 이때 메서드 매개변수 타입에 해당하는 빈 객체를 찾아서 주입한다.

>@Autowired 애노테이션을 필드나 세터 메서드에 붙이면 스프링은 타입이 일치하는 빈 객체를 찾아서 주입한다.

#### src/main/java/spring/MemberRegisterService.java

```java
...
import org.springframework.beans.factory.annotation.Autowired;
...
public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService() {
		
	}
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	...
}
```
memberDao  필드에 @Autowired 애노테이션을 붙였고 매개변수 없는 생성자를 추가하였다.

#### src/main/java/spring/MemberListPrinter.java

```java
...
import org.springframework.beans.factory.annotation.Autowired;
...


public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;

	public MemberListPrinter() {
		
	}
	
	...
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
```

#### src/main/java/config/AppCtx.java
```java
...
@Configuration
public class AppCtx {
	...
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	...
}
```

### 일치하는 빈이 없는 경우

@Autowired 애노테이션을 적용한 대상에 일치하는 빈이 없으면 UnsatisfiedDependencyException이 발생한다.

```java
...
@Configuration
public class AppCtx {

	/**
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	*/
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	...
}
```
memberDao() 메서드를 주석처리하고 MainForSpring을 실행하면 예외가 발생하면서 제대로 실행되지 않으며 다음과 같은 에러 메세지가 출력된다.

```
6월 11, 2022 8:11:56 오후 org.springframework.context.support.AbstractApplicationContext refresh
경고: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'memberRegSvc': Unsatisfied dependency expressed through field 'memberDao'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'spring.MemberDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'memberRegSvc': Unsatisfied dependency expressed through field 'memberDao'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'spring.MemberDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: 
... 생략
```

이 에러 메세지는 @Autowired 애노테이션을 붙인 MemberRegisterService의 memberDao 필드에 주입할 MemberDao 빈이 존재하지 않아 에러가 발생했다는 사실을 알려준다.
<br>
<br>
반대로 @Autowired 애노테이션을 붙인 주입 대상에 일치하는 빈이 두 개 이상이라면?

```java
	/**
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	*/
	
	@Bean
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
	}
```

memberPrinter1(), memberPrinter2() 메서드를 추가한 뒤 memberPrinter()를 주석처리하고 MainForSpring을 실행하면 다음의 예외 메세지가 출력된다.

```
월 11, 2022 8:16:44 오후 org.springframework.context.support.AbstractApplicationContext refresh
경고: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'listPrinter': Unsatisfied dependency expressed through method 'setMemberPrinter' parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'spring.MemberPrinter' available: expected single matching bean but found 2: memberPrinter1,memberPrinter2
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'listPrinter': Unsatisfied dependency expressed through method 'setMemberPrinter' parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'spring.MemberPrinter' available: expected single matching bean but found 2: memberPrinter1,memberPrinter2
7) 
... 생략
```

자동 주입을 하려면 해당 타입을 가진 빈이 어떤 빈인지 정확하게 한정할 수 있어야 하는데 MemberPrinter, 타입의 빈이 두 개여서 어떤 빈을 자동 주입 대상으로 선택해야 할지 한정할 수 없다. 이 경우 스프링은 자동 주입에 실패하고 예외를 발생시킨다.

## @Qualifier 애노테이션을 이용한 의존 객체 선택

- 자동 주입 가능한 빈이 두 개 이상이라면 자동 주입할 빈을 지정할 수 있는 방법이 필요하다. 이때 @Qualifier 애노테이션을 사용한다. @Qualifier 애노테이션을 사용하면 자동 주입 대상 빈을 한정할 수 있다.
- @Qualifier 애노테이션은 두 위치에서 사용가능하다
- 첫 번째는 @Bean 애노테이션을 붙인 빈 설정 메서드이다.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
... 생략

@Configuration
public class AppCtx {
	
	... 생략
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
	}
	
	... 생략
}
```
이 코드에서 memberPrinter1() 메서드에 "printer" 값을 갖는 @Qualifier 애노테이션을 붙였다. 이 설정은 해당 빈의 한정 값으로 "printer"를 지정한다.<br><br>
이렇게 지정한 한정 값은 @Autowired 애노테이션에서 자동 주입할 빈을 한정할 떄 사용한다. 이곳이 @Qualifier 애노테이션을 사용하는 두 번째 위치이다. 

```java 
public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	... 생략
	
	@Autowired
	@Qualified("printer")
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
```

setMemberPrinter() 메서드에 @Autowired 애노테이션을 붙였으므로 MemberPrinter 타입의 빈을 자동 주입한다. 이때 @Qualifier 애노테이션 값이 "printer"이므로 한정 값이 "printer"인 빈을 의존 주입 후보로 사용한다. 앞서 스프링 설정 클래스에서 @Qualifier 애노테이션의 값으로 "printer"를 준 MemberPrinter 타입의 빈(memberPrinter1)을 자동 주입 대상으로 사용한다.<br>
MemberListPrinter 클래스뿐 아니라 MemberInfoPrinter의 setPrinter() 메서드에도 @Qualifier("printer") 애노테이션을 붙여서 의존 주입 대상을 설정한다. MemberPrinter 타입 빈을 주입받는 모든 @Autowired 애노테이션에 @Qualifier 애노테이션을 붙였으므로 다시 MainForSpring을 실행해보면 정상 동작한다.

### 빈 이름과 기본 한정자
빈 설정에 @Qualifier 애노테이션이 없으면 빈의 이름을 한정자로 지정한다.

```java 
@Configuration
public class AppCtx2 {
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("mprinter")
	public MemberPrinter printer2() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter2 infoPrinter() {
		MemberInfoPrinter2 infoPrinter = new MemberInfoPrinter2();
		return infoPrinter;
	}
}
```
- printer() 메서드로 정의한 빈의 한정자는 빈 이름인 "printer"가 된다. printer2빈은 @Qualifier 애노테이션 값은 "mprinter"가 한정자가 된다.

- @Autowired 애노테이션도 @Qualifier 애노테이션이 없으면 필드나 파라미터 이름을 한정자로 사용한다. 

#### 빈 이름과 한정자 관계

|빈 이름|@Qualifier|한정자|
|----|------|------|
|printer||printer|
|printer2|mprinter|mprinter|
|infoPrinter||infoPrinter|

## @Autowired 애노테이션의 필수 여부
MemberPrinter 코드를 다음과 같이 변경해보자.

#### src/main/java/spring/MemberPrinter.java

```java
package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.*;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(), member.getEmail(),
					member.getName(),
					dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	@Autowired
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
```

- MainForSpring 실행 결과

```
6월 11, 2022 9:10:25 오후 org.springframework.context.support.AbstractApplicationContext refresh
경고: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'memberPrinter': Unsatisfied dependency expressed through method 'setDateFormatter' parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'java.time.format.DateTimeFormatter' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'memberPrinter': Unsatisfied dependency expressed through method 'setDateFormatter' parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'java.time.format.DateTimeFormatter' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
... 생략
```
dateTimeFormatter 필드가 null이면 날짜 형식을 %tF로 출력하고 이 필드가 null이 아니면 dateTimeFormatter를 이용해서 날짜 형식을 맞춰 출력하도록 print() 메서드를 수정했다. 세터 메서드는 @Autowired 애노테이션을 이용해서 자동 주입하도록 했다.<br><br>
print() 메서드는 dateTimeFormatter가 null인 경우에도 알맞게 동작한다. 즉 반드시 setDateFormatter()를 통해서 의존 객체를 주입할 필요는 없다. setDateFormatter()에 주입할 빈이 존재하지 않아도 MemberPrinter가 동작하는데는 문제가 없다.<br><br>
그런데 @Autowired 애노테이션은 기본적으로 @Autowired 애노테이션을 붙인 타입에 해당하는 빈이 존재하지 않으면 익셉션을 발생한다. 따라서 setDateFormatter() 메서드에서 필요로 하는 DateTimeFormatter 타입의 빈이 존재하지 않으면 익셉션이 발생한다. MemberPrinter는 setDateFormatter() 메서드에 자동 주입할 빈이 존재하지 않으면 익셉션이 발생하기보다는 그냥 dateTimeFormatter 필드가 null이면 된다.<br><br>
이렇게 자동 주입할 대상이 필수가 아닌 경우에는 <b>@Autowired 애노테이션의 required 속성</b>을 다음과 같이 <b>false로 지정</b>하면 된다.

```java
... 생략
public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		... 생략
	}
	
	@Autowired(required = false)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
```
- @Autowired 애노테이션의 required 속성을 false로 지정하면 매칭되는 빈이 없어도 익셉션이 발생하지 않으며 자동 주입을 수행하지 않는다. 위 예에서 DateTimeFormatter 타입의 빈이 존재하지 않으면 익셉션을 발생하지 않고 setDateFormatter() 메서드를 실행하지 않는다.

- 스프링 5 버전 부터는 @Autowired 애노테이션의 required 속성을 false로 하는 대신 다음과 같이 의존 주입 대상에 <b>자바1.8에서 도입된 Optional을 사용</b>해도 된다.

```java
public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		... 생략 
	}
	
	@Autowired
	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
		if (formatterOpt.isPresent()) {
			this.dateTimeFormatter = formatterOpt.get();
		} else {
			this.dateTimeFormatter = null;
		}
	}
}
```

- 자동 주입 대상 타입이 Optional인 경우, 일치하는 빈이 존재하지 않으면 값이 없는 Optional을 인자로 전달하고(익셉션이 발생하지 않는다). 일치하는 빈이 존재하면 해당 빈을 값으로 갖는 Optional을 인자로 전달한다. Optional을 사용하는 코드는 값 존재 여부에 따라 알맞게 의존 객체를 사용하면 된다. 위 코드는 Optional#isPresent() 메서드가 true이면 값이 존재하므로 해당 값을 dateTImeFormatter 필드에 할당한다. 즉, DateTimeFormatter 타입 빈을 주입 받아 dateTimeFormatter 필드에 할당한다. 값이 존재하지 않으면 주입 받은 빈 객체가 없으므로 dateTimeFormatter 필드에 null을 할당한다.

- 필수 여부를 지정하는 세번째 방법은 <b>@Nullable 애노테이션</b>을 사용하는 것이다.

```java
import org.springframework.lang.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		... 생략 
	}
	
	@Autowired
	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
```

- @Autowired 애노테이션을 붙인 세터 메서드에서 @Nullable 애노테이션을 의존 주입 대상 매개변수에 붙이면, 스프링 컨테이너는 세터 메서드를 호출할 때 자동 주입할 빈이 존재하면 해당 빈을 인자로 전달하고, 존재하지 않으면 인자로 null을 전달한다.
- @Autowired 애노테이션의 required 속성을 false로 할 때와 차이점은 @Nullable애노테이션을 사용하면 자동 주입할 빈이 존재하지 않아도 메서드가 호출된다는 점이다. 
- @Autowired 애노테이션의 경우 required 속성이 false인데 대상 빈이 존재하지 않으면 세터 메서드를 호출하지 않는다.<br><br>

앞서 설명한 세 가지 방식은 필드에도 그대로 적용된다. 필드에 @Autowired,. 애노테이션을 사용했다면 required 속성을 false로 지정하거나 Optional을 사용하거나 @Nullable 애노테이션을 사용해서 필수 여부를 지정할 수 있다.
- required 속성을 false로 지정한 예

```java 
public class MemberPrinter {
	@Autowired(required=false)
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		... 생략
	}
} 
```

- 필드 타입으로 Optional을 사용한 예

```java
public class MemberPrinter {
	@Autowired
	private Optional<DateTimeFormatter> formatterOpt;
	
	public void print(Member member) {
		DateTimeFormatter dateTimeFormatter = formatterOpt.orElse(null);
		if (dateTImeFormatter == null) {
			... 생략
		} else {
			... 생략 
		}
	}
}
```

- @Nullable 애노테이션 사용 예

```java
public class MemberPrinter {
	@Autowired
	@Nullable
	private DateTimeFormatter dateTimeFormatter;
	
	public void print(Member member) {
		... 생략
	}
}
```

### 생성자 초기화 필수 여부 지정 방식 동작 이해

#### src/main/java/spring/MemberPrinter.java

```java
package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.*;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(), member.getEmail(),
					member.getName(),
					dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	@Autowired(required = false)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
```
- MainForSpring 실행 결과

```
명령어를 입력하세요:
new yonggyo00@kakao.com 이용교 1234 1234
등록했습니다.

명령어를 입력하세요:
info yonggyo00@kakao.com
회원정보: 아이디=1, 이메일=yonggyo00@kakao.com, 이름=이용교, 등록일=2022년 06월 11일

명령어를 입력하세요:
```

- 이 코드는 기본 생성자에서 dateTimeFormatter 필드의 값을 초기화 한다. 또한 @Autowired 애노테이션의 required 속성은 false로 지정했다.
- DateTimeFormatter 타입의 빈이 존재하지 않은 상태에서 MainForSpring을 실행한 뒤 info 명령어로 회원 정보를 출력해보면 기본 생성자에서 초기화한 DateTimeFormatter를 사용해서 회원의 가입 일자를 출력하는 것을 확인할 수 있다.
- 이 실행 결과를 통해 @Autowired 애노테이션의 required 속성이 false이면 일치하는 빈이 존재하지 않을 때 자동 주입대상이 되는 필드나 메서드에 null을 전달하지 않는다는 것을 알 수 있다. 만약 일치하는 빈이 존재하지 않을 때 setDateFormatter() 메서드에 null을 인자로 주어 호출한다면 기본 생성자에서 초기화한 DateTimeFormatter의 형식으로 출력하지 않을 것이다.<br><br>
- @Autowired(required = false) 대신에 @Nullable을 사용하도록 바꿔보자

#### src/main/java/spring/MemberPrinter.java

```java
public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		//dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Member member) {
		... 생략
	}
	
	@Autowired(required = false)
	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
```

- MainForSpring 실행 결과

```java
명령어를 입력하세요:
new yonggyo00@kakao.com 이용교 1234 1234
등록했습니다.

명령어를 입력하세요:
info yonggyo00@kakao.com
회원정보: 아이디=1, 이메일=yonggyo00@kakao.com, 이름=이용교, 등록일=2022-06-11
```

- @Nullable 애노테이션을 사용할 경우 스프링 컨테이너는 의존 주입 대상이 존재하지 않으면 null을 값으로 전달한다.
- 위 예의 경우 setDateFormatter() 메서드에 null을 전달한다. 스프링 컨테이너는 빈을 초기화하기 위해 기본 생성자를 이용해서 객체를 생성하고 의존 자동 주입을 처리하기 위해 setDateFormatter() 메서드를 호출한다.

* * *
# 컴포넌트 스캔
자동 주입과 함께 사용되는 추가 기능이 컴포넌트 스캔이다. 컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능이다. 설정 클래스에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록할 수 있으므로 컴포넌트 스캔 기능을 사용하면 설정 코드가 크게 줄어든다.

## @Component 애노테이션으로 스캔 대상 지정
- 스프링으로 검색해서 빈으로 등록할 수 있으려면 클래스에 @Component 애노테이션을 붙여야 한다. 
- @Component 애노테이션은 해당 클래스를 스캔 대상으로 표시한
다. 

#### src/main/java/spring/MemberDao.java

```java
package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	... 생략
}
```

- 앞서 작성한 다음 클래스에도 MemberDao와 마찬가지로 @Component 애너테이션을 붙여보세요.
	- ChangePasswordService
	- MemberDao
	- MemberRegisterService
	
- MemberInfoPrinter 클래스에는 다음과 같이 @Component 애노테이션에 속성 값을 준다.

#### src/main/java/spring/MemberInfoPrinter.java

```java
package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {
	... 생략
}
```

- @Component 애노테이션에 값을 주었는지에 따라 빈으로 등록할 때 사용할 이름이 결정된다. @Component 애노테이션에 값을 주지 않는 경우 클래스의 이름의 첫 글자를 소문자로 바꾼 이름을 빈 이름으로 사용한다. 
- 예를 들어 클래스 이름이 MemberDao이면 빈 이름으로 "memberDao"를 사용하고 클래스 이름이 MemberRegisterService이면 빈 이름으로 "memberRegisterService"를 사용한다.
- @Component 애노테이션 값을 주면 그 값을 빈 이름으로 사용한다. MemberInfoPrinter 클래스는 빈 이름으로 "infoPrinter"를 사용한다.

- MemberListPrinter 클래스도 다음과 같이 @Component 애노테이션을 설정한다.

```java 
... 생략
import org.springframework.stereotype.Component;
... 생략

@Component("listPrinter")
public class MemberListPrinter {
	... 생략
}
```

## @ComponentScan 애노테이션으로 스캔 설정
@Component 애노테이션을 붙인 클래스를 스캔해서 스프링 빈으로 등록하려면 설정 클래스에 @ComponentScan 애노테이션을 적용해야 한다. 설정 클래스인 AppCtx에 @ComponentScan 애노테이션을 적용한 코드는 다음과 같다.

#### src/main/java/config/AppCtx.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.MemberPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages= {"spring"})
public class AppCtx {
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
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
## 예제 실행

MainForSpring 클래스에서 일부 수정할 코드가 있다. MainForSpring 코드를 보면 다음과 같이 이름으로 빈을 검색하는 코드가 있다.

```java
// processNewCommand() 메서드
MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);

// processChangeCommand() 메서드
ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);

// processListCommand() 메서드
MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);

// processInfoCommand() 메서드
MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);

// processVersionCommand() 메서드
VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
```

- 이 중에서 MemberRegisterService 타입 빈과 ChangePasswordService 타입의 빈은 이름이 달라졌다. 이 두 클래스에 @Component 애노테이션을 붙일 때 속성값을 주지 않았는데. 이 경우 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 빈 이름으로 사용한다. 
- 따라서 MemberRegisterService 타입 빈 객체의 이름은 "memberRegisterService"가 되고 ChangePasswordService 타입 빈 객체의 이름은 "changePasswordService"가 된다.
- MemberListPrinter 클래스의 MemberInfoPrinter 클래스는 @Component 애노테이션 속성값으로 빈 이름을 알맞게 지정했으므로 MainForSpring에서 빈을 구하는 코드를 수정할 필요가 없다.


## 스캔 대상에서 제외하거나 포함하기

### 기본 스캔 대상
@Component 애노테이션을 붙인 클래스만 컴포넌트 스캔 대상에 포함되는 것은 아니다. 다음 애노테이션을 붙인 클래스가 컴포넌트 스캔 대상에 포함된다.
- @Component(org.springframework.stereotype 패키지)
- @Controller(org.springframework.stereotype 패키지)
- @Service(org.springframework.stereotype 패키지)
- @Repository(org.springframework.stereotype 패키지)
- @Aspect(org.aspectj.lang.annotation 패키지)
- @Configuration(org.springframework.context.annotation 패키지)
<br><br>
@Aspect 애노테이션을 제외한 나머지 애노테이션은 실제로는 @Component 애노테이션에 대한 특수 애노테이션이다. 예를 들어 @Controller 애노테이션은 다음과 같다.

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
	@AliasFor(annotation = Component.class)
	String value() defualt "";
}
```
- @Component 애노테이션이 붙어 있는데, 스프링은 @Controller 애노테이션을 @Component 애노테이션과 동일하게 컴포넌트 스캔 대상에 포함한다. 
- @Controller 애노테이션이나 @Repository 애노테이션 등은 컴포넌트 스캔 대상이 될 뿐만 아니라 스프링프레임워크에서 특별한 기능과 연관되어 있다. 
- @Controller 애노테이션은 웹 MVC와 관련이 있고 @Repository 애노테이션은 DB 연동과 관련있다.

## 컴포넌트 스캔에 따른 충돌 처리
excludeFilters 속성을 사용하면 스캔할 때 특정 대상을 자동 등록 대상에서 제외할 수 있다. 다음 코드는 excludeFilters 속성의 사용 예를 보여준다.

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = {"spring"},
   excludeFilters = @Filter(type = FilterType.REGEX, pattern=="spring\\..*Dao"))
public class AppCtxWithExclude {
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

- 이 코드는 @Filter 애노테이션의 type 속성으로 FilterType.REGEX를 주었다. 이는 정규표현식을 사용해서 제외 대상을 지정한다는 것을 의미한다.
- Pattern 속성은 FilterType에 적용할 값을 설정한다. 위 설정에서는 "spring."으로 시작하고 Dao로 끝나는 정규표현식을 지정했으므로 spring.MemberDao 클래스를 컴포넌트 스캔 대상에서 제외한다.
<br><br>
- FilterType.ASPECTJ를 필터 타입으로 설정할 수도 있다. 이 타입을 사용하면 정규표현식 대신 AspectJ 패턴을 사용해서 대상을 지정한다.

```java
@Configuration
@ComponentScan(basePackages = {"spring"},
   excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao"))
public class AppCtxWithExclude {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
}
```

- AspectJ 패턴은 정규표현식과 다른데, 이에 관련된 내용은 [3일차 - AOP 프로그래밍](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)) 에서 학습할 예정입니다. 
- AspectJ 패턴이 동작하려면 의존 대상에 aspectjweaver 모듈을 추가해야 한다.

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.9.1</version>
</dependency>
```

- patterns 속성은 String[] 타입이므로 배열을 이용해서 패턴을 한 개 이상 지정할 수 있다.
<br><br>
- 특정 애노테이션을 붙인 타입을 컴포넌트 대상에서 제외할 수도 있다.

```java
@Retention(RUNTIME)
@Target(TYPE)
public @interface NoProduct {
}

@Retention(RUNTIME)
@Target(TYPE)
public @interface ManualBean {
}
```

- 이 두 애노테이션을 붙인 클래스를 컴포넌트 스캔 대상에서 제외하려면 다음과 같이 excludeFilters 속성을 설정한다.

```java
@Configuration
@ComponentScan(basePackages = {"spring", "spring2"}, 
	excludeFilters = @Filter(type=FilterType.ANNOTATION, classes = {NoProduct.class, ManualBean.class}))
public class AppCtxWithExclude {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	...
}
```

- type 속성 값으로 FilterType.ANNOTATION을 사용하면 classes속성에 필터로 사용될 애노테이션 타입을 값으로 준다.
- 이 코드는 @ManualBean 애노테이션을 제외 대상에 추가했으므로 다음 클래스를 컴포넌트 스캔 대상에서 제외한다.

```java
@ManualBean 
@Component
public class MemberDao {
	...
}
```

특정 타입이나 그 하위 타입을 컴포넌트 스캔 대상에서 제외하려면 ASSIGNABLE_TYPE을 FilterType으로 사용한다.

```java
@Configuration
@ComponentScan(basePackages = {"spring"},
	excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE,
		classes = MemberDAO.class ))
public class AppCtxWithExclude {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	...
}
```

classes 속성에는 제외할 타입 목록을 지정한다. 위 설정은 제외할 타입이 한 개이므로 배열 표기를 사용하지 않았다.<br><br>

설정할 필터가 두 개 이상이면 @ComponentScan의 excludeFilters 속성에 배열을 사용해서 @Filter 목록을 전달하면 된다.

```java
@Configuration
@ComponentScan(basePackages = {"spring"}, 
	excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = ManualBean.class),
		@Filter(type = FilterType.REGEX, pattern = "spring2\\.*")
	})
```

### 빈 이름 충돌
spring 패키지와 spring2 패키지에 MemberRegisterService 클래스가 존재하고 두 클래스 모두 @Component 애노테이션을 붙였다고 하자. 이 상태에서 다음 @ComponentScan 애노테이션을 사용하게 되면 예외(BeanDefinitionStoreException)가 발생한다.

```java
@Configuration
@ComponentScan(basePackages={"spring", "spring2"})
public class AppCtx {
	...
}
```

이런 문제는 컴포넌트 스캔 과정에서 쉽게 발생할 수 있다. 이렇게 컴포넌트 스캔 과정에서 서로 다른 타입인데 같은 빈 이름을 사용하는 경우가 있다면 둘 중 하나에 명시적으로 빈 이름을 지정해서 이름 충돌을 피해야 한다.

### 수동 등록한 빈과 충돌
앞서 MemberDao 클래스에 @Component 애노테이션을 붙였다.

```java
@Component
public class MemberDao {
	...
}
```

MemberDao 클래스는 컴포넌트 스캔 대상이다. 자동 등록된 빈의 이름은 클래스 이름의 첫 글자를 소문자로 바꾼 "memberDao"이다. 그런데 다음과 같이 설정 클래스에 직접 MemberDao 클래스를 "memberDao"라는 이름의 빈으로 등록하게 되면 어떻게 될까?

```java
@Configuration
@ComponentScan(basePackages={"spring"})
public class AppCtx {
	@Bean
	public MemberDao memberDao() {
		MemberDao memberDao = new MemberDao();
		return memberDao;
	}
}
```

- 스캔할 때 사용하는 빈 이름과 수동 등록한 빈 이름이 같은 경우 <b>수동 등록한 빈이 우선한다.</b> 즉, MemberDao 타입의 빈은 AppCtx에서 정의한 한 개만 존재한다.
- 다음과 같이 다른 이름을 사용한다면 어떻게 될까?

```java
@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {
	@Bean
	public MemberDao memberDao2() {
		MemberDao memberDao = new MemberDao();
		return memberDao;
	}
}
```

이 경우 스캔을 통해 등록한 "memberDao" 빈과 수동 등록한 "memberDao2" 빈이 모두 존재한다. MemberDao 타입의 빈이 두 개 생성되므로 자동 주입하는 코드는 @Qualifier 애노테이션을 사용해서 알맞은 빈을 선택해야 한다.

* * *
# 빈 라이프 사이클과 범위

## 컨테이너 초기화와 종료
스프링 컨테이너는 <b>초기화와 종료</b>라는 라이프 사이클을 갖는다.
- 1. 컨테이너 초기화

```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
```

- AnnotationConfigApplicationContext의 생성자를 이용해서 컨텍스트 객체를 생성하는데 이 시점에 스프링 컨테이너를 초기화한다. 
- 스프링 컨테이너는 설정 클래스에서 정보를 읽어와 알맞은 빈 객체를 생성하고 각 빈을 연결(의존 주입)하는 작업을 수행한다.
	
- 2. 컨테이너에서 빈 객체를 구해서 사용

```java
Greeter g = ctx.getBean("greeter", Greeter.class);
String msg = g.greet("스프링");
System.out.println(msg);
```

- 컨테이너 초기화가 완료되면 컨테이너를 사용할 수 있다. 
- 컨테이너를 사용한다는 것은 getBean()과 같은 메서드를 이용해서 컨테이너에 보관된 빈 객체를 구한다는 것을 뜻한다.

- 3. 컨테이너 종료

```java
ctx.close();
```

- 컨테이너 사용이 끝나면 컨테이너를 종료한다. 컨테이너를 종료할 때 사용하는 메서드가 close() 메서드이다.
- close() 메서드는 AbstractApplicationContext 클래스에 정의되어 있다. 
- 자바 설정을 사용하는 AnnotationConfigApplicationContext 클래스나 XML 설정을 사용하는 GenericXmlApplicationContext 클래스 모두 AbstractApplicationContext 클래스를 상속받고 있다. 따라서 앞서 코드처럼 close() 메서드를 이용해서 컨테이너를 종료할 수 있다.
	
- 컨테이너를 초기화하고 종료할 때는 다음의 작업도 함께 수행한다.
	- <b>컨테이너 초기화</b> : 빈 객체의 생성, 의존 주입, 초기화
	- <b>컨테이너 종료</b> : 빈 객체의 소멸
	
- 스프링 컨테이너의 라이프사이클에 따라 빈 객체도 자연스럽게 생성과 소멸이라는 라이프사이클을 갖는다.

#### 실습 프로젝트 생성 
- 1. 프로젝트 생성

	```
	mvn archetype:generate
	```
	
	- groupId, artifactId는 적절하게 입력해 줍니다.

- 2. pom.xml
	- 자바 실습 버전을 최신버전(17)로 변경합니다.
	- spring-context 의존성을 [mvnrepository](https://mvnrepository.com) 에서 검색하여 다음과 같이 추가합니다.
	
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
			<version>5.3.20</version>
		</dependency>
	
		... 생략
	</dependencies>
	```
	
- 3. 이클립스에서 Import - Existing Maven Projects를 클릭하여 생성된 폴더를 선택하여 생성해 줍니다.

## 스프링 빈 객체의 라이프사이클 
스프링 컨테이너는 빈 객체의 라이프사이클을 관리한다. 

#### 빈 객체의 라이프 사이클 

객체 생성 -> 의존 설정 -> 초기화 -> 소멸
<br><br>
- 스프링 컨테이너를 초기화할 때 스프링 컨테이너는 가장 먼저 빈 객체를 생성하고 의존을 설정한다. 
- 의존 자동 주입을 통한 의존 설정이 이 시점에 수행된다. 
- 모든 의존 설정이 완료되면 빈 객체의 초기화를 수행한다. 빈 객체를 초기화하기 위해 스프링은 빈 객체의 지정된 메서드를 호출한다.
- 스프링 컨테이너를 종료하면 스프링 컨테이너는 빈 객체의 소멸을 처리한다. 이때에도 지정된 메서ㄷ를 호출한다.

### 빈 객체의 초기화와 소멸 : 스프링 인터페이스
- 스프링 컨테이너는 빈 객체를 초기화하고 소멸하기 위해 빈 객체의 지정한 메서드를 호출한다. 
- 스프링은 다음의 두 인터페이스에 이 메서드를 정의하고 있다.
	- org.springframework.beans.factory.InitializingBean
	- org.springframework.beans.factory.DisposableBean
	
```java
public interface InitializingBean {
	void afterPropertiesSet() throws Exception;
}

public interface DisposableBean {
	void destroy() throws Exception;
}
```

- 빈 객체가 InitializingBean 인터페이스를 구현하면 스프링 컨테이너는 초기화 과정에서 빈 객체의 afterPropertiesSet() 메서드를 실행한다.
- 빈 객체를 생성한 뒤에 초기화 과정이 필요하면 InitializingBean 인터페이스를 상속하고 afterPropertiesSet() 메서드를 알맞게 구현하면 된다.
<br>
- 스프링 컨테이너는 빈 객체가 DisposableBean 인터페이스를 구현한 경우 소멸 과정에서 빈 객체의 destroy() 메서드를 실행한다.
- 빈 객체의 소멸 과정이 필요하면 DisposableBean 인터페이스를 상속하고 destroy() 메서드를 알맞게 구현하면 된다.

#### src/main/java/spring/Client.java

```java
package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client.afterPropertiesSet() 실행");
	}
	
	public void send() {
		System.out.println("Client.send() to " + host);
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("Client.destroy() 실행");
	}
}
```

#### src/main/java/config/AppCtx.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Client;

@Configuration
public class AppCtx {
	
	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
}
```

#### src/main/java/main/Main.java

```java
package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;

public class Main {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Client client = ctx.getBean(Client.class);
		client.send();
		
		ctx.close();
	}
}
```

- 실행 결과

```
Client.afterPropertiesSet() 실행
Client.send() to host
Client.destroy() 실행
```

### 빈 객체의 초기화와 소멸 : 커스텀 메서드
- 모든 클래스가 InitializingBean 인터페이스와  DisposableBean 인터페이스를 상속받아 구현할 수 있는 것은 아니다. 직접 구현한 클래스가 아닌 외부에서 제공받은 클래스를 스프링 빈 객체로 설정하고 싶을 때도 있다. 이 경우 소스 코드를 받지 않았다면 두 인터페이스를 구현하도록 수정할 수 없다. 
- 이렇게 InitializingBean 인터페이스와 DisposableBean 인터페이스를 구현할 수 없거나 이 두 인터페이스를 사용하고 싶지 않은 경우에는 스프링 설정에서 직접 메서드를 지정할 수 있다.

- 방법은 @Bean 태그에서 initMethod 속성과 destroyMethod 속성를 사용해서 초기화 메서드와 소멸 메서드의 이름을 지정하면 된다.

#### src/main/java/spring/Client2.java

```java
package spring;

public class Client2 {
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void connect() {
		System.out.println("Client2.connect() 실행");
	}
	
	public void send() {
		System.out.println("Client.send() to " + host);
	}
	
	public void close() {
		System.out.println("Client2.close() 실행");
	}
}
```

#### src/main/java/config/AppCtx.java

```java
... 생략 
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

	... 생략 
	
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}
}
```
> initMethod 속성과 destroyMethod 속성에 지정한 메서드는 매개변수가 없어야 한다. 이 두 속성에 지정한 메서드에 매개변수가 존재할 경우 스프링 컨테이너는 예외를 발생시킨다. 

#### src/main/java/main/Main2.java

```java
package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client2;

public class Main2 {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Client2 client = ctx.getBean(Client2.class);
		client.send();
		
		ctx.close();
	}
}
```

- 실행 결과

```
Client.afterPropertiesSet() 실행
Client2.connect() 실행
Client.send() to host
Client2.close() 실행
Client.destroy() 실행
```
## 빈 객체의 생성과 관리 범위
- 스프링 컨테이너는 빈 객체를 한 개만 생성한다고 합니다.
- 예를 들어 아래 코드와 같이 동일한 이름을 갖는 빈 객체를 구하면 client1과 client2는 동일한 빈 객체를 참조합니다.

```java
Client client1 = ctx.getBean("client", Client.class);
Client client2 = ctx.getBean("client", Client.class);
// client1 == client -> true
```

- 이와 같이 한 식별자에 대해 한 개의 객체만 존재하는 빈은 <b>싱글톤(singleton) 범위(scope)</b>를 갖는다. 별도 설정을 하지 않으면 빈은 싱글톤 범위를 갖는다.
- 사용 빈도가 낮긴 하지만 프로토타입 범위의 빈을 설정할 수도 있다. 빈의 범위를 프로토타입으로 지정하면 빈 객체를 구할 때 마다 매번 새로운 객체를 생성한다.

```java
// client 빈의 범위가 프로토타입일 경우, 매번 새로운 객체를 생성
Client client1 = ctx.getBean("client", Client.class);
Client client2 = ctx.getBean("client", Client.class);
// client1 != client2 -> true
```

- 특정 빈을 프로토타입 범위로 지정하려면 다음과 같이 값으로 "prototype"을 갖는 **Scope 애노테이션**을 @Bean 애노테이션과 함께 사용하면 된다.

```java
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtxWithPrototype {
	@Bean
	@Scope("prototype")
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
}
```

- 싱글톤 범위를 명시적으로 지정하고 싶다면 @Scope 애노테이션 값으로 "singleton"을 주면 된다.

```java
@Bean(initMethod = "connect", destroyMethod = "close")
@Scope("singleton")
public Client2 client2() {
	Client2 client = new Client2();
	client.setHost("host");
	return client;
}
```

- 프로토타입 범위를 갖는 빈은 완전한 라이프사이클을 따르지 않는다는 점에 주의해야 한다.
- 스프링 컨터이너는 프로토타입의 빈 객체를 생성하고 프로퍼티를 설정하고 초기화 작업까지는 수행하지만 컨테이너를 종료한다고 해서 생성자 프로토타입 빈 객체의 소멸 메서드를 실행하지는 않는다.
- 따라서 프로토타입 범위의 빈을 사용할 때에는 빈 객체의 소명 처리를 코드에서 직접 해야 한다.
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

## DI 방식 1 : 생성자 방식
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
* * *
# 의존 자동 주입

* * *
# 컴포넌트 스캔

* * *
# 빈 라이프 사이클과 범위

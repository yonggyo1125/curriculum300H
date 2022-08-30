# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1F9_rKwDSd62GofwpC-f71kn5mtm0GlOI?usp=sharing)

# AOP 프로그래밍

뒤에서 설명할 트랜잭션의 처리 방식을 이해하려면 AOP(Aspect Oriented Programming)를 알아야 한다. 

## 프로젝트 준비

- 프로젝트 생성
```
mvn archetype:generate
```
- groupId, artifactId는 적절하게 입력해 줍니다. 
- 자바 실습 버전을 최신버전(17)로 변경합니다.
- spring-context와 aspectjweaver 의존성을 [mvnrepository](https://mvnrepository.com) 에서 검색하여 다음과 같이 추가합니다.

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
    	<groupId>org.aspectj</groupId>
	    <artifactId>aspectjweaver</artifactId>
	    <version>1.9.9.1</version>
	</dependency>
	
	... 생략
</dependencies>

```
- 이클립스에서 Import - Existing Maven Projects를 클릭하여 생성된 폴더를 선택하여 생성해 줍니다.

- 스프링 프레임워크의 AOP 기능은 spring-aop 모듈이 제공하는데 spring-context 모듈을 의존 대상에 추가하면 spring-aop 모듈도 함께 의존 대상에 포함된다. 따라서 spring-aop  모듈에 대한 의존을 따로 추가하지 않아도 된다. aspectjweaver 모듈은 AOP 를 설정하는데 필요한 애노테이션을 제공하므로 이 의존을 추가해야 한다.
- 메이븐 프로젝트를 생성하고 이클립스에 임포트 했다면 다음과 같이 코드를 작성한다.

#### src/main/java/aopex/Calculator.java

```java
package aopex;

public interface Calculator {
	
	public long factorial(long num);
}
```

> 양의 정수 n의 계승은 n!으로 표현하며 n!은 1부터 n까지의 숫자의 곱을 의미한다. 예를 들어 4!의 값은 4 \* 3 \* 2 \* 1의 결과인 24가 된다.

#### src/main/java/aopex/ImpeCalculator.java
- Calculator 인터페이스를 구현한 첫 번째 클래스는 for 문을 이용해서 계승 값을 구했다.

```java
package aopex;

public class ImpeCalculator implements Calculator {
	
	@Override
	public long factorial(long num) {
		long result = 1;
		for (long i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}
}
```

#### src/main/java/aopex/RecCalculator.java
- Calculator 인터페이스를 구현한 두 번째 클래스는 다음과 같이 재귀호출을 이용해서 계승을 구한다.

```java
package aopex;

public class RecCalculator implements Calculator {
	
	@Override
	public long factorial(long num) {
		if (num == 0)
			return 1;
		else 
			return num * factorial(num - 1);
	}
}
```

## 프록시와 AOP
- 앞에서 구현한 계승 구현 클래스의 실행 시간을 출력하려면 어떻게 해야 할까? 
- 쉬운 방법은 메서드의 시작과 끝에서 시작 시간을 구하고 이 두 시간의 차이를 출력하는 것이다.
- 예를 들어 ImpleCalculator 클래스를 다음과 같이 수정하면 된다.


#### src/main/java/aopex/RecCalculator.java
```java
... 생략

public class ImpeCalculator implements Calculator {
	
	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		
		long result = 1;
		for (long i = 1; i <= num; i++) {
			result += i;
		}
		
		long end = System.currentTimeMillis();
		System.out.printf("ImpeCalculator.factorial(%d) 실행 시간 = %d\n", num, (end-start));
		
		return result;
	}
}
```

- RecCalculator 클래스는 약간 복잡해진다. RecCalculator 클래스의 factorial() 메서드는 재귀 호출로 구현해서 factorial() 메서드의 시작과 끝에 시간을 구해서 차이를 출력하는 코드를 넣으면 메세지가 여러번 출력되는 문제가 있다.
- factorial(2)를 실행하면 내부적으로  다시 factorial(1)이 실행되고, 다시 factorial(0)이 실행된다. 따라서 실행 시간을 출력하는 메세지가 3번 출력된다.

#### src/main/java/main/Main.java
```java
package main;

import aopex.*;

public class Main {

	public static void main(String[] args) {
		ImpeCalculator impeCal = new ImpeCalculator();
		impeCal.factorial(4);
		
		RecCalculator recCal = new RecCalculator();
		recCal.factorial(4);
	}
}
```
- 기존 코드를 수정하지 않고 코드 중복도 피할 수 있는 방법은 없을까? 이때 출현하는 것이 바로 프록시 객체이다. 일단 코드부터 보자

#### src/main/java/aopex/ExeTimeCalculator.java

```java
package aopex;

public class ExeTimeCalculator implements Calculator {
	
	private Calculator delegate;
	
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long result = delegate.factorial(num);
		long end = System.nanoTime();
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));
		
		return result;
	}
}
```
- ExeTimeCalculator 클래스는 Calculator 인터페이스를 구현하고 있다. 
- 이 클래스는 생성자를 통해 다른 Calculator 객체를 전달받아 delegate 필드에 할당하고 factorial() 메서드에서 delegate.factorial() 메서드를 실행한다. 
- 그리고 delegate.factorial()의 코드를 실행하기 전후에 현재 시간을 구해 차이를 출력한다. 즉 delegate.factorial()의 실행 시간을 구해서 출력한다.

- ExeTimeCalculator 클래스를 사용하면 다음과 같은 방법으로 ImpeCalculator의 실행시간을 측정할 수 있다.

```java
ImpeCalculator impeCal = new ImpeCalculator();
ExeTimeCalculator calculator = new ExeTimeCalculator(impeCal);
long result = calculator.factorial(4);
```

- 위 코드에서 calculator.factorial()을 실행하면 다음과 같은 순서로 코드가 실행된다.

#### ExeTimeCalculator의 실행 흐름

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image1.png)


- 위 그림의 실행 흐름을 보면 ExeTimeCalculator 클래스의 factorial() 메서드는 결과적으로 ImpeCalculator의 factorial() 메서드의 실행 시간을 구해서 콘솔에 출력하게 된다. 

#### src/main/java/main/MainProxy.java

```java
package aopex;

import aopex.ImpeCalculator;
import aopex.RecCalculator;
import aopex.ExeTimeCalculator;

public class MainProxy {
	
	public static void main(String[] args) {
		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));
		
		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(ttCal2.factorial(20));
	}
}
```
- 실행 결과

```
ImpeCalculator.factorial(20) 실행 시간 = 7000
2432902008176640000
RecCalculator.factorial(20) 실행 시간 = 7599
2432902008176640000
```
- 위 결과에서 다음을 알 수 있다.
	- 기존 코드를 변경하지 않고 실행 시간을 출력할 수 있다.
	- 실행 시간을 구하는 코드의 중복을 제거했다.
- 이것이 가능한 이유는 ExeTimeCalculator 클래스를 다음과 같이 구현했기 때문이다.
	- factorial() 기능 자체를 직접 구현하기 보다는 다른 객체에 factorial()의 실행을 위임한다.
	- 계산 기능외에 다른 부가적인 기능을 실행한다. 여기서 부가적인 기능은 실행 시간 측정이다.

- 이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 <b>프록시(proxy)</b>라고 부른다.
- 실제 핵심 기능을 실행하는 객체는 대상 객체라고 부른다. ExeTimeCalculator가 <b>프록시</b>이고 ImpeCalculator 객체가 프록시의 <b>대상 객체</b>가 된다.

> 엄밀히 말하면 지금 작성한 코드는 프록시(proxy)라기 보다는 <b>데코레이터</b>객체에 가깝다. 프록시는 접근 제어 관점에 초점이 맞춰져 있다면, 데코레이터는 기능 추가와 확장에 초점이 맞춰 있기 때문이다. 예제에서는 기존 기능에 시간 측정 기능을 추가하고 있기 때문에 데코레이터에 가깝지만 스프링 레퍼런스 문서에서 AOP를 설명할 때 프록시란 용어를 사용하고 있어 여기에서도 프록시라는 용어를 사용했다.

- 프록시의 특징은 핵심 기능은 구현하지 않는다는 점이다. 
- ImpeCalculator나 RecCalculator는 팩토리얼 연산이라는 핵심 기능을 구현하고 있다. 반면에 ExeTimeCalculator 클래스는 팩토리얼 연산 자체를 구현하고 있지 않다.

- 프록시는 핵심 기능을 구현하지 않는 대신 여러 객체에 공통으로 사용할 수 있는 기능을 구현한다. 이 예에서는 ExeTimeCalculator 클래스는 ImpeCalculator 객체의 RecCalculator 객체에 공통으로 적용되는 실행 시간 측정 기능을 구현하고 있다.
- 정리하면 ImpeCalculator와 RecCalculator는 팩토리얼을 구한다는 핵심 기능 구현에 집중하고 프록시인 ExeTimeCalculator는 실행 시간 측정이라는 <b>공통 기능 구현에 집중</b>한다. 
- 이렇게 공통 기능 구현과 핵심 기능 구현을 분리하는 것이 AOP의 핵심이다.

### AOP
- AOP는 Aspect Oriented Programming의 약자로, 여러 객체에 공통적으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍 기법이다. 
- AOP는 핵심 기능과 공통 기능의 구현을 분리함으로써 핵심 기능을 구현한 코드의 수정 없이 공통 기능을 적용할 수 있게 만들어 준다.

> Aspect Oriented Programming을 우리말로는 관점 지향 프로그래밍 정도로 많이 번역하고 있으나, 여기서 Aspect는 구분되는 기능이나 요소를 의미하기 때문에 관점 보다는 <b>기능 내지 관심</b>이라는 표현이 더 알맞다.

- 스프링도 프록시를 이용해서 AOP를 구현하고 있다. 
- AOP의 기본 개념은 핵심 기능에 공통 기능을 삽입하는 것이다. 즉 핵심 기능의 코드를 수정하지 않으면서 공통 기능의 구현을 추가하는 것이 AOP이다. 
- 핵심 기능에 공통 기능을 삽입하는 방법에는 다음 세 가지가 있다.
	- (1) 컴파일 시점에 코드에 공통 기능을 삽입하는 방법
	- (2) 클래스 로딩 시점에 바이트 코드에 공통 기능을 삽입하는 방법
	- (3) <b>런타임에 프록시 객체를 생성</b>해서 <b>공통 기능을 삽입</b>하는 방법
- 첫 번째 방법은  AOP 개발 도구가 소스 코드를 컴파일 하기 전에 공통 구현 코드를 소스에 삽입하는 방식으로 동작한다. 
- 두 번째 방법은 클래스를 로딩할 때 바이트 코드에 공통 기능을 클래스에 삽입하는 방식으로 동작한다. 
- 이 두가지는 스프링 AOP에서는 지원하지 않으며 AspectJ와 같이 AOP 전용 도구를 사용해서 적용할 수 있다.

- 스프링이 제공하는  AOP 방식은 프록시를 이용한 세 번째 방식이다.
- 프록시 방식은 앞서 살펴본 것 처럼 중간에 프록시 객체를 생성한다. 그리고 실제 객체의 기능을 실행하기 전 후에 공통 기능을 호출한다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image2.png)

- <b>스프링 AOP는 프록시 객체를 자동으로 만들어준다.</b> 따라서 ExeTimeCalculator 클래스 처럼 상위 타입의 인터페이스를 상속받은 프록시 클래스를 직접 구현할 필요가 없다. 단지 공통 기능을 구현한 클래스만 알맞게 구현하면 된다.

- AOP에서 공통 기능을 Aspect라고 하는데 Aspect 외에 알아두어야 할 용어를 다음과 같이 정리했다.

#### AOP 주요 용어

|용어|의미|
|----|-------|
|Advice|언제 공통 관심 기능을 핵심 로직에 적용할지를 정의하고 있다. 예를 들어 메서드를 호출하기 전(언제)에 트랜잭션 시작(공통 기능) 기능을 적용한다는 것을 정의한다.|
|Joinpoint|Advice를 적용 가능한 지점을 의미한다. 메서드 호출, 필드 값 변경 등이 Joinpoint에 해당한다. 스프링은 프록시를 이용해서 AOP를 구현하기 떄문에 메서드 호출에 대한 Joinpoint만 지원한다.|
|Pointcut|Joinpoint의 부분 집합으로 실제 Advice가 적용되는 Joinpoint를 나타낸다. 스프링에서는 정규 표현식이나 AspectJ의 문법을 이용하여 Pointcut을 정의할 수 있다.|
|Weaving|Advice를 핵심 로직 코드에 적용하는 것을 weaving이라고 한다.|
|Aspect|여러 객체에 공통적으로 적용되는 기능을 Aspect라고 한다. 트랜잭션이나 보안 등이 Aspect의 좋은 예이다.|


### Advice 종류
- 스프링은 프록시를 이용해서 메서드 호출 시점에 Aspect를 적용하기 때문에 구현 가능한 Advice의 종류는 다음과 같다.

#### 스프링에서 구현 가능한 Advice의 종류

|종류|설명|
|------|---------|
|Before Advice|대상 객체의 메서드 호출 전에 공통 기능을 실행한다.|
|After Returning Advice|대상 객체의 메서드가 익셉션 없이 실행된 이후에 공통 기능을 실행한다.|
|After Throwing Advice|대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우에 공통 기능을 실행한다.|
|After Advice|익셉션 발생 여부에 상관없이 객체의 메서드 실행 후 공통 기능을 실행한다. (try-catch-finally의 finally 블록과 비슷하다.)|
|Around Advice|대상 객체의 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능을 실행하는데 사용된다.|

- 이 중에서 널리 사용되는 것은 <b>Around Advice</b>이다. 이유는 <b>대상 객체의 메서드를 실행하기 전/후, 익셉션 발생 시점 등 다양한 시점에 원하는 기능을 삽입할 수 있기 때문</b>이다. 
- 캐시 기능, 성능 모니터링 기능과 같은 Aspect를 구현할 때에는 Around Advice를 주로 이용한다. 

## 스프링 AOP 구현

- 스프링 AOP를 이용해서 공통 기능을 구현하고 적용하는 방법은 단순하다. 다음과 같은 절차만 따르면 된다.
	- Aspect로 사용할 클래스에 @Aspect 애노테이션을 붙인다.
	- @Pointcut  애노테이션으로 공통 기능을 적용할 Pointcut을 정의한다. 
	- 공통 기능을 구현한 메서드에 @Around 애노테이션을 적용한다.

### @Aspect, @Pointcut, @Around를 이용한  AOP 구현
- 개발자는 공통 기능을 제공하는 Aspect 구현 클래스를 만들고 자바 설정을 이용해서 Aspect를 어디에 적용할지 설정하면 된다. 
- Aspect는  @Aspect 애노테이션을 이용해서 구현한다. 프록시는 스프링 프레임워크가 알아서 만들어준다.

#### src/main/java/aspect/ExeTimeAspect.java
```java
package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeTimeAspect {
	
	@Pointcut("execution(public * aopex..*(..))")
	private void publicTarget() {
		
	}
	
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n", 
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()), 
					(finish - start));
		}
	}
}
```

- 먼저 @Aspect 애노테이션을 적용한 클래스는 Advice와 Pointcut을 함께 제공한다.
- @Pointcut은 공통 기능을 적용할 대상을 결정한다.
- @Around 애노테이션은 Around Advice를 설정한다. @Around 애노테이션의 값이 "publicTarget()"인데 이는 publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용한다는 것을 의미한다.
- publicTarget() 메서드는 aopex 패키지와 그 하위 패키지에 위치한 public 메서드를 Pointcut으로 설정하고 있으므로 aopex 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드에 @Around가 붙은 measure() 메서드를 적용한다.

- measure() 메서드의 ProceedingJoinPoint 타입 매개변수는 프록시 대상 객체의 메서드를 호출할 때 사용된다.
- proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출한다.  이 메서드를 호출하면 대상 객체의 메서드가 실행되므로 이 코드 이전과 이후에 공통 기능을 위한 코드를 위치시키면 된다. 
- ProceedingJoinPoint의 getSignature(), getTarget(), getArgs() 등의 메서드를 적용하고 있는데, 각 메서드는 호출한 메서드의 시그니처, 대상 객체, 인자 목록을 구하는데 사용된다. 이 메서드를 사용해서 대상 객체의 클래스 이름과 메서드 이름을 출력한다. 

> 자바에서는 메서드 이름과 매개변수를 합쳐서 메서드 시그니쳐라고 한다. 메서드 이름이 다르거나 매개변수 타입, 개수가 다르면 시그니쳐가 다르다고 표현한다. 자바에서 메서드 리턴 타입이나 익셉션 타입은 스그니처에 포함되지 않는다.

- 공통 기능을 적용하는데 필요한 코드를 구현했으므로 스프링 설정 클래스를 작성할 차례이다. 설정 클래스는 다음과 같다.

#### src/main/java/config/AppCtx.java
 
```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aopex.Calculator;
import aopex.RecCalculator;
import aspect.ExeTimeAspect;

@Configuration
@EnableAspectJAutoProxy
public class AppCtx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
```

- @Aspect 애노테이션을 붙인 클래스를 공통 기능으로 적용하려면 <b>@EnableAspectJAutoProxy</b> 애노테이션을 설정 클래스에 붙여냐 한다. 
- 이 애노테이션을 추가하면 스프링은 @Aspect 애노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정을 사용한다.

- ExeTimeAspect 클래스에 설정한 코드를 다시 보면

```java
@Pointcut("execution(public * aopex..*(..))")
private void publicTarget() {
		
}
	
@Around("publicTarget()")
public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
	...
}
```

- @Around 애노테이션은 Pointcut으로 publicTarget() 메서드를 설정했다. 
- publicTarget() 메서드의  @Pointcut은 aopex 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드를 설정한다. Calculator 타입이 aopex 패키지에 속하므로 calculator 빈에 ExeTimeAspect 클래스에 정의한 공통 기능인 measure()를 적용한다.

#### @Enable류 애노테이션
- 스프링은 @EnableAspectJAutoProxy와 같이 Enable로 시작하는 다양한 애노테이션을 제공한다. 
- @Enable로 시작하는 애노테이션은 관련 기능을 적용하는데 필요한 다양한 스프링 설정을 대신 처리한다. 
- 예를 들어 @EnableAspectJAutoProxy 애노테이션은 프록시 생성과 관련된 AnnotationAwareAspectJAutoProxyCreator 객체를 빈으로 등록한다.
- 웹 개발과 관련된 @EnableWebMvc 애노테이션 역시 웹 개발과 관련된 다양한 설정을 등록한다.
- @Enable 류의 애노테이션은 복잡한 스프링 설정을 대신 하기 때문에 개발자가 쉽게 스프링을 사용할 수 있도록 만들어준다.


#### src/main/java/main/MainAspect.java

```java
package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopex.Calculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();
	}
}
```
- 실행 결과

```java
RecCalculator.factorial([5]) 실행 시간 : 88800 ns
cal.factorial(5) = 120
jdk.proxy2.$Proxy17
```

- 여기에서 첫 번째 줄은 ExeTimeAspect 클래스의 measure() 메서드가 출력한 것이다. 
- 출력 결과를 보면 Calculator 타입이 RecCalculator 클래스가 아니고 $Proxy17이다. 이 타입은 스프링이 생성한 프록시 타입이다(프록시 클래스의 이름은 자바 버전이나 스프링 버전에 따라 달라질 수 있다).

- cal.factorial(5) 코드를 호출할 때 실행되는 과정은 다음과 같다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image3.png)

- AOP를 적용하지 않았으면 리턴한 객체는 프록시 객체가 아닌 RecCalculator 타입이었을 것이다. 
- AOP를 적용하지 않으면 어떻게 되는지 실제로 확인해보자. AppCtx 클래스에서 exeTimeAspect() 메서드를 주석처리하고 다시 MainAspect 클래스를 실행해보자 콘솔에 다음과 같은 메세지가 출력될 것이다.

```java
cal.factorial(5) = 120
aopex.RecCalculator
```

### ProceedingJoinPoint의 메서드

- Around Advice에서 사용할 공통 기능 메서드는 대부분 매개변수로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출하면 된다.
- 예를 들어 ExeTimeAspect 클래스도 다음처럼 proceed() 메서드를 호출했다.

```java
... 생략

public class ExeTimeAspect {
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			... 생력
		}
	}
}
```

- 호출되는 대상 객체에 대한 정보, 실행되는 메서드에 대한 정보, 메서드를 호출할 때 전달된 인자에 대한 정보가 필요할 때가 있다. 
- 이들 정보게 접근할 수 있도록 <b>ProceedingJoinPoint 인터페이스</b>는 다음 메서드를 제공한다.
	- Signature getSignature() : 호출되는 메서드에 대한 정보를 구한다. 
	- Object getTarget() : 대상 객체를 구한다. 
	- Object[] getArgs() : 매개면수 목록을 구한다. 

- org.aspectj.lang.Signature 인터페이스는 다음 메서드를 제공한다. 각 메서드는 호출되는 메서드의 정보를 제공한다.
	- String getName() : 호출되는 메서드의 이름을 구한다.
	- String toLongString() : 호출되는 메서드를 완전하게 표현한 문장을 구한다(메서드의 리턴타입, 매개변수 타입이 모두 표시된다).
	- String toShortString() : 호출되는 메서드를 축약해서 표현한 문장을 구한다(기본 구현은 메서드의 이름만 구한다).

## 프록시 생성방식
- MainAspect 클래스 코드를 다음과 같이 변경해 보자.

```java
// 수정 전
Calculator cal = ctx.getBean("calculator", Calculator.class);

// 수정 후 (import에도 RecCalculator 추가)
RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
```

- getBean() 메서드에 Calculator 타입 대신에 RecCalculator 타입을 사용하도록 수정했다. 
- 자바 설정 파일을 보면 다음과 같이 "calculator" 빈을 생성할 때 사용한 타입이 RecCalculator 클래스이므로 문제가 없어 보인다.

```java
// AppCtx 파일
@Bean
public Calculator calculator() {
	return new RecCalculator();
}
```

- 코드 수정 이후에 MainAspect 클래스를 실행해 보자. 정상 실행될 것이라는 예상과는 달리 다음과 같은 익셉션이 발생한다.

```
Exception in thread "main" org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'calculator' is expected to be of type 'aopex.RecCalculator' but was actually of type 'jdk.proxy2.$Proxy17'
```
- 익셉션 메시지를 보면 getBean() 메서드에 사용한 타입이 RecCalculator인데 반해 실제 타입은 $Proxy17이라는 메시지가 나온다. $Proxy17은 스프링이 런타입에 생성한 프록시 객체의 클래스 이름이다.
- 이 $Proxy17 클래스는 RecCalculator 클래스가 상속받은 Calculator 인터페이스를 상속받게 된다. 즉 다음과 같은 계층 구조를 갖는다.

#### 빈 객체가 인터페이스를 상속하면 인터페이스를 이용해서 프록시를 생성

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image4.png)

- 스프링 AOP를 위한 프록시 객체를 생성할 때 실제 생성한 빈 객체가 인터페이스를 상속하면 인터페이스를 이용해서 프록시를 생성한다.
- 앞서 예에서도 RecCalculator 클래스가 Calculator 인터페이스를 상속하므로 Calculator 인터페이스를 상속받은 프록시 객체를 생성했다.
- 따라서 아래 코드 처럼 빈의 실제 타입이 RecCalculator라고 하더라도 "calculator" 이름에 해당하는 빈 객체의 타입은 Calculator 인터페이스를 상속받은 프록시 타입이 된다.

```java
// 설정 클래스
// AOP 적용시 RecCalculator가 상속받은 Calculator 인터페이스를 이용해서 프록시 생성
@Bean
public Calculator calculator() {
	return new RecCalculator();
}

// 자바 코드
// "calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로
// RecCalculator로 타입 변환을 할 수 없기 때문에 익셉션 발생
RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
```

- 빈 객체가 인터페이스를 상속할 때 인터페이스가 아닌 클래스를 이용해서 프록시를 생성하고 싶다면 다음과 같이 설정하면 된다.

```java
@Configuration
@EnableAspectAutoProxy(proxyTargetClass = true)
public class AppCtx {
	... 생략 
}
```

- @EnableAspectJAutoProxy 애노테이션의 proxyTargetClass 속성을 true로 지정하면 인터페이스가 아닌 자바 클래스를 상속받아 프록시를 생성한다. 
- 스프링 프록시를 이용해 생성한 빈 객체를 구할 떄 다음과 같이 getBean() 메서드에 실제 클래스를 이용해서 빈 객체를 구할 수 있게 된다.

```java
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
	... 생략 
}

// 자바 코드. "calculator" 프록시의 실제 타입은 RecCalculator를 상속받았으므로 
// RecCalculator로 타입 변환 가능
RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
```

### execution 명시자 표현식

- Aspect를 적용한 위치를 지정할 때 사용한 Pointcut 설정을 보면 execution 명시자를 사용했다.

```java
@Pointcut("execution(public * aopex..*(..))")
private void publicTarget() {
}
```

- execution 명시자는 Advice를 적용할 메서드를 지정할 때 사용한다. 기본 형식은 다음과 같다.

```
execution(수식어패턴? 리턴타입패컨 클래스이름패턴?메서드이름패턴(매개변수패턴))
```

- '수식어패턴'은 생략 가능하며 public, protected 등이 온다. <b>스프링 AOP는 public 메서드에만 적용할 수 있기 떄문에 사실상 public만 의미있다.</b>

- '리턴타입패턴'은 리턴 타입을 명시한다. '클래스이름패턴'과 '메서드이름패턴'은 클래스이름 및 메서드 이름을 패턴으로 명시한다. '매개변수패턴'은 매칭될 매개변수에 대해서 명시한다.

- 각 패턴은 '\*'를 이용하여 모든 값을 표현할 수 있다. 또한 '..'(점 두 개)을 이용하여 0개 이상이라는 의미를 표현할 수 있다.

#### execution 명시자 예시

|예|설명|
|-----|-----|
|execution(public void set\*(..))|리턴 타입이 void이고, 메서드 이름이 set으로 시작하고, 매개변수가 0개 이상인 메서드 호출. 매개변수 부분에 '..'을 사용하여 매개변수가 0개 이상인 것을 표현했다.|
|execution(\* aopex.\*.\*())|aopex 패키지의 타입에 속한 매개변수가 없는 모든 메서드 호출|
|execution(\* aopex..\*.\*(..))|aopex 패키지 및 하위 패키지에 있는, 매개변수가 0개 이상인 메서드 호출. 패키지 부분에 '..'을 사용하여 해당 패키지 또는 하위 패키지를 표현했다.|
|execution(Long aopex.Calculator.factorial(..))|리턴 타입이 Long인 Calculator 타입의 factorial() 메서드 호출|
|execution(\* get\*(\*))|이름이 get으로 시작하고 매개변수가 한 개인 메서드 호출|
|execution(\* get\*(\*,\*))|이름이 get으로 시작하고 매개변수가 두 개인 메서드 호출|
|execution(\* read\*(Integer, ..))|메서드 이름이 read로 시작하고, 첫 번째 매개변수 타입이 Integer이며, 한 개 이상의 매개변수를 갖는 메서드 호출|

### Advice 적용 순서

- 한 Pointcut에 여러 Advice를 적용할 수도 있다.

#### src/main/java/aspect/CacheAspect.java

```java
package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {
	private Map<Long, Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * aopex..*(long))")
	public void cacheTarget() {
		
	}
	
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long) joinPoint.getArgs()[0];
		if (cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache에서 구함[%d]%n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed();
		cache.put(num,  result);
		System.out.printf("CacheAspect: Cache에 추가[%d]%n", num);
		return result;
	}
}
```

- CacheAspect 클래스는 간단하게 캐시를 구현한 공통 기능이다. 동작 순서는 아래와 같다.
	- 첫 번째 인자를 Long 타입으로 구한다.
	- 22행에서 구한 키 값이 cache에 존재하면 키에 해당하는 값을 구해서 리턴한다.
	- 22행에서 구한 키 값이 cache에 존재하지 않으면 프록시 대상 객체를 실행한다.
	- 프록시 대상 객체를 실행한 결과를 cache에 추가한다.
	- 프록시 대상 객체의 실행 결과를 리턴한다.


- @Around 값으로  cacheTarget() 메서드를 지정했다. @Pointcut 설정은 첫 번째 인자가 long인 메서드를 대상으로 한다. 따라서 execute() 메서드는 앞서 작성한 Calculator의 factorial(long) 메서드에 적용된다.

- 새로운 Aspect를 구현했으므로 스프링 설정 클래스에 다음과 같이 두 개의 Aspect를 추가할 수 있다. ExeTimeAspect는 앞서 구현한 시간 측정 Aspect이다. 두 Aspect에서 설정한 Pointcut은 모두 Calculator 타입의 factorial() 메서드에 적용된다.


#### src/main/java/config/AppCtxWithCache.java

```java
package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import aopex.Calculator;
import aopex.RecCalculator;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {
	
	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
```

- 이 설정 클래스를 이용하는 예제코드를 다음과 같이 작성하자.

#### src/main/java/main/MainAspectWithCache.java

```java
package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopex.Calculator;
import config.AppCtxWithCache;

public class MainAspectWithCache {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);
		
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		cal.factorial(7);
		cal.factorial(7);
		cal.factorial(5);
		cal.factorial(5);
		ctx.close();
	}
}
```

- 실행 결과

```
RecCalculator.factorial([7]) 실행 시간 : 49900 ns
CacheAspect: Cache에 추가[7]
CacheAspect: Cache에서 구함[7]
RecCalculator.factorial([5]) 실행 시간 : 15600 ns
CacheAspect: Cache에 추가[5]
CacheAspect: Cache에서 구함[5]
```

- "RecCalculator.factorial(숫자) 실행 시간" 메시지는 ExeTimeAspect가 출력한다. "CacheAspect: Cache에 추가"나 "CacheAspect: Cache에서 구함" 메시지는 CacheAspect가 출력한다. 

- 결과를 보면 첫 번째 factorial(7)을 실행할 때와 두 번째 factorial(7)을 실행할 떄 콘솔에 출력되는 내용이 다르다. 첫 번째 실행결과는 ExeTimeAspect와 CacheAspect가 모두 적용되었고 두 번째 실행 결과는 CacheAspect만 적용되었다. 이렇게 첫 번째와 두 번째 실행 결과가 다른 이유는 Advice를 다음 순서로 적용했기 때문이다.

#### Advice 적용 순서

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image5.png)

- calculator 빈은 실제로는 CacheAspect 프록시 객체이다. 그런데 CacheAspect 프록시 객체의 대상 객체는 ExeTimeAspect의 프록시 객체이다. 그리고 ExeTimeAspect 프록시의 대상 객체가 실제 대상 객체이다.

```java
Calculator cal = ctx.getBean("calculator", Calculator.class);
cal.factorial(7);  // CacheAspect 실행 -> ExeTimeAspect 실행 -> 대상 객체 실행
```

- 그래서 cal.factorial(7)을 실행하면 CacheAspect의 코드가 먼저 실행된다. 실제 실행 순서는 다음과 같다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image6.png)

- CacheAspect는 cache 맵에 데이터가 존재하지 않으면 joinPoint.proceed()를 실행해서 대상을 실행한다. 그 대상이 ExeTimeAspect이므로 ExeTimeAspect의 measure() 메서드가 실행된다(1).
- ExeTimeAspect는 실제 대상 객체를 실행하고(2) 콘솔에 실행 시간을 출력한다(3).
- ExeTimeAspect 실행이 끝나면 CacheAspect는 cache 맵에 데이터를 넣고 콘솔에 "CacheAspect: Cache에 추가" 메시지를 출력한다(4). 
- 그래서 factorial(7)을 처음 실행할 때에는 ExeTimeAspect가 출력하는 메세지가 먼저 출력되고 CacheAspect가 출력하는 메세지가 뒤에 출력되는 것이다.

- factorial(7)을 두 번째 실행할 때는 실행 흐름이 다음과 같이 달라진다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image7.png)

- 처음 factorial(7)을 호출하면 CacheAspect의 cache 맵에 데이터를 추가한다. 따라서 factorial(7)을 두 번째 호출하면 cache.containsKey(num)이 true를 리턴하므로 콘솔에 "CacheAspect: Cache에서 구함" 메시지를 출력하고 cache 맵에 담긴 값을 리턴하고 끝난다. 이 경우 joinPoint.proceed()를 실행하지 않으므로 ExeTimeAspect나 실제 객체가 실행되지 않는다. 
- 콘솔에도 다음과 같이 CacheAspect가 생성한 메시지만 출력한다.

```
CacheAspect: Cache에서 구함 [7]
```

- 어떤 Aspect가 먼저 적용될지는 스프링 프레임워크나 자바 버전에 따라 달라질 수 있기 때문에 적용 순서가 중요하다면 직접 순서를 지정해야 한다.
- 이럴때 사용하는 것이 <b>@Order 애노테이션</b>이다. <b>@Aspect 애노테이션과 함께 @Order 애노테이션을 클래스에 붙이면 @Order 애노테이션에 지정한 값에 따라 적용 순서를 결정</b>한다.

- @Order 애노테이션의 값이 작으면 먼저 적용하고 크면 나중에 적용한다. 예를 들어 다음과 같이 두 Aspect 클래스에 @Order 애노테이션을 적용했다고 하자.

```java
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExeTimeAspect {
	...
}

@Aspect
@Order(2)
public class CacheAspect {
	...
}
```

- ExeTimeAspect에 적용한 @Order 애노테이션 값이 1이고 CacheAspect에 적용한 @Order 애노테이션의 값이 2이므로 다음과 같이 ExeTimeAspect가 먼저 적용되고 그 다음에 CacheAspect가 적용된다.


#### @Order 애노테이션을 이용해서 적용 순서 변경 가능

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image8.png)


- 실행 결과

```
CacheAspect: Cache에 추가[7]
RecCalculator.factorial([7]) 실행 시간 : 65560100 ns
CacheAspect: Cache에서 구함[7]
RecCalculator.factorial([7]) 실행 시간 : 231600 ns
CacheAspect: Cache에 추가[5]
RecCalculator.factorial([5]) 실행 시간 : 221200 ns
CacheAspect: Cache에서 구함[5]
RecCalculator.factorial([5]) 실행 시간 : 200500 ns
```

- factorial(7)을 처음 실행할 때와 두 번째 실행할 때 모두 두 Aspect가 적용되는 것을 확인할 수 있다.


### @Around의 Pointcut 설정과 @Pointcut 재사용

- @Pointcut 애노테이션이 아닌 @Around 애노테이션에 execution 명시자를 직접 지정할 수도 있다. 

```java
@Aspect
public class CacheAspect {
	@Around("execution(public * aopex..*(..))")
	public Object execution(ProceedingJoinPoint joinPoint) throws Throwable {
		... 생략
	}
}
```

- 만약 같은 Pointcut을 여러 Advice가 함께 사용한다면 공통 Pointcut을 재사용할 수도 있다. 

```java 
@Aspect
public class ExeTimeAspect {
	
	@Pointcut("execution(public * aopex..*(..))")
	private void publicTarget() {
	
	}
	
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		... 생략
	}
}
```

- 이 코드에서 @Around는 publicTarget() 메서드에 설정한 Pointcut을 사용한다.
- publicTarget() 메서드는 private인데 이 경우 같은 클래스에 있는 @Around 애노테이션에서만 해당 설정을 사용할 수 있다. 
- 다른 클래스에 위치한 @Around 애노테이션에서 publicTarget() 메서드의 Pointcut을 사용하고 싶다면 publicTarget() 메서드를 public으로 바꾸면 된다.

```java
@Aspect
public class ExeTimeAspect {
	
	@Pointcut("execution(public * aopex..*(..))")
	public void publicTarget() {
	}
}
```
- 그리고 해당 Pointcut의 완전한 클래스 이름을 포함한 메서드 이름을 @Around 애노테이션에서 사용하면 된다. 
- 예를 들어서 다음 설정은 CacheAspect 클래스의 @Around 메서드에서 ExeTimeAspect 클래스의 publicTarget()에 정의된 Pointcut을 사용한다. 

```java
@Aspect
public class CacheAspect {
	
	@Around("aspect.ExeTimeAspect.publicTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable) {
		... 생략
	}
}
```

- CacheAspect와 ExeTimeAspect 클래스는 같은 패키지에 위치하므로 패키지 이름이 없는 간단한 클래스 이름으로 설정할 수 있다.

```java
@Aspect
public class CacheAspect {
	
	@Around("ExeTimeAspect.publicTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		... 생략 
	}
}
```

- 여러 Aspect에서 공통적으로 사용하는 Pointcut이 있다면 다음과 같이 별도 클래스에 Pointcut을 정의하고 각 Aspect 클래스에서 해당 Pointcut을 사용하도록 구성하면 Pointcut 관리가 편해진다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20AOP%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/images/image9.png)

- @Pointcut을 설정한 CommonPointcut은 빈으로 등록할 필요가 없다.
- @Around 애노테이션에서 해당 클래스에 접근 가능하면 해당 Pointcut을 사용할 수 있다.

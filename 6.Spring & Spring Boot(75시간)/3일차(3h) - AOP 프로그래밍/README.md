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

- 이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부거적인 기능을 제공하는 객체를 <b>프록시(proxy)</b>라고 부른다.
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

### Advice 종류

## 스프링 AOP 구현

### @Aspect, @Pointcut, @Around를 이용한  AOP 구현

### ProceedingJoinPoint의 메서드

## 프록시 생성방식

### execution 명시자 표현식

### Advice 적용 순서

### @Around의 Pointcut 설정과 @Pointcut 재사용



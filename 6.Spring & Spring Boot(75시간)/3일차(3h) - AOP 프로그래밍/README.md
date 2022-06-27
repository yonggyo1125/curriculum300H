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



### AOP

### Advice 종류

## 스프링 AOP 구현

### @Aspect, @Pointcut, @Around를 이용한  AOP 구현

### ProceedingJoinPoint의 메서드

## 프록시 생성방식

### execution 명시자 표현식

### Advice 적용 순서

### @Around의 Pointcut 설정과 @Pointcut 재사용



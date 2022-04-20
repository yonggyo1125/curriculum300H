# 람다식(Lamba expression)

## 람다식이란?
- 메서드를 하나의 '식(expression)'으로 표현한 것
- 람다식은 메서드를 간략하면서도 명확한 식으로 표현할 수 있게 해준다.
- 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로, 람다식을 '익명 함수(anonymous function)' 이라고도 한다.

## 람다식 문법 살펴보기 
### 매개변수 자료형과 괄호 생략하기
- 매개변수 자료형 생략 가능, 단 매개변수 타입이 있으면 괄호를 생략할 수 없다.
- 매개변수가 하나인 경우에는 괄호도 생략할 수 있음
- 매개변수가 2개 이상인 경우 괄호를 생략할 수 없다.
```
str -> { System.out.println(str); }		
(String str) -> { System.out.println(str) }

(x, y) -> { System.out.println(x + y) }

```
### 중괄호 생략하기
- 중괄호 안의 구현 부분이 한 문장인 경우 중괄호를 생략할 수 있다.
```
str -> System.out.println(str);
```

- 중괄호 안의 구현부분이 한 문장이더라도 return 문은 중괄호를 생략할 수 없다.
```
str -> return str.length(); // 잘못된 형식(오류 발생)
str -> str.length(); // 올바른 형식
```

### return 생략하기
- 중괄호 안의 구현 부분이 return문 하나라면 중괄호와 return을 모두 생략하고 식만 쓴다.
```
(x,y) -> x+y
str -> str.length()
```
* * * 

## 람다식 사용하기

### 람다식 구현 방법
1. 인터페이스를 만든다.
2. 구현할 메서드를 선언한다.
	```
	package day18;

	public interface MyCalculator {
		int plus(int num1, int num2);
	}
	```

3. 이때 메서드는 한개만 정의한다.
	```
	package day18;

	public class MyCalulatorTest {
		public static void main(String[] args) {
			MyCalculator calcu = (x, y) -> x + y;
			int result = calcu.plus(20, 30);
			
			System.out.println(result);
		}
	}
	```
4. 람다식을 구현할 때 되도록 생략할 수 있는 부분은 생략하여 구현


### 함수형 인터페이스(Functional Interface)
- 람다식은 메서드 이름이 없고 메서드를 실행하는 데 필요한 매개변수와 매개변수를 활용한 실행코드를 구현하는 것
- 따라서 람다식식을 구현하기 위해서는 함수형 인터페이스를 만들고 인터페이스에 람다식으로 구현할 메서드를 선언 하는 것
- 람다식은 오직 하나의 추상 메서드만 선언한 인터페이스를 구현할 수 있다
	- 람다식은 이름이 없는 익명 함수로 구현하기 때문에 인터페이스에 추상 메서드가 여러 개 있다면 어떤 메서드를 구현한 것인지 모호해 지므로 하나의 메서드만 허용
	- 다만 구현체가 있는 static메서드와 default 메서드는 정의 할 수 있습니다(모호함이 없으므로)
		
	#### @FunctionalInterface 애노테이션
	- 람다식은 하나의 추상 메서드를 선언해야 하므로 실수하기 쉽다 
	- 이러한 실수를 방지하기 위해 @FunctionalInterface 애노테이션을 사용합니다.
	- @FunctionalInterface 애노테이션을 선언하고 메서드를 하나 이상 선언하면 오류가 발생하므로 실수를 방지할 수 있다.
		```
		package day18;

		@FunctionalInterface
		public interface MyCalculator {
			int plus(int num1, int num2);
		}
		```
- 람다식은 익명 내부 클래스를 구현하는 것
	- 함수형 언어에서는 함수만 따로 호출할 수 있지만, 자바에서는 참조 변수 없이 메서드를 호출할 수 없다.
	- 람다식은 익명(지역) 내부 클래스를 구현하고 생성된 인스턴스에서 선언된 메서드를 호출하는 것과 같다.
	- 따라서 람다식은 익명 내부 클래스의 속성과 동일하다.
		- 람다식 내에서 사용되는 지역변수는 상수화 된다.
			```
			익명(지역) 내부 클래스는 메서드내에서 구현되고 익명(지역) 내부 클래스의 인스턴스 메서드가 호출되는데, 메서드내에 지역변수를 
			익명(지역) 내부클래스의 메서드가 참조하고 있다면 메서드 호출이 완료가 되도 제거가 되면 안된다.
			(메서드는 호출이 종료가 되면 스택메모리 영역에서 자원이 제거가 된다)
			따라서 제거가 되지 않기 위해 항상 유지가 되는 데이터 영역의 상수형태로 컴파일 하여 변환한다(변수 앞에 final 키워드가 생략되어 있다라고 보면 된다.)
			```
		
	- 람다식으로 코드의 표현이 간단해 졌지만 근본적으로 달라진 것은 없다.
		````
		package day18;

		public class MyCaculatorTest2 {
			public static void main(String[] args) {
				
				int num3 = 10;
				MyCalculator calcu = new MyCalculator() {
					@Override
					public int plus(int num1, int num2) {
						//num3 = 20; 익명(지역) 내부 클래스의 메서드에서 사용되는 지역 변수는 상수화 된다
						int result = num1 + num2 + num3;
						return result;
					}
				};
				
				
				int result = calcu.plus(20, 30);
				System.out.println(result);
			}
		}
	```

### 함수형 인터페이스 타입의 매개변수와 반환 타입
- 매개변수
	```
	package day18;

	public class MyCalculatorParamTest {
		public static void main(String[] args) {
			calcuPlus((x, y) -> x + y);
		}
		
		public static void calcuPlus(MyCalculator calcu) {
			int result = calcu.plus(10, 20);
			System.out.println(result);
		}
	}
	```
	
- 반환타입
```
package day18;

public class MyCalculatorReturnTest {
	public static void main(String[] args) {
		MyCalculator calcu1 = calcuPlus();
		int result1 = calcu1.plus(10,20);
		System.out.println(result1);
		
		MyCalculator calcu2 = calcuPlus2();
		int result2 = calcu2.plus(10,20);
		System.out.println(result2);
	}
	
	public static MyCalculator calcuPlus() {
		MyCalculator calcu = (x, y) -> x + y;
		
		return calcu;
	}

	public static MyCalculator calcuPlus2() {		
		return (x, y) -> x + y;
	}
}
```

## java.util.function패키지
- 대부분의 메서드는 타입이 비슷하다
- 매개변수가 없거나 한 개, 두 개, 반환값이 없거나 한 개이다.
- 제네릭 메서드로 정의하면 매개변수나 반환 타입이 달라도 문제가 되지 않는다. 
- java.util.function 패키지에 일반적으로 자주 쓰는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았다.
- 매번 함수형 인터페이스를 정의하기 보다는 가능하면 이 패키지의 인터페이스를 활용하는 것이 좋다.

	### java.util.function  패키지의 주효 함수형 인터페이스
	|함수형 인터페이스|메서드|설명|
	|----|-----|-----|
	|java.lang.Runnable|void() run()|매개변수도 없고 반환값도 없음|
	|Supplier<T>|T get()|매개변수는 없고 반환값만 있음|
	|Consumer<T>|void accept(T t)|Supplier와 반대로 매개변수만 있고, 반환값이 없음|
	|Function<T,R>|R apply(T t)|일반적인 함수. 하나의 매개변수를 받아서 결과를 반환|
	|Predicate<T>|boolean test(T t)|조건식을 표현하는데 사용됨.   매개변수는 하나. 반환값은 boolean|

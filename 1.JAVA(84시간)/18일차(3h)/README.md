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

## 람다식 사용하기

### 람다식 구현 방법
1. 인터페이스를 만든다.
2. 구현할 메서드를 선언한다.
	````
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

# 예외처리

## 예외 클래스
### 오류란 무인가요?
- <b>컴파일 오류(compile error)</b>
	- 프로그램 코드 작성 중 실수로 발생하는 오류
	- 발생한 컴파일 오류를 모두 수정해야 프로그램이 정상적으로 실행 되므로, 문법적으로 오류가 있다는 것을 바로 알 수 있습니다.
	
- <b>실행 오류(runtime error)</b>
	- 실행 중인 프로그램이 의도하지 않은 동작을 하거나 프로그램이 중지되는 오류 입니다. 실행 오류 중 프로그램을 잘목 수현하여 의도한 바와 다르게 실행되어 생기는 오류를 <b>버그(bug)</b>라고 합니다.
	- 프로그램 실행 중에 발생하는 오류는 예측하기 어려운 경우가 많고, 프로그램이 비정상 종료되면서 갑자기 멈춰 버립니다.
	- 자바은 이러한 비정상 종료를 최대한 줄이기 위해 다양한 예외에 대한 처리방법을 가지고 있습니다.
	- 예외 처리를 하는 목적은 프로그램이 비정상 종료 되는 것을 방지하기 위한 것 입니다.

### 오류와 예외
- <b>시스템 오류(error)</b> 
	- 자바 가상 머신에서 발생
	- 시스템 오류의 예로는 사용 가능한 동적 메모리가 없는 경우나 스택 메모리의 오버플로가 발생한 경우 등이 있습니다.
	- 이러한 시스템 오류는 프로그램에서 제어할 수 없습니다.
	
- <b>예외(exception)</b>
	- 프로그램에서 제어할 수 있습니다.
	- 예를 들어 프로그램에서 파일을 읽어 사용하려는데 파일이 없는 경우, 네트워크로 데이터를 전송하려는데 연결이 안된 경우, 배열 값을 출력하는데 배열 요소가 없는 경우 등
	
![예외처리1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC1.png)

- 오류 클래스는 모두 **Throwable 클래스**에서 상속받습니다. **Error 클래스**의 하위 클래스는 시스템에서 발생하는 오류를 다루며 프로그램에서 제어하지 않습니다. 프로그램에서 제어하는 부분은 **Exception 클래스**와 그 하위에 있는 예외 클래스 입니다.

### 예외 클래스의 종류
- 프로그램에서 처리하는 예외 클래스의 최상위 클래스는 Exception 클래스 입니다. Exception 클래스의 내용을 살펴보면 다음과 같습니다.
```
Class Exception

java.lang.Object
	java.lang.Throwable
		java.lang.Exception
```

- 다음 그림은 Exception 하위 클래스 중 사용 빈도가 높은 클래스 위주로 계층도를 표현한 것 입니다.
![예외처리2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC2.png)

-  Exception 클래스 하위에는 이 외에도 많은 클래스가 있습니다. 계층도에서 IOException 클래스는 입출력에 대한 예외를 처리하고, RuntimeException은 프로그램 실행 중 발생할 수 있는 오류에 대한 예외를 처리합니다.
- 이클립스 같은 개발환경에서는 예외가 발생하면 대부분 처리하라는 컴파일 오류 메세지를 띄웁니다.  곧 배우게될 try ~ catch문을 사용하여 예외 처리를 해야 합니다.
- Exception 하위 클래스 중 **RuntimeException**은 try ~ catch문을 사용하여 **예외 처리를 하지 않아도 컴파일 오류가 나지 않습니다**. 곧 배우게될 예외 전가도 처리하지 않아도 컴파일 오류가 발생하지 않습니다.
- 예를 들어 RuntimeException 하위 클래스 중 ArithmeticException은 산술 연산 중 발생할 수 있는 예외, 즉 '0'으로 숫자 나누기와 같은 경우 발생하는 예외 입니다. 이러한 **컴파일러에 의해 체크되지 않는 예외는 프로그래머가 알아서 처리해야 하므로 주의해야 합니다.**

## 예외 처리하기
### try ~ catch문
```
try {
	예외가 발생할 수 있는 코드 부분
} catch (처리할 예외 타입 e) {
	try 블록 안에서 예외가 발생했을 때 예외를 처리하는 부분
}
```
- try 블록에는 **예외가 발생할 가능성이 있는 코드를 작성**합니다. 
- try 블록 안에 예외가 발생하면 바로 catch 블록이 수행됩니다. 
- catch문의 괄호()안에 쓰는 예외 타입은 예외 상황에 따라 달라집니ㅏㄷ.

#### day11/exception/ArrayExceptionHandling.java - try ~ catch문 사용하
```
package day11.exception;

public class ArrayExceptionHandling {
	public static void main(String[] args) {
		int[] arr = new int[5];
		
		try {
			// 예외가 발생할 수 있으므로 try 블록에 작성 
			for (int i = 0; i <= 5; i++) {
				arr[i] = i;
				System.out.println(arr[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// 예외가 발생하면 catch 블록 수행 
			System.out.println(e);
			System.out.println("예외처리 부분");
		}
	}
}

실행결과

0
1
2
3
4
java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
예외처리 부분
```
- 배열에 저장하려는 값의 개수가 배열 범위를 벗어났기 때문에 예외가 발생한 것 입니다.
- 참고로 이 예외는 **RuntimeException의 하위 클래스**인 ArrayIndexOutOfBoundsException으로 처리하는에, 이 클래스는 **예외처리를 하지 않아도 컴파일 오류가 나지 않습니다.**
- 따라서 프로그래머가 직접 예외 처리를 하지 않으면 예외가 잡히지 않아서 예외가 발생하는 순간(이 예제에서는 i가 5가 되는 순간) 프로그램이 갑자기 멈춥니다. 그러므로 예외가 발생한 순간 프로그램이 비정상 종료되지 않도록 예외처리를 해주어야 합니다.
- **예외 처리는 프로그램이 비정상  종료되는 것을 방지할 수 있으므로 매주 중요**합니다.

### 컴파일러에 의해 예외가 처리되는 경우
- 자바에서 제공하는 많은 예외 클래스들은 컴파일러에 의해 처리됩니다. 이런 경우 자바에서는 예외 처리를 하지 않으면 컴파일 오류가 계속 남습니다.
- 컴파일오류가 발생하므로 적절한 예외처리가 이뤄지지 않는다면 컴파일이 발생하지 않아 실행되지 않습니다.

#### day11/exception/ExceptionHandling1.java
```
package day11.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionHandling1 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("a.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e); // 예외 클래스의 toString() 메서드 호출 
		}
		
		System.out.println("여기도 수행됩니다."); // 정상 출력
	}
}

실행 결과

java.io.FileNotFoundException: a.txt (지정된 파일을 찾을 수 없습니다)
여기도 수행됩니다.
```
> 예외 처리를 한다고 해서 프로그램의 예외 상황 자체를 막을 수는 없습니다. 하지만 예외 처리를 하면 예외 상황을 알려 주는 메시지를 볼 수 있고, 프로그램이 비정상 종료되지 않고 계속 수행되도록 만들 수 있습니다.

### try-catch-finally문
- try 블록이 수행되면 finally  블록은 어떤 경우에도 반드시 실행됩니다.
- try나 catch에 return문이 있어도 수행됩니다.
```
try {
	예외가 발생할 수 있는 부분
} catch (처리할 예외 타입 e) {
	예외를 처리하는 부분
} finally {
	항상 수행되는 부분
}
```
#### day11/exception/ExceptionHandling2.java
```
package day11.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionHandling2 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("a.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return;
		} finally {
			if (fis != null) {
				try {
					fis.close();  // 파일 입력 스트림 닫기
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("항상 수행됩니다.");
		}
		System.out.println("여기도 수행됩니다.");
	}
}

실행결과

java.io.FileNotFoundException: a.txt (지정된 파일을 찾을 수 없습니다)
항상 수행됩니다.
```

- 프로그램에서 사용항 리소스는 프로그램이 종료되면 자동으로 해제됩니다. 예를 들어 네트워크가 연결되었을 경우에 채팅 프로그램이 종료될 때 연결도 닫힙니다.
- 하지만 끝나지 않고 계속 수행되는 서비스 같은 경우에 리소스를 여러 번 반복해서 열기만 하고 닫지 않는다면 문제가 발생합니다. **시스템에서 허용하는 자원은 한계가 있습니다.**
- 사용한 시스템 리소스는 사용 후 반드시 close() 메서드로 닫아 주어야 합니다.
- 입력받은 파일이 없는 경우에 대해 try - catch 문을 사용해 FileNotFoundException예외 처리를 하였습니다. 프로그램을 실행하면 a.txt 파일이 없으므로 예외가 발생하여 catch 블록이 수행될 것 입니다.
- 예외를 출력하고 강제로 return을 해보았지만 **return문과 상관 없이 finally 블록이 수행**되어 '항상 수행됩니다.' 문장이 출력 됩니다.
- 파일 접근을 위해 열린 리소스는 **예외 발생과 상관없이 항상 닫혀야 하므로 finally 블록에 파일 리소스를 닫는 코드를 구현**하였습니다.


### try-with-resources문
- 시스템 리소스를 사용하고 해제하는 코드는 다소 복잡합니다. 
- JDK1.7 부터 try-with-resources문을 제공하여 close() 메서드를 명시적으로 호출하지 않아도 try 블록 내에서 열린 리소스를 자동으로 닫도록 만들 수 있습니다.
- try-with-resources 문법을 사용하려면 해당 리소스가 **AutoCloseable 인터페이스를 구현**해야 합니다.

- AutoCloseable 인터페이스에는 close() 메서드가 있고 이를 구현한 클래스는 close()를 명시적으로 호출하지 않아도 close() 메서드 부분이 호출됩니다.

#### FileInputStream의 JavaDoc 예시 
![예외처리3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC3.png)

- FileInputStream 클래스는 Closeable과 AutoCloseable 인터페이스를 구현했습니다. 
- JDK1.7부터는 try-with-resources 문법을 사용하면 FileInputStream을 사용할 때 close()를 명시적으로 호출하지 않아도 정상적인 경우와 예외가 발생한 경우 모두 close()메서드가 호출됩니다.
- FileInputStream 이외에 네트워크(Socket)와 데이터베이스(Connection) 관련 클래스도 AutoCloseable 인터페이스를 구현하고 있습니다.

>AutoCloseable  인터페이스를 구현한 클래스가 무엇이 있는지 궁금하다면 JavaDoc에서 AutoCloseable Interface를 찾아보세요.

#### day11/exception/AutoCloseObj.java
```
package day11.exception;

public class AutoCloseObj implements AutoCloseable {
	@Override
	public void close() throws Exception {
		System.out.println("리소스가 close() 되었습니다.");
	}
}
```
- AutoCloseable 인터페이스는 반드시 close() 메서드를 구현해야 합니다.

#### day11/exception/AutoCloseTest.java
```
package day11.exception;

public class AutoCloseTest {
	public static void main(String[] args) {
		try (AutoCloseObj obj = new AutoCloseObj()) { // 사용할 리소스 선언
			
		} catch(Exception e) {
			System.out.println("예외 부분 입니다.");
		}
	}
}

실행결과

리소스가 close() 되었습니다.
```
- try-with-resources문을 사용할 때 try문의 괄호() 안에 리소스를 선언합니다.
- 이 예제는 예외가 발생하지 않고 정상 종료 되는데 출력 결과를 보면 close() 메서드가 호출되어 **리소스가 close() 되었습니다.** 문장이 출력 됩ㄴ디ㅏ.
- 리소스를 여러개 생성해야 한다면 세미 콜론(;)으로 구분합니다.
```
try(A a = new A(); B b = new B()) {
	...
} catch(Exception e) {
	...
}
```

#### day11/exception/AutoCloseObjTest.java
```
package day11.exception;

public class AutoCloseObjTest {
	public static void main(String[] args) {
		try (AutoCloseObj obj = new AutoCloseObj()) { // 사용할 리소스 선언
			throw new Exception(); // 강제 예외 발생
		} catch(Exception e) {
			System.out.println("예외 부분 입니다.");
		}
	}
}


실행결과

리소스가 close() 되었습니다.
예외 부분 입니다.
```
- 강제로 예외를 발생시키면 catch 블록이 수행됩니다.
- 출력 결과를 보면 리소스의 close() 메서드가 먼저 호출되고 예외 블록 부분이 수행되는 것을 알 수 있습니다.
- 이처럼 try-with-resources문을 사용하면 close() 메서드를 명시적으로 호출하지 않아도 정상 종료된 경우와 예외가 발생한 경우 모두 리소스가 잘 해제 됩니다.

##  예외 처리 미루기

## 사용자 정의 예외


* * * 
# java.lang 패키지

## Object 클래스

### equals(Object obj)

### hashCode()

### toString()

### clone()

### 공변 반환타입

### 얕은 복사와 깊은 복사


### Class 객체를 얻는 방법

## String 클래스

### 변경 불가능한(immutable)클래스

### 문자열의 비교

### 문자열 리터럴

### 빈 문자열(empty string)

### String클래스의 생성자와 메서드

### join()과 StringJointer

### 문자 인코딩 변환

### String.format()

### 기본형 값을 String으로 변환

### String을 기본형 값으로 변환

## StringBuffer클래스와 StringBuilder 클래스

### StringBuffer 생성자

### StringBuffer의 변경

### StringBuffer의 비교

### StringBuffer클래스의 생성자와 메서드

### StringBuilder란? 


## Math클래스

### 올림, 버림, 반올림

### Math클래스의 메서드

## 래퍼(wrapper) 클래스

### Number클래스

### 문자열을 숫자로 변환하기

### 오토박싱 & 언박싱(autoboxing & unboxing)


* * * 
# 유용한 클래스

## java.util.Objects 클래스

## java.util.Random 클래스

## 정규식(Regular Expression) - java.util.regex패키지

## java.util.Scanner 클래스

## java.util.StringTokenizer 클래스

## java.math.BigInteger 클래스

## java.math.BigDecimal 클래스
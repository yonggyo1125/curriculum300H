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

### 예외 처리를 미루는 throws 사용하기
- 예외를 해당 메서드에서 처리하지 않고 미룬 후 메서드를 호출하여 사용하는 부분에서 예외를 처리하는 방법

#### day11/exception/ThrowsException.java
```
package day11.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsException {
	public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName); // FileNotFoundException 발생 가능
		Class c = Class.forName(className); // ClassNotFoundException 발생 가능
		return c;
	}
	public static void main(String[] args) {
		ThrowsException test = new ThrowsException();
		test.loadClass("a.txt", "java.lang.String"); // 메서드 호출할 때 예외처리함
	}
}
```

#### throws를 활용하여 예외처리 미루기
- 예외를 처리하지 않고 미룬다고 선언하면, 그 메서드를 호출하여 사용하는 부분에서 예외 처리를 해야 합니다.

![예외처리4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC4.png)

![예외처리5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC5.png)

- **Add throws declaration** 
	- main() 함수 선언 부분에 throws FileNotFoundException, ClassNotFoundException을 추가하고 예외 처리를 미룬다는 뜻 입니다. 
	- main() 함수에서 미룬 예외 처리는 main()  함수를 호출하는 자바 가상 머신으로 보내집니다.즉, 예외를 처리하는 것이 아니라 대부분의 프로그램이 비정사아 종료 됩니다.
- <b>Surround with try/multi-catch</b> - 여러 예외를 한꺼번에 처리하기
	```
	public static void main(String[] args) {
		ThrowsException test = new ThrowsException();
		try {
			test.loadClass("a.txt", "java.lang.String");
		} catch (FileNotFoundException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	```

- **Surround with try/catch** - 예외를 상황마다 처리하기
	```
	...
	public static void main(String[] args) {
		ThrowsException test = new ThrowsException();
		try {
			test.loadClass("a.txt", "java.lang.String");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	...
	```

- 예외가 발생한 메서드에서 그 예외를 바로 처리할 것인지, 아니면 미루어서 그 메서드를 호출하여 사용하는 부분에서 처리할 것인지는 만들고자 하는 프로그램 상황에 따라 다를 수 있습니다.
- 만약 어떤 메서드가 다른 여러 코드에서 호출되어 사용된다면 메서드를 호출하는 부분에서 예외처리를 하도록 미루는 것이 합리적입니다.

### 다중 예외 처리
- 어떤 예외가 발생할지 미리 알수 없지만 모든 예외 상황을 처리하고자 한다면 맨 마지막 부분에 Exception 클래스를 활용하여 catch 블록을 추가합니다.

#### day11/exception/ThrowsException.java
```
package day11.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsException {
	public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName); // FileNotFoundException 발생 가능
		Class c = Class.forName(className); // ClassNotFoundException 발생 가능
		return c;
	}
	public static void main(String[] args) {
		ThrowsException test = new ThrowsException();
		try {
			test.loadClass("a.txt", "java.lang.String");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) { // Exception  클래스로 그 외 예외 상황 처리
			e.printStackTrace();
		}
	}
}
```
- Exception 클래스는 모든 예외 클래스의 최상위 클래스 입니다. 따라서 다른 catch 블록에서 선언한 것 이외의 예외가 발생하더라도 Exception 클래스로 자동 형 변환 됩니다(다형성).
- 가장 처음 Exception이 catch  구간에 있다면 모든 예외가 이 구간으로 유입이 되어 적절한 처리가 되지 않습니다. 따라서 기본 예외 처리를 하는 **Exception 클래스 블록은 여러 예외  처리 블록의 가장 아래 놓여야 합니다.**

## 사용자 정의 예외
- 사용자 정의 예외 클래스를 구현할 때는 기존 JDK에서 제공하는 예외 클래스 중 가장 유사한 클래스를 상속받는 것이 좋습니다.
- 유사한 예외 클래스를 잘 모르겠다면 **가장 상위 클래스인 Exception 클래스**를 상속받으시면 됩니다.

#### day11/exception/IDFormatException.java
```
package day11.exception;

public class IDFormatException extends Exception {
	// 생성자의 매개변수로 예외 상황 메시지를 받음
	public IDFormatException(String message) {
		super(message);
	}
}
```
- 위 코드는 Exception 클래스에서 상속받아 구현했습니다.
- 예외 상황 메세지를 생성자에 입력 받습니다. 
- Exception 클래스에서 메세지 생성자, 멤버 변수와 메서드를 이미 제공하고 있으므로 super(message)를 사용하여 메시지를 설정합니다.
- 나중에 getMessage() 메서드를 호출하면 메시지 내용을 볼 수 있습니다.

#### day11/exception/IDFormatTest.java
```
package day11.exception;

public class IDFormatTest {
	private String userID;
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) throws IDFormatException {
		if (userID == null) {
			// 강제로 예외 발생
			throw new IDFormatException("아이디는 null일 수 없습니다.");
		} else if (userID.length() > 0 || userID.length() > 20) {
			// 강제로 예외 발생 
			throw new IDFormatException("아이디는 8자 이상 20자 이하로 쓰세요");
		}
		this.userID = userID;
	}
	
	public static void main(String[] args) {
		IDFormatTest test = new IDFormatTest();
		
		String userID = null; // 아이디 값이 null인 경우
		try {
			test.setUserID(userID); 
		} catch (IDFormatException e) {
			System.out.println(e.getMessage());
		}
		
		userID = "1234567"; // 아이디 값이 8자 이하인 경우
		try {
			test.setUserID(userID);
		} catch (IDFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}

실행결과

아이디는 null일 수 없습니다.
아이디는 8자 이상 20자 이하로 쓰세요
```
- 여기에서 발생하는 예외는 자바에서 제공하는 예외가 아니므로 예외 클래스를 직접 생성하여 예외를 발생시켜야 합니다.
- 예외 메시지를 생성자에 넣어 예외 클래스를 생성한 후 **throw문으로 직접 예외를 발생**시킵니다.


* * * 

# java.lang 패키지
- java.lang 패키지에는 기본적으로 많이 사용하는 클래스들이 포함되어 있습니다.
- 예를 들면 String, Integer와 같은 클래스는 java.lang 패키지에 속해 있고, String 클래스의 전체 이름은 java.lang.String이고 Integer 클래스의 전체 이름은 java.lang.Integer입니다.
- 외부 패키지에 선언한 클래스를 사용할 때는 import문으로 클래스가 어느 패키지에 속해 있는지 선언해야 하나 String 클래스를 쓰면서 import java.lang.String; 문장을 쓴 적이 없습니다.
- java.lang 패키지는 컴파일 할 때 import java.lang.\*; 문장이 자동으로 추가되어 java.lang 패키지 하위 클래스를 모두 사용할 수 있으므로 직접 써 줄 필요가 없습니다.
- 즉, import문을 직접 쓰지 않아도 java.lang 패키지의 모든 하위 클래스를 참조할 수 있습니다.

## Object 클래스
- Object 클래스는 **모든 자바 클래스의 최상위 클래스** 입니다.
- 다시 말하면 모든 클래스는 Object 클래스로 부터 상속을 받습니다.
- 우리가 클래스를 만들 때 Object를 상속받는 코드를 작성한 적이 없는데, Object 클래스의 상속을 의미하는 **extends Object가 컴파일 과정에서 자동으로 쓰입니다.**

- 코드를 작성할 때
```
class Student {
	int studentID;
	String studentName;
}
```

- 컴파일러가 변환 
```
class Student extends Object {
	int studentID;
	String studentName;
}
```

#### String 클래스 JavaDoc 예시
![기본클래스1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EA%B8%B0%EB%B3%B8%ED%81%B4%EB%9E%98%EC%8A%A41.png)

- **java.lang.Object** : 최상위 클래스
- java.lang.String : JavaDoc에서 검색한 클래스

- JavaDoc을 보니 String 클래스 역시 Object 클래스를 상속받았음을 알 수 있습니다.
- 모든 클래스가 **Object 클래스를 상속**받았으므로 **Object의 메서드를 사용할 수 있고, 재정의 할 수도 있고, Object형으로 변환할 수 도 있습니다.**
- 자바로 프로그래밍을 하다 보면 클래스가 Object형으로 변환되는 경우도 있고, Object에서 원래 클래스형으로 다운 캐스팅되는 경우도 있습니다.


#### Object 클래스에 정의된 메서드

|메서드|설명|
|-----|--------|
|String toString()|객체를 문자열로 표현하여 반환합니다. 재정의하여 객체에 대한 설명이나 특정 멤버 변수 값을 반환합니다.|
|boolean equals(Object obj)|두 인스턴스가 동일한지 여부를 반환합니다. 재정의하여 논리적으로 동일한 인스턴스임을 정의할 수 있습니다.|
|int hashCode()|객체의 해시 코드 값을 반환합니다.|
|Object clone()|객체를 복제하여 동일한 멤버 변수 값을 가진 새로운 인스턴스를 생성합니다.|
|Class getClass()|객체의 Class 클래스를 반환합니다.|
|void finalize()|인스턴스가 힙 메모리에서 제거될 때 가비지 컬렉터(GC)에 의해 호출되는 메서드입니다. 네트워크 연결 해제, 열려 있는 파일 스트림 해제 등을 구현합니다.|
|void wait()|멀티스레드 프로그램에서 사용하는 메서드입니다. 쓰레드를 **기다리는 상태**(non runnable)로 만듭니다.|
|void notify()|wait() 메서드에 의해 기다리고 있는 쓰레드(non runnable 상태)를 **실행 가능한 상태**(runnable)로 가져옵니다.|

- Object 메서드 중에는 재정의 할 수 있는 메서드도 있고 그렇지 않은 메서드도 있습니다.
- Object 메서드 중에서 final 예약어로 선언된 메서드는 자바 쓰레드에서 사용하거나 클래스를 로딩하는 등 자바 가상 머신과 관련된 메서드이기 때문에 재정의할 수 없습니다.|

### toString() 메서드
- Object 클래스에서 기본으로 제공하는 toString() 메서드는 이름 처럼 객체 정보를 문자열(String)로 바꾸어 줍니다.
- Object 클래스를 상속받은 모든 클래스는 toString()을 재정의할 수 있습니다.
- String이나 Integer 등 여러 JDK 클래스에는 toString() 메서드가 이미 재정의 되어 있습니다.

#### Object 클래스의 toString() 메서드
- toString() 메서드는 인스턴스 정보를 문자열로 변환하는 메서드 입니다.
- toString() 메서드의 원형은 생성된 인스턴스 클래스의 이름과 주소 값을 보여줍니다.

#### day11/object/ToStringEx.java
```
package day11.object;

class Book {
	int bookNumber;
	String bookTitle;
	
	Book(int bookNumber, String bookTitle) {
		this.bookNumber = bookNumber;
		this.bookTitle = bookTitle;
	}
}

public class ToStringEx {
	public static void main(String[] args) {
		Book book1 = new Book(200, "개미");
		
		 // 클래스 정보(클래스 이름, 주소 값)
		System.out.println(book1);
		
		// toString())) 메서드로 인스턴스 정보(클래스 이름, 주소 값)를 보여 줌
		System.out.println(book1.toString());
	}
}

실행결과

day11.object.Book@5e91993f
day11.object.Book@5e91993f
```
- System.out.println() 출력문에 참조 변수를 넣으면 인스턴스 정보가 출력되는데, 이 때 자동으로 호출되는 메서드가 toString() 입니다.
- 여기에서 호출되는 toString()은 Book클래스의 메서드가 아닌 **Object 클래스의 메서드** 입니다.
- Object 클래스의 toString()메서드 원형은 다음과 같습니다.
```
getClass().getName() + '@' + Integer.toHexString(hashCode())
```
- **클래스 이름@해시 코드 값** 입니다. 즉, 클래스의 이름과 16진수 해시 코드 값이 출력됩니다.

#### String과 Integer클래스의 toString() 메서드

```
String str = new String("test");
System.out.println(str); // test 출력됨
Integer i1 = new Integer(100);
System.out.println(i1); // 100 출력됨
```
- 두 클래스의 출력 결과는 **클래스이름@해시코드 값**이 아니라 문자열 값 test, 정수값 100이 각각 출력됩니다.
- 그 이유는 String과 Integer 클래스는 toString() 메서드를 미리 재정의해 두었기 때문입니다.
- JDK에서 제공하는 클래스 중에는 toString() 메서드를 미리 재정의한 클래스가 많습니다.
- toString() 메서드가 재정의된 클래스는 '클래스의 이름@해시코드 값'을 출력하는 toString() 메서드의 원형이 아닌 **재정의된 메서드**가 호출되는 것입니다.

#### day11/object/ToStringEx.java
```
package day11.object;

class Book {
	int bookNumber;
	String bookTitle;
	
	Book(int bookNumber, String bookTitle) {
		this.bookNumber = bookNumber;
		this.bookTitle = bookTitle;
	}
	
	@Override
	public String toString() { // toString() 메서드 재정의
		return bookTitle + "," + bookNumber;
	}
}

public class ToStringEx {
	public static void main(String[] args) {
		Book book1 = new Book(200, "개미");
		
		 // 클래스 정보(클래스 이름, 주소 값)
		System.out.println(book1);
		
		// toString())) 메서드로 인스턴스 정보(클래스 이름, 주소 값)를 보여 줌
		System.out.println(book1.toString());
	}
}

실행결과

개미,200
개미,200
```

### equals() 메서드
- equals() 메서드의 원래 기능은 두 인스턴스의 주소 값을 비교하여 boolean 값(true/false)을 반환해 주는 것입니다.
- 주소 값이 같다면 당연히 같은 인스턴스 입니다. 그런데 서로 다은 주소 값을 가질 때도 같은 인스턴스라고 정의할 수 있는 경우가 있습니다.
- 물리적 동일성(인스턴스 메모리 주소가 같음)뿐 아니라 <b>논리적 동일성(논리적으로 두 인스턴스가 같음)을 구현할 때도 equals() 메서드를 재정의</b>하여 사용합니다.

#### Object 클래스의 equals() 메서드
- 두 인스턴스가 물리적으로 같다는 것은, 두 인스턴스의 주소 값이 같은 경우를 말합니다. 즉, 두 변수가 같은 메모리 주소를 가리키고 있다는 뜻 입니다.
```
Student studentLee = new Student(100, "이상원");
Student studentLee2 = studentLee; // 주소 복사
```
- 두 변수는 다음 그림과 같이 동일한 인스턴스를 가리킵니다. 이 때 equals()메서드를 이용해 두 변수를 비교하면 동일하다는 결과가 나옵니다.
![기본클래스2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EA%B8%B0%EB%B3%B8%ED%81%B4%EB%9E%98%EC%8A%A42.png)


```
Student studentLee = new Student(100, "이상원");
Student studentLee2 = studentLee;
Student studentSang = new Student(100, "이상원");
```
- 위 코드를 표현한 그림은 다음과 같습니다.

![기본클래스3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EA%B8%B0%EB%B3%B8%ED%81%B4%EB%9E%98%EC%8A%A43.png)

- studentLee, studentLee2가 가리키는 인스턴스와 studentSang이 가리키는 인스턴스는 서로 다른 주소를 가지고 있지만, 저장된 학생의 정보는 같습니다. 이런 경우 논리적으로는 studentLee, studentLee2와 studentSang을 같은 학생으로 처리하는 것이 맞을 것 입니다.

#### day11/object/EqualsTest.java
```
package day11.object;

class Student{
	
	int studentId;
	String studentName;
	
	public Student(int studentId, String studentName){
		this.studentId = studentId;
		this.studentName = studentName;
	}
	
	public String toString(){
		return studentId + "," + studentName;
	}
}

public class EqualsTest {

	public static void main(String[] args) {

		Student studentLee = new Student(100, "이상원");
		Student studentLee2 = studentLee; // 주소 복사 
		Student studentSang = new Student(100, "이상원");

		// 동일한 주소의 두 인스턴스 비교 
		if(studentLee == studentLee2) // == 기호로 비교 
			System.out.println("studentLee와 studentLee2의 주소는 같습니다.");
		else
			System.out.println("studentLee와 studentLee2의 주소는 다릅니다.");
		
		if(studentLee.equals(studentLee2)) // equals() 메서드로 비교 
			System.out.println("studentLee와 studentLee2는 동일합니다.");
		else
			System.out.println("studentLee와 studentLee2는 동일하지 않습니다.");
		
		
		// 동일인이지만 인스턴스의 주소가 다른 경우 
		if(studentLee == studentSang) // == 기호로 비교 
			System.out.println("studentLee와 studentSang의 주소는 같습니다.");
		else
			System.out.println("studentLee와 studentSang의 주소는 다릅니다.");
		
		if(studentLee.equals(studentSang)) // equals() 메서드로 비교ㅕ
			System.out.println("studentLee와 studentSang은 동일합니다.");
		else
			System.out.println("studentLee와 studentSang은 동일하지 않습니다.");  
	
	}
}

실행결과

studentLee와 studentLee2의 주소는 같습니다.
studentLee와 studentLee2는 동일합니다.
studentLee와 studentSang의 주소는 다릅니다.
studentLee와 studentSang은 동일하지 않습니다.
```
- Object의 equals() 메서드의 원래 기능은 두 인스턴스의 주소를 비교하는 것입니다. 따라서 같은 주소인 경우만 equals() 메서드의 결과가 true가 됩니다.
- 두 인스턴스가 있을 때 ==는 단순히 물리적으로 같은 메모리 주소인지 여부를 확인할 수 있고, Object의 equals() 메서드는 재정의를 하여 논리적으로 같은 인스턴스인지(메모리 주소가 다르더라도 같은 학생인지) 확인하도록 구현할 수 있습니다.

#### String과 Integer 클래스의 equals() 메서드
- JDK에서 제공하는 String 클래스와 Integer 클래스에는 equals() 메서드가 이미 재정의 되어 있습니다. 

#### day11/object/StringEquals.java
```
package day11.object;

public class StringEquals {
	public static void main(String[] args) {

		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);  // 두 스트링 인스턴스의 주소 값은 다름
		System.out.println(str1.equals(str2)); // String 클래스의 equals 메소드가 재정의 됨
		
		Integer i1 = new Integer(100); // Integer 보다는 Integer.valueOf 사용 -> 인스턴스 주소는 같은 값에 대해 동일 
		Integer i2 = new Integer(100);
		
		System.out.println(i1 == i2);   // 두 정수 인스턴스의 주소 값은 다름 
		System.out.println(i1.equals(i2)); // Integer 클래스의 equals 메소드가 재정의 됨
	}
}

실행결과

false
true
false
true
```
- 코드의 내용을 보면 str1과 str2는 서로 다른 인스턴스를 가리키기 때문에 str1 == str2의 결과는 false 입니다.  하지만 String 클래스의 equals() 메서드는 같은 문자열의 경우 true를, 그렇지 않은 경우 false를 반환하도록 **재정의**되어 있습니다.
- 두 문자열은 "abc"로 같은 값을 가지므로 str1.equals(str2)의 반환 값은 true입니다.
- Integer 클래스의 경우도 정수 값이 같은 경우 true를 반환하도록 equals() 메서드가 재정의되어 있습니다.

#### day11/object/EqualsTest.java - Student 클래스에서 equals() 메서드 직접 재정의 하기
```
package day11.object;

class Student{
	...
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student){
			Student std = (Student)obj;
			// 학생의 학번이 같으면 true 반환
			if( studentId == std.studentId)
				return true;
			else return false;
		}
		return false;
	}
}
...

실행결과

studentLee와 studentLee2의 주소는 같습니다.
studentLee와 studentLee2는 동일합니다.
studentLee와 studentSang의 주소는 다릅니다.
studentLee와 studentSang은 동일합니다.

```
- equals() 메서드를 재정의하였습니다. 
- equals() 메서드의 매개변수는 Object형 입니다. 비교될 객체가 Object형으로 전달되면 instanceof를 사용하여 매개변수의 원래 자료형이 Student인지 확인합니다.
- this의 학번과 매개변수로 전달된 객체의 학번이 같으면 true를 반환합니다.
- equals() 메서드를 재정의한 후 출력 결과를 보면 studentLee와 studentSang은 서로 다른 메모리 주소에 존재하는 인스턴스이므로 == 연산의 결과 값은 false를 반환하지만, 학번이 같으므로 equals()는 true를 반환합니다.


### hashCode() 메서드
- 해시(hash)는 정보를 저장하거나 검색할 때 사용하는 자료구조 입니다.
- 정보를 어디에 저장할 것인지, 어디서 가져올 것인지 해시 함수를 사용하여 구현합니다.
- 해시 함수는 객체의 특정 정보(키 값)를 매개변수 값으로 넣으면 그 객체가 저장되어야 할 위치나 저장된 해시 테이블 주소(위치)를 반환합니다.
- 따라서 객체 정보를 알면 해당 객체의 위치를 빠르게 검색할 수 있습니다.
- 해시 함수(java.util.Objects.hash(key))는 개발하는 프로그램에 따라 다르게 구현됩니다.

- 자바에서는 인스턴스를 힙 메모리에 생성하여 관리할 떄 해시 알고리즘을 사용합니다.
```
hashCode = java.util.Objects.hash(key); // 객체의 해시 코드 값(메모리 위치 값)이 반환됨
```
- 자바에서는 두 인스턴스가 같다면 hashCode() 메서드에서 반환하는 해시 코드 값이 같아야 합니다. 따라서 논리적으로 같은 두 객체도 같은 해시 코드 값을 반환하도록 hashCode() 메서드를 재정의해야 합니다. 다시 말해, equals()  메서드를 재정의 했다면 hashCode() 메서드도 재정의해야 합니다.

#### String과 Integer 클래스의 hashCode() 메서드
- String 클래스와 Integer 클래스의 equals() 메서드는 재정의되어 있습니다. 마찬가지로 hashCode() 메서드도 함께 재정의되어 있습니다.

```
package day11.object;

public class HashCodeTest {
	public static void main(String[] args) {

		String str1 = new String("abc");
		String str2 = new String("abc");
		
		// abc 문자열의 해시 코드 값 출력
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		Integer i1 = new Integer(100);
		Integer i2 = new Integer(100);
		
		// Integer(100)의 해시코드 값 출력
		System.out.println(i1.hashCode());
		System.out.println(i2.hashCode());
	}
}

실행결과

96354
96354
100
100
```
- String 클래스는 같은 문자열을 가진 경우 즉, equals() 메서드의 결과 같이 true인 경우 hashCode() 메서드는 동일한 해시 코드 값을 반환합니다.
- Integer 클래스의 hashCode() 메서드는 정수 값을 반환하도록 재정의되어 있습니다.

#### day11/object/EqualsTest.java - Student 클래스에서 hashCode()메서드 재정의
```
package day11.object;

class Student{
	...
	@Override
	public int hashCode() {
		return studentId;
	}
}

public class EqualsTest {
	public static void main(String[] args) {
		...
		System.out.println("studentLee의 hashCode :" +studentLee.hashCode());
		System.out.println("studentSang의 hashCode :" +studentSang.hashCode());
		
		System.out.println("studentLee의 실제 주소값 :"+ System.identityHashCode(studentLee));
		System.out.println("studentSang의 실제 주소값 :"+ System.identityHashCode(studentSang));
	}
}

실행결과

studentLee와 studentLee2의 주소는 같습니다.
studentLee와 studentLee2는 동일합니다.
studentLee와 studentSang의 주소는 다릅니다.
studentLee와 studentSang은 동일합니다.
studentLee의 hashCode :100
studentSang의 hashCode :100
studentLee의 실제 주소값 :1586600255
studentSang의 실제 주소값 :474675244
```

- 출력 결과를 보면 studentLee, studentSang은 학번이 같기 때문에 논리적으로 같은지 확인하는 equals() 메서드 출력 값이 true 입니다.
- 또한 같은 해시 코드 값을 반환하고 있습니다.
- hashCode() 메서드를 재정의했을 때 실제 인스턴스 주소 값은 System.indentityHashCode() 메서드를 사용하면 알 수 있습니다. studentLee와 studentSang은 실제 메모리 주소 값은 다릅니다. 즉, 논리적으로는 같지만 실제로는 다른 인스턴스 입니다.

## String 클래스
### String을 선언하는 두 가지 방법
- 자바에서는 문자열을 사용할 수 있도록 String 클래스를 제공합니다.
- String을 사용할 때 문자열을 생성자의 매개변수로 하여 생성하는 방식과 이미 생성된 문자열 상수를 가리키는 방식이 있습니다.

```
String str1 = new String("abc"); // 생성자의 매개변수로 문자열 생성
String str2 = "test"; // 문자열 상수를 가리키는 방식
```
- 내부적으로 두 가지 방식은 큰 차이가 있습니다.
- new 예약어를 사용하여 객체를 생성하는 경우는 "abc" 문자열을 위한 메모리가 할당되고 새로운 객체가 생성됩니다.
- 하지만 str2 = "test"와 같이 생성자를 이용하지 않고 바로 문자열 상수를 가리키는 경우에는 str2가 기존에 만들어져 있던 "test"라는 문자열 상수의 메모리 주소를 가리키게 됩니다.
- 따라서 String str3 = "test" 코드를 작성하면 str2와 str3는 주소 값이 같게 됩니다.

![기본클래스4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EA%B8%B0%EB%B3%B8%ED%81%B4%EB%9E%98%EC%8A%A44.png)

- test나 10, 20 등과 같이 프로그램에서 사용되는 상수값을 저장하는 공간을 <b>상수 풀(constant pool)</b> 이라고 합니다. [상수와 리터럴 참조]
(https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95#%EC%83%81%EC%88%98%EC%99%80-%EB%A6%AC%ED%84%B0%EB%9F%B4)


#### day11/string/StringTest1.java
```
package day11.string;

public class StringTest1 {
	public static void main(String[] args) {

		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);    //false
		System.out.println(str1.equals(str2)); //true
	
		String str3 = "abc";
		String str4 = "abc";
	
		System.out.println(str3 == str4);  //true
		System.out.println(str3.equals(str4)); //true
	}
}

실행결과

false
true
true
true
```
- 문자열 상수를 바로 가리키는 경우에는 주소 값이 같음을 알 수 있습니다.

### String 클래스의 final char[] 변수
- 다른 프로그래밍 언어는 문자열을 구현할 때 일반적으로 char[] 배열을 사용합니다.
- 자바는 String클래스를 제공해 char[] 배열을 직접 구현하지 않고도 편리하게 문자열을 사용할 수 있습니다.<br>(JDK12 이후 byte[] 배열로 변경됨)
```
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence,
               Constable, ConstantDesc {

	...
    @Stable
    private final byte[] value;
	...
```

- String 클래스의 구현 내용을 보면 private final char value[]라고 선언된 char형 배열이 있습니다. 
- 프로그램에서 String s = new String("abc")라고 쓰면 abc는 String 클래스의 value 변수에 저장됩니다. 그런데 이 변수는 **final로 선언**되어 있습니다.
- **final은 문자열을 변경할 수 없다**는 뜻입니다. 따라서 **한번 생성된 문자열은 변경되지 않습니다.**
- 이런 문자열의 특징을 <b>문자열은 불변(immutable)한다</b>라고 합니다.
- 프로그램에서 두 개의 문자열을 연결하면 둘 중에 하나의 문자열이 변경되는 것이 아니라 두 문자열이 연결된 새로운 문자열이 생성됩니다.

#### day11/string/StringTest2.java
```
package day11.string;

public class StringTest2 {
	public static void main(String[] args) {

		String javaStr = new String("java");
		String androidStr = new String("android");
		System.out.println(javaStr);
		System.out.println("처음 문자열 주소 값: "+ System.identityHashCode(javaStr));
		
		javaStr = javaStr.concat(androidStr); //java 와 android 문자열의 연결
		
		System.out.println(javaStr);
		System.out.println("연결된 문자열 주소 값: " +System.identityHashCode(javaStr));
	}
}

실행결과

java
처음 문자열 주소 값: 1651191114
javaandroid
연결된 문자열 주소 값: 1586600255
```

- 두 개의 문자열 "java"와 "android"를 생성했습니다. 그리고 두 문자열을 연결하는 concat() 메서드를 호출했습니다.
- javaStr 변수 출력 결과를 보면 "javaandroid"로 연결되어 잘 출력되고 있습니다. 
- 이 결과만 보면 "java" 문자열에 "android"문자열이 연결된 것 같지만, <b>문자열은 불편(immutable)하므로 javaStr 변수 값 자체가 변하는 것이 아니라 새로운 문자열이 생성</b>된 것입니다.

![기본클래스5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/11%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC%2C%20java.lang%ED%8C%A8%ED%82%A4%EC%A7%80%2C%20%EC%9C%A0%EC%9A%A9%ED%95%9C%ED%81%B4%EB%9E%98%EC%8A%A4/images/%EA%B8%B0%EB%B3%B8%ED%81%B4%EB%9E%98%EC%8A%A45.png)

### StringBuffer와 StringBuilder 클래스 활용하기
- 프로그램을 만들다 보면 문자열을 변경하거나 연결해야 할 떄가 많습니다. 
- 그런데 String 클래스는 한번 생성되면 그 내부의 문자열이 변경되지 않기 때문에 String 클래스를 사용하여 문자열을 계속 연결하거나 변경하는 프로그램을 작성하면 메모리가 많이 낭비됩니다.
- 이 문제를 해결하는 것이 바로 **StringBuffer**와 **StringBuilder** 클래스 입니다.

- StringBuffer와 StringBuilder는 <b>내부에 변경가능한 ( final이 아닌) char[](JDK12에서 byte[]로 변경)를 변수</b>로 가지고 있습니다. 
- 이 두 클래스를 사용하여 문자열을 연결하면 기존에 사용하던 char[] 배열이 확장되므로 추가 메모리를 사용하지 않습니다.
- 따라서 문자열을 연결하거나 변경할 경우 두 클래스 중 하나를 사용하면 됩니다. 
- 두 클래스의 차이는 여러 작업(쓰레드)이 동시에 문자열을 변경하려 할 때 문자열이 안전하게 변경되도록 보장해 주는가 그렇지 않은가의 차이 입니다.

- StringBuffer 클래스는 문자열이 안전하게 변경되도록 보장하지만, StringBuilder 클래스는 보장 되지 않습니다.
- 프로그램에서 따로 쓰레드를 생성하는 멀티쓰레드 프로그램이 아니라면 StringBuilder를 사용하는 것이 싱행 속도가 좀 더 빠릅니다.

#### day11/string/StringBuilderTest.java
```
package day11.string;

public class StringBuilderTest {
	public static void main(String[] args) {
		
		String javaStr = new String("Java");
		System.out.println("javaStr 문자열 주소 :" +System.identityHashCode(javaStr));//처음 생성된 메모리 주소
		
		StringBuilder buffer = new StringBuilder(javaStr); //String으로 부터 StringBuilder생성
		System.out.println("연산 전 buffer 메모리 주소:" + System.identityHashCode(buffer));//buffer 메모리 주소
		buffer.append(" and");                // 문자열 추가
		buffer.append(" android");            // 문자열 추가
		buffer.append(" programming is fun!!!"); //문자열 추가
		System.out.println("연산 후 buffer 메모리 주소:" + System.identityHashCode(buffer));//buffer 메모리 주소
		
		javaStr = buffer.toString(); //String 클래스로 반환
		System.out.println(javaStr);
		System.out.println("새로 만들어진 javaStr 문자열 주소 :" +System.identityHashCode(javaStr)); //새로 생성된 메모리 주소

	}
}

실행결과

javaStr 문자열 주소 :1651191114
연산 전 buffer 메모리 주소:1586600255
연산 후 buffer 메모리 주소:1586600255
Java and android programming is fun!!!
새로 만들어진 javaStr 문자열 주소 :474675244
```


## Wrapper 클래스

## Class 클래스



* * * 
# 유용한 클래스

## java.util.Objects 클래스

## java.util.Random 클래스

## 정규식(Regular Expression) - java.util.regex패키지

## java.util.Scanner 클래스

## java.util.StringTokenizer 클래스

## java.math.BigInteger 클래스

## java.math.BigDecimal 클래스
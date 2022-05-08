# 쓰레드

## 프로세스와 쓰레드
- 프로세스란 간단히 말해서 **실행 중인 프로그램**(program)이다. 프로그램을 실행하면 OS로 부터 실행에 필요한 자원(메모리)를 할당 받아 프로세스가 된다.
- 프로세스는 프로그램을 수행하는 데 필요한 **데이터**와 **메모리**등의 자원 그리고 **쓰레드**로 구성되어 있다.
- 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것이 **쓰레드**이다.
- 모든 프로세스에는 최소한 하나 이상의 쓰레드가 존재하며, 둘 이상의 쓰레드를 가진 프로세스를 **멀티쓰레드 프로세스**(multi-threaded process)라고 한다.
- 하나의 프로세스가 가질 수 있는 쓰레드의 개수는 제한되어 있지 않으나 쓰레드가 작업을 수행하는데 **개별적인 메모리 공간**(호출 스택)을 필요로 하기 때문에 **프로세스의 메모리 한계에 따라 생성할 수 있는 쓰레드의 수가 결정**된다.

### 멀티쓰레딩
멀티쓰레딩은 하나의 프로세스내에서 여러 쓰레드가 동시에 작업을 수행하는 것

### 멀티쓰테딩의 장점 
- CPU의 사용률을 향상시킨다.
- 자원을 보다 효율적으로 사용할 수 있다.
- 사용자에 대한 응답성이 향상된다.
- 작업이 분리되어 코드가 간결해진다.

### 멀티쓰레딩의 단점 
- 여러 쓰레드가 같은 프로세스 내에서 자원을 공유하면서 작업을 하기 때문에 발생할 수 있는 동기화(synchronization), 교착상태(deadlock)와 같은 문제를 고려해서 신중하게 프로그래밍해야 한다.


## 쓰레드의 구현과 실행
- Thread클래스를 상속받는 방법과 Runnable인터페이스를 구현하는 방법이 있다.
- Thread클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에 Runnable 인터페이스를 구현하는 방법이 일반적이다.

```
1. Thread 클래스를 상속
class MyThread extends Thread {
	// Thread 클래스의 run()을 오버라이딩
	public void run() {   
		// 작업내용
	}
}

```

```
2. Runnable 인터페이스를 구현
class MyThread implements Runnable {
	// Runnable 인터페이스의 run()을 구현
	public void run() {
		// 작업내용
	}
}
```
> Runnable 인터페이스는 오로지 run만 정의되어 있는 간단한 인터페이스 이다.
```
public interface Runnable {
	public abstract void run();
}
```

#### day17/ThreadEx1.java
```
package day17;

class ThreadEx1_1 extends Thread {
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println(getName()); // 조상인 Thread의 getName()을 호출
		}
	}
}

class ThreadEx1_2 implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			// Thread.currentThread(); // 현재 실행중인 Thread를 반환한다.
			System.out.println(Thread.currentThread().getName());
		}
	}
}

public class ThreadEx1 {
	public static void main(String[] args) {
		ThreadEx1_1 t1 = new ThreadEx1_1();
		
		Runnable r = new ThreadEx1_2();
		Thread t2 = new Thread(r); //  생성자 new Thread(Runnable target)
		
		t1.start();
		t2.start();
	}
}


실행결과
Thread-1
Thread-1
Thread-1
Thread-1
Thread-1
Thread-0
Thread-0
Thread-0
Thread-0
Thread-0
```

- Thread클래스를 상속받으면, 자손 클래스에서 조상인 Thread클래스의 메서드를 직접 호출할 수 있지만, Runnable을 구현하면 Thread클래스의 static메서드인 currentThread()를 호출하여 스레드에 대한 참조를 얻어와야만 호출이 가능하다.
```
static Thread currentThread() : 현재 실행중인 쓰레드의 참조를 반환한다.
String getName() : 쓰레드의 이름을 반환한다.
```

- 쓰레드의 이름은 다음과 같은 생성자나 메서드를 통해서 지정 또는 변경할 수 있다.<br>
(쓰레드의 이름을 지정하지 않으면 'Thread-번호'의 형식으로 이름이 정해진다)
```
Thread(Runnable target, String name)
Thread(String name)
void setName(String name)
```

### 쓰레드의 실행 - start()
- 쓰레드를 생성했다고 해서 자동으로 실행되는 것은 아니다. 
- start()를 호출해야만 쓰레드가 실행된다.
- 사실은 start()가 호출되었다고 해서 바로 실행되는 것이 아니라. 일단 실행대기 상태에 있다가 자신의 차례가 되어야 실행된다. 물론 실행대기중인 쓰레드가 하나도 없으면 곧바로 실행상태가 된다.
- 한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다.(즉, 하나의 쓰레드에 대해  start()가 한번만 호출될수 있다.)

> 쓰레드의 실행순서는 OS의 스케줄러가 작성한 스케줄에 의해 결정된다. 

```
ThreadEx1_1 t1 = new ThreadEx1_1();
t1.start();
t1.start(); // IllegalThreadStateException 예외발생
```

## start()와 run()
- main메서드에서 run()을 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것일 뿐이다.

#### main메서드에서 run()을 호출했을 때의 호출스택
![main메서드에서 run()을 호출했을 때의 호출스택](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/main%EB%A9%94%EC%84%9C%EB%93%9C%EC%97%90%EC%84%9C_run()%ED%98%B8%EC%B6%9C.png)

- 반면에 start()는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택(call stack)을 생성한 다음에 run()을 호출해서, 생성된 호출 스택에 run()이 첫 번째로 올라가게 한다.
- 모든 쓰레드는 독립적인 작업을 수행하기 위해 자신만의 호출스택을 필요로 하기 때문에, 새로운 쓰레드를 생성하고 실행시킬 때마다 새로운 호출스택이 생성되고 스레드가 종료되면 작업에 사용된 호출스택은 소멸된다.

#### 새로운 쓰레드를 생성하고 start()를 호출한 후 호출스택의 변화
![새로운 쓰레드를 생성하고 start()를 호출한 후 호출스택의 변화](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/start()%ED%98%B8%EC%B6%9C%ED%9B%84_%ED%98%B8%EC%B6%9C%EC%8A%A4%ED%83%9D%EB%B3%80%ED%99%94.png)


1. main() 메서드에서 쓰레드의  start()를 호출한다.
2. start()는 새로운 쓰레드를 생성하고 쓰레드가 작업하는데 사용될 호출 스택을 생성한다.
3. 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
4. 이제는 호출 스택이 2개이므로 스케줄러가 정한 순서에 의해서 번갈아가며 실행된다.

- 쓰레드가 둘 이상일 때는 호출스택의 최상위에 있는 메서드일지라도 대기상태에 있을 수 있다.
- 스케줄러는 실행대기중인 쓰레드들의 우선순위를 고려하여 실행순서와 실행시간을 결정하고, 각 쓰레드들은 작성된 스케줄에 따라 자신의 순서가 되면 지정된 시간동안 작업을 수행한다.
- 이 때 주어진 시간동안 작업을 마치지 못한 쓰레드는 다시 자신의 차례가 돌아올 때까지 대기상태로 있게 된다.
- 작업을 마친 쓰레드, 즉 run()은 수행이 종료된 쓰레드는 호출스택이 모두 비워지면서 이 쓰레드가 사용하던 호출스택은 사라진다.
- 이는 자바프로그램을 실행하면 호출스택이 생성되고 main메서드가 처음으로 호출되고, main메서드가 종료되면 호출스택이 비워지면서 프로그램도 종료되는 것과 같다.

### main 쓰레드
- main메서드의 작업을 수행하는 것도 쓰레드이며, 이를 main쓰레드라고 한다.
- main메서드가 수행를 마쳤다하더라도 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램이 종료되지 않는다.
- ***실행중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.**


#### day17/ThreadEx2.java
```
package day17;

class ThreadEx2_1 extends Thread {
	public void run() {
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class ThreadEx2 {
	public static void main(String[] args) throws Exception {
		ThreadEx2_1 t1 = new ThreadEx2_1();
		t1.start();
	}
}

실행결과
java.lang.Exception
	at day17.ThreadEx2_1.throwException(ThreadEx2.java:10)
	at day17.ThreadEx2_1.run(ThreadEx2.java:5)
```

- 호출스택의 첫 번쨰 메서드가 main 메서드가 아니라 run메서드이다.
- 한 쓰레드가 예외가 발생해서 종료되어도 다른 쓰레드의 실행에는 영향을 미치지 않는다.
- main쓰레드 호출스택이 없는 이유는 main쓰레드가 종료되었기 때문이다.

![start 실행](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/start_%EC%8B%A4%ED%96%89.png)



#### day17/ThreadEx3.java
```
package day17;


class ThreadEx3_1 extends Thread {
	public void run() {
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class ThreadEx3 {
	public static void main(String[] args) throws Exception {
		ThreadEx3_1 t1 = new ThreadEx3_1();
		t1.run();
	}
}

실행결과
java.lang.Exception
	at day17.ThreadEx3_1.throwException(ThreadEx3.java:11)
	at day17.ThreadEx3_1.run(ThreadEx3.java:6)
	at day17.ThreadEx3.main(ThreadEx3.java:21)
	
```
- 단순히 ThreadEx3_1 클래스의 run()이 호출되었고 쓰레드가 새로 생성되지 않았다.
- main쓰레드의 호출 스택에서 run()이 호출되었고  main메서드가 포함되어 있다.
![run 실행](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/run_%EC%8B%A4%ED%96%89.png)


## 싱글쓰레드와 멀티쓰레드
- 하나의 쓰레드로 두 작업을 처리하는 경우 한 작업을 마친 후에 다른 작업을 시작한다.
- 두 개의 쓰레드로 작업하는 경우에는 짧은 시간동안 2개의 쓰레드가 번갈아 가면서 작업을 수행해서 동시에 두 작업이 처리되는 것과 같이 느끼게 한다.
- 하나의 쓰레드로 두개의 작업을 수행한 시간과 두개의 쓰레드로 두 개의 작업을 수행한 시간은 거의 같다.
- 오히려 두 개의 쓰레드로 작업한 시안이 싱글쓰레드로 작업한 시간보다 더 걸리게 되는데, 쓰레드간의 작업 전환(context switching)에 시간이 걸리기 때문이다.
- 작업 전환을 할 때는 현재 진행 중인 작업의 상태, 다음에 실행할 위치 등의 정보를 저장하고 읽어 오는 시간이 소요된다.
- 단순히 CPU만 사용하는 계산작업이라면 오히려 멀티쓰레드보다 싱글쓰레드로 프로그래밍하는 것이 더 효율적이다.

![싱글쓰레드 프로세스와 멀티쓰레드 프로세스의 비교](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/%EC%8B%B1%EA%B8%80%EC%93%B0%EB%A0%88%EB%93%9C_%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EC%99%80_%EB%A9%80%ED%8B%B0%EC%93%B0%EB%A0%88%EB%93%9C_%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4_%EB%B9%84%EA%B5%90.png)

>프로세스 또는 쓰레드간의 작업 전환을 컨텍스트 스위칭(context switching)이라고 한다.


#### day17/ThreadEx4.java
```
package day17;

public class ThreadEx4 {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < 300; i++) {
			System.out.printf("%s", new String("-"));
		}
		
		System.out.print("소요시간1:" + (System.currentTimeMillis() - startTime));
		
		for(int i = 0; i < 300; i++) {
			System.out.printf("%s", new String("-"));
		}
		
		System.out.print("소요시간2:" + (System.currentTimeMillis() - startTime));
	}
}

실행결과
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
소요시간1:62-----------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
---------------소요시간2:78
```
#### day17/ThreadEx5.java
```
package day17;

class ThreadEx5_1 extends Thread {
	public void run() {
		for(int i = 0; i < 300; i++) {
			System.out.printf("%s", new String("-"));
		}
		
		System.out.print("소요시간2:" + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}

public class ThreadEx5 {
	static long startTime = 0;
	
	public static void main(String[] args) {
		ThreadEx5_1 th1 = new ThreadEx5_1();
		th1.start();
		startTime = System.currentTimeMillis();
		
		for(int i = 0; i < 300; i++) {
			System.out.printf("%s", new String("-"));
		}
		
		System.out.print("소요시간1:" + (System.currentTimeMillis() - startTime));
	}
}

실행시간
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
---------------------------------------------------
-------------------소요시간1:74-----------------
---소요시간2:76
```

#### day17/ThreadEx6.java
```
package day17;

import javax.swing.JOptionPane;

public class ThreadEx6 {
	public static void main(String[] args) throws Exception {
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력하신 값은 " + input + "입니다.");
		
		for(int i = 10; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초간 시간을 지연한다.
			} catch (InterruptedException e) {}
		}
	}
}

실행결과
입력하신 값은 abcd입니다.
10
9
8
7
6
5
4
3
2
1
```

#### day17/ThreadEx7.java
```
package day17;

import javax.swing.JOptionPane;

class ThreadEx7_1 extends Thread {
	public void run() {
		for(int i = 10; i > 0; i--) {
			System.out.println(i);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

public class ThreadEx7 {
	public static void main(String[] args) throws Exception {
		ThreadEx7_1 th1 = new ThreadEx7_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력하신 값은 " + input + "입니다.");
	}
}

실행결과
10
9
8
입력하신 값은 abcd입니다.
7
6
5
4
3
2
1
```

## 쓰레드의 우선순위
- 쓰레드는 우선순위(priority)라는 속성을 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라진다.
- 쓰레드가 수행하는 작업의 중요도에 따라 쓰레드의 우선순위를 서로 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있다.

### 쓰레드 우선순위 지정하기
```
void setPriority(int newPriority)   // 쓰레드의 우선순위를 지정한 값으로 변경한다. 
int getPriority()   // 쓰레드와 우선순위를 반환한다.

public static final int MAX_PRIORITY = 10; // 최대 우선순위
public static final int MIN_PRIORITY = 1;  // 최소 우선순위
public static final int NORM_PRIORITY = 5;  // 보통 우선순위
```
- 쓰레드가 가질 수 있는 우선순위 범위는 1~10이며 숫자가 높을수록 우선순위가 높다
- 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다.
- main메서드를 수행하는 쓰레드는 우선순위가 5이므로 main메서드내에서 생성하는 쓰레드의 우선순위는 자동적으로 5가 된다.
- 쓰레드의 우선순위는 쓰레드를 실행하기 전에 변경할 수 있다.

#### day17/ThreadEx8.java
```
package day17;

class ThreadEx8_1 extends Thread {
	public void run() {
		for(int i = 0; i < 300; i++) {
			System.out.print("-");
			for(int x=0; x < 10000000; x++);
		}
	}
}

class ThreadEx8_2 extends Thread {
	public void run() {
		for(int i = 0; i < 300; i++) {
			System.out.print("|");
			for(int x=0; x < 10000000; x++);
		}
	}
}

public class ThreadEx8 {
	public static void main(String[] args) {
		ThreadEx8_1 th1 = new ThreadEx8_1();
		ThreadEx8_2 th2 = new ThreadEx8_2();
		
		th2.setPriority(7);
		
		System.out.println("Priority of th1(-) : " + th1.getPriority());
		System.out.println("Priority of th2(|) : " + th2.getPriority());
		th1.start();
		th2.start();
	}
}

실행결과
Priority of th1(-) : 5
Priority of th2(|) : 7
-|-|-------------------------------------------------
------------------------||||||||||||||||||||||||||||||||||||||||||
|||||||||----------------------------------------------
------------||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
||||||||||||||||||||||||||||||||||||||||||||||----||||||||||||||||||||||||||
||||||||||||||||||||||||||||||||||||||----------------||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||-----------
----------------------------------------------------
----------------------------------------------------
--------------------------------
```
- 우선순위가 높은 th2의 실행시간이 th1에 비해 상당히 늘어난 것을 알 수 있다.


## 쓰레드 그룹(thread group)
- 쓰레드 그룹은 서로 관련된 쓰레드를 그룹으로 다루기 위한 것으로, 폴더를 생성해서 관련된 파일을 함께 넣어서 관리하는 것처럼 쓰레드 그룹을 생성해서 쓰레드를 그룹으로 묶어서 관리할 수 있다.
- 폴더 안에 폴더를 생성할 수 있듯이 쓰레드 그룹에 다른 쓰레드 그룹을 포함시킬 수 있다.
- 쓰레드 그룹은 보안상의 이유로 도입된 개념으로 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹은 변경할 수 있지만 다른 쓰레드 그룹의 쓰레드는 변경할 수 없다.
- ThreadGroup을 사용해서 생성할 수 있다.

### ThreadGroup의 생성자와 메서드

|생성자 / 메서드|설명|
|-----|--------|
|ThreadGroup(String name)|지정된 이름의 새로운 쓰레드 그룹을 생성|
|ThreadGroup(ThreadGroup parent, String name)|지정된 쓰레드 그룹에 포함되는 새로운 쓰레드 그룹을 생성|
|int activeCount()|쓰레드 그룹에 포함된 활성상태에 있는 쓰레드의 수를 반환|
|int activeGroupCount()|쓰레드 그룹에 포함된 활성상태에 있는 쓰레드 그룹의 수를 반환|
|void checkAccess()|현재 실행중인 쓰레드가 쓰레드 그룹을 변경할 권한이 있는지 체크, 만일 궎란이 없다면 SecurityException을 발생시킨다.|
|void destroy()|쓰레드 그룹과 하위 쓰레드 그룹까지 모두 삭제한다. 단 쓰레드 그룹이나 하위 쓰레드 그룹은 비어있어야 한다.|
|int enumerate(Thread[] list)<br>int enumerate(Thread[] list, boolean recurse)<br>int enumerate(ThreadGroup[] list)<br>int enumerate(ThreadGroup[] list, boolean recurse)|쓰레드 그룹에 속한 쓰레드 또는 하위 쓰레드 그룹의 목록을 지정된 배열에 담고 그 개수를 반환.<br>두 번째 매개변수인 recurse의 값을 true로 하면 쓰레드 그룹에 속한 하위 쓰레드 그룹에 쓰레드 또는 쓰레드 그룹까지 배열에 담는다.|
|int getMaxPriority()|쓰레드 그룹의 최대우선순위를 반환|
|String getName()|쓰레드 그룹의 이름을 반환|
|ThreadGroup getParent()|쓰레드 그룹의 상위 쓰레드그룹을 반환|
|void interrupt()|쓰레드 그룹에 속한 모든 쓰레드를  interrupt|
|boolean isDaemon()|쓰레드 그룹이 데몬 쓰레드그룹인지 확인|
|boolean isDestoryed()|쓰레드 그룹이 삭제되었는지 확인|
|void list()|쓰레드 그룹에 속한 쓰레드와 하위 쓰레드그룹에 대한 정보를 출력|
|boolean parentOf(ThreadGroup g)|지정된 쓰레드 그룹은 상위 쓰레드그룹인지 확인|
|void setDaemon(boolean daemon)|쓰레드 그룹을 데몬 쓰레드 그룹으로 설정/해제|
|void setMaxPriority(int pri)|쓰레드 그룹의 최대우선순위를 설정|

```
Thread(ThreadGroup group, String name)
Thread(ThreadGroup group, Runnable target)
Thread(ThreadGroup group, Runnable target, String name)
Thread(ThreadGroup group, Runnable target, String name, long stackSize)
```

- 모든 쓰레드는 반드시 쓰레드 그룹에 포함되어 있어야 하기 때문에 쓰레드 그룹을 지정하하는 생성자를 사용하지 않은 쓰레드는 기본적으로 자신을 생성한 쓰레드와 같은 그룹에 속하게 된다.
- 자바 애플리케이션이 실행이 되면, JVM은 main과 system이라는 쓰레드 그룹을 만들고 JVM운영에 필요한 쓰레드들을 생성해서 이 쓰레드 그룹을 포함시킨다. 
- 예를 들면 main메서드를 수행하는 main이라는 이름의 쓰레드는 main쓰레드 그룹에 속하고, 가비지 컬렉션을 수향하는 Finalizer쓰레드는 system쓰레드 그룹에 속한다.
- 우리가 생성하는 모든 쓰레드 그룹은 main쓰레드 그룹의 하위 쓰레드 그룹이 되며, 쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 자동적으로 main쓰레드 그룹에 속하게 된다.

- 그 외에 Thread의 쓰레드 그룹과 관련된 메서드
```
ThreadGroup getThreadGroup()  // 쓰레드 자신이 속한 쓰레드 그룹을 반환한다.
void uncaughtException(Thread t, Throwable e)  // 쓰레드 그룹의 쓰레드가 처리되지 않은 예외에 의해 실행이 종료되었을 때, JVM에 의해 이 메서드가 자동적으로 호출된다.
```

#### day17/ThreadEx9.java
```
package day17;

public class ThreadEx9 {
	public static void main(String[] args) {
		ThreadGroup main = Thread.currentThread().getThreadGroup();
		ThreadGroup grp1 = new ThreadGroup("Group1");
		ThreadGroup grp2 = new ThreadGroup("Group2");
		
		// ThreadGroup(ThreadGroup parent, String name)
		ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");
		
		grp1.setMaxPriority(3); // 쓰레드 그룹 grp1의 최대 우선순위를 3으로 변경
		
		Runnable r = new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000); // 쓰레드를 1초간 멈추게 한다.
				} catch(InterruptedException e) {}
			}
		};
		
		// Thread(ThreadGroup tg, Runnable r, String name)
		new Thread(grp1, r, "th1").start();
		new Thread(subGrp1, r, "th2").start();
		new Thread(grp2, r, "th3").start();
		
		System.out.println(">>List of ThreadGroup : " + main.getName() + ", Active ThreadGroup: " + main.activeGroupCount() + ",  Active Thread: " + main.activeCount());
		main.list();
	}	
}


실행결과
>>List of ThreadGroup : main, Active ThreadGroup: 3,  Active Thread: 4
java.lang.ThreadGroup[name=main,maxpri=10]
    Thread[main,5,main]
    java.lang.ThreadGroup[name=Group1,maxpri=3]
        Thread[th1,3,Group1]
        java.lang.ThreadGroup[name=SubGroup1,maxpri=3]
            Thread[th2,3,SubGroup1]
    java.lang.ThreadGroup[name=Group2,maxpri=10]
        Thread[th3,5,Group2]

```
- 새로 생ㄹ성한 모든 쓰레드 그룹은 main쓰레드 그룹의 하위 쓰레드 그룹으로 포함되어 있다.
- setMaxPriority()는 쓰레드가 쓰레드 그룹에 추가되이 이전에 호출되어야 한다.
- 쓰레드 그룹 grp1의 최대우선순위를 3으로 했기 때문에, 후에 여기에 속하게 된 쓰레드 그룹과 쓰레드가 영향을 받았다.


## 데몬 쓰레드(daemon thread)
- 데몬 쓰레드는 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드이다.
- 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동 종료되는데, 데몬 쓰레드는 일반 쓰레드의 보조 역할을 수행하므로 일반 쓰레드가 모두 종료되고 나면 데몬 쓰레드의 존재의 의미가 없어지게 된다.
- 데몬 쓰레드의 예 : 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등
- 데몬 쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다.
- 데몬 쓰레드는 일반 쓰레드의 작성방법과 실행방법이 같다. 다만 쓰레드를 생성한 다음 실행하기 전에 setDaemon(true)를 호출하기만 하면 된다.
- 데몬 쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드가 된다.

```
boolean isDaemon()  // 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환한다.
void setDaemon(boolean on) // 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. 매개변수 on의 값을 true로 지정하면 데몬 쓰레드가 된다.
```

#### day17/ThreadEx10.java
```
package day17;

public class ThreadEx10 implements Runnable {
	static boolean autoSave = false;
	
	public static void main(String[] args) {
		Thread t = new Thread(new ThreadEx10());
		t.setDaemon(true); // 이 부분이 없으면 종료되지 않는다.
		t.start();
		
		for(int i=1; i <=10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println(i);
			if (i == 5)
				autoSave = true;
		}
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(3 * 1000); // 3초마다
			} catch (InterruptedException e) {}
			
			// autoSave의 값이 true이면 autoSave()를 호출한다.
			if (autoSave) {
				autoSave();
			}
		}
	}
	
	public void autoSave() {
		System.out.println("작업파일이 자동 저장되었습니다.");
	}
}

실행결과
1
2
3
4
5
작업파일이 자동 저장되었습니다.
6
7
8
작업파일이 자동 저장되었습니다.
9
10
```


## 쓰레드의 실행제어


### sleep(long millis) - 일정시간 동안 쓰레드를 멈추게 한다.


### interrupt()와 interrupted() - 쓰레드의 작업을 취소한다.

### suspend(), resume(), stop()

### yield() - 다른 쓰레드에게 양보한다.

### join() - 다른 쓰레드의 작업을 기다린다.


## 쓰레드의 동기화

### synchronized를 이용한 동기화

### volatile

####  volatile로 long과 double을 원자화
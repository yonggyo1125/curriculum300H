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

![싱글쓰레드 프로세스와 멀티쓰레드 프로세스의 비교](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/17%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%93%B0%EB%A0%88%EB%93%9C/images/%EC%8B%B1%EA%B8%80%EC%93%B0%EB%A0%88%EB%93%9C_%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EC%99%80_%EB%A9%80%ED%8B%B0%EC%93%B0%EB%A0%88%EB%93%9C_%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4_%EB%B9%84%EA%B5%90.png)


>프로세스 또는 쓰레드간의 작업 전환을 컨텍스트 스위칭(context switching)이라고 한다.



## 쓰레드의 우선순위

## 쓰레드 그룹(thread group)

## 데몬 쓰레드(daemon thread)

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
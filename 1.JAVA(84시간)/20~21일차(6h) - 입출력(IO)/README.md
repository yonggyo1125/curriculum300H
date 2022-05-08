# 입출력(I/O)

## 자바에서의 입출력

### 입출력이란?
- I/O란 Input과 Output의 약자로 입력과 출력, 간단히 줄여서 입출력이라고 한다. 
- 입출력은 컴퓨터 내부 또는 외부와 프로그램간의 데이터를 주고받는 것을 말한다.
- 키보드로부터 데이터를 입력받는다든가 System.out.println()을 이용해서 화면에 출력한다던가 하는 것이 가장 기본적인 입출력의 예이다.

### 스트림(stream)
자바에서 입출력을 입출력을 수행하려면, 즉 어느 한쪽에서 다른 쪽으로 데이터를 전달하려면, 두 대상을 연결하고 데이터를 전송할 수 있는 무언가가 필요한데, 이것을 스트림(stream)이라고 정의한다.
>스트림은 '19일차 - 스트림'과 같은 용어를 쓰지만 다른 개념이다.

**스트림이란 데이터를 운반하는데 사용되는 연결통로이다.**

- 스트림은 단방향 통신만 가능하기 때문에 하나의 스트림으로 입력과 출력을 동시에 처리할 수 없다.
- 입력과 출력을 동시에 수행하려면 입력을 위한 입력스트림(input stream)과 출력을 위한 출력스트림(output stream), 모두 2개의 스트림이 필요하다.

#### Java애플리케이션과 파일간의 입출력
![Java애플리케이션과 파일간의 입출력](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/20~21%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9E%85%EC%B6%9C%EB%A0%A5(IO)/images/Java%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98%EA%B3%BC_%ED%8C%8C%EC%9D%BC%EA%B0%84%EC%9D%98_%EC%9E%85%EC%B6%9C%EB%A0%A5.png)


### 바이트기반 스트림 - InputStream, OutputStream

바이트 단위로 데이터를 전송하며 입출력 대상에 따라 다음과 같은 입출력 스트림이 있다.

#### 입력스트림과 출력스트림 종류

|입력스트림|출력스트림|입출력 대상의 종류|
|-----|----|-----|
|FileInputStream|FileOutputStream|파일|
|ByteArrayInputStream|ByteArrayOutputStream|메모리(byte배열)|
|PipedInputStream|PipedOutputStream|프로세스(프로세스간의 통신)|
|AudioInputStream|AudioOutputStream|오디오장치|

- 모두 InputStream 또는 OutputStream의 자손이며, 각각 읽고 쓰는데 필요한 추상메서드를 자신에 맞게 구현해 놓았다.
- 자바에서는 **java.io패키지**를 통해서 많은 종류의 입출력 관련 클래스들을 제공하고 있으며, 입출력을 처리할 수 있는 표준화된 방법을 제공함으로써 입출력의 대상이 달라져도 동일한 방법으로 입출력이 가능하기 때문에 프로그래밍을 하기에 편리하다.

#### InputStream과 OutputStream에 정의된 읽기와 쓰기를 수행하는 메서드
|InputStream|OutputStream|
|----|----|
|abstract int read()|abstract void write(int b)|
|int read(byte[] b)|void write(byte[] b)|
|int read(byte[] b, int off, int len)|void write(byte[] b, int off, int len)|

>read()의 반환타입이 byte가 아니라 int인 이유는 read()의 반환값의 범위가 0~255와 -1이기 때문이다.

### 보조스트림
- 보조스트림은 실제 데이터를 주고 받는 스트림이 아니기 때문에 데이터를 입출력할 수 있는 기능은 없지만, 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있다.
- 따라서 보조스트림만으로는 입출력을 처리할 수 없고, 스트림을 먼저 생성한 다음에 이를 이용해서 보조스트림을 생성해야 한다.

```
// 먼저 기반 스트림을 생성한다. 
FileInputStream fis = new FileInputStream("test.txt");

// 기반 스트림을 이용해서 보조스트림을 생성한다.
BufferedInputStream bis = new BufferedInputStream(fis);

bis.read(); // 보조스트림인 BufferedInputStream으로 부터 데이터를 읽는다.
```

- 실제 입력기능은 BufferedInputStream과 연결된 FileInputStream이 수향하고, 보조스트림인 BufferedInputStream은 버퍼만 제공한다.
- 버퍼를 사용한 입출력과 사용하지 않은 입출력간의 성능차이는 상당하므로 대부분의 경우 버퍼를 이용한 보조스트림을 사용한다.

#### 보조스트림의 종류

|입력|출력|설명|
|----|----|----------|
|FilterInputStream|FilterOutputStream|필터를 이용한 입출력 처리|
|BufferedInputStream|BufferedOutputStream|버퍼를 이용한 입출력 성능향상|
|DataInputStream|DataOutputStream|int, float와 같은 기본형 단위(primitive type)로 데이터를 처리하는 기능|
|SequenceInputStream|없음|두 개의 스트림을 하나로 연결|
|LineNumberInputStream|없음|읽어 온 데이터의 라인 번호를 카운트(JDK1.1부터 LineNumberReader로 대체)|
|ObjectInputStream|ObjectOutputStream|데이터를 객체단위로 읽고 쓰는데 사용<br>주로 파일을 이용하여 객체 직렬화와 관련있음|
|없음|PrintStream|버퍼를 이용하여 추가적인 print관련 기능(print, printf, println메서드) 제공|
|PushbackInputStream|없음|버퍼를 이용해서 읽어 온 데이터를 다시 되돌리는 기능|


### 문자기반 스트림 - Reader, Writer


## 바이트기반 스트림

### InputStream과 OutputStream

### ByteArrayInputStream과 ByteArrayOutputStream

### FileInputStream과 FileOutputStream


## 바이트기반의 보조스트림

### FilterInputStream과 FilterOutputStream

### BufferedInputStream과 BufferedOutputStream

### DataInputStream과 DataOutputStream

### SequenceInputStream

### PrintStream


## 문자기반 스트림

### Reader와 Writer 

### FileReader와 FileWriter

### PipedReader와 PipedWriter

### StringReader와 StringWriter


## 문자기반의 보조스트림

### BufferedReader와 BufferedWriter

### InputStreamReader와 OutputStreamWriter

## 표준입출력과 File

### 표준입출력 - System.in, System.out, System.err

### 표준입출력의 대상변경 - setOut(), setErr(), setIn()

### RandomAccessFile

### File


## 직렬화(Serialization)

### 직렬화란?

### ObjectInputStream, ObjectOutputStream

### 직렬화가 가능한 클래스 만들기 - Serializable, transient

### 직렬화 가능한 클래스의 버전관리





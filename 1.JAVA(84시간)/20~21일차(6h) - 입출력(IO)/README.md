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

### 보조스트림

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





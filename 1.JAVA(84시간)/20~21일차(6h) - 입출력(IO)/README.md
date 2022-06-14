# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1zCi3G6NNdGvqsE-c12CCa5dPpgHEOXPN?usp=sharing)

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

- 모두 InputStream 또는 OutputStream의 하위 클래스이며, 각각 읽고 쓰는데 필요한 추상메서드를 자신에 맞게 구현해 놓았다.
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
- 바이트 기반은 입출력의 단위가 1byte라는 뜻이며,  2byte인 문자를 처리하는데 어려움이 있다. 이 점을 보완하기 위해 문자기반의 스트림이 제공된다.
- 문자데이터를 입출력할 때는 바이트기반 스트림대신 문자기반 스트림을 사용한다.

- inputStream -> **Reader**
- OutputStream -> **Writer**

#### 바이트기반 스트림과 문자기반 스트림의 비교

|바이트기반 스트림|문자기반 스트림|
|-----|-----|
|FileInputStream<br>FileOutputStream|FileReader<br>FileWriter|
|ByteArrayInputStream<br>ByteArrayOutputStream|CharArrayReader<br>CharArrayWriter|
|PipedInputStream<br>PipedOutputStream|PipedReader<br>PipedWriter|
|StringBufferInputStream(deprecated)<br>StringBufferOutputStream(deprecated)|StringReader<br>StringWriter|

> StringBufferInputStream, StringBufferOutputStream은 StringReader, StringWriter로 대체되어 더 이상 사용되지 않는다.

- 문자기반 스트림의 이름은 바이트기반 스트림의 이름에서  InputStream을 Reader로 OutputStream은 Writer로만 바꾸면 된다. 
- 단, ByteArrayInputStream에 대응하는 문자기반 스트림은 char 배열을 사용하는 CharArrayReader이다.
- 문자기반 스트림에서는 byte배열 대신 char배열을 사용한다.

#### 바이트 기반 스트림과 문자기반 스트림의 읽고 쓰는 메서드

|InputStream|Reader|
|-----|-----|
|abstract int read()<br>int read(byte[] b)<br>int read(byte[] b, int off, int len|int read()<br>int read(char[] cbuf)<br>abstract int read(char[] cbuf, int off, int len)|

|OutputStream|Writer|
|-----|-----|
|abstract void write(int b)<br>void write(byte[] b)<br>void write(byte[] b, int off, int len|void write(int c)<br>void write(char[] cbuf)<br>abstract void write(char[] cbuf, int off, int len)<br>void write(String str)<br>void write(String str, int off, int len)|


#### 바이트기반 보조스트림과 문자기반 보조스트림

|바이트기반 보조스트림|문자기반 보조스트림|
|-----|-----|
|BufferedInputStream<br>BufferdOutputStream|BufferedReader<br>BufferedWriter|
|FilterInputStream<br>FilterOutputStream|FilterReader<br>FilterWriter|
|LineNumberInputStream(deprecated)|LineNumberReader|
|PrintStream|PrintWriter|
|PushbackInputStream|PushbackReader|

## 바이트기반 스트림

### InputStream과 OutputStream
InputStream과 OutputStream은 모든 바이트기반의 스트림의 상위 인터페이스이며 다음과 같은 메서드가 선언되어 있다.

#### InputStream 메서드 

|메서드명|설명|
|----|------|
|int available()|스트림으로 부터 읽어 올 수 있는 데이터의 크기를 반환한다.|
|void close()|스트림을 닫음으로써 사용하고 있던 자원을 반환한다.|
|void mark(int readlimit)|현재위치를 표시해 놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다. readlimit은 되돌아갈 수 있는 byte의 수이다.|
|boolean markSupported()|mark()와 resest()을 지원하는지를 알려준다.mark()와 reset()기능을 지원하는 것은 선택적이므로 mark()와 reset()을 사용하기 전에 markSupported()를 호출해서 지원여부를 확인해야 한다.|
|abstract int read()|1 byte를 읽어 온다(0~255사이의 값). 더 이상 읽어올 데이터가 없으면 -1을 반환한다. abstract메서드라서 InputStream의 하위클래스들은 자신의 상황에 알맞게 구현해야 한다.|
|int read(byte[] b)|배열 b의 크기만큼 읽어서 배열을 채우고 읽어 온 데이터의 수를 반환한다. 반환하는 값은 항상 배열의 크기보다 작거나 같다.|
|int read(byte[] b, int off, int len)|최대 len개의 byte를 읽어서, 배열 b의 지정된 위치(off)부터 저장한다. 실제로 읽어 올 수 있는 데이터가 len개보다 적을 수 있다.|
|void reset()|스트림에서의 위치를 마지막으로 mark()이 호출되었던 위치로 되돌린다.|
|long skip(long n)|스트림에서 주어진 길이(n)만큼을 건너뛴다.|


#### OutputStream 메서드

|메서드명|설명|
|----|------|
|void close()|입력소스를 닫음으로써 사용하고 있던 자원을 반환한다.|
|void flush()|스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다.|
|abstract void write(int b)|주어진 값을 출력소스에 쓴다.|
|void write(byte[] b)|주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다|
|void write(byte[] b, int off, int len)|주어진 배열 b에 저장된 내용 중에서 off번째 부터 len개 만큼을 읽어서 출력소스에 쓴다.|

- 스트림의 종류에 따라서 mark()와 reset()을 사용하여 이미 읽은 데이터를 되돌려 다시 읽을 수 있다. 이 기능을 지원하는 스트림인지 확인하는 markSupported()를 통해서 알 수 있다.
- 프로그램이 종료될 때, 사용하고 닫지 않은 스트림을 JVM이 자동으로 닫아 주기는 하지만, 스트림을 사용해서 모든 작업을 마치고 난 후에는 close()를 호출해서 반드시 닫아 주어야 한다.
- ByteArrayInputStream과 같이 메모리를 사용하는 스트림과 System.in, System.out과 같은 표준 입출력 스트림은 닫아 주지 않아도 된다.


### ByteArrayInputStream과 ByteArrayOutputStream
- ByteArrayInputStream, ByteArrayOutputStream은 메모리, 즉 바이트배열에 데이터를 입출력 하는데 사용되는 스트림이다.
- 주로 다른 곳에 입출력하기 전에 데이터를 임시로 바이트배열에 담아서 변환 등의 작업을 하는데 사용된다.

#### day20_21/IOEx1.java
```
package day20_21;

import java.io.*;
import java.util.Arrays;

public class IOEx1 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		while((data = input.read()) != -1) {
			output.write(data);
		}
		outSrc = output.toByteArray(); // 스트림의 내용을 byte배열로 반환한다.
		
		System.out.println("Input Source :" + Arrays.toString(inSrc));
		System.out.println("Output Source :" + Arrays.toString(outSrc));
	}
}

실행결과
Input Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Output Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
 
- 바이트배열은 사용하는 자원이 메모리 밖에 없으므로 가비지컬렉터에 의해 자동적으로 자원을 반환하므로  close()를 이용해서 스트림을 닫지 않아도 된다.
- read()와 write(int b)를 사용하기 때문에 한 번에 1byte만 읽고 쓰므로 작업효율이 떨어진다.


#### day20_21/IOEx2.java
```
package day20_21;

import java.io.*;
import java.util.Arrays;

public class IOEx2 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[10];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		input.read(temp, 0, temp.length); // 읽어온 데이터를 배열 temp에 담는다.
		output.write(temp, 5, 5); // temp[5]부터 5개의 데이터를 write한다.
		
		outSrc = output.toByteArray();
		
		System.out.println("Input Source :" + Arrays.toString(inSrc));
		System.out.println("temp            :" + Arrays.toString(temp));
		System.out.println("Ouput Source :" + Arrays.toString(outSrc));
	}
}

실행결과
Input Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
temp            :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Ouput Source :[5, 6, 7, 8, 9]
```
- byte 배열을 사용해서 한 번에 배열의 크기만큼 읽고 쓸 수 있다. 배열(temp)을 이용하면 한 번에 더 많은 물건을 옮길 수 있는 것과 같다.
- 배열을 이용한 입출력은 작업의 효율을 증가시키므로 가능하면 입출력 대상에 따라 알맞은 크기의 배열을 사용하는 것이 좋다.

 
#### day20_21/IOEx3.java
```
package day20_21;

import java.io.*;
import java.util.Arrays;

public class IOEx3 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input  = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		System.out.println("Input Source :" + Arrays.toString(inSrc));
		
		try {
			while(input.available() > 0) {
				input.read(temp);
				output.write(temp);
				
				outSrc = output.toByteArray();
				printArrays(temp, outSrc);
			}
		} catch (IOException e) {}
	}
	
	static void printArrays(byte[] temp, byte[] outSrc) {
		System.out.println("temp          :" + Arrays.toString(temp));
		System.out.println("Output Source :" + Arrays.toString(outSrc));
	}
}

실행결과
Input Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
temp          :[0, 1, 2, 3]
Output Source :[0, 1, 2, 3]
temp          :[4, 5, 6, 7]
Output Source :[0, 1, 2, 3, 4, 5, 6, 7]
temp          :[8, 9, 6, 7]
Output Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]
```
- read()나 write()는 IOException을 발생시킬 수 있기 때문에 try ~ catch문으로 감싸주었다.
- available()은 블락킹(blocking)없이 읽어 올 수 있는 바이트 수를 반환한다.
- 보다 나은 성능을 위해서 temp에 담긴 내용을 지우고 쓰는 것이 아니라 그냥 기존의 내용 위에 덮어 쓴다. 따라서 temp의 내용은 '[4,5,6,7]'이었는데, 8과 9를 읽고 난 후에는 [8,9,6,7]이 된다.

- 원하는 결과를 얻기 위해서는 코드를 다음과 같이 수정해야 한다. 

수정 전 
```
while(input.available() > 0) {
		input.read(temp);
		output.write(temp);
}
```

수정 후
```
while(input.available() > 0) {
	int len = input.read(temp);
	output.write(temp, 0, len);
}
```

#### day20_21/IOEx4.java
```
package day20_21;

import java.io.*;
import java.util.Arrays;

public class IOEx4 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input  = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
				
		try {
			while(input.available() > 0) {
				int len = input.read(temp); // 읽어 온 데이터의 개수를 반환한다.
				output.write(temp, 0, len); // 읽어 온 만큼만 write한다.
			}
		} catch (IOException e) {}
		
		outSrc = output.toByteArray();
				
		System.out.println("Input Source :" + Arrays.toString(inSrc));
		System.out.println("temp            :" + Arrays.toString(temp));
		System.out.println("Ouput Source :" + Arrays.toString(outSrc));
	}
}

실행결과
Input Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
temp            :[8, 9, 6, 7]
Ouput Source :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```


### FileInputStream과 FileOutputStream
- FileInputStream/FileOutputStream은 파일에 입출력을 하기 위한 스트림이다.
- 실제 프로그래밍에서 많이 사용되는 스트림 중 하나이다.

#### FileInputStream과 FileOutputStream의 생성자

|생성자|설명|
|----|------|
|FileInputStream(String name)|지정한 파일이름(name)을 가진 실제 파일과 연결된 FileInputStream을 생성한다.|
|FileInputStream(File file)|파일의 이름이 String이 아닌 File인스턴스로 지정해주어야 한다는 점을 제외하고 FileInputStream(String name)과 같다.|
|FileInputStream(FileDescriptor fdObj)|파일 디스크립터(fdObj)로 FileInputStream을 생성한다.|
|FileOutputStream(String name)|지정된 파일이름(name)을 가진 실제 파일과의 연결된 FileOutputStream을 생성한다.|
|FileOutputStream(String name, boolean append)|지정된 파일이름(name)을 가진 실제 파일과 연결된 FileOutputStream을 생성한다. 두번째 인자인 append를 true로 하면 출력 시 기존의 파일내용의 마지막에 덧붙인다.false면 기존의 파일내용을 덮어쓰게 된다.|
|FileOutputStream(File file)|파일의 이름을 String이 아닌 File인스턴스로 지정해주어야 하는 점을 제외하고 FileOutputStream(String name)과 같다.|
FileOutputStream(File file, boolean append)|파일의 이름을 String이 아닌 File인스턴스로 지정해주어야 하는 점을 제외하고 FileOutputStream(String name, boolean append)과 같다.|
|FileOutputStream(FileDescriptor fdObj)|파일 디스크립터(fdObj)로 FileOutputStream을 생성한다.|

#### day20_21/FileViewer.java
```
package day20_21;

import java.io.*;

public class FileViewer {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(args[0]);
		int data = 0;
		
		while((data = fis.read()) != -1) {
			char c = (char)data;
			System.out.print(c);
		}
		
		fis.close();
	}
}

실행결과
D:\javaEx\lecture\src>java day20_21.FileViewer D:\javaEx\lecture\src\day20_21\FileViewer.java
package day20_21;

import java.io.*;

public class FileViewer {
        public static void main(String[] args) throws IOException {
                FileInputStream fis = new FileInputStream(args[0]);
                int data = 0;

                while((data = fis.read()) != -1) {
                        char c = (char)data;
                        System.out.print(c);
                }

                fis.close();
        }
}
```
> read()가 한 번에 1byte씩 파일로부터 데이터를 읽어 들이긴 하지만, 데이터의 범위가 십진수로 0~255범위의 정수값이고, 또 읽을 수 있는 입력값이 더이상 없음을 알릴 수 있는 값(-1)도 필요하다. 그래서 다소 크긴 하지만 정수형 중에서는 연산이 가장 효율적이고 빠른 int형 값을 반환하도록 한 것이다.

#### day20_21/FileCopy.java
```
package day20_21;

import java.io.*;

public class FileCopy {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			
			int data = 0;
			while((data=fis.read()) != -1) {
				fos.write(data);;  // void write(int b)
			}
			fis.close();
			fos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

실행결과
java day20_21/FileCopy.java day20_21/FileCopy.java D:\javaEx\lecture\src\day20_21\FileCopy.bak

```
> 텍스트파일을 다루는 경우에는 FileInputStream/FileOutputStream보다 문자기반의 스트림인 FileReader/FileWriter를 사용하는 것이 더 좋다.


## 바이트기반의 보조스트림

### FilterInputStream과 FilterOutputStream
- FilterInputStream/FilterOutputStream은 InputStream/OutputStream의 하위 클래스이면서 모든 보조스트림의 상위 클래스이다.
- 보조스트림은 자체적으로 입출력을 수행할 수 없기 때문에 기반스트림을 필요로 한다.
```
protected FilterInputStream(InputStream in)
public FilterOutputStream(OutputStream out)
```

- FilterInputStream/FilterOutputStream의 모든 메서드는 단순히 기반스트림의 메서드를 그대로 호출할 뿐 FilterInputStream/FilterOutputStream자체로는 아무런 일도 하지 않는다. 
- FilterInputStream/FilterOutputStream은 상속을 통해 원하는 작업을 수행하도록 읽고 쓰는 메서드를 재정의해야 한다.

- 생성자 FilterInputStream(InputStream in)은 접근제어가가 protected이기 때문에 FilterInputStream의 인스턴스를 생성해서 사용할 수 없고 상속을 통해서 재정의 되어야 한다. 
-FilterInputStream/FilterOutputStream을 상속받아서 기반스트림에 보조기능을 추가한 보조스트림 클래스는 다음과 같다.

- **FilterInputStream의 하위클래스** : BufferedInputStream, DataInputStream, PushbackInputStream 등 
- **FilterOutputStream의 하위클래스** : BufferedOutputStream, DataOutputStream, PrintStream 등


### BufferedInputStream과 BufferedOutputStream
- BufferedInputStream/BufferedOutputStream은 스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림이다.
- 한 바이트씩 입출력하는 것 보다는 버퍼(바이트배열)를 이용해서 한 번에 여러 바이트를 입출력하는 것이 빠르기 때문에 대부분의 입출력 작업에 사용된다.

#### BufferedInputStream의 생성자

|생성자|설명|
|----|------|
|BufferedInputStream(InputStream in, int size)|주어진 InputStream인스턴스를 입력소스(input source)로 하여 지정된 크게(byte단위)의 버퍼를 갖는 BufferedInputStream인스턴스를 생성한다.|
|BufferedInputStream(InputStream in)|주어진 InputStream인스턴스를 입력소스(input source)로 하여 버퍼의 크기를 지정해주지 않으므로 기본적으로 8192byte 크기의 버퍼를 갖게 된다.|


#### BufferedOutputStream의 생성자와 메서드

|메서드/생성자|설명|
|----|------|
|BufferedOutputStream(OutputStream out, int size)|주어진 OutputStream인스턴스를 출력소스(output source)로 하여 지정된 크기(단위byte)의 버퍼를 갖는 BufferedOutputStream인스턴스를 생성한다.|
|BufferedOutputStream(OutputStream out)|주어진 OutputStream인스턴스를 출력소스(output source)로 하여 버퍼의 크기를 지정해주지 않으므로 기본적으로 8192byte크기와 버퍼를 갖게된다.|
|flush()|버퍼의 모든 내용을 출력소스에 출력한 다음, 버퍼를 비운다.|
|close()|flush()를 호출해서 버퍼의 모든 내용을 출력소스에 출력하고, BufferedOutputStream인스턴스가 사용하던 모든 자원을 반환한다.|

- 버퍼가 가득 찼을 때만 출력소스에 출력을 하기 때문에, 마지막 출력부분이 출력소스에 쓰이지 못하고 BufferedOutputStream의 버퍼에 남아있는 채로 프로그램이 종료될 수 있다는 점을 주의해애 한다.
- 그래서 프로그램에서 모든 출력작업을 마친 후 BufferedOutputStream에 close()나 flush()를 호출해서 마지막에 버퍼에 있는 모든 내용이 출력소스에 출력되도록 해야 한다.
> BufferedOuptutStream의 close()는 flush()를 호출하여 버퍼의 내용을 출력스트림에 쓰도록 한 후, BufferedOutputStream인스턴스의 참조변수에 null을 지정함으로써 사용하던 자원들이 반환되게 한다.

#### day20_21/BufferedOutputStreamEx1.java
```
package day20_21;

import java.io.*;

public class BufferedOutputStreamEx1 {
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("123.txt");
			
			// BufferedOutputStream의 버퍼크기를 5로 한다.
			BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
			
			// 파일 123.txt에 1 부터 9까지 출력한다.
			for(int i = '1'; i <= '9'; i++) {
				bos.write(i);
			}
			
			//fos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

### DataInputStream과 DataOutputStream
- DataInputStream/DataOutputStream도 각각 FilterInputStream/FilterOutputStream의 하위클래스이며 DataInputStream은 DataInput인터페이스를, DataOutputStream은 DataOutput인터페이스를 각각 구현하였기 때문에, 데이터를 읽고 쓰는데 있어서 byte단위가 아닌, 8가지 기본 자료형의 단위로 읽고 쓸 수 있다는 장점이 있다.
- DataOutputStream이 출력하는 형식은 각 기본 자료형 값을 16진수로 표현하여 저장한다. 예를 들어 int값을 출력한다면 4byte의 16진수로 출력된다.
- 각 자료형의 크기가 다르므로, 출력한 데이터를 다시 읽어 올 때는 출력했을 때의 순서를 염두에 두어야 한다.

#### DataInputStream의 생성자와 메서드

|메서드/생성자|설명|
|----|------|
|DataInputStream(InputStream in)|주어진 InputStream인스턴스를 기반스트림으로 하는 DataInputStream인스턴스를 생성한다.|
|boolean readBoolean()<br>byte readByte()<br>char readChar()<br>short readShort()<br>int readInt()<br>long readLong()<br>float readFloat()<br>double readDouble()<br>int readUnsignedByte()<br>int readUnsignedByte()<br>int readUnsignedShort()|각 타입에 맞게 값을 읽어온다.<br>더 이상 읽을 값이 없으면 EOFException을 발생시킨다.|
|void readFully(byte[] b)<br>void readFully(byte[] b, int off, int len)|입력스트림에서 지정된 배열의 크기만큼 또는 지정된 위치에서 len만큼 데이터를 읽어온다. 파일의 끝에 도달하면 EOFException이 발생하고, I/O에러가 발생하면 IOException이 발생한다.|
|String readUTF()|UTF-8형식으로 쓰여진 문자를 읽는다.<br>더 이상 읽을 값이 없으면 EOFException이 발생한다.|
|static String readUTF(DataInput in)|입력스트림(in)에서 UTF-8형식의 유니코드를 읽어온다.|
|int skipBytes(int n)|현재 읽고 있는 위치에서 지정된 숫자(n) 만크을 건너뛴다.|


#### DataOutputStream의 생성자와 메서드

|메서드/생성자|설명|
|----|------|
|DataOutputStream(OutputStream out)|주어진 OutputStream인스턴스를 기반스트림으로 하는 DataOutputStream인스턴스를 생성한다.|
|void writeBoolean(boolean b)<br>void writeByte(int b)<br>void writeChar(int c)<br>void writeChars(String s)<br>void writeShort(int s)<br>void writeInt(int i)<br>void writeLong(long l)<br>void writeFloat(float f)<br>void writeDouble(double d)|각 자료형에 알맞는 값들을 출력한다.|
|void writeUTF(String s)|UTF형식으로 문자를 출력한다.|
|void writeChars(String s)|주어진 문자열을 출력한다. writeChar(int c)메서드를 여러번 호출한 결과와 같다.|
|int size()|지금까지 DataOutputStream에 쓰여진 byte의 수를 알려 준다.|

#### day20_21/DataOutputStreamEx1.java
```
package day20_21;

import java.io.*;

public class DataOutputStreamEx1 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream("sample.dat");
			dos = new DataOutputStream(fos);
			dos.writeInt(10);
			dos.writeFloat(20.0f);
			dos.writeBoolean(true);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

#### day20_21/DataOutputStreamEx2.java
```
package day20_21;

import java.io.*;
import java.util.Arrays;

public class DataOutputStreamEx2 {
	public static void main(String[] args) {
		ByteArrayOutputStream bos = null;
		DataOutputStream dos = null;
		
		byte[] result = null;
		
		try {
			bos = new ByteArrayOutputStream();
			dos = new DataOutputStream(bos);
			dos.writeInt(10);
			dos.writeFloat(20.0f);
			dos.writeBoolean(true);
			
			result = bos.toByteArray();
			
			String[] hex = new String[result.length];
			
			for(int i = 0; i < result.length; i++) {
				if (result[i] < 0) {
					hex[i] = String.format("%02x", result[i]+256);
				} else {
					hex[i] = String.format("%02x", result[i]);
				}
			}
			
			System.out.println("10진수 :" + Arrays.toString(result));
			System.out.println("16진수 :" + Arrays.toString(hex));
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

실행결과
10진수 :[0, 0, 0, 10, 65, -96, 0, 0, 1]
16진수 :[00, 00, 00, 0a, 41, a0, 00, 00, 01]
```

#### day20_21/DataInputStreamEx1.java
```
package day20_21;

import java.io.*;

public class DataInputStreamEx1 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("sample.dat");
			DataInputStream dis = new DataInputStream(fis);
			
			System.out.println(dis.readInt());
			System.out.println(dis.readFloat());
			System.out.println(dis.readBoolean());
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


실행결과
10
20.0
true
```
>여러종류의 자료형으로 출력한 경우, 읽을 때는 반드시 쓰인 순서대로 읽어야 한다.
#### day20_21/DataOuputStreamEx3.java
```
package day20_21;

import java.io.*;

public class DataOuputStreamEx3 {
	public static void main(String[] args) {
		int[] score = {100, 90, 95, 85, 50};
		
		try {
			
			FileOutputStream fos = new FileOutputStream("score.dat");
			DataOutputStream dos = new DataOutputStream(fos);
			
			for(int i=0; i<score.length; i++) {
				dos.writeInt(score[i]);
			}
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

#### day20_21/DataInputStreamEx2.java
```
package day20_21;

import java.io.*;

public class DataInputStreamEx2 {
	public static void main(String[] args) {
		int sum = 0;
		int score = 0;
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("score.dat");
			dis = new DataInputStream(fis);
			while(true) {
				score = dis.readInt();
				System.out.println(score);
				sum += score;
			}
		} catch (EOFException e) {
			System.out.println("점수의 총합은 " + sum + "입니다.");
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (dis != null)
					dis.close();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
}

실행결과
100
90
95
85
50
점수의 총합은 420입니다.
```
- DataInputStream의 readInt()와 같이 데이터를 읽는 메서드는 더 이상 읽을 데이터가 없으면 EOFException을 발생시킨다. 그래서 다른 입력 스트림들과는 달리 무한 반복문과 EOFException을 처리하는 catch문을 이용해서 데이터를 읽는다.
- while문으로 작업을 마친 후에 스트림을 닫아줘야 하는 데, while문이 무한 반복문이기 때문에 finally 블럭에서 스트림을 닫도록 처리하였다.
- 참조변수 dis가 null일때 close()를 호출하면 NullPointException이 발생하므로 if문을 사용해서 dis가 null인지 체크한 후에 close()를 호출해야 한다. 그리고 'close()'는 IOException을 발생시킬 수 있으므로 try~catch블럭으로 감싸주어야 한다.


- JDK1.7부터는 **try-with-resources**문을 이용해서 close()를 직접 호출하지 않아도 자동호출되도록 할 수 있다(AutoCloseable 인터페이스의 close() 구현)

#### day20_21/DataInputStreamEx3.java
```
package day20_21;

import java.io.*;

public class DataInputStreamEx3 {
	public static void main(String[] args) {
		int sum = 0;
		int score = 0;
		
		try (FileInputStream fis = new FileInputStream("score.dat");
			DataInputStream dis = new DataInputStream(fis)) {
			
			while(true) {
				score = dis.readInt();
				System.out.println(score);
				sum += score;
			}
			
		} catch(EOFException e) {
			System.out.println("점수의 총합은 " + sum + "입니다.");
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}

실행결과
100
90
95
85
50
점수의 총합은 420입니다.
```

### SequenceInputStream
SequenceInputStream은 여러 개의 입력스트림을 연속적으로 연결해서 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있도록 도와준다.

#### SequenceInputStream의 생성자

|메서드/생성자|설명|
|----|------|
|SequenceInputStream(Enumeration e)|Enumeration에 저장된 순서대로 입력스트립을 하나의 스트림으로 연결한다.|
|SequenceInputStream(InputStream s1, InputStream s2)|두 개의 입력스트림을 하나로 연결한다.|

#### 사용 예1
```
Vector files = new Vector();
files.add(new FileInputStream("file.001"));
files.add(new FileInputStream("file.002"));
SequenceInputStream in = new SequenceInputStream(files.elements());
```

#### 사용 예2
```
FileInputStream file1 = new FileInputStream("file.001");
FileInputStream file2 = new FileInputStream("file.002");
SequenceInputStream in = new SequenceInputStream(file1, file2);
```

#### day20_21/SequenceInputStreamEx.java
```
package day20_21;

import java.io.*;
import java.util.*;

public class SequenceInputStreamEx {
	public static void main(String[] args) {
		byte[] arr1 = {0,1,2};
		byte[] arr2 = {3,4,5};
		byte[] arr3 = {6,7,8};
		byte[] outSrc = null;
		
		Vector v = new Vector();
		v.add(new ByteArrayInputStream(arr1));
		v.add(new ByteArrayInputStream(arr2));
		v.add(new ByteArrayInputStream(arr3));
		
		SequenceInputStream input = new SequenceInputStream(v.elements());
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data = 0;
		
		try {
			while((data = input.read()) != -1) {
				output.write(data); // void write(int b)
			}
		} catch (IOException e) {}
		
		outSrc = output.toByteArray();
		
		System.out.println("Input Source1  :" + Arrays.toString(arr1));
		System.out.println("Input Source2  :" + Arrays.toString(arr2));
		System.out.println("Input Source3  :" + Arrays.toString(arr3));
		System.out.println("Output Source  :" + Arrays.toString(outSrc));
	}
}


실행결과
Input Source1  :[0, 1, 2]
Input Source2  :[3, 4, 5]
Input Source3  :[6, 7, 8]
Output Source  :[0, 1, 2, 3, 4, 5, 6, 7, 8]
```

### PrintStream
- PrintStream은 데이터를 기반스트림에 다양한 형태로 출력할 수 있는 print, println, printf 같은 메서드를 오버로딩하여 제공한다.
- PrintStream은 데이터를 적절한 문자로 출력하는 것이기 때문에 문자기반 스트림의 역할을 수향한다. 그래서 JDK1.1에서 부터 PrintStream보다 향상된 기능의 문자기반 스트림인 PrintWriter가 추가되었으나 그 동안 매우 빈번히 사용되던 System.out이  PrintStream이다 보니 둘 다 사용할 수 밖에 없게 되었다.
- PrintStream과 PrintWriter는 거의 같은 기능을 가지고 있지만 PrintWriter가 PrintStream에 비해 다양한 언어의 문자를 처리하는데 적합하기 때문에 가능하면 PrintWriter를 사용하는 것이 좋다.
>PrintStream은 우리가 지금까지 알게 모르게 많이 사용해 왔다. System클래스의 static멤버인 out과 err, 즉 System.out, System.err이 PrintStream이다.

|메서드/생성자|설명|
|----|------|
|PrintStream(File file)<br>PrintStream(File file, String csn)<br>PrintStream(OutputStream out)<br>PrintStream(OutputStream out, boolean autoFlush)<br>PrintStream(OutputStream out, boolean autoFlush, String encoding)<br>PrintStream(String fileName)<br>PrintStream(String fileName, String csn)|지정된 출력스트림을 기반으로 하는 PrintStream인스턴스를 생성한다. autuFlush의 값을 true로 하면 println메서드가 호출되거나 개행문자가 출력될 때 자동으로 flush된다. 기본값은 false이다.|
|boolean checkError()|스트림을 flush하고 에러가 발생했는지를 알려준다.|
|void print(boolean b)<br>void print(char c)<br>void print(char[] s)<br>void print(double d)<br>void print(float f)<br>void print(int i)<br>void print(long l)<br>void println(Object obj)<br>void println(String s)<br>void println(boolean b)<br>void println(char c)<br>void println(char[] s)<br>void println(double d)<br>void println(float f)<br>void println(int i)<br>void println(long l)<br>void println(Object obj)<br>void println(String s)|인자로 주어진 값을 출력소스에 문자로 출력한다. println메서드는 출력 후 줄바꿈을 하고, print메서드는 줄을 바꾸지 않는다.|
|void println()|줄바꿈 문서(line separator)를 출력함으로써 줄을 바꾼다.|
|PrintStream printf(String format, Object... args)|정형화된(formatted) 출력을 가능하게 한다.|
|protected void setError()|작업 중에 오류가 발생했음을 알린다.(setError()를 호출한 후에 checkError()를 호출하면 true를 반환한다.)|

- print()나 println()을 이용해서 출력하는 중에 PrintStream의 기반 스트림에서 IOException이 발생하면 checkError()를 통해서 인지할 수 있다. println()이나 print()는 예외를 던지지 않고 내부에서 처리하도록 정의하였는데, 그 이유는 println()과 같은 메서드가 매우 자주 사용되는 것이기 때문이다.
- 만약 println()이 예외를 던지도록 정의되었다면 println()을 사용하는 모든 곳에 try ~ catch문을 사용해야 할 것이다.

- printf()는 JDK1.5부터 추가된 것으로 C언어와 같이 편리한 형식화된 출력을 지원하게 되었다. printf()에 사용할수 있는 옵션은 하기 표를 참고.

#### 정수의 출력에 사용할 수 있는 옵션

|format|설명|결과(int i = 65)|
|----|----------|------|
|%d|10진수|65|
|%o|8진수|101|
|%x|16진수|41|
|%c|문자|A|
|%s|문자열|65|
|%5d|5자리 숫자. 빈자리는 공백으로 채운다.|   65|
|%-5d|5자리 숫자. 빈자리는 공백으로 채운다(왼쪽 정렬)|65|
|%05d|5자리 숫자. 빈자리는 0으로 채운다.|00065|


#### 문자열의 출력에 사용할 수 있는 옵션

|format|설명|결과(String str = "ABC")|
|----|----------|------|
|%s|문자열(string)|ABC|
|%5s|5자리 문자열. 빈자리는 공백으로 채운다.|  ABC|
|%-5s|5자리 문자열. 빈자리는 공백으로 채운다.(왼쪽 정렬)|ABC|


#### 실수의 출력에 사용될 수 있는 옵션

|format|설명|결과(float f = 1234.56789f)|
|----|----------|------|
|%e|지수형태표현|1.234568e+03|
|%f|10진수|1234.56789|
|%3.1f|출력될 자리수를 최소 3자리(소수점 포함) 소수점 이하 1자리(2번째 자리에서 반올림)|1234.6|
|%8.1f|소수점이상 최소 6자리, 소수점 이하 1자리.<br>출력될 자리수를 최소 8자리(소수점 포함)를 확보한다. 빈자리는 공백으로 채워진다(오른쪽 정렬)|  1234.6|
|%08.1f|소수점이상 최소 6자리, 소수점 이하 1자리.<br>출력될 자리수를 최소 8자리(소수점 포함)를 확보한다. 빈자리는 0으로 채워진다.|001234.6|
|%-8.1f|소수점이상 최소 6자리, 소수점 이하 1자리.<br>출력될 자리를 최소 8자리(소수점포함)를 확보한다. 빈자리는 공백으로 채워진다.(왼쪽 정렬)|1234.6|


#### 특수문자를 출력하는 옵션

|format|설명|
|----|----------|
|\t|탭(tab)|
|%n|줄바꿈 문자(new line)|
|%%|%|


#### 날짜와 시간의 출력에 사용할 수 있는 옵션

|format|설명|결과|
|----|----------|------|
|%tR<br>%tH:%tM|시분(24시간)|21:05<br>21:05|
|%tT<br>%tH:%tM:%tS|시분초(24시간)|21:05:33<br>21:05:33|
|%tD<br>%tm/%tD/%ty|월일년|11/16/15<br>11/16/15|
|%tF<br>%tY-%tm-%td|년월일|2015-11-16<br>2015-11-16|

#### day20_21/PrintStreamEx1.java
```
package day20_21;

import java.util.*;

public class PrintStreamEx1 {
	public static void main(String[] args) {
		int i = 65;
		float f = 1234.56789f;
		
		Date d = new Date();
		
		System.out.printf("문자 %c의 코드는 %d%n", i, i);
		System.out.printf("%d는 8진수로 %o, 16진수로 %x%n", i, i, i);
		System.out.printf("%3d%3d%3d\n", 100, 90, 80);
		System.out.println();
		System.out.printf("%s%-5s%5s%n", "123", "123", "123");
		System.out.println();
		System.out.printf("%-8.1f%8.1f %e%n", f, f, f);
		System.out.println();
		System.out.printf("오늘은 %tY년 %tm월 %td일 입니다.%n", d, d, d);
		System.out.printf("지금은 %tH시 %tM분 %tS초 입니다.%n", d, d, d);
		System.out.printf("지금은 %1$tH시 %1$tM분 %1$tS초 입니다.%n", d);
	}
}

실행결과
문자 A의 코드는 65
65는 8진수로 101, 16진수로 41
100 90 80

123123    123

1234.6    1234.6 1.234568e+03

오늘은 2022년 05월 09일 입니다.
지금은 00시 43분 04초 입니다.
지금은 00시 43분 04초 입니다.
```
- '숫자$'를 옵션 앞에 붙여 줌으로써 출력된 매개변수를 지정해 줄 수 있다. 예를 들어 '1$'라면 첫 번째 매개변수를 의미한다.


## 문자기반 스트림
문자 데이터를 다루는데 사용된 다는 것을 제외하고는 바이트기반 스트림과 문자기반 스트림의 사용방법은 거의 같다.

### Reader와 Writer 
- 바이트기반 스트림의 상위 인터페이스가 InputStream/OutputStream인 것과 같이 문자기반 스트림에서는 Reader/Writer가 그와 같은 역할을 한다. 
- Reader/Writer의 메서드에서 char배열을 사용한다는 것 외에는 InputStream/OutputStream의 메서드와 다르지 않다.

#### Reader의 메서드

|메서드|설명|
|----|----------|
|abstract void close()|입력스트림을 닫음으로써 사용하고 있던 자원을 반환한다.|
|void mark(int readlimit)|현재위치를 표시해놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다.|
|boolean markSupported()|mark()와 reset()을 지원하는 지를 알려 준다.|
|int read()|입력소스로부터 하나의 문자를 읽어 온다. char의 범위인 0~65535범위의 정수를 반환하며, 입력스트림의 마지막 데이터에 도달하면 -1을 반환한다.|
|int read(char[] c)|입력소스로부터 매개변수로 주어진 배열 C의 크기만큼 읽어서 배열 c에 저장한다. 읽어온 데이터의 개수 또는 -1을 반환한다.|
|abstract int read(char[] c, int off, int len)|입력소스로부터 최대 len개의 문자를 읽어서, 배열 c의 지정된 위치(off)부터 읽은 만큼 저장한다. 읽어 온 데이터의 개수 또는 -1을 반환한다.|
|int read(CharBuffer target)|입력소스로 부터 읽어서 문자버퍼(target)에 저장한다.|
|boolean ready()| 입력소스로 부터 데이터를 읽을 준비가 되어있는지 알려 준다.|
|void reset()|입력소스에서 위치를 마지막으로 mark()가 호출되었던 위치로 되돌린다.|
|long skip(long n)|현재 위치에서 주어진 문자 수(n)만큼 건너뛴다.|


#### Writer의 메서드

|메서드|설명|
|----|----------|
|Writer append(char c)|지정된 문자를 출력소스에 출력한다.|
|Writer append(CharSequence c)|지정된 문자열(CharSequence)를 출력소스에 출력한다.|
|Writer append(CharSequence c, int start, int end)|지정된 문자열(CharSequence)의 일부를 출력소스에 출력(CharBuffer, String, StringBuffer가 CharSequence를 구현)|
|abstract void close()|출력스트림을 닫음으로써 사용하고 있던 자원을 반환한다.|
|abstract void flush()|스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다(버퍼에 있는 스트림에만 해당 됨)|
|void write(int b)|주어진 값을 출력소스에 쓴다.|
|void write(char[] c)|주어진 배열 c에 저장된 모든 내용을 출력소스에 쓴다.|
|abstract void write(char[] c, int off, int len)|주어진 배열 c에 저장된 내용 중에서 off번째부터 len길이만큼만 출력소스에 쓴다.|
|void write(String str)|주어진 문자열(str)을 출력소스에 쓴다.|
|void write(String str, int off, int len)|주어진 문자열(str)의 일부를 출력소스에 쓴다.(off번째 문자부터 len개 만큼의 문자열)|


### FileReader와 FileWriter
- FileReader/FileWriter는 파일로부터 텍스트데이터를 읽고, 파일을 쓰는데 사용된다.
- 사용방법은 FileInputStream/FileOutputStream과 거의 같다.


#### day20_21/FileReaderEx1.java
```
test.txt
Hello, 안녕하세요?
```
```
package day20_21;

import java.io.*;

public class FileReaderEx1 {
	public static void main(String[] args) {
		try {
			String fileName = "test.txt";
			FileInputStream fis = new FileInputStream(fileName);
			FileReader fr = new FileReader(fileName);
			
			int data = 0;
			// FileInputStream을 이용해서 파일내용을 읽어 화면에 출력한다.
			while((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			System.out.println();
			fis.close();
			
			// FileReader를 이용해서 파일내용을 읽어 화면에 출력한다.
			while((data = fr.read()) != -1) {
				System.out.print((char)data);
			}
			
			System.out.println();
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

실행결과
Hello, ¾?³???¼¼¿??
Hello, 안녕하세요?
```
>FileInputStream을 사용했을 때는 한글이 깨져 출력이 되지만, FileReader에서는 한글이 정상적으로 출력이 된다.


### StringReader와 StringWriter
- StringReader/StringWriter는 CharArrayReader/CharArrayWriter와 같이 입출력 대상이 메모리인 스트림이다.
- StringWriter에 출력되는 데이터는 내부의 StringBuffer에 저장되며 StringWriter의 다음과 같은 메서드를 이용해서 저장된 데이터를 얻을 수 있다.

```
StringBuffer getBuffer() : StringBuffer에 출력한 데이터가 저장된 StringBuffer를 반환한다.
String toString() : StringWriter에 출력된 (StringBuffer에 저장된) 문자열을 반환한다.
```
- 근본적으로는 String도 char배열이지만, char배열보다는 String으로 처리하는 것이 여러모로 편리한 경우가 더 많다.

#### day20_21/StringReaderWriterEx.java
```
package day20_21;

import java.io.*;

public class StringReaderWriterEx {
	public static void main(String[] args) {
		String inputData = "ABCD";
		StringReader input = new StringReader(inputData);
		StringWriter output = new StringWriter();
		
		int data = 0;
		
		try {
			while((data = input.read()) != -1) {
				output.write(data); // void write(int b)
			}
		} catch (IOException e) {}
		
		System.out.println("Input Data   :" + inputData);
		System.out.println("Output Data :" + output.toString());
		System.out.println("Output Data :" + output.getBuffer().toString());
	}
}


실행결과
Input Data   :ABCD
Output Data :ABCD
Output Data :ABCD
```

## 문자기반의 보조스트림

### BufferedReader와 BufferedWriter
- BufferedReader/BufferedWriter는 버퍼를 이용해서 입출력의 효율을 높일 수 있도록 해주는 역할을 한다.
- 버퍼를 이용하면 입출력의 효율이 비교할 수 없을 정도로 좋아지기 때문에 사용하는 것이 좋다.
- BufferedReader의 readLine()을 사용하면 데이터를 라인단위로 읽을 수 있다.
- BufferedWriter는 newLine()이라는 줄바꿈 해주는 메서드를 가지고 있다.

#### day20_21/BufferedReaderEx1.java
```
package day20_21;

import java.io.*;

public class BufferedReaderEx1 {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("D:\\javaEx\\lecture\\src\\day20_21\\BufferedReaderEx1.java");
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			int i = 1;
			while((line = br.readLine()) != null) {
				// ";"를 포함한 라인을 출력한다.
				if (line.indexOf(";") != -1) {
					System.out.println(i + ":" + line);
				}
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


실행결과
1:package day20_21;
3:import java.io.*;
8:			FileReader fr = new FileReader("D:\\javaEx\\lecture\\src\\day20_21\\BufferedReaderEx1.java");
9:			BufferedReader br = new BufferedReader(fr);
11:			String line = "";
12:			int i = 1;
14:				// ";"를 포함한 라인을 출력한다.
15:				if (line.indexOf(";") != -1) {
16:					System.out.println(i + ":" + line);
18:				i++;
20:			br.close();
22:			e.printStackTrace();
```

### InputStreamReader와 OutputStreamWriter
- InputStreamReader/OutputStreamReader는 바이트기반 스트림을 문자기반 스트림으로 연결시켜주는 역할을 한다.
- 바이트기반 스트림의 데이터를 지정된 인코딩의 문자데이터로 변환하는 작업을 수행한다.
- 인코딩을 지정해 주지 않는다면 OS에서 사용하는 인코딩을 사용해서 파일을 해석해서 보여주게 된다.
- OS에서 사용하는 인코딩이 아닌 경우(예 - 중국어)는 적절하게 인코딩을 설정하여야 한다.

#### InputStreamReader의 생성자와 메서드

|생성자/메서드|설명|
|----|----------|
|InputStreamReader(InputStream in)|OS에서 사용하는 기본 인코딩의 문자로 변환하는 InputStreamReader를 생성한다.|
|InputStreamReader(InputStream in, String encoding)|지정된 인코딩을 사용하는 InputStreamReader를 생성한다.|
|String getEncoding()|InputStreamReader의 인코딩을 알려준다.|


#### OutputStreamWriter의 생성자와 메서드

|생성자/메서드|설명|
|----|----------|
|OutputStreamWriter(OutputStream in)|OS에서 사용하는 기본 인코딩의 문자로 변환하는 OutputStreamWriter를 생성한다.|
|OutputStreamWriter(OutputStream in, String encoding)|지정된 인코딩을 사용하는 OutputStreamWriter를 생성한다.|
|String getEncoding()|OutputStreamWriter의 인코딩을 알려준다.|

>시스템 속성에서 sun.jnu.encoding의 값을 보면 OS에서 사용하는 인코딩의 종류를 알 수 있다.
```
Properties prop = System.getProperties();
System.out.println(prop.get("sun.jnu.encoding"));
```

#### InputStreamReaderEx.java
```
package day20_21;

import java.io.*;

public class InputStreamReaderEx {
	public static void main(String[] args) {
		String line = "";
		
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println("사용중인 OS의 인코딩 :" + isr.getEncoding());
			
			do {
				System.out.print("문장을 입력하세요. 마치시려면 q를 입력하세요.>");
				line = br.readLine();
				System.out.println("입력하신 문장 : " + line);
			} while(!line.equalsIgnoreCase("q"));
			
			// br.close() // System.in과 같은 표준 입출력은 닫지 않아도 된다.
			System.out.println("프로그램을 종료합니다.");
		} catch (IOException e) {}
	}
}

실행결과
사용중인 OS의 인코딩 :MS949
문장을 입력하세요. 마치시려면 q를 입력하세요.>안녕하세요.
입력하신 문장 : 안녕하세요.
문장을 입력하세요. 마치시려면 q를 입력하세요.>q
입력하신 문장 : q
프로그램을 종료합니다.
```
> 한글 윈도우즈에서 사용하는 인코딩의 종류는 MS949이다.


## 표준입출력과 File

### 표준입출력 - System.in, System.out, System.err
- 표준 입출력은 콘솔(console, 도스창)을 통한 데이터 입력과 콘솔로의 데이터 출력을 의미 한다.
- 자바에서는 표준 입출력(standard I/O)을 위해 3가지 입출력 스트림, System.in, System.out, System.err을 제공하는데, 이 들은 자바 애플리케이션의 실행과 동시에 사용할 수 있도록 생성되기 때문에 개발자가 별도로 스트림을 생성하는 코드를 작성하지 않고도 사용이 가능하다.
- 자바를 처음 시작할 때부터 지금까지 줄 곧 사용해온 System.out을 스트림 생성없이 사용할 수 있었던 것이 바로 이러한 이유 때문이다.

- **System.in** : 콘솔로부터 데이터를 입력받는데 사용 (**InputStream**)
- **System.out** : 콘솔로 데이터를 출력하는데 사용 (**PrintStream**)
- **System.err** : 콘솔로 데이터를 출력하는데 사용 (**PrintStream**)

- in, out, err는 System클래스에 선언된 클래스변수(static변수)이다.
- 선언부만을 봐서는 out, err, in의 타입은 InputStream과 PrintStream이지만 실제로는 버퍼를 이용하는 BufferedInputStream과 BufferedOutputStream의 인스턴스를 사용한다.

#### day20_21/StandardIOEx1.java
```
package day20_21;

import java.io.*;

public class StandardIOEx1 {
	public static void main(String[] args) {
		try {
			int input = 0;
			while((input = System.in.read()) != -1) {
				System.out.println("input :" + input + ", (char)input :" + (char)input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


실행결과
hello
input :104, (char)input :h
input :101, (char)input :e
input :108, (char)input :l
input :108, (char)input :l
input :111, (char)input :o
input :13, (char)input :

input :10, (char)input :

```
- 마지막 2개 출력 결과물이 비어 있는 상태로 출력이 되고 있는데, 실제로는 두개의 특수문자 '\r'과 '\n'이 입력된 것으로 간주된다.
- '\r'은 캐리지리턴(carriage return), 즉 커서를 현재 라인의 첫 번째 컬럼으로 이동시키고, '\n'은 커서를 다름줄로 이동시키는 줄바꿈(new line)을 한다.
- 그래서 Enter키를 누르면, 캐리지리턴과 줄바꿈이 수행되어 다음 줄의 첫 번째 컬럼으로 커서가 이동하는 것이다.

- 여기에서 한 가지 문제는 Enter키도 사용자 입력으로 간주되어 매 입력때마다 '\r'과 '\n'이 붙기 때문에 이 들을 제거해 주어야 하는 불편한 점이 있다.
- 이러한 불편함을 제거하려면 System.in에 bufferedReader를 이용해서 readLine()을 통해 라인단위로 데이터를 입력받으면 된다.


### 표준입출력의 대상변경 - setOut(), setErr(), setIn()
setIn(), setOut(), setErr()을 사용하면 입출력을 콘솔 이외에 다른 입출력 대상으로 변경하는 것이 가능하다.

|메서드|설명|
|------|--------|
|static void setOut(PrintStream out)|System.out의 출력을 지정된  PrintStream으로 변경|
|static void setErr(PrintStream err)|System.err의 출력을 지정한 PrintStream으로 변경|
|static void setIn(InputStream in)|System.in의 입력을 지정한 InputStream으로 변경|


#### day20_21/StandardIOEx2.java
```
package day20_21;

public class StandardIOEx2 {
	public static void main(String[] args) {
		System.out.println("out : Hello World!");
		System.err.println("err : Hello World!");
	}
}

실행결과
out : Hello World!
err : Hello World!
```

#### day20_21/StandardIOEx3.java
```
package day20_21;

import java.io.*;

public class StandardIOEx3 {
	public static void main(String[] args) {
		PrintStream ps = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("test.txt");
			ps = new PrintStream(fos);
			System.setOut(ps); // System.out의 출력대상을 test.txt파일로 변경
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		}
		
		System.out.println("Hello by System.out");
		System.err.println("Hello by System.err");
	}
}


실행결과
Hello by System.err

test.txt
Hello by System.out
```


### RandomAccessFile
- 자바에서는 입력과 출력이 각각 분리되어 별도로 작업을 하도록 설계되어 있는데 RandomAccessFile만은 하나의 클래스로 파일에 대한 입력과 출력을 모두 할 수 있도록 되어 있다.
- InputStream이나 OutputStream으로부터 상속받지 않고 DataInput인터페이스와 DataOutput인터페이스를 모두 구현했기 때문에 읽기와 쓰기가 모두 가능하다 
- 사실 DataInputStream은 DataInput인터페이스를, DataOutputStream은 DataOutput인터페이스를 구현했다. 이 두 클래스의 기본 자료형(primitive data type)을 읽고 쓰기 위한 메서드들은 모두 이 2개의 인터페이스에 정의되어 있는 것들이다.
- 따라서 RandomAccessFile클래스도 DataInputStream과 DataOutputStream처럼 기본자료형 단위로 데이터를 읽고 쓸 수 있다.
- RandomAccessFile클래스의 가장 큰 장점은 파일의 어느 위치에나 읽기/쓰기가 가능하다는 것이다. 다른 입출력 클래스들은 입력소스에 순차적으로 읽기/쓰기를 하기 때문에 읽기와 쓰기가 제한적인데 반해서 RandomAccessFile클래스는 파일에 읽고 쓰는 위치에 제한이 없다.
- 내부적으로 파일 포인터를 사용하는데, 입출력 작업이 수행되는 곳이 바로 파일 포인터가 위치한 곳이 된다.
- 파일 포인터의 위치는 파일의 제일 첫 부분(0부터 시작)이며, 읽기 또는 쓰기를 수행할때 마다 작업이 수향된 다음 위치로 이동하게 된다.
- 순차적으로 읽기나 쓰기를 한다면 , 파일 포인터를 이동시키기 위해 별도의 작업이 필요하지 않지만 파일의 임의의 위치에 있는 내용에 대해서 작업하고자 한다면, 먼저 파일 포인터를 원하는 위치로 옮긴 다음 작업을 해야 한다.
- getFilePointer() : 현재 작업중인 파일에서 파일 포인터의 위치를 알고 싶을 때 사용
- seek(long pos), skipBytes(int n) : 파일 포인터의 위치를 옮기기 위해서 사용 

> 사실 모든 입출력에 사용되는 클래스들은 입출력 시 다음 작업이 이루어질 위치를 저장하고 있는 포인터를 내부적으로 갖고 있다. 다만 내부적으로 사용될 수 있기 때문에 작업자가 포인터의 위치를 마음대로 변경할 수 없다는 것이 RandomAccessFile과 다른 점이다.

#### RandomAccessFile의 생성자와 메서드

|생성자/메서드|설명|
|------|--------|
|RandomAccessFile(File file, String mode)<br>RandomAccessFile(String fileName, String mode)|주어진 file에 읽기 또는 읽기와 쓰기를 하기 위한 RandomAccessFile인스턴스를 생성한다. mode의 값은 "r", "rw", "rws", "rwd"가 지정가능하다.<br>"r" - 파일로부터 읽기(r)만을 수행할 때<br>"rw" - 파일에 읽기(r)와 쓰기(w)<br>"rws"와 "rwd"는 기본적으로 "rw"와 같은데, 출력내용이 파일에 지연 없이 바로 쓰이게 한다. "rwd"는 파일 내용만, "rws"는 파일 메타정보도 포함|
|FileChannel getChannel()|파일의 파일 채널을 반환한다.|
|FileDescriptor getFD()|파일의 파일 디스크립터를 반환|
|long getFilePointer()|파일 포인터의 위치를 알려 준다.|
|long length()|파일의 크기를 얻을 수 있다.(단위 byte)|
|void seek(long pos)|파일 포인터의 위치를 변경한다. 위치는 파일의 첫 부분부터 pos크기만큼 떨어진 곳이다(단위 byte)|
|void setLength(long newLength)|파일의 크기를 지정한 길이로 변경한다.(byte 단위)|
|int skipBytes(int n)|지정된 수만큼의  byte를 건너뛴다.|


#### day20_21/RandomAccessFileEx1.java
```
package day20_21;

import java.io.*;

public class RandomAccessFileEx1 {
	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("test.dat", "rw");
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
			raf.writeInt(100);
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
			raf.writeLong(100L);
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


실행결과

파일 포인터의 위치: 0
파일 포인터의 위치: 4
파일 포인터의 위치: 12
```

#### day20_21/RandomAccessFileEx2.java
```
package day20_21;

import java.io.*;

public class RandomAccessFileEx2 {
	public static void main(String[] args) {
		//				   번호, 국어, 영어, 수학 
		int[] score = {   1,  100,    90,     90, 
							    2,   70,    90,    100,
							    3,  100,   100,    100,
							    4,   70,    60,     80,
							    5,   70,    90,    100 };
		
		
		try {
			RandomAccessFile raf = new RandomAccessFile("score2.dat", "rw");
			for(int i = 0; i < score.length; i++) {
				raf.writeInt(score[i]);
			}
			
			/**
			 * 파일포인터가 파일의 끝에 도달해 있으므로 
			 * 파일 포인터를 다시 처음으로 이동 시켜야 저장된 값을 읽어올 수 있다.
			 */
			raf.seek(0);  
			while(true) {
				System.out.println(raf.readInt());
			}
			
		} catch (EOFException eof) {
			// readInt()를 호출했을 때 더 이상 읽을 내용이 없다면 EOFException이 발생한다.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 

실행결과

1
100
90
90
2
70
90
100
3
100
100
100
4
70
60
80
5
70
90
100
```

#### day20_21/RandomAccessFileEx3.java
```
package day20_21;

import java.io.*;

public class RandomAccessFileEx3 {
	public static void main(String[] args) {
		int sum = 0;
		
		try {
			RandomAccessFile raf = new RandomAccessFile("score2.dat", "r");
			int i = 4;
			
			while(true) {
				raf.seek(i);
				sum += raf.readInt();
				i+=16;
			}
			
		} catch (EOFException e) {
			System.out.println("sum : " + sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


실행결과

sum : 410
```

### File
자바에서는 File클래스를 통해서 파일과 디렉토리를 다룰 수 있도록 하고 있다.(File인스턴스는 파일 일 수도 있고 디렉토리일 수도 있다.)

#### File의 생성자와 경로와 관련된 메서드

|생성자/메서드|설명|
|------|--------|
|File(String fileName)|주어진 문자열(fileName)을 이름으로 갖는 파일을 위한 File인스턴스를 생성한다. 파일 뿐만 아니라 디렉토리도 같은 방법으로 다룬다.<br>여기서 fileName은 주로 경로(path)를 포함해서 지정해주지만, 파일 이름만 사용해도 되는 데 이 경우 프로그램이 실행되는 위치가 경로(path)로 간주된다.|
|File(String pathName, String fileName)<br>File(File pathName, String fileName)|파일의 경로와 이름을 따로 분리해서 지정할 수 있도록 한 생성자. 이 중 두 번째 것은 경로를 문자열이 아닌 File인스턴스인 경우를 위해서 제공된 것이다.|
|File(URI uri)|지정된 uri로 파일을 생성|
|String getName()|파일이름을 String으로 반환|
|String getPath()|파일의 경로(path)를 String으로 반환|
|String getAbsolutePath()<br>File getAbsoluteFile()|파일의 상위 디렉토리를 String으로 반환<br>파일의 상위 디렉토리를 File로 반환|
|String getCanonicalPath()<br>File getCanonicalFile()|파일의 정규경로를 String으로 반환<br>파일의 정규경로를 File로 반환|


#### 경로와 관련된 File의 멤버변수

|멤버변수|설명|
|------|--------|
|static String pathSeparator|OS에서 사용하는 경로(path) 구분자. 윈도우 ';', 유닉스 ':'|
|static char pathSeparatorChar|OS에서 사용하는 경로(path) 구분자. 윈도우에서는 ';', 유닉스 ':'|
|static String separator|OS에서 사용하는 이름 구분자. 윈도우 '\', 유닉스 '/'|
|static char separatorChar|OS에서 사용하는 이름 구분자. 윈도우 '\', 유닉스 '/'|


#### day20_21/FileEx1.java 

```
package day20_21;

import java.io.*;

public class FileEx1 {
	public static void main(String[] args) throws IOException {
		File f = new File("c:\\jdk1.8\\work\\FileEx1.java");
		String fileName = f.getName();
		int pos = fileName.lastIndexOf(".");
		
		System.out.println("경로를 제외한 파일이름 - " + f.getName());
		System.out.println("확장자를 제외한 파일이름 - " + fileName.substring(0, pos));
		
		System.out.println("경로를 포함한 파일이름 - " + f.getPath());
		System.out.println("파일의 절대경로 - " + f.getAbsolutePath());
		System.out.println("파일의 정규경로 - " + f.getCanonicalPath());
		System.out.println("파일이 속해있는 디렉토리 - " + f.getParent());
		System.out.println();
		System.out.println("File.pathSeparator - " + File.pathSeparator);
		System.out.println("File.pathSeparatorChar - " + File.pathSeparatorChar);
		System.out.println("File.separator - " + File.separator);
		System.out.println("File.separatorChar - " + File.separatorChar);
		System.out.println();
		System.out.println("user.dir=" + System.getProperty("user.dir"));
		System.out.println("sun.boot.class.path=" + System.getProperty("sun.boot.class.path"));
	}
}


실행결과

경로를 제외한 파일이름 - FileEx1.java
확장자를 제외한 파일이름 - FileEx1
경로를 포함한 파일이름 - c:\jdk1.8\work\FileEx1.java
파일의 절대경로 - c:\jdk1.8\work\FileEx1.java
파일의 정규경로 - C:\jdk1.8\work\FileEx1.java
파일이 속해있는 디렉토리 - c:\jdk1.8\work

File.pathSeparator - ;
File.pathSeparatorChar - ;
File.separator - \
File.separatorChar - \

user.dir=D:\javaEx\lecture
sun.boot.class.path=null
```

- 윈도우에서 사용하는 구분자(\)를 코드에 직접 적어 놓았다면 이 코드는 다른 OS에서는 오류를 일으킬 수 있다. - File.separator 사용할 것 
- 절대경로(absolute path)는 파일시스템의 루트(root)로 부터 시작하는 파일의 전체경로를 의미한다.
- OS에 따라 다르지만, 하나의 파일에 대해 둘 이상의 절대경로가 존재할 수 있다. 현재 디렉토리를 의미하는 '.'와 같은 기호나 링크를 포함하고 있는 경우가 이에 해당한다. 
- 그러나 정규경로(canonical path)는 기호나 링크 등을 포함하지 않는 유일한 경로를 의미한다. 예) C:\jdk1.8\work\FileEx1.java의 또 다른 절대경로는 'C:\jdk1.8\work\.\FileEx.java가 있지만, 정규경로는 **C:\jdk1.8\work\FileEx1.java** 단 하나 뿐이다.
- 시스템 속성 중에서 user.dir의 값을 확인하면 현재 프로그램이 실행 중인 디렉토리를 알 수 있다. 
- 우리가 OS의 시스템 변수로 설정하는 classpath외에 sun.boot.class.path라는 시스템 속성에 기본적인 classpath가 있어서 기본적인 경로들은 이미 설정되어 있다. 그래서 처음에 JDK설치 후 classpath를 따로 지정해주지 않아도 된다.

- 새로운 파일을 생성하기 위해서는 File인스턴스를 생성한 다음, 출력스트림을 생성하거나 createNewFile()을 호출해야 한다.
```
1. 이미 존재하는 파일을 참조할때
File f = new File("c:\\jdk1.8\\work", "FileEx1.java");

2. 기존에 없는 파일을 새로 생성할 때
File f = new File("c:\\jdk1.8\\work", "FileEx1.java");
f.createNewFile(); // 새로운 파일이 생성된다.
```


#### File의 메서드

|메서드|설명|
|------|--------|
|boolean canRead()|읽을 수 있는 파일인지 검사한다.|
|boolean canWrite()|쓸 수 있는 파일인지 검사한다.|
|boolean canExecute()|실행할 수 있는 파일인지 검사한다.|
|int compareTo(File pathname)|지정된 파일(pathname)과 비교한다.|
|boolean exists()|파일이 존재하는지 검사한다.|
|boolean isAbsolute()|파일 또는 디렉토리가 절대경로명으로 지정되었는지 확인한다.|
|boolean isDirectory()|디렉토리인지 확인한다.|
|boolean isFile()|파일인지 확인한다.|
|boolean isHidden()|파일의 속성이 '숨김(Hidden)'인지 확인한다. 또한 파일이 존재하지 않으면 false를 반환한다.|
|int compareTo(File pathname)|주어진 파일 또는 디렉토리를 비교한다. 같으면 0을 반환하며, 다르면 1 또는 -1을 반환한다(Unix시스템에서는 대소문자를 구별하며, Windows에서는 구별하지 않는다.)|
|boolean createNewFile()|아무런 내용이 없는 새로운 파일을 생성한다.(단, 생성하려는 파일이 이미 존재하면 생성되지 않는다.)|
|static File createTempFile(String prefix, String suffix)|임시파일을 시스템의 임시 디렉토리에 생성한다.|
|static File createTempFile(String prefix, String suffix, File directory)|임시파일을 시스템의 지정된 디렉토리에 생성한다.|
|boolean delete()|파일을 삭제한다.|
|void deleteOnExit()|응용 프로그램 종료시 파일을 삭제한다. 주로 실행 시 작업에 사용된 임시파일을 삭제하는데 사용된다.|
|boolean equals(Object obj)|주어진 객체(주로 File인스턴스)가 같은 파일인지 비교한다.(Unix시스템에서는 대소문자를 구별하며, Windows에서는 구별하지 않는다.)|
|long lastModified()|파일의 마지막으로 수정된 시간을 반환|
|long length()|파일의 크기를 반환한다.|
|String[] lit()|디렉토리의 파일목록(디렉토리 포함)을 String배열로 반환한다.|
|String[] list(FilenameFilter filter)<br>File[] list(FilenameFilter filter)|FilenameFilter 인스턴스에 구현된 조건에 맞는 파일을 String배열(File배열)로 반환한다.|
|File[] listFiles()<br>File[] listFiles(FileFilter filter)<br>File[] listFiles(FilenameFilter f)|디렉토리의 파일목록(디렉토리 포함)을 File배열로 반환(filter가 지정된 경우에는 filter의 조건과 일치하는 파일만 반환)|
|static File[] listRoots()<br>log getFreeSpace()<br>long getTotalSpace()<br>long getUsableSpace()|컴퓨터의 파일시스템의 root의 목록(예 - C:\, D:\)<br>get으로 시작하는 메서드들은 File이 root일 때 비어있는 공간, 전체 공간, 사용가능한 공간을 바이트 단위로 반환|
|boolean mkdir()<br>boolean mkdirs()|파일에 지정된 경로로 디렉토리(폴더)를 생성. 성공하면 true<br>mkdirs는 필요하면 부모 디렉토리까지 생성|
|boolean renameTo(File dest)|지정된 파일(dest)로 이름을 변경|
|boolean setExecutable(boolean executable)<br>boolean SetExecutable(boolean executable, boolean ownerOnly)<br>boolean setReadable(boolean readable)<br>boolean setReadable(boolean readable, boolean ownerOnly)<br>boolean setReadOnly()<br>boolean setWritable(boolean writable)<br>boolean setWritable(boolean writable, boolean ownerOnly)|
|boolean setLastModified(long t)|파일의 마지막으로 수정된 시간을 지정된 시간(t)으로 변경|
|Path toPath()|파일을 Path로 변환해서 반환|
|URI toURI()|파일을 URI로 변환해서 반환|

#### day20_21/FileEx2.java
```
package day20_21;

import java.io.*;

public class FileEx2 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("USAGE : FileEx2 DIRECTORY");
			System.exit(0);
		}
		
		File f = new File(args[0]);
		
		if (!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);
		}
		
		File[] files = f.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			System.out.println(files[i].isDirectory() ? "[" + fileName +"]" :  fileName);
		}
	}
}


실행결과
java FileEx2 d:\javaEx\lecture

.classpath
.project
[.settings]
123.txt
[bin]
data.txt
output.txt
output.xml
sample.dat
score.dat
score2.dat
[src]
test.dat
test.txt
```

#### day20_21/FileEx3.java
```
package day20_21;

import java.io.*;
import java.util.ArrayList;

public class FileEx3 {
	static int totalFiles = 0;
	static int totalDirs = 0;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("USAGE : java FileEx3 DIRECTORY");
			System.exit(0);
		}
		
		File dir = new File(args[0]);
		
		if (!dir.exists() || !dir.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);
		}
		
		printFileList(dir);
		
		System.out.println();
		System.out.println("총 " + totalFiles + " 개의 파일");
		System.out.println("총 " + totalDirs + "개의 디렉토리");

	}
	
	public static void printFileList(File dir) {
		System.out.println(dir.getAbsolutePath() + " 디렉토리");
		File[] files = dir.listFiles();
		
		ArrayList subDir = new ArrayList();
		
		for(int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			
			if (files[i].isDirectory()) {
				filename = "[" + filename + "]";
				subDir.add(i + "");
			}
			
			System.out.println(filename);
		}
		
		int dirNum = subDir.size();
		int fileNum = files.length - dirNum;
		
		totalFiles += fileNum;
		totalDirs += dirNum;
		
		System.out.println(fileNum + "개의 파일, " + dirNum + "개의 디렉토리");
		System.out.println();
		
		for(int i = 0; i < subDir.size(); i++) {
			int index = Integer.parseInt((String)subDir.get(i));
			printFileList(files[index]);
		}
	}
}

실행결과
java FileEx3 d:\javaEx\lecture\src
d:\javaEx\lecture\src 디렉토리
[day12]
[day13_14]
[day15]
[day16]
[day17]
[day18]
[day20_21]
0개의 파일, 7개의 디렉토리

d:\javaEx\lecture\src\day12 디렉토리
[date_calendar]
[format]
[time]
0개의 파일, 3개의 디렉토리

d:\javaEx\lecture\src\day12\date_calendar 디렉토리
CalendarEx1.java
CalendarEx2.java
CalendarEx3.java
CalendarEx4.java
4개의 파일, 0개의 디렉토리

...

```


## 직렬화(Serialization)
객체를 컴퓨터에 저장했다가 다음에 다시 꺼내 쓸 수는 없을지, 네트웍을 통해 컴퓨터간에 서로 객체를 주고받을 수는 없는지 고민해 본적이 있을 것이다. 이러한 일을 가능하게 해주는 것이 직렬화(Serialization)이다.

### 직렬화란?
- 직렬화란 객체를 데이터 스트림으로 만드는 것을 뜻한다.
- 객체에 저장된 데이터를 스트림에 쓰기(write)위해 연속적인(serial) 데이터로 변환하는 것을 말한다.
- 반대로 스트림으로부터 데이터를 읽어서 객체를 만드는 것을 역직렬화(deserialization)라고 한다.

![객체의 직렬화와 역직렬화](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/20~21%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9E%85%EC%B6%9C%EB%A0%A5(IO)/images/%EA%B0%9D%EC%B2%B4%EC%9D%98_%EC%A7%81%EB%A0%AC%ED%99%94_%EC%97%AD%EC%A7%81%EB%A0%AC%ED%99%94.png)


- 객체는 클래스에 정의된 인스턴스변수의 집합이다. 객체에는 클래스변수나 메서드가 포함되지 않는다. 객체는 오직 인스턴스변수들로만 구성되어 있다.
- 그래서 객체를 저장한다는 것은 객체의 모든 인스턴스변수의 값을 저장한다는 것과 같은 의미이다. 
- 어떤 객체를 저장하고자 한다면, 현재 객체의 모든 인스턴스변수의 값을 저장하기만 하면 된다. 
- 저장했던 객체를 다시 생성하려면 , 객체를 생성한 후에 저장했던 값을 읽어서 생성한 객체의 인스턴스변수에 저장하면 되는 것이다.

![클래스와 인스턴스](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/20~21%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9E%85%EC%B6%9C%EB%A0%A5(IO)/images/%ED%81%B4%EB%9E%98%EC%8A%A4%EC%9D%98_%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4.png)


### ObjectInputStream, ObjectOutputStream
- 직렬화(스트림에 객체를 출력)에는 ObjectOutputStream을 사용하고 역직렬화(스트림으로 부터 객체를 입력)에는 ObjectInputStream을 사용한다.
- ObjectInputStream과 ObjectOutputStream은 각각 InputStream과 OutputStream을 직접 상속받지만 **기반스트림을 필요로 하는 보조스트림이다.** 
- 그래서 객체를 생성할 때 입출력(직렬화/역직렬화)할 스트림을 지정해주어야 한다.

```
ObjectInputStream(InputStream in)
ObjectOutputStream(OutputStream out)
```

- 파일에 객체를 저장(직렬화)
- ObjectOuputStream의 writeObject(Object obj)를 사용해서 객체를 출력하면, 객체가 파일에 직렬화되어 저장된다.
```
FileOutputStream fos = new FileOutputStream("objectfile.ser");
ObjectOutputStream out = new ObjectOutputStream(fos);

out.writeObject(new UserInfo());
```

- 역직렬화는 직렬화할 때와는 달리 입력스트림을 사용하고 wirteObject(Object obj)대신 readObject()를 사용하여 저장된 데이터를 읽기만 하면 객체로 역직렬화된다.
- 다만 readObject()의 반환타입이 Object이기 때문에 객체 원래의 타입으로 형변환 해주어야 한다.
```
FileInputStream fis = new FileInputStream("objectfile.ser");
ObjectInputStream in = new ObjectInputStream(fis);

UserInfo info = (UserInfo)in.readObject();
```

#### ObjectInputStream과  ObjectOutputStream의 메서드
|ObjectInputStream|ObjectOutputStream|
|-----|-----|
|void defaultReadObject()<br>int read()<br>int read(byte[] buf, int off, int len)<br>boolean readBoolean()<br>byte readByte()<br>char readChar()<br>double readDouble()<br>float readFloat()<br>int readInt()<br>long readLong()<br>short readShort()<br>Object readObject()<br>int readUnsignedByte()<br>int readUnsignedShort()<br>Object readUnshared()<br>String readUTF()|void defaultReadObject()<br>void write(byte[] buf)<br>void write(byte[] buf, int off, int len)<br>void write(int val)<br>void writeBoolean(boolean val)<br>void writeByte(int val)<br>void writeBytes(String str)<br>void writeChar(int val)<br>void writeChars(String str)<br>void writeDouble(double val)<br>void writeFloat(float val)<br>void writeInt(int val)<br>void writeLong(long val)<br>void writeObject(Object obj)<br>void writeShort(int val)<br>void writeUnshared(Object obj)<br>void writeUTF(String str)|

- 상기 메서드들은 직렬화와 역직렬화를 직접 구현할 때 주로 사용되며, defaultReadObject()와 defaultWriteObject()는 자동 직렬화를 수행한다.
- 객체를 직렬화/역직렬화하는 작업은 객체의 모든 인스턴스변수가 참조하고 잇는 모든 객체에 대한 것이기 때문에 상당히 복잡하여 시간도 오래걸린다.
- readObject()와 writeObject()를 사용한 자동 직렬화가 편리하기는 하지만 직렬화 작업시간을 단축시키려면 직렬화하고자 하는 객체의 클래스에 추가적으로 다음과 같은 2개의 메서드를 직접 구현해주어야 한다.

```
private void writeObject(ObjectOutputStream out) throws IOException {
	// write메서드를 사용해서 직렬화를 수행한다.
}

private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	// read메서드를 사용해서 역직렬화를 수행한다.
}
```

### 직렬화가 가능한 클래스 만들기 - Serializable, transient
- 직렬화가 가능한 클래스를 만드는 방법은 클래스가 java.io.Serializable인터페이스를 구현하도록 하면된다.

변경 전 
```
public class UserInfo {
	String name;
	String password;
	int age;
}
```

변경 후 
```
public class UserInfo implements java.io.Serializable {
	String name;
	String password;
	int age;
}
```

- Serializable 인터페이스는 아무런 내용도 없는 빈 인터페이이지만, 직렬화를 고려하여 작성한 클래스인지를 판단하는 기준이된다.
```
public interface Serializable {}
```

- Serializable을 구현한 클래스를 상속받는다면 Serializable을 구현하지 않아도 된다. 
```
public class SuperUserInfo implements Serializable {
	String name;
	String password;
}

public class UserInfo extends SuperUserInfo {
	int age;
}
```

- 그러나 상위클래스가 Serializable을 구현하지 않았다면 하위클래스를 직렬화할 때 상위클래스에 정의된 인스턴스 변수는 직렬화 대상에서 제외된다.
```
public class SuperUserInfo {
	String name;
	String password;
}

public class UserInfo extends SuperUserInfo implements Serializable {
	int age;
}
```

- 모든 클래스의 가장상위 클래스인 Object는 Serializable을 구현하지 않았기 때문에 직렬화할 수 없다(Object 객체를 멤버변수로 사용하면 java.io.NotSerializableException이 발생하면서 직렬화에 실패한다.)
```
public class UserInfo implements Serializable {
	String name;
	String password;
	int age;
	
	Obect obj = new Object(); // Object 객체는 직렬화할 수 없다.
}
```
- 하기 코드에서는 Object이긴 하지만 실제로 저장되는 객체는 직렬화가 가능한 String인스턴스이기 때문에 직렬화가 가능하다.
- 인스턴스 변수의 탕비이 아닌 실제로 연결된 객체의 종류에 의해서 결정된다는 것

```
public class UserInfo implements Serializable {
	String name;
	String password;
	int age; 
	
	Object obj = new String("abc"); // String은 직렬화될 수 있다.
}
```

- 제어자 **transient**를 붙여서 직렬화 대상에서 제외되도록 할 수 있다.
- **transient**가 붙은 인스턴스 변수의 값은 그 타입의 기본값으로 직렬화된다고 볼 수 있다.
```
public class UserInfo implements Serializable {
	String name;
	transient String password; // 직렬화 대상에서 제외된다.
	int age;
	
	transient Object obj = Object(); // 직렬화 대상에서 제외된다.
}
```

#### day20_21/UserInfo.java
```
package day20_21;

import java.io.*;

public class UserInfo implements Serializable {
	String name;
	String password;
	int age;
	
	public UserInfo() {
		this("Unknown", "1111", 0);
	}
	
	public UserInfo(String name, String password, int age) {
		this.name = name;
		this.password = password;
		this.age = age;
	}
	
	public String toString() {
		return "(" + name + "," + password + "," + age + ")";
	}
}
```

#### day20_21/SerialEx1.java
```
package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo.ser";
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo u1 = new UserInfo("JavaMan", "1234", 30);
			UserInfo u2 = new UserInfo("JavaWoman", "4321", 26);
			
			ArrayList<UserInfo> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);
			
			// 객체를 직렬화 한다.
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			out.close();
			System.out.println("직렬화가 잘 끝났습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

실행결과

직렬화가 잘 끝났습니다.
```
>ArrayList와 같은 객체를 직렬화하면 ArrayList에 저장된 모든 객체들과 각 객체의 인스턴스 변수가 참조하고 있는 객체들까지 모두 직렬화된다.

#### day20_21/SerialEx2.java
```
package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx2 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo.ser";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			
			// 객체를 읽을 때는 출력한 순서와 일치해야 한다.
			UserInfo u1 = (UserInfo)in.readObject();
			UserInfo u2 = (UserInfo)in.readObject();
			ArrayList<UserInfo> list = (ArrayList<UserInfo>)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

실행결과

(JavaMan,1234,30)
(JavaWoman,4321,26)
[(JavaMan,1234,30), (JavaWoman,4321,26)]
```
>주의해야 할 점은 객체를 역직렬화 할 때는 직렬화할 때의 순서와 일치해야 한다는 것이다. 예를 들어 객체 u1, u2, list의 순서로 직렬화 했다면, 역직렬화 할 때도 u1, u2, list의 순서로 처리해야 한다. 그래서 직렬화할 객체가 많을 때는 각 객체를 개별적으로 직렬화하는 것보다 ArrayList와 같은 컬렉션에 저장해서 직렬화하는 것이 좋다.<br>역직렬화할 때 ArrayList 하나만 역직렬화 하면 되므로 역직렬화할 객체의 순서를 고려하지 않아도 되기 때문이다.

#### day20_21/UserInfo2.java
```
package day20_21;

import java.io.*;

class SuperUserInfo {
		String name;
		String password;
		
		SuperUserInfo() {
			this("Unknown", "1111");
		}
		
		SuperUserInfo(String name, String password) {
			this.name = name;
			this.password = password;
		}
}

public class UserInfo2 extends SuperUserInfo implements Serializable {
	int age;
	
	public UserInfo2() {
		this("Unknown", "1111", 0);
	}
	
	public UserInfo2(String name, String password, int age) {
		super(name, password);
		this.age = age;
	}
	
	public String toString() {
		return "(" + name + "," + password + "," + age + ")";
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(password);
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		password = in.readUTF();
		in.defaultReadObject();
	}
}
```

#### day20_21/SerialEx3.java
```
package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx3 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo2.ser";
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo2 u1 = new UserInfo2("JavaMan", "1234", 30);
			UserInfo2 u2 = new UserInfo2("JavaWoman", "4321", 26);
			
			ArrayList<UserInfo2> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);
			
			// 객체를 직렬화 한다.
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			out.close();
			System.out.println("직렬화가 잘 끝났습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

```

#### day20_21/SerialEx4.java
```
package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx4 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo2.ser";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			
			// 객체를 읽을 때는 출력한 순서와 일치해야 한다.
			UserInfo2 u1 = (UserInfo2)in.readObject();
			UserInfo2 u2 = (UserInfo2)in.readObject();
			ArrayList<UserInfo2> list = (ArrayList<UserInfo2>)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

실행결과

(JavaMan,1234,30)
(JavaWoman,4321,26)
[(JavaMan,1234,30), (JavaWoman,4321,26)]
```

- 직렬화 되지 않는 상위 클래스로 부터 상속받은 인스턴스변수에 대한 직렬화를 구현한 것이다. 
- 직렬화될 객체의 클래스에 아래와 같이 writeObject()와 readObject()를 추가해서 상위 클래스로 부터 상속받은 인스턴스변수인 name과 password가 직접 직렬화되도록 해야 한다.
- 이 메서드들은 직렬화/역직렬화 작업시에 자동적으로 호출된다.
- defaultWriteObject()는 UserInfo2클래스 자신에 정의된 인스턴스변수 age의 직렬화를 수행한다.


### 직렬화 가능한 클래스의 버전관리
- 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스를 사용해야 한다. 그러나 클래스의 이름이 같더라도 클래스의 내용이 변경된 경우 역직렬화는 실패하며 다음과 같은 예외가 발생한다.
```
java.io.InvalidClassException: day20_21.UserInfo2; local class incompatible: stream classdesc serialVersionUID = -2313780759168234882, local class serialVersionUID = -4721494493246995595
...

```
- 위 예외의 내용은 직렬화 할 때의 역직렬화 할 때의 클래스의 버전이 같아야 하는데 다르다는 것이다. 
- 객체가 직렬화될 때 클래스에 정의된 멤버들의 정보를 이용해서 serialVersionUID라는 클래스의 버전을 자동생성해서 직렬화 내용에 포함된다.
- 그래서 역직렬화 할 때 클래스의 버전을 비교함으로써 직렬화할 때의 클래스의 버전과 일치하는지 확인할 수 있는 것이다.
- 그러나 static변수나 상수 또는 transient가 붙은 인스턴스변수가 추가되는 경우에는 직렬화에 영향을 미치지 않기 때문에 클래스의 버전을 다르게 인식하도록 할 필요는 없다.
- 네트웍으로 객체를 직렬화하여 전송하는 경우. 보내는 쪽과 받는 쪽이 모두 같은 버전의 클래스를 가지고 있어야하는데 클래스가 조금만 변경되어도 해당 클래스를 재배포하는 것은 프로그램을 관리하기 어렵게 만든다. 이럴 때는 클래스의 버전을 수동으로 관리해줄 필요가 있다.
- 클래스의 버전을 수동으로 관리하려면 다음과 같이 serialVersionUID를 추가로 정의해야 한다.

```
public class UserInfo2 extends SuperUserInfo implements Serializable {
	private static final long serialVersionUID = -2313780759168234882L;
	int age;
	
	...
}
```


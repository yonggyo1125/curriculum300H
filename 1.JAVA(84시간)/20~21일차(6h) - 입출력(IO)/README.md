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
InputStream과 OutputStream은 모든 바이트기반의 스트림의 조상이며 다음과 같은 메서드가 선언되어 있다.

#### InputStream 메서드 

|메서드명|설명|
|----|------|
|int available()|스트림으로 부터 읽어 올 수 있는 데이터의 크기를 반환한다.|
|void close()|스트림을 닫음으로써 사용하고 있던 자원을 반환한다.|
|void mark(int readlimit)|현재위치를 표시해 놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다. readlimit은 되돌아갈 수 있는 byte의 수이다.|
|boolean markSupported()|mark()와 resest()을 지원하는지를 알려준다.mark()와 reset()기능을 지원하는 것은 선택적이므로 mark()와 reset()을 사용하기 전에 markSupported()를 호출해서 지원여부를 확인해야 한다.|
|abstract int read()|1 byte를 읽어 온다(0~255사이의 값). 더 이상 읽어올 데이터가 없으면 -1을 반환한다. abstract메서드라서 InputStream의 자손들은 자신의 상황에 알맞게 구현해야 한다.|
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
- FilterInputStream/FilterOutputStream은 InputStream/OutputStream의 자손이면서 모든 보조스트림의 조상이다.
- 보조스트림은 자체적으로 입출력을 수행할 수 없기 때문에 기반스트림을 필요로 한다.
```
protected FilterInputStream(InputStream in)
public FilterOutputStream(OutputStream out)
```

- FilterInputStream/FilterOutputStream의 모든 메서드는 단순히 기반스트림의 메서드를 그대로 호출할 뿐 FilterInputStream/FilterOutputStream자체로는 아무런 일도 하지 않는다. 
- FilterInputStream/FilterOutputStream은 상속을 통해 원하는 작업을 수행하도록 읽고 쓰는 메서드를 오버라이딩해야 한다.

- 생성자 FilterInputStream(InputStream in)은 접근제어가가 protected이기 때문에 FilterInputStream의 인스턴스를 생성해서 사용할 수 없고 상속을 통해서 오버라이딩 되어야 한다. 
-FilterInputStream/FilterOutputStream을 상속받아서 기반스트림에 보조기능을 추가한 보조스트림 클래스는 다음과 같다.

- **FilterInputStream의 자손** : BufferedInputStream, DataInputStream, PushbackInputStream 등 
- **FilterOutputStream의 자손** : BufferedOutputStream, DataOutputStream, PrintStream 등


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





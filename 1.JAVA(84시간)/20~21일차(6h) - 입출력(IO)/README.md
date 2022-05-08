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
- DataInputStream/DataOutputStream도 각각 FilterInputStream/FilterOutputStream의 자손이며 DataInputStream은 DataInput인터페이스를, DataOutputStream은 DataOutput인터페이스를 각각 구현하였기 때문에, 데이터를 읽고 쓰는데 있어서 byte단위가 아닌, 8가지 기본 자료형의 단위로 읽고 쓸 수 있다는 장점이 있다.
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
- 바이트기반 스트림의 조상이 InputStream/OutputStream인 것과 같이 문자기반 스트림에서는 Reader/Writer가 그와 같은 역할을 한다. 
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

### 표준입출력의 대상변경 - setOut(), setErr(), setIn()

### RandomAccessFile

### File


## 직렬화(Serialization)

### 직렬화란?

### ObjectInputStream, ObjectOutputStream

### 직렬화가 가능한 클래스 만들기 - Serializable, transient

### 직렬화 가능한 클래스의 버전관리





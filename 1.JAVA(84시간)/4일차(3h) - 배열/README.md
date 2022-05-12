# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/10yXq3LwKg3fWNpranL7S0hjHaDfQQigy?usp=sharing)

# 배열

## 배열이란?

### 자료를 순차적으로 관리하는 구조, 배열
학교에 학생이 100명 있습니다. 학번의 자료형을 정수라고 하면 학생이 100명일 때 int studentID1, int studentID2, int studentID3,...,int studentId100 이렇게 변수 100개를 선언해서 사용해야 합니다. 그런데 학번에 대한 여러 개 변수들을 일일이 쓰는 것은 번거로운 일입니다. 이때 사용하는 자료형이 **배열(array)**입니다. 

![배열_자료구조](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EB%B0%B0%EC%97%B4_%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0.png)

- 배열을 사용하면 **자료형이 같은 자료 여러개를 한 번에 관리**할 수 있습니다.
- 배열은 **자료가 연속으로 나열된 자료구조**입니다.

### 배열 선언과 초기화
- 배열을 사용하려면 먼저 배열을 선언해야 합니다.
- 배열도 변수와 마찬가지로 자료형을 함께 선언합니다. 

```
자료형[] 배열이름 = new 자료형[개수];
자료형 배열이름[] = new 자료형[개수];
```
- 배열을 이루는 각각의 자료를 배열요소라고 합니다. 배열 요소는 자료형이 모두 같습니다.
- 먼저 저장하려는 자료의 성격에 맞게 자료형을 정하고 선언하려는 배열의 요소 개수만큼[] 안에 적습니다.
- new 예약어는 배열을 새로 만들라는 의미입니다.
- **배열형** 자료라는 의미로 변수 앞에 **자료형[]**을 사용하는 **자료형[] 변수이름 = new 자료형[개수];** 선언 방식이 조금 더 유의미합니다.(물론 두 방법 모두 올바른 사용방법입니다.)

- 상기 선언 방식을 사용해서 학생들의 학번을 배열로 선언하면
```
int[] studentIDs = new int[10]; // int형 요소가 10개인 배열 선언
```

- int형 요소가 10개인 배열을 선언한 것입니다. 이렇게 선언했을 때 메모리 상태를 그림으로 나태내면 다음과 같습니다.

![배열의 길이](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EB%B0%B0%EC%97%B4%EC%9D%98_%EA%B8%B8%EC%9D%B4.png)

- 배열을 선언하면 선언한 자료형과 배열 길이에 따라 메모리가 할당됩니다. 
- 위 그림을 보면 자료형이 int형이므로 배열 요소를 저장할 수 있는 공간의 크기는 전부 4바이트로 동일합니다.
- 배열 요소를 저장할 수 있는 공간이 총 10개이므로 이 배열을 위해 총 40바이트의 메모리가 할당되는 것입니다.

#### 배열 초기화하기
- 자바에서 배열을 선언하면 그와 동시에 각 요소의 값이 초기화됩니다. 
- 배열의 자료형에 따라 정수는 0, 실수는 0.0, 객체 배열은 null로 초기화 되며, 다음처름 배열 선언과 동시에 특정 값으로 초기화할 수도 있습니다. 
- 배열리 초기화 요소의 개수만큼 생성되므로 []안의 개수는 생략합니다.

```
int[] studentIDs = new int[]{101, 102, 103}; // 개수는 생략함
```

- 다음과 같이 값을 넣어 초기화할 때 []안에 개수를 쓰면 오류가 발생합니다.
```
int[] studentIDs = new int[3]{101, 102, 103}; // 오류 발생
```

- 선언과 동시에 초기화 할 때 다음과 같이 new int[]부분을 생략할 수도 있습니다. int형 요소 3개가 배열을 생성한다는 의미이므로 new int[]를 생략해도 됩니다.
```
int[] studentIDs = {102, 102, 103}; // int형 요소가 3개인 배열 생성
```

- 하지만 다음과 같이 배열의 자료형을 먼저 선언하고 초기화하는 경우에는 new int[]를 생략할 수 없습니다.
```
int[] studentIDs; // 배열 자료형 선언
studentIDs = new int[]{101, 102, 103}; // new int[]를 생략할 수 없음
```

### 배열 사용하기
- 선언한 배열의 각 요소에 값을 넣을 때나 배열 요소에 있는 값을 가져올 때는 []를 사용합니다.
- 만약 배열의 첫 번째 요소에 값 10을 저장한다면 다음처럼 코드를 작성합니다.
```
studentIDs[0] = 10;  // 배열의 첫 번째 요소에 값 10을 저장
```

#### 인덱스 연산자
- []는 배열을 처음 선언할 때 사용한 연산자입니다. 배열 이름에 []를 사용하는 것을 **인덱스 연산**이라고 합니다. 
- 인덱스 연산자의 기능은 **배열 요소가 저장된 메모리 위치를 찾아 주는 역할** 입니다.
- 변수 이름으로 변수가 저장된 메모리의 위치를 찾는 것처럼, 배열에서 [i] 인덱스 연산을 하면 i번째 요소의 위치를 찾아 해당 위치의 메모리에 값을 넣거나 이미 저장되어 있는 값을 가져와서 사용할 수 있습니다.
- 예를 들어 int형으로 선언한 num배열의 네 번째 요소에 값 25를 저장하고, 그 값을 가져와 int형 변수 age에 저장한다면 다음 그림과 같습니다.

![인덱스 연산자](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EC%9D%B8%EB%8D%B1%EC%8A%A4_%EC%97%B0%EC%82%B0%EC%9E%90.png)

>**배열의 물리적 위치와 논리적 위치는 같습니다.**<br>물리적(physical)위치란 배열이 메모리에서 실제 저장되는 곳을 의미하며, 논리적(logical) 위치란 이론상 배열 위치를 의미합니다. 배열은 요소 10개를 선언하면 사용하는 실제 값도 바로 이웃한 메모리에 놓입니다. 즉 '5 다음에 10이 있다'는 논리적 순서와 실제 메모리를 살펴보면 **값 5가 놓인 메모리 주소에서 4바이트(int형 크기) 다음 메모리 주소에 값 10이 놓입니다.**


#### 배열의 순서는 0번 부터
- 배열 길이(처음에 선언한 배열 전체 요소 개수)가 n이라고 하면, 배열 순서는 0번부터 n-1번까지 입니다.
- 0번 요소를 배열의 첫 번째 요소라고 합니다.

#### day04/array/arrayTest.java
```
package day04.array;

public class ArrayTest {
	public static void main(String[] args) {
		int[] num = new int[] {1,2,3,4,5,6,7,8,9,10};
		
		for(int i = 0; i < num.length; i++) {
			System.out.println(num[i]);
		}
	}
}

실행결과
1
2
3
4
5
6
7
8
9
10
```

- int[] num = new int[] {1,2,3,4,5,6,7,8,9,10} :  int형 num을 선언하고 1부터 10까지의 값으로 초기화하였다.

![배열 선언 및 초기화](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EB%B0%B0%EC%97%B4_%EC%84%A0%EC%96%B8_%EC%B4%88%EA%B8%B0%ED%99%94.png)

- 배열 요소를 하나씩 가져와 출력하기 위해 for 반복문을 사용했습니다. 배열의 첫 번째 요소 **인덱스는 0부터 시작**합니다.

>**0부터 9까지 반복이면 조건식을 i<=9로 쓰는 게 더 좋은것이 아닌가?**<br> i<=9 보다는 i < 10으로 하는것이 더 직관적입니다. 왜냐하면 10이 배열의 길이이므로 10으로 쓰는 것이 훨씬 직관적이기 떄문입니다. 배열 객체의 속성인 length를 사용하면 배열의  길이를 쉽게 알 수 있습니다.

- 자바의 배열은 배열의 길이를 나타내는 **length 속성**을 가집니다. 
- 자바에서 배열 길이는 처음에 선언한 배열의 전체 요소 개수를 의미합니다. 전체 길이를 알고 싶은 배열 이름 뒤에 마침표(.) 연산자를 붙이고 length 속성을 쓰면 배열 길이를 반환합니다. 
- for문의 조건에서 얼만큼 반복할지 결정해야 하는데, 배열 요소 끝까지 반복하기 위해 배열 전체 길이(length)를 넣습니다, 따라서 num.length 값은 10이 됩니다.
- 이렇게 배열 전체 길이만큼 수행문을 반복해야 할 때는 숫자를 직접 사용하는 것보다 **length 속성**을 사용하는 것이 좋습니다.



#### 전체 배열 길이와 유효한 값 
우리가 배열을 사용할 때 처음 선언한 배열 길이만큼 값을 저장해서 사용하는 경우는 많지 않습니다. 따라서 **전체 배열의 길이와 현재 배열에 유효한 값이 저장되어 있는 배열 요소의 개수가 같다고 혼동하면 안된다.**

#### day04/array/ArrayTest2.java
```
package day04.array;

public class ArrayTest2 {
	public static void main(String[] args) {
		double[] data = new double[5];
		
		data[0] = 10.0;  // 첫 번째 요소에 값 10.0 대입
		data[1] = 20.0;  // 두 번째 요소에 값 20.0 대입 
		data[2] = 30.0; // 세 번째 요소에 값 30.0 대입
		
		for(int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}

실행결과
10.0
20.0
30.0
0.0
0.0
```

- 자바에서 정수 배열과 실수 배열을 별도로 초기화하지 않고 선언하면 배열의 요소 값은 0으로 초기화됩니다.
- for문에서 i가 0부터 배열 길이인 data.length 미만까지 반복하여 배열에 저장된 요소 값을 출력합니다.
- 배열의 네 번째 요소와 다섯 번째 요소에는 값을 저장하지 않았기 때문에 0이 출력되는 것을 알 수 있습니다.
- 즉, 배열의 세 번째 요소까지만 유효한 값이 저장되었습니다.

#### day04/array/ArrayTest3.java
```
package day04.array;

public class ArrayTest3 {
	public static void main(String[] args) {
		double[] data = new double[5];
		int size = 0; //유효한 값이 지정된 배열 요소 개수를 저장하 변수 선언
		
		//  값을 저장한 후 size 변수 값 증가
		data[0] = 10.0; size++; 
		data[1] = 20.0; size++;
		data[2] = 30.0; size++; 
		
		// 유효한 값이 저장된 배열 요소 개수만큼 반복문 실행
		for(int i = 0; i < size; i++) {
			System.out.println(data[i]);
		}
	}
}

실행결과
10.0
20.0
30.0
```
- 유효한 값이 저장된 배열 요소 개수를 저장할 size 변수를 선언했습니다. 
- 배열 요소에 순서대로 값을 저장할 때마다 size 변수의 값을 하나씩 증가시킵니다. 
- 즉, 유효한 값을 저장하고 있는 배열 요소의 개수를 알 수 있습니다.


### 문자 저장 배열 만들기
문자 자료형 배열을 만들고 알파벳 대문자 A부터 Z까지 저장한 후 각 요소 값을 알파벳 문자와 정수 값(아스키 코드 값)으로 출력해 보겠습니다. 문자 자료형 배열은 char[]로 선언해야 합니다.

#### day04/array/CharArray.java
```
package day04.array;

public class CharArray {
	public static void main(String[] args) {
		char[] alphabets = new char[26];
		char ch = 'A';
		
		for(int i = 0; i < alphabets.length; i++, ch++) {
			alphabets[i] = ch; // 아스키 값으로 각 요소에 저장
		}
		
		for(int i = 0; i < alphabets.length; i++) {
			System.out.println(alphabets[i] + "," + (int)alphabets[i]);
		}
	}
}


실행결과
A,65
B,66
C,67
D,68
E,69
F,70
G,71
H,72
I,73
J,74
K,75
L,76
M,77
N,78
O,79
P,80
Q,81
R,82
S,83
T,84
U,85
V,86
W,87
X,88
Y,89
Z,90
```

### 객체 배열 사용하기
- 동일한 기본 자료형(int 등)변수 여러 개를 배열로 사용할 수 있듯이 참조 자료형 변수도 여러 개를 배열로 사용할 수 있습니다.
- 객체배열은 int나 char등 기본 자료형 배열과 사용방법이 조금 다릅니다.

#### day04/array/Book.java
```
package day04.array;

public class Book {
	private String bookName;
	private String author;
	
	public Book() {} // 디폴트 생성자
	
	public Book(String bookName, String author) {
		this.bookName = bookName;
		this.author = author;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void showBookInfo() {
		System.out.println(bookName + "," + author);
	}
}
```

Book 클래스는 책 이름과 저자를 멤버 변수로 가지는 클래스 입니다 .디폴드 생성자 외에도 책 이름과 저자 이름을 받는 생성자를 하나 더 구현했습니다.


#### day04/array/BookArray.java
```
package day04.array;

public class BookArray {
	public static void main(String[] args) {
		Book[] library = new Book[5];  // Book 클래스형으로 객체 배열 생성
		
		for (int i = 0; i < library.length; i++) {
			System.out.println(library[i]);
		}
	}
}

실행결과
null
null
null
null
null
```

- Book[] library = new Book[5]; 문장을 보면 Book 인스턴스 5개가 생성된 것처럼 보이나 Book 인스턴스 5개가 바로 생성되는 것은 아닙니다. 이때 만들어 지는 것은 인스턴스 주소를 담을 공간 입니다. 
- Book[] library = new Book[5];는 각각의 Book인스턴스 주소 값을 담을 공간 5개를 생성하는 문장입니다.
- Book 주소값을 담을 공간이 5개 만들어 지고 자동으로 각 공간은 **비어 있다**는 의미의 null 값으로 초기화됩니다.

![객체배열 초기화](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EA%B0%9D%EC%B2%B4%EB%B0%B0%EC%97%B4_%EC%B4%88%EA%B8%B0%ED%99%94.png)
 
#### day04/array/BookArray2.java
```
package day04.array;

public class BookArray2 {
	public static void main(String[] args) {
		Book[] library = new Book[5];
		library[0] = new Book("태백산맥", "조정래");
		library[1] = new Book("데미안", "헤르만 헤세");
		library[2] = new Book("어떻게 살 것인가", "유시민");
		library[3] = new Book("토지", "박경리");
		library[4] = new Book("어린왕자", "생텍쥐페리");
		
		for (int i = 0; i < library.length; i++) {
			library[i].showBookInfo();
		}
		
		for (int i = 0; i < library.length; i++) {
			System.out.println(library[i]);
		}
	}
}

실행결과
태백산맥,조정래
데미안,헤르만 헤세
어떻게 살 것인가,유시민
토지,박경리
어린왕자,생텍쥐페리
day04.array.Book@5e91993f
day04.array.Book@1c4af82c
day04.array.Book@379619aa
day04.array.Book@cac736f
day04.array.Book@5e265ba4
```
 
 
### 배열 복사하기
배열을 복사하는 방법은 두 가지 있습니다.
1. 기존 배열과 배열 길이가 같거나 더 긴 배열을 만들고 for문을 사용하여 각 요소 값을 반복해서 복사하는 방법
2. System.arraycopy() 메서드를 사용하는 방법

#### System.arraycopy(src, srcPos, dest, destPos, length)

|매개변수|설명|
|-----|--------|
|src|복사할 배열 이름|
|srcPos|복사할 배열의 첫 번째 위치|
|dest|복사해서 붙여 넣을 대상 배열 이름|
|destPos|복사해서 대상 배열에 붙여 넣기를 시작할 첫 번째 위치|
|length|src에서 dest로 자료를 복사할 요소 개수|

#### day04/array/ArrayCopy.java
```
package day04.array;

public class ArrayCopy {
	public static void main(String[] args) {
		int[] array1 = {10, 20, 30, 40, 50};
		int[] array2 = {1,2,3,4,5};
		
		System.arraycopy(array1, 0, array2, 1, 4);
		for(int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
		}
	}
}

실행결과
1
10
20
30
40

```

#### 객체 배열 복사하기
#### day04/array/ObjectCopy1.java
```
package day04.array;

public class ObjectCopy1 {
	public static void main(String[] args) {
		Book[] bookArray1 = new Book[3];
		Book[] bookArray2 = new Book[3];
		
		bookArray1[0] = new Book("태백산맥", "조정래");
		bookArray1[1] = new Book("데미안", "헤르만 헤세");
		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
		System.arraycopy(bookArray1, 0, bookArray2, 0, 3);
		
		for (int i = 0; i < bookArray2.length; i++) {
			bookArray2[i].showBookInfo();
		}
	}
}

실행결과
태백산맥,조정래
데미안,헤르만 헤세
어떻게 살 것인가,유시민
```
- 위 예제 코드의 출력 결과를 보면 bookArray1 배열에서 bookArray2 배열로 요소 값이 잘 복사된 것을 알 수 있습니다. 
- 한가지 의문점은 bookArray2 배열의 인스턴스를 따로 만들지 않았는데, 각 요소 값이 잘 출력되고 있습니다. 
- 객체 배열을 사용하려면 꼭 인스턴스를 생생해서 넣어야 하는데, 예제코드는 의문점이 생길 수 있습니다.

#### 얕은 복사
#### day04/array/ObjectCopy2.java
```
package day04.array;

public class ObjectCopy2 {
	public static void main(String[] args) {
		Book[] bookArray1 = new Book[3];
		Book[] bookArray2 = new Book[3];
		
		bookArray1[0] = new Book("태백산맥", "조정래");
		bookArray1[1] = new Book("데미안", "헤르만 헤세");
		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
		System.arraycopy(bookArray1, 0, bookArray2, 0, 3);
		
		for (int i = 0; i < bookArray2.length; i++) {
			bookArray2[i].showBookInfo();
		}
		
		// bookArray1 배열의 첫 번째 요소 값 변경
		bookArray1[0].setBookName("나목");
		bookArray1[0].setAuthor("박완서");
		
		System.out.println("=== bookArray1 ===");
		for (int i = 0; i < bookArray1.length; i++) {
			bookArray1[i].showBookInfo();
		}
		
		System.out.println("=== bookArray2 ===");
		for (int i = 0; i < bookArray2.length; i++) {
			bookArray2[i].showBookInfo(); // bookArray2 배열 요소의 값도 변경되어 출력
		}
	}
}

실행결과
태백산맥,조정래
데미안,헤르만 헤세
어떻게 살 것인가,유시민
=== bookArray1 ===
나목,박완서
데미안,헤르만 헤세
어떻게 살 것인가,유시민
=== bookArray2 ===
나목,박완서
데미안,헤르만 헤세
어떻게 살 것인가,유시민
```
- bookArray1 배열 요소 값을 변경했는데 bookArray2배열 요소 값도 변경된 것을 알 수 있습니다.
- 그 이유는 객체 배열의 요소에 저장된 값은 인스턴스 자체가 아니고 인스턴스의 주소 값이기 때문입니다.
- 객체 배열을 복사할 때 인스턴스를 따로 생성하는 게 아니라 기존 인스턴스의 주소 값만 복사합ㄴ디ㅏ.
- 결국 두 배열의 서로 다른 요소가 같은 인스턴스를 가리키고 있으므로 복사되는 배열의 인스턴스 값이 변경되면 두 배열 모두 영향을 받는 것입니다.

![얕은복사](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EC%96%95%EC%9D%80%EB%B3%B5%EC%82%AC.png)


- 이와 같은 복사를 주소 값만 복사한다고 해서 **얕은 복사(sallow copy)**라고 합니다.

#### 깊은 복사
- 반복문을 사용하건 System.arraycopy() 메서드를 사용하건 객체 배열을 복사하면 항상 인스턴스의 주소가 복사됩니다.
- 대부분의 경우는 이렇게 해도 문제가 없지만, 인스턴스를 따로 관리하고 싶다면 직접 인스턴스를 만들고 그 값을 복사해야 합니다. 이를 **깊은 복사(deep copy)**라고 합니다.

#### day04/array/ObjectCopy3.java
```
package day04.array;

public class ObjectCopy3 {
	public static void main(String[] args) {
		Book[] bookArray1 = new Book[3];
		Book[] bookArray2 = new Book[3];
		
		bookArray1[0] = new Book("태백산맥", "조정래");
		bookArray1[1] = new Book("데미안", "헤르만 헤세");
		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
		System.arraycopy(bookArray1, 0, bookArray2, 0, 3);
		
		bookArray2[0] = new Book();
		bookArray2[1] = new Book();
		bookArray2[2] = new Book();
		
		// bookArray1 배열 요소를 새로 생성한 bookArray2 배열 인스턴스에 복사
		for (int i = 0; i < bookArray1.length; i++) {
			bookArray2[i].setBookName(bookArray1[i].getBookName());
			bookArray2[i].setAuthor(bookArray1[i].getAuthor());
		}
		
		for (int i = 0; i < bookArray2.length; i++) {
			bookArray2[i].showBookInfo();
		}
		
		// bookArray1 배열의 첫 번째 요소 값 변경
		bookArray1[0].setBookName("나목");
		bookArray1[0].setAuthor("박완서");
				
		System.out.println("=== bookArray1 ===");
		for (int i = 0; i < bookArray1.length; i++) {
			bookArray1[i].showBookInfo();
		}
				
		System.out.println("=== bookArray2 ===");
		for (int i = 0; i < bookArray2.length; i++) {
			bookArray2[i].showBookInfo(); // bookArray2 배열 요소의 값도 변경되어 출
		}
	}
}

실행결과
태백산맥,조정래
데미안,헤르만 헤세
어떻게 살 것인가,유시민
=== bookArray1 ===
나목,박완서
데미안,헤르만 헤세
어떻게 살 것인가,유시민
=== bookArray2 ===
태백산맥,조정래
데미안,헤르만 헤세
어떻게 살 것인가,유시민
```

![깊은 복사](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/%EA%B9%8A%EC%9D%80%EB%B3%B5%EC%82%AC.png)


### 향상된 for문과 배열
- JDK1.5부터 제공되는 향상된 for문(enhanced for loop)은 배열의 처음부터 끝까지 모든 요소를 참조할 때 사용하는 편리한 반복문 입니다.
- 향상된 for문은 배열 요소 값을 순서대로 하나씩 가져와서 변수에 대입합니다.
- 따로 초기화와 종료 조건이 없기 때문에 모든 **배열의 시작 요소부터 끝 요소까지 실행**합니다.

```
for(변수 : 배열) {
	반복 실행문;
}
```

#### day04/array/EnhancedForLoop.java
```
package day04.array;

public class EnhancedForLoop {
	public static void main(String[] args) {
		String[] strArray = {"Java", "Android", "C", "JavaScript", "Python"};
		
		for(String lang : strArray) {
			System.out.println(lang);
		}
	}
}

실행결과
Java
Android
C
JavaScript
Python
```

## 다차원배열
- 이차원 이상으로 구현한 배열을 **다차원 배열**
- 다차원 배열은 평면이나 공간 개념을 구현하는 데 사용합니다.

### 이차원 배열
- 다음은 2행 3열의 이차원 배열을 선언하는 코드의 논리적 구조입니다.

![2차원 배열](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B41.png)

- 배열의 모든 요소를 참조하려면 각 행을 기준으로 열 값을 순회하면 됩니다. 
-이차원 배열을 초기화하려면 다음처럼 행과 열 개수에 맞추어서 중괄호 {}안에 콤마(,)로 구분해서 값을 적습니다. 
- 이렇게 이차원 배열을 초기화하면 괄호 안에 적은 6개의 값이 순서대로 arr 배열의 각 요소에 저장됩니다.

![2차원 배열](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B42.png)

#### day04/array/TwoDimension.java
```
package day04.array;

public class TwoDimension {
	public static void main(String[] args) {
		int[][] arr = {{1,2,3}, {4,5,6}}; // 2차원 배열 선언과 동시에 초기화
		
		for(int i = 0; i < arr.length; i++) { // 행 
			for(int j = 0; j < arr[i].length; j++) { // 열
				System.out.println(arr[i][j]);
			}
			System.out.println();
		}
	}
}

실행결과
1
2
3

4
5
6
```

- 중첩 for문은 배열 인덱스용으로 i, j 두 변수를 사용하는데 i는 행을. j는 열을 가리킵니다. 전체 배열 길이인 arr.length는 행의 개수를 각 행의 길이 arr[i].length는 열의 개수를 나타냅니다.

![2차원 배열](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%B0%B0%EC%97%B4/images/2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B43.png)

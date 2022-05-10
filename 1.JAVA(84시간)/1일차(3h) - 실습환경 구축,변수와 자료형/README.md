# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1rA1I9xadimigr0uJKLrzoMbhpdZ_VRDH?usp=sharing)

# 실습환경 구축하기

## 자바 설치하기
- [JDK 다운로드](https://www.oracle.com/java/technologies/downloads/)
- [JDK 문서 다운로드](https://www.oracle.com/java/technologies/javase-jdk18-doc-downloads.html)

### 환경변수 설정하기
![환경변수 설정하기1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B01.png)

![환경변수 설정하기2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B02.png)

![환경변수 설정하기3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B03.png)


- 시스템 변수나 사용자변수에 하기 변수값을 입력하기
- **JAVA_HOME**에 C:\Program Files\Java\jdk-17.0.2 -- JDK가 설치된 경로 입력
- **CLASSPATH**에  %JAVA_HOME%\lib;. 입력 

>시스템 변수는 모든 사용자에게 적용되는 변수이며, 사용자 변수는 현재 사용자에게만 적용되는 변수로써 동작합니다.

## 이클립스 설치하기
- [이클립스 다운로드](https://www.eclipse.org/downloads/packages/)
>이클립스는 인스톨러로 설치하기보다는 zip파일로 압축된 무설치판으로 이용하는것이 편리하다.

* * * 

# 변수와 자료형

## 컴퓨터는 데이터를 어떻게 표현할까?
- 우리가 사용하는 모든 데이터는 컴푸터 내부에서 0과 1로 이루어져 있습니다.
- 컴퓨터 내부를 구성하는 반도체가 데이터를 0과 1로만 표현할 수 있기 때문입니다.
- 집에서 사용하는 전구처럼 불이 켜지는 경우와 꺼지는 경우 두 가지 밖에 없다고 생각하면 됩니다.
- 이렇게 0또는 1로 표현할 수 있는 최소 단위를 **비트**(bit)라고 하며, **8비트**가 모이면 **1바이트**(byte)가 됩니다.

### 10진수와 2진수
- 우리가 일상 생활에서 사용하는 숫자는 0부터 9까지 한 자릿수 이고 10부터는 두 자리수가 됩니다. 이를 10진수라고 합니다.
- 컴퓨터 내부에서는 모든 데이터를 0과 1로 표현해야 합니다. 이때 사용하는 것이 바로 2진수 입니다. 
- 2진수란 0과 1 두 개로만 표현되는 수를 말합니다. 
- 0부터 10까지 10진수를 2진수로 표현하면 다음과 같습니다.

![10진수와 2진수](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/10%EC%A7%84%EC%88%98%EC%99%80_2%EC%A7%84%EC%88%98.png)

- 컴퓨터 내부에서는 숫자뿐 아니라 문자도 2진수로 표현합니다. 예를 들면 A라는 문자가 있을때 A는 숫자 65로 표현하도록 약속되어 있습니다.
- 따라서 컴퓨터 내부에서는 A는 2진수 1000001으로 표현됩니다. 이 값을 A문자의 아스키(ASCII)값이라고 합니다. 
- 모든 문자는 컴퓨터가 이해할 수 있는 아스키값이 미리 정해져 있습니다.

>아스키(ASCII)란 American Standard Code for Information Interchange의 약어로 미국 표준협회(ANSI)가 제정한 규칙입니다. 영문자, 숫자, 특수 문자를 8비트 값의 수로 미리 정의해 놓았습니다.


### 2진수, 16진수, 8진수
- 자바 프로그램에서는 2진수, 8진수, 16진수를 사용할 수 있습니다.
- 2진수는 비트 값을 그대로 표현할 수 있지만 길이가 너무 길어서 불편하므로 8진수나 16진수로 바꿔서 사용하면 간단하게 표현할 수 있습니다.
- 8진수를 2진수로 표현하기 위해서는 3개의 비트가 필요하고 16진수는 4개의 비트가 필요합니다. 

![2진수 16진수 8진수](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/2%EC%A7%84%EC%88%98_16%EC%A7%84%EC%88%98_8%EC%A7%84%EC%88%98.png)

- 8진수는 2진수 3비트를, 16진수는 4비트를 합쳐서 간단하게 표현할 수 있습니다.
- 프로그램에서 **2진수**를 사용할땐 숫자 앞에 **0B**를 붙이고 **8진수**를 사용할 때는 **0**, **16진수**를 사용할 때는 **0X**를 붙입니다. 소문자 b와 x도 사용할 수 있습니다.
- 예) 10진수 10 - 2진수 - 0B1010, 8진수 - 012, 16진수 - 0XA

### 부호있는 수를 표현하는 방법 
- 컴퓨터는 숫자를 0과 1로만 표현할 수 있기 때문에 부호 또한 0과 1로 표현합니다.
- 부호를 나타내는 비트는 맨 앞에 붙이며 **부호 비트**라고 부릅니다.
- 부호 비트가 0이면 양수, 1이면 음수를 나타냅니다. 
- 8비트로 5라는 숫자를 표현하면 다음과 같다.

![부호비트](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%EB%B6%80%ED%98%B8%EB%B9%84%ED%8A%B8.png)


## 변수란 무엇일까?

- 프로그래밍에서는 변사는 값을 **변수**라고 합니다.
- **변수**는 말 그대로 **변하는 수** 입니다.

### 변수 선언하고 값 대입하기
- 어떤 값을 저장하려면 공간이 필요합니다. 이 **공간의 이름**이 바로 **변수**입니다.
- 변수를 사용하기 위해 어떤 형태의 자료를 저장할 것인지 정해야 합니다. 
- 사람의 나이를 저장하려면 정수형태를 써야 하고, 이름을 저장하려면 문자형태를 써야하는데, 이러한 형태를 변수의 **자료형**이라고 합니다.
- 변수의 자료형을 선택했다면 변수의 이름도 정해주어야 합니다.
- **변수를 선언한다** : 변수의 자료형을 선택하고 이름을 정하는 것

![변수 선언 및 대입](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%EB%B3%80%EC%88%98%EC%84%A0%EC%96%B8_%EB%8C%80%EC%9E%85.png)

- '='기호는 수학에서 오른쪽과 왼쪽이 같다는 의미이지만 프로그램에서는 **오른쪽 값을 왼쪽에 대입한다**는 의미로 사용합니다.
- 상기 코드의 두 문장을 해석하면 **level**이라는 이름의 변수를 **정수 자료형**으로 선언한다. 선언한 level 변수에 값 **10을 넣는다**(**대입한다**)입니다.


#### day01/Variable1.java
```
package day01;

public class Variable1 {
	public static void main(String[] args) {
		int level;  // 정수형 변수 level을 선언
		level = 10; // level 변수에 값 10을 대입 
		System.out.println(level);  // level 값 출력
	}
}

실행결과
10
```
>package는 소스 코드의 묶음을 나타냅니다. '5~6일차(9h) - 객체지향 프로그래밍1에서 설명합니다.'

### 변수 초기화하기 
- 변수에 **처음 값을 대입하는 것**을 **초기화**라고 합니다.
- 반드시 변수 선언과 동시에 초기화를 해야 하는 것은 아닙니다. **변수 선언과 동시에 초기화**를 할 수도 있고 **변수를 선언한 이후에 대입할 값이 정해지는 시점에 초기화**를 할 수도 있습니다.

### 변수 이름 정하기
자바에서 변수 이름은 용도에 맞게 지으면 됩니다. 다만 다음과 같은 제약사항이 있습니다.

|제약 사항|예시|
|-------|----|
|변수 이름은 영문자(대문자, 소문자)나 숫자를 사용할 수 있고, 특수 문자 중에서는 $, \_만 사용할 수 있습니다.|g_level(O),count100(O), \_master(O), $won(O)|
|변수 이름은 수자로 시작할 수 없습니다.|27day(X), 1abc(X)|
|자바에서 이미 사용 중인 예약어는 사용할 수 없습니다.|while, int, break, ...|

>예약어(reserved word)f란 프로그래밍 언어에서 특별한 의미로 미리 약속되어 있는 단어를 말합니다.

- 변수 이름은 프로그램에서 계속 사용하기 때문에 사용 목적에 맞게 의미를 잘 부여해서 만드는 것이 좋습니다.
- 예) 학생수 - noOfStudent
- 변수명 관례 - 카멜케이스 : 첫 단어를 제외하고 단어의 시작은 대문자로 표기

## 변수가 저장되는 공간의 특성, 자료형

### 변수와 메모리
- 변수는 컴퓨터 내부의 메모리 공간에 저장됩니다.
- 메모리는 프로그램이 실행되는 작업 공간입니다.
- 예를 들어 int level; 문장을 선언하면 메모리에 4바이트(int 형)크기의 공간이 level이라는 이름으로 할당됩니다. 앞으로 이 메모리를 변수 level로 사용하겠다는 뜻 입니다.
- 즉, 변수를 선언한다는 것은 선언한 변수의 이름으로 어떤 위치에 있는 메모리를 얼마만큼의 크기로 사용하겠다는 뜻입니다.

### 기본 자료형의 종류
- 자바에서 제공하는 저료형은 기본 자료형과 참조 자료형이 있습니다.
- 기본 자료형은 자바 라이브러리에서 기본으로 제공하며. 얼마만큼의 메모리를 어떻게 사용할 것인지가 이미 정해져 있습니다.
- 기본 자료형은 각 자료형이 사용할 공간의 크기, 즉 바이트 수가 정해져 있습니다.

||정수형|문자형|실수형|논리형|
|----|----|----|----|----|
|1바이트|byte|||boolean|
|2바이트|short|char|||
|4바이트|int||float||
|8바이트|long||double||

### 정수 자료형
- 정수 자료형은 양수, 음수, 0을 나타내는 데 사용하는 자료형입니다. 
- byte형, short형, int형, long형 4가지 자료형으로 나타낼 수 있습니다. 각 자료형은 메모리에서 사용하는 바이트 수와 용도가 다릅니다.

- 컴퓨터 내부에서 모든 정보는 0과 1로 표현됩니다. 예를 들어 정수 자료형 중 가장 많이 사용하는 int형(4바이트, 32비트)에 10진수 10을 저장한다면 다음과 같습니다.

![정수자료형](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%EC%A0%95%EC%88%98%EC%9E%90%EB%A3%8C%ED%98%95.png)

- 부호가 있는 수를 표현할 때 맨 앞의 비트는 부호를 나타냅니다. 
- 부호 비트가 0이면 양수, 1이면 음수 입니다. 위에서 10은 양수이므로 맨 앞의 비트 값이 0입니다. int는 4바이트이므로 나타낼 수 있는 수의 범위는 맨 앞의 부호비트를 제외하고 생각하면 -2^31~2^31-1입니다. 0은 모든 비트가 0이므로 양수의 범위는 0을 뺀 2^31-1까지 되는 것입니다.

#### 정수자료형이 표현할 수 있는 수의 유효범위

|자료형|바이트 크기|수의 범위|
|-----|-----|-----|
|byte|1|-2^7~2^7-1|
|short|2|-2^15~2^15-1|
|int|4|-2^31~2^31-1|
|long|8|-2^63~2^63-1|

#### byte형
1바이트는 8비트 입니다. 바이트 단위의 정보를 저장하거나 통신할 때 주로 사용합니다.

#### short형 
2바이트로 정수를 표현하는 자료형입니다. 

#### int형
- 정수를 표현할 때 가장 많이 사용하는 자료형입니다. 
- 정수 자료형에서 int형을 가장 많이 사용하는 이유는 컴픁너에서 정수 연산을 할 때 4바이트 단위로 처리하는 것이 가장 효율적이기 때문이다.	
> 자바에서는 연산을 할떄 정수형은 int, 실수형은 double를 기본 단위로 사용한 후에 선언된 자료형에 맞게 형변환을 한다. 따라서 형변환이 발생하지 않는 int와 double이 가장 효율적인 자료형이다.

#### long형
- 자바에서 정수를 표현하는 가장 큰 단위의 자료형 입니다.
- int 형을 넘어서는 정수를 사용할 때 long형을 사용합니다. 그런데 long형을 사용할 때는 주의점이 있습니다.
```
int num1 = 12345678900;
long num2 = 12345678900;
```
- 위와 같이 선언하면 두 문장이 모두 오류가 납니다. 첫 번째 문장은 int형으로 표현할 수 있는 범위를 넘어섰기 때문에 오류가 발생합니다.
- 두번째 문장역시 오류가 발생하는데 **자바에서는 모든 정수 값을 기본으로 int형으로 처리**하기 때문입니다. 즉, 숫자 12345678900을 int로 처리하기 때문입니다.
- 이런 경우는 이 숫자를 long형으로 처리하라고 컴파일러에게 알려주어야 합니다. 그러기 위해서는 long형을 나타내는 식별자인 L이나 l을 사용하려는 숫자 뒤에 붙입니다.
```
long num2 = 12345678900L;
```

### 문자 자료형
- 컴퓨터는 0과 1로만 표현할 수 있다고 했으므로 문자역시 컴퓨터 내부에서 표현할 때 0과 1의 조합으로 나타내야 합니다.
- 따라서 어떤 문자를 컴퓨터 내부에서 표현하려면 정수 값으로 정하고자 약속합니ㅏㄷ.
- 예를 들어 A를 얼마로 표현할 것인지 약속하는데, 이런 코드 값을 모아 둔 것을 **문자세트**라고 하고 문자를 정해진 코드 값으로 변환하는 것을 **문자 인코딩**(encoding)이라고 합니다.
- 반대로 코드 값을 다시 문자로 변환하는 것을 **문자 디코딩(decoding)**이라고 합니다.

- 가장 기본이 되는 문자인코딩은 아스키(ASCII)코드 입니다. 아스키 코드는 영문자, 숫자, 특수 문자 등을 나타내는 문자 세트 입니다. 영문자는 대문자, 소문자, 특수문자, 기호를 포함해도 1바이트(2^8 = 256개)로 표현할 수 있기 때문에 아스키 코드는 1바이트만 사용합니다. 하지만 한글 등 다른 언어 문자는 복잡하고 다양하기 때문에 1바이트만으로 모든 문자를 표현하기 어렵습니다. 그래서 2바이트 이상을 사용하게 되었는데, 이때 각 언어의 표준인코딩을 정의해 놓은 것이 '**유니코드**(unicode)'입니다. 유니코드의 1바이트는 아스키 코드 값과 호환되고, 그 밖의 문자를 2바이트나 그 이상의 조합으로 표현합니다. 
- 자바는 유니코드에 기반하여 문자를 표현하기 때문에, 자바의 문자 자료형인 **char형은 2바이트를 사용**합니다.
- 문자형 변수는 다음과 같이 선언합니다. 
```
char myChar = 'A';
```

#### day01/CharacterEx1.java
```
package day01;

public class CharacterEx1 {
	public static void main(String[] args) {
		char ch1 = 'A';
		System.out.println(ch1); // 문자 출력
		System.out.println((int)ch1); // 문자에 해당하는 정수 값(아스키 코드 값)출력 
		
		char ch2 = 66; // 정수값 대입
		System.out.println(ch2); // 정수 값에 해당하는 문자 출력
		
		int ch3 = 67;
		System.out.println(ch3); // 문자 정수 값 출력
		System.out.println((char)ch3); // 정수 값에 해당하는 문자 출력
		
	}
}

실행결과

A
65
B
67
C
```

- 프로그램에서 **문자**를 사용할 때는 항상 **작은따옴표('')**를 사용합니다. 
- 문자를 여러 개 이은 문자열을 사용할 때는 **큰 따옴표("")**를 사용합니다. 문자열은 "Hello"처럼 여러 문자를 큰 따옴표로 감싸 표현하고 기본자료형으로는 표현할 수 없습니다. 
- 자바에서 문자열을 다룰 때 **String 클래스**를 사용합니다.

#### day01/CharacterEx2.java
```
package day01;

public class CharacterEx2 {
	public static void main(String[] args) {
		char ch1 = '한';
		char ch2 = '\uD55C';
		
		System.out.println(ch1);
		System.out.println(ch2);
	}
}

실행결과

한
한
```

### 문자형 변수에 숫자를 저장한다면
- char형은 문자 자료형이지만 다른 자료형과 마찬가지로 컴퓨터 내부에서는 정수 값으로 표현되기 때문에 정수 자료형으로 분류하는 경우도 있습니다.
- 다른 정수 자료형과 차이점은 **char형은 음수값을 표현할 수 없다는 것**입니다.

#### day01/CharacterEx3.java
```
package day01;

public class CharacterEx3 {
	public static void main(String[] args) {
		int a = 65;
		int b = -66;
		
		char a2 = 65;
		// 문자형 변수에 음수를 넣으면 오류가 발생하므로 주석처리함
		//char b2 = -66; 
		
		System.out.println((char)a);
		System.out.println((char)b);
		System.out.println(a2);
	}
}

실행결과
A
?
A
```
> 자바의 기본 인코딩 방식은 모든 문자를 2바이트로 표현하는 UTF-16입니다.

### 실수 자료형
- 0과 1 사이에는 무한개의 실수가 있습니다. 이 무한개의 실수를 모두 표현하는 데 정수를 표현하는 방식은 한계가 있습니다.
- 컴퓨터에서는 실수는 정수와는 조금 다른 방식으로 표현해야 합니다.

#### 부동소수점 방식 
실수값 0.1은 1.0\*10^-1으로도 표현할 수 있습니다. 이처럼 가수부분(1.0)과 지수부분(-1)을 나누어서 실수를 나타내는 방식을 **부동 소수점 방식**이라고 합니다. 이 방식을 사용하면 더 많은 실수를 좀 더 세밀하게 표현할 수 있습니다.

#### float형과 double형
- 실수 자료형에는 float형과  double 형이 있습니다. 
- float형은 부호 1비트, 지수부 8비트, 가수부 23비트로 총 32비트(4바이트)를 사욜합니다.
- double형은 부호1비트, 지수부 11비트, 가수부 52비트로 총 64비트(8바이트)를 사용합니다.
- 자바에서 실수는 **double형을 기본으로 사용**합니다. float형(4바이트)에 비해 double형(8바이트)이 더 정밀하게 실수를 표현할 수 있습니다.

#### day01/DoubleEx1.java
```
package day01;

public class DoubleEx1 {
	public static void main(String[] args) {
		double dnum = 3.14;
		float fnum = 3.14F; //  식별자 F 추가
		
		System.out.println(dnum);
		System.out.println(fnum);
	}
}


실행결과
3.14
3.14
```
- float로 대입되는 값 3.14는 double형이 아닌 float형 값이 대입된다는 의미로 F또는 f를 숫자 뒤에 붙여서 식별해주어야 합니다.

#### 부동 소점 방식의 오류
지수와 가수부로 나태내는 부동 소수점 방식은 지수로 표현되는 값이 0을 나타낼 수 없습니다. 따라서 부동 소수점 값을 연산하면 약간의 오차가 발생할 수 있습니다.

#### day01/DoubleEx2.java
```
package day01;

public class DoubleEx2 {
	public static void main(String[] args) {
		double dnum = 1;
		
		for(int i = 0; i < 10000; i++) {
			dnum = dnum + 0.1;
		}
		System.out.println(dnum);
	}
}

실행결과
1001.000000000159
```
- 약간의 오차를 감수하고더라도 더 넓은 범위의 실수 값을 표현하기 위해 부동 소수점 방식을 사용합니다.

### 논리자료형 
- 논리자료형은 어떤 변수의 참, 거짓의 값을 나타내는 데 사용합니다.
- 종류는 boolean 한 가지 뿐입니다.
- boolean형 변수는 1바이트로 값을 저장하며, true(참), false(거짓) 두 가지 값만 가집니다.

```
boolean isMarried;
```

#### day01/BooleanEx.java
```
package day01;

public class BooleanEx {
	public static void main(String[] args) {
		boolean isMarried = true; // boolean 변수를 선언하고 초기화
		System.out.println(isMarried);
	}
}

실행결과
true

```


## 상수와 리터럴

### 상수 선언하기
- 프로그램에서는 변하지 않는 수도 필요합니다. 예를 들면 원의 넓이를 구할 때 원주율을 3.14로 정했다면 이 값은 변하지 않는 값이되어야 합니다.
- 이렇게 변하지 않는 값을 **상수**(constant)라고 합니다.
- 자바에서 상수는 다음처름 final 예약어를 사용해 선언합니다.

```
final double PI = 3.14;
final int MAX_NUM = 100;
```
- 상수 이름은 대문자를 주로 사용하고, 여러 단어를 연결하는 경우에 \_기호를  사용하면 보기 좋습니다. 
- 한 번 선언한 상수는 변하지 않기 때문에 선언과 동시에 값을 지정하는 것이 좋습니다.
- 가끔은 선언만 하고 사용하기 전에 값을 지정하기도 합니다.

#### day01/Constant.java
```
package day01;

public class Constant {
	public static void main(String[] args) {
		final int MAX_NUM = 100; // 선언과 동시에 초기화
		final int MIN_NUM;
		
		// 사용하기 전에 초기화, 초기화 하지 않으면 오류 발생
		MIN_NUM = 0; 
		
		System.out.println(MAX_NUM);
		System.out.println(MIN_NUM);
		
		//MAX_NUM = 1000; // 오류 발생, 상수는 값을 변경할 수 없음
	}
}

실행결과
100
0
```

### 리터럴
- 리터럴(literal)이란 프로그램에서 사용하는 모든 숫자, 문자, 논리값(true, false)을 일컫는 말입니다.
```
char ch = 'A';
int num = 10;
final double PI = 3.14;
```

- 상기 코드에서 사용한 'A', 10, 3.14와 같은 문자와 숫자를 '리터럴' 혹은 **리터럴 상수** 라고 합니다.
- 리터럴은 변수나 상수 값으로 대입할 수 있습니다.
- 리터럴은 프로그램이 시작할 때 시스템에 같이 로딩되어 특정 메모리 공간인 **상수 풀**(constant pool)에 놓입니다. 예를 들어 int num=3; 문장에서 값 3이 메모리 공간 어딘가에 존재해야 num 변수에 그 값을 복사할 수 있습니다.
- 즉, **숫자가 변수에 대입되는 과정**은 **일단 숫자 값이 어딘가 메모리에 쓰여 있고** 이 값이 다시 **변수 메모리에 복사**되는 것입니다.

![상수풀](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%EC%83%81%EC%88%98%ED%92%80.png)


## 형변환 

### 형 변환이란?
- 정수와 실수는 컴퓨터 내부에서 표현되는 방식이 전혀 다릅니다. 따라서 정수와 실수를 더한다고 할 때 그대로 연산을 수행할 수 없고 하나의 자료형으로 통일한 후 연산을 해야 합니다. 이때 형 변환(type conversion)이 이루어집니다.

``` 
int n = 10; // int형 변수 n에 정수 값 10을 대입
double dnum = n; // int 형 변수 n의 값을 double형 변수 dnum에 대입
```

- 위 문장에서 변수 n은 int형 이고 변수 dnum은 double형 입니다. 형 변환이란 이렇게 각 변수의 자료형이 다를 때 자료형을 같게 바꾸는 것을 말합니다.

- 형변환은 크게 **묵시적 형변환**(자동 형 변환)과 **명시적 형변환**(강제 형 변환) 두 가지로 구별해서 생각할 수 있습니다.

#### 형변환의 기본 원칙
1. 바이트 크기가 작은 자료형에서 큰 자료형으로 형 변환은 자동으로 이루어진다.
2. 덜 정밀한 자료형에서 더 정밀한 자료형으로 형 변환은 자동으로 이루어진다.

![묵시적 형변환](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%B6%95%2C%EB%B3%80%EC%88%98%EC%99%80%20%EC%9E%90%EB%A3%8C%ED%98%95/images/%EB%AC%B5%EC%8B%9C%EC%A0%81%ED%98%95%EB%B3%80%ED%99%98.png)

- 위 그림에서 화살표 방향과 반대로 형 변환을 하려면 강제로 변환해야 합니다. 
- long형이 8바이트 이고 float형이 4바이트 인데 자동 형 변환이 되는 이유는 **실수가 정수보다 표현 범위가 더 넓고 정밀하기 때문**입니다.
- 화살표 방향으로 형 변환이 이뤄질 때는 자료 손실이 없지만, 그 반대의 경우에는 자료 손실이 발생할 수 있습니다.

### 묵시적 형변환
1. 바이트 크기가 작은 자료형에서 큰 자료형으로 대입하는 경우 
	```
	byte bNum = 10;
	int iNum = bNum; // byte형 변수 bNum 값을 int형 변수 iNum에 대입함
	```
	
2. 덜 정밀한 자료형에서 더 정밀한 자료형으로 대입하는 경우
	```
	int iNum2 = 20;
	float fNum = iNum2;
	```
	- 두 변수의 크기가 같은 4바이트라도 float형이 더 정밀하게 데이터를 표현할 수 있으므로 실수형인 float로 변환됩니다.

3. 연산중에 자동 변환되는 경우
	```
	int iNum = 20;
	float fNum = iNum;
	double dNum;
	dNum = fNum + iNum;
	```
	- 바이트 크기가 작은 수에서 큰 수로, 덜 정밀한 수에서 더 정밀한 수로 자료형이 변환되는 경우에 자동 형 변환이 일어납니다.
	
#### day01/ImplicitConversion.java
```
package day01;

public class ImplicitConversion {
	public static void main(String[] args) {
		byte bNum = 10;
		int iNum = bNum; // byte형 값이 int형 변수로 대입됨
		
		System.out.println(bNum);
		System.out.println(iNum);
		
		int iNum2 = 20;
		float fNum = iNum2; // int형 값이 float형 변수로 대입됨 
		
		System.out.println(iNum);
		System.out.println(fNum);
		
		double dNum;
		dNum = fNum + iNum;
		System.out.println(dNum);
	}
}

실행결과
10
10
10
20.0
30.0
```

### 명시적 형변환
1. 바이트 크기가 큰 자료형에서 작은 자료형으로 대입하는 경우
	```
	int iNum = 10;
	byte bNum = (byte)iNum; // 강제로 형을 바꾸려면 바꿀 형을 괄호를 써서 명시해야 함
	```
	- 표현할 수 있는 범위를 넘는 경우에는 자료 손실이 발생할 수 있습니다.
	```
	int iNum = 1000;
	byte bNum = (byte)iNum; // -24
	```
	
2. 더 정밀한 자료형에서 덜 정밀한 자료형으로 대입하는 경우
	- 실수 자료형에서 정수 자료형으로 값이 대입되는 경우에도 역시 형 변환을 명시적으로 해주어야 합니다.
	```
	double dNum = 3.14;
	int iNum2 = (int)dNum; // 실수 자료형 double을 정수 자료형 int로 형 변환
	```
	
3. 연산 중 형변환 
#### day01/ExplicitConversion.java
```
package day01;

public class ExplicitConversion {
	public static void main(String[] args) {
		double dNum1 = 1.2;
		float fNum2 = 0.9F;
		
		int iNum3 = (int)dNum1 + (int)fNum2; // 두 실수가 각각 형변환되어 더해짐
		int iNum4 = (int)(dNum1 + fNum2); // 두 실수의 합이 먼저 계산되고 형 변환됨
		System.out.println(iNum3);
		System.out.println(iNum4);
	}
}

실행결과
1
2
```
# 객체지향 프로그래밍1

## 객체 지향 프로그래밍과 클래스
### 객체와 객체 지향 프로그래밍
- 국어사전에서 객체의 뜻을 찾아보면 '의사나 행위가 미치는 대상'이라고 설명합니다. 
- 우리 주위에 있는 객체를 생각해 보면 사람, 자동차, 건물 등이 있습니다. 즉, 눈에 보이는 사물은 모두 객체라고 할 수 있습니다. 
- 눈에 안보이는 것도 객체가 될 수 있습니다. 주문, 생산, 관리등 어떤 행동을 나타내는 단어도 객체가 될 수 있습니다.
- 자바 객체 지향 프로그래밍(Object-Oriented Programming: OOP)은 객체를 기반으로 하는 프로그램입니다.
- 객체지향 프로그램이란 '**어떤 대상(객체)**'을 가지고 프로그래밍한다는 것 

### 생활 속에서 객체 찾아보기
- 절차 지향 프로그래밍 : 순서대로 일어나는 일을 시간순으로 프로그래밍 하는 것

![절차지향 프로그래밍](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/5~7%EC%9D%BC%EC%B0%A8(9h)%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D1/images/%EC%A0%88%EC%B0%A8%EC%A7%80%ED%96%A5%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D.png)

- 객체 지향 프로그래밍 : 객체를 정의하고 객체 간 협력을 프로그래밍 하는 것
- 객체지향 프로그램은 먼저 객체를 만들고 객체 사이에 일어나는 일을 구현하는 것
- 객체지향 프로그래밍을 할 때는 객체를 먼저 정의하고 각 객체가 어떤 기능을 제공하고 객체 간 협력을 어떻게 구현할 것인지를 고민해야 한다.

![객체지향 프로그래밍](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/5~7%EC%9D%BC%EC%B0%A8(9h)%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D1/images/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D.png)
>객체지향 프로그래밍은 객체인 학생, 밥, 버스, 학교 사이에 일어나는 일을 프로그램으로 구현하는 것입니다.

## 클래스 살펴보기
- 객체지향프로그램은 클래스를 기반으로 프로그래밍합니다. 
- 클래스는 객체의 속성과 기능을 코드로 구현한 것입니다.
- 객체를 클래스로 구현한다는 것을 '**클래스를 정의한다**'라고 합니다. 
- 클래스를 정의하려면 우선 클래스의 이름과 클래스가 가지는 속성 또는 특성이 필요합니다.

![클래스](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/5~7%EC%9D%BC%EC%B0%A8(9h)%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D1/images/%ED%81%B4%EB%9E%98%EC%8A%A4.png)

- 학생 객체를 생각해 보면, 먼저 객체를 표현할 클래스의 '이름'이 필요합니다. 클래스명을 Student라고 정합니다.
- 학생 객체가 가지는 일반적인 속성은 학번, 이름, 학년, 사는 곳 등으로 생각해볼 수 있습니다. 
- 이런 클래스의 속성은 특성이라고 하고 클래스 내부에 변수로 선언합니다. 
- 이렇게 선언하는 클래스의 속성을 **멤버변수**라고 합니다.

#### 클래스를 정의하는 문법
```
(접근제어자) class 클래스 이름 {
	멤버변수;
	메서드;
}
```
>클래스 이름 앞에 있는 public 예약어는 **접근 제어자**라고 합니다. [정보은닉 참고](https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/5~7%EC%9D%BC%EC%B0%A8(9h)%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D1#%EC%A0%95%EB%B3%B4-%EC%9D%80%EB%8B%89)

#### day05_07/Student.java
```
package day05_07;

public class Student {
	int studentID; // 학번
	String studentName; // 학생이름
	int grade; // 학년
	String address; // 사는 곳
}
```
- 클래스를 만들때는 class 예약어를 사용합니다.
- 클래스 이름은 Student라고 정했습니다.
- { }안에는 클래스의 내용을 구현합니다.
- 프로그램에서 사용할 객체의 속성을 클래스의 변수로 선언합니다.
- 변수를 선언할 때는 각 변수의 속성에 맞는 자료형을 사용해야 합니다.
- 자바 프로그램은 모든 요소가 클래스 내부에 있어야 합니다.
- 클래스 외부에는 package 선언과 import문장 외에 아무것도 선언하지 않습니다.

### 클래스 이름을 짓는 규칙
- 클래스 이름은 대문자로 시작합니다.(파스칼 케이스 - 첫 시작 단어는 대문자로 예) OrderInfo, OrderItems)
- 소문자로 시작한다고 해서 오류가 발생하는 것은 아니나 개발자들 사이에서 관습처럼 사용되는 규칙입니다.
- 관습을 따르면 각 명칭의 용도를 쉽게 유추할 수 있습니다.


### 클래스의 속성을 구현하는 멤버변수 
- 클래스에 선언하여 객체 속성을 나타내는 변수가 멤버 변수(member variable)입니다.
```
public class Student {
	int studentID; // 학번 
	String studentName; // 학생이름
	int grade; // 학년
	String address; // 사는 곳
}
```
- 멤버변수 : int studentID, String studentName, int grade, String address
- 클래스에 선언하는 멤버변수는 다른말로 **속성(property)**, **특성(attribute)** 등으로 표현하기도 합니다.
- 멤버변수는 속성이 무엇이냐에 따라 알맞는 자료형을 선언해 주어야 합니다.


### Person 클래스 만들기
#### day05_07/Person.java
```
package day05_07;

public class Person {
	String name; // 이름
	int height; // 키
	double weight; // 몸무게
	char gender; // 성별
	boolean married; // 결혼여부
}
```
- Person 클래스의 멤버 변수로 이름, 키, 몸무게, 성별, 결혼여부를 선언했습니다.
- 이와 같은 멤버변수를 선언할 때 int, double형 같은 **기본자료형**(primitive data type)으로 선언할 수도 있고 또 다른 클래스형으로 선언할 수도 있습니다.
- 클래스형이란 다른 말로 **참조자료형**이라고 합니다.
- 참조자료형으로 사용하는 클래스는 String, Date와 같이 이미 JDK에서 제공하는 것일 수도 있고, 개발자가 직접 만든 Student나 Person같은 클래스가 다른 클래스에서 사용하는 멤버변수의 자료형이 될 수 있습니다.

#### 변수의 자료형
|기본자료형|참조자료형|
|----|----|
|int, long, float, double등|String, Date, Student 등|


## 클래스와 인스턴스
- 클래스에서는 학생 객체가 가지고 있는 속성르 사용해 학생과 관련된 기능을 구현할 수 있습니다.<br>
	(예 : '학생에게 이름을 부여한다.', '학생이 사는 곳을 출력한다.')
- 클래스 내부에서 멤버 변수를 사용하여 클래스의 기능을 구현한 것을 '**멤버 함수**(member function)' 또는 '**메서드**(method)'라고 합니다.<br>
	(메서드로 용어를 통일하여 진행)

### 학생 이름과 주소를 출력하는 메서드 만들기
#### day05_07/Student .java
```
package day05_07;

public class Student {
	int studentID; // 학번
	String studentName; // 학생이름
	int grade; // 학년
	String address; // 사는 곳
	
	public void showStudentInfo() {
		System.out.println(studentName + "," + address); // 이름, 주소 출력
	}
}

```

### 패키지란? 
- 클래스파일의 묶음
- 패키지를 만들면 프로젝트 하위에 물리적으로 디렉토리가 생성됩니다.
- 패키지는 계층구조를 가지고 있습니다.
- 패키지가 단순히 클래스 묶음이 아닌 프로젝트 전체 소스 코드를 구성하는 계층구조가 되고, 이 계층구조를 잘 구성해야 소스 코드 관리와 유지보수가 편리합니다.

#### 패키지 선언하기
- 자바 소스 코드에서 클래스의 패키지 선언은 다음처음 맨 위에서 합니다.
```
package domain.student.view

public class StudentView {
	...
}
```
- 클래스 이름은 StudentView이지만, 클래스의 전체 이름(class full name)은 domain.student.view.StudentView입니다. 
- 클래스 이름이 같다고 해도 패키지 이름이 다르면 클래스 전체 이름이 다른 것이므로 다른 클래스가 됩니다.
- 같은 이름의 클래스라도 다른 패키지에 속해 있으면 서로 연관이 없습니다.

## 메서드
메서드는 함수(function)의 한 종류 입니다. 

### 함수란? 
- 하나의 기능을 수행하는 일련의 코드
- 함수는 어떤 기능을 수행하도록 미리 구현해 놓고 필요할 때마다 호출하여 사용할 수 있습니다.

### 함수의 입력과 반환
- 함수는 이름이 있고, 입력된 값과 결과 값을 갖습니다. 
- 두 수를 더하는 함수를 예로 들면 두 수를 입력받아서 '더하기 함수'를 거치면 두 수의 합을 반환합니다. 

![더하기 함수]()

## 생성자

## 참조 자료형

## 정보 은닉 



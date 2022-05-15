# 예외처리

## 예외 클래스
### 오류란 무인가요?
- <b>컴파일 오류(compile error)</b>
	- 프로그램 코드 작성 중 실수로 발생하는 오류
	- 발생한 컴파일 오류를 모두 수정해야 프로그램이 정상적으로 실행 되므로, 문법적으로 오류가 있다는 것을 바로 알 수 있습니다.
	
- <b>실행 오류(runtime error)</b>
	- 실행 중인 프로그램이 의도하지 않은 동작을 하거나 프로그램이 중지되는 오류 입니다. 실행 오류 중 프로그램을 잘목 수현하여 의도한 바와 다르게 실행되어 생기는 오류를 <b>버그(bug)</b>라고 합니다.
	- 프로그램 실행 중에 발생하는 오류는 예측하기 어려운 경우가 많고, 프로그램이 비정상 종료되면서 갑자기 멈춰 버립니다.
	- 자바은 이러한 비정상 종료를 최대한 줄이기 위해 다양한 예외에 대한 처리방법을 가지고 있습니다.
	- 예외 처리를 하는 목적은 프로그램이 비정상 종료 되는 것을 방지하기 위한 것 입니다.

### 오류와 예외
- <b>시스템 오류(error)</b> 
	- 자바 가상 머신에서 발생
	- 시스템 오류의 예로는 사용 가능한 동적 메모리가 없는 경우나 스택 메모리의 오버플로가 발생한 경우 등이 있습니다.
	- 이러한 시스템 오류는 프로그램에서 제어할 수 없습니다.
	
- <b>예외(exception)</b>
	- 프로그램에서 제어할 수 있습니다.
	- 예를 들어 프로그램에서 파일을 읽어 사용하려는데 파일이 없는 경우, 네트워크로 데이터를 전송하려는데 연결이 안된 경우, 배열 값을 출력하는데 배열 요소가 없는 경우 등
	

- 오류 클래스는 모두 **Throwable 클래스**에서 상속받습니다. **Error 클래스**의 하위 클래스는 시스템에서 발생하는 오류를 다루며 프로그램에서 제어하지 않습니다. 프로그램에서 제어하는 부분은 **Exception 클래스**와 그 하위에 있는 예외 클래스 입니다.


## 예외 처리하기


##  예외 처리 미루기

## 사용자 정의 예외


* * * 
# java.lang 패키지

## Object 클래스

### equals(Object obj)

### hashCode()

### toString()

### clone()

### 공변 반환타입

### 얕은 복사와 깊은 복사


### Class 객체를 얻는 방법

## String 클래스

### 변경 불가능한(immutable)클래스

### 문자열의 비교

### 문자열 리터럴

### 빈 문자열(empty string)

### String클래스의 생성자와 메서드

### join()과 StringJointer

### 문자 인코딩 변환

### String.format()

### 기본형 값을 String으로 변환

### String을 기본형 값으로 변환

## StringBuffer클래스와 StringBuilder 클래스

### StringBuffer 생성자

### StringBuffer의 변경

### StringBuffer의 비교

### StringBuffer클래스의 생성자와 메서드

### StringBuilder란? 


## Math클래스

### 올림, 버림, 반올림

### Math클래스의 메서드

## 래퍼(wrapper) 클래스

### Number클래스

### 문자열을 숫자로 변환하기

### 오토박싱 & 언박싱(autoboxing & unboxing)


* * * 
# 유용한 클래스

## java.util.Objects 클래스

## java.util.Random 클래스

## 정규식(Regular Expression) - java.util.regex패키지

## java.util.Scanner 클래스

## java.util.StringTokenizer 클래스

## java.math.BigInteger 클래스

## java.math.BigDecimal 클래스
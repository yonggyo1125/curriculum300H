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


## 변수란 무엇일까?

## 변수가 저장되는 공간의 특성, 자료형

## 상수와 리터럴

## 형변환 
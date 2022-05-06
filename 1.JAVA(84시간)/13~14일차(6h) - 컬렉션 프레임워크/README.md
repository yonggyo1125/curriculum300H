# 컬렉션 프레임워크(Collections Framework)
- 컬렉션 프레임워크란, '데이터 군을 저장하는 클래스등을 표준화한 설계'를 뜻한다.
- JDK1.2 이전까지는 Vector, Hashtable, Properties와 같은 컬렉션 클래스, 다수의 데이터를 저장할 수 있는 클래스들을 서로 다른 각자의 방식으로 처리해야 했다.
- JDK1.2부터 컬렉션 프레임워크가 등장하면서 다양한 종류의 컬렉션 클래스가 추가되고 모든 컬렉션 클래스를 표준화된 방식으로 다룰 수 있도록 체계화되었다.
> Vector와 같이 다수의 데이터를 저장할 수 있는 클래스를 컬렉션 클래스라고 한다.

- 컬렉션 프레임워크는 컬렉션(다수의 데이터)을 다루는 데 필요한 다양하고 풀부한 클래스를 제공한다.
- 인터페이스의 다형성을 이용한 객체지향적 설계를 통해 표준화되어 있기 때문에 사용법을 익히기에도 편리하고 재사용성이 높은 코드를 작성할 수 있다는 장점이 있다.


## 컬렉션 프레임워크 핵심 인터페이스
- 컬렉션 프레임워크에서는 컬렉션데이터 그룹을 크게 3가지 타입이 존재한다고 인식하고 컬렉션을 다루는데 필요한 기능을 가진 3개 인터페이스(List, Set, Map)를 정의하였다.
- 인터페이스 List와 Set의 공통된 부분을 다시 뽑아서 새로운 인터페이스인 Collection을 추가로 정의하였다.
![컬렉션 프레임워크의 핵심 인터페이스간의 상속 계층도](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EA%B0%84_%EC%83%81%EC%86%8D%EB%8F%84.png)

>인터페이스의  List와 Set을 구현한 컬렉션 클래스들은 서로 많은 공통부분이 있어서, 공통된 부분을 다시 뽑아 Collection인터페이스를 정의할 수 있었지만 Map 인터페이스는 이들과는 전혀 다른 형태로 컬렉션을 다루기 때문에 같은 상속계층도에 포함되지 못했다.



### Collection인터페이스

### List 인터페이스

### Set 인터페이스


### Map 인터페이스

### Map.Entry 인터페이스

## ArrayList

## LinkedList

## Stack과 Queue


## Iterator, ListIterator, Enumeration

### Iterator

### ListIterator와 Enumeration

## Arrays

### 배열의 복사 - copyOf(), copyOfRange()

### 배열 채우기 - fill(), setAll()

### 배열의 정렬과 검색 - sort(), binarySearch()

### 문자열 비교와 출력 - equals(), toString()

### 배열을 List로 변환 - asList(Object... a)

### stream()


## Comparator와 Comparable

## HashSet

## TreeSet

## HashMap과 Hashtable

### 해싱과 해시함수

## TreeMap

## Properties

## Collections

### 컬렉션의 동기화

### 변경불가 컬렉션 만들기

### 싱글톤 컬렉션 만들기

### 한 종류의 객체만 저장하는 컬렉션 만들기 


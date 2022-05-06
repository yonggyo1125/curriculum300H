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

|인터페이스|특징|
|----|----------|
|List|순서가 있는 데이터의 집합. 데이터의 중복을 허용한다.<br>구현클래스:ArrayList, LinkedList, Stack,Vector등|
|Set|순서를 유지하지 않는 데이터의 집합. 데이터의 중복을 허용하지 않는다.<br>구현클래스: HashSet, TreeSet 등|
|Map|키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합<br>순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값은 중복을 허용한다.<br>구현클래스: HashMap, TreeMap, Hashtable, Properties|

> 키(Key)란, 데이터 집합 중에서 어떤 값(value)을 찾는데 열쇠(key)가 된다는 의미에서 붙여진 이름이다. 그래서 키(Key)는 중복을 허용하지 않는다.

- 컬렉션 프레임워크의 모든 컬렉션 클래스들을  List, Set, Map중 하나를 구현하고 있다. 
- 구현한 인터페이스의 이름이 클래스의 이름에 포함되어 있어서 이름만으로도 클래스의 특징을 쉽게 알 수 있도록 되어 있다.
- 그러나 Vector, Stack, Hashtable, Properties와 같은 클래스들은 컬렉션 프레임워크가 만들어지기 이전부터 존재하던 것이기 때문에 컬렉션 프레임워크의 명명법을 따르지 않는다.
- Vector나 Hashtable과 같은 기존의 컬렉션 클래스들은 호환을 위해, 설계를 변경해서 남겨두었지만 가능하면 사용하지 않는 것이 좋다. 그 대신 새로 추가된 ArrayList와 HashMap을 사용하는 것이 좋다.


### Collection인터페이스
- Collection은 List와 Set의 조상 인터페이스 이다.
- Collection 인터페이스는 컬렉션 클래스에 저장된 데이터를 읽고, 추가하고, 삭제하는 등 컬렉션을 다루는데 가장 기본이적인 메서드들을 정의하고 있다.

#### Collection 인터페이스에 정의된 메서드
|메서드|설명|
|----|----------|
|boolean add(Object o)<br>boolean addAll(Collection c)|지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가한다.|
|void clear()|Collection의 모든 객체를 삭제한다.|
|boolean contains(Object o)<br>boolean containsAll(Collection c)|지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인한다.|
|boolean equals(Object o)|동일한 Collection인지 비교한다.|
|int hashCode()|Collection의 hash code를 반환한다.|
|boolean isEmpty()|Collection이 비어 있는지 확인한다.|
|Iterator iterator()|Collection의 iterator를 얻어서 반환한다.|
|boolean remove(Object o)|지정된 객체를 삭제한다.|
|boolean removeAll(Collection c)|지정된 Collection에 포함된 객체들을 삭제한다.|
|boolean retainAll(Collection c)|지정된 Collection에 포함된 객체만 남기고 다른 객체들은 Collection에서 삭제한다. 이 작업으로 인해 Collection에 변화가 있으면 true를 그렇지 않다면 false를 반환한다.|
|int size()|Collection에 저장된 객체의 개수를 반환한다.|
|Object[] toArray()|Collection에 저장된 객체를 객체배열(Object[])로 반환한다.|
|Object[] toArray(Object[] a)|지정된 배열에 Collection의 객체를 지정해서 반환한다.|


### List 인터페이스
List 인터페이스는 **중복을 허용**하면서 **저장순서가 유지**되는 컬렉션을 구현하는데 사용된다.

#### List의 상속계층도
![List의 상속 계층도](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/List%EC%9D%98_%EC%83%81%EC%86%8D%EA%B3%84%EC%B8%B5%EB%8F%84.png)

#### List인터페이스에 정의된 메서드
Collection 인터페이로부터 상속받은 것은 제외하였다.

|메서드|설명|
|----|----------|
|void add(int index, Object element)<br>boolean addAll(int index, Collection c)|지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가한다.|
|Object get(int index)|지정된 위치(index)에 있는 객체를 반환한다.|
|int indexOf(Object o)|지정된 객체의 위치(index)를 반환한다.<br>(List의 첫 번째 요소로부터 순방향으로 찾는다.)|
|int lastIndexOf(Object o)|지정된 객체의 위치(index)를 반환한다.<br>(List의 마지막 요소로 부터 역방향으로 찾는다.)|
|ListIterator listIterator()<br>ListIterator listIterator(int index)|List의 객체에 접근할 수 있는 ListIterator를 반환한다.|
|Object remove(int index)|지정된 위치(index)에 있는 객체를 삭제하고 삭제된 객체를 반환한다.|
|Object set(int index, Object element)|지정된 위치(index)에 객체(element)를 저장한다.|
|void sort(Comparator c)|지정된 비교자(Comparator)로 List를 정렬한다.|
|List subList(int fromIndex, int toIndex)|지정된 범위(fromIndex부터 toIndex)에 있는 객체를 반환한다.|


### Set 인터페이스
- Set 인터페이스는 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션 클래스를 구현하는데 사용된다.
- Set 인터페이스를 구현한 클래스로는 HashSet, TreeSet 등이 있다.

#### Set의 상속계층도
![Set의 상속계층도](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/Set%EC%9D%98_%EC%83%81%EC%86%8D%EA%B3%84%EC%B8%B5%EB%8F%84.png)


### Map 인터페이스
- Map인터페이스는 키(key)와 값(value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는 데 사용된다.
- 키는 중복될 수 없지만 값은 중복을 허용한다.
- 기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게된다.
- Map인터페이스를 구현한 클래스로는 Hashtable, HashMap, LinkedHashMap, SortedMap, TreeMap등이 있다.
> Map이란 개념은 어떤 두 값을 연결한다는 의미에서 붙여진 이름이다.


#### Map의 상속계층도
![Map의 상속계층도](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/Map%EC%9D%98_%EC%83%81%EC%86%8D%EA%B3%84%EC%B8%B5%EB%8F%84.png)


#### Map인터페이스의 메서드

|메서드|설명|
|----|----------|
|void clear()|Map의 모든 객체를 삭제한다.|
|boolean containsKey(Object key)|지정된 key객체와 일치하는  Map의 key객체가 있는지 확인한다.|
|boolean containsValue(Object value)|지정된 value객체와 일치하는 Map의 value객체가 있는지 확인한다.|
|Set entrySet()|Map에 저장되는 있는 key-value쌍을 Map.Entry타입의 객체로 저장한 Set으로 반환한다.|
|boolean equals(Object o)|동일한 Map인지 비교한다.|
|Object get(Object key)|지정된 key객체에 대응하는 value객체를 찾아서 반환한다.|
|int hashCode()|해시코드를 반환한다.|
|boolean isEmpty()|Map이 비어있는지 확인한다.|
|Set keySet()|Map에 저장된 모든 key객체를 반환한다.|
|Object put(Object key, Object value)|Map에 value객체를 key객체에 연결(mapping)하여 저장한다.|
|void putAll(Map f)|지정된 Map의 모든 key-value쌍을 추가한다.|
|Object remove(Object key)|지정한 key객체와 일치하는 key-value객체를 삭제한다.|
|int size()|Map에 저장된 key-value쌍의 개수를 반환한다.|
|Collection values()|Map에 저장된 모든 value객체를 반환한다.|

> Map 인터페이스에서 값(values)은 중복을 허용하기 때문에 Collection타입으로 반환하고, 키(key)는 중복을 허용하지 않기 때문에 Set타입으로 반환한다.


### Map.Entry 인터페이스
- Map.Entry인터페이스는 Map인터페이스의 내부 인터페이스이다.
- 내부 클래스와 같이 인터페이스도 인터페이스 안에 인터페으스를 정의하는 것이 가능하다.
- Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry 인터페이스를 정의해 놓은 것

```
Map 인터페이스의 소스코드 일부

public interface Map {
	...
	interface Entry {
		Object getKey();
		Object getValue();
		Object setValue(Object value);
		boolean equals(Object o);
		int hashCode();
		...
	}
}
```

#### Map.Entry 인터페이스와 메서드

|메서드|설명|
|----|----------|
|boolean equals(Object o)|동일한 Entry인지 비교한다.|
|Object getKey()|Entry의 key 객체를 반환한다.|
|Object getValue()|Entry의 value객체를 반환한다.|
|int hashCode()|Entry의 해시코드를 반환한다.|
|Object setValue(Object value)|Entry의 value객체를 지정된 객체로 바꾼다.|


## ArrayList
- ArrayList는 컬렉션 프레임워크에서 가장 많이 사용되는 컬렉션 클래스이다.
- ArrayList는 List인터페이스를 구현하기 때문에 데이터의 저장순서가 유지되고 중복을 허용한다는 특징을 갖는다.
- ArrayList는 기존의 Vector를 개선한 것으로 Vector와 구현원리와 기능적인 측면에서 동일하다고 할 수 있다.<br>
  (Vector는 기존에 작성된 소스와의 호환성을 위해서 남겨둔 것이므로 가능하면 Vector보다는 ArrayList를 사용하는 것이 좋다.)
  
- ArrayList는 Object배열을 이용해서 데이터를 순차적으로 자정한다.
- 첫 번쨀로 저장한 객체는 Object배열의 0번째 위치에 저장되고 그 다음에 저장하는 객체는 1번째 위치에 저장된다(배열에 순서대로 저장된다.)
- 배열에 더 이상 저장할 공간이 없으면 **보다 큰 새로운 배열을 생성**해서 **기존의 배열에 저장된 내용을 새로운 배열로 복사**한 다음에 저장된다.

```
public class ArraList extends AbstractList implements List, RandomAccess, Cloneable, java.io.Serializable {
	...
	transient Object[] elementData;
	...
}
```
- ArrayList는 elementData라는 이름의 Object 배열을 멤버변수로 선언하고 있다.
- 선언된 배열의 타입의 모든 객체의 최상위 조상인 Object이기 때문에 모든 종류의 객체를 담을 수 있다.

### ArrayList의 생성자와 메서드

|메서드|설명|
|----|----------|
|ArrayList()|크기가 10인 ArrayList를 생성|
|ArrayList(Collection c)|주어진 컬렉션이 저장된 ArrayList를 생성|
|ArrayList(int initialCapacity)|지정된 초기용량을 갖는 ArrayList를 생성|
|boolean add(Object o)|ArrayList의 마지막 객체를 추가, 성공하면 true|
|void add(int index, Object element)|지정된 위치(index)에 객체를 저장|
|boolean addAll(Collection c)|주어진 컬렉션의 모든 객체를 저장한다.|
|boolean addAll(int index, Collection c)|지정된 위치부터 주어진 컬렉션의 모든 객체를 저장한다.|
|void clear()|ArrayList를 완전히 비운다.|
|Object clone()|ArrayList를 복제한다.|
|boolean contains(Object c)|지정된 객체(o)가 ArrayList에 포함되어 있는지 확인|
|void ensureCapacity(int minCapacity)|ArrayList의 용량이 최소한 minCapacity가 되도록 한다.|
|Object get(int index)|지정된 위치(index)에 저장된 객체를 반환한다.|
|int indexOf(Object o)|지정된 객체가 저장된 위치를 찾아 반환한다.|
|boolean isEmpty()|ArrayList가 비어있는지 확인한다.|
|Iterator iterator()|ArrayList의 Iterator 객체를 반환|
|int lastIndexOf(Object o)|객체(o)가 저장된 위치를 끝부터 역방향으로 검색해서 반환|
|ListIterator listIterator()|ArrayList의 ListIterator를 반환|
|ListIterator listIterator(int index)|ArrayList의 지정된 위치부터 시작하는 ListIterator를 반환|
|Object remove(int index)|지정된 위치(index)에 있는 객체를 제거한다.|
|boolean remove(Object o)|지정된 객체를 제거한다.(성공하면 true, 실패하면 false)|
|boolean removeAll(Collection c)|지정된 컬렉션에 저장된 것과 동일한 객체들을 ArrayList에서 제거한다.|
|boolean retainAll(Collection c)|ArrayList에 저장된 객체중 주어진 컬렉션과 공통인 것만 남기고 나머지는 삭제한다.|
|Object set(int index, Object element)|주어진 객체(element)를 지정된 위치(index)에 저장한다.|
|int size()|ArrayList에 저장된 객체의 개수를 반환한다.|
|void sort(Comparator c)|지정된 정렬기준(c)으로 ArrayList를 정렬|
|List subList(int fromIndex, int toIndex)|fromIndex부터 toIndex사이에 저장된 객체를 반환한다.|
|Object[] toArray()|ArrayList에 저장된 모든 객체들을 객체배열로 반환한다.|
|Object[] toArray(Object[] a)|ArrayList에 저장한 모든 객체들을 객체배열 a에 담아 반환한다.|
|void trimToSize()|용량을 크기에 맞게 줄인다.(빈 공간을 없앤다.)|



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


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
- 첫 번째로 저장한 객체는 Object배열의 0번째 위치에 저장되고 그 다음에 저장하는 객체는 1번째 위치에 저장된다(배열에 순서대로 저장된다.)
- 배열에 더 이상 저장할 공간이 없으면 **보다 큰 새로운 배열을 생성**해서 **기존의 배열에 저장된 내용을 새로운 배열로 복사**한 다음에 저장된다.

```
public class ArraList extends AbstractList implements List, RandomAccess, Cloneable, java.io.Serializable {
	...
	transient Object[] elementData;
	...
}
```
- ArrayList는 elementData라는 이름의 Object 배열을 멤버변수로 선언하고 있다.
- 선언된 배열의 타입이 모든 객체의 최상위 조상인 Object이기 때문에 모든 종류의 객체를 담을 수 있다.

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

#### day13_14/ArrayListEx1.java
```
package day13_14;

import java.util.*;

public class ArrayListEx1 {
	public static void main(String[] args) {
		ArrayList list1 = new ArrayList(10);
		list1.add(Integer.valueOf(5));
		list1.add(Integer.valueOf(4));
		list1.add(Integer.valueOf(2));
		list1.add(Integer.valueOf(0));
		list1.add(Integer.valueOf(1));
		list1.add(Integer.valueOf(3));
		
		ArrayList list2 = new ArrayList(list1.subList(1, 4));
		print(list1, list2);
		
		Collections.sort(list1);
		Collections.sort(list2);
		print(list1, list2);
		
		System.out.println("list1.containsAll(list2):" + list1.containsAll(list2));
		
		list2.add("B");
		list2.add("C");
		list2.add(3, "A");
		print(list1, list2);
		
		list2.set(3, "AA");
		print(list1, list2);
		
		// list1과 list2와 겹치는 부분만 남기ㄷ고 나머지는 삭제한다.
		System.out.println("list1.retainAll(list2):" + list1.retainAll(list2));
		
		print(list1, list2);
		
		
		// list2에서 list1에 포함된 객체들을 삭제한다.
		for(int i = list2.size() - 1; i >=0; i--) {
			if (list1.contains(list2.get(i)))
				list2.remove(i);
		}
		print(list1, list2);
	}
	
	static void print(ArrayList list1, ArrayList list2) {
		System.out.println("list1:" + list1);
		System.out.println("list2:" + list2);
		System.out.println();
	}
}

실행결과
list1:[5, 4, 2, 0, 1, 3]
list2:[4, 2, 0]

list1:[0, 1, 2, 3, 4, 5]
list2:[0, 2, 4]

list1.containsAll(list2):true
list1:[0, 1, 2, 3, 4, 5]
list2:[0, 2, 4, A, B, C]

list1:[0, 1, 2, 3, 4, 5]
list2:[0, 2, 4, AA, B, C]

list1.retainAll(list2):true
list1:[0, 2, 4]
list2:[0, 2, 4, AA, B, C]

list1:[0, 2, 4]
list2:[AA, B, C]
```

- list2의 각 요소를 접근하기 위해 get(int index)메서드와 for문을 사용하였는데, for문의 변수 i를 0부터 증가시킨 것이 아니라 **list2.size() - 1** 부터 감소시키면서 거꾸로 반복 시켰다.
- 변수 i를 증가시켜가면서 삭제하면, 한 요소가 삭제될 때마다 빈 공간을 채우기 위해 나머지 요소들이 자리이동을 하기 때문에 올바른 결과를 얻을 수 없다.
- 그래서 제어변수를 감소시켜가면서 삭제를 해야 자리이동이 발생해도 영향을 받지 않고 작업이 가능하다.

#### day13_14/ArrayListEx2.java
```
package day13_14;

import java.util.*;

public class ArrayListEx2 {
	public static void main(String[] args) {
		final int LIMIT = 10;
		String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
		int length = source.length();
		
		List list = new ArrayList(length / LIMIT + 10); // 크기를 약간 여유 있게 잡는다.
		
		for(int i = 0; i < length; i+=LIMIT) {
			if (i+LIMIT < length) {
				list.add(source.substring(i, i+LIMIT));
			} else {
				list.add(source.substring(i));
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

실행결과
0123456789
abcdefghij
ABCDEFGHIJ
!@#$%^&*()
ZZZ
```

- ArrayList를 생성할 때, 저장할 요소의 개수를 고려해서 실제 저장할 개수보다 약간 여유 있는 크기로 하는것이 좋다.
- 생설할 때 지정한 크기보다 더 많은 객체를 저장하면 자동적으로 크기가 늘어나기는 하지만 이 과정에서 처리시간이 많이 소요되기 때문이다.

>ArrayList나 Vector같이 배열을 이용한 자료구조는 데이터를 읽어오고 저장하는 데는 효율이 좋지만, 용량을 변경해야 할 때는 새로운 배열을 생성한 후 기존의 배열로 부터 새로 생성된 배열로 데이터를 복사해야하기 때문에 상당히 효율이 떨어진다는 단점을 가지고 있다. 따라서 처음에 인스턴스를 생성할 때, 저장한 데이터의 개수를 잘 고려하여 충분한 용량의 인스턴스를 생성하는 것이 좋다.


## LinkedList
- 배열은 가장 기본적인 형태의 자료구조로 구조가 간단하며 사용하기 쉽고 데이터를 읽어오는데 걸리는 시간(접근 시간, access time)이 가장 빠르다라는 장점을 가지고 있다. 
- 그러나 다음과 같은 단점도 가지고 있다.
	- 크기를 변경할 수 없다.
		- 크기를 변경할 수 없으므로 새로운 배열을 생성해서 데이터를 복사해야 한다.
		- 실행속도를 향상시키기 위해서는 충분히 큰 크기의 배열을 생성해야 하므로 메모리가 낭비된다.
		
	- 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸린다.
		- 차례대로 데이터를 추가하고 마지막에서부터 데이터를 삭제하는 것은 빠르지만
		- 배열의 중간에 데이터를 추가하려면, 빈자리를 만들기 위해 다른 데이터를 복사해서 이동해야 한다.
		
- 이러한 배열의 단점을 보완하기 위해서 링크드 리스트(linked list)라는 자료구조가 고안되었다.
- 배열은 모든 데이터가 연속적으로 존재하지만 링크드 리스트는 불연속적으로 존재하는 데이터를 서로 연결(link)한 형태로 구성되어 있다.
![배열과 링크드 리스트](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EB%B0%B0%EC%97%B4%EA%B3%BC_%EB%A7%81%ED%81%AC%EB%93%9C%EB%A6%AC%EC%8A%A4%ED%8A%B8.png)


- 상기 그림에서 알수 있는 것 처럼, 링크드 리스트의 각 요소들은 자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성되어 있다.


- 링크드 리스트에서 삭제는 삭제하고자 하는 요소의 이전 요소가 삭제하고자 하는 요소의 다음 요소를 참조하도록 변경하기만 하면 된다. 단 하나의 참조만 변경하면 삭제가 이루어지므로 배열처럼 데이터를 이동하기 위해 복사하는 과정이 없기 때문에 처리속도가 매우 빠르다.
![링크드 리스트에서의 데이터 삭제](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EB%A7%81%ED%81%AC%EB%93%9C%EB%A6%AC%EC%8A%A4%ED%8A%B8_%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%82%AD%EC%A0%9C.png)

- 새로운 데이터를 추가할 때는 새로운 요소를 생성한 다음 추가하고자 하는 위치의 이전 요소의 참조를 새로운 요소에 대한 참조로 변경해주고, 새로운 요소가 그 다음 요소를 참조하도록 변경하기만 하면 되므로 처리 속도가 매우 빠르다.
![링크드 리스트에서의 데이터 추가](https://github.com/yonggyo1125/curriculum300H/blob/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EB%A7%81%ED%81%AC%EB%93%9C%EB%A6%AC%EC%8A%A4%ED%8A%B8_%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%B6%94%EA%B0%80.png)


- 링크드 리스트는 이동방향이 단방향이기 때문에 다음 요소에 대한 접근은 쉽지만 이전요소에 대한 접근은 어렵다. 이 덤을 보완한 것이 더블 링크드 리스트(이중 연결리스트, doubly linked list)이다.
- 더블 링크드 리스트는 단순히 링크드 리스트에 참조변수를 하나 더 추가하여 다음 요소에 대한 참조뿐 아니라 이전 요소에 대한 참조가 가능하도록 했을 뿐, 링크드 리스트와 같다.
![더블 링크드 리스트](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EB%8D%94%EB%B8%94%EB%A7%81%ED%81%AC%EB%93%9C%EB%A6%AC%EC%8A%A4%ED%8A%B8.png)

- 더블 링크드 리스트의 접근성을 보다 향상시킨 것이 '더블 써큘러 링크드 리스트(이중 원형 연결리스트, doubly circular linked list)'이다. 이는 단순히 더블 링크드 리스트의 첫 번째 요소와 마지막 요소를 서로 연결시킨 것이다. 
- 이렇게 하면 마지막요소의 다음요소가 첫번째 요소가 되고, 첫 번째 요소의 이전 요소가 마지막 요소가 된다.
![더블 써큘러 링크드 리스트](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%BB%AC%EB%A0%89%EC%85%98%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/images/%EB%8D%94%EB%B8%94%EC%8D%A8%ED%81%98%EB%9F%AC%EB%A7%81%ED%81%AC%EB%93%9C%EB%A6%AC%EC%8A%A4%ED%8A%B8.png)

- 실제로 구현된 LinkedList 클래스는 이름과 달리 '링크드 리스트'가 아닌 **더블 링크드 리스트**로 구현되어 있다.

### LinkedList의 생성자와 메서드

|생성자 또는 메서드|설명|
|----|----|
|LinkedList()|LinkeList 객체를 생성|
|LinkList(Collection c)|주어진 컬렉션을 포함하는 LinkedList객체를 생성|
|boolean add(Object o)|지정된 객체(o)를 LinkedList의 끝에 추가, 저장에 성공하면 true, 실패하면 false|
|void add(int index, Object element)|지정된 위치(index)에 객체(element)를 추가|
|boolean addAll(Collection c)|주저진 컬렉션에 포함된 모든 요소를 LinkedList의 끝에 추가한다. 성공하면 true, 실패하면 false)
|void clear()|LinkedList의 모든 요소를 삭제|
|boolean contains(Object o)|지정된 객체가 LinkedList에 포함되어 있는지 알려줌|
|boolean containsAll(Collection c)|지정된 컬렉션의 모든 요소가 포함되어 있는지 알려줌|
|Object get(int index)|지정된 위치(index)의 객체를 반환|
|int indexOf(Object o)|지정된 객체가 저장된 위치(앞에서 몇 번째)를 반환|
|boolean isEmpty()|LinkedList가 비어 있는지 알려 준다. 비어있으면 true|
|Iterator iterator()|Iterator를 반환한다.|
|int lastIndexOf(Object o)|지정된 객체의 위치(index)를 반환(끝부터 역순검색)|
|ListIterator listIterator()|ListIterator를 반환한다.|
|ListIterator listIterator(int index)|지정된 위치에서부터 시작하는 ListIterator를 반환|
|Object remove(int index)|지정된 위치(index)의 객체를 LinkedList에서 제거|
|booleam remove(Object o)|지정된 객체를 LinkedList에서 제거, 성공하면 true, 실패하면 false|
|boolean removeAll(Collection c)|지정된 컬렉션의 요소와 일치하는 요소를 모두 삭제|
|boolean retainAll(Collection c)|지정된 컬렉션의 모든 요소가 포함되어 있는지 확인|
|Object set(int index, Object element)|지정된 위치(index)의 객체를 주어진 객체로 바꿈|
|int size()|LinkedList에 저장된 객체의 수를 반환|
|List subList(int fromIndex, int toIndex)|LinkedList의 일부를 List로 반환|
|Object[] toArray()|LinkedList에 저장된 객체를 배열로 반환|
|Object[] toArray(Object[] a)|LinkedList에 저장된 객체를 주어진 배열에 저장하여 반환|
|Object element()|LinkedList의 첫 번째 요소를 반환|
|boolean offer(Object o)|지정된 객체(o)를 LinkedList의 끝에 추가, 성공하면 true, 실패하면 false|
|Object peek()| LinkedList의 첫 번째 요소를 반환|
|Object poll()|LinkedList의 첫 번째 요소를 반환, LinkedList에서는 제거된다.|
|Object remove()|LinkedList의 첫 번째 요소를 제거|
|void addFirst(Object o)|LinkeList의 맨 앞에 객체(o)를 추가|
|void addLast(Object o)|LinkedList의 맨 끝에 객체(o)를 추가|
|Iterator descendingIterator()|역순으로 조회하기 위한 DescendingIterator를 반환|
|Object getFirst()|LinkedList의 첫번째 요소를 반환|
|Object getLast()|LinkedList의 마지막 요소를 반환|
|boolean offerFirst(Object o)|LinkedList의 맨 앞에 객체(o)를 추가, 성공하면 true|
|boolean offerLast(Object o)|LinkedList의 맨 끝에 객체(o)를 추가, 성공하면 true|
|Object peekFirst()|LinkedList의 첫번째 요소를 반환|
|Object peekLast()|LinkedList의 마지막 요소를 반환|
|Object pollFirst()|LinkedList의 첫번째 요소를 반환하면서 제거|
|Object pollLast()|LinkedList의 마지막 요소를 반환하면서 제거|
|Object pop()|removeFirst()와 동일|
|void push(Object o)|addFirst()와 동일|
|Object removeFirst()|LinkedList의 첫번째 요소를 제거|
|Object removeLast()|LinkedList의 마지막 묘소를 제거|
|boolean removeFirstOccurence(Object o)|LinkedList에서 첫번째로 일치하는 객체를 제거|
|boolean removeLastOccurence(Object o)|LinkedList에서 마지막으로 일치하는 객체를 제거|

>>LinkedList는 Queue인터페이스(JDK1.5)와 Deque인터페이스(JDK1.6)를 구현하도록 변경되었는데, 마지막 22개 메서드는 Queue인터페이스와 Deque 인터페이스를 구현하면서 추가된 것이다.

LinkedList 역시 List 인터페이스를 구현했기 때문에 ArrayList의 내부구현방법만 다를 뿐 제공하는 메서드의 종류와 기능은 거의 같다.

#### day13_14/ArrayListLinkedListTest.java

#### day13_14/ArrayListLinkedListTest2.java


- LinkedList는 불연속적으로 위치한 각 요소들이 서로 연결된 것이라 처음부터 n번째 데이터까지 차례대로 따라가야만 원하는 값을 얻을 수 있다.
- 따라서 LinkedList는 저장해야하는 데이터의 개수가 많아질수록 데이터를 읽어 오는 시간, 즉 접근시간(access time)이 길어진다는 단점이 있다.

#### ArrayList와 LinkedList 비교
|컬렉션|읽기(접근시간)|추가/삭제|비고|
|----|----|----|--------|
|ArrayList|빠르다|느리다|순차적인 추가삭제는 더 빠름.<br>비효율적인 메모리사용|
|LinkedList|느리다|빠르다|데이터가 많을수록 접근성이 떨어진다.|




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


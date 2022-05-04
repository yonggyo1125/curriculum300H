# 지네릭스
## 지네릭스란?
- 지네릭스는 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일시의 타입체크(compile-time type check)를 해주는 기능이다. 
- 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움이 줄어든다 
- 다룰 객체의 타입을 미리 명시해줌으로써 번거로운 형변환을 줄여준다.

```
class Box {
	Object item;
	
	void setItem(Object item) { 
		this.item = item; 
	}
	
	Object getItem() { 
		return item;
	}
}
```
지네릭 클레스로 변경
```
class Box<T> { // 지네릭 타입 T를 선언
	T item;
	
	void setItem(T item) { 
		this.item = item; 
	}
	
	T getItem() {
		return item;
	}
}
```
- Box<T>에서 T를 '타입 변수(type variable)'라 하며 Type의 첫 글자에서 따온 것이다.
- 타입 변수는 다른 것을 사용해도 되며, 의미 적으로 E(Element), K(Key), V(Value)등을 관례적으로 많이 사용한다.
	- 예) ArrayList<E>의 경우 타입 변수 E는 Element(요소)의 첫글자를 따서 사용
	- 예) Map<K,V>에서 K는 Key(키)를 의미하고, V는 Value(값)을 의미한다.
	- 기호의 종류만 다를 뿐 **'임의의 참조형 타입'**을 의미한다는 것은 모두 같다.

- 지네릭 클래스가 된 Box 클래스의 객체를 생성할 때는 다음과 같이 참조변수와 생성자에 T 대신에 사용될 실제 타입을 지정해주어야 한다.
```
Box<String> b = new Box<String>(); // 타입 T 대신, 실제 타입을 지정
b.setItem(new Object()); // 에러, String이외의 타입은 지정불가
b.setItem("ABC"); // OK, String 타입이므로 가능
String item = b.getItem(); // 형변환 필요없음
```
- 상기 코드에서 타입 T대신 String 타입을 지정해줬으므로, 지네릭 클래스 Box<T>는 다음과 같이 정의된 것과 같다.
```
class Box<String> { // 지네릭 타입을 String으로 지정
	String item;
	
	void setItem(String item) {
		this.item = item;
	}
	
	String getItem() {
		return item;
	}
}
```
- 지네릭이 도입되기 이전의 코드와 호환을 위해, 타입을 지정하지 않는 예전의 방식으로 객체를 생성하는 것이 허용된다. 다만 지네릭 타입을 지정하지 않아서 안전하지 않다는 경고가 발생한다.
```
Box b = new Box(); // OK, T는 Object로 간주한다.
b.setItem("ABC"); // 경고, unchecked or unsafe operation
b.setItem(new Object()); // 경고, unchecked or unsafe operation
```

- 하기와 같이 타입 변수 T에 Object 타입을 지정하면, 타입을 지정하지 않은 것이 아니라 알고 적은 것이므로 경고는 발생하지 않는다.
```
Box<Object> b = new Box<Object>();
b.setItem("ABC"); // 경고발생 안함 
b.setItem(new Object()); // 경고 발생 안함
```

### 지네릭스의 용어

Class Box<T> {}
- **Box<T>** : 지네릭 클래스, 'T의 Box' 또는 'T Box'라고 읽는다.
- **T** : 타입 변수 또는 타입 매개변수.(T는 타입문자)
- **Box** : 원시 타입(raw type)


Box<String> b = new Box<String>();
- 타입 매개변수에 타입을 지정하는 것을 **'지네릭타입 호출'**이라 한다.
- 지정된 타입 'String'을 **'매개변수화된 타입(parameterized type)'** 또는 **'대입된 타입'**이라고 한다.
- 컴파일 후에는 Box<String>과 Box<Integer>는 이들의 '원시타입'인 Box로 바뀐다.(즉, 지네릭 타입이 제거된다)


### 지네릭스의 제한
- 타입 매개변수를 가지는 참조 변수와 인스턴스는 타입 매개변수에 동일한 자료형을 입력해야 한다.
```
Box<Apple> appleBox = new Box<Apple>(); // OK, Apple 객체만 저장가능
Box<Grape> grapeBox = new Box<Grape>(); // OK, Grape 객체만 저장가능
```
- static멤버에 타입 변수 T를 사용할 수 없다.
	- T는 인스턴스 변수로 간주되기 때문에 모든 객체에 대해 동일하게 동작해야 하는 static 멤버에서는 사용할 수 없다.
	- static멤버는 타입 변수에 지정된 타입, 즉 대입된 타입의 종류에 관계없이 동일한 것이어야 하기 때문이다. 
	- 'Box<Apple>.item'과 'Box<Grape>.item'이 다른 것이어서는 안된다.
	
	```
	class Box<T> {
		static T item; // 에러
		static int compare(T t1, T t2) { ... } // 에러 
		...
 	}
	```
	
- 지네릭 타입의 배열을 생성하는 것도 허용하지 않는다.
	- 지네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만, 'new T[10]' 과 같이 배열을 생성하는 것은 불가
	- 지네릭 배열을 생성할 수 없는 것은 new 연산자 때문인데, 이 연산자는 컴파일 시점에 타입 T가 뭔지 정확히 알아야 한다.
	- 하기 코드에 정의된 Box<T>클래스를 컴파일하는 시점에서는 T가 어떤 타입이 될지 전혀 알수 없기 때문이다.(지네릭은 인스턴스가 만들어질때 T 타입이 결정된다.)
	- new 연산자와 같은 이유로 instanceof 연산자도 T를 피연산자로 사용할 수 없다.
	  

## 지네릭 클래스의 선언

## 지네릭 클래스의 객체 생성과 사용

## 제한된 지네릭 클래스

## 와일드 카드

## 지네릭 메서드



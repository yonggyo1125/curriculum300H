# 열거형(enums)
## 열거형이란?
- 자바는 C언어와 달리 열거형라는 것이 존재하지 않았으나 JDK1.5부터 추가되었다.
- 자바의 열거형은 C언어의 열거형보다 더 향상된 특징을 가진다.
- 값뿐만 아니라 타입까지 체크하여 타입에 안전하다
- 상수의 값이 바뀌면 해당 상수를 참조하는 모든 소스를 다시 컴파일 해야 한다. 그러나 열거형 상수를 사용하면, 기존의 소스를 다시 컴파일하지 않아도 된다.

```
class Card {
	enum Kind { CLOVER, HEART, DIAMOND, SPADE } // 열거형 Kind를 정의
	enum Value { TWO, THREE, FOUR }  // 열거형 Value를 정의
	
	final Kind kind; 
	final Value value;
}
```

## 열거형 정의와 사용
```
enum 열거형이름 { 상수명1, 상수명2, ... }
```
- 사용하는 방법은 **열거형 이름.상수명**이다 
- 클래스의 static 변수를 참조하는 것과 동일

```
enum Direction { EAST, SOUTH, WEST, NORTH }

class Unit {
	int x, y; // 유닛의 위치
	Direction dir; // 열거형을 인스턴스 변수로 선언 
	
	void init() {
		dir = Direction.EAST; // 유닛의 방향을 EAST로 초기화
	}
}
```

- 열거형 상수간의 비교에는 '=='를 사용할 수 있다.(상수의 주소를 비교하므로)
- '<', '>'와 같은 비교연산자는 사용할 수 없고 compareTo()는 사용가능하다.
```
	if (dir == DIRECTION.EAST) {
		x++;
	} else if (dir > Direction.WEST) { // 에러. 열거형 상수에 비교연산자 사용불가.
		... 
	} else if (dir.compareTo(Direction.WEST) > 0) { // compareTo()는 가능
		...
	}
```

- switch문 조건식에도 열거형을 사용할 수 있다.
- 주의할 점은 case문에 열거형의 이름은 적지 않고 상수의 이름만 적어야 한다.
```
	void move() {
		switch(dir) {
			case EAST: x++; // Direction.EAST라고 쓰면 안된다.
				break;
			case WEST: x--; 
				break;
			case SOUTH: y++;
				break;
			case NORTH: y--;
				break;
		}
	}
```

### 모든 열거형의 조상 - java.lang.Enum
```
Direction[] dArr = Direction.values();

for(Direction d : dArr) {
	System.out.printf("%s = %d%n", d.name(), d.ordinal());
}
```
- values()는 열거형의 모든 상수를 배열에 담아서 반환한다.
- 이 메서드는 모든 열거형이 가지고 있는 것으로 컴파일러가 자동으로 추가해 준다.
- ordinal()은 모든 열거형의 조상인 java.lang.Enum 클래스에 정의된 것으로 열거형 상수가 정의된 순서(0부터 시작)를 정수로 반환한다

#### Enum 클래스에 정의된 메서드

|메서드|설명|
|------|----------|
|Class\<E\> getDeclaringClass()|열거형의 Class 객체를 반환한다.|
|String name()|열거형 상수의 이름을 문자열로 반환한다.|
|int ordinal()|열거형 상수가 정의된 순서를 반환한다.(0부터 시작)|
|T valueOf(Class\<T\> enumType, String name)|지정된 열거형에서 name값과 일치하는 열거형 상수를 반환한다.|

#### 컴파일러가 자동으로 추가해주는 메서드
static E values()
static E valueOf(String name)
- 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해준다.
```
Direction d = Direction.valueOf("WEST");

System.out.println(d); // WEST
System.out.println(Direction.WEST == Direction.valueOf("WEST")); // true
```

#### day16/EnumEx1.java
```
package day16;

enum Direction { EAST, SOUTH, WEST, NORTH };

public class EnumEx1 {
	public static void main(String[] args) {
		Direction d1 = Direction.EAST;
		Direction d2 = Direction.valueOf("WEST");
		Direction d3 = Enum.valueOf(Direction.class, "EAST");
		
		System.out.println("d1=" + d1);
		System.out.println("d2=" + d2);
		System.out.println("d3=" + d3);
		
		System.out.println("d1==d2 ? " + (d1 == d2)); // false  
		System.out.println("d1==d3 ? " + (d1 == d3)); // true
		System.out.println("d1.equals(d3) ? " + d1.equals(d3)); // true
		// System.out.println("d2 > d3 ? " + (d1 > d3)); // 에러
		System.out.println("d1.compareTo(d3) ? " + (d1.compareTo(d3))); // 0
		System.out.println("d1.compareTo(d2) ? " + (d1.compareTo(d2)));
		
		switch(d1) {
			case EAST: // Direction.EAST라고 쓸 수 없다.
				System.out.println("The direction is EAST."); break;
			case SOUTH:
				System.out.println("The direction is SOUTH."); break;
			case WEST:
				System.out.println("The direction is WEST."); break;
			case NORTH:
				System.out.println("The direction is NORTH"); break;
			default:
				System.out.println("Invalid direction.");
		}
		
		Direction[] dArr = Direction.values();
		
		for(Direction d : dArr) {
			System.out.printf("%s=%d%n", d.name(), d.ordinal());
		}
	}
}

실행 결과

d1=EAST
d2=WEST
d3=EAST
d1==d2 ? false
d1==d3 ? true
d1.equals(d3) ? true
d1.compareTo(d3) ? 0
d1.compareTo(d2) ? -2
The direction is EAST.
EAST=0
SOUTH=1
WEST=2
NORTH=3
```

## 열거형에 멤버 추가하기





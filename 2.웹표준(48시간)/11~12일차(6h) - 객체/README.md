# 객체

## 객체 생성하기

자바 스크립트의 객체는 이름과 값을 한 쌍으로 묶은 집합입니다. 이름과 값이 한 쌍을 이룬 것을 프로퍼티라고 하고, 그것의 이름을 프로퍼티 이름 또는 키(key)라고 합니다.  값으로는 모든 데이터르 타입의 데이터(원시 값, 객체)를 저장할 수 있으며, 함수의 참조를 값으로 가진 프로퍼티는 메서드라는 이름으로 부릅니다.<br>
자바스크립트로 이러한 객체를 생성하려면 다음 세 가지 방법을 사용합니다.

- **객체 리터럴로 생성하는 방법**<br>
```javascript
var card = { suit: "하트", rank: "A" };
```

- **생성자로 생성하는 방법**<br>
```javascript
function Card(suit, rank) {
	this.suit = suit; 
	this.rank = rank;
}

var card = new Card("하트", "A");
```

- **Object.create로 생성하는 방법**<br>
```javascript
var card = Object.create(Object.prototype, {
	suit: {
		value: "하트",
		writable: true,
		enumerable: true,
		configurable: true
	},
	rank: {
		value: "A", 
		writable: true, 
		enumerable: true,
		configurable: true
	}
});
```

## 프로토타입 상속
자바스크립트는 프로토타입 상속에 기반을 둔 객체 지향 언어 입니다.

### 상속
- 상속(inheritance)이란 일반적으로 특정 객체가 다른 객체로부터 기능을 이어받는 것을 말합니다. 
- C++나 Java처럼 클래스를 이용하는 객체 지향 언어에서는 객체의 설계도라고 할 수 있는 클래스를 상속할 수 있습니다. 
- 반면 **자바스크립트에서는 클래스가 아닌 객체를 상속**합니다.
- 자바 스크립트의 상속은 **프로토타입 체인이라고 부르는 객체의 자료구조로 구현**되어 있으며, **프로토타입 상속**이라고 부릅니다.
- C++나 Java 등의 객체 지향 언어에서는 클래스를 상속하기 위한 구문이 별도로 마련되어 있지만 **자바스크립트**에서는 **생성자가 클래스의 역할**을 하지만 **생성자를 상속하기 위한 구문을 언어 차원에서 제공하지는 않습니다.** 
- 하지만 몇 가지 기법을 활용하면 상속과 비슷한 것을 흉내 낼 수 있습니다. 
- 또한 ECMAScript 6부터는 생성자의 정의를 더 간결하고 명료하게 표현할 수 있는 **클래스 구문**이 추가되었습니다.

### 상속을 하는 이유
- 이미 정의된 프로퍼티와 메서드의 코드를 재사용할 수 있고 새로운 기능을 추가해서 확장된 객체를 만들수 있습니다.
- 중복 코드를 작성하지 않아도 되므로 유지 보수성이 높은 프로그램을 만들 수 있습니다.

### 프로토타입 체인
#### 내부 프로퍼티 [[Prototype]]
- 모든 객체는 내부 프로퍼티[[Prototype]]을 가지고 있습니다.
- 이것은 함수 객체 prototype 프로퍼티와는 다른 객체입니다.
- ECMAScript 5까지는 사용자가 이 내부 프로퍼티 [[Prototype]]을 읽거나 쓸 수 없었지만, ECMAScript 6부터는 __proto__ 프로퍼티에 [[Prototype]]의 값이 저장됩니다.
- 현재의 주요 웹 브라우저는 __proto__ 프로퍼티를 지원합니다.

```javascript
var obj = {};
console.log(obj.__proto__); // -> Object {}
```
- 단 모든 자바스크립트 실행 환경이 __proto__ 프로퍼티를 지원하는 것은 아니므로 주의하세요.

#### 프로토타입 체인
- 객체의 __proto__ 프로퍼티는 그 객체에 상속을 해 준 부모 객체를 가리킵니다. 
- 객체는 __proto__ 프로퍼티가 가리키는 부모 객체의 프로퍼티를 사용할 수 있습니다.
```javascript
var objA = {
	name: "Tom",
	sayHello : function() { console.log("Hello! " + this.name); }
};

var objB = {
	name: "Huck"
};

objB.__proto__  = objA;
var objC = {};
objC.__proto__ = objB;

obj.sayHello();  // -> "Hello! Huck"
```

- 앞의 코드에서 등장한 객체 세 개는 __proto__ 프로퍼티를 사용한 연결 고리로 묶여 있습니다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image1.png)



## 프로퍼티(속성)

## 객체 잠그기

## 클래스 구문

## ECMAScript6+에 추가된 객체의 기능

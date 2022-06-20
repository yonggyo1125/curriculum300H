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

sayHello 프로퍼티는 다음과 같은 과정으로 찾아냅니다.

- objC.sayHello()가 호출되면 먼저 objC 자신이 sayHello라는 프로퍼티를 소유하고 있는지 확인합니다. 하지만 objC 안에서는 찾을 수 없습니다.
- 다음으로 objC.__proto__가 가리키는 objB가 sayHello라는 프로퍼티를 소유하고 있는지 확인합니다. 역시 objB 안에서도 찾을 수 없습니다.
- 이제 objB.__proto___.__proto__가 가리키는 objA가 sayHello라는 프로퍼티를 소유하고 잇는지 확인합니다. sayHello라는 프로퍼티를 찾았습니다. 이제 objA.sayHello를 사용합니다.

이번에는 objA.sayHello 코드 안에서 this.name을 검색해 봅니다.
- 먼저 objC 스스로가 name이라는 프로퍼티를 소유하고 있는지 확인합니다. 하지만 objC 안에서는 찾을 수 없습니다.
- 다음으로 objC.__proto__가 가리키는 objB가 name이라는 프로퍼티를 소유하고 있는지 확인합니다. name 프로퍼티를 찾았습니다. 이 값을 this.name 값으로 사용합니다.

이처럼 자신이 갖고 있지 않은 프로퍼티를 __proto__ 프로퍼티가 가리키는 객체를 차례대로 거슬러 올라가며 검색합니다. 이와 같은 객체의 연결 고리를 **프로토타입 체인**이라고 합니다.<br><br>
여기에서 객체의 __proto__ 프로퍼티가 가리키는 객체가 바로 상속을 해 준 객체이며, 이 객체를 그 객체의 **프로토타입**이라고 합니다. 객체는 자신이 가지고 잇지 않은 특성(프로퍼티와 메서드)을 프로토타입 객체에 위임(delegate)한다고 할 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image2.png)

이처럼 자바스크립트는 프로토타입 체인을 사용하여 객체의 프로퍼티를 다른 객체로 전파합니다. 이를 프로토타입 상속이라고 합니다. **프로토타입 상속**을 하는 객체 지향 언어를 가리켜 **프로토타입 기반 객체 지향 언어**라고 합니다.<br><br>
실제 프로그래밍을 할 때는 __proto__ 프로퍼티 값을 직접 입력해서 상속하지는 않습니다. 일반적으로는 다음과 같은 방법으로 상속합니다.
- 생성자로 객체를 생성할 때 생성자의 prototype 프로퍼티에 추가하는 방법
- Object.create 메서드로 상속을 받을 프로토타입을 지정하여 객체를 생성하는 방법

#### 프로토타입 가져오기
객체의 프로토타입은 Object.getPrototypeOf 메서드로 가져올 수 있습니다.

```javascript
function F() {}
var obj = new F();
console.log(Object,getPrototypeOf(obj));  // -> Object {}
```

물론 웹 브라우저에서는 obj.__proto__로도 프로토타입을 가져올 수 있지만 지원하지 않는 웹 브라우저도 있습니다. Object.getPrototypeOf 메서드는 ECMAScript 5 이상을 지원하는 자바스크립트로 실행 환경을 사용할 수 있습니다.<br><br>

ECMAScript 6부터는 객체의 프로토타입을 설정하는 메서드인 Object.setPrototypeOf 도 추가 되었습니다. 

### new 연산자의 역할
생성자를 new 연산자로 호출해서 인스턴스를 생성하면 내부적으로는 어떤 작업을 할까요? 다음은 new 연산자가 수행하는 내부적인 작업을 설명합니다.
```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
}

Circle.prototype.area = function() {
	return Math.PI * this.radius * this.radius;
};
```

Circle 생성자로 인스턴스를 생성할 때는 다음과 같이 new 연산자를 사용했습니다.
```javascript
var c = new Circle({x : 0, y: 0}, 2.0);
```
new 연산자로 Circle 생성자를 사용하면 내부적으로는 다음과 같은 작업을 수행합니다.
- (1) 빈 객체를 생성합니다.
```javascript
var newObj = {};
```
- (2) Circle.prototype을 생성된 객체의 프로토타입으로 설정합니다.
```javascript
newObj.__proto__ = Circle.prototype;
```
이때 Circle.prototype이 가리키는 값이 객체가 아니라면 Object.prototype을 프로토타입으로 설정합니다.
- (3) Circle 생성자를 실행하고 newObj를 초기화 합니다. 이때 this는 (1)로 생성한 객체로 설정합니다. 인수는 new 연산자와 함께 사용할 인수를 그대로 사용합니다.
```javascript
Circle.apply(newObj, arguments);
```
- (4) 완성된 객체를 결과값으로 반환합니다.
```javascript
return newObj;
```
단, Circle,. 생성자가 객체를 반환하는 경우에는 그 객체를 반환합니다.<br>
이때 (2)에서 생성자의 prototype 프로퍼티 값을 인스턴스의 __proto__ 프로퍼티 값으로 대입하는 부분에 주목하세요. 이를 이용해 인스턴스의 프로토타입 체인이 정의되며, 생성자로 생성한 모든 인스턴스가 생성자의 프로토타입 객체의 프로퍼티를 사용할 수 있게 됩니다. 이것이 prototype 프로퍼티의 역할입니다.<br><br>

이처럼 생성자를 new 연산자로 호출하면 **객체의 생성**, **프로토타입의 설정**, **객체의 초기화**를 수행합니다.

### 프로토타입 객체의 프로퍼티
함수를 정의하면 함수 객체는 기본적으로 prototype 프로퍼티를 갖게 됩니다. 그리고 이 prototype 프로퍼티는 프로토타입 객체를 가리키며, 이 프로토타입 객체는 기본적으로 constructor 프로퍼티와 내부 프로퍼티 [[Prototype]](__proto__)을 가지고 있습니다.

#### constructor 프로퍼티
constructor 프로퍼티는 함수 객체의 참조를 값으로 갖고 있습니다.
```javascript
function F() {};
console.log(F.prototype.constructor);  // -> Function F() {}
```
생성자와 생성자의 프로토타입 객체는 서로를 참조합니다. 정확히는 **생성자의 prototype 프로퍼티가 프로토타입 객체를 가리키며** **이 프로토타입 객체의 contructor 프로퍼티가 생성자를 가리키는 연결 고리**로 묶여 있습니다. 반면 **생성자로 생성한 인스턴스는 생성될 때의 프로토타입 객체의 참조만 가지고 있을 뿐 생성자와는 직접적인 연결 고리가 없습니다.**

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image3.png)

인스턴스가 어떤 생성자로 생성된 것인지 알아내는 방법으로 인스턴스가 가진 프로토타입의  constructor 프로퍼티 값을 확인하는 방법이 있습니다. 인스턴스는 프로토타입에서 constructor 프로퍼티를 상속받기 때문에 contructor 프로퍼티를 인스턴스 프로퍼티로 참조할 수 있습니다.
```javascript
function F() {};
obj = new F();
console.log(obj.constructor);  // -> Function F() {}
```

#### 내부 프로퍼티 [[Prototype]]
함수 객체가 가진 프로토타입 객체의 내부 프로퍼티 [[Prototype]]는 기본적으로 Object.prototype을 가리킵니다. 즉, 프로토타입 객체의 프로토타입은 Object.prototype 입니다.
```javascript
function F() {};
console.log(F.prototype.__proto__); // -> Object {} : Object.prototype
```
이 덕분에 생성자로 생성한 인스턴스가 Object.prototype의 프로퍼티를 사용할 수 있습니다. 또한 Object.prototype의 프로토타입은 nnull을 가리킵니다. 이와 같은 연결 고리를 정리해서 그림으로 표현하면 다음과 같습니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image4.png)

#### 프로토타입 객체의 교체 및 constructor 프로퍼티
생성자가 가진 prototype 프로퍼티 값을 새로운 객체로 교환할 때는 주의해야 합니다. 프로퍼티만 정의되어 있는 새로운 객체를 prototype 프로퍼티 값으로 대입하면 인스턴스와 생성자 사이의 연결 고리가 끊겨 버립니다. 그 객체에는 constructor 프로퍼티가 없기 때문입니다. 인스턴스와 생성자 사이의 연결 고리를 유지하려면 prototype으로 사용할 객체에 constructor 프로퍼티를 정의하고, 그 프로퍼티에 생성자의 참조를 대입해야 합니다.
```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
}
Circle.prototype = {
	constructor: Circle, // 생성자를 constructor로 대입
	area: function() { return Math.PI * this.radius * this.radius; }
};
var c = new Circle({x:0, y:0}, 2.0);
console.log(c.constructor); // -> Function Circle
console.log(c instanceof Circle); // true
``` 
이 코드에서는 인스턴스 c가 생성자 Circle로 생성한 것인지를 **instanceof 연산자**로 확인합니다.

#### 인스턴스 생성 후에 생성자의 프로토타입을 수정하거나 교체한 경우
인스턴스의 프로토타입은 생성자의 인스턴스를 생성할 때 가지고 있던 프로토타입 객체입니다. 인스턴스를 생성한 후에 생성자의 prototype 프로퍼티 값을 다른 객체로 교환해도 인스턴스의 프로토타입은 바뀌지 않습니다. 다시 말해 인스턴스의 프로퍼티는 생성되는 시점의 프로토타입에서 상속받습니다. **생성된 후에는 생성자의 프로토타입을 바꾸어도 교체한 객체로부터는 프로퍼티를 상속받지 않습니다.**
```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
}

var c = new Circle({x: 0, y: 0}, 2.0);
Circle.prototype = {
	constructor: Circle,
	area : function() { return Math.PI * this.radius * this.radius; }
};
c.area(); // Uncaught TypeError : c.area is not a function
```
하지만 **생성자가 기존에 가지고 있던 프로토타입 객체에 프로퍼티를 추가한 경우**에는 **생성자의 인스턴스 사이의 연결 고리가 끊기지 않습니다.** 따라서 생성자에서 정의한 프로퍼티를 인스턴스에서 사용할 수 있습니다.
```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
}
var c = new Circle({x: 0, y: 0}, 2.0);
Circle.prototype.area = function() {
	return Math.PI * this.radius * this.radius;
};
c.area(); // -> 12.566370614359172
```

### 프로토타입의 확인
특정 프로토타입 객체가  그 객체의 프로토타입 체인에 포함되어 있는지 확인하는 방법에는 instanceof 연산자를 사용하는 방법과 isPrototypeOf 메서드를 사용하는 방법이 있습니다.

#### instanceof 연산자
instanceof 연산자는 지정한 객체의 프로토타입 체인에 지정한 생성자의 프로토타입 객체가 포함되어 있는지 판정합니다. instanceof 연산자는 논리값을 반환하는 이항 연산자로 사용법은 다음과 같습니다.
```javascript
객체 instanceof 생성자
```

```javascript
function F() {};
var obj = new F();
console.log(obj instanceof F);  // -> true
console.log(obj instanceof Object);  // -> true
console.log(obj instanceof Date);  // -> false
```

이때 instanceof 연산자가 확인하는 것은 그 인스턴스가 해당 생성자로 생성되었는지 여부가 아닙니다. **instanceof 연산자는 인스턴스가 생성자의 프로토타입 객체를 상속받았는지 확**한다는 점에 유의하세요.

#### isPrototypeOf 메서드
isPrototypeOf 메서드는 특정 객체가 다른 객체의 프로토타입 체인에 포함되어 있는지 판정합니다. 사용법은 다음과 같습니다.
```javascript
프로토타입객체.isPrototypeOf(객체)
```

```javascript
function F() {};
var obj = new F();
console.log(F.prototype.isPrototypeOf(obj));  // -> true
console.log(Object.prototype.isPrototypeOf(obj)); // -> true
console.log(Date.prototype.isPrototypeOf(obj));  // -> false
```

### Object.prototype
Object.prototype의 메서드는 모든 내장 객체로 전파되며 모든 인스턴스에서 사용할 수 있습니다. 

####  Object 생성자
Object 생성자는 내장 생성자로 일반적인 객체를 생성합니다. Object 생성자를 인수 없이 실행하면 Object 생성자는 빈 객체를 생성합니다.

```javascript
var obj = new Object();
```

이는 객체 리터럴로 작성한 빈 객체와 완전히 같습니다.
```javascript
var obj = {};
```

인수에 값을 지정하면 그 값을 Object 객체로 변환한 인스턴스를 생성합니다.
```javascript
var obj = new Object("ABC");
```

Object 생성자는 new 없이 호출해도 new를 붙여서 호출했을 때와 같은 방식으로 동작합니다.
```javascript
var obj = Object(); // var obj = new Object()와 같음
```
Object 생성자는 객체를 생성하는 것보다 일반적인 객체를 조작하기 위한 메서드와 프로퍼티를 제공하고, Object.prototype으로 모든 내장 생성자 인스턴스에 사용할 수 있는 메서드를 제공하는 것에 의의가 있습니다. 

#### Object 생성자의 프로퍼티와 메서드

- Object 생성자 프로퍼티

|프로퍼티|설명|
|----|-------|
|prototype|Object 생성자의 프로토타입 객체를 저장한다.|

- Object 생성자의 메서드

|메서드|설명|
|----|---------|
|assign(target, ...)|첫 번째 인수로 지정한 객체에 두 번째 이후 인수로 지정한 객체가 소유한 모든 열거 가능한 String 또는 Symbol 프로퍼티를 추가해서 반환한다.|
|create(proto [, propertiesObject])|인수로 지정한 프로토타입 객체 또는 프로퍼티로 새로운 객체를 생성한다.|
|defineProperty(obj, prop, descriptor)|인수로 지정한 디스크립터 설정에 따라 프로퍼티를 객체에 추가한다.|
|defineProperties(obj, props)|인수로 지정한 디스크립터 설정에 따라 프로퍼티 여러 개를 객체에 추가한다.|
|freeze(obj)|인수로 지정한 객체의 프로퍼티를 변경하거나 삭제하지 못하게 동결한다.|
|getOwnPropertyDescriptor(obj, prop)|인수로 지정한 객체의 프로퍼티 디스크립터를 반환한다.|
|getOwnPropertyNames(obj)|인수로 지정한 객체가 소유한 모든 프로퍼티 이름을 배열로 반환한다.|
|getOwnPropertySymbols(obj)|인수로 지정한 객체가 소유한 모든 심벌 프로퍼티를 배열로 반환한다.|
|getPrototypeOf(obj)|인수로 지정한 객체의 프로토타입을 반환한다.|
|is(value1, value2)|첫 번째 인수와 두 번째 인수가 같은지 판정한다.|
|isExtensible(obj)|인수로 지정한 객체가 확장 가능한지 판정한다.|
|isFrozon(obj)|인수로 지정한 객체가 동결된 상태인지 판정한다.|
|isSealed(obj)|인수로 지정한 객체가 밀봉된 상태인지 판정한다.|
|keys(obj)|인수로 지정한 객체가 소유한 열거 가능한 프로퍼티 이름을 배열로 반환한다.|
|preventExtensions(obj)|인수로 지정한 객체의 확장을 금지한다.|
|seal(obj)|인수로 지정한 객체를 밀봉하여 프로퍼티 삭제를 금지한다.|
|setPrototypeOf(obj, prototype)|인수로 지정한 객체의 프로토타입을 설정한다.|


#### Object.prototype의 메서드

## 프로퍼티(속성)

## 객체 잠그기

## 클래스 구문

## ECMAScript6+에 추가된 객체의 기능

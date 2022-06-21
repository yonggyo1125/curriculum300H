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
자바스크립트의 내장 생성자가 소유한 프로토타입 객체의 프로토타입은 Object.prototype입니다. 따라서 내장 생성자로 생성한 모든 인스턴스는 Object.prototype의 메서드를 사용할 수 있습ㄴ디ㅏ.

|메서드 이름|설명|
|----|--------|
|hasOwnProperty(key)|호출한 객체가 문자열 key를 이름으로 가진 프로퍼티를 소유하는지를 뜻하는 논리값을 반환한다.|
|isPrototypeOf(obj)|호출한 객체가 인수 obj에 지정한 객체의 프로토타입(인자)를 뜻하는 논리값을 반환한다.|
|propertyIsEnumerable(key)|호출한 객체가 문자열 key를 이름으로 가진 프로퍼티를 열거할 수 있는지를 뜻하는 논리값을 반환한다. 이 메서드를 호출한 객체가 소유한 프로퍼티만 판정하며 프로토타입의 프로퍼티는 판정하지 않는다.|
|toString()|호출한 객체를 뜻하는 문자열을 반환한다.|
|toLocaleString()|toString 메서드와 같다.|
|valueOf()|호출한 객체의 원시 값을 반환한다.|

예를 들어 Date 생성자로 객체를 생성해 보겠습니다.
```javascript
var day = new Date();
```
이때 인스턴스 day의 프로토타입은 Date.prototype이므로 인스턴스 day에서는 Date.prototype의 프로퍼티의 메서드를 사용할 수 있습니다.
```javascript
console.log()day.getMonth()); // -> 6
```
Date.prototype의 프로토타입은 Object.prototype으로 되어 있습니다. 따라서 인스턴스 day에서 Object.prototype의 메서드와 프로퍼티를 사용할 수 있습니다.
```javascript
console.log(day.hasOwnProperty("length")); // false
```
이처럼 내장 생성자의 모든 인스턴스는 Object.prototype의 프로퍼티와 메서드를 상속하며, Object.prototype의 프로토타입은 null을 가리킵니다. 즉, Object.prototype은 인스턴스에서 프로토타입 체인을 따라 거슬러 올라갈 수 있는 마지막 단계의 객체입니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image5.png)

앞의 그림에서 Date.prototype과 Object.prototype에는 이름이 같은 메서드(toString과 valueOf)가 있지만 프로토타입 체인에서는 호출한 인스턴스와 가까운 Date.prototype의 메서드를 사용합니다.

### Object.create로 객체 생성하기
Object.create 메서드를 사용하면 명시적으로 프로토타입을 지정해서 객체를 생성할 수 있습니다. 이 메서드를 활용하면 가장 간단하게 상속을 표현할 수 있습니다.<br>
Object.create 메서드의 첫 번째 인수는 생성할 객체의 프로토타입입니다. 두 번째 인수를 지정하면 생성할 객체의 프로퍼티도 지정할 수 있습니다. 두 번째 인수는 선택 사항입니다.

```javascript
var person1 = {
	name: "Tom",
	sayHello: function() { console.log("Hello! " + this.name); }
};

var person2 = Object.create(person1);
person2.name = "Huck";
person2.sayHello(); // -> Hello! Huck
```

person2는 person1의 name 프로터티와 sayHello 메서드를 상속받았습니다. 하지만 person2 객체는 name 프로퍼티를 가지고 있으므로 person2는 person2의 name 프로퍼티 값을 사용합니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image6.png)

인수에 null을 넘기면 프로토타입이 없는 객체를 생성할 수 있습니다.
```javascript
var blankObject = Object.create(null);
```
이 객체는 프로토타입도 프로퍼티도 없는 이른바 백지 상태의 객체입니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image7.png)

이를 활용하면 순수한 프로퍼티 집합(해시 테이블)을 만들 수 있습니다. 그러나 이 객체는 Object.prototype을 상속받지 않았으므로 toString()이나 valueOf() 등의 기본적인 메서드조차 사용할 수 없습니다.<br><br>
객체 리터럴로 생성한 빈 객체 ({})와 똑같은 객체를 생성하려면 인수로 Object.prototype을 넘깁니다.
```javascript
var obj = Object.create(Object.prototype);
```
## 접근자 프로퍼티
접근자 프로퍼티를 사용하면 프로퍼티를 읽고 쓸 때 원하는 작업을 자동으로 처리할 수 있습니다.

### 프로퍼티의 종류
- **데이터 프로퍼티** : 값을 저장하기 위한 프로퍼티
- **접근자 프로퍼티** : 값이 없음. 프로퍼티를 읽거나 쓸 때 호출하는 함수를 값 대신에 지정할 수 있는 프로퍼티

> 지금까지 설명한 모든 프로퍼티는 데이터 프로퍼티입니다.

### 접근자 프로퍼티
- **접근자**란 객체 지향 프로그래밍에서 객체가 가진 프로퍼티 값을 객체 바깥에서 읽거나 쓸 수 있도록 제공하는 메서드를 말합니다. 
- 객체의 프로퍼티를 객체 바깥에서 직접 조작하는 행위는 데이터의 유지 보수성을 해치는 주요한 원인입니다. 
- 접근자 프로퍼티를 사용하면 데이터를 부적절하게 변경하는 것을 막고 특정 데이터를 외부로부터 숨길 수 있으며 외부에서 데이터를 읽으려고 시도할 떄 적절한 값으로 가공해서 넘길 수 있습니다.
- 자바스크립트의 접근자 프로퍼티는 객체에 접근자를 정의할 수 있게 합니다. 
- 접근자 프로퍼티를 사용하여 프로퍼티를 읽고 쓸 수 있게 하면 프로그램의 유지 보수성을 높일 수 있습니다.
<br>
- 접근자 프로퍼티 하나에 대해 그 프로퍼티를 읽을 때 처리를 담당하는 <b>게터 함수(getter)</b>와 쓸 때의 처리를 담당하는 <b>세터 함수(setter)</b>를 정의할 수 있습니다.
- 접근자 프로퍼티는 getter와 setter 중에서 하나만 정의할 수도 있지만 모두 정의할 수도 있습니다.

```javascript
var person = {
	_name: "Tom",
	get name() {
		return this._name;
	},
	set name(value) {
		var src = value.charAt(0).toUpperCase() + value.substring(1);
		this._name = str;
	}
};
console.log(person.name); // true
person.name = "huck";  // 접근자 프로퍼티에 값을 대입한다.
console.log(person.name);  // -> Huck
```
- 이 예제에서는 name이라는 이름의 접근자 프로퍼티를 정의합니다. 접근자 프로퍼티 name은 데이터 프로퍼티 \_name의 값을 읽고 쓰는 일을 담당하고 있습니다. 같은 값을 쓸 때는 문자열의 첫 글자를 대문자로 바꾼 후에 \_name 프로퍼티에 대입합니다.
- 접근자 프로퍼티에 getter와 setter를 정의하려면 function 키워드 대신 **get이나 set 키워드**를 사용한 함수를 작성합니다.
- getter에는 인수가 없고 setter는 인수를 한 개 받습니다. getter와 setter는 일반 함수와 마찬가지로 중괄호 안에 모든 처리를 작성할 수 있습니다. 
- 접근자 프로퍼티의 값을 읽으려모고 시도하면 getter가 호출되고 값을 쓰려고 시도하면 setter가 호출됩니다.
- getter가 없는 접근자 프로퍼티를 읽으려고 시도하면 undefined가 반환됩니다. 
- setter가 없는 접근자 프로퍼티를 쓰려고 시도하면 아무것도 실행되지 않으며 제어권이 곧장 호출자에게 되돌아옵니다.
- Strict 모드에서 setter가 없는 접근자 프로퍼티를 쓰려고 시도하면 오류가 발생합니다.
- 이 예제에서는 접근자 프로퍼티로 데이터 프로퍼티 \_name을 읽고 쓰지만 다른 변수나 객체도 접근자 프로퍼티를 읽거나 쓸 수 있습니다. 또한 getter와 setter는 데이터 프로퍼티와 일기와 쓰기를 비롯한 다른 작업도 할 수 있습니다.
- 접근자 프로퍼티 꼬한 데이터 프로퍼티와 마찬가지로 delete 연산자로 삭제할 수 있습니다.
```javascript
delete person.name;
```

### 데이터의 캡슐화
접근자 프로퍼티를 이용해서 간접적으로 데이터 프로퍼티를 읽거나 쓸수 있게 만들었지만 여전히 데이터 프로퍼티를 직접 읽고 쓸 수 있습니다. **즉시 실행 함수로 클로저를 생성하면 데이터를 객체 외부에서 읽고 쓸 수 없도록 숨기고 접근자 프로퍼티로만 읽고 쓰도록 만들 수 있습니다.**
```javascript
var person = (function() {
	var _name = "Tom";  // 프라이빗 변수
	return {
		get name() {
			return _name;
		},
		set name(value) {
			var str = value.charAt(0).toUpperCase() + value.substring(1);
			_name = str;
		}
	};
})();
console.log(person.name);  // -> Tom
person.name = "Huck"; 	// 접근자 프로퍼티에 값을 대입한다.
console.log(person.name);  // -> Huck
```
이 코드의 변수 \_name은 즉시 실행 함수의 지역 변수이므로 함수 바깥에서 읽거나 쓸 수 없습니다.

## 프로퍼티의 속성
프로퍼티는 프로퍼티의 이름과 값이 한 쌍을 이룬 것이지만 이와는 별개로 내부적인 속성을 몇 개 더 가지고 있습니다. ECMAScript 3에서도 열거가 가능한지를 뜻하는 내부 속성은 있었지만 그것을 수정하는 수단은 제공하지 않았습니다. ECMAScript 5부터는 쓰기 기능 여부와 재정 가능 여부를 뜻하는 내부 속성 두 개, 접근자 프로퍼티, 내부 속성을 조작할 수 있는 수단이 추가되었습니다.<br><br>

프로퍼티는 다음과 같은 세 가지 내부 속성을 가지며 각 속성 값은 논리값입니다.

#### 쓰기 가능(writable)
프로퍼티에 쓰기가 가능한지를 뜻하는 속성입니다. 이 속성 값이 true면 프로퍼티 값을 수정할 수 있습니다.

#### 열거 가능(enumerable)
프로퍼티가 for/in 문이나 Object.keys 등의 반복문으로 찾을 수 있는 대상인지를(열거 가능) 뜻하는 속성입니다.

#### 재정의 가능(configurable)
 프로퍼티 내부 속성을 수정할 수 있는지를 뜻하는 속성입니다. 이 속성 값이 true면 delete 연산자로 그 프로퍼티를 제거할 수 있으며 프로퍼티가 가진 내부 속성을 수정할 수 있습니다.<br><br>
 
- 객체에 새로운 프로퍼티를 추가하면 기본적으로 그 프로퍼티의 기본 속성이 <b>쓰기 가능/열거 가능/재정의 가능</b>으로 설정됩니다.
- 내장 생성자가 가지고 있는 프로토타입 객체의 프로퍼티 대부분의 내장 속성은 <b>쓰기 가능/열거 불가능/재정의 가능</b> 입니다.
- **데이터 프로퍼티** :  값(value), 쓰기 가능(writable), 열거 가능(enumerable), 재정의 가능(configurable)이라는 네 개의 속성을 갖습니다.
- **접근자 프로퍼티** : 읽기(get), 쓰기(set), 열거 가능(enumerable), 재정의 가능(configurable)이라는 네 개의 속성을 갖습니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image8.png)

### 프로퍼티 디스크립터와 프로퍼티를 읽고 쓰는 메서드
프로퍼티 속성은 **프로퍼티 디스크립터**(프로퍼티 기술자)로 설정할 수 있습니다.

#### 프로퍼티 디스크립터
프로퍼티 디스크립터는 프로퍼티의 속성 값을 뜻하는 객체입니다. 이 객체가 가진 프로퍼티 이름은 프로퍼티가 가진 속성 이름과 같습니다.<br>
- **데이터 프로퍼티의 프로퍼티 디스크립터**는 다음 네 개의 프로퍼티를 가진 객체입니다.
```javascript
{
	value: 프로퍼티의 값,
	writable: 논리값,
	enumerable: 논리값,
	configurable: 논리값
}
```
- **접근자 프로퍼티의 프로퍼티 디스크립터**는 다음 네 개의 프로퍼티를 가진 객체입니다.
```javascript
{
	get: getter 함수값,
	set: setter 함수값,
	enumerable: 논리값,
	configurable: 논리값
}
```

#### 프로퍼티 디스크립터 가져오기 : Object.getOwnPropertyDescriptor
- Object.getOwnPropertyDescriptor 메서드는 객체 프로퍼티의 프로퍼티 디스크립터를 가져옵니다. 
- 첫 번째 인수는 객체의 참조이고 두 번째 인수는 프로퍼티 이름을 뜻하는 문자열입니다. 
```javascript
var tom = { name : "Tom" };
Object.getOwnPropertyDescriptor(tom, "name");
// -> {value: "Tom", writable: true, enumerable: true, configurable: true}
```
- 프로토타입으로 상속받은 프로퍼티나 없는 프로퍼티를 지정하면 undefined를 반환합니다.
```javascript
Object.getOwnPropertyDescriptor({}, "name");  // -> undefined
Object.getOwnPropertyDescriptor(tom, "toString");  // -> undefined
```
이처럼 Object.getOwnPropertyDescriptor 메서드는 그 객체의 프로퍼티 디스크립터만 가져올 수 있습니다.

#### 객체의 프로퍼티 설정하기 : Object.defineProperty
- Object.defineProperty 메서드는 객체의 프로퍼티에 프로퍼티 디스크립터를 설정합니다. 
- 첫 번째 인수는 객체의 참조, 두 번째 인수는 프로퍼티의 이름을 뜻하는 문자열, 세 번째 인수는 프로퍼티 디스크립터의 참조입니다.
- 실행 후에는 수정한 객체의 참조를 반환합니다.
```javascript
var obj = {};
Object.defineProperty(obj, "name", {
	value: "Tom", 
	writable: true,
	enumerable: false,
	configurable: true
});
Object.getOwnPropertyDescriptor(obj, "name");
// -> {value: "Tom", writable: true, enumerable: false, configurable: true}
```
이 메서드를 사용할 때 프로퍼티 디스크립터의 각 프로퍼티는 생략할 수 있습니다.

## 객체 잠그기

## 클래스 구문

## ECMAScript6+에 추가된 객체의 기능

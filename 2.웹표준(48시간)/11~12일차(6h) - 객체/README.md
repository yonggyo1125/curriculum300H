# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1lPxIUrW6dV_Geciy4M7lWA6Ot1jWyAUJ?usp=sharing)

# 객체

## 객체 생성하기

자바 스크립트의 객체는 이름과 값을 한 쌍으로 묶은 집합입니다. 이름과 값이 한 쌍을 이룬 것을 프로퍼티라고 하고, 그것의 이름을 프로퍼티 이름 또는 키(key)라고 합니다.  값으로는 모든 데이터 타입의 데이터(원시 값, 객체)를 저장할 수 있으며, 함수의 참조를 값으로 가진 프로퍼티는 메서드라는 이름으로 부릅니다.<br>
자바스크립트로 이러한 객체를 생성하려면 다음 세 가지 방법을 사용합니다.

- **객체 리터럴로 생성하는 방법**

```javascript
var card = { suit: "하트", rank: "A" };
```

- **생성자로 생성하는 방법**

```javascript
function Card(suit, rank) {
	this.suit = suit; 
	this.rank = rank;
}

var card = new Card("하트", "A");
```

- **Object.create로 생성하는 방법**

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
- ECMAScript 5까지는 사용자가 이 내부 프로퍼티 [[Prototype]]을 읽거나 쓸 수 없었지만, ECMAScript 6부터는 \_\_proto\_\_ 프로퍼티에 [[Prototype]]의 값이 저장됩니다.
- 현재의 주요 웹 브라우저는 \_\_proto\_\_ 프로퍼티를 지원합니다.

```javascript
var obj = {};
console.log(obj.__proto__); // -> Object {}
```

- 단 모든 자바스크립트 실행 환경이 \_\_proto\_\_ 프로퍼티를 지원하는 것은 아니므로 주의하세요.

#### 프로토타입 체인
- 객체의 \_\_proto\_\_ 프로퍼티는 그 객체에 상속을 해 준 부모 객체를 가리킵니다. 
- 객체는 \_\_proto\_\_ 프로퍼티가 가리키는 부모 객체의 프로퍼티를 사용할 수 있습니다.

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

- 앞의 코드에서 등장한 객체 세 개는 \_\_proto\_\_ 프로퍼티를 사용한 연결 고리로 묶여 있습니다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image1.png)

sayHello 프로퍼티는 다음과 같은 과정으로 찾아냅니다.

- objC.sayHello()가 호출되면 먼저 objC 자신이 sayHello라는 프로퍼티를 소유하고 있는지 확인합니다. 하지만 objC 안에서는 찾을 수 없습니다.
- 다음으로 objC.\_\_proto\_\_가 가리키는 objB가 sayHello라는 프로퍼티를 소유하고 있는지 확인합니다. 역시 objB 안에서도 찾을 수 없습니다.
- 이제 objB.\_\_proto\_\_.\_\_proto\_\_가 가리키는 objA가 sayHello라는 프로퍼티를 소유하고 잇는지 확인합니다. sayHello라는 프로퍼티를 찾았습니다. 이제 objA.sayHello를 사용합니다.

이번에는 objA.sayHello 코드 안에서 this.name을 검색해 봅니다.
- 먼저 objC 스스로가 name이라는 프로퍼티를 소유하고 있는지 확인합니다. 하지만 objC 안에서는 찾을 수 없습니다.
- 다음으로 objC.\_\_proto\_\_가 가리키는 objB가 name이라는 프로퍼티를 소유하고 있는지 확인합니다. name 프로퍼티를 찾았습니다. 이 값을 this.name 값으로 사용합니다.

이처럼 자신이 갖고 있지 않은 프로퍼티를 \_\_proto\_\_ 프로퍼티가 가리키는 객체를 차례대로 거슬러 올라가며 검색합니다. 이와 같은 객체의 연결 고리를 **프로토타입 체인**이라고 합니다.<br><br>
여기에서 객체의 \_\_proto\_\_ 프로퍼티가 가리키는 객체가 바로 상속을 해 준 객체이며, 이 객체를 그 객체의 **프로토타입**이라고 합니다. 객체는 자신이 가지고 있지 않은 특성(프로퍼티와 메서드)을 프로토타입 객체에 위임(delegate)한다고 할 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image2.png)

이처럼 자바스크립트는 프로토타입 체인을 사용하여 객체의 프로퍼티를 다른 객체로 전파합니다. 이를 프로토타입 상속이라고 합니다. **프로토타입 상속**을 하는 객체 지향 언어를 가리켜 **프로토타입 기반 객체 지향 언어**라고 합니다.<br><br>
실제 프로그래밍을 할 때는 \_\_proto\_\_ 프로퍼티 값을 직접 입력해서 상속하지는 않습니다. 일반적으로는 다음과 같은 방법으로 상속합니다.
- 생성자로 객체를 생성할 때 생성자의 prototype 프로퍼티에 추가하는 방법
- Object.create 메서드로 상속을 받을 프로토타입을 지정하여 객체를 생성하는 방법

#### 프로토타입 가져오기
객체의 프로토타입은 Object.getPrototypeOf 메서드로 가져올 수 있습니다.

```javascript
function F() {}
var obj = new F();
console.log(Object,getPrototypeOf(obj));  // -> Object {}
```

물론 웹 브라우저에서는 obj.\_\_proto\_\_로도 프로토타입을 가져올 수 있지만 지원하지 않는 웹 브라우저도 있습니다. Object.getPrototypeOf 메서드는 ECMAScript 5 이상을 지원하는 자바스크립트로 실행 환경을 사용할 수 있습니다.<br><br>

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
이때 (2)에서 생성자의 prototype 프로퍼티 값을 인스턴스의 \_\_proto\__ 프로퍼티 값으로 대입하는 부분에 주목하세요. 이를 이용해 인스턴스의 프로토타입 체인이 정의되며, 생성자로 생성한 모든 인스턴스가 생성자의 프로토타입 객체의 프로퍼티를 사용할 수 있게 됩니다. 이것이 prototype 프로퍼티의 역할입니다.<br><br>

이처럼 생성자를 new 연산자로 호출하면 **객체의 생성**, **프로토타입의 설정**, **객체의 초기화**를 수행합니다.

### 프로토타입 객체의 프로퍼티
함수를 정의하면 함수 객체는 기본적으로 prototype 프로퍼티를 갖게 됩니다. 그리고 이 prototype 프로퍼티는 프로토타입 객체를 가리키며, 이 프로토타입 객체는 기본적으로 constructor 프로퍼티와 내부 프로퍼티 \[\[Prototype\]\](\_\_proto\_\_)을 가지고 있습니다.

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
이 메서드를 사용할 때 프로퍼티 디스크립터의 각 프로퍼티는 생략할 수 있습니다. 디스크립ㅌ어의 일부 프로퍼티를 생략한 후에 특정 객체에 새로운 프로퍼티를 추가하면 새로운 프로퍼티의 속성 값 중에서 프로퍼티 디스크립터에서 생략한 프로퍼티에 대응하는 속성 값이 false 또는 undefined로 설정됩니다. 디스크립터에서 일부 프로퍼티를 생략한 후에 특정 객체가 갖고 있는 프로퍼티를 수정하면 그 프로퍼티의 속성 값 중에서 프로퍼티 디스크립터에서 생략한 프로퍼티에 대응하는 속성 값은 수정하지 않습니다.<br><br>

다음은 프로퍼티의 쓰기 가능 속성을 바꾸는 예입니다.

```javascript
var person = { name: "Tom" };
Object.defineProperty(person, "name", {
	writable: false,
});
Object.getOwnPropertyDescriptor(person, "name");
// -> {value: "Tom", writable: false, enumerable: true, configurable: true}
person.name = "Huck";
console.log(person.name); // -> Tom
```

이 코드는 Person 객체를 생성한 후에 name 프로퍼티를 수정할 수 없게 만듭니다. 그러면 person.name에 값을 대입해도 무시됩니다.<br><br>

다음은 프로퍼티의 열거 가능 속성을 바꾸는 예입니다.

```javascript
var person = {
	name: "Tom",
	age: 17,
	sayHello: function() { console.log("Hello! " + this.name); }
};

Object.defineProperty(person, "sayHello", {enumerable: false});
for(var p in person) console.log(p);  // name, age ... 순서대로 표시됨
```

이 코드는 객체 person의 프로퍼티인 sayHello를 열거할 수 없도록 설정합니다. 그러면 sayHello는 열거할 수 없는 프로퍼티가 되므로 for/in 문을 사용해서 person의 프로퍼티를 열거해도 sayHello는 열거하지 않습니다. 또한 객체 person이 상속받은 Object.prototype 프로퍼티도 열거할 수 없습니다.<br><br>

다음은 프로퍼티의 재정의 가능 속성을 바꾸는 예입니다.

```javascript
var person = { name: "Tom", age: 17, sex: "남" };
Object.defineProperty(person, "age", {configurable: false});
delete person.age;
console.log(person.age); // -> 17
Object.defineProperty(person, "age", {enumerable: false});
// -> Uncaught TypeError: Cannot redefine property: age
Object.defineProperty(person, "age", {writable: false});
```

앞에서 예로 든 객체 person의 프로퍼티 중에서 age를 다시 정의할 수 없도록 설정합니다. 그러면 delete 문으로 person.age를 삭제하라는 명령이 무시됩니다.  또한 age의 enumerable의 configurable 속성을 바꾸려고 시도하면 오류가 발생합니다. 단, writable 속성만큼은 true일 때 false로 바꿀 수 있습니다. 즉, 한 번 configurable 속성 값을 false로 바꾸면 writable 속성 값이 true일 때 false로 바꿀 수 있을 뿐, 그 외의 내부 속성 값은 바꿀 수 없게 된다는 점에 유의하세요. 특히 configurable 속성 값은 true로 되돌릴 수 없습니다.

#### 객체의 프로퍼티 속성 여러 개를 한꺼번에 설정하기 : Object.defineProperties

Object.defineProperties 메서드는 객체가 가진 프로퍼티 여러 개에 각각의 프로퍼티 디스크립터를 설정하여 첫 번째 인수는 객체의 참조입니다. 두 번째 인수는 새롭게 설정 또는 변경하고자 하는 프로퍼티 이름이 키로 지정된 프로퍼티 여러 개가 모인 객체이며, 각 키 값은 프로퍼티 디스크립터의 참조입니다. 실행 후에는 수정된 객체의 참조를 반환합니다.

```javascript
var person = Object.defineProperties({}, {
	_name: {
		value: "Tom",
		writable: true,
		enumerable: true,
		configurable: true
	},
	name: {
		get: function() { return this._name; },
		set: function(value) {
			var str = value.charAt(0).toUpperCase() + value.substring(1);
			this._name = str;
		},
		enumerable: true,
		configurable: true
	}
});
Object.getOwnPropertyDescriptor(person, "name");
// -> {enumerable: true, configurable: true}
```

### Object.create의 두 번째 인수

앞서 Object.create 메서드의 첫 번째 인수로 프로포타입을 넘겨서 객체를 생성하는 방법을 배웠습니다. 이번에는 새로 생성할 객체가 포함할 프로퍼티 목록을 Object.create 메서드의 두 번째 인수로 넘겨서 새로운 객체를 생성하는 방법을 소개합니다. 두 번째 인수로는 Object.defineProperties 메서드의 두 번째 인수와 마찬가지로 프로퍼티 디스크립터를 넘깁니다.

```javascript
var group = {
	groupName: "Tennis circle",
	sayGroupName: function() { console.log("belong to " + this.groupName); }
};

var person = Object.create(group, {
	name: {
		value: "Tom",
		writable: true,
		enumerable: true,
		configurable: true
	},
	age: {
		value: 10,
		writable: true,
		enumerable: true,
		configurable: true,
	},
	sayName: {
		value: function() { console.log("I'm " + this.name); },
		writable: true,
		enumerable: false, 
		configurable: true
	}
});
console.log(person);  // -> Object {name: "Tom", age: 18}
console.log(person.groupName);  // -> Tennis circle
person.sayGroupName();  // -> belong to Tennis circle
person.sayName();  // -> I'm Tom
```
이 코드에서 person 객체의 프로토타입으로 group 객체를 사용합니다. 따라서 person 객체가 group 객체의 groupName 프로퍼티와  sayGroupName 메서드를 상속받습니다. person 객체는 name 프로퍼티의 age 프로퍼티와 sayName 메서드를 자신의 것으로 소유합니다.<br><br>
이처럼 Object.create 메서드를 사용하면 프로토타입, 프로퍼티 값, 프로퍼티 속성을 모두 설정한 객체를 생성할 수 있습니다.

## 프로퍼티가 있는지 확인하기

### in 연산자

in 연산자는 객체 안에 지명한 프로퍼티가 있는지 검색하며, 그 검색 대상은 **그 객체가 소유한 프로퍼티와 상속받은 프로퍼티 모두**입니다.

```javascript
var person = { name : "Tom" };
console.log("name" in person);  // -> true : 이 객체가 소유한 프로퍼티
console.log("age" in person);  // -> false : 프로퍼티가 없음
console.log("toString" in person); // -> true : person은 toString을 상속받았음 
```

### hasOwnProperty 메서드

hasOwnProperty 메서드는 지명한 프로퍼티가 **그 객체가 소유한 프로퍼티면 true를 반환**하고 **상속받은 프로퍼티면 false를 반환**합니다.

```javascript
var person = { name: "Tom" };
console.log(person.hasOwnProperty("name"));  // -> true : 이 객체가 소유한 프로퍼티
console.log(person.hasOwnProperty("toString")); // -> false : 상속받은 프로퍼티
```
앞 코드에서 name 프로퍼티는 person 객체의 소유이므로 hasOwnProperty 메서드가 true를 반환합니다. 하지만 toString 메서드는 Object.prototype에서 상속받은 메서드이며 person 객체에도 toString 메서드가 없기 때문에 hasOwnProperty 메서드가 false를 반환합니다.

### propertyIsEnumerable 메서드

propertyIsEnumerable 메서드는 지정한 프로퍼티가 **그 객체가 소유한 프로퍼티이며 열거할 수 있을 때 true를 반환**합니다.

```javascript
var person1 = { name: "Tom", age: 17 };
var person2 = Object.create(person1);
person2.name = "Huck";
console.log(person2.propertyIsEnumerable("name"));  // -> true : 이 객체가 소유한 열거 가능 프로퍼티
console.log(person2.propertyIsEnumerable("age"));  // -> false : 상속받은 프로퍼티
console.log(Object.prototype.propertyIsEnumerable("toString"));  // -> false : 열거할 수 없는 프로퍼티
```

## 프로퍼티의 열거

### for/in 문
for/in문은 객체와 객체의 프로토타입 체인에서 열거할 수 있는 프로퍼티를 찾아내어 꺼내는 반복문입니다.

```javascript
var person1 = { name: "Tom", age: 17 };
var person2 = Object.create(person1);
person2.name = "Huck";
for(var p in person2) console.log(p);   // name, age ... 순서대로 표시됨
```
앞 코드의 person2 객체가 사용할 수 있는 프로퍼티는 이 객체가 소유한 프로퍼티인 name, person1에서 상속받은 프로퍼티인 name과 age입니다.  물론 Object.prototype에서 상속받은 toString 등의 프로퍼티도 사용할 수 있습니다. 이때 person2 객체는 person1 객체에서 상속받은 name 프로퍼티 대신 객체 자신이 소유한 name 프로퍼티를 사용하게 됩니다. Object.prototype의 프로퍼티는 열거할 수 없으므로 for/in 문으로는 찾아낼 수 없습니다.<br><br>

```javascript
var a = [0, 2, 4, 6, 8];
a.name = "evens";
for(var i in a) console.log(i);  // 0, 1, 2, 3, 4, name의 순서대로 표시됨.
```

자바스크립트의 배열은  Array타입의 객체이며 Array 객체의 각 요소는 프로퍼티입니다. 즉, 요소의 인덱스는 프로퍼티의 이름이며 요소 값은 프로퍼티 값입니다. 또한 배열은 Array.prototype에서 상속받은 length, push 등의 프로퍼티와 Object.prototype에서 상속받은 프로퍼티를 사용할 수 있습니다. 그러나 Array.prototype과 Object.prototype의 프로퍼티는 열거할 수 없기 때문에 for/in 문으로는 찾아낼 수 없습니다.

### Object.keys 메서드

Object.keys. 메서드는 지정한 객체가 소유한 프로퍼티 중에서 열거할 수 있는 프로퍼티 이름만 배열로 만들어 반환합니다.

```javascript
var group = { groupName: "Tennis circle" };
var person = Object.create(group);
person.name = "Tom";
person.age = 17;
person.sayHello = function() { console.log("Hello! " + this.name); };
Object.defineProperty(person, "sayHello", {enumerable: false});
console.log(Object.keys(person));  // -> {"name", "age"}
```
앞 코드의 person 객체에서 사용할 수 있는 프로프터는 이 객체가 소유한 프로퍼티인 name, age, sayHello와 group에서 상속받은 프로퍼티인 groupName 입니다. 물론 Object.prototype에서 상속받은 프로퍼티도 사용할 수 있습니다. Object.keys 메서드는 이 중에서 해당 객체가 소유한 프로퍼티이면서 열거할 수 있는 프로퍼티인 name과 age의 이름만 배열로 만들어서 반환합니다. 이처럼 **해당 객체가 소유한 프로퍼티 이름만 조회하는 용도로는 Object.keys 메서드가 적합니다.**


### Object.getOwnPropertyNames 메서드 

Object.getOwnPropertyNames 메서드도 인수로 지정한 객체가 소유한 프로퍼티 이름을 배열로 만들어서 반환합니다. 그때 **열거할 수 있는 프로퍼티와 열거할 수 없는 프로퍼티의 이름 모두 배열로 만드는 점이 특징**입니다.

```javascript
console.log(Object.getOwnPropertyNames(person));  // -> ["name", "age", "sayHello"]
```

Object.keys 메서드는 열거할 수 없는 프로퍼티인 sayHello는 열거하지 않았지만 getOwnPropertyNames 메서드는 열거할 수 없는 프로퍼티까지 모두 열거합니다.

## 객체 잠그기

객체를 잠가 수정할 수 없게 만드는 방법을 알아봅니다. 객체를 잠글 때는 객체의 확장 가능 속성, 재정의 가능 속성, 쓰기 가능 속성을 설정합니다. ECMAScript 5부터 이 속성들을 한꺼번에 설정할 수 있는 메서드가 추가되었으며 잠금 강도에 따라 3단계 잠금이 가능해졌습니다.

### 확장 가능 속성

- 객체의 확장 가능(extensible) 속성은 **객체에 새로운 프로퍼티를 추가할 수 있는지를 결정합니다.**
- 확장 가능 속성 값이 true로 설정된 객체에는 새로운 프로퍼티를 추가할 수 있지만 false로 설정된 객체에는 추가할 수 없습니다. 
- 사용자가 정의한 객체와 내장 객체는 기본적으로 확장이 가능하지만 호스트 객체의 확장 가능한 속성은 자바스크립트 실행 환경에 따라 설정된 값이 다릅니다.

### 확장 방지 : Object.preventExtensions 메서드
- Object.preventExtensions메서드는 인수로 받은 객체를 확장할 수 없게 만듭니다.
- 이 메서드로 확장할 수 없게 만든 객체는 두 번 다시 프로퍼티를 추가할 수 없게 됩니다.

```javascript
var person = { name : "Tom" };
Object.preventExtensions(person);
person.age = 17;
console.log("age" in person); // -> false
```

- 이처럼 확장할 수 없는 객체에 프로퍼티를 추가하는 명령은 무시됩니다. 
- 또한 Strict 모드에서 setter가 없는 접근자 프로퍼티를 쓰려고 시도하면 오류가 발생합니다.
- **Object.isExtensible** 메서드를 사용하면 지정한 객체가 확장 가능한지 확인할 수 있습니다.

```javascript
console.log(Object.isExtensible(person));  // -> false
```

### 밀봉 : Object.seal 메서드

- <b>Object.seal 메서드</b>는 인수로 받은 객체를 밀봉합니다. **밀봉이란 객체에 프로퍼티를 추가하는 것을 금지하고 기존의 모든 프로퍼티를 재정의할 수 없게 만드는 것**을 말합니다. 
- 다시 말해 객체를 밀봉하면 프로퍼티의 추가, 삭제, 수정을 할 수 없고 값의 **읽기와 쓰기만 가능해집니다.** 

```javascript
var person = { name: "Tom" };
Object.seal(person);
person.age = 17;
delete person.name;
Object.defineProperty(person, "name", {enumerable: false});
console.log("name" in person);  // -> true : name이 삭제되지 않았음
console.log("age" in person); // -> false : age가 추가되지 않았음
console.log(Object.getOwnPropertyDescriptor(person, "name"));
// -> {value: "Huck", writable: true, enumerable: true, configurable: false}
person.name = "Huck";
console.log(person);
```
- 밀봉된 객체를 대상으로 한 프로퍼티의 추가, 삭제, 수정 명령은 무시됩니다.
- 또한 strict 모드에서 밀봉한 객체를 대상으로 프로퍼티 추가, 삭제, 수정 명령을 내리면 오류가 발생합니다.
- **Object.isSealed** 메서드를 사용하면 인수로 받은 객체가 밀봉된 상태인지 확인할 수 있습니다.

```javascript
console.log(Object.isSealed(person));  // -> true
```


### 동결 : Object.freeze 메서드

- <b>Object.freeze 메서드</b>는 인수로 받은 객체를 동결합니다. 
- 동결이란 객체에 프로퍼티를 추가하는 것을 금지하고 기존의 모든 프로퍼티를 재정의할 수 없게 만들며 데이터 프로퍼티를 쓸 수 없게 만드는 것입니다.
- 다시 말해 객체를 동결하면 객체의 프로퍼티가 일기만 가능한 상태가 됩니다.
- 단, 객체에 접근자 프로퍼티가 정의되어 있다면 게터 함수와 세터 함수 모두를 호출할 수 있습니다.

```javascript
var person = { name : "Tom" };
Object.freeze(person);
```
- 이렇게 설정하면 person 객체의 프로퍼티는 읽기만 가능한 상태가 됩니다.
- <b>Object.isFrozen 메서드</b>를 사용하면 인수로 받은 객체가 동결된 상태인지 확인할 수 있습니다.

```javascript
console.log(Object.isFrozon(person));  // -> true
```

## JSON
JSON을 사용하면 다른 프로그래밍 언어와의 데이터 송수긴이 간단해집니다. 웹 어플리케이션에서는 서버와 웹 클라이언트가 데이터가 주고받을 때 JSON을 자주 사용합니다.

### JSON 
- JSON(Javascript Object Notation)은 자바스크립트 객체를 문자열로 표현하는 데이터 포맷입니다. 
- JSON을 사용하면 객체를 **직렬화**할 수 있습니다. 
- **직렬화**컴퓨터의 메모리 속에 있는 객체를 똑같은 객체로 환원할 수 있는 문자열로 변환하는 과정을 말합니다.

### 표기 방법
JSON의 포맷은 **자바스크립트의 리터럴 표기법에 기반**을 두고 있습니다.  JSON은 자바스크립트 리터럴 표기법의 부분 집합입니다. 따라서 자바스크립트 리터럴로 표현할 수 있는 데이터 일부를 표현할 수 있습니다.<br><br>

예를 들면 다음과 같은 객체 리터럴이 있다고 합시다.

```javascript
{name: "Tom", age: 17, marriage: false, date: [2, 5, null]};
```

이 객체 리터럴을 JSON 데이터로 바꾸면 다음과 같아집니다.

```javascript
'{"name":"Tom", "age":17, "marriage":false, "data":[2, 5, null]}'
```

- 이처럼 JSON 데이터는 그 전체를 작은따옴표로 묶은 문자열 입니다. 
- 이때 객체의 프로퍼티 이름은 큰따옴표로 묶어야 합니다. 

#### JSON의 데이터 타입과 표기법

|데이터 타입|표기 예|설명|
|----|------|-----------|
|숫자|12.345|정수와 부동소수점의 10진수 표기만 가능하다. 부동소수점은 지수 표기법도 사용할 수 있다.|
|문자열|"abc"|큰따옴표로 묶어야 한다. 이스케이스 시퀀스도 포함할 수 있다.|
|논리값|true|true 또는 false|
|null값|null||
|배열|[1, "abc", true]|모든 데이터 타입을 배열의 요소로 사용할 수 있다.|
|객체|{"x":1, "y":"abc"}|프로퍼티 이름은 큰따옴표로 묶은 문자열로 표기한다.|

### JSON의 변환과 환원

#### JSON.stringify : 자바스크립트 객체를 JSON 문자열로 변환하기

```javascript
JSON.stringify(value[, replacer[, space]])
```
-  **value** : JSON으로 변환할 객체를 지정합니다.
- **replacer** : 함수 또는 배열을 지정합니다. 함수를 지정하면 문자열로 만드는 프로퍼티의 키와 값을 함수의 인수로 받아서 프로퍼티 값을 표현하는 문자열을 반환합니다. 배열을 지정하면 배열의 요소로 객체의 프로퍼티 이름을 필터링 합니다.
- **space** : 출력하는 문자열을 구분할 때 사용할 공백 문자를 지정합니다.
<br><br>
JSON.stringify 메서드를 사용할 때는 다음 사항에 유의
	- NaN, Infinity, -Infinity는 null로 직렬화된다.
	- Date 객체는 ISO 포맷의 날짜 문자열로 직렬화된다. 단, JSON.parse는 이 문자열을 그대로 출력한다.
	- Function, RegExp, Error 객체, undefined, 심벌은 직렬화할 수 없다.
	- 객체 자신이 가지고 있는 열거 가능한 프로퍼티만 직렬화된다.
	- 직렬화할 수 없는 프로퍼티는 문자열로 출력되지 않는다.
	- 프로퍼티 중에서 키가 심벌인 프로퍼티는 직렬화되지 않는다.

#### JSON.parse : JSON 문자열을 자바스크립트 객체로 환원하기

```javascript
JSON.parse(text[, reviver]);
```
- **text** : 자바스크립트의 객체로 환원하고자 하는 JSON 문자열을 지정합니다.
- **reviver** : 프로퍼티의 키와 값을 인수로 받는 함수를 지정할 수 있습니다. 이 함수는 환원될 객체의 프로퍼티 값을 반환해야 합니다.

#### JSON을 활용한 객체의 깊은 복사
- 객체를 단순히 변수에 대입하면 얕은 복사를 합니다.

```javascript
var copy = obj;
```

- 이렇게 대입하면 이 코드의 obj와 copy가 똑같은 객체를 참조합니다. JSON.stringify와 JSON.parse 메서드를 사용하면 깊은 복사를 할 수 있습니다.

```javascript
var copy = JSON.parse(JSON.stringify(obj));
```

- 이 방법을 사용하면 중첩된 객체도 올바르게 복사할 수 있습니다. 단, 가장 아래에 중첩된 객체의 프로퍼티(말단 프로퍼티)는 값의 타입이 원시 타입(primitive type)이어야 합니다.

```javascript
var point = {x:0, y:0};
var circle = {center:point, radius:2};
var copy = JSON.parse(JSON.stringify(circle));
```

- 이 방법은 말단 프로퍼티 값이 Date, function, RegExp 객체이거나 프로퍼티 이름이 심벌일 때는 올바르게 동작하지 않습니다.  
- 어떠한 상황에서나 깊은 복사를 올바르게 수행하려면 <b>Object.assign 메서드</b>를 사용하세요.

## 생성자

### 생성자를 정의하는 방법

- (1) 함수 선언문으로 정의하는 방법

```javascript
function Card(suit, rank) {
	this.suit = suit;
	this.rank = rank;
}

Card.prototype.show = function() {
	console.log(this.suit * this.rank);
};
```

- (2) 함수 리터럴로 정의하는 방법

```javascript
var Card = function(suit, rank) {
	this.suit = suit;
	this.rank = rank;
};
Card.prototype.show = function() {
	console.log(this.suit + this.rank);
};
```

- (3) 클래스 선언문으로 정의하는 방법

```javascript
class Card {
	constructor (suit, rank) {
		this.suit = suit;
		this.rank = rank;
	}
	show() {
		console.log(this.suit + this.rank);
	}
	
}
```

- (4) 클래스 표현식으로 정의하는 방법

```javascript
var Card = class {
	constructor(suit, rank) {
		this.suit = suit;
		this.rank = rank;
	}
	show() {
		console.log(this.suit + this.rank);
	}
}
```
(1)의 함수 선언문으로 정의한 생성자는 자바스크립트 엔진이 프로그램 시작 부분으로 끌어 올립니다. (2), (3), (4)로 정의한 생성자는 끌어올리지 않습니다. 따라서 (2), (3), (4)로 정의한 생성자는 호출하기 전에 정의해야 합니다.

### 생성자로 접근자 프로퍼티 정의하기

```javascript
function Person(name) {
	Object.defineProperty(this, "name", {
		get: function() {
			return name;
		},
		set: function(newName) {
			name = newName;
		},
		enumerable: true,
		configurable: true
	});
}
Person.prototype.sayName = function() {
	console.log(this.name);
};
```
- Person 생성자로 생성한 인스턴스의 name 프로퍼티는 접근자 프로퍼티이며 인수로 받은 지역 변수 name 값을 읽고 씁니다. 
- Person 생성자가 인수로 받은 name과 접근자 프로퍼티 name의 차이점에 주목하면, 인수로 받은 name이 클로저 안에 저장된 프라이빗(private) 변수가 되었습니다. 
- 이는 게터 함수와 세터 함수가 인수로 받은 name을 참조하여 클로저 안에 저장했기 때문입니다.

```javascript
var person = new Person("Tom");
console.log(p.name); // -> Tom
p.name = "Huck";
console.log(p.name); // -> Huck
p.sayName();  // -> Huck 
```

## 생성자 상속
- C++나 Java처럼 클래스를 이용하는 객체 지향 언어에서는 객체 설계도라고 할 수 있는 클래스를 상속할 수 있습니다. 상속으로 이미 정의된 클래스의 코드를 재사용하고 새로운 기능을 추가해서 클래스를 확장할 수 있습니다.

- 자바스크립트에서는 생성자가 클래스 역할을 대신합니다. 자바스크립트는 생성자 상속 메커니즘 대신 **객체의 프로토타입 상속 매커니즘을 채택한 언어**입니다. 하지만 **생성자 또한 객체이므로 객체의 프로토타입 상속을 활용하면 생성자 상속을 구현할 수 있습니다.**

- C++나 Java 등에서는 상속하는 상위 클래스를 슈퍼클래스, 상속 받은 하위 클래스를 서브 클래스라고 부릅니다.
- 자바스크립트에서는 클래스가 없으므로 상속하는 생성자를 **슈퍼 타입 생성자**, 상속을 받은 생성자를 **서브 타입 생성자** 부릅니다.

### 슈퍼타입 생성자의 예

#### 타원의 장축 방향 반지음과 단축 방향 반지름

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image9.png)

```javascript
function Ellipse(a, b) {
	this.a = a;  // 장축 방향 반지름
	this.a = b;  // 단축 방향 반지름
}

// 타원의 넓이를 계산하는 메서드
Ellipse.prototype.getArea = function() {
	return Math.PI * this.a * this.b
};

// Object.prototype.toString을 덮어쓴다.
Object.prototype.toString = function() {
	return "Ellipse "  + this.a + " " + this.b;
};
```

이 생성자로 인스턴스를 생성해 봅니다.

```javascript
var ellipse = new Ellipse(5, 3);
```

이제 ellipse의 프로토타입 체인은 다음 그림과 같아집니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image10.png)

이를 이용해 인스턴스 ellipse는 자신이 갖고 있지 않은 메서드 Ellipse.prototype과 Object.prototype에서 상속받아 사용할 수 있습니다.

```javascript 
console.log(ellipse.getArea());  // 47.123888980...
console.log(ellipse.toString()); // Ellipse 5 3
```

### 생성자의 prototype 상속하기
먼저 Circle 생성자를 다음과 같이 정의합니다.

```javascript
function Circle(r) {
	this.a = r;
	this.b = r;
}
```

이 생성자로 원 객체를 생성합니다.

```javascript
var circle = new Circle(2);
```

- circle의 프로토타입은 Circle.prototype이고 Circle.prototype의 프로토타입은 Object.prototype입니다. 
- Circle 생성자의 인스턴스 circle에서 Ellipse.prototype의 메서드를 사용하려면 Circle.prototype이 Ellipse.prototype을 상속받아야 합니다. 
- 따라서 circle의 프로토타입 체인에 Ellipse.prototype을 삽입합니다. 
- 이를 위해 Circle.prototype을 Ellipse.prototype을 프로토타입으로 가지는 객체로 바꿉니다. 
- 이때 기존의 Circle.prototype을 대체할 새로운 Circle.prototype의 constructor 프로퍼티의 value 속성을 Circle로 설정합니다. 
- 이를 실제로 구현하면 다음과 같습니다.

```javascript 
function Ellipse(a, b) {
	this.a = a;  // 장축 방향 반지름
	this.a = b;  // 단축 방향 반지름
}

// 타원의 넓이를 계산하는 메서드
Ellipse.prototype.getArea = function() {
	return Math.PI * this.a * this.b
};

// Object.prototype.toString을 덮어쓴다.
Object.prototype.toString = function() {
	return "Ellipse "  + this.a + " " + this.b;
};

function Circle(r) {
	this.a = r;
	this.b = r;
}

Circle.prototype = Object.create(Ellipse.prototype, {
	constructor: {
		configurable: true,
		enumerable: true,
		value: Circle,
		writable: true
	}
});
```
- 그리고 Circle.prototype.toString 메서드를 정의해서 Ellipse.prototype.toString 메서드를 덮어씁니다.

```javascript
Circle.prototype.toString = function() {
	return "Circle " + this.a + " " + this.b;
};
```

- 이렇게 수정한 다음 Circle 생성자로 인스턴스 circle을 생성합니다.

```javascript
var circle = new Circle(2);
```

- 이제 circle의 프로토타입 체인은 다음 그림과 같아집니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/11~12%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9D%EC%B2%B4/images/image11.png)

- 이렇게 되면 circle 인스턴스가 Ellipse.prototype의 메서드를 사용할 수 있게 됩니다.

```javascript
console.log(circle.getArea());
console.log(circle.toString());
```


### 생성자 빌려오기

- 앞서 소개한 방법에서는 Ellipse 생성자 안에서 정의한 프로퍼티(this.a와 this.b)를 Circle 생성자 안에서 다시 정의합니다. 
- 이번에는 Ellipse 생성자에서 만든 프로퍼티를 Circle 생성자 안으로 가져오도록 만들겠습니다. 
- 이를 구현하려면 Circle 생성자에서 Ellipse 생성자를 call 메서드로 호출합니다.
- 그리고 Ellipse.prototype을 Circle.prototype에 상속합니다.

```javascript
function Circle(r) {
	// Ellipse 생성자를 빌려와서 프로퍼티를 정의합니다.
	Ellipse.call(this, r, r);
	
	// 이곳에서 새로운 프로퍼티를 작성하거나 기존의 프로퍼티를 덮어쓸 수 있음
}

Circle.prototype = Object.create(Ellipse.prototype, {
	constructor: {
		configurable: true,
		enumerable: true,
		value: Circle,
		writable: true
	}
});
Circle.prototype.toString = function() {
	return "Circle " + this.a + " " + this.b;
};
```
- 이제 circle 생성자 안에서 프로퍼티를 별도로 생성하지 않아도 Ellipse의 생성자를 빌려서 자동으로 프로퍼티를 생성할 수 있게 되었습니다.

```javascript
var circle = new Circle(2);
console.log(circle.getArea());
console.log(circle.toString());
```

### 슈퍼 타입의 메서드 이용하기
- 지금까지 사용한 방법은 Circle.prototype.toString 메서드를 새로 정의해서 Ellipse.prototype.toString 메서드를 덮어쓰는 방식입니다. 
- 이 메서드를 새로 정의하는 대신에 Ellipse.prototype 메서드를 이용해서 정의해 보겠습니다.

```javascript
// 슈퍼 타입의 toString 메서드를 이용해서 Circle.prototype.toString을 정의한다.
Circle.prototype.toString = function() {
	var str = Ellipse.prototype.toString.call(this);
	return str.replace("Ellipse", "Circle");
};
```
- 지금까지 생성자를 상속하는 모든 방법을 설명하였습니다. 
- 이러한 생성자 상속 방법은 클래스 기반 객체 지향 언어의 고전적 상속을 흉내 낸다고 하여 <b>가상의 전통적 상속(pseudo classical inheritance)</b>이라는 이름으로도 부릅니다.

## 클래스 구문

### 클래스 구문의 기본
- ECMAScript 6부터는 생성자를 정의하는 새로운 문법인 클래스 구분이 추가되었습니다.
- 클래스 구문 종류에는 **클래스 선언문**과 **클래스 표현식**이 있습니다. 

```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
}
Circle.prototype.area = function() {
	return Math.PI * this.radius * this.radius;
};
```
- 앞의 코드를 클래스 구문으로 고치면 다음과 같은 모습이 됩니다.

```javascript
class Circle {
	// 생성자를 사용한 초기화
	constructor(center, radius) {
		this.center = center;
		this.radius = radius;
	}
	
	// prototype 메서드
	area() {
		return Math.PI * this.radius * this.radius;
	}
}
```

- 앞의 코드로 생성자 함수 Circle을 정의하게 됩니다.
- 실제 인스턴스를 생성해 봅니다.

```javascript
var c = new Circle({x: 0, y: 0}, 2);
console.log(c.area());
```

#### 클래스 선언문을 작성하는 방법 정리

- class 키워드 뒤에 생성자 함수의 이름을 표기합니다. 이 함수의 이름은 클래스 이름입니다.
- { ... } 안은 <b>클래스 몸통(class body)</b>이라고 합니다. 클래스 몸통에는 클래스 멤버를 정의합니다. **클래스 멤버**는 함수 선언문에서 function 키워드를 생략한 표현식입니다.
- 클래스 멤버인 constructor() { ... } 에는 특별한 의미가 있습니다. constructor는 생성자로 객체를 생성할 때 초기화 처리를 담당하는 메서드입니다. 지금까지 생성자 함수에 작성했던 작업들을 이곳에 작성해서 객체에 프로퍼티를 추가합니다.
- constructor 다음에 작성된 클래스 멤버는 생성자 함수의 prototype에 메서드로 추가됩니다.
<br><br>

클래스 선언문으로 정의한 생성자는 함수 선언문으로 정의한 생성자와 같습니다. 그러나 <b>클래스 선언문과 함수 선언문은 다음 차이점이 있습니다.</b>

 - 클래스 선언문은 자바스크립트 엔진이 끌어올리지 않습니다. 따라서 생성자를 사용하기 전에 클래스 선언문을 작성해야 합니다.
 - 클래스 선언문은 한 번만 작성할 수 있습니다. 같은 이름을 가진 클래스 선언문을 두 번 이상 작성하면 타입 오류가 발생합니다.
 - 클래스 선언문에 정의한 생성자만 따로 호출할 수 없습니다.

<br><br>

앞에서 본 생성자는 클래스 표현식으로 정의할 수 있습니다.

```javascript
var Circle = class {
	// 생성자를 사용한 초기화
	constructor(center, radius) {
		this.center = center;
		this.radius = radius;
	}
	
	// prototype 메서드
	area() {
		return Math.PI * this.radius * this.radius;
	}
};
```

class 다음에는 모든 식별자를 이름으로 사용할 수 있습니다.

```javascript
var Class = class Kreis { ... };
```

- 단, 이렇게 표기한 이름(앞의 예에서는 Kreis)은 클래스 몸통 안에서만 유효합니다. 
- 클래스 표현식은 익명함수를 대입할 때와 마찬가지로 실행 시점에 변수에 할당되므로 자바스크립트 엔진이 끌어올리지 않습니다.
- 또한 같은 이름을 가진 클래스 선언문을 여러 개 작성할 수 있습니다.

### 접근자 작성하기

```javascript
class Person {
	// 생성자를 사용한 초기화
	constructor(name) {
		this.name = name;
	}
	
	// prototype 메서드
	get name() {
		return this._name;
	}
	
	set name(value) {
		this._name = value;
	}
	
	sayName() {
		console.log(this.name);
	}
}
```

클래스 멤버 앞에 **get 키워드**를 사용하면 게터가 되고. **set 키워드**를 사용하면 세터가 됩니다. 

```javascript
var person = new Person("Tom");
console.log(person.name);  // -> Tom
person.name = "Huck";
console.log(person.name); // -> Huck
person.sayName();  // -> Huck
```

- 생성자 Person으로 생성한 인스턴스 person에는 name이라는 접근자 프로퍼티가 정의되어 있습니다. 
- name이라는 세터 프로퍼티에 값을 대입하면 객체에는 객체에는 \_name이라는 프로퍼티가 추가되고 그 값을 \_name 프로퍼티에 저장합니다. 
- constructor 멤버 안의 다음 코드는 생성자의 인수인 name 값을 세터 프로퍼티인 person.name에 대입합니다. 

```javascript
this.name = name;
```

- 그리고 constructor가 인수로 받은 name, name 접근자 프로퍼티, \_name 프로퍼티는 모두 다른 존재라는 점에 유의하세요. 
- 또한 게터와 세터가 Person.prototype에 정의됩니다.

### 정적 메서드 작성하기

- class 구문을 사용하면 생성자 함수에 메서드를 추가할 수도 있습니다. 
- class 구문에서는 생성자의 메서드를 정적 메서드라는 이름으로 부릅니다.
- 정적 메서드를 정의하려면 클래스 멤버 앞에 <b>static 키워드</b>를 붙여 줍니다.

```javascript
class Person {
	constructor(name) {
		this.name = name;
		Person.personCount++;
	}
	get name() {
		return this._name;
	}
	set name(value) {
		this._name = value;
	}
	
	sayName() {
		console.log(this.name);
	}
	// 정적 메서드 
	static count() {
		return Person.personCount;
	}
}
Person.personCount = 0;
```

다음 코드는 Person 생성자의 프로퍼티 PersonCount에 Person 생성자를 호출한 횟수를 기록합니다. 정적 메서드 count는 Person.personCount 값을 출력합니다.

```javascript
var person1 = new Person("Tom");
console.log(Person.count()); // -> 1
var person2 = new Person("Huck");
console.log(Person.count()); // -> 2
```

### 상속으로 클래스 확장하기

- <b>extends 키워드</b>를 클래스 선언문이나 클래스 표현식에 붙여 주면 다른 생성자를 상속받을 수 있습니다. 그리고 생성자에 새로운 메서드를 추가해서 확장할 수 있습니다.
- 이 방법은 prototype을 상속하고 새로운 메서드를 추가하는 등의 간단한 작업만 할 수 있습니다.
- 앞의 예인 Ellipse를 Circle에 상속할 때처럼 <b>프로퍼티 속성까지 바꾸어 가며 상속하는 방법은 지원하지 않습니다.</b>

```javascript
class Circle {
	constructor(center, radio) {
		this.center = center;
		this.radius = radius;
	}
	
	area() {
		return Math.PI * this.radius * this.radius;
	}
	
	toString() {
		return "Circle "
			+ "중심점 (" + this.center.x + ", " + this.center.y 
			+ "), 반지름 = " + this.radius;
	}
}

class Ball extends Circle {
	move(dx, dy) {
		this.center.x += dx;
		this.center.y += dy;
	}
}
```

Ball 생성자로 인스턴스를 생성해 봅니다.

```javascript
var ball = new Ball({x: 0, y: 0}, 2);
console.log(ball.toString());  // -> Circle 중심점 (0, 0), 반지름 = 2
console.log(ball.area()); // -> 32.56637...
ball.move(1, 2);
console.log(ball.toString()); // -> Circle 중심점 (1, 2), 반지름 = 2
```

이처럼 Ball 생성자의 인스턴스가 Circle 생성자의 프로퍼티와 메서드 외에도 새로 추가된 move 메서드를 사용할 수 있음을 확인할 수 있습니다.

### 슈퍼 타입의 메서드 호출하기

- 서브 타입의 생성자는 슈퍼타입의 생성자의 메서드를 덮어쓸 수 있습니다.
- 이때 <b>super 키워드</b>를 활용하면 서브 타입의 생성자가 슈퍼 타입 생성자의 메서드를 호출할 수 있습니다.

```javascript
class Ball extends Circle {
	move(dx, dy) {
		this.center.x += dx;
		this.center.y += dy;
	}
	
	toString() {
		var str = super.toString();
		return str.replace("Circle", "Ball");
	}
}
```

- 이 예에서는 Circle의 toString 메서드를 새로운 메서드로 덮어쓰고 있습니다. 그리고 슈퍼타입 생성자 Circle의 메서드인 toString을 super 키워드로 호출해서 사용하고 있습니다. 
- 이 Ball 생성자로 인스턴스를 생성하면 다음과 같은 결과가 나옵니다.

```javascript
var ball = new Ball({x: 0, y: 0}, 2);
console.log(ball.toString());  // -> Circle 중심점 (0,0), 반지름 = 2
```

> 클래스 구문은 함수를 정의한다. 클래스를 정의하지 않는다.<br><br>ECMAScript 6부터 추가된 클래스 구문은 생성자 함수를 간결하게 작성하기 위한 문법 요소라는 점에서 C++나 Java 등의 클래스와는 차이가 있습니다. 클래스 구문이 추가되었음에도 자바스크립트의 객체 지향 매커니즘은 바뀐 것이 없습니다. 클래스 구문을 사용할 때는 늘 이점에 유의하세요. 클래스 구문은 그 배경지식인 자바스크립트 객체의 메커니즘과 프로토타입 상속을 제대로 이해한 후에 사용해야 합니다.


## ECMAScript6+에 추가된 객체의 기능

### 프로퍼티 이름으로 심벌 사용하기

ECMAScript 5까지는 객체의 프로퍼티 이름으로 식별자나 문자열만 사용할 수 있었습니다. EMCAScript 6부터는 심벌을 프로퍼티 이름으로 사용할 수 있게 되었습니다.

```javascript
var obj = {};
var s = Symbol("heart");
obj[s] = 3;
console.log(obj);  // ->Object { Symbol(heart): 3}
```

계산된 프로퍼티 이름(Computed Property names)을 활용하면 객체 리터럴로 객체를 정의할 때 프로퍼티 이름으로 심벌을 사용할 수 있습니다.

```javascript
var obj = { [Symbol("heart")]: "A" };
```
심벌은 그 어떤 값과도 다른 유일무이한 값입니다. 따라서 함수 안에서 심벌을 생성하여 그것을 속성 이름으로 사용하고 그 프로퍼티에 값을 할당하면 함수 바깥에서 프로퍼티 값을 읽거나 쓸 수 없습니다. 즉, 명시적으로 객체의 프로퍼티를 숨길 수 있습니다. 실제로 이름이 심벌로 작성된 프로퍼티는 다음 방법으로 찾아낼 수 없습니다.

- for/in 반복문
- Object.keys
- Object.getOwnPropertyNames

단, ECMAScript 6부터 추가된 Object.getOwnPropertySymbols 메서드를 사용하면 객체 안에서 이름을 심벌로 지정한 프로퍼티 이름 목록을 가져올 수 있으므로 완전히 숨길 수는 없습니다.

><b>심벌과 하위 호환성</b><br><br>심벌은 ECMAScript 6부터 새롭게 도입된 값입니다. 심벌이 추가된 이유는 여러 가지가 있지만 ECMAScript 6 이전에 작성된 프로그램에 영향을 주지 않고 새로운 기능을 추가하기 위해서입니다.<br> 예를 들어 for/of 반복문은 반복 가능한 객체를 대상으로 사용할 수 있습니다. 이때 임의의 객체 a가 반복 가능한지 여부는 a[Symbol.iterator]처럼 Symbol.iterator 심벌을 이름으로 가지는 메서드가 있는지로 판단합니다. 또한 객체를 순회할 때도 Symbol.iterator를 프로퍼티 이름으로 가지는 메서드가 반환하는 이터레이터를 사용합니다. 심벌을 자바스크립트 사양에 추가하지 않았다면 어떤 상황이 벌어졌을까요? 예를 들어 메서드 이름을 a["iterator"]라는 식으로 추가하면 for/in 반복문이나 Object.keys 메서드 등으로 조회할 수 있게 됩니다. 그러면 기존의 프로그램에 영향을 끼쳐 원활하게 동작하지 않을 수 있습니다. 자바스크립트 자체의 추가 기능을 심벌을 이름으로 사용하는 특수한 메서드에 구현하면 이러한 문제를 피할 수 있습니다.<br><br> 실제로 ECMAScript 6부터는 Symbol.iterator를 비롯한 내장 심벌이 여러 개 정의되어 있으며, 그 대부분을 새로 추가된 메서드 이름으로 사용하고 있습니다. 이러한 의미에서 심벌은 추후에 언어 사양을 확장하기 위한 메커니즘의 일부분으로 도입되었다고 볼 수 있습니다.


#### 내장 생성자 prototype의 안전한 확장

- 일반적으로 Array와 Date등 기본 생성자의 prototype에 메서드를 추가해서 확장하는 방법은 권장되지 않습니다. 사용자가 추가한  메서드 이름이 다른 자바스크립트 라이브러리나 미래의 자바스크립트 사양에 추가될 메서드 이름과 겹칠 가능성이 있기 때문입니다.
- 심벌을 사용하면 메서드 이름이 겹치는 것을 피하면서도 기본 생성자의 prototype을 확장할 수 있습니다.

```javascript
Array.prototype[Symbol.for("shuffle")] = function() {
	var a = this;
	var m = a.length, t, i;
	while(m) {
		i = Math.floor(Math.random()*m--);
		t = a[m];
		a[m] = a[i];
		a[i] = t;
	}
	return this;
};
var array = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];

// 값을 무작위로 섞기 때문에 실행할 때마다 출력되는 값이 다르다.
console.log(array[Symbol.for("shuffle")]()); 
```

### 객체 리터럴에 추가된 기능

#### 계산된 프로퍼티 이름(Computed Property names)

```javascript
{ [계산식]: value }
```

- 대괄호로 묶인 임의의 계산식이 평가된 값을 프로퍼티 이름으로 사용할 수 있게 되었습니다.
- 평가한 값이 Symbol 타입이라면 그대로 사용하고 그렇지 않다면 문자열 타입으로 변환합니다.

```javascript
var prop = "name", i = 1;
var obj = { [prop+i]: "Tom" };
console.log(obj);  // -> Object {name1: "Tom"}
var obj = { [Symbol("heart")]: "A" };
console.log(obj);  // -> Object {Symbol(heart): "A"}
```
- 객체 리터럴에서 심벌을 프로퍼티 이름으로 사용하려면 [Symbol("heart")] 처럼 심벌을 대괄호로 묶어 줍니다.

#### 프로퍼티 정의의 약식 표기 : { prop }
변수 prop가 선언되어 있을 때 { prop }를 { prop: prop }로 사용할 수 있게 되었습니다.  즉, 프로퍼티 이름이 변수 이름과 같을 때 { prop }로 줄여서 표현할 수 있게 된 것입니다.

```javascript
var prop = 2;
var obj = { prop };
console.log(obj);  // -> Object {prop: 2}
```

#### 메서드 정의의 약식 표기 : { method() {} }
- 프로퍼티 값으로 함수를 지정할 때 사용하는 약식 표기법이 추가되었습니다.

```javascript
var person = {
	name: "Tom",
	sayHello() { console.log("Hello! " + this.name); }
};

person.sayHello();  // -> "Hello! Tom"
```

- 앞의 코드는 다음 코드와 거의 같습니다.

```javascript
var person = {
	name: "Tom",
	sayHello: function() { console.log("Hello! " + this.name); }
};
```
- 하지만 다음과 같은 차이점이 있습니다.
	- { method() {} }는 생성자로 사용할 수 없다. 즉, prototype 프로퍼티를 가지지 않으므로 new 연산자로 인스턴스를 생성할 수 없다. 
	- { method() {} }는 super 키워드를 사용할 수 있다.
	
#### 제너레이터 정의의 약식표기 {*generate() {} }

- 프로퍼티의 값이 제너레이터 함수일 때 사용할 수 있는 약식 표기법입니다.

```javascript
var obj = {
	*range(n) { for(var i = 0; i < n; i++) yield i; }
};

var it = obj.range(10);
console.log(it.next().value); // -> 0
console.log(it.next().value); // -> 1
console.log(it.next().value); // -> 2
```
- 이 표기법을 사용하면 super 키워드를 사용할 수 있습니다.
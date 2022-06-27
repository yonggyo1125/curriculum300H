# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1aajT5lF0zge77wBgrc_qf96yMf6amLUe?usp=sharing)

# 객체 리터럴
- 객체는 이름과 값을 한쌍을 묶은 데이터를 여러개 모은 것
- 즉, 객체는 데이터 여러개를 하나로 모은 복합 데이터로 연관배열 또는 사전(Diction) 이라고 부릅니다. 

|이름|값|
|----|----|
|suit|"하트"|
|rank|"A"|

- 객체에 포함된 데이터 하나(이름과 값의 쌍)를 가리켜 객체의 프로퍼티라고 부른다
- 프로퍼티 이름 부분을 “프로퍼티 이름” 또는 “키”라고 부릅니다.
- 객체를 생성하는 방법 2가지 : 객체 리터럴 사용, 생성자 함수 사용  

## 객체 리터럴로 객체 생성하기 
```javascript
var card = { suit  : "하트", rank : "A" };
```
- { ... } 부분이 객체 리터럴 이며 객체 리터럴을 변수 card에 대입하고 있음
- 프로퍼티 값은 suit : “하트” 처럼 콜론(:)을 사용해서 구분
- 중괄호({})안에 있는 프로퍼티들은 쉼표(,)로 구분
- 변수에 대입된 객체 안의 프로퍼티 값을 읽거나 쓸 때에는 마침표(.) 연산자 또는 
- 대괄호 ([]) 연산자를 사용 

```javascript
 card.suit // -> 하트
 card['rank'] // -> A 
```

- 객체에 없는 프로퍼티를 읽으려고 시도하면 undefined를 반환
```javascript
card.color // -> undefined
```
- 객체 리터럴 안에 어떠한 프로퍼티도 작성하지 않으면 빈 객체가 생성됨
```javascript
var obj = {};
console.log(obj); // -> Object{}
```

## 프로퍼티 추가와 삭제
- 없는 프로퍼티 이름에 값을 대입하면 새로운 프로퍼티가 추가됨
```javascript
card.value = 14;
console.log(card); // Object {suit : "하트", rank : "A", value : 14 }
```
- delete 연산자를 사용하여 프로퍼티 삭제
```javascript
delete card.rank;
console.log(card); // Object { suit : “하트”, value : 15 }
```

## in 연산자로 프로퍼티가 있는지 확인하기
프로퍼티가 객체에 포함되어 있을때 true, 없을때 false를 반환 
```javascript  
var card = { suite : "하트", rank : "A" };
console.log(”suit” in card); // -> true
console.log(”color” in card); // false
```
## 객체 리터럴 예제
- 예1) 좌표평면의 점을 표현하는 객체
```javascript
var p = { x : 1.0, y : 2.0 };
```
- 예2) 원을 표현하는 객체
```javascript
var circle = {
	center : { x : 1.0, y : 2.0 }, // 원의 중심을 표현하는 객체 
    radius : 2.5 // 원의 반지름
};
```

- 예3) 회원 정보를 표현하는 객체
```javascript
var person = {
   name : "이용교", 
   age : 41,
   gender : "남",
   married : true
};
```

## 메서드
프로퍼티에 저장된 값의 타임이 함수인 경우 
```javascript
var person = {
   name : "이용교", 
   age : 41,
   gender : "남",
   married : true,
 getLoginInfo : function() {}
};

person.getLoginInfo();
```

## 객체는 참조 타입
- 생성된 객체는 메모리의 영역을 차지하는 한 덩어리가 됩니다. 
- 객체 타입의 값을 변수에 대입하면 그 변수에는 객체의 참조(메모리에서의 위치 정보)가 저장됩니다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image1.png)

- 변수에 저장된 객체의 참조는 다른 원시값과 마찬가지로 다른 변수에 저장할 수 있음
```javascript
var a = card;
console.log(a.suit); // -> 하트
a.suit = "스페이드";
console.log(a.suit); // -> 스페이드
console.log(card.suit); // 스페이드
```

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image2.png)

* * *
# 함수
- 일련의 처리를 하나로 모아 언제든 호출할 수 있도록 만들어 놓은것
- 수학 함수와 비슷
- 함수의 입력 값을 인수, 함수의 출력 값을 반환값
```javascript 
function 함수명 (인수) {   
	처리 로직 
 
   return 출력 반환값
}
```
## 함수 선언문으로 함수 정의하기
- 함수는 function 키워드를 사용해서 정의
```javascript
function square(x) {    
	var result = x * x; 
	
	return result; 
}  
``` 
- square - 함수 이름
- x - 인수
- var result = x \* x - 처리 로직 
- return result - 처리 후 반환값 
- 참조) 함수명 캐멀 표기법

## 함수 호출
함수를 호출하려면 함수 이름 뒤에 소괄호 인수를 묶어 입력
```javascript
square(3); // 9
```
## 인수

함수는 인수를 여러 개 받을 수 있음
- 인수가 여러 개라면 인수와 인수를 쉼표(,)로 구분
```javascript
function add(a, b) {   
	var c = a + b;
	return c;
}
```
- 인수를 받지 않는 함수도 정의할 수 있음
```javascript
function bark() {    
	console.log(”멍멍”); 
};

bark(); // 멍멍 
console.log(bark()); // undefined - 반환값이 없으므로
```

## 함수의 실행흐름
- 호출된 코드에 있는 인수가 함수 정의문에 대입된다. 
- 함수 정의문의 중괄호 안에 작성된 플그램이 순차적으로 실행된다.
- return 문이 실행되면 호출된 코드로 돌아간다. return 문의 값은 함수의 반환값이 된다.
- return 문이 실행되지 않은 상태로 마지막 문장이 실행되면, 호출한 코드로 돌아간 후에 
- undefined가 함수의 반환값이 된다.

## 함수 선언문의 끌어올림
- 자바스크립트 엔진은 변수 선언문과 마찬가지로 함수 선언문을 프로그램의 첫머리로 끌어올림
- 따라서 함수 선언문은 프로그램 어떤 위치에서도 작성할 수 있다.

```javascript
console.log(square(5)); // 25 
function square(x) {  return x * x; }
```

## 값으로서의 함수
- 자바스크립트에서는 함수가 객체입니다. 
- 함수를 선언하면 내부적으로는 그 함수 이름을 변수 이름으로 한 변수와 함수 객체가 만들어지고, 그 변수에 함수 객체의 참조가 저장됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image3.png)

- 이 변수 값을 다른 변수에 할당하면 그 변수 이름으로 함수를 실행할 수 있습니다.
```javascript
var sq = square;
console.log(sq(5)); // 25 
```
- 함수를 다른 함수의 인수로 넘길 수도 있습니다.
```javascript
function callback(scrollTop) {
    console.log(`이벤트 콜백 부분 - 인수${scrollTop}`);

    return scrollTop;
}

function goTop(callback) {
   var scrollTop = window.pageYOffset;
   
   return callback(scrollTop);
}
```
## 참조에 의한 호출과 값에 의한 호출 
- 원시값을 함수에 인수로 넘겼을때는 대입되어 복사가 됩니다.
- 객체 값을 인수로 넘겼을때 객체의 참조가 대입되어 복사 됩니다.

- 즉 객체의 값을 인수로 넘기게 되면 인자와 인수가 동일하게 변경이 됩니다
(동일한 데이터의 주소를 참조하므로)

- 원시값은 값 자체가 복사가 되므로 인자와 인수가 각각 유지가 됩니다.


## 변수의 유효범위 
### 전역 유효 범위와 지역 유효범위
전역변수
- 함수 바깥에서 선언된 변수
- 유효 범위는 전체 프로그램

지역변수
- 함수 안에서 선언된 변수
- 함수 인자 
- 유효범위는 변수가 선언된 함수 내부

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image4.png)

### 변수의 충돌
- 변수에 유효 범위가 있는 이유는 프로그램의 다른 부분에서 선언된 이름이 같은 변수와 충돌하지 않도록 하기 위해서입니다.
- 다른 함수 내부에서 선언된 각각의 지역변수는 모두 선언된 함수 내부에서만 유효하므로 이름이 같아도 충돌하지 않습니다.
- 함수 안에서 변수 이름을 지을 때는 다른 함수 안에서 사용하는 변수 이름까지 신경쓸  필요 없음
- 전역변수와 지역변수 이름이 같아지면 전역변수를 숨기고 지역변수를 사용

```javascript
var a = "global";
function f() {   
	var a = "local";
	console.log(a); // -> local
	return a;
}
f();
console.log(a); // -> global
```

### 함수 안에서 변수 선언과 변수 끌어올림
- 함수 안에서 선언된 지역변수의 유효범위는 함수 전체입니다.
- 함수 중간 부분에서 변수를 선언하더라도 변수는 함수의 첫머리로 끌어올립니다.
```javascript
function f() {
	console.log(a); // undefined -> 끌어올리지 않는다면 Reference Error 발생
	var a = "local";
	console.log(a); // -> local 
	return a;
} 
```

### 함수 안에서 변수 선언 생략
- 변수를 선언하지 않은 상태에서 값을 대입하면 전역변수로 선언됨
- 지역변수로써만 정의하는 경우는 반드시 변수 선언을 해야 함 
```javascript
예) var a = "local"; 
```
```javascript
function f() {    
	a = "local";
	console.log(a); // -> local
    return a; 
}

f();
console.log(a);// local
```

## 블록 유효 범위 : let과 const
### let 선언자
let 문은 블록 유효 범위를 갖는 지역변수를 선언합니다. 사용법은 var문과 같다.
```javascript    
    let x = "outer x";
    {
      let x = "inner x";
      let y = "inner y";
      console.log(x); // -> inner x
      console.log(y); // inner y
    }
    console.log(x); // -> outer x
    console.log(y); // -> ReferenceError : y is not defined
```

### const 선언자
- const 문은 블록 유효 범위를 가지면서 한 번만 할당할 수 있는 변수(상수)를 선언한다.
- const로 선언한 상수는 let 문으로 선언한 변수처럼 동작한다.
- 단, 반드시 초기화해야 한다는 차이점이 있다.
```javascript
const c = 2; 
```
- const 문으로 선언한 변수에 다시 대입을 시도하면 타입 오류가 발생
```javascript
c = 5; // -> uncaught TypeError
```
- const 문으로 선언한 상수 값은 수정할 수 없지만, 상수값이 객체이거나 배열일 경우 프로퍼티 또는 프로퍼티 값을 수정할 수 있다.
```javascript
const origin = {x:1, y:2};
origin.x = 3;
console.log(origin); // -> Object {x:3, y:2}
```

## 함수 리터럴로 함수 정의하기
- 함수는 리터럴로도 정의할 수 있다
```javascript
var square = function(x) { 
	return x * x; 
};
```
- function(x) { ... } 부분이 함수 리터럴
- 함수 리터럴은 이름이 없느 함수 이므로 익명함수 또는 무명함수라고 한다
- 함수 선언문에서는 끝에 세미콜론(;)을 붙일 필요가 없으나 함수 리터럴을 사용할땐 끝에 세미콜론(;)을 붙여야 한다
- 함수 리터럴로 정의한 함수는 끌어올리지 않는다
```javascript
console.log(square(3)); // -> TypeError : square is not a function
var square = function(x) { return x * x; }
```
- 익명함수에도 이름을 붙일 수 있다
```javascript
var square = function sq(x) { return x * x; };
```
sq라는 함수 이름은 함수 안에서만 유효하며 함수 바깥에서는 sq라는 이름으로 함수를 호출할 수 없다.

## 객체의 메서드 
- 객체의 프로퍼티 중에서 함수 객체의 참조를 값으로 담고 있는 프로퍼티를 메서드라 한다
- 메서드를 정의할 때는 프로퍼티 값으로 함수 리터럴을 대입합니다.
```javascript  
  var circle = {
    center : { x : 1.0, y: 2.0 },  // 원의 중점을 표현하는 객체
    radius : 2.5,  // 원의 반지름
    area : function() {   // 원의 넓이를 구하는 메서드
	return Math.PI * this.radius * this.radius;
    }
  };
```  
- 함수 객체 안에 적힌 this는 그 함수를 메서드로 가지고 있는 객체를 가리킨다<br>
   (즉, circle,  this.radius -> circle.radius)   
- 메서드 호출
```javascript
   circle.area(); 
```

- 메서드 또한 프로퍼티의 일종이므로 나중에 추가 할 수 있다
```javascript
   circle.translate = function(a, b) {
      this.center.x = this.center.x + a;
      this.center.y = this.center.y + b; 
   };
   
 circle.translate(1,2);
 circle.center; // Object { x = 2, y = 4 }
```

## 즉시 실행 함수 
- 자바스크립트에는 익명 함수를 정의하고 곧바로 실행하는 '즉시 실행 함수'라는 구분이 있음
```javascript
(function() { .. })();
```
- 즉시 실행 함수에도 인수를 넘길 수 있음
```javascript
(function (a, b) {  
 return a + b;
})(1,2);
```

- 반환값 역시 받을 수 있음
```javascript
var x = (function(a,b) { 
  return a + b;
})(a,b);
```
## 가변길이 인수 목록(Arguments 객체)
- 모든 함수에 있는 지역변수로 arguments로 접근
- 유사 배열 객체 이며 다음과 같이 접근 가능
```javascript
  arguments[0]; // 첫 번째 인수 값
  arguments[1]; // 두번째 인수 값
  ... 
  arguments[n-1] : // n번째 인수 값
```
- arguments.length : 인수 개수
- arguments.callee : 현재 실행되고 있는 함수의 참조

```javascript
function f(x, y) {  
	arguments[1] = 3;  
	console.log(”x = “ + x + “, y = “ + y);
}

f(1,2); // -> x = 1, y = 3;
```

- 가변 변수로 인수로 지정하는 방법도 있음<br>
  가변 변수는 ...변수명
```javascript
function abc(a, ...b) {
 
}
abc(1, 2,3,4,5);로 호출한다면 
a는 1, b는 [2,3,4,5]
```
## 함수를 활용하면 얻을 수 있는 장점
- 재사용할 수 있다
- 만든 프로그램을 이해하기 쉽다
- 프로그램 수정이 간단해진다 
 
* * * 
# 생성자
생성자를 사용하면 이름이 같은 메서드와 프로퍼티를 가진 객체를 여러개를 효율적으로 생성할수 있다

## 생성자로 객체 생성하기
자바스크립트에서는 생성자라고 하는 함수로 객체를 생성할 수 있다.
```javascript
function Card(suit, rank) {
	this.suit = suit;
    this.rank = rank;
}

var card = new Card("하트", "A");
console.log(card); //-> Card { suit: "하트", rank : "A" }
```
### 생성자
- new 연산자로 객체를 생성할 것이라 기대하고 만든 함수를 생성자라고 한다.
- 생성자는 일반 함수와 구분할 수 있도록 관용적으로 첫 글자를 대문자로 쓴다(파스칼 표기법).
- 생성자 안에서 this.프로퍼티 이름에 값을 대입하면 그 이름을 가진 프로퍼티에 값이 할당된 객체가 생성된다.       
- 생성자와 new 연산자로 생성한 객체를 생성자의 인스턴스라고 부른다.
- 앞의 예) var card = new Card("하트", "A"); 객체 리터럴로 고쳐 쓸 수 있다.
```javascript
var card = {};
card.suit = "하트";
card.rank = "A";
```
### 생성자의 역할
- 생성자는 객체를 생성하고 초기화 하는 역할    
- 생성자 이름은 같지만 프로퍼티 값이 다른 객체(인스턴스)를 생성할 수 있다.
```javascript
var card1 = new Card("하트", "A");
var card2 = new Card("클럽", "K");
var card3 = new Card("스페이드", "2");  
```

### 객체 리터럴과 생성자의 차이
객체 리터럴은 생성자 함수가 이미 생성된 상태, 즉 객체가 변수에 대입된 상태이다. 따라서 new 연산자를 사용하여 객체를 여러개 생성할 수 없다.

- **생성자 함수**
	- 생성자 함수는 prototype 객체로 이뤄져 있고 구성요소로 constructor(생성자)와 __proto__(링커 – 상속받은 객체)로 구성되어 있다
	 
	- 생성자 함수는 생성자(constructor)가 있어 이를 new 연산자로 여러 변수에 대입하여 객체를 생성할 수 있다. 
- **객체 리터럴 **
	- 객체 리터럴은 이미 abc라는 변수에 {}라는 객체가 대입된 상태 이므로 내부적으로는 new 연산자로 생성된 상태이다. 따라서 prototype 객체는 존재 하지 않고 __proto__(링커)만 노출이 되며, 상속 관계(Object를 상속 받음)만 나타나게 된다. 
	- 즉, 객체 리터럴은 prototype객체가 없고 contructor가 없으므로 new 연산자로 생성 할 수 없다.
	- 생성자 함수도 new 연산자를 이용하여 생성된 인스턴스는 객체 리터럴과 동일한 상태
	- 객체(인스턴스) a 역시 __proto__만 존재하며 객체 상속 관계만 나타냅니다.

### 메서드를 가진 객체를 생성하는 생성자
생성자에서 this.프로퍼티 이름에 함수의 참조를 대입하면 메서드를 정의할 수 있다.
```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
	this.area = function() {
		return Math.PI * this.radius * this.radius;
	};  
}

var p = {x:0, y: 0};
var c = new Circle(p, 2.0);
console.log(“넓이 = ”+ c.area());
```
this는 생성된 객체(인스턴스)를 가리킨다.
```
function abc(a, b) {       
	this.a = a;
	this.b = b;    
}
    
var obj1 = new abc(1,2); -> this는 obj1를 가리킴
var obj2 = new abc(3,4); -> this는 obj2를 가리킴
```
## 프로토타입
### 생성자 안에서 메서드를 정의하는 방식의 문제점
생성자 안에서 this 뒤에 메서드를 정의하면 그 생성자로 생성한 모든 인스턴스에 똑같은 메서드가 추가됩니다. 따라서 메서드를 포함한 생성자로 인스턴스를 여러개 생성하면 같은 작업을 하는 인스턴스 개수만큼 생성하게 되며 결과적으로 그만큼의 메모리를 소비하게 된다.

```javascript
function Circle(center, radius) {
	this.center = center;
	this.radius = radius;
	this.area = function() {
		return Math.PI * this.radius * this.radius;
	};
}

var c1 = new Circle({x:0, y: 0}, 2.0);
var c2 = new Circle({x:0, y: 1}, 3.0);
var c3 = new Circle({x:1, y: 0}, 1.0);

console.log(c1.area === c2.area) // false  -> 생성된 생성자 내부에 있는 area메소드 이므로 서로 다르다(즉, 서로 다른 메모리에 있다)
```

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image5.png)

### 프로토타입 객체
자바스크립트에서는 함수도 객체이므로 함수 객체가 기본적으로 prototype 프로퍼티를 갖고 있다.
```javascript
function F() {}; 
console.log(F.prototype); // -> Object {}
```
- 함수의 prototype 프로퍼티가 가리키는 객체를 그 함수의 프로토타입 객체라고 한다
- 프로토타입 객체의 프로퍼티는 생성자로 생성한 모든 인스턴스에서 그 인스턴스의 프로퍼티처럼 사용할 수 있다.
- 인스턴스에 아무것도 정의 하지 않더라도(즉, 객체를 생성하지 않더라도) 처음부터 사용할 수 있는 것 
- 1번만 생성자 Prototype 프로퍼티에 정의하면 인스턴스 생성시에 생성자 함수안에 포함 되지않더라도 계속 접근 및 사용이 가능 - 메모리 절약 가능
```javascript
function Circle(center, radius) {      
	this.center = center;
	this.radius = radius;
} 
// Circle 생성자의 prototype 프로퍼티에 area 메서드를 추가
Circle.prototype.area = function() {      
	return Math.PI * this.radius * this.radius;   
};
   
var c1 = new Circle({x:0, y: 0}, 2.0);
var c2 = new Circle({x:0, y: 1}, 3.0);
var c3 = new Circle({x:1, y: 0}, 1.0);
  
console.log(c1.area === c2.area); // true  c1.area와 c2.area는 Circle.prototype.area를 서로 공유하므로 동일하다
```

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image6.png)

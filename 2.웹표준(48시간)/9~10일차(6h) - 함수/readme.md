# 함수
## 함수 정의하기
함수를 정의하는 방법은 네 가지입니다.
- 함수 선언문으로 정의하는 방법
```javascript
function square(x) { return x*x; }
```
- 함수 리터럴로 정의하는 방법
```javascript
var square = function(x) { return x*x; };
```
- Function 생성자로 정의하는 방법
```javascript
var square = new Function("x", "return x*x");
```
- 화살표 함수 표현식으로 정의하는 방법
```javascript
var square = x => x*x;
```
자바스크립트 엔진은 함수 선언문을 프로그램의 첫머리 또는 함수의 첫머리로 끌어올립니다. 따라서 함수 선언문으로 정의한 함수는 호출문이 그보다 앞에 위치해도 호출할 수 있습니다.
```javascript
console.log(square(2)); // 4
function square(x) { return x*x; }
```
그러나 함수 리터럴, Function 생성자, 화살표 함수 표현식으로 정의한 함수는 변수에 그 함수의 참조를 할당해야 비로소 사용할 수 있는 상태가 됩니다. 따라서 이 세 가지 방법으로 함수를 정의하는 코드는 호출하는 코드보다 앞에 위치해야 합니다.
```javascript
// 함수 리터럴로 정의한 함수는 그 참조를 변수에 할당한 후에 호출할 수 있다.
var square = function(x) { return x*x; };
console.log(square(2)); // 4

// Function 생성자로 정의한 함수는 그 참조를 변수에 할당한 후에 호출할 수 있다.
var square = new Function("x", "return x*x");
console.log(square(2)); // 4 

// 화살표 함수 표현식으로 정의한 함수는 그 참조를 변수에 할당한 후에 호출할 수 있다.
var square = x => x*x;
console.log(square(2)); // 4 
```

### 중첩 함수
틀정 함수 내부에 선언된 함수를 가리켜 그 함수의 중첨 함수라고 합니다. 중첩 함수(Nested Function)를 지역 함수 또는 내부 함수(Inner Function)라고 부르기도 하는데 중첩 함수(Nested Function)가 올바른 표현입니다.<br>
C나 Java 등에서는 중첩 함수를 사용할 수 없지만 자바스크립트에서는 중첩 함수를 사용할 수 있습니다. 자바스크립트에서는 외부 함수의 최상위 레벨에만 중첩 함수를 작성할 수 있습니다. 바꿔 말하면 함수 안의 if문과 while 문 등의 문장 블록 안에는 중첩 함수를 작성할 수 없습니다.
```javascript
function norm(x) {
	var sum2 = sumSquare();
	return Math.sqrt(sum2);
	function sumSquare() {
		sum = 0;
		for(var i = 0; i < x.length; i++) sum += x[i]*x[i];
		return sum;
	}
}
var a = [2,1,3,5,7];
var n = norm(a);
console.log(n);
```
중첩 함수의 참조는 그 중첩 함수를 둘러싼 외부 함수의 지역 변수에 저장되므로 외부 함수의 바깥에서는 읽거나 쓸 수 없습니다.<br><br>
중찹 함수에는 또 다른 중요한 성질이 있습니다. 바로 자신을 둘러싼 외부 함수의 인수와 지역 변수에 접근할 수 있다는 점입니다.<br><br>
예를 들면 위 예제 중첩 함수 sumSquare는 변수 x를 사용합니다. 하지만 변수 x는 외부 함수인 norm의 인수입니다. 외부 함수의 변수 유효 범위가 그 함수의 중첩 함수에까지 미친다는 규칙은 나중에 설명하는 클로저의 핵심적인 구성요소가 됩니다.

## 함수 호출하기
함수는 정의만 해서는 실생되지 않습니다. 함수는 호출해야만 실행됩니다.

### 함수를 호출하는 방법

#### 함수 호출
함수의 참조가 저장된 변수 뒤에 그룹 연산자인 ()를 붙여서 함수를 호출합니다.
```javascript
var s = square(5);
```

#### 메서드 호출
객체의 프로퍼티에 저장된 값이 함수 타입일 때는 그 프로퍼티를 메서드라고 부릅니다. 메서드를 호출할 때는 그룹 연산자인 ()를 붙여서 호출합니다. 이는 **함수 호출**과 본질적으로 같은 방법입니다.
```javascript
obj.m = function() { ... };
obj.m();
```

#### 생성자 호출
함수 또는 메서드를 호출할 때 함수의 참조를 저장한 변수 앞에 new 키워드를 추가하면 함수가 생성자로 동작합니다.
```javascript
var obj = new Object();
```

#### call, apply를 사용한 간접 호출
함수의  call과 apply 메서드를 사용하면 함수를 간접적으로 호출할 수 있습니다.

### 즉시 실행 함수
일반적으로 익명 함수를 실행할 때는 익명 함수의 참조를 변수에 할당한 후에 그룹 연산자인 ()를 붙여서 실행합니다.
```javascript
var f = function() { ... };
```
<br>
자바스크립트에서는 익명 함수를 정의하고 곧바로 실행하는 **즉시 실행 함수**라는 구문이 있습니다.
```javascript
(function() { ... })();
```
즉시 실행 함수에도 인수를 넘길 수 있습니다.
```javascript
(function(a,b) { ... })(1,2);
```
즉시 실행 함수에도 이름을 붙일 수 있지만 함수 이름이 함수 내부에서만 유효합니다.
```javascript
(function fact(n) {
	if (n<=1) return 1;
	return n*fact(n-1);
})(5);
```
함수 실행 결과를 변수 할당할 수 있으며 표현식 안에서 사용할 수 있습니다.
```javascript
var x = (function() { ... })();
```
즉시 실행 함수는 전역 유효 범위를 오염시키지 않는 이름 공간을 생성할 때 사용합니다.

## 함수의 인수
자바스크립트에서는 함수를 호출할 때 인수를 생략할 수 있습니다. 반대로 함수 정의식에 작성된 인자 개수보다 더 많은 개수의 인수를 넘겨서 함수를 실행할 수 있습니다.

### 인수의 생략
함수 정의식에 작성된 인자 개수보다 인수를 적게 전달해서 함수를 실행하면 인수에서 생략한 인자는 undefined가 됩니다.
```javascript
function f(x, y) {
	console.log("x = " + x + ", y = " + y);
}
f(2);
```
이러한 성질을 활용하면 호출할 때 인수를 생략할 수 있는 함수를 정의할 수 있습니다. 이를 구현하려면 함수 정의식에서 인수를 생략했을 때 사용할 초깃값을 설정해야 합니다.
```javascript
function multiply(a, b) {
	b = b || 1; // b의 초깃값을 1로 설정
	return a*b;
}
multiply(2, 3); // 6
multiply(2);  // 2
```

### 가변 길이 인수 목록(Arguments 객체)
모든 함수에서 사용할 수 있는 지역 변수로는 arguments 변수가 있습니다. arguments 변수의 값은 Arguments 객체 입니다. 함수에 인수를 n개 넘겨서 호출하면 인수 값이 다음과 같이 arguments에 저장됩니다.
```javascript
arguments[0] : 첫 번째 인수 값
arguments[1] : 두 번째 인수 값
... 
arguments[n-1] : n번째 인수 값
```
Arguments 객체는 프로퍼티로 length와 callee를 갖고 있으며 각 프로퍼티에는 다음과 같은 값이 담겨 있습니다.
- argument.length : 인수 개수
- arguments.callee : 현재 실행되고 있는 함수의 참조

이 Arguments 객체는 **유사 배열 객체**입니다.<br>
arguments[i] 값을 바꾸면 i + 1번째 인자가 있을 때 그 값이 함께 바뀝니다.
```javascript
function f(x, y) {
	arguments[1] = 3;
	console.log("x = " + x + ", y = "+ y);
}
x(1, 2);  // x = 1, y = 3
```
<br>
arguments 변수를 활용하면 인수 개수가 일정하지 않은 가변 인수 함수를 정의할 수 있습니다.

```javascript
function myConcat(separator) {
	var s = "";
	for (var i = 1; i < arguments.length; i++) {
		s += arguments[i];
		if (i < arguments.length - 1) s += separator;
	}
	return s;
}
console.log(myConcat("/", "apple", "orange", "peach")); // apple/orange/peach
```
<br>
arguments[]는 유사 배열 객체입니다. 하지만 다음과 같은 방법을 사용하면 배열 객체로 변환할 수 있습니다.

```javascript
var params = [].slice(arguments);
```

## 재귀 함수
함수가 자기 자신을 호출하는 행위를 가리켜 <b>재귀 호출(recursive call)</b>이라고 합니다. 이러한 재귀 호출을 수행하는 함수를 **재귀 함수**라고 합니다.

### 재귀 함수릐 기본
```javascript
function fact(n) {
	if (n <= 1) return 1;
	return n*fact(n-1);
}
fact(5); // 120
``` 
함수 fact를 함수 함수 리터럴로 정의하려면 다음과 같이 함수 리터럴 표현식에 함수 이름을 적습니다. 단, 함수 이름 f는 함수 안에서만 유효합니다.
```javascript
var fact = function f(x) {
	if (n <= 1) return 1;
	return n*f(n-1);
}
```
<br>
arguments.callee를 사용하면 이름이 없는 익명 함수도 재귀호출을 할 수 있습니다. arguments.callee가 지금 실행 중인 함수를 가리키기 때문입니다.
```javascript
var fact = function(n) {
	if (n <= 1) return 1;
	return n*arguments.callee(n-1);
}	
```
<br>
재귀 함수를 정의할 때는 다음 두 가지 사항에 유의해야 합니ㅏㄷ.
- 재귀 호출은 반드시 멈춰야 한다.<br>
	함수가 자신을 호출하면 무한한 연쇄 호출로 이어지므로 프로그램이 멈추지 않을 가능성이 있습니다. 따라서 재귀 호출이 중간에 멈출 수 있도록 만들어야 합니다.
	

- 재귀 호출로 문제를 간단하게 해결할 수 있을 때만 사용한다.

## 프로그램 평가와 실행과정

## 클로저

## 객체써의 함수

## 콜백함수

## ECMAScript6+에 추가된 기능
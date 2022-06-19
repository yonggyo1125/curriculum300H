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


## 재귀함수

## 프로그램 평가와 실행과정

## 클로저

## 객체써의 함수

## 콜백함수

## ECMAScript6+에 추가된 기능
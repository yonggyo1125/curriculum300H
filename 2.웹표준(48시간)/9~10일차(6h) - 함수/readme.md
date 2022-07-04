# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1-sdfVxRGLpwqupCJCHEnE3q6NefvMGNC?usp=sharing)

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
특정 함수 내부에 선언된 함수를 가리켜 그 함수의 중첩 함수라고 합니다. 중첩 함수(Nested Function)를 지역 함수 또는 내부 함수(Inner Function)라고 부르기도 하는데 중첩 함수(Nested Function)가 올바른 표현입니다.<br>
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
자바스크립트에서는 익명 함수를 정의하고 곧바로 실행하는 <b>즉시 실행 함수</b>라는 구문이 있습니다.

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

### 재귀 함수의 기본
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
재귀 함수를 정의할 때는 <b>다음 두 가지 사항에 유의</b>해야 합니다.

- 재귀 호출은 반드시 멈춰야 한다.<br>
	함수가 자신을 호출하면 무한한 연쇄 호출로 이어지므로 프로그램이 멈추지 않을 가능성이 있습니다. 따라서 재귀 호출이 중간에 멈출 수 있도록 만들어야 합니다.
	
![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image1.png)
	

- 재귀 호출로 문제를 간단하게 해결할 수 있을 때만 사용한다.
	- 재귀 함수는 재귀 호출이 멈출 때까지 몇 번이고 자기 자신을 호출하는 함수입니다.
	- 호출된 각각의 재귀 함수는 메모리의 다른 영역을 사용합니다. 따라서 호출된 횟수만큼 메모리 소비량이 늘어납니다. 
	- 반복문을 재귀함수로 바꾸어 표현할 수 있는지만 대부분은 while문이나 for문으로 작성하는 편이 이해하기 쉽고 메모리 공간도 적게 차지합니다.
	- 따라서 재귀 함수는 재귀 호출로 문제를 더 간단하게 해결할 수 있을 때만 사용하는 것이 바람직합니다.
	
## 프로그램 평가와 실행과정

### 실행 가능한 코드
자바스크립트 엔진은 실행 가능한 코드(Executable Code)를 만나면 그 코드를 평가(Evaluation)해서 실행 문맥(Execution Context)으로 만듭니다. 이 실행 가능한 코드(Executable Code)의 유형은 다음과 같습니다.
- 전역 코드
- 함수 코드
- eval 코드

전역 코드는 전역 객체 Window 아래에 정의된 함수를 말하고, 함수 코드는 문자 그래도 함수를 말하며, eval 코드는 eval 함수를 말합니다.<br><br>
자바스크립트 엔진이 실행 가능한 코드의 유형을 분류하는 이유는 실행 문맥을 초기화하는 환경과 과정이 다르기 때문입니다. 

### 실행 문맥의 구성
실행 문맥(Execution Context)은 실행 가능한 코드가 실제로 실행되고 관리되는 영역으로 실행에 필요한 모든 정보를 컴포넌트 여러 개가 나누어 관리하도록 만들어져 있습니다.<br><br>

그 중에서 가중 중요한 컴포넌트는 <b>렉시컬 환경(LexicalEnvironment) 컴포넌트</b>, <b>변수 환경(VariableEnvironment) 컴포넌트</b>, <b>디스 바인딩(This Binding) 컴포넌트</b>입니다. 다음 코드는 자바스크립트의 객체 표현을 빌려 실행 문맥을 표현한 것입니다. 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.

```javascript
// 실행 문맥
ExecutionContext = {
	// 렉시컬 환경 컴포넌트
	LexicalEnvironment: {},
	
	// 변수 환경 컴포넌트
	VariableEnvironment: {},
	
	// 디스 바인딩 컴포넌트
	ThisBinding: null,
}
```

#### 렉시컬 환경 컴포넌트와 변수 환경 컴포넌트
렉시컬 환경(LexicalEnvironment) 컴포넌트와 변수 환경(VariableEnvironment) 컴포넌트는 앞으로 설명할 렉시컬 환경(Lexical Environment) 타입의 컴포넌트 입니다. 렉시컬 환경 컴포넌트와 변수 환경 컴포넌트는 타입이 같고 실제로 내부 값이 같으므로 똑같이 취급해도 무리가 없습니다. 따라서 이후부터는 렉시컬 환경 컴포넌트로 통일해서 설명하겠습니다.

#### 디스 바인딩 컴포넌트
디스 바인딩(This Binding) 컴포넌트는 그 함수를 호출한 객체의 참조가 저장되는 곳입니다.이것이 가리키는 값이 곧 해당 실행 문맥의 this가 됩니다.

#### 렉시컬 환경 컴포넌트의 구성
실행 문맥의 구성 요소인 렉시컬 환경(LexicalEnvironment) 컴포넌트는 자바스크립트 엔진이 자바스크립트 코드를 실행하기 위한 자원을 모아 둔 곳으로 구체적으로는 함수 또는 블록의 유효 범위안에 있는 식별자와 그 결과값이 저장되는 곳입니다.<br>자바스크립트 엔진은 해당 자바스크립트 코드의 유효범위 안에 있는 식별자와 그 식별자가 가리키는 값을 키와 값의 쌍으로 바인드해서 렉시컬 환경 컴포넌트에 기록합니다.<br><br>
렉시컬 환경 컴포넌트는 <b>환경 레코드(Environment Record)</b>와 <b>외부 렉시컬 환경 참조(Outer Lexical Environment Reference) 컴포넌트</b>로 구성되어 있습니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사코드로 렉시컬 환경 컴포넌트를 표현한 것입니다. 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.

```javascript
// 렉시컬 환경 컴포넌트
LexicalEnvironment: {
	// 환경 레코드
	EnvironmentRecord: {},
	// 외부 렉시컬 환경 참조
	OuterLexicalEnvironmentReference: {}
}
```

#### 환경 레코드
환경 레코드(Environment Record)는 유효 범위 안에 포함된 식별자를 기록하고 실행하는 영역으로 ECMAScript 3의 변수 객체(Variable Object)와 매우 비슷한 역할을 합니다. 자바스크립트 엔진은 유효 범위 안의 식별자와 결과값을 바인드해서 환경 레코드에 기록합니다.

#### 외부 렉시컬 환경 참조
자바스크립트는 함수 안에 함수를 중첩해서 정의할 수 있는 언어입니다. 따라서 자바스크립트 엔진은 유효 범위 너머의 유효 범위도 검색할 수 있어야 합니다. 외부 렉시컬 환경 참조(Outer Lexical Environment Reference)에는 함수를 둘러싸고 있는 코드가 속한 렉시컬 환경 컴포넌트의 참조가 저장됩니다. 중첩된 함수 안에서 바깥 코드에 정의된 변수를 읽거나 써야 할 때, 자바스크립트 엔진은 외부 렉시컬 환경 참조를 따라 한 단계씩 렉시컬 환경을 거슬러 올라가서 그 변수를 검색합니다.

### 환경 레코드의 구성
렉시컬 환경 컴포넌트 구성 요소인 환경 레코드(Environment Record)는 렉시컬 환경 안의 식별자와 그 식별자가 가리키는 값의 묶음이 실제로 저장되는 영역입니다. 이 환경 레코드는 <b>선언적 환경 레코드(Declarative Environment Record)</b>와 <b>객체 환경 레코드(Object Environment Record)</b>로 구성되어 있으며 저장하는 값의 유형에 따라 쓰임새가 달라집니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사 코드로 환경 레코드를 표현한 것입니다. 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.

```javascript
// 환경 레코드
EnvironmentRecord: {
	// 선언적 환경 레코드
	DeclarativeEnvironmentRecord: {} 
	
	// 객체 환경 레코드
	ObjectEnvironmentRecord: {}
}
```
#### 선언적 환경 레코드
<b>선언적 환경 레코드(Declarative Environment Record)</b>는 실제로 함수와 변수, catch 문의 식별자와 실행 결과가 저장되는 영역입니다.

#### 객체 환경 레코드
선언적 환경 레코드가 식별자와 그 실행 결과를 키와 값의 쌍으로 관리하는 반면 <b>객체 환경 레코드(Object Environment Record)</b>는 실행 문맥 외부에 별도로 저장된 객체의 참조에서 데이터를 읽거나 씁니다.<br>
즉, with문의 렉시컬 환경이나 전역 객체처럼 별도의 객체에 저장된 데이터는 그 객체가 가진 키와 값의 쌍을 복사해 오는 것이 아니라 **그 객체 전체의 참조를 가져와서 객체 환경 레코드의 bindObject라는 프로퍼티에 바인드**하도록 만들어져 있습니다.

### 전역 환경과 전역 객체의 생성 
자바스크립트 인터프리터는 시작하자마자 렉시컬 환경 타입의 전역환경(Global Environment)을 생성합니다. 웹 브라우저에 내장된 자바스크립트 인터프리터는 새로운 웹 페이지를 읽어 들인 후에 전역 환경을 생성합니다. 그리고 전역 객체를 생성한 다음 전역 환경의 객체 환경 레코드에 전역 객체의 참조를 대입합니다. 최상위 레벨(함수 바깥에 있는 코드)의 this는 [전역 객체](https://github.com/yonggyo1125/curriculum300H/tree/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%82%B4%EC%9E%A5%EA%B0%9D%EC%B2%B4#%EC%A0%84%EC%97%AD-%EA%B0%9D%EC%B2%B4) 를 가리킵니다.

```javascript
this === window // true
```
이 코드의 상태를 의사 코드로 표현하면 다음과 같습니다.
```javascript
// 전역환경
GlobalEnvironment = {
	ObjectEnvironmentRecord: {
		bindObject: window
	},
	OuterLexicalEnvironmentReference: null
}

// 전역 실행 문맥
ExecutionContext = {
	LexicalEnvironment: GlobalEnvironment,
	ThisBinding: window,
}
```
- 웹 브라우저의 자바스크립트 실행 환경에서는 Window 객체가 전역 객체이므로 객체 환경 레코드의 bindObject 프로퍼티에는 전역객체 Window의 참조가 할당됩니다. 이로 인해 전역 환경의 변수와 함수를 Window 안에서 검색하게 됩니다. 
- 전역 환경의 외부에는 다른 렉시컬 환경이 없으므로 외부 렉시컬 환경 참조(Outer Lexical Environment Reference)에는 null을 할당합니다.
- 전역 실행 문맥의 디스 바인딩 컴포넌트에도 Window의 참조가 할당되어 전역 실행 문맥의 this가 Window를 가리키게 되고, 전역 실행 문맥의 프로퍼티를 디스 바인딩 컴포넌트 안에서 검색하게 됩니다.
- Window 객체는 Window 객체의 프로퍼티인 window로 참조할 수 있습니다. Window 객체에는 일반 전역 객체의 프로퍼티와 클라이언트 측 자바스크립트에서만 사용할 수 있는 다양한 프로퍼티가 정의되어 있습니다.

### 프로그램 평가와 전역 변수
전역 환경과 전역 객체를 생성한 후에는 자바스크립트 프로그램을 읽어 들입니다. 자바스크립트 프로그램을 다 읽어 들인 후에는 프로그램을 평가하며, 최상위 레벨에 var 문으로 작성한 전역 변수는 전역 환경의 환경 레코드(객체 환경 레코드)의 프로퍼티로 추가됩니다.
```javascript
// 전역 환경
GlobalEnvironment = {
	// 전역 환경의 환경 레코드인 객체 환경 레코드에 Window의 참조가 설정되어 있음
	ObjectEnvironmentRecord: {
		bindObject : window
	},
	OuterLexicalEnvironmentReference: null
}
```
자바스크립트 엔진은 전역 코드를 평가할 떄 최상위 레벨에 var로 작성한 전역 변수를 전역 환경의 환경 레코드(객체 환경 레코드)에 프로퍼티로 기록합니다. 그 프로퍼티 이름은 식별자 이름이 되고 프로퍼티 값은 undefined가 됩니다. 함수의 경우 최상위 레벨에 작성된 함수 선언 문을 함수 객체로 생성해서 전역 환경의 환경 레코드(객체 환경 레코드)에 프로퍼티로 기록합니다. 이처럼 최상위 레벨에 선언된 변수와 함수는 프로그램을 평가할 때 객체 환경 레코드에 기록됩니다.

```javascript
var a = { x: 1, y: 2 };
console.log(window.a);  // Object { x=1, y=2 }
function norm(x) { ... }
console.log(window.norm);  // norm(x)
```
즉, 전역 변수의 실체는 전역 객체의 프로퍼티 또는 전역 객체의 실행 문맥에 들어 있는 환경 레코드(객체 환경 레코드)의 프로퍼티입니다. 마찬가지로 함수 안에 선언된 지역 변수와 중첩 함수의 참조 또한 그 함수가 속한 실행 환경의 환경 레코드(선언적 환경 레코드)의 프로퍼티입니다. 이처럼 자바스크립트의 모든 변수를 객체의 프로퍼티로 간주하면 쉽게 이해할 수 있습니다.<br><br>
앞에서 이야기한 것 처럼 최상위 레벨에 선언된 변수와 함수는 프로그램을 평가하는 시점에 환경 레코드(객체 환경 레코드)에 추가됩니다. 최상위 레벨에 선언된 함수와 변수는 프로그램을 평가하는 단계에 이미 객체 환경 레코드에 추가된 상태이기 때문에 코드의 어느 위치에 작성해도 전체 프로그램이 참조할 수 있습니다. 이것이 **최상위 코드의 변수 선언문과 함수 선언문의 끌어올림**이라는 현상의 실체입니다.
또한 var문과 함수 선언문으로 선언한 전역 변수는 [[Configuable]] 속성이 false로 설정되어 있어서 delete 연산자로 삭제할 수 없습니다. [[Configurable]]은 스펙에 정의된 명칭이며 자바스크립트에서는 configurable이라는 이름으로 참조할 수 있습니다.
```javascript
var a = { x: 1, y: 2 };
console.log(Object.getOwnPropertyDescriptor(window, 'a'));
// -> Object {value: {...}, writable: true, enumerate: true, configurable: false}
delete a;
console.log(a); // -> Object {x: 1, y: 2}
```
하지만 var 문을 사용하지 않고 변수를 선언해서 값을 할당하면 프로그램을 실행하는 도중에 디스 바인딩 컴포넌트가 가리키는 객체의 프로퍼티로 추가됩니다. 전역 객체의 디스 바인딩 컴포넌트는 전역 객체를 가리키므로 결국 전역 객체의 프로퍼티가 되는 것입니다. 또한 [[Configurable]] 속성도 true로 설정됩니다. 따라서 이 경우에는 delete 연산자로 삭제할 수 있습니다.

```javascript
a = { x: 1, y: 2 };
console.log(Object.getOwnPropertyDescriptor(window, 'a'));
// -> Object {value: {...}, writable: true, enumerable: true, configurable: true}
delete a;
console.log(a); // -> Uncaught ReferenceError: a is not defined
```
> Object.getOwnPropertyDescriptor 메서드<br>프로퍼티의 속성을 가져오는 용도로 사용하며 해당 프로퍼티 속성이 담긴 프로퍼티 디스크립터라는 객체를 반환합니다.

### 프로그램 실행과 실행 문맥
- 프로그램이 평가된 다음에는 프로그램이 실행되며, 프로그램은 실행 문맥(Execution Context)안에서 실행됩니다. 실행 문맥은 실행 가능 코드(Executable Code, 전역 코드, Eval 코드, 함수 코드)별로 생성됩니다.
- 실행 문맥은 **스택**이라는 구조로 관리됩니다. 스택이란 일종의 자료 구조로 데이터를 아래에서부터 쌓아 올리며 마지막으로 추가한 데이터를 먼저 꺼내는 <b>후입선출(LIFO, Last In First Out)</b> 방식으로 관리됩니다. 스택의 가장 윗부분에 데이터를 쌓는 행위를 **push**라고 하고, 스택의 가장 윗부분에서 데이터를 배내는 행위를 **pop**이라고 합니다.
- 실행 문맥은 프로그램 실행 중에 스택에 push되어 실행됩니다. 가장 먼저 실행하는 코드는 전역 코드이며, 이 때문에 스택의 맨 아랫부분에는 항상 전역 코드를 실행하기 위한 실행 문맥이 자리 잡고 있습니다.
- 전역 코드 안에서 함수를 실행하면 그 함수를 실행하기 위한 실행 문맥을 스택에 push 합니다. 그리고 그 함수의 작업을 끝내고 함수를 호출한 부분으로 제어권이 돌아오면 스택에서 pop합니다.
- 이때 실행하는 함수가 특정 함수의 내부에 정의된 중첩함수라면 중첩 함수에 실행 문맥을 새로 만들어서 스택에 push합니다.(**중첩 함수의 실행 문맥이 외부 함수의 실행 문맥 안에 중첩되지 않습니다.**)
- 마찬가지로 함수 안에 있는 코드를 실행하는 도중에 다른 함수를 호출하면 그 함수의 실행 문맥도 스택에 push합니다.  이 방식은 중첩 함수를 호출했을 경우와 함수를 재귀적으로 호출한 경우에도 똑같이 적용됩니다.
- 재귀 호출한 함수는 분명 호출한 함수와 같은 함수입니다. 하지만 전혀 다른 함수로 스택에 push됩니다.
- 이처럼 함수의 실행 문맥은 호출될 때마다 스택에 push 됩니다. 그리고 return 문이 실행되어 제어권이 호출한 코드로 돌아가면 스택에서 pop됩니다. 
- 이런 이유로 실행 문맥 스택을 <b>호출스택(call stack)</b>이라는 이름으로 부릅니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image2.png)

### 자바스크립트는 싱글 스레드
프로그램을 실행하는 방식에는 **싱글 스레드** 방식과 **멀티스레드** 방식이 있습니다. 스레드란 프로그램의 처리 흐름입니다. 
- 싱글스레드 방식 : 프로그램 한 개의 처리 흐름으로 프로그램을 순차적으로 실행하는 방식
- 멀티스레드 방식 : 프로그램 여러 개의 처리 흐름으로 동시에 작업을 여러 개 병렬로 실행하는 방식입니다.


#### 싱글 스레드와 멀티스레드

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image3.png)

- 자바스크립트에서는 작업을 싱글 스레드로 처리합니다. 호출 스택에 쌓인 실행 문맥(함수 또는 코드)을 위에서부터 아래로 차례차례 실행해 나갑니다. 그리고 실행 문맥 하나의 작업이 끝나면 pop을 하고 바로 아래에 있는 실행 문맥을 실행합니다. 
- 즉, 실행 문맥 단위의 작업을 차례대로 실행하므로 실행 문맥(함수나 코드) 하나의 작업이 끝날 때까지 또 다른 실행 문맥의 작업을 실행 하지 않습니다.
- 이벤트 처리와 같은 비동기 처리도 똑같은 방식으로 실행합니다. 실행할 준비를 마친 이벤트 처리기 함수와 비동기 처리는 실행하기 앞서 이벤트 큐에 대기 행렬을 만듭니다.  그리고 현재 실행 중인 함수의 작업이 끝나면 대기 행렬에서 실행을 기다리는 첫 번째 실행 문맥(함수 또는 코드)부터 차례대로 호출 스택에 push해서 실행해 나갑니다.
- 이때 웹 브라우저의 API인 Web Workers를 사용하면 특정 작업을 백그라운드에 있는 다른 스레드에서 실행할 수 있습니다. 즉, 멀티스레드 처리를 할 수 있습니다.

### 환경 레코드와 지역 변수
함수를 호출하면 현재 실행 중인 코드의 작업을 일시적으로 멈추고 <b>실행 문맥(Execution Context)</b>영역을 생성합니다. 그리고 프로그램의 실행 흐름이 그 실행 문맥으로 이동합니다. 다음으로 그 함수의 실행 문맥이 호출 스택에 push되고 실행 문맥 안에 렉시컬 환경 컴포넌트를 생성합니다.<br>이 렉시컬 환경 컴포넌트는 환경 레코드를 가지고 있으며 환경 레코드 안에 그 함수 안에서 선언된 중첩 함수의 참조와 변수를 기록합니다. 즉 <b>함수 안팍의 환경(Environment)</b>을 기록합니다. 이 환경 레코드는 사용자가 읽거나 쓸 수 없으며 다음과 같은 정보를 기록하는 용도로 사용됩니다.
- 함수의 인자 
- 함수 안에서 선언된 중첩 함수의 참조
- 함수 안에서 var로 선언한 지역 변수
- arguments

함수가 실행될 때 그 함수의 선언적 환경 레코드에는 이에 대응하는 인수의 값이 설정되며, 대응하는 인수가 없으면 undefined가 설정됩니다. 함수 선언문으로 생성한 함수 안의 지역 변수에는 함수 선언문으로 생성한 함수 객체의 참조가 설정됩니다. var로 선언한 지역 변수에는 undefined가 설정됩니다. arguments는 인수 값 전체 목록, length 프로퍼티, callee 프로퍼티를 갖고 있습니다.<br><br>

함수의 실행 문맥, 렉시컬 환경, 환경 레코드가 생성되면 실행 문맥에 있는 <b>디스 바인딩(This Binding)</b> 컴포넌트에 그 함수를 호출한 객체의 참조를 저장하며, 이것으로 **this 값을 결정**합니다. 이 this는 동적이며 함수를 호출하는 상황에 따라 가리키는 객체가 바뀝니다.<br><br>

환경 레코드와 this값이 결정되면 함수 안의 코드가 순서대로 실행됩니다. 함수가 실행되는 시점에는 지역 변수 또는 함수 선언문으로 선언한 함수 이름이 함수를 평가하는 시점에 선언적 환경 레코드에 기록된 상태입니다. 따라서 변수 또는 함수 선언문이 함수 안의 어떤 부분에 위치하더라도 함수 전체에서는 사용할 수 있습니다. <b>이것이 변수 선언문과 함수 선언문이 함수의 첫머리로 끌어올려지는(호이스팅되는) 이유입니다.</b><br><br>
함수가 종료되어 제어권이 호출한 코드로 돌아가면 일반적으로 실행 문맥과 함께 그 안에 있는 렉시컬 환경 컴포넌트가 메모리에서 지워집니다. 하지만 그 함수의 바깥에 위치한 함수의 참조가 환경 레코드에 유지되는 경우에는 렉시컬 환경 컴포넌트가 메모리에서 지워지지 않습니다.

### this 값
함수가 호출되어 실행되는 시점에 this값이 결정됩니다. 이 this 값은 **함수가 호출되었을 때 그 함수가 속해 있던 객체의 참조**이며 실행 문맥의 디스 바인딩 컴포넌트가 참조하는 객체입니다.
```javascript
var tom = {
	name : "Tom",
	sayHello : function() {
		console.log("Hello! " + this.name);
	}
};
```

앞의 sayHello 메서드를 호출하면 다음과 같은 결과가 나옵니다.
```javascript
tom.sayHello(); // -> Hello! Tom
```

이 코드에서는 함수를 tom.sayHello라는 이름으로 참조해서 실행하고 있습니다. tom.sayHello가 속한 객체는 tom입니다. 즉, sayHello 메서드가 호출되는 실행 문맥의 디스 바인딩 컴포넌트가 가리키는 객체가 tom입니다. 따라서 this 값이 tom을 가리키므로 this.name 값이 "Tom"이 됩니다. tom.sayHello 값은 함수의 참조입니다. 따라서 이 값을 다른 객체의 프로퍼티에 대입할 수 있습니다.
```javascript
var huck = { name : "Huck" };
huck.sayHello = tom.sayHello;
```
객체 huck의 sayHello 메서드를 호출하면 다음과 같은 결과가 나옵니다.
```javascript
huck.sayHello(); // -> Hello! Huck
```
이 코드에서 함수를 huck.sayHello라는 이름으로 참조해서 실행합니다. huck.sayHello가 속한 객체는 huck입니다. 즉, 실행 문맥의 디스 바인딩 컴포넌트가 가리키는 객체가 tom에서 huck으로 바뀌었습니다. 따라서 this 값이 huck 객체를 가리키고 this.name 값이 huck이 됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image4.png)

이처럼 자바스크립트 함수는 특정 객체에 묶여 있지 않습니다. 일반적으로는 객체 여러개가 함수 하나를 가리킵니다. 따라서 **호출될 때의 상황에 따라 this가 가리키는 객체가 바뀝니다.** 엄밀히 말하면 실행 문맥의 디스 바인딩 컴포넌트가 가리키는 객체가 무엇인지에 따라 this가 가리키는 객체가 바뀝니다.

#### 상황별 this가 가리키는 객체
- **최상위 레벨 코드의 this**<br>
	최상위 레벨 코드의 this는 전역 객체를 가리킵니다. 실행 문맥이 초기화될 때 그 안의 디스 바인딩 컴포넌트가 전역 환경을 가리키도록 초기화되기 때문입니다.
	```javascript
	console.log(this); // -> Window
	```
- **이벤트 처리기 안에 있는 this**<br>
이벤트 처리기 안에 있는 this는 이벤트가 발생한 요소 객체(이벤트 처리기가 등록된 객체)를 가리킵니다.

- **생성자 함수 안에 있는 this**<br>
사용자가 정의한 생성자 함수 안에 있는 this는 그 생성자로 생성한 객체를 가리킵니다.

- **생성자의 prototype 메서드 안에 있는 this**<br>
생성자의 prototype 메서드 안에 있는 this는 그 생성자로 생성한 객체를 가리킵니다.

- **직접 호출한 함수 안에 있는 this**<br>
함수를 최상위 레벨의 코드에서 호출하면 함수 안에 있는 this가 전역 객체를 가리킵니다. 이는 f(); 코드 앞에 객체가 없으므로 디스 바인딩 컴포넌트가 전역 객체를 가리키기 때문입니다.

```javascript
function f() { console.log(this); }
f(); // Window
```
함수 앞에 어떤 객체를 붙여서 호출하면 디스 바인딩 컴포넌트가 그 객체를 가리킵니다.
```javascript
var a = {};
// a의 프로퍼티 위에 정의한 함수 f의 참조를 대입한다.
a.f = f;
// a의 f를 호출하면 함수 f가 실행되는 실행 문맥의 디스 바인딩 컴포넌트가 객체 a를 가리킨다.
a.f(); // Object { f: f}
```
- **apply와 call 메서드로 호출한 함수 안에 있는 this**<br>
함수 객체의 apply와 call 메서드를 사용하면 함수를 호출 할 때 this가 가리키는 객체를 바꿀 수 있습니다. 즉, 그 함수 객체가 실행되는 실행 문맥의 디스 바인딩 컴포넌트가 가리키는 객체를 명시적으로 설정할 수 있습니다.


### 식별자 결정 : 유효 범위 체인
자바스크립트는 대다수의 언어와 마찬가지로 어휘적 유효 범위를 채용하고 있습니다. 그래서 변수를 선언하면 그 안쪽에 있는 코드 전체가 그 변수를 사용할 수 있는 유효 범위가 됩니다.<br>
예를 들어 전역 변수의 유효 범위는 코드 전체이고, 함수 안에 있는 지역 변수의 유효 범위는 함수 안에 있는 전체 코드입니다. 따라서 중첩 함수와 외부 함수 혹은 전역 코드에서 같은 이름을 가진 변수를 사용하면 충돌이 발생합니다. 이때 변수 x가 어디에서 선언된 변수인지를 결정하는 작업을 가리켜 변수 x의 <b>식별자 결정(identifier Resolution)</b>이라고 합니다.<br>
자바스크립트의 식별자 규칙은 **좀 더 안쪽 코드에 선언된 변수를 사용한다**입니다.

```javascript
var a = "A";
function f() {
	var b = "B";
	function g() {
		var c = "C";
		console.log(a+b+c);
	}
	g();
}
f(); // -> ABC
```
자바 스크립트 엔진은 앞 코드의 함수 g안의 문장인 console.log(a+b+c)에서 변수 a,b,c의 식별자를 어떻게 찾아낼까요?
- 일반적으로 함수의 인수와 지역 변수를 **속박 변수**라고 하고 그 외의 변수를 **자유 변수**라고 합니다. 
- 앞 코드에서는 c가 속박 변수이고 a와 b가 자유 변수 입니다. 또한 속박 변수만 포함하는 함수를 **닫힌 함수**, 자유 변수를 가지고 있는 함수를 **열린 함수**라고 합니다.
- 앞 코드에서는 함수 f가 닫힌 함수이고 함수 g가 열린 함수 입니다.
<br>

내부적으로는 변수 식별자 결정에 렉시컬 환경 컴포넌트 안의 외부 렉시컬 환경 참조와 환경 레코드(선언적 환경 레코드 또는 객체 환경 레코드)를 사용해서, 변수 a, b, c가 선언된 위치를 다음과 같은 방법으로 찾아냅니다.

#### 속박 변수 c
변수 c는 함수 g 안에서 선언된 속박 변수이므로 함수 g의 환경 레코드(선언적 환경 레코드)안에서 찾을 수 있습니다. 변수 c는 이것으로 결정합니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.
```javascript
// 함수 g가 속산 렉시컬 환경 컴포넌트
g.LexicalEnvironment: {
	// 선언적 환경 레코드
	DeclarativeEnvironmentRecord: {
		c: "C"
	},
	// 함수 f의 렉시컬 환경 컴포넌트를 참조
	OuterLexicalEnvironmentReference: f_LexicalEnvironment 
}
```

#### 자유 변수 b
변수 b는 함수 g의 바깥에서 선언된 자유 변수입니다. 변수 b는 함수 g가 속한 실행 문맥의 환경 레코드(선언적 환경 레코드)안에서 찾을 수 없습니다. 그래서 <b>실행 문맥 속에 있는 외부 렉시컬 환경 참조를 따라 g를 호출한 함수인 f가 속한 실행 문맥의 환경 레코드(선언적 환경 레코드)를 검색합니다.</b> 변수 b는 함수 f안에 선언되어 있으므로 함수 f의 환경 레코드(선언적 환경 레코드)안에서 찾을 수 있습니다. 이제 식별자를 이것으로 결정합니다.<br><br>

함수 f가 호출되면 함수 f의 환경 레코드에 변수 b가 프로퍼티로 추가됩니다. 그 후에 함수 g의 선언문이 평가되어 환경 레코드가 생성됩니다. 이때 함수 g의 함수 객체가 함수 f의 렉시컬 환경을 참조합니다. 이 참조로 함수 g안에서 변수 b를 사용할 수 있게 됩니다. 이러한 과정을 거쳐 함수 g를 실행하는 시점에는 변수 b의 위치를 외부 렉시컬 환경 참조를 따라 검색할 수 있는 상태가 됩니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.

```javascript
// 함수 g가 속한 실행 렉시컬 환경 컴포넌트
g.LexicalEnvironment: {
	// 선언적 환경 레코드
	DeclarativeEnvironmentRecord: {
		c: "C"
	},
	// 함수 f의 렉시컬 환경 컴포넌트를 참조
	OuterLexicalEnvironmentReference: global_LexicalEnvironment
}
```

#### 자유 변수 a
변수 a는 함수 g의 바깥에서 선언된 자유 변수입니다. 이 경우에는 변수 a를 함수 g의 환경 레코드(선언적 환경 레코드)안에서 찾을 수 없습니다. 그래서 실행 문맥 속에 있는 외부 렉시컬 환경 참조를 따라 함수 g를 호출한 함수인 f의 환경 레코드(선언적 환경 레코드)내부를 검색합니다.<br>
변수 a는 var로 선언되어 있으므로 전역 실행 문맥의 환경 레코드(객체 환경 레코드) 안에서 찾을 수 있습니.(변수 a가 var로 선언되어 있지 않다면 전역 실행 문맥의 디스 바인딩 컴포넌트가 가리키는 전역 객체 안에서 검색합니다). 이제 식별자를 이것으로 결정합니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습니다.

```javascript
// 함수 g가 속한 렉시컬 환경 컴포넌트
g_LexicalEnvironment: {
	// 선언적 환경 레코드
	DeclarativeEnvironmentRecord: {
		c: "C"
	},
	// 함수 f의 렉시컬 환경 컴포넌트를 참조
	OuterLexicalEnvironmentReference: f_LexicalEnvironment
}

// 함수 f가 속한 렉시컬 환경 컴포넌트
f_LexicalEnvironment: {
	DeclarativeEnvironmentRecord: {
		b: "B"
	},
	// 전역 실행 환경의 렉시컬 환경 컴포넌트를 참조
	OuterLexicalEnvironmentReference: global_LexicalEnvironment
}

// 전역 실행 환경(렉시컬 환경 컴포넌트)
global_LexicalEnvironment: {
	ObjectEnvironmentRecord: {
		bindObject: {
			a: "A"
		}
	},
	OuterLexicalEnvironmentReference: null
}
```
이처럼 식별자 결정은 현재 유효 범위 안에 없는 식별자를 찾을 때 바깥쪽 범위로 호출자의 렉시컬 환경에 속한 외부 렉시컬 환경의 참조를 따라 찾아가는 방식을 취합니다. 이러한 <b>논리적 연결고리(선형 목록)</b>를 ECMAScript 3 기준에서는  **스코프 체인**이라고 부릅니다. ECMAScript 5 부터는 **스코프 체인**이라는 단어가 사라졌으므로 <b>외부 렉시컬 환경 체인(Outer Lexical Environment Reference Chain)</b>이라는 단어가 적합하지만 이 또한 ECMAScript 5 스펙에서는 **유효 범위 체인**이라는 이름으로 부릅니다.<br><br>
다음 그림은 유효 범위 체인의 참조 관계를 그림으로 묘사한 것입니다. 이 그림에서는 외부 렉시컬 환경 참조가 저장된 프로퍼티를 __scope__로 표기했습니다.<br>

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image5.png)

유효 범위 체인 안에서 식별자를 찾지 못하면 참조 오류(ReferenceError)가 발생합니다. 유효 범위 체인은 클로저를 이해하는 데 가장 기본이 되는 지식입니다.


### 가비지 컬렉션
프로그램에서 객체를 생성하면 메모리 공간이 동적으로 확보됩니다. 사용하지 않는 객체의 메모리 영역은 **가비지 컬렉터**가 자동으로 해제 합니다. 이 메커니즘을 가리켜 <b>가비지 컬렉션(garbage collection)</b>이라고 합니다. 이때 **사용하지 않는 객체**란 **다른 객체의 프로퍼티 변수가 참조하지 않는 객체**를 말합니다.

```javascript
var p = {x: 1, y: 2};
console.log(p);  // -> Object {x=1, y=2}
p = null;  // -> Object {x=1, y=2}를 참조하지 않게 됨
```

이 코드의 세 번째 줄이 실행되면 객체 {x:1, y:2}는 그 어떤 변수에서도 참조하지 않는 상태가 됩니다. 따라서 가비지 컬렉터는 객체 {x:1, y:2}를 메모리에서 해제합니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image6.png)

가비지 컬렉터가 동작하므로 프로그래머는 필요한 객체를 생성하기만 하면 됩니다. 객체를 삭제한 다음 메모리에서 해제하는 작업은 가비지 컬렉터에게 위임하는 셈입니다. 이 덕분에 프로그래머는 메모리 관리를 전혀 고려하지 않아도 많은 것을 구현할 수 있습니다. 

## 클로저
자바스크립트의 모든 함수는 클로저를 정의합니다. 클로저는 자바스크립트가 가진 가장 강력한 기능으로, 이를 활용하면 변수를 은닉하여 지속성을 보장하는 등의 다양한 기능을 구현할 수 있습니다.

### 클로저
클로저(closure, 함수 폐포)를 프로그래밍 언어적인 관점에서 설명하면 다음과 같은 동작을 하는 함수와 그 기능을 구현한 자료 구조의 모음이라고 할 수 있습니다.<br><br>
**자기 자신이 정의된 환경에서 함수 안에 있는 자유 변수의 식별자 결정을 실행한다.**
<br><br>

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image7.png)

이 코드에서 중첩 함수 g가 정의된 렉시컬 환경은 함수 g를 둘러싼 바깥 영역 전체입니다(연하늘 색으로 칠해진 부분). **이 렉시컬 환경에서 함수 g의 자유 변수 a와 b의 식별자 결정을 합니다.** 이것은 **변수의 식별자 결정**에서 설명한 다음의 메커니즘으로 구현되어 있습니다.
- 함수 f를 호출할 때 함수 f의 렉시컬 환경 컴포넌트가 생성된다.
- 그 후에 함수 g의 함수 선언문을 평가해서 함수 객체를 생성한다. 이 함수 객체의 렉시컬 환경 컴포넌트에는 함수 g의 코드, 함수 f의 렉시컬 컴포넌트 참조(이 안에 변수 b가 들어 있음). 전역 객체의 참조(이 안에 변수 a가 들어 있음)가 저장된다.
- 함수 g를 호출해서 실행하면 그 시점에 함수 g의 렉시컬 환경 컴포넌트를 생성한다. 이와 동시에 함수g의 렉시컬 환경 컴포넌트를 생성한다. 이와 동시에 함수 g의 실행 문맥의 외부 렉시컬 환경 참조(__scope__)를 마치 체인처럼 거슬러 올라가서 자유 변수 a와 b 값을 참조한다.
<br><br>
즉, 함수 g의 함수 객체와 객체가 참조하는 렉시컬 환경 컴포넌트가 자유 변수 a,b의 식별자 결정을 위한 자료 구조라고 할 수 있습니다. 이 자료 구조는 함수 f가 호출되어 함수 g가 평가되는 시점에 생성됩니다. 따라서 자바스크립트의 클로저는 함수 객체와 렉시컬 환경 컴포넌트의 집합이라고 할 수 있습니다.
<br><br>
**클로저 = 함수 객체 + 렉시컬 환경 컴포넌트**<br>

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image8.png)

이 예에서 함수 g의 함수 객체가 있는 동안에는 클로저 안의 모든 렉시컬 환경 컴포넌트를 함수 g의 함수 객체가 참조하므로 클로저는 가비지 컬렉션 대상이 되지 않습니다. 따라서 함수 g의 함수 객체가 있는 한 클로저는 메모리에서 지워지지 않습니다.

>클로저의 어원 : 위의 예에 등장한 함수 g는 열린 함수입니다. 그러나 유효 범위 체인으로 주변 환경의 변수 b와 a를 들여와서 실실적으로는 폐쇄 함수가 되었습니다. 이러한 **열려 있던 것을 닫는다**는 개념이 클로저(Closure)의 어원입니다.

### 클로저의 성질
카운터 함수를 만드는 함수 
```javascript
function makeCounter() {
	var count = 0;
	return f;
	function f() {
		return count++;
	}
}
var counter = makeCounter();
console.log(counter()); // -> 0
console.log(counter()); // -> 1
console.log(counter()); // -> 2
```
- (1) 외부 함수 makeCounter는 중첩 함수 f의 참조를 반환한다.
- (2) 중첩 함수 f는 외부 함수 makeCounter의 지역변수 counter를 참조한다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image10.png)

(1)로 인해 f의 함수 객체를 전역변수 counter가 참조합니다. (2)로 인해 함수 makeCounter의 렉시컬 환경 컴포넌트를 f의 함수 객체가 참조합니다. 그 결과 함수 makeCounter의 렉시컬 환경 컴포넌트를 전역 변수 counter가 f의 함수 객체로 간접적으로 참조하게 되므로 가비지 컬렉션의 대상이 되지 않습니다. 따라서 makeCounter 실행이 끝나서 호출자에 제어권이 넘어가도 makeCounter의 렉시컬 환경 컴포넌트가 메모리에서 지워지지 않게 됩니다. 지역 변수 count는 함수 makeCounter가 속한 렉시컬 환경 컴포넌트에 있는 선언적 환경 레코드의 프로퍼티이므로 이 또한 메모리에서 지워지지 않습니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image9.png)

앞의 예에서 변수 count는 클로저 내부 상태로서 저장됩니다. 또한 count는 지역 변수이기 때문에 함수 바깥에서 읽거나 쓸 수 없습니다. 또한 함수 f가 클로저의 내부 상태를 바꾸는 메서드의 역할을 하고 있습니다. 이렇게 놓고 보니 클로저가 마치 객체와 같다는 생각이 듭니다.<br>
앞의 예에서는 지역 변수 count가 객체의 프로퍼티에 해당하고 함수 f가 객체의 메서드에 해당합니다. 또한 객체의 프로퍼티를 외부에서 읽고 쓸수 있지만 클로저 내부 상태는 외부로부터 숨겨진 상태입니다.<br>
객체 지향 프로그래밍에서는 객체의 프로퍼티를 외부로부터 은폐하는 행위를 가리켜 캡슐화라고 합니다. 즉, '**클로저는 캡술화된 객체**'라고 할 수 있습니다.<br><br>
makeCounter()를 실행해서 함수 두 개를 생성해 보면 모두 별개의 카운터가 됩니다.
```javascript
var counter1 = makeCounter();
var counter2 = makeCounter();
console.log(counter1()); // -> 0
console.log(counter2()); // -> 0
console.log(counter1()); // -> 1
console.log(counter2()); // -> 1
```
그 이유는 <b>makeCounter()를 호출할 때 마다 makeCounter()의 렉시컬 환경 컴포넌트가 새로 생성되기 때문</b>입니다. 따라서 <b>각 클로저는 서로 다른 내부 상태를 저장</b>합니다. <b>클로저를 객체로 간주하면 makeCounter()는 클로저라는 객체를 생성하는 팩토리 함수로 간주</b>할 수 있습니다.<br><br>

예제에서는 설명을 위해 중첩 함수에 f라는 이름을 붙였지만, 보통은 이름이 없는 익명 함수를 반환하는 방법을 자주 사용합니다.
```
function makeCounter() {
	var count = 0;
	return function() {
		return count++;
	};
}

var counter = makeCounter();

console.log(counter()); // -> 0
console.log(counter()); // -> 1
console.log(counter()); // -> 2
```

#### 클로저를 이해하기 위한 핵심 사항
- 외부 함수를 호출하면 그 함수의 렉시컬 환경 컴포넌트가 생성됩니다. 그리고 그 안에 중첩된 중첩 함수의 함수 객체를 생성해서 반환합니다. 그 결과 외부 함수의 렉시컬 환경 컴포넌트를 참조하는 중첩 함수가 정의한 클로저가 생성됩니다. 즉, 외부 함수는 클로저를 생성하는 팩토리 함수라고 할 수 있습니다.
- 외부 함수가 속한 렉시컬 환경 컴포넌트는 클로저 내부 상태 자체입니다. 외부 함수가 호출될 때마다 새로 생성됩니다.
- 중첩 함수의 함수 객체가 있는 한 외부 함수가 속한 렉시컬 환경 컴포넌트는 지워지지 않습니다. 외부 함수의 함수 객체가 사라져도 지워지지 않습니다.
- 클로저 내부 상태(외부 함수의 지역 변수, 선언적 환경 레코드)는 외부로부터 은폐되어 있으며 중첩 함수 안에서만 읽거나 쓸 수 있습니다.

### 클로저를 응용한 예제
클로저를 사용하면 데이터와 데이터를 조작하는 함수를 하나로 묶을 수 있습니다. 객체 지향 프로그래밍에서 프로퍼티를 조작하는 메서드와 역할이 비슷합니다.

#### 여러 개의 내부 상태와 메서드를 가진 클로저
```javascript
function Person(name, age) {
	var _name = name;
	var _age = age;
	return {
		getName : function() { return _name; },
		getAge : function() { return _age; },
		setAge : function(x) { _age = x; }
	}
}
var person = Person("Tom", 10);
console.log(person.getName()); // -> Tom
console.log(person.getAge());  // -> 18
person.setAge(19);
console.log(person.getAge()); // -> 19
```

#### 함수 팩토리
클로저를 활용하여 다양한 매개변수를 받는 함수를 여러 개 생성할 수 있습니다.
```javascript
function makeMultiplier(x) {
	return function(y) {
		return x*y;
	};
}
var multi2 = makeMultiplier(2);
var multi10 = makeMultiplier(10);
console.log(multi2(3)); // -> 6
console.log(multi10(3)); // -> 30
```

## 이름 공간
### 전역 이름 공간의 오염
전역 변수와 전역 함수를 전역 객체에서 선언하는 행위를 가리켜 **전역 유효 범위를 오염시킨다**고 합니다. 전역 유효 범위가 오염되면 다음과 같은 상황일 때 변수 이름과 함수 이름이 겹칠 수 있습니다.
- 라리브러리 파일을 여러 개 읽어 들여 사용할 때
- 규모가 큰 프로그램을 만들 때
- 여러 사람이 한 프로그램을 만들 때
전역 유효 범위 안에서 이름이 같은 변수나 함수를 선언하면 자바스크립트 엔진이 그 프로그램의 첫 머리로 끌어올려서 변수 또는 함수를 단 하나만 생성합니다. 그러면 다른 목적으로 사용하는 코드가 같은 변수와 함수를 공유하게 되므로 프로그램이 올바르게 동작하지 않을 수 있습니다. 게다가 프고르램 오류로 표시되지 않으므로 찾아내기도 어렵습니다.<br><br>
이러한 예기치 않은 오류를 피하려면 전역 유효 범위의 오염을 최소화해야 합니다. 전역 변수의 오염을 방지하기 위한 몇 가지 기법을 소개 합니다.

### 객체를 이름 공간으로 활용하기
<b>이름 공간(name space)</b>이란 변수 이름과 함수 이름을 한곳에 모아 두어 이름 충돌을 미리 방지하고, 변수와 함수를 쉽게 가져다 쓸 수 있게 만든 매커니즘 입니다. Java와 C++ 등의 언어에서는 기본적으로 이름 공간 기능을 제공합니다. **자바스크립트에서는 기본적으로 이름 공간 기능을 제공하지 않지만 객체를 이름 공간으로 활용할 수 있습니다.**<br><br>
객체를 이름 공간으로 활용하려면 모든 변수와 함수를 프로퍼티로 정의합니다. 예를 들어 다음과 같은 방법으로 myApp이라는 전역 변수를 이름 공간으로 활용할 수 있습니다.

```javascript
var myApp = myApp || {};
```
이렇게 작성해 두면 myApp이 이미 정의되어 있을 때는 그것을 사용하고 그렇지 않다면 빈 객체를 myApp에 할당합니다.

```javascript
myApp.name = "Tom";
myApp.showName = function() { ... }
```

이렇게 되면 myApp만이 사용자가 정의한 전역 변수가 되므로 전역 유효 범위의 오염을 최소화할 수 있습니다.<br><br>

또한 <b>부분 이름 공간(sub name space)</b>을 만들 수도 있습니다.

```javascript
myApp.view = {};
myApp.controls = {};
```

각자의 부분 이름 공간 안에 필요한 변수 또는 함수를 프로퍼티에 정의해서 사용합니다.
```javascript
myApp.view.draw = function() { ... };
myApp.controls.timeInterval = 16;
```
객체를 이름 공간으로 이용하면 변수 또는 함수 이름을 계층적으로 관리할 수 있습니다.

### 함수를 이름 공간으로 활용하기
함수 안에서 선언된 변수의 유효 범위는 함수 내부입니다. 따라서 그 변수를 함수 안에서는 읽기나 쓸 수 있지만 바깥에서는 읽거나 쓸 수 없습니다. 이 성질을 활용하면 함수를 이름 공간으로 활용할 수 있습니다.

```javascript
var x = "global x";
(function() {
	var x = "local x";
	var y = "local y";
})();
console.log(x); // -> global x
console.log(y); // -> Uncaught ReferenceError: y is not defined
```
<b>즉시 실행 함수(Immedicately-Invoked Function Expression)</b> 내부에서 선언한 변수인 x와 y는 이 함수의 지역변수이므로 전역 변수와 이름이 충돌하지 않습니다.

#### 모듈 패턴 
즉시 실행 함수를 사용하여 모듈로 정의하는 방법을 소개합니다. 모듈운 기능(함수) 여러 개를 하나로 묶은 것입니다. 일반적으로 모듈은 함수 여러 개와 함수가 공유하는 데이터로 구성됩니다.

```javascript
var Module = Module || {};
(function(_Module) {
	var name = "NoName";   // 프라이빗 변수
	function getName() {  // 프라이빗 함수
		return name;
	}
	_Module.showName = function() { // 퍼블릭 함수 
		console.log(getName());
	};
	_Module.setName = function(x) { // 퍼블릭 함수
		name = x;
	};
})(Module);
Module.setName("Tom");
Module.showName(); // -> Tom
```

## 객체로서 함수
자바스크립트에서는 함수도 일종의 객체입니다. 따라서 함수는 값을 처리할 수 있으며 프로퍼티와 메서드도 가지고 있습니다.

### 함수는 객체
자바스크립트의 함수는 Function 객체입니다. 따라서 다른 객체와 마찬가지로 다음과 같은 특징이 있습니다.
- 함수는 변수나 프로퍼티나 배열 요소에 대입할 수 있다.
- 함수는 함수의 인수로 사용할 수 있다.
- 함수는 함수의 반환값으로 사용할 수 있다.
- 함수는 프로퍼티와 메서드를 가질 수 있다.
- 함수는 이름 없는 리터럴로 표현할 수 있다(익명 함수).
- 함수는 동적으로 생성할 수 있다.

일반적으로 이러한 작업이 가능한 객체를 가리켜 **일급 객체**라고 합니다. 일급 객체인 함수는 **일급 함수**라고 합니다.  따라서 자바스크립트의 함수는 일급 함수지만 C나 Java와 같은 프로그래밍 언어의 함수는 일급 함수가 아닙니다.<br><br>
자바스크립트는 함수가 일급 객체이므로 함수형 언어처럼 함수형 프로그래밍을 할 수 있습니다.

### 함수의 프로퍼티

|프로퍼티 이름|설명|
|-----|--------|
|caller|현재 실행 중인 함수를 호출한 함수|
|length|함수의 인자 개수|
|name|함수를 표시할 때 사용하는 이름|
|prototype|프로토타입 객체의 참조|

또한 함수는 Function 생성자의 prototype 객체(Function.prototype)의 프로퍼티를 상속받아 사용합니다.

|프로퍼티 이름|설명|
|-----|--------|
|apply()|선택한 this의 인수를 사용하여 함수를 호출한다. 인수는 배열 객체다.|
|bind()|선택한 this의 인수를 적용한 새로운 함수를 반환한다.|
|call()|선택한 this와 인수를 사용하여 함수를 호출한다. 인수는 쉼표로 구분한 값이다.|
|constructor|Function 생성자의 참조|
|toString()|함수의 소스 코드를 문자열로 만들어 반환한다.|

### apply와 call 메서드
- Function 객체의 메서드에는 apply와 call이 있습니다. 
- this 값과 함수의 인수를 사용하여 함수를 실행하는 메서드입니다. 
- apply와 call의 동작은 본질적으로 같습니다. 차이점은 함수에 인수를 넘기는 방법뿐입니다. 
- apply의 인수는 배열이고 call의 인수는 쉼표로 구분한 값의 목록입니다.

```javascript
function say(greeting, honorifics) {
	console.log(greetings + " " + honorifics + this.name);
}
var tom = { name : "Tom Sawyer"};
var becky = { name: "Becky Thatcher" };

say.apply(tom, ["Hello", "Mr."]); // -> "Hello! Mr. Tom Sawyer"
say.apply(becky, ["Hi!", "Ms."]); // -> "Hi! Ms. Becky Thatcher"

say.call(tom, "Hello", "Mr."); // -> "Hello! Mr. Tom Sawyer"
say.call(becky, "Hi!", "Ms."); // -> "Hi! Ms. Becky Thatcher"
```

### bind 메서드
Function 객체의 bind 메서드는 객체에 함수를 바인드합니다. 
```javascript
function say(greetings, honorifics) {
	console.log(greetings + " " + honorifics + this.name);
}

var tom = { name : "Tom Sawyer"};
var sayToTom = say.bind(tom); 
sayToTom("Hello", "Mr."); // -> "Hello! Mr. Tom Sawyer"
```
이 코드에서 sayToTom 함수를 호출하면 항상 this가 객체 tom을 가리킵니다. 이처럼 say.bind(tom)은 tom 객체를 함수 say의 this로 설정한 새로운 함수를 만들어서 반환합니다.

### 함수에 프로퍼티 추가하기
다른 객체와 마찬가지로 함수에도 프로퍼티를 추가할 수 있습니다.
```javascript
function f(x) { ... }
f.p = a;
f.g = function() { ... };
```
Function 객체에 추가된 프로퍼티는 그 함수를 실행하지 않아도 읽거나 쓸 수 있습니다. 함수의 프로퍼티에는 일반적으로 그 함수의 작업과 관련된 데이터와 메서드를 저장합니다. 물론 이들을 전역 변수에 저장해도 같은 작업을 할 수 있습니다. 하지만 전역 변수를 사용하면 전역 유효 범위를 오염시켜 버리므로 변수 이름의 충돌을 늘 경계해야 합니다. 그러나 이를 함수의 프로퍼티로 작성하면 함수 객체가 이름 공간의 역할을 하기 때문에 문제가 발생하지 않습니다.

```javascript
function fibonacci(n) {
	if (n<2) return n;
	if (!(n in fibonacci)) {
		fibonacci[n] = fibonacci(n-1) + fibonacci(n-2);
	}
	return fibonacci[n];
}

for (var i = 0; i <= 20; i++) {
	console.log((" " + i).slice(-2) + ":" + finbonacci(i));
}
```

## 고차 함수
- 고차 함수란 함수를 인수로 받는 함수 또는 함수를 반환하는 함수를 말합니다.
- 자바스크립트의 함수는 일급 객체이고 함수의 인수로 함수를 넘길 수 있으며 함수를 반환할 수 있으므로 고차 함수를 쉽게 정의할 수 있습니다. 
- 고차 함수를 사용하면 처리 패턴이 같은 작업을 추상화하여 하나로 합칠 수 있습니다.
- 고차 함수는 함수형 프로그래밍을 할 때 자주 사용합니다.

### 간단한 예
```javascript
digits = "";
for (var i = 0; i < 10; i++) {
	digits += i;
}

console.log(digits);

randomChars = ""';
for(var i = 0; i < 8; i++) {
	randomChars += String.fromCharCode(Math.random() + 26) + "a".charCodeAt(0));
}
console.log(randomChars); 
```
위 코드의 공통 부분을 고차함수로 만들어서 하나로 만들어 봅니다.
```javascript
function joinString(n, f) {
	var s = "";
	for(var i = 0; i<n; i++) {
		s += f(i);
	}
	return s;
}

var digits = joinStrings(10, function(i) { return i; });
var randomChars = joinString(8, function(i) {
	return String.fromCharCode(Math.floor(Math.random()*26) + "a".charCodeAt(0));
});
console.log(digits); 
console.log(randomChars);
```

### 메모이제이션
```javascript
function memorize(f) {
	var cache = {};
	return function(x) {
		if (cache[x] == undefined) cache[x] = f(x);
		return cache[x];
	};
}

var fibonacci = memorize(function(n) {
	if (n<2) return n;
	return fibonacci(n-1) + fibonacci(n-2);
});

for(var i = 0; i <= 20; i++) {
	console.log(("  "+ i).slice(-2) + ":" + fibonacci(i));
}
```

## 콜백함수
자바스크립트의 함수는 일급 객체이며 다른 함수에 인수로 넘겨질 수 있습니다. 다음과 같이 다른 함수에 인수로 넘겨지는 함수를 가리켜 콜백 함수라고 부릅니다.
```javascript
f(g, ...);
...
function f(callback, ...) {
	... 
	callback();
	...
}
```
이 코드에서 함수 f의 인수로 넘겨진 함수인 g가 콜백 함수 입니다. 이렇게 작성하면 호출한 함수 f안에서 특정 콜백 함수를 실행시켜서 그 콜백 함수에 제어권을 부여할 수 있습니다.

### 이벤트 처리기
이벤트 처리기는 특정 이벤트가 발생했을때 실행하도록 등록하는 함수입니다.
```javascript
button.onclick = function() { ... }
```
이는 함수를 호출할 때 무언가 사건이 발생하면 콜백 함수를 실행하도록 인수로 넘기는 행위와 닯아 있습니다. 
```javascript
button.addEventListener("click", function() { ... }, false);
```

### 타이머
```javascript
setInterval(function() { ... }, 2000);
```

## ECMAScript6+에 추가된 기능

### 화살표 함수 표현식으로 함수 정의하기
ECMAScript6 부터 추가된 화살표 함수 표현식은 함수 리터럴(익명 함수)의 단축 표현입니다.

#### 화살표 함수 표현식의 작성법

다음 문장은 기존의 함수 리터럴로 함수를 정의한 것입니다.
```javascript
var square = function(x) { return x*x;  };
```

이를 화살표 함수 표션식으로 작성하면 다음과 같습니다.
```javascript
var square = (x) => { return x*x; };
```

인수가 여러 개 있으면 인수를 쉼표로 구분합니다.
```javascript
var f = (x, y, z) => { ... };
```

인수가 하나만 있으면 인수를 묶는 괄호를 생략할 수 있습니다.
```javascript
var square = x => { return x*x; };
```

인수가 없으면 인수를 묶는 괄호를 생략할 수 없습니다.
```javascript
var f = () => { ... };
```

함수 몸통의 문장이 return뿐이면 중괄호와 return 키워드를 생략할 수 있습니다.
```javascript
var square = x => x*x;
```

화살표 함수도 즉시 실행 함수로 사용할 수 있습니다.
```javascript
(x => x*x)(3); // -> 9
```

#### 함수 리터럴과 화살표 함수의 차이점
- **this의 값이 함수를 정의할 때 결정된다**<br>
함수 리터럴로 정의된 this 값은 함수를 호출할 대 결정됩니다. 그러나 **화살표 함수의 this 값은 함수를 정의할 때 결정됩니다. 즉, 화살표 함수 바깥의 this값이 화살표 함수의 this 값이 됩니다.**
```javascript
var obj = {
	say : function() {
		console.log(this);  // -> [object Object]
		var f = function() { console.log(this); };  // -> [object Window]
		f();
		var g = () => console.log(this);  // -> [object Object]
		g();
	}
};
obj.say();
```
이 예제의 함수 f는 say라는 함수의 중첩 함수이며 this의 값은 전역 객체를 가리킵니다.  한편 화살표 함수 g의 this 값은 함수 g를 정의한 익명함수의 this의 값인 객체 obj를 가리킵니다.<br>
화살표 함수는 call이나 apply 메서드를 사용하여 this를 바꾸어 호출해도 this 값이 바뀌지 않습니다.
```javascript
var f = function() { console.log(this.name); };
var g = console.log(this.name);
var tom = { name : "Tom" };
f.call(tom); // -> "Tom"
g.call(tom); // -> ""
```

- **arguments 변수가 없다**<br>
화살표 함수 안에는 arguments 변수가 정의되어 있지 않으므로 사용할 수 없습니다.
```javascript
var f = () -> console.log(arguments);
f();  // -> ReferenceError : arguments is not defined
```

- **생성자로 사용할 수 없다**<br>
화살표 함수 앞에 new 연산자를 붙여서 호출할 수 없습니다.
```javascript
var Person = (name, age) => { this.name = name; this.age = age; };
var tom = new Person("Tom", 17); // -> TypeError: Person is not a constructor 
```

- **yield 키워드를 사용할 수 없다**<br>
화살표 함수 안에서는 yield 키워드를 사용할 수 없습니다. 따라서 화살표 함수는 제너레이터로 사용할 수 없습니다.

### 인수에 추가된 기능 
#### 나머지 매개변수
ECMAScript 6부터는 함수의 인자가 들어가는 부분에 ...을 입력하면 그 만큼 인수를 배열로 받을 수 있습니다. 이렇게 ...으로 표현한 인자를 나머지 매개변수(rest parameter)라고 부릅니다.
```javascript
function f(a, b, ...args) {
	console.log(a, b, args);
}

f(1, 2, 3, 4, 5, 6);  // -> 1,2, [3, 4, 5, 6]
```
함수에 가변 인수를 이용하려면 ECMAScript 5까지는 arguments 변수를 사용해야 했습니다. 하지만 arguments는 배열이 아닌 유사 배열 객체이므로 forEach 등의 배열 메서드로 조작하려면 배열로 변환해야 해서 번거로웠습니다. 나머지 매개변수는 인수를 배열로 받기 때문에 덜 번거롭습니다.<br><br>
화살표 함수 안에서는 arguments를 사용할 수 없지만 나머지 매개변수를 사용하면 화살표 함수안에서도 가변 인수를 이용할 수 있습니다.
```javascript
var sum = (...args) => {
	for(var i = 0, s = 0; i < args.length; i++) s+=args[i];
	return s;
};
sum(1, 2, 3, 4, 5); // -> 15
```

#### 인수의 기본값
ECMAScript 6부터는 함수의 인자에 대입(=) 연산자를 사용해서 기본값을 설정할 수 있습니다. 기본값을 설정한 인자에 호응하는 인수를 생략하거나 undefined를 넘기면 대입 연산자의 우변의 값이 기본값이 됩니다.<br>
```javascript
function multiply(a, b = 1) {
	return a*b;
}
multiply(3); // -> 3
multiply(3, 2); // -> 6
```

다른 인자의 값도 기본값으로 사용할 수 있습니다.
```javascript
function add(a, b=a+1) { return a+b; }
add(2);  // -> 5
add(2, 1);  // -> 3
```

### 이터레이터와  for/of 문
ECMAScript 6부터 추가된 이터레이터와 그와 관련된 제어 구문인 for/of 문을 배웁니다. 이터레이터와 나중에 언급하는 제너레이터는 ECMAScript 6부터 추가된 기능 중 가장 강력한 기능일 뿐만 아니라 ECMAScript 6의 새로운 기능을 이해하는 데 핵심이 되는 요소입니다.

#### 이터레이션
이터레이션(iteration)은 반복 처리라는 뜻으로 데이터 안의 요소를 연속적으로 꺼내는 행위를 말합니다. 예를 들어 배열의 forEach 메서드는 배열의 요소를 순차적으로 검색하여 그 값을 함수의 인수로 넘기기를 반복합니다.
```javascript
var a = [5, 4, 3];
a.forEach(function(val) { console.log(val); });
```

#### 이터레이터
이터레이터(iterator)란 <b>반복 처리(iteration)이 가능한 객체</b>를 말합니다. 앞의 forEach메서드는 배열의 요소를 꺼내 그 값을 함수의 인수로 넘기고, 그 작업이 끝나면 배열의 다음 요소를 꺼내 함수의 인수로 넘기기를 반복합니다. 이 작업은 내부적으로 처리되므로 개발자는 각 처리 단계를 제어할 수 없습니다. 그러나 ECMAScript 6부터는 추가된 이터레이터를 사용하면 개발자가 반복 처리를 단계별로 제어할 수 있습니다.<br><br>
ECMAScript6의 이터레이터가 무엇인지 설명하기 앞서 이터레이터의 예를 보겠습니다. 다음 예는 배열의 이터레이터입니다. 배열은 Symbol.iterator 메서드를 가지고 있습니다. Symbol.iterator는 자바스크립트에 내장되어 있는 특별한 의미를 가진 심벌(내장 심벌)입니다. @@iterator라고 표기하고 이터레이터 심벌이라고 읽습니다. 배열의 **Symbol.iterator 메서드**는 이터레이터를 반환하는 함수입니다.
```javascript
var a = [5, 4, 3];
var iter = a[Symbol.iterator]();
console.log(iter.next()); // -> {value: 5, done: false}
console.log(iter.next()); // -> {value: 4, done: false}
console.log(iter.next()); // -> {value: 3, done: false}
console.log(iter.next()); // -> {value: undefined, done: true}
console.log(iter.next()); // -> {value: undefined, done: true}
```
이처럼 iter의 next 메서드를 호출할 때마다 <b>이터레이터 리절트(iterator result)</b>라는 객체가 반환됩니다. 이터레이터 리절트 value와 done 프로퍼티를 갖는 객체입니다. next 메서드가 호출될 때마다 value 프로퍼티에는 차례대로 꺼내진 배열 요소의 값이 저장되고 done 프로퍼티에는 요소의 열거가 끝났는지를 뜻하는 논리값이 저장됩니다.<br><br>

ECMAScript6의 이터레이터는 일반적으로 다음 두 가지 항목을 만족하는 객체입니다.
- next 메서드를 가진다.
- next 메서드의 반환값은 value 프로퍼티와 done 프로퍼티를 가진 객체이다. 이때 value에는 꺼낸 값이 저장되고 done에는 반복이 끝났는지를 뜻하는 논리값이 저장된다.

#### 반복 가능한 객체와 for/of 문
이터레이터를 사용해서 이터레이션(반복)을 하려면 개발자가 적절한 처리를 직접 작성해야 합니다.
```javascript
var a = [5, 4, 3];
var iter = a[Symbol.iterator]();
while(true) {
	var iteratorResult = iter.next();
	if (iteratorResult.done == true) break;
	var v = iteratorResult.value;
	console.log(v);
}
```

for/of 문을 사용하면 이러한 반복 처리를 자동으로 하도록 만들 수 있습니다. 
```javascript
var a = [5, 4, 3];
for(var v of a) console.log(v);
```

일반적으로 for/of 문은 다음 두 가지 조건을 만족하는 객체를 반복 처리합니다.
- Symbol.iterator 메서드를 가지고 있다.
- Symbol.iterator 메서드는 반환값으로 이터레이터를 반환한다.

Symbol.iterator 메서드를 가진 객체를 <b>반복가능(이터러블, iterable)</b>한 객체라고 합니다. 다음 생성자로 생성한 내장 객체는 처음부터 Symbol.iterator 메서드를 내장하고 있습니다. 즉, 반복 가능(이터러블)합니다.
```
Array, String, TypedArray, Map, Set
```
```javascript
for(var v of "ABC") console.log(v);  // "A", "B", "C"를 순서대로 표시한다.
```
반복 가능한 객체는 <b>for/of문, 전개 연산자, yield\*, 비구조화 할당</b> 등에 활용할 수 있습니다.


### 제너레이터
제너레이터는 다음과 같은 성질을 지닌 함수입니다.
- 반복 가능한 이터레이터를 값으로 반환한다.
- 작업의 일시 정지와 재시작이 가능하며 자신의 상태를 관리한다.

제너레이터는 이터레이터의 반복 처리를 강력하게 지원합니다. 제너레이터를 활용하면 반복 알고리즘을 독자적으로 구현한 이터레이터보다 유연하게 표현할 수 있습니다.

#### 제너레이터의 정의와 실행
제너레이터는 function\* 문으로 정의한 함수이며, 하나 이상의 yield 표현식을 포함합니다. 
```javascript
function* gen() {
	yield 1;  // 포인트 1
	yield 2; // 포인트 2
	yield 3; // 포인트 3
}

var iter = gen();
console.log(iter.next());  // -> {value: 1, done: false}
console.log(iter.next());  // -> {value: 2, done: false}
console.log(iter.next());  // -> {value: 3, done: false}
console.log(iter.next());  // -> {value: undefined, done: true}
```
- 제너레이터 함수인 gen은 호출해도 바로 실행되지 않습니다. 그 대신 이터레이터를 반환합니다. 앞의 예에서는 이터레이터 변수 iter에 대입합니다.
- 이터레이터의 next 메서드가 호출되면 함수의 첫 번째 yield 연산자의 위치(**포인트 1**)까지 실행하며 결과값으로 이터레이터 리절트를 반환합니다. 이터레이터 리절트의 value 프로퍼티 값으로 yield 표현식에 지정한 값을 저장하고, done 프로퍼티 값으로 제너레이터 함수를 끝까지 실행했는지 저장합니다. **이때 제너레이터 함수의 내부 처리는 포인트 1에서 일시 정지 상태**가 됩니다.
- 또 다시 이터레이터의 **next 메서드가 호출**되면 **일시 정지한 위치에 있는 처리를 재개**합니다. 그리고 제너레이터 함수 안의 다음 번 yield 연산자의 위치(**포인트 2**)까지 실행합니다. 마찬가지로 value 프로퍼티와 done 프로퍼티를 가진 **이터레이터 리절트를 반환하고 처리를 일시 정지**합니다.
- 이터레이터의 **next 메서드가 호출된 후 멈추었던 처리를 재개**합니다.  그리고 제너레이터 함수 안에 있는 다음 yield 연산자의 위치까지 실행합니다. 마찬가지로 value 프로퍼티의 done 프로퍼티를 가진 이터레이터 **리절트를 반환하고 처리를 일시 정지**합니다.
- 이터레이터의 next 메서드가 호출되어 함수 처리가 **마지막 yield에 도착**했습니다. **value 프로퍼티 값이 undefined고 done 프로퍼티 값이 true인 이터레이터 리절트를 반환**합니다.
<br><br>
즉, 제너레이터 함수의 **yield**는 프로그램이 **일시적으로 정지하는 위치** 입니다. 그리고 제너레이터로 생성한 이터레이터의 **next 메서드**는 제너레이터 함수의 상태를 **일시 정지 상태에서 실행상태로 바꾸는 역할**을 합니다. 이때 이터레이터 객체는 처리를 재개할 수 있도록 제너레이터 함수의 내부 상태를 모두 저장합니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/9~10%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%95%A8%EC%88%98/images/image11.png)


```javascript
function createNumbers(from, to) {
	while( from <= to) yield from++;
}

var iter = createNumbers(10, 20);
for(var v of iter) console.log(v);  // 10~20 사이의 정수를 순서대로 출력한다.
```

### 템플릿 리터럴의 태그 함수

#### 태그가 지정된 템플릿 리터럴
템플릿 리터럴 앞에 함수 이름을 적으면 템플릿 리터럴의 내용을 인수로 받는 함수를 호출할 수 있습니다.
```javascript
func`${a} + ${b} = ${a+b}`
```

이 코드에서 func 부분을 <b>태그 함수(tag function)</b>라고 합니다. 태그 함수의 첫 번째 인수는 문자열을 요소로 담은 배열입니다. 이 배열의 요소는 템플릿 리터럴 안의 문자열을 ${...}를 기준으로 분할한 문자열입니다. 두 번째 이후 인수로는 각 ${...} 안에 지정된 표현식을 평가한 값이 순서대로 들어갑니다. 태그 함수의 반환값이 반드시 문자열일 필요는 없으며 그 어떤 값도 반환할 수 있습니다.
```javascript
function list() { return arguments; }
var t = list`a${1}b${2}c${3}`;
console.log(t);  // Arguments(4) [Array(4), 1, 2, 3, callee: ƒ, Symbol(Symbol.iterator): ƒ] 
```


```javascript
function htmlEscape(strings, ...values) {
	var result = strings[0];
	for(var i=0; i<values.length; i++) {
		result += escape(values[i]) + strings[i+1];
	}
	return result;
	function escape(s) {
		return s.replace(/&/g, "&amp;")
				.replace(/</g, "&lt;")
				.replace(/>/g, "&gt")
				.replace(/'/g, "&#039;")
				.replace(/"/g, "&quot;")
				.replace(/`/g, "&#096;");
	}
}
var userinput = "<script>alert('test');</script>";
var message = htmlEscape`<p>${userinput}</p>`;
console.log(message); 
// <p>&lt;script&gtalert(&#039;test&#039;);&lt;/script&gt</p>
```

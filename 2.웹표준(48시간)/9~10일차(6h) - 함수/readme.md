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
실행 문맥의 구성 요소인 렉시컬 환경(LexicalEnvironment) 컴포넌트는 자바스크립트 엔진이 자바스크립트 코드를 실행하기 위한 자원을 모아 둔 곳으로 구체적으로는 함수 또는 블록의 유효 범위안에 있는 식별자와 그 결과값이 저장되는 곳입니다.<br>자바스크립트 엔진은 해당 자바스크립트 코드의 유효범위 안에 있는 식별자와 그 식별자가 가리키는 값을 키와 값의 쌍으로 바인드해서 렉시컬 환경 컴포넌트에 기록합니다.<br><br>
렉시컬 환경 컴포넌트는 <b>환경 레코드(Environment Record)</b>와 <b>외부 렉시컬 환경 참조(Outer Lexical Environment Reference) 컴포넌트</b>로 구성되어 있습니다. 다음 코드는 자바스크립트의 객체 표현을 빌린 의사코드로 렉시컬 환경 컴포넌트를 표현한 것입니다. 자바스크립트의 객체 표현을 빌린 의사 코드라 실행할 수 없습ㄴ디ㅏ.

```javascript
// 렉시컬 환경 컴포넌트
LexicalEnvironment: {
	// 환경 레코드
	EnvironmentRecord: {},
	// 외부 렉시컬 환경 참조
	OuterLexicalEnvironment Reference: {}
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
- 전역 실행 문맥의 디스 바인딩 컴포넌트에도 Window의 참조가 할당되어 전역 실행 문맥의 this가 Window를 가리키게 되고, 전역 실행 문맥의 프로퍼티를 디스 바인딩 컴포넌트 안에서 컴색하게 됩니다.
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


### 자바스크립트는 싱글 스레드


## 클로저

## 객체써의 함수

## 콜백함수

## ECMAScript6+에 추가된 기능
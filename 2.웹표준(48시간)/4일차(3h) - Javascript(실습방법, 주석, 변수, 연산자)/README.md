# 실습방법 안내 
- 자바스크립트 실습 환경 
	- 웹브라우저 콘솔 
		- 별도 파일을 생성하지 않는 간단한 실습시 사용
		- 웹브라우저도 일종의 자바스크립트 런타임이므로 자바스크립드를 실행할 수 있습니다.     
		- 실습의 통일성을 위해 “크롬” 웹브라우저를 사용합니다.
		- 크롬 -> F12 -> Console 탭 -> 스크립트 코드 테스트
	- node.js
		- node.js도 일종의 자바스크립트 런타임입니다.  node.js는 크롬에 탑재된 Chrome V8 Javascript 엔진을 사용합니다. 
		- 자바스크립트 런타임이란 단순히 탑재된 자바크립트 엔진을 사용하여 실행하는 환경을 제공해 주는 프로그램이라고 생각할 수 있습니다.
		- node.js에서도 자바스크립트를 실행할 수 있습니다. 
		<br>
		- 방법1. 콘솔(REPL(Read Eval Print Loop))에서 실행하는 방법
		- 방법2. js 파일로 실행하는 방법
			- node 파일명.js 
			
		- [nodejs 다운로드](https://nodejs.org/ko/)
		
	- HTML문서 파일로 작성하여 브라우저에서 확인
			
# 자바스크립트 기초 문법 

## 자바스크립트 사용하는 방법
- <script></script> 태그 안쪽에 소스코드를 넣어서 사용하는 방법
```javascript
<script>
  alert("안녕하세요.");
</script>
```
- <script> 태그에 src 속성에 외부 javascript를 불러와서 사용하는 방법
```javascript
<script src="js/script.js"></script>
```
- HTML 요소의 이벤트 속성에 정의 하는 방법(**권장 하지 않음**)
```
<div onclick="alert('안녕하세요');">인사</div>
```
## 자바스크립트 주석 처리 
- 한줄 주석 : // 
- 여러줄 주석 : /* ~ */


## 변수 
- 정의
	- 변수는 값을 담기 위해 이름을 붙인 상자 
	- 컴퓨터의 메모리에 일정한 크기의 영역으로 생성됩니다.
- 변수선언 
	- 자바스크립트는 런타임시에 자료형이 결정되는 동적타입 변수입니다.
	- 따라서 변수에는 자료형을 명시하지 않고 **var, let, const** 선언자만 사용합니다.

- 선언자 종류
	- **var 선언자** : 일반적인 변수 선언자 입니다. 
	- **let 선언자** : 블록 유효 범위를 가지는 지역변수를 선언합니다.
	```javascript
	let x = "outer x";
	{
		let x = "inner x";
		let y = "inner y";
		console.log(x); // inner x
		console.log(y);  // inner y
	}
	console.log(x); // outer x
	console.log(y); // ReferenceError: y is not defined 
	```
	- **const 선언자** : 블록 유효 범위를 가지면서 한 번만 할당 할수 있는 변수(상수)를 선언합니다.
		- const로 선언한 상수는 let문으로 선언한 변수처럼 동작합니다. 단, 반드시 초기화해야 한다는 차이점이 있습니다.
		- const 문으로 선언한 변수에 다시 대입을 시도하면 타입 오류가 발생합니다.
		```javascript
		const c = 2;
		c = 5; // Uncaught TypeError 
		```
		- const 문으로 선언한 상수 값은 수정할 수 없지만, 상수 값이 객체이거나 배열일 경우에는 프로퍼티 또는 프로퍼티 값을 수정할 수 있습니다. 
- 선언 방법
	- 선언자 변수명; (선언만)  예) var sum;
	- 선언자 변수명 = 값; (선언후 초기화)  예) var sum = 10;
	- 쉼표를 사용하면 변수 여러개를 한문장으로 선언할 수 있습니다.
	```javascript
	예) var sum, a;
	```
	- 변수를 선언하기만 하면 변수 안에는 정의되지 않았음을 뜻하는 undefined라는 값이 들어갑니다.
	```javascript
	var x; 
	console.log(x); // -> undefined
	```
	- 대입연산자(=)를 사용하면 변수에 값을 대입할 수 있습니다.  
	```javascript
	var x = 5;
	var a = 1, b = 2, c = 3;
	```
- 변수 선언 생략 
	- var 문으로 선언하지 않은 변수 값을 읽으려고 시도하면 참조 오류가 발생
	```javascript
	console.log(x); // -> ReferenceError : x is not defined(오류 메시지)
	```
	- 그러나 var 문으로 선언하지 않은 변수에 값을 대입할 때는 오류가 발생하지 않음
	```
	x = 2; 
	console.log(x); // 2
	```
	
	- 변수를 선언하지 않은 상태에서 값을 대입하면 자바스크립트 엔진이 그 변수를 자동으로 전역 변수로 선언하게 됨
	- 변수를 선언하지 않고 변수를 사용하는 행위는 오류의 원인이 될 수 있습니다.
	
- 변수 끌어올림과 변수 중복 선언
	- 프로그램은 작성한 순서에 따라 윗줄 부터 차례대로 실행됩니다. 하지만 변수 선언은 이 원칙을 따르지 않습니다. 
	```javascript
	console.log(x); // -> undefined
	var x; 
	```
	
	- 이 코드에서 1번줄은 아직 변수 x가 선언되지 않았기 때문에 오류가 발생할 것 같지만 실제로는 오류가 발생하지 않고 undefined가 출력됩니다.

	- 프로그램 중간에서 변수를 선언하더라도 변수가 프로그램 첫머리에 선언된 것 처럼 다른 문장 앞에 생성되기 때문입니다. 

	- 이를 변수 선언의 끌어올림(호이스팅 hoisting)이라고 합니다.

	- 단 선언과 동시에 대입하는 코드는 끌어올리지 않습니다.
	```javascript
	console.log(x); // -> undefined
	var x = 5; 
	console.log(x) // -> 5
	```
- 변수의 명명규칙
	- 사용할 수 있는 문자는 알파벳(a-z, A-Z), 숫자(0-9), 밑줄(_), 달러 기호($)
	- 첫 글자로는 숫자를 사용할 수 없다. 즉 첫 글자는 알파벳(a-z, A-Z), 밑줄(_), 달러기호($)중 하나
	- 예약어를 식별자로 사용할 수 없다
	- 사용가능 예)
	```
	key, sum1, _name, $width, sum_all, sumAll, newVale
	```

	- 사용불가 예)
	```
	1st - 첫 글자가 숫자이므로
	sum-all (하이픈은 사용할 수 없읍)
	new(예약어)
	```
   
	- 예약어 예시 : [https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Lexical_grammar](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Lexical_grammar) (예약어 부분 참조)
	
	
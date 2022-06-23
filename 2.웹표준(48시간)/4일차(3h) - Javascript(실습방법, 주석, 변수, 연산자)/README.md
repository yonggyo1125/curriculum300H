# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1PPBuHDhDH6w-w7zk9SlgfqSWEGOexWOq?usp=sharing)

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
	```javascript
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
	
- 변수에 저장할 수 있는 자료형
	- <b>문자형(String)</b> : var 변수 = "사용할 문자나 숫자"; 
	- <b>숫자형(Number)</b> : var 변수 = 숫자; 또는 Number(”문자와 숫자”);
	- <b>논리형(Boolean)</b>
		- var 변수 = true 또는 false; Boolean(데이터);
		- false - false, 0, null, '', undefined
	- <b>null</b> : 변수의 값이 비어 있다는 것을 표시할 경우 
	- <b>undefined</b> : 
		- 변수가 선언되었을때 값이 지정되지 않았을 경우
		- 변수를 선언하면 기본값은 undefined 입니다.

	- <b>자료형 체크</b> : typeof 변수 또는 데이터
	- <b>객체(Object)</b>
	- **원시값** : 객체 이외의 값(숫자, 문자, 논리값, null, undefined, ""(빈값)

## 연산자
- 산술연산자 
	- 더하기(+), 빼기(-), 곱하기(\*), 나누기(/), 나머지(%)
- 문자 결합 연산자
	- 여러개의 문자를 하나의 문자형 데이터로 결합할 때 사용
	- 더하기에 피연산자로 문자형 데이터가 한 개라도 포함되어 있으면 다른 피연산자의 데이터는 자동으로 문자형 데이터로 형 변환되고 문자 결합이 이루어져 하나의 문자형 데이터로 반환
	```javascript
	"Text1 " + "Text2" = "Text1 Text2";
	"100" + 200 = 100200;
	100 + "200" = 100200;
	```
- 대입연산자
	- 연산된 데이터를 변수에 저장할 때 사용
	```javascript
	A = B
	A = 1 + 2
	```
	
	- 복합대입연산자 : 산술연산자 + 대입연산자
	<table>
		<tbody>
			<tr>
				<td>A += B</td>
				<td>A = A + B</td>
			</tr>
			<tr>
				<td>A *= B</td>
				<td>A = A * B</td>
			<tr>
			<tr>
				<td>A /= B</td>
				<td>A = A / B</td>
			</tr>
			<tr>
				<td>A %= B</td>
				<td>A = A % B</td>
			</tr>
		</tbody>
	</table>
	
	- 문자형 데이터 결합
	```javascript
	  var str = "<table border='1'>";
	  str += "<tr>";
	  str += "<td>1</td><td>2</td><td>3</td>";
	  str += "</tr>";
	  str += "</table>";
			
	  document.body.innerHTML = str;
	```
- 증감연산자
	- 증가연산자(++) - 숫자형 데이터를 1씩 증가
	```
	변수++; 또는 ++변수;
	```
	- 감소연산자(--) - 숫자형 데이터를 1씩 감소
	```
    변수--; 또는 --변수; 
	```
	- var a = ++b;   b를 1 증가 시킨 후 a에 대입
	- var a = b++;   a에 대입 후 b를 1 증가 
	
- 비교연산자 
	- 두 데이터를 '크다', '작다', '같다'와 같이 비교할때 사용
	- 연산된 결과는 논리형데이터(true - 참, false - 거짓)
	
|종류|설명||
|----|------|----------|
|A > B|||
|A < B|||
|A >= B|||
|A <= B|||
|A == B|A와 B는 같다|데이터 일치 여부만 체크( 10 == "10" -> true)|
|A != B|A와 B는 다르다|데이터 불일치 여부만 체크( 10 != "10" ->  false)|
|A === B|A와 B는 같다|데이터 일치 + 데이터 종류 일치 여부 체크(10 === "10" --> false)|
|A !== B|A와 B는 다르다|데이터 불일치 + 데이터 종류 불일치 여부 체크(10 !== "10" --> true)|

- 논리연산자

|종류|설명|
|-----|--------|
|\|\||OR 연산자, 비교하는 대상 중 하나라도 true면 true가 됨|
|&&|AND 연산자, 비교하는 대상 모두 true일때 true, 그렇지 않다면 false|
|!|not 연산자, 피 연산자의 값을 반대로 바꿈, true -> false, false -> true|

- 연산자 우선순위
	- 아래에서 위로 우선순위가 높아짐
	- 1. () 
	- 2. 단항 연산자(--, ++, !)
	- 3. 산술 연산자(\*, /, %, +, -)
	- 4. 비교 연산자(>, >=, <, <=, ==, ===, !=, !==)
	- 5. 논리 연산자(&&, ||)
	- 6. 대입(복합 대입)연산자(=, +=, -=, \*=, /=, %=)
	```
	  예) ++A * B <= C
		 단항 연산자 ++A
		 * B 
	  C와 비교(<=)
	```
- 삼항 조건 연산자
	- 조건식? 조건식이 true일때 : 조건식이 false 일때
	- var a = (b > 2)?”2 보다 크다”:”2보다 작거나 같다”;  

## 제어문 
### 조건문 
- 조건식의 값이 참(true), 거짓(false)인지에 따라 제어
```javascript
   if (조건식) {
     // 처리할 내용
 } 
```
```javascript
	if (조건식) {
   
	} else { // 조건식이 false 일때 
		// 처리할 내용
	}
```
```javascript
	if (조건식1) {

	} else if (조건식2) { // 조건식1이 false일때 
	
	} else if (조건식3) { // 조건식1, 조건식2가 false 일때
 
	} else { // 조건식1, 조건식2, 조건식3이 false 일때 

	}
```   
- 조건식에서 false가 되는 기타 데이터<br>
   **0, null, ""(빈문자), null, undefined는 false로 인식**을 하고 **그 이외의 값은 true로 인식**
   
- 조건식을 여러개 사용할 경우 논리연산자 사용
	- **조건식1|| 조건식2** : 조건식1이 참이거나 조건식2가 참일때
  
	- **조건식1 && 조건식2** : 조건식1과 조건식2가 모두 참일때
    
- if문 안에 if 문을 중첩해서 사용할 수 있다.

## 선택문
- 여러개(case) 중에서 하나를 선택
```javascript
var 변수 = 초기값;
switch (변수) {
	case 값1 : 코드1;
		break;
	case 값2 : 코드2;
		break;
	case 값3 : 코드3;
		break;
	case 값4 : 코드4;
		break;
	default : 코드5;	
}
```     
- 변수에 할당된 값이 case의 각 값에 매칭되면 매칭된 코드가 실행 됩니다.
- 최종적으로 매칭되는 값이 없는 경우는 default 부분의 코드5가 실행됩니다.
- 각 case에서 break가 없다면 다음 case로 넘어값니다. 
```     
	case 값1: 코드1; 
	case 값2: 코드2;
		break     
```     
- 값1로 매칭이 되면 코드1, 코드2가 실행이 됩니다.

## 반복문

### while 문
```javascript
while (조건식) {
	// 조건식이 true일때 반복 실행되는 부분 
}
```
- while구분에서는 반복 구간을 탈출할 수 있는 조건을 반드시 구현해야 합니다.(없을 경우 무한 loop) 
- 탈출 키워드 (while, do~while, for)
	- **break** : 반복을 종료
	- **continue** : 현재 반복 실행을 종료 하고 다음 반복 실행으로 넘어감
	```javascript
	var num = 0;     
	while (num < 1000) { 
		if (num >= 100) break;  // 반복횟수가 100이상이면 멈춤     
		num++;
	}
	```
### do ~ while 문
do {  }로 정의된 반복 실행을 적어도 1번은 실행하고 while 조건식에 따라 반복
```javascript
var 변수 = 초기값; 
do {
	// 최소 한번 이상 실행되는 반복 처리
} while(조건식);
```
```javascript
var num = 10;
do {
	console.log(num); // 10
    num++;
} while (num < 10);
```
### for문
while, do~while과 마찬가지로 조건식을 만족하면 반복을 합니다. 다만 for문은 **초기값, 증감식, 조건식**을 통한 일정 구간 반복
```
for(초기값; 조건식; 증감식) {
   // 반복 처리
}
```
```
var total = 0;
for (var i = 0; i < 100; i++) {  total += i;}
```

### 중첩반복문
반복문도 조건문(if)와 마찬가지로 중첩해서 반복할 수 있습니다.
```
예)
for(초기값; 조건식; 증감식) {
	for (초기값; 조건식; 증감식) {
		// 반복 처리 코드 
	}
}
```
* * * 
# ECMAScript 6 부터 추가된 데이터 타입
## 심벌
심벌은 ECMAScript6 부터 추가된 원시 값입니다. 심벌은 자기 자신을 제외한 그 어떤 값과도 다른 유일무의한 값입니다.

### 심벌의 생성
심벌은 Symbol()을 사용해서 생성합니다.
```javascript
var sym1 = Symbol();
```
Symbol()은 호출할 때마다 새로운 값을 만듭니다. 이를 확인하기 위해 또 다른 심벌을 생성해 보겠습니다.
```javascript
var sym2 = Symbol();
```
다음처럼 sym1 값과 sym2 값이 다르다는 사실을 확인할 수 있습니다.
```javascript
console.log(sym1 == sym2); // false
```
또한 Symbol()에 인수를 전달하면 생성된 심벌의 설명을 덧붙일 수 있습니다.
```javascript
var HEART = Symbol("하트");
``` 
심벌의 설명은 toString() 메서드를 사용해서도 확인할 수 있습니다.
```javascript
console.log(HEART.toString()); // Symbol(하트)
```
예를 들어 오셀로 케임을 만들 때 칸의 상태를 값으로 표현하는 코드를 작성한다고 가정해 보면 다음과 같이 칸의 상태를 숫자와 같은 값으로 표현할 수 있습니다.
```javascript
var NONE = 0; 
var BLACK = -1;
var WHITE = 1;
```

이 코드에서 숫자 자체의 특별한 의미가 없습니다. 칸의 상태를 cell 변수에 저장한다고 가정했을 때, cell 값을 확인하려면 cell == WHITE라고 작성해야 프로그램이 읽기 쉬워질 것입니다. 게다다 cell == 1이라고 작성해도 아무런 문제없이 동작합니다. 그러나 이러한 행위는 프로그램을 읽기 어렵게 만들므로 바람직하지 않습니다. **심벌을 활용하면 앞의 코드를 다음처럼 고칠 수 있습니다.**
```javascript
var NON = Symbol("none");
var BLACK = Symbol("black");
var WHITE = Symbol("white");
```
**심벌은 유일무이한 값입니다.** 따라서 이렇게 수정하면 변수 cell 값을 확인할 때 NONE, BLACK, WHITE만 사용하도록 제한할 수 있습니다.

### 심벌과 문자열 연결하기
Symbol.for()를 활용하면 문자열과 연결된 심벌을 생성할 수 있습니다.
```javascript
var sym1 = Symbol.for("club");
```
그러면 전역 레지스트리에 심벌이 만들어집니다. 또한 전역 레지스트리에서 그 심벌을 위에 지정한 문자열로 불러올 수 있습니다.
```javascript
var sym2 = Symbol.for("club");
console.log(sym1 == sym2); // true
```
이 기능을 활용하면 코드의 어느 부분에서도 같은 심벌을 공유할 수 있습니다. 심벌과 연결된 문자열은 Symbol.keyFor()로 구현할 수 있습니다.
```javascript
var sym1 = Symbol.for("club");
var sym2 = Symbol("club");
console.log(Symbol.keyFor(sym1)); // club
console.log(Symbol.keyFor(sym2)); // undefined
```

## 템플릿 리터럴
- 템플릿 리터럴은 ECMAScript6 부터 추가된 문자열 표현 구문입니다. 
- 템플릿이란 이부만 변경해서 반복하거나 재사용할 수 있는 틀을 말합니다. 템플릿 리터럴을 사용하면 표현식의 값을 문자열에 추가하거나 여러 줄의 문자열을 표현할 수 있습니다. 

### 기본적인 사용법
템플릿 리터럴은 역따옴표(\`)로 묶은 문자열입니다. 간단한 템플릿 리터럴은 큰 따옴표 또는 작은 따옴표로 묶은 문자열과 모습이 같습니다.
```javascript
`I'm going to learn Javascript.`
```
문자열 리터럴에서 줄 바꿈 문자를 표현할 때는 이스케이프 시퀀스(\n)를 사용했지만, 템플릿 리터를을 사용하면 일반적인 줄 바꿈 문자를 사용할 수 있습니다.
```javascript
var t =`Main errs as long as 
he strives.`;
```
이 문자열을 문자열 리터럴로 표현하면 다음과 같은 모습이 됩니다.
```javascript
var t ="Main errs as long as\nhe strives.";
```
물론 템플릿 리터럴에서도 이스케이스 시퀀스를 사용할 수 있습니다.
```javascript
var t =`Main errs as long as\nhe strives.`;
```
이스케이프 시퀀스 문자를 그대로 출력하려면 템플릿 리터럴 앞에 String.raw를 붙입니다.
```javascript
var t =String.raw`Main errs as long as\nhe strives.`;
```
이 문자열을 문자열 리터럴로 표현하면 다음과 같은 모습이 됩니다.
```javascript
var t ="Main errs as long as\\nhe strives.";
```
> 템플릿 리터럴 앞에 붙은 String.raw는 태그 함수라고 부릅니다.

### 보간 표현식
- 템플릿 리터럴 안에는 플레이스 홀더를 넣을 수 있습니다. 플레이스 홀더는 ${...}로 표기합니다.
- 자바스크립트 엔진은 플레이스 홀더 안에 든 ... 부분을 표현식으로 간주하여 평가(evaluation)합니다. 이를 활용하여 문자열 안에 변수나 표현식의 결과값을 삽입할 수 있습니다.
```javascript
var a = 2, b = 3;
console.log(`${a} + ${b} = ${a+b}`);
var now = new Date();
console.log(`오늘은 ${now.getMonth() + 1}월 ${now.getDate()}일 입니다.`);
```
- 모든 코드에서 ${} 안에 든 표현식이 평가되어 문자열로 바뀌었다는 사실을 확인할 수 있습니다.
- ECMAScript5 까지는 문자열에 변수 값을 삽입할 때 더하기(+) 연산자로 문자열을 연결하는 방법을 사용했지만 보간 표현식을 활용하면 좀 더 알아보기 쉽게 작성할 수 있습니다.

>**플레스이 홀더**<br>플레이스 홀더는 실제 내용물을 나중에 삽입할 수 있도록 확보한 장소라는 뜻으로 쓰입니다. 프로그래밍 언어에서 플레이스 홀더는 사용자의 입력 값처럼 실행 시점에 외부에서 주어지는 값을 표현식에 반영하고자 할 때 그것이 들어갈 수 있도록 마련한 장소를 뜻합니다.
# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1rrUPS1V0jcIFK2sVFGA0E9oIbqsWj5U2?usp=sharing)

# 내장 객체
자바스크립트에는 처음부터 사용할 수 있는 내장 객체(빌트인 오브젝트)가 마련되어 있습니다. 내장 객체는 자바스크립트라는 프로그래밍 언어의 뼈대를 구성합니다.

## 내장 생성자
- 자바스크립트에서 사용할 수 있는 생성자에는 사용자가 정의하는 생성자 외에도 자바스크립트에 처음부터 포함된 **내장 생성자**가 있습니다.
- ECMAScript5에서 사용할 수 있는 주요 내장 생성자는 다음과 같습니다. 이 생성자는 모든 자바스크립트 실행 환경에서 사용할 수 있습니다. 내장 생성자에는 이미 유용한 프로퍼티와 메서드가 마련되어 있으므로 내장 생성자를 이용하여 객체를 생성하면 다양한 작업을 쉽게 처리할 수 있습니다.

#### ES5의 내장 생성자

|생성자 이름|생성되는 객체|학습 URL|
|----|-------|----|
|Object|일반 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Object)|
|String|문자열 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/String)|
|Number|숫자 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Number)|
|Boolean|논리값 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Boolean)|
|Array|배열|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array)|
|Date|날짜와 시간을 다루는 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date)|
|Function|함수 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Function)|
|RegExp|정규표현식 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/RegExp)|
|Error|오류 객체|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Error)|
|EvalError|eval() 함수와 관련된 오류를 표현하는 객체|
|InternalError|자바스크립트 내부에서 발생한 오류를 표현하는 객체||
|RangeError|값이 허용 범위를 넘었을 때 발생하는 오류를 표현하는 객체||
|SyntaxError|문법에 어긋날 때 발생한 오류를 표현하는 객체||
|TypeError|값이 기대한 타입이 아닐 때 발생한 오류를 표현하는 객체||
|URIError|잘못된 URI를 만났을 때 발생한 오류를 표현하는 객체||

#### ES6부터 추가된 주요 내장 생성자

|생성자 이름|생성되는 객체|학습 URL|
|----|-------|----|
|Symbol|심벌을 생성|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Symbol)|
|Int8Array8|부호가 있는 8비트 정수 배열을 생성||
|Uint8Array8|부호가 없는 8비트 정수 배열을 생성||
|Int16Array16|부호가 있는 16비트 정수 배열을 생성||
|Uint16Array16|부호가 없는 16비트 정수 배열을 생성||
|Int32Array32|부호가 있는 32비트 정수 배열을 생성||
|Uint32Array32|부호가 없는 32비트 정수 배열을 생성||
|Float32Array32|32비트 부동 소수점 배열을 생성||
|Float64Array64|64비트 부동 소수점 배열을 생성||
|ArrayBuffer|고정 길이 이진 데이터 버퍼를 생성|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer)|
|DataView|ArrayBuffer를 읽고 쓸 수 있는 수단을 제공|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/DataView)|
|Promise|처리 지연 및 비동기 처리를 관리하는 수단을 제공|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)|
|Generator|제너레이터 함수를 다룰 수 있는 수단을 제공||
|GeneratorFunction|제너레이터 함수를 생성||
|Proxy|객체의 기본적인 동작을 제정의하는 기능을 제공||
|Map|key/value 맵을 생성|[학습 URL](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map)|
|Set|중복을 허용하지 않는 데이터 집합을 생성|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Set)|
|WeakMap|약한 참조를 유지하는 key/value 맵을 생성|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/WeakMap)|
|WeekSet|약한 참조를 유지하는 고유한 데이터 집합을 생성|[학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/WeakSet)|



## Function 생성자
- Function은 함수를 생성하는 내장 생성자 입니다.
- 예를 들어 앞서 예제로 등장한 함수 square는 다음과 같이 Function 생성자로 함수를 생성하도록 수정할 수 있습니다.
```javascript
var square = new Function("x", "return x * x");
```
- 이때 첫 번째 인자인 "x"는 인수의 이름을 뜻하는 문자열이고 두 번째 인수는 함수의 몸통(function body)이 작성된 문자열 입니다.
- 일반적으로 인수가 n개일 때는 다음과 같이 사용합니다.

```
var 변수 이름 = new Function(첫 번째 인수, ..., n번째 인수, 함수 몸통);
```
- Function 생성자로 생성한 함수는 전역 변수와 자신의 지역변수 만 읽고 쓸 수 있다는 단점이 있어서 함수를 동적으로 생성해야 하는 특별한 상황 외에는 사용하지 않습니다.
- 또한 악의를 품은 사용자가 입력한 문자열을 Function 생성자의 인수인 '함수의 몸통'으로 전달하면 악성코드가 실행되어 보안 문제가 발생할 수도 있습니다.
- Function 생성자는 함수를 생성하는 기능보다 함수 리터럴에 래퍼 객체를 제공한다는 점에 의미를 둘 수 있습니다.
- Function 객체에는 함수를 다룰 때 중요한 프로퍼티와 메서드가 정의되어 있으며 이를 모든 함수에서 사용할 수 있습니다.
- [Function 생성자 참조](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Function)



## 기타 내장 객체
자바스크립트에서는 처음부터 사용할 수 있는 객체를 가리켜 <b>내장 객체(빌트인 오브젝트)</b>라고 부르며, 앞서 설명한 내장 생성자가 내장 객체를 생성합니다.

#### 기타 내장 객체

|내장객체|설명|
|----|--------|
|전역객체|프로그램 어디에서나 사용할 수 있는 객체|
|JSON|JSON을 처리하는 기능을 제공 [JSON 객체 참조](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/JSON)|
|Math|수학적인 함수와 상수를 제공 [Math 객체 참조](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Math)|
|Reflect|프로그램의 흐름을 가로채는 기능을 제공|


## 전역 객체
- 전역 객체의 프로퍼티는 프로그램의 어느 위치에서나 사용할 수 있습니다.
- 자바스크립트 인터프리터가 시작될 때 혹은 웹 브라우저가 새로운 페이지를 읽어 들일 때마다 새로운 전역객체가 생성됩니다.

#### 전역 객체 프로퍼티

|분류|프로퍼티|
|----|--------|
|전역 프로퍼티|undefined, NaN, Infinity|
|생성자|Object(), String(), Number() 등|
|전역 함수|parseInt(), parseFloat(), isNaN() 등|
|내장 객체|Math, JSON, Reflect|

- 클라이언트 측 자바스크립트에서는 Window 객체가 전역 객체입니다. 
- Window 객체에는 위의 표의 프로퍼티와 웹 브라우저 고유의 다양한 프로퍼티가 추가되어 있습니다.
- 웹 브라우저의 전역 객체 프로퍼티는 콘솔에서 다음과 같이 입력하여 확인할 수 있습니다.
```javascript
console.dir(window);
```

## 자바 스크립트 객체의 분류

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%82%B4%EC%9E%A5%EA%B0%9D%EC%B2%B4/images/image1.png)

### 네이티브 객체
- **ECMAScript 사양에 정의된 객체**가 네이티브 객체(native Object)입니다. 
- 내장 생성자(Object, String, Number, Boolean, Array, Function 등)로 생성된 객체와 JSON, Math, Reflect 등이 네이티브 객체입니다.

### 호스트 객체
- ECMAScript에는 정의되어 있지 않지만 **자바스크립트 실행 환경에 정의된 객체**가 호스트 객체(host object)입니다. 
- 브라우저 객체(Window, Navigator, History, Location 등), DOM에 정의되어 있는 객체, Ajax를 위한 XMLHttpRequest 객체, HTML5의 각종 API 등이 클라이언트 측 자바스크립트에 젖의된 호스트 객체의 예입니다.

### 사용자 정의 객체
- 사용자가 정의한 자바스크립트 코드를 실행한 결과로 생성된 객체가 사용자 정의 객체입니다.


### 정규 표현식
- abc - 문자열을 포함한다
- [abc] - 문자클래스 - 문자집합안에 특정 문자 한개
- [^abc] - 부정문자클래스 : 문자 집합안의 특정 문자 한개
- [a-z] - 두 문자 사이의 모든 문자
- .  - 줄 바꿈 문자를 제외한 문자 한개
- \d - 모든 숫자[0-9]와 같음
- \D - 숫자를 제외한 모든 문자 한개 [^0-9]와 같음
- \w - 임의의 영어 단어 문자(알파벳, 숫자, 언더스코어) 한개
- \W - 영어단어 문자(알파벳, 숫자, 언더스코어)를 제외한 문자 한개
- \s  - 모든 공백 문자 한 개
- \S  - 공백문자가 아닌 문자 한개

- x{2,4} - x를 최소 2번, 최대 4번 반복
- x{2,} - x를 2번 잇ㅇ 반복
- x?  - x를 한번 이하 반복
- x+ - x를 한번 이상 반복
- x\* - x를 0번 이상 반복
- (x) - x를 그룹화(부분 정규 표현식)
- ^ - 문자열의 시작 위치 
- $ - 문자열의 마지막 위치
- x|y|z - x,y,z 중 하나(선택)

- 정규표현식 플래그
	- i - 대문자와 소문자를 구별하지 않는다
	- g - 전역 검색한다. 처음뿐만 아니라 일치하는 모든 것을 검색한다
	- m - 여러 줄 모드로 검색한다 
	
	

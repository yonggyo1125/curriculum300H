# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1anlosgavXJsAuJKygKsHQNKo816JqjEH?usp=sharing)

# 배열

## 배열의 기초

### 배열 리터럴로 생성하기
- 배열 리터럴은 쉼표로 구분한 값을 대괄호([])로 묶어서 표현합니다.
```javascript
var evens = [2, 4, 6, 8];
```
- [...] 부분이 배열 리터럴이며 배열 값 하나를 **배열 요소**라고 부릅니다.
- 배열 요소에는 왼쪽부터 순서대로 0, 1, 2, ...라는 번호가 매겨져 있습니다. 
- 요소에 매긴 번호는 **요소 번호**  또는 **인덱스**라고 부릅니다.
- 자바스크립트의 배열은 객체 타입이므로 변수에 대입하면 배열의 참조가 변수에 저장됩니다.
[그림 필요]
- 배열 리터럴 안에 어떠한 요소도 작성하지 않으면 빈 배열이 생성됩니다.
```javascript
var empty = [];
console.log(empty); // -> []
```
- 배열 리터럴 요소의 값을 생략하면 그 요소는 생성되지 않습니다.
```javascript
var a = [2, , 4];
console.log(a); // [2, undefined, 4]
```
- 앞 코드의 결과에서 인덱스가 1인 요소, 즉 두 번째 요소에 undefined가 표시되어 있지만 실제로는 없습니다.
- 변수의 요소에는 변수와 마찬가지로 모든 타입의 값이 올 수 있습니다.
```javascript
var various = [ 3.14, "pi", true, {x:1, y:2}, [2,4,6,8] ];
```

### length 프로퍼티
- 배열의 length 프로퍼티에는 **배열 요소의 최대 인덱스 값 + 1**이 담겨 있습니다.
```javascript
var evens = [ 2, 4, 6, 8 ];
evens.length  // 4
```
- length 프로퍼티 값을 가리켜 **배열 길이**라고 부릅니다.
- 자바스크립트에서는 배열 요소의 개수를 뜻하지 않는 경우가 있으므로 주의해야 합니다.
- length 프로퍼티에 현재의 배열 요소의 개수보다 작고 0보다 큰 정수 값을 대입하면 배열 길이가 줄어듭니다. 즉, 그 배열 길이를 넘는 인덱스 번호에 할당된 배열 요소는 삭제됩니다.
```javascript
var a = ["A", "B", "C", "D"];
a.length = 2;
console.log(a); // -> ["A", "B"]
```
- length 프로퍼티에 그 배열 길이보다 큰 정수 값을 대입하면 배열에 새로운 요소가 추가되지 않고 length 프로퍼티 값만 바뀝니다.

### Array 생성자로 생성하기
- 배열은 Array 생성자로 생성할 수 있습니다.
```javascript
var evens = new Array(2, 4, 6, 8); // [2, 4, 6, 8]을 생성
var empty = new Array();  // 빈 배열 []을 생성
var a = new Array(2, 4);  // 배열 리터럴 [2, 4]와 똑같은 배열을 생성
var various = new Array(3.14, "pi", "true", { x : 1, y : 2}, [2, 4, 6, 8]);
```
- Array 생성자의 인수가 한 개고 그 값이 양의 정수면 의미가 달라집니다. 이때 인수는 배열 길이를 뜻하므로 배열의 그 길이만큼 생성됩니다.
```javascript
var x = new Array(3);
console.log(x.length); // 3
```
- 반면 Array 생성자의 인수가 한 개고 그 값이 양의 정수가 아니라면 오류가 발생합니다.
```javascript
var x = new Array(-3);
```

### 배열 요소의 참조
- 특정 인덱스의 요소는 대괄호([])를 사용해서 읽거나 쓸 수 있습니다.
```javascript
evens[2] // -> 6(인덱스가 2인 요소 즉, 세 번째 요소)
```
- 배열 요소 하나는 변수 하나로 사용할 수 있습니다. 즉, 배열 요소에는 모든 타입의 데이터를 할당할 수 있으며 프로그램에서 참조할 수 있습니다.

### 배열은 객체
- C나 Java 같은 프로그래밍 언어의 배열 요소는 메모리의 연속된 공간에 차례대로 배치되어 있습니다. 따라서 인덱스를 지정하면 인덱스가 가리키는 요소를 매우 빠르게 읽거나 쓸 수 있습니다.
- 그러나 자바스크립트의 배열은 다릅니다. 자바스크립트의 배열은 Array 객체이며 객체로 배열의 기능을 가상으로 흉내 낸 것입니다.
- Array 객체는 배열의 인덱스를 문자열로 변환해서 그것을 프로퍼티로 이용합니다. 즉, 배열에 대괄호 연산자를 사용하는 것은 객체에 대괄호 연산자를 사용하는 것과 마찬가지이며, 배열의 요소 번호로 숫자 값 대신 문자열을 사용할 수 있습니다.
```javascript
var a = ["A", "B", "C", "D"];
console.log(a["2"]); // -> c
```
- 이때 없는 배열 요소를 읽으려고 시도하면 undefined가 반환됩니다.
```javascript
a[4] // undefined
```

>ECMAScript6 부터는 TypedArray 객체가 추가되었습니다. TypedArray의 배열 요소는 C나 Java 등의 배열과 마찬가지로 메모리의 연속된 공간에 차례대로 배치됩니다. 이를 활용하면 배열 요소를 빠르게 읽고 쓸 수 있습니다.

### 배열 요소의 추가와 삭제
- 없는 배열요소에 값을 대입하면 새로운 요소가 추가됩니다.
```javascript
var a = ["A", "B, "C"]
a[3] = "D";
console.log(a);  // -> ["A", "B", "C", "D"]
```
- 다음과 같이 push 메서드를 사용하면 요소를 배열 끝에 추가할 수 있습니다.
```javascript
var a = ["A", "B", "C"];
a.push("D");
console.log(a); // ["A", "B", "C", "D"]
```
- delete 연산자를 사용하면 특정 배열 요소를 삭제할 수 있습니다.
```javascript
delete a[1];
console.log(a); // -> ["A", undefined, "C", "D"]
```
- delete 연산자를 사용하여 배열의 요소를 삭제해도 그 배열의 length 프로퍼티 값은 바뀌지 않습니다. 즉, 삭제한 요소만 사라집니다.

### 배열 요소가 있는지 확인하기
배열 요소가 있는지 확인하는 방법은 객체의 프로퍼티가 있는지 확인하는 방법과 같습니다. 즉, for/in 문이나 hasOwnProperty 메서드를 사용해서 확인할 수 있습니다. 
```javascript
for(var i in a) console.log(i);
a.hasOwnProperty("3");
a.hasOwnProperty("4");
```

* * * 
## 배열의 메서드
배열에는 Array 타입 객체이며 Array.prototype 프로퍼티를 상속받습니다. Array.prototype에는 수많은 메서드가 정의되어 있으며, 모든 배열은 이 메서드를 사용할 수 있습니다. 이들 메서드를 사용하면 직접 코드를 작성하지 않아도 다양한 배열을 처리할 수 있습니다.

### Array.prototype의 메서드 목록
- 수정 메서드는 원본 배열을 바로 수정합니다. 
- 접근자 메서드는 배열을 다른 형태로 가공한 새로운 배열을 반환하며 원본 배열은 수정하지 않습니다.
- 반복 메서드는 원본 배열의 모든 요소를 순회하며 특정한 작업을 수행합니다.

<table>
	<thead>
	<tr>
		<th>분류</th>
		<th>메서드</th>
		<th>설명</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan='9' nowrap>수정<br>메서드</td>
			<td>copyWithin(target, begin, end)</td>
			<td>begin~end-1 사이의 요소를 target의 위치에 복사한다.</td>
		</tr>
		<tr>
			<td>fill(value, begin, end)</td>
			<td>begin~end-1 사이의 요소를 target으로 대체한다.</td>
		</tr>
		<tr>
			<td>pop()</td>
			<td>배열의 마지막 요소를 잘라낸다</td>
		</tr>
		<tr>
			<td>push(data)</td>
			<td>배열 끝에 data 값을 배열 요소로 추가한다.</td>
		</tr>
		<tr>
			<td>reverse()</td>
			<td>배열의 요소를 역순으로 정렬한다.</td>
		</tr>
		<tr>
			<td>shift()</td>
			<td>배열의 첫 번째 요소를 잘라낸다</td>
		</tr>
		<tr>
			<td>sort([callback])</td>
			<td>배열의 요소를 callback이 구현한 방법에 따라 정렬한다.</td>
		</tr>
		<tr>
			<td nowrap>splice(index, howmany, [,data ...])</td>
			<td>index부터 howmany개의 배열 요소를  data로 대체한다.</td>
		</tr>
		<tr>
			<td>unshift(data1, [, data2, ...])</td>
			<td>인수로 지정한 데이터를 배열의 시작 부분에 추가한다.</td>
		</tr>
		<tr>
			<td rowspan='7' nowrap>접근자<br>메서드</td>
			<td>concat(array)</td>
			<td>지정된 배열을 대상 배열에 연결한다.</td>
		</tr>
		<tr>
			<td>indexOf(data, index)</td>
			<td>data와 같은 첫 번째 배열 요소의 키를 검색한다(index는 검색을 시작하는 위치).</td>
		</tr>
		<tr>
			<td>join(string)</td>
			<td>배열의 요소를 string으로 연결한 문자열을 반환한다.</td>
		</tr>
		<tr>
			<td>lastIndexOf(data, index)</td>
			<td>data와 같은 마지막 배열 요소의 키를 검색한다(index는 검색을 시작하는 위치).</td>
		</tr>
		<tr>
			<td>slice(begin, [, end])</td>
			<td>begin~end-1 사이의 요소를 제거한 새로운 배열을 반환한다.</td>
		</tr>
		<tr>
			<td>toLocaleString()</td>
			<td>배열의 요소를 해당 지역에 맞는 언어로 번역한(지역화한) 문자열로 변환한 뒤 쉼표로 연결해서 반환한다.</td>
		</tr>
		<tr>
			<td>toString()</td>
			<td>배열의 요소를 문자열로 변환한 뒤 쉼표로 연결해서 반환한다.</td>
		</tr>
		<tr>
			<td rowspan='12'>반복<br>메서드</td>
			<td>entries()</td>
			<td>배열 요소의 인덱스와 값이 요소로 들어 있는 배열을 값으로 반환하는 이터레이터를 반환한다.</td>
		</tr>
		<tr>
			<td>every(callback)</td>
			<td>배열의 모든 요소가 callback 조건에 부합하는지 판정한다.</td>
		</tr>
		<tr>
			<td>filter(callback)</td>
			<td>callback조건에 부합하는 배열 요소만 담은 새로운 배열을 생성한다.</td>
		</tr>
		<tr>
			<td>find(callback, [, thisArg])</td>
			<td>callback 조건에 부합하는 첫 번째 배열 요소를 반환한다.</td>
		</tr>
		<tr>
			<td>findIndex(callback, [, thisArg])</td>
			<td>callback 조건에 부합하는 첫 번째 배열 요소의 인덱스를 반환한다.</td>
		</tr>
		<tr>
			<td>forEach(callback [, thisArg])</td>
			<td>배열의 요소를 callback을 사용하여 차례대로 처리한다.</td>
		</tr>
		<tr>
			<td>keys()</td>
			<td>배열 요소의 인덱스를 값으로 가지는 이터레이터를 반환한다.</td>
		</tr>
		<tr>
			<td>map(callback [, thisArg])</td>
			<td>배열의 요소를 callback으로 처리한 결과물을 배열로 반환한다.</td>
		</tr>
		<tr>
			<td>reduce(callback [, inital])</td>
			<td>이웃한 배열 요소를 배열의 오른쪽부터 차례대로 callback으로 처리하여 하나의 값으로 만들어 반환한다(initial은 초깃값).
		</tr>
		<tr>
			<td>reduceRight(callback [, initial])</td>
			<td>이웃한 배열 요소를 배열의 오른쪽부터 차례대로 callback으로 처리하여 하나의 값으로 만들어 반환한다(initial은 초기값).</td>
		</tr>
		<tr>
			<td>some(callback [, thisArg])</td>
			<td>배열 요소 단 하나라도 callback 조건에 부합하는지 판정한다.</td>
		</tr>
		<tr>
			<td>values()</td>
			<td>배열 요소의 데이터를 값으로 가지는 이터레이터를 반환한다.</td>
		</tr>
	</tbody>
</table>


## 다차원 배열
자바스크립트는 다차원 배열을 정의하기 위한 문법은 제공하지 않지만, 배열에 배열을 중첩하면 다차원 배열과 비슷한 기능을 구현할 수 있습니다.


### 2차원 배열의 생성
2차원 배열을 생성하는 방법은 다음과 같습니다.

```javascript
var x = new Array(3); // 우변을 배열 리터럴 []로 표기할 수도 있음 
for(var i = 0; i < 3; i++) {
	x[i] = new Array(3); // 우변을 배열 리터럴 []로 표기할 수도 있음
}

// 2차원 배열에 값을 대입
for(var count = 1, i = 0; i < 3; i++) {
	for(var j = 0; j < 3; j++) {
		x[i][j] = count++;
	}
}
```

이 코드를 배열 리터럴을 사용해 다음과 같이 표현할 수 있습니다.
```javascript
var x = [
	[1, 2, 3],
	[4, 5, 6],
	[7, 8, 9]
];
```
2차원 배열의 요소를 읽고 쓸 때는 [] 연산자를 두 번 사용합니다. 행 번호가 i고 열 번호가 j인 요소는 x[i][j]로 표기합니다.

## 유사배열
자바스크립트 프로그램에서는 배열은 아니지만 배열로 처리할 수 있는 객체를 다양한 상황에서 사용합니다.

### 유사 배열 객체
자바스크립트에서 배열이란 곧 Array 타입의 객체를 말합니다. Array 타입의 객체는 다음과 같은 장점이 있습니다.
- 0 이상의 정수 값을 프로퍼티 이름으로 갖는다.
- length 프로퍼티가 있으며, 요소를 추가하거나 삭제하면 length 프로퍼티 값이 바뀐다. 또한 length 프로퍼티 값을 줄이면 그에 따라 배열 크기가 줄어든다.
- 프로토타입이 Array.prototype이므로 Array.prototype의 메서드를 상속받아서 사용할 수 있다. 또한 instanceof 연산자로 평가하면 Array 생성자 함수로 생성된 객체로 표시된다.

그러나 이러한 성질 중에서 프로퍼티 이름이 0이상의 정수이며 length 프로퍼티가 있는 객체는 대부분 배열로 다룰 수 있습니다. 이러한 객체를 **유사 배열 객체**라고 합니다.<br><br>
예를 들어 다음 객체가 유사 배열 객체입니다.
- 함수의 인수를 저장한 Arguments 객체
- DOM의 document.getElementsByTagName 메서드, document.getElementsByName 메서드 등이 반환하는 NodeList 객체

유사 배열 객체는 다음과 같은 방법으로 직접 생성할 수 있습니다.
```javascript
// 유사 배열 객체를 생성해서 값을 대입한다.
var a = {};
for(var i = 0; i < 10; i++) {
	a[i] = i;
}
a.length = 10;

// 유사 배열 객체의 요소 합을 구한다
for(var i = 0, sum = 0; i < a.length; i++) sum += a[i];
console.log(sum);
```
유사 배열 객체는 Array.prototype의 메서드를 사용할 수 없습니다. 그러나 배열로 참조하거나 대입할 수 있으며 for 문이나 for/in 문으로 반복 처리를 할 수 있습니다. 따라서 많은 자바스크립트 프로그램에서 배열로 다룰 수 있습니다. <br>
그러나 요소의 추가와 삭제 또는 length 프로퍼티값을 요소의 개수와 연계하는 등의 처리는 배열처럼 동작하지 않습니다.

### Array.prototype의 메서드를 유사 배열 객체에서 사용하기
유사 배열 객체는 Array.prototype의 메서드를 직접 사용할 수 없습니다. 그러나 Function.prototype.call 메서드로 간접 호출하면 사용할 수 있습니다.

```javascript
var a = {0:"A", 1: "B", 2: "C", length: 3};
Array.prototype.join.call(a, ","); // -> "A,B,C"
Array.prototype.push.call(a, "D");
// -> Object {0: "A", 1: "B", 2: "C", 3 : "D", length : 4}
Array.prototype.slice.call(a, 0); //  -> ["A", "B", "C", "D"] : 진짜 배열로 변환
var a = {0: 1, 1: 2, 2: 3, length: 3};
Array.prototype.map.call(a, function(c) { return v*v; }); // -> [1, 4, 9]
```
이처럼 Array.prototype의 메서드를 유사 배열 객체에 적용할 수는 있지만 concat 메서드를 제외한 나머지 배열 메서드는 배열처럼 동작하지 않습니다.
```javascript
var a = {0: "A", 1: "B", 2: "C", length: 3};
Array.prototype.concat.call(a, ["D", "E"]);
// -> [{0: "A", 1: "B", 2: "C", length : 3}, "D", "E"]
```

## ECMAScript6+에 추가된 기능

### 비구조화 할당
비구조화 할당(Destructuring)은 배열, 객체, 반복 가능한 객체에서 값을 꺼내어 그 값을 별도의 변수에 대입하는 문장입니다.

#### 배열의 비구조화 할당
**기본적인 사용법**<br>

```javascript
var [a, b] = [1, 2];  // var a = 1, b = 2와 같음
```
- 이 문장을 실행하면 변수 a와 b를 선언한 후 우변의 배열에서 요소를 하나씩 꺼내어 인덱스 순서대로 a, b에 대입합니다. 우변의 배열을 분할하여 좌변의 변수에 할당하므로 분할 할당이라고도 합니다.
- 우변이 배열일 때 좌변의 변수 쌍을 표현하는 문법은 배열 리터럴과 비슷합니다. 즉, 변수를 쉼표로 구분하고 대괄호로 묶습니다.<br><br>

- <b>이미 선언된 변수를 비구조화 할당하는 예</b>

```javascript
[a, b] = [2*a, 3*b];  // a = 2*a, b = 3*b와 같음
[a, b] = [b, a] = // a 값과 b 값을 교환함
```

- 이때 우변의 값의 개수와 좌변 변수의 개수가 같을 필요는 없습니다.
- 좌변의 변수 개수가 우변의 값 개수보다 많으면 좌변의 남는 변수에는 undefined가 값으로 할당됩니다.
- 우변의 값 개수가 좌변의 변수 개수보다 많으면 남은 값은 무시됩니다. 
- 또한 변수가 없는 인덱스 값도 무시됩니다.

```javascript
[a, b, c] = [1, 2]; // a = 1, b = 2, c = undefined와 같음
[a, b] = [1, 2, 3]; // a = 1, b = 2와 같음, 3은 무시됨
[,a,,b] = [1, 2, 3, 4]; // a = 2, b = 4와 같음
```

- 배열 비구조화 할당 표현식의 값은 우변 값이 됩니다.

```javascript
var array, first, second;
array = [first, second] = [1,2,3,4];
// first = 1, second = 2, array = [1, 2, 3, 4]와 같음 
```

<br>
<b>나머지 요소</b><br>
배열의 비구조화 할당을 할 때는 함수의 나머지 매개변수와 마찬가지로 전개 연산자인 ...을 사용하여 나머지 요소(rest elements)를 이용할 수 있습니다.

```javascript
[a,b, ...rest] = [1, 2, 3, 4]; // a = 2, b = 2, rest = [3, 4]와 같음
```

좌변의 ...rest 부분이 나머지 요소이며, 변수 rest에는 할당되지 않은 우변의 남은 요소들이 배열로 할당됩니다.<br><br>

<b>요소의 기본값</b><br>
배열의 비구조화 할당을 할 때는 함수의 인수와 마찬가지로 기본값을 설정할 수 있습니다. 비구조화 할당하는 좌변의 변수에 undefined가 할당되면 undefined 대신에 기본값을 할당합니다.

```javascript
[a=0, b=1, c=2] = [1, 2];  // a = 1, b = 2, c = 2와 같음
```

#### 객체의 비구조화 할당
**기본적인 사용법**<br>
객체도 비구조화 할당을 할 수 있습니다. 이때 좌변에는 객체 리터럴과 비슷한 문법을 사용합니다. 프로퍼티를 쉽표로 구분하고 중괄호로 묶어줍니다. 이 프로퍼티의 이름은 우변의 프로퍼티 이름이며 프로퍼티 값으로는 임의의 변수를 사용할 수 있습니다.
```javascript
var {a: x, b: y} = {a: 1, b: 2};  // x = 1, y = 2와 같음
```
<br>
이미 선언된 변수를 비구조화 할당하는 예

```javascript
{a: x, b: y} = {a: y, b: x};  // x 값과 y 값을 교환한다.
```

<br>
좌변의 변수에 호응하는 프로퍼티 이름이 오른쪽에 없으면 그 변수에는 undefined가 할당됩니다.

```javascript
{a: x, b: y} = {a: 1, c: 2};  // x = 1, y = undefined와 같음
```

<br>
우변에 값이 있지만 그에 대응하는 이름의 변수가 좌변에 없으면 무시됩니다.

```javascript 
{a: x, b: y} = {a: 1, b: 2, c: 3}; // x = 1, y = 2와 같음. 3은 무시됨
```

<br>
다음 코드는 Math 객체의 프로퍼티를 변수에 대입합니다.

```javascript
var {sin: sin, cos: cos, tan: tan, PI: PI} = Math;
// var = sin = Math.sign, cos = Math.cos, tan = Math.tan, PI = Math.PI와 같음
```

이렇게 만들어 두면 Math.sin(Math.PI / 3)이라는 코드를 sin(Math.PI / 3)으로 간결하게 표현할 수 있습니다.<br><br>

<b>프로터피의 기본값</b><br>
배열의 비구조화 할당을 할 때는 함수의 인수와 마찬가지로 기본값을 설정할 수 있습니다. 비구조화 할당하는 좌변의 변수에 undefined가 할당되면 그 대신에 기본값을 할당합니다.

```javascript
{a: x = 1, b: y=2, c: z=3} = {a: 2, b: 4}; // x = 2, y = 4, z = 3과 같음
```

<br><br>
<b>프로퍼티 이름 생략하기</b><br>
좌변에는 변수 이름만 쉼표로 구분해서 작성할 수 있습니다. 이때는 프로퍼티 이름이 변수의 이름이 됩니다.

```javascript
{a, b} = {a: 1, b: 2}; // {a: a, b: b} = {a: 1, b: 2}와 같음
var {sin, cos, tan, PI} = Math;
//var sin = Math.sin, cos = Math.cos, tan = Math.tan, PI = Math.PI와 같음
```

프로퍼티 이름을 생략한 상태에서도 기본값을 지정할 수 있습니다.

```javascript
{a=1, b=2, c=3} = {a: 2, b: 4}; // a = 2, b = 4, c = 3과 같음
```

<br><br>
<b>반복 가능한 객체의 비구조화 할당</b><br>
우변에 반복 가능한(이터러블한)객체가 있을 때도 비구조화 할당을 할 수 있습니다. 이때 좌변에는 리터럴과 비슷한 문법을 사용합니다.

```javascript
var [a, b, c] = "ABC"; // var a = "A", B = "B", c = "C"와 같음
function* createNumbers(from, to) {
	while(from <= to) yield from++;
}
var [a,b,c,d,e] = createNumbers(10, 15);
// a = 10, b = 11, c = 12, d = 13, e = 14와 같음 
```

<br><br>
<b>중첩된 자료 구조의 비구조화 할당</b><br>
중첩된 객체나 배열에도 비구조화 할당을 적용할 수 있습니다.<br>

```javascript
var [a, [b, c]] = [1, [2, 3]]; // var a = 1, b = 2, c = 3과 같음
var {a: x, b : {c: y, d: z}} = {a: 1, b: {c: 2, d: 3}};
// var x = 1, y = 2, z = 3과 같음
var person = {name: "Tom", age: 17, hobby: ["Tennis", "Music"]};
var {name, age, hobby:[hobby1]} = person;
// var name = "Tom", age = 17, hobby1 = "Tennis"와 같음
```

### 전개 연산사

...은 전개 연산자(spread operator)라고 합니다. 전개 연산자는 반복 가능한(이터러블한) 객체를 반환하는 표현식 앞에 표기하며. 이를 통해 반복 가능한 객체를 배열 리터럴 또는 함수의 인수 목록으로 펼칠 수 있습니다.

```javascript
[..."ABC"]  // -> ["A", "B", "C"]
f(..."ABC")  // f("A", "B", "C")와 같음
[1, ...[2, 3, 4], 5]  // -> [1, 2, 3, 4, 5]
f(...[1, 2, 3])  // f(1, 2, 3)과 같음
```

다음 코드는 제너레이터가 만든 이터레이터를 배열 리터럴 안에 펼치는 예입니다.

```javascript
function* createNumbers(from, to) {
	while(from <= to) yield from++;
}
var iter = createNumbers(10, 15);
[...iter]  // -> [10, 11, 12, 13, 14, 15]
```

배열 두 개를 연결할 때는 보통 Array.prototype.concat 메서드를 사용하지만 전개 연산자를 활용하면 Array.prototype.push 메서드로도 배열을 연결할 수 있습니다.

```javascript
var a = ["A", "B", "C"];
a.push(...["D", "E"]);  // -> ["A", "B", "C", "D", "E"]
```

다음 코드는 배열 안의 최대값은 Math.max로 구합니다.

```javascript
var a = [5,2,3,7,1];
Math.max(...a);
```

### Map
Map 객체는 데이터는 수집하여 활용하기 위한 객체입니다. 값의 고유한 식별 정보인 **키**와 **값**의 쌍를 Map 객체 안에 저장해서 사용합니다. Map 객체 안에서 키는 고유한 값입니다. 따라서 Map 객체는 키로 값을 사상(Map)한 객체로 간주합니다. Map 객체는 외부에서 키를 사용하여 원하는 값을 추가/삭제/검색할 수 있습니다. 키와 값의 데이터 타입에는 제한이 없습니다.<br><br>
Object 객체도 키와 값이 쌍을 이룬 프로퍼티가 모여서 만들어진 것입니다. 그런 의미에서 보면 Map 객체와 비슷해 보입니다. 하지만 Map 객체는 Object 객체와 비교했을 떄 다음과 같은 차이점이 있습니다.<br>
- Map 객체에는 데이터를 수집하기 위한 다양한 메서드가 마련되어 있다.
- Object 객체는 키로 문자열만 사용할 수 있지만 Map 객체는 키 타입에 제한이 없다.
- Map 객체는 내부적으로 해시 테이블을 활용하기 때문에 데이터 검색 속도가 빠르다.
- Map 객체는 반복 가능(이터러블)하여 for/of 문으로 순회하면 키와 값으로 구성된 배열을 반환한다.
- Map 객체는 데이터 개수를 size 프로퍼티로 구할 수 있다. 하지만 Object 객체는 프로퍼티 개수를 수동으로 계산해야 한다.
이처럼 Map 객체는 Object 객체보다 빠르기도 하지만 다양한 데이터 수집 기능까지 제공합니다.

#### Map 객체의 생성
Map 객체는 Map 생성자 함수로 생성합니다. 인수를 지정하지 않으면 데이터가 없는 빈 Map 객체가 생성됩니다.

```javascript
var map = new Map();
console.log(map); // -> Map {}
```

<br><br>
초기 데이터를 인수로 지정해서 생성할 수도 있습니다. 이때 초기 데이터는 요소를 두 개 이상 포함한 배열 [키, 값]을 그 값으로 가지는 반복 가능한(이터러블한) 객체입니다.

```javascript
var zip = new Map([["Tom", "131-8634"], ["Huck", "556-0002"]]);
console.log(zip); // -> Map {"Tom" => "131-8634", "Huck" => "556-0002"}
```

다음 코드는 제너레이터로 이터레이터를 생성하는 방식으로 앞의 코드와 똑같은 작업을 처리합니다.

```javascript
function* makeZip() {
	yield ["Tom", "131-8634"];
	yield ["Huck", "556-0002"];
}
var zips = makeZip();
zip = new Map(zips);
console.log(zip);  // -> Map {"Tom" => "131-8634", "Huck" => "556-0002"}
```

Map 객체 안에 저장된 데이터(키와 값의 쌍)의 개수는 size 프로퍼티로 구할 수 있습니다.

```javascript
console.log(zip.size); // -> 2
```

#### Map 객체의 메서드
Map 객체는 Map.prototype 프로퍼티와 메서드를 상속받습니다.

|메서드|설명|
|-----|-----------|
|clear()|Map 객체 안에 있는 모든 데이터를 삭제한다.|
|delete(key)|Map 객체에서 key가 가리키는 데이터를 삭제한다.|
|entries()|Map 객체가 가진 데이터(키와 값 쌍) 값을 저장한 이터레이터를 데이터를 삽입한 순서대로 반환한다.|
|forEach(callback)|Map 객체의 모든 데이터를 대상으로 callback 함수를 실행한다. 이때 실행 순서는 데이터가 삽입된 순서이다.|
|get(key)|Map 객체에서 key가 가리키는 데이터를 반환한다.|
|has(key)|Map 객체에서 key가 가리키는 데이터가 있는지 판정한다.|
|keys()|Map 객체에서 데이터 키를 값으로 가지는 이터레이터를 반환한다.|
|set(key, value)|Map 객체에 키가 key고 값이 value인 데이터를 추가한다.|
|values()|Map 객체에서 데이터 값을 값으로 가지는 이터레이터를 반환한다.|

#### 데이터 추가하기
Map 객체에 데이터를 추가할 때 set(key, value) 메서드를 사용합니다. key는 데이터의 키 값이 되며 value는 데이터의 값이 됩니다. 이미 그 키 값이 가리키는 데이터가 있다면 덮어씁니다.

```javascript
var zip = new Map();
zip.set("Tom", "131-8634");
zip.set("Huck", "556-0002");
console.log(zip);  // -> Map {"Tom" => "131-8634", "Huck" => "556-0002"}
```

#### 값 읽기
특정 키 값이 가리키는 데이터 값을 읽을 때는 get(key) 메서드를 사용합니다. key는 데이터의 키값 입니다.

```javascript
console.log(zip.get("Tom")); // -> 131-8634
```

지정한 키가 Map 객체 안에 없으면 undefined를 반환합니다.

#### 데이터가 있는지 확인
Map 객체 안에 특정 키를 가진 데이터가 있는지 확인하려면 has(key) 메서드를 사용합니다. key는 데이터의 키 값입니다.

```javascript
console.log(zip.has("Tom")); // true
console.log(zip.has("Becky")); // false
```

#### 데이터의 삭제
특정 키 값이 가리키는 데이터 값을 삭제하려면 delete(key) 메서드를 사용합니다. key는 데이터의 키 값입니다.

```javascript
zip.delete("Huck");
```

Map 객체 안에 있는 모든 데이터를 삭제하려면 clear 메서드를 사용합니다.

```javascript
zip.clear();
```

#### 모든 키 값의 열거
keys 메서드는 Map 객체가 가진 데이터의 키 값을 가진 이터레이터를 반환합니다.

```javascript
var zip = new Map([["Tom", "131-8634"], ["Huck", "556-0002"]]);
var iter = zip.values();
for(var v of iter) console.log(v);  // 131-8634, 556-0002의 순서대로 출력
```

#### 모든 데이터의 열거
entries 메서드는 Map 객체가 가진 데이터(키, 값)을 가진 이터레이터를 반환합니다.

```javascript
var zip = new Map([["Tom", "131-8634"], ["Huck", "556-0002"]]);
var iter = zip.entries();
for(var v of iter) console.log(v);
// ["Tom", "131-8634"], ["Huck", "556-0002"]의 순서대로 출력
```

Map 객체는 그 자체가 반복 가능한(이터러블한) 객체입니다. 이를 활용하여 Map 객체의 데이터를 열거할 수도 있습니다.

```javascript
var zip = new Map([["Tom", "131-8634"], ["Huck", "556-0002"]]);
for(var v of zip) console.log(v);
// ["Tom", "131-8634"], ["Huck", "556-0002"]의 순서대로 출력
```

비구조화 할당을 활용하면 키 값과 값을 꺼내는 코드를 더욱 간결하게 작성할 수 있습니다.

```javascript
var zip = new Map([["Tom", "131-8634"], ["Huck", "556-0002"]]);
for(var [key, value] of zip) console.log(key, value);
// Tom  131-8634, Huck 556-0002의 순서대로 출력
```

#### 모든 데이터를 함수로 처리하기
forEach 메서드를 활용하면 인수로 받은 함수를 Map 객체의 모든 데이터에 적용할 수 있습니다.<br>
forEach 메서드에 인수로 넘긴 함수는 다음과 같은 인수를 받습니다.
- value : 현재 처리하는 데이터 값
- key : 현재 처리하는 데이터 키
- map : 처리 중인 Map 객체
<br>
다음 예는 Map 객체의 데이터 목록을 표시합니다.

```javascript
var zip = new Map([["Tom", "131-86634"], ["Huck", "556-0002"]])
zip.forEach(function(value, key, map) {
	console.log(key + " => " + value);
});
// -> Tom => 131-8634
// Huck => 556-0002
```

### Set
Set 객체는 중복되지 않는 유일한 데이터를 수집하여 활용하기 위한 객체입니다. Set 객체는 데이터 값의 단순 집합(Set)으로 간주합니다. Set 객체는 외부에서 키를 사용하여 데이터 값을 추가/삭제/검색할 수 있습니다. 값의 데이터 타입에는 제한이 없습니다. 객체 타입도 사용할 수 있고 원시 타입도 사용할 수 있습니다.

#### Set 객체의 생성
Set 객체는 Set 생성자 함수로 생성합니다. 인수를 넘기지 않으면 데이터가 없는 빈 Set 객체가 생성되빈다.

```javascript
var set = new Set();
console.log(set); // Set {}
```

초기 데이터를 인수로 지정해서 생성할 수도 있습니다. 이때의 초기 데이터는 값을 가지는 반복 가능한(이터러블한) 객체입니다.

```javascript
var zip = new Set(["131-8634", "556-0002"]);
```

다음 코드는 제너레이터로 이터레이터를 생성해서 앞의 코드와 같은 작업을 합니다.

```javascript
function* makeZip() {
	yield "131-8634";
	yield "556-0002";
}

var zips = makeZip();
zip = new Set(zips);
console.log(zip);	// Set {"131-8634", "556-0002"}
```

Set 객체 안에 저장된 데이터(키와 값의 쌍) 개수는 size 프로퍼티로 구할 수 있습니다.

```javascript
console.log(zip.size);  // 2
```

#### 동일성의 정의
Set 객체에서 값 동일성은 일치(===) 연산자가 정의하는 동일성과는 약간 차이가 납니다. Set 객체에서는 NaN과 NaN이 같으며 +0과 -0이 같습니다.

#### Set 객체의 메서드
Set 객체는 Set.prototype에서 프로퍼티와 메서드를 상속받습니다.

|메서드|설명|
|-----|----------|
|add(value)|Set 객체에 데이터 값 value를 추가한다.|
|clear()|Set 객체 안의 모든 데이터를 삭제한다.|
|delete(value)|Set 객체에서 value를 값으로 갖는 데이터를 삭제한다.|
|values()|Set 객체에서 데이터 값을 값으로 갖는 이터레이터를 반환한다.|
|forEach(callback)|Set 객체의 모든 데이터를 대상으로 callback 함수를 실행한다.|
|has(value)|Set 객체에서 value를 값으로 갖는 데이터가 있는지 판별한다.|
|keys()|Set 객체에서 데이터 값을 값으로 갖는 이터레이터를 반환한다.|
|values()|Set 객체에서 데이터를 값으로 갖는 이터레이터를 반환한다.|
> keys 메서드는 values 메서드와 같습니다. 원래 Set 객체에는 필요없는 메서드지만 Map 객체와 Set 객체의 유사성을 유지하는 데 필요합니다.

#### 데이터 추가
Set 객체에 데이터를 추가할 때는 add(value) 메서드를 사용합니다. value는 데이터 값으로 반환값은  Set 객체 입니다.

```javascript
var zip = new Set();
zip.add("131-8634");
zip.add("556-0002");
console.log(zip);  // -> Set {"131-8634", "556-0002"}
```

#### 데이터가 있는지 확인
Set 객체 안에 특정 값을 갖는 데이터가 있는지 확인하려면 has(value) 메서드를 사용합니다. value는 데이터 값입니다.

```javascript
console.log(zip.has("131-8634"));	// true
console.log(zip.has("154-0000"));	// false
```

#### 데이터의 삭제
Set 객체 안에서 특정한 값에 대응하는 데이터를 삭제하려면 delete(value) 메서드를 사용합니다. value는 데이터 값입니다.

```javascript
zip.delete("131-8634");
```

Set 객체 안에 있는 모든 데이터를 삭제하려면 clear 메서드를 사용합니다.

```javascript
zip.clear();
```

#### 전체 데이터 값의 열거
keys와 values 메서드는 Set 객체가 가진 데이터 값을 저장한 이터레이터를 반환합니다.

```javascript
var zip = new Set(["131-8634", "556-0002"]);
var iter = zip.keys();
for(var v of iter) console.log(v); // 131-8634, 556-0002의 순서대로 출력
```
<br>
entries 메서드는 Set 객체가 가진 데이터인 [값, 값]을 저장한 이터레이터를 반환합니다. 똑같은 데이터 값을 두 개나 갖고 있는 이유는 Map의 entries 메서드와 출력 형식을 통일하기 위해서 입니다.

```javascript
var zip = new Set(["131-8634", "556-0002"]);
var iter = zip.entries();
for(var v of iter) console.log(v);
// ["131-8634", "131-8634"], ["556-0002", "556-0002"]의 순서대로 출력
```
<br>
Set 객체는 그 자체가 반복 가능한(이터러블한) 객체이므로 이를 활용하여 Set 객체의 데이터를 열거할 수도 있습니다.

```javascript
var zip = new Set(["131-8634", "556-0002"]);
for(var v of zip) console.log(v);
// 131-8634, 556-0002의 순서대로 출력
```

#### 모든 데이터를 함수로 처리하기
forEach 메서드를 활용하면 인수로 받은 함수를 Set 객체의 모든 데이터에 적용할 수 있습니다.<br>forEach 메서드에 넘긴 함수는 다음과 같은 인수를 받습니다.
- 첫 번쨰 인수 value1 : 현재 처리하는 데이터 값
- 두 번째 인수 value2 : 현재 처리하는 데이터 값
- 세 번째 인수 set : 처리 중인 Set 객체
첫 번째 인수와 두 번째 인수는 모두 현재 처리하는 데이터 값입니다. 같은 내용의 인수를 중복해서 받는 이유는 Map.prototype.forEach와 Array.prototype.forEach 메서드의 인터페이스를 통일하기 위해서입니다.<br>
다음 코드는 Set 객체의 데이터 목록을 표시하는 예입니다.

```javascript
var zip = new Set(["131-8634", "556-0002"]);

zip.forEach(function(value1, value2, map) {
	console.log(value1 + " => " + value2);
});
// -> 131->8634 => 131-8634
// 556-0002 => 556-0002
```

#### WeakMap과 WeakSet
Map이나 Set과 유사한 객체로 WeekMap과 WeekSet을 들 수 있습니다. 이들 객체는 데이터의 키 값을 약한 참조로 관리합니다. 키 값이 약한 참조라는 말은 다른 객체가 참조하고 있는 객체라도 가비지 컬렉션의 대상이 될 수 있다는 뜻입니다. 즉, WeakMap과 WeakSet에 저장된 데이터라 하더라도 키 값으로 사용한 원본 객체를 참조하는 객체가 없어지면 가비지 컬렉션의 대상이 됩니다. WeakMap과 WeakSet에서는 키 값을 약한 참조로 관리하기 때문에 다음과 같은 제약 사항이 있습니다.
- 키 값으로 객체만 사용할 수 있다.
- 키 값 목록은 가져올 수 없다.
- 반복 가능한 객체가 아니므로 for/of 문으로는 열거할 수 없다.
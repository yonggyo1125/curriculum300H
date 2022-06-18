# 배열

## 배열의 기초

### 배열 리터럴로 생성하기
- 배열 리터럴은 쉼표로 구분한 값을 대괄호([])로 묶어서 표현합니다.
```javascript
var evens = { 2, 4, 6, 8 };
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
			<td rowspan='9'>수정<br>메서드</td>
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
			<td>splice(index, howmany, [,data ...])</td>
			<td>index부터 howmany개의 배열 요소를  data로 대체한다.</td>
		</tr>
		<tr>
			<td>unshift(data1, [, data2, ...])</td>
			<td>인수로 지정한 데이터를 배열의 시작 부분에 추가한다.</td>
		</tr>
		<tr>
			<td rowspan='7'>접근자<br>메서드</td>
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
	</tbody>
</table>


## 다차원 배열


## 유사배열

## ECMAScript6+에 추가된 기능
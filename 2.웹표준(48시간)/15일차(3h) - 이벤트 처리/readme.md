# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/112vMoOeJTD505HJVx45no94cvTXw_F_r?usp=sharing)

# 이벤트 처리

## 이벤트 처리기를 등록하는 방법
- 이벤트 주도형 프로그램(event driven program)에서는 이벤트가 발생했을 때 실행할 함수를 등록해 둡니다.
- 이벤트가 발생했을 때 실행되는 함수를 <b>이벤트 처리기</b> 또는 <b>이벤트 리스너</b>라고 합니다. 
- 이벤트 처리기는 세 가지 방법으로 등록할 수 있습니다.

	- (1) HTML 요소의 이벤트 처리기 속성에 설정하는 방법
	
	```html 
	<input type="button" onclick="changeColor();">
	```
	
	- (2) DOM 요소 객체의 이벤트 처리기 프로퍼티에 설정하는 방법
	
	```javascript
	var btn = document.getElementById("button");
	btn.onclick = changeColor;
	```
	
	- (3) addEventListener 메서드를 사용하는 방법
	
	```javascript
	var btn = document.getElementById("button");
	btn.addEventListener("click", changeColor, false);
	```

### 이벤트 처리기의 문제점

이벤트 처리기를 등록하는 방법 중에서 (1)과 (2)는 다음과 같은 특징과 문제점이 있습니다.

- HTML 요소의 이벤트 처리기 속성에 설정할 경우
	- HTML 문서를 읽어 들일 때 이벤트 처리기도 함께 설정하기 때문에 설정하기 쉽습니다.
	- HTML과 자바스크립트 프로그램이 뒤섞여 프로그램의 가독성이 떨어집니다. 결과적으로 프로그램의 유지 보수성이 떨어집니다.
	- 특정 요소의 특정 이벤트에 대해서 이벤트 처리기를 단 하나만 등록할 수 있습니다. 그 요소에 똑같은 이벤트를 처리하는 이벤트 처리기를 등록하면 나중에 등록한 함수가 이전에 등록한 함수를 덮어씁니다. 일반적으로 HTML 문서 하나는 여러 명의 개발자가 작성한 자바스크립트 파일과 라이브러리 위에서 동작합니다. 이때 두 개 이상의 파일이 같은 요소에 같은 이벤트를 처리하는 함수를 등록해 버리면 이전에 등록한 이벤트 처리기가 무효로 처리됩니다. 따라서 해당 자바스크립트 파일이나 라이브러리가 제대로 동작하지 않을 수 있습니다.
	
- DOM 요소 객체의 이벤트 처리기 프로퍼티에 설정할 경우
	- HTML과 자바스크립트 프로그램을 분리해서 작성할 수 있습니다. 즉, HTML과 자바스크립트의 연결을 느슨하게 하는 <b>겸손한 자바스크립트</b>를 구현할 수 있습니다. 결과적으로 프로그램의 유지 보수성을 높일 수 있습니다.
	- 특정 요소의 특정 이벤트에 대해서 이벤트 처리기를 단 하나만 등록할 수 있습니다. 이는 (1)번과 같은 단점입니다.

## 이벤트 리스너를 등록하고 삭제하는 방법

### addEventListener 메서드로 이벤트 리스너 등록하기
- addEventListener로 등록한 함수는 이벤트 리스너라는 이름으로 부릅니다.
- addEventListener 메서드를 이용하면 같은 요소의 같은 이벤트에 이벤트 리스너를 여러 개 등록할 수 있습니다. 

```javascript
target.addEventListener(type, listener, useCapture);
```

- 구성 요소의 의미는 다음과 같습니다.
	 - <b>target</b> : 이벤트 리스너를 등록할 DOM 노드
	 - <b>type</b> : 이벤트 유형을 뜻하는 문자열("click", "mouseup" 등)
	 - <b>listener</b> : 이벤트가 발생했을 때 처리를 담당하는 콜백 함수의 참조
	 - <b>useCapture</b> : 이벤트 단계
- useCapture는 다음 값 중 하나가 될 수 있습니다.
	- <b>true</b> : 캡쳐링 단계
	- <b>false</b> : 버블링 단계(생략했을 때의 기본값)
	
```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			var element = document.getElementById("box");
			element.addEventListener("click", function(e) {
				e.currentTarget.style.backgroundColor = "red";
			}, false);
		};	
	</script>
</head>
<body>
	<div id="box">click me</div>
</body>
</html>
```	

- 앞 코드에서 이벤트 리스너 함수가 받는 인수 e는 <b>이벤트 객체</b>이며, 이벤트 객체 안에는 발생한 이벤트의 다양한 정보가 담겨 있습니다. 
- <b>e.currentTarget</b>은 클릭한 요소를 참조하는 요소 객체입니다. 
- addEventListener 메서드의 인수인 type에는 이벤트 처리기 이름 앞부분에 붙은 on을 생략한 문자열을 대입합니다. 
- 앞 코드에서는 useCapture 값을 false로 지정합니다. false는 이벤트 전파 단계가 버블링 단계일 때 그 이벤트를 캡쳐하라는 뜻 입니다. 보통은 false를 지정합니다.
- 앞 코드에서는 listener 부분에 익명 함수를 넘겼지만 함수 선언문으로 정의한 함수 이름을 넘길 수도 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			var element = document.getElementById("box");
			element.addEventListener("click", changeColor, false);
		};
		
		function changeColor(e) {
			e.currentTarget.style.backgroundColor = "red";
		}
	</script>
</head>
<body>
	<div id="box">click me</div>
</body>
</html>
```

### addEventListener를 사용해서 얻을 수 있는 장점
- 같은 요소의 같은 이벤트에 이벤트 리스너를 여러 개 등록할 수 있다.
- 버블링 단계는 물론 캡처링 단계에서도 활용할 수 있다. 반면에 DOM 요소 객체에 직접 등록한 이벤트 처리기는 버블링 단계의 이벤트만 캡쳐할 수 있다.
- removeEventListener, stopPropagation, preventDefault를 활용하여 이벤트 전파를 정밀하게 제어할 수 있다.
- HTML 요소를 포함한 모든 DOM 노드에 이벤트 리스너를 등록할 수 있다.

### removeEventListener 메서드로 이벤트 리스너 삭제하기

```javascript 
target.removeEventListener(type, listener, useCapture);
```

- 인수는 addEventListener 메서드와 같습니다. removeEventListener 메서드를 사용하면 이미 등록된 이벤트 리스너 중에서 같은 인수를 사용한 이벤트 리스너가 target 요소에서 삭제됩니다.
- 만약 addEventListener의 listener로 익명함수를 사용했다면 해당 이벤트 리스너는 removeEventListener 메서드로 삭제할 수 없습니다. 
- 이벤트 리스너를 삭제하려면 그 함수의 참조를 변수나 배열에 저장하고 있어야 합니다. 
- 하지만 이벤트 리스너 안에서 removeEventListener를 호출해서 이벤트 리스너가 스스로 삭제하게 만드는 방법이 있습니다. 이때는 listener에 <b>arguments.callee</b>를 넘깁니다.

```html
<!DOCTYPE html?
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			var element = document.getElementById("button");
			element.addEventListener("click", function() {
				console.log("Hello!");
				element.removeEventListener("click", arguments.callee, false);
			}, false);
		};
	</script>
</head>
<body>
	<button id="button">버튼</button>
</body>
</html>
```

## 이벤트 객체
- 이벤트 처리기와 이벤트 리스너는 이벤트 객체를 인수로 받습니다. 이벤트 객체는 해당 이벤트의 다양한 정보를 저장한 프로퍼티와 이벤트의 흐름을 제어하는 메서드를 가지고 있습니다.

```javascript
function changeColor(e) {
	e.currentTarget.style.backgroundColor = "red";
}
```

- 이 함수의 인수 e가 바로 이벤트 객체입니다. 이벤트 객체를 받는 인수의 이름이 e로 되어 있지만 이것은 어디까지나 함수 안에서만 유효한 인자이므로 그 어떤 식별자도 이벤트 객체의 이름으로 사용할 수 있습니다. 
- 이것이 이벤트 객체임을 명시하기 위해 e 또는 event를 사용하는 것이 관계입니다. 
- 또한 이벤트 객체가 가지는 프로퍼티는 발생한 이벤트 유형에 따라 달라집니다.

### 이벤트 객체의 공통 프로퍼티

|프로퍼티|값의 타입|설명|
|-----|----|----------|
|type|문자열|이벤트 이름("click", "mousedown", "keydown" 등)|
|target|요소 객체|이벤트가 발생한 요소|
|currentTarget|요소 객체|처리를 담당하는 이벤트 리스너가 등록된 요소 객체|
|eventPhase|정수|이벤트 전파 단계(1: 캡쳐링 단계, 2: 타깃 단계, 3: 버블링 단계)|
|timeStamp|정수|이벤트 발생 시각|
|bubbles|논리값|버블링 단계인지를 뜻하는 값|
|cancelable|논리값|preventDefault()로 기본 이벤트를 취소할 수 있는지를 뜻하는 값|
|defaultPrevented|논리값|preventDefault()로 기본 작업이 취소되었는지를 뜻하는 값|
|isTrusted|논리값|해당 이벤트가 사용자의 액션에 의해 생성되었는지를 뜻하는 값|


### 마우스 이벤트 객체

대표적인 이벤트로는 click, dblclick, mousedown, mouseup, mousemove, mouseout, mouseover 등이 있습니다.

#### 마우스와 관련된 이벤트가 가진 고유의 프로퍼티

|프로퍼티|값의 타입|설명|
|-----|----|--------|
|screenX, screenY|정수|클릭한 위치 좌표(컴퓨터 화면의 왼쪽 위 꼭짓점이 원점)|
|clientX, clientY|정수|클릭한 위치의 윈도우 좌표(표시 영역의 왼쪽 위 꼭짓점이 원점)|
|pageX, pageY|정수|클릭한 위치의 문서 좌표(문서의 왼쪽 위 꼭짓점이 원점)|
|offsetX, offsetY|정수|이벤트가 발생한 요소의 상대 좌표(요소의 왼쪽 위 꼭짓점이 원점)|
|altKey|논리값|Alt가 눌렸는지 뜻하는 논리값|
|ctrlKey|논리값|Ctrl이 눌렸는지를 뜻하는 논리값|
|shiftKey|논리값|Shift가 눌렸는지를 뜻하는 논리값|
|detail|정수|이벤트의 자세한 정보 : 마우스 이벤트의 경우에는 클릭한 횟수|
|button|정수|눌린 마우스의 버튼(0: 왼쪽 버튼, 1: 휠 버튼, 2: 오른쪽 버튼)|
|relatedTarget|객체|mouseover 이벤트에서는 마우스가 떠난 노드<br>mouseout : 이벤트에서는 마우스가 들어온 노드|


#### 마우스 이벤트 객체에서 좌표를 담당하는 프로퍼티

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/15%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC/images/image1.png)


### 키보드 이벤트 객체

대표적인 이벤트로는 keydown, keypress, keyup 등이 있습니다. 

#### 키보드와 관련된 이벤트가 가진 고유의 프로퍼티

|프로퍼티|값의 타입|설명|
|-----|----|---------|
|altKey|논리값|Alt가 눌렀는지를 뜻하는 논리값|
|ctrlKey|논리값|Ctrl이 눌렀는지를 뜻하는 논리값|
|shiftKey|논리값|shift가 눌렸는지를 뜻하는 논릭밧|
|metaKey|논리값|Meta 키가 눌렸는지를 뜻하는 논리값(맥은 Commmand 키, 윈도는 시작 버튼)|
|key|문자열|눌린 키의 DOMString|
|keyCode|정수|눌린 키의 키 코드|
|code|문자열|눌린 키가 키보드에서 차지하는 물리적 위치를 뜻하는 문자열|

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>이벤트 객체 안의 키보드 관련 프로퍼티 표시하기</title>
	<script>
		window.onload = function() {
			document.addEventListener("keydown", showKey, false);
			var display = document.getElementById("display");
			function showKey(e) {
				var prop = ["altKey", "ctrlKey", "shiftKey", "metaKey", "key", "code", "keyCode"];
				var s = "";
				for (var i in prop) {
					s += "<br>" + prop[i] + ": " + e[prop[i]];
				}
				s += " -> " + String.fromCharCode(e.keyCode);
				display.innerHTML = s;
			}
		}
	</script>
</head>
<body>
	<p id="display"></p>
</body>
</html>
```
## 이벤트 전파
- Window 객체나 XMLHttpRequest 객체처럼 단독으로 존재하는 객체에서 이벤트가 발생하면 웹 브라우저는 해당 객체에 등록된 이벤트 처리기를 호출합니다. 
- 그러나 이벤트가 HTML 요소에 발생하면 그 요소는 물론 그 요소의 모든 조상 요소에 이벤트를 전파합니다.
- 결과적으로 그 모든 요소에 등롣된 이벤트 처리기가 호출됩니다. 

### 이벤트의 단계
HTML 요소에서는 클릭 같은 사용자 행위와 프로그램 코드가 이벤트를 발생시킵니다.<br>
이벤트가 발생한 요소는 <b>이벤트 타깃</b>이라고 합니다. 또한 이벤트를 발생시키는 것을 가리켜 <b>이벤트를 발사(Fire)</b>한다고 표현하기도 합니다.<br>
HTML에서는 부모 요소의 보더 박스 안에 자식 요소를 배치하며, 자식 요소는 부모 요소 안에 겹쳐진 상태로 표시됩니다. 이런 상태에서 자식 요소를 클릭하면 컴퓨터는 자식 요소를 클릭했는지 부모 요소를 클릭했는지 알아낼 방법이 없습니다. 그래서 요소에서 이벤트가 발생하면 DOM 트리의 관련 요소(즉, 그 요소의 조상 요소) 전체에 그 이벤트에 반응하는 이벤트 처리기나 이벤트 리스너가 등록되어 있는지를 확인하는 작업을 거칩니다. 그리고 등록된 함수가 있을 때는 그 함수를 실행하도록 만들어져 있습니다.<br>
구체적으로는 요소에서 이벤트가 발생하면 그 다음 단계에 접어들었을 때 그 이벤트를 다음 요소로 전파합니다.  그 단계에 해당 이벤트에 반응하는 이벤트 처리기나 이벤트 리스너를 발견하면 그들을 실행합니다.<br><br>

- <b>캡쳐링 단계</b>
	- 이벤트가 Window 객체에서 출발해서 DOM 트리를 타고 이벤트 타깃까지 전파됩니다.
	- 이단계 반응하도록 등록된 이벤트 리스너는 이벤트가 발생한 요소에 등록된 이벤트 처리기나 이벤트 리스너보다 먼저 실행됩니다.
	- 이 단계에서는 이벤트 타깃이 이벤트를 수신하기 전에 이벤트를 빼돌리는(캡쳐하는) 단계라는 뜻에서 캡쳐링 단계라는 이름이 붙었습니다.
	
- <b>타깃 단계</b>
	- 이벤트가 이벤트 타깃에 전파되는 단계입니다. 이벤트 타깃에 등록된 이벤트 처리기나 이벤트 리스너는 이 시점에 실행됩니다.
	
- <b>버블링 단계</b>
	- 이벤트가 이벤트 타깃에서 출발해서 DOM 트리를 타고 Window 객체까지 전파됩니다. 이 단계에 반응하도록 등록된 이벤트 리스너는 이벤트가 발생한 요소에 등록된 이벤트 처리기나 이벤트 리스너 다음에 실행됩니다. 
	- 이 단계는 이벤트가 마치 거품(버블)이 올라오는 것처럼 DOM 트리 아래에서 부터 위로 올라온다는 뜻에서 버블링 단계라는 이름이 붙었습니다. 
	- 단, focus와 blur 이벤트는 그 요소에만 필요한 이벤트이므로 버블링이 발생하지 않습니다.
	- 만약 이벤트 처리기를 등록했다면 이벤트 처리기는 타깃 단계와 버블링 단계에 모두 실행됩니다.
	- 이벤트 리스너는 실행할 단계를 선택할 수 있습니다. 이벤트 리스너의 useCapture가 true면 타깃 단계와 캡쳐링 단계일 때만 실행됩니다. useCapture가 false면 타깃 단계와 버블링 단계일 때만 실행됩니다.
	- 같은 요소의 같은 이벤트, 같은 단계에 반응하게끔 등록된 이벤트 처리기와 이벤트 리스너가 있다면 이벤트 처리기가 먼저 실행되며 이벤트 리스너가 등록된 순서에 따라 차례대로 실행됩니다. 
	- 또한 같은 요소의 같은 이벤트의 캡쳐링 단계와 버블링 단계 모두에 이벤트 리스너를 등록할 수 있습니다.
	

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/15%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC/images/image2.png)

```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
	
</head>
<body>
	<div id="outer">
		outer
		<div id="inner1">inner1</div>
		<div id="inner2">inner2</div>
	</div>
</body>
</html>
```

- 이때 div#inner2를 클릭하면 콘솔에 다음과 같은 결과가 표시됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/15%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC/images/image4.png)

- 이처럼 이벤트 리스너는 이벤트가 전파되는 순서에 따라 차례대로 실행됩니다.

### 이벤트 전파
- 자식 요소에서 발생한 이벤트는 부모 요소에도 전파됩니다. 이 때문에 의도하지 않은 동작을 할 때가 있습니다. 
- 이럴 때는 이벤트 전파를 취소해서 부모 요소가 이벤트 처리를 하지 않도록 만들 수 있습니다. 
- 또한 이벤트가 발생했을 때 실행되는 웹 브라우저의 기본 동작도 취소할 수 있습니다.

#### 이벤트 전파 취소하기 : stopPropagation 메서드

- 이벤트 리스너 안에서 이벤트 객체의 stopPropagation 메서드를 호출하면 이벤트가 그 다음 요소로 전파되는 것을 막습니다.
- 그러나 그 요소 객체의 그 이벤트에 등록한 다른 이벤트 리스너는 변함없이 실행됩니다. 
- stopPropagation 메서드의 사용법은 다음과 같습니다.

```javascript
event.stopPropagation();
```

#### 이벤트 전파의 일시 정지 : stopImmediatePropagation 메서드
- 이벤트 리스너 안에서 요소 객체의 stopImmediatePropagation 메서드를 호출하면 그 다음 요소로의 이벤트 전파가 일시적으로 멈춥니다.
- 또한 그 요소 객체의 그 이벤트를 등록한 다른 이벤트 리스너도 일시적으로 멈춥니다.
- stopImmediatePropagation 메서드의 사용법은 다음과 같습니다.

```javascript
event.stopImmediatePropagation();
```

#### 기본 동작 취소하기 : preventDefault 메서드

- 예를 들어 a 요소를 클릭하면 링크된 페이지로 이동합니다. 이처럼 웹 브라우저에 구현된 기본 동작을 취소하려면 preventDefault 메서드를 사용합니다.
- preventDefault 메서드의 사용법은 다음과 같습니다.

```javascript
event.preventDefault();
``` 

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<script>
			window.onload = function() {
				var anchor = document.getElementById("school");
				anchor.addEventListener("click", function(e) {
					if (!confirm("페이지를 이동하시겠습니까?")) e.preventDefault();
				}, false);
			};
		</script>
	</head>
	<body>
		<a id="school" href="http://school.yonggyo.com">강의실</a>
	</body>
</html>
```

- "페이지를 이동하시겠습니까?"라는 질문에 <b>확인</b> 버튼을 누르면 페이지가 이동하지만 <b>취소</b> 버튼을 누르면 페이지 이동이 취소됩니다. 
- 단, preventDefault 메서드로 취소할 수 없는 기본 동작도 있습니다. 해당 이벤트 객체의 cancelable 프로퍼티가 true면 취소할 수 있지만 false면 취소할 수 없습니다.

## 이벤트 리스너 안의 this 

### 이벤트 리스너 안의 this는 이벤트가 발생한 요소 객체

이벤트 리스너 안의 this 값은 '함수를 호출 할때 그 함수가 속해 있던 객체의 참조' 입니다. 

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			function Person(name) {
				this.name = name;
			}
			
			Person.prototype.sayHello = function() {
				console.log("Hello! " + this.name);
			}
			
			var person = new Person("Tom");
			var button = document.getElementById("button");
			button.addEventListener("click", person.sayHello, false);
		};	
	</script>
</head>
<body>
	<p>
		<button id="button">버튼</button>
	</p>
</body>
</html>
```

- <b>버튼</b>을 클릭하면 person.sayHello가 실행됩니다. person.sayHello의 this가 person을 가리킬 거라 기대하지만 실제로는 button 요소 객체를 가리킵니다.
- button 객체의 name 프로퍼티 값은 빈 문자열이므로 콘솔에 다음과 같은 결과가 표시됩니다.

```
Hello!
```

- 이처럼 이벤트 리스너 안의 this는 이벤트가 발생한 요소 객체를 가리킵니다.


### this가 원하는 객체를 가리키도록 설정하는 방법

#### bind 메서드를 사용하는 방법

- 함수 객체의 bind 메서드를 사용하면 그 함수가 실행될 때 this를 지정할 수 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			function Person(name) {
				this.name = name;
			}
			
			Person.prototype.sayHello = function() {
				console.log("Hello! " + this.name);
			}
			
			var person = new Person("Tom");
			var button = document.getElementById("button");
			button.addEventListener("click", person.sayHello.bind(person), false);
		};	
	</script>
</head>
<body>
	<p>
		<button id="button">버튼</button>
	</p>
</body>
</html>
```

- 그러면 this가 person을 가리키므로 콘솔에 표시되는 결과는 다음과 같이 바뀝니다.

```
Hello! Tom
```

#### 익명 함수 안에서 실행하는 방법
- 이벤트 처리기 또는 이벤트 리스너로 익명 함수를 넘기고 익명 함수 안에서 메서드를 호출하면 그 메서드의 this가 메서드를 참조하는 객체를 가리킵니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			function Person(name) {
				this.name = name;
			}
			
			Person.prototype.sayHello = function() {
				console.log("Hello! " + this.name);
			}
			
			var person = new Person("Tom");
			var button = document.getElementById("button");
			button.addEventListener("click", function() { person.sayHello() }, false);
		};	
	</script>
</head>
<body>
	<p>
		<button id="button">버튼</button>
	</p>
</body>
</html>
```
- 마찬기지로 this가 person을 가리키므로 콘솔에 표시되는 결과가 다음과 같이 바뀝니다.

```
Hello! Tom
```

#### 화살표 함수를 사용하는 방법

- ECMAScript 6부터 추가된 화살표 함수의 this는 함수를 초기화하는 시점에 결정됩니다.
- 이를 활용하면 객체 안의 메서드를 화살표 함수로 표기하면 그 안의 this가 생성된 객체를 가리킵니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			function Person(name) {
				this.name = name;
				this.sayHello = () => {
					console.log("Hello! " + this.name);
				};
			}
			
			var person = new Person("Tom");
			var button = document.getElementById("button");
			button.addEventListener("click", function(e) {
				person.sayHello();
			}, false);
		};	
	</script>
</head>
<body>
	<p>
		<button id="button">버튼</button>
	</p>
</body>
</html>
```

```
Hello! Tom
```

- 단 화살표 함수 표현식으로 정의한 함수를 객체의 prototype의 메서드로 추가하면 의도한 대로 동작하지 않습니다.

```javascript
// 객체의 프로토타입에 화살표 함수 표현식으로 추가한 함수의 this는 Window를 가리킨다. 
Person.prototype.sayHello = () => {
	console.log("Hello! " + this.name);
}
```
- 이 경우에는 화살표 함수 표현식으로 정의한 함수 안의 this가 Window 객체를 가리킵니다.

#### addEventListener의 두 번째 인수로 객체를 넘기는 방법
- addEventListener 메서드의 두 번째 인수는 함수지만 함수 대신 객체를 넘길 수 있습니다.
- 그리고 그 객체에 handleEvent 메서드가 있으면 그 메서드를 이벤트 리스너로 등록하도록 만들어져 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			function Person(name) {
				this.name = name;
			}
			
			Person.prototype.handleEvent = function() {
				console.log("Hello! " + this.name);
			}
			
			var person = new Person("Tom");
			var button = document.getElementById("button");
			button.addEventListener("click", person, false);
		};	
	</script>
</head>
<body>
	<p>
		<button id="button">버튼</button>
	</p>
</body>
</html>
```

- <b>버튼을 누르면 person.handleEvent 메서드가 실행</b>됩니다. handleEvent 메서드의 this는 생성된 객체에 고정되므로 콘솔에 다음과 같은 결과를 표시합니다.

```
Hello! Tom
```

## 이벤트 리스너에 추가적인 정보를 넘기는 방법

### 익명 함수 안에서 실행하기
- 익명 함수를 이벤트 리스너로 지정하고 이벤트 리스너안에 함수를 실행하면 그 함수에 추가적인 정보를 값으로 넘길 수 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			var box = document.getElementById("box");
			box.addEventListener("click", function(e) {
				changeBgColor(e, "red");
			}, false);
			
			function changeBgColor(e,color) {
				e.currentTarget.style.backgroundColor = color;
			}
		};
	</script>
</head>
<body>
	<div id="box">click me</div>
</body>
</html>
```

### 함수를 반환하는 함수를 이벤트 리스너로 등록하기

- 이벤트 객체를 인수로 받은 함수를 반환하는 함수를 정의해서 그 함수가 반환한 함수를 이벤트 처리기로 등록하는 방법이 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		window.onload = function() {
			var box = document.getElementById("box");
			box.addEventListener("click", changeBgColor("red"), false);
			function changeBgColor(color) {
				return function(e) {
					e.currentTarget.style.backgroundColor = color;
				};
			}
		};
	</script>
</head>
<body>
	<div id="box">click me</div>
</body>
</html>
```
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
	btn.onclick = changeColor();
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
			element.addEventListener("click", function() {
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




### 키보드 이벤트 객체
## 이벤트 전파


## 이벤트 리스너 안의 this 

## 이벤트 리스너에 추가적인 정보를 넘기는 방법

## 커스텀 이벤트
# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/1fAseZVsvQIrcNB_3FcxICN4Dmt--bsAt?usp=sharing)

# 웹 브라우저 객체

## 클라이언트 측 자바스크립트

### 웹 브라우저에서 자바스크립트가 하는 일
- (1) 웹 페이지의 Document 객체 제어(HTML 요소와 CSS 스타일 작업) 
	- DOM 이라는 API를 활용합니다.
- (2) 웹 페이지의 Window 객체 제어 및 브라우저 제어
	- 웹 브라우저에 내장된 다양한 객체를 활용하며, 대표적인 객체로 Location, Navigator 등이 있습니다. 
- (3) 웹 페이지에서 발생하는 이벤트 처리
- (4) Http를 이용한 통신 제어
	- XMLHttpRequest 객체를 활용합니다.

### 웹 브라우저에서 자바스크립트 실행 순서

웹 브라우저에서 HTML 문서를 분석하고 표시하는 프로그램을 가리켜 렌더링 엔진이라고 합니다. 렌더링 엔진은 다음과 같은 처리과정을 처러 HTML 문서의 구문을 분석하고 DOM 트리를 구축한 후에 HTML 안에 지정된 자바스크립트 코드를 실행합니다.

- (1) 웹 브라우저로 웹 페이지를 열면 가장 먼저 Window 객체가 생성됩니다. Window 객체는 웹 페이지의 전역 객체로 웹 페이지의 탭마다 생성됩니다.
- (2) Document 개체가 Window 객체의 프로퍼티로 생성되며 웹 페이지를 해석해서 DOM 트리의 구축을 시도합니다. Document 객체는 readyState 프로퍼티를 가지고 있으며, 이 프로퍼티는 HTML 문서의 해석 상태를 뜻하는 문자열이 저장됩니다. <b>readyState 프로퍼티</b>의 초깃값은 "<b>loading</b>"이라는 문자열입니다.
- (3) HTML 문서는 HTML 구문을 작성 순서에 따라 분석하며 Document 객체 요소와 텍스트 노드를 추가해 나갑니다.
- (4) HTML 문서 안에 script 요소가 있으면 script 요소 안의 코드 또는 외부 파일에 저장된 코드의 구문을 분석합니다. 그 결과 오류가 발생하지 않으면 그 시점에 코드를 실행합니다. 이때 script 요소는 동기적으로 실행됩니다. 즉, <b>script 요소의 구문을 분석해서 실행할 때는 HTML 문서의 구문 분석이 일시적으로 막히고, 자바스크립트 코드의 실행을 완료한 후에는 일시적으로 막혔던 HTML 문서의 구문 분석을 재개합니다.</b>
- (5) HTML 문서의 모든 내용을 읽은 후에 DOM 트리 구축을 완료하면 <b>document.readyState 프로퍼티</b> 값이 "<b>interactive</b>"로 바뀝니다.
- (6) 웹 브라우저는 Document 객체에 <b>DOM 트리 구축 완료를 알리기 위해 DOMContentLoaded 이벤트를 발생</b>시킵니다.
- (7) img 등의 요소가 이미지 파일 등의 외부 리소스를 읽어 들여야 한다면 이 시점에 읽어 들입니다.
- (8) 모든 리소스를 읽어 들인 후에는 <b>document.readyState</b> 프로퍼티 값이 "<b>complete</b>"로 바뀝니다.  마지막으로 웹 브라우저는 <b>Window 객체를 생대로 load 이벤트를 발생</b>시킵니다.
- (9) 이 시점부터 다양한 이벤트(사용자 정의 이벤트, 네트워크 이벤트)를 수신하며, 이벤트가 발생하면 이벤트 처리기가 비동기로 호출됩니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images1.png)

- 여기에서 window.load 이벤트는 모든 리소스를 읽어 들인 후에 발생합니다. 
- HTML 문서를 다 읽어 들이지 못한 상태에서 자바스크립트로 HTML 요소를 조작하면 조작할 요소 객체가 없으므로 자바스크립트 코드가 의도대로 동작하지 않습니다.
- 이런 상황을 만나지 않으려면 window.onload의 이벤트 처리기에 초기화 스크립트를 등록합니다. HTML 문서를 다 읽어 들인 후에 각 HTML 요소의 이벤트 처리기 등록과 같은 초기화 작업을 해야 하기 때문입니다.
- 여기에서 이미지 등의 외부 리소스를 읽어 들이는 시점이 DOM 트리 구축이 끝난 후라는 점에 유의하세요. load 이벤트는 이미지 등의 외부 리소스를 모두 읽어 들인 후에 발생하기 때문에 외부 리소스를 읽어 들이는 시간이 걸리는 만큼 사용자가 기다려야 하는 시간도 길어집니다.
- 이를 방지하려면 load 이벤트 대신 <b>DOMContentLoaded</b> 이벤트의 이벤트 처리기에 초기화 작업을 작성한 함수를 등록합니다. 

```javascript
document.addEventListener("DOMContentLoaded", function(e) {
	// 초기화 작업을 작성 
}, false);
```

이렇게 작성하면 사용자가 오래 기다리지 않고도 웹 페이지를 조작할 수 있게 됩니다.

#### async와 defer 속성
- <code>async</code>와 <code>defer</code> 속성은 script  요소의 논리 속성으로 HTML5부터 추가된 속성입니다. 
- 둘 다 src 속성을 가진 script 요소에는 적용할 수 있지만 인라인 스크립트에는 사용할 수 없습니다.
- 이들 속성을 사용하면 자바스크립트 코드를 실행할 때 HTML 구문 분석을 막지 않습니다. 
- 현재의 주요 웹브라우저에서는 두 개의 속성을 모두 지원하고 있습니다.

```javascript
<script async src="../scripts/sample.js"></script>
<script defer src="../scripts/sample.js"></script>
```

- script 요소에 <code>async</code> 속성을 설정하면 script 요소와 코드가 비동기적으로 실행됩니다. 즉, HTML 문서의 구문 분석 처리를 막지 않으며 script 요소의 코드를 최대한 빨리 실행합니다. 여러 개의 script 요소에 async 속성을 설정하면 다 읽어 들인 코드부터 비동기적으로 실행하므로 실행 순서가 보장되지 않습니다. 읽어 들이는 순서에 의존하는 script 요소에는 async 속성을 설정하지 말아야 합니다.

- <code>defer</code> 속성을 설정한 script 요소는 DOM 트리 구축이 끝난 후에 실행됩니다. DOM 구축이 끝난 시점에 실행되기 때문에 자바스크립트 코드로 요소 객체에 이벤트 처리기를 등록하는 등의 초기화 작업을 할 수 있습니다. 따아서 <b>defer 속성은 DOMContentLoaded 이벤트의 대안으로 활용할 수 있습니다.</b>


### CSS와 렌더링

지금까지 HTML 문서의 구문 분석, DOM 트리 구축, 자바스크립트 코드의 실행 절차를 설명했습니다. 그러나 렌더링 엔진이 처리하는 또 다른 중요한 과정이 있습니다. 바로 CSS를 읽어 들이는 작업과 구문 분석 , 그에 따른 웹페이지의 렌더링(Rendering) 작업입니다. 이 과정은 앞서 설명한 DOM 트리 구축과 함께 실행합니다. 

- (1) style 요소 안에 작성된 CSS 코드와 link 요소를 읽어 들인 CSS 코드를 CSS 파서가 분석합니다. 구문 분석이 끝난 CSS 코드는 스타일 규칙으로 만들어집니다. 여기에서 파서란 구문을 분석하는 소프트웨어를 말합니다.
- (2) HTML 코드로 만들어진 DOM 트리와 스타일 규칙을 바탕으로 런더 트리라는 또 다른 트리를 만듭니다. 렌더 트리에는 표시해야 하는 요소만 저장되며, 렌더 트리에 저장된 요소에는 스타일 정보를 추가합니다.
- (3) 렌더 트리가 만들어진 후에는 DOM의 각 노드 위치와 크기를 결정합니다.
- (4) 마지막으로 DOM의 각 노드를 렌더 트리의 스타일 정보를 바탕으로 그립니다. 앞의 과정은 HTML 문서를 다 읽어들인 후에 실행되지 않고 HTML 문서를 읽어 들이는 과정에서 단계적으로 실행됩니다.

렌더링 엔진은 사용자가 쾌적하게 사용할 수 있도록 콘텐츠를 최대한 빨리 화면에 표시하려고 합니다. 일부 콘텐츠의 해석이 끝나면 곧바로 그것을 표시하고, 그 사이에 네트워크로 나머지 콘텐츠를 방아서 처리해 나갑니다. 이 렌더 트리를 구축해서 배치하고 그리는 과정은 처음 리소스를 읽어 들일 때, 자바스크립트로 동적인 처리를 할 때, 사용자의 조작으로 HTML/CSS 코드가 바뀔 때마다 실행됩니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images2.png)


### 웹 브라우저 렌더링 엔진과 자바스크립트 엔진

현재 널리 사용되고 있는 웹브라우저의 렌더링 엔진과 자바스크립트 엔진은 다음과 같습니다.

|웹브라우저|제작사|렌더링 엔진|자바스크립트 엔진|
|----|----|----|----|
|크롬|구글|Webkit/Blink|V8|
|인터넷 익스플로러|마이크로소프트|Trident|JScript|
|엣지|마이크로소프트|EdgeHTML|Chakra|
|파이어폭스|모질라|Gecko|SpiderMonkey|
|사파리|애플|Webkit|JavaScriptCore|
|오페라|오페라|Webkit/Blink|V8|

### Window 객체

- 클라이언트 측 자바스크립트에서 가장 중요한 객체는 Window 객체입니다. 
- Window 객체는 전역 객체이며, 전역 변수는 Window 객체의 프로퍼티입니다. 
- 웹 브라우저에서 사용할 수 있는 다양한 객체가 모두 Window 객체의 프로퍼티입니다. 
- Window 객체의 프로퍼티와 메서드는 window 프로퍼티로 참조할 수 있습니다.  예를 들어 Document 객체는 다음처럼 참조할 수 있습니다. 

```javascript
window.document
```

-  또한 window 부분은 생략할 수 있어서 다음처럼 단순히 프로퍼티 이름만 사용해서 참조할 수도 있습니다.

```javascript
document
```

#### Window 객체의 주요 프로퍼티

|프로퍼티|설명|
|-----|-----------|
|screen|Screen 객체를 가리킨다.|
|document|Document 객체를 가리킨다.|
|location|Location 객체를 가리킨다.|
|navigator|Navigator 객체를 가리킨다.|
|history|History 객체를 가리킨다.|
|event|Event 객체를 가리킨다.|
|console|Console 객체를 가리킨다.|
|window|Window 객체를 가리킨다.|
|self|Window 객체를 가리킨다(window 프로퍼티와 같음).|
|parent|해당 창이 프레임 안의 창이면 부모 window 객체를 가리킨다. 그렇지 않으면 자기 자신을 가리킨다.|
|top|해당 창이 프레임 안의 창이면 top 레벨의 window 객체를 가리킨다. 그렇지 않으면 자기 자신을 가리킨다.|
|opener|현재 창을 생성한 창을 가리킨다.|
|frames[]|창에 포함된 각 프레임을 가리키는 참조가 저장된 배열|
|closed|현재 창이 닫혀 있는지를 뜻하는 논리값|
|name|현재 창의 이름(읽기/쓰기 가능)|
|status|상태 표시줄의 텍스트(읽기/쓰기 가능)|
|screenX, screenY|컴퓨터 화면의 왼쪽 윗부분을 기준으로 했을 때 브라우저 왼쪽 위 꼭지점의 수평 위치와 수직 위치, 인터엣 익스플로러는 지원하지 않는다.|
|screenLeft, screenTop|각각 screenX, screenY와 같다. 파이어폭스는 지원하지 않는다.|
|innerHeight, innerWidth|창 안쪽의 높이와 너비(스크롤 막대가 차지하는 영역은 제외함)|
|outerHeight, outerWeight|창 바깥의 높이와 너비(스크롤 막대가 차지하는 영역을 포함)|
|scrollX, scrollY|수평 방향과 수직 방향으로 HTML 문서가 스크롤되는 픽셀의 수|
|pageXOffset, pageYOffset|각각 scrollX, scrollY와 같다.|


#### Window 객체의 주요 메서드

|메서드|설명|
|-----|-----------|
|alert(message)|경고 대화상자를 표시한다.|
|prompt(message, default)|입력 대화상자를 표시한다.|
|confirm(question)|확인 대화상자를 표시한다.|
|setTimeout(callback, interval)|interval(ms)마다 callback을 호출한다.|
|setInterval(callback, delay)|delay(ms) 후에 callback을 호출한다.|
|clearTimeout(timeoutId)|timeId의 setTimeout을 취소한다.|
|clearInterval(intervalId)|intervalId의 setInterval을 취소한다.|
|blur()|창에서 포커스를 제거한다.|
|focus()|창에 포커스를 준다.|
|close()|창을 닫는다.|
|open()|새로운 창을 연다.|
|moveBy(x, y)|창을 수평으로 x, 수직으로 y만큼 이동한다.|
|moveTo(x, y)|창을 좌표 (x, y)로 이동한다.|
|resizeBy(width, height)|창의 너비를 width, 높이를 height만큼 키운다.|
|resizeTo(width, height)|창의 너비를 width, 높이를 height로 설정한다.|
|scrollBy(x, y)|스크롤을 수평으로 x, 수직으로 y만큼 이동한다.|
|scrollTo(x, y)|스크롤을 좌표 (x,y)로 이동한다.|
|print()|창 안에서 문서를 인쇄하는 대화상자를 연다.|

* * * 
## Location 객체
- Location 객체는 창에 표시되는 URL을 관리합니다.
- Location 객체는 window.Location 또는 location으로 참조할 수 있습니다.
- document.location 또한 Location 객체를 참조합니다.
- Location 객체의 프로퍼티는 수정할 수 있으며, 그 값을 수정하면 필요에 따라 웹 서버에 수정을 요청하고 응답에 따라 창을 갱신합니다.

#### Location 객체의 프로퍼티

```
http://www.example.com:80/test/index.html?q=value#anchor
```

|프로퍼티|설명|예|
|----|------|-------|
|hash|앵커 부분|#anchor|
|host|호스트 이름:포트번호|www.example.com:80|
|hostname|호스트 이름|www.example.com|
|href|전체 URL|http://www.example.com:80/test/index.html?q=value#anchor|
|pathname|웹 사이트의 루트를 기준으로 한 상대 경로|/test|
|port|포트번호|80|
|protocol|프로토콜|http|
|search|질의 문자열|?q=value|

#### Location 객체의 메서드

|메서드|설명|
|----|-------|
|assign(url)|url이 가리키는 문서를 읽는다. 웹 브라우저의 이력에 남는다.|
|reload()|문서를 다시 읽어 들인다.|
|replace(url)|url로 이동한다. 웹 브라우너의 이력에 남지 않는다.|
|toString()|location.href 값을 반환한다.|

- 다음 두 코드는 모두 해당 URL이 가리키는 문서를 읽어 들입니다.

```javascript
location.href="https://naver.com");
location.assign("https://naver.com")
```

- 두 코드는 모두 읽어 들이기 이전의 URL을 이력을 남기므로, '뒤로 가기' 버튼을 사용해서 되돌아갈 수 있습니다.
	
- URL이 가리키는 문서를 읽어 들일 때 이력을 남기지 않으므로 replace 메서드를 사용합니다.

```javascript
location.replace("https://naver.com");
```

이때는 읽어 들이기 이전의 URL이 이력에서 삭제되므로 '뒤로 가지' 버튼으로 되돌아갈 수 없습니다.
	
- URL에는 상대 경로를 지정할 수도 있습니다. 상대 경로를 지정하면 이전 웹 페이지의 사이트 루트에 대한 상대 URL로 인식합니다. 다음 코드는 3초 후에 같은 사이트의 다른 페이지로 이동(리다이렉트)gksms dPdlqslek.

```javascript
setTimeout(function() {
	location.replace("/book/bookList.asp");
}, 3000);
```

- reload 메서드를 사용하면 현재의 페이지를 다시 읽어 들일 수 있습니다.

```javascript
location.reload();
```

- hash 프로퍼티에 HTML 요소의 id 속성 값을 대입하면 HTML 요소로 스크롤합니다.

```javascript
location.hash = "#header";  // id = "header"인 HTML 요소로 스크롤하기
```

- search 프로퍼티의 값을 바꾸면 서버에 질의 문자열을 보냅니다.

```javascript
location.search = "some data";
// URL 끝에 "?some%20data"를 덧붙여 서버에 보낸다.
```

이때 search 프로퍼티에 저장된 값은 URL 인코딩되어 서버로 전송됩니다.
	
* * * 
## History 객체
- History 객체는 창의 웹 페이지 열람 이력을 관리합니다. 
- History 객체는 window.history 또는 history로 참조할 수 있습니다.

#### History 객체의 프로퍼티

|프로퍼티|설명|
|-----|--------|
|length|현재 세션의 이력 개수(읽기 전용)|
|scrollRestoration|웹 페이지를 이동한 후에 동작하는 웹 브아루저의 자동 스크롤 기능을 켜거나 끄는 값. "auto" 또는 "manual"이 들어갈 수 있다.|
|state|pushState와 replaceState 메서드로 설정한 state 값(읽기 전용)|


#### History 객체의 메서드

|메서드|설명|
|------|--------|
|back()|창의 웹 페이지 열람 이력을 되돌린다.|
|forward()|창의 웹 페이지 열람 이력을 하나 진행한다.|
|go(number)|창의 웹페이지 열람 이력을 number만큼 진행한다. number 값이 음수면 그 만틈 되돌린다.|
|pushState(state, title, url)|창의 웹 페이지 열람 이력을 추가한다. 페이지는 이동하지 않는다.|
|replaceState(state, title, url)|현재 창의 열람 이력을 수정한다.|

- 웹 페이지 열람 이력을 진행하거나 되돌아갈 때는 back과 forward 메서드를 사용합니다.

```javascript
history.back();  // 웹 페이지 열람 이력을 하나 되돌아간다.
history.forward(); // 웹 페이지 열람 이력을 하나 진행한다.
```

- 건너뛸 횟수를 지정해야 웹 페이지 이력을 진행하거나 되돌아갈 때는 go 메서드를 사용합니다.

```javascript
history.go(-3);  // 웹 페이지 열람 이력을 세 개 되돌아간다
history.go(2);  // 웹 페이지 열람 이력을 두 개 진행한다.
```

- 페이지를 전환하지 않고 웹 페이지 열람 이력만 추가하고자 할 때는 pushState 메서드를 사용합니다.

```javascript
history.pushState(null, null, "page2.html");
```
- pushState 메서드는 인수를 세 개 받습니다.
	- **state** :  새롭게 추가되는 웹 페이지 열람 이력과 연결할 객체. pushState 이벤트의 state 프로퍼티 참조를 할 수 있다.
	- **title** : 새로운 열람 웹 페이지 이력을 가리키는 문자열
	- **url** : 새로운 열람 이력의 URL. pushState 메서드를 호출하더라도 이 URL이 가리키는 웹 페이지를 읽어 들이지 않는다. 이 인수는 생략할 수 있다. 이 인수를 생략하면 현재의 URL이 설정된다.

<br>
<br>

- 똑같은 웹 페이지 위에서 열람 이력을 수정해도 pushstate 이벤트는 발생합니다. poststate 이벤트는 웹 브라우저의 뒤로가기, 앞으로 가기 버튼을 눌었을 때 또는 back, forward, go 메서드를 호출할 때 발생합니다.

- pushState 메서드는 주로 Ajax방식으로 웹 페이지를 그릴 때 사용합니다. Ajax 방식은 웹 페이지를 이동하지 않고 페이지 내용만 수정합니다. 이때 웹 페이지 주소를 바꾸면 웹 페이지 열람 이력에 변경된 내용이 추가되지 않은 상태라 뒤로가기 버튼을 사용해서 되돌아갈 수 없습니다. 웹 페이지 내용이 바뀌었을 때 pushState 메서드로 웹 페이지 열람 이력에 저장해 두면 뒤로가기 버튼을 사용해서 되돌아갈 수 있습니다.

* * * 
## Naviator 객체

- Navigator 객체는 스크립트가 실행 중인 웹 브라우저 등의 애플리케이션 정보를 관리합니다.
- Navigator 객체는 window.navigator 또는 navigator로 참조할 수 있습니다. 

#### Navigator 객체의 주요 프로퍼티

|프로퍼티|설명|
|-----|--------|
|appCodeName|웹 브라우저의 내부 코드 네임. 정확하지 않음|
|appName|웹 브라우저 이름. 정확하지 않음|
|appVersion|웹 브라우저 버전. 정확하지 않음|
|geolocation|단말기의 물리적 위치를 관리하는 Geolocation 객체|
|language|웹 브라우저의 UI 언어("en", "en-US", "ko", "ko-KR", "fr" 등)|
|mimeTypes[]|웹 브라우저가 지원하는 MIME 타입 목록을 저정한 MimeTypeArray 객체|
|onLine|웹 브라우저가 네트워크에 연결되어 있는지를 뜻하는 논리값|
|platform|웹 브라우저의 플랫폼. 윈도는 "win32" 맥은 "MacIntel"|
|plugins|웹 브라우저에 설치된 플러그인 목록을 가리키는 Plugin 객체의 배열|
|userAgent|웹 브라우저가 USER-AGENT 헤더에 보내는 문자열|

#### Navigator 객체의 메서드

|메서드|설명|
|-----|--------|
|javaEnabled()|Java를 사용할 수 있는지 뜻하는 논리값을 반환|
|getUserMedia()|단말기의 마이크와 카메라에서 audio와 video 스트림을 반환|
|registerContentHandler(mimeType, uri, title)|웹 사이트를 특정 MIME 타입과 연결|
|registerContentHandler(mimeType, uri, title)|웹 사이트를 특정 프로토콜과 연결|
|vibrate()|단말기를 진동시킴|

- Navigator 객체는 브라우저 테스트에 활용합니다. 브라우저 테스트에 사용할 수 있는 Navigator 객체의 프로퍼티는 userAgent뿐이며 다른 프로퍼티에서는 웹 브라우저의 정확한 정보를 얻을 수 없습니다. 
- 브라우저 테스트를 할 때는 userAgent 프로퍼티의 문자열을 정규 표현식으로 분석해서 웹 브라우저의 버전과 플랫폼 등의 정보에 따라 대응합니다.

* * * 
## Screen 객체
- Screen 객체는 화면(모니터) 크기와 색상 등의 정보를 관리합니다.
- Screen 객체는 window.screen 또는 screen으로 참조할 수 있습니다.

#### Screen 객체의 주요 프로퍼티 

|프로퍼티|설명|
|-----|--------|
|availTop|사용할 수 있는 화면의 첫 번째 픽셀의 y좌표|
|availLeft|사용할 수 있는 화면의 첫 번째 픽셀의 x좌표|
|availHeight|사용할 수 있는 화면의 높이|
|availWidth|사용할 수 있는 화면의 너비|
|colorDepth|화면의 색상 심도(비트 수) - 약 1,678만 색상이면 24|
|pixelDepth|화면의 비트 심도(비트 수) - 약 1,678만 색상이면 24(IE9는 제공하지 않음)|
|height|화면 높이|
|width|화면 너비|
|orientation|화면 방향|

> colorDepth와 pixelDepth 프로퍼티는 픽셀 한 개가 표시할 수 있는 색상을 표현하기 위한 정보량을 담고 있으며, 그 단위는 비트입니다.

- 사용할 수 있는 화면이란 화면에서 작업 표시줄 등을 제외한 나머지 부분을 뜻합니다. 
- Screen 객체를 사용하면 웹 페이지가 어떤 크기의 단말기에 표시되고 있는지를 알 수 있습니다.
- 예를 들어 스마트 폰 등의 작은 화면에 표시할 때 크기가 작은 글꼴을 사용하거나 이미지를 작게 표시하는 등의 대응이 필요할 때 사용할 수 있습니다.

* * * 

## 창 제어하기

- 웹 브라우저는 일반적으로 여러 개의 창과 탭을 표시합니다. 각각의 창과 탭은 별도의 <b>브라우징 컨텍스트</b>(사용자에게 표시되는 환경)를 제공합니다. 그리고 각각의 브라우징 컨텍스트는 별도의 Window 객체를 가집니다.

- 각 브라우징 컨텍스트(창 또는 탭)는 상호 간의 독립적이며 다른 브라우징 컨텍스트에 간섭할 수 없습니다. 그러나 이 조건에는 예외가 있습니다. 그것은 브라우징 컨택스트를 열 때입니다. 이 경우의 부모 브라우징 컨텍스트와 자식 브라우징 컨텍스트는 상호작용이 가능합니다. 

### 창 여닫기

새로운 창 또는 탭을 열 때는 open 메서드를 사용합니다. open 메서드의 사용법은 다음과 같습니다.

```javascript
var w = open(url, 창의 이름, 옵션);
```
- open 메서드가 받는 인수의 의미는 다음과 같습니다.
	- <b>url</b> : 새롭게 여는 창이 읽어 들이는 문서의 URL. 이 인수를 생략하면 빈 페이지가 표시된다.
	- <b>창의 이름</b> : 새로운 창의 이름. 이미 이름이 같은 창이 있다면 새로 열지 않고 그 창에 표시된다.  이 인수를 생략하면 이름이 없는 창을 연다.
	- <b>옵션</b> : 새로운 창의 설정 값(창의 크기 등)을 쉼표로 연결해서 표기한다. 생략하면 기본 크기로 표시된다. 탭을 표시할 수 있는 웹 브라우저에서는 새로운 탭으로 표시된다.

- open 메서드는 새로운 창이 Window 객체를 반환합니다.
- 세 번째 인수의 <b>옵션</b>은 옵션의 <b>이름=값</b>을 쉽표로 연결한 문자열이며, 사용할 수 있는 <b>옵션</b>의 목록은 다음과 같습니다.


#### open 메서드로 설정할 수 있는 옵션의 이름

|옵션의 이름|설명|
|-----|---------|
|width|창 너비|
|height|창 높이|
|location|주소 표시줄 표시 여부(yes 또는 no)|
|scrollbars|스크롤 막대 표시 여부(yes 또는 no)|
|toolbar|도구 모음 표시 여부(yes 또는 no)|
|status|상태 표시줄 표시 여부(yes 또는 no)|
|menubar|메뉴 막대 표시 여부(yes 또는 no)|

열린 창을 닫을 때는 close 메서드를 사용합니다. close 메서드의 사용법은 다음과 같습니다.

```javascript
w.close();
```

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>top page</title>
	<script>
		window.onload = function() {
			var w;
			document.getElementById("open").onclick = function() {
				w = open("newpage.html", "new page", "width=400, height=300");
			};
			document.getElementById("close").onclick = function() {
				if (w) w.close();
			};
		};
	</script>
</head>
<body>
	<p>
		<input type="button" value="open" id="open">
		<input type="button" value="close" id="close">
	</p>
</body>
</html>
```

### 창 제어하기

- open 메서드로 연 창은 위치와 크기를 바꾸거나 스크롤할 수 있습니다. 위치를 변경하려면 moveBy와 moveTo 메서드를 사용합니다.

```javascript
w.moveBy(10, 20);  // 수평 방향(오른쪽)으로 10px 이동하고 수직 방향(아래쪽)으로 20px 이동
w.moveTo(100, 150);  // 창의 왼쪽 좌표(100, 150)로 이동
```

- 크기를 변경하려면 resizeBy와 resizeTo 메서드를 사용합니다.

```javascript
w.resizeBy(10, 20);  // 너비를 10px, 높이를 20px 키운다
w.resizeTo(200, 150);  // 너비를 200px, 높이를 150px로 설정한다.
```

- 창에 표시되는 페이지를 스크롤하려면 scrollBy와 scrollTo를 사용합니다.

```javascript
w.scrollBy(0,100);  // 아래쪽으로 100px 스크롤한다.
w.scrollTo(0,0);  // 시작 위치로 이동한다.
```

### 다른 창 참조하기

- 부모 창과 부모 창이 open 메서드로 연 자식 창은 서로의 Window 객체를 참조할 수 있습니다. 그러면 상대 프로퍼티와 메서드를 참조할 수 있습니다. 
- 그러나 상호 참조할 수 있는 경우는 동일 출처 정책을 만족할 때로 한정됩니다. 부모 창이 자식 창의 Window 객체를 참조할 때는 open 메서드가 반환한 값을 사용합니다.

```javascript
var w = open("newpage.html", "new page", "width=400, height=300");
w.document.body.style.backgrounColor = "green";  // 자식 창의 배경색을 녹색으로 만듬
```

- 자식 창이 부모 창의 Window 객체를 참조할 떄는 자식 창의 Window 객체에 있는 opener 프로퍼티를 사용합니다.

```javascript
opener.document.body.style.backgroundColor = "red"; //  부모 창의 배경색을 빨간색으로 만듬
```
- 이 코드에서 등장한 document.body.style.backgroundColor는 문서의 body 요소의 배경색을 가리키는 CSS의 인라인 스타일입니다. 

> <b>동일 출처 정책</b><br>동일 출처 정책(same origin policy)이란 웹 페이지 위에서 동작하는 프로그램(자바스크립트 등) 출처와 그 프로그램이 읽으려고 시도하는 데이터 출처가 서로 다를 경우에 리소스 간의 상호작용을 제한하는 정책을 말합니다.<br><br>여기에서 말하는 출처(origin)란 리소스 URL이 지닌 세 가지 식별 정보를 뜻합니다. 식별 정보는 프로토콜, 포트 번호, 호스트 이름으로 구성됩니다.  즉, 웹에서는 다음 세 가지 조건을 모두 충족하는 리소스를 가리켜 출처가 같은 리소스라고 합니다.<br><br>(1) 프로토콜이 같다.<br>(2) 포트 번호가 같다.<br>(3) 호스트 이름이 같다.<br><br>특히 다른 도메인(호스트 이름)에 있는 리소스는 출처가 다르며, 기본적으로 출처가 다른 데이터는 읽어 들일 수 없습니다.<br><br>동일 출처 정책은 클라이언트 측 프로그램의 부담을 줄이면서 악성 프로그램의 실행을 막을 목적으로 마련되었습니다. 이는 클라이언트 측 프로그램잉을 할 때 보안성을 확보하기 위한 중요한 개념으로 자리 잡았습니다.


* * * 
## Document 객체
- Document 객체는 창에 표시되고 있는 웹 페이지를 관리합니다. 
- 이 객체로 웹 페이지의 내용물인 DOM 트리를 읽거나 쓸 수 있습니다. 
- Document 객체는 클라이언트 측 자바스크립트에서 가장 중요한 객체입니다. 


####  Document 객체의 주요 프로퍼티
|프로퍼티|설명|
|-----|--------|
|characterSet|문서에 적용된 문자 인코딩(읽기 전용)|
|cookie|문서의 cookies를 쉼표로 연결한 문자열|
|domain|문서의 도메인(읽기 전용)|
|lastModified|문서를 마지막 수정한 날(읽기 전용)|
|location|window.location 프로퍼티와 마찬가지로 Location 객체를 가리킴|
|readyState|문서를 읽어들인 상태읽기(읽기 전용)|
|referrer|문서에 링크된 페이지 URL(읽기 전용)|
|title|문서 제목|
|URL|문서 URL(읽기 전용)|


#### Document 객체 주요 메서드

|메서드|설명|
|-----|--------|
|close()|document.open() 메서드로 연 문서를 닫는다.|
|open()|문서를 쓰기 위해 연다.|
|write(text)|document.open() 메서드로 연 문서에 기록한다.|
|writeln(text)|document.open() 메서드로 연 문서에 기록하고 개행 문자를 추가한다.|


* * * 
## 문서 제어

### DOM 트리 
- 웹 페이지의 내용은 Document 객체가 관리합니다. 웹 브라우저가 웹 페이지를 읽어 들이면 렌더링 엔진은 웹 페이지의 HTML 문서 구문을 해석하고 Document 객체에서 문서 내용을 관리하는 DOM 트리라고 하는 객체의 트리 구조를 만듭니다. 구체적으로 다음 HTML 문서를 예로 들어 설명하겠습니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>간단한 HTML문서</title>
		<meta  charset="UTF-8">
	</head>
	<body>
		<h1>DOM이란?</h1>
		<p><strong>Document Object Model</strong>의 줄입말입니다.</p>
	</body>
</html>
```

- 웹 브라우저가 HTML 문서를 읽어 들이면 다음 그림처럼 Document 객체로 시작하는 DOM 트리가 만들어집니다.
- DOM 트리를 구성하는 객체 하나를 노드(Node)라고 합니다. 다음 세 종류의 노드가 기본적인 노드입니다.
	- <b>문서 노드</b> : 전체 문서를 가리키는 Document 객체, document로 참조할 수 있다.
	- <b>HTML 요소 노드</b> : HTML 요소를 가리키는 객체(이 절에서는 요소 객체로 줄여서 부름)
	- <b>텍스트 노그</b> : 텍스트를 가리키는 객체(이 절에서는 텍스트 객체로 줄여서 부름)
	

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images3.png)

	
- HTML은 요소 뒤에 공백 문자(공백 문자, 탭 문자, 줄 바꿈 문자 등)가 여러 개 있어도 무시합니다. 
- 그러나 DOM 트리는 요소 앞뒤에서 연속적인 공백 문자를 발견하면 텍스트로 취급하여 텍스트 노드로 생성합니다. 이렇게 공백 문자만으로 구성된 텍스트 노드를 공백 노드라고 합니다. 
- 단, html 요소 안에 있는 첫 공백 문자와 마지막 공백 문자에 대해서는 공백노드를 생성하지 않습니다.

- DOM 트리는 HTML 문서 안의 요소와 텍스트 사이의 포함관계를 표현합니다. 이 포함 관계는 부모-자식 관계라고 부르기도 합니다.  
- 노드 A 바로 아래에 노드 B가 있을 때 A를 B의 <code>부모 노드</code>라고 하고 B를 A의 <code>자식 노드</code>라고 합니다. 
- 같은 부모를 가진 같은 레벨의 노드를 <code>형제 노드</code>라고 합니다. 또한 그 노드가 포함된 노드는 그 노드의 자식 노드라고 하고, 그 노드를 포함한 노드는 <code>조상 노드</code>라고 합니다. 
- Document 객체는 모든 노드의 조상 노드이며 DOM트리의 <code>루트</code>입니다.

### 노드 객체의 프로퍼티

#### 노드 객체의 DOM 트리 계층을 표현하는 프로퍼티

|프로퍼티 이름|설명|
|-----|-----------|
|parentNode|이 노드의 부모 노드를 참조한다. Document 객체의 부모 노드는 null이 된다.|
|childNodes|이 노드의 자식 노드의 참조를 저장한 유사 배열 객체(NodeList)|
|firstChild|이 노드의 첫 번째 자식 노드, 자식 노드가 없을 때는 null이 된다.|
|lastChild|이 노드의 마지막 자식 노드, 자식 노드가 없을 때는 null이 된다.|
|nextSibling|이 노드의 같은 부모 노드를 가진 이 노드 다음의 형제 노드|
|previousSibling|이 노드와 같은 부모 노드를 가진 이 노드 이전의 형제 노드|
|nodeType|노드 유형을 뜻하는 숫자(1: 요소 노드, 3: 텍스트 노드, 9: Document)|
|nodeValue|텍스트 노드의 텍스트 콘텐츠, 요소 노드에서는 null이 된다.|
|nodeName|요소 노드는 대문자로 바뀐 요소 이름이 들어간다. 텍스트 노드는 "#text"가 들어간다.|

- 노드가 가진 이런 프로퍼티를 활용하면 Document 객체를 타고 내려가 특정 요소 객체나 텍스트 객체를 참조할 수 있습니다. 
- 예를 들어 앞서 예제인 HTML 문서의 body 요소 객체는 다음과 같은 다양한 방법으로 참조할 수 있습니다.

```javascript
document.childNodes[0].childNodes[2]
document.firstChild.lastChild
```

- 하지만 이러한 참조 방법은 요소와 요소 사이에 있는 공백 문자의 영향을 받습니다. 
- 공백 문자의 유무에 따라 요소 객체를 참조하는 방법을 바꿔야 해서 다루기가 불편합니다. 따라서 노드 객체를 참조할 떄는 앞에 작성한 코드처럼 Document 객체부터 자식 노드의 프로퍼티를 순서대로 타고 내려가는 경우가 드뭅니다. 보통은 요소의 id와 class 속성을 사용하여 원하는 요소 객체를 직접 가져옵니다.

#### HTML 요소의 트리

- HTML 문서 안의 요소에만 관심이 있는 경우도 childNodes와 firstChild로 노드를 참조하면 앞에서 이야기한 것처럼 공백 문자의 유무에 따라 검색 방법을 바꿔야 해서 불편합니다. 
- 그래서 각 노드에서는 DOM 트리 안의 텍스트 노드를 무시하고 HTML 문서에서 요소의 계층 구조만 가져오기 위한 프로퍼티가 마련되어 있습니다.


|프로퍼티 이름|설명|
|-----|-----------|
|children|이 요소의 자식 요소 참조를 저장한 유사 배열 객체(NodeList)|
|parentElement|이 요소의 부모 요소 객체를 참조한다.|
|firstElementChild|이 요소의 첫 번째 자식 요소 객체를 참조한다.|
|lastElementChild|이 요소의 마지막 자식 요소 객체를 참조한다.|
|nextElementSibling|이 요소와 같은 부모를 가진 이 요소 다음 형제 요소 객체를 참조한다.|
|previousElementSibling|이 요소와 같은 부모를 가진 이 요소 이전의 형제 요소 객체를 참조한다.|
|childElementCount|자식 요소 개수 children.length와 같다.|

- 예를 들어 앞서 예제인 HTML 문서의 body 요소 객체는 다음과 같은 방법으로 참조할 수 있습니다.

```javascript
document.children[0].children[1]
document.firstElementChild.lastElementChild
```

#### 주요 노드 객체

|노드의 종류|생성자|nodeName|nodeValue|nodeType|
|-----|-----|-----|-----|----|
|문서 노드|HTMLDocument|"#document"|null|9|
|요소 노드|HTMLElement|요소 이름(대문자)|null|1|
|텍스트 노드|Text|"#text"|텍스트|3|
|주석노드|Comment|"#comment"|주석 내용|8|
|속성노드|Attr|속성 이름|속성 값|2|

- 앞서 예제인 HTML 문서에서 일부 노드 생성자와 nodeName, nodeValue, nodeType 값을 출력해 보면 다음 코드의 주석과 같은 결과가 나옵니다.

```javascript
console.log(document.constructor);  // -> HTMLDocument() { ... }
console.log(document.nodeName, document.nodeValue, document.nodeType); 
// -> #document null 9
var element = document.children[0].children[1].firstElementChild;
console.log(element.constructor); // -> HTMLHeadingElement() { ... }
console.log(element.nodeName, element.nodeValue, element.nodeType); 
// -> H1 null 1
```


### 자바스크립트로 웹 페이지 제어하기

- 자바스크립트를 사용하면 DOM 트리의 노드 객체를 가져와서 제어할 수 있습니다. 또한 자바스크립트로 스타일 규칙을 가져와서 제어할 수도 있습니다. <b>렌더링 엔진은 DOM 트리와 스타일 규칙이 바뀔 때마다 렌더 트리를 다시 구성해서 웹페이지를 다시 그립니다.</b>

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images4.png)


- 웹페이지를 사용자가 조작하거나 자바스크립트 코드로 DOM 트리나 스타일을 수정하면 렌더링 엔진은 그때마다 화면을 다시 렌더링합니다.
- 렌더 트리를 다시 구성하고 다시 렌더링하는 처리는 일반적으로 시간이 많이 걸리는 작업입니다. 따라서 이러한 처리가 자주 발생하면 렌더링이 원활하지 않을 가능성이 있습니다. 
- 웹 브라우저는 이러한 상황을 피하기 위해 렌더링 처리 횟수를 가능한 줄이는 최적화 처리를 합니다. 예를 들어 스타일의 수정 요청이 여러 번 반복되면 요청을 대기열에 모아 두고 마지막에 한꺼번에 처리합니다.

## 노드 객체 가져오기
자바스크립트로 HTML 요소를 제어하려면 그 전에 제어하고자 하는 요소 객체를 먼저 가져와야 합니다. 물론 Document 객체의 DOM 트리를 타고 올라가 요소 객체를 가져오는 방법도 있지만 Document 객체는 이보다 편리하게 요소 객체를 가져올 수 있는 메서드가 마련되어 있습니다.

### id 속성으로 노드 가져오기
- HTML 문서의 요소에는 id 속성을 지정할 수 있습니다.
- id 속성 값은 문서에서 유일한 값이어야 합니다. 따라서 id 속성 값으로 요소 하나를 가리킬 수 있습니다.

```javascript
document.getElementById(id 값);
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div id="first">첫 번째 div</div>
		<div id="second">두 번째 div</div>
		<div id="third">세 번째 div</div>
		<script>
			var element = document.getElementById("second");
			element.innerHTML = "여기를 수정함"; // 두 번째 div 요소의 내용을 수정한다.
		</script>
	</body>
</html>
```


### 요소 이름으로 노드 가져오기
- 인수로 넘긴 문자열과 같은 이름을 가진 태그 목록을 가져올 수 있습니다. 인수로는 태그 이름을 넘깁니다.
- 태그 이름은 대소문자를 구별하지 않습니다.
- getElementsByTagName 메서드는 반환값이 복수개의 요소 입니다. 이것은 일반적인 HTML 문서에는 이름이 같은 태그가 많이 사용되기 때문입니다. 
- getElementsByTagName 메서드는 NodeList 객체를 반환합니다. NodeList 객체는 유사 배열 객체이며 읽기 전용입니다. 
- 요소 이름 대신 와일드카드(\*)를 지정할 수도 있습니다. 이 경우에는 NodeList에 HTML 문서 안의 모든 요소를 담아서 반환합니다.

```javascript
document.getElementsByTagName(요소의 태그 이름);
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div id="first">첫 번째 div</div>
		<div id="second">두 번째 div</div>
		<div id="third">세 번째 div</div>
		<script>
			var elements = document.getElementsByTagName("div");
			elements[2].innerHTML = "여기릴 수정함";  // 세 번째 div 요소의 내용을 수정한다.
		</script>
	</body>
</html>
```

> <b>NodeList 객체</b><br>
getElementsByTagName, getElementsByName, getElementsByClassName 메서드는 NodeList 객체를 반환합니다. 이는 length 프로퍼티를 가진 유사 배열 객체이며 for 문 등을 사용해서 순회할 수 있습니다.<br><br>
NodeList 객체는 '살아 있는' 상태를 가리킵니다. 즉, 이 객체는 특정 시점의 정적인 상태를 표현하는 것이 아니라 HTML 문서의 변화에 따라 동적으로 바뀝니다. 예를 들어 div 요소가 없는 HTML 문서에서 다음 코드로 div 요소의 목록을 가져온다고 합시다.<br><br>
var divs = document.getElementsByTagName("div");<br><br>
그러면 divs라는 목록은 비어 있는 상태이며 length 프로퍼티 값도 0인 상태입니다. 하지만 나중에 div 요소를 추가하면 divs 목록에 그 요소가 추가되고 목록의 length 프로퍼티 값도 함께 증가합니다.<br><br>
NodeList 객체가 '살아 있는' 상태를 표현한다는 것이 때로는 편리할 수 있지만, NodeList 객체를 순회하면서 요소를 추가해야 하는 상황에서는 주의를 기울여야 합니다. 이때는 아래와 같은 방법으로 NodeList 객체의 정적(Static) 복사본을 만들어 순회해야 합니다.<br><br>
var staticList = Array.prototype.slice.call(divs, 0);


### class 속성 값으로 노드 가져오기
- class 속성 값은 0개 이상의 식별자(클래스 이름)를 CSS에서 사용하는 공백 문자(공백, 탭 등)로 연결한 문자열로 표기합니다. 이 class 속성으로 class 속성 값을 갖는 요소의 집합이 정의됩니다. 즉, 똑같은 식별라 class 속성 값으로 포함된 요소들이 모인 집합 하나가 정의됩니다.
- getElementsByClassName 메서드를 사용하면 특정 class  속성 값을 class 속성 값으로 가지는 요소 객체 목록(NodeList)을 가져올 수 있습니다.

```javascript
document.getElementsByClassName(class의 이름);
```

```httml
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<div class="cat black">봄베이</div>
		<div class="cat white">페르시안</div>
		<div class="dog white">스피츠</div>
		<script>
			var cats = document.getElementsByClassName("cat");
			for(var i = 0; i < cats.length; i++) {
				console.log(i + " 번째 고양이 : " + cats[i].innerHTML);
			}
		</script>
	</body>
</html>
```

- 식별자 여러 개를 공백 문자로 연결한 문자열을 인수로 넘기면, 그 식별자들을 class 속성 값으로 갖는 요소의 목록을 가져올 수 있습니다.

```javascript
var whiteCats = document.getElemensByClassName("white cat");
```

- getElementsByClassName 메서드는 요소 객체에서도 사용할 수 있습니다. 이 경우에는 getElementsByClassName 메서드가 그 요소의 자식 요소 중에서 인수로 지정한 문자열을 class 속성 값으로 가지는 요소 객체를 NodeList에 담아서 반환합니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<p>
			<strong class="important">Document Object Model</strong>의 줄임말입니다.
		</p>
		<script>
			var paras = document.getElementsByTagName("p");
			var firstParaImportants = paras[0].getElementsByClassName("important");
			console.log(firstParaImportants);
		</script>
	</body>
</html>
```

### name 속성 값으로 노드 가져오기

- name 속성은 그 요소 이름으로 지정할 때 사용하며 form과 input 등의 폼 컨트롤 요소, iframe, img, map, object 요소 등에 지정할 수 있습니다. 
- name 속성 값은 class 속성 값과 마찬가지로 요소 여러 개를 대상으로 같은 값(이름)을 사용할 수 있습니다.
- getElementsByName 메서드를 사용하면 특정 문자열을 name 속성 값으로 갖는 요소 객체 목록(NodeList)을 가져올 수 있습니다.
- 인수는 name 속성 값입니다.

```javascript
document.getElementsByName(name);
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<form>
			<input type="checkbox" name="dog" value="pome">포메라니안<br>
			<input type="checkbox" name="dog" value="dalma">달마시안<br>
			<input type="checkbox" name="dog" value="bool">불독<br>
		</form>
		<script>
			var dogs = document.getElementsByName("dog");
			dogs[i].value = "corgi";
			dogs[i].nextSiblings.nodeValue="윌시 코기";
			for(var i = 0; i < dogs.length; i++) {
				console.log(i + " 번째의 값 : " + dogs[i].value);
			}
		</script>
	</body>
</html>
```

### CSS 선택자로 노드 가져오기

- getElementssByTagName 메서드 등이 반환하는 NodeList와 달리 querySelectorAll 메서드가 반환하는 NodeList는 '살아 있는 상태'가 아닙니다.
- NodeList에 포함된 요소는 메서드를 호출한 시점에 일치한 요소입니다. 
- HTML 문서 내용이 바뀌어도 querySelectorAll 메서드가 반환한 NodeList는 바뀌지 않습니다. 
- 또한 querySelectorAll 메서드는 일치한 요소가 없을 때 빈 NodeList를 반환합니다.

```javascript
document.querySelectorAll("선택자");
```

- querySelector 메서드는 지정한 선택자와 일치하는 요소 객체 중에서 문서 위치가 첫 번째인 요소 객체를 반환합니다.

```javascript
document.querySelector("선택자");
```

### Document 객체의 프로퍼티

- W3C에서 DOM을 표준화하기 이전의 DOM을 가리켜 DOM Level 0라고 합니다. DOM Level0에서는 설명한 노드를 가져오는 메서드가 없었습니다. 그 대신 특정 요소를 읽고 쓰는 수단이 Document객체의 프로퍼티로 마련되어 있었습니다. 이들 프로퍼티는 DOM의 최신 버전(DOM Level 3)에도 남아 있으며 주요 브라우저에서도 사용할 수 있습니다.
- 다음의 표에서 embeds, plugins, scripts는 HTML5의 구성 요소로 새롭게 추가된 것입니다. 
- document.forms[]부터 그 밑에 나오는 프로퍼티들은 HTMLCollection 객체입니다. HTMLCollection 객체는 유사 배열 객체이며 읽기 전용입니다. HTMLCollection 객체의 요소는 배열 요소의 인덱스뿐만 아니라 id 속성 값, name 속성 값으로 참조할 수 있습니다.


#### 요소를 읽고 쓰기 위해 마련된 Document 객체의 프로퍼티

|프로퍼티|설명|
|-----|-------|
|document.documentElement|문서의 루트 요소(html 요소) 객체의 참조|
|document.head|문서의 head 요소 객체의 참조|
|docoument.body|문서의 body요소 객체의 참조|
|document.forms[]|문서 안의 form 요소 객체의 참조를 저장한 유사 배열 객체|
|document.images[]|문서 안의 images 요소 객체의 참조를 저장한 유사  배열 객체|
|document.anchors[]|문서 안의 anchors 요소 객체의 참조를 저장한 유사 배열 객체|
|document.apples[]|문서 안의 applets 요소 객체의 참조를 저장한 유사 배열 객체|
|document.links[]|문서 안의 links 요소 객체의 참조를 참조를 저장한 유사 배열 객체|
|document.embeds[]|문서 안의 embeds 요소 객체의 참조를 저장한 유사 배열 객체|
|document.plugins[]|문서 안의 plugins 요소 객체의 참조를 저장한 유사 배열 객체|
|document.scripts[]|문서 안의 scripts 요소 객체의 참조를 저장한 유사 배열 객체|

> document.embeds와 document.plugins는 같은 객체를 참조합니다.



## 속성 값의 읽기와 쓰기

### 요소의 속성 값

대부분의 HTML 요소에는 속성을 설정해서 추가적인 정보를 더할 수 있습니다. 이를 활용해서 요소에 다른 요소와 구별되는 특별한 스타일을 입히거나 특별한 기능을 부여하기도 합니다.

```html
<a id="school" href="http://school.yonggyo.com">강의실</a>
```

이 a 요소에는 id속성과 href 속성이 설정되어 있습니다. 이처럼 속성은 <b>속성 이름 = 속성 값</b>으로 표기합니다.

### 요소 객체의 프로퍼티로 요소의 속성을 읽고 쓰기
- 요소 객체에는 요소의 일반적인 속성을 표현하기 위한 프로퍼티가 정의되어 있습니다.
- 요소 객체에는 일반적인 HTML 속성(id, class, name, src, href, ...)과 이벤트 처리기 프로퍼티(onclick, onmousedown, ...) 등이 정의되어 있습니다. 
- 일반적인 속성의 속성 이름은 다음과 같이 표기합니다.

```
요소 객체.속성 이름
```

- 이 프로퍼티는 읽기와 쓰기가 가능합니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<a id="school" href="http://school.yonggyo.com">강의실</a>
		<script>
			var anchor = document.getElementById("school");
			console.log(anchor.href);  // -> http://school.yonggyo.com
		</script>
	</body>
</html>
```

- 속성 프로퍼티는 쓰기가 가능하며 속성 값을 설정할 수 있습니다.


```javascript
anchor.href = "http://www.yonggyo.com";
```

- HTML 요소의 속성 이름은 대소문자를 구분하지 않지만 자바스크립트 요소 객체의 속성 프로퍼티는 대소문자를 구분합니다.
- HTML 요소의 속성을 요소 객체의 속성 프로퍼티로 사용할 때는 소문자로 작성합니다. 
- 단, 속성 이름이 여러 단어로 구성되었다면 두 번째 이 후 단어의 첫 글자를 대문자로 표기한 프로퍼티 이름을 사용합니다.

```
accept-charset  -> acceptCharSet  // form 요소에서 사용할 수 있음
accesskey -> accessKey  // 전역 속성(모든 요소에서 사용할 수 있음)
maxlength -> maxLength   // input 요소와 textarea 요소에서 사용할 수 있음
tabindex -> tabIndex  // 전역 속성(모든 요소에서 사용할 수 있음)
```

- 몇몇 HTML 속성의 이름은 자바스크립트에서 예약어로 사용하고 있는 이름입니다. 따라서 이런 경우네는 속성 이름 앞에 HTML을 덧붙입니다.
- 예를 들어 label 요소의 for 속성을 설정할 때 자바스크립트에서는 <code>htmlFor</code>를 사용하는 식입니다. 
- 단, class 속성은 특별한 취급을 받으며 자바스크립트에서는 className 프로퍼티를 사용합니다.


### 속성 값 가져오기
- 요소 객체의 getAttrbute 메서드는 요소의 속성을 가져옵니다. 
- 해당하는 속성이 없을 때는 null 또는 빈 문자열을 반환합니다.

```javascript
요소 객체.getAttribute(속성의 이름)
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<form id="favorite">
			<input type="checkbox" name="dog" value="pome">포레라니안<br>
			<input type="checkbox" name="dog" value="dalma">달마시안<br>
			<input type="checkbox" name="dog" value="bool">불독<br>
		</form>
		<script>
			var result = [];
			var fm = document.getElementById("favorite");
			var list = fm.children;
			for(var i = 0; i < list.length; i++) {
				if (list[i].nodeName == "INPUT" && list[i].type == "checkbox") {
					result.push(list[i]).getAttribute("value"));
				}
			}
			console.log(result.join(","));  // -> pome,dalma,bool
		</script>
	</body>
</html>
```

### 속성 값 설정하기
- 요소 객체의 setAttribute 메서드는 요소의 속성을 설정합니다. 
- 해당하는 속성이 없ㅇ을 때는 그 속성을 새롭게 추가한 후에 설정합니다.

```javascript
요소 객체.setAttribute(속성 이름, 속성 값)
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<a id="school" href="http://school.yonggyo.com">강의실</a>
		<script>
			var anchor = document.getElementById("school");
			anchor.setAttribute("href", "http://www.yonggy.com");
			console.log(anchor);
		</script>
	</body>
</html>
```

- getAttribute와 setAttribute 메서드는 요소 객체의 속성 프로퍼티에 비해 다음과 같은 장점이 있습니다.
	- HTML의 일반적인 속성은 물론 모든 속성을 설정할 수 있습니다.
	- 속성 이름을 프로그램 실행 중에 동적으로 설정할 수 있다.
	
### 속성이 있는지 확인하기

```javascript
요소 객체.hasAtrribute(속성 이름)
```

### 속성 삭제하기

```javascript
요소 객체.removeAttribute(속성 이름)
```

### 전체 속성의 목록 가져오기ㅏ

- 요소 객체에는 attributes 프로퍼티가 정의되어 있습니다. 
- 이 프로퍼티는 NamedNodeMap 객체로 그 요소에 설정된 모든 속성의 속성 노드 객체가 담겨 있습니다.
- NamedNodeMap 객체는 유사 배열 객체이며 읽기 전용입니다. 
- NamedNodeMap 객체의 요소인 속성 노드 객체의 name 프로퍼티에는 속성 이름이 담겨 있으며, value 프로퍼티에는 속성 값이 담겨 있습니다.

```javascript
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<p id="controls">
			<input type="button" value="click" onClick="doSomething();">
		</p>
		<script>
			var para = document.getElementById("controls");
			var list = para.firstElementChild.attrbutes;
			for(var i = 0; i < list.length; i++) {
				console.log(list[i].name + ": " + list[i].value);
			}
		</script>
	</body>
</html>
```

- NamedNodeMap 객체의 요소는 배열의 인덱스는 물론 속성 값으로도 가져올 수 있습니다.

```javascript
console.log(list["type"].value);  // -> button
console.log(list["value"].value);  // -> click
console.log(list.onclick.value);  // -> doSomeThing();
```

## HTML 요소의 내용을 읽고 쓰기
요소의 내용이라는 단어에는 다음 세 가지 의미가 함축되어 있습니다.
- 요소 안의 HTML 코드 
- 요소를 웹 페이지에 표시할 때의 텍스트 정보
- 요소 객체 안의 노드의 계층 구조

### innerHTML 프로퍼티
- innerHTML 프로퍼티는 요소 안의 HTML 코드를 가리킵니다. innerHTML 프로퍼티를 사용해서 요소 안의 코드를 읽거나 쓸 수 있습니다. 

```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		var para = document.getElementById("cards");
		console.log(para.innerHTML);
	</script>
</head>
<body>
	<h1>하트</h1>
	<p id="cards">&hearts;하트는 <em>승려</em>라는 뜻입니다.</p>
</body>
</html>
```
- innerHTML 프로퍼티로 p 요소의 내용을 수정하면 요소의 내용이 대입한 코드로 바뀝니다.

```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		var para = document.getElementById("cards");
		param.innerHTML = &diams;다이아는 <strong>상인</strong>이라는 뜻입니다.";
		console.log(para.innerHTML);
	</script>
</head>
<body>
	<h1>하트</h1>
	<p id="cards">&hearts;하트는 <em>승려</em>라는 뜻입니다.</p>
</body>
</html>
```

### textContent와 innerText 프로퍼티
- textContent 프로퍼티는 요소의 내용을 웹 페이지에 표시했을 때의 텍스트 정보를 표시합니다.
- textContent 프로퍼티 값은 지정한 요소의 자식 노드인 모든 텍스트 노드를 연결한 값입니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script>
		var para = document.getElementById("cards");
		console.log(para.textContent);
	</script>
</head>
<body>
	<h1>하트</h1>
	<p id="cards">&hearts;하트는 <em>승려</em>라는 뜻입니다.</p>
</body>
</html>
```

- 또한 textContent 프로퍼티에 텍스트를 대입하면 요소의 내용을 텍스트로 변환할 수 있습니다.



## 노드 생성/삽입/삭제하기

### 노드 생성하기
- 새로운 요소 노드 객체를 생성할 때는 createElement 메서드를 사용합니다.

```javascript
var element = document.createElement(요소의 이름);
```

- 예를 들어 h1 요소를 만들 때는 코드를 다음과 같이 작성합니다.

```javascript
var headLine = document.createElement("h1");
```

- 이 코드의 headline에 설정된 프로퍼티 값은 다음과 같습니다. 

```javascript
console.log(headline.nodeName);   // -> "H1"
console.log(headline.nodeType);   // -> 1
```

- 그러나 DOM 트리 계층 구조를 뜻하는 프로퍼티(parentNode, childNode 등) 값은 모두 null 입니다. 
- 즉, createElement로 생성한 객체는 메모리에 생성되어 있을 뿐 문서의 DOM 트리와는 아무런 관계가 없습니다.

- 새로운 텍스트 노드 객체를 생성할 때는 createTextNode 메서드를 사용합니다.

```javascript
var textnode = document.createTextNode(텍스트);
```

- 다음 코드는 새로운 텍스트 노드를 생성합니다.

```javascript
var textnode = document.createTextNode(텍스트);
```

- 다음 코드는 새로운 텍스트 노드를 생성합니다.

```javascript
var newtext = document.createTextNode("we only see what we know");
```

- createElement와 마찬가지로 createTextNode로 만든 노드 객체도 문서의 DOM 트리와는 아무런 관계가 없다는 점에 유의하세요.

#### 노드 객체를 생성하는 주요 메서드

|메서드|생성하는 노드 객체|
|----|----------|
|document.createElement(요소 이름)|요소 노드 객체|
|document.createAttribute(속성 이름)|속성 노드 객체|
|document.createTextNode(텍스트)|텍스트 노드 객체|
|document.createComment(텍스트)|주석 노드 객체|
|document.createDocumentFragment()|도큐먼트 프레그먼트|
|document.importNode(다른 문서의 노드, deep)|다른 문서에 있는 노드를 복사한다. deep을 true로 설정하면 자식 노드까지 복사하고, false로 설정하면 얕은 복사를 한다.|
|node.cloneNode(deep)|노드를 복사한다. deep을 true로 설정하면 자식 노드까지 복사하고 false로 설정하면 얕은 복사를 한다.|


### 노드 삽입하기

#### 요소의 마지막에 삽입하기 : appendChild 메서드

```
요소 노드.appendChild(삽입할 노드)
```

- appendChild 메서드로 노드 객체를 삽입하면 그 객체가 DOM 트리에 추가되고 DOM트리의 각 노드에 계층 구조를 정의하는 프로퍼티(parentNode, childNode 등)가 바뀝니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<ul id="doglist">
			<li>포메라니안</li>
			<li>달마시안</li>
		</ul>
	</body>
</html>
```

- 이 문서의 ul 요소에 마지막 자식 요소로 <code><li>불독</li></code>를 삽입하려면 다음과 같은 코드를 작성합니다.

```javascript
var doglist = document.getElementById("doglist");
var element = document.createElement("li");  // li 요소 객체를 생성한다.
var text = document.createTextNode("불독");  // 텍스트 노드를 생성한다.
doglist.appendChild(element); // ul 요소에 element를 삽입한다.
element.appendChild(text);  // element에 텍스트 노드를 삽입한다.
```

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images5.png)

- 이 코드에서 doglist.appendChild(element)를 실행하기 전에 element.appendChild(text)를 실행해도 같은 결과가 나옵니다. 또한 텍스트 노드를 생성해서 대입하는 대신 다음 코드를 실행해도 같은 결과가 나옵니다.

```javascript
var doglist = document.getElementById("doglist");
var element = document.createElement("li");  // li 요소 객체를 생성한다.
element.textContent = "불독";  // 텍스트 노드를 생성한다.
doglist.appendChild(element);  // ul 요소에 element를 삽입한다.
```

#### 지정한 자식 노드의 바로 앞에 삽입하기 : insertBefore 메서드

```javascript
요소 노드.insertBefore(삽입할 노드, 자식 노드)
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<ul id="doglist">
			<li>포메라니안</li>
			<li>달마시안</li>
		</ul>
		<script>
			var doglist = document.getElementById("doglist");
			var element = document.createElement("li");
			var text = document.createTextNode("불독");
			doglist.insertBefore(element, doglist.children[1]);
			// 두 번째 자식 요소의 바로 앞에 삽입
			element.appendChild(text);
		</script>
	</body>
</html>
```

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images6.png)


#### 노드 옮기기 
- 이미 있는 노드를 appendChild와 insertBefore 메서드로 문서에 삽입하면 해당 노드를 현재 위치에서 삭제하고 새로운 위치에 삽입합니다. 
- 결과적으로 그 노드는 이동하게 됩니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<ul id="doglist">
			<li>포메라니안</li>
			<li>달마시안</li>
		</ul>
		<script>
			var doglist = document.getElementById("doglist");
			doglist.appendChild(doglist.children[0]); // 첫 번째 자식 요소의 가장 끝에 삽입
		</script>
	</body>
</html>
```

#### 노드 삭제하기
```javascript
노드.removeChild(자식 노드)
```

```javascript
node.parentNode.removeChild(node);
```

#### 노드 치환하기

```javascript
노드.replaceChild(새로운 노드, 자식 노드)
```

- 해당 노드의 자식 노드만 치환할 수 있다는 점에 유의하세요.
- 즉, 치환하려면 노드의 부모 노드에서 replaceChild 메서드를 호출합니다. 
- 특정 노드인 node를 새로운 노드인 newnode로 치환하고자 할 때는 다음과 같이 작성합니다.

```javascript
node.parentNode.replaceChild(newnode, node);
```

## HTML 요소의 위치

### HTML 요소의 위치를 표현하는 좌표계

#### 뷰 포트 좌표계
- 뷰 포트의 왼쪽 위 꼭짓점을 원점으로 하는 좌표계입니다.
- 뷰 포트란 웹 브라우저에서 문서의 내용을 표시하는 영역을 말합니다. 이 영역은 메뉴, 도구 모음, 탭 등을 포함하지 않습니다.
- 뷰 포트 좌표계는 <b>윈도우 좌표계</b>라고 부르기도 합니다.

#### 문서 좌표계
- 문서의 왼쪽 위 꼭짓점을 원점으로 하는 좌표계입니다.
- 문서는 웹 브라우저 표시 영역(뷰 포트)안에 표시되는데, 문서를 스크롤하면 문서 좌표계의 원점이 뷰 포트를 따라 이돌합니다. 
- 문서 좌표계를 따르는 요소의 좌표는 사용자가 문서를 스크롤해도 바뀌지 않으므로 뷰 포트 좌표계를 따르는 요소보다 다루기 쉽다는 특징이 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images7.png)

### HTML 요소의 위치와 크기 구하기
- 요소 객체의 getBoundingClientRect 메서드는 뷰 포트 좌표계로 측정한 해당 요소의 보더 박스 위치와 크기 정보를 담은 객체를 반환합니다.

```javascript
var rect = 요소 객체.getBoundingClientRect();
```

- getBoundingClientRect 메서드는 ClientRect 객체를 반환하며 다음과 같은 프로퍼티를 갖고 있습니다.
	- <b>left</b> : 요소 박스의 왼쪽 위 꼭짓점의 X좌표
	- <b>top</b> : 요소 박스의 왼쪽 위 꼭짓점의 Y좌표
	- <b>right</b> : 요소 박스의 오른쪽 아래 꼭짓점의 X좌표
	- <b>bottom</b> : 요소 박스의 오른쪽 아래 꼭짓점의 Y좌표
	- <b>width</b> : 요소 박스의 너비
	- <b>height</b> : 요소 박스의 높이


#### getBoundingClientRect 메서드의 반환값이 갖는 프로퍼티

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images8.png)


#### 블록 박스의 박스 모델 

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/13~14%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%9B%B9%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%20%EA%B0%9D%EC%B2%B4/images/images9.png)

### 뷰 포드의 크기 가져오기
- 뷰 포트의 크기는 다음 프로퍼티에 담겨 있습니다.
	- <code>document.documentElement.clientWidth</code> : 뷰 포트의 너비(스크롤 막대의 너비를 포함하지 않음)
	- <code>document.documentElement.clientHeight</code> : 뷰 포트의 높이(스크롤 막대의 옾이를 포함하지 않음)

- IE9 이후에 출시된 다른 웹 브라우저는 다음 프로퍼티도 제공합니다.
	- <code>window.innerWidth</code> : 뷰 포트의 너비(스크롤 막대의 너비를 포함)
	- <code>window.innerHeight</code> : 뷰 포토의 높이(스크롤 막대의 높이를 포함)
	
### 스크롤한 거리 구하기

- 인터넷 익스플로러, 파이어 폭스
	- <code>document.documentElement.scrollLeft</code> : X축 방향으로 스크롤한 거리
	- <code>document.documentElement.scrollTop</code> : Y축 방향으로 스크롤한 거리
	- ※ 크롬, 사파리, 오페라에도 같은 프로퍼티가 있지만 값이 항상 0입니다.
	
- 크롬, 사파리, 오페라, 엣지, 각 웹 브라우저의 Quirks Mode
	- <code>document.body.scrollLeft</code> : X축 방향으로 스크롤한 거리
	- <code>document.body.scrollTop</code> : Y축 방향으로 스크롤한 거리
	- ※ 인터넷 익스플로러, 파이어폭스에도 같은 프로퍼티가 있지만 값이 항상 0입니다. 
	
- 파이어폭스, 크롬, 사파리, 오페라, 엣지, IE9 이상
	- <code>window.pageXOffset</code> : X축 방향으로 스크롤한 거리
	- <code>window.pageYOffset</code> : Y축 방향으로 스크롤한 거리
	
### 스크롤하기

#### 특정 위치로 스크롤하기
window 객체의 scrollTo 메서드는 문서 좌표 (X,Y)를 인수로 받으며, 뷰 포트 좌표의 원점(표시 영역의 왼쪽 위 모서리)까지 스크롤합니다.

```javascript
window.scrollTo(X,Y);
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<style>
			.box {
				display: inline-block;
				padding: 100px;
				margin: 100px;
				margin-left: 0;
				background-color: yellow;
			}
		</style>
	</head>
	<body>
		<div class="box" id="sec1">#sec1</div>
		<br>
		<div class="box" id="sec2">#sec2</div>
		<br>
		<div class="box" id="sec3">#sec3</div>
		<script>
			// 크롬을 비롯한 일부 웹브라우저에서는 스크롤 복원 기능을 꺼야 합니다.
			if ('scrollRestoration' in history) {
				history.scrollRestoration = 'manual';
			}
			var element = document.getElementById("sec3");
			var rect = element.getBoundingClientRect();
			scrollTo(rect.left + window.pageXOffset, rect.top + window.pageYOffset);
		</script>
	</body>
</html>
```

#### 특정 거리만큼 스크롤하기
- Window 객체의 scrollBy 메서드는 스크롤할 거리를 인수로 받아 문서를 그 거리만큼 스크롤합니다.

```javascript
window.scrollBy(dx, dy);
```

- 예를 들어 한 페이지 분량만큼(표시 영역 높이만큼) 스크롤하려면 다음과 같이 작성합니다.

```javascript
scrollBy(0, window.innerHeight);
```

#### 특정 요소가 있는 위치까지 스크롤하기
- 요소 객체의 scrollIntroView 메서드는 그 요소가 웹 브라우저의 표시 영역에 들어올 때까지 스크롤 합니다.

```javascript
요소 객체.scrollIntoView(alignWithTop);
```

- 인수 alignWidthTop은 선택 사항으로 생략하면 true로 간주합니다. alignWidthTop이 true면 요소가 표시 영역의 위쪽 끝에 오도록 스크롤합니다. false면 요소가 표시 영역의 아래쪽 끝에 오도록 스크롤합니다.
- 앞 예제에서 id 속성 값이 "sec3"인 요소를 표시 영역의 위쪽 끝까지 스크롤하려면 코드를 다름과 같이 작성합니ㅏㄷ.

```javascript
var element = document.getElementById("sec3");
element.scrollIntoView();
```

### HTML 요소의 위치 변경고하 렌더링 성능
렌더 트리를 다시 구성하고 그리는 작업에는 시간이 걸립니다. 그래서 웹 브라우저는 이러한 상황을 피하고자 렌더링 처리 횟수를 가능한 줄이는 최적화 처리를 합니다. 그러나 요소의 위치와 크기를 변경하는 작업은 레이아웃 처리를 촉발하는 방아쇠가 되며, 최적화의 대상이 되지 않습니다. 레이아웃을 처리하려면 부모 요소까지 거슬러 올라가 계산해야 하며, 이는 렌더링 성능을 현저하게 떨어뜨리는 원인이 됩니다. 특히 position 루트부터 다시 계산해야 하므로 그만큼 시간이 걸립니다.  웹페이지의 렌더링 성능을 개선하고자 한다면 요소의 크기 변경과 위치 변경이 영향을 미치는 범위를 염두에 두어야 합니다.

## HTML 폼

- 폼에 입력한 데이터를 웹 서버로 보내고 웹 서버는 그 데이터를 처리한다. 그 결과를 사용자에게 반환하거나 데이터베이스에 저장한다.
- 클라이언트 측 자바스크립트로 웹 애플리케이션을 만들 때 사용자 입력을 받는 사용자 인터페이스로 사용한다. 이때 데이터 처리는 클라이언트 측 자바스크립트 프로그램이 담당한다.

### 폼 요소와 폼 컨트롤 요소
- 웹 서버에 데이터를 보낼 때는 다음과 같은 과정을 거칩니다. 우선 form 요소를 작성하고 method와 action 속성을 지정합니다.
	- <b>method 속성</b> : 데이터 전송 방법("POST" 또는 "GET")
	- <b>action 속성</b> : 데이터를 처리하는 CGI 프로그램의 URL

- 그 다음 form 요소 안에 사용자로부터 입력을 받는 input 요소 등의 폼 컨트롤 요소를 배치합니다.
- 마지막으로 form 요소 안에 데이터를 전송하기 위한 submit 버튼과 데이터 입력을 취소하는 reset 버튼을 배치합니다. 

```html
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>간단한 폼 예제</title>
</head>
<body>
	<form method="post" action="./cgi-bin/post.cgi" name="questions" id="form1">
		<h1>설문 조사</h1>
		<p>
			<label>이름 : </label>
			<input type="text" name="name">
		</p>
		<p>
			<label>성별 : </label>
			<label><input type="radio" name="gender" value="male">남</label>
			<label><input type="radio" name="gender" value="female">여</label>
		</p>
		<p>
			<label>혈액형 : </label>
			<select name="bloodtype" id="menu1">
				<option>A형</option>
				<option>B형</option>
				<option>O형</option>
				<option>AB형</option>
			</select>
		</p>
		<p>
			<h2>의견</h2>
			<textarea name="opinion" rows="6" cols="80" placeholder="의견을 남겨주세요"></textarea>
		</p>
		<p>
			<input type="submit" value="보내기">
			<input type="reset" value="취소">
		</p>
	</form>
</body>
</html>
```

### 폼 요소와 폼 컨트롤 요소 가져오기

#### DOM 메서드로 가져오는 방법
- 폼 요소와 폼 컨트롤은 DOM의 표준 메서드를 사용하여 가져올 수 있습니다.

```javascript
var menu = document.getElementById("menu1");
var options = menu.getElementsByTagName("option");
```

- querySelectorAll 메서드를 사용하면 성별 정보를 구하는 라디오 버튼 목록을 다음과 같이 가져올 수 있습니다.

```javascript
var inputs = document.querySelectorAll("#form1 input[type='radio']");
```

#### forms 프로퍼티로 form 요소 가져오기
- 문서 안의 form 요소 목록은 Document 객체의 forms 프로퍼티에서도 가져올 수 있습니다. 
- forms 프로퍼티 값은 HTMLCollection 객체 입니다.
- HTMLCollection 객체의 요소는 배열 요소의 인덱스는 물론 id 속성 값, name 속성 값으로 가져올 수 있습니다.

```javascript
document.forms[0]   // 인덱스로 가져오기
document.forms.form1 // id 속성 값으로 가져오기
document.forms.questions // name 속성 값으로 가져오기
```

- 이 방법 중에서 인덱스로 폼 요소를 가져오는 방법은 특별한 목적이 있지 않은 한 사용하지 않는편이 좋습니다. 자바스크립트를 사용해서 form 요소를 문서에 동적으로 추가하면 이 인덱스가 다른 요소를 가리키게 될 가능성이 있기 때문입니다. 
- 또한 name 속성을 가지는 form 요소의 목록은 다음 방법으로도 가져올 수 있습니다.

```javascript
document.questions;  // name 속성 값이 "questions"인 form 요소의 목록 
```

#### form 요소 객체의 자식 요소 객체 가져오기
- form 요소 객체 자체도 유사 배열 객체이며, 해당 폼의 자식 요소인 폼 컨트롤 요소 객체를 배열의 요소로 갖고 있습니다. 
- 배열의 요소는 HTMLCollection 객체와 마찬가지로 배열 요소의 인덱스, id 속성 값, name 속성 값으로 가져올 수 있습니다.
- 예를 들어 혈액형 메뉴를 정의한 select 요소 객체를 다음과 같은 방법으로 가져올 수 있습니다.

```javascript
document.forms.form1[3]
document.forms.form1.bloodtype
document.forms.form1.menu1
```
- 또한 form 요소 객체는 elements 프로퍼티도 가지고 있습니다. elements 프로퍼티도 유사 배열 객체이며, 해당 폼의 자식 요소인 폼 컨트롤 요소 객체를 배열의 요소로 갖고 있습니다.
- 배열의 요소는 HTMLCollection 객체와 마찬가지로 배열 요소의 인덱스, id 속성 값, name 속성 값으로 가져올 수 있습니다.

```javascript
document.forms.form1.elements[3]
document.forms.form1.elements.bloodtype
document.forms.form1.elements.menu1
```

- 앞의 두 번쨰 코드는 name 속성 값이 가리키는 요소의 개수에 따라 반환하는 값이 달라집니다. name 속성 값이 가리키는 요소가 하나일 때는 그 폼 컨트롤 요소 객체 자체를 반환합니다. name 속성 값이 가리키는 요소가 여러 개 일 때는 그 요소들을 담은 유사 배열 객체를 반환합니다.

```javascript
var inputs = document.forms.form1.elements.gender;
```

- 그러면 inputs에는 성별을 선택하는 두 개의 input 요소가 담긴 유사 배열 객체의 참조가 들어옵니다. 
- 이때 사용자가 선택한 성별(input 요소의 value 속성 값)을 다음 코드로 가져올 수 있습니다.

```javascript
var gener_selected;
for(var i = 0; i < inputs.length; i++) {
	if(inputs[i].checked) gender_selected = inputs[i].value;
}
```


## CSS 제어하기

### 스타일 변경 방법

- 요소 객체의 style 속성 값을 수정하는 방법
- 스타일 시트에 스타일을 클래스로 설정해 둔 다음 요소의 class 속성 값을 바꾸어 스타일을 전환하는 방법
- 스타일 시트를 수정하는 방법

### 인라인 스타일 제어하기


- 요소 객체에는 style 프로퍼티를 갖고 있습니다. 이 style 프로퍼티를 사용해서 개별 요소의 스타일을 제어할 수 있습니다.

```javascript
요소 객체.style.프로퍼티 이름 = 값;
```

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1 id="title">Javascript</h1>
	</body>
</html>
```

- 이 요소를 마우스를 클릭했을 때 배경색을 분홍색으로 바꾸려면 코드를 다음과 같이 작성합니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1 id="title">Javascript</h1>
		<script>
			var element = document.getElementById("title");
			element.onclick = function() {
				element.style.backgroundColor = "pink";
			};
		</script>
	</body>
</html>
```

- 요소 객체가 가진 style 프로퍼티 값은 CSSStyleDeclaration 객체 입니다. CSSStyleDeclaration 객체는 CSS의 다양한 스타일과 대응하는 프로퍼티를 가지고 있으며, 이 프로퍼티 값을 바꾸면 요소 스타일을 바꿀 수 있습니다.
- 이 CSSStyleDeclaration 객체의 프로퍼티 이름에는 일정한 법칙이 있습니다.
	- CSS 프로퍼티 이름에 하이픈(-)이 없으면 그 이름을 그대로 사용한다.
	- CSS의 프로퍼티 이름에 하이픈이 있으면 하이픈을 제거하고, 뒤 단어의 첫 글자를 대문자로 바꾼 문자열을 사용한다. 단, CSS의 프로퍼티 이름 앞에 하이픈이 있으면 하이픈을 삭제하고 그 뒤에 이어지는 단어를 소문자로 표기한다.

```javascript
font-size -> fontSize
background-color -> backgroundColor
-webkit-transform -> webkitTransform
```
- 단, float 프로퍼티만 예외적으로 cssFloat가 됩니다.

#### 계산된 스타일 구하기
- 요소의 스타일은 스타일 시트 여러 개와 각 요소의 style 속성에 작성한 인라인 스타일에 의해 결정됩니다.
- 이 중에서 요소의 인라인 스타일은 스타일 요소 객체의 style 프로퍼티로 읽거나 쓸 수 있습니다. 웹 브라우저는 이렇게 다양한 위치에 작성된 스타일 규칙을 취합해서 요소에 적용할 스타일을 계산합니다. 
- 이때는 스타일 시트 설정보다 style 속성에 작성된 값을 우선적으로 적용합니다. 따라서 자바스크립트로 요소의 스타일을 조작할 때는 style 프로퍼티를 사용하는 편이 좋습니다. 하지만 계산된 스타일을 style 프로퍼티로 구할 수 없을 때가 있습니다. 
- 예를 들어 스타일 시트만으로 스타일을 지정한 요소의 style 객체를 확인해 보면 모든 프로퍼티 값이 빈 문자로 채워져 있습니다.

- 요소의 계산된 스타일은 Window 객체의 <code>getComputedStyle</code> 메서드로 가져올 수 있습니다. 
- getComputedStyle 메서드는 style 객체와 마찬가지로 CSSStyleDeclaration 객체를 반환합니다. 
- CSSStyleDeclaration 객체는 '살아 있는' 상태를 표현합니다. 그러나 style 객체와 달리 getComputedStyle 메서드로 가져온 계산된 스타일은 읽기 전용입니다.

```css
p { color: red; }
```

```html
<p id="note">...</p>
```

- 이 요소를 가리키는 style 객체의 color 프로퍼티와 with 프로퍼티 값을 확인해 봅니다.
```javascript
var element = document.getElementById("note");
console.log(element.style.color);  // -> 
console.log(element.style.height);  // -> 
```
- 모두 빈 문자가 채워져 있습니다. 이것은 인라인 스타일을 지정하지 않았기 때문입니다.
- 같은 프로퍼티의 계산된 스타일을 getComputedStyle 메서드로 확인해 봅니다.

```javascript
var computedStyles = getComputedStyle(element);
console.log(computedStyles.color);  // -> rgb(255, 0, 0)
console.log(computedStyles.height); // -> 24px
```
- 이때 표시되는 계산된 스타일의 프로퍼티 값은 절대값입니다. 백분율 등의 상대값은 절대 값으로 바뀝니다.


### 클래스 제어로 스타일 변경
- 요소의 인라인 스타일을 바꾸는 대신에 요소의 class 속성 값을 바꾸어 스타일을 제어하는 방법이 있습니다.
- 이를 구현하려면 스타일 시트에 정의된 스타일을 요소의 class 속성으로 사용하도록 HTML 문서를 작성해야 합니다. 
- 그 다음 자바스크립트 요소 객체의 className 프로퍼티에 기록된 class 속성 값을 바꿉니다. 
- 요소의 class 속성 값을 바꾸면 그 요소에 적용되는 스타일도 바뀝니다. 
- 이 방법을 활용하면 여러 요소의 스타일을 한꺼번에 바꿀 수 있습니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<style>
			.emphasize {
				background-color: pink;
			}
		</style>
	</head>
	<body>
		<h1 id="title">JavaScript</h1>
		<script>
			var element = document.getElementById("title");
			element.onmouseover = function() {
				element.className = "emphasize";
			};
			element.onmouseout = function() {
				element.className = "";
			};
		</script>
	</body>
</html>
```

#### classList 프로퍼티
- HTML 요소의 class 속성에는 여러 개의 클래스 이름을 공백 문자로 연결해서 지정할 수 있습니다. 이렇게 하면 요소가 여러 개의 CSS 클래스에 속하게 됩니다.

```html
<p class="note invisible" id="note1">...</p>
```

- 이때 특정 클래스 이름을 취득/수정/삭제하려면 복잡한 문자열 처리를 해야 합니다. "note invisible"처럼 여러 개의 클래스 이름을 공백 문자로 연결한 문자열이 들어가 있는 상태이기 때문입니다. 
- 이런 상황에서 사용할 수 있도록 모든 요소 객체에는 classList 프로퍼티가 마련되어 있습니다. 
- <code>classList</code> 속성 값은 <code>DOMTokenList</code> 객체 입니다. <code>DOMTokenList</code> 객체는 유사 배열 객체이며 읽기 전용입니다.
- 이 객체는 HTMLCollection 객체와 마찬가지로 '살아 있는' 상태를 표현합니다. classList 객체의 배열 요소는 className 프로퍼티 값이 항상 반영된 상태입니다.

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<p class="note invisible" id="note1">...</p>
		<script>
			var element = document.getElementById("note1");
			var list = element.classList;
			for(var i = 0; i < list.length; i++) {
				console.log(list[i]);
			}
			// note, invisible을 순서대로 표시한다.
		</script>
	</body>
</html>
```

#### DOMTokenList 객체의 메서드

|메서드|설명|
|----|------|
|add(클래스 이름)|요소의 클래스 목록에 해당 클래스를 추가한다.|
|remove(클래스 이름)|요소의 클래스 목록에서 해당 클래스를 삭제한다.|
|toggle(클래스 이름)|요소의 클래스 목록에 해당 클래스가 없으면 추가하고 있으면 삭제한다.|
|contains(클래스 이름)|요소의 클래스 목록에 해당 클래스가 있는지를 뜻하는 논리값을 반환한다.|

```html
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<p class="note invisible" id="note1">...</p>
		<script>
			var element = document.getElementById("note1");
			var list = element.classList;
			list.toggle("invisible");
			console.log(element.className); // note
			list.toggle("invisible");
			console.log(element.className); // note invisible
		</script>
	</body>
</html>
```
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



* * * 
## Location 객체

* * * 
## History 객체

* * * 
## Naviator 객체

* * * 
## Screen 객체

* * * 
## 창 제어하기

* * * 
## Document 객체
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


### 웹 브라우저 렌더링 엔진과 자바스크립트 엔진

### Window 객체



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
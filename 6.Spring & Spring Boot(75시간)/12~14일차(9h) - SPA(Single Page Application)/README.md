# CORS(Cross-Origin Resource Sharing)

- 교차 출처 리소스 공유(Cross-Origin Resource Sharing, CORS)는 추가 HTTP 헤더를 사용하여, 한 출처에서 실행 중인 웹 애플리케이션이 다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 체제입니다. 웹 애플리케이션은 리소스가 자신의 출처(도메인, 프로토콜, 포트)와 다를 때 교차 출처 HTTP 요청을 실행합니다.

-- 교차 출처 요청의 예시: https://domain-a.com 의 프론트 엔드 JavaScript 코드가 XMLHttpRequest를 사용하여 https://domain-b.com/data.json 을 요청하는 경우.

- 보안 상의 이유로, 브라우저는 스크립트에서 시작한 교차 출처 HTTP 요청을 제한합니다. 예를 들어, XMLHttpRequest와 Fetch API는 동일 출처 정책을 따릅니다. 즉, 이 API를 사용하는 웹 애플리케이션은 자신의 출처와 동일한 리소스만 불러올 수 있으며, 다른 출처의 리소스를 불러오려면 그 출처에서 올바른 CORS 헤더를 포함한 응답을 반환해야 합니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/12~14%EC%9D%BC%EC%B0%A8(9h)%20-%20SPA(Single%20Page%20Application)/images/image1.png)


- CORS 체제는 브라우저와 서버 간의 안전한 교차 출처 요청 및 데이터 전송을 지원합니다. 최신 브라우저는 XMLHttpRequest 또는 Fetch와 같은 API에서 CORS를 사용하여 교차 출처 HTTP 요청의 위험을 완화합니다.

* * * 
# Ajax(Asynchronous JavaScript and XML)

- [XMLHttpRequest](https://github.com/yonggyo1125/curriculum300H/tree/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/16%EC%9D%BC%EC%B0%A8(3h)%20-%20Promise%2C%20XMLHttpRequest%2C%20FileReader%2C%20%EB%93%9C%EB%9E%98%EA%B7%B8%EC%95%A4%EB%93%9C%EB%A1%AD#xmlhttprequest)

- [Axios](https://axios-http.com/kr/docs/intro)

* * * 
# Vue.js와 스프링 Rest API 이용하여 SPA(Single Page Application) 만들기

## Vue.js 개발환경 구축, Vue.js 기본 문법

- [vue.js Part1](https://github.com/yonggyo1125/curriculum300H/blob/main/9.%EA%B8%B0%ED%83%80/vue.js/part1.md)

- [vue.js Part2](https://github.com/yonggyo1125/curriculum300H/blob/main/9.%EA%B8%B0%ED%83%80/vue.js/part2.md)

## ToDo 리스트 SPA 만들기

- [ToDOSPA 레포지토리](https://github.com/yonggyo1125/ToDoSPA)





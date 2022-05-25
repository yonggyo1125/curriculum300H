# JSTL

## 설치하기

![JSTL1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSTL/images/JSTL1.png)

- [JSTL1.2 다운로드](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2)
- 다운로드 받은 jstl1.2.jar 파일을 src/webapp/WEB-INF/lib에 복사해 준다.

## 코어(core) 라이브러리
- JSTL의 코어 라이브러리는 글자 그대로 가장 핵심적인 기능을 제공하는 라이브러리 입니다.
- 이 라이브러리에서 제공하는 커스텀 액션을 이용하면 일반 프로그래밍 언어에서 제공하는 변수 선언, 조건 분기, 반복 수행 등의 로직을 구사할 수 있고, 다른 JSP 페이지를 호출할 수도 있습니다.

### <c:set> 커스텀 액션
- \<c:set\>은 변수를 선언하고 나서 그 변수에 초기값을 대입하는 기능의 커스텀 액션입니다.\
- \<c:set var="num" value="100" /\>
	- num : 변수의 이름
	- 100 : 초기값

- 이렇게 선언한 변수는 익스프레션 언어의 EL 식 안에서 사용할 수 있습니다. 하지만 JSP 스트립팅 요소 안에서 사용할 수는 없습니다.
- \<c:set\> 커스텀 액션을 이용해서 선언한 변수는 자바 변수가 되는 것이 아니라 page 데이터 영역의 애트리뷰트가 되기 때문입니다.

```
<c:set var="num" value="100" />
...
${num}
```
>page 데이터 영역이란 setAttribute, getAttribute 메서드를 이용해서 데이터를 저장할 수 있는 데이터 영역(page, request, session, application) 중 가장 작은 범위의 영역을 말합니다.
- \<c:set\> 커스텀 액션의 value 애트리뷰트 값에는 EL 식을 쓸 수도 있습니다. 이런 특성을 활용하면 \<c:set\>액션을 이용해서 선언한 변수의 값을 또 다른 변수의 초기값으로 사용할 수도 있습니다.

```
<c:set var="sum" value="${num1+num2}" />  // value 애트리뷰트 값으로 EL 식을 쓸 수도 있습니다.
```

#### <c:set> 액션과 스크립팅 요소

## 포매팅(fmt) 라이브러리

## 함수(functions) 라이브러리

## SQL 라이브러리
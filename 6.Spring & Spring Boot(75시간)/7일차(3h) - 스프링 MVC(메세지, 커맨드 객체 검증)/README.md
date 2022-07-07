# 스프링 MVC : 메시지, 커맨드 객체 검증

## 프로젝트 준비

- 스프링 MVC : 요청 매핑, 커맨드 객체, 리다이렉트, 폼 태그, 모델에서 사용한 [예제 소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/6%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EC%9A%94%EC%B2%AD%EB%A7%A4%ED%95%91%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%2C%20%ED%8F%BC%20%ED%83%9C%EA%B7%B8%2C%20%EB%AA%A8%EB%8D%B8)/%EC%98%88%EC%A0%9C%20%EC%86%8C%EC%8A%A4) 를 이어서 사용 

## \<spring:message\> 태그로 메시지 출력하기

- 사용자 화면에 보일 문자열은 JSP에 직접 코딩한다. 예를 들어 로그인 폼을 보여줄 때'아이디', '비밀번호' 등의 문자열을 다음과 같이 뷰 코드에 직접 삽입한다.

```html
<label>이메일</label>
<input type="text" name="email">
```

- '이메일'과 같은 문자열은 로그인 폼, 회원 가입 폼, 회원 정보 수정 폼에서 반복해서 사용된다. 이렇게 문자열을 직접 하드 코딩하면 동일 문자열을 변경할 때 문제가 있다. 예를 들어 폼에서 사용할 '이메일'을 '이메일 주소'로 변경하기로 했다면 각 폼을 출력하는 JSP를 찾아서 모두 변경해야 한다.

- 문자열이 뷰 코드에 하드 코딩 되어 있을 때의 또 다른 문제점은 다국어 지원에 있다. 전 세계를 대상으로 서비스를 제공해야 하는 경우 사용자의 언어 설정에 따라 '이메일', 'E-mail'과 같이 각 언어에 맞게 문자열을 표시해야 한다. 그런데 뷰 코드에 ‘이메일'이라고 문자열이 하드 코딩되어 있으면 언어별로 뷰 코드를 따로 만드는 상황이 발생한다.

- 두 가지 문제를 해결하는 방법은 뷰 코드에서 사용할 문자열을 언어별로 파일에 보관하고 뷰 코드는 언어에 따라 알맞은 파일에서 문자열을 읽어와 출력하는 것이다. 스프링은 자체적으로 이 기능을 제공하고 있기 때문에 약간의 수고만 들이면 각각의 언어별로 알맞은 문자열을 출력하도록 JSP 코드를 구현할 수 있다.

- 문자열을 별도 파일에 작성하고 JSP 코드에서 이를 사용하려면 다음 작업을 하면 된다
	- 문자열을 담은 메시지 파일을 작성한다.
	- 메시지 파일에서 값을 읽어오는 MessageSource 빈을 설정한다.
	- JSP 코드에서 \<spring:message\> 태그를 사용해서 메시지를 출력한다.
	

- 먼저 메시지 파일을 작성해보자. 메시지 파일은 자바의 프로퍼티 파일 형식으로 작성한다. 메시지 파일을 보관하기 위해 src/main/resources에 message 폴더를 생성하고 이 폴더에 label.properties 파일을 생성한다.

- UTF-8 인코딩을 사용해서 label.properties 파일을 작성한다. 이를 위해 label.properties 파일을 열 때 Text Editor를 사용해서 연다. Properties File Editor를 이용하면 '이메일'과 같은 한글 문자가 '\uC774\uBA54\uC77C'와 같은 유니코드값으로 표현되어 알아보기 힘들다.
	- label.properties 파일에서 마우스 오른쪽 버튼을 클릭해서 [Properties] 메뉴를 실행한 뒤 Resource에서 Text File Encoding 값을 other의 'UTF-8'로 변경하면 된다

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%A9%94%EC%84%B8%EC%A7%80%2C%20%EC%BB%A4%EB%A7%A8%EB%93%9C%20%EA%B0%9D%EC%B2%B4%20%EA%B2%80%EC%A6%9D)/images/image1.png)


#### src/main/resources/message/label.properties

```

```
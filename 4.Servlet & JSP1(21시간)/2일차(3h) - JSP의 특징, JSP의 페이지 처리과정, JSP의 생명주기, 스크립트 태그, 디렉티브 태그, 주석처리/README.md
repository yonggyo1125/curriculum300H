## JSP의 특징
- JSP는 서블릿 기술의 확장 
- JSP는 유지 관리가 용이
- JSP는 빠른 개발이 가능
- JSP로 개발하면 코드 길이를 줄일 수 있다.

## JSP의 페이지 처리과정

![JSP 페이지 처리과정](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSP%EC%9D%98%20%ED%8A%B9%EC%A7%95%2C%20JSP%EC%9D%98%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%B2%98%EB%A6%AC%EA%B3%BC%EC%A0%95%2C%20JSP%EC%9D%98%20%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%2C%20%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%ED%83%9C%EA%B7%B8%2C%20%EB%94%94%EB%A0%89%ED%8B%B0%EB%B8%8C%20%ED%83%9C%EA%B7%B8%2C%20%EC%A3%BC%EC%84%9D%EC%B2%98%EB%A6%AC/images/JSP%ED%8E%98%EC%9D%B4%EC%A7%80_%EC%B2%98%EB%A6%AC_%EA%B3%BC%EC%A0%95.png)

1. 웹 브라우저가 웹 서버에 JSP를 요청합니다. 웹 서버는 요청된 Hello.jsp에서 jsp 확장자를 발견하여 JSP 페이지임을 확인하고 웹 서버에 있는 JSP 컨테이너에 전달합니다.
2. JSP 컨테이너는 JSP 페이지를 서블릿 프로그램인 Hello_jsp.java로 변환합니다.
3. JSP 컨테이너가 서블릿 프로그램을 컴파일하여 Hello_jsp.class로 만들고 이를 웹 서버에 전달합니다.
4. 웹 서버는 정적 웹페이지처럼 \*.class의 실행 결과를 웹브라우저에 응답으로 전달하므로 웹 브라우저는 새로 가공된 HTML 페이지를 동적으로 처리한 결과를 보여줍니다.

## JSP 생명 주기
JSP페이지를 컴파일한 \*.class에는 \_jspInit(), \_jspService(), \_jspDestroy() 메서드가 존재하며, JSP 생성부터 파괴까지의 과정에서 다음과 같은 역할을 수행합니다.

![JSP 생명 주기](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/2%EC%9D%BC%EC%B0%A8(3h)%20-%20JSP%EC%9D%98%20%ED%8A%B9%EC%A7%95%2C%20JSP%EC%9D%98%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%B2%98%EB%A6%AC%EA%B3%BC%EC%A0%95%2C%20JSP%EC%9D%98%20%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%2C%20%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%ED%83%9C%EA%B7%B8%2C%20%EB%94%94%EB%A0%89%ED%8B%B0%EB%B8%8C%20%ED%83%9C%EA%B7%B8%2C%20%EC%A3%BC%EC%84%9D%EC%B2%98%EB%A6%AC/images/JSP_%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0.png)

1. 번역(translation)단계
JSP 컨테이너가 JSP 소스 파일을 자바 코드(서블릿)로 변환합니다. 번역 단계에서는 JSP 컨테이너는 JSP 파일을 읽고 구문을 분석합니다. JSP 컨테이너는 JSP 페이지와 페이지에 사용된 태그 라이브러리를 참조하는 사용자 정의 태그 표준 디렉티브, 액션 태그의 구문 정확성을 검증합니다.

2. 컴파일(compilation)단계
JSP 컨테피너가 번역 단계에서 생성된 자바 코드인 서블릿을 컴파일하여 클래스 파일을 생성합니다. 컴파일 단계에서는 자바 코드의 모든 구문을 검사합니다. 즉, JSP 페이지 내의 선언문, 처리문, 표현문 등의 스크립트 태그를 사용하여 삽입된 자바 코드의 구문 오류를 검사합니다.

3. 로딩(loading) 및 초기화(initialization) 단계
JSP 컨테이너가 앞의 두 단계에서 생성된 \*.class를 로딩하고 클래스의 인스턴스를 작성합니다. 이때 인수가 없는 생성자를 사용합니다. Hell_jsp.class가 로딩되고 인스텬스가 만들어집니다. 이제 JSP 컨테이너는 서블릿의 init() 메서드, 즉 jspInit()을 호출하여 인스턴스가 된 객체를 초기화합니다. 일반적으로 초기화는 한 번만 수행이되고 데이터베이스 연결, 파일 열기, 룩업 테이블 생성 등을 초기화합니다.

4. 실행(execution) 단계
각 클라이언트의 요청에 대해 JSP 컨테이너가 요청 및 응답 객체를 전달하는 \_jspService()메서드를 실행합니다. 웹 브라우저가 JSP 페이지를 요청하여 페이지가 로딩 및 초기화돌 때마다 JSP 컨테이너는 JSP에서 \_jspService() 메서드를 호출하여 응답 객체를 전달합니다. 이 단계는 JSP 생명 주기가 끝날 때까지 모든 클라이언트의 요청에 대해 상호 작용을 합니다.

5. 소멸(destruction) 단계
JSP 생명 주기를 완료합니다. JSP 컨터에너는 실행되고 있는 JSP를 \_jspDestroy() 메서드를 사용하여 제거합니다. \_jspDestroy() 메서드를 서블릿의 destory() 메서드에 해당합니다. 이 메서드는 데이터베이스 연결 해제 또는 열려 있는 파일 닫기 등을 수행해야 할 때 jspDestroy() 메서드를 오버라이딩 합니다. JSP 컨테이너가 해당 서블릿 인스턴스를 제거할 때 어떤 활동을 정리하기 위해 \_jspDestory() 메서드를 호출합니다.

> \_jspInit(), \_jspDestory() 메서드는 컨테이너가 기본 기능을 제공하기 때문에 오버라이딩이 선택 사항이지만 기본적으로 \_jspService() 메서드는 컨테이너가 추가하기 때문에 오버라이딩 할 수 없습니다.


## 스크립트 태그

### 스크립트 태그의 종류

|스크립트 태그|형식|설명|
|----|---|----------|
|선언문(declaration)|<%! ... %>|자바 변수나 메서드를 정의하는 데 사용합니다.|
|스크립틀릿(scriptlet)|<% .... %>|자바 로직 코드를 작성하는데 사용합니다.|
|표현문(expression)|<%= ... %>|변수, 계산식, 메서드 호출 결과를 문자열 형태로 출력하는 데 사용합니다.|

### 선언문(declaration)

- 선언문(declaration)태그는 변수나 메서드 등을 선언하는 태그
- 선언문 태그에 선언된 변수와 메서드는 서블릿 프로그램으로 번역될 때 _jspService() 메서드 외부에 배치되므로 JSP 페이지의 임의의 위치에서 선언할 수 있습니다.
- 스크립틀릿 태그보다 나중에 선언해도 스크립틀릿 태그에서 사용할 수 있습니다.
- 선언문 태그로 선언된 변수는 서블릿 프로그램으로 번역될 때 클래스 수준의 멤버 변수가 되므로 전역 변수로 사용됩니다.
- 선언문 태그로 선언된 메서드는 전역변수처럼 전역 메서드로 사용됩니다.

#### day02/declaration01.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!int data = 50;%>
	<%
		out.println("Value of the variable is:" + data);
	%>
</body>
</html>
```

#### day02/declaration02.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!int sum(int a, int b) {
		return a + b;
	}%>
	<%
		out.println("2 + 3 = " + sum(2, 3));
	%>
</body>
</html>
```

#### day02/declaration03.jsp
```
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%!String makeItLower(String data) {
		return data.toLowerCase();
	}%>
	<%
		out.println(makeItLower("Hello World"));
	%>
</body>
</html>
```

### 스크립틀릿(scriptlet)

### 표현문(expression)

## 디렉티브 태그

### page

### include

### taglib

## JSP의 주석 처리
# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1-gr9TNZ8g3lCWoCsIijcFFU7xABVeIeX?usp=sharing)


# 웹 MVC
- MVC는 Model, View, Controller의 약자로 웹 애플리케이션을 비즈니스 로직, 프레젠테이션 로직, 데이터로 분리하는 디자인 패턴입니다.
- 웹 애플리케이션에서는 일반적으로 애플리케이션을 비즈니스 로직, 프레젠테이션 로직, 요청 처리 데이터로 분류
- 비즈니스 로직은 애플리케이션의 데이터, 즉 고객, 제품, 주문 정보의 조작에 사용되는 용어
- 프레젠테이션은 애플리케이션이 사용자에게 어떻게 표시되는지, 즉 위치, 폰트, 크기를 나타냅니다.
- 요청 처리 데이터는 비즈니스 로직과 프레젠테이션 파트를 함께 묶는 것
- 웹 애플리케이션에 MVC 패턴을 사용하면 유지 보수가 용이하고 쉽게 확장 및 테스트할 수 있습니다.


- <b>모델(model)</b> : 애플리케이션의 데이터와 비즈니스 로직을 담는 객체입니다.
- <b>뷰(view) </b> : 사용자에게 모델의 정보(데이터)를 보여주는 약할을 합니다. 비즈니스 로직을 포함하지 않으며, 하나의 모델을 다양한 뷰에서 사용할 수 있습니다.
- <b>컨트롤러(controller)</b> : 모델과 뷰 사이에 어떤 동작이 있을 때 조정하는 역할을 합니다. 웹으로부터 받은 요청에 가장 적합한 모델을 생성하는 것을 처리하는 역할과 사용자에게 응답하는 적절한 뷰를 선택하여 해당 모델을 전달하는 역할을 합니다,

## MVC 패턴 구조

### 모델1
- 모델1은 기존의 JSP로만 구현한 웹 애플리케이션으로, 웹브라우저의 요청을 JSP 페이지가 받아서 처리하는 구조
- JSP 페이지에 비즈니스 로직을 처리하는 코드와 웹 브라우저에 결과를 출력하는 코드가 섞입니다.
- JSP가 핵심 역할을 수형하며, 웹 브라우저가 요청한 작업을 자바빈즈나 서비스 클래스를 사용하여 처리합니다.
- 모델1은 모델2와 달리 대부분의 처리가 JSP 자체에 의해 수행됩니다.

#### 모델1의 구조와 요청 처리 흐름 

![model1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9B%B9MVC/images/model1.png)

#### 모델1의 장점
- 구조가 단순하여 개발자의 수준이 낮아도 쉽게 익힐 수 있어 구현이 용이합니다.
- 개발 초기에 복잡한 설정이 필요 없어 빠른 개발이 가능합니다.
- 개발 속도가 빠릅니다.

#### 모델1의 단점
- 출력을 위한 뷰 코드와 로직 처리를 위한 자바 코드(컨트롤러)가 섞여 있어 분업이 용이하지 않습니다.
- 코드가 복잡하여 유지보수가 어렵습니다.
- 자바 코드와 JSP, HTML이 섞이기 때문에 코드가 복잡합니다.


### 모델2
- 모델2는 클라이언트 요청 처리, 응답 처리, 비즈니스 로직 처리 부분을 모듈화한 구조입니다.
- 모델1과 달리 요청에 대한 로직을 처리할 자바빈즈나 자바 클래스인 모델, 요청 결과를 출력하는 JSP 페이지인 뷰, 모든 흐름을 제어하는 서블릿인 컨트롤러로 나뉘어 웹 브라우저가 요청한 작업을 처리합니다.
- 모델2에서는 서블릿이 중요한 역할을 하며, 웹 브라우저의 요청을 알맞게 처리한 후 그 결과가 보여줄 JSP 페이지로 포워딩하고 이를 통해 요청 흐름을 받은 JSP 페이지는 결과 화면을 웹 브라우저에 전송합니다. 즉, 서블릿이 비즈니스 로직 부분을 처리합니다.

#### 모델2의 구조와 요청 처리 흐름

![model2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/4%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%9B%B9MVC/images/model2.png)

- (1) 웹 브라우저가 웹 서버에 웹 애플리케이션 실행을 요청하면 웹 서버는 요청을 처리할 수 있는 컨트롤러(서블릿)을 찾아서 요청을 전달합니다.
- (2) 컨트롤러(서블릿)는 모델 자바 객체의 메서드를 호출합니다.
- (3) 데이터를 가공하여 값 객체를 생성하거나 JDBC를 사용하여 데이터베이스와의 인터렉션을 통해 값 객체를 생성합니다.
- (4) 업무 수행을 마친 결과 값을 컨트롤러에 반환합니다.
- (5) 컨트롤러는 모델로부터 받은 결과 값을 뷰에 전달합니다.
- (6) JSP는 전달받은 값을 참조해서 출력할 결과를 만들어 웹 서버에 전달하고, 웹 브라우저는 웹 서버로부터 결과 값을 받아 화면에 출력합니다.

#### 모델2의 장점 
- 출력을 위한 뷰 코드와 로직 처리를 위한 자바 코드를 분리하기 때문에 모델1 보다 코드가 간결합니다.
- 뷰와 로직 처리에 대한 분업이 용이합니다.
- 기능에 따라 분리되어 있기 때문에 유지 보수가 용이합니다.
- 확장이 용이합니다.

#### 모델2의 단점
- 구조가 복잡하여 습득하기 어렵고 작업량이 많습니다.
- 개발 초기에 설정이 필요한 부분이 모델 1보다 많기 때문에 실질적인 작업을 시작하기까지 시간이 걸립니다.
- 코드가 분리됨으로써 관리해야 할 파일이 많아집니다.

## MVC 패턴 구현하기

- web.xml 파일에 서블릿 구성하기<br>웹 MVC 에서는 클라이언트로부터 컨트롤러인 서블릿을 통해 요청을 받아야 합니다. 이를 위해 웹 애플리케이션(톰캣 기반)의 /WEB-INF/ 폴더에 있는 환경 설정 파일인 web.xml에 서블릿 클래스와 웹 브라우저의 요청 URL 패턴을 등록해야 합니다.

### <servlet>요소로 서블릿 클래스 등록하기
```
<servlet>
   <servlet-name>서블릿 이름</servlet-name>
   <servlet-class>서블릿 클래스(패키지 이름.클래스)</servlet-class>
   [<init-param> 
      <param-name>매개변수 이름</param-name>
      <param-value>매개변수 값</param-value>
   </init-param>]
</servlet>
```

- \<servlet\> 요소로 서블릿 클래스를 등록한 예
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	<servlet>
	   <servlet-name>myController</servlet-name>
	   <servlet-class>ch18.com.controller.MyController</servlet-class>
	</servlet>
</web-app>
```

### <servlet-mapping> 요소로 요청 URL 패턴 설정하기
```
<servlet-mapping>
   <servlet-name>서블릿 이름</servlet-name>
   <url-pattern>요청할 URL</url-pattern>
</servlet-mapping>
```

- \<servlet-mapping\> 요소로 요청 URL 패턴을 등록한 예
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	<servlet>
	   <servlet-name>myController</servlet-name>
	   <servlet-class>ch18.com.controller.MyController</servlet-class>
	</servlet>
	<servlet-mapping>
	   <servlet-name>myController</servlet-name>
	   <url-pattern>*.do</url-pattern>
	</servlet-mapping>
</web-app>
```

### 컨트롤러 생성하기
- 컨트롤러는 뷰와 모델 간의 인터페이스 역할을 하여 웹 브라우저의 모든 요청 URL을 받아들이고 요청 URL과 함께 전달되는 요청 파라미터를 받아 처리하는 서블릿 클래스입니다.
- 컨트롤러는 요청된 데이터를 처리하기 위해 모델로 보내고 처리된 결과를 받아서 응답할 JSP 페이지로 이동합니다.

#### 서블릿 클래스 생성하기
- HttpServlet 클래스를 확장하여 생성
- 전송되는 GET 방식과 POST 방식에 따라 각각 doGet(), doPost() 메서드를 통해 요청작업을 수행한 후 웹브라우저에 응답합니다.

```
public class 서블릿 이름 extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // Get 방식으로 전송되는 요청을 처리
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Post 방식으로 전송되는 요청을 처리
  }
}
```

#### 페이지 이동하기
- 서블릿 클래스에서 웹브라우저로부터 요청된 처리 결과를 보여줄 응답 페이지로 이동하는 형식은 다음과 같습니다.
```
RequestDispatcher dispatcher = request.getRequestDispatcher(“JSP 페이지”);
dispatcher.forward(request, response);
```
- 현재 뷰 페이지에서 이동할 뷰 페이지에 요청 정보를 그대로 전달하며, 뷰 페이지가 이동해도 처음에 요청된 URL을 계속 유지하기 위해 포워딩 방식을 사용합니다.

- 컨트롤러인 서블릿 클래스 MyController 생성 예
```
public class MyController extends HttpServlet {
   protected void doGet(HttpServletRequest response) throws ServletException, IOException {
      request.setAttribute(“message”, “Hello! Java Server Page.”);
      RequestDispatcher rd = request.getRequestDispatcher(“view.jsp”);
      rd.forward(request, response);
   }
}
```

### 모델 생성하기
- 모델은 웹 애플리케이션의 비즈니스 로직을 포함하는 데이터로 웹 애플리케이션의 상태를 나타냅니다.
- 모델은 데이터베이스에서 데이터를 가져오거나 웹 애플리케이션에 필요한 서비스를 수행하는 간단한 자바 클래스로 자바빈즈를 의미합니다.
- 자바빈즈는 데이터를 담을 멤버 변수인 프로퍼티와 데이터를 가져오거나 저장하는 Getter/Setter 메서드로 구성됩니다.


### 뷰 생성하기
- 뷰는 웹브라우저의 요청을 처리한 결과를 사용자에게 보여주는 JSP 페이지를 의미합니다.
- 뷰는 JSP가 제공하는 태그를 사용하여 컨트롤러가 전송한 모델 데이터를 웹 브라우저에 출력합니다. 

- MyController 클래스로부터 뷰 페이지에 전달된 message 속성 값을 출력하는 예

#### view.jsp
```
<%@ page contentType=“text/html; charset=utf-8” %>
<%
   String msg = (String) request.getAttribute(“message”);
   out.println(msg);
%>
```

#### WEB-INF/web.xml 
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	<servlet>
      <servlet-name>myController</servlet-name>
      <servlet-class>day04.com.controller.ControllerServlet</servlet-class>
   </servlet>
   
   <servlet-mapping>
      <servlet-name>myController</servlet-name>
      <url-pattern>/day04/ControllerServlet</url-pattern>
   </servlet-mapping>
</web-app>
```

#### /src/main/java/day04/com/model/LoginBean.java
```
package day04.com.model;

public class LoginBean {
    private String id;
    private String password;

    public String getId() {
       return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public String getPassword() {
       return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {
        if (password.equals(“admin”))
	   return true;
        else 
        return false;
    }
}
```

#### /src/main/java/day04/com/controller/ControllerServlet.java
```
package day04.com.controller

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import day04.com.model.LoginBean;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setContentType(“text/html; charset=utf-8”);
  
       String id = request.getParameter(“id”);
       String password = request.getParameter(“passwd”);

       LoginBean bean = new LoginBean();
       bean.setId(id);
       bean.setPassword(password);
       request.setAttribute(“bean”, bean);

       boolean status = bean.validate();

       if (status) {
          RequestDispatcher rd = request.getRequestDispatcher(“mvc_success.jsp”);
          rd.forward(request, response);
       } else {
          RequestDispatcher rd = request.getRequestDispatcher(“mvc_error.jsp”);
          rd.forward(request, response);
       } 
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
```

#### day04/mvc.jsp
```
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
   <form method=“post” action=“ControllerServlet”>
      아이디 : <input type=“text” name=“id”><br>
      비밀번호 : <input type=“password” name=“passwd”><br>
      <input type=“submit” value=“전송”>
   </form>
</body>
</html>
```
#### day04/mvc_success.jsp 
```
<%@ page contentType=“text/html; charset=utf-8” %>
<%@ page import=“day04.com.model.LoginBean” %>
<html>
<body>
   로그인 성공했습니다<br>
   <%
       out.print(“아이디 : ” + bean.getId()); 
   %>
</body>
</html>
```

#### day04/mvc_error.jsp
```
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
   아이디의 비밀번호를 확인해주세요.<br>
   <%@ include file=“mvc.jsp” %>
</body>
</html>
```
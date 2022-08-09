# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1-7xY1joTEf5fJJ-gV6JUlo3jq6Km1zky?usp=sharing)


# 필터와 래퍼
- 웹 사이트를 개발하다보면 여러 웹 컴포넌트(JSP 페이지와 서블릿 클래스의 통칭)에 똑같은 사전작업이나 사후 작업을 해야 하는 경우가 종종 있습니다. 예를 들어 어떤 웹 컴포넌트는 로그인한 다음에만 사용할 수 있어야 하기 때문에 먼저 로그인 여부를 체크해야 합니다. 그리고 어떤 웹 컴포넌트는 유료 서비스를 제공하기 때문에 실행 후에 요금을 부과하는 처리를 해야 합니다.

- 각각의 웹 컴포넌트 마다 이런 코드를 따로 작성해 둔다면, 프로그래밍 분량이 굉장히 많아질 것이고, 코드 간의 일관성을 유지하기도 힘들어질 것입니다. 그러므로 이런 코드는 한 곳에 작성해 놓고 공통적으로 사용할 수 있으면 편리할 것입니다. 필터와 래퍼는 그러한 방법을 제공하는 기술입니다.

- 필터(fiilter)란 글자 그대로 여과기 역할을 하는 프로그램입니다. 이프로그램은 자바 클래스 형태로 구현해야 하는데, 이 클래스를 필터 클래스(filter class)라고 합니다. 하지만 이 클래스가 클래스 형태 그대로 바로 실행되는 것은 아닙니다. 

- 웹 컨터이너는 이 클래스를 가지고 필터 객체를 만들고, 그 다음에 그 필터 객체를 초기화합니다. 그리고 나서야 비로소 그 객체가 필터로서 실행될 수 있습니다. 이것은 서블릿 클래스가 서블릿 객체로 만들어지고, 초기화되고 난 다음에 실행되는 것과 마찬가지 입니다.

#### 필터 클래스, 필터 객체, 필터 

![filter1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/filter1.png)

- 초기화된 필터는 웹 브라우저와 웹 컴포넌트 사이에 위치하게 됩니다.
- 웹 브라우저가 웹 컴포넌트를 호출했을 때 그 대신 필터가 호출되며, 필터는 사전작업을 수행한 다음에 웹 컴포넌트를 호출할 것입니다.
- 그때부터 웹 컴포넌트가 시작되어서 실행되고, 실행이 끝나고 나면 제어 흐름은 다시 필터로 되돌가갈 것입니다.  그러면 필터는 필요한 사후작업을 수행한 다음에 웹브라우저로 응답을 보낼 것입니다.

#### 필터가 위치하는 곳 

![filter2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/filter2.png)

- 로그인 여부의 확인이나 요금 부과 처리와 같은 일은 필터 클래스 안에 구현해 놓으면 됩니다.
- 하지만 필터 클래스만 가지고는 할 수 없는 일도 있습니다. 웹 브라우저와 웹 컴포넌트 사이를 오가는 데이터를 변경하는 일은 할 수 없습니다.
- 예를 들어 웹 브라우저와 웹 컴포넌트 사이를 오가는 메시지를 암호화하거나, 메시지에 포함된 데이터의 일부를 걸러서 전달되지 못하도록 막는 일은 할 수 없습니다.
- 그런 일을 하기 위해서는 필터뿐만 아니라 래퍼도 필요합니다.

- 래퍼(wrapper)란 글자 그대로 무언가를 포장하는 프로그램입니다.  즉, 웹브라우저와 웹 컴포넌트 사이를 오가는 요청 메시지와 응답 메시지를 포장합니다.
- 래퍼는 래퍼 클래스(wrapper class) 형태로 구현되고, 포장되는 객체의 종류에 따라 요청 래퍼 클래스와 응답 래퍼 클래스로 나뉘어 집니다.
- 웹 컨테이너는 웹 브라우저로부터 요청을 받으면 요청 객체와 응답 객체를 만들어서 웹 컴포넌트로 전달합니다. 그것이 바로 doGet, doPost 메서드의 파라미터 변수나 request, response 내장 객체를 통해 서블릿과  JSP 페이지로 전달되는 값입니다. 하지만 웹 브라우저와 웹 컴포넌트 사이에 필터가 있으면 이 두 객체는 필터로 넘겨집니다.
- 이때 필터가 요청 래퍼 클래스와 응답 래퍼 클래스를 가지고 이 두 객체를 포장한 다음에 웹 컴포넌트로 전달하면 웹 컴포넌트는 새로운 두 객체를 기존의 요청 객체와 응답 객체인 줄 알고 사용할 것입니다.

#### 요청 래퍼 객체와 응답 래퍼 객체 

![filter3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/filter3.png)

- 래퍼 클래스 안에 입력 데이터와 출력 데이터를 변형하는 로직을 구사해 놓으면 웹 컴포넌트가 요청 래퍼 객체와 응답 래퍼 객체를 사용할 때 그 로직이 실행될 것이고 그 결과 변형된 데이터가 웹 브라우저 또는 웹 컴포넌트로 전송될 것입니다.

## 필터 클래스의 작성, 설치, 등록 

- 필터 클래스를 작성할 때는 서블릿 규격서에 정해져 있는 규칙을 지켜야 합니다.
- 그중 가장 중요한 규칙은 **javax.servlet.Filter 인터페이스**를 구현해야 한다는 것입니다.
- [Filter 인터페이스](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/Filter.html)

![Filter 인터페이스](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/filter5.png)

-  **doFilter 메서드**
	- 가장 중요한 메서드이며 웹 브라우저가 웹 컨테이너로 요청을 보냈을 때 호출되는 것이 바로 이 메서드입니다.
	- 웹 컴포넌트에 대한 사전 작업과 사후 작업, 그리고 웹 컴포넌트를 호출하는 일은 이 메서드 안에 구현해야 합니다.
- init 메서드
	- 필터 클래스는 필터 객체가 만들어지고 초기화된 다음에 실행되는데, 바로 이 초기화 작업을 할 때 호출되는 메서드입니다.
- destroy 메서드
	- 웹 컨테이너가 필터를 더 이상 필요없다고 판단해서 제거하기 직전에 호출되는 메서드입니다. 
	- 필터의 전체 라이프 사이클 동안 딱 한번만 실행하면 되는 로직은 이 두 메서드 안에 써 놓으면 됩니다.
	
```java
public class SimpleFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
	}
	
	public void destroy() {
		
	}
}
```


- 모든 필터 클래스에 공통적으로 기술해야 하는 명렬문이 하나 있는데, 웹 컴포넌트를 호출하는 명령문입니다. 이 명령문은 doFilter 메서드의 파라미터를 이용해서 만들어야 합니다.
- doFilter 메서드에는 세 개의 매개변수가 있습니다. 이 세 매개변수의 값은 모두 웹 컨테이너가 필터로 넘겨주는 것인데, 이 중 첫 번째와 두 번째 파라미터는 요청 객체와 응답 객체입니다.
- 필터가 없다면 이 두 객체는 웹 컨테이너가 웹 컴포넌트로 직접 넘겨줬겠지만, 필터가 있기 때문에 이 메서드로 전달된 것입니다.

```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

}
```
- ServletRequest request : 웹 컨테이너가 넘겨주는 요청 객체
- ServletResponse response : 웹 컨테이너가 넘겨주는 응답 객체
- FilterChain chain : 필터가 웹 컴포넌트를 호출할 때 가장 중요한 매개변수

#### 필터 체인(filter chain)
- 하나의 웹 컴포넌트에 대해 여러 개의 필터가 실행될 수도 있습니다. 그럴 때는 미리 정해 놓은 순서에 따라 첫 번째 필터가 두 번째 필터를 호출하고, 두 번째 필터가 세 번째 필터를 호출하는 식으로 진행되다고 마지막 필터가 웹 컴포넌트를 호출합니다. 
- 그러면 웹 컴포넌트는 자기의 할 일을 하고 나서 자신을 호출했던 마지막 필터로 응답을 보냅니다.  그러면 그 필터는 자신을 호출했던 필터로 응답을 보내고 응답을 받은 필터는 또 자신을 호출했던 필터로 응답을 보냅니다.그런식으로 계속 진행되어서 결국 웹 브라우저로 응답이 도달합니다. 
- 이렇게 호출과 응답이 이어진 필터들은 체인 형태를 이루기 때문에 이것을 <b>필터 체인(filter chain)</b>이라고 부릅니다.

#### 연속해서 호출되는 필터들로 이루어지는 필터 체인
![filter4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/filter4.png)

- 웹 컨테이너는 필터가 실행되기 전에 필터 체인에 대한 정보를 수깁해서 FilterChain 객체로 만든 다음에 doFilter 메서드로 전달합니다. 이 객체는 필터 체인의 다음번 멤버를 호출할 때 필요한데, 구체적인 사용 방법은 다음과 같습니다.
```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	// 사전 작업 기술 
	... 
	
	chain.doFilter(request, response);
	
	// 사후 작업 기술
	... 
	
}
```
- <b>chain.doFilter(request, response);</b> : 필터의 다음번 멤버를 호출하는 역할을 하는데, 그 멤버는 필터가 될 수도 있고 웹 컴포넌트가 될 수도 있습니다.
- 위에서 처럼 chain.doFilter 메서드 호출문을 기술한 다음에, 그 앞과 뒤에 웹 컴포넌트에 대한 사전작업과 사후작업에 해당하는 로직을 기술하면 필터 클래스가 완성됩니다.

#### src/main/java/myfilter/SimpleFilter.java
```java
package myfilter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import java.io.IOException;

public class SimpleFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("이제 곧 웹 컴포넌트가 시작됩니다.");
		chain.doFilter(request, response);
		System.out.println("이제 막 웹 컴포넌트가 완료되었습니다.");
	}
	
}
```
-  필터를 적용하려면 web.xml 파일에 필터 클래스를 등록해야 합니다.
```xml
<web-app ...>
	<filter>
		// 필터를 등록하는 요소
	</filter>
	<filter-mapping>
		// 필터를 적용할 웹 컴포넌트를 지정하는 요소
	</filter-mapping>
</web-app>
```

- \<filter\> 요소 안에는 \<filter-name\>과 \<filter-class\>라는 두 개의 하위 요소를 써야 합니다.
	- \<filter-name\> : 필터 이름
	- \<filter-class\> : 필터 클래스
```
<filter>
	<filter-name>simple-filter</filter-name>
	<filter-class>myfilter.SimpleFilter</filter-class>
</filter>
```

- \<filter-mapping\> 요소 안에도 기본적으로 두 개의 하위 요소를 써야 합니다.
	- \<filter-name\> : \<filter\>요소의 \<filter-name\>에 썼던 것과 동일한 필터 이름을 써야 합니다
	- \<servlet-name\> 또는 \<url-pattern\> 중 하나가 될 수 있는데, 이 중 어떤 것을 써야 할지는 몇 개의 웹 컴포넌트에 필터를 적용할 것인지에 따라 달라집니다.
	- 필터를 하나의 웹 컴포넌트에 적용하고자 할 때는 \<servlet-name\> 하위 요소를 사용하면 됩니다. 이 요소 안에는 필터를 적용할 서블릇의 이름을 써야 하는데, 그 이름은 서블릿을 등록할 때와 동일한 이름이어야 합니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<servlet-name>hello-servlet</servlet-name>
	</filter-mapping>
	```
	- 필터를 여러 개의 웹 컴포넌트에 한꺼번에 적용하려면 \<url-pattern\> 하위 요소를 하용해야 합니다.
	- 이 요소 안에는 와일드카드 문자(\*)를 포함한 URL패턴을 쓸 수 있습니다. 예를 들어 같은 웹 애플리케이션 디렉토리에 있는 모든 웹 컴포넌트에 필터를 적용하려면 /\*라고 쓰면 됩니다.
	- 여기에서 처음에 오는 슬래시(/) 기호는 웹 애플리케이션의 루트 디렉토리를 의미합니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	```
	
	
	- 필터를 같은 웹 애플리케이션 디렉토리에 있는 모든 JSP 페이지에 적용하려면 \<url-pattern\> 요소 안에 \*.jsp라고 쓰면 됩니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>*.jsp</url-pattern>
	</filter-mapping>
	```
	
	- http://localhost:8080/.../sub1/ 이라는 URL로 시작하는 모든 웹 컴포넌트에 대해 필터를 적용하고 싶은 경우 다음과 같이 web.xml 파일에 추가하면 됩니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>/sub1/*</url-pattern>
	</filter-mapping>
	```
	
	- \<filter-mapping\> 요소 안에는 여러 개의 \<url-pattern\> 하위 요소를 쓸 수 있습니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>/sub1/*</url-pattern>
		<url-pattern>/sub2/*</url-pattern>
	</filter-mapping>
	```
	- \<filter-mapping\> 요소 안에 \<servlet-name\>과 \<url-pattern\> 요소를 섞어서 쓸 수도 있습니다.
	```
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>/sub1/*</url-pattern>
		<url-pattern>/sub2/*</url-pattern>
		<servlet-name>hello-servlet</servlet-name>
	</filter-mapping>
	```
	
#### WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	
    <filter>
    	<filter-name>simple-filter</filter-name>
    	<filter-class>myfilter.SimpleFilter</filter-class>	
    </filter>
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>*.jsp</url-pattern>
	</filter-mapping>    
</web-app>
```

#### Simple.jsp
```html
<%@page contentType="text/html; charset=utf-8" %>
<% System.out.println("이것은 JSP 페이지 안에서 출력하는 메시지 입니다."); %>
<html>
	<body>
		이것은 필터 테스트를 위해 만들어진 JSP 페이지 입니다.
	</body>
</html>
```

- 실행 결과
```
이제 곧 웹 컴포넌트가 시작됩니다.
이것은 JSP 페이지 안에서 출력하는 메시지 입니다.
이제 막 웹 컴포넌트가 완료되었습니다.
```

#### src/main/java/mysevlet/SimpleServlet.java
```java
package myservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("이것은 서블릿 클래스 안에서 출력하는 메세지입니다.");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>필터 테스트용 서블릿</title></head>");
		out.println("<body>");
		out.println("이것은 필터 테스트를 위해 만들어진 서블릿 입니다.");
		out.println("</body>");
		out.println("</html>");
	}
}
```

#### WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	 <servlet>
    	<servlet-name>simple-servlet</servlet-name>
    	<servlet-class>myservlet.SimpleServlet</servlet-class>
    </servlet>
    <servlet-mapping>
    	<servlet-name>simple-servlet</servlet-name>
    	<url-pattern>/simple</url-pattern>
    </servlet-mapping>
    <filter>
    	<filter-name>simple-filter</filter-name>
    	<filter-class>myfilter.SimpleFilter</filter-class>	
    </filter>
	<filter-mapping>
		<filter-name>simple-filter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
</web-app>
```

- 실행 결과
```
이제 곧 웹 컴포넌트가 시작됩니다.
이것은 서블릿 클래스 안에서 출력하는 메세지입니다.
이제 막 웹 컴포넌트가 완료되었습니다.
```

### 필터 클래스의  init 메서드와 destroy 메서드
- 필터의 라이프 사이클 동안 단 한 번만 실행하면 되는 코드는 필터 클래스의 init 메서드나 destroy 메서드에 기술하는 것이 좋습니다.
- 예를 들면 doFilter 메서드 안에서 파일로 메세지를 출력하는 일을 한다면 그 파일을 여는 코드는 init 메서드 안에, 닫는 코드는 destroy 메서드 안에 기술하는 것이 좋습니다.


#### src/main/java/myfilter/LogMessageFilter.java - 미완성
```java
package myfilter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import java.io.*;

public class LogMessageFilter implements Filter {
	PrintWriter writer;
	
	public void init(FilterConfig config) throws ServletException {
		try {
			writer = new PrintWriter(new FileWriter("D:\\logs\\myfilter.log", true), true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		writer.println("에제 곧 웹 컴포넌트가 시작될 것입니다.");
		writer.flush();
		chain.doFilter(request, response);
		writer.println("이제 막 웹 컴포넌트가 완료되었습니다.");
		writer.flush();
	}
	
	public void destory() {
		writer.close();
	}
}
```
- 위 예제에서는 출력한 로그 메세지는 D:\logs 디렉토리에 있는 myfilter.log 파일에 기록될 것 입니다. 
- 그러나 소스 코드 안에 로그파일의 이름이 고정되어 있기 때문에 파일의 이름을 바꾸거나, 다른 디렉토리로 옮길 때 소스 코드를 수정하고, 컴파일하고 설치하는 작업을 다시 해야 합니다. 이런 불편함을 해소하기 위해서는 로그 파일명의 경로명을 필터의 초기화 파라미터로 web.xml 파일에 등록해 두는 것이 좋습니다.

- 필터의 초기화 파라미터란 필터 클래스에서 사용할 데이터를 web.xml 파일 안에 이름-값 쌍으로 지정해 놓은 것을 말합니다. 필터의 초기화 파라미터는 필터를 등록하는 \<filter\> 요소 안에 \<init-param\> 하위 요소를 추가해서 등록할 수 있습니다.
- 이 요소에는 다시 \<param-name\>과 \<param-value\> 하위 요소를 추가해야 하고, 그 안에 각각 초기화 파라미터의 이름과 값을 서 넣으면 됩니다.

#### WEB-INF/web.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	<filter>
		<filter-name>log-filter</filter-name>
		<filter-class>myfilter.LogMessageFilter</filter-class>
		<init-param>
			<param-name>FILE_NAME</param-name>
			<param-value>D:\\logs\\myfilter.log</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>log-filter</filter-name>
		<url-pattern>*.jsp</url-pattern>
	</filter-mapping>
</web-app>
```

- 필터 클래스 안에서 필터의 초기화 파라미터를 읽어오려면 init 메서드에 있는 FilterConfig 객체에서 getInitParameter 메서드를 호출하면서 초기화 파라미터의 이름을 넘겨주면 초기화 파라미터의 값이 반환됩니다.
```java
String filename = config.getInitParameter("FILE_NAME");
```

#### src/main/java/myfilter/LogMessageFilter.java
```java
package myfilter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import java.io.*;

public class LogMessageFilter implements Filter {
	PrintWriter writer;
	
	public void init(FilterConfig config) throws ServletException {
		String filename = config.getInitParameter("FILE_NAME");
		if (filename == null)
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		
		try {
			writer = new PrintWriter(new FileWriter(filename, true), true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		writer.println("에제 곧 웹 컴포넌트가 시작될 것입니다.");
		writer.flush();
		chain.doFilter(request, response);
		writer.println("이제 막 웹 컴포넌트가 완료되었습니다.");
		writer.flush();
	}
	
	public void destory() {
		writer.close();
	}
}
```

#### \<dispatcher\> 요소에 대하여
- 웹 컴포넌트를 호출하는 방법은 크게 네 가지입니다. 
	- 브라우저를 이용해서 호출하는 방법
	- forward 메서드를 통해 호출하는 방법
	- include 메서드를 통해 호출하는 방법
	-  익셉션이 발생했을 때 웹 컨테이너가 자동으로 호출하는 방법
- 이 네가지 방법에 따라 필터를 선택적으로 적용할 수 있습니다. 그때 \<dispatcher\> 요소를 사용하시면 됩니다.

- web.xml 파일의 \<filter-mapping\> 요소 안에 \<dispatcher\>라는 요소를 추가하고, 거기에 REQUEST, FORWARD, ERROR 중 한 값을 쓰면됩니다.
	- REQUEST : 웹 브라우저에 의한 호출
	- FORWARD : forward 메서드에 의한 호출
	- INCLUDE : include 메서드에 의한 호출
	- ERROR : 에러 발생에 의한 호출
	
```xml
<filter-mapping>
	<filter-name>log-filter</filter-name>
	<url-pattern>/sub2/*</url-pattern>
	<dispatcher>FORWARD</dispatcher>
</filter-mapping>
```
- 위에서 처럼 \<dispatcher\> 요소를 기술하면  forward 메서드를 통해 웹 컴포넌트를 호출했을 때만 log-filter가 적용될 것이고, 다른 방법으로 호출했을 때는 적용되지 않을 것이다.
- \<filter-mapping\> 요소 안에 여러 개의 \<dispatcher\> 하위 요소를 쓸 수도 있습니다. 
```xml
<filter-mapping>
	<filter-name>log-filter</filter-name>
	<url-pattern>/sub2/*</url-pattern>
	<dispatcher>FORWARD</dispatcher>
	<dispatcher>INCLUDE</dispatcher>
</filter-mapping>
```
- 위 코드에서처럼 두 개의 \<dispatcher\>요소를 기술하면 forward 또는 include 메서드를 통해 웹 컴포넌트를 호출했을 때는 필터가 적용될 것이고, 그 밖의 경우에는 필터가 적용되지 않을 것입니다.

### 요청 메시지와 응답 메세지에 포함된 정보 조회하기
- 다음에 작성할 예제는 웹 브라우저의 IP 주소와 응답 메세지에 포함된 컨텐트 타입을 포함한 메세지를 로그 파일에 기록하는 필터 클래스 입니다.
- 이런 정보를 구하기 위해서는 필터 클래스의 doFilter 메서드 안에서 ServletRequest 객체와 ServletResponse 객체를 이용하면 됩니다.
- 웹 브라우저의 IP 주소를 구하기 위해서는 ServletRequest 객체에 대해 getRemoteAddr 이라는 메서드를 호출하시면 됩니다.

```
String clientAddr = request.getRemoteAddr();
```
- 응답 메세지에 포함된 컨텐트 타입을 구하기 위해서는 ServletResponse 객체에 대해 getContentType 이라는 메서드를 호출하시면 됩니다.
- 이 메서드를 호출할 때는 주의할 점이 있는데, ServletResponse 객체에 대해 호출하는 메서드는 반드시 chain.doFilter 메서드보다 나중에 호출해야 합니다.
- 그렇게 해야만 ServletResponse 객체에 응답 메시지 관련 정보가 저장되기 때문입니다.

#### src/main/java/myfilter/NewLogMessageFilter.java
```java
package myfilter;

import javax.servlet.*;
import java.io.*;
import java.util.*;

public class NewLogMessageFilter implements Filter {
	PrintWriter writer;
	
	public void init(FilterConfig config) throws ServletException {
		String filename = config.getInitParameter("FILE_NAME");
		if (filename == null)
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		try {
			writer = new PrintWriter(new FileWriter(filename, true), true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Calendar now = Calendar.getInstance();
		writer.printf("현재일시 : %TF %TT %n", now, now);
		String clientAddr = request.getRemoteAddr();
		writer.printf("클라이언트 주소: %s %n", clientAddr);
		chain.doFilter(request, response);
		String contentType = response.getContentType();
		writer.printf("문서의 컨텐트 타입 : %s %n", contentType);
		writer.println("-------------------------------------------------------------------");
	}
	
	public void destroy() {
		writer.close();
	}
}
```

#### WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	<filter>
		<filter-name>new-log-filter</filter-name>
		<filter-class>myfilter.NewLogMessageFilter</filter-class>
		<init-param>
			<param-name>FILE_NAME</param-name>
			<param-value>D:\\logs\\myfilter.log</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>new-log-filter</filter-name>
		<url-pattern>/sub3/*</url-pattern>
	</filter-mapping>
</web-app>
```

#### .../sub3/Greetings.jsp
```html
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<head><title>인사말</title></head>
	<body>
		안녕하세요, 여러분<br><br>
		여러분은 지금 필터 테스트를 위한 JSP 페이지를 실행 중 입니다.
	</body>
</html>
```

#### 필터 체인의 필터 순서가 정해지는 방법
- 필터 체인에 속하는 필터들의 순서는 기본적으로 web.xml 파일에 있는 \<filter-mapping\> 요소의 순서에 따라 결정됩니다.
- 예를 들어 web.xml 파일에 다음과 같은 세 개의 \<filter-mapping\> 요소가 있다고 가정하면 
```xml
<filter-mapping>
	<filter-name>my-filter1</filter-name>
	<url-pattern>*.jsp</url-pattern>
</filter-mapping>

<filter-mapping>
	<filter-name>my-filter2</filter-name>
	<url-pattern>/sub7/*</url-pattern>
</filter-mapping>

<filter-mapping>
	<filter-name>my-filter3</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
- /sub7/Hello.jsp라는 URL 경로명 위치에 있는 웹 컴포넌트가 호출되었을 때 위에 있는 세 계의 필터가 모두 적용될 것입니다. 그리고 필터 체인은 my-filter1, my-filter2, my-filter3 순서로 구성될 것 입니다.

- 하지만 \<url-pattern> 요소와 \<servlet-name\>요소가 섞여서 사용되었을 때는 필터 페인의 필터 순서가 이와 다르게 구성됩니다. 
- 그럴 때는 \<url-pattern\> 요소에 해당하는 필터들이 순서대로 필터 체인의 앞부분을 구성하고, \<servlet-name\> 요소에 해당하는 필터들이 순서대로 필터 체인의 뒷부분을 구성합니다.
```xml
<filter-mapping>
	<filter-name>my-filter1</filter-name>
	<url-pattern>*.jsp</url-pattern>
</filter-mapping>

<filter-mapping>
	<filter-name>my-filter2</filter-name>
	<servlet-name>hello-servlet</servlet-name>
</filter-mapping>

<filter-mapping>
	<filter-name>my-filter3</filter-name>
	<url-pattern>/sub7/*</url-pattern>
</filter-mapping>
```
- 위의 \<servlet-name\> 요소 안에 있는 hello-servlet이라는 서블릿 URL 경로명이 /sub7/hello라면 이 서블릿이 호출 되었을 때 위의 세 필터가 모두 적용될 것 입니다. 
- 그리고 필터 체인은 my-filter1, my-filter3, my-filter2 순서로 구성될 것입니다.

* * * 
## 래퍼 클래스 작성 및 적용하기
- 웹 브라우저와 웹 컴포넌트 사이를 오가는 데이터에 변형을 가하려면 필터 클래스와 더불어 래퍼클래스를 작성해야 합니다. 
- 래퍼 클래스(wrapper class)란 글자 그대로 포장하는 역할을 하는 클래스입니다.
- 래퍼 클래스의 종류는 요청 객체를 포장하는 요청 래퍼 클래스와 응답 객체를 포장하는 응답 래퍼 클래스 두 가지 입니다.
- 이 두 종류의 클래스를 작성할 때는 지켜야 할 규칙이 있는데, 그 중 가장 중요한 규칙은 이들이 각각 HttpServletRequestWrapper 클래스와 HttpServletResponseWrapper 클래스를 상속하도록 만들어야 한다는 것 입니다.


#### HttpServletRequestWrapper 클래스

![wrapper1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/wrapper1.png)


#### HttpServletResponseWrapper 클래스

![wrapper2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/5.JSP2%20%26%20JSP%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8(60%EC%8B%9C%EA%B0%84)/7%EC%9D%BC%EC%B0%A8(3h)%20-%20%ED%95%84%ED%84%B0%2C%20%EB%9E%98%ED%8D%BC%20%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC/images/wrapper2.png)

- HttpServletRequestWrapper클래스는 HttpServletRequest 인터페이스를 구현하고, HttpServletResponseWrapper 클래스는 HttpServletResponse 인터페이스를 구현합니다. 그러므로 이 두 클래스를 이용해서 만든 요청 래퍼 클래스와 응답 래퍼 클래스도 간접적으로 이 두 인터페이스를 구현하게 됩니다. 웹 컴포넌트가 요청 래퍼 객체와 응답 래퍼 객체를 요청 객체와 응답 객체로 인식할 수 있는 것은 바로 이런 이유 때문입니다.

### 요청 래퍼 클래스를 작성하는 방법
- 요청 래퍼 클래스는 HttpServletRequestWrapper 클래스를 상속받아야 하므로 다음과 같은 골격을 만드는 것으로 클래스 작성을 시작해야 합니다.
```java
public class MyRequestWrapper extends HttpServletRequestWrapper {

}
```
- MyRequestWrapper : 프로그래머가 정하는 요청 래퍼 클래스의 이름
- HttpServletRequestWrapper : 요청 래퍼 클래스가 상속해야 하는 클래스

- 그 다음에는 이 클래스의 가장 기본적인 역할인 요청 객체를 포장하는 일을 해야 합니다. 그런일은 이 클래스의 생성자를 통해서 할 수 있습니다.
- 요청 객체를 파라미터로 받는 생성자를 선언해 놓고, 그 안에서 파라미터 값을 필드(클래스의 멤버 변수)에 저장하도록 만들면 됩니다.
```java
public class MyRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
}
```
- 다음에는 이 클래스 안에 데이터를 변형하는 코드를 써 넣을 차례입니다.  그 코드는 웹 컴포넌트가 입력 데이터를 가져오기 위해 호출하는 메서드와 똑같은 시그니처의 메서드를 선언해 놓고 그 안에 써 넣어야 합니다.
- 예를 들어 \<form\> 입력 데이터를 가져올 때 호출하는 getParameter 메서드는 String 타입의 파라미터를 받고 String 타입의 값을 반환합니다. 그러므로 \<form\> 입력 데이터를 변형하기 위해서는 다음과 같은 getParameter 메서드를 선언하고 그 안에 데이터를 변형하는 코드를 써 넣어야 합니다.

```java
public class MyRequestWrapper extends HttpServletRequestWrapper {
	... 
	public String getParameter(String name) {
		// HTML 문서의 <form> 요소를 통해 입력된 데이터를 
		// 변형하는 코드는 여기에 써 넣어야 합니다.
	}
}
```
- 이렇게 하면 웹 컴포넌트가 이 메서드를 요청 객체의 메서드인 줄 알고 호출할 것이고, 그러면 그 때 \<form\> 데이터를 변형하는 코드가 실행될 것입니다.

- 입력데이터에 있는 모든 소문자를 대문자로 바꾸어 보는 예
```java
public class MyRequestWrapper extends HttpServletRequetWrapper {
	...
	public String getParameter(String name) {
		String value = request.getParameter(name);
		String newValue = value.getUpperCase();
		return newValue;
	}
}
```
- String value = request.getParameter(name); : 요청 객체(request)를 이용해서 입력 데이터를 가져옵니다.
- String newValue = value.getUpperCase(); : 가져온 입력 데이터를 변형합니다.
- return newValue; : 변형된 결과를 반환합니다.

- 이렇게 요청 래퍼 클래스를 작성해 놓는다고 해서 기존의 요청 객체가 요청 래퍼 객체로 자동으로 바뀌는 것은 아닙니다.  그런 일은 필터 클래스의 doFilter 메서드 안에서 해야 하고, 직접 코드를 서 넣어야 합니다. 그런 다음 그 코드를 통해서 만든 요청 래퍼 객체를 다음과 같이 chain.doFilter 메서드에 매개변수로 넘겨주어야 합니다.

```java
public class MyFilter implements Filter {
	...
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) ... {
		
		MyRequestWrapper requestWrapper = new MyRequestWrapper((HttpServletRequest) request);
		
		chain.doFilter(requestWrapper, response);
	}
}
```

#### src/main/java/myfilter/ParamUpperCaseRequestWrapper.java
```java
package myfilter;

import javax.servlet.http.*;

public class ParamUpperCaseRequestWrapper extends HttpServletRequestWrapper {
	
		HttpServletRequest request;
		public ParamUpperCaseRequestWrapper(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		public String getParameter(String name) {
			String str = request.getParameter(name);
			if (str == null) 
				return null;
			
			return str.toUpperCase();
		}
}
```

#### src/main/java/myfilter/ParamUpperCaseFilter.java
```java
package myfilter;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ParamUpperCaseFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ParamUpperCaseRequestWrapper requestWrapper = new ParamUpperCaseRequestWrapper((HttpServletRequest) request);
		chain.doFilter(requestWrapper, response);
	}
}
```

#### src/webapp/WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	<filter>
		<filter-name>param-upper-filter</filter-name>
		<filter-class>myfilter.ParamUpperCaseFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>param-upper-filter</filter-name>
		<url-pattern>/sub4/*</url-pattern>
	</filter-mapping>
</web-app>
```

#### sub4/Welcome.jsp
- http://localhost:8080/../sub4/Welcome.jsp?name=Jessica
```html
<%@page contentType="text/html; charset=utf-8" %>
<% String name = request.getParameter("name"); %>
<html>
	<body>
		안녕하세요, <%= name %>님
	</body>
</html>
```

- 서블릿 클래스나 JSP 페이지에서 \<form\> 데이터를 가져올 때에는 getParameter 메서드뿐만 아니라 getParameterValues나 getParameterMap 메서드를 사용할 수도 있습니다. 이러한 메서드를 사용하면 \<form\> 데이터에 포함된 소문자가 여전히 그대로 나타날 것 입니다.
- 이런 문제를 해결하기 위해서는 요청 래퍼 클래스에 getParameterValues 메서드와 getParameterMap 메서드를 추가로 선언하여 데이터를 변형하는 코드를 써 넣으면 됩니다.

#### src/main/java/myfilter/ParamUpperCaseRequestWrapper.java
```
package myfilter;

import javax.servlet.http.*;
import java.util.*;

public class ParamUpperCaseRequestWrapper extends HttpServletRequestWrapper {
	
		HttpServletRequest request;
		public ParamUpperCaseRequestWrapper(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		public String getParameter(String name) {
			String str = request.getParameter(name);
			if (str == null) 
				return null;
			
			return str.toUpperCase();
		}
		
		public String[] getParameterValues(String name) {
			String[] str = request.getParameterValues(name);
			if (str == null)
				return null;
			for (int i = 0; i < str.length; i++)
				str[i] = str[i].toUpperCase();
			return str;
		}
		
		public Map getParameterMap() {
			Map map = request.getParameterMap();
			HashMap<String, String[]> newMap = new HashMap<String, String[]>();
			
			Object[] name = map.keySet().toArray();
			for(int i = 0; i < name.length; i++) {
				String[] value = (String[])map.get(name[i]);
				for (int j = 0; j < value.length; j++) 
					value[j] = value[j].toUpperCase();
				newMap.put((String)name[i], value);
			}
			
			return newMap;
		}
}
```

#### sub4/Colors.jsp
- http://localhost:8080/jspWeb1/brain11/sub4/Colors.jsp?color=red&color=white&color=black
```html
<%@page contentType="text/html; charset=utf-8" %>
<% String[] color = request.getParameterValues("color"); %>
<html>
	<body>
		<h4>선택하신 색은 다음과 같습니다.</h4>
		<%
			if (color != null) {
				for (int i = 0; i < color.length; i++) {
					out.println(color[i] + "<br>");
				}
			}
		%>
	</body>
</html>
```

### 응답 래퍼 클래스를 작성하는 방법
- 요청 래퍼 클래스를 작성하는 방법과 여러 가지 면에서 비슷합니다. 요청 래퍼 클래스가 HttpServletRequestWrapper 클래스를 상속해야 하는 것처럼 응답 래퍼 클래스는 HttpServletResponseWrapper 클래스를 상속해야 합니다.
```java
public class MyResponseWrapper extends HttpServletResponseWrapper {

}
```
- MyResponseWrapper : 프로그래머가 정하는 응답 래퍼 클래스의 이름
- HttpServletResponseWrapper : 응답 래퍼 클래스가 상속해야 하는 클래스

- 요청 래퍼 클래스가 요청 객체를 포장해야 하는 것처럼 응답 래퍼 클래스는 응답 객체를 포장하는 일을 해야 합니다. 이런 일은 응답 래퍼 클래스에 생성자를 선언하고, 그 안에서 응답 객체를 파라미터로 받아서 필드에 저장하는 식으로 구현할 수 있습니다. 

```java
public class MyResponseWrapper extends HttpServletResponseWrapper {
	HttpServletResponse response;
	public ParamUpperCaseResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
}
```
- 이 클래스 안에 응답 데이터를 변형하는 코드를 써 넣어야 합니다. 그리고 이런 코드는 웹 컴포넌트에서 해당 데이터를 출력할 때 사용하는 메서드와 똑같은 시그니처의 메서드를 선언하고 그 안에 써 넣어야 합니다.
- 예를 들어 웹 컴포넌트에서 쿠기 데이터를 웹 브라우저로 보낼 때는 HttpServletResponse 인터페이스의 addCookie 메서드를 호출해야 합니다. 그러므로 쿠키 데이터를 변형하기 위해서는 그와 똑같은 시그니처의 addCookie 메서드를 선언하고, 그 안에 해당 로직을 써 넣으면 됩니다.

```java
public class MyRequestWrapper extends HttpServletRequestWrapper {
	HttpServletRequest request;
	...
	public void addCookie(Cookie cookie) {
		String name = cookie.getName();
		String value = cookie.getValue();
		String newValue = value.toLowerCase();
		Cookie newCookie = new Cookie(name, newValue);
		
		response.addCookie(newCookie);
	}
}
```
- 이 메서드는 응답 객체의 addCookie 메서드인 줄로 알고 호출할 것이고, 그 결과 쿠키에 포함된 데이터가 변형된 다음에 웹 브라우저로 전달될 것입니다.
- 이 클래스가 응답 객체를 가져다가 응답 래퍼 객체를 만드는 일을 자동으로 하는 것은 아니기 때문에, 그런 일을 하는 코드를 필터 클래스의 doFilter 메서드 안에 직접 써 넣어야 합니다. 그리고 그렇게 만든 응답 래퍼 객체를 chain.doFilter 메서드에 매개변수로 넘겨주어야 합니다.

```java
public class MyFilter implements Filter {
	...
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) ... {
		MyResponseWrapper responseWrapper = new MyResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, responseWrapper);
	}
}
```

#### src/main/java/myfilter/CookieLowerCaseResponseWrapper.java
```java
package myfilter;

import javax.servlet.http.*;

public class CookieLowerCaseResponseWrapper extends HttpServletResponseWrapper {
	private HttpServletResponse response;
	
	public CookieLowerCaseResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	public void addCookie(Cookie cookie) {
		String value = cookie.getValue();
		String newValue = value.toLowerCase();
		cookie.setValue(newValue);
		
		response.addCookie(cookie);
	}
}
```

#### src/main/java/myfilter/CookieLowerCaseFilter.java
```java
package myfilter;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class CookieLowerCaseFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		CookieLowerCaseResponseWrapper responseWrapper = new CookieLowerCaseResponseWrapper((HttpServletResponse) response);
		
		chain.doFilter(request, responseWrapper);
	}
}
```

#### src/webapp/WEB-INF/web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
	...
	<filter>
		<filter-name>cookie-lower-filter</filter-name>
		<filter-class>myfilter.CookieLowerCaseFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>cookie-lower-filter</filter-name>
		<url-pattern>/sub5/*</url-pattern>
	</filter-mapping>
</web-app>
```

#### sub5/CookieSaver.jsp
```html
<%@page contentType="text/html; charset=utf-8" %>
<%
	Cookie cookie = new Cookie("CART", "LemonTree");
	response.addCookie(cookie);
%>
<html>
	<body>
		쿠키가 저장되었습니다.
	</body>
</html>
```

#### sub5/CookieRetriever.jsp
```html
<%@page contentType="text/html; charset=utf-8" %>
<html>
	<body>
		CART = ${cookie.CART.value}
	</body>
</html>
```

* * * 
# 예외처리
- 예외처리는 프로그램이 처리되는 동안 특정한 문제가 발생했을 때 처리를 중단하고 다른 처리를 하는 것으로 오류 처리라고도 합니다.
- 웹 애플리케이션 실행 도중에 발생할 수 있는 오류에 대비한 예외 처리 코드를 작성하여 비정상적인 종료를 막을 수 있습니다.

## 예외처리 방법의 종류 

|예외 처리 방법|설명|
|-----|------|
|page 디렉티브 태그를 이용한 예외처리|errorPage와 isErrorPage 속성을 이용합니다.|
|web.xml 파일을 이용한 예외처리|\<error-code\>또는 \<exception-type\>요소를 이용합니다.|
|try/catch/finally를 이용한 예외처리|자바 언어의 예외 처리 구문을 이용합니다.|

## 예외처리 방법의 우선순위
- JSP 페이지에서 try-catch-finally 문으로 처리하는 경우
- page 디렉티브 태그의 errorPage속성에서 설정한 오류페이지
- JSP 페이지에서 발생한 예외 유형이 web.xml 파일에서 설정한 예외 유형
- JSP 페이지에서 발생한 오류 코드가 web.xml 파일에서 설정한 오류코드와 동일한 경우
- 상기 항목에 해당되지 않는 경우 웹 서버가 제공하는 기본 오류 페이지를 출력합니다.

##page 디렉티브 태그를 이용한 예외처리

### errorPage 속성으로 오류페이지 호출하기
- JSP 페이지가 실행되는 도중에 오류가 발생하면 웹 서버의 기본 오류 페이지를 대신하여 errorPage 속성에 설정한 페이지가 오류 페이지로 호출됩니다.

```
<%@ page errorPage = “오류 페이지 URL” %>
```

#### source/errorPage.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page errorPage="errorPage_error.jsp"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	name 파라미터 : <%=request.getParameter("name").toUpperCase()%>
</body>
</html>
```
#### source/errorPage_error.jsp
```
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>오류가 발생하였습니다.
</body>
</html>
```

### isErrorPage 속성으로 오류 페이지 만들기
- 오류 페이지에서 exception 내장 객체를 사용할 수 있습니다.
```
<%@ page isErrorPage = “true” %>
```

### exception 내장 객체의 메서드

|메서드|형식|설명|
|-----|----|---------|
|getMessage()|String|오류 이벤트와 함께 들어오는 메시지를 출력합니다.|
|toString()|String|오류 이벤트의 toString()을 호출하여 간단한 오류 메시지를 확인합니다.|
|printStackTrace()|String|오류 메시지의 발생 근원지를 찾아 단계적으로 오류를 출력합니다.|

#### source/isErrorPage.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page errorPage="isErrorPage_error.jsp"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	name 파라미터 : <%=request.getParameter("name").toUpperCase()%>
</body>
</html>
```

#### source/isErrorPage_error.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>오류가 발생하였습니다.
	<p>	예외 유형 :	<%=exception.getClass().getName()%>
	<p>	오류 메시지 : <%=exception.getMessage()%>
</body>
</html>
```

#### source/exception.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<form action="exception_process.jsp" method="post">
		<p> 숫자1 : <input type="text" name="num1">
		<p> 숫자2 : <input type="text" name="num2">
		<p> <input type="submit" value="나누기">
	</form>
</body>
</html>
```

#### source/exception_process.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page errorPage="exception_error.jsp"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<%
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
		out.print(num1 + " / " + num2 + " = " + c);
	%>
</body>
</html>
```

#### source/exception_error.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>오류가 발생하였습니다.
	<p>	예외 : <%=exception%>
	<p>	toString() : <%=exception.toString()%>
	<p>	getClass().getName() :	<%=exception.getClass().getName()%>
	<p>	getMessage() :	<%=exception.getMessage()%>
</body>
</html>
```

## web.xml 파일을 이용한 예외처리
```xml
<error-page>
   <error-code>...</error-code>|<exception-type>...</exception-type>
   <location>...</location>
</error-page>
```

#### \<error-page\>를 구성하는 하위요소

|요소|설명|
|----|--------|
|\<error-code\>|오류 코드를 설정하는 데 사용합니다.|
|\<exception-type\>|자바 예외 유형의 정규화된 클래스 이름을 설정하는 데 사용합니다.|
|\<location\>|오류 페이지의 URL을 설정하는 데 사용합니다.|

### 주요 오류 코드의 종류
- 200 OK<br>요청이 성공적으로 되었습니다. 

- 201 Created<br>요청이 성공적이었으며 그 결과로 새로운 리소스가 생성되었습니다. 이 응답은 일반적으로 POST 요청 또는 일부 PUT 요청 이후에 따라옵니다.

301 Moved Permanently
이 응답 코드는 요청한 리소스의 URI가 변경되었음을 의미합니다. 새로운 URI가 응답에서 아마도 주어질 수 있습니다.

302 Found
이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다. 새롭게 변경된 URI는 나중에 만들어질 수 있습니다. 그러므로, 클라이언트는 향후의 요청도 반드시 동일한 URI로 해야합니다.

304 Not Modified
이것은 캐시를 목적으로 사용됩니다. 이것은 클라이언트에게 응답이 수정되지 않았음을 알려주며, 그러므로 클라이언트는 계속해서 응답의 캐시된 버전을 사용할 수 있습니다

400 Bad Request
이 응답은 잘못된 문법으로 인하여 서버가 요청을 이해할 수 없음을 의미합니다.

401 Unauthorized
비록 HTTP 표준에서는 "미승인(unauthorized)"를 명확히 하고 있지만, 의미상 이 응답은 "비인증(unauthenticated)"을 의미합니다. 클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.

403 Forbidden
클라이언트는 콘텐츠에 접근할 권리를 가지고 있지 않습니다. 예를들어 그들은 미승인이어서 서버는 거절을 위한 적절한 응답을 보냅니다. 401과 다른 점은 서버가 클라이언트가 누구인지 알고 있습니다.
- 404 Not Found<br>서버는 요청받은 리소스를 찾을 수 없습니다. 브라우저에서는 알려지지 않은 URL을 의미합니다. 이것은 API에서 종점은 적절하지만 리소스 자체는 존재하지 않음을 의미할 수도 있습니다. 서버들은 인증받지 않은 클라이언트로부터 리소스를 숨기기 위하여 이 응답을 403 대신에 전송할 수도 있습니다. 이 응답 코드는 웹에서 반복적으로 발생하기 때문에 가장 유명할지도 모릅니다.

- 500 Internal Server Error<br>서버가 처리 방법을 모르는 상황이 발생했습니다. 서버는 아직 처리 방법을 알 수 없습니다.

- 502 Bad Gateway<br>이 오류 응답은 서버가 요청을 처리하는 데 필요한 응답을 얻기 위해 게이트웨이로 작업하는 동안 잘못된 응답을 수신했음을 의미합니다.

- 503 Service Unavailable<br>서버가 요청을 처리할 준비가 되지 않았습니다. 일반적인 원인은 유지보수를 위해 작동이 중단되거나 과부하가 걸렸을 때입니다.

#### \<error-code\>요소 사용 예
```xml
<web-app ...> 
	...
   <error-page>
      <error-code>404</error-code>
      <location>/errorCode_404.jsp</location>
   </error-page>

   <error-page>
     <error-code>500</error-code>
    <location>/errorCode_500.jsp</location>
   </error-page>
</web-app>
```

#### WEB-INF/web.xml 
```xml
<?xml version=“1.0” encoding=“UTF-8”?>
<web-app ...>
   <error-page>
     <error-code>500</error-code>
     <location>/ch11/errorCode_error.jsp</location>
   </error-page>
</web-app>
```

#### source/errorCode.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<form action="errorCode_process.jsp" method="post">
		<p> 숫자1 : <input type="text" name="num1">
		<p> 숫자2 : <input type="text" name="num2">
		<p> <input type="submit" value="나누기">
	</form>
</body>
</html>
```

#### source/errorCode_process.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<%
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
		out.print(num1 + " / " + num2 + " = " + c);
	%>
</body>
</html>
```

#### source/errorCode_error.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	errorCode 505 오류가 발생하였습니다.	
</body>
</html>
```

### 예외 유형으로 오류 페이지 호출하기
```xml
<error-page>
   <exception-type>예외 유형</exception-type>
   <location>오류 페이지의 URI</location>
</error-page>
```

#### \<exception-type\> 요소 사용 예
```xml
<web-app ...>
   <error-page>
       <exception-type>java.lang.NullPointerException</exception-type>
       <location>/errorNullPointer.jsp</location>
   </error-page>
</web-app>
```

#### WEB-INF/web.xml 
```xml
<?xml version=“1.0” encoding=“UTF-8”?>
<web-app ...>
   <error-page>
      <exception-type>java.lang.Exception</exception-type>
      <location>/ch11/exceptionType_error.jsp</location>
   </error-page>
</web-app>
```

#### source/exceptionType.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<form action="exceptionType_process.jsp" method="post">
		<p> 숫자1 : <input type="text" name="num1">
		<p> 숫자2 : <input type="text" name="num2">
		<p> <input type="submit" value="나누기">
	</form>
</body>
</html>
```

#### source/exceptionType_process.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<%
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
		out.print(num1 + " / " + num2 + " = " + c);
	%>
</body>
</html>
```

#### source/exceptionType_error.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	exception type  오류가 발생하였습니다.
</body>
</html>
```


## try/catch/finally를 이용한 예외처리
```
try{
   //예외가 발생할 수 있는 실행문
} catch ( 처리할 예외 유형 설정) {
  // 예외 처리문
} 
[finally {
  // 예외와 상관없이 무조건 실행되는 문장(생략 가능)
}]
```

#### try-catch-finally 사용 예 
```html
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
  <%
    try {
       int num = 10 / 0;
    } catch (NumberFormatException e) {
       RequestDispatcher dispatcher = request.getRequestDispatcher(“error.jsp”);
       dispatcher.forward(request, response);
    }
  %>
</body>
</html>
```

#### 참고) 서블릿에서 jsp 호출(forward와 include 방식)
- forward() 메서드 방식 : JSP 페이지를 호출하는 순간 서블릿 프로그램이 실행을 멈추고 JSP 페이지로 넘어가 그곳에서 실행하고 프로그램이 끝납니다.
	```java
	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(“jspFile”);
	request.setAttribute(“name”, “value”);
	rd.forward(request, response);
	```

- include() 메서드 방식 : JSP 페이지가 실행된 후 나머지 서블릿 프로그램이 실행됩니다.
	```java
	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(“jspFile”);
	rd.include(request, response);
	```
	
#### source/tryCatch.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<form action="tryCatch_process.jsp" method="post">
		<p> 숫자1 : <input type="text" name="num1">
		<p> 숫자2 : <input type="text" name="num2">
		<p> <input type="submit" value="전송">
	</form>
</body>
</html>
```

#### source/tryCatch_process.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>	   
<%
	try {
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
	} catch (NumberFormatException e) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("tryCatch_error.jsp");
		dispatcher.forward(request, response);
	}
%>
</body>
</html>
```

#### source/tryCatch_error.jsp
```html
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>잘못된 데이터가 입력되었습니다.
	<p>	<%=" 숫자1 : " + request.getParameter("num1")%>
	<p>	<%=" 숫자2 : " + request.getParameter("num2")%>	
</body>
</html>
```
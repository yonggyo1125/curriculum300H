## param 

- request 파라미터/어트리뷰트에 접근할때

```
${param.foo} // Retrieves a String[] with the values of request parameter 'foo'
${param.size()}
${param.isEmpty()}
${param.containsKey('foo')}
```

## session 

- session 어트리뷰트에 접근할 때

```
${session.foo} // Retrieves the session atttribute 'foo'
${session.size()}
${session.isEmpty()}
${session.containsKey('foo')}
```

## application

- application/servlet context 어트리뷰트(attribute)에서 접근할 때

```
${application.foo} // Retrieves the ServletContext atttribute 'foo'
${application.size()}
${application.isEmpty()}
${application.containsKey('foo')}
```

## #request 

- 현재 요청에 대한 javax.servlet.http.HttpServletRequest  객체에 바로 접근할때 

```
${#request.getAttribute('foo')}
${#request.getParameter('foo')}
${#request.getContextPath()}
${#request.getRequestName()}
```

## #session

- 현재 요청에 대한 javax.servlet.http.HttpSession 객체에 바로 접근할때 

```
${#session.getAttribute('foo')}
${#session.id}
${#session.lastAccessedTime}
```

## #servletContext

- 현재 요청에 대한 javax.servlet.ServletContext 객체에 바로 접근할때

```
${#servletContext.getAttribute('foo')}
${#servletContext.contextPath}
```


## #ctx
- context 객체(object). org.thymeleaf.context.IContext 또는
org.thymeleaf.context.IWebContext의 구현체, 현재 실행 환경에 대한 정보를 가지고 있다.
- #vars, #root와 같은 객체이나 #ctx가 추천됩니다.

```
${#ctx.locale}
${#ctx.variableNames}

${#ctx.request}
${#ctx.response}
${#ctx.session}
${#ctx.servletContext}
```


## #ids 
- utility methods for dealing with id attributes that might be repeated (for example, as a result of an iteration)

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Ids
* ======================================================================
*/
/*
* Normally used in th:id attributes, for appending a counter to the id attribute value
* so that it remains unique even when involved in an iteration process.
*/
${#ids.seq('someId')}
/*
* Normally used in th:for attributes in <label> tags, so that these labels can refer to Ids
* generated by means if the #ids.seq(...) function.
*
* Depending on whether the <label> goes before or after the element with the #ids.seq(...)
* function, the "next" (label goes before "seq") or the "prev" function (label goes after
* "seq") function should be called.
*/
${#ids.next('someId')}
${#ids.prev('someId')}
```
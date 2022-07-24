## #conversions

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Conversions
* ======================================================================
*/
/*
* Execute the desired conversion of the 'object' value into the
* specified class.
*/
${#conversions.convert(object, 'java.util.TimeZone')}
${#conversions.convert(object, targetClass)}
```
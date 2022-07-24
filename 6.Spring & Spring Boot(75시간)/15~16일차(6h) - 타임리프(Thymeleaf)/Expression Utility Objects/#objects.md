## #objects : utility methods for objects in general

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Objects
* ======================================================================
*/

/*
* Return obj if it is not null, and default otherwise
* Also works with arrays, lists or sets
*/
${#objects.nullSafe(obj,default)}
${#objects.arrayNullSafe(objArray,default)}
${#objects.listNullSafe(objList,default)}
${#objects.setNullSafe(objSet,default)}
```
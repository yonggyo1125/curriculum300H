## #aggregates : utility methods for creating aggregates on arrays or collections

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Aggregates
* ======================================================================
*/
/*
* Compute sum. Returns null if array or collection is empty
*/
${#aggregates.sum(array)}
${#aggregates.sum(collection)}
/*
* Compute average. Returns null if array or collection is empty
*/
${#aggregates.avg(array)}
${#aggregates.avg(collection)}
```
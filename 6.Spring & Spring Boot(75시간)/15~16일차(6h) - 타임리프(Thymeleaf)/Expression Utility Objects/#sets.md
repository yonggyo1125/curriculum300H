## #sets : utility methods for s

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Sets
* ======================================================================
*/

/*
* Converts to set
*/
${#sets.toSet(object)}

/*
* Compute size
*/
${#sets.size(set)}

/*
* Check whether set is empty
*/
${#sets.isEmpty(set)}

/*
* Check if element or elements are contained in set
*/
${#sets.contains(set, element)}
${#sets.containsAll(set, elements)}
```
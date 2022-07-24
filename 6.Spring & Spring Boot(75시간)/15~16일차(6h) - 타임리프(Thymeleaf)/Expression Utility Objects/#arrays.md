## #arrays : utility methods for arrays

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Arrays
* ======================================================================
*/
/*
* Converts to array, trying to infer array component class.
* Note that if resulting array is empty, or if the elements
* of the target object are not all of the same class,
* this method will return Object[].
*/
${#arrays.toArray(object)}

/*
* Convert to arrays of the specified component class.
*/
${#arrays.toStringArray(object)}
${#arrays.toIntegerArray(object)}
${#arrays.toLongArray(object)}
${#arrays.toDoubleArray(object)}
${#arrays.toFloatArray(object)}
${#arrays.toBooleanArray(object)}

/*
* Compute length
*/
${#arrays.length(array)}

/*
* Check whether array is empty
*/
${#arrays.isEmpty(array)}

/*
* Check if element or elements are contained in array
*/
${#arrays.contains(array, element)}
${#arrays.containsAll(array, elements)}
```
## #lists : utility methods for lists

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Lists
* ======================================================================
*/
/*
* Converts to list
*/
${#lists.toList(object)}

/*
* Compute size
*/
${#lists.size(list)}

/*
* Check whether list is empty
*/
${#lists.isEmpty(list)}

/*
* Check if element or elements are contained in list
*/
${#lists.contains(list, element)}
${#lists.containsAll(list, elements)}

/*
* Sort a copy of the given list. The members of the list must implement
* comparable or you must define a comparator.
*/
${#lists.sort(list)}
${#lists.sort(list, comparator)}
```
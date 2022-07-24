## #maps : utility methods for maps

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Maps
* ======================================================================
*/
/*
* Compute size
*/
${#maps.size(map)}
/*
* Check whether map is empty
*/
${#maps.isEmpty(map)}
/*
* Check if key/s or value/s are contained in maps
*/
${#maps.containsKey(map, key)}
${#maps.containsAllKeys(map, keys)}
${#maps.containsValue(map, value)}
${#maps.containsAllValues(map, value)}
```
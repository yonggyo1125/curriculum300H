## #uris

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Uris
* ======================================================================
*/
/*
* Escape/Unescape as a URI/URL path
*/
${#uris.escapePath(uri)}
${#uris.escapePath(uri, encoding)}
${#uris.unescapePath(uri)}
${#uris.unescapePath(uri, encoding)}

/*
* Escape/Unescape as a URI/URL path segment (between '/' symbols)
*/
${#uris.escapePathSegment(uri)}
${#uris.escapePathSegment(uri, encoding)}
${#uris.unescapePathSegment(uri)}
${#uris.unescapePathSegment(uri, encoding)}

/*
* Escape/Unescape as a Fragment Identifier (#frag)
*/
${#uris.escapeFragmentId(uri)}
${#uris.escapeFragmentId(uri, encoding)}
${#uris.unescapeFragmentId(uri)}
${#uris.unescapeFragmentId(uri, encoding)}

/*
* Escape/Unescape as a Query Parameter (?var=value)
*/
${#uris.escapeQueryParam(uri)}
${#uris.escapeQueryParam(uri, encoding)}
${#uris.unescapeQueryParam(uri)}
${#uris.unescapeQueryParam(uri, encoding)}
```
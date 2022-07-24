## #messages
- 메세지 파일에서 메세지를 얻기위한 유틸리티 메서드

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.Messages
* ======================================================================
*/
/*
* Obtain externalized messages. Can receive a single key, a key plus arguments,
* or an array/list/set of keys (in which case it will return an array/list/set of
* externalized messages).
* If a message is not found, a default message (like '??msgKey??') is returned.
*/
${#messages.msg('msgKey')}
${#messages.msg('msgKey', param1)}
${#messages.msg('msgKey', param1, param2)}
${#messages.msg('msgKey', param1, param2, param3)}
${#messages.msgWithParams('msgKey', new Object[] {param1, param2, param3, param4})}
${#messages.arrayMsg(messageKeyArray)}
${#messages.listMsg(messageKeyList)}
${#messages.setMsg(messageKeySet)}
/*
* Obtain externalized messages or null. Null is returned instead of a default
* message if a message for the specified key is not found.
*/
${#messages.msgOrNull('msgKey')}
${#messages.msgOrNull('msgKey', param1)}
${#messages.msgOrNull('msgKey', param1, param2)}
${#messages.msgOrNull('msgKey', param1, param2, param3)}
${#messages.msgOrNullWithParams('msgKey', new Object[] {param1, param2, param3, param4})}
${#messages.arrayMsgOrNull(messageKeyArray)}
${#messages.listMsgOrNull(messageKeyList)}
${#messages.setMsgOrNull(messageKeySet)}
```
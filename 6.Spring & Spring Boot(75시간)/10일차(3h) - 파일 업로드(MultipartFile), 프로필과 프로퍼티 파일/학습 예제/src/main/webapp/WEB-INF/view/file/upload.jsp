<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var='url' value="/file/upload" />
<form:form action="${url}"  method="post" enctype="multipart/form-data">   
    <dl>
        <dt>파일 선택 :</dt> 
        <dd>
            <input type="file" name="file">
        </dd>
    </dl>
    <input type="submit" value="업로드" />
</form:form>
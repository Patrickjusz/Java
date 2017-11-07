<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${not empty param.id}">
    

<div class="alert alert-dismissable alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    Czy na pewno chcesz usunąć <b><c:out value="${nazwa}" /></b>?<br><br>
        &nbsp;<a href="usun?id=${id}&akceptacja=1&typ=${typ}">Tak</a> / <a href="przekieruj?typ=${typ}">Nie</a>
</div>

</c:if>


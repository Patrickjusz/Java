<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajPrzejazd" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <b>Uwaga!</b> <c:out value="${trescBledu}" />
</div>
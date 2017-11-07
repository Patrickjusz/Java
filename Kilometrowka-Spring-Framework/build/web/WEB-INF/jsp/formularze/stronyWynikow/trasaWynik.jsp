<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/trasy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="alert alert-dismissable alert-success">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <b>Operacja wykonana pomyślnie!</b> Dziekujemy za dodanie trasy.
</div>
<table border="0">
    <tr>
        <td><b>Nazwa:</b></td>
        <td><c:out value="${dodajTraseForm.nazwa}" /></td>
    </tr>
    <tr>
        <td><b>Miejscowość źródłowa:</b></td>
        <td><c:out value="${dodajTraseForm.skad}" /></td>
    </tr>
    <tr>
        <td><b>Miejscowość docelowa:</b></td>
        <td><c:out value="${dodajTraseForm.dokad}" /></td>
    </tr>

    <tr>
        <td><b>Odległość:</b></td>
        <td><c:out value="${dodajTraseForm.odleglosc}" /></td>
    </tr>

    <tr>
        <td><b>Opis:</b></td>
        <td><c:out value="${dodajTraseForm.opis}" /></td>
    </tr>

    <tr>
    </tr>

</table>
<br><a class="btn btn-info btn-sm" href="${url}">Przejdź do strony z trasami</a>
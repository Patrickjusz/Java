<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/przejazdy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="alert alert-dismissable alert-success">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <b>Operacja wykonana pomyślnie!</b> Dziekujemy za dodanie przejazdu.
</div>
<table border="0">

    <tr>
        <td><b>Pojazd:</b></td>
        <td><c:out value="${dodajPrzejazdForm.idPojazdu}" /></td>
    </tr>

    <tr>
        <td><b>Trasa:</b></td>
        <td><c:out value="${dodajPrzejazdForm.idTrasy}" /></td>
    </tr>

    <tr>
        <td><b>Firma:</b></td>
        <td><c:out value="${dodajPrzejazdForm.idFirmy}" /></td>
    </tr>

    <tr>
        <td><b>Pracownik:</b></td>
        <td><c:out value="${dodajPrzejazdForm.idPracownika}" /></td>
    </tr>

    <tr>
        <td><b>Cel wyjazdu:</b></td>
        <td><c:out value="${dodajPrzejazdForm.celWyjazdu}" /></td>
    </tr>


    <tr>
        <td><b>Data:</b></td>
        <td><c:out value="${dodajPrzejazdForm.data}" /></td>
    </tr>

    <tr>
        <td><b>Adniotacje:</b></td>
        <td><c:out value="${dodajPrzejazdForm.adnotacje}" /></td>
    </tr>


</table>
<br><a class="btn btn-info btn-sm" href="${url}">Przejdź do strony z przejazdami</a>
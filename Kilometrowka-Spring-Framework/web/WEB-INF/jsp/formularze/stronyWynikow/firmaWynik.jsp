<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/firmy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <div class="alert alert-dismissable alert-success">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <b>Operacja wykonana pomyślnie!</b> Dziekujemy za dodanie firmy.
    </div>
<table border="0">

    <tr>
        <td><b>Nazwa:</b></td>
        <td><c:out value="${dodajFirmeForm.nazwaFirmy}" /></td>
    </tr>
    <tr>
        <td><b>Adres:</b></td>
        <td><c:out value="${dodajFirmeForm.adres}" /></td>
    </tr>
    <tr>
        <td><b>Miasto:</b></td>
        <td><c:out value="${dodajFirmeForm.miasto}" /></td>
    </tr>


    <tr>
        <td><b>Kod pocztowy:</b></td>
        <td><c:out value="${dodajFirmeForm.kodPocztowy}" /></td>
    </tr>

    <tr>
        <td><b>NIP:</b></td>
        <td><c:out value="${dodajFirmeForm.nip}" /></td>
    </tr>

    <tr>
        <td><b>REGON:</b></td>
        <td><c:out value="${dodajFirmeForm.regon}" /></td>
    </tr>
 

</table>
        <br><a class="btn btn-info btn-sm" href="${url}">Przejdź do strony z firmami</a>
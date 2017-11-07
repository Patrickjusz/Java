<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/pracownicy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <div class="alert alert-dismissable alert-success">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <b>Operacja wykonana pomyślnie!</b> Dziekujemy za dodanie pracownika.
    </div>
        <table border="0">
           
            <tr>
                <td><b>Imie:</b></td>
                <td><c:out value="${dodajPracownikaForm.imie}" /></td>
            </tr>
            <tr>
                <td><b>Nazwisko:</b></td>
                <td><c:out value="${dodajPracownikaForm.nazwisko}" /></td>
            </tr>
            <tr>
                <td><b>Adres:</b></td>
                <td><c:out value="${dodajPracownikaForm.adres}" /></td>
            </tr>
           
            
            <tr>
                <td><b>Miasto:</b></td>
                <td><c:out value="${dodajPracownikaForm.miasto}" /></td>
            </tr>
            
            <tr>
                <td><b>Kod pocztowy:</b></td>
                <td><c:out value="${dodajPracownikaForm.kodPocztowy}" /></td>
            </tr>
            <tr>
                <td><b>Telefon:</b></td>
                <td><c:out value="${dodajPracownikaForm.telefon}" /></td>
            </tr>
        </table>
<br><a class="btn btn-info btn-sm" href="${url}">Przejdź do strony z pracownikami</a>
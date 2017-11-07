<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajPracownika" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przeglądać, modyfikować, dodawać i usuwać pracowników.
</div>

<table class="table table-striped table-bordered table-condensed table-hover">
    <tr><th>Imie</th><th>Nazwisko</th><th>Adres</th><th>Miasto</th><th>Kod pocztowy</th><th>Telefon</th></tr>
            <c:forEach var="pracownik" items="${pracownicy}">
        <tr>
            <td><c:out value="${pracownik.imie}" /></td>
            <td><c:out value="${pracownik.nazwisko}" /></td>
            <td><c:out value="${pracownik.adres}" /></td>
            <td><c:out value="${pracownik.miasto}" /></td>
            <td><c:out value="${pracownik.kodPocztowy}" /></td>
            <td><c:out value="${pracownik.telefon}" /></td>

            <td><a href="edytujPracownika?id=${pracownik.id}">
                    <span class="glyphicon glyphicon-edit" > </span> Edytuj</a></td>

            <td><a href="usun?id=${pracownik.id}&typ=3">
                    <span class="glyphicon glyphicon-trash" ></span> Usuń</a></td>
        </tr>
    </c:forEach>
</table>

<a class="btn btn-primary btn-sm" href="${url}">Dodaj pracownika</a>
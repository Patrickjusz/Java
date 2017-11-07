<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajPojazd" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przegladać, modyfikować, dodawać i usuwać pojazdy.
</div>

<table class="table table-striped table-bordered table-condensed table-hover">
    <tr><th>Nazwa</th><th>Numer rejestracyjny</th><th>Pojemnosc silnika</th><th>Opis</th></tr>
            <c:forEach var="pojazd" items="${pojazdy}">
        <tr>
            <td><c:out value="${pojazd.nazwa}" /></td>
            <td><c:out value="${pojazd.numerRejestracyjny}" /></td>
            <td><c:out value="${pojazd.pojemnoscSilnika}" /></td>
            <td><i><c:out value="${pojazd.opis}" /></i></td>

            <td><a href="edytujPojazd?id=${pojazd.id}"> 
                    <span class="glyphicon glyphicon-edit" > </span> Edytuj</a></td>

            <td><a href="usun?id=${pojazd.id}&typ=1"> 
                    <span class="glyphicon glyphicon-trash" ></span> Usuń</a></td>
        </tr>
    </c:forEach>
</table>

<a class="btn btn-primary btn-sm" href="${url}">Dodaj pojazd</a>
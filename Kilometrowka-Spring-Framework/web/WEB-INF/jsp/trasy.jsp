<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajTrase" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przegladać, modyfikować, dodawać i usuwać trasy.
</div>

<table class="table table-striped table-bordered table-condensed table-hover">
    <tr><th>Nazwa</th><th>Skąd</th><th>Dokąd</th><th>Długość</th><th>Opis</th></tr>
            <c:forEach var="trasa" items="${trasy}">
        <tr>
            <td><c:out value="${trasa.nazwa}" /></td>
            <td><c:out value="${trasa.skad}" /></td>
            <td><c:out value="${trasa.dokad}" /></td>
            <td><c:out value="${trasa.odleglosc} km" /></td>
            <td><i><c:out value="${trasa.opis}" /></i></td>

            <td><a href="edytujTrase?id=${trasa.id}">
                    <span class="glyphicon glyphicon-edit" > </span> Edytuj</a></td>

            <td><a href="usun?id=${trasa.id}&typ=2"> 
                    <span class="glyphicon glyphicon-trash" ></span> Usuń</a></td>
        </tr>
    </c:forEach>
</table>

<a class="btn btn-primary btn-sm" href="${url}">Dodaj trase</a>




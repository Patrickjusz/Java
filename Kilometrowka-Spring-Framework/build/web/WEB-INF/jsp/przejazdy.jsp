<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajPrzejazd" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przegladać, modyfikować, dodawać i usuwać przejazdy.
</div>

<table class="table table-striped table-bordered table-condensed table-hover">
    <tr><th>Data</th><th>Trasa</th><th>Pojazd</th><th>Pracownik</th><th>Firma</th><th>Cel wyjazdu</th><th>Adnotacje</th></tr>
            <c:forEach var="przejazd" items="${przejazdy}">
        <tr>
            <td><c:out value="${przejazd.data}" /></td>
            <td><c:out value="${przejazd.nazwaTrasy}" /></td>
            <td><c:out value="${przejazd.nazwaPojazdu}" /></td>
            <td><c:out value="${przejazd.nazwaPracownika}" /></td> 
            <td><c:out value="${przejazd.nazwaFirmy}" /></td>
            <td><i><c:out value="${przejazd.celWyjazdu}" /></i></td>
            <td><c:out value="${przejazd.adnotacje}" /></td>

            <td><a href="edytujPrzejazd?id=${przejazd.id}">
                    <span class="glyphicon glyphicon-edit" > </span> Edytuj</a></td>

            <td><a href="usun?id=${przejazd.id}&typ=4"> 
                    <span class="glyphicon glyphicon-trash" ></span> Usuń</a></td>
        </tr>
    </c:forEach>
</table>

<c:if test="${paginacja==1}">
    <ul class="pager">
        <c:if test="${iloscPoprzednich>0}">
            <li>
                <a href="przejazdy?nr=${wtyl}">Poprzednia strona</a>
            </li>
        </c:if>
        <c:if test="${iloscNastepnych>0}">
            <li>
                <a href="przejazdy?nr=${wprzod}">Następna strona</a>
            </li>    
        </c:if>
    </ul>

</c:if>

<a class="btn btn-primary btn-sm" href="${url}">Dodaj przejazd</a>

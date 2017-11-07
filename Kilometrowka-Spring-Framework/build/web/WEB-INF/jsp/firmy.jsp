<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dodajFirme" var="url" htmlEscape="true"/>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przegladać, modyfikować, dodawać i usuwać firmy.
</div>

<table class="table table-striped table-bordered table-condensed table-hover">
    <tr><th>Nazwa firmy</th><th>Adres</th><th>Miasto</th><th>Kod pocztowy</th><th>NIP</th><th>Regon</th></tr>
            <c:forEach var="firma" items="${firmy}">
        <tr>
            <td><c:out value="${firma.nazwaFirmy}" /></td>
            <td><c:out value="${firma.adres}" /></td>
            <td><c:out value="${firma.miasto}" /></td>
            <td><c:out value="${firma.kodPocztowy}" /></td>
            <td><c:out value="${firma.nip}" /></td>
            <td><c:out value="${firma.regon}" /></td>
           
            <td><a href="edytujFirme?id=${firma.id}"> 
                    <span class="glyphicon glyphicon-edit" > </span> Edytuj</a></td>
                    
            <td><a href="usun?id=${firma.id}&typ=0"> 
                    <span class="glyphicon glyphicon-trash" ></span> Usuń</a></td> 
        </tr>
    </c:forEach>
</table>

<a class="btn btn-primary btn-sm" href="${url}">Dodaj firme</a>
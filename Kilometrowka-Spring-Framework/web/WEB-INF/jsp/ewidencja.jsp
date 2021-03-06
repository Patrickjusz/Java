<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="alert alert-dismissable alert-info">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    W tym module możesz przegladać ewidencje przejazdów.
</div>

<div>
    <form:form action="ewidencja" method="POST" commandName="ewidencjaForm">
        <label>Wybierz firme:</label>
        <form:select path="idFirmy" class="form-control">
            <form:options items="${listaFirm}" />
        </form:select>
        <br>
        <input type="submit" value="Wybierz" class="btn btn-primary btn-sm"/>
    </form:form>
</div>

<c:if test="${czyPost==1}">
    <table class="table table-striped table-bordered table-condensed table-hover" style="margin: 15px 15px 15px 15px;">
        <tr><th>Pojazd</th><th>Data ostatniego użycia pojazdu</th><th>Całkowita odległość</th><th>Całkowita kwota</th></tr>

        <c:forEach var="przejazd" items="${przejazdy}">
            <tr>
                <td><c:out value="${przejazd.nazwaPojazdu}" /></td>
                <td><c:out value="${przejazd.data}" /></td>
                <td>${przejazd.odlegloscTrasy} km</td>
                <td>${przejazd.cena} zł</td>
                <td>
                    <ul class="nav nav-tabs">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Miesiąc/rok<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="data" items="${przejazd.miesiacIRok}"> 
                                    <li><a href="ewidencjaNr?idFirmy=${przejazd.idFirmy}&idPojazdu=${przejazd.idPojazdu}&miesiac=${data.miesiac}&rok=${data.rok}" class="glyphicon glyphicon-search"> ${data.miesiac}/${data.rok}</a></li>   
                                    </c:forEach>
                            </ul>
                        </li>
                    </ul> 

                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

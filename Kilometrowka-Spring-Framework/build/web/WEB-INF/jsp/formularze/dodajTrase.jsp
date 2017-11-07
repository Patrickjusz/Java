<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<spring:url value="/dodajPojazd" var="url" htmlEscape="true"/>
<c:if test="${edycja==1}">
    <form:form action="edytujTrase" method="post" commandName="dodajTraseForm">
        <table border="0">

            <tr>
                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj dowolną nazwę trasy. Nazwa trasy służy tylko i wyłącznie do celów opisowych dla Ciebie."></span>&nbsp;<label class="form-label">Nazwa trasy:</label><form:input path="nazwa" class="form-control" /><font color="red"><form:errors path="nazwa" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miejsce początkowe trasy (miasto)."></span>&nbsp;<label class="form-label">Miejscowość źródłowa:</label><form:input path="skad" class="form-control" /><font color="red"><form:errors path="skad" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miejsce końcowe trasy (miasto)."></span>&nbsp;<label class="form-label">Miejscowość docelowa:</label><form:input path="dokad" class="form-control" /><font color="red"><form:errors path="dokad" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj długość w kilometrach wprowadzanej trasy."></span>&nbsp;<label class="form-label">Odległość w km:</label><form:input path="odleglosc" class="form-control" type="number" min="1" max="999999999"/><font color="red"><form:errors path="odleglosc" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj opis trasy. Opis trasy jest opcjonalny."></span>&nbsp;<label class="form-label">Opis*:</label><form:textarea path="opis" class="form-control" /><font color="red"><form:errors path="opis" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><form:input path="id" class="form-control" value="${trasa.id}" type="hidden"/><br><input type="submit" value="Edytuj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>
</c:if>
<c:if test="${edycja!=1}">


    <form:form action="dodajTrase" method="post" commandName="dodajTraseForm">
        <table border="0">

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj dowolną nazwę trasy. Nazwa trasy służy tylko i wyłącznie do celów opisowych dla Ciebie."></span>&nbsp;<label class="form-label">Nazwa trasy:</label><form:input path="nazwa" class="form-control" /><font color="red"><form:errors path="nazwa" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miejsce początkowe trasy (miasto)."></span>&nbsp;<label class="form-label">Miejscowość źródłowa:</label><form:input path="skad" class="form-control" /><font color="red"><form:errors path="skad" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miejsce końcowe trasy (miasto)."></span>&nbsp;<label class="form-label">Miejscowość docelowa:</label><form:input path="dokad" class="form-control" /><font color="red"><form:errors path="dokad" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj długość w kilometrach wprowadzanej trasy."></span>&nbsp;<label class="form-label">Odległość w km:</label><form:input path="odleglosc" class="form-control" type="number" min="1" max="999999999"/><font color="red"><form:errors path="odleglosc" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj opis trasy. Opis trasy jest opcjonalny."></span>&nbsp;<label class="form-label">Opis*:</label><form:textarea path="opis" class="form-control" /><font color="red"><form:errors path="opis" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>

</c:if>
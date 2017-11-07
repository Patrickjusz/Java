<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<c:if test="${edycja==1}">
    <form:form action="edytujFirme" method="post" commandName="dodajFirmeForm">
        <table border="0">
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj prawdziwą nazwę Twojej firmy."></span>&nbsp;<label class="form-label">Nazwa firmy</label>
                    <form:input path="nazwaFirmy" class="form-control" max="128"/><font color="red"><form:errors path="nazwaFirmy" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj nazwę ulicy, numer domu i mieszkania pod którym znajduje się Twoja firma."></span>&nbsp;<label class="form-label">Adres:</label><form:input path="adres" class="form-control" max="128" /><font color="red"><form:errors path="adres" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miasto w którym znajduje się Twoja firma."></span>&nbsp;<label class="form-label">Miasto:</label><form:input path="miasto" class="form-control" max="64" /><font color="red"><form:errors path="miasto" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj kod pocztowy firmy w formacie XX-XXX."></span>&nbsp;<label class="form-label">Kod pocztowy:</label><form:input path="kodPocztowy" class="form-control" pattern="^[0-9]{2}-[0-9]{3}$" placeholder="XX-XXX"  maxlength="6"/><font color="red"><form:errors path="kodPocztowy" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj NIP Twojej firmy."></span>&nbsp;<label class="form-label">NIP:</label><form:input path="nip" class="form-control" pattern="^((\d{3}[- ]\d{3}[- ]\d{2}[- ]\d{2})|(\d{3}[- ]\d{2}[- ]\d{2}[- ]\d{3}))$" max="32" placeholder="XXX-XX-XX-XXX"/><font color="red"><form:errors path="nip" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj regon Twojej firmy."> </span>&nbsp;<label class="form-label">REGON:</label><form:input path="regon" class="form-control" max="14"/><font color="red"><form:errors path="regon" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><form:input path="id" class="form-control" value="${firma.id}" type="hidden"/><br><input type="submit" value="Edytuj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>
</c:if>



<c:if test="${edycja!=1}">
    <form:form action="dodajFirme" method="post" commandName="dodajFirmeForm">
        <table border="0">
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj prawdziwą nazwę Twojej firmy."></span>&nbsp;<label class="form-label">Nazwa firmy</label>
                    <form:input path="nazwaFirmy" class="form-control" max="128"/><font color="red"><form:errors path="nazwaFirmy" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj nazwę ulicy, numer domu i mieszkania pod którym znajduje się Twoja firma."></span>&nbsp;<label class="form-label">Adres:</label><form:input path="adres" class="form-control" max="128"/><font color="red"><form:errors path="adres" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miasto w którym znajduje się Twoja firma."></span>&nbsp;<label class="form-label">Miasto:</label><form:input path="miasto" class="form-control"  max="64"/><font color="red"><form:errors path="miasto" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj kod pocztowy firmy w formacie XX-XXX."></span>&nbsp;<label class="form-label">Kod pocztowy:</label><form:input path="kodPocztowy" class="form-control" pattern="^[0-9]{2}-[0-9]{3}$" placeholder="XX-XXX" maxlength="6"/><font color="red"><form:errors path="kodPocztowy" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj NIP Twojej firmy."></span>&nbsp;<label class="form-label">NIP:</label><form:input path="nip" class="form-control" pattern="^((\d{3}[- ]\d{3}[- ]\d{2}[- ]\d{2})|(\d{3}[- ]\d{2}[- ]\d{2}[- ]\d{3}))$" max="32" placeholder="XXX-XX-XX-XXX"/><font color="red"><form:errors path="nip" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj regon Twojej firmy."> </span>&nbsp;<label class="form-label">REGON:</label><form:input path="regon" class="form-control" max="14"/><font color="red"><form:errors path="regon" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>
</c:if>
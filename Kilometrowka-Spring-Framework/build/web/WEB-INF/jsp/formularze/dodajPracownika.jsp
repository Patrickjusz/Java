<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:if test="${edycja==1}">
    <form:form action="edytujPracownika" method="post" commandName="dodajPracownikaForm">
        <table border="0">

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj imię pracownika."></span>&nbsp;<label class="form-label">Imie:</label><form:input path="imie" class="form-control" placeholder="Imię pracownika"/><font color="red"><form:errors path="imie" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj nazwisko pracownika."></span>&nbsp;<label class="form-label">Nazwisko:</label><form:input path="nazwisko" class="form-control" placeholder="Nazwisko pracownika"/><font color="red"><form:errors path="nazwisko" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj adres zamieszkania pracownika (nazwę ulicy, numer domu i mieszkania)."></span>&nbsp;<label class="form-label">Adres:</label><form:input path="adres" class="form-control" placeholder="Ulica i nr. mieszkania"/><font color="red"><form:errors path="adres" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj kod pocztowy firmy w formacie XX-XXX."></span>&nbsp;<label class="form-label">Kod pocztowy:</label><form:input path="kodPocztowy" class="form-control" pattern="^[0-9]{2}-[0-9]{3}$" placeholder="XX-XXX" maxlength="6"/><font color="red"><form:errors path="kodPocztowy" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miasto w którym zamieszkuje pracownik."></span>&nbsp;<label class="form-label">Miasto:</label><form:input path="miasto" class="form-control" placeholder="Miasto pracownika"/><font color="red"><form:errors path="miasto" /></font></td>
            </tr>



            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj telefon kontaktowy do pracownika. To pole jest polem opcjonalnym."></span>&nbsp;<label class="form-label">Telefon*:</label><form:input path="telefon" class="form-control" placeholder="Telefon do pracownika" type="tel" /><font color="red"><form:errors path="telefon" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><form:input path="id" class="form-control" value="${pracownik.id}" type="hidden"/><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm" /></td>
            </tr>
        </table>
    </form:form>
</c:if>


<c:if test="${edycja!=1}">
    <form:form action="dodajPracownika" method="post" commandName="dodajPracownikaForm">
        <table border="0">

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj imię pracownika."></span>&nbsp;<label class="form-label">Imie:</label><form:input path="imie" class="form-control" placeholder="Imię pracownika"/><font color="red"><form:errors path="imie" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj nazwisko pracownika."></span>&nbsp;<label class="form-label">Nazwisko:</label><form:input path="nazwisko" class="form-control" placeholder="Nazwisko pracownika"/><font color="red"><form:errors path="nazwisko" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj adres zamieszkania pracownika (nazwę ulicy, numer domu i mieszkania)."></span>&nbsp;<label class="form-label">Adres:</label><form:input path="adres" class="form-control" placeholder="Ulica i nr. mieszkania"/><font color="red"><form:errors path="adres" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj kod pocztowy firmy w formacie XX-XXX."></span>&nbsp;<label class="form-label">Kod pocztowy:</label><form:input path="kodPocztowy" class="form-control" pattern="^[0-9]{2}-[0-9]{3}$" placeholder="XX-XXX" maxlength="6"/><font color="red"><form:errors path="kodPocztowy" /></font></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj miasto w którym zamieszkuje pracownik."></span>&nbsp;<label class="form-label">Miasto:</label><form:input path="miasto" class="form-control" placeholder="Miasto pracownika"/><font color="red"><form:errors path="miasto" /></font></td>
            </tr>



            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj telefon kontaktowy do pracownika. To pole jest polem opcjonalnym."></span>&nbsp;<label class="form-label">Telefon*:</label><form:input path="telefon" class="form-control" placeholder="Telefon do pracownika" type="tel" /><font color="red"><form:errors path="telefon" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm" /></td>
            </tr>
        </table>
    </form:form>
</c:if>
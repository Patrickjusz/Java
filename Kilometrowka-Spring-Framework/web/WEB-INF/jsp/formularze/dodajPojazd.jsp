<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:if test="${edycja==1}">
    <form:form action="edytujPojazd" method="post" commandName="dodajPojazdForm">
        <table border="0">

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj prawdziwą nazwę Twojego pojazdu (marka i model)."></span>&nbsp;<label class="form-label">Nazwa:</label><form:input path="nazwa" class="form-control" placeholder="Nazwa pojazdu"/><font color="red"><form:errors path="nazwa" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj numer rejestracyjny pojazdu."></span>&nbsp;<label class="form-label">Numer rejestracyjny:</label><form:input path="numerRejestracyjny" class="form-control" placeholder="Numer rej. pojazdu"/><font color="red"><form:errors path="numerRejestracyjny" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz z listy pojemność skokową silnika pojazdu."></span>&nbsp;<label class="form-label">Pojemność silnika:</label>
                    <form:select path="pojemnoscSilnika" class="form-control">  
                        <c:if test="${dodajPojazdForm.pojemnoscSilnika==1}">
                            <form:option value="1" selected="selected" >Samochod os. poj. poniżej 900cm^3</form:option>  
                            <form:option value="2">Samochod os. poj. powyżej 900cm^3</form:option>  
                            <form:option value="3">Motocykl</form:option>  
                            <form:option value="4">Motorower</form:option>  
                        </c:if>

                        <c:if test="${dodajPojazdForm.pojemnoscSilnika==2}">
                            <form:option value="1">Samochod os. poj. poniżej 900cm^3</form:option>  
                            <form:option value="2" selected="selected">Samochod os. poj. powyżej 900cm^3</form:option>  
                            <form:option value="3">Motocykl</form:option>  
                            <form:option value="4">Motorower</form:option>  
                        </c:if>

                        <c:if test="${dodajPojazdForm.pojemnoscSilnika==3}">
                            <form:option value="1">Samochod os. poj. poniżej 900cm^3</form:option>  
                            <form:option value="2">Samochod os. poj. powyżej 900cm^3</form:option>  
                            <form:option value="3" selected="selected">Motocykl</form:option>  
                            <form:option value="4">Motorower</form:option>  
                        </c:if>

                        <c:if test="${dodajPojazdForm.pojemnoscSilnika==4}">
                            <form:option value="1">Samochod os. poj. poniżej 900cm^3</form:option>  
                            <form:option value="2">Samochod os. poj. powyżej 900cm^3</form:option>  
                            <form:option value="3">Motocykl</form:option>  
                            <form:option value="4" selected="selected">Motorower</form:option>  
                        </c:if>



                    </form:select>  <font color="red"><form:errors path="pojemnoscSilnika" /></font></td>

    <td><!--form:select path="country" items="${countryList}" /--></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj opis pojazdu. Opis jest opcjonalny i nie musisz go podawać."></span>&nbsp;<label class="form-label">Opis*:</label><form:textarea path="opis" class="form-control" placeholder="Krótki opis pojazdu"/><font color="red"><form:errors path="opis" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><form:input path="id" class="form-control" value="${przejazd.id}" type="hidden"/><form:input path="id" class="form-control" value="${pojazd.id}" type="hidden"/><br><input type="submit" value="Edytuj" class="btn btn-primary btn-sm" class="form-control" /></td>
            </tr>
        </table>

    </form:form>
</c:if>




<c:if test="${edycja!=1}">
    <form:form action="dodajPojazd" method="post" commandName="dodajPojazdForm">
        <table border="0">

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj prawdziwą nazwę Twojego pojazdu (marka i model)."></span>&nbsp;<label class="form-label">Nazwa:</label><form:input path="nazwa" class="form-control" placeholder="Nazwa pojazdu"/><font color="red"><form:errors path="nazwa" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj numer rejestracyjny pojazdu."></span>&nbsp;<label class="form-label">Numer rejestracyjny:</label><form:input path="numerRejestracyjny" class="form-control" placeholder="Numer rej. pojazdu"/><font color="red"><form:errors path="numerRejestracyjny" /></font></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz z listy pojemność skokową silnika pojazdu."></span>&nbsp;<label class="form-label">Pojemność silnika:</label>
                    <form:select path="pojemnoscSilnika" class="form-control">  
                        <form:option value="1">Samochod os. poj. poniżej 900cm^3</form:option>  
                        <form:option value="2"  selected="selected" >Samochod os. poj. powyżej 900cm^3</form:option>  
                        <form:option value="3">Motocykl</form:option>  
                        <form:option value="4">Motorower</form:option>  
                    </form:select>  <font color="red"><form:errors path="pojemnoscSilnika" /></font></td>

    <td><!--form:select path="country" items="${countryList}" /--></td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj opis pojazdu. Opis jest opcjonalny i nie musisz go podawać."></span>&nbsp;<label class="form-label">Opis*:</label><form:textarea path="opis" class="form-control" placeholder="Krótki opis pojazdu"/><font color="red"><form:errors path="opis" /></font></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm" class="form-control" /></td>
            </tr>
        </table>

    </form:form></c:if>
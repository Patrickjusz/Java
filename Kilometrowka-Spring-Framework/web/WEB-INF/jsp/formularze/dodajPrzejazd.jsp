<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<c:if test="${edycja==1}">

    <form:form action="edytujPrzejazd" method="post" commandName="dodajPrzejazdForm" onsubmit = "return sprawdzCelPrzejazdu();">
        <table border="0" onload="myFunction()">
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz pojazd który bierze udział w przejeździe. Nowe pojazdy możesz dodać w zakładce pojazdy."></span>&nbsp;<label class="form-label">Pojazd</label>
                    <!--form:input path="idPojazdu" class="form-control" /-->

                    <form:select path="idPojazdu" class="form-control">
                        <form:options items="${listaPojazdow}" />
                    </form:select>

                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz trase którą przejechał pojazd. Nową trase możesz dodać w zakładce trasy."></span>&nbsp;<label class="form-label">Trasa:</label><!--form:input path="idTrasy" class="form-control" /-->
                    <form:select path="idTrasy" class="form-control">
                        <form:options items="${listaTras}" />
                    </form:select>
                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz firmę dla której został wykonany bieżący przejazd. Nową firme możesz dodać w zakładce firmy."></span>&nbsp;<label class="form-label">Firma:</label><!--form:input path="idFirmy" class="form-control" /-->
                    <form:select path="idFirmy" class="form-control">
                        <form:options items="${listaFirm}" />
                    </form:select>
                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz pracownika który wykonał przejazd. Nowego pracownika możesz dodać w zakładce pracownicy."></span>&nbsp;<label class="form-label">Pracownik:</label><!--form:input path="idPracownika" class="form-control" /-->
                    <form:select path="idPracownika" class="form-control">
                        <form:options items="${listaPracownikow}" />
                    </form:select>
                </td>
            </tr>


            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj krótki cel wykonanego przejazdu (2-32 znaki)."></span>&nbsp;<label class="form-label">Cel wyjazdu:</label><form:input path="celWyjazdu" class="form-control" min="2"/></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj datę przejazdu."></span>&nbsp;<label class="form-label">Data:</label><form:input path="data" class="form-control" type="date" value="${przejazd.data}"/></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj krótki opcjonalny opis przejazdu."></span>&nbsp;<label class="form-label">Adnotacje*:</label><form:input path="adnotacje" class="form-control" /></td>
            </tr>




            <tr>
                <td colspan="2" align="center"><form:input path="id" class="form-control" value="${przejazd.id}" type="hidden"/><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>
</c:if>


<c:if test="${edycja!=1}">
    <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");%>

    <form:form action="dodajPrzejazd" method="post" commandName="dodajPrzejazdForm" onsubmit = "return sprawdzCelPrzejazdu();">
        <table border="0" onload="myFunction()">
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz pojazd który bierze udział w przejeździe. Nowe pojazdy możesz dodać w zakładce Pojazdy."></span>&nbsp;<label class="form-label">Pojazd</label>
                    <!--form:input path="idPojazdu" class="form-control" /-->

                    <form:select path="idPojazdu" class="form-control">
                        <form:options items="${listaPojazdow}" />
                    </form:select>

                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz trase którą przejechał pojazd. Nową trase możesz dodać w zakładce trasy."></span>&nbsp;<label class="form-label">Trasa:</label><!--form:input path="idTrasy" class="form-control" /-->
                    <form:select path="idTrasy" class="form-control">
                        <form:options items="${listaTras}" />
                    </form:select>
                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz firmę dla której został wykonany bieżący przejazd. Nową firme możesz dodać w zakładce firmy."></span>&nbsp;<label class="form-label">Firma:</label><!--form:input path="idFirmy" class="form-control" /-->
                    <form:select path="idFirmy" class="form-control">
                        <form:options items="${listaFirm}" />
                    </form:select>
                </td>
            </tr>
            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Wybierz pracownika który wykonał przejazd. Nowego pracownika możesz dodać w zakładce pracownicy."></span>&nbsp;<label class="form-label">Pracownik:</label><!--form:input path="idPracownika" class="form-control" /-->
                    <form:select path="idPracownika" class="form-control">
                        <form:options items="${listaPracownikow}" />
                    </form:select>
                </td>
            </tr>


            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj krótki cel wykonanego przejazdu (2-32 znaki)."></span>&nbsp;<label class="form-label">Cel wyjazdu:</label><form:input path="celWyjazdu" class="form-control"/></td>
            </tr>

            <tr>


            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj datę w której miał miejsce przejazd."></span>&nbsp;<label class="form-label">Data:</label><form:input path="data" class="form-control" type="date" value="<%= df.format(new java.util.Date())%>" /></td>
            </tr>

            <tr>

                <td><span style="color: silver;" class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-original-title="Podaj krótki opcjonalny opis przejazdu."></span>&nbsp;<label class="form-label">Adnotacje*:</label><form:input path="adnotacje" class="form-control" /></td>
            </tr>



            <tr>
                <td colspan="2" align="center"><br><input type="submit" value="Dodaj" class="btn btn-primary btn-sm"/></td>
            </tr>
        </table>
    </form:form>
</c:if>

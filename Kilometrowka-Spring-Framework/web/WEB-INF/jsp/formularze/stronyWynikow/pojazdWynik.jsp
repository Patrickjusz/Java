<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/pojazdy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

    <div class="alert alert-dismissable alert-success">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <b>Operacja wykonana pomyślnie!</b> Dziekujemy za dodanie pojazdu.
    </div>
        <table border="0">

            <tr>
                <td><b>Nazwa:</b></td>
                
                <td><c:out value="${dodajPojazdForm.nazwa}" /></td>
            </tr>
            <tr>
                <td><b>Numer rejestracyjny:</b></td>
                <td><c:out value="${dodajPojazdForm.numerRejestracyjny}" /></td>
            </tr>
            <tr>
                <td><b>Pojemnosc:</b></td>
                <td><c:out value="${dodajPojazdForm.pojemnoscSilnika}" /></td>
            </tr>
            
            <tr>
                <td><b>Opis:</b></td>
                <td><c:out value="${dodajPojazdForm.opis}" /></td>
            </tr>
            
            <tr>
            </tr>
 
        </table>
<br><a class="btn btn-info btn-sm" href="${url}">Przejdź do strony z pojazdami</a>
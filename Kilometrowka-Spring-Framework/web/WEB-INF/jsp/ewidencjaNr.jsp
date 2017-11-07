<%@page import="kilometrowka.klasy.Stawka"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<div id="drukowanie">

    <hr>
    <center><h2>Ewidencja przebiegu pojazdu <b>${miesiac} ${rok}</b></h2></center>
    <hr>

    <div style="float: left; min-width: 100%;">
        <div style="float: left; min-width: 85%;">
            <b>Pojazd:</b> <c:out value="${pojazd.nazwa}" /><br>
            <b>Nr. rejestracyjny:</b> <c:out value="${pojazd.numerRejestracyjny}" /><br>
            <b>Pojemność silika:</b> <c:out value="${pojazd.pojemnoscSilnika}" /><br>
        </div>
        <div style="float: left; min-width:15%">
            <b>Firma:</b> <c:out value="${firma.nazwaFirmy}" /><br>
            <b>NIP:</b> <c:out value="${firma.nip}" /><br>
            <b>REGON:</b> <c:out value="${firma.regon}" /><br><br>
        </div>
    </div>

    <table class="table table-striped table-bordered table-condensed table-hover">
        <tr><th>LP</th><th>Data</th><th>Trasa</th><th>Cel wyjazdu</th><th>Pracownik</th><th>Stawka</th><th>Przejechane km</th><th>Wartość</th><th style="min-width: 80px;">Podpis</th></tr>

        <c:set var="sumaZl" value="${0}"/>    
        <c:set var="sumaKm" value="${0}"/> 
        <c:set var="lp" value="${0}"/> 

        <c:forEach var="przejazd" items="${przejazdy}">
            <c:set var="lp" value="${lp+1}"/>          
            <tr class="wycentruj">
                <td>${lp}</td>
                <td><c:out value="${przejazd.data}" /></td>
                <td><c:out value="${przejazd.nazwaTrasy}" /></td>
                <td style="max-width: 60px;"><c:out value="${przejazd.celWyjazdu}" /></td>
                <td><c:out value="${przejazd.nazwaPracownika}" /></td> 
                <td><fmt:formatNumber pattern="0.0000" value="${pojazd.stawka}"/></td>
                <td>${przejazd.odlegloscTrasy}km</td>
                <td><fmt:formatNumber pattern="0.00" value="${pojazd.stawka * przejazd.odlegloscTrasy}"/>zł</td>
                <td> </td>
            </tr>    
            <c:set var="sumaZl" value="${sumaZl + pojazd.stawka * przejazd.odlegloscTrasy}"/>   
            <c:set var="sumaKm" value="${sumaKm + przejazd.odlegloscTrasy}"/> 
        </c:forEach>

    </table>

    <b>Suma przejechanych kilometrów:</b> 
    <fmt:formatNumber pattern="0.00" value='${sumaKm}' type="currency" groupingUsed='true' /> km<br>

    <b>Wartość w złotówkach:</b> 
    <fmt:formatNumber pattern="0.00" value='${sumaZl}' type="currency" groupingUsed='true' /> zł

</div>

<hr>
<p>
    (<a  style="color: green;" href="#" onclick="drukuj('#drukowanie')">
        <span class="glyphicon glyphicon-print">&nbsp;Drukuj</span></a>)

    &nbsp;&nbsp;(<a style="color: green;" href="raport.txt?idFirmy=${firma.id}&idPojazdu=${pojazd.id}&miesiac=${miesiacLiczbowo}&rok=${rok}" download="raport-${miesiacLiczbowo}-${rok}.txt">
        <span style="color: green;" class="glyphicon glyphicon-download-alt">&nbsp;Pobierz raport</span></a>)


</p>

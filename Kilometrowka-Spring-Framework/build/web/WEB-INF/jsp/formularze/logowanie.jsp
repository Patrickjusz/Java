<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<center>
<c:if test="${logowanie==1}">
    <div class="alert alert-dismissable alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    Dane logowania nieprawidłowe. Spróbuj ponownie!
</div>
</c:if>

<c:if test="${reg==1}">
    <div class="alert alert-dismissable alert-success">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    Rejestracja przebiegła pomyślnie. Teraz możesz się zalogować.
</div>
</c:if>
<form:form action="logowanie" method="post" commandName="dodajLogowanieForm">
    <table border="0">

        <tr>

            <td><label class="form-label">Login:</label><form:input path="login" class="form-control" /><font color="red"><form:errors path="login" /></font></td>
        </tr>
        <tr>

            <td><label class="form-label">Hasło:</label><form:input path="haslo" class="form-control" type="password" /><font color="red"><form:errors path="haslo" /></font></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><br><input type="submit" value="Loguj" class="btn btn-primary btn-sm"/></td>
        </tr>
    </table>
</form:form>
</center>
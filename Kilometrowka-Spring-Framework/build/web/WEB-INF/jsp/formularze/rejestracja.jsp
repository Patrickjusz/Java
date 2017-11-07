<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<center>
<c:if test="${reg==1}">
    <div class="alert alert-dismissable alert-danger">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        Podany login jest zajęty! Proszę użyć innego.
    </div>
</c:if>

<c:if test="${reg==2}">
    <div class="alert alert-dismissable alert-danger">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        Podane hasła nie są identyczne!
    </div>
</c:if>
<form:form action="rejestracja" method="post" commandName="dodajLogowanieForm" onsubmit = "return sprawdzHasla();">
    <table border="0">

        <tr>

            <td><label class="form-label">Login:</label><form:input path="login" class="form-control" /><font color="red"><form:errors path="login" /></font></td>
        </tr>
        <tr>

            <td><label class="form-label">Hasło:</label><form:input path="haslo" class="form-control" type="password" /><font color="red"><form:errors path="haslo" /></font></td>
        </tr>

        <tr>

            <td><label class="form-label">Powtórz hasło:</label><form:input path="haslo2" class="form-control" type="password" /><font color="red"><form:errors path="haslo2" /></font></td>
        </tr>

        <tr>

            <td><label class="form-label">e-mail:</label><form:input path="email" class="form-control" type="email" /><font color="red"><form:errors path="email" /></font></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><br><input type="submit" value="Zarejestruj" class="btn btn-primary btn-sm"/></td>
        </tr>
    </table>
</form:form>
</center>
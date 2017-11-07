<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/przejazdy" var="url" htmlEscape="true"/>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:out value="${dodajLogowanieForm.adnotacje}" />
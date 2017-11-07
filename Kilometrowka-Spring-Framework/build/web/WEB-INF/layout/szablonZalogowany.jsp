<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>

<html>
    <head>
        <title><tiles:getAsString name="title" /></title>
        <meta name="robots" content="noindex">
        <meta name="robots" content="nofollow">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="zasoby/css/bootstrap.min.css">
        <script type="text/javascript" src="zasoby/jquery.min.js"></script>
        <script type="text/javascript" src="zasoby/js/bootstrap.min.js"></script> 
        <script src="zasoby/mojeJS.js"></script>
        <link rel="stylesheet" type="text/css" href="zasoby/mojCSS.css" />

    </head>

    <body>
        <div class="container">
            <tiles:insertAttribute name="nav" />
            <div class="container">
                <h2><tiles:getAsString name="title" /></h2>
                <tiles:insertAttribute name="body" />
                <tiles:insertAttribute name="footer" />
            </div>
        </div>
    </body>
</html>


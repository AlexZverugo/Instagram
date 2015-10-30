<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Instagram</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

        <link rel="stylesheet" href="<c:url value="/resources/css/common.sigup.css"/>" type="text/css"/>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">

    </head>
<body>
<div class="container" align="center">
    <%@ include file="toolkit/language.jsp" %>
    <h2><a href="/login"><spring:message code="welcome.label.signin"/></a></h2>

    <h2><a href="/registration"><spring:message code="welcome.label.signup"/></a></h2>
</div>
</body>
</html>

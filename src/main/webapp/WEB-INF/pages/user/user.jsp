<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>User page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <%@ include file="../common/toolkit/navbar.jsp" %>

    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <h2>ID: ${id}</h2>

    <form action="/users/addPost?id=${id}" method="post" class="form-horizontal" role="form">
        <input type="submit" class="btn btn-primary" value="<spring:message code="user.button.addpost"/>">
    </form>
    <h2><a href="${logoutUrl}"><spring:message code="user.button.logout"/></a></h2>
</div>

</body>
</html>

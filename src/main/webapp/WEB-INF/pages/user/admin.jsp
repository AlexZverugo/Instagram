<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Admin page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <%@ include file="../common/toolkit/navbar.jsp" %>

    <c:forEach var="user" items="${users}">
        <form:form action="/admin/deleteUser?id=${user.id}" method="post" class="form-horizontal" role="form">

            <label><spring:message code="admin.label.username"/>:</label> </label>
            <c:out value="${user.login}"/><br>
            <label><spring:message code="admin.label.email"/>:</label>
            <c:out value="${user.email}"/><br>

            <input type="submit" class="btn btn-primary" value="<spring:message code="admin.button.delete"/>">
        </form:form>
    </c:forEach>

    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <h2><a href="${logoutUrl}"><spring:message code="admin.button.logout"/></a></h2>
</div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags"%>

<html>
<head>
    <toolkit:header title="Instagram admin page"/>
</head>
<body>
<div class="container" align="center">
    <toolkit:navbar/>
    <c:forEach var="user" items="${users}">
        <form:form action="/admin/deleteUser?id=${user.id}" method="post" class="form-horizontal" role="form">

            <label><spring:message code="admin.label.username"/>:</label> </label>
            <c:out value="${user.login}"/><br>
            <label><spring:message code="admin.label.email"/>:</label>
            <c:out value="${user.email}"/><br>

            <input type="submit" class="btn btn-primary" value="<spring:message code="admin.button.delete"/>">
        </form:form>
    </c:forEach>

</div>
</body>
</html>

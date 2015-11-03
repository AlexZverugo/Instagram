<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <toolkit:header title="Instagram admin page"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/admin.css"/>" type="text/css"/>
</head>
<body class="bg-common">
<toolkit:navbar/>
<div class="container" align="center">
    <br><br>
    <br><br>
    <table class="table table-stl">
        <tr class="tr-stl">
            <th><spring:message code="admin.label.username"/></th>
            <th><spring:message code="admin.label.email"/></th>
            <th><spring:message code="admin.tablehead.delete"/></th>
            <th><spring:message code="admin.tablehead.block"/></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <form:form action="/admin/deleteUser?id=${user.id}" method="post" class="form-horizontal" role="form">
                <tr>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td>
                        <input type="submit" class="btn btn-danger"
                               value="<spring:message code="admin.button.delete"/>">
                    </td>
                </tr>

            </form:form>
        </c:forEach>
    </table>
</div>
</body>
</html>

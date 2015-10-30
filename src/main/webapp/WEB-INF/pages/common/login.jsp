<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>

<html>
<head>
    <title>Add user</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/resources/css/signin.css"/>" type="text/css"/>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <%@ include file="toolkit/language.jsp" %>
    <h2><spring:message code="login.label.signin"/></h2>
    <h6 class="exc-col">${message}</h6>
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post" class="form-horizontal">


        <div class="form-group">
            <div class="col-sm-10 my-col-sm-10">
                <input class="form-control" name="username" type="text"
                       placeholder="<spring:message code="login.placeholder.login"/>"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-10 my-col-sm-10">
                <input class="form-control" name="password" type="password"
                       placeholder="<spring:message code="login.placeholder.password"/>"/>
            </div>
        </div>


        <spring:message code="login.button.signin" var="SubmitLabel"/>
        <input type="submit" class="btn btn-info my-btn" value="${SubmitLabel}"/>
    </form>

    <form action="/registration" method="get" class="form-horizontal">
        <spring:message code="login.button.signup" var="SignUpButton"/>
        <input type="submit" class="btn btn-info my-btn" value="${SignUpButton}"/>
    </form>
</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <spring:message code="pages.title.login" var="title"/>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span id="login-main-label"><spring:message code="login.label.signin"/></span></h2>
    <c:if test="${message}">
        <h6 class="login-exc-col"><spring:message code="login.label.invalidlogin"/></h6>
    </c:if>
    <c:url value="/j_spring_security_check" var="loginUrl"/>

    <spring:message code="login.label.login" var="loginLabel"/>
    <spring:message code="login.label.password" var="passwordLabel"/>
    <form action="${loginUrl}" method="post" class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${loginLabel}:</span></h5>
                <input class="form-control" name="username" type="text"
                       placeholder="<spring:message code="login.placeholder.login"/>" autofocus/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${passwordLabel}:</span></h5>
                <input class="form-control" name="password" type="password"
                       placeholder="<spring:message code="login.placeholder.password"/>"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <div class="col-sm-7">
                <i><spring:message code="login.label.rememberme"/></i>
                <input checked type="checkbox" name="remember-me"/>
            </div>
        </div>
        <spring:message code="login.button.signin" var="SubmitLabel"/>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${SubmitLabel}"/>
    </form>

    <c:url value="/registration" var="regUrl"/>
    <form action="${regUrl}" method="get" class="form-horizontal">
        <spring:message code="login.button.signup" var="SignUpButton"/>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${SignUpButton}"/>
    </form>
</div>

</body>
</html>
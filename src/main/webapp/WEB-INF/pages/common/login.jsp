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

    <form action="${loginUrl}" method="post" class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input class="form-control" name="username" type="text"
                       placeholder="<spring:message code="login.placeholder.login"/>" autofocus/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input class="form-control" name="password" type="password"
                       placeholder="<spring:message code="login.placeholder.password"/>"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <div class="col-sm-7">
                <i><spring:message code="login.label.rememberme"/></i>
                <input type="checkbox" name="remember-me"/>
            </div>
        </div>
        <spring:message code="login.button.signin" var="SubmitLabel"/>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${SubmitLabel}"/>
    </form>
    <hr>
    <c:url value="/registration" var="regUrl"/>
    <spring:message code="login.label.donothaveaccountyet" var="donotHaveAccount"/>
    <spring:message code="login.label.joinnow" var="joinNow"/>
    <label for="signInLink" style="color:#808080">
        ${donotHaveAccount}
    </label>
    <a href="${regUrl}" id="signInLink">${joinNow}</a>
    <br><br>
</div>

</body>
</html>
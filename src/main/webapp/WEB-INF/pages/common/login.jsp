<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags"%>

<html>
<head>
    <toolkit:header title="Instagram login page"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span id="login-main-label"><spring:message code="login.label.signin"/></span></h2>
    <h6 class="login-exc-col">${message}</h6>
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post" class="form-horizontal">


        <div class="form-group">
            <div class="col-sm-10 my-col-sm-10">
                <input class="form-control" name="username" type="text"
                       placeholder="<spring:message code="login.placeholder.login"/>" autofocus/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-10 my-col-sm-10">
                <input class="form-control" name="password" type="password"
                       placeholder="<spring:message code="login.placeholder.password"/>"/>
            </div>
        </div>


        <spring:message code="login.button.signin" var="SubmitLabel"/>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${SubmitLabel}"/>
    </form>

    <form action="/registration" method="get" class="form-horizontal">
        <spring:message code="login.button.signup" var="SignUpButton"/>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${SignUpButton}"/>
    </form>
</div>

</body>
</html>
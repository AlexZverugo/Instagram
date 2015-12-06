<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html lang="en">
<head>
    <spring:message code="pages.title.signup" var="title"/>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span class="signup-main-label"><spring:message code="signup.label.input_inf"/></span></h2>
    <c:url value="/registration/registerUser" var="regUserUrl"/>
    <form:form action="${regUserUrl}" method="post" commandName="userForm" class="form-horizontal"
               role="form">

        <spring:message code="login.label.login" var="loginLabel"/>
        <spring:message code="login.label.password" var="passwordLabel"/>
        <spring:message code="login.label.repeatedpassword" var="repeatedpasswordLabel"/>
        <spring:message code="login.label.email" var="emailLabel"/>

        <form:errors path="login" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${loginLabel}:</span></h5>
                <spring:message code="signup.placeholder.login" var="Login"/>
                <form:input path="login" class="form-control" type="text" placeholder="${Login}" autofocus="true"/>
            </div>
        </div>


        <form:errors path="password" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${passwordLabel}:</span></h5>
                <spring:message code="signup.placeholder.password" var="Password"/>
                <form:input path="password" class="form-control" type="password" placeholder="${Password}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="repeatedPassword" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${repeatedpasswordLabel}:</span></h5>
                <spring:message code="signup.placeholder.repeatedlpassword" var="RepeatedPassword"/>
                <form:input path="repeatedPassword" class="form-control" type="password"
                            placeholder="${RepeatedPassword}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="email" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <h5 align="left"><span class="signup-label">${emailLabel}:</span></h5>
                <form:input path="email" class="form-control" type="email" placeholder="Email" disabled="false"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <spring:message code="signup.button.signup" var="SignUpButton"/>
                <input type="submit" class="btn-bg btn btn-info my-btn-size" value="${SignUpButton}"/>
            </div>
        </div>
        <c:url value="/login" var="loginUrl"/>
        <spring:message code="signup.button.login" var="SignInButton"/>
        <a href="${loginUrl}"><input type="button" class="btn btn-info btn-bg my-btn-size" value="${SignInButton}"/></a>
    </form:form>
</div>

</body>
</html>

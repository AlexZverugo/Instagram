<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags"%>
<html lang="en">
<head>
    <toolkit:header title="Instagram registration page"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span id="signup-main-label"><spring:message code="signup.label.input_inf"/></span></h2>
    <form:form action="/registration/registerUser" method="post" commandName="userForm" class="form-horizontal"
               role="form">
        <form:errors path="login" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="my-col-sm-10 col-sm-10">
                <spring:message code="signup.placeholder.login" var="Login"/>
                <form:input path="login" class="form-control" type="text" placeholder="${Login}"/>
            </div>
        </div>

        <form:errors path="password" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="my-col-sm-10 col-sm-10">
                <spring:message code="signup.placeholder.password" var="Password"/>
                <form:input path="password" class="form-control" type="password" placeholder="${Password}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="repeatedPassword" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="my-col-sm-10 col-sm-10">
                <spring:message code="signup.placeholder.repeatedlpassword" var="RepeatedPassword"/>
                <form:input path="repeatedPassword" class="form-control" type="password" placeholder="${RepeatedPassword}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="email" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="my-col-sm-10 col-sm-10">
                <form:input path="email" class="form-control" type="email" placeholder="Email" disabled="false"/>
            </div>
        </div>

        <spring:message code="signup.button.signup" var="SignUpButton"/>
        <input type="submit" class="btn-bg btn btn-info my-btn-size" value="${SignUpButton}"/>
    </form:form>
</div>

</body>
</html>

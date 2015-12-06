<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <toolkit:header title="Instagram"/>
    <link href="<c:url value="/resources/css/welcome.css"/>" type="text/css" rel="stylesheet">
</head>
<body class="welcome-bg">
<toolkit:langnav/>
<c:url value="/login" var="loginUrl"/>
<c:url value="/registration" var="regUrl"/>
<div class="container my-container" align="center">
    <div id="welcome-cont">
        <img src="<c:url value="/resources/photo/insta.png"/>"><br><br>
        <a href="${loginUrl}" class="btn btn-info btn-lg welcome-btn">
            <spring:message code="welcome.label.signin"/></a><br><br>
        <a href="${regUrl}" class="btn btn-info btn-lg welcome-btn">
            <spring:message code="welcome.label.signup"/></a><br><br>
    </div>
</div>
</body>
</html>

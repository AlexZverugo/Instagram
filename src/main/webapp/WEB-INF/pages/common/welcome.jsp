<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container my-container" align="center">
    <div id="cont">
        <img src="<c:url value="/resources/foto/insta.png"/>"><br><br>
        <a href="/login" class="btn btn-info btn-lg my-btn">
            <spring:message code="welcome.label.signin"/></a><br><br>
        <a href="/registration" class="btn btn-info btn-lg my-btn">
            <spring:message code="welcome.label.signup"/></a><br><br>
    </div>
</div>
</body>
</html>

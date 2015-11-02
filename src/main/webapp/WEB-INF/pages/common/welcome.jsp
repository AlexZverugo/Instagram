<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <toolkit:header title="Instagram"/>
</head>
<body>
<div class="container" align="center">
    <toolkit:langnav/>
    <h2><a href="/login"><spring:message code="welcome.label.signin"/></a></h2>

    <h2><a href="/registration"><spring:message code="welcome.label.signup"/></a></h2>
</div>
</body>
</html>

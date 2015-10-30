<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Instagram</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true" aria-expanded="false">
                    <spring:message code="language.label.language"/><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en"><spring:message code="language.item.english"/> </a></li>
                    <li><a href="?lang=ru"><spring:message code="language.item.russian"/></a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>

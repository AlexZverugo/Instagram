<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Post</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <%@ include file="../common/toolkit/language.jsp" %>
    <form:form action="/post/addPost" method="post" commandName="postForm">

        <div class="form-group">
            <label for="comment"><spring:message code="post.label.inputpost"/>:</label>
            <form:textarea  path="postContent" class="form-control" rows="5" id="comment"/>
        </div>

        <p><input type="submit" class="btn btn-primary" value="<spring:message code="post.button.send"/>"></p>
    </form:form>
</div>
</body>
</html>

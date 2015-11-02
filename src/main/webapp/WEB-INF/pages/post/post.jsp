<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <toolkit:header title="Post adder page"/>
</head>
<body>
<div class="container" align="center">
    <toolkit:navbar/>
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

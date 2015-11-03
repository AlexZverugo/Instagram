<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <toolkit:header title="Post adder page"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/post.css"/>" type="text/css"/>
</head>
<body class="bg-common">
<toolkit:navbar/>
<div class="container" align="center">
    <br><br>
    <br><br>
    <form:form action="/post/addPost" method="post" commandName="postForm">
        <div class="form-group">
            <label for="comment"><span style="color:#31b0d5"><spring:message
                    code="post.label.inputpost"/>:</span></label>

            <div class="my-textarea">
                <form:textarea path="postContent" class="form-control" rows="5" id="comment"/>
            </div>
        </div>

        <p><input type="submit" class="btn btn-primary" value="<spring:message code="post.button.send"/>"></p>
    </form:form>

    <form:form enctype="multipart/form-data" method="post" action="post/upload" commandName="photoForm">
        <span class="btn btn-primary btn-file">
           <form:input path="photo" type="file" name="photo" accept="image/*"/>
        </span>
        <br><br>
        <spring:message code="post.button.upload" var="UploadButton"/>
        <input type="submit" class="btn btn-primary" value="${UploadButton}">
    </form:form>

</div>
</body>
</html>

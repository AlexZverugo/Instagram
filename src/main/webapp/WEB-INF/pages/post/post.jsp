<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <toolkit:header title="Post adder page"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container" align="center">
    <c:url value="/post/addPost" var="addPostUrl"/>
    <form:form action="${addPostUrl}" method="post" commandName="postForm" id="postSubmit"
               enctype="multipart/form-data">
        <form:hidden path="owner"/>
        <div class="form-group">
            <label for="comment"><span style="color:#31b0d5"><spring:message
                    code="post.label.inputpost"/>:</span></label>

            <div class="post-textarea-border">
                <spring:message code="post.placeholder.sendpost" var="taPlaceholder"/>
                <form:textarea path="postContent" class="form-control" rows="5" id="comment"
                               placeholder="${taPlaceholder}" autofocus="autofocus"/>
            </div>
        </div>
        <span class="btn btn-primary btn-file">
           <form:input path="picture" type="file" name="photo" accept="image/*"/>
        </span>
        <br><br>

        <p><input type="submit" class="btn btn-primary" value="<spring:message code="post.button.send"/>"></p>
    </form:form>


</div>
</body>
</html>

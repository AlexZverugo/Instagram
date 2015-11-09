<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>

<html>
<head>
    <toolkit:header title="Instagram user page"/>
    <%--<style>--%>
    <%--span.glyphicon-remove {--%>
    <%--font-size: 6em;--%>
    <%--}--%>
    <%--</style>--%>
</head>
<body class="bg-common">
<toolkit:navbar/>

<h2 class="user-label-fixed">${username}</h2>

<form action="/users/addPost?id=${id}" method="post" class="form-horizontal" role="form">
    <input type="submit" class="user-btn-size btn btn-primary user-btn-fixed"
           value="<spring:message code="user.button.addpost"/>">
</form>

<div class="container" align="center">
    <br><br>
    <br><br>

    <div class="user-layer">
        <c:forEach var="post" items="${posts}">
            <br><br>

            <div class="user-post-br" align="center">
                <div class="col-sm-1 user-post-position">
                    <div class="thumbnail">
                        <img class="img-responsive user-photo" src="../../../resources/photo/default_avatar.png">
                    </div>
                </div>
                <div class="col-sm-5 user-post-position">
                    <div class="panel panel-default">
                        <div class="left panel-heading">
                            <div align="left">
                                <span class="text-muted"><spring:message code="user.label.username"/>:</span>
                                <strong>${usernames.get(post.sender)}</strong>
                                <a href="" style="float:right;">
                                    <span class="glyphicon glyphicon-remove" style="float:right;" aria-hidden="true"></span>
                                </a>
                            </div>

                        </div>
                        <div align="justify">
                            <pre class="user-post-scroll pre-post"><c:out value="${post.postContent}"/>
                            </pre>
                        </div>
                        <hr>
                        <c:if test="${post.imageByte != null}">
                            <img src="<pi:image imageByte="${post.imageByte}"/>" width="430" height="430">
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>

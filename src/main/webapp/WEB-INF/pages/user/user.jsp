<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>

<html>
<head>
    <toolkit:header title="Instagram user page"/>
</head>

<body class="bg-common">
<toolkit:navbar/>


<h2 class="user-label-fixed">${username}</h2>

<form action="/users/addPost?id=${id}" method="post" class="form-horizontal" role="form">
    <input type="submit" class="user-btn-size btn btn-primary user-btn-fixed"
           value="<spring:message code="user.button.addpost"/>">
</form>

<div class="container user-layer" align="center">
    <br><br>
    <br><br>

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

                        <c:if test="${removingCross}">
                            <a href="/post/deletePost/id=${post.id}"
                               id="post-del">
                                    <span class="glyphicon glyphicon-remove"
                                          aria-hidden="true"></span>
                            </a>
                        </c:if>

                    </div>
                </div>



                <div id="post${post.id}" onclick="setDisplayAttribute('block');
                        setPostPopUpData('<pi:image imageByte="${post.imageByte}"/>', '${post.postContent}');">
                    <div align="justify">
                        <c:set var="postContent" value="${fn:trim(post.postContent)}"/>
                        <c:set var="encodedBr" value="${fn:replace(postContent,'%0A','<br>')}"/>
                        <c:set var="encodedPost" value="${fn:replace(encodedBr,'%0D','')}"/>
                        <pre id="postPanel" class="user-post-scroll pre-post">
                            <p>${encodedPost}</p>
                        </pre>
                    </div>

                    <hr>

                    <c:if test="${post.imageByte != null}">
                        <img src="<pi:image imageByte="${post.imageByte}"/>" width="430" height="430">
                    </c:if>

                    <div class="panel-footer">
                        <button id="commentTextareaButton${post.id}" class="btn">
                        <span class="glyphicon glyphicon-arrow-down"
                              aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </div>
            <br><br>
        </div>
        </c:forEach>
    </div>


    <div onclick="setDisplayAttribute('none')" id="wrap"></div>

    <div id="window" style="align-content: center">
        <img class="close" onclick="setDisplayAttribute('none')"
             src="../../../resources/photo/close.png">

        <div class="post-popup">
            <pre class="pre-post" id="postContent"></pre>
        </div>

        <br>
        <img src="" id="postImg" class="post-popup-img">
        <br><br><br>

        <div  style="height: 50%; width: 50%;">
            <form:form action="/users/user" method="post">
                <textarea class="form-control" cols="40" rows="5"></textarea>
                <br>

                <p><input type="submit" class="btn btn-primary" value="<spring:message code="user.button.send"/>"></p>
            </form:form>
        </div>
    </div>

</body>
</html>

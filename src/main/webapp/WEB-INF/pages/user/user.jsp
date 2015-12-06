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
    <script type="text/javascript">
        var contextPath='${pageContext.request.contextPath}';
    </script>
    <script src="<c:url value="/resources/js/user.js"/>"></script>
</head>

<body class="bg-common">
<toolkit:navbar/>

<h2 class="user-label-fixed">${username}</h2>
<c:url value="/post?postOwnerId=${profile.user}" var="addPostUrl"/>
<a href="${addPostUrl}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-add-btn-position"
           value="<spring:message code="user.button.addpost"/>">
</a>

<c:url value="/profile/user=${profile.user}" var="showProfileUrl"/>
<a href="${showProfileUrl}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-profile-btn-position"
           value="<spring:message code="user.button.showprofile"/>">
</a>

<div id="searchResult" class="row"></div>

<div class="container user-layer" align="center">
    <br><br>
    <br><br>

    <div id="allPosts">
        <c:forEach var="post" items="${posts}">
            <div id="fullPostContent${post.id}" class="user-post-br" align="center">
                <div class="col-sm-1 user-post-position">
                    <div class="thumbnail">
                        <a href="/profile/user=${post.sender}">
                            <c:choose>
                                <c:when test="${post.senderAvatar != null}">

                                    <img class="img-responsive user-photo"
                                         src="<pi:image imageByte="${post.senderAvatar}"/>">
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/resources/photo/default_avatar.png" var="defaultAvatar"/>
                                    <img class="img-responsive user-photo"
                                         src="${defaultAvatar}">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                </div>
                <div class="col-sm-5 user-post-position">
                    <div class="panel panel-default">
                        <div class="left panel-heading">
                            <div align="left">
                                <span class="text-muted"><spring:message code="user.label.username"/>:</span>
                                <strong>${post.senderName}</strong>

                                <div id="remove${post.id}" class="post-del cursor-pointer">

                                    <c:if test="${authUser.userId eq post.sender or authUser.userId eq post.owner}">
                                        <span data-toggle="tooltip" title="<spring:message code="user.tooltip.delete"/>"
                                              class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </c:if>

                                </div>
                            </div>
                        </div>

                        <div id="post${post.id}" class="cursor-pointer" data-toggle="modal" data-target="#myModal"
                             id-parameter="${post.id}">

                            <div class="user-post-scroll">
                            <pre id="contentPost${post.id}" class="pre-post">
                                <p>${post.postContent}</p>
                            </pre>
                            </div>
                            <hr>
                            <div id="imgPost${post.id}">
                                <c:if test="${post.imageByte != null}">
                                    <img class="post-popup-img" src="<pi:image imageByte="${post.imageByte}"/>">
                                </c:if>
                            </div>

                        </div>
                        <br>

                        <div class="panel-footer">
                            <div class="row">
                                <div id="likeButton${post.id}" class="cursor-pointer col-sm-1">
                                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                                </div>

                                <span id="likeCount${post.id}" class="col-sm-1">${post.like}</span>

                                <div id="dislikeButton${post.id}" class="cursor-pointer col-sm-1">
                                    <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
                                </div>

                                <span id="dislikeCount${post.id}" class="col-sm-1">${post.dislike}</span>

                                <span class="col-sm-offset-4 col-sm-4">${post.dateDispatch}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="user-post-br" align="center">
            <spring:message code="user.post.popup.head" var="showMore"/>
            <input type="button" class="btn btn-primary" style="width: 39%" id="showMore" value="Show more"/>
        </div>
    </div>

    <div id="myModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="user.post.popup.head"/></h4>
                </div>
                <div class="modal-body">
                    <div id="popupContent">
                        <pre class="pre-post"></pre>
                    </div>
                    <hr>

                    <div id="popupImg">
                        <img class="post-popup-img">
                    </div>
                    <hr>
                    <div id="commentsOfPost" class="row"></div>

                </div>
                <div id="authUser${authUser.userId}" current-user="${profile.user}"></div>
                <div class="row">
                    <form id="commentInput">
                        <textarea id="commentContent" placeholder="<spring:message code="user.placeholder.sendcomment"/>"
                                  class="form-control comment-textarea col-sm-offset-1 col-sm-5" rows="4"></textarea>

                        <p>
                            <input type="submit" class="btn btn-primary col-sm-3 comment-btn"
                                   value="<spring:message code="user.button.send"/>">
                        </p>
                    </form>
                </div>
                <br>
            </div>
        </div>
    </div>

</div>
</body>
</html>

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
    <script src="<c:url value="/resources/js/user.js"/>"></script>
</head>

<body class="bg-common">
<toolkit:navbar/>

<h2 class="user-label-fixed">${username}</h2>

    <a href="/users/addPost?id=${profile.user}">
        <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-add-btn-position"
           value="<spring:message code="user.button.addpost"/>">
    </a>

    <a href="/profile/user=${profile.user}">
        <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-profile-btn-position"
           value="<spring:message code="user.button.showprofile"/>">
    </a>

<div class="container user-layer" align="center">
    <br><br>
    <br><br>

    <c:forEach var="post" items="${posts}">
        <br><br>

        <div id="fullPostContent${post.id}" class="user-post-br" align="center">
            <div class="col-sm-1 user-post-position">
                <div class="thumbnail">
                    <c:choose>
                        <c:when test="${profilesImages.get(post.id) != null}">
                            <a href="/profile/user=${post.sender}">
                                <img class="img-responsive user-photo"
                                     src="<pi:image imageByte="${profilesImages.get(post.id)}"/>">
                            </a>
                        </c:when>
                        <c:otherwise>
                            <img class="img-responsive user-photo" src="../../../resources/photo/default_avatar.png">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-sm-5 user-post-position">
                <div class="panel panel-default">
                    <div class="left panel-heading">
                        <div align="left">
                            <span class="text-muted"><spring:message code="user.label.username"/>:</span>
                            <strong>${usernames.get(post.sender)}</strong>

                            <c:if test="${removingCross}">
                                <div id="remove${post.id}" class="post-del cursor-pointer">
                                    <span class="glyphicon glyphicon-remove"
                                          aria-hidden="true"></span>
                                </div>
                            </c:if>

                        </div>
                    </div>

                    <div id="post${post.id}" class="cursor-pointer" data-toggle="modal" data-target="#myModal" id-parameter="${post.id}">

                        <div align="justify" class="user-post-scroll">
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
                        <div class="panel-body">
                            <span class="time-pos">${post.dateDispatch}</span>
                        </div>
                    </div>
                </div>
                <br><br>
            </div>
        </div>
    </c:forEach>

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
                    <br>

                    <div id="popupImg">
                        <img class="post-popup-img">
                    </div>
                    <hr>
                    <div id="commentsOfPost" class="row"></div>


                </div>
                <div class="row">
                    <form id="commentInput">
                        <textarea id="commentContent"
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>User page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/user.css"/>" type="text/css"/>
</head>
<body>
<div class="container" align="center">
    <%@ include file="../common/toolkit/navbar.jsp" %>
    <h2 style="color:blue">${username}</h2>

    <form action="/users/addPost?id=${id}" method="post" class="form-horizontal" role="form">
        <input type="submit" class="btn-size btn btn-primary" value="<spring:message code="user.button.addpost"/>">
    </form>

    <c:forEach var="post" items="${posts}">
        <div class="post-br" align="center">
            <div class="col-sm-1 post-position">
                <div class="thumbnail">
                    <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                </div>
            </div>
            <div class="col-sm-5 post-position">
                <div class="panel panel-default">
                    <div class="left panel-heading">
                        <span class="text-muted"><spring:message code="user.label.username"/>:</span> <strong>${post.sender}</strong>
                    </div>
                    <div class="panel-body">
                        <c:out value="${post.postContent}"/><br>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

</body>
</html>

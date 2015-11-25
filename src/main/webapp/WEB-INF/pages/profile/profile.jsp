<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>

<html>
<head>
    <toolkit:header title="User profile page"/>
    <script src="<c:url value="/resources/js/profile.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-datepicker.min.js"/>"></script>

</head>
<body class="bg-common">
<toolkit:langnav/>
<c:if test="${isEditable}">
        <input type="button" class="user-btn-size btn btn-primary user-btn-fixed profile-edit-btn-position"
               data-toggle="modal" data-target="#profileModal"
               value="<spring:message code="profile.label.edit"/>">
</c:if>

<a href="/users/user/${profile.user}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed profile-user-return-btn-position"
           value="<spring:message code="profile.button.userpage"/>">
</a>

<div class="container profile-layer" align="center">
    <c:choose>
        <c:when test="${profile.avatar != null}">
            <img class="profile-img" src="<pi:image imageByte="${profile.avatar}"/>"
                 data-toggle="modal" data-target="#profileAvatarModal">
            <hr>
        </c:when>
        <c:otherwise>
            <img class="profile-img" src="../../../resources/photo/default_avatar.png">
            <hr>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <c:choose>
            <c:when test="${profile.sex eq 'male'}">
                <h3 class="col-sm-3 profile-header-text" align="right">
                    <b><spring:message code="profile.label.sex"/>:</b>
                </h3>

                <h3 class="col-sm-6 profile-text" align="left">
                    <spring:message code="profile.popup.select.option.male"/>
                </h3>
            </c:when>
            <c:when test="${profile.sex eq 'female'}">
                <h3 class="col-sm-3 profile-header-text" align="right">
                    <b><spring:message code="profile.label.sex"/>:</b>
                </h3>

                <h3 class="col-sm-6 profile-text" align="left">
                    <spring:message code="profile.popup.select.option.female"/>
                </h3>
            </c:when>
        </c:choose>
    </div>
    <div class="row">
        <c:if test="${profile.firstName != null}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.firstname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.firstName}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${profile.surname != null}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.surname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.surname}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${profile.secondName != null}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.secondname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.secondName}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${profile.country != null}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.country"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.country}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${profile.city != null}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.city"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.city}</h3>
        </c:if>
    </div>
    <hr>

    <div id="profileModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="profile.popup.head"/></h4>
                </div>
                <div class="modal-body">
                    <form:form action="/profile/updateProfile" method="post" commandName="profile"
                               id="profileSubmit" enctype="multipart/form-data">
                        <form:hidden path="id"/>
                        <form:hidden path="user"/>
                        <form:hidden path="avatar"/>
                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.avatar"/>:
                                </span>
                                <span class="btn btn-primary btn-file col-sm-4">
                                    <form:input path="picture" value="${profile.avatar}" type="file" name="photo"
                                                accept="image/*"/>
                                </span>
                        </div>
                        <br><br>

                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.firstname"/>:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.firstName}" path="firstName"/>
                        </div>
                        <br>

                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.surname"/>:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.surname}" path="surname"/><br>
                        </div>
                        <br>

                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.secondname"/>:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.secondName}"
                                        path="secondName"/><br>
                        </div>
                        <br>

                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.country"/>:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.country}" path="country"/><br>
                        </div>
                        <br>

                        <div class="row">
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.city"/>:
                                </span>
                            <form:input class="col-sm-4" type="text" path="city" value="${profile.city}"/><br>
                        </div>
                        <br>

                        <div class="row">
                            <spring:message code="profile.popup.select.option.male" var="male"/>
                            <spring:message code="profile.popup.select.option.female" var="female"/>
                            <span class="col-sm-offset-2 col-sm-2" align="right">
                                    <spring:message code="profile.label.sex"/>:
                                </span>
                            <form:select path="sex" class="col-sm-2">
                                <form:option value="male" label="${male}"/>
                                <form:option value="female" label="${female}"/>
                            </form:select>
                        </div>
                        <br>

                        <p>
                            <input type="submit" class="btn btn-primary"
                                   value="<spring:message code="profile.button.send"/>">
                        </p>
                    </form:form>
                </div>
                <div class="row">

                </div>
                <br>
            </div>
        </div>
    </div>

    <%--avatar popup--%>
    <div id="profileAvatarModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="profile.popup.avatar.head"/></h4>
                </div>
                <div class="modal-body">
                    <img class="profile-popup-avatar" src="<pi:image imageByte="${profile.avatar}"/>">
                </div>
                <br>
            </div>
        </div>
    </div>

</div>
</body>
</html>

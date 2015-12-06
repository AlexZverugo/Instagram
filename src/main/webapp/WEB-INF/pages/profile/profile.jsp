<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>

<html>
<head>
    <spring:message code="pages.title.profile" var="title"/>
    <toolkit:header title="${title}"/>
    <link href="<c:url value="/resources/bootstrap/datepicher/css/bootstrap-datepicker.min.css"/>"
          type="text/css" rel="stylesheet">
    <script src="<c:url value="/resources/bootstrap/datepicher/js/bootstrap-datepicker.min.js"/>"></script>
    <script src="<c:url value="/resources/js/profile.js"/>"></script>
    <spring:message code="profile.datepicker.locale" var="datepickerLocale"/>
    <script src="<c:url value="/resources/bootstrap/datepicher/locales/bootstrap-datepicker.${datepickerLocale}.min.js"/>"></script>
</head>
<body class="bg-common">
<toolkit:langnav/>
<c:if test="${isEditable}">
    <input id="editButton" type="button" class="user-btn-size btn btn-primary user-btn-fixed profile-edit-btn-position"
           data-toggle="modal" data-target="#profileModal"
           value="<spring:message code="profile.label.edit"/>">
</c:if>
<c:url value="/users/user/${profile.user}" var="userPageUrl"/>
<a href="${userPageUrl}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed profile-user-return-btn-position"
           value="<spring:message code="profile.button.userpage"/>">
</a>

<div class="container profile-layer" align="center">
    <c:choose>
        <c:when test="${not empty profile.avatar}">
            <img class="profile-img" src="<pi:image imageByte="${profile.avatar}"/>"
                 data-toggle="modal" data-target="#profileAvatarModal">
            <hr>
        </c:when>
        <c:otherwise>
            <c:url value="/resources/photo/default_avatar.png" var="defaultAvatar"/>
            <img class="profile-img" src="${defaultAvatar}">
            <hr>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <c:if test="${not empty profile.sex}">
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
        </c:if>
    </div>
    <div class="row">
        <c:if test="${not empty profile.firstName}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.firstname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.firstName}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.surname}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.surname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.surname}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.secondName}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.secondname"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.secondName}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.birthday}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.birthday"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.birthday}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.country}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.country"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.country}</h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.city}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b><spring:message code="profile.label.city"/>:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">${profile.city}</h3>
        </c:if>
    </div>
    <hr>
    <c:if test="${not isValid}">
        <script>
            $('#editButton').click();
        </script>
    </c:if>

    <div id="profileModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="profile.popup.head"/></h4>
                </div>
                <div class="modal-body">
                    <c:url value="/profile/updateProfile" var="updateProfileUrl"/>
                    <form:form action="${updateProfileUrl}" method="post" commandName="profile"
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
                            <form:errors path="firstName" cssClass="error" cssStyle="color:red"/>
                            <spring:message code="profile.label.firstname" var="firstnamePlaceholder"/>
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    ${firstnamePlaceholder}:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.firstName}"
                                        placeholder="${firstnamePlaceholder}" path="firstName"/>
                        </div>
                        <br>

                        <div class="row">
                            <form:errors path="surname" cssClass="error" cssStyle="color:red"/>
                            <spring:message code="profile.label.surname" var="surnamePlaceholder"/>
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                   ${surnamePlaceholder}:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.surname}"
                                        placeholder="${surnamePlaceholder}" path="surname"/><br>
                        </div>
                        <br>

                        <div class="row">
                            <form:errors path="secondName" cssClass="error" cssStyle="color:red"/>
                            <spring:message code="profile.label.secondname" var="secondnamePlaceholder"/>
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    ${secondnamePlaceholder}:
                                </span>
                            <form:input class="col-sm-4" type="text" value="${profile.secondName}"
                                        placeholder="${secondnamePlaceholder}" path="secondName"/><br>
                        </div>
                        <br>

                        <div class="row">
                            <spring:message code="profile.label.country" var="countryPlaceholder"/>
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                   ${countryPlaceholder}:
                                </span>
                            <form:input class="col-sm-4" type="text"
                                        placeholder="${countryPlaceholder}" value="${profile.country}"
                                        path="country"/><br>
                        </div>
                        <br>

                        <div class="row">
                            <spring:message code="profile.label.city" var="cityPlaceholder"/>
                                <span class="col-sm-offset-2 col-sm-2" align="right">
                                    ${cityPlaceholder}:
                                </span>
                            <form:input class="col-sm-4" type="text" path="city"
                                        placeholder="${cityPlaceholder}" value="${profile.city}"/><br>
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

                        <spring:message code="profile.placeholder.dateformat" var="dateFormat"/>
                        <div class="row">
                                    <span class="col-sm-offset-2 col-sm-2" align="right">
                                        <spring:message code="profile.label.birthday"/>:
                                    </span>
                            <div class='col-sm-3'>
                                <div class="form-group">
                                    <div class='input-group date' id='birthdayDatepicker'>
                                        <form:input path="birthday" id="datepickerBirthday" class="form-control"
                                                    style="cursor: default" locale="${datepickerLocale}"
                                                    placeholder="${dateFormat}" value="${profile.birthday}"/>
                                                    <span class="input-group-addon cursor-pointer">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                    </div>
                                </div>
                            </div>
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

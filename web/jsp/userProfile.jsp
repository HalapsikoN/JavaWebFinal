<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 31.01.2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="outputTag" uri="/WEB-INF/dateTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="bonusList" class="java.util.ArrayList" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">
    <br>
    <h2>${sessionScope.username} <fmt:message key="locale.userProfile.title" bundle="${bundle}"/>profile</h2>
    <br>
    <p><fmt:message key="locale.userProfile.name" bundle="${bundle}"/>: <strong>${sessionScope.username}</strong></p>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCenter">
        <fmt:message key="locale.userProfile.changeUserNameBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle"><fmt:message key="locale.userProfile.changeUserNameTitle" bundle="${bundle}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="text-align: left">
                    <form id="update_username" action="atrack" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="update_username">
                            <label for="new_username"><fmt:message key="locale.userProfile.changeUserNameName" bundle="${bundle}"/>:</label>

                            <input type="text" name="new_username" id="new_username" class="form-control"
                                   placeholder="<fmt:message key="locale.userProfile.changeUserNameNamePlaceholder" bundle="${bundle}"/>">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="changeUsernameForm('update_username')">
                        <fmt:message key="locale.button.submit" bundle="${bundle}"/>
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <p><fmt:message key="locale.userProfile.login" bundle="${bundle}"/>: <strong>${sessionScope.login}</strong></p>
    <p><fmt:message key="locale.userProfile.role" bundle="${bundle}"/>: <strong>${sessionScope.role}</strong></p>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCenter2">
        <fmt:message key="locale.userProfile.changePasswordBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle2"><fmt:message key="locale.userProfile.changePasswordTitle" bundle="${bundle}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="update_password" action="atrack" method="post">
                        <div class="form-group" style="text-align: left">
                            <input type="hidden" name="command" value="update_password">
                            <label for="new_password"><fmt:message key="locale.userProfile.changePasswordNewPassword" bundle="${bundle}"/>:</label>

                            <input type="password" name="new_password" id="new_password" class="form-control"
                                   placeholder="<fmt:message key="locale.userProfile.changePasswordNewPasswordPlaceholder" bundle="${bundle}"/>">
                            <br>
                            <label for="repeat_new_password"><fmt:message key="locale.userProfile.changePasswordNewPasswordRepeat" bundle="${bundle}"/>:</label>

                            <input type="password" name="repeat_new_password" id="repeat_new_password"
                                   class="form-control" placeholder="<fmt:message key="locale.userProfile.changePasswordNewPasswordRepeatPlaceholder" bundle="${bundle}"/>">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="changePasswordForm('update_password')">
                        <fmt:message key="locale.button.submit" bundle="${bundle}"/>
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <p class="badge badge-info">${param.get("message")}</p>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/profile.js"></script>
</body>
</html>

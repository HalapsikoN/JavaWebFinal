<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="user" class="by.epam.finalTask.entity.User" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">

<div id="center_div">
    <br>
    <h2>${user.name} <fmt:message key="locale.editUser.title" bundle="${bundle}"/>editor</h2>
    <br>
    <p><fmt:message key="locale.editUser.name" bundle="${bundle}"/>: <strong>${user.name}</strong></p>
    <p><fmt:message key="locale.editUser.login" bundle="${bundle}"/>: <strong>${user.login}</strong></p>
    <p><fmt:message key="locale.editUser.role" bundle="${bundle}"/>: <strong>${user.role}</strong></p>
    <br>
    <form action="atrack" method="post" class="form-inline" style="justify-content: center">
        <div class="form-group mb-2">
            <input type="hidden" name="command" value="change_role">
            <input type="hidden" name="user_id" value="${user.id}">
            <label for="controlSelect"><fmt:message key="locale.editUser.changeRole" bundle="${bundle}"/>:</label>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <select name="role" class="form-control" id="controlSelect">
                <option value="USER"><fmt:message key="locale.editUser.user" bundle="${bundle}"/></option>
                <option value="ADMIN"><fmt:message key="locale.editUser.admin" bundle="${bundle}"/></option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2"><fmt:message key="locale.button.change" bundle="${bundle}"/></button>
    </form>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter1">
        <fmt:message key="locale.editUser.addBonusBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter1" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle1"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle1"><fmt:message key="locale.editUser.addBonusTitle" bundle="${bundle}"/> (${user.name})?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="text-align: left">
                    <form id="add_bonus" action="atrack" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="add_bonus">
                            <input type="hidden" name="user_id" value="${user.id}">
                            <div class="form-group row">
                                <label for="name" class="col-sm-3 col-form-label"><fmt:message key="locale.editUser.nameBonus" bundle="${bundle}"/>: </label>
                                <div class="col-sm">
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="<fmt:message key="locale.editUser.nameBonusPlaceholder" bundle="${bundle}"/>" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="discount" class="col-sm-4 col-form-label"><fmt:message key="locale.editUser.discount" bundle="${bundle}"/>: </label>

                                <input type="number" class="col-sm-6 form-control" id="discount" name="discount"
                                       placeholder="<fmt:message key="locale.editUser.discountPlaceholder" bundle="${bundle}"/>"
                                       required min="1" max="100">
                                <div class="col-sm-1 input-group-append">
                                    <span class="input-group-text">%</span>
                                </div>

                            </div>
                            <div class="form-group row">
                                <label for="start_date" class="col-sm-3 col-form-label"><fmt:message key="locale.editUser.startDate" bundle="${bundle}"/>: </label>
                                <div class="col-sm">
                                    <input type="date" class="form-control" id="start_date" name="start_date"
                                           placeholder="<fmt:message key="locale.editUser.startDatePlaceholder" bundle="${bundle}"/>" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="start_date" class="col-sm-3 col-form-label"><fmt:message key="locale.editUser.endDate" bundle="${bundle}"/>: </label>
                                <div class="col-sm">
                                    <input type="date" class="form-control" id="end_date" name="end_date"
                                           placeholder="<fmt:message key="locale.editUser.endDatePlaceholder" bundle="${bundle}"/>" required>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-danger" onclick="bonusAddForm('add_bonus')"><fmt:message key="locale.button.add" bundle="${bundle}"/></button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCenter2">
        <fmt:message key="locale.editUser.deleteUserBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle2"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle2"><fmt:message key="locale.editUser.deleteUserTitle1" bundle="${bundle}"/>
                        (<strong>${user.name}</strong>) <fmt:message key="locale.editUser.deleteUserTitle2" bundle="${bundle}"/>?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <form action="atrack" method="post">
                        <input type="hidden" name="command" value="delete_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="submit" class="btn btn-danger"><fmt:message key="locale.button.yes" bundle="${bundle}"/></button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.no" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/bonus.js"></script>
</body>
</html>

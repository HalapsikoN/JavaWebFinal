<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="user" class="by.epam.finalTask.entity.User" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">

<div id="center_div">
    <br>
    <h2>${user.name} editor</h2>
    <br>
    <p>Name: <strong>${user.name}</strong></p>
    <p>Login: <strong>${user.login}</strong></p>
    <p>Role: <strong>${user.role}</strong></p>
    <br>
    <form action="atrack" method="post" class="form-inline" style="justify-content: center">
        <div class="form-group mb-2">
            <input type="hidden" name="command" value="change_role">
            <input type="hidden" name="user_id" value="${user.id}">
            <label for="controlSelect">Change role:</label>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <select name="role" class="form-control" id="controlSelect">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Change</button>
    </form>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter1">
        Add bonus
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter1" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle1"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle1">Which discount you want to give (${user.name})?</h5>
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
                                <label for="name" class="col-sm-3 col-form-label">Name: </label>
                                <div class="col-sm">
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="Enter bonus name" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="discount" class="col-sm-4 col-form-label">Discount: </label>

                                <input type="number" class="col-sm-6 form-control" id="discount" name="discount"
                                       placeholder="Enter discount"
                                       required min="1" max="100">
                                <div class="col-sm-1 input-group-append">
                                    <span class="input-group-text">%</span>
                                </div>

                            </div>
                            <div class="form-group row">
                                <label for="start_date" class="col-sm-3 col-form-label">Start date: </label>
                                <div class="col-sm">
                                    <input type="date" class="form-control" id="start_date" name="start_date"
                                           placeholder="Enter start date" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="start_date" class="col-sm-3 col-form-label">End date: </label>
                                <div class="col-sm">
                                    <input type="date" class="form-control" id="end_date" name="end_date"
                                           placeholder="Enter end date" required>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-danger" onclick="bonusAddForm('add_bonus')">Add</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCenter2">
        Delete user
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle2"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle2">Are you sure that you want to delete user
                        (<strong>${user.name}</strong>) from service?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <form action="atrack" method="post">
                        <input type="hidden" name="command" value="delete_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="submit" class="btn btn-danger">Yes</button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <p class="badge badge-info" style="font-size: 20px">${message}</p>
    <br>
</div>

<script src="${pageContext.request.contextPath}/jsp/js/bonus.js"></script>
</body>
</html>

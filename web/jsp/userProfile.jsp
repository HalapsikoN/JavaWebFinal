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
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="bonusList" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">
    <br>
    <h2>${sessionScope.username} profile</h2>
    <br>
    <p>Name: <strong>${sessionScope.username}</strong></p>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCenter">
        Change username
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Username change</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="text-align: left">
                    <form id="update_username" action="atrack" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="update_username">
                            <label for="new_username">New username:</label>

                            <input type="text" name="new_username" id="new_username" class="form-control"
                                   placeholder="Enter new username">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="changeUsernameForm('update_username')">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <p>Login: <strong>${sessionScope.login}</strong></p>
    <p>Role: <strong>${sessionScope.role}</strong></p>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCenter2">
        Change password
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle2">Password changing</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="update_password" action="atrack" method="post">
                        <div class="form-group" style="text-align: left">
                            <input type="hidden" name="command" value="update_password">
                            <label for="new_password">New password:</label>

                            <input type="password" name="new_password" id="new_password" class="form-control"
                                   placeholder="Enter new password">
                            <br>
                            <label for="repeat_new_password">Repeat new password:</label>

                            <input type="password" name="repeat_new_password" id="repeat_new_password"
                                   class="form-control" placeholder="Repeat new password">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="changePasswordForm('update_password')">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <p class="badge badge-info">${message}</p>
</div>

<script src="${pageContext.request.contextPath}/jsp/js/profile.js"></script>
</body>
</html>

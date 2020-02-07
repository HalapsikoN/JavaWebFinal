<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 06.02.2020
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<br>
<table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
    <thead class="thead-dark">
    <tr>
        <th>Username</th>
        <th>Login</th>
        <th>Role</th>
        <th>Wallet</th>
        <th>Unban</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">

        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.role}</td>
            <td>${user.wallet}</td>
            <td>
                <form id="form${user.id}" method="post" action="atrack">
                    <input type="hidden" name="command" value="unban_user">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/jsp/icons/unban.png"
                             onclick="submitById('form${user.id}')">
                    </a>
                </form>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>


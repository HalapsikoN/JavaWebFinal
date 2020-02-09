<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<br>
<div id="center_div">
    <p class="badge badge-info" style="font-size: 20px">${param.get("message")}</p>
</div>
<table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
    <thead class="thead-dark">
    <tr>
        <th><fmt:message key="locale.general.tableUsername" bundle="${bundle}"/></th>
        <th><fmt:message key="locale.general.tableLogin" bundle="${bundle}"/></th>
        <th><fmt:message key="locale.general.tableRole" bundle="${bundle}"/></th>
        <th><fmt:message key="locale.general.tableWallet" bundle="${bundle}"/></th>
        <th><fmt:message key="locale.general.tableEditUser" bundle="${bundle}"/></th>
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
                        <input type="hidden" name="command" value="edit_user_page">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <a href="#">
                            <img src="${pageContext.request.contextPath}/jsp/icons/editUser.png"
                                 onclick="submitById('form${user.id}')">
                        </a>
                    </form>
                </td>
            </tr>

    </c:forEach>
    </tbody>
</table>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

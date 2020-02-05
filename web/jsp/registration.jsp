<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 28.01.2020
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/reg_sig.css" rel="stylesheet">
<form method="post" id="form" action="atrack">
    <div class="form-group" id="main">
        <input type="hidden" name="command" value="registration">
        <section id="section" class="form-fields">
            <label for="username">Name:</label>
            <input type="text" name="username"  class="form-control" id="username" placeholder="Enter your name" required minlength="2"/>

            <label for="login">Login:</label>
            <input type="text" name="login" id="login" class="form-control" placeholder="Enter login" required minlength="4"/>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Enter password" required minlength="6"/>

            <label for="re_pass">Repeat password:</label>
            <input type="password" name="re_pass" id="re_pass" class="form-control" placeholder="Repeat password" required minlength="6"/>

        </section>

        <input type="button" class="btn btn-primary" value="Register" onclick="regForm(this)">
        <a type="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/atrack?command=main_page">Cancel</a>
    </div>
</form>

<br>
<p class="badge badge-info" style="font-size: 20px; margin-left: 10%">${message}</p>

<script src="${pageContext.request.contextPath}/jsp/js/registration.js"></script>
</body>
</html>

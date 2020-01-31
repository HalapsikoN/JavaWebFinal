<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 29.01.2020
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/reg_sig.css" rel="stylesheet">
<form method="post" id="form" action="atrack">
    <div class="form-group" id="main">
        <input type="hidden" name="command" value="sign_in">
        <section id="section" class="form-fields">
            <label for="login">Login:</label>
            <input type="text" name="login" class="form-control" id="login" placeholder="Enter login" required minlength="4"/>
            <br>
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" required minlength="6"/>
            <br>
        </section>

        <input type="button" class="btn btn-primary" value="Sign in" onclick="signInForm(this)">
        <a type="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/atrack?command=main_page">Cancel</a>
    </div>
</form>

<script src="${pageContext.request.contextPath}/jsp/js/signIn.js"></script>
</body>
</html>

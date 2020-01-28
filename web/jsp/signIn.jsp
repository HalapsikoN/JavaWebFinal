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

<c:set var="path" value="../" scope="request"/>

<html>
<head>
    <title>Sign in</title>
    <c:import url="${path}WEB-INF/head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<form method="post" id="form" action="atrack">
    <input type="hidden" name="command" value="sign_in">
    <section id="section" class="form-fields">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" placeholder="Enter login" required minlength="4"/>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Enter password" required minlength="6"/>
    </section>

    <input type="button" value="Register" onclick="signInForm(this)">
    <input type="button" value="Cancel" onclick="window.location='${path}WEB-INF/main.jsp'"/>
</form>

<script src="${pageContext.request.contextPath}/jsp/js/signIn.js"></script>
</body>
</html>

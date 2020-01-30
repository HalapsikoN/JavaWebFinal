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

<html>
<head>
    <title>Registration</title>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<form method="post" id="form" action="atrack">
    <input type="hidden" name="command" value="registration">
    <section id="section" class="form-fields">
        <label for="username">Name:</label>
        <input type="text" name="username" id="username" placeholder="Enter your name" required minlength="2" />
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" placeholder="Enter login" required minlength="4"/>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Enter password" required minlength="6"/>
        <label for="re_pass">Repeat password:</label>
        <input type="password" name="re_pass" id="re_pass" placeholder="Repeat password" required minlength="6"/>
    </section>

    <input type="button" value="Register" onclick="regForm(this)">
    <input type="button" value="Cancel" onclick="window.location='${pageContext.request.contextPath}/jsp/main.jsp'"/>
</form>

    <p>
        <c:if test="${requestScope.get('message')!=null}">
            <c:out value="${requestScope.get('message')}"/>
        </c:if>
    </p>

    <script src="${pageContext.request.contextPath}/jsp/js/registration.js"></script>
</body>
</html>

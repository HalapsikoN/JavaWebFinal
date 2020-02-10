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
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

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
            <label for="username"><fmt:message key="locale.formUser.name" bundle="${bundle}"/>:</label>
            <input type="text" name="username" class="form-control" id="username"
                   placeholder="<fmt:message key="locale.formUser.namePlaceholder" bundle="${bundle}"/>" required
                   minlength="2"/>

            <label for="login"><fmt:message key="locale.formUser.login" bundle="${bundle}"/>:</label>
            <input type="text" name="login" id="login" class="form-control"
                   placeholder="<fmt:message key="locale.formUser.loginPlaceholder" bundle="${bundle}"/>" required
                   minlength="4"/>

            <label for="password"><fmt:message key="locale.formUser.password" bundle="${bundle}"/>:</label>
            <input type="password" name="password" id="password" class="form-control"
                   placeholder="<fmt:message key="locale.formUser.passwordPlaceholder" bundle="${bundle}"/>" required
                   minlength="6"/>

            <label for="re_pass"><fmt:message key="locale.formUser.repeatPassword" bundle="${bundle}"/>:</label>
            <input type="password" name="re_pass" id="re_pass" class="form-control"
                   placeholder="<fmt:message key="locale.formUser.repeatPasswordPlaceholder" bundle="${bundle}"/>"
                   required minlength="6"/>

        </section>

        <input type="button" class="btn btn-primary"
               value="<fmt:message key="locale.formUser.registerBtn" bundle="${bundle}"/>" onclick="regForm(this)">
        <a type="button" class="btn btn-secondary"
           href="${pageContext.request.contextPath}/atrack?command=main_page"><fmt:message key="locale.button.cancel"
                                                                                           bundle="${bundle}"/></a>
    </div>
</form>

<br>
<p class="badge badge-info" style="font-size: 20px; margin-left: 10%">${param.get("message")}</p>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/registration.js"></script>
</body>
</html>

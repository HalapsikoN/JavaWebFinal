<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 07.02.2020
  Time: 2:38
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
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">

    <br>
    <div class="alert alert-danger" role="alert">
        <h1><fmt:message key="locale.errorPage.title" bundle="${bundle}"/>:</h1>
        <br>
        <c:if test="${empty message}">
            <p><fmt:message key="locale.errorPage.standartReason" bundle="${bundle}"/>.</p>
        </c:if>
        <c:if test="${not empty message}">
            <p><fmt:message key="locale.errorPage.reason" bundle="${bundle}"/>: ${message}.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/atrack"><fmt:message key="locale.general.hrefMainPage" bundle="${bundle}"/>.</a>
    </div>
</div>
<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
</body>
</html>

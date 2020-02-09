<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 06.02.2020
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
<jsp:useBean id="credit" class="by.epam.finalTask.entity.Credit" scope="request"/>
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
        <p><fmt:message key="locale.ban.title" bundle="${bundle}"/>:</p>
        <p><fmt:message key="locale.ban.amountToPay" bundle="${bundle}"/>: <strong>${credit.amount}</strong></p>
        <p><fmt:message key="locale.ban.endDate" bundle="${bundle}"/>: <strong><outputTag:date format="dd_mm_yyyy" item="${credit.date}"/></strong></p>
        <br>
        <p><fmt:message key="locale.ban.advice" bundle="${bundle}"/>.</p>
        <a href="${pageContext.request.contextPath}/atrack"><fmt:message key="locale.general.hrefMainPage" bundle="${bundle}"/>.</a>
    </div>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
</body>
</html>

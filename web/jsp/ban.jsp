<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 06.02.2020
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
<jsp:useBean id="credit" class="by.epam.finalTask.entity.Credit" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">

    <br>
    <div class="alert alert-danger" role="alert">
        <p>You have been baned from service "ATrack" because of credit:</p>
        <p>Amount to pay: <strong>${credit.amount}</strong></p>
        <p>End date: <strong><outputTag:date format="dd_mm_yyyy" item="${credit.date}"/></strong></p>
        <br>
        <p>To return your account contact administrator of a site.</p>
        <a href="${pageContext.request.contextPath}/atrack">Main page.</a>
    </div>
</div>
</body>
</html>

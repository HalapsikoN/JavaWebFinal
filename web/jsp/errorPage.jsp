<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 07.02.2020
  Time: 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">

    <br>
    <div class="alert alert-danger" role="alert">
        <h1>OOOPS, there is some problems:</h1>
        <br>
        <c:if test="${empty message}">
            <p>Reason: No such page.</p>
        </c:if>
        <c:if test="${not empty message}">
            <p>Reason: ${message}.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/atrack">Main page.</a>
    </div>
</div>
</body>
</html>

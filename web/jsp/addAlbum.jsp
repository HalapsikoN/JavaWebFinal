<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 03.02.2020
  Time: 12:29
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
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/input_form.css" rel="stylesheet">

<div id="center_div">
    <br>
    <h2><fmt:message key="locale.addAlbum.title" bundle="${bundle}"/></h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="add_album">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.name" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="locale.formAlbum.namePlaceholder" bundle="${bundle}"/>" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="artist" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.artist" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="artist" name="artist" placeholder="<fmt:message key="locale.formAlbum.artistPlaceholder" bundle="${bundle}"/>" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.date" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="number" class="date-own form-control" id="date" name="date" placeholder="<fmt:message key="locale.formAlbum.datePlaceholder" bundle="${bundle}"/>" required min="1900" max="2099" step="1">
            </div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="locale.button.add" bundle="${bundle}"/></button>

    </form>

    <br>
    <p class="badge badge-info" style="font-size: 20px">${param.get("message")}</p>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
</body>
</html>

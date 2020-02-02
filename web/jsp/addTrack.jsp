<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
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
    <h2>Add new track</h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="add_track">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter track name" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="artist" class="col-sm-2 col-form-label">Singer: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="artist" name="artist" placeholder="Enter singer name" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label">Year: </label>
            <div class="col-sm-10">
                <input type="number" class="date-own form-control" id="date" name="date" placeholder="Select the year of the track" required min="1900" max="2099" step="1">
            </div>
        </div>
        <div class="form-group row">
            <label for="price" class="col-sm-2 col-form-label">Price: </label>
            <div class="col-sm-10">
                <input type="number" class="form-control" id="price" name="price" placeholder="Select the price of the track" required min="0" step="0.01">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Add</button>

    </form>

    <br>
    <p class="badge badge-info" style="font-size: 20px">${message}</p>
</div>

</body>
</html>

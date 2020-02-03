<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 03.02.2020
  Time: 12:29
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
    <h2>Add new playlist</h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="add_playlist">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name(topic): </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter playlist name(topic)" required>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Add</button>

    </form>

    <br>
    <p class="badge badge-info" style="font-size: 20px">${message}</p>
</div>

</body>
</html>

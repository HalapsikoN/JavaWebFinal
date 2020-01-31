<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 31.01.2020
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="playlist" class="by.epam.finalTask.entity.Playlist" scope="request"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<br>
<div id="center_div">
    <h2>Playlist information</h2>
    <br>
    <p>Name: <strong>${playlist.name}</strong></p>
    <p>Create date: <strong>${playlist.date.get(5)}.${playlist.date.get(2)}.${playlist.date.get(1)}</strong></p>
    <p>Album price: <strong>${playlistPrice}</strong></p>
</div>
<table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
    <thead class="thead-dark">
    <tr>
        <th>Song</th>
        <th>Singer</th>
        <th>Date</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="song" items="${playlist.trackList}">
        <tr>
            <td>${song.name}</td>
            <td>${song.artist}</td>
            <td>${song.date.get(1)}</td>
            <td>${song.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

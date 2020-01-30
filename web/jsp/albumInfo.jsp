<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 30.01.2020
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/album_and_playlist_info.css" rel="stylesheet">
<br>
<div id="album_and_playlist_div">
    <h2>Album information</h2>
    <br>
    <p>Name: <strong>${album.name}</strong></p>
    <p>Singer: <strong>${album.artist}</strong></p>
    <p>Release year: <strong>${album.date.get(1)}</strong></p>
    <p>Album price: <strong>${albumPrice}</strong></p>
</div>
<table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
    <thead class="thead-dark">
    <tr>
        <th>Song</th>
        <th>Date</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="song" items="${album.trackList}">
        <tr>
            <td>${song.name}</td>
            <td>${song.date.get(1)}</td>
            <td>${song.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

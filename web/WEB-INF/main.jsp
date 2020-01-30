<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 27.01.2020
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ATrack</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>

</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="<c:url value="/jsp/css/table.css"/> " rel="stylesheet">
<div>
    <br>
    <br>
    <br>
    <c:if test="${requestScope.get('songList')!=null}">
        <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
            <thead class="thead-dark">
            <tr>
                <th>Song</th>
                <th>Signer</th>
                <th>Date</th>
                <th>Price</th>
                <c:if test="${sessionScope.role eq 'USER'}">
                    <th>Buy</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="playlists" items="${songList}">
                <tr>
                    <td >${playlists.name}</td>
                    <td>${playlists.artist}</td>
                    <td>${playlists.date.get(1)}</td>
                    <td>${playlists.price}</td>
                    <c:if test="${sessionScope.role eq 'USER'}">
                        <td>
                            <form id="form1" method="post" action="atrack">
                                <input type="hidden" name="command" value="buy_song">
                                <input type="hidden" name="song_id" value="${playlists.id}">
                                <a href="#">
                                <img src="${pageContext.request.contextPath}/jsp/icons/buy.png" onclick="submitById('form1')">
                                </a>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>


<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

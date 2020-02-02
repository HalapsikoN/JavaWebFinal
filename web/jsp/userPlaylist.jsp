<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 31.01.2020
  Time: 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="playlistList" class="java.util.ArrayList" scope="request"/>
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<br>
<c:if test="${requestScope.get('playlistList').isEmpty()}">
    <p>There are not any playlists yet. You can correct this: </p>
    <a href="${pageContext.request.contextPath}/atrack?command=playlists_page">
        Click here.
    </a>
</c:if>
<c:if test="${!requestScope.get('playlistList').isEmpty()}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th>Playlist name(topic)</th>
            <th>Date</th>
            <th>Track list</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="playlist" items="${playlistList}">
            <tr>
                <td >${playlist.name}</td>
                <td><outputTag:date format="dd_mm_yyyy" item="${playlist.date}"/></td>
                <td>
                    <form id="info_playlist${playlist.id}" method="post" action="atrack">
                        <input type="hidden" name="command" value="playlist_info">
                        <input type="hidden" name="playlist_id" value="${playlist.id}">
                        <a href="#">
                            <img src="${pageContext.request.contextPath}/jsp/icons/trackList.png"
                                 onclick="submitById('info_playlist${playlist.id}')">
                        </a>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 30.01.2020
  Time: 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="playlistList" class="java.util.ArrayList" scope="request"/>
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>

</head>
<body>
<c:import url="header/header.jsp" charEncoding="utf-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<div id="center_div">
    <br>
    <p class="badge badge-info" style="font-size: 20px">${param.get("message")}</p>
</div>
<c:if test="${sessionScope.role eq 'ADMIN'}">
    <br>
    <div id="center_div">
        <a href="${pageContext.request.contextPath}/atrack?command=add_playlist_page"
           class="btn btn-primary"><fmt:message key="locale.playlists.title" bundle="${bundle}"/></a>
    </div>
</c:if>
<br>
<c:if test="${!requestScope.get('playlistList').isEmpty()}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="locale.general.tablePlaylistName" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableDate" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableTrackList" bundle="${bundle}"/></th>
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <th><fmt:message key="locale.general.tableEdit" bundle="${bundle}"/></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="playlist" items="${playlistList}">
            <tr>
                <td>${playlist.name}</td>
                <td><outputTag:date format="dd_mm_yyyy" item="${playlist.date}"/></td>
                <td>
                    <a href="atrack?command=playlist_info&playlist_id=${playlist.id}">
                        <img src="${pageContext.request.contextPath}/jsp/icons/trackList.png">
                    </a>
                </td>
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <td>
                        <a href="atrack?command=edit_playlist_page&playlist_id=${playlist.id}">
                            <img src="${pageContext.request.contextPath}/jsp/icons/edit.png">
                        </a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

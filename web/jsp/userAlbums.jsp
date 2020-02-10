<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 30.01.2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumList" class="java.util.ArrayList" scope="request"/>
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
<br>
<c:if test="${requestScope.get('albumList').isEmpty()}">
    <div id="center_div">
        <p><fmt:message key="locale.userAlbums.title" bundle="${bundle}"/>: </p>
        <a href="${pageContext.request.contextPath}/atrack?command=albums_page">
            <fmt:message key="locale.general.hrefClickHere" bundle="${bundle}"/>.
        </a>
    </div>
</c:if>
<c:if test="${!requestScope.get('albumList').isEmpty()}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="locale.general.tableAlbumName" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableArtist" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableDate" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableTrackList" bundle="${bundle}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="album" items="${albumList}">
            <tr>
                <td>${album.name}</td>
                <td>${album.artist}</td>
                <td><outputTag:date format="yyyy" item="${album.date}"/></td>
                <td>
                    <form id="info_album${album.id}" method="post" action="atrack">
                        <input type="hidden" name="command" value="album_info">
                        <input type="hidden" name="album_id" value="${album.id}">
                        <a href="#">
                            <img src="${pageContext.request.contextPath}/jsp/icons/trackList.png"
                                 onclick="submitById('info_album${album.id}')">
                        </a>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

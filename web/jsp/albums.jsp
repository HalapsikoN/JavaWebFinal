<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 30.01.2020
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="album" class="by.epam.finalTask.entity.Album" scope="request"/>
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
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">
    <br>
    <p class="badge badge-info" style="font-size: 20px">${param.get("message")}</p>
    <c:if test="${sessionScope.role eq 'ADMIN'}">

        <a href="${pageContext.request.contextPath}/atrack?command=add_album_page" class="btn btn-primary"><fmt:message
                key="locale.albums.addNewAlbumBtn" bundle="${bundle}"/></a>

    </c:if>
</div>
<br>
<c:if test="${!requestScope.get('albumList').isEmpty()}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="locale.general.tableAlbumName" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableArtist" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableDate" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableTrackList" bundle="${bundle}"/></th>
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <th><fmt:message key="locale.general.tableEdit" bundle="${bundle}"/></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="album" items="${albumList}">
            <tr>
                <td>${album.name}</td>
                <td>${album.artist}</td>
                <td><outputTag:date format="yyyy" item="${album.date}"/></td>
                <td>
                    <a href="atrack?command=album_info&album_id=${album.id}">
                        <img src="${pageContext.request.contextPath}/jsp/icons/trackList.png">
                    </a>
                </td>
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <td>
                        <a href="atrack?command=edit_album_page&album_id=${album.id}">
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

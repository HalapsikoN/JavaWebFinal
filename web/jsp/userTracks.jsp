<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 30.01.2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="songList" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<div>
    <br>
    <c:if test="${requestScope.get('songList').isEmpty()}">
        <p>There are not any songs yet. You can correct this: </p>
        <a href="${pageContext.request.contextPath}/atrack?command=main_page">
            Click here.
        </a>
    </c:if>
    <c:if test="${!requestScope.get('songList').isEmpty()}">
        <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
            <thead class="thead-dark">
            <tr>
                <th>Song</th>
                <th>Signer</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="songs" items="${songList}">
                <tr>
                    <td >${songs.name}</td>
                    <td>${songs.artist}</td>
                    <td>${songs.date.get(1)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>

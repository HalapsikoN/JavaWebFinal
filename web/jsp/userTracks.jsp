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
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
<jsp:useBean id="songList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="commentList" class="java.util.ArrayList" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div>
    <br>
    <c:if test="${requestScope.get('songList').isEmpty()}">
        <div id="center_div">
            <p><fmt:message key="locale.userTracks.title" bundle="${bundle}"/>: </p>
            <a href="${pageContext.request.contextPath}/atrack?command=main_page">
                <fmt:message key="locale.general.hrefClickHere" bundle="${bundle}"/>.
            </a>
        </div>
    </c:if>
    <c:if test="${!requestScope.get('songList').isEmpty()}">
        <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="locale.general.tableTrackPlay" bundle="${bundle}"/></th>
                <th><fmt:message key="locale.general.tableTrack" bundle="${bundle}"/></th>
                <th><fmt:message key="locale.general.tableArtist" bundle="${bundle}"/></th>
                <th><fmt:message key="locale.general.tableDate" bundle="${bundle}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="song" items="${songList}">
                <tr>
                    <td onclick="hideAndSick('hiddenRow${song.id}')">
                            <%--                        <audio controls>--%>
                            <%--                            <source src="${pageContext.request.contextPath}/track/${song.filename}" type="audio/mp3">--%>
                            <%--                            <fmt:message key="locale.general.tableTrackCannotPlay" bundle="${bundle}"/>--%>
                            <%--                        </audio>--%>
                            <%--                                                    <audio controls>--%>
                            <%--                                                        <source src="atrack?command=get_track&filename=${song.filename}" type="audio/mp3">--%>
                            <%--                                                        <fmt:message key="locale.general.tableTrackCannotPlay" bundle="${bundle}"/>--%>
                            <%--                                                    </audio>--%>
                        <a href="atrack?command=get_track&filename=${song.filename}">
                            <img src="${pageContext.request.contextPath}/jsp/icons/play.png">
                        </a>
                    </td>
                    <td onclick="hideAndSick('hiddenRow${song.id}')">${song.name}</td>
                    <td onclick="hideAndSick('hiddenRow${song.id}')">${song.artist}</td>
                    <td onclick="hideAndSick('hiddenRow${song.id}')"><outputTag:date format="yyyy"
                                                                                     item="${song.date}"/></td>
                </tr>
                <tr id="hiddenRow${song.id}" style="display: none">
                    <td colspan="10" class="comment" style="text-align: left; margin: 0">
                        <div class="card card-body">
                            <div class="container-fluid">
                                <c:forEach var="comment" items="${commentList}">
                                    <c:if test="${comment.trackId == song.id}">
                                        <div class="row">
                                            <div class="col-2">
                                                <p><strong>${comment.username}</strong></p>
                                                <p><outputTag:date format="hh_mm_ss_dd_mm_yyyy"
                                                                   item="${comment.date}"/></p>
                                            </div>

                                            <div class="col">
                                                <p>${comment.text}</p>
                                            </div>
                                        </div>
                                        <hr>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:if test="${sessionScope.role==null}">
                                <p><fmt:message key="locale.general.tableCommentNotSignIn" bundle="${bundle}"/>!</p>
                            </c:if>
                            <c:if test="${sessionScope.role eq 'USER'}">
                                <form action="atrack" method="post">
                                    <div class="form-group">
                                        <input type="hidden" name="command" value="add_comment">
                                        <input type="hidden" name="track_id" value="${song.id}">
                                        <label for="textComment${song.id}"><fmt:message
                                                key="locale.general.tableAddYourComment" bundle="${bundle}"/></label>
                                        <textarea class="form-control" name="text" id="textComment${song.id}"
                                                  rows="1" required></textarea>
                                        <br>
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="locale.general.tableAddBtn" bundle="${bundle}"/></button>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/comment.js"></script>
</body>
</html>

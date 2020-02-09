<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="album" class="by.epam.finalTask.entity.Album" scope="request"/>
<jsp:useBean id="trackList" class="java.util.ArrayList" scope="request"/>
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
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/input_form.css" rel="stylesheet">

<div id="center_div">
    <br>
    <h2><fmt:message key="locale.editAlbum.title1" bundle="${bundle}"/> (${album.name}) <fmt:message key="locale.editAlbum.title2" bundle="${bundle}"/></h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="edit_album">
        <input type="hidden" name="album_id" value="${album.id}">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.name" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="locale.formAlbum.namePlaceholder" bundle="${bundle}"/>" required
                       value="${album.name}">
            </div>
        </div>
        <div class="form-group row">
            <label for="artist" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.artist" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="artist" name="artist" placeholder="<fmt:message key="locale.formAlbum.artistPlaceholder" bundle="${bundle}"/>"
                       required value="${album.artist}">
            </div>
        </div>
        <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label"><fmt:message key="locale.formAlbum.date" bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="number" class="date-own form-control" id="date" name="date"
                       placeholder="<fmt:message key="locale.formAlbum.datePlaceholder" bundle="${bundle}"/>" required min="1900" max="2099" step="1"
                       value="<outputTag:date format="yyyy" item="${album.date}"/>">
            </div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="locale.button.update" bundle="${bundle}"/></button>

    </form>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter2">
        <fmt:message key="locale.editAlbum.editAlbumTracksBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle1"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle1"><fmt:message key="locale.editAlbum.editAlbumTrackTitle" bundle="${bundle}"/>:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="text-align: left">
                    <form id="album_tracks" action="atrack" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="edit_album_tracks">
                            <input type="hidden" name="album_id" value="${album.id}">
                            <c:forEach var="track" items="${trackList}">
                                <input type="checkbox" name="tracks_update" value="${track.id}"> ${track.name} - <outputTag:date format="yyyy" item="${track.date}"/>
                                <br>
                            </c:forEach>
                            <c:if test="${empty trackList}">
                                <p><fmt:message key="locale.editAlbum.noArtistTracks" bundle="${bundle}"/>.</p>
                            </c:if>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <c:if test="${not empty trackList}">
                    <button type="button" class="btn btn-primary" onclick="submitById('album_tracks')"><fmt:message key="locale.button.submit" bundle="${bundle}"/></button>
                    </c:if>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCenter">
        <fmt:message key="locale.editAlbum.deleteAlbumBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle2"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle2"><fmt:message key="locale.editAlbum.deleteAlbumTitle1" bundle="${bundle}"/>
                        (<strong>${album.name}</strong>) <fmt:message key="locale.editAlbum.deleteAlbumTitle2" bundle="${bundle}"/>?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <form action="atrack" method="post">
                        <input type="hidden" name="command" value="delete_album">
                        <input type="hidden" name="album_id" value="${album.id}">
                        <button type="submit" class="btn btn-danger"><fmt:message key="locale.button.yes" bundle="${bundle}"/></button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.no" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

    <br>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>

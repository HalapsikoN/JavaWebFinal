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
<jsp:useBean id="playlist" class="by.epam.finalTask.entity.Playlist" scope="request"/>
<jsp:useBean id="trackList" class="java.util.ArrayList" scope="request"/>
<%@ taglib uri="/WEB-INF/dateTag" prefix="outputTag" %>
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
    <h2>Edit (${playlist.name}) playlist</h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="edit_playlist">
        <input type="hidden" name="playlist_id" value="${playlist.id}">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name(topic): </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter track name(topic)" required
                       value="${playlist.name}">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>

    </form>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter2">
        Edit playlist tracks
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle1"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle1">Choose which tracks should be in album:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="text-align: left">
                    <form id="playlist_tracks" action="atrack" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="edit_playlist_tracks">
                            <input type="hidden" name="playlist_id" value="${playlist.id}">
                            <c:forEach var="track" items="${trackList}">
                                <input type="checkbox" name="tracks_update" value="${track.id}"> ${track.name} - <outputTag:date format="yyyy" item="${track.date}"/>
                                <br>
                            </c:forEach>
                            <c:if test="${empty trackList}">
                                <p>There are no tracks on server.</p>
                            </c:if>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <c:if test="${not empty trackList}">
                    <button type="button" class="btn btn-primary" onclick="submitById('playlist_tracks')">Submit</button>
                    </c:if>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCenter">
        Delete playlist
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle2"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle2">Are you sure that you want to delete playlist
                        (<strong>${playlist.name}</strong>) from service?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <form action="atrack" method="post">
                        <input type="hidden" name="command" value="delete_playlist">
                        <input type="hidden" name="playlist_id" value="${playlist.id}">
                        <button type="submit" class="btn btn-danger">Yes</button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <p class="badge badge-info" style="font-size: 20px">${message}</p>
</div>


<script src="${pageContext.request.contextPath}/jsp/js/submition.js"></script>
</body>
</html>
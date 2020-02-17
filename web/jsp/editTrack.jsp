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
<jsp:useBean id="track" class="by.epam.finalTask.entity.Track" scope="request"/>
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
    <h2><fmt:message key="locale.editTrack.title1" bundle="${bundle}"/> (${track.name}) <fmt:message
            key="locale.editTrack.title2" bundle="${bundle}"/></h2>
    <br>
    <form action="atrack" method="post" id="input_form">
        <input type="hidden" name="command" value="edit_track">
        <input type="hidden" name="track_id" value="${track.id}">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label"><fmt:message key="locale.formTrack.name"
                                                                           bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name"
                       placeholder="<fmt:message key="locale.formTrack.namePlaceholder" bundle="${bundle}"/>" required
                       value="${track.name}">
            </div>
        </div>
        <div class="form-group row">
            <label for="artist" class="col-sm-2 col-form-label"><fmt:message key="locale.formTrack.artist"
                                                                             bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="artist" name="artist"
                       placeholder="<fmt:message key="locale.formTrack.artistPlaceholder" bundle="${bundle}"/>" required
                       value="${track.artist}">
            </div>
        </div>
        <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label"><fmt:message key="locale.formTrack.date"
                                                                           bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="number" class="date-own form-control" id="date" name="date"
                       placeholder="<fmt:message key="locale.formTrack.datePlaceholder" bundle="${bundle}"/>" required
                       min="1900" max="2099" step="1" value="<outputTag:date format="yyyy" item="${track.date}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="price" class="col-sm-2 col-form-label"><fmt:message key="locale.formTrack.price"
                                                                            bundle="${bundle}"/>: </label>
            <div class="col-sm-10">
                <input type="number" class="form-control" id="price" name="price"
                       placeholder="<fmt:message key="locale.formTrack.pricePlaceholder" bundle="${bundle}"/>" required
                       min="0" step="0.01" value="${track.price}">
            </div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="locale.button.update"
                                                                   bundle="${bundle}"/></button>

    </form>

    <br>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCenter">
        <fmt:message key="locale.editTrack.deleteTrackBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle"><fmt:message
                            key="locale.editTrack.deleteTrackTitle1" bundle="${bundle}"/>
                        (<strong>${track.name}</strong>) <fmt:message key="locale.editTrack.deleteTrackTitle2"
                                                                      bundle="${bundle}"/>?</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <form action="atrack" method="post">
                        <input type="hidden" name="command" value="delete_track">
                        <input type="hidden" name="track_id" value="${track.id}">
                        <input type="hidden" name="filename" value="${track.filename}">
                        <button type="submit" class="btn btn-danger"><fmt:message key="locale.button.yes"
                                                                                  bundle="${bundle}"/></button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="locale.button.no" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>

</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
</body>
</html>

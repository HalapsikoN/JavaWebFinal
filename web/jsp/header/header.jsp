<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 27.01.2020
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="${path}/jsp/css/header.css">

<nav class="navbar navbar-expand-md navbar-dark bg-dark">

    <a href="${path}/atrack?command=main_page"> <fmt:message key="locale.header.tracks" bundle="${bundle}"/> </a>
    <a href="${path}/atrack?command=albums_page"> <fmt:message key="locale.header.albums" bundle="${bundle}"/> </a>
    <a id="" href="${path}/atrack?command=playlists_page"> <fmt:message key="locale.header.playlists"
                                                                        bundle="${bundle}"/> </a>

    <c:if test="${sessionScope.id==null}">
        <a id="ends" href="${path}/atrack?command=sign_in_page"> <fmt:message key="locale.header.signIn"
                                                                              bundle="${bundle}"/> </a>
        <a href="${path}/atrack?command=registration_page"> <fmt:message key="locale.header.register"
                                                                         bundle="${bundle}"/> </a>
    </c:if>

    <c:if test="${sessionScope.id!=null}">
        <c:choose>
            <c:when test="${sessionScope.role eq 'ADMIN'}">
                <a id="ends" href="${path}/atrack?command=user_list"> <fmt:message key="locale.header.listOfUsers"
                                                                                   bundle="${bundle}"/> </a>
                <a href="${path}/atrack?command=user_ban_list"> <fmt:message key="locale.header.listOfBanedUsers"
                                                                             bundle="${bundle}"/> </a>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenuLink2" aria-haspopup="true"
                   aria-expanded="false"> ${sessionScope.username} </a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink2" id="dropdownMenu1" style="left: auto">
                    <a class="dropdown-item" href="${path}/atrack?command=user_profile"><fmt:message
                            key="locale.header.myProfile" bundle="${bundle}"/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${path}/atrack?command=sign_out"><fmt:message
                            key="locale.header.signOut" bundle="${bundle}"/></a>
                </div>
            </c:when>
            <c:when test="${sessionScope.role eq 'USER'}">
                <a id="ends" href="${path}/atrack?command=user_tracks"> <fmt:message key="locale.header.myTracks"
                                                                                     bundle="${bundle}"/> </a>
                <a href="${path}/atrack?command=user_albums"> <fmt:message key="locale.header.myAlbums"
                                                                           bundle="${bundle}"/> </a>
                <a href="${path}/atrack?command=user_playlists"> <fmt:message key="locale.header.myPlaylists"
                                                                              bundle="${bundle}"/> </a>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenuLink1" aria-haspopup="true"
                   aria-expanded="false"> ${sessionScope.username} </a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink1" id="dropdownMenu1" style="left: auto">
                    <a class="dropdown-item" href="${path}/atrack?command=user_profile"><fmt:message
                            key="locale.header.myProfile" bundle="${bundle}"/></a>
                    <a class="dropdown-item" href="${path}/atrack?command=user_wallet"><fmt:message
                            key="locale.header.myWallet" bundle="${bundle}"/> (<strong>${sessionScope.wallet}</strong>)</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${path}/atrack?command=sign_out"><fmt:message
                            key="locale.header.signOut" bundle="${bundle}"/></a>
                </div>
            </c:when>
        </c:choose>
    </c:if>
</nav>




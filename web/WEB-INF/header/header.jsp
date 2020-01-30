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

<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

<nav>
    <a href="${path}/atrack?command=main_page"> Songs </a>
    <a href="${path}/atrack?command=albums_page"> Albums </a>
    <a id="" href="${path}/atrack?command=playlists_page"> Playlists </a>

    <c:if test="${sessionScope.id==null}">
        <a id="ends" href="${path}/atrack?command=sign_in_page"> Sign in </a>
        <a href="${path}/atrack?command=registration_page"> Register </a>
    </c:if>

    <c:if test="${sessionScope.id!=null}">
        <c:choose>
            <c:when test="${sessionScope.role eq 'ADMIN'}">
                <a id="ends" href="header.jsp"> List of users </a>
                <a href="header.jsp"> ${sessionScope.username} </a>
            </c:when>
            <c:when test="${sessionScope.role eq 'USER'}">
                <a id="ends" href="header.jsp"> My songs </a>
                <a href="header.jsp"> My albums </a>
                <a href="header.jsp"> My playlists </a>
                <a href="header.jsp"> ${sessionScope.username} </a>
            </c:when>
        </c:choose>
    </c:if>
</nav>

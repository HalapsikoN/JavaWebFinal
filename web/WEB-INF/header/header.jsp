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

<nav>
    <a id="starts" href="header.jsp"> Songs </a>
    <a id="starts" href="header.jsp"> Albums </a>
    <a id="starts" href="header.jsp"> Playlists </a>

    <c:if test="${sessionScope.user.id==null}">
        <a id="ends" href="header.jsp"> Sign in </a>
        <a href="${pageContext.request.contextPath}/atrack?command=registration_page" methods="post"> Register </a>
    </c:if>

    <c:if test="${sessionScope.user.id!=null}">
        <c:choose>
            <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                <a id="ends" href="header.jsp"> List of users </a>
                <a id="ends" href="header.jsp"> < value="${sessionScope.user.name}"/> </a>
            </c:when>
            <c:when test="${sessionScope.user.role eq 'USER'}">
                <a id="ends" href="header.jsp"> My songs </a>
                <a id="ends" href="header.jsp"> My albums </a>
                <a id="ends" href="header.jsp"> My playlists </a>
                <a id="ends" href="header.jsp"> < value="${sessionScope.user.name}"/> </a>
            </c:when>
        </c:choose>
    </c:if>
</nav>

<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 08.02.2020
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/footer.css">
<footer>

    <a href="${pageContext.request.contextPath}/atrack?locale=en"><img
            src="${pageContext.request.contextPath}/jsp/icons/eng.png"><fmt:message key="locale.lang.en"
                                                                                    bundle="${bundle}"/> </a>
    <a href="${pageContext.request.contextPath  }/atrack?locale=ru"><img
            src="${pageContext.request.contextPath}/jsp/icons/rus.png"><fmt:message key="locale.lang.ru"
                                                                                    bundle="${bundle}"/></a>

</footer>

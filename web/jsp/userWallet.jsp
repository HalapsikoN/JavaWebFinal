<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 31.01.2020
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="outputTag" uri="/WEB-INF/dateTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="bonusList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<jsp:useBean id="credit" class="by.epam.finalTask.entity.Credit" scope="request"/>
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
<div id="center_div">
    <h2>${sessionScope.username} <fmt:message key="locale.userWallet.title" bundle="${bundle}"/>wallet</h2>
    <c:if test="${credit.amount!=0}">
        <br>
        <div class="alert alert-danger" role="alert">
            <p><fmt:message key="locale.userWallet.haveCredit" bundle="${bundle}"/>:</p>
            <p><fmt:message key="locale.userWallet.amountToPay" bundle="${bundle}"/>: <strong>${credit.amount}</strong></p>
            <p><fmt:message key="locale.userWallet.endDate" bundle="${bundle}"/>: <strong><outputTag:date format="dd_mm_yyyy" item="${credit.date}"/></strong></p>
        </div>
    </c:if>
    <br>
    <p><fmt:message key="locale.userWallet.amount" bundle="${bundle}"/>: <strong>${sessionScope.wallet}</strong></p>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter1">
        <fmt:message key="locale.userWallet.replenishBalanceBtn" bundle="${bundle}"/>
    </button>

    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalCenter2">
        <fmt:message key="locale.userWallet.promisedPaymentBtn" bundle="${bundle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter1" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle1"><fmt:message key="locale.userWallet.replenishBalanceTitle" bundle="${bundle}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="adding_balance" action="atrack" method="post">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">$</span>
                            </div>
                            <input type="hidden" name="command" value="add_user_wallet">
                            <input type="number" name="amount" id="amount" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="<fmt:message key="locale.userWallet.replenishBalancePlaceholder" bundle="${bundle}"/>" required min="1">
                            <div class="input-group-append">
                                <span class="input-group-text">.00</span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="replenishBalanceFormById('adding_balance')"><fmt:message key="locale.button.submit" bundle="${bundle}"/></button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="modalCenter2" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle2" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalCenterTitle2"><fmt:message key="locale.userWallet.promisedPaymentTitle" bundle="${bundle}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="add_credit" action="atrack" method="post">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">$</span>
                            </div>
                            <input type="hidden" name="command" value="add_credit">
                            <input type="number" name="amount" id="credit" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="<fmt:message key="locale.userWallet.promisedPaymentPlaceholderAmount" bundle="${bundle}"/>" required min="1">
                            <input type="date" name="date" id="date" class="form-control" aria-label="End date if payment">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addCreditFormById('add_credit')"><fmt:message key="locale.button.submit" bundle="${bundle}"/></button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="locale.button.cancel" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <p class="badge badge-info">${message}</p>
    <br>
    <c:if test="${empty bonusList}">
        <p><fmt:message key="locale.userWallet.noBonuses" bundle="${bundle}"/>.</p>
    </c:if>
</div>

<c:if test="${not empty bonusList}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="locale.general.tableBonusName" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableDiscount" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableStartDate" bundle="${bundle}"/></th>
            <th><fmt:message key="locale.general.tableEndDate" bundle="${bundle}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bonus" items="${bonusList}">
            <tr>
                <td>${bonus.name}</td>
                <td>-${bonus.discount}%</td>
                <td><outputTag:date format="dd_mm_yyyy" item="${bonus.startDate}"/></td>
                <td><outputTag:date format="dd_mm_yyyy" item="${bonus.endDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>



<script src="${pageContext.request.contextPath}/jsp/js/wallet.js"></script>
</body>
</html>

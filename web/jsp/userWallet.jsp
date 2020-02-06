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
<jsp:useBean id="bonusList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<html>
<head>
    <c:import url="head/head.jsp" charEncoding="UTF-8"/>
</head>
<body>
<c:import url="header/header.jsp" charEncoding="UTF-8"/>
<link href="${pageContext.request.contextPath}/jsp/css/table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/jsp/css/center_info.css" rel="stylesheet">
<div id="center_div">
    <h2>${sessionScope.username} wallet</h2>
    <br>
    <p>Amount: <strong>${sessionScope.wallet}</strong></p>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCenter">
        Replenish balance
    </button>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Replenishing of balance</h5>
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
                            <input type="number" name="amount" id="amount" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="Enter the amount to add (min->1)" required min="1">
                            <div class="input-group-append">
                                <span class="input-group-text">.00</span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="replenishBalanceFormById('adding_balance')">Submit</button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <p class="badge badge-info">${message}</p>
    <br>
    <c:if test="${empty bonusList}">
        <p>You have no bonuses.</p>
    </c:if>
</div>

<c:if test="${not empty bonusList}">
    <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
        <thead class="thead-dark">
        <tr>
            <th>Bonus name</th>
            <th>Discount</th>
            <th>Start date</th>
            <th>End date</th>
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

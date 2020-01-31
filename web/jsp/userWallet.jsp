<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 31.01.2020
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</div>
<div>
    <br>
    <c:if test="${requestScope.get('bonusList').isEmpty()}">
        <p>There are not any bonuses yet.</p>
    </c:if>
    <c:if test="${!requestScope.get('bonusList').isEmpty()}">
        <table id="table" class="table table-secondary table-striped table-bordered table-hover justify-content-center">
            <thead class="thead-dark">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Start date</th>
                <th>End date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bonus" items="${bonusList}">
                <tr>
                    <td >${bonus.name}</td>
                    <td>${bonus.description}</td>
                    <td>${bonus.startDate.get(10)}:${bonus.startDate.get(12)}:${bonus.startDate.get(13)}   ${bonus.startDate.get(5)}.${bonus.startDate.get(2)}.${bonus.startDate.get(1)}</td>
                    <td>${bonus.endDate.get(10)}:${bonus.endDate.get(12)}:${bonus.endDate.get(13)}   ${bonus.endDate.get(5)}.${bonus.endDate.get(2)}.${bonus.endDate.get(1)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/jsp/js/wallet.js"></script>
</body>
</html>

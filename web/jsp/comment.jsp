<%--
  Created by IntelliJ IDEA.
  User: Halapsikon
  Date: 02.02.2020
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="commentList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<link href="${pageContext.request.contextPath}/jsp/css/comment.css" rel="stylesheet">

<td colspan="10">
    <div class="card card-body">
        <div class="container-fluid">
            <c:forEach var="comment" items="${commentList}">
                <c:if test="${comment.trackId == song.id}">
                    <div class="row">
                        <div class="col-2">
                            <p>${comment.userId}</p>
                            <p>${comment.date.get(5)}.${comment.date.get(2)}.${comment.date.get(1)}</p>
                        </div>

                        <div class="col">
                            <p>${comment.text}</p>
                        </div>
                    </div>
                    <hr>
                </c:if>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.role eq 'USER'}">
            <form action="atrack" method="post">
                <div class="form-group">
                    <input type="hidden" name="command" value="add_comment">
                    <input type="hidden" name="songId" value="${song.id}">
                    <input type="hidden" name="date" value="${now}">
                    <label for="textComment${song.id}">Add your comment</label>
                    <textarea class="form-control" name="text" id="textComment${song.id}"
                              rows="1"></textarea>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </c:if>
    </div>

</td>
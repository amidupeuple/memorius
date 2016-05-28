<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

<jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

<body>
<div class="container">

    <custom:bodyHeader/>

    <h2>Goals</h2>
    <br/>
    <c:if test="${not empty goals}">
        <%--<ul>
            <c:forEach var="goal" items="${goals}">
                <li>${goal.name}</li>
            </c:forEach>
        </ul>--%>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Deadline</th>
                    <th>Creator</th>
                    <th>Notification Frequency</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="goal" items="${goals}">
                    <tr>
                        <td class="goalsTableCell">${goal.name}</td>
                        <td class="goalsTableCell">${goal.description}</td>
                        <td class="goalsTableCell"><fmt:formatDate value="${goal.deadline}" pattern="yyyy-MM-dd"/></td>
                        <td class="goalsTableCell">${goal.creator}</td>
                        <td class="goalsTableCell">${goal.notificationFrequency}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:if>

    <br/>
    <a href="/home">Back to home</a></li>

</div>
</body>
</html>

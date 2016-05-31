<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

<%--<jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>--%>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0-rc2/css/bootstrap.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/vendors/tablesorter-master/css/theme.blue.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" />
</head>
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
        <table id="goalsTable" class="tablesorter">
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
                    <tr onclick="window.location='goal/${goal.id}'">
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




<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0-rc2/js/bootstrap.min.js"></script>
<script src="/resources/vendors/tablesorter-master/dist/js/jquery.tablesorter.min.js"/>
<script src="/resources/vendors/tablesorter-master/dist/js/jquery.tablesorter.widgets.js"></script>

<script>
    $(document).ready(function(){
        $(function(){
            $("#goalsTable").tablesorter(
                    {
                        theme : 'blue',

                        sortList : [[1,0],[2,0],[3,0]],

                        // header layout template; {icon} needed for some themes
                        headerTemplate : '{content}{icon}',

                        // initialize column styling of the table
                        widgets : ["columns"],
                        widgetOptions : {
                            // change the default column class names
                            // primary is the first column sorted, secondary is the second, etc
                            columns : [ "primary", "secondary", "tertiary" ]
                        }
                    });
        });
    });
</script>

</body>
</html>

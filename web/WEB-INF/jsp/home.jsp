<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> " rel="stylesheet"/>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
            <custom:bodyHeader/>

            <ul id="navigation_wrapper" class="nav nav-pills">
                <li role="presentation"><a href="/addGoal"><img border="0" alt="Add goal" src="/resources/images/add.png" width="100" height="100"></a></li>
                <li role="presentation"><a href="/showGoals"><img border="0" alt="Show goals" src="/resources/images/show.png" width="100" height="100"></a></li>
                <%--<li role="presentation"><a href="/test">Test</a></li>--%>
                <li role="presentation"><a href="#"><img border="0" alt="Statistic" src="/resources/images/stats.png" width="100" height="100"></a></li>
                <li role="presentation"><a href="javascript:formSubmit()"><img border="0" alt="Logout" src="/resources/images/logout.png" width="100" height="100"></a></li>
            </ul>

            <c:url value="/logout" var="logoutUrl"/>

            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>

            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>

            <c:if test="${not empty isGoalSaved}">
                <c:if test="${isGoalSaved == 'true'}">
                    <div class="alert alert-success goalModificationAlert">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${goalName}</strong> <span style="margin-right: 20px"> was saved!</span>
                    </div>
                </c:if>
            </c:if>
        </div>

    </body>
</html>

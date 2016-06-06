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

    </head>

    <body>
        <div class="container">
            <custom:bodyHeader/>

            <ul id="navigation_wrapper" class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/addGoal">Add goal</a></li>
                <li role="presentation"><a href="/showGoals">Show goals</a></li>
                <li role="presentation"><a href="javascript:formSubmit()">Logout</a></li>
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
                    <div class="alert alert-success">
                        <a href="/home" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong><span style="color: black">${goalName}</span> was saved!
                    </div>
                </c:if>
            </c:if>
        </div>

    </body>
</html>

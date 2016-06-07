<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
<div class="container">
    <custom:bodyHeader/>

    <b><span style="color: #5bb75b; font-size: 30px; margin-right: 20px">${goal.name}   </span></b>
    <a id="editButton" class="btn btn-default" role="button" href="/editGoal/${goal.id}">
        <span class="glyphicon glyphicon-pencil"/>
    </a>

    <br/>
    <br/>

    <form:form role="form" commandName="goal">
        <div class="form-group">
            <label for="creator">Creator:</label>
            <span id="creator">${goal.creator}</span>
        </div>
        <div class="form-group">
            <label for="deadline">Deadline:</label>
            <span id="deadline"><fmt:formatDate value="${goal.deadline}" pattern="yyyy-MM-dd"/></span>
        </div>
        <div class="form-group">
            <label for="notifications">Notification frequency:</label>
            <span id="notifications">${goal.notificationFrequency}</span>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <span id="status">${goal.status}</span>
        </div>
        <div class="form-group">
            <p>${goal.description}</p>
        </div>
    </form:form>

    <br/>
    <br/>
    <a class="btn btn-default" role="button" href="/showGoals">
        <span class="glyphicon glyphicon-chevron-left"></span>Back to goals</a>
    <br/>
    <br/>
    <br/>
    <br/>

    <script>
        $('#disableGoalDescription').click(function() {
            if ($('#goalDescription').is(':disabled')) {
                $('#goalDescription').prop('disabled', false)
            } else {
                $('#goalDescription').prop('disabled', true)
            }
        });
    </script>
</div>

</body>
</html>

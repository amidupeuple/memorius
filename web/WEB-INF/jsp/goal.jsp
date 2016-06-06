<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <h2>Goal <span style="color: #5bb75b">${goal.name}</span></h2>

    <br/>

    <form:form action="/goal" commandName="goal" method="post" cssClass="form-horizontal">
        <div class="row">
            <div class="col-lg-8">
                <form:errors path="*" cssClass="errorblock" element="div"/>
            </div>
        </div>

        <div class="form-group">
            <form:textarea path="description" cssClass="form-control col-lg-6" id="goalDescription"/>
            <button type="button" class="btn btn-default" id="disableGoalDescription">
                <span class="glyphicon glyphicon-pencil"/>
            </button>
        </div>

    </form:form>

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

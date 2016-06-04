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

    <h2>Goal <span style="color: #5bb75b">${goal.name}</span></h2>

    <br/>

    <p><b>Goal!${goalId}</b></p>

    <br/>
    <a href="/home">Back to home</a></li>

</div>

</body>
</html>

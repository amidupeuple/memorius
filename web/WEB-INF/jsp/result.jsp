<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

<body>
<div class="container">
    <custom:bodyHeader/>

    <br/>

    <p><b>Yep!</b>Goal successfully added:<br/>
        ID: ${goal.id}<br/>
        Name: ${goal.name}<br/>
        Description: ${goal.description}<br/>
        Deadline: ${goal.deadline}</p>

    <br/>
    <a href="/home">Back to home</a></li>

</div>

</body>
</html>

<%--suppress ALL --%>
<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" />

    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

    <script>
        $(document).ready(function() {
            $("#datepicker").datepicker({
                locale: 'ru'
            });
        });
    </script>
</head>


<body>
<div class="container">

    <custom:bodyHeader/>

    <h2>Edit goal</h2>

    <br/>

    <form:form action="/editGoal?goalId=${goal.id}" commandName="goal" method="post">
        <div class="row">
            <div class="col-lg-8">
                <form:errors path="*" cssClass="errorblock" element="div"/>
            </div>
        </div>

        <form:input path="id" type="hidden"/>
        <form:input path="name" type="hidden"/>
        <form:input path="creator" type="hidden"/>

        <!-- how to send value in controller via form submitting of disabled field -->
        <div class="row">
            <div class="form-group col-lg-6">
                <label for="nameOfGoal">Name:</label><br/>
                <form:input path="name" cssClass="form-control" id="nameOfGoal" disabled="true"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-6">
                <label for="goalDescription">Description:</label>
                <form:textarea path="description" cssClass="form-control" id="goalDescription"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label for="datepicker">Finish:</label><br/>
                <form:input id="datepicker" path="deadline" maxlength="50" cssClass="form-control"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label for="creator">Creator:</label><br/>
                <form:input path="creator" cssClass="form-control" id="creator" disabled="true" />
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label for="notificationFreqSel">Notification frequency:</label><br/>
                <form:select path="notificationFrequency" id="notificationFreqSel" cssClass="form-control" >
                    <form:option value=""/>
                    <form:option value="Day before"/>
                    <form:option value="Everyday"/>
                </form:select>

                <c:set var="notificationVal" value="goal.notificationFrequency"/>
                <c:choose>
                    <c:when test="${notificationVal eq ''}">
                        <script>setSelected(0)</script>
                    </c:when>
                    <c:when test="${notificationVal eq 'Day before'}">
                        <script>setSelected(1)</script>
                    </c:when>
                    <c:when test="${notificationVal eq 'Everyday'}">
                        <script>setSelected(2)</script>
                    </c:when>
                </c:choose>

                <script>
                    function setSelected(index) {
                        var notificationsSelect = document.getElementById('notificationFreqSel');
                        notificationsSelect.selectedIndex = index;
                    }
                </script>
            </div>
        </div>

        <%--<div class="row">
            <div class="form-group col-lg-4">
                <label for="status">Status:</label><br/>
                <form:input path="status" cssClass="form-control" id="status" disabled="true" />
            </div>
        </div>--%>

        <div class="row">
            <div class="form-group col-lg-4">
                <label for="statusSelector">Status:</label><br/>
                <form:select path="status" id="statusSelector" cssClass="form-control" >
                    <form:option value="Open"/>
                    <form:option value="Postponed"/>
                    <form:option value="Closed"/>
                </form:select>

                <c:set var="statusVal" value="goal.status"/>
                <c:choose>
                    <c:when test="${notificationVal eq 'Open'}">
                        <script>setSelectedStatus(0)</script>
                    </c:when>
                    <c:when test="${notificationVal eq 'Postponed'}">
                        <script>setSelectedStatus(1)</script>
                    </c:when>
                    <c:when test="${notificationVal eq 'Closed'}">
                        <script>setSelectedStatus(2)</script>
                    </c:when>
                </c:choose>

                <script>
                    function setSelectedStatus(index) {
                        var notificationsSelect = document.getElementById('statusSelector');
                        notificationsSelect.selectedIndex = index;
                    }
                </script>
            </div>
        </div>

        <button type="submit" class="btn btn-success">Submit</button><br/>
        <br/>
        <br/>
        <a class="btn btn-default" role="button" href="/goal/${goal.id}">
            <span class="glyphicon glyphicon-chevron-left"></span>Back to goal</a>
        <br/>
        <br/>
        <br/>
        <br/>


    </form:form>


</div>
</body>


</html>

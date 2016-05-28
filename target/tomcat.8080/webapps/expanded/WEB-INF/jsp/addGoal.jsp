<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body>
        <div class="container">

            <custom:bodyHeader/>

            <h2>Add a new goal</h2>

            <br/>

            <form:form role="form" action="/addGoal" modelAttribute="newGoal">
                <div class="form-group">
                    <label for="nameOfGoal">Name:</label><br/>
                    <form:input path="name" cssClass="form-control" id="nameOfGoal"/>
                </div>
                <div class="form-group">
                    <label for="goalDescription">Description:</label>
                    <form:textarea path="description" cssClass="form-control" id="goalDescription"/>
                </div>
                <div class="form-group">
                    <label for="datepicker">Finish:</label><br/>
                    <form:input id="datepicker" path="deadline" maxlength="50" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label for="creator">Creator:</label><br/>
                    <form:input path="creator" cssClass="form-control" id="creator" disabled="true" />
                </div>
                <div class="form-group">
                    <label for="notificationFreqSel">Notification frequency:</label><br/>
                    <form:select path="notificationFrequency" id="notificationFreqSel" cssClass="form-control">
                        <form:option value=""/>
                        <form:option value="Day before"/>
                        <form:option value="Everyday"/>
                    </form:select>
                </div>

                <br/>

                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>
        </div>
    </body>


</html>

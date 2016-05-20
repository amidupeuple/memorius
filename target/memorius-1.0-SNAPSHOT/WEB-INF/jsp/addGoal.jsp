<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body>
        <div class="container">

            <custom:bodyHeader/>

            <div class="jumbotron">
                <h2>Add a new goal</h2>

                <br/>

                <form role="form">
                    <div class="form-group">
                        <label for="nameOfGoal">Name:</label>
                        <input type="text" class="form-control" id="nameOfGoal">
                    </div>
                    <div class="form-group">
                        <label for="goalDescription">Description:</label>
                        <textarea class="form-control" id="goalDescription"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="finishDate">Finish:</label>
                        <input type="date" class="form-control" id="finishDate">
                    </div>
                    <br/>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
        </div>
    </body>
</html>

<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body>
        <div class="container">
            <custom:bodyHeader/>

            <ul id="navigation_wrapper" class="list-group">
                <li class="list-group-item"><a href="/addGoal">Add goal</a></li>
                <li class="list-group-item"><a href="/showGoals">Show goals</a></li>
                <li class="list-group-item"><a href="javascript:formSubmit()">Logout</a></li>
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

            <%--<c:if test="${pageContext.request.userPrincipal.name != null}">
                <h2>Welcome : ${pageContext.request.userPrincipal.name} |
                <a href="javascript:formSubmit()">Logout</a> </h2>
            </c:if>--%>
        </div>

    </body>
</html>

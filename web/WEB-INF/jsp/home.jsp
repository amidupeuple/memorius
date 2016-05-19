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

            <%--<div id="navigation_wrapper" class="row">
                <div class="col-sm-4"><a href="">Add goal</a></div>
                <div class="col-sm-4"><a href="">Show goals</a></div>
                <div class="col-sm-4"><a href="">Logout</a></div>
            </div>--%>

            <ul id="navigation_wrapper">
                <li><a href="/addGoal">Add goal</a></li>
                <li><a href="">Show goals</a></li>
                <li><a href="javascript:formSubmit()">Logout</a></li>
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

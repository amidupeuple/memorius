<!DOCTYPE html>

<%@ page session="true" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body>
        <custom:bodyHeader/>

        <h1>Welcome to Memorius!</h1>

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

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Welcome : ${pageContext.request.userPrincipal.name} |
        <a href="javascript:formSubmit()">Logout</a> </h2>
    </c:if>
    </body>
</html>

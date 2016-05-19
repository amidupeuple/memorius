<!DOCTYPE html>

<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body>
        <custom:bodyHeader/>

        <c:url value="/login" var="loginUrl"/>

        <form action="${loginUrl}" method="post">
            <c:if test="${not empty error}">
                <p>
                    ${error}
                </p>
            </c:if>
            <c:if test="${not empty msg}">
                <p>
                    ${msg}
                </p>
            </c:if>
            <p>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>
            </p>
            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn">Log in</button>
        </form>
    </body>
</html>

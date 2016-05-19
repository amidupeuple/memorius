<!DOCTYPE html>

<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">

    <jsp:include page="/WEB-INF/jsp/fragments/htmlHeader.jsp"/>

    <body id="login_body">
        <div class="container">
            <custom:bodyHeader/>

            <c:url value="/login" var="loginUrl"/>

            <div class="container">
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
            </div>
        </div>
    </body>
</html>

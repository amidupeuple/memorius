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


                <div class="form-group">
                    <input type="text" class="form-control login_field" placeholder="username" name="username" required autofocus>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control login_field" placeholder="password" name="password" required >
                </div>
                <br/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-default">Log in</button>
            </form>
        </div>
    </body>
</html>

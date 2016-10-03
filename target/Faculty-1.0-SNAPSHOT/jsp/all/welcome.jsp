<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body style="text-align:center;">

<div class="info">
    <h2>Hi, ${sessionScope.get("user")}</h2>
    <h4>Login: ${sessionScope.get("login")}</h4>
    <h4>Role: ${sessionScope.get("role")}</h4>

    <c:if test="${role == 'TEACHER'}">
        <h4>Position: ${sessionScope.get("position")}</h4>
    </c:if>

    <c:if test="${role == 'STUDENT'}">
        <h4>Contract: ${sessionScope.get("contract")}</h4>
    </c:if>
    <br>

</div>
<br>

</body>
</html>

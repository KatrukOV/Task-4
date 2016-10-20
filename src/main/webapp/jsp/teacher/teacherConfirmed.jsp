<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Confirmed</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>

<body style="text-align:center;">

<h3>Confirmed</h3>

<br>
<label><b>${title} </label>
<br>
<br>
<c:forEach var="human" items="${humanList}">
    <label><b>${human.lastName}</b></label>
    <label><b>${human.name}</b></label>
    <label><b>${human.patronymic}</b></label>

    <form action="/dispatcher" method="post">
        <input type="hidden" name="login" value="${human.login}"/>
        <input type="hidden" name="title" value="${title}"/>
        <input type="hidden" name="command" value="confirmed"/>
        <input type="submit" value="confirmed"/>
    </form>

    <%--<form action="/dispatcher" method="post">--%>
    <%--<input type="hidden" name="login" value="${human.login}"/>--%>
    <%--<input type="hidden" name="title" value="${title}"/>--%>
    <%--<input type="hidden" name="command" value="deleted"/>--%>
    <%--<input type="submit" value="deleted"/>--%>
    <%--</form>--%>


    <c:if test="${error != 'NULL'}">
        <label><b>Error: ${requestScope.get("error")}</b></label>
    </c:if>
</c:forEach>

<a href="jsp/teacher/teacherDisciplines.jsp"><input type="button"
                                                    value="back to teacher Disciplines"/></a>

<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>

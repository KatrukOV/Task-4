<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Disciplines</title>
</head>

<body style="text-align:center;">

<h3>All disciplines</h3>

<c:forEach var="discipline" items="${disciplineList}">
    <label><b>${discipline.title}</b></label>
    <label><b>${discipline.teacher}</b></label>

    <c:if test="${role == 'STUDENT'}">
        <form action="/dispatcher" method="post">
            <input type="hidden" name="title" value="${discipline.title}"/>
            <input type="hidden" name="command" value="declared"/>
            <input type="submit" value="Declared"/>
        </form>
    </c:if>

    <br>
</c:forEach>

<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>


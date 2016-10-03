<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Disciplines</title>
</head>


<body style="text-align:center;">

<h3>Declared disciplines</h3>

<c:forEach var="discipline" items="${allDeclaredDisciplines}">
    <label><b>${discipline.title}</b></label>
    <label><b>${discipline.teacher}</b></label>


    <form action="/dispatcher" method="post">
        <input type="hidden" name="title" value="${discipline.title}"/>
        <input type="hidden" name="command" value="revoked"/>
        <input type="submit" value="Revoked"/>
    </form>
    <br>
</c:forEach>
<br>
<br>
<br>
<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>

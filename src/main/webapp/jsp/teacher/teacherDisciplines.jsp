<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Teacher Disciplines</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>

<body style="text-align:center;">
<br>
<c:forEach var="discipline" items="${disciplineList}">
    <label><b>${discipline.title}</b></label>

    <form action="/dispatcher" method="get">
        <input type="hidden" name="title" value="${discipline.title}"/>
        <input type="hidden" name="command" value="redirectToConfirmed"/>
        <input type="submit" value="Go to Confirmed Student"/>
    </form>


    <form action="/dispatcher" method="get">
        <input type="hidden" name="title" value="${discipline.title}"/>
        <input type="hidden" name="command" value="redirectToEvaluation"/>
        <input type="submit" value="To Evaluation"/>
    </form>
    <br>

</c:forEach>
<br>
<br>
<br>
<form action="/dispatcher" method="post">
    <h2>Add new discipline</h2>
    <label>title</label>
    <input type="text" name="title" placeholder="Discipline title"/>
    <input type="hidden" name="command" value="addDiscipline"/>
    <input type="submit" value="Add"/>
</form>
<br>
<br>
<br>
<jsp:include page="/jsp/all/toProfile.jsp"/>
</body>
</html>

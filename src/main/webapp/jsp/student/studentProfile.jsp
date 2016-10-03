<html>
<head>
    <title>Student Profile</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>
<jsp:include page="/jsp/all/welcome.jsp"/>

<body style="text-align:center;">
<br>
<br>


<form action="/dispatcher" method="get">
    <input type="hidden" name="command" value="getAllDisciplines">
    <input type="submit" value="Find all Disciplines"/>
</form>

<br>
<br>

<form action="/dispatcher" method="get">
    <input type="hidden" name="command" value="redirectToDeclaredDisciplines">
    <input type="submit" value="show my declared Disciplines"/>
</form>

<br>
<br>

<form action="/dispatcher" method="get">
    <input type="hidden" name="command" value="redirectToMarks">
    <input type="submit" value="Get my MARKS"/>
</form>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Teacher Profile</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>
<jsp:include page="/jsp/all/welcome.jsp"/>

<body style="text-align:center;">
<br>
<br>
<br>
<form action="/dispatcher" method="get">
    <input type="hidden" name="command" value="redirectToTeacherDisciplines">
    <input type="submit" value="Show my disciplines"/>
</form>
<br>

</body>
</html>

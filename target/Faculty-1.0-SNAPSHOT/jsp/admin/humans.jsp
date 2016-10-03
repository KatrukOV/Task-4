<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ALL HUMANS</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>

<body style="text-align:center;">
<h3>ALL HUMANS</h3>

<div class="humans">
    <c:forEach var="human" items="${humanList}">
        <form action="/dispatcher" method="post">
            <label><b>${human.lastName}</b></label>
            <label><b>${human.name}</b></label>
            <label><b> ${human.patronymic}</b></label>
            <label><b> role now: ${human.role}</b></label>
            <br>
            <label> set Role: </label>
            <select name="role">
                <option value="STUDENT" ${human.role.equals('STUDENT') ? 'selected="selected"' : ''}>STUDENT</option>
                <option value="TEACHER" ${human.role.equals('TEACHER') ? 'selected="selected"' : ''}>TEACHER</option>
                <option value="ADMIN" ${human.role.equals('ADMIN') ? 'selected="selected"' : ''}>ADMIN</option>
            </select>
            <input type="hidden" name="login" value="${human.login}"/>
            <input type="hidden" name="command" value="setRole"/>
            <input type="submit" value="Submit"/>
        </form>
    </c:forEach>
</div>

<br>
<br>
<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>

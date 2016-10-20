<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All students}</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>

<body style="text-align:center;">

<h3>ALL TEACHERS</h3>

<div class="humans">
    <c:forEach var="teacher" items="${teacherList}">
        <form action="/dispatcher" method="post">
            <label><b>${teacher.lastName}</b></label>
            <label><b>${teacher.name}</b></label>
            <label><b>${teacher.patronymic}</b></label>
            <label><b> position ${teacher.position}</b></label>

            <label> set position: </label>
            <select name="position">
                <option value="ASSISTANT_PROFESSOR" ${teacher.position.equals('ASSISTANT_PROFESSOR')
                        ? 'selected="selected"' : ''}>ASSISTANT_PROFESSOR
                </option>
                <option value="ASSOCIATE_PROFESSOR" ${teacher.position.equals('ASSOCIATE_PROFESSOR')
                        ? 'selected="selected"' : ''}>ASSOCIATE_PROFESSOR
                </option>
                <option value="PROFESSOR" ${teacher.position.equals('PROFESSOR')
                        ? 'selected="selected"' : ''}>PROFESSOR
                </option>
            </select>

            <input type="hidden" name="login" value="${teacher.login}"/>
            <input type="hidden" name="command" value="setPosition"/>
            <input type="submit" value="Submit"/>
        </form>
    </c:forEach>
</div>

<br>
<br>

<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>

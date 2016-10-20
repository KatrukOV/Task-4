<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All students}</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>

<body style="text-align:center;">
<h3>ALL students</h3>

<div class="humans">
    <c:forEach var="student" items="${studentList}">
        <form action="/dispatcher" method="post">
            <label><b>${student.lastName}</b></label>
            <label><b>${student.name}</b></label>
            <label><b>${student.patronymic}</b></label>
            <label><b> ${student.contract}</b></label>

                <%--<label>           set contract true</label>--%>
                <%--<input type="checkbox" name="contract" value="contract" />--%>

            <label> set contract: </label>
            <select name="contract">
                <option value="TRUE" ${teacher.contract.equals('TRUE') ? 'selected="selected"' : ''}>
                    TRUE
                </option>
                <option value="FALSE" ${teacher.contract.equals('FALSE') ? 'selected="selected"' : ''}>
                    FALSE
                </option>
            </select>

            <input type="hidden" name="login" value="${student.login}"/>
            <input type="hidden" name="command" value="setContract"/>
            <input type="submit" value="Submit"/>
        </form>
    </c:forEach>
</div>

<br>

<jsp:include page="/jsp/all/toProfile.jsp"/>

</body>
</html>

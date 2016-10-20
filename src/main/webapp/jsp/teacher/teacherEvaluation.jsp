<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Evaluation</title>
</head>

<body style="text-align:center;">

<h4>Welcome to the Evaluation of discipline</h4>

<h3>Students:</h3>
<c:forEach var="evaluation" items="${evaluationList}">

    <form action="/dispatcher" method="post">
        <label><b>${evaluation.student} </b></label>

        <input type="text" name="feedback" placeholder="Feedback"
               value="${evaluation.feedback == null ? "" : evaluation.feedback}"/>

        <label><b> ${evaluation.mark == null ? "" : evaluation.mark} </b></label>

        <label> set Mark: </label>
        <select name="mark">
            <option value="A" ${evaluation.mark.equals('A') ? 'selected="selected"' : ''}>A</option>
            <option value="B" ${evaluation.mark.equals('B') ? 'selected="selected"' : ''}>B</option>
            <option value="C" ${evaluation.mark.equals('C') ? 'selected="selected"' : ''}>C</option>
            <option value="D" ${evaluation.mark.equals('D') ? 'selected="selected"' : ''}>D</option>
            <option value="E" ${evaluation.mark.equals('E') ? 'selected="selected"' : ''}>E</option>
            <option value="Fx" ${evaluation.mark.equals('Fx') ? 'selected="selected"' : ''}>Fx
            </option>
            <option value="F" ${evaluation.mark.equals('F') ? 'selected="selected"' : ''}>F</option>
        </select>

        <input type="hidden" name="title" value="${evaluation.discipline.title}"/>
        <input type="hidden" name="student" value="${evaluation.student.login}"/>

        <input type="hidden" name="command" value="feedback"/>
        <input type="submit" value="Submit"/>
    </form>
</c:forEach>
<br>
<br>
<a href="jsp/teacher/teacherDisciplines.jsp"><input type="button"
                                                    value="back to teacher Disciplines"/></a>
<br>
<br>
<br>
<jsp:include page="/jsp/all/toProfile.jsp"/>
</body>
</html>

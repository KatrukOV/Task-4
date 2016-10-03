<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>Student Marks and FeedBack</title>
</head>

<jsp:include page="/jsp/all/logout.jsp"/>
<jsp:include page="/jsp/all/welcome.jsp"/>

<body style="text-align:center;">


<div class="feedback">

    <h3>Feedback</h3>

    <c:if test="${fn:length(studentMarkList) == 0}">
        <p> you have no marks </p>
    </c:if>

    <c:forEach var="studentMark" items="${studentMarkList}">

        <div>
            <h4>on discipline: ${studentMark.titleDiscipline}</h4>
            <h4>Mark: ${studentMark.mark==null ? '' : studentMark.mark}</h4>
            <p>Feedback: ${studentMark.feedback}</p>
            <br>
        </div>

    </c:forEach>
</div>
<br>
<br>
<jsp:include page="/jsp/all/toProfile.jsp"/>
</body>
</html>

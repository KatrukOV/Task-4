<%--<%@ page pageEncoding="UTF-8" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'ua'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<fmt:setBundle basename="i18n.text"/>

<!DOCTYPE html>

<html lang="${language}">
<html>
<head>
    <title><fmt:message key="index.authorization"/></title>
</head>

<h1><fmt:message key="index.text.welcome"/></h1>

<body style="text-align:center;">

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukraine</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russia</option>
    </select>
</form>

<form action="/dispatcher" method="post">
    <label for="Login"><fmt:message key="index.label.login"/>:</label>
    <input type="text" id="login" name="login">
    <br>
    <label for="password"><fmt:message key="index.label.password"/>:</label>
    <input type="password" id="password" name="password">
    <br>

    <input type="hidden" name="command" value="login"/>
    <fmt:message key="index.button.submit" var="buttonValue"/>
    <input type="submit" name="submit" value="${buttonValue}">

    <a href="jsp/reg/registration.jsp"><input type="button"
                                              value="<fmt:message key="index.label.signUp"/>"/></a>
</form>


</body>
</html>

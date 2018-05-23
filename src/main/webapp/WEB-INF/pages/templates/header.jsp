<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.coliseum.web.util.Command" %>
<%@ page import="com.coliseum.web.util.Attribute" %>
<%@ page import="com.coliseum.model.entities.enums.Role" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <c:set var="language"
               value="${empty param.language ? (empty sessionScope.language ? 'en' : sessionScope.language) : param.language }"
               scope="session"/>
        <fmt:setLocale value="${sessionScope.language}"/>
        <fmt:setBundle basename="bundles\bundle"/>
        <link href="<c:url value='/resources/bootstrap/css/bootstrap.css'/>" rel="stylesheet" type="text/css">

    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">COLISEUM</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav ">

                        <li>
                            <form action="${Command.TIMETABLE_PAGE_CMD.getCommand()}" method="post">
                                <input class="btn btn-link btn-lg" type="submit" value=<fmt:message
                                        key="header.label.sessionsButton"/>>
                            </form>
                        </li>

                        <li>
                            <form action="${Command.MOVIES_PAGE_CMD.getCommand()}" method="post">
                                <input class="btn btn-link btn-lg" type="submit" value=<fmt:message
                                        key="header.label.moviesButton"/>>
                            </form>
                        </li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <c:if test="${sessionScope.get(Attribute.CURRENT_USER_ATR.getAttribute()).role != Role.GUEST}">
                            <li>
                                <form action="${Command.HOME_PAGE_CMD.getCommand()}" method="post">
                                    <input class="btn btn-link btn-lg" type="submit"
                                           value="<fmt:message key="header.label.cabinetButton"/>">
                                </form>
                            </li>

                            <li>
                                <form action="${Command.PROFILE_PAGE_CMD.getCommand()}" method="post">
                                    <input class="btn btn-link btn-lg" type="submit" value="<fmt:message
                                            key="header.label.profileButton"/>">
                                </form>
                            </li>

                            <li>
                                <form action="${Command.LOGOUT_CMD.getCommand()}" method="post">
                                    <input class="btn btn-link btn-lg" type="submit" value="<fmt:message
                                            key="header.label.logoutButton"/>">
                                </form>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.get(Attribute.CURRENT_USER_ATR.getAttribute()).role == Role.GUEST}">
                            <li>
                                <form action="${Command.LOGIN_PAGE_CMD.getCommand()}" method="post">
                                    <input class="btn btn-link btn-lg" type="submit" value="<fmt:message
                                            key="header.label.cabinetButton"/>">
                                </form>
                            </li>
                        </c:if>

                        <li>
                            <form id="locale" method="post"
                                  action="${requestScope.get(Attribute.COMMAND_ATR.getAttribute())}">
                                <select class="btn btn-default" name="language" onchange="submit()" form="locale">
                                    <option value="en" ${language == 'en' ? 'selected' : ''}>
                                        <fmt:message key="header.label.language.en"/>
                                    </option>
                                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>
                                        <fmt:message key="header.label.language.ru"/>
                                    </option>
                                </select>
                            </form>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
        <%--<c:if test="${not empty requestScope.get(Attribute.MESSAGE_ATR.getAttribute())}">--%>
        <%--<div class="alert alert-warning">--%>
        <%--<strong>Warning!</strong> <c:out value="${requestScope.get(Attribute.MESSAGE_ATR.getAttribute())}"/>--%>
        <%--</div>--%>
        <%--</c:if>--%>

    </body>
</html>

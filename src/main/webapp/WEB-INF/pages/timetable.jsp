<html>
    <head>
        <title>Timetable</title>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><fmt:message key="timetable.label.startTime"/></th>
                            <th><fmt:message key="timetable.label.movie"/></th>
                            <th><fmt:message key="timetable.label.hall"/></th>
                            <th><fmt:message key="timetable.label.price"/></th>
                            <th><fmt:message key="timetable.label.status"/></th>
                        </tr>
                    </thead>
                    <c:forEach var="session" items="${requestScope.get(Attribute.SESSIONS_ATR.getAttribute())}">
                        <tbody>
                            <tr>
                                <th>
                                    <form action="${Command.SESSION_PAGE_CMD.getCommand()}" method="post">
                                        <input class="btn btn-link btn-md" type="submit"
                                               value="<c:out value="${session.getStartTime()}"/>">
                                        <input type="hidden" name="${Attribute.SESSION_ID_ATR.getAttribute()}"
                                               value="${session.getId()}"/>
                                    </form>
                                </th>
                                <th><c:out value="${session.getMovie().getTitle()}"/></th>
                                <th><c:out value="${session.getHall().getName()}"/></th>
                                <th><c:out value="${session.getPrice()}"/></th>
                                <th><c:out value="${session.getStatus()}"/></th>
                            </tr>

                        </tbody>
                    </c:forEach>
                </table>

                <div class="btn-group btn-group-xs">
                    <c:forEach var="page" items="${requestScope.get(Attribute.PAGES_ATR.getAttribute())}">
                        <form class="btn btn-info" action="${Command.TIMETABLE_PAGE_CMD.getCommand()}" method="post">
                            <input type="hidden" name="${Attribute.NAVIGATION_FORM_ATR.getAttribute()}"
                                   value="timetableForm"/>
                            <input type="hidden" name="${Attribute.PAGE_ATR.getAttribute()}" value="${page}"/>
                            <input type="submit" class="btn btn-link" value="${page}"/>
                        </form>
                    </c:forEach>
                </div>

            </div>
        </div>

    </body>
</html>

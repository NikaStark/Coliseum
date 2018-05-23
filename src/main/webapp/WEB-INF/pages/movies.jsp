<html>
    <head>
        <title>Movies</title>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><fmt:message key="movies.label.title"/></th>
                            <th><fmt:message key="movies.label.duration"/></th>
                            <th><fmt:message key="movies.label.status"/></th>
                        </tr>
                    </thead>
                    <c:forEach var="session" items="${requestScope.get(Attribute.MOVIES_ATR.getAttribute())}">
                        <tbody>
                            <tr>
                                <th><c:out value="${session.getTitle()}"/></th>
                                <th><c:out value="${session.getDuration()}"/></th>
                                <th><c:out value="${session.getStatus()}"/></th>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>

                <div class="btn-group btn-group-xs">
                    <c:forEach var="page" items="${requestScope.get(Attribute.PAGES_ATR.getAttribute())}">
                        <form class="btn btn-info" action="${Command.MOVIES_PAGE_CMD.getCommand()}" method="post">
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

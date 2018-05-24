<html>
    <head>
        <title>Session</title>
        <style type="text/css">
            .btn-sq-xs {
                width: 25px !important;
                height: 25px !important;
                padding: 2px !important;
            }

            .cell {
                margin: 1px !important;
                border: 0 !important;
                padding: 0 !important;
                width: 25px;
                height: 25px;
            }
        </style>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-9">


                    <p class="center-block"><h3>
                        <c:forEach begin="1" end="15">
                            &nbsp;
                        </c:forEach>
                        <fmt:message key="session.label.screen"/>
                    </h3></p>


                    <c:forEach var="i" begin="1"
                               end="${requestScope.get(Attribute.SESSION_ATR.getAttribute()).getHall().getCountRows()}">
                        <div class="btn-group btn-group-xs">
                            <c:forEach var="j" begin="1"
                                       end="${requestScope.get(Attribute.SESSION_ATR.getAttribute()).getHall().getCountColumns()}">
                                <form class="btn cell" action="${Command.ADD_TO_BASKET_CMD.getCommand()}" method="post">
                                    <input class="btn btn-primary btn-sq-xs
                                <c:forEach var="ticket" items="${requestScope.get(Attribute.TICKETS_ATR.getAttribute())}">
                                    ${ticket.getRow() == i && ticket.getSeat() == j ? 'disabled \"' : ''}"
                                    </c:forEach>
                                           type="submit" value="${j}"/>
                                    <input type="hidden" name="${Attribute.ROW_ATR.getAttribute()}" value="${i}"/>
                                    <input type="hidden" name="${Attribute.SEAT_ATR.getAttribute()}" value="${j}"/>
                                </form>
                            </c:forEach>
                        </div>
                        ${i}
                    </c:forEach>

                </div>
                <div class="col-md-3">

                    <fmt:message key="session.label.basket"/>:

                    <ul class="list-group">
                        <c:forEach var="reservedTicket"
                                   items="${requestScope.get(Attribute.RESERVED_TICKETS_ATR.getAttribute())}">
                            <li class="list-group-item">
                                <p><fmt:message key="session.label.ticket"/></p>

                                <p><fmt:message key="session.label.row"/> : ${reservedTicket.getRow()}</p>

                                <p><fmt:message key="session.label.seat"/> : ${reservedTicket.getSeat()}</p>

                                <form class="btn btn-md" action="${Command.REMOVE_FROM_BASKET_CMD.getCommand()}"
                                      method="post">
                                    <button class="btn btn-md btn-danger" type="submit">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        <fmt:message key="session.label.remove"/>
                                    </button>
                                    <input type="hidden" name="${Attribute.TICKET_ID_ATR.getAttribute()}"
                                           value="${reservedTicket.getId()}"/>
                                </form>
                            </li>
                        </c:forEach>

                    </ul>

                    <c:if test="${sessionScope.get(Attribute.CURRENT_USER_ATR.getAttribute()).role == Role.CLIENT ||
                    not empty requestScope.get(Attribute.RESERVED_TICKETS_ATR.getAttribute())}">
                        <form class="btn btn-md" action="${Command.BUY_BASKET_CMD.getCommand()}" method="post">
                            <button class="btn btn-lg btn-success" type="submit">
                                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                                <fmt:message key="session.label.buy"/>
                            </button>
                        </form>
                    </c:if>


                </div>
            </div>
        </div>

    </body>
</html>

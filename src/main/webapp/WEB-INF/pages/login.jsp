<html>
    <head>
        <title>Login</title>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <form action="${Command.AUTH_CMD.getCommand()}" method="post">
                        <div class="form-group">
                            <label><fmt:message key="login.label.username"/>:</label>
                            <input type="text" class="form-control" name="${Attribute.USERNAME_ATR.getAttribute()}"
                                   value="" required/>
                        </div>

                        <div class="form-group">
                            <label><fmt:message key="login.label.password"/>:</label>
                            <input type="password" class="form-control" name="${Attribute.PASSWORD_ATR.getAttribute()}"
                                   value="" required/>
                        </div>

                        <input type="submit" class="btn btn-success" value=<fmt:message key="login.label.submitButton"/>>
                    </form>

                    <form action="${Command.REG_PAGE_CMD.getCommand()}" method="post">
                        <input type="submit" class="btn btn-primary" value=<fmt:message key="login.label.registrationButton"/>>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>

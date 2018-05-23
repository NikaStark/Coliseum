<html>
    <head>
        <title>Registration</title>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form class="form-signin-reg" action="${Command.CREATE_USER_CMD.getCommand()}" method="post">
                        <label><fmt:message key="registration.label.username"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.USERNAME_ATR.getAttribute()}"
                               required/>

                        <label><fmt:message key="registration.label.password"/>:</label>
                        <input type="password" class="form-control" name="${Attribute.PASSWORD_ATR.getAttribute()}"
                               required/>

                        <label><fmt:message key="registration.label.passwordRepeat"/>:</label>
                        <input type="password" class="form-control"
                               name="${Attribute.PASSWORD_REPEAT_ATR.getAttribute()}" required/>

                        <label><fmt:message key="registration.label.email"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.EMAIL_ATR.getAttribute()}" required/>

                        <label><fmt:message key="registration.label.firstName"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.FIRST_NAME_ATR.getAttribute()}"
                               required/>

                        <label><fmt:message key="registration.label.lastName"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.LAST_NAME_ATR.getAttribute()}"
                               required/> <br/>

                        <input type="submit" class="btn btn-success" value=<fmt:message key="registration.label.submitButton"/>>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>

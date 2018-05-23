<html>
    <head>
        <title>Profile</title>
    </head>
    <body>

        <%@include file="templates/header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <h1><fmt:message key="profile.label.title"/></h1>

                    <h3><fmt:message key="profile.label.changeInformation"/></h3>

                    <form class="form-info" action="${Command.UPDATE_USER_INFO_CMD.getCommand()}" method="post">
                        <label><fmt:message key="profile.label.username"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.USERNAME_ATR.getAttribute()}"
                               value="<c:out value="${requestScope.get(Attribute.USERNAME_ATR.getAttribute())}"/>" required/>

                        <label><fmt:message key="profile.label.email"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.EMAIL_ATR.getAttribute()}"
                               value="<c:out value="${requestScope.get(Attribute.EMAIL_ATR.getAttribute())}"/>" required/>

                        <label><fmt:message key="profile.label.firstName"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.FIRST_NAME_ATR.getAttribute()}"
                               value="<c:out value="${requestScope.get(Attribute.FIRST_NAME_ATR.getAttribute())}"/>" required/>

                        <label><fmt:message key="profile.label.lastName"/>:</label>
                        <input type="text" class="form-control" name="${Attribute.LAST_NAME_ATR.getAttribute()}"
                               value="<c:out value="${requestScope.get(Attribute.LAST_NAME_ATR.getAttribute())}"/>" required/>

                        <input type="hidden" name="${Attribute.ID_ATR.getAttribute()}"
                               value="${requestScope.get(Attribute.ID_ATR.getAttribute())}"/>
                        <input type="submit" class="btn btn-success" value=<fmt:message key="profile.label.submitButton"/>>
                    </form>

                    <h3><fmt:message key="profile.label.changePassword"/></h3>

                    <form class="form-pass" action="${Command.UPDATE_USER_PASSWORD_CMD.getCommand()}" method="post">
                        <label><fmt:message key="profile.label.password"/>:</label>
                        <input type="password" class="form-control" name="${Attribute.PASSWORD_ATR.getAttribute()}"
                               required/>

                        <label><fmt:message key="profile.label.passwordRepeat"/>:</label>
                        <input type="password" class="form-control"
                               name="${Attribute.PASSWORD_REPEAT_ATR.getAttribute()}"
                               required/>

                        <input type="hidden" name="${Attribute.ID_ATR.getAttribute()}"
                               value="${requestScope.get(Attribute.ID_ATR.getAttribute())}"/>
                        <input type="submit" class="btn btn-success" value=<fmt:message key="profile.label.submitButton"/>>
                    </form>

                </div>
            </div>
        </div>

    </body>
</html>

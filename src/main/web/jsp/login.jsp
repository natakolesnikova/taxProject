<%--
  Created by IntelliJ IDEA.
  User: nata
  Date: 07.08.18
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Login Page</h2>
    <c:if test="${sessionScope.authenticationFailed}">
        <c:set var="authenticationFailed" value="false" scope="session"/>
    </c:if>
    <div class="form">
        <form action="${pageContext.request.contextPath}/controller" method="post">

            <input type="text" name="login" placeholder="Login" required autofocus/>

            <input type="password" name="password" placeholder="Password" required/>
            <input type="hidden" name="command" value="login"/>
            <input type="hidden" name="page" value="login.jsp"/>
            <button type="submit" >Sign In</button>
        </form>
    </div>

<%--    <style>
        .vertical-center {
            min-height: 100%;
            display: flex;
            align-items: center;
        }
    </style>


    <div class="vertical-center">
        <div class="container w-25">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <h1>Please sign in</h1>
                <div class="form-group">
                    <label for="inputLogin" class="sr-only">Login</label>
                    <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Login" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                    <input type="hidden" name="command" value="login"/>
                    <input type="hidden" name="page" value="/login.jsp"/>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
    </div>--%>
</body>
</html>

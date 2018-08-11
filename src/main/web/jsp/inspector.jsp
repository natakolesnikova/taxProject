<%--
  Created by IntelliJ IDEA.
  User: nata
  Date: 07.08.18
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="hidden" name="command" value="inspector"/>
    Hi inspector
    Welcome, ${sessionScope.inspector.workNumber}
    <h2>
        <a href="${pageContext.request.contextPath}/controller?command=inspector&page=inspector.jsp">
            Hi inspector
        </a>

    </h2>
</body>
</html>

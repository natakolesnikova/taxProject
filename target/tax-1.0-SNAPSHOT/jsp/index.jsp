<%--
  Created by IntelliJ IDEA.
  User: nata
  Date: 07.08.18
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Welcome</h2>


    <h3>What id </h3>

<%--    <c:if test="${sessionScope.size() > 0}">
        <c:if test="${sessionScope.user.userRoleId == 1}">

        </c:if>
        <c:if test="${sessionScope.user.userRoleId == 2}">
            <a href="${pageContext.request.contextPath}/jsp/taxPayer.jsp">Go to taxpayer cabinet</a>
        </c:if>
    </c:if>--%>
   <%-- <c:if test="${cookie.size() == 0}">--%>
<%--    <%if (session == null) {%>
    <jsp:forward page="login.jsp" />
    <%}%>--%>
   <%-- </c:if>--%>

    <c:if test="${not empty sessionScope.user}">
        <c:if test="${sessionScope.inspector}">
            <a href="${pageContext.request.contextPath}/jsp/inspector.jsp">TO inspector</a>
        </c:if>
        <c:if test="${sessionScope.taxPayer}">
            <a href="${pageContext.request.contextPath}/jsp/taxPayer.jsp">TO taxPayer</a>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">TO LOGIN</a>
    </c:if>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: lvlang
  Date: 2018/8/20
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Just a simple demo: SpringMVC + Mybatis</title>
</head>
<body>
<table>
    <thead>
    <tr class="table-info">
        <th width="25%">userID</th>
        <th>name</th>
        <th>address</th>
    </tr>
    </thead>
    <c:forEach items="${user}" var="user">
        <tr>
            <th>${user.id}</th>
            <th>${user.name}</th>
            <th>${user.address}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>

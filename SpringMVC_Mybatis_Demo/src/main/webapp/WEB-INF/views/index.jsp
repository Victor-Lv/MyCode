<%--
  Created by IntelliJ IDEA.
  User: lvlang
  Date: 2018/8/20
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Just a simple demo: SpringMVC + Mybatis</title>
</head>
<body>
<h1>Just a simple demo: SpringMVC + Mybatis</h1>

<form action="/selectAllUser" method="post">
    <div>
        <span>Search all user:</span>
        <input type="submit" id="selectAllUser" value="search" />
    </div>
</form>
<form action="/selectUserID" method="post">
    <div>
        <span>Search user by user ID, please enter user ID:</span>
        <input type="text" name="userID">
        <input type="submit" value="search" />
    </div>
</form>
<form action="/insertUser" method="post">
    <div>
        <span>Insert user, please enter user name:</span>
        <input type="text" name="insertUserName" />
        <span>user address:</span>
        <input type="text" name="insertUserAddress" />
        <input type="submit" id="insert" value="insert" />
    </div>
</form>
<form action="/updateUser" method="post">
    <div>
        <span>Update user, please enter user ID:</span>
        <input type="text" name="updateUserID" />
        <span>user name:</span>
        <input type="text" name="updateUserName" />
        <span>user address:</span>
        <input type="text" name="updateUserAddress" />
        <input type="submit" id="updateUser" value="update" />
    </div>
</form>
<form action="/deleteUser" method="post">
    <div>
        <span>Delete user by ID, please enter user ID:</span>
        <input type="text" name="deleteUserID"/>
        <input type="submit" id="deleteUser" value="delete" />
    </div>
</form>

</body>
</html>

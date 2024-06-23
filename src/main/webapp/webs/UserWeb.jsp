<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 25/03/2024
  Time: 3:26 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<a  class="btn btn-primary" href="/home/home-page">Trang chủ</a>
<div class="container">
    <div class="row">
        <h3>Quản lý người dùng</h3>
        <c:url var="url" value="/user"></c:url>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Passwrod</th>
                <th scope="col">Email</th>
                <th scope="col">FullName</th>
                <th scope="col">Admin</th>
                <th scope="col">Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${Item}">
                <tr>
                    <td>${item.getId()}</td>
                    <td>${item.getPassword()}</td>
                    <td>${item.getEmail()}</td>
                    <td>${item.getFullname()}</td>
                    <td>${item.getAdmin() == 1?"Admin":"User"}</td>
                    <td>
                        <a href="${url}/update/${item.getId()}" class="btn btn-success">Update</a>
<%--                        <a href="${url}/delete/${item.getId()}" class="btn btn-danger">Xóa</a>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <a href="${url}/add" class="btn btn-success">Add user</a>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 25/03/2024
  Time: 3:26 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<a  class="btn btn-primary" href="/home/home-page">Trang chủ</a>
<div class="container">
    <div class="row">
        <h3>Quản lý Video</h3>
        <c:url var="url" value="/video"></c:url>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Img</th>
                <th scope="col">Mã Video</th>
                <th scope="col">DanhMuc</th>
                <th scope="col">Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${Item}">
                <c:set var="myValue" value="${myMap[item.getId()]}" />
                <tr>
                    <td>${item.getId()}</td>
                    <td>${item.getTitle()}</td>
                    <td>${item.getDescription()}</td>
                    <td><img width="400px" height="223px" src="data:image/jpeg;base64,${myValue}" alt="Image"></td>
                    <td>${item.getMaVd()}</td>
                    <td>${item.getDanhMuc().getName()}</td>
                    <td>
                        <a href="${url}/update/${item.getId()}" class="btn btn-success">Update</a>
<%--                        <a href="${url}/delete/${item.getId()}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>--%>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="row">
        <a href="${url}/add" class="btn btn-success">Add Video</a>
    </div>
</div>
</body>
</html>

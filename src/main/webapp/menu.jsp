<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 07/04/2024
  Time: 10:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<a href="/logout" class="btn btn danger">Đăng xuất</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Trang</th>
        <th scope="col">Chuyển trang</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Xem Video</th>
        <td><a href="home/home-page">Xem Video</a></td>
    </tr>
    <c:if  test="${checkAdmin == true}">
        <tr>
            <th scope="row">QLVideo</th>
            <td><a href="video/hien-thi">QLVideo</a></td>
        </tr>
        <tr>
            <th scope="row">QLUser</th>
            <td><a href="user/hien-thi">QLUser</a></td>
        </tr>
        <tr>
            <th scope="row">QlDanhMuc</th>
            <td><a href="DanhMuc/home">QlDanhMuc</a></td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 25/03/2024
  Time: 3:45 SA
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
<div class="container">
    <div class="row">
        <h3>User Detail</h3>
        <% if (request.getParameter("error") != null && request.getParameter("error").equals("emptyField")) { %>
        <div class="alert alert-danger" role="alert">
            Tất cả các trường là bắt buộc!
        </div>
        <% } else if (request.getParameter("error") != null && request.getParameter("error").equals("invalidPassword")) { %>
        <div class="alert alert-danger" role="alert">
            Mật khẩu phải dài ít nhất 6 ký tự và chứa ít nhất một chữ số và một chữ cái.
        </div>
        <% } %>
        <c:url var="url" value="/user"></c:url>
        <form action="${url}/hien-thi" method="post">
            <div class="mb-3">
                <label class="form-label">ID:</label>
                <input type="text" class="form-control" name="id" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" class="form-control" name="Password">
            </div>
            <div class="mb-3">
                <label class="form-label">Email:</label>
                <input type="text" class="form-control" name="email">
            </div>
            <div class="mb-3">
                <label class="form-label">Fullname:</label>
                <input type="text" class="form-control" name="fullname">
            </div>
            <div class="mb-3">
                <label class="form-label">Admin:</label>
                <input type="checkbox" class="form-check-input" name="admin" value="1">
            </div>
            <div class="mb-3">
                <button formaction="${url}/add" class="btn btn-primary">ADD</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>

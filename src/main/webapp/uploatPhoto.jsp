<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 04/04/2024
  Time: 11:10 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 10px;">

    <div class="row"
         style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">

        <div class="col-sm-12">

            <h2 class="myclass">Upload Photo</h2>
            <c:url var="url" value="/video"></c:url>
            <h2>${message}</h2>
            <form action="${url}/add" method="post" enctype="multipart/form-data">
                <div class="form-group">


                    <label>ID</label>

                    <input type="text" class="form-control" name="id"  readonly>

                </div>
                <div class="form-group">
                    <label>Mã Video</label>
                    <input type="text" class="form-control" name="maVd" >

                </div>

                <div class="form-group">

                    <label>Title</label>

                    <input type="text" class="form-control" name="title" >

                </div>
                <div class="form-group">

                    <label>description</label>

                    <textarea class="form-control" rows="5" id="description" name="description"></textarea>

                </div>

                <div class="form-group">
                    <label>Photo</label>
                    <br />
                    <input type="file" class="form-control" name="img">

                </div>
                <div class="form-group">

                    <label>Danh mục</label>
                    <select class="form-control" name="danhMuc">
                        <c:forEach var="danhMuc" items="${DanhMuc}">
                            <option value="${danhMuc.id}">
                                    ${danhMuc.name}
                            </option>
                        </c:forEach>
                    </select>

                </div>
                <button type="submit" class="btn btn-primary">Save</button>

                <button type="reset" class="btn btn-primary">Cancel</button>

            </form>

        </div>
    </div>
</div>
</body>
</html>

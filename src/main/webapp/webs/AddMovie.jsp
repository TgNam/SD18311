<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 25/03/2024
  Time: 5:23 SA
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
          <h3>Movie Detail</h3>
          <c:url var="url" value="/movie"></c:url>
          <form action="${url}/hien-thi" method="post">
              <div class="mb-3">
                  <label class="form-label">ID:</label>
                  <input type="text" class="form-control" name="id" value="${form.id}">
                  <c:if test="${checkform==false}">
                      <!-- Hiển thị thông báo lỗi nếu ID trống -->
                      <c:if test="${check.id == null || check.id.trim().isEmpty()}">
                          <div class="alert alert-danger" role="alert">
                              ID is required!
                          </div>
                      </c:if>
                  </c:if>
              </div>
              <div class="mb-3">
                  <label class="form-label">Title:</label>
                  <input type="text" class="form-control" name="title" value="${form.title}">
                  <c:if test="${checkform==false}">
                      <!-- Hiển thị thông báo lỗi nếu title trống -->
                      <c:if test="${check.title == null || check.title.trim().isEmpty()}">
                          <div class="alert alert-danger" role="alert">
                              Title is required!
                          </div>
                      </c:if>
                  </c:if>
              </div>
              <div class="mb-3">
                  <label class="form-label">Description:</label>
                  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description" value="${form.description}"></textarea>
              </div>
              <div class="mb-3">
                  <label class="form-label">Active:</label>
                  <input type="checkbox" class="form-check-input" name="active" ${form.active== true?"checked":""} value="true">
              </div>
              <div class="mb-3">
                  <label class="form-label">Poster:</label>
                  <input type="text" class="form-control" name="poster" value="${form.poster}">
                  <c:if test="${checkform==false}">
                      <!-- Hiển thị thông báo lỗi nếu title trống -->
                      <c:if test="${check.poster == null || check.poster.trim().isEmpty()}">
                          <div class="alert alert-danger" role="alert">
                              Poster is required!
                          </div>
                      </c:if>
                  </c:if>
              </div>

              <div class="mb-3">
                  <button formaction="${url}/add" class="btn btn-primary">ADD</button>
              </div>
          </form>
      </div>
  </div>
  </body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Thêm Sản Phẩm</title></head>
<body>
<h2>Thêm Sản Phẩm</h2>
<form:form method="post" modelAttribute="productBT02" action="/addProductCookie">
  <p>Tên sản phẩm: <form:input path="name"/></p>
  <p>Giá: <form:input path="price" type="number" step="0.01"/></p>
  <p><input type="submit" value="Thêm sản phẩm"/></p>
</form:form>
<a href="/product_listBT02">Xem danh sách</a>
</body>
</html>

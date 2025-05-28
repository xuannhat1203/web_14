<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh Sách Sản Phẩm</title></head>
<body>
<h2>Danh sách sản phẩm đã thêm:</h2>
<c:choose>
  <c:when test="${not empty products}">
    <c:forEach items="${products}" var="p">
      <p>
          ${p.name} - ${p.price} VNĐ
        <a href="/deleteProduct?name=${p.name}">[Xoá]</a>
      </p>
    </c:forEach>
  </c:when>
  <c:otherwise>
    <p>Chưa có sản phẩm nào.</p>
  </c:otherwise>
</c:choose>

<a href="/addProductCookie">Thêm sản phẩm mới</a>
</body>
</html>

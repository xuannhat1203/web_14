<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách đơn hàng</title></head>
<body>
<h2>Danh Sách Đơn Hàng</h2>
<a href="${pageContext.request.contextPath}/orders/add">Thêm mới</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Mã đơn hàng</th>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.productName}</td>
            <td>${order.quantity}</td>
            <td>
                <a href="edit?orderId=${order.orderId}">Sửa</a>
                <a href="delete?orderId=${order.orderId}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

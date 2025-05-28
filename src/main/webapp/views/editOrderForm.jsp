<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head><title>Sửa đơn hàng</title></head>
<body>
<h2>Sửa Đơn Hàng</h2>

<form:form method="post" action="edit" modelAttribute="order">
    Mã đơn hàng: <form:input path="orderId" readonly="true"/><br/>
    Tên sản phẩm: <form:input path="productName" required="true"/><br/>
    Số lượng: <form:input path="quantity" type="number" required="true"/><br/>
    <input type="submit" value="Cập nhật"/>
</form:form>

<a href="orderList">Quay lại danh sách</a>
</body>
</html>


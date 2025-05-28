<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Thêm đơn hàng</title></head>
<body>
<h2>Thêm Đơn Hàng</h2>

<form:form method="post" action="orderProduct" modelAttribute="order">
    Mã đơn hàng: <form:input path="orderId" required="true"/><br/>
    Tên sản phẩm: <form:input path="productName" required="true"/><br/>
    Số lượng: <form:input path="quantity" type="number" required="true"/><br/>
    <input type="submit" value="Thêm"/>
</form:form>

<a href="orderList">Quay lại danh sách</a>
</body>
</html>


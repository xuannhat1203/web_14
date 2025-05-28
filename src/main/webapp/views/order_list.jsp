<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn đặt hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
            color: #333;
        }

        h2 {
            text-align: center;
            color: #0056b3;
            margin-bottom: 30px;
        }

        .message {
            text-align: center;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-weight: bold;
        }

        .success-message {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .order-container {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin-bottom: 25px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .order-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 1px dashed #eee;
        }

        .order-id {
            font-size: 1.2em;
            font-weight: bold;
            color: #007bff;
        }

        .order-date {
            font-size: 0.9em;
            color: #666;
        }

        .order-products table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .order-products th, .order-products td {
            border: 1px solid #eee;
            padding: 10px;
            text-align: left;
        }

        .order-products th {
            background-color: #f9f9f9;
        }

        .order-total {
            text-align: right;
            font-size: 1.3em;
            font-weight: bold;
            margin-top: 20px;
            color: #e44d26;
        }

        .back-button-container {
            text-align: center;
            margin-top: 30px;
        }

        .back-button {
            background-color: #6c757d;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
        }

        .back-button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<h2>Lịch sử đơn đặt hàng của bạn</h2>

<c:if test="${not empty successMessage}">
    <div class="message success-message">
            ${successMessage}
    </div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="message error-message">
            ${errorMessage}
    </div>
</c:if>

<c:choose>
    <c:when test="${empty allPlacedOrders}">
        <p style="text-align: center; font-size: 1.1em; color: #555;">Bạn chưa có đơn đặt hàng nào trong lịch sử.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="orderProducts" items="${allPlacedOrders}" varStatus="orderStatus">
            <div class="order-container">
                <div class="order-header">
                    <span class="order-id">Đơn hàng #${orderStatus.index + 1}</span>
                </div>
                <div class="order-products">
                    <table>
                        <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="totalAmount" value="0"/>
                        <c:forEach var="product" items="${orderProducts}">
                            <tr>
                                <td>${product.productName}</td>
                                <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VNĐ"/></td>
                                <td>${product.quantity}</td>
                                <td><fmt:formatNumber value="${product.price * product.quantity}" type="currency" currencySymbol="VNĐ"/></td>
                            </tr>
                            <c:set var="totalAmount" value="${totalAmount + (product.price * product.quantity)}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="order-total">
                    Tổng cộng: <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="VNĐ"/>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<div class="back-button-container">
    <a href="product_list" class="back-button">Tiếp tục mua sắm</a>
</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
        }

        .order-button-container {
            text-align: center; /* Center the button */
            margin-top: 30px; /* Add some space above the button */
        }

        .order-button {
            background-color: #4CAF50; /* Green background */
            color: white; /* White text */
            padding: 15px 30px; /* Some padding */
            border: none; /* No border */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Change cursor on hover */
            font-size: 18px; /* Larger font size */
            text-decoration: none; /* Remove underline for anchor tag */
            display: inline-block; /* Allow padding and margin */
        }

        .order-button:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
</head>
<body>
<h2>Danh sách sản phẩm</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Số lượng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${listProductCart}">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="order-button-container">
    <a href="submitOrder" class="order-button">Đặt hàng</a>
</div>
</body>
</html>
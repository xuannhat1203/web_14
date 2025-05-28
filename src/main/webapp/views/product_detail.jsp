<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }

        .product-detail {
            max-width: 500px;
            width: 100%;
            margin: auto;
            background: #fff;
            border: 1px solid #ddd;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        .field {
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
        }

        .field label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        .field span {
            color: #222;
        }

        input[type="number"] {
            padding: 8px 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            transition: border 0.3s;
        }

        input[type="number"]:focus {
            border-color: #3498db;
            outline: none;
        }

        .submit-btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            transition: background 0.3s;
        }

        .submit-btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<div class="product-detail">
    <h2>Chi tiết sản phẩm</h2>

    <div class="field">
        <label>ID:</label>
        <span>${product.id}</span>
    </div>

    <div class="field">
        <label>Tên sản phẩm:</label>
        <span>${product.productName}</span>
    </div>

    <div class="field">
        <label>Giá:</label>
        <span>${product.price} VNĐ</span>
    </div>
    <form:form method="post" modelAttribute="product" action="/addToCart">
        <div class="field">
            <label for="quantity">Số lượng:</label>
            <form:input path="quantity" id="quantity" type="number" min="1" value="1" />
        </div>
        <input type="hidden" name="id" value="${product.id}" />
        <button type="submit" class="submit-btn">Thêm sản phẩm vào giỏ hàng</button>
    </form:form>
</div>
</body>
</html>

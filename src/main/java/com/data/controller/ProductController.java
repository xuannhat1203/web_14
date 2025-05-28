package com.data.controller;

import com.data.model.Product;
// Không cần import Order class nếu bạn chỉ lưu danh sách Product vào carts
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger; // Giữ lại nếu bạn vẫn muốn có ID cho đơn hàng (tùy chọn)

@Controller
public class ProductController {
    private final List<Product> products = new ArrayList<Product>();
    public ProductController() {
        products.add(new Product(1, "Iphone 14 Pro Max", 3000,10));
        products.add(new Product(2, "Samsung Galaxy S23 Ultra", 2500,10));
        products.add(new Product(3, "Google Pixel 7 Pro", 2000,10));
        products.add(new Product(4, "OnePlus 11", 1800,10));
        products.add(new Product(5, "Xiaomi 13 Pro", 1500,10));
    }

    @GetMapping("product_list")
    public String productList(Model model) {
        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("showDetail")
    public String showDetail(@RequestParam("id") int id, Model model) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        if (product != null) {
            model.addAttribute("product", product);
            return "product_detail";
        } else {
            model.addAttribute("errorMessage", "Product not found");
            return "product_list";
        }
    }

    @PostMapping("addToCart")
    public String addToCart(@ModelAttribute("product") Product product,
                            HttpSession session, Model model) {
        List<Product> listProductCart = (List<Product>) session.getAttribute("listProductCart");
        if (listProductCart == null) {
            listProductCart = new ArrayList<>();
            session.setAttribute("listProductCart", listProductCart);
        }
        Product selectedProduct = null;
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                selectedProduct = new Product(p.getId(), p.getProductName(), p.getPrice(), product.getQuantity());
                break;
            }
        }

        if (selectedProduct != null) {
            boolean existsInCart = false;
            for (Product p : listProductCart) {
                if (p.getId() == selectedProduct.getId()) {
                    p.setQuantity(p.getQuantity() + selectedProduct.getQuantity());
                    existsInCart = true;
                    break;
                }
            }
            if (!existsInCart) {
                listProductCart.add(selectedProduct);
            }
            session.setAttribute("listProductCart", listProductCart);
            model.addAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        } else {
            model.addAttribute("errorMessage", "Sản phẩm không hợp lệ!");
        }

        return "redirect:/product_list";
    }

    @GetMapping("cart")
    public String cart(Model model, HttpSession session) {
        List<Product> listProductCart = (List<Product>) session.getAttribute("listProductCart");
        if (listProductCart == null || listProductCart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống.");
        } else {
            model.addAttribute("listProductCart", listProductCart);
        }
        return "product_cart";
    }

    @GetMapping("submitOrder")
    public String submitOrder(HttpSession session, Model model) {
        List<Product> currentCart = (List<Product>) session.getAttribute("listProductCart");

        if (currentCart == null || currentCart.isEmpty()) {
            model.addAttribute("errorMessage", "Giỏ hàng của bạn đang trống, không thể đặt hàng.");
            return "product_cart";
        }
        List<List<Product>> allPlacedOrders = (List<List<Product>>) session.getAttribute("carts");
        if (allPlacedOrders == null) {
            allPlacedOrders = new ArrayList<>();
            session.setAttribute("carts", allPlacedOrders);
        }
        List<Product> orderedProductsSnapshot = new ArrayList<>(currentCart);
        allPlacedOrders.add(orderedProductsSnapshot);
        session.setAttribute("carts", allPlacedOrders);
        session.removeAttribute("listProductCart");
        model.addAttribute("successMessage", "Đơn hàng của bạn đã được đặt thành công!");
        return "redirect:/order_list";
    }

    @GetMapping("order_list")
    public String showOrderList(Model model, HttpSession session) {
        List<List<Product>> allPlacedOrders = (List<List<Product>>) session.getAttribute("carts");
        if (allPlacedOrders == null || allPlacedOrders.isEmpty()) {
            model.addAttribute("errorMessage", "Bạn chưa có đơn đặt hàng nào.");
        } else {
            model.addAttribute("allPlacedOrders", allPlacedOrders);
        }
        return "order_list";
    }
}
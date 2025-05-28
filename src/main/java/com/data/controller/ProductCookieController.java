package com.data.controller;

import com.data.model.ProductBT02;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductCookieController {

    @GetMapping("addProductCookie")
    public String addProductCookie(Model model) {
        model.addAttribute("productBT02", new ProductBT02());
        return "product_form";
    }

    @PostMapping("addProductCookie")
    public String addProductCookiePost(@ModelAttribute ProductBT02 productBT02,
                                       @CookieValue(value = "productBT02", defaultValue = "") String productCookie,
                                       HttpServletResponse response) {
        List<ProductBT02> products = new ArrayList<>();
        if (!productCookie.isEmpty()) {
            String decoded = URLDecoder.decode(productCookie, StandardCharsets.UTF_8);
            String[] items = decoded.split("\\|");
            for (String item : items) {
                String[] parts = item.split(",");
                if (parts.length == 2) {
                    products.add(new ProductBT02(parts[0], Double.parseDouble(parts[1])));
                }
            }
        }
        products.add(productBT02);
        StringBuilder sb = new StringBuilder();
        for (ProductBT02 p : products) {
            if (sb.length() > 0) sb.append("|");
            sb.append(p.getName()).append(",").append(p.getPrice());
        }
        String encodedValue = URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);

        Cookie cookie = new Cookie("productBT02", encodedValue);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/product_listBT02";
    }

    @GetMapping("product_listBT02")
    public String productListBT02(@CookieValue(value = "productBT02", defaultValue = "") String productCookie,
                                  Model model) {
        List<ProductBT02> products = new ArrayList<>();

        if (!productCookie.isEmpty()) {
            String decoded = URLDecoder.decode(productCookie, StandardCharsets.UTF_8);
            String[] items = decoded.split("\\|");
            for (String item : items) {
                String[] parts = item.split(",");
                if (parts.length == 2) {
                    products.add(new ProductBT02(parts[0], Double.parseDouble(parts[1])));
                }
            }
        }

        model.addAttribute("products", products);
        return "product_listBT02";
    }

    @GetMapping("deleteProduct")
    public String deleteProduct(@RequestParam String name,
                                @CookieValue(value = "productBT02", defaultValue = "") String productCookie,
                                HttpServletResponse response) {
        List<ProductBT02> products = new ArrayList<>();

        if (!productCookie.isEmpty()) {
            String decoded = URLDecoder.decode(productCookie, StandardCharsets.UTF_8);
            String[] items = decoded.split("\\|");
            for (String item : items) {
                String[] parts = item.split(",");
                if (parts.length == 2 && !parts[0].equals(name)) {
                    products.add(new ProductBT02(parts[0], Double.parseDouble(parts[1])));
                }
            }
        }

        Cookie cookie;
        if (products.isEmpty()) {
            cookie = new Cookie("productBT02", "");
            cookie.setMaxAge(0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (ProductBT02 p : products) {
                if (sb.length() > 0) sb.append("|");
                sb.append(p.getName()).append(",").append(p.getPrice());
            }
            String encodedValue = URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);
            cookie = new Cookie("productBT02", encodedValue);
            cookie.setMaxAge(60 * 60 * 24);
        }
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/product_listBT02";
    }
}

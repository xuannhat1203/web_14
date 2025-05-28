package com.data.controller;

import com.data.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("orderProduct")
    public String orderProduct(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "Please login first");
            return "login";
        }
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("orderProduct")
    public String orderProductPost(@ModelAttribute("order") Order order, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "Please login first");
            return "login";
        }

        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
        }

        for (Order existing : orders) {
            if (existing.getOrderId().equals(order.getOrderId())) {
                model.addAttribute("order", order);
                return "orderForm";
            }
        }
        orders.add(order);
        session.setAttribute("orders", orders);
        model.addAttribute("orders", orders);
        return "orderList";
    }
    @GetMapping("edit")
    public String edit(@RequestParam("orderId") String id, Model model, HttpSession session) {
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        for (Order order : orders) {
            if (order.getOrderId().equals(id)) {
                model.addAttribute("order", order);
                return "editOrderForm";
            }
        }
        model.addAttribute("error", "Order not found");
        return "orderList";
    }
    @PostMapping("edit")
    public String editPost(@ModelAttribute("order") Order order, Model model, HttpSession session) {
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.set(i, order);
                session.setAttribute("orders", orders);
                model.addAttribute("orders", orders);
                return "orderList";
            }
        }
        return "orderList";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("orderId") String id, Model model, HttpSession session) {
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders != null) {
            orders.removeIf(order -> order.getOrderId().equals(id));
            session.setAttribute("orders", orders);
        }
        model.addAttribute("orders", orders);
        return "orderList";
    }
}

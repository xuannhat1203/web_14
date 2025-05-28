package com.data.controller;

import com.data.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        Login loginForm = new Login();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userlogin".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    String[] parts = value.split(":");
                    if (parts.length == 2) {
                        loginForm.setUsername(parts[0]);
                        loginForm.setPassword(parts[1]);
                    }
                    break;
                }
            }
        }

        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("loginForm") Login login,
                            @RequestParam(value = "rememberMe", required = false) String rememberMe,
                            Model model,
                            HttpSession session,
                            HttpServletResponse response) {
        if ("admin".equals(login.getUsername()) && "123".equals(login.getPassword())) {
            session.setAttribute("user", login);

            if (rememberMe != null) {
                String loginValue = login.getUsername() + ":" + login.getPassword();
                Cookie cookie = new Cookie("userlogin", loginValue);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("userlogin", null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            return "redirect:/orderProduct";
        } else {
            model.addAttribute("error", "Login failed, please try again");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

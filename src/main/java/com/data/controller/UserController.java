package com.data.controller;

import com.data.dto.UserForm;
import com.data.dto.UserValidator;
import com.data.model.User;
import com.data.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserController {
    public UserServiceImp userServiceImp = new UserServiceImp();
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("userForm") @Valid UserForm userForm,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        boolean isRegistered = userServiceImp.addUser(user);
        if (isRegistered) {
            model.addAttribute("message", "register.success");
        } else {
            model.addAttribute("error", "register.error");
            return "register";
        }
        return "register-success";
    }
}

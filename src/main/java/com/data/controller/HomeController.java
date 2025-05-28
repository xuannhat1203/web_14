package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale; // Đảm bảo import Locale

@Controller
public class HomeController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver; // Inject LocaleResolver

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
        Locale currentLocale = localeResolver.resolveLocale(request);
        model.addAttribute("title", messageSource.getMessage("app.title", null, currentLocale));
        model.addAttribute("message", messageSource.getMessage("app.greeting", null, currentLocale));
        model.addAttribute("selectLanguage", messageSource.getMessage("app.select.language", null, currentLocale));
        model.addAttribute("welcomeMessage", messageSource.getMessage("app.welcome", null, currentLocale));

        model.addAttribute("lang", currentLocale.getLanguage());

        return "home";
    }
}
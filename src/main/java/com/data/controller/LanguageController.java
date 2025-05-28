package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping("language")
public class LanguageController {
    @GetMapping
    public String languagePage(@CookieValue(value = "lang", defaultValue = "en") String lang, Model model) {
        Locale locale = new Locale(lang);

        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        String title = bundle.getString("title");
        String message = bundle.getString("message");
        String selectLanguage = bundle.getString("select_language");
        String welcomeMessage = bundle.getString("welcome.message");

        model.addAttribute("title", title);
        model.addAttribute("message", message);
        model.addAttribute("selectLanguage", selectLanguage);
        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("lang", lang);
        return "home";
    }
}
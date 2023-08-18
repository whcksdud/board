package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }
    @GetMapping("/home")
    public String home(Model model) {

        return "index";
    }
    @GetMapping("/feature")
    public String feature(Model model) {

        return "feature";
    }
    @GetMapping("/add")
    public String add(Model model) {

        return "add";
    }
    @GetMapping("/post")
    public String post(Model model) {

        return "post";
    }
    @GetMapping("/404")
    public String error(Model model) {

        return "404";
    }

}
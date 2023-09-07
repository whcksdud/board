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
    @GetMapping("/votepost")
    public String votepost(Model model) {

        return "votepost";
    }
    @GetMapping("/voteadd")
    public String voteadd(Model model) {

        return "voteadd";
    }
    @GetMapping("/votefeature")
    public String votefeature(Model model) {

        return "votefeature";
    }
    @GetMapping("/404")
    public String error(Model model) {

        return "404";
    }
    @GetMapping("/game")
    public String game(Model model) {

        return "game";
    }

}
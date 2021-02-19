package com.jumanji.capston.controller;

import com.jumanji.capston.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
//    @Autowired
//    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
}

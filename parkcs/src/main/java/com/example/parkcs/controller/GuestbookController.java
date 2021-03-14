package com.example.parkcs.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
@Log4j2
public class GuestbookController {

    @GetMapping("/ex1")
    public void ex1(){

        log.info("ex1...............");

    }


    @GetMapping("/ex3")
    public void ex3(){

        log.info("ex3");

    }


//    @GetMapping("/exLayout1")
//    public void exLayout1(){
//
//        log.info("exLayout............");
//    }

    @GetMapping({"/exLayout1","/exLayout2", "/exTemplate","/exSidebar"})
    public void exLayout1(){
        log.info("exLayout............");
    }


}
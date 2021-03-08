package com.example.demo.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
    private static final Logger log = LogManager.getLogger(GuestBookController.class);

    @GetMapping({"/", "/list"})
    public String list() {
        log.debug("list........");

        return "/guestbook/list";
    }

    @GetMapping("/exTemplate")
    public void exLayout1() {
        log.debug("exLayout1....");
    }
}

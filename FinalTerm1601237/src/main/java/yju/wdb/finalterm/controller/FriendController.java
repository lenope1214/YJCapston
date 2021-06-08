package yju.wdb.finalterm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friends")

public class FriendController {


    private static final Logger log = LogManager.getLogger(FriendController.class);

    @GetMapping({"/", "/list"})
    public String list() {
        log.info("list...");

        return "/friends/list";
    }
}

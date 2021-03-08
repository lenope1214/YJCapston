package org.zero.test2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zero.test2.dto.MemberDTO;
import org.zero.test2.service.MemberService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @GetMapping({"/", "/register"})
    public String register() {
        return "/register";
    }

    @PostMapping("/register")
    public String Register(MemberDTO dto) {
        service.register(dto);

        return "/register";
    }
}

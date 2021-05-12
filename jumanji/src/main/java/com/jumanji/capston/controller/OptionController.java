package com.jumanji.capston.controller;

import com.jumanji.capston.data.Option;
import com.jumanji.capston.service.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/")
public class OptionController {

    @Autowired
    private OptionServiceImpl optionService;

    @PostMapping("menus/options")
    public ResponseEntity postMenuOption(@RequestHeader String authorization, @RequestBody Option.Request request){
        Option option = optionService.post(authorization, request);
        Option.Response response = new Option.Response(option);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}

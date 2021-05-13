package com.jumanji.capston.controller;

import com.jumanji.capston.data.Option;
import com.jumanji.capston.data.OptionGroup;
import com.jumanji.capston.service.OptionGroupServiceImpl;
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
public class OptionGroupController {

    @Autowired
    private OptionGroupServiceImpl optionGroupService;

    @PostMapping("menus/options/groups")
    public ResponseEntity postMenuOption(@RequestHeader String authorization, @RequestBody OptionGroup.Request request){
        OptionGroup oGroup = optionGroupService.post(authorization, request);
        OptionGroup.Response response = new OptionGroup.Response(oGroup);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
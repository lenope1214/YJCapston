package com.jumanji.capston.controller;

import com.jumanji.capston.data.Option;
import com.jumanji.capston.service.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/")
public class OptionController {

    @Autowired
    private OptionServiceImpl optionService;

    @GetMapping("menus/options/{optionGroupId}")
    public ResponseEntity<?> getOptionList(@PathVariable String optionGroupId){
        List<Option> optionList = optionService.getList(null, optionGroupId);
        List<Option.Response> response = new ArrayList<>();
        for(Option option : optionList){
            response.add(new Option.Response(option));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("menus/options")
    public ResponseEntity<?> postMenuOption(@RequestHeader String authorization, @RequestBody Option.Request request){
        Option option = optionService.post(authorization, request);
        Option.Response response = new Option.Response(option);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}

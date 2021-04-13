package com.jumanji.capston.controller;

import com.jumanji.capston.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class TableController {
    @Autowired
    TableServiceImpl tableService;

}

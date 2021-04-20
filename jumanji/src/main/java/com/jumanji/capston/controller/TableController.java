package com.jumanji.capston.controller;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class TableController {
    @Autowired
    TableServiceImpl tableService;

    @GetMapping("/table/{tabId}")
    public ResponseEntity<?> getTabById(@PathVariable String tabId){
        return new ResponseEntity<>(tableService.get(tabId),HttpStatus.OK);
    }

    @PostMapping("/table")
    public ResponseEntity<?> postTable(@RequestHeader String authorization, @RequestBody Tab.Request request){
        return new ResponseEntity<>(tableService.post(authorization, request), HttpStatus.CREATED);
    }
}

package com.jumanji.capston.controller;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class TableController {
    @Autowired
    TableServiceImpl tableService;

    @GetMapping("tables/{tabId}")
    public ResponseEntity<?> getTabById(@PathVariable String tabId){
        Tab tab = tableService.get(tabId);
        Tab.Response response = new Tab.Response(tab);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("tablesList/{shopId}")
    public ResponseEntity<?> getTableListByShop(@PathVariable String shopId){
        List<Tab> tabList = tableService.getList(shopId);
        List<Tab.Response> response = new ArrayList<>();
        for(Tab tab : tabList){
            response.add(new Tab.Response(tab));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("tables")
    public ResponseEntity<?> postTable(@RequestHeader String authorization, @RequestBody Tab.Request request){
        Tab tab = tableService.post(authorization, request);
        Tab.Response response = new Tab.Response(tab);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("tables")
    public ResponseEntity<?> patchTable(@RequestHeader String authorization, @RequestBody Tab.Request request){
        return new ResponseEntity<>(tableService.patch(authorization, request), HttpStatus.OK);
    }

    @DeleteMapping("tables/{tabId}")
    public ResponseEntity<?> deleteTable(@RequestHeader String authorization, @PathVariable String tabId){
        tableService.delete(authorization, tabId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

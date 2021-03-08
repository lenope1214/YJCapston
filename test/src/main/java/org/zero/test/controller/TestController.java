package org.zero.test.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.zero.test.Service.TestService;
import org.zero.test.dto.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/query")
    public List<Test> query() throws Exception{
        return testService.getAll();
    }

    @GetMapping("/list")
    public void list() {
        log.info("dwa");
    }

}
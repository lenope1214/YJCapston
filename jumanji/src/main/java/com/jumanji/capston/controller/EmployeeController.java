package com.jumanji.capston.controller;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Pos;
import com.jumanji.capston.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    @Transactional(readOnly = true)
    @GetMapping("/shop/employee/{shopId}")
    public ResponseEntity<?> getShopPos(@RequestHeader String authorization, @PathVariable String shopId){
        List<Employee> employeeList = employeeService.getList(authorization, shopId);
        List<Employee.Response> response = new ArrayList<>();
        for(Employee e : employeeList){
            response.add(new Employee.Response(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PostMapping("/shop/employee")
    public ResponseEntity<?> getShopPos(@RequestHeader String authorization, @RequestBody Employee.Request request){
        Employee employee = employeeService.post(authorization, request);
        Employee.Response response = new Employee.Response(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.jumanji.capston.controller;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.EmployeeCommutes;
import com.jumanji.capston.data.Pos;
import com.jumanji.capston.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Query;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;


    @Transactional(readOnly = true)
    @GetMapping("shops/{shopId}/employees")
    public ResponseEntity<?> getShopEmployees(@RequestHeader String authorization, @PathVariable String shopId) {
        List<Employee> employeeList = employeeService.getList(authorization, shopId);
        List<Employee.Response> response = new ArrayList<>();
        for (Employee e : employeeList) {
            response.add(new Employee.Response(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("shops/{shopId}/employees/{empNo}")
    public ResponseEntity<?> getEmployeeInfo(@RequestHeader String authorization, @PathVariable String shopId, @PathVariable String empNo) {
        Employee employee = employeeService.get(authorization, shopId, empNo);
        Employee.Response response = new Employee.Response(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("shops/{shopId}/work-times")
    public ResponseEntity<?> getShopEmpWorkTimes(@RequestHeader String authorization, @PathVariable String shopId,
                                                 @Nullable @RequestParam String date, @Nullable @RequestParam String empNo) {
        List<Employee.Dao> employeeList = employeeService.getWorkTimes(authorization, shopId, date, empNo);
        List<Employee.Response> response = new ArrayList<>();
        for (Employee.Dao e : employeeList) {
            response.add(new Employee.Response(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("shops/employees")
    public ResponseEntity<?> getShopPos(@RequestHeader String authorization, @RequestBody Employee.Request request) {
        System.out.println("직원등록 입장");
        Employee employee = employeeService.post(authorization, request);
        Employee.Response response = new Employee.Response(employee);
        System.out.println("직원 등록 성공!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/shops/employees")
    public ResponseEntity<?> deleteEmployee(@RequestHeader String authorization,
                                            @RequestParam("empNo") int empNo,
                                            @RequestParam("shopId") String shopId) {
        employeeService.delete(authorization, shopId, String.valueOf(empNo));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PostMapping("/shops/employees/work-start")
    public ResponseEntity<?> workStart(@RequestHeader String authorization,
                                       @RequestBody EmployeeCommutes.Request request) {
        EmployeeCommutes employeeCommutes = employeeService.workStart(authorization, request);
        System.out.println("출근 empC tostring : " + employeeCommutes.getEmployee().getId());
        EmployeeCommutes.Response response = new EmployeeCommutes.Response(employeeCommutes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/shops/employees/work-finish")
    public ResponseEntity<?> workFinish(@RequestHeader String authorization,
                                        @RequestBody EmployeeCommutes.Request request) {
        EmployeeCommutes employeeCommutes = employeeService.workFinish(authorization, request);
        EmployeeCommutes.Response response = new EmployeeCommutes.Response(employeeCommutes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

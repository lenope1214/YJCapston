package com.jumanji.capston.service;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.repository.EmployeeRepository;
import com.jumanji.capston.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.graalvm.compiler.phases.common.DeadCodeEliminationPhase.Optionality.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    @Override
    public Employee get(String authorization, String empNo) {
        return null;
    }

    @Override
    public List<Employee> getList(String authorization, String shopId) {
        String loginId = userService.getMyId(authorization);

        return null;
    }

    @Override
    public Employee post(String authorization, Employee.Request request) {
        return null;
    }

    @Override
    public Employee patch(String authorization, Employee.Request request) {
        return null;
    }

    @Override
    public void delete(String authorization, String empNo) {

    }

    public Employee isPresent(String empId){
        Optional<Employee> employee = employeeRepository.findById(empId);
        if(employee.isPresent())return employee.get();
        else throw new
    }
}

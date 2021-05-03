package com.jumanji.capston.service;

import ch.qos.logback.classic.pattern.DateConverter;
import com.jumanji.capston.data.DateOperator;
import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Menu;
import com.jumanji.capston.repository.EmployeeRepository;
import com.jumanji.capston.service.exception.EmployeeException.EmployeeHasExistException;
import com.jumanji.capston.service.exception.EmployeeException.EmployeeNotFoundException;
import com.jumanji.capston.service.interfaces.EmployeeService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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

        // 유효성 검사
        userService.isLogin(authorization);
        shopService.isOwnShop(loginId, shopId);

        // 서비스
        List<Employee> employeeList = employeeRepository.findByShopId(shopId);
        
        return employeeList;
    }

    @Transactional // Transactional이 있는 save는 안된다. 그래서 saveAndFlush를 통해 함수 종료 후에 실행되도록 함.
    @Override
    public Employee post(String authorization, Employee.Request request) {
        String loginId = userService.getMyId(authorization);
        String empId = toEmpId(request.getShopId(), request.getEmpNo());

        // 유효성 검사
        if(request.getEmpNo() == 0)throw new CanNotBeZero();
        userService.isLogin(authorization);
        isEmpty(empId);
        
        // 서비스
        Employee employee = Employee.builder()
                .id(empId)
                .name(request.getEmpName())
                .birthday(request.getBirthday())
                .hiredate(request.getHiredate())
                .phone(request.getPhone())
                .gender(request.getGender())
                .build();
        System.out.println(employee.toString());
        Employee e = employeeRepository.saveAndFlush(employee);
        return e;
    }

    @Override
    public Employee patch(String authorization, Employee.Request request) {
        String loginId = userService.getMyId(authorization);
        String empId = toEmpId(request.getShopId(), request.getEmpNo());

        // 유효성 검사
        userService.isLogin(authorization);
        Employee employee = isPresent(empId);

        // 서비스
        employee.update(request);
        return employee;
    }

    @Override
    public void delete(String authorization, String empNo) {

    }

    private String toEmpId(String shopId, int empNo){
        return shopId + 'e' + String.format("%03d", empNo);
    }

    public Employee isPresent(String empId){
        Optional<Employee> employee = employeeRepository.findById(empId);
        if(employee.isPresent())return employee.get();
        else throw new EmployeeNotFoundException(empId.substring(11));
    }

    public boolean isEmpty(String empId){
        Optional<Employee> employee = employeeRepository.findById(empId);
        if(employee.isEmpty())return true;
        else throw new EmployeeHasExistException(empId.substring(11));
    }
}

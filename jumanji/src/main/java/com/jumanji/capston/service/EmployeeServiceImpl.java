package com.jumanji.capston.service;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.repository.EmployeeRepository;
import com.jumanji.capston.service.exception.CanNotBeZero;
import com.jumanji.capston.service.exception.employeeException.EmployeeHasExistException;
import com.jumanji.capston.service.exception.employeeException.EmployeeNotFoundException;
import com.jumanji.capston.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        List<Employee> employeeList = employeeRepository.findByShopId(shopId);
        List<Employee> employeeList = employeeRepository.findByIdStartsWith(shopId);
//        for(int i =0; i<employeeList.size();i++){
//            System.out.println("eL 0 : " + employeeList.get(i) +"\n" +
//                    "eL 1 : " + employeeList1.get(i));
//        }
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
    public void delete(String authorization, String shopId, int empNo) {
        String loginId = userService.getMyId(authorization);
        String empId = shopId + 'e' + String.format("%03d", empNo);
        // 유효성 체크
        userService.isPresent(loginId); // 로그인한 계정이 존재하는지
        shopService.isOwnShop(loginId, shopId); // 존재한다면 그 매장이 내 매장인지
        Employee e = isPresent(empId); // 존재하는 직원인지
        employeeRepository.delete(e);
        isEmpty(empId);
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

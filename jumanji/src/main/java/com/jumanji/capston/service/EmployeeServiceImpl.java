package com.jumanji.capston.service;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.EmployeeCommutes;
import com.jumanji.capston.repository.EmpCommutesRepository;
import com.jumanji.capston.repository.EmployeeRepository;
import com.jumanji.capston.service.exception.CanNotBeZero;
import com.jumanji.capston.service.exception.employeeException.EmployeeAlreadyStartException;
import com.jumanji.capston.service.exception.employeeException.EmployeeHasExistException;
import com.jumanji.capston.service.exception.employeeException.EmployeeNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements BasicService<Employee, Employee.Request> {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmpCommutesRepository empCommutesRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    @Override
    public Employee get(String authorization, String... str) {
        String shopId = str[0], empNo = str[1];
        String loginId = userService.getMyId(authorization);
        String empId = shopId + 'e' + empNo;
        Employee employee;
        // 유효성 검사
        userService.isLogin(authorization);
        shopService.isOwnShop(loginId, shopId);
        employee = isPresent(empId);

        return employee;
    }

    @Override
    public List<Employee> getList(@Nullable String authorization, String... str) {
        String shopId = str[0];
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

    @Override
    @Transactional // Transactional이 있는 save는 안된다. 그래서 saveAndFlush를 통해 함수 종료 후에 실행되도록 함.
    public Employee post(@Nullable String authorization, Employee.Request request)  {
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
    public void delete(@Nullable String authorization, String... str) {
        String loginId = userService.getMyId(authorization);
        String shopId = str[0], empNo = str[1];
        String empId = shopId + 'e' + empNo;
        // 유효성 체크
        userService.isPresent(loginId); // 로그인한 계정이 존재하는지
        shopService.isOwnShop(loginId, empId.substring(0, 10)); // 존재한다면 그 매장이 내 매장인지
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

    public EmployeeCommutes workStart(String authorization, EmployeeCommutes.Request request) {
        // 변수
        String loginId = userService.getMyId(authorization);
        String empId = request.getShopId() + 'e' + String.format("%03d", request.getEmpNo());
        String empCmtId = request.getShopId()+request.getEmpNo();
        int empWorkCount = 0;
        // 값 체크
        System.out.println("empId : " + empId);

        // 유효성 체크
        shopService.isOwnShop(loginId, request.getShopId());
        isPresent(empId);
        // 출근은 있고 퇴근이 없는 열이 있으면 출근중. 요청 취소.
        isStart(empCmtId);

        // 서비스
        empWorkCount = empCommutesRepository.countByIdStartsWith(empCmtId);
        empCmtId += String.format("%04d",empWorkCount);
        EmployeeCommutes empCommutes = EmployeeCommutes.builder()
                .id(empCmtId)
                .build();

        return empCommutesRepository.save(empCommutes);
    }

    private void isStart(String empCmtId) {
        if(empCommutesRepository.countByIdStartsWithAndFinishTimeIsNull(empCmtId) > 0)
            throw new EmployeeAlreadyStartException();
    }
}

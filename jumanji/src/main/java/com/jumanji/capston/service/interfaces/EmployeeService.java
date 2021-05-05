package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Menu;

import java.util.List;

public interface EmployeeService {
    public Employee get(String authorization, String empNo);
    public List<Employee> getList(String authorization,String shopId);
    public Employee post(String authorization, Employee.Request request);
    public Employee patch(String authorization, Employee.Request request);
    public void delete(String authorization, String shopId, int empNo);
}

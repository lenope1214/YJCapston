package com.jumanji.capston.repository;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "select * from employees where id like :shopId%",
            nativeQuery = true)
    List<Employee> findByShopId(String shopId);

    List<Employee> findByIdStartsWith(String shopId);

//    @Query(value = "select\n" +
//            "       e.ID empId, e.NAME empName, ec.START_TIME startTime, ec.FINISH_TIME finishTime, e.phone empPhone\n" +
//            "from EMPLOYEES e\n" +
//            " left join EMP_COMMUTES ec\n" +
//            "    on e.ID = SUBSTR(ec.id, 0, 10) || 'e'||substr(ec.id, 11, 3)\n" +
//            "where\n" +
//            "    to_char(START_TIME, 'yyyy-MM-dd') =  decode(:date, null, to_char(sysdate, 'yyyy-MM-dd'), :date)\n" +
//            "or\n" +
//            "    e.id = decode(:empId, null, e.id, :empId)", nativeQuery = true)
//    List<Employee.Dao> findByDateOrEmpId(String empId, String date);


    @Query(value = "select\n" +
            "    e.ID           empId,\n" +
            "    e.NAME         empName,\n" +
            "    ec.START_TIME  startTime,\n" +
            "    ec.FINISH_TIME finishTime,\n" +
            "    e.phone        empPhone,\n" +
            "    to_char(START_TIME, 'yyyy-MM-dd') dates\n" +
            "    from EMPLOYEES e\n" +
            "    left join EMP_COMMUTES ec\n" +
            "    on e.ID = SUBSTR(ec.id, 0, 10) || 'e' || substr(ec.id, 11, 3)\n" +
            "    where\n" +
            "    to_char(START_TIME, 'yyyy-MM-dd') = decode(:date, null, to_char(START_TIME, 'yyyy-MM-dd'), :date)\n" +
            "    and e.id = decode(:empId, null, e.id, :empId)", nativeQuery = true)
    List<Employee.Dao> findByDateOrEmpId(String empId, String date);


    @Query(value = "", nativeQuery = true)
    List<Employee.Dao> findByBetweenDateOrEmpId(String empId, String date);

}
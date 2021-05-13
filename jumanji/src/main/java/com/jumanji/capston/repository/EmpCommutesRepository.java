package com.jumanji.capston.repository;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.EmployeeCommutes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpCommutesRepository extends JpaRepository<EmployeeCommutes, String> {

//    @Query(value = "", nativeQuery = true )//TODO query문 만들어야 함.
//    List<EmployeeCommutes> findByShopIdAndEmpNo(String shopId, String empNo);

    /**
     *
     * @param id shopId + EmpNo
     * @return 해당 직원의 출퇴근 회수 출력
     */
    int countByIdStartsWith(String id);

    /**
     *
     * @param id shopId + EmpNo
     * @return 해당 직원의 출퇴근 회수 출력
     */
    int countByIdStartsWithAndFinishTimeIsNull(String id);
}

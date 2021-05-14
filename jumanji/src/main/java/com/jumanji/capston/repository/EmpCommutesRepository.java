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

    @Query(value = "select ec.*\n" +
            "from EMP_COMMUTES ec\n" +
            "where id = (SELECT max(ec.ID)\n" +
            "    KEEP(DENSE_RANK FIRST ORDER BY START_TIME DESC) id FROM EMP_COMMUTES ec\n" +
            "where substr(ec.id, 0, 13) = :id)", nativeQuery = true)
    EmployeeCommutes findByShopIdAndEmpNo(String id);
}

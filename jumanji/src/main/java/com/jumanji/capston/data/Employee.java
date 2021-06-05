package com.jumanji.capston.data;

import lombok.*;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name="employees")
@Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class Employee {
    @Id
    private String id; //리뷰번호 식당번호(10) + 'e' + 직원번호(3)

    @Column(length = 15, updatable = false)
    private String name; // 이름
    @Column(updatable = false)
    private Date birthday; // 생년월일
    private Date hiredate; // 고용일
    @Column(updatable = false)
    private char gender; // 성별
    @Column(length = 11)
    private String phone; // 전화번호


    public void update(Request request){
        if (request.getEmpNo() != 0)id = id.substring(0,11) + String.format("%03d", request.getEmpNo());
        if(request.getHiredate()!=null)hiredate = request.getHiredate();
        if(request.getPhone() != null)phone = request.getPhone();
    }

    /**
     * empId : shopId(10) + 'e' + empNo(3) 14자리
     */
    @NoArgsConstructor @Getter
    public static class Request{
        private String shopId;
        private String empName;
        private int empNo;
        private Date birthday; // yyyy/MM/dd
        private Date hiredate; // yyyy/MM/dd
        private char gender; // 성별
        private String phone; // 전화번호
    }

    @AllArgsConstructor @Getter
    public static class Response{
        private String shopId;
        private int empNo;
        private String empName;
        private String birthday; // yyyyMMdd
        private String hiredate; // yyyyMMdd
        private char gender = ' '; // 성별
        private String phone; // 전화번호
        @Nullable
        private String startTime;
        @Nullable
        private String finishTime;

        public Response(Employee employee){
            this.shopId = employee.getId().substring(0, 10);
            this.empNo = employee.parseEmpNo(employee.getId());
            this.empName = employee.getName();
            this.birthday = DateOperator.dateToYYYYMMDD(employee.getBirthday(), true);
            this.hiredate = DateOperator.dateToYYYYMMDD(employee.getHiredate(), true);
            this.gender = employee.getGender();
            this.phone = employee.getPhone();
        }

        public Response(Employee.Dao dao){
            this.shopId = dao.getEmpId().substring(0, 10);
            this.empNo = Integer.parseInt(dao.getEmpId().substring(11, 14));
            this.empName = dao.getEmpName();
            this.startTime = dao.getStartTime();
            this.finishTime = dao.getFinishTime();
            this.phone = dao.getEmpPhone();
        }

//        public Response(Employee.Dao dao, List<EmployeeCommutes> ecList){
//            this.shopId = dao.getEmpId().substring(0, 10);
//            this.empNo = Integer.parseInt(dao.getEmpId().substring(11, 14));
//            this.empName = dao.getEmpName();
//            this.startTime = dao.getStartTime();
//            this.finishTime = dao.getFinishTime();
//            this.phone = dao.getEmpPhone();
//            this.workTimes = ecList;
//        }
    }

    public int parseEmpNo(String empId){
        return Integer.parseInt(empId.substring(11, 14));
    }

    public interface Dao{
        String getEmpId();
        String getEmpName();
        String getEmpPhone();
        String getStartTime();
        String getFinishTime();
    }
}

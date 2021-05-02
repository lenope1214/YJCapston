package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;
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
        if(request.getHiredate()!=null)hiredate = DateOperator.strToDate(request.getHiredate());
        if(request.getPhone() != null)phone = request.getPhone();
    }

    @NoArgsConstructor @Getter
    public static class Request{
        private String shopId;
        private String empName;
        private int empNo;
        private String birthday; // yyyyMMdd
        private String hiredate; // yyyyMMdd
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
        private char gender; // 성별
        private String phone; // 전화번호

        public Response(Employee employee){
            this.shopId = employee.getId().substring(0, 10);
            this.empNo = employee.parseEmpNo(employee.getId());
            this.empName = employee.getName();
            this.birthday = DateOperator.dateToYYYYMMDD(employee.getBirthday());
            this.hiredate = DateOperator.dateToYYYYMMDD(employee.getHiredate());
            this.gender = employee.getGender();
            this.phone = employee.getPhone();
        }
    }

    public int parseEmpNo(String empId){
        return Integer.parseInt(empId.substring(11, 14));
    }

}

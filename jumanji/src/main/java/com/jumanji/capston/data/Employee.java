package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Entity
@Table(name="employees")
public class Employee {
    @Id
    private String id; //리뷰번호 식당번호(10) + 'e' + 직원번호(3)

    @Column(length = 15)
    private String name; // 이름
    private Date birthday; // 생년월일
    private Date hiredate; // 고용일
    private char gender; // 성별
    @Column(length = 11)
    private String phone; // 전화번호

    @NoArgsConstructor @AllArgsConstructor @Getter
    public static class Request{
        private String shopId;
        private String empName;
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
    }

}

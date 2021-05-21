package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Entity(name = "EMP_COMMUTES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class EmployeeCommutes {
    @Id
    @Column(length = 17)
    @JsonIgnore
    private String id;

    @Column
    private Date startTime;

    private Date finishTime;

    @Transient
    @JoinColumn
    @ManyToOne
    @Nullable
    @JsonIgnore
    private Employee employee;

    public EmployeeCommutes(String id) {
        this.id = id;
    }

    public void finish(Employee e, Date finishTime) {
        this.employee = e;
        this.finishTime = finishTime;
    }

    @NoArgsConstructor
    @Getter
    public static class Request {
        private String shopId;
        private int empNo;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private String shopId;
        private String empNo;
        private String counts;
        private String empName;
        private String startTime;
        private String finishTime;

        public Response(EmployeeCommutes empCommutes) {
            this.counts = new DecimalFormat("0").format(Integer.valueOf(empCommutes.id.substring(14)));
            this.startTime = DateOperator.dateToYYYYMMDDHHMMSS(empCommutes.getStartTime());
            this.finishTime = DateOperator.dateToYYYYMMDDHHMMSS(empCommutes.getFinishTime());
            this.shopId = empCommutes.getEmployee().getId().substring(0, 10);
            this.empName = empCommutes.getEmployee().getName();
            this.empNo = new DecimalFormat("0").format(Integer.valueOf(empCommutes.getEmployee().getId().substring(11)));
        }
    }
}

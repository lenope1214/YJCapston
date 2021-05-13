package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "EMP_COMMUTES")
@Builder @AllArgsConstructor
@NoArgsConstructor @Getter
public class EmployeeCommutes {
    @Id
    @Column(length = 17)
    private String id;

    @Column
    private Date startTime = new Date();

    private Date finishTime;

    public EmployeeCommutes(String id){
        this.id = id;
    }

    @NoArgsConstructor @Getter
    public static class Request{
        private String shopId;
        private int empNo;
    }

    @AllArgsConstructor @Getter
    public static class Response{
        private String shopId;
        private String empNo;
        private String empName;
        private String startTime;
        private String finishTime;

        public Response(EmployeeCommutes empCommutes){
            this.startTime = DateOperator.dateToYYYYMMDDHHMMSS(empCommutes.getStartTime());
            this.finishTime = DateOperator.dateToYYYYMMDDHHMMSS(empCommutes.getFinishTime());
        }
    }
}

package com.example.jmjapp.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tables {
    private String tabId;
    private String shopId;
    private String no;
    private int seatQty;
    private char using;
    private String qrCode;
    private String orderId;

    public Tables(String tabId, String no) {
        this.tabId = tabId;
        this.no = no;
    }

    public Tables(String no, char using) {
        this.no = no;
        this.using = using;
    }

}

package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class StompMessage {
    private Integer shopId;
    private String type;
    private String roomId;
    private String username;
    private String message;
    private String orderList;
    private String orderId;
    private String orderNumber;
}
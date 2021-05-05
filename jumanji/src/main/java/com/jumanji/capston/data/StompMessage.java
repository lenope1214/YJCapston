package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StompMessage {
    private Integer shopId;
    private String type;
    private String username;
    private String message;
}

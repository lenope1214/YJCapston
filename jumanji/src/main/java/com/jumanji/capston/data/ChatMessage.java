package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage {
    private Integer shopId;
    private String type;
    private String username;
    private String message;
}

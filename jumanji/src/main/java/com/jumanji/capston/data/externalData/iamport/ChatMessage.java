package com.jumanji.capston.data.externalData.iamport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage {
    private Integer roomSeq;
    private String message;
}

package com.example.jmjapplication2.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Validate {
    private String userid;
    private boolean result;

    public Boolean getResult() {
        return result;
    }
}

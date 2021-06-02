package com.example.jmjapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mark {
    private String userId;
    private String shopId;

    @Data
    public static class MarkList{
        private List<Shop> shopList;
    }
}

package com.jumanji.capston.data;

import lombok.*;

import java.util.List;

@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Pos {
    private String tabNo;
    private String orderRequest;
    private List<OrderMenu> orderMenuList;
    private int people;
}

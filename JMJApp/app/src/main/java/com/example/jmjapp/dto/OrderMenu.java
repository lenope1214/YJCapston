package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenu {
    private String orderId;
    private String shopId;
    private String menuId;
    private String quantity;
    private int tabNo;

    public OrderMenu(String orderId, String shopId, String menuId, String quantity) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.menuId = menuId;
        this.quantity = quantity;
    }
//    private List<Option> optionList;
}

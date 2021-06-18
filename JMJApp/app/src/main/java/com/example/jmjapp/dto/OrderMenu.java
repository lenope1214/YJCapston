package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenu {
    private String orderMenuId;
    private String shopId;
    private String menuName;
    private String quantity;
    private String tabNo;
    private int menuPrice;

    private String orderId;
    private String menuId;

//    private List<Option> optionList;

    public OrderMenu(String menuName, String quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }
}

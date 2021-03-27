package com.jumanji.capston.data.Request;

import com.jumanji.capston.data.Shop;
import lombok.Data;

@Data
public class ShopRequest {
    private String id;
    private Shop shop;
    private String shopId;
    private String category;
}

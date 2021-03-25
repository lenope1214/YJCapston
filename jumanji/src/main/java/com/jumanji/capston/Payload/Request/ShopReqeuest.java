package com.jumanji.capston.Payload.Request;

import com.jumanji.capston.data.Shop;
import lombok.Data;

@Data
public class ShopReqeuest {
    private String id;
    private Shop shop;
}

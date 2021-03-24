package com.jumanji.capston.Payload.Request;

import com.jumanji.capston.data.Shop;
import lombok.Data;

@Data
public class PutShopReqeuest {
    private String id;
    private Shop shop;
}

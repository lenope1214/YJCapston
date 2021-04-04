package com.jumanji.capston.DTO;

import com.jumanji.capston.data.Shop;
import lombok.Data;

@Data
public class PutShopDTO {
    private String id;
    private Shop shop;
}

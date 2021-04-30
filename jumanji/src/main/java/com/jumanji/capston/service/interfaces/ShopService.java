package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Shop;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShopService {
    public Shop get(String shopId);

    public ResponseEntity<?> getList(String category, String sortTarget);

    public Shop post(String authorization, Shop.PostRequest request);

    public Shop patch(String authorization, Shop.PatchRequest request);

    public void delete(String authorization, String shopId);
}

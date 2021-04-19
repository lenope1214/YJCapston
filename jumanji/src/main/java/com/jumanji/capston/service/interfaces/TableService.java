package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Tab;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TableService {
    public Tab.Response get(String tableId);
    public List<Tab.Response> getList(String shopId);
    public Tab.Response post(String authorization, Tab.Request request);
    public Tab.Response patch(Tab.Request request);
    public boolean delete(String tableId);
}

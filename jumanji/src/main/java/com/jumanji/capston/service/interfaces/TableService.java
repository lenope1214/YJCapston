package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Tab;

import java.util.List;

public interface TableService {
    public Tab.Response get(String tableId);
    public List<Tab.Response> getList(String shopId);
    public Tab.Response post(String authorization, Tab.Request request);
    public Tab.Response patch(String authorization, Tab.Request request);
    public void delete(String authorization, String tableId);
}

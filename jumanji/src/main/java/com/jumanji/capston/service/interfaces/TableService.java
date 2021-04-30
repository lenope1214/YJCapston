package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Tab;

import java.util.List;

public interface TableService {
    public Tab get(String tableId);
    public List<Tab> getList(String shopId);
    public Tab post(String authorization, Tab.Request request);
    public Tab patch(String authorization, Tab.Request request);
    public void delete(String authorization, String tableId);
}

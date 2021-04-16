package com.jumanji.capston.service;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import com.jumanji.capston.service.exception.TableException.TableHasExistException;
import com.jumanji.capston.service.exception.TableException.TableNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService, BasicService {
    @Autowired
    TableRepository tableRepository;


    public Tab getTableInfo(String tabId){
        isPresent(tabId);
        Tab table = tableRepository.findById(tabId).get();
        return table;
    }

    @Override
    public ResponseEntity<?> get(String tableId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList(String shopId) {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Tab.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Tab.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String tableId) {
        return null;
    }

    @Override
    public boolean isPresent(String id) {
        if(tableRepository.findById(id).isPresent())return true;
        throw new TableNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        if(tableRepository.findById(id).isEmpty())return true;
        throw new TableHasExistException();
    }
}

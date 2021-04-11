package com.jumanji.capston.service;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import com.jumanji.capston.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService, BasicService {
    @Autowired
    TableRepository tableRepository;

    public Tab findById(String id){
        return tableRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Tab insert(Tab _tab){
        return tableRepository.save(_tab);
    }

    public String delete(Tab _tab){
        Tab tab  = tableRepository.findById(_tab.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        tableRepository.delete(tab);
        return "ok";
    }

    public List<Tab> findAll() {
        return tableRepository.findAll();
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
        return false;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}

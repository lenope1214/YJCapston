package com.jumanji.capston.service;

import com.jumanji.capston.data.Tab;
import com.jumanji.capston.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
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
}

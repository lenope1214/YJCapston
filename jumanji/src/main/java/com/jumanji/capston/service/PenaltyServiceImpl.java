package com.jumanji.capston.service;

import com.jumanji.capston.data.Penalty;
import com.jumanji.capston.repository.PenaltyRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyServiceImpl implements PenaltyService, BasicService {
    @Autowired
    PenaltyRepository penaltyRepository;

    public Penalty findById(Long id){
        return penaltyRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
    }


    public Penalty insert(Penalty _penalty){
        return penaltyRepository.save(_penalty);

    }

    public String delete(Penalty _penalty){
        System.out.println("");
        Penalty penalty  = penaltyRepository.findById(_penalty.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
        penaltyRepository.delete(penalty);
        return "ok";
    }

    public List<Penalty> findAll() {
        return penaltyRepository.findAll();
    }


    @Override
    public ResponseEntity<?> get(String penaltyId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Penalty.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Penalty.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String penaltyId) {
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

package com.jumanji.capston.service;

import com.jumanji.capston.data.Penalty;
import com.jumanji.capston.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyService {
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
}

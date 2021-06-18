package com.jumanji.capston.service;

import com.jumanji.capston.data.Penalty;
import com.jumanji.capston.repository.PenaltyRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PenaltyServiceImpl implements BasicService<Penalty, Penalty.Request, Long> {
    @Autowired
    PenaltyRepository penaltyRepository;

    @Override
    public Penalty get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Penalty> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public Penalty post(@Nullable String authorization, Penalty.Request request) {
        return null;
    }

    @Override
    public Penalty patch(@Nullable String authorization, Penalty.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Penalty isPresent(Long id) {
        return null;
    }

    @Override
    public boolean isEmpty(Long id) {
        return false;
    }
}

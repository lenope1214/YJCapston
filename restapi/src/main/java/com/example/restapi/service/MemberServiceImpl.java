package com.example.restapi.service;

import com.example.restapi.model.Member;
import com.example.restapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public void register(Map<String, String> map) {
        Member member = dtoToEntity(map);

        memberRepository.save(member);
    }
}

package com.example.restapi.service;

import com.example.restapi.model.Member;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface MemberService {
    void register(@RequestBody Map<String, String> map);

    default Member dtoToEntity(Map<String, String> map) {
        Member member = Member.builder()
                .id(map.get("id"))
                .pw(map.get("pw"))
                .name(map.get("name"))
                .email("")
                .address("")
                .birthday(null)
                .phone(map.get("phone"))
                .is_wdrw('N')
                .role(map.get("role"))
                .social("jm")
                .sign_date(null)
                .level("0")
                .point(0)
                .code(200)
                .build();

        return member;
    }
}

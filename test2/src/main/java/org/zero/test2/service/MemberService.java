package org.zero.test2.service;

import org.zero.test2.dto.MemberDTO;
import org.zero.test2.member.Member;

public interface MemberService {
    Long register(MemberDTO dto);

    default Member dtoToEntity(MemberDTO dto) {
        Member entity = Member.builder()
                .idCode(dto.getIdCode())
                .id(dto.getId())
                .password(dto.getPassword())
                .build();
        return entity;
    }
}

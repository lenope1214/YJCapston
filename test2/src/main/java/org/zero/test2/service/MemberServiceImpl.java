package org.zero.test2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zero.test2.dto.MemberDTO;
import org.zero.test2.member.Member;
import org.zero.test2.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

    @Override
    public Long register(MemberDTO dto) {
        log.info("DTO----------------------");
        log.info(dto);

        Member entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getIdCode();
    }
}

package com.jumanji.capston.controller;

//
//

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


/**
 *  통합 테스트 -> 전체 스프링을 테스트 = 모든 Bean들을 똑같이 IoC(메모리)에 올리고 테스트.
 *  WebEnvironment.MOCK = 실제 톰캣을 올리는게 아니라 가짜 톰캣으로 테스트.
 *  WebEnvironment.RANDOM_PORT = 실제 톰캣으로 테스트.
 *  @AutoConfigureMockMvc  MockMvc를 IoC에 등록해줌.
 *  @Transactional   각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션 !
 *
 */

@Transactional // 각각의 메소드 종료시 롤백.
@AutoConfigureMockMvc // 이게 없으면 mockMvc가 IoC에 안들어가있어서 Bean을 못찾음. WebMvcTest에는 담겨져 있음.
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // 통합 테스트용. 모든것이 올라감.
public class ApiControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1(){

    }

}

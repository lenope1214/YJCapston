package com.jumanji.capston.controller;


// 단위 테스트 -> 컨트롤러만 테스트
//   = 컨트롤러 관련 로직만 IoC(메모리)에 올리고 테스트.
//    => Filter, ControllerAdvice(exception)

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@Slf4j
@WebMvcTest // 얘를 붙이면 Controller, Filter, ControllerAdvice 등이 메모리에 올라감. 단위테스트용.
// WebMvcTest에 웬만한 어노테이션이 있어서 굉장히 유용.
public class ApiControllerUnitTest {

    @Autowired // 주소 호출을 해서 테스트 해주는 도구
    private MockMvc mockMvc;

    @Test
    public void test_save(){
        log.info("save 테스트() 시작============");
    }




}

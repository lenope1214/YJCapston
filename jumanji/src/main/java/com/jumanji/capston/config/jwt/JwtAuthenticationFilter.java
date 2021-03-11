package com.jumanji.capston.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumanji.capston.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 필터가 있음.
// /login 에 username, password를 post로 요청하면 위의 필터가 동작함.
// SecurityConfig 에서 formLogin().disable 을 했기 때문에 위의 필터가 작동을 안하게 됨.
// 그러므로 다시 만들어서 SecurityConfig에 등록해줘야함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

        // 1. username(변수명이 아이디를 나타내는건지 확인 해봐야함.), password(pw로 바꿔도 되는지 확인 해봐야함.)를 받아서
        try{
            // 아래는 x-www-form-urlencoded 로 들어온 데이터 처리할 때.
//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while((input = br.readLine()) != null){
//                System.out.println(input);
//            }

            // 아래는 json 타입 처리할 때
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);
//            System.out.println(request.getInputStream()); // 이 안에 유저명, 비밀번호가 담겨있음
            System.out.println("========================");
        }catch(IOException e){
            e.printStackTrace();
        }

        // 2. 정상인지 로그인 시도를 해본다. authenticationManager로 로그인 시도를 하면,
        //    PrincipalDetailsService가 호출. loadUserByUsername() 함수 실행된다.
        // 3. PrincipalDetails를 세션에 담고 -> 세션에 담지 않는다면 권한관리가 안된다. 권한관리 안할거면 PrincipalDetails를 안만들어도 된다.
        // 4. JWT 토큰을 만들어서 응답해주면 됨.


        return super.attemptAuthentication(request, response);
    }
}

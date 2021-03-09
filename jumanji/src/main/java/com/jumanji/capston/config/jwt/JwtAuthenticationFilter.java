package com.jumanji.capston.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        // 1. username(변수명이 아이디를 나타내는건지 확인 해봐야함.), password(pw로 바꿔도 되는지 확인 해봐야함.)를 받아서
        try{
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("유저정보======\n"
                    + "아이디 : " + user.getId() + "\n"
                    + "비밀번호 : " + user.getPassword()
            );

            UsernamePasswordAuthenticationToken authenticationToken = // 첫번쨰 인자 : id로 받는 변수, 두번쨰 : 비밀번호
                    new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());

            // 이 명령문이 실행될때 PrincipalDetailsService의 loadUserByUsername()가 실행됨.
            // 이 변수에는 로그인한 정보가 담긴다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // authentication 객체가 session 영역에 저장됨 -> 로그인 되었다는 뜻
            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            System.out.println("프린유저 : " + principalDetails.getUser().getId());
            return authentication; // 인증정보 리턴
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("===================================");
        // 2. 정상인지 로그인 시도를 해본다. authenticationManager로 로그인 시도를 하면,
        //    PrincipalDetailsService가 호출. loadUserByUsername() 함수 실행된다.
        // 3. PrincipalDetails를 세션에 담고 -> 세션에 담지 않는다면 권한관리가 안된다. 권한관리 안할거면 PrincipalDetails를 안만들어도 된다.
        // 4. JWT 토큰을 만들어서 응답해주면 됨.

        System.out.println("JwtAuthenticationFilter : 로그인 시도중");
        return null;
    }
}

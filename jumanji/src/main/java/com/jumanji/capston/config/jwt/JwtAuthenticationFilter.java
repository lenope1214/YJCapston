package com.jumanji.capston.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 필터가 있음.
// /login 에 username, password를 post로 요청하면 위의 필터가 동작함.
// SecurityConfig 에서 formLogin().disable 을 했기 때문에 위의 필터가 작동을 안하게 됨.
// 그러므로 다시 만들어서 SecurityConfig에 등록해줘야함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 실행 순서
    // 1. attemptAuthentication -> 인증 확인을 한다.
    // 2. successfulAuthentication -> 인증이 정상적으로 되었을 때.


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

            // authenticationToken는 로그인이 잘 됐을때 생성됨.
            UsernamePasswordAuthenticationToken authenticationToken = // 첫번쨰 인자 : id로 받는 변수, 두번쨰 : 비밀번호
                    new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());

            // 이 명령문이 실행될때 PrincipalDetailsService의 loadUserByUsername()가 실행됨.
            // 이 변수에는 로그인한 정보가 담긴다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // authentication 객체가 session 영역에 저장됨 -> 로그인 되었다는 뜻
            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            System.out.println("로그인 완료됨? (principalDetails.getUser().getId()): " + principalDetails.getUser().getId()); // 값이 나오면 로그인이 정상적으로 됐다는 뜻.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 한다.
            // 굳이 jwt 토큰을 사용하면서 세션을 만들 이유가 없는데 단지 권한 처리 때문에 session에 넣어 줍니다.

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


    // attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 메소드가 실행.
    // 여기서 JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 뜻임.");
        PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
        // principalDetails를 통해 jwt 만들것임.

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername()) // 얘는 인터페이스라 내용을 ID로 수정했음 subject 지정.
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME)) // 유효시간 지정 현재시간 + n/ms 시간
                .withClaim("id", principalDetails.getUser().getId())     // json 타입 키 밸류라고 생각.
                .withClaim("pw", principalDetails.getUser().getPassword()) // 키 : name 밸류 : 유저 이름
//                .withClaim("")
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        System.out.println("인증 완료 후 처리 단계...");
        
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
//        super.successfulAuthentication(request, response, chain, authResult);
    }
    // 기존 ->
    // 로그인 요청시 아이디, 패스워드가 정상이라면
    // 서버쪽 세션 ID를 생성하고
    // 클라이언트 쿠키 세션ID를 응답해준다.
    // 요청할 때마다 쿠키값 세션 ID를 항상 들고 서버쪽으로 요청하기 때문에 서버는
    // 세션ID가 유효한지 판단해서
    // 유효하면 인증이 필요한 페이지로 접근하게 하면 된다.

    // JWT 사용 ->
    // 로그인 요청시 아이디, 패스워드가 정상이라면
    // JWT토큰을 생성, 클라이언트에게 JWT을 응답.
    // 클라는 요청할 때마다 JWT토큰을 가지고 요청.
    // 서버는 JWT 토큰이 유효한지를 판단( 필터를 만들어야 함.)

}

package com.jumanji.capston.config;


import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.config.auth.PrincipalDetailsService;
import com.jumanji.capston.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 스프링에서 Spring Security 를 이용하면 사용자를 인증하고 권한에 따라서 특정 경로에 접근하는 것을 제한하는 기능을 설정으로
 * 쉽게 적용할 수 있다. 그러나 설정만으로 모든 것을 처리할 수 있는 것은 아니므로 필요하다면
 * AuthenticationProvider 인터페이스를 구현하여 authenticate()메소드를 오버라이드하면
 * 인증하는 부분의 로직을 개발자가 모두 작성할 수 있다.
 *
 * 아래는 참고 자료
 * 출처: https://javaengine.tistory.com/entry/Spring-Security-Custom-AuthenticationProvider-example [nalaolla]
 * 출처 : https://to-dy.tistory.com/87
 */

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PrincipalDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 검증을 위한 구현
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("provider 0");
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        PrincipalDetails user = (PrincipalDetails) userDetailsService.loadUserByUsername(username);

        System.out.println("provider 1");
        if(!matchPassword(password, user.getPassword())) {
            throw new BadCredentialsException(username);
        }

        System.out.println("provider 2");
        if(!user.isEnabled()) {
            throw new BadCredentialsException(username);
        }
        System.out.println("provider 3");
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }


    // 출처: https://to-dy.tistory.com/87 [todyDev]

    // 토큰 타입과 일치할 때 인증
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    // 출처: https://to-dy.tistory.com/87 [todyDev]
    private boolean matchPassword(String loginPwd, String password) {
        return loginPwd.equals(password);
    }



}
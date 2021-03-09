package com.jumanji.capston.config.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// 시큐리티가 filter를 가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있음.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 핉터를 무조건 타게 되어있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 위의 필터를 거치지 않는다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");
    }
}

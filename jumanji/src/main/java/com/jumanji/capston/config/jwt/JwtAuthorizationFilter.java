//package com.jumanji.capston.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.jumanji.capston.config.auth.PrincipalDetails;
//import com.jumanji.capston.data.User;
//import com.jumanji.capston.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//// 시큐리티가 filter를 가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있음.
//// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 핉터를 무조건 타게 되어있음.
//// 만약에 권한이 인증이 필요한 주소가 아니라면 위의 필터를 거치지 않는다.
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
//    private UserRepository userRepository;
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
//        super(authenticationManager);
//        System.out.println("AuthorizationFilter -> 인증 권한 필요 주소 요청");
//        this.userRepository = userRepository;
//    }
//
//
//    // 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨.
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
////        super.doFilterInternal(request, response, chain); // doFilterInternal 이게 두번이상이 되면 오류.
//        System.out.println("AuthorizationFilter_doFilter -> 인증 권한 필요 주소 요청");
//
//        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
//        System.out.println("JwtAuthorization - jwtHeader : " + jwtHeader);
//
//        // header가 있는지 확인
//        if(jwtHeader == null || !(jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX))){ // jwt 앞에 붙여준 검증
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // JWT 토큰을 검증해서 정상적인 사용자인지 확인
//        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
//
//        // 서명 진행
//        String id = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
//                .build()
//                .verify(jwtToken)
//                .getClaim("id")
//                .asString();
//
//        // 서명이 정상적으로 됨
//        if(id != null){
//            Optional<User> userEntity = userRepository.findById(id);
//            PrincipalDetails principalDetails = new PrincipalDetails(userEntity.get());
//
//            // jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어 준다.
//            // id가 널이 아니라는 것은 정상적인 유저라는 증거기 때문에 두번째 매개변수인 비밀번호를 null로 줌
//            Authentication authEntication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//
//            // 시큐리티를 저장할 수 있는 세션 공간
//            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
//            SecurityContextHolder.getContext().setAuthentication(authEntication);
////            response.addHeader("JWT", authEntication);
//            chain.doFilter(request, response);
//        }
//
//
//    }
//}

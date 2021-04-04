package com.jumanji.capston.config.jwt;

import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="login required")
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private PrincipalDetailsService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private static final List<String> EXCLUDE_URL =
            List.of("/api/v1/login"
                    , "/authenticate"  // 얜 필수..!
                    , "/api/v1/join"
                    , "/api/v1/shopList"
                    , "/api/v1/validate"
                    , "/api/v1/validateDscNo"
                    , "/api/v1/searchAddr"
                    , "/api/v1/shop"
                    , "/api/v1/shopList"
                    , "/api/v1/menu"
                    , "/api/v1/menuList"
                    , "/api/v1/test/uploadTest01"
                    , "/api/v1/test/uploadTest02"
                    , "/api/v1/test/loadImg/*"
                );


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("In JwtRequestFilter");
        final String requestTokenHeader = request.getHeader("Authorization");
//        System.out.println("TOKEN : " + requestTokenHeader );
        String username = null;
        String jwtToken = null;
        boolean jwtFlag = false;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) { // 헤더에 Authorization 이 있고 Bearer로 시작하면,
            jwtToken = requestTokenHeader.substring(7);
//            System.out.println("Parsing jwtToken : " + jwtToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println("username : " + username);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
                response.sendError(400, "Unable to get JWT Token");
                jwtFlag = true;
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                response.sendError(401, "JWT Token has expired"); // 401 =>
                jwtFlag = true;

            } catch (NullPointerException e){
                System.out.println("Username is Null!");
                response.sendError(400, "Username is Null!");
                jwtFlag = true;
            }
        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
        }
//        System.out.println("토큰 체킹 ! ");

        if(username != null) {
            if(SecurityContextHolder.getContext().getAuthentication() == null){
//                System.out.println("doFilterInternal -> username != null ... ");
                PrincipalDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

                if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }else{ System.out.println("SecurityContextHolder.getContext().getAuthentication() is null"); }
        }else{ System.out.println("token's username is null"); }
//        System.out.println("JWT 체킹 완료!!!");

        if(!jwtFlag){
            System.out.println("토큰 핉터 넘어감.");
            filterChain.doFilter(request,response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }

}
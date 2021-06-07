package com.jumanji.capston.config.jwt;

import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "login required")
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String EXPATH = "/api/v1/";
    @Autowired
    private PrincipalDetailsService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private static final List<String> EXCLUDE_URL =
            List.of(
                    "login"
                    , "authenticate"
                    , "join"
                    , "shops/list"
                    , "validate"
                    , "validateDscNo"
                    , "searchAddr"
                    , "shops"
                    , "menus"
                    , "menus/list"
                    , "reviews"
                    , "payments/complite"
                    , "menus/options"
            );


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("In JwtRequestFilter");
        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
        }
        final String requestTokenHeader = request.getHeader("Authorization");
//        System.out.println("TOKEN : " + requestTokenHeader );
        String username = null;
        String jwtToken = null;
//        System.out.println("Authorization : " + requestTokenHeader);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) { // 헤더에 Authorization 이 있고 Bearer로 시작하면,
            jwtToken = requestTokenHeader.substring(7);
//            System.out.println("Parsing jwtToken : " + jwtToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println("username : " + username);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token");
                response.sendError(400, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.error("JWT Token has expired");
                response.sendError(403, "JWT Token has expired"); // 401 => 인증안됨, 403 => 사용자 확인 불가?? 뭐 조수연 교수님이ㅣ 토큰 만료 403 준다함.
            } catch (NullPointerException e) {
                log.error("Username is Null!");
                response.sendError(400, "Username is Null!");
            }catch (SignatureException ex) {
                log.error("Invalid JWT signature");
                response.sendError(400, "Invalid request.");
            } catch (MalformedJwtException ex) {
                log.error("Invalid JWT token");
                response.sendError(400, "Invalid request.");
            } catch (UnsupportedJwtException ex) {
                log.error("Unsupported JWT token");
                response.sendError(400, "Invalid request.");
            }
        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
        }
//        System.out.println("토큰 체킹 ! ");
        System.out.println("토큰 username : " + username);
        if (username != null) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
//                System.out.println("doFilterInternal -> username != null ... ");
                PrincipalDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                System.out.println("SecurityContextHolder.getContext().getAuthentication() is null");
            }
        } else {
            System.out.println("token's username is null");
        }
//        System.out.println("JWT 체킹 완료!!!");

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("요청 url : [" +request.getMethod() + "] " + request.getServletPath());
//        for(String path :request.getServletPath().split("/") ){65
//            System.out.println("-------> " + path);
//        }
        String path =  request.getServletPath();
        if(path.startsWith("/ws-stomp") || path.startsWith("/start")){
            return true;
        }
        if(path.startsWith("/api/v1/")) {
            final String PATH = path.substring(path.indexOf("EXPATH") + EXPATH.length()+1);
//            System.out.println("잘라낸 패스 : "  + PATH);

//            for(Object str : EXCLUDE_URL.toArray()){
//                System.out.println(str);
//            }
//            System.out.println(EXCLUDE_URL.stream().anyMatch(exclude -> exclude.startsWith(PATH)));
            return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.startsWith(PATH));
        }
        return false;
    }
}
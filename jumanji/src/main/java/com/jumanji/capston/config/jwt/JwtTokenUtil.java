package com.jumanji.capston.config.jwt;

import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements JwtProperties {

//    private static final String secret = "jumanjiSecretKey";

//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 * 3600 = 5시간

    @Autowired
    UserRepository userRepository;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(String id) {
        return generateToken(id, new HashMap<>());
    }

    public String generateToken(String id, Map<String, Object> claims) {
        return doGenerateToken(id, claims);
    }

    private String doGenerateToken(String id, Map<String, Object> claims) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS512"); header.put("typ", "JWT");
//        Claims claims

        if(userRepository.findById(id).isPresent())
            claims.put("role", userRepository.findById(id).get().getRole());

        System.out.println(">>>>>>>>>>>>>> doGenerateToken's id : " + id);
        return Jwts.builder()
                .setHeader(header) // 헤더에 토큰 타입과 알고리즘.
                .setClaims(claims)
                .setSubject("userInfo")
                .setId(id)
//                .set
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Boolean validateToken(String token, PrincipalDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
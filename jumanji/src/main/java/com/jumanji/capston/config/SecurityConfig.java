package com.jumanji.capston.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

// jwt 설정 securityconfig
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록. 필터체인(묶음)에 필터등록
@EnableGlobalMethodSecurity(
        securedEnabled = true,  // Controller 에서 @Secured 어노테이션 사용가능. @Secured("roleName")
        prePostEnabled = true   // preAuthorize 어노테이션 활성화
)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
<<<<<<< HEAD
//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;

=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
    private final CorsFilter corsFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
<<<<<<< HEAD
<<<<<<< HEAD
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // Security Filter Chain 의 BasicAuth...Filter 전에 추가한다는 뜻.
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다.
                .and()
//                .addFilter(corsFilter)
                // 위의 addFilter를 하면 모든 요청은 corsFilter를 거치게 돼있음.
                // @CrossOrigin는 인증이 필요한 상황에선 해결되지 않는다.
                // 인증이 있을때는 시큐리티 필터에 등록을 해줘야 한다.
                .formLogin().disable()
                .httpBasic().disable()
<<<<<<< HEAD
<<<<<<< HEAD
//                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager 을 넘겨줘야함. WebSecurityConfigurerAdapter가 들고있음.
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
                .authorizeRequests()
                .antMatchers("/api/v1/user/**").hasAnyRole("USER", "OWNER", "ADMIN")
                .antMatchers("/api/v1/owner/**").hasAnyRole("OWNER", "ADMIN")
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();
<<<<<<< HEAD
<<<<<<< HEAD
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm")
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
=======
>>>>>>> parent of 54f6ae7 (24강까지 완료)
    }
}
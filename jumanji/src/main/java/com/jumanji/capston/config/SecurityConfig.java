package com.jumanji.capston.config;




import com.jumanji.capston.config.auth.PrincipalDetailsService;
import com.jumanji.capston.config.jwt.JwtRequestFilter;
import com.jumanji.capston.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

// jwt 설정 securityconfig
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록. 필터체인(묶음)에 필터등록
//@EnableGlobalMethodSecurity(
//        securedEnabled = true,  // Controller 에서 @Secured 어노테이션 사용가능. @Secured("roleName")
//        prePostEnabled = true   // preAuthorize 어노테이션 활성화
//)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;
    private final CorsFilter corsFilter;
    private final HttpHeaders httpHeaders = new HttpHeaders();
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PrincipalDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
// 인증하지 않을 주소 추가.
        web.ignoring().antMatchers("/html/**", "/css/**", "/js/**", "/image/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("happydaddy")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("angrydaddy")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("guest")
                .password("{noop}1234")
                .roles("GUEST");
        auth.jdbcAuthentication().dataSource(dataSource);
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public HttpHeaders  getHttpHeaders(){return httpHeaders;}

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // Security Filter Chain 의 BasicAuth...Filter 전에 추가한다는 뜻.
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다.
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilter(corsFilter)
                // 위의 addFilter를 하면 모든 요청은 corsFilter를 거치게 돼있음.
                // @CrossOrigin는 인증이 필요한 상황에선 해결되지 않는다.
                // 인증이 있을때는 시큐리티 필터에 등록을 해줘야 한다.
                .formLogin().disable()
                .headers()
                    .frameOptions().sameOrigin()
                .and()
                .httpBasic().disable()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager 을 넘겨줘야함. WebSecurityConfigurerAdapter가 들고있음.{
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository)) // AuthenticationManager 을 넘겨줘야함. WebSecurityConfigurerAdapter가 들고있음.{
                .authorizeRequests()
                    .antMatchers("/api/v1/user/**").hasAnyRole("USER", "OWNER", "ADMIN")
                    .antMatchers("/api/v1/owner/**").hasAnyRole("OWNER", "ADMIN")
                    .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/loginForm") //이 줄을 지우면 스프링이 제공하는 폼이 출력됨.
//                .loginProcessingUrl("/api/v1/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행.
                    .defaultSuccessUrl("/") // 로그인 성공하면 갈 주소.
                    .permitAll()
                .and()
                .oauth2Login()
                    .loginPage("/loginForm")
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService); // 후처리 서비스 지정
//                .and()
//                .formLogin()
////                .loginPage("/api/v1/login") 	// 로그인 페이지 url
//                .loginProcessingUrl("/api/v1/login")  // view form의 action과 맞아야함
//                .failureUrl("/?error=1") // 로그인 실패시 redirect
//                .usernameParameter("id") // 로그인 폼에서 비밀번호 받는 input 태그 name 속성이 id로 변경됨.
//                .passwordParameter("password")  // 로그인 폼에서 비밀번호 받는 input 태그 name 속성이 password가 됨.(메소드 이름이 usernameParameter로 무조건 써야하지만, 파라미터는 email이든 id이든 name이든 상관없다.)
//                .defaultSuccessUrl("/", true) // 로그인 성공시
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm")
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService)

        // username password 확인 필터 전에 jwt있는지 확인을 위해!
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpHeaders.add("Content-Type", "text/html; charset=UTF-8");
    }

}
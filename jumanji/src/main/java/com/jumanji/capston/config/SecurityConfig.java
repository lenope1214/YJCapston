//package com.jumanji.capston.config;
//
//
//import com.jumanji.capston.Service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImpl userDetails;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//// 인증하지 않을 주소 추가.
//        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
//        System.out.println("암호 : " + passwordEncoder().encode("1111"));
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
////.antMatchers("/mypoint/**").hasAnyAuthority("QUERY", "WRITE") // 접근 권한.
//                .antMatchers("/mypoint/**").hasAnyRole("admin", "user")
//                .antMatchers("/adduser/**", "/update/**").hasRole("admin") // 관리자만 접근 가능.
//                .antMatchers("/**").permitAll() // 위 경우를 빼고 모든 권한을 줌=로그인 필요 없음.
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") //이 줄을 지우면 스프링이 제공하는 폼이 출력됨.
//                .defaultSuccessUrl("/") // 로그인 성공하면 갈 주소.
////.usernameParameter("userid") // 로그인 폼에서 username 을 사용하지 않았다면 여기서 처리
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
////.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 다른 주소 쓸 때.
//                .logoutSuccessUrl("/") // 로그아웃 이후 갈 페이지.
//                .invalidateHttpSession(true)
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/denied") // 403 에러 처리.
//                .and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//}
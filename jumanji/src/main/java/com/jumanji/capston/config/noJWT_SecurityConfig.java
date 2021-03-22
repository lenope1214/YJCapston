//package com.jumanji.capston.config;
//
//
////import com.jumanji.capston.Service.UserDetailsServiceImpl;
//import com.jumanji.capston.config.oauth.PrincipalOauth2UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록. 필터체인(묶음)에 필터등록
//@EnableGlobalMethodSecurity(
//        securedEnabled = true,  // Controller 에서 @Secured 어노테이션 사용가능. @Secured("roleName")
//        prePostEnabled = true   // preAuthorize 어노테이션 활성화
//)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private UserDetailsServiceImpl userDetails;
//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;
//
//    //@Bean을 붙여 해당 메소드의 리턴되는 오브젝트를 IOC로 등록해줌.
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//// 인증하지 않을 주소 추가.
//        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
//    }
//
////    @Override
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
////        System.out.println("암호 : " + passwordEncoder().encode("1111"));
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // csrf 비활성화
////        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않겠다.
//        http
//                .authorizeRequests()
////.antMatchers("/mypoint/**").hasAnyAuthority("QUERY", "WRITE") // 접근 권한.
//                .antMatchers("/myPoint/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/adduser/**", "/update/**").hasRole("ADMIN") // 관리자만 접근 가능.
//                .antMatchers("/**").permitAll() // 위 경우를 빼고 모든 권한을 줌=로그인 필요 없음.
//                .anyRequest().authenticated() // authenticated => 인증만되면 모두 허용
//                .and()
//                // 로그인 안했을 시 로그인 페이지로 이동하게 함.
//                .formLogin()
//                .loginPage("/loginForm") //이 줄을 지우면 스프링이 제공하는 폼이 출력됨.
//                .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행.
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
//                .accessDeniedPage("/denied") // 403 에러 처리. denied mapping
//                .and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm")
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService); // 후처리 서비스 지정
//                // oauth2 로그인이 다 되고 나서 후처리가 필요함.
//                // 1. 코드받기(인증) 2. 엑세스토큰(권한)
//                // 3.사용자 프로필 정보 가져오기 4-1. 정보를 토대로 회원가입을 자동으로 진행시키기도 함.
//                // 4-2 추가적인 정보가 필요하게 되면 자동이 아닌 추가 정보 기입 후 가입진행.
//                // Tip. oauth2 로그인이 완료되면 코드X, (엑세스토큰+사용자 프로필정보를 한번에 받음.)
////                .and()
//
//
//
//    }
//}
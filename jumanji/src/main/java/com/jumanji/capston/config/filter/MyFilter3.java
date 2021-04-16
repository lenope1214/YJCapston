//package com.jumanji.capston.config.Filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class MyFilter3 implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest)request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        if(req.getMethod().equals("POST")){
//            String headerAuth = req.getHeader("Authorization"); // jmj
//            System.out.println(headerAuth);
//            System.out.println("필터3");
//
//            // 토큰 : jmj 이걸 만들어줘야 함. -> id, pw가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어 주고 그걸 응답해 준다.
//            // 요청할 때마다 header에 Authorization에 value값으로 토큰을 가지고 온다.
//            // 그때 토큰이 넘어오면 내가 만든 토큰인지 검증만 하면 된다. ( RSA, HS256) 을 통해
//            if(headerAuth.equals("jmj")){
//                chain.doFilter(req,res); // 다시 필터에 들어가라! 이게 없으면 얘는 한번하고 끝나버림.
//            }else{
//                PrintWriter out = res.getWriter();
//                out.println("인증되지 않음.");
//            }
//        }else{
//            chain.doFilter(req,res);
//        }
//
////        chain.doFilter(req,res);
//    }
//}
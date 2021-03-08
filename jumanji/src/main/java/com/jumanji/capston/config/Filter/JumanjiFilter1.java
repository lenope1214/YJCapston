package com.jumanji.capston.config.Filter;

import javax.servlet.*;
import java.io.IOException;

public class JumanjiFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.printf("필터1");
        chain.doFilter(request,response); // 다시 필터에 들어가라! 이게 없으면 얘는 한번하고 끝나버림.
    }
}

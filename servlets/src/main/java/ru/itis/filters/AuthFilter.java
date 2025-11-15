package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
//@WebFilter(urlPatterns = {"/singIn", "/signUp"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        Boolean isAuthenticated = false;
        Boolean sessionExist = session != null;
        Boolean isLoginPage = httpServletRequest.getRequestURI().equals("/signIn");

        if (sessionExist) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if (isAuthenticated && !isLoginPage || !isAuthenticated && isLoginPage) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else if (isAuthenticated && isLoginPage) {
            httpServletResponse.sendRedirect("/users");
        } else {
            httpServletResponse.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}

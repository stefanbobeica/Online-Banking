package com.banking.sys.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlreadyAuthenticatedFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            String requestURI = req.getRequestURI();
            if (requestURI.equals("/login") || requestURI.equals("/register")) {
                res.sendRedirect("/");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}

package com.neuro.service001useroperations.security;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CsrfTokenLogger implements Filter {

    private final Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Object csrfToken = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) csrfToken;

        logger.info("CSRF Token: " + token.getToken());
        chain.doFilter(request, response);
    }
}
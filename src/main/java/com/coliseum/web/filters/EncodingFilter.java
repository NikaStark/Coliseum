package com.coliseum.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingFilter.class);
    private static final String ENCODING_TYPE = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("EncodingFilter initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING_TYPE);
        response.setCharacterEncoding(ENCODING_TYPE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("EncodingFilter destroyed.");
    }

}

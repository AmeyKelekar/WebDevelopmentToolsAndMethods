package com.amey.spring.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RegExpFilter implements Filter{
    private FilterConfig filterConfig;
    
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        filterConfig.getServletContext().log("Initializing RegExp Filter");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String host = request.getRemoteHost();
        int port = request.getRemotePort();
        filterConfig.getServletContext().log("Remote host - " + host);
        filterConfig.getServletContext().log("Remote port - " + port);
        chain.doFilter(new RequestWrapper((HttpServletRequest)request), response);
    }

    public void destroy() {
        filterConfig.getServletContext().log("Destroying RegExp Filter");
        this.filterConfig = null;
    }    
    
}
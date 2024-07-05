//package com.houstondirectauto.refurb.config;
//
//import java.io.IOException;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class CorsFilter extends OncePerRequestFilter {
//
//
//
//    @Override
//    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Max-Age", "*");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Expose-Headers", "*");
//
//        if ("OPTIONS".equalsIgnoreCase(servletRequest.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//        	filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
//
//
//}

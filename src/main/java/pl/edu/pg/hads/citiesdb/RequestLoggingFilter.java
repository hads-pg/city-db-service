package pl.edu.pg.hads.citiesdb;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;

public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        long startTime = System.currentTimeMillis();
        
        // Log request details
        logRequest(requestWrapper);
        
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            
            // Log response details
            logResponse(responseWrapper, duration);
            
            // This is important to copy content back to the original response
            responseWrapper.copyBodyToResponse();
        }
    }
    
    private void logRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("============================= REQUEST BEGIN =============================\n");
        sb.append("URI         : ").append(request.getRequestURI()).append("\n");
        sb.append("Method      : ").append(request.getMethod()).append("\n");
        sb.append("Headers     : \n");
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            sb.append("    ").append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }
        
        sb.append("Parameters  : \n");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            sb.append("    ").append(paramName).append(": ").append(request.getParameter(paramName)).append("\n");
        }
        
        sb.append("============================= REQUEST END ===============================");
        System.out.println(sb.toString());
    }
    
    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("============================= RESPONSE BEGIN ============================\n");
        sb.append("Status code : ").append(response.getStatus()).append("\n");
        sb.append("Duration    : ").append(duration).append("ms\n");
        sb.append("Headers     : \n");
        
        for (String headerName : response.getHeaderNames()) {
            sb.append("    ").append(headerName).append(": ").append(response.getHeader(headerName)).append("\n");
        }
        
        sb.append("============================= RESPONSE END ==============================");
        System.out.println(sb.toString());
    }
} 
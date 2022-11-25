package com.mrhc.api.safe.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class LoginFilter implements Filter{
	private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("In LoginFilter...");
		
		String url = ((HttpServletRequest) request).getRequestURI();
        if(url.contains("/api/auth")){
        	chain.doFilter(request, response);
        	return;
        }
        
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        
        if (token == null)
        {
        	HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        	Map<String, Object> errorDetails = new HashMap<>();
        	
            errorDetails.put("message", "credenciales obligatorias");
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
        	
        	return;
        }
        
        if(!token.equals("AHGC-12BD-1328-75HF-HD64"))
		{
        	LOG.info("Token..." + token);
        	HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        	Map<String, Object> errorDetails = new HashMap<>();
        	
            errorDetails.put("message", "token invalido");
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
        	
        	return;
		} else {
			chain.doFilter(request, response);
		}
	}
}

package com.ldapsecurity.securityexceptionhandler;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationExcpetionHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("timestamp", Calendar.getInstance().getTime());
		responseData.put("message", "Invalid Credentials");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getOutputStream().println(mapper.writeValueAsString(responseData));
	}
}

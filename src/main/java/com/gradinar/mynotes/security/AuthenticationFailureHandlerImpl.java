package com.gradinar.mynotes.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", new Date());
        errors.put("status", 404);
        errors.put("error", "User not found");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), errors);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}

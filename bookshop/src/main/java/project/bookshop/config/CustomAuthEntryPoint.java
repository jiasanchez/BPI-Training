package project.bookshop.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
	
	private final ObjectMapper objectMapper;
	
	public CustomAuthEntryPoint(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json");
			Map<String, Object> body = new HashMap<>();
			body.put("status", 401);
			body.put("error", "Unathorized");
			body.put("message", "User need to log in first.");
			body.put("path", req.getRequestURI());
			
			objectMapper.writeValue(res.getWriter(), body);
	}

}

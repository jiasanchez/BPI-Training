package m9.act.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private final ObjectMapper objectMapper;
	
	public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ex) throws IOException {
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		res.setContentType("application/json");
		Map<String, Object> body = new HashMap<>();
		body.put("status", 403);
		body.put("error", "Forbidden");
		body.put("message", "User not permitted to access.");
		body.put("path", req.getRequestURI());
		
		objectMapper.writeValue(res.getWriter(), body);
	}
}
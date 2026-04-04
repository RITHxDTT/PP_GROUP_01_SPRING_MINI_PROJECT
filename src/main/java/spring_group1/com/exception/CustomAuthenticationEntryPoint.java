package spring_group1.com.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.BAD_REQUEST.value()); // 👈 change 401 → 400

        Map<String, Object> error = new HashMap<>();
        error.put("type", "about:blank");
        error.put("title", "Bad Request");
        error.put("status", 400);
        error.put("detail", "Invalid username, email, or password. Please check your credentials and try again.");
        error.put("instance", request.getRequestURI());
        error.put("timestamp", LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), error);
    }
}

package spring_group1.com.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "401 UNAUTHORIZED");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, Object> error = new HashMap<>();
        error.put("type", "about:blank");
        error.put("title", "Bad Request");
        error.put("status", 400);
        error.put("detail", "Invalid username, email, or password. Please check your credentials and try again.");
        error.put("instance", request.getRequestURI());
        error.put("timestamp", LocalDateTime.now().toString());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(error);

        response.getWriter().write(json);
        response.getWriter().flush();
    }
}

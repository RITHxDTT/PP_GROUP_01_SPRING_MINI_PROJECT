package spring_group1.com.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import spring_group1.com.response.ApiRespone;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public JwtAuthEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ApiRespone<Object> apiRespone = ApiRespone.builder()
                .title("Unauthorized")
                .message("Invalid or missing token")
                .status(HttpStatus.UNAUTHORIZED)
                .success(false)
                .timestamp(LocalDate.now())
                .payload(null)
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.getWriter().write(objectMapper.writeValueAsString(apiRespone));
    }
}

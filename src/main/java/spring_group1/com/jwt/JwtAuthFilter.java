package spring_group1.com.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring_group1.com.services.AppUserService;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        System.out.println("=== DEBUG ===");
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Authorization header: " + header);

        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("Token: " + token);

            try {
                String email = jwtUtils.extractEmail(token);
                System.out.println("Extracted email from token: " + email);

                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = appUserService.loadUserByUsername(email);
                    System.out.println("User found: " + userDetails.getUsername());
                    System.out.println("User authorities: " + userDetails.getAuthorities());

<<<<<<< HEAD
                if(jwtUtils.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
=======
                    if(jwtUtils.isTokenValid(token, userDetails)) {
                        System.out.println("Token is VALID!");
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        System.out.println("Token is INVALID!");
                    }
>>>>>>> c46cdab (test)
                }
            } catch (Exception e) {
                System.out.println("JWT Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No Authorization header or not Bearer token");
        }

        filterChain.doFilter(request, response);
    }
}

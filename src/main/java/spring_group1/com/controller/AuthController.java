package spring_group1.com.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.jwt.JwtUtils;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.model.request.LoginRequest;
import spring_group1.com.model.response.ApiRespone;
import spring_group1.com.services.AppUserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor

public class AuthController {
// update
    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiRespone> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        String token =jwtUtils.generateToken(loginRequest.getEmail());

        ApiRespone apiRespone = ApiRespone.builder()
                .success(true)
                .message("login sucess ")
                .status(HttpStatus.OK)
                .timestamp(LocalDate.now())
                .payload(token).build();

        return ResponseEntity.ok(apiRespone);
        
    }


//    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<Object> registerg(@Valid @RequestBody AppUserRequest appUserRequest) {
        AppUser appUser = appUserService.createAppUser(appUserRequest);

        ApiRespone<Object> response = ApiRespone.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Success register ! ")
                .timestamp(LocalDate.now())
                .payload(appUser)
                .build();

        return ResponseEntity.ok(response);
    }
}


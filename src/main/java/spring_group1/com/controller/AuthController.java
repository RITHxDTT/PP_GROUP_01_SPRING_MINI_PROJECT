package spring_group1.com.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import spring_group1.com.model.request.ResendRequest;
import spring_group1.com.model.request.VerifyRequest;
import spring_group1.com.model.response.ApiResponse;
import spring_group1.com.model.response.AppUserResponse;
import spring_group1.com.model.response.AuthResponse;
import spring_group1.com.services.AppUserService;
import spring_group1.com.services.EmailService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor

public class AuthController {

    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

//        System.out.println("Login Email: " + loginRequest.getEmail());
//        System.out.println("Login Password: " + loginRequest.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        String token = jwtUtils.generateToken(loginRequest.getEmail());

        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .success(true)
                .title("Login Success")
                .message("login successful. Authentication token generated!")
                .status(HttpStatus.OK)
                .timestamp(LocalDate.now())
                .payload(authResponse)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody AppUserRequest appUserRequest) {
        AppUserResponse appUser = appUserService.createAppUser(appUserRequest);

        ApiResponse<Object> response = ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .title("Registration successful")
                .message("Successfully register!")
                .timestamp(LocalDate.now())
                .payload(appUser)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verify(@Valid @RequestBody VerifyRequest request) {

        appUserService.verifyOtp(request.getEmail(), request.getOtp());

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .title("Verification successful")
                .message("Account verified successfully!")
                .status(HttpStatus.OK)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend")
    public ResponseEntity<ApiResponse> resend(@Valid @RequestBody ResendRequest request) {

        appUserService.resendOtp(request.getEmail());

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .title(" OTP Resend successful")
                .message("OTP resend successfully, Please check your email.")
                .status(HttpStatus.OK)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.ok(response);
    }


}


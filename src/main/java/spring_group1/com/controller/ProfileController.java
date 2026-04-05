package spring_group1.com.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.request.ProfileRequest;
import spring_group1.com.model.response.ApiRespone;
import spring_group1.com.model.response.ProfileResponse;
import spring_group1.com.services.AppUserService;
import spring_group1.com.utils.SecurityUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {

    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<ApiRespone<ProfileResponse>> getProfile() {
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        ProfileResponse profile = appUserService.getUserProfile(currentUserEmail);

        ApiRespone<ProfileResponse> response = ApiRespone.<ProfileResponse>builder()
                .success(true)
                .message("Profile fetched successfully")
                .status(HttpStatus.OK)
                .payload(profile)
                .timestamp(LocalDate.from(LocalDateTime.now()))
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiRespone<ProfileResponse>> deleteProfile(){
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
                appUserService.deleteProfile(currentUserEmail);

        ApiRespone<ProfileResponse> response = ApiRespone.<ProfileResponse>builder()
                .success(true)
                .message("Profile deleted successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .timestamp(LocalDate.from(LocalDateTime.now()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiRespone<ProfileResponse>> updateProfile(@RequestBody ProfileRequest profileRequest){
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        ProfileResponse profileResponse = appUserService.updateProfile(currentUserEmail, profileRequest);

        ApiRespone<ProfileResponse> response = ApiRespone.<ProfileResponse>builder()
                .success(true)
                .message("Profile updated successfully")
                .status(HttpStatus.OK)
                .payload(profileResponse)
                .timestamp(LocalDate.from(LocalDateTime.now()))
                .build();

        return ResponseEntity.ok(response);
    }
}

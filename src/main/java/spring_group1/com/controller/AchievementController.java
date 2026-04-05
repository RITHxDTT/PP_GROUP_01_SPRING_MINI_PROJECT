package spring_group1.com.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.Achievements;

import spring_group1.com.model.response.ApiResponse;
import spring_group1.com.services.AchievementService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;
    private final AuthenticationManager authenticationManager;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<?> getAllAchievements(@RequestParam @Positive(message = "Page can't be negative and zero") Integer page, @RequestParam @Positive(message = "size can't be negative and zero") Integer size){
        List<Achievements> achievements = achievementService.getAllAchievements(page,size);

        ApiResponse apiRespone = ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Get All achievements successfully")
                .payload(achievements)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiRespone);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/app-users/{userId}")
    public ResponseEntity<?> getAllAchievementsForUser(
            @PathVariable Integer userId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {

        List<Achievements> achievements =
                achievementService.getAllAchievementForUser(userId, page, size);

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Achievements for user retrieved successfully!")
                .payload(achievements)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.ok(response);
    }


}

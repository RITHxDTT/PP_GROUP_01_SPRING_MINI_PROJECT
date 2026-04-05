package spring_group1.com.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.ApiRespone;
import spring_group1.com.model.response.HabitLogResponse;
import spring_group1.com.services.HabitLogService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ApiRespone<HabitLogResponse>> create(@RequestBody HabitLogRequest request
    , Authentication authentication) {

        HabitLogResponse createdLog = habitLogService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiRespone.<HabitLogResponse>builder()
                        .message("Habit log created successfully.")
                        .status(HttpStatus.CREATED)
                        .payload(createdLog)
                        .build()
        );
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{habitId}")
    public ResponseEntity<ApiRespone<List<HabitLogResponse>>> getHabitLogsById(@PathVariable Integer habitId
    , Authentication authentication, Integer page, Integer size) {
        System.out.println(authentication.getPrincipal());

        List<HabitLogResponse> logs = habitLogService.getLogsByHabitId(habitId, page, size);

        return ResponseEntity.ok(
                ApiRespone.<List<HabitLogResponse>>builder()
                        .status(HttpStatus.OK)
                        .payload(logs)
                        .timestamp(LocalDate.now())
                        .build()

        );
    }


    }

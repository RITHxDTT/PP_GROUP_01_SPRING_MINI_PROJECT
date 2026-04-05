package spring_group1.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.ApiRespone;
import spring_group1.com.model.response.HabitLogResponse;
import spring_group1.com.services.HabitLogService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @PostMapping
    public ResponseEntity<ApiRespone<HabitLogResponse>> create(@RequestBody HabitLogRequest request){
        HabitLogResponse createdLog = habitLogService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiRespone.<HabitLogResponse>builder()
                        .message("Habit log created successfully.")
                        .status(HttpStatus.valueOf(201))
                        .payload(createdLog)
                        .build()
        );
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<ApiRespone<List<HabitLogResponse>>> getHabitLogsById(@PathVariable Integer habitId) {

        List<HabitLogResponse> logs = habitLogService.getLogsByHabitId(habitId);

        return ResponseEntity.ok(
                ApiRespone.<List<HabitLogResponse>>builder()
                        .status(HttpStatus.OK)
                        .payload(logs)
                        .timestamp(LocalDate.now())
                        .build()

        );
    }


    }

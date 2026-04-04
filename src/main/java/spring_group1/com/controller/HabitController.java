package spring_group1.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.Habit;
import spring_group1.com.response.ApiRespone;
import spring_group1.com.services.HabitService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public ResponseEntity<ApiRespone<List<Habit>>> getAllHabit(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiRespone.<List<Habit>>builder()
                        .success(true)
                        .message("Fetched all habits successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.getAllHabit(page, size))
                        .build()
        );
    }

    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiRespone<Habit>> getHabitById(@PathVariable("habit-id") Integer habitId){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiRespone.<Habit>builder()
                        .success(true)
                        .message("Habit ID "+habitId+" fetched successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.getHabitById(habitId))
                        .build()
        );
    }


}

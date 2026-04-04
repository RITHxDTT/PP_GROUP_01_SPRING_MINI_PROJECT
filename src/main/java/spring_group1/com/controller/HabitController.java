package spring_group1.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_group1.com.model.Habit;
import spring_group1.com.request.HabitRequest;
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

    @PostMapping
    public ResponseEntity<ApiRespone<Habit>> createHabit(@RequestBody HabitRequest habitRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiRespone.<Habit>builder()
                        .success(true)
                        .message("Habit "+habitRequest.getTitle()+" created successfully!")
                        .status(HttpStatus.CREATED)
                        .payload(habitService.createhabit(habitRequest))
                        .build()
        );
    }

    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiRespone<Habit>> updateHabit(@PathVariable("habit-id") Integer habitId, @RequestParam HabitRequest habitRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiRespone.<Habit>builder()
                        .success(true)
                        .message("Habit ID "+habitId+" created successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.updateHabit(habitId, habitRequest))
                        .build()
        );
    }

    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiRespone<Habit>> deleteHabit(@PathVariable("habit-id") Integer habitId){

        boolean deleteHabit = habitService.deleteHabit(habitId);

        if(deleteHabit){
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiRespone.<Habit>builder()
                            .success(true)
                            .message("Habit ID " + habitId + " deleted successfully!")
                            .status(HttpStatus.OK)
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiRespone.<Habit>builder()
                            .success(false)
                            .message("Habit ID " + habitId + " not found!")
                            .status(HttpStatus.NOT_FOUND)
                            .build()
            );
        }
    }



}

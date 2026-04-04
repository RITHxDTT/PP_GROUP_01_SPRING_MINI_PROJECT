package spring_group1.com.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Habit>  getAllHabit(@RequestParam Integer page, @RequestParam Integer size){
        return habitService.getAllHabit(page , size);
    }


}

package spring_group1.com.services;

import org.springframework.stereotype.Service;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.HabitLogResponse;

import java.util.List;

@Service
public interface HabitLogService {

    HabitLogResponse create(HabitLogRequest request);


    List<HabitLogResponse> getLogsByHabitId(Integer habitId, Integer page, Integer size);
}

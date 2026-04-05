package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.model.HabitLog;
import spring_group1.com.model.HabitLogStatus;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.HabitLogResponse;
import spring_group1.com.repository.HabitLogRepository;
import spring_group1.com.services.HabitLogService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final  HabitLogRepository habitLogRepository;

    @Override
    public HabitLogResponse create(HabitLogRequest request) {
        request.setLogDate(LocalDate.now());

        if (request.getStatus() == HabitLogStatus.COMPLETED) {
            request.setXpEarned(10);
        } else {
            request.setXpEarned(0);
        }
        habitLogRepository.insert(request);

        return HabitLogResponse.builder()
                .habitLogId(request.getHabitLogId())
                .logDate(request.getLogDate())
                .status(request.getStatus())
                .habitId(request.getHabitId())
                .build();
    }

    @Override
    public List<HabitLogResponse> getLogsByHabitId(Integer habitId, Integer page, Integer size) {
        return habitLogRepository.findHabitLogById(habitId, page, size);
    }

}

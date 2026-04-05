package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.model.HabitLog;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.HabitLogResponse;
import spring_group1.com.repository.HabitLogRepository;
import spring_group1.com.services.HabitLogService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final  HabitLogRepository habitLogRepository;

    @Override
    public HabitLogResponse create(HabitLogRequest request) {

        HabitLog habitLog = new HabitLog();
        habitLog.setHabitLogId(UUID.randomUUID());
        habitLog.setLogDate(LocalDate.now());
        habitLog.setStatus(request.getStatus());
        habitLog.setXpEarned(habitLog.getXpEarned());
        habitLog.setHabitId(request.getHabitId());

        habitLogRepository.saveHabitLog(habitLog);

        HabitLogResponse response = new HabitLogResponse();
        response.setHabitLogId(habitLog.getHabitLogId());
        response.setLogDate(habitLog.getLogDate());
        response.setXpEarned(habitLog.getXpEarned());
        response.setHabitId(habitLog.getHabitId());

        return response;
    }

    @Override
    public List<HabitLogResponse> getLogsByHabitId(Integer habitId) {

        List<HabitLog> entities = habitLogRepository.selectByHabitId(habitId);
        List<HabitLogResponse> responses = new ArrayList<>();

        for (HabitLog entity : entities) {
            HabitLogResponse res = new HabitLogResponse();

            res.setHabitLogId(entity.getHabitLogId());
            res.setLogDate(entity.getLogDate());
            res.setXpEarned(entity.getXpEarned());
            res.setHabitId(entity.getHabitId());

            responses.add(res);
        }
    return responses;
    }


}

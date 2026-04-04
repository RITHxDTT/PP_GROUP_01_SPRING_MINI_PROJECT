package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import spring_group1.com.services.OtpService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String OTP_KEY = "OTP:";
    private static final String COOLDOWN_KEY = "OTP:cooldown:";


    @Override
    public void saveOtp(String email, String otp) {
        stringRedisTemplate.opsForValue().set(OTP_KEY + email, otp, Duration.ofMinutes(3));
    }

    @Override
    public String getOtp(String email) {
        return stringRedisTemplate.opsForValue().get(OTP_KEY + email);
    }

    @Override
    public void deleteOtp(String email) {
        stringRedisTemplate.delete(OTP_KEY + email);
    }

    @Override
    public boolean isCooldown(String email) {
        return stringRedisTemplate.hasKey(COOLDOWN_KEY + email);
    }

    @Override
    public void setCooldown(String email) {
        stringRedisTemplate.opsForValue().set(COOLDOWN_KEY + email, String.valueOf(System.currentTimeMillis()));
    }
}

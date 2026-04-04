package spring_group1.com.services;

public interface OtpService {

    void saveOtp(String email, String otp);
    String getOtp(String email);
    void deleteOtp(String email);
    boolean isCooldown(String email);
    void setCooldown(String email);
}

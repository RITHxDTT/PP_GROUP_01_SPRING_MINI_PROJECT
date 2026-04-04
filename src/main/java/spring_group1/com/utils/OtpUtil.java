package spring_group1.com.utils;

import java.util.Random;

public class OtpUtil {
    public static String generateOtp(){
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.valueOf(otp);
    }
}

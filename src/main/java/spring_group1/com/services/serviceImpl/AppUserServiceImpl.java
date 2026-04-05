package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.AccountAlreadyVerifiedException;
import spring_group1.com.exception.DuplicateEmailException;
import spring_group1.com.exception.EmailNotFound;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.response.AppUserResponse;
import spring_group1.com.repository.AppUserRepository;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.services.AppUserService;
import spring_group1.com.services.EmailService;
import spring_group1.com.services.OtpService;
import spring_group1.com.utils.OtpUtil;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final OtpService otpService;
    private final EmailService emailService;

    @Override
    public AppUserResponse createAppUser(AppUserRequest appUserRequest) {

        AppUser findByEmail = appUserRepository.findUserByEmail(appUserRequest.getEmail());
        if (findByEmail != null) {
            throw new DuplicateEmailException("Email already exists!");
        }

        AppUser findByName = appUserRepository.findUserByUsername(appUserRequest.getUserName());
        if (findByName != null) {
            throw new DuplicateEmailException("Username already exists!");
        }

        String hasPassword = passwordEncoder.encode(appUserRequest.getPassword());

        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setProfileImg(appUserRequest.getProfileImg());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(hasPassword);

        appUser.setIsVerified(false);
        appUser.setTimestamp(LocalDateTime.now());
        AppUser savedUser = appUserRepository.createAppUser(appUser);

        //OTP logic
        String otp = OtpUtil.generateOtp();
        otpService.saveOtp(savedUser.getEmail(), otp);
        otpService.setCooldown(savedUser.getEmail());
        emailService.sendOtp(savedUser.getEmail(), otp);

        AppUserResponse response = AppUserResponse.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .profileImg(savedUser.getProfileImg())
                .isVerified(savedUser.getIsVerified())
                .xp(savedUser.getXp())
                .level(savedUser.getLevel())
                .build();

        return response;
    }

    @Override
    public void verifyOtp(String email, String otp) {

        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null){
            throw new RuntimeException("User not found!");
        }
        if(Boolean.TRUE.equals(appUser.getIsVerified())){
            throw new RuntimeException("Account is already verified!");
        }

        // check OTP
        String savedOtp = otpService.getOtp(email);

        if(savedOtp == null){
            throw new RuntimeException("OTP expired or not found!");
        }

        if(!savedOtp.equals(otp)){
            throw new RuntimeException("Invalid OTP!");
        }

        // update DB
        appUserRepository.updateUserVerification(email);

        // delete OTP
        otpService.deleteOtp(email);
    }

    @Override
    public void resendOtp(String email) {

        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null){
            throw new RuntimeException("User not found!");
        }

        if (Boolean.TRUE.equals(appUser.getIsVerified())) {
            throw new AccountAlreadyVerifiedException("Account is already verified!");
        }

        if(otpService.isCooldown(email)){
            throw new RuntimeException("Please wait for 5 minutes before requesting new OTP.");
        }

        // generate new OTP
        String otp = otpService.getOtp(email);

        // save to Redis
        otpService.saveOtp(email, otp);

        // set Cooldown
        otpService.setCooldown(email);

        // send email
        emailService.sendOtp(email, otp);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        System.out.println("SPRING SEARCHING EMAIL: " + email);

        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null) {
         throw new EmailNotFound("Email not found");
        }

//        System.out.println("DB EMAIL: " + appUser.getEmail());
//        System.out.println("DB PASSWORD: " + appUser.getPassword());
//        System.out.println("IS VERIFIED: " + appUser.getIsVerified());

        if(!appUser.getIsVerified()) {
            throw new RuntimeException("User is not verified");
        }
        return appUser;
    }




}

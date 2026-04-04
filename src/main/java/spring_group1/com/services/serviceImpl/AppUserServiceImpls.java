package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.DuplicateEmailException;
import spring_group1.com.exception.EmailNotFound;
import spring_group1.com.model.AppUser;
import spring_group1.com.repository.AppUserRepository;
import spring_group1.com.request.AppUserRequest;
import spring_group1.com.services.AppUserService;
import spring_group1.com.services.EmailService;
import spring_group1.com.services.OtpService;
import spring_group1.com.utils.OtpUtil;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpls implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final OtpService otpService;
    private final EmailService emailService;

    @Override
    public AppUser createAppUser(AppUserRequest appUserRequest) {

        AppUser findByemail = appUserRepository.findUserByEmail(appUserRequest.getEmail());
        if (findByemail != null) {
            throw new DuplicateEmailException("Email already exists!");
        }

        AppUser findByname = appUserRepository.findUserByUsername(appUserRequest.getUserName());
        if (findByname != null) {
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
        appUserRepository.createAppUser(appUser);

        //OTP logic
        String otp = OtpUtil.generateOtp();
        otpService.saveOtp(appUser.getEmail(), otp);
        otpService.setCooldown(appUser.getEmail());

        emailService.sendOtp(appUser.getEmail(), otp);

        return appUser;
    }

    @Override
    public void verifyOtp(String email, String otp) {

        // get otp from Redis
        String savedOtp = otpService.getOtp(email);

        if(savedOtp == null){
            throw new RuntimeException("OTP expired or not found!");
        }

        if(!savedOtp.equals(otp)){
            throw new RuntimeException("Invalid OTP!");
        }

        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null){
            throw new RuntimeException("User not found!");
        }

        // verify user
        appUser.setIsVerified(true);
        appUserRepository.updateUserVerification(user);

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null) {
         throw new EmailNotFound("Email not found");
        }

        if(!appUser.getIsVerified()) {
            throw new RuntimeException("User is not verified");
        }
        return appUser;
    }




}

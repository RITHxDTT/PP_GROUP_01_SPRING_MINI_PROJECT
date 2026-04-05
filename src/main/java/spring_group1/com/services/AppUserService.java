package spring_group1.com.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.model.response.AppUserResponse;


public interface AppUserService extends UserDetailsService {

    AppUserResponse createAppUser(AppUserRequest appUserRequest);

   void verifyOtp(String email, String otp);

   void resendOtp(String email);



}

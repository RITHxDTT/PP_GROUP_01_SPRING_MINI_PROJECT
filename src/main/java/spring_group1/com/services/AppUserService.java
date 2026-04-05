package spring_group1.com.services;

//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.model.response.AppUserResponse;
import spring_group1.com.model.response.ProfileResponse;
import spring_group1.com.model.request.ProfileRequest;


public interface AppUserService extends UserDetailsService {

    AppUserResponse createAppUser(AppUserRequest appUserRequest);

   void verifyOtp(String email, String otp);

   void resendOtp(String email);


    ProfileResponse getUserProfile(String email);

    ProfileResponse deleteProfile(String email);

    ProfileResponse updateProfile(ProfileRequest profileRequest);


}

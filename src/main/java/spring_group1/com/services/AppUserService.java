package spring_group1.com.services;

//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.model.request.ProfileRequest;
import spring_group1.com.model.response.ProfileResponse;


public interface AppUserService extends UserDetailsService {
    AppUser createAppUser(AppUserRequest appUserRequest);

    ProfileResponse getUserProfile(String email);

    ProfileResponse deleteProfile(String email);

    ProfileResponse updateProfile(String email, ProfileRequest profileRequest);


}

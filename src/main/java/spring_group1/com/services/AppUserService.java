package spring_group1.com.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.request.AppUserRequest;


public interface AppUserService extends UserDetailsService {
    AppUser createAppUser(AppUserRequest appUserRequest);


}

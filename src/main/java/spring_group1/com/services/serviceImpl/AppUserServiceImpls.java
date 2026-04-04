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
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.services.AppUserService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpls implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser createAppUser(AppUserRequest appUserRequest) {

        AppUser findByemail = appUserRepository.findUserByEmail(appUserRequest.getEmail());
        if(findByemail != null){
            throw new DuplicateEmailException("That Email Already exist! ");
        }

        AppUser findByname = appUserRepository.findUserByUsername(appUserRequest.getUserName());
        if(findByname != null){
            throw new DuplicateEmailException("That Username Already exist! ");
        }


        String hasPassword = passwordEncoder.encode(appUserRequest.getPassword());

        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setProfileImg(appUserRequest.getProfileImg());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(hasPassword);
        appUser.setIsVerified(true);
        appUser.setTimestamp(LocalDateTime.now());
        appUser.getLevel();
        appUser.getXp();
        appUserRepository.createAppUser(appUser);



        return appUser;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null) {
         throw new EmailNotFound("Email not found");
        }

        assert appUser != null;

        return appUser;
    }


}

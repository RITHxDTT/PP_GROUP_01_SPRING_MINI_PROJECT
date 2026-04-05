package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.DuplicateEmailException;
import spring_group1.com.exception.DuplicateName;
import spring_group1.com.exception.EmailNotFound;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.request.AppUserRequest;
import spring_group1.com.model.request.ProfileRequest;
import spring_group1.com.model.response.ProfileResponse;
import spring_group1.com.repository.AppUserRepository;
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
//        appUser.getCreatedAt();
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


    @Override
    public ProfileResponse getUserProfile(String email) {
        AppUser appUser = appUserRepository.findUserByEmail(email);
        if (appUser == null) {
            throw new EmailNotFound("User not found");
        }

        return ProfileResponse.builder()
                .appUserId(String.valueOf(appUser.getUserId()))
                .username(appUser.getUserName())
                .email(appUser.getEmail())
                .level(appUser.getLevel())
                .xp(appUser.getXp())
                .profileImageUrl(appUser.getProfileImg())
                .isVerified(appUser.getIsVerified())
                .createdAt(appUser.getTimestamp())
                .build();
    }

    @Override
    public ProfileResponse deleteProfile(String email) {
        AppUser appUser = appUserRepository.findUserByEmail(email);
        if (appUser == null){
            throw new EmailNotFound("User not found");
        }
        return  appUserRepository.deleteProfile(email);
    }


    @Override
    public ProfileResponse updateProfile(String email, ProfileRequest profileRequest) {
        AppUser user = appUserRepository.findUserByEmail(email);
        if (user == null) {
            throw new EmailNotFound("User not found");
        }
        if (profileRequest.getUsername() != null && !profileRequest.getUsername().trim().isEmpty()) {
            AppUser existingUser = appUserRepository.findUserByUsername(profileRequest.getUsername());
            if (existingUser != null && existingUser.getUserId() != user.getUserId()) {
                throw new DuplicateName("Username ng mean hx");
            }
            user.setUserName(profileRequest.getUsername());
        }
        if (profileRequest.getProfileImageUrl() != null && !profileRequest.getProfileImageUrl().trim().isEmpty()) {
            user.setProfileImg(profileRequest.getProfileImageUrl());
        }
        appUserRepository.updateProfile(user);
        return mapToProfileResponse(user);
    }
    private  ProfileResponse mapToProfileResponse(AppUser user) {
        return ProfileResponse.builder()
                .appUserId(String.valueOf(user.getUserId()))
                .username(user.getUserName())
                .email(user.getEmail())
                .level(user.getLevel())
                .xp(user.getXp())
                .profileImageUrl(user.getProfileImg())
                .isVerified(user.getIsVerified())
                .createdAt(user.getTimestamp())
                .build();
    }


}

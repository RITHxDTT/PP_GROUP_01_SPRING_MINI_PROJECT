package spring_group1.com.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import spring_group1.com.model.AppUser;

public class SecurityUtils {
    public static String getCurrentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public static AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Integer getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the principal is an instance of your AppUser model
//        if (principal instanceof AppUser) {
            return ((AppUser) principal).getUserId();
//        }
//
//        throw new RuntimeException("User not authenticated or session expired");
    }

}

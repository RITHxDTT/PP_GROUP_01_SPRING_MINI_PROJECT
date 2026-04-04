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
            return ((AppUser) principal).getUserId();
    }
}

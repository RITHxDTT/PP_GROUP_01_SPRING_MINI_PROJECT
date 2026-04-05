package spring_group1.com.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import spring_group1.com.model.AppUser;

public class SecurityUtils {
    public static String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

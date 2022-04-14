package com.example.musify;

import com.example.musify.model.User;
import com.example.musify.security.JwtUtils;

public class UserUtils {
    public static boolean isCurrentAdmin() {
        return JwtUtils.getCurrentUserRole().equals("admin");
    }

    public static boolean isAdmin(User user) {
        return user.getRole().equals("admin");
    }

    public static boolean isActive(User user) {
        return user.getStatus().equals("active");
    }

    public static boolean canLogin(User user, String encryptedPassword) {
        return user.getEncryptedPassword().equals(encryptedPassword);
    }

    public static boolean isOperationOnSelf(Integer id) {
        return (JwtUtils.getCurrentUserId().intValue() == id.intValue());
    }
}

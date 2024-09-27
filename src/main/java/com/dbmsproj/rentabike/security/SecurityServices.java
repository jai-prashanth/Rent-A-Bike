package com.dbmsproj.rentabike.security;

import com.dbmsproj.rentabike.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SecurityServices {

    public String findLoggedInUsername() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (myUserDetails instanceof securityuserdetails) {
            return ((securityuserdetails) myUserDetails).getUser().getUsername();
        }
        return null;
    }

    public User findLoggedInUser() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (myUserDetails instanceof securityuserdetails) {
            return ((securityuserdetails) myUserDetails).getUser();
        }
        return null;
    }

}
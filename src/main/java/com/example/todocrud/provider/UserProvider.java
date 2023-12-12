package com.example.todocrud.provider;

import org.springframework.stereotype.Component;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails; 

@Component
class SecurityContextHolderUserProvider implements ConnectionUsernameProvider {
     @Override
     public String getUserName() {
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         
         if (principal instanceof UserDetails) {
             return ((UserDetails)principal).getUsername();
         } else {
             return principal.toString();
         }
     }
}

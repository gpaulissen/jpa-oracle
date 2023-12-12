package com.example.todocrud.provider;

import org.springframework.stereotype.Component;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.web.bind.annotation.*;

@Component
class SecurityContextHolderUserProvider implements ConnectionUsernameProvider {
     @Override
     public String getUserName() {
         Object principal = SecurityContextHolder.context.authentication.principal;
         
         if (principal instanceof UserDetails) {
             return ((UserDetails)principal).getUsername();
         } else {
             return principal.toString();
         }
     }
}

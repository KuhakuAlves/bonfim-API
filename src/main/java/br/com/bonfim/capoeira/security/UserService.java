package br.com.bonfim.capoeira.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserDetailsSecurity authenticated() {
        try {
            return (UserDetailsSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}

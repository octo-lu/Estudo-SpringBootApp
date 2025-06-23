package com.example.nobsv2.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<CustomUser> optionalUser = customUserRepository.findById(username);


        //this is where you can add roles and authorities to the user
        //relational mapping to get roles and authorities

        if(optionalUser.isPresent()){
            return User.withUsername(optionalUser.get().getUsername())
                    .password(optionalUser.get().getPassword())
                    .roles(optionalUser.get().getRole()).build();
        }

        throw new RuntimeException("User not found");
    }
}

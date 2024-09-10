package com.chronix.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chronix.model.User;
import com.chronix.repository.UserRepository;

@Service
public class CustomUserDetailsImplementation implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
            user.getPassword(), authorities);
    }
}

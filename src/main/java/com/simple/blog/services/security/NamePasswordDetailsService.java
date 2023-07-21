package com.simple.blog.services.security;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.simple.blog.repositories.UserRepository;

@Component
public class NamePasswordDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userOpt = userRepository.findByUsername(username);
       if (userOpt.isEmpty()) {
          throw new UsernameNotFoundException(MessageFormat.format("User with username {0} not found", username));
       }

       var user = userOpt.get();
       return User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
    }
    
}

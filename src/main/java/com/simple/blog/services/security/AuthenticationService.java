package com.simple.blog.services.security;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.simple.blog.models.User;
import com.simple.blog.models.dtos.RegisterDTO;
import com.simple.blog.repositories.UserRepository;
import com.simple.blog.repositories.records.UserRecord;
import com.simple.blog.ultilities.CommonFunction;

import lombok.extern.java.Log;

@Component
@Log
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonFunction commonFunction;

    @Autowired
	private PasswordEncoder passwordEncoder;

    public void registerNewUser(RegisterDTO registerDTO) {
         var dateTime = commonFunction.generateTimestampForNewObject();

         var newUser = new User(CommonFunction.DEFAULT_FAKE_ID_FOR_AUTO_GENERATED_CLASS, registerDTO.getName(), registerDTO.getEmail(), passwordEncoder.encode(registerDTO.getPassword()), dateTime, dateTime);
         var newUserRecord = new UserRecord(newUser);
         var savedUser = userRepository.save(newUserRecord);

         log.info(MessageFormat.format("Saved new user successfully with ID {0}", savedUser.getId()));
    }

    public String getCurrentLoggedInUsername() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
    
}

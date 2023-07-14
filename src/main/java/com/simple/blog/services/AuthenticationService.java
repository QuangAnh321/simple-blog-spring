package com.simple.blog.services;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.simple.blog.models.User;
import com.simple.blog.models.dtos.LoginDTO;
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

         var newUser = new User(CommonFunction.DEFAULT_FAKE_ID_FOR_AUTO_GENERATED_CLASS, registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPassword(), dateTime, dateTime);
         var newUserRecord = new UserRecord(newUser);
         var savedUser = userRepository.save(newUserRecord);

         log.info(MessageFormat.format("Saved new user successfully with ID {0}", savedUser.getId()));
    }

    public boolean logUserIn(LoginDTO loginDTO) {
        var isUserExist = userRepository.findByUsername(loginDTO.getUsername()).isPresent();
        if (isUserExist) {
            var userInDB = new User(userRepository.findByUsername(loginDTO.getUsername()).get());
            var isPasswordMatch = userInDB.getPassword() == passwordEncoder.encode(loginDTO.getPassword());
            if (isPasswordMatch) {
                log.info(MessageFormat.format("The password is matched, start logining for user with username: ${0}", userInDB.getUsername()));
                return true;
            } else {
                log.warning(MessageFormat.format("Login for username ${0} failed because: The password does not match", userInDB.getUsername()));
                return false;
            }
        } else {
            log.warning(MessageFormat.format("Login for username ${0} failed because: The user with that name cannot be found", loginDTO.getUsername()));
            return false;
        }
    }
}

package com.simple.blog.models;

import java.time.LocalDateTime;

import com.simple.blog.repositories.records.UserRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(UserRecord userRecord) {
        this.id = userRecord.getId();
        this.username = userRecord.getUsername();
        this.email = userRecord.getEmail();
        this.password = userRecord.getPassword();
        this.createdAt = userRecord.getCreatedAt();
        this.updatedAt = userRecord.getUpdatedAt();
    }
}

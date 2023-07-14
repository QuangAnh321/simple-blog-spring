package com.simple.blog.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

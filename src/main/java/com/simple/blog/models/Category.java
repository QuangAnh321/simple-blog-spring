package com.simple.blog.models;

import java.time.LocalDateTime;

import com.simple.blog.repositories.records.CategoryRecord;

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

    public Category(CategoryRecord record) {
        this.id = record.getId();
        this.name = record.getName();
        this.createdAt = record.getCreatedAt();
        this.updatedAt = record.getUpdatedAt();
    }
    
}

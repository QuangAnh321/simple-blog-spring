package com.simple.blog.models;

import java.time.LocalDateTime;

import com.simple.blog.repositories.records.BlogRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    
    private long id;
    private String title;
    private String content;
    private long categoryId;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Blog(BlogRecord blogRecord) {
        this.id = blogRecord.getId();
        this.title = blogRecord.getTitle();
        this.content = blogRecord.getContent();
        this.categoryId = blogRecord.getCategoryId();
        this.userId = blogRecord.getUserId();
        this.createdAt = blogRecord.getCreatedAt();
        this.updatedAt = blogRecord.getUpdatedAt();
    }
}

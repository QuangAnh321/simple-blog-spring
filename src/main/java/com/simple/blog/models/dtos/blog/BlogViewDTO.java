package com.simple.blog.models.dtos.blog;

import java.time.LocalDateTime;

import com.simple.blog.models.Blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogViewDTO {

    private long id;
    private String title;
    private String content;
    private long categoryId;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public BlogViewDTO(Blog blog, String userName) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.categoryId = blog.getCategoryId();
        this.userName = userName;
        this.createdAt = blog.getCreatedAt();
        this.updatedAt = blog.getUpdatedAt();
    }
}

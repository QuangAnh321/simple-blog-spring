package com.simple.blog.models.dtos.blog;

import com.simple.blog.models.Blog;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogUpdateFormInfo {
    
    private Blog blog;
    private BlogViewDTO blogViewDTO;
    private String username;
}

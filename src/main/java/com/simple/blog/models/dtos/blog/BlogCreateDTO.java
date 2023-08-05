package com.simple.blog.models.dtos.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateDTO {
     
    private String title;
    private String content;
    private long categoryId;
    private String userName;
    
}
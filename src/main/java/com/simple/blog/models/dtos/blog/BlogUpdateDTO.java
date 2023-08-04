package com.simple.blog.models.dtos.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateDTO {
    
    private String newTitle;
    private String newContent;
}

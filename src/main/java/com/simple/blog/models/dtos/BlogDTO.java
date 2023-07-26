package com.simple.blog.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
     
    private String title;
    private String content;
    private long categoryId;
    private String userName;
}

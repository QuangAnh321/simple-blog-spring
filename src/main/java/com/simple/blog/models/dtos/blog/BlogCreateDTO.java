package com.simple.blog.models.dtos.blog;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateDTO {
     
    @NotBlank(message = "Blog title cannot be empty")
    private String title;
    private String content;
    private String contentSummary;
    private String contentFormatted;
    private long categoryId;
    private String userName;
    
}

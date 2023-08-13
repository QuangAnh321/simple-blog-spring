package com.simple.blog.models.dtos.blog;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateDTO {
    
    @NotBlank(message = "Blog title cannot be empty")
    private String newTitle;
    private String newContent;
    private String newContentSummary;
    private String newContentFormatted;
}

package com.simple.blog.models;

import java.time.LocalDateTime;

import com.simple.blog.models.dtos.blog.BlogUpdateDTO;
import com.simple.blog.repositories.records.BlogRecord;
import com.simple.blog.ultilities.CommonFunction;

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
    private String contentSummary;
    private String contentFormatted;
    private long categoryId;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Blog(BlogRecord blogRecord) {
        this.id = blogRecord.getId();
        this.title = blogRecord.getTitle();
        this.content = blogRecord.getContent();
        this.contentSummary = blogRecord.getContentSummary();
        this.contentFormatted = blogRecord.getContentFormatted();
        this.categoryId = blogRecord.getCategoryId();
        this.userId = blogRecord.getUserId();
        this.createdAt = blogRecord.getCreatedAt();
        this.updatedAt = blogRecord.getUpdatedAt();
    }

    public void update(BlogUpdateDTO blogUpdateDTO, CommonFunction commonFunction) {
        this.setTitle(blogUpdateDTO.getNewTitle());
        this.setContent(blogUpdateDTO.getNewContent());
        this.setContentSummary(blogUpdateDTO.getNewContentSummary());
        this.setContentFormatted(blogUpdateDTO.getNewContentFormatted());
        this.setUpdatedAt(commonFunction.generateTimestampForNewObject());
    }
}

package com.simple.blog.repositories.records;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.simple.blog.models.Blog;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = BlogRecord.TABLE_NAME)
public class BlogRecord {

    private static final String TABLE_NAME = "blogs";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String contentSummary;
    private long categoryId;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BlogRecord(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.contentSummary = blog.getContentSummary();
        this.categoryId = blog.getCategoryId();
        this.userId = blog.getUserId();
        this.createdAt = blog.getCreatedAt();
        this.updatedAt = blog.getUpdatedAt();
    }
    
}

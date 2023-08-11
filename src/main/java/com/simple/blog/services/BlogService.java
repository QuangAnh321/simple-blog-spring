package com.simple.blog.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.blog.models.Blog;
import com.simple.blog.models.Category;
import com.simple.blog.models.dtos.blog.BlogCreateDTO;
import com.simple.blog.models.dtos.blog.BlogUpdateDTO;
import com.simple.blog.repositories.BlogRepository;
import com.simple.blog.repositories.CategoryRepository;
import com.simple.blog.repositories.UserRepository;
import com.simple.blog.repositories.records.BlogRecord;
import com.simple.blog.repositories.records.CategoryRecord;
import com.simple.blog.ultilities.CommonFunction;

import lombok.extern.java.Log;

@Component
@Log
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommonFunction commonFunction;

    @Autowired
    private UserRepository userRepository;
    
    public ArrayList<Blog> getAllBlogs() {
        var blogs = new ArrayList<Blog>();
        var blogRecords = blogRepository.findAll();
        for (BlogRecord record : blogRecords) {
            blogs.add(new Blog(record));
        }

        log.info(MessageFormat.format("Retrieved {0} blogs from the database", blogs.size()));
        return blogs;
    }

    public ArrayList<Category> getAllCategories() {
        var categories = new ArrayList<Category>();
        var categoryRecords = categoryRepository.findAll();
        for (CategoryRecord record : categoryRecords) {
            categories.add(new Category(record.getId(), record.getName(), record.getCreatedAt(), record.getUpdatedAt()));
        }

        log.info(MessageFormat.format("Retrieved {0} categories from the database", categories.size()));
        return categories;
    }

    public void createNewBlog(BlogCreateDTO blogDTO) {
        var dateTime = commonFunction.generateTimestampForNewObject();
        var userId = userRepository.findByUsername(blogDTO.getUserName()).get().getId();

        var newBlog = new Blog(CommonFunction.DEFAULT_FAKE_ID_FOR_AUTO_GENERATED_CLASS, blogDTO.getTitle(),
           blogDTO.getContent(), blogDTO.getContentSummary() ,
           blogDTO.getCategoryId(), userId, dateTime, dateTime);
        
        var newwBlogRecord = new BlogRecord(newBlog);
        var savedBlog = blogRepository.save(newwBlogRecord);
        log.info(MessageFormat.format("Saved new blog successfully with ID {0}", savedBlog.getId()));
    }

    public Blog retrieveSingleBlogById(long blogId) throws Exception {
        var blogRecordOpt = blogRepository.findById(blogId);
        if (blogRecordOpt.isPresent()) {
            var blogRecord = blogRecordOpt.get();
            var blog = new Blog(blogRecord);
            log.info(MessageFormat.format("Retrieved blog with id {0} successfully", blogId));

            return blog;
        } else {
            throw new Exception(MessageFormat.format("Blog with id {0} cannot be found", blogId));
        }
    }

    public boolean isUserCanUpdateOrDeleteThisBlog(Blog blog, String username) throws Exception {
        var user = userRepository.findByUsername(username);
        return blog.getUserId() == user.get().getId();
    }

    public Optional<Category> getCategoryInfoForBlog(Blog blog) {
        return categoryRepository
                .findById(blog.getCategoryId())
                .map(categoryRecord -> new Category(categoryRecord.getId(), categoryRecord.getName(), categoryRecord.getCreatedAt(), categoryRecord.getUpdatedAt()));
    }

    public void updateBlog(BlogUpdateDTO blogUpdateDTO, Long blogId) throws Exception {
        var blogToUpdate = retrieveSingleBlogById(blogId);
        blogToUpdate.update(blogUpdateDTO, commonFunction);
        blogRepository.save(new BlogRecord(blogToUpdate));
    }

    public void deleteBlog(Long blogId) throws Exception {
        blogRepository.deleteById(blogId);
    }
}

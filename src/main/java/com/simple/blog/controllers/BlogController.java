package com.simple.blog.controllers;

import java.text.MessageFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simple.blog.models.dtos.blog.BlogCreateDTO;
import com.simple.blog.models.dtos.blog.BlogUpdateDTO;
import com.simple.blog.models.dtos.blog.BlogUpdateFormInfo;
import com.simple.blog.models.dtos.blog.BlogViewDTO;
import com.simple.blog.services.BlogService;
import com.simple.blog.services.security.AuthenticationService;

import lombok.extern.java.Log;

@Controller
@Log
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String home(Model model) {
        var allBlogs = blogService.getAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        return "home";
    }

    @GetMapping("/blog/create")
    public String showCreateBlogForm(Model model) {
        var allCategories = blogService.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("blogCreateDTO", new BlogCreateDTO());
        return "createBlog";
    }

    @PostMapping("/blog/create")
    public String createBlog(@Valid @ModelAttribute BlogCreateDTO blogCreateDTO, BindingResult bindingResult, Model model) {
        var allCategories = blogService.getAllCategories();
        if (bindingResult.hasErrors()) {
            model.addAttribute("allCategories", allCategories);
            return "createBlog";
        } else {
            blogService.createNewBlog(blogCreateDTO);
            return "redirect:/";
        }
    }

    @GetMapping("/blog/{id}")
    public String viewSingleBlog(@PathVariable("id") String blogId, Model model) {
        try {
            var userName = authenticationService.getCurrentLoggedInUsername();
            var blog = blogService.retrieveSingleBlogById(Long.parseLong(blogId));
            var blogToDisplayDTO = new BlogViewDTO(blog, userName);

            model.addAttribute("blog", blogToDisplayDTO);
            return "singleBlog";
        } catch (Exception ex) {
            return "404";
        }
    }

    @GetMapping("/blog/update/{id}")
    public String showUpdateBlogForm(@PathVariable("id") String blogId, Model model) {
        try {
            var blogUpdateFormInfo = createBlogUpdateFormInfo(authenticationService, blogId);
            var categoryOfBlogToUpdateOpt = blogService.getCategoryInfoForBlog(blogUpdateFormInfo.getBlog());

            if (categoryOfBlogToUpdateOpt.isPresent()) {
                if (blogService.isUserCanUpdateOrDeleteThisBlog(blogUpdateFormInfo.getBlog(), blogUpdateFormInfo.getUsername())) {
                    model.addAttribute("blogUpdateDTO", new BlogUpdateDTO());
                    model.addAttribute("blogViewDTO", blogUpdateFormInfo.getBlogViewDTO());
                    model.addAttribute("categoryOfBlogToUpdate", categoryOfBlogToUpdateOpt.get());
                    return "updateBlog";
                } else {
                    return "403";
                }
            } else {
                log.severe(MessageFormat.format("Category info of blog id {0} cannot be found", blogId));
                return "500";
            }
        } catch (Exception ex) {
            log.severe(MessageFormat.format("Error happened while trying to show update form of blog id {0}", blogId));
            ex.printStackTrace();

            return "500";
        }
    }

    @PostMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable("id") String blogId, @Valid @ModelAttribute BlogUpdateDTO blogToUpdateNewContent,
        BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                var blogUpdateFormInfo = createBlogUpdateFormInfo(authenticationService, blogId);
                var categoryOfBlogToUpdateOpt = blogService.getCategoryInfoForBlog(blogUpdateFormInfo.getBlog());
                model.addAttribute("blogViewDTO", blogUpdateFormInfo.getBlogViewDTO());
                model.addAttribute("categoryOfBlogToUpdate", categoryOfBlogToUpdateOpt.get());
                return "updateBlog";
            } else {
                var blogIdLong = Long.parseLong(blogId);
                blogService.updateBlog(blogToUpdateNewContent, blogIdLong);
                return "redirect:/blog/" + blogIdLong;
            }
        } catch (Exception ex) {
            log.severe(MessageFormat.format("Error happened while trying to update blog with id {0}", blogId));
            ex.printStackTrace();

            return "500";
        }     
    }

    private BlogUpdateFormInfo createBlogUpdateFormInfo(AuthenticationService authenticationService, String blogId) throws Exception {
        var userName = authenticationService.getCurrentLoggedInUsername();
        var blog = blogService.retrieveSingleBlogById(Long.parseLong(blogId));
        var blogToUpdateDTO = new BlogViewDTO(blog, userName);
        return new BlogUpdateFormInfo(blog, blogToUpdateDTO, userName);
    } 

    @PostMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable("id") String blogId) {
        try {
            var userName = authenticationService.getCurrentLoggedInUsername();
            var blog = blogService.retrieveSingleBlogById(Long.parseLong(blogId));

            if (blogService.isUserCanUpdateOrDeleteThisBlog(blog, userName)) {
                blogService.deleteBlog(blog.getId());
                return "redirect:/";
            } else {
                return "403";
            }
        } catch (Exception ex) {
            log.severe(MessageFormat.format("Error happened while trying to delete blog id {0}", blogId));
            ex.printStackTrace();

            return "500";
        }
    }
}

package com.simple.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simple.blog.models.dtos.BlogDTO;
import com.simple.blog.services.BlogService;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String home(Model model) {
        var allBlogs = blogService.getAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        return "home";
    }

    @GetMapping("/blog/create")
    public String showCreateBlogForm(BlogDTO blog, Model model) {
        var allCategories = blogService.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "createBlog";
    }

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute BlogDTO blogDTO, Model model) {
        blogService.createNewBlog(blogDTO);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}")
    public String viewSingleBlog(@PathVariable("id") String blogId, Model model) {
        try {
            var blog = blogService.retrieveSingleBlogById(Long.parseLong(blogId));
            model.addAttribute("blog", blog);
            return "singleBlog";
        } catch (Exception ex) {
            return "redirect:/404";
        }
    }
}

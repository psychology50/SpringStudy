package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.BlogForm;
import Likelion.SpringStudy.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/blogs/create")
    public String createForm(Model model) {
        model.addAttribute("blogForm", new BlogForm());
        return "blogs/blogCreate";
    }

    @PostMapping("/blogs/create")
    public String create(BlogForm form, Authentication authentication) {
        UserDomain userDomain = (UserDomain) authentication.getPrincipal();
        blogService.create(form, userDomain);
        return "redirect:/";
    }

    @GetMapping("/blogs/{blog_id}/receive")
    public String receive(@PathVariable(value="blog_id") String blog_id, Model model) {
        Blog blog = blogService.findBlog(Long.parseLong(blog_id)).get();
        model.addAttribute("blog", blog);
        return "blogs/blogReceive";
    }
}

package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.config.security.UserContext;
import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.BlogForm;
import Likelion.SpringStudy.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/blogs/create")
    public String createForm(Model model) {
        model.addAttribute("blogForm", new BlogForm());
        return "blogs/createBlog";
    }

    @PostMapping("/blogs/create")
    public String create(BlogForm form,
                         @AuthenticationPrincipal UserContext userContext) {
        blogService.create(form, userContext.getUser());
        return "redirect:/";
    }
}

package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/blogs/{blog_id}/posts/create")
    public String createForm(Model model, @PathVariable(value="blog_id") String blog_id) {
        model.addAttribute("postForm", new PostForm());
        return "blogs/posts/postCreate";
    }

    @PostMapping("/blogs/{blog_id}/posts/create")
    public String createForm(@PathVariable(value="blog_id") String blog_id, PostForm postForm) {
        postForm.setBlog_id(Long.parseLong(blog_id));
        postService.create(postForm);
        return "redirect:/blogs/blogReceive";
    }
}

package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/blogs/{blog_id}/posts/create")
    public String createForm(Model model, @PathVariable(value="blog_id") String blog_id) {
        PostForm postForm = new PostForm();
        postForm.setId(Long.parseLong(blog_id));
        model.addAttribute("postForm", postForm);

        return "blogs/posts/postCreate";
    }


}

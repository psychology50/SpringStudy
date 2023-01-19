package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Log4j2
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
    public RedirectView createForm(@PathVariable(value="blog_id") String blog_id, PostForm postForm) {
        postForm.setBlog_id(Long.parseLong(blog_id));
        log.info("!!!!!! controller post_id = {}", postForm.getId());
        log.info("!!!!!! controller blog_id = {}", postForm.getBlog_id());
        postService.create(postForm);
        return new RedirectView("/blogs/" + blog_id + "/receive");
    }

    @GetMapping("blogs/{blog_id}/posts/list")
    public String postList(@PathVariable(value="blog_id") Long blog_id, Model model) {
        List<Post> posts = postService.findAllByBlogId(blog_id);
        model.addAttribute("posts", posts);
        return "blogs/posts/postList";
    }

    @GetMapping("blogs/{blog_id}/posts/{post_id}/receive")
    public String receive(@PathVariable String blog_id,
                          @PathVariable String post_id,
                          Model model) {
        Post post = postService.findById(Long.parseLong(post_id));
        model.addAttribute("post", post);
        return "blogs/posts/postPage";
    }

    @GetMapping("blogs/{blog_id}/posts/{post_id}/update")
    public String update(@PathVariable String blog_id,
                         @PathVariable String post_id,
                         Model model) {
        Post post = postService.findById(Long.parseLong(post_id));
        model.addAttribute("post", post);
        model.addAttribute("postForm", new PostForm());
        return "blogs/posts/postUpdateForm";
    }

    @PostMapping("blogs/{blog_id}/posts/{post_id}/delete")
    public RedirectView postDelete(@PathVariable(value="blog_id") String blog_id,
                                   @PathVariable(value="post_id") Long post_id) {
        postService.deleteById(post_id);
        return new RedirectView("/blogs/" + blog_id + "/receive");
    }
}

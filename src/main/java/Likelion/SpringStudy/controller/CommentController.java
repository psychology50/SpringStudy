package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.CommentForm;
import Likelion.SpringStudy.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/blogs/{blog_id}/posts/{post_id}/comments/create")
    public RedirectView create(@PathVariable(value="blog_id") String blog_id,
                               @PathVariable(value="post_id") String post_id,
                               Authentication authentication,
                               CommentForm form) {
        log.info("authentication.getPrincipal() = {}", authentication.getPrincipal()); // Likelion.SpringStudy.domain.UserDomain@3c40da78
        log.info("authentication.getCredentials() = {}", authentication.getCredentials()); // null
        log.info("authentication.getDetails() = {}", authentication.getDetails()); // WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=B9D8AEBBC88A811EE71992FF1DBEAAB7]
        log.info("authentication.getName() = {}", authentication.getName()); // Likelion.SpringStudy.domain.UserDomain@3c40da78
        UserDomain userDomain = (UserDomain) authentication.getPrincipal();
        commentService.create(form, userDomain.getId(), Long.parseLong(post_id));
        return new RedirectView("/blogs/" + blog_id + "/posts/" + post_id + "/receive");
    }

    @PostMapping("/blogs/{blog_id}/posts/{post_id}/comments/{comment_id}/delete")
    public RedirectView delete(@PathVariable(value="blog_id") String blog_id,
                               @PathVariable(value="post_id") String post_id,
                               @PathVariable(value="comment_id") String comment_id,
                               Authentication authentication) {
        commentService.delete(Long.parseLong(comment_id));
        return new RedirectView("/blogs/" + blog_id + "/posts/" + post_id + "/receive");
    }

}

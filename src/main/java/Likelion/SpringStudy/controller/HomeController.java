package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BlogService blogService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDomain userDomain = (UserDomain) authentication.getPrincipal();
            Blog blog = userDomain.getBlog();
            model.addAttribute("blog", blog);
        }
        return "home";
    }
}

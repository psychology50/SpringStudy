package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.UserForm;
import Likelion.SpringStudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;

    @GetMapping("/users/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/userRegisterForm";
    }

    @PostMapping("/users/register")
    public String create(UserForm form) {
        userService.register(form);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String SignInForm(@RequestParam(value="error", required = false) String error,
                             @RequestParam(value="exception", required = false) String exception,
                             Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "users/login";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value="exception", required = false) String exception,
                               Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDomain user = (UserDomain) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("exception", exception);
        return "users/denied";
    }
}

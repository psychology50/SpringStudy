package Likelion.SpringStudy.controller;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.UserForm;
import Likelion.SpringStudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String list(Model model) {
        List<UserDomain> userDomains = userService.findUsers();
        model.addAttribute("users", userDomains);
        return "users/userList";
    }

    @GetMapping("/users/receive")
    public String SearchForm(Model model) {
        model.addAttribute("user", new UserDomain());
        return "users/userSearchForm";
    }

    @PostMapping("/users/receive")
    public String recieve(UserForm form, Model model) {
        UserDomain userDomain = userService.findUserByNickname(form.getNickname()).orElseGet(UserDomain::new);
        model.addAttribute("user", userDomain);
        return "users/userSearchForm";
    }

    @GetMapping("/users/delete")
    public String deleteForm() {return "users/userDeleteForm";}

    @PostMapping("/users/delete")
    public String delete(UserForm form) {
        userService.deleteUserByNickname(form.getNickname());
        return "redirect:/";
    }

    @GetMapping("/users/{id}/update")
    public String updateForm(@PathVariable(value="id") String id, Model model) {
        UserDomain userDomain = userService.findUserById(Long.parseLong(id)).get();
        model.addAttribute("user", userDomain);
        model.addAttribute("userForm", new UserForm());
        return "users/userUpdateForm";
    }
}

package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.Book;
import kz.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class Controller3 {
    private final UserDAO userDAO;
    @Autowired
    public Controller3(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("usersList", userDAO.getUserList());
        return "user-list";
    }

    @GetMapping("/{username}")
    public String book(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userDAO.findByUserName(username));
        return "user-page";
    }

    @GetMapping("/edit/{username}")
    public String updateUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userDAO.findByUserName(username));
        return "user-edit";
    }

    @PatchMapping("/{username}")
    public String updateUserPatch(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("username") String username) {
        if (bindingResult.hasErrors())
            return "user-edit";

        userDAO.updateUser(user);
        return "redirect:/users/"+user.getEmail();
    }

    @DeleteMapping("/{username}")
    public String deleteUserPatch(@PathVariable("username") String username) {
        userDAO.deleteUser(username);
        return "redirect:/users";
    }
}
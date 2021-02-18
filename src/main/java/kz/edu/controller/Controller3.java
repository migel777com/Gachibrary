package kz.edu.controller;

import kz.edu.dao.UserDAO;
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

    // Login => Need to add exception for Login of inactive Users

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("usersList", userDAO.getUserList());
        return "users-list";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.findByUserId(id));
        return "user-page";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.findByUserId(id));
        return "user-edit";
    }

    @PatchMapping("/{id}")
    public String updateUserPatch(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "user-edit";

        userDAO.updateUser(user);
        return "redirect:/users/"+user.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteUserPatch(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }
}

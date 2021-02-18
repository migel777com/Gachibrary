package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class Controller2 {
    private final UserDAO userDAO;

    @Autowired
    public Controller2(UserDAO userDAO) { this.userDAO = userDAO;}

    PasswordEncoder passwordEncoder;

    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder) { this.passwordEncoder = passwordEncoder;}

    @RequestMapping(value={"", "/", "home"})
    public String home()
    {
        return "home";
    }

    @GetMapping("/arrivals")
    public String arrivals()
    {
        return "arrivals";
    }

    @GetMapping("/profile")
    public String profile()
    {
        return "profile";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("username") String email, Model model) {
        System.out.println("REGISTRATION:" + email);

        if (userDAO.findByUserName(email) != null && userDAO.findByUserName(email).isActive()) {
            model.addAttribute("message", "User exists!");
            return "registration";
        } else if (userDAO.findByUserName(email) != null && !userDAO.findByUserName(email).isActive()) {
            User usertemp = userDAO.findByUserName(email);
            usertemp.setActive(true);
            userDAO.updateUser(usertemp);
            return "home";
        } else {
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
            return "redirect:/login";
        }
    }

}
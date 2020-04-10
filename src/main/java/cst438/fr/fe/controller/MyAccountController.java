package cst438.fr.fe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cst438.fr.fe.domain.User;
import cst438.fr.fe.domain.UserRepository;

@Controller
public class MyAccountController {
	
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index( Model model) {
        return "index";
    }

    @GetMapping("/users/new")
    public String createUser( Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping("/users/new")
    public String processUserForm(@Valid User country, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_form";
        } 

        userRepository.save(country);
        return "user_show";
    }
    
    
}
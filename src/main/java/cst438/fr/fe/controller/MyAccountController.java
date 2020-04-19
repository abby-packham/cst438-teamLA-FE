package cst438.fr.fe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cst438.fr.fe.Utils;
import cst438.fr.fe.domain.User;
import cst438.fr.fe.domain.UserRepository;

@Controller
public class MyAccountController {
	
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/signout")
    public String signOut( Model model, HttpServletRequest request, HttpServletResponse response) {
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        if (userID != null) {
            Utils.deleteUserCookie(response);
            System.out.println("Clearing cookie for " + userID);
            model.addAttribute(Utils.COOKIE_USER_ID, null);
        }
        
        return "index";
    }

    @GetMapping("/users/signin")
    public String signIn( Model model, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        model.addAttribute("user", user);
        return "signin_form";
    }
    
    @PostMapping("/users/signin")
    public String processSignIn(@Valid User user,  Model model, HttpServletRequest request, HttpServletResponse response) {
        //check if user already exists from database.
        String userID = user.getUserId();
        User existingUser = userRepository.findByUserId(userID);
        if (existingUser != null) {
            if (existingUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                Utils.setUserCookie(userID, response);
                model.addAttribute(Utils.COOKIE_USER_ID, userID);
            }
        }
        return "index";
    }
    
    @GetMapping("/users/new")
    public String createUser( Model model, HttpServletRequest request) {
        User user = new User();
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        System.out.println("createUser: " + userID);
        if (userID != null && userRepository.findByUserId(userID)!= null) {
            user = userRepository.findByUserId(userID);
            model.addAttribute(Utils.COOKIE_USER_ID, userID);
        }
        model.addAttribute("user", user);
        
        return "user_form";
    }

    @PostMapping("/users/new")
    public String processUserForm(@Valid User user, BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "user_form";
        } 
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        if (userID == null) {
            userID = user.getUserId();
        }
        //check if user already exists from database.
        User existingUser = userRepository.findByUserId(userID);
        if (existingUser == null) {
            userRepository.save(user);
        } else {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
        }
        //success
        //save cookie
        if (Utils.getCookieValue(request, Utils.COOKIE_USER_ID) == null) {
            Utils.setUserCookie(userID, response);
            model.addAttribute(Utils.COOKIE_USER_ID, userID);
        }
        return "user_show";
    }
    
    
}
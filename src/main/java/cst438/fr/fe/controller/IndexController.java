package cst438.fr.fe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cst438.fr.fe.Utils;

@Controller
public class IndexController {
	
    @GetMapping("/")
    public String index( Model model, HttpServletRequest request) {
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        if (userID != null && !userID.isEmpty()) {
            model.addAttribute(Utils.COOKIE_USER_ID, userID);
        }
        return "index";
    }


}
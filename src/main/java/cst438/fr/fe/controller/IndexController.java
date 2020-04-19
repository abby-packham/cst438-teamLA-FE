package cst438.fr.fe.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
    @GetMapping("/")
    public String index( Model model, HttpServletRequest request) {
        model.addAttribute("userid", "");

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            System.out.println("-----------------------" + cookies.toString());

            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("userid")) {
                    System.out.println("-----------------------" + cookie.toString());
                    model.addAttribute("userid", cookie.getValue());
                }
            }

        }
        return "index";
    }


}
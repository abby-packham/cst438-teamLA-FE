package cst438.fr.fe.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cst438.fr.fe.Utils;
import cst438.fr.fe.domain.Flight;
import cst438.fr.fe.domain.FlightRepository;

@Controller
public class FlightController {
	
    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/flight/reserve")
    public String flightReserve( Model model, HttpServletRequest request, HttpServletResponse response) {
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        Flight flight = new Flight();
        model.addAttribute("flight", flight);

        return "flight_reserve_form";
    }

    @PostMapping("/flight/reserve")
    public String processFlightReservation(@Valid Flight flight, Model model, HttpServletRequest request, HttpServletResponse response) {
        //check if user already exists from database.
        String flightNumber = flight.getFlightNumber();
        Flight existingUser = flightRepository.findByFlightNumber(flightNumber);
        if (existingUser != null) {
//            if (existingUser.getPassword().equalsIgnoreCase(user.getPassword())) {
//                Utils.setUserCookie(userID, response);
//                model.addAttribute(Utils.COOKIE_USER_ID, userID);
//            } else {
//                //invalid password
//                model.addAttribute("signin_status", "Invalid Password.");
//            }
        } else {
            //User not found
            model.addAttribute("signin_status", "User not found.");
        }
        return "index"; 
    }
    

    
}
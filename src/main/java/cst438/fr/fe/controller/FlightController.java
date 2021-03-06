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
import cst438.fr.fe.domain.Flight;
import cst438.fr.fe.domain.FlightInfo;
import cst438.fr.fe.domain.FlightRepository;
import cst438.fr.fe.service.FlightListService;
import cst438.fr.fe.service.FlightReserveService;

@Controller
public class FlightController {
	
    @Autowired
    FlightRepository flightRepository;

    
    @Autowired
    FlightListService flightListService;
    
    @Autowired
    FlightReserveService flightReserveService;


    @GetMapping("/flight/reserve")
    public String flightReserve( Model model, HttpServletRequest request, HttpServletResponse response) {
        Utils.setUserIDModel(request, model);
        Flight flight = new Flight();
        model.addAttribute("flight", flight);

        return "flight_reserve_form";
    }

    @PostMapping("/flight/reserve")
    public String processFlightReservation(@Valid Flight flight, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "flight_reserve_form";
        } 
        //check if user already exists from database.
        String flightNumber = flight.getFlightNumber();
        Flight foundFlight = flightRepository.findByFlightNumber(flightNumber);
        flightReserveService.requestReservation(flight.getDepartureCity(), flight.getArrivalCity(),
                flight.getDateOfDeparture(), flight.getDateOfArrival());
        return "flight_reserve_show"; 
    }
    

    @GetMapping("/flight/list")
    public String listFlights( Model model, HttpServletRequest request, HttpServletResponse response) {
        Utils.setUserIDModel(request, model);
        FlightInfo flightList = flightListService.getFlightList();
        model.addAttribute("flightList", flightList.getFlights());
        return "flight_list";
    }
    
}
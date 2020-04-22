package cst438.fr.fe.service;

import java.util.Date;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightReserveService {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

	public FlightReserveService() {
	    
	}
	
    public void requestReservation(String departureCity, String arrivalCity, Date dateOfDeparture,
            Date dateOfArrival) {
        String msg  = "{\"departureCity\": \""+ departureCity + 
                "\" \"arrivalCity\": \""+arrivalCity+
                "\" \"dateOfDeparture\": \""+dateOfDeparture+
                "\" \"dateOfArrival\": \""+dateOfArrival+"\"}" ;
        
        System.out.println("Sending message:"+msg);
        
        rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);

    }
}

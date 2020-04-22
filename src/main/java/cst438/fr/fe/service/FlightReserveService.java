package cst438.fr.fe.service;

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
	
    public void requestReservation(String cityName, String level, String email) {
        String msg  = "{\"cityName\": \""+ cityName + 
                "\" \"level\": \""+level+
                "\" \"email\": \""+email+"\"}" ;
        
        System.out.println("Sending message:"+msg);
        
        rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);

    }
}

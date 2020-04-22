package cst438.fr.fe.domain;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class FlightTest {
	 @Test
	    public void testFlight() throws Exception {
	        String flightNumber = "FL309";
	        String operatingAirlines = "United";
	        String departureCity = "Los Angeles";
	        String arrivalCity = "Seattle";
	        @SuppressWarnings("deprecation")
			Date dateOfDeparture = new Date(2020, 10, 12);
	        Flight flight = new Flight();
	        flight.setFlightNumber(flightNumber);
	        flight.setOperatingAirlines(operatingAirlines);
	        flight.setDepartureCity(departureCity);
	        flight.setArrivalCity(arrivalCity);
	        flight.setDateOfDeparture(dateOfDeparture);
	        
	        assertThat(flight.getFlightNumber()).isEqualTo(flightNumber);
	        assertThat(flight.getOperatingAirlines()).isEqualTo(operatingAirlines);
	        assertThat(flight.getDepartureCity()).isEqualTo(departureCity);
	        assertThat(flight.getArrivalCity()).isEqualTo(arrivalCity);
	        assertThat(flight.getDateOfDeparture()).isEqualTo(dateOfDeparture);
	    }
	 
	 @Test
	    public void testFlightConstructor() throws Exception {
		 	String flightNumber = "FL309";
	        String operatingAirlines = "United";
	        String departureCity = "Los Angeles";
	        String arrivalCity = "Seattle";
			@SuppressWarnings("deprecation")
			Date dateOfDeparture = new Date(2020, 10, 12);
			Flight flight = new Flight(flightNumber, operatingAirlines, departureCity, arrivalCity, dateOfDeparture, dateOfDeparture, null);
		 
	 }

}

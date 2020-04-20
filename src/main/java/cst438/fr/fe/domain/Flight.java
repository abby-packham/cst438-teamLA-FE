package cst438.fr.fe.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {

    @Id
    @GeneratedValue     //let database create the ID
    private long id;
    
	private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    private Date dateOfDeparture;
    private Date dateOfArrival;
    private Timestamp estimatedDepartureTime;

    public Flight() {

    }
 
    public Flight(String flightNumber, String operatingAirlines, String departureCity, String arrivalCity,
            Date dateOfDeparture, Date dateOfArrival, Timestamp estimatedDepartureTime) {
        super();
        this.flightNumber = flightNumber;
        this.operatingAirlines = operatingAirlines;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.estimatedDepartureTime = estimatedDepartureTime;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOperatingAirlines() {
        return operatingAirlines;
    }

    public void setOperatingAirlines(String operatingAirlines) {
        this.operatingAirlines = operatingAirlines;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Timestamp getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public void setEstimatedDepartureTime(Timestamp estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }
    
    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
    @Override
    public String toString() {
        return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", operatingAirlines=" + operatingAirlines
                + ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity + ", dateOfDeparture="
                + dateOfDeparture + ", estimatedDepartureTime=" + estimatedDepartureTime + "]";
    }
}

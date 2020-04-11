package cst438.fr.fe.domain;

import javax.persistence.OneToOne;

public class FlightReservation {
	
	private Boolean checkedIn;
    private Integer numberOfBags;

    @OneToOne
    private User user;
    @OneToOne
    private Flight flight;

    public FlightReservation() {
    }



    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Integer getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}

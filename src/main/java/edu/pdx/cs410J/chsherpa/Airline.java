package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chsherpa on 7/11/17.
 */
public class Airline extends AbstractAirline{
    public String getName() {
        return name;
    }

    /**
     * Default constructor
     */
    public Airline(){ }

    /**
     * Constructor with flights object passed in
     * Adds to the List only if the name of the flights passed in matches
     * @param flights
     */
    public Airline( Flight flights){
        if( flights.getFlightName().equals(this.name) )
            this.flights.add(flights);
        else
            throw new IllegalArgumentException("Flight Name does not match Airline's Name");
    }

    @Override
    public void addFlight(AbstractFlight flight) {
       // addFlightFrom( flights );
    }

    @Override
    public Collection getFlights() {
        return flights;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Set the name of the Airline
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to add flights object
     * @param flight
     */
    public void addFlightFrom( Flight flight ){
        if( flight.getFlightName().equals(this.name) )
            this.flights.add(flight);
        else
            throw new IllegalArgumentException("Flight Name does not match Airline's Name");
        return;
    }

    /**
     * Method to display the Airline flights attached to this object
     */
    public void displayAirlineFlights(){
        for(int i = 0; i < flights.size(); i++ ){
            flights.get(i).displayFlightInfo();
        }
    }

    public void copyTo( Airline flights ){
        for( Flight temp : this.flights){
            flights.flights.add(temp);
            //flights.addFlightFrom(temp);
        }
    }

    public void copyFrom( Airline flights ){
        for( Flight temp: flights.flights ){
            flights.addFlightFrom(temp);
        }
    }

    private String name;
    private List<Flight> flights = new ArrayList<Flight>();
}

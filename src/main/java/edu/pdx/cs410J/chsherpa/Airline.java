package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chsherpa on 7/11/17.
 */
public class Airline extends AbstractAirline<Flight>{
    private List<Flight> flights = new ArrayList<Flight>();

    public String getName() {
        return this.flights.get(0).getFlightName();
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
        if( flights.getFlightName().equals(this.flights.get(0).getFlightName()) )
            this.flights.add(flights);
        else
            throw new IllegalArgumentException("Flight Name does not match Airline's Name");
    }

    @Override
    public void addFlight(Flight flight ) {
       addFlightFrom( flight );
    }

    @Override
    public Collection<Flight> getFlights() {
        return this.flights;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Method to add flights object
     * @param flight
     */
    public void addFlightFrom( Flight flight ){
        if( flight.getFlightName().equals(this.flights.get(0).getFlightName()) )
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

}

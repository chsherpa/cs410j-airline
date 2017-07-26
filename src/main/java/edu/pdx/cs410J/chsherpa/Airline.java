package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/**
 * Created by chsherpa on 7/11/17.
 */
public class Airline extends AbstractAirline<Flight>{
    private List<Flight> flights = new ArrayList<Flight>();

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
        addFlightFrom(flights);
    }

    @Override
    public void addFlight(Flight flight ) {
       addFlightFrom( flight );
    }

    @Override
    /**
     *  Collection method to return flights properly
     */
    public Collection<Flight> getFlights() {
        return this.flights;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Return the name of the Airlines
     * @return Name of the first flight in the list
     */
    public String getName() {
        return (this.flights != null )?this.flights.get(0).getFlightName():null;
    }

    /**
     * Method to add flights object
     * @param flight
     */
    public void addFlightFrom( Flight flight ){
        if( this.flights.isEmpty() )
            this.flights.add(flight);
        else{
          if (flight.getFlightName().equals(this.flights.get(0).getFlightName()))
          {
            if( this.flights.size() > 1 )
            {
              this.flights.add(flight);
              Arrays.sort(new List[]{flights});

              /*
              this.flights.sort(new Comparator<Flight>() {
                DateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm" );
                @Override
                public int compare(Flight f1, Flight f2)
                {
                  int value1 = f1.getSource().compareTo( f2.getSource() );
                  if( value1 == 0 )
                  {
                    try{
                      return f.parse( f1.getDepartureString() ).compareTo( f.parse(f2.getDepartureString()) );
                    }
                    catch (ParseException e)
                    {
                      throw new IllegalArgumentException(e);
                    }
                  }
                  return value1;
                }
              });
              */
            }
            else
            {
              this.flights.add(flight);
            }
          }
          else
              throw new IllegalArgumentException("Flight Name does not match Airline's Name");
        }
        return;
    }
}

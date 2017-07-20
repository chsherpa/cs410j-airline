package edu.pdx.cs410J.chsherpa;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chsherpa on 7/11/17.
 */

public class AirlineTest {
    @Test
    public void initialAirlineNameReturnsNull() {
        Airline air = new Airline();
        assertThat(air.getName(),is(nullValue()));
    }

    @Test
    public void addAirlineTest(){
        Flight flight = new Flight();
        flight.setFlightName("Grace");
        Airline air = new Airline();
        air.addFlight(flight);
        assert(air.getName().equals(flight.getFlightName()));
    }

}

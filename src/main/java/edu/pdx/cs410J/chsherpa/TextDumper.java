package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chsherpa on 7/18/17.
 */
public class TextDumper implements AirlineDumper<Airline> {
    private final PrintWriter writer;

    public TextDumper(/*String fileName, Airline airline,*/ PrintWriter writer ) {
        this.writer = writer;
    }

    @Override
    public void dump(Airline airline) throws IOException {
        for (Flight flight : airline.getFlights()) {
          this.writer.println(flight.getFlightName());
          this.writer.println(flight.getNumber());
          this.writer.println(flight.getSource());
          this.writer.println(flight.getDepartureString());
          this.writer.println(flight.getDestination());
          this.writer.println(flight.getDepartureString());
          this.writer.println("--END--");
        }
        this.writer.flush();
    }
}

package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.chsherpa.Project3.*;
/**
 * Created by chsherpa on 7/18/17.
 */
public class TextParser implements AirlineParser<Airline>{
    private final Reader reader;

    /**
     * TextParser default constructor
     * @param reader
     */
    public TextParser( Reader reader ){
        this.reader = reader;
    }

    @Override
    /**
     * Override of AirlineParser Abstract class method parse() passed to type T
     *
     * @return Airline Object
     */
    public Airline parse() throws ParserException {
        Airline air = new Airline();
        BufferedReader br = new BufferedReader(reader);
        List<String> flightInfo = new ArrayList<String>();

        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if( line.compareTo("--END--") == 0){
                    if( FlightInfoCheck(flightInfo) )
                    {
                        air.addFlight(new Flight(flightInfo));
                        flightInfo.clear();
                    }
                    else{
                        flightInfo.clear();
                    }
                }
                else {
                    flightInfo.add(line);
                }
            }
        }
        catch (IOException ex) {
            throw new ParserException("While parsing text", ex);
        }
        return air;
    }

}

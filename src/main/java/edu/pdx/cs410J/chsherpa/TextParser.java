package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by chsherpa on 7/18/17.
 */
public class TextParser implements AirlineParser{
    String file;
    Airline AirlineTarget;

    public TextParser( String fileName, Airline airline){
        this.file = new String( fileName );
    }

    @Override
    public AbstractAirline parse() throws ParserException {
        try{
            Files.lines(Paths.get( this.file )).forEach(System.out::println);
        }
        catch(IOException e){
            throw new IllegalArgumentException("\n"+this.file + "is not valid");
//            System.out.println("\n"+this.file + "is not valid");
        }

        return null;
    }
}

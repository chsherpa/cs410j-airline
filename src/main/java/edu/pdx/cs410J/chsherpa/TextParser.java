package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by chsherpa on 7/18/17.
 */
public class TextParser implements AirlineParser<Airline>{
    String file;
    Airline AirlineTarget;
    private final Reader reader;

    public TextParser( Reader reader ){
        this.reader = reader;
    }
    /*
    public TextParser( String fileName, Airline airline){
        this.file = new String( fileName );
    }
    */

    @Override
    public Airline parse() throws ParserException {
        Airline air = new Airline();
        BufferedReader br = new BufferedReader(reader);

        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(br.readLine());
                //TODO
                air.addFlight(new Flight());
            }
        }
        catch (IOException ex) {
            throw new ParserException("While parsing text", ex);
        }
        return air;
        /*
        try{
            Files.lines(Paths.get( this.file )).forEach(System.out::println);
        }
        catch(IOException e){
            throw new IllegalArgumentException("\n"+this.file + "is not valid");
//            System.out.println("\n"+this.file + "is not valid");
        }

        return null;
        */
    }

}

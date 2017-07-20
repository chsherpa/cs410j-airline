package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by chsherpa on 7/18/17.
 */
public class TextDumper implements AirlineDumper<Airline> {
    private String newFileName;
    private final PrintWriter writer;

    public TextDumper(/*String fileName, Airline airline,*/ PrintWriter writer ) {
        /*
        this.newFileName = new String(fileName);
        fileCreate(this.newFileName);

        try { dump( airline ); }
        catch (IOException e) {
            e.printStackTrace();
        }
        */
        this.writer = writer;
    }

    /**
     * Function acts much like touch command of unix
     *
     * @param fileName File to be created
     */
    public void fileCreate(String fileName) {
        File newFile = new File(fileName);
        //Check if file exist; if not, create one
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dump(Airline airline) throws IOException {
        for( Flight flight : airline.getFlights()){
            this.writer.print(flight.getFlightName());
        }

    }
}

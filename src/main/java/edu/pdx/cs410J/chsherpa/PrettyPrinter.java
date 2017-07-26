package edu.pdx.cs410J.chsherpa;

import java.io.Reader;
import java.util.Date;

/**
 * Created by chsherpa on 7/25/17.
 */
public class PrettyPrinter {

    /**
     * Default Constructor for Pretty Printer Class
     */
    PrettyPrinter( Airline air )
    {
        PrettyPrint(air);
    }

    private static String toTitleCase( String proper )
    {
        StringBuilder titled = new StringBuilder();
        boolean nextTitledCase = true;

        for( char c: proper.toCharArray() )
        {
            if( Character.isSpaceChar(c) )
            {
                nextTitledCase = true;
            }
            else if( nextTitledCase )
            {
                c = Character.toTitleCase(c);
                nextTitledCase = false;
            }

            titled.append(c);
        }

        return titled.toString();
    }

    private void PrettyDateAndTime( Date date )
    {

    }

    private void PrettyPrint(Airline air)
    {
        /*
        for( Flight A: air.getFlights() )
        {
            System.out.printf("%s", toTitleCase( A.getFlightName() ));
            A.getNumber();
            A.getSource().toUpperCase();
            A.getArrivalString().getClass();
            A.getArrival();
            A.getDestination().toUpperCase();
        }
        */

    }


}

package edu.pdx.cs410J.chsherpa;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by chsherpa on 7/25/17.
 */
public class PrettyPrinter {
    private PrintWriter writer;

    /**
     * Default Constructor
     */
    public PrettyPrinter(){}

    /**
     * Default Constructor for Pretty Printer Class
     */
    public PrettyPrinter( PrintWriter writer )
    {
        this.writer = writer;
    }

    /**
     * Proper String Case Everything
     * @param proper
     * @return String First Letter is UpperCased, rest are left as is
     */
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

    /**
     * Method to create a pretty date and time from "mm/dd/yyyy hh:aa" format
     * @param date
     * @return
     * @throws ParseException
     */
    private String PrettyDateAndTime( String date ) throws ParseException {
//      SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      DateFormat sf = DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT );
      SimpleDateFormat pdf = new SimpleDateFormat("EEE, MMM d yyyy, hh:mm aaa");
      Date dDate= sf.parse(date);
      return pdf.format(dDate);
    }

    /**
     * Get the duration of the flight
     * Source: StackOverflow
     *
     * @param startDate     Start of the Flight
     * @param finishDate    End of the Flight
     * @param time          TimeUnit Measurements
     * @return              Duration of the Flight
     */
    private static long getDuration( String startDate, String finishDate, TimeUnit time ) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date sDate= df.parse(startDate);
        Date fDate= df.parse(finishDate);
        long diffInMin = fDate.getTime() - sDate.getTime();
        return time.convert(diffInMin, TimeUnit.MILLISECONDS);
    }

    /**
     * Pretty Print to Terminal
     *
     * @param air Airline to be parse
     * @throws ParseException This is due to string format parsing to make some elements pretty
     */
    public void PrettyPrint( Airline air ) throws ParseException {
      for( Flight A: air.getFlights() )
      {
        System.out.printf( "%-20s%s\n","Airline:", toTitleCase( A.getFlightName() ) );
        System.out.printf( "%-20s%s\n","Flight Number:", A.getNumber() );
        System.out.printf( "%-20s%s\n","Flight Source:", A.getSource().toUpperCase() );
        System.out.printf( "%-20s%s\n","Time of Departure:", PrettyDateAndTime( A.getDepartureString() ) );
        System.out.printf( "%-20s%s\n","Flight Destination:", A.getDestination().toUpperCase() );
        System.out.printf( "%-20s%s\n","Time of Arrival:", PrettyDateAndTime( A.getArrivalString() ) );
        System.out.printf("%-20s%d\n", "Duration (in Mins):", getDuration(A.getDepartureString(),A.getArrivalString(),TimeUnit.MINUTES));
      }
    }

    /**
     * Write out of the Pretty Print Information
     *
     * @param air Airline to be gone through
     * @param out File to be written to
     * @throws IOException Standard IO exception catch for IO
     * @throws ParseException Standard Parse exception catch for prettying up strings
     */
    public void PrettyPrintDump( Airline air, String out ) throws IOException, ParseException {
        try
        {
            for (Flight A : air.getFlights())
            {
                this.writer.printf( "%-20s%s\n","Airline:", toTitleCase( A.getFlightName() ) );
                this.writer.printf( "%-20s%s\n","Flight Number:", A.getNumber() );
                this.writer.printf( "%-20s%s\n","Flight Source:", A.getSource().toUpperCase() );
                this.writer.printf( "%-20s%s\n","Time of Departure:", PrettyDateAndTime( A.getDepartureString() ) );
                this.writer.printf( "%-20s%s\n","Flight Destination:", A.getDestination().toUpperCase() );
                this.writer.printf( "%-20s%s\n","Time of Arrival:", PrettyDateAndTime( A.getArrivalString() ) );
                this.writer.printf("%-20s%d\n", "Duration (in Mins):", getDuration(A.getDepartureString(),A.getArrivalString(),TimeUnit.MINUTES));
                this.writer.println("-------------");
            }
            this.writer.flush();
        }
        catch( IllegalArgumentException ex )
        {
            System.out.println( "PrettyFileProblems: " + ex.getMessage()
                    + "PrettyFileProblemsCause: " + ex.getCause() );
        }


    }


}

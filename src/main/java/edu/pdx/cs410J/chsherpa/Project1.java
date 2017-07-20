package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {
  public static boolean debugFlag = true;

  /**
   * README : This explains usage of this program
   */
  public static void README(){
    System.out.println("Name: Chhewang");
    System.out.println("Project 1");
    System.out.println("The objectives of this project is to be able to input the following command"
    + "\nline usage, as well having that added to a flight object for future use in Project two"
    + "\nwith multiple Flight that are airlines.");
    System.out.println("Project1 was achieved through extensive Google searching through Stackoverflow"
    + "\nand the use of the Java natives libraries in the form of List<T> and Date.\n"
    + "\nUsage is described below:");
    System.out.println( "usage: java edu.pdx.cs410J.csherpa.Project1 [options] <args>");
    System.out.println( "args are (in this order):");
    System.out.println( "name"+ "\t\t\t" + "The name of the airline");
    System.out.println( "flightNumber"+ "\t" + "The flight number");
    System.out.println( "src" +"\t\t\t\t" + "Three-letter code of departure airport");
    System.out.println( "departTime"+ "\t\t" + "Departure date and time (24-hour time)");
    System.out.println( "dest"+ "\t\t\t" + "Three-letter code of arrival airport");
    System.out.println( "arriveTime"+ "\t\t" + "Arrival date and time (24-hour time)");
    System.out.println( "|_Date and time should be in the format: mm/dd/yyyy hh:mm");
    System.out.println( "\nflag options (options may appear in any order):");
    System.out.println( "-textFile file" +"\t" +"Where to read/write the airline info");
    System.out.println( "-print"+ "\t\t\t" + "Prints a description of the new flight");
    System.out.println( "-README"+ "\t\t\t" + "Prints a README for this project and exits");
  }

  /**
   * Check if datetime stamp is in MM/dd/yyyy hh:ss format
   * Source: Stackoverflow
   * @param inputDate String to be checked; hopefully containing the desired format
   * @return String value
   */
  public static String dateCheck( String inputDate ){
    Date date = new Date();
    String s = new String();
    try{
      DateFormat dateInput = new SimpleDateFormat("MM/dd/yyyy hh:mm");
      date = dateInput.parse(inputDate);
      if( inputDate.equals(date) ){
        date = null;
      }
      s = date.toString();
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    if( date == null )
      throw new IllegalArgumentException("Date argument not valid");
    else if ( debugFlag == true ){
      System.out.println("\nDate Checks:\n" + date);
      System.out.println(s);
    }
    return inputDate;
  }

  /**
   *
   * @param places
   * @return
   */
  public static String SrcDestLengthCheckAndNotNumeric( String places ){
    if( places.length() != 3 ) {
      System.out.println( places );
      System.out.println( "Length:" + places.length() );
      throw new IllegalArgumentException("\n"+ places +" source is not three letters");
    }
    if (places.matches("-?\\d+(\\.d\\d+)?") ) {
      throw new IllegalArgumentException("\n"+ places +" source has numeric values ");
    }
    return places.toUpperCase();
  }

  /**
   * Flight Info Checks
   * @param flightInfo
   */
  public static void FlightInfoCheck( List<String> flightInfo ){
    if( flightInfo.size() > 6) {
      throw new IllegalArgumentException("\nFlight info should be less than " + flightInfo.size() + " arguments");
    }

    if( flightInfo.size() == 0 ){
      System.out.printf("\nFlight info is empty.\n");
      System.out.println("\nMissing Command Line Arguments");
    }
    else {
      //Check if fight number is positive numeric
      //Source: Stackoverflow
      if (flightInfo.get(1).matches("\\d+(\\.d\\d+)?") == false) {
        throw new IllegalArgumentException("Flight number is not a numeric value");
      }

      // Check for Source being three letters long
      flightInfo.set(2, SrcDestLengthCheckAndNotNumeric(flightInfo.get(2)));
      // Check for Dest being three letters long
      flightInfo.set(4, SrcDestLengthCheckAndNotNumeric(flightInfo.get(4)));
      //Date Check for Departure
      flightInfo.set(3, dateCheck(flightInfo.get(3)) );
      //Date Check for Arrival
      flightInfo.set(5, dateCheck(flightInfo.get(5)) );
    }
  }

  /**
   *
   * @param args
   * @throws IOException
   * @throws ParserException
   */
  public static void main(String[] args) throws IOException, ParserException {
    Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean readMeFlag = false;
    boolean printFlightFlag = false;
    String fileName = new String();

    List<String> optsList = new ArrayList<String>();
    List<String> flightInfo = new ArrayList<String>();
    List<Airline> airlines = new ArrayList<Airline>();

   // System.out.println("Missing command line arguments");

    if( debugFlag == true ){ //Default Print
      System.out.println("All args");
      for (String arg : args) {
        System.out.print( arg + " ");
      }
    }

    //Parser
    // Source: Stackoverflow for case '-' and numeric ladder
    for( int i = 0; i < args.length; i++ ) {
      switch (args[i].charAt(0)) {
        case '-':
          if (args[i].length() < 2)
            throw new IllegalArgumentException("Flag Arg Not Valid: " + args[i]);
          if( args[i].substring(1,args[i].length() ).equals("textFile") ){
            optsList.add(args[i].substring(1,args[i].length()));
            fileName = new String( args[i+1] ).trim();
            i++;
            break;
          }
          optsList.add(args[i].trim().substring(1,args[i].length()));
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '7':
        case '8':
        case '9':
            try{
              if (args[i].charAt(1) == '/' ) {
                flightInfo.add(new String(args[i] + " "+args[i + 1]).trim());
                i++;
                break;
              }
              else if( args[i].charAt(2) == '/' ) {
                flightInfo.add(new String(args[i] + " "+args[i + 1]).trim());
                i++;
                break;
              }
            }
            catch(IllegalArgumentException ex){
              System.out.println("Date was not in the correct input format");
              break;
            }
        default:
          flightInfo.add((args[i]).trim());
          break;
      }
    }

    if( debugFlag == true ) {
      List<String> flightInfo2 = new ArrayList<String>();
      flightInfo2.add("name");
      flightInfo2.add("1393930");
      flightInfo2.add("src");
      flightInfo2.add("3/15/2017 10:39");
      flightInfo2.add("des");
      flightInfo2.add("03/2/2017 1:03");
      printFlightFlag = true;
    }

   //Error catches for SIZE for LISTs for Args and
    if( optsList.size() > 3 )
      throw new IllegalStateException("\nOptslist should have less than " + optsList.size() +" arguments");

    //CATCH FOR FLAGS
    for( String flagArgs: optsList ) {
      if( debugFlag == true ) {
        System.out.println(flagArgs);
      }
      if (flagArgs.toLowerCase().equals("print")) {
        printFlightFlag = true;
      }
      if (flagArgs.toUpperCase().equals("README")) {
        readMeFlag = true;
      }
      if (flagArgs.toLowerCase().equals("textfile")) {
        if (debugFlag == true)
          System.out.println("\nFileNameParsed: " + fileName);

        Airline AirlinefromFile = new Airline();
        File text = new File(fileName);
        if( text.exists() ){
          TextParser parser = new TextParser(new FileReader(text));
          AirlinefromFile = parser.parse();
          //Check for proper input??
          airlines.add(AirlinefromFile);
        }
      }
    }

    //PRINT AND README FLAGS
    if( readMeFlag == true ) {
      README();
      System.exit(1);
    }

    //Add FlightInfo
    FlightInfoCheck(flightInfo);
    Flight flight = new Flight(flightInfo);
    Airline air = new Airline();
    air.addFlight(flight);
    airlines.add(air);

    if( printFlightFlag == true ) {
      System.out.println("Print Info Below");
      try {
        flight.displayFlightInfo();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    TextDumper dumper = new TextDumper( new PrintWriter(new FileWriter(fileName,true)));
    for( Airline temp: airlines ){
      dumper.dump(temp);
    }

    System.exit(1);
  }
}
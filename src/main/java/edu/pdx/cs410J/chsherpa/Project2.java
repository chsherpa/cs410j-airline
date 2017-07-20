package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {
  public static boolean debugFlag = true;

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
      System.out.println(date);
      System.out.printf(s);
    }
    return s;
  }

  public static String SrcDestLengthCheck( String places ){
    if( places.length() != 3 ) {
      System.out.println( places );
      System.out.println( "Length:" + places.length() );
      throw new IllegalArgumentException("Flight source is not three letters");
    }
    return places.toUpperCase();
  }

  private static void readFromFile(String fileName, Airline flights ) {
      TextParser parse = new TextParser( fileName, flights );
  }

  public static boolean WriteToFile( String fileName, Airline flights ){
    TextDumper dump = new TextDumper( fileName, flights );
    return true;
  }

  public static void main(String[] args) {
    Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean readMeFlag = false;
    boolean printFlightFlag = false;
    boolean writeToFlag = false;
    String fileName = new String();

    List<String> optsList = new ArrayList<String>();
    List<String> flightInfo = new ArrayList<String>();
    List<Airline> airlines = new ArrayList<Airline>();

   // System.out.println("Missing command line arguments");

    if( debugFlag == true ){ //Default Print
      for (String arg : args) {
        System.out.println( arg );
      }
    }

    //Parser
    // Source: Stackoverflow for case '-' and numeric ladder
    for( int i = 0; i < args.length; i++ ) {
      switch (args[i].charAt(0)) {
        case '-':
          if (args[i].length() < 2)
            throw new IllegalArgumentException("Not Valid: " + args[i]);
          if( args[i].substring(1,args[i].length() ).equals("textFile") ){
            writeToFlag = true;
            optsList.add(args[i].substring(1,args[i].length()));
            fileName = new String( args[i+1] );
            i++;
            break;
          }
          optsList.add(args[i].substring(1,args[i].length()));
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
//          if( args[i].substring(0,1).matches("[0-9]") ) {
            try{
              if (args[i].charAt(1) == '/' ) {
                flightInfo.add(new String(args[i] + " "+args[i + 1]));
                i++;
                break;
              }
              else if( args[i].charAt(2) == '/' ) {
                flightInfo.add(new String(args[i] + " "+args[i + 1]));
                i++;
                break;
              }
            }
            catch(IllegalArgumentException ex){
              System.out.println("Date was not in the correct input format");
              break;
            }
        default:
          flightInfo.add(args[i]);
          break;
      }
    }

    /*
    if( debugFlag == true ) {
      flightInfo.add("name");
      flightInfo.add("1393930");
      flightInfo.add("src");
      flightInfo.add("3/15/2017 10:39");
      flightInfo.add("des");
      flightInfo.add("03/2/2017 1:03");
      printFlightFlag = true;
      readMeFlag = true;
    }
    */

   //Error catches for SIZE for LISTs for Args and
    if( optsList.size() > 3 )
      throw new IllegalStateException("Optslist should have less than " + optsList.size() +" arguments");

    if( flightInfo.size() > 6) {
      throw new IllegalArgumentException("Flight info should be less than " + flightInfo.size() + " arguments");
    }
    else if( flightInfo.size() == 0 ){
      System.out.printf("Flight info is empty.\n");
    }
    else {
      //Check if fight number is positive numeric
      //Source: Stackoverflow
      if (flightInfo.get(1).matches("\\d+(\\.d\\d+)?") == false) {
        throw new IllegalArgumentException("Flight number is not a numeric value");
      }

      // Check for Source being three letters long
      flightInfo.set(2, SrcDestLengthCheck(flightInfo.get(2)));
      // Check for Dest being three letters long
      flightInfo.set(4, SrcDestLengthCheck(flightInfo.get(4)));
      //Date Check for Departure
      flightInfo.set(3, dateCheck(flightInfo.get(3)) );
      //Date Check for Arrival
      flightInfo.set(5, dateCheck(flightInfo.get(5)) );
    }

    //CATCH FOR FLAGS
    for( String temp: optsList ) {
//      System.out.println(temp);
      if (temp.toLowerCase().equals("print")) {
        printFlightFlag = true;
      }
      if (temp.toUpperCase().equals("README")) {
        readMeFlag = true;
      }
      if (temp.toLowerCase().equals("textfile")) {
        if (debugFlag == true)
          System.out.println(fileName);
        File file = new File(fileName);
        if (file.length() != 0) {
          Airline AirlinefromFile = new Airline();
          readFromFile( fileName, AirlinefromFile );
        }
      }
    }

    /**
    Flight tre = new Flight();
    tre.addFlightInfo(one);
    tre.setFlightName("test");

    //PROJECT TWO STUFF
    //Set Airline Information
    Airline two = new Airline();
    two.setName(one.getFlightName());

    for( int i = 0; i < args.length; i++ )
      two.addFlight(one);

    Airline fr = new Airline();
    fr.setName(tre.getFlightName());
    for( int i = 0; i < args.length; i++ )
      fr.addFlight(tre);

    airlines.add(two);
    airlines.add(fr);

    for( int i = 0; i< airlines.size(); i++ ){
      System.out.println( airlines.get(i).getName() );
      airlines.get(i).displayAirlineFlights();
    }

    for( Airline temp: airlines )
        temp.displayAirlineFlights();
    **/

    //PRINT AND README FLAGS
    if( readMeFlag == true ) {
      README();
    }
    else if( readMeFlag != true && printFlightFlag == true ) {
      System.out.println("Print Info Below");
      if( flightInfo.size() > 0 && flightInfo.size() <= 6){
        Flight one = new Flight();
        one.addFlightInfo(flightInfo);
        one.displayFlightInfo();
      }
    }

    System.exit(1);
  }


}
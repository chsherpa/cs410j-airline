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
import java.lang.Comparable;


/**
 * The main class for the CS410J airline Project
 */
public class Project3 {
  public static boolean debugFlag = false;

  /**
   * README : This explains usage of this program
   */
  public static void README(){
    int ProjNum = 3;
    System.out.println("Name: Chhewang Sherpa");
    System.out.println("Project " + ProjNum );
    System.out.println("The objectives of this project is to be able to input the following command"
    + "\nline usage, as well having that added to a flight object for future use in Project two"
    + "\nwith multiple Flight that are airlines.");
    System.out.println("Project "+ ProjNum +" was achieved through extensive Google searching through Stackoverflow"
    + "\nand the use of the Java natives libraries in the form of List<T> and Date.\n"
    + "\nUsage is described below:");
    System.out.println( "usage: java edu.pdx.cs410J.csherpa.Project3 [options] <args>");
    System.out.println( "args are (in this order):");
    System.out.printf("%-20s%s","name", "The name of the airline\n");
    System.out.printf("%-20s%s", "flightNumber", "The flight number\n");
    System.out.printf("%-20s%s", "src", "Three-letter code of departure airport\n");
    System.out.printf("%-20s%s", "departTime", "Departure date and time (12-hour time)\n");
    System.out.printf("%-20s%s","dest", "Three-letter code of arrival airport\n");
    System.out.printf("%-20s%s","arriveTime", "Arrival date and time (12-hour time)\n");
    System.out.println( "|_Date and time should be in the format: mm/dd/yyyy hh:mm");
    System.out.println( "\nflag options (options may appear in any order):");
    System.out.printf("%-20s%s", "-pretty file", "Pretty print the airline’s flights to\n");
    System.out.printf("%-20s%s","", "a text file or standard out (file -)\n");
    System.out.printf("%-20s%s","-textFile file", "Where to read/write the airline info\n");
    System.out.printf("%-20s%s","-print", "Prints a description of the new flight\n");
    System.out.printf("%-20s%s","-README", "Prints a README for this project and exits\n");

    /*
    System.out.println( "name"+ "\t\t\t\t" + "The name of the airline");
    System.out.println( "flightNumber"+ "\t\t" + "The flight number");
    System.out.println( "src" +"\t\t\t\t\t" + "Three-letter code of departure airport");
    System.out.println( "departTime"+ "\t\t\t" + "Departure date and time (24-hour time)");
    System.out.println( "dest"+ "\t\t\t\t" + "Three-letter code of arrival airport");
    System.out.println( "arriveTime"+ "\t\t\t" + "Arrival date and time (24-hour time)");
    System.out.println( "|_Date and time should be in the format: mm/dd/yyyy hh:mm");
    System.out.println( "\nflag options (options may appear in any order):");
    System.out.println("-pretty file" +"\t\t" + "Pretty print the airline’s flights to\n" +
            "\t\t\t\t\ta text file or standard out (file -)");
    System.out.println( "-textFile file" +"\t\t" +"Where to read/write the airline info");
    System.out.println( "-print"+ "\t\t\t\t" + "Prints a description of the new flight");
    System.out.println( "-README"+ "\t\t\t\t" + "Prints a README for this project and exits");
    */
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
   * Proper String Case Everything
   * @param proper
   * @return String First Letter is UpperCased, rest are left as is
   */
  private static String Proper( String proper )
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
   * Flight Info Checks
   * @param flightInfo
   */
  public static boolean FlightInfoCheck(List<String> flightInfo ){
    try
    {
        if( flightInfo.size() > 6 )
        {
          System.out.println("\nFlight info should only have 6 arguments.\n");
          System.out.println("System passed in the following arguments: " + flightInfo.toString() );
        }
        if( flightInfo.size() < 6 )
        {
          System.out.println("\nFlight info needs 6 arguments\n");
          System.out.println("System passed in the following arguments: " + flightInfo.toString() );
        }
    }
    catch( IllegalArgumentException ex)
    {
      throw new IllegalArgumentException( "\nSystem passed in the following arguments: " + ex.getMessage() +" " +flightInfo.toString() );
    }

    flightInfo.set(0, Proper(flightInfo.get(0)) );
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
    return true;
  }

  /**
   * File writer: Creates File object off of fileName param and uses
   * File methods to check of atomicity. That methods will create as
   * necessary.
   *
   * @param air
   * @param fileName
   * @throws IOException
   */
  private static void WriteOut(Airline air, String fileName ) throws IOException {
    File stdout = new File(fileName);
    stdout.createNewFile();
    TextDumper dumper = new TextDumper(new PrintWriter(new FileWriter(fileName, false)));
    dumper.dump(air);
  }

  /**
   * Pretty Print Out method for the Main Class
   * @param air Airline Object
   * @param fileName String; Name of the file to be written too
   * @throws IOException Standard IO exception catch
   * @throws ParseException Standard ParseException catch
   */
  private static void PrettyWriteOut( Airline air, String fileName ) throws IOException, ParseException {
    PrettyPrinter dumper = new PrettyPrinter();

    if(fileName.trim().equals("-") ){
      dumper.PrettyPrint(air);
    }
    else
    {
      File stdout = new File(fileName);
      stdout.createNewFile();
      dumper = new PrettyPrinter(new PrintWriter(new FileWriter(fileName, false)));
      dumper.PrettyPrintDump(air, fileName );
    }
  }

  /**
   *
   * @param args
   * @throws IOException
   * @throws ParserException
   */
  public static void main(String[] args) throws IOException, ParserException, ParseException {
    Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean readMeFlag = false;
    boolean printFlightFlag = false;
    boolean writeOutFlag = false;
    boolean prettyFlag = false;
    String fileName = new String();
    String prettyPrintFile = new String();

    List<String> FlagOptionsList = new ArrayList<String>();
    List<String> flightInfo = new ArrayList<String>();
    Airline airlineFromFile = new Airline();

   // System.out.println("Missing command line arguments");

    if( debugFlag == true ){ //Default Print
      System.out.println("All args");
      for (String arg : args) {
        System.out.print( arg + " ");
      }
    }

    //System Argv Parser
    // Source: Stackoverflow for case '-' and numeric ladder
    for( int i = 0; i < args.length; i++ ) {
      switch (args[i].charAt(0)) {
        case '-'://Flag Catch
          if (args[i].length() < 2)
            throw new IllegalArgumentException("Flag Arg Not Valid: " + args[i]);

          //TextFile Catch; create new file if file does not exists
          if( args[i].substring(1,args[i].length() ).equals("textFile") ){
            File testExists = new File(args[i + 1].trim());
            testExists.createNewFile();
            FlagOptionsList.add(args[i].substring(1, args[i].length()));
            fileName = new String(args[i + 1]).trim();
            writeOutFlag = true;
            i++;
            break;
          }

          //Pretty Print Catch
          if( args[i].substring(1,args[i].length() ).toLowerCase().equals("pretty") )
          {
              FlagOptionsList.add(args[i].substring(1,args[i].length()));
              prettyPrintFile = new String( args[i+1] ).trim();
              i++;
          }

          //Other flags catch
          FlagOptionsList.add(args[i].trim().substring(1,args[i].length()));
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
          //SimpleDateCheck: Only breaks if in format ##/ or #/
          if( args[i].charAt(1) == '/' || args[i].charAt(2) == '/' )
          {
            if ( args[i].trim().matches("\\d{1,2}/\\d{1,2}/\\d{4}") ) //DateRegexMatch
            {
              try
              {
                if (args[i + 1] != null && args[i + 1].trim().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) //TimeRegexMatch
                {
                  flightInfo.add(new String(args[i] + " " + args[i + 1] +" "+args[i+2]).trim());
                  i=i+2;
                  break;
                }
              }
              catch ( IllegalArgumentException ex )
              {
                throw new IllegalArgumentException("\nTime Arg passed in from Command Line not valid"
                        + "\nTime Arg passed: " + args[i + 1].toString() +"\n");
              }
              catch ( IndexOutOfBoundsException ex)
              {
                throw new IndexOutOfBoundsException( "\nMissing arguments: #"+ ex.getMessage()
                        + "\nCause: "+ ex.getCause() );
              }

            }
            else
            {
              throw new IllegalArgumentException("\nDate Args passed in from Command Line not valid"
                      + "\nDate Passed In: " + args[i].toString());
            }
          }
        default:
          flightInfo.add((args[i]).trim());
          break;
      }
    }

    if( debugFlag == true ) //Debugger Flight Object
    {
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
    if( FlagOptionsList.size() > 3 )
      throw new IllegalStateException("\nOptslist should have less than " + FlagOptionsList.size() +" arguments");

    //CATCH FOR FLAGS
    for( String flagArgs: FlagOptionsList )
    {
      if( debugFlag == true )
      {
        System.out.println(flagArgs);
      }
      if (flagArgs.toLowerCase().equals("print"))
      {
        printFlightFlag = true;
      }
      if (flagArgs.toUpperCase().equals("README"))
      {
        readMeFlag = true;
      }
      if (flagArgs.toLowerCase().equals("textfile"))
      {
        if (debugFlag == true)
        {
          System.out.println("\nFileNameParsed: " + fileName);
        }

        //fileName = new String(flight.getFlightName() + "Flights.txt");
        TextParser parser = new TextParser(new FileReader(fileName));
        airlineFromFile = parser.parse(); //Check for proper input done in airline class
      }
      if( flagArgs.toLowerCase().equals("pretty"))
      {
          prettyFlag = true;
      }
    }

    //PRINT AND README FLAGS
    if( readMeFlag == true )
    {
      README();
      System.exit(1);
    }

    //Add FlightInfo from System Argv Parsed Info
    FlightInfoCheck(flightInfo);
    Flight flight = new Flight(flightInfo);
    Airline AddedAirline = new Airline();
    AddedAirline.addFlight(flight);

    if( printFlightFlag == true )
    {
      System.out.println("Print Info Below");
      try {
        flight.displayFlightInfo();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    if( writeOutFlag == true )
    {
      if (airlineFromFile.getFlights().isEmpty()) {
        WriteOut(AddedAirline, fileName);
      } else if (AddedAirline.getName().equals(airlineFromFile.getName())) {
        //Add the new Airline Flight to the old Airlines Flight
        //Sort happens during adding of flight
        airlineFromFile.addFlight(flight);
        WriteOut(airlineFromFile, fileName);
      } else {
        //WriteOut both the parsed in airline and the new added airline from the parameter
        WriteOut(airlineFromFile, fileName);
        String fileName2 = new String(AddedAirline.getName() + "Flights.txt");
        WriteOut(AddedAirline, fileName2);
      }
    }

    if( prettyFlag == true )
    {
      if (airlineFromFile.getFlights().isEmpty()) {
        PrettyWriteOut( AddedAirline, prettyPrintFile );
      } else if (AddedAirline.getName().equals(airlineFromFile.getName())) {
        //Add the new Airline Flight to the old Airlines Flight
        airlineFromFile.addFlight(flight); //Add flight sorts it
        PrettyWriteOut( airlineFromFile, prettyPrintFile );
      } else {
        //WriteOut both the parsed in airline and the new added airline from the parameter
        PrettyWriteOut( AddedAirline, prettyPrintFile );
        String fileName2 = new String(AddedAirline.getName() + "Flights.txt");
        PrettyWriteOut( AddedAirline, fileName2 );
      }
    }
    System.exit(1);
  }
}
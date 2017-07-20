package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static edu.pdx.cs410J.chsherpa.Project1.*;

public class Flight extends AbstractFlight {

  /**
   * Default Constructor
   */
  public Flight(){}

  /**
   * Constructor with parameter List for easy input of List object
   *
   * @param flightInfo
   */
  public Flight( List<String> flightInfo ){
    addFlightInfo(flightInfo);
  }

  /**
   * Returns the name of the Flight
   * @return FlightName Name property of the Flight object
   */
  public String getFlightName(){
    return this.FlightName;
  }

  /**
   * Set the name of the Flight
   * @param flightName Name property of the Flight object
   */
  public void setFlightName( String flightName ){
    this.FlightName = flightName;
  }

  @Override
  /**
   * Override of parent function getNumber
   */
  public int getNumber() {
    return flightNumber;
  }

  /**
   * Set the Flight Number
   * @param flightNumber numeric flight number
   */
  public void setNumber( int flightNumber ){
    this.flightNumber = flightNumber;
  }

  @Override
  /**
   * Override of the AbstractClass Method getSource
   */
  public String getSource() {
    return src;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * Set the source of the flight
   * @param src Source property of the flight object
   */
  public void setSource(String src) {
    this.src = src;
  }

  @Override
  public String getDepartureString() {
    return departTime;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * Set the depart time of the flight object
   * @param departTime
   */
  public void setDepartureString( String departTime ){
    this.departTime = departTime;
  }

  @Override
  /**
   * Override of the AbstractClass Method getDestination
   */
  public String getDestination() {
    return dest;
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * Sets the destination property of the flight object
   * @param dest destination of the flight
   */
  public void setDestination( String dest ){
    this.dest = dest;
  }

  @Override
  /**
   * Override of the AbstractClass Method getArrivalString
   */
  public String getArrivalString() {
    return arriveTime;
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * Set the arrival time property of the flight object
   * @param arriveTime arrival time of the object; in format
   */
  public void setArrivalString( String arriveTime ){
    this.arriveTime = arriveTime;
  }

  /**
   * Method to add multiple flights to this object
   * @param flightInfo List Object of flights
   */
  public void addFlightInfo(List<String> flightInfo) {
      FlightInfoCheck(flightInfo);
      if( flightInfo.size() == 6){
        this.setFlightName(flightInfo.get(0));
        this.setNumber(Integer.parseInt(flightInfo.get(1)));
        this.setSource(flightInfo.get(2));
        this.setDepartureString(flightInfo.get(3));
        this.setDestination(flightInfo.get(4));
        this.setArrivalString(flightInfo.get(5));
      }
      else
        throw new IllegalArgumentException("Not enough arguments for adding to flight info");
      return;
  }

  /**
   * Display function for a Flight
   */
  public void displayFlightInfo() throws ParseException {
    System.out.println("Flight");
    System.out.println("|_"+"Name: "+this.getFlightName() );
    System.out.println("|_"+"Number: "+ this.getNumber() );
    System.out.println("|_"+"Source: "+ this.getSource() );
    System.out.printf("|_Departure Time: %s%n", prettyDate(this.getDepartureString()));
    System.out.println("|_"+"Destination: "+ this.getDestination() );
    System.out.printf("|_Arrival Time: %s%n", prettyDate(this.getArrivalString()));
    return;
  }

  /**
   * Pretty Date format for checking the date
   * @param inputDate
   * @return
   * @throws ParseException
   */
  public String prettyDate( String inputDate ) throws ParseException {
    Date date;
    DateFormat dateInput = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    date = dateInput.parse( inputDate );
    if( inputDate.equals(date) ){
        date = null;
    }
    return date.toString();
  }

  private String FlightName; //= "Flight Default";
  private int flightNumber = 42;
  private String src;//= "Source Airport";
  private String departTime;
  private String dest;// = "Destination Airport";
  private String arriveTime;
}

package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractFlight;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Flight extends AbstractFlight {

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
   * Method to set property by property of a single flight
   * @param one Flight object passed in
   */
  public void addFlightInfo(Flight one) {
    this.setFlightName(one.getFlightName());
    this.setNumber(one.getNumber());
    this.setSource(one.getSource());
    this.setDepartureString(one.getDepartureString());
    this.setDestination(one.getDestination());
    this.setArrivalString(one.getArrivalString());
    return;
  }

  /**
   * Display function for a Flight
   */
  public void displayFlightInfo(){
    System.out.println("Flight");
    System.out.println("|_"+"Name: "+this.getFlightName() );
    System.out.println("|_"+"Number: "+ this.getNumber() );
    System.out.println("|_"+"Source: "+ this.getSource() );
    System.out.println("|_"+"Departure Time: "+ this.getDepartureString() );
    System.out.println("|_"+"Destination: "+ this.getDestination() );
    System.out.println("|_"+"Arrival Time: "+ this.getArrivalString() );
    return;
  }

  private String FlightName; //= "Flight Default";
  private int flightNumber = 42;
  private String src;//= "Source Airport";
  private String departTime;
  private String dest;// = "Destination Airport";
  private String arriveTime;



}

package airReservation;
import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
	static ArrayList<Flight> list=new ArrayList<Flight>();
	static ArrayList<Reservation> reserv=new ArrayList<Reservation>();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		list.add(new Flight(123,"paris",5));
		list.add(new Flight(888,"london",3));
		list.add(new Flight(332,"bangolore",5));
		while(true) {
			System.out.println("\n-----Airline Reservation System-----");
			System.out.println("1.Display Available Flights");
			System.out.println("2.Book a Flight");
			System.out.println("3.view Reservations");
			System.out.println("4.cancel Booking");
			System.out.println("5.Exit");
			System.out.println("Choose an option:");
			int choice=getValidIntegerInput();
			
			switch(choice) {
			case 1:
				displayAvailableFlights();
				break;
			case 2:
				bookFlight();
				break;
			case 3:
				viewReservation();
				break;
			case 4:
				cancelBooking();
				break;
			case 5:
				System.out.println("Exiting the System");
				sc.close();
				return;
			default:
				System.out.println("Invalid Option, please try again");
			}
		}
	}
	private static void cancelBooking() {
		// TODO Auto-generated method stub
		System.out.println("Enter the name of the passenger to cancel the Flight");
		String passengerName=sc.next();
		Reservation reservationToCancel=null;
		for(Reservation r:reserv) {
			if(r.getName().equalsIgnoreCase(passengerName)) {
				reservationToCancel=r;
				break;
			}
		}
		if(reservationToCancel!=null) {
			Flight flight=reservationToCancel.getFlight();
			flight.setAvailableSeats(flight.getAvailableSeats()+1);
			reserv.remove(reservationToCancel);
			System.out.println("Reservation is cancelled for the Passenger");
		}
		else {
			System.out.println("No reservation made yet with the name:"+passengerName);
		}
	}
	private static void viewReservation() {
		// TODO Auto-generated method stub
		if(reserv.isEmpty()) {
			System.out.println("No Reservation is done yet!!!");
		}
		else {
			System.out.println("Reservations-----");
			for(Reservation r:reserv) {
				System.out.println("Passengername:"+r.getName());
				System.out.println("FlightNumber:"+r.getFlight().getFlightNumber());
				System.out.println("Designation:"+r.getFlight().getDesignation());
				System.out.println("-----------------------------------------");
			}
		}
	}
	private static void bookFlight() {
		// TODO Auto-generated method stub
		displayAvailableFlights();
		System.out.println("Enter the Flight Number to Book a Flight");
		int flightNumber=getValidIntegerInput();
		Flight selectFlight=null;
		for(Flight flight:list) {
			if(flight.getFlightNumber()==flightNumber) {
				selectFlight=flight;
				break;
			}
		}
		if(selectFlight==null) {
			System.out.println("Invallid Flight number.Try again!");
			return;
		}
		if(selectFlight.getAvailableSeats()>0) {
			System.out.println("Enter your name:");
			String passengerName=sc.next();
			Reservation reservation=new Reservation(passengerName,selectFlight);
			reserv.add(reservation);
			selectFlight.decreaseAvailableSeats();
			System.out.println("Booking successfull!!!");
		}
		else {
			System.out.println("Sorry, Seats are not available in the selected flight");
		}
		
	}
	private static void displayAvailableFlights() {
		// TODO Auto-generated method stub
		System.out.println("\n-----Available Flights-----");
		for(Flight f:list) {
			System.out.println("FlightNumber:"+f.getFlightNumber()+",Designation:"+f.getDesignation()+",AvailableSeats:"+f.getAvailableSeats());
		}
		
	}
	private static int getValidIntegerInput() {
		// TODO Auto-generated method stub
		while(!sc.hasNextInt()) {
			System.out.println("Enter a proper Number");
			sc.next();
		}
		return sc.nextInt();
	}
}

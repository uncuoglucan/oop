package flightSystem;

import java.util.*;

public class AirlineSystem {

    // 🔷 ENUMS
    enum AircraftStatus {
        WORKING,
        UNDER_MAINTENANCE
    }

    enum ExperienceLevel {
        JUNIOR,
        MID,
        SENIOR
    }

    // 🔷 AIRCRAFT TYPE
    static class AircraftType {
        private String typeName;
        private int requiredPilotCount;

        public AircraftType(String typeName, int requiredPilotCount) {
            this.typeName = typeName;
            this.requiredPilotCount = requiredPilotCount;
        }

        public String getTypeName() {
            return typeName;
        }

        public int getRequiredPilotCount() {
            return requiredPilotCount;
        }
    }

    // 🔷 AIRCRAFT
    static class Aircraft {
        private String id;
        private AircraftType type;
        private AircraftStatus status;

        public Aircraft(String id, AircraftType type, AircraftStatus status) {
            this.id = id;
            this.type = type;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public AircraftType getType() {
            return type;
        }

        public AircraftStatus getStatus() {
            return status;
        }
    }

    // 🔷 PILOT
    static class Pilot {
        private String id;
        private String name;
        private ExperienceLevel experienceLevel;

        public Pilot(String id, String name, ExperienceLevel experienceLevel) {
            this.id = id;
            this.name = name;
            this.experienceLevel = experienceLevel;
        }

        public String getName() {
            return name;
        }

        public ExperienceLevel getExperienceLevel() {
            return experienceLevel;
        }
    }

    // 🔷 AIRPORT
    static class Airport {
        private String id;
        private String name;

        public Airport(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // 🔷 FLIGHT
    static class Flight {
        private String id;
        private Airport departureAirport;
        private Airport arrivalAirport;
        private String departureTime;
        private String arrivalTime;

        private Pilot pilot;
        private Pilot coPilot;
        private Aircraft aircraft;

        public Flight(String id,
                      Airport departureAirport,
                      Airport arrivalAirport,
                      String departureTime,
                      String arrivalTime,
                      Pilot pilot,
                      Pilot coPilot,
                      Aircraft aircraft) {

            this.id = id;
            this.departureAirport = departureAirport;
            this.arrivalAirport = arrivalAirport;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.pilot = pilot;
            this.coPilot = coPilot;
            this.aircraft = aircraft;
        }

        public void printFlightInfo() {
            System.out.println("Flight ID: " + id);
            System.out.println("From: " + departureAirport.getName());
            System.out.println("To: " + arrivalAirport.getName());
            System.out.println("Pilot: " + pilot.getName());
            System.out.println("Co-Pilot: " + coPilot.getName());
            System.out.println("Aircraft: " + aircraft.getType().getTypeName());
            System.out.println("---------------------------");
        }
    }

    // 🔷 AIRLINE
    static class Airline {
        private String id;
        private String name;

        private List<Aircraft> aircrafts = new ArrayList<>();
        private List<Pilot> pilots = new ArrayList<>();
        private List<Flight> flights = new ArrayList<>();

        public Airline(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void addAircraft(Aircraft aircraft) {
            aircrafts.add(aircraft);
        }

        public void addPilot(Pilot pilot) {
            pilots.add(pilot);
        }

        public void addFlight(Flight flight) {
            flights.add(flight);
        }

        public void printAllFlights() {
            System.out.println("Airline: " + name);
            for (Flight flight : flights) {
                flight.printFlightInfo();
            }
        }
    }

    // 🔷 MAIN (TEST)
    public static void main(String[] args) {

        // Aircraft Types
        AircraftType boeing737 = new AircraftType("Boeing 737", 2);
        AircraftType airbusA320 = new AircraftType("Airbus A320", 2);

        // Aircrafts
        Aircraft aircraft1 = new Aircraft("AC1", boeing737, AircraftStatus.WORKING);
        Aircraft aircraft2 = new Aircraft("AC2", airbusA320, AircraftStatus.WORKING);

        // Airports
        Airport istanbul = new Airport("IST", "Istanbul Airport");
        Airport ankara = new Airport("ESB", "Ankara Esenboğa Airport");

        // Pilots
        Pilot pilot1 = new Pilot("P1", "Ahmet", ExperienceLevel.SENIOR);
        Pilot pilot2 = new Pilot("P2", "Mehmet", ExperienceLevel.MID);

        // Flight
        Flight flight1 = new Flight(
                "F1",
                istanbul,
                ankara,
                "10:00",
                "11:00",
                pilot1,
                pilot2,
                aircraft1
        );

        // Airline
        Airline airline = new Airline("THY", "Turkish Airlines");
        airline.addAircraft(aircraft1);
        airline.addAircraft(aircraft2);
        airline.addPilot(pilot1);
        airline.addPilot(pilot2);
        airline.addFlight(flight1);

        // Print
        airline.printAllFlights();
    }
}
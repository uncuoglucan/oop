package movie;

import java.util.*;

public class MovieSystem {

    // 🔷 MOVIE
    static class Movie {
        private String id;
        private String title;
        private double price;
        private double rentalPrice;
        private boolean isAvailable;

        public Movie(String id, String title, double price, double rentalPrice, boolean isAvailable) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.rentalPrice = rentalPrice;
            this.isAvailable = isAvailable;
        }

        public String getTitle() { return title; }
        public double getPrice() { return price; }
        public double getRentalPrice() { return rentalPrice; }
        public boolean isAvailable() { return isAvailable; }
    }

    // 🔷 USER (BASE)
    static class User {
        protected String id;
        protected String name;
        protected List<Purchase> purchases = new ArrayList<>();

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void buyMovie(Movie movie) {
            purchases.add(new Purchase(movie, new Date()));
            System.out.println(name + " bought: " + movie.getTitle());
        }
    }

    // 🔷 SUBSCRIBER
    static class Subscriber extends User {
        private double creditBalance;
        private List<Rental> rentals = new ArrayList<>();
        private List<CreditTransaction> transactions = new ArrayList<>();

        public Subscriber(String id, String name) {
            super(id, name);
        }

        public void buyCredit(double amount) {
            creditBalance += amount;
            transactions.add(new CreditTransaction(amount, new Date()));
            System.out.println(name + " added credit: " + amount);
        }

        public void rentMovie(Movie movie) {
            if (!movie.isAvailable()) {
                System.out.println("Movie not available!");
                return;
            }

            if (creditBalance >= movie.getRentalPrice()) {
                creditBalance -= movie.getRentalPrice();
                rentals.add(new Rental(movie, new Date(), 48));
                System.out.println(name + " rented: " + movie.getTitle());
            } else {
                System.out.println("Not enough credit!");
            }
        }
    }

    // 🔷 PURCHASE
    static class Purchase {
        private Movie movie;
        private Date purchaseDate;

        public Purchase(Movie movie, Date purchaseDate) {
            this.movie = movie;
            this.purchaseDate = purchaseDate;
        }
    }

    // 🔷 RENTAL
    static class Rental {
        private Movie movie;
        private Date rentalDate;
        private int durationHours;

        public Rental(Movie movie, Date rentalDate, int durationHours) {
            this.movie = movie;
            this.rentalDate = rentalDate;
            this.durationHours = durationHours;
        }
    }

    // 🔷 CREDIT TRANSACTION
    static class CreditTransaction {
        private double amount;
        private Date date;

        public CreditTransaction(double amount, Date date) {
            this.amount = amount;
            this.date = date;
        }
    }

    // 🔷 REQUEST (Film talebi)
    static class Request {
        private String movieName;
        private Date requestDate;
        private User user;

        public Request(String movieName, User user) {
            this.movieName = movieName;
            this.user = user;
            this.requestDate = new Date();
        }
    }

    // 🔷 MAIN (TEST)
    public static void main(String[] args) {

        // Movies
        Movie movie1 = new Movie("1", "Inception", 50, 10, true);
        Movie movie2 = new Movie("2", "Interstellar", 60, 12, false);

        // Users
        User normalUser = new User("U1", "Ali");
        Subscriber subscriber = new Subscriber("U2", "Veli");

        // Buy movie (everyone can)
        normalUser.buyMovie(movie1);
        subscriber.buyMovie(movie1);

        // Credit & rent
        subscriber.buyCredit(20);
        subscriber.rentMovie(movie1); // başarılı
        subscriber.rentMovie(movie2); // available değil

        // Request movie
        Request request = new Request("Avatar 3", normalUser);

        System.out.println("System test completed.");
    }
}
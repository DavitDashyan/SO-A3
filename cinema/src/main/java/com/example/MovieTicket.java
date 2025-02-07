package com.example;

public class MovieTicket {
    private MovieScreening movieScreening;
    private boolean isPremium;
    private int seatRow;
    private int seatNr;

    public MovieTicket(MovieScreening movieScreening, boolean isPremiumReservation, int seatRow, int seatNr) {
        this.movieScreening = movieScreening;
        this.isPremium = isPremiumReservation;
        this.seatRow = seatRow;
        this.seatNr = seatNr;
    }

    public boolean isPremiumTicket() {
        return isPremium;
    }

    public double getPrice() {
        double basePrice = movieScreening.getPricePerSeat();
        return basePrice;
    }

    public MovieScreening getMovieScreening() {
        return movieScreening;
    }

    public String toString() {
        return String.format("Rij %d, Stoel %d (%s) - %s", 
            seatRow, 
            seatNr, 
            isPremium ? "Premium" : "Standaard",
            movieScreening);
    }
}
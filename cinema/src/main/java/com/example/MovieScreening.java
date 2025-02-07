package com.example;

import java.time.LocalDateTime;

public class MovieScreening {
    private LocalDateTime dateAndTime;
    private double pricePerSeat;
    private Movie movie;

    public MovieScreening(Movie movie, LocalDateTime dateAndTime, double pricePerSeat) {
        this.movie = movie;
        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String toString() {
        return movie + " op " + dateAndTime;
    }
}
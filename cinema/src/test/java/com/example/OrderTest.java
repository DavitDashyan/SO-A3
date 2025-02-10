package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class OrderTest {

    @Test
    public void testNoTickets() {
        Order order = new Order(1, false);
        assertEquals(0.0, order.calculatePrice());
    }

    @Test
    public void testSingleStandardTicketNoStudent() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        Order order = new Order(1, false);
        order.addSeatReservation(new MovieTicket(screening, false, 1, 1));
        assertEquals(10.0, order.calculatePrice());
    }

    @Test
    public void testSinglePremiumTicketNoStudent() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        Order order = new Order(1, false);
        order.addSeatReservation(new MovieTicket(screening, true, 1, 1));
        assertEquals(13.0, order.calculatePrice());
    }

    @Test
    public void testSingleStandardTicketStudent() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        Order order = new Order(1, true);
        order.addSeatReservation(new MovieTicket(screening, false, 1, 1));
        assertEquals(10.0, order.calculatePrice());
    }

    @Test
    public void testSinglePremiumTicketStudent() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        Order order = new Order(1, true);
        order.addSeatReservation(new MovieTicket(screening, true, 1, 1));
        assertEquals(12.0, order.calculatePrice());
    }

    @Test
    public void testMultipleStandardTicketsNoStudentWeekday() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 20.0);
        Order order = new Order(1, false);
        order.addSeatReservation(new MovieTicket(screening, false, 1, 1));
        order.addSeatReservation(new MovieTicket(screening, false, 1, 2));
        assertEquals(20.0, order.calculatePrice());
    }

    // @Test
    // public void testMultipleStandardTicketsNoStudentWeekend() {
    // Movie movie = new Movie("The Matrix");
    // MovieScreening screening = new MovieScreening(movie,
    // LocalDateTime.now().plusDays(3), 10.0); // Weekend
    // Order order = new Order(1, false);
    // order.addSeatReservation(new MovieTicket(screening, false, 1, 1));
    // order.addSeatReservation(new MovieTicket(screening, false, 1, 2));
    // assertEquals(20.0, order.calculatePrice());
    // }

    @Test
    public void testMultiplePremiumTicketsNoStudentWeekday() {
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        Order order = new Order(1, false);
        order.addSeatReservation(new MovieTicket(screening, true, 1, 1));
        order.addSeatReservation(new MovieTicket(screening, true, 1, 2));
        assertEquals(13.0, order.calculatePrice());
    }

    // @Test
    // public void testMultiplePremiumTicketsNoStudentWeekend() {
    // Movie movie = new Movie("The Matrix");
    // MovieScreening screening = new MovieScreening(movie,
    // LocalDateTime.now().plusDays(3), 10.0); // Weekend
    // Order order = new Order(1, false);
    // order.addSeatReservation(new MovieTicket(screening, true, 1, 1));
    // order.addSeatReservation(new MovieTicket(screening, true, 1, 2));
    // assertEquals(26.0, order.calculatePrice());
    // }

    // @Test
    // public void testMultipleMixedTicketsStudentWeekday() {
    // Movie movie = new Movie("The Matrix");
    // MovieScreening screening = new MovieScreening(movie,
    // LocalDateTime.now().plusDays(1), 10.0);
    // Order order = new Order(1, true);
    // order.addSeatReservation(new MovieTicket(screening, false, 1, 1));
    // order.addSeatReservation(new MovieTicket(screening, true, 1, 2));
    // assertEquals(22.0, order.calculatePrice());
    // }

    // @Test
    // public void testMultipleMixedTicketsNoStudentWeekendGroupDiscount() {
    // Movie movie = new Movie("The Matrix");
    // MovieScreening screening = new MovieScreening(movie,
    // LocalDateTime.now().plusDays(3), 10.0); // Weekend
    // Order order = new Order(1, false);
    // for (int i = 0; i < 6; i++) {
    // order.addSeatReservation(new MovieTicket(screening, i % 2 == 0, 1, i + 1));
    // }
    // assertEquals(54.0, order.calculatePrice()); // 10% korting op 60.0
    // }
}
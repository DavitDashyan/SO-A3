package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private List<MovieTicket> tickets;
    private static int nextOrderNr = 1;

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
        this.tickets = new ArrayList<>();
    }

    public int getOrderNr() {
        return orderNr;
    }

    public void addSeatReservation(MovieTicket ticket) {
        tickets.add(ticket);
    }

    public double calculatePrice() {
        if (tickets.isEmpty()) return 0.0;

        double totalPrice = 0.0;

        for (int i = 0; i < tickets.size(); i++) {
            MovieTicket ticket = tickets.get(i);
            double ticketPrice = ticket.getPrice();
            
            // Premium toeslag
            if (ticket.isPremiumTicket()) {
                ticketPrice += isStudentOrder ? 2.0 : 3.0;
            }

            // Gratis tweede kaartje voor studenten of doordeweeks
            boolean isWeekday = ticket.getMovieScreening().getDateAndTime().getDayOfWeek().getValue() <= 4;
            if (isStudentOrder || (isWeekday && !isStudentOrder)) {
                if (i % 2 == 1) {  // Elk tweede kaartje
                    ticketPrice = 0.0;
                }
            }

            totalPrice += ticketPrice;
        }

        // Groepskorting voor niet-studenten in het weekend
        boolean isWeekend = tickets.get(0).getMovieScreening().getDateAndTime().getDayOfWeek().getValue() > 4;
        if (!isStudentOrder && isWeekend && tickets.size() >= 6) {
            totalPrice *= 0.9;  // 10% korting
        }

        return Math.round(totalPrice * 100.0) / 100.0;
    }

    public void export(TicketExportFormat exportFormat) {
        String filename = "order_" + orderNr + (exportFormat == TicketExportFormat.JSON ? ".json" : ".txt");
        
        try {
            switch (exportFormat) {
                case PLAINTEXT:
                    exportToPlainText(filename);
                    break;
                case JSON:
                    exportToJSON(filename);
                    break;
            }
            System.out.println("Order geëxporteerd naar " + filename);
        } catch (IOException e) {
            System.err.println("Fout bij exporteren: " + e.getMessage());
        }
    }

    private void exportToPlainText(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Order #" + orderNr + "\n");
            writer.write("Type: " + (isStudentOrder ? "Student" : "Regulier") + "\n");
            writer.write("Aantal kaartjes: " + tickets.size() + "\n");
            writer.write("Totaalprijs: €" + String.format("%.2f", calculatePrice()) + "\n\n");
            
            for (MovieTicket ticket : tickets) {
                writer.write(ticket.toString() + "\n");
            }
        }
    }

    private void exportToJSON(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"orderNr\": " + orderNr + ",\n");
            json.append("  \"isStudentOrder\": " + isStudentOrder + ",\n");
            json.append("  \"totalPrice\": " + calculatePrice() + ",\n");
            json.append("  \"tickets\": [\n");

            for (int i = 0; i < tickets.size(); i++) {
                MovieTicket ticket = tickets.get(i);
                json.append("    {\n");
                json.append("      \"screening\": \"" + ticket.getMovieScreening().toString() + "\",\n");
                json.append("      \"isPremium\": " + ticket.isPremiumTicket() + ",\n");
                json.append("      \"price\": " + ticket.getPrice() + "\n");
                json.append("    }" + (i < tickets.size() - 1 ? "," : "") + "\n");
            }

            json.append("  ]\n");
            json.append("}");

            writer.write(json.toString());
        }
    }
}
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Voorbeeld setup
        Movie movie = new Movie("The Matrix");
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.now().plusDays(1), 10.0);
        movie.addScreening(screening);

        System.out.println("Welkom bij het bioscoopkaartjes systeem!");
        
        System.out.print("Is dit een studentenbestelling? (ja/nee): ");
        boolean isStudent = scanner.nextLine().toLowerCase().startsWith("j");
        
        Order order = new Order(1, isStudent);
        
        while (true) {
            System.out.print("Wilt u een kaartje toevoegen? (ja/nee): ");
            if (!scanner.nextLine().toLowerCase().startsWith("j")) break;
            
            System.out.print("Wilt u een premium stoel? (ja/nee): ");
            boolean isPremium = scanner.nextLine().toLowerCase().startsWith("j");
            
            System.out.print("Rijnummer: ");
            int row = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Stoelnummer: ");
            int seatNr = Integer.parseInt(scanner.nextLine());
            
            MovieTicket ticket = new MovieTicket(screening, isPremium, row, seatNr);
            order.addSeatReservation(ticket);
            
            System.out.println("Kaartje toegevoegd!");
        }
        
        System.out.println("\nTotaalprijs: €" + order.calculatePrice());
        
        System.out.print("Wilt u de order exporteren? (ja/nee): ");
        if (scanner.nextLine().toLowerCase().startsWith("j")) {
            System.out.print("In welk formaat? (1: PLAINTEXT, 2: JSON): ");
            int format = Integer.parseInt(scanner.nextLine());
            order.export(format == 1 ? TicketExportFormat.PLAINTEXT : TicketExportFormat.JSON);
        }
        
        scanner.close();
        System.out.println("Bedankt voor uw bestelling!");
    }
}
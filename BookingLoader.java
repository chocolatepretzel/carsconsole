// BookingLoader.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BookingLoader {
    public static List<Booking> loadBookingsFromCSV(String fileName) {
        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Fejléc átugrása
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String startDate = fields[1];
                String endDate = fields[2];
                int carId = Integer.parseInt(fields[3]);
                int totalPrice = Integer.parseInt(fields[4]);
                String userUID = fields[5];

                bookings.add(new Booking(id, startDate, endDate, carId, totalPrice, userUID));
            }
        } catch (Exception e) {
            System.out.println("Hiba a foglalások beolvasásakor: " + e.getMessage());
        }

        return bookings;
    }
}

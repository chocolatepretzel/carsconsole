import java.io.*;
import java.util.*;

public class FoglalasokCSVKiiras {

    // Metódus a foglalások CSV fájlba írására
    public static void writeSortedBookings(List<Booking> bookings) {
        // Sorba rendezés
        bookings.sort(Comparator.comparing(Booking::getStartDate));

        // CSV fájlba írás
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("foglalasok.csv"))) {
            // Írjuk a fejlécet
            writer.write("id,startDate,endDate,carId,totalPrice,userUID\n");

            // Írjuk a foglalásokat
            for (Booking booking : bookings) {
                writer.write(booking.getId() + "," +
                             booking.getStartDate() + "," +
                             booking.getEndDate() + "," +
                             booking.getCarId() + "," +
                             booking.getTotalPrice() + "," +
                             booking.getUserUID() + "\n");
            }
            System.out.println("Foglalások sikeresen kiírva a foglalasok.csv fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása közben: " + e.getMessage());
        }
    }
}

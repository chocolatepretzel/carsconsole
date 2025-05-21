import java.io.*;
import java.util.*;

public class NemHasznaltCSVKiiras {

    public static void writeUnusedCars(List<Car> cars, List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nemhasznalt.csv"))) {
            writer.write("carId,brand,model\n");

            for (Car car : cars) {
                boolean hasBooking = false;

                for (Booking booking : bookings) {
                    if (booking.getCarId() == car.getId()) {
                        hasBooking = true;
                        break;
                    }
                }
                if (!hasBooking) {
                    writer.write(car.getId() + "," + car.getBrand() + "," + car.getModel() + "," +
                            car.getLicensePlate() + "," + car.getYear() + "," + car.getDailyPrice() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása Közben: " + e.getMessage());
        }
    }

}

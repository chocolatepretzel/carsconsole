import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class BevetelCSVKiiras {

    public static void writeRevenuePerCar(List<Car> cars, List<Booking> bookings) {

        // CSV fájlba írás
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bevetel.csv"))) {
            writer.write("carId,brand,model,revenue\n");

            for (Car car : cars) {
                int revenue = 0;

                for (Booking booking : bookings) {
                    if (booking.getCarId() == car.getId()) {
                        revenue += booking.getTotalPrice();
                    }
                }
                writer.write(car.getId() + "," + car.getBrand() + "," + car.getModel() + "," + revenue + "\n");
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása Közben: " + e.getMessage());

        }
    }

}

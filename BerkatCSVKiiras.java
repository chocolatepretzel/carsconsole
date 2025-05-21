import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BerkatCSVKiiras {

    public static void writeAverageDailyPricePerBrand(List<Car> cars) {
        List<String> uniqueBrands = new ArrayList<>();

        // Egyedi márkák kigyűjtése
        for (Car car : cars) {
            if (!uniqueBrands.contains(car.getBrand())) {
                uniqueBrands.add(car.getBrand());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("berkat.csv"))) {
            writer.write("brand,averageDailyPrice\n");

            for (String brand : uniqueBrands) {
                int sum = 0;
                int count = 0;

                for (Car car : cars) {
                    if (car.getBrand().equals(brand)) {
                        sum += car.getDailyPrice();
                        count++;
                    }
                }

                double avg = count > 0 ? (double) sum / count : 0;
                writer.write(brand + "," + String.format("%.2f", avg) + "\n");
            }

            System.out.println("berkat.csv fájl sikeresen létrehozva Map nélkül.");
        } catch (IOException e) {
            System.out.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }
}

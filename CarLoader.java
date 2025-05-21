import java.io.*;
import java.util.*;

public class CarLoader {

    //Ez egy statikus metódus, amely bemeneti paraméterként kap egy fájlnevet (filename), majd visszatér egy List<Car> listával.
    public static List<Car> loadCarsFromCSV(String filename) {
        List<Car> cars = new ArrayList<>();

        //Ez nyitja meg a fájlt olvasásra.
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;


            //A CSV fájl első sora valószínűleg oszlopneveket tartalmaz (pl. id,brand,model,...), ezért az első sort continue-val átugorjuk.
            while ((line = br.readLine()) != null) {
                // Első sort (fejlécet) kihagyjuk
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // A CSV sort vessző mentén feldaraboljuk
                String[] parts = line.split(",");

                // Minden oszlop értéket kinyerünk
                int id = Integer.parseInt(parts[0]);
                String brand = parts[1];
                String model = parts[2];
                String licensePlate = parts[3];
                int year = Integer.parseInt(parts[4]);
                int dailyPrice = Integer.parseInt(parts[5]);

                // Létrehozzuk az autó objektumot
                Car car = new Car(id, brand, model, licensePlate, year, dailyPrice);

                // Hozzáadjuk a listához
                cars.add(car);
            }

        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }

        return cars;
    }
}

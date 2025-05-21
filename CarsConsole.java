
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarsConsole {
    public static void main(String[] args) {

        List<Car> cars = CarLoader.loadCarsFromCSV("cars.csv");
        List<Booking> bookings = BookingLoader.loadBookingsFromCSV("bookings.csv");
        FoglalasokCSVKiiras.writeSortedBookings(bookings);
        BevetelCSVKiiras.writeRevenuePerCar(cars, bookings);
        NemHasznaltCSVKiiras.writeUnusedCars(cars, bookings);
        BerkatCSVKiiras.writeAverageDailyPricePerBrand(cars);

        // _________________1.feladat
        // Rendezés napi díj szerint csökkenően
        cars.sort(Comparator.comparing(Car::getDailyPrice).reversed());

        // Kiírás
        for (Car car : cars) {
            System.out.println(car);
        }

        // _____________________________________________

        // _________________ 2.feladat
        // Lista minden autóra, hogy tároljuk az összesített díjat
        for (Car car : cars) {
            int totalPrice = 0;

            // Végigmegyünk minden foglaláson
            for (Booking booking : bookings) {
                if (booking.getCarId() == car.getId()) {
                    // A foglalás napi díját szorozzuk a napok számával
                    LocalDate startDate = LocalDate.parse(booking.getStartDate(), DateTimeFormatter.ISO_DATE);
                    LocalDate endDate = LocalDate.parse(booking.getEndDate(), DateTimeFormatter.ISO_DATE);
                    long days = ChronoUnit.DAYS.between(startDate, endDate);

                    totalPrice += days * car.getDailyPrice();
                }
            }

            // Ha volt foglalás az autóra, kiírjuk
            if (totalPrice > 0) {
                System.out.println(
                        "Autó: " + car.getBrand() + " " + car.getModel() + " - Összesen: " + totalPrice + " Ft");
            }
        }

        // _________________________________

        // ___________________3. feladat

        Car leggyakrabbanFoglaltAuto = null;
        int maxFoglaloDb = 0;

        for (Car car : cars) {
            int foglalasokSzama = 0;

            // Végigmegyünk a foglalásokon és megszámoljuk, hányszor szerepel az autó
            for (Booking booking : bookings) {
                if (booking.getCarId() == car.getId()) {
                    foglalasokSzama++;
                }
            }

            // Ha több foglalása volt, mint eddig bármelyik autónak, frissítjük
            if (foglalasokSzama > maxFoglaloDb) {
                maxFoglaloDb = foglalasokSzama;
                leggyakrabbanFoglaltAuto = car;
            }
        }

        // Eredmény kiírása
        if (leggyakrabbanFoglaltAuto != null) {
            System.out.println("A legtöbbször foglalt autó: " + leggyakrabbanFoglaltAuto.getBrand() +
                    " " + leggyakrabbanFoglaltAuto.getModel() + " - Foglalások száma: " + maxFoglaloDb);
        } else {
            System.out.println("Nincs foglalás.");
        }
        // _________________________________________

        // ______________4.feladat

        Car legtobbetHozoAuto = null;
        int maxBevetel = 0;

        for (Car car : cars) {
            int osszeg = 0;

            // Számoljuk össze az adott autóra eső bevételeket
            for (Booking booking : bookings) {
                if (booking.getCarId() == car.getId()) {
                    osszeg += booking.getTotalPrice();
                }
            }

            // Ellenőrizzük, hogy ez-e a legnagyobb érték
            if (osszeg > maxBevetel) {
                maxBevetel = osszeg;
                legtobbetHozoAuto = car;
            }
        }

        // Eredmény kiírása
        if (legtobbetHozoAuto != null) {
            System.out.println("A legtöbb bevételt hozó autó: " + legtobbetHozoAuto.getBrand() +
                    " " + legtobbetHozoAuto.getModel() + " - Bevétel: " + maxBevetel + " Ft");
        } else {
            System.out.println("Nincs foglalás.");
        }

        // ________________________________

        // ______________5.feladat

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long totalDays = 0;
        int bookingCount = bookings.size();

        for (Booking booking : bookings) {
            LocalDate start = LocalDate.parse(booking.getStartDate(), formatter);
            LocalDate end = LocalDate.parse(booking.getEndDate(), formatter);
            long days = ChronoUnit.DAYS.between(start, end);
            totalDays += days;
        }

        double average = (double) totalDays / bookingCount;
        System.out.println("Átlagos bérleti időtartam: " + String.format("%.2f",
                average) + " nap");

        // ________________________________

        // ______________6.feladat

        Booking latestBooking = null;
        LocalDate latestDate = null;
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Booking booking : bookings) {
            LocalDate start = LocalDate.parse(booking.getStartDate(), formatter);

            if (latestDate == null || start.isAfter(latestDate)) {
                latestDate = start;
                latestBooking = booking;
            }
        }
        if (latestBooking != null) {
            int carId = latestBooking.getCarId();
            Car bookedCar = null;

            for (Car car : cars) {
                if (car.getId() == carId) {
                    bookedCar = car;
                    break;
                }
            }

            if (bookedCar != null) {
                System.out.println("6) Legutóbb foglalt autó:");
                System.out.println("Márka: " + bookedCar.getBrand());
                System.out.println("Modell: " + bookedCar.getModel());
                System.out.println("Rendszám: " + bookedCar.getLicensePlate());
                System.out.println("Dátum: " + latestDate);
            } else {
                System.out.println("Nincs hozzá tartozó autó.");
            }
        } else {
            System.out.println("Nincs egyetlen foglalás sem.");
        }

        // ______________________________

        // ______________7.feladat

        int totalRevenue = 0;
        for (Booking booking : bookings) {
            totalRevenue += booking.getTotalPrice();
        }
        System.out.println("7) Az osszes bevetel: " + totalRevenue + " Ft");

        // ________________________________
    }
}

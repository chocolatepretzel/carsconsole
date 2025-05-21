public class Car {
        // 🔸 Adattagok (mezők), ezek tárolják az autó tulajdonságait:
    private int id;
    private String brand;
    private String model;
    private String licensePlate;
    private int year;
    private int dailyPrice;

    public Car(int id, String brand, String model, String licensePlate, int year, int dailyPrice) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.dailyPrice = dailyPrice;
    }

    
    // Getterek
    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getLicensePlate() { return licensePlate; }
    public int getYear() { return year; }
    public int getDailyPrice() { return dailyPrice; }

    @Override
    public String toString() {
        return String.format("%d - %s %s (%s) - %d Ft/nap", id, brand, model, licensePlate, dailyPrice);
    }
}

// Booking.java
public class Booking {
    private int id;
    private String startDate;
    private String endDate;
    private int carId;
    private int totalPrice;
    private String userUID;

    // Konstruktor
    public Booking(int id, String startDate, String endDate, int carId, int totalPrice, String userUID) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
        this.totalPrice = totalPrice;
        this.userUID = userUID;
    }

    // Getter metódusok
    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getCarId() {
        return carId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getUserUID() {
        return userUID;
    }
}

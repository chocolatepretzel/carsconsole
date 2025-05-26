1. Általános lépések minden hasonló feladathoz
1. Projekt előkészítése
Hozz létre egy új Java projektet (pl. CarsConsole néven)

Készíts 3 fő osztályt:

Car – az autók adatmodellje

Booking – a foglalások adatmodellje

CarsConsole – a main() metódust tartalmazó futtatható osztály

2. CSV fájlkezelő osztályok
CarLoader → beolvassa a cars.csv fájlt és visszaad List<Car> listát

BookingLoader → beolvassa a bookings.csv fájlt és visszaad List<Booking> listát

3. Adatok reprezentálása
Minden adatmodell tartalmazza:

java
Copy
Edit
// Példa: Car.java
public class Car {
    private int id;
    private String brand;
    private String model;
    private String licensePlate;
    private int year;
    private int dailyPrice;

    // Konstruktor, getterek, toString()
}
📊 Feladattípusok és megközelítések
1) Autók listázása napi bérleti díj szerint (csökkenő sorrendben)
📌 cars.sort(Comparator.comparing(Car::getDailyPrice).reversed())
🖨️ Ezután kiírás a képernyőre.

2) Foglalások kiírása (autó márkával és bérleti díjjal)
📌 Minden Booking alapján keresd meg a hozzá tartozó Car objektumot.
➕ Írd ki: márka + modell + napi díj + dátumok.

3) Legtöbbször foglalt autó
📌 Számold meg autóként, hányszor szerepel a foglalások között (getCarId())
🧮 Map<Integer, Integer> vagy simán for ciklussal számlálás
🔎 Legnagyobb érték megkeresése

4) Legtöbb bevételt hozó autó
📌 Minden Booking tartalmaz egy getTotalPrice() értéket
➕ Összeadod autónként
🔍 Keresd meg azt, amelyiknek a legnagyobb az összeg

5) Átlagos bérleti időtartam
📆 LocalDate-ből számold a napokat:

java
Copy
Edit
long days = ChronoUnit.DAYS.between(startDate, endDate);
➕ Összegzed és elosztod a foglalások számával

6) Legutóbb kezdődő foglalás
📅 Legnagyobb startDate megkeresése
📌 Ismét LocalDate.parse(...), majd isAfter(...) metódussal összehasonlítás

7) Összes bevétel
💰 Egyszerű összeadás: minden foglalás getTotalPrice() értékét add össze

8) Foglalások havi bontásban → írás CSV-be
📆 LocalDate startDate = LocalDate.parse(...) → startDate.getMonth() és getYear()
📁 Írd ki: YYYY-MM szerint csoportosítva
📝 CSV fájlba BufferedWriter-rel

9) Autóként összesített bevétel → CSV-be
📊 Map-et használhatsz: Map<Integer, Integer> (carId → bevétel)
📁 CSV-be kiírás: id,brand,model,totalRevenue

10) Nem használt autók → CSV-be
🔍 Minden Car esetén nézd meg, hogy egyik foglalásban sem szerepel
📄 Ha nincs Booking, ami az adott autóra vonatkozik → kiírás CSV-be

11) Márkánként átlagos napi díj → CSV-be
📊 Map: Map<String, List<Integer>> vagy Map<String, Integer[2]> → [összeg, darabszám]
📈 Átlag: összeg / darab
📝 Írd ki: brand,averageDailyPrice

🛠️ Hasznos eszközök és Java osztályok
Cél	Osztály / Metódus
Fájl beolvasás	BufferedReader, FileReader
Fájl írás	BufferedWriter, FileWriter
Dátumkezelés	LocalDate, DateTimeFormatter
Napok közti különbség	ChronoUnit.DAYS.between(...)
Rendezes	Comparator.comparing(...)
Formázás	String.format(...)

📌 Vizsgán Hasznos Tipp
Mindig tesztelj kis CSV fájllal: 2-3 sor bőven elég a próbához.

Dátum formátumok egyezzenek: "yyyy-MM-dd" legyen mindenhol, ha ilyen van a fájlban.

Ha valami nem működik: Ellenőrizd, hogy minden fájlt beolvastál-e, és hogy jól vannak-e a mezők indexelve.

Kód tagolása segít: külön osztály a fájlíráshoz pl. FoglalasokCSVKiiras.java, stb.

Segédfüggvények használata: ha több helyen keresel autót id alapján → írj getCarById(...) segédfüggvényt.

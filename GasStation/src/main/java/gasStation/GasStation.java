package gasStation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class GasStation extends Thread {
    private CashDesk cashDesk1;
    private CashDesk cashDesk2;
    private Employee employee1 = new Employee();
    private Employee employee2 = new Employee();
    private ArrayList<Queue<Car>> kolonki = new ArrayList<>();
    private ArrayList<ArrayBlockingQueue<Car>> kasi = new ArrayList<>();
    private HashMap<Integer, HashMap<Car.FuelType, ConcurrentHashMap<LocalDateTime, Integer>>> dataCollection; //kolonka -> fuel type -> amount, date

    public GasStation() {
        buildCollections();
        cashDesk1 = new CashDesk(kasi.get(0));
        cashDesk2 = new CashDesk(kasi.get(1));
        Employee.gasStation = this;
        CashDesk.gasStation = this;
        employee1.start();
        employee2.start();
        cashDesk1.start();
        cashDesk2.start();
    }

    private void buildCollections() {
        dataCollection = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            this.kolonki.add(new LinkedList<>());
            dataCollection.put((i + 1), new HashMap<>());
            dataCollection.get((i + 1)).put(Car.FuelType.DISEL, new ConcurrentHashMap<>());
            dataCollection.get((i + 1)).put(Car.FuelType.GAS, new ConcurrentHashMap<>());
            dataCollection.get((i + 1)).put(Car.FuelType.PETROL, new ConcurrentHashMap<>());
        }

        for (int i = 0; i < 2; i++) {
            this.kasi.add(new ArrayBlockingQueue<>(10));
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60_000);
                DBManager.getInstance().getAllLoadings();
                DBManager.getInstance().getAllLoadedCars();
                DBManager.getInstance().getLoadedFuelByType();
                DBManager.getInstance().getProfit();
            } catch (InterruptedException e) {
                System.out.println("problem with the repost");
            }
        }
    }

    public void enterGasStation(Car car) {
        kolonki.get(new Random().nextInt(kolonki.size())).offer(car);
    }

    public List<Queue<Car>> getKolonki() {
        return Collections.unmodifiableList(kolonki);
    }

    public void pay(Car car, Car.FuelType fuelType, int fuelVolume) {
        kasi.get(new Random().nextInt(kasi.size())).offer(car);
    }

    public void addData(Car.FuelType fuelType, int fuelVolume, int kolonka, LocalDateTime date) {
        dataCollection.get(kolonka).get(fuelType).put(date, fuelVolume);
        DBManager.getInstance().insertIntoDB(fuelType, fuelVolume, kolonka, date);
    }

    public void printStatistics() {
        for (Integer kolonka : dataCollection.keySet()) {
            System.out.println("Na kolonka " + kolonka + " sa zaredili: ");
            for (Car.FuelType type : dataCollection.get(kolonka).keySet()) {
                System.out.println("\t\t\t\t" + type.toString() + ":");
                for (Map.Entry<LocalDateTime, Integer> e : dataCollection.get(kolonka).get(type).entrySet()) {
                    System.out.println("\t\t\t\t" + e.getValue() + " litra na " + e.getKey());
                }
            }
        }
    }

    public static void writeReportsInFile(String content, int number) {
        File file = null;
        try {
            file = new File("Report -" + number + "- " + LocalDate.now() + ".txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

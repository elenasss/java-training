package gasStation;

import java.util.Queue;
import java.util.Random;

public class Employee extends Thread {

    static GasStation gasStation;

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < gasStation.getKolonki().size(); i++) {
                Queue<Car> kolonka = gasStation.getKolonki().get(i);
                Car car;
                synchronized (kolonka) {
                    if (kolonka.isEmpty()) {
                        continue;
                    }
                    car = kolonka.poll();
                    try {
                        Thread.sleep(5000); //зареждане на колонката
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Car.FuelType fuelType = Car.FuelType.values()[new Random().nextInt(Car.FuelType.values().length)];
                int fuelVolume = new Random().nextInt(31) + 10;
                car.setFuelType(fuelType);
                car.setFuelVolume(fuelVolume);
                car.setKolonka(i + 1);
                System.out.println("Kolata zaredi na " + (i + 1) + " kolonka " + fuelVolume + " litra " + fuelType.toString());
                gasStation.pay(car, fuelType, fuelVolume);
            }
        }
    }
}

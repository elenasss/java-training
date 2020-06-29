package gasStation;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;

public class CashDesk extends Thread{

    static GasStation gasStation;
    private ArrayBlockingQueue<Car> kasa;

    public CashDesk(ArrayBlockingQueue<Car> kasa){
        this.kasa = kasa;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Car car = kasa.take();
                Thread.sleep(5000);
                System.out.println("Kolata si plati za " + car.getFuelVolume() + " litra " + car.getFuelType().toString());
                gasStation.addData(car.getFuelType(), car.getFuelVolume(), car.getKolonka(), LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

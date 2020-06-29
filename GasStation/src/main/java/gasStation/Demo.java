package gasStation;

public class Demo {

    public static void main(String[] args) {

        GasStation gasStation = new GasStation();
        gasStation.start();
        for (int i = 0; i < 50; i++) {
            gasStation.enterGasStation(new Car());
        }

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gasStation.printStatistics();
    }
}

package gasStation;

public class Demo {
    //in progress
    public static void main(String[] args) {

        GasStation gasStation = new GasStation();
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

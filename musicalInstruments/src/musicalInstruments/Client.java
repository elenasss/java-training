package musicalInstruments;

import java.util.HashMap;
import java.util.Random;

public class Client extends Thread {

    static MusicalStore musicalStore;
    private static final int TIME_BETWEEN_BUYS = 1500;
    private String name;

    public Client(MusicalStore musicalStore, String name) {
        this.musicalStore = musicalStore;
        this.name = name;
    }

    public void buyInstrument() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(TIME_BETWEEN_BUYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int quantity = new Random().nextInt(5) + 1;
            int index = new Random().nextInt(musicalStore.getCatalog().size());
            MusicalInstrument.Instrument instrument = musicalStore.getCatalog().get(index);
            System.out.println(this.name + " is buying " + quantity + " " + instrument);
            int waitTime = musicalStore.sellInstruments(instrument, quantity);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.println("No connection with the supplier");
            }
            HashMap<MusicalInstrument.Instrument, Integer> receivedInstruments = musicalStore.receiveDelivery();
            System.out.println(this.name + " received " + instrument + " - " + quantity);
        }
    }

    public String clientsName() {
        return this.name;
    }
}

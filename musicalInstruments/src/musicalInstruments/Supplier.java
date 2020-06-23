package musicalInstruments;

import java.util.HashMap;
import java.util.Random;

public class Supplier {
    private MusicalStore musicalStore;
    private HashMap<MusicalInstrument.Instrument, Integer> instrumentsToSupply;
    private static final int MIN_TIME_TO_DELIVERY = 1000;
    private static final int MAX_TIME_TO_DELIVERY = 5_000;

    public Supplier(MusicalStore musicalStore) {
        this.musicalStore = musicalStore;
        this.instrumentsToSupply = new HashMap<>();
    }


    public HashMap<MusicalInstrument.Instrument, Integer> deliver() {
        return instrumentsToSupply;
    }

    public int order(MusicalInstrument.Instrument instrument, int quantity) {
        instrumentsToSupply.put(instrument, quantity);
        int timeToDeliver =  new Random().nextInt(MAX_TIME_TO_DELIVERY + 1) + MIN_TIME_TO_DELIVERY;
        System.out.println(instrument + " will be delivered after " + timeToDeliver/1000 + " days");
        return timeToDeliver;
    }
}

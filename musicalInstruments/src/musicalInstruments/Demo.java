package musicalInstruments;

import musicalInstruments.MusicalInstrument.*;

public class Demo {
    private static Store store = new Store(1000.0);

    public static void main(String[] args) {

        addNewInstruments();

        store.sellInstrument(Instrument.VIOLA, 6);
        store.sellInstrument(Instrument.PIANO, 3);
        store.sellInstrument(Instrument.GUITAR, 3);
        store.sellInstrument(Instrument.VIOLIN, 6);
        store.sellInstrument(Instrument.HARP, 1);
        store.sellInstrument(Instrument.ORGAN, 2);
        store.sellInstrument(Instrument.BASS_GUITAR, 8);
        store.sellInstrument(Instrument.ELECTRIC_VIOLIN, 10);

        store.takeNewInstruments(Instrument.FLUTE, 1);
        store.takeNewInstruments(Instrument.DRUM, 1);
        store.takeNewInstruments(Instrument.VIOLIN, 1);

//        store.sortInstrumentsByType();
//        store.sortInstrumentsByName();
//        store.sortInstrumentsByPrice("descending");
//        store.sortInstrumentsByPrice("ascending");

//        store.showAvailableInstruments();
        store.sellInstrument(Instrument.ORGAN, 1);
//        store.sellInstrument(Instrument.DAYEREH, 1);
//        store.showAvailableInstruments();

//        store.SortBySoldQuantity();
//        store.generateProfit();
//        store.bestSelling();
//        store.worstSelling();

//        store.bestSellingType();
//        store.bestProfitType();
//        store.noSelling();
    }

    private static void addNewInstruments(){
        store.addInstrument(Instrument.CLARINET, new MusicalInstrument(Instrument.CLARINET, InstrumentType.BRASS, 90.0, 5));
        store.addInstrument(Instrument.FLUTE, new MusicalInstrument(Instrument.FLUTE, InstrumentType.BRASS, 80.0, 10));
        store.addInstrument(Instrument.TOMBONE, new MusicalInstrument(Instrument.TOMBONE, InstrumentType.BRASS, 60.0, 4));
        store.addInstrument(Instrument.TRUMPET, new MusicalInstrument(Instrument.TRUMPET, InstrumentType.BRASS, 155, 2));
        store.addInstrument(Instrument.TUBA, new MusicalInstrument(Instrument.TUBA, InstrumentType.BRASS, 80, 5));
        store.addInstrument(Instrument.BASS_GUITAR, new MusicalInstrument(Instrument.BASS_GUITAR, InstrumentType.ELECTRIC, 100.0, 10));
        store.addInstrument(Instrument.ELECTRIC_VIOLIN, new MusicalInstrument(Instrument.ELECTRIC_VIOLIN, InstrumentType.ELECTRIC, 120, 12));
        store.addInstrument(Instrument.SYNTHESIZER, new MusicalInstrument(Instrument.SYNTHESIZER, InstrumentType.ELECTRIC, 200, 4));
        store.addInstrument(Instrument.ACCORDION, new MusicalInstrument(Instrument.ACCORDION, InstrumentType.KEYBOARD, 70, 8));
        store.addInstrument(Instrument.PIANO, new MusicalInstrument(Instrument.PIANO, InstrumentType.KEYBOARD, 800, 3));
        store.addInstrument(Instrument.ORGAN, new MusicalInstrument(Instrument.ORGAN, InstrumentType.KEYBOARD, 100000, 1));
        store.addInstrument(Instrument.DAVUL, new MusicalInstrument(Instrument.DAVUL, InstrumentType.PERCUSSION, 20, 4));
        store.addInstrument(Instrument.DAYEREH, new MusicalInstrument(Instrument.DAYEREH, InstrumentType.PERCUSSION, 35, 1));
        store.addInstrument(Instrument.DRUM, new MusicalInstrument(Instrument.DRUM, InstrumentType.PERCUSSION, 100, 10));
        store.addInstrument(Instrument.DOUBLE_BASS, new MusicalInstrument(Instrument.DOUBLE_BASS, InstrumentType.STRING, 300, 3));
        store.addInstrument(Instrument.GADULKA, new MusicalInstrument(Instrument.GADULKA, InstrumentType.STRING, 20, 2));
        store.addInstrument(Instrument.GUITAR, new MusicalInstrument(Instrument.GUITAR, InstrumentType.STRING, 300, 10));
        store.addInstrument(Instrument.HARP, new MusicalInstrument(Instrument.HARP, InstrumentType.STRING, 500, 2));
        store.addInstrument(Instrument.VIOLA, new MusicalInstrument(Instrument.VIOLA, InstrumentType.STRING, 200, 7));
        store.addInstrument(Instrument.VIOLIN, new MusicalInstrument(Instrument.VIOLIN, InstrumentType.STRING, 300, 6));
    }

}

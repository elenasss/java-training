package musicalInstruments;

import musicalInstruments.MusicalInstrument.*;

public class Demo {
    private static MusicalStore musicalStore = new MusicalStore(1000.0);

    public static void main(String[] args) {

        addNewInstruments();

        musicalStore.sellInstrument(Instrument.VIOLA, 6);
        musicalStore.sellInstrument(Instrument.PIANO, 3);
        musicalStore.sellInstrument(Instrument.GUITAR, 3);
        musicalStore.sellInstrument(Instrument.VIOLIN, 6);
        musicalStore.sellInstrument(Instrument.HARP, 1);
        musicalStore.sellInstrument(Instrument.ORGAN, 2);
        musicalStore.sellInstrument(Instrument.BASS_GUITAR, 8);
        musicalStore.sellInstrument(Instrument.ELECTRIC_VIOLIN, 10);

        musicalStore.receiveNewInstruments(Instrument.FLUTE, 1);
        musicalStore.receiveNewInstruments(Instrument.DRUMS, 1);
        musicalStore.receiveNewInstruments(Instrument.VIOLIN, 1);

        musicalStore.sortInstrumentsByType();
        musicalStore.sortInstrumentsByName();
        musicalStore.sortInstrumentsByPrice("descending");
        musicalStore.sortInstrumentsByPrice("ascending");

        musicalStore.showAvailableInstruments();
        musicalStore.sellInstrument(Instrument.ORGAN, 1);
        musicalStore.sellInstrument(Instrument.DAYEREH, 1);
        musicalStore.showAvailableInstruments();

        musicalStore.SortBySoldQuantity();
        musicalStore.generateProfit();
        musicalStore.bestSelling();
        musicalStore.worstSelling();
        musicalStore.bestSellingType();
        musicalStore.bestProfitType();
    }

    private static void addNewInstruments(){
        musicalStore.addInstrument(Instrument.CLARINET, new MusicalInstrument(Instrument.CLARINET, InstrumentType.BRASS, 90), 5);
        musicalStore.addInstrument(Instrument.FLUTE, new MusicalInstrument(Instrument.FLUTE, InstrumentType.BRASS, 80), 10);
        musicalStore.addInstrument(Instrument.TOMBONE, new MusicalInstrument(Instrument.TOMBONE, InstrumentType.BRASS, 60), 4);
        musicalStore.addInstrument(Instrument.TRUMPET, new MusicalInstrument(Instrument.TRUMPET, InstrumentType.BRASS, 155), 2);
        musicalStore.addInstrument(Instrument.TUBA, new MusicalInstrument(Instrument.TUBA, InstrumentType.BRASS, 80), 5);
        musicalStore.addInstrument(Instrument.BASS_GUITAR, new MusicalInstrument(Instrument.BASS_GUITAR, InstrumentType.ELECTRIC, 100), 10);
        musicalStore.addInstrument(Instrument.ELECTRIC_VIOLIN, new MusicalInstrument(Instrument.ELECTRIC_VIOLIN, InstrumentType.ELECTRIC, 120), 12);
        musicalStore.addInstrument(Instrument.SYNTHESIZER, new MusicalInstrument(Instrument.SYNTHESIZER, InstrumentType.ELECTRIC, 200), 4);
        musicalStore.addInstrument(Instrument.ACCORDION, new MusicalInstrument(Instrument.ACCORDION, InstrumentType.KEYBOARD, 70), 8);
        musicalStore.addInstrument(Instrument.PIANO, new MusicalInstrument(Instrument.PIANO, InstrumentType.KEYBOARD, 800), 3);
        musicalStore.addInstrument(Instrument.ORGAN, new MusicalInstrument(Instrument.ORGAN, InstrumentType.KEYBOARD, 100000), 1);
        musicalStore.addInstrument(Instrument.DAVUL, new MusicalInstrument(Instrument.DAVUL, InstrumentType.PERCUSSION, 20), 4);
        musicalStore.addInstrument(Instrument.DAYEREH, new MusicalInstrument(Instrument.DAYEREH, InstrumentType.PERCUSSION, 35), 1);
        musicalStore.addInstrument(Instrument.DRUMS, new MusicalInstrument(Instrument.DRUMS, InstrumentType.PERCUSSION, 100), 10);
        musicalStore.addInstrument(Instrument.DOUBLE_BASS, new MusicalInstrument(Instrument.DOUBLE_BASS, InstrumentType.STRING, 300), 3);
        musicalStore.addInstrument(Instrument.GADULKA, new MusicalInstrument(Instrument.GADULKA, InstrumentType.STRING, 20), 2);
        musicalStore.addInstrument(Instrument.GUITAR, new MusicalInstrument(Instrument.GUITAR, InstrumentType.STRING, 300), 10);
        musicalStore.addInstrument(Instrument.HARP, new MusicalInstrument(Instrument.HARP, InstrumentType.STRING, 500), 2);
        musicalStore.addInstrument(Instrument.VIOLA, new MusicalInstrument(Instrument.VIOLA, InstrumentType.STRING, 200), 7);
        musicalStore.addInstrument(Instrument.VIOLIN, new MusicalInstrument(Instrument.VIOLIN, InstrumentType.STRING, 300), 6);
    }

}

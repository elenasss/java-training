package musicalInstruments;

public class ThreadDemo {

    private static MusicalStore musicalStore = new MusicalStore(1000.0);

    public static void main(String[] args) {

        addNewInstruments();

        for (int i = 0; i < 5; i++) {
            Client client = new Client(musicalStore, "Client " + (i+1));
            client.buyInstrument();
        }

        musicalStore.monthlyDelivery();
    }

    private static void addNewInstruments() {
        musicalStore.addInstrument(MusicalInstrument.Instrument.CLARINET, new MusicalInstrument(MusicalInstrument.Instrument.CLARINET, MusicalInstrument.InstrumentType.BRASS, 90), 5);
        musicalStore.addInstrument(MusicalInstrument.Instrument.FLUTE, new MusicalInstrument(MusicalInstrument.Instrument.FLUTE, MusicalInstrument.InstrumentType.BRASS, 80), 10);
        musicalStore.addInstrument(MusicalInstrument.Instrument.TOMBONE, new MusicalInstrument(MusicalInstrument.Instrument.TOMBONE, MusicalInstrument.InstrumentType.BRASS, 60), 4);
        musicalStore.addInstrument(MusicalInstrument.Instrument.TRUMPET, new MusicalInstrument(MusicalInstrument.Instrument.TRUMPET, MusicalInstrument.InstrumentType.BRASS, 155), 2);
        musicalStore.addInstrument(MusicalInstrument.Instrument.TUBA, new MusicalInstrument(MusicalInstrument.Instrument.TUBA, MusicalInstrument.InstrumentType.BRASS, 80), 5);
        musicalStore.addInstrument(MusicalInstrument.Instrument.BASS_GUITAR, new MusicalInstrument(MusicalInstrument.Instrument.BASS_GUITAR, MusicalInstrument.InstrumentType.ELECTRIC, 100), 10);
        musicalStore.addInstrument(MusicalInstrument.Instrument.ELECTRIC_VIOLIN, new MusicalInstrument(MusicalInstrument.Instrument.ELECTRIC_VIOLIN, MusicalInstrument.InstrumentType.ELECTRIC, 120), 12);
        musicalStore.addInstrument(MusicalInstrument.Instrument.SYNTHESIZER, new MusicalInstrument(MusicalInstrument.Instrument.SYNTHESIZER, MusicalInstrument.InstrumentType.ELECTRIC, 200), 4);
        musicalStore.addInstrument(MusicalInstrument.Instrument.ACCORDION, new MusicalInstrument(MusicalInstrument.Instrument.ACCORDION, MusicalInstrument.InstrumentType.KEYBOARD, 70), 8);
        musicalStore.addInstrument(MusicalInstrument.Instrument.PIANO, new MusicalInstrument(MusicalInstrument.Instrument.PIANO, MusicalInstrument.InstrumentType.KEYBOARD, 800), 3);
        musicalStore.addInstrument(MusicalInstrument.Instrument.ORGAN, new MusicalInstrument(MusicalInstrument.Instrument.ORGAN, MusicalInstrument.InstrumentType.KEYBOARD, 100000), 1);
        musicalStore.addInstrument(MusicalInstrument.Instrument.DAVUL, new MusicalInstrument(MusicalInstrument.Instrument.DAVUL, MusicalInstrument.InstrumentType.PERCUSSION, 20), 4);
        musicalStore.addInstrument(MusicalInstrument.Instrument.DAYEREH, new MusicalInstrument(MusicalInstrument.Instrument.DAYEREH, MusicalInstrument.InstrumentType.PERCUSSION, 35), 1);
        musicalStore.addInstrument(MusicalInstrument.Instrument.DRUMS, new MusicalInstrument(MusicalInstrument.Instrument.DRUMS, MusicalInstrument.InstrumentType.PERCUSSION, 100), 10);
        musicalStore.addInstrument(MusicalInstrument.Instrument.DOUBLE_BASS, new MusicalInstrument(MusicalInstrument.Instrument.DOUBLE_BASS, MusicalInstrument.InstrumentType.STRING, 300), 3);
        musicalStore.addInstrument(MusicalInstrument.Instrument.GADULKA, new MusicalInstrument(MusicalInstrument.Instrument.GADULKA, MusicalInstrument.InstrumentType.STRING, 20), 2);
        musicalStore.addInstrument(MusicalInstrument.Instrument.GUITAR, new MusicalInstrument(MusicalInstrument.Instrument.GUITAR, MusicalInstrument.InstrumentType.STRING, 300), 10);
        musicalStore.addInstrument(MusicalInstrument.Instrument.HARP, new MusicalInstrument(MusicalInstrument.Instrument.HARP, MusicalInstrument.InstrumentType.STRING, 500), 2);
        musicalStore.addInstrument(MusicalInstrument.Instrument.VIOLA, new MusicalInstrument(MusicalInstrument.Instrument.VIOLA, MusicalInstrument.InstrumentType.STRING, 200), 7);
        musicalStore.addInstrument(MusicalInstrument.Instrument.VIOLIN, new MusicalInstrument(MusicalInstrument.Instrument.VIOLIN, MusicalInstrument.InstrumentType.STRING, 300), 6);
    }

}

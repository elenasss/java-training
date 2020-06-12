package musicalInstruments;

public class MusicalInstrument {

    public enum InstrumentType {
        BRASS,
        ELECTRIC,
        KEYBOARD,
        PERCUSSION,
        STRING;
    }

    public enum Instrument {
        CLARINET("Clarinet"), FLUTE ("Flute"), TOMBONE("Tombone"), TRUMPET("Trumpet"), TUBA("Tuba"),
        BASS_GUITAR("BassGuitar"), ELECTRIC_VIOLIN("Electric Violin"), SYNTHESIZER("Synthesizer"),
        ACCORDION("Accordion"), ORGAN("Organ"), PIANO("Piano"),
        DAVUL("Davul"), DAYEREH("Dayereh"), DRUM("Drum"),
        DOUBLE_BASS("Double Bass"), GUITAR("Guitar"), GADULKA("Gadulka"), HARP("Harp"), VIOLIN("Violin"), VIOLA("Viola");

        private String name;

        Instrument(String value){
            this.name = value;
        }
        public String getName() {
            return name;
        }
    }

    private Instrument name;
    private double price;
    private int availability;
    private InstrumentType type;
    private int soldInstruments;

    public MusicalInstrument(Instrument name, InstrumentType type, double price, int availability) {
        this.name = name;
        this.type = type;
        this.price = setPrice(price);
        this.availability = setAvailability(availability);
        this.soldInstruments = 0;
    }

    private int setAvailability(int availability) {
        if (availability < 0) {
            throw new IllegalArgumentException("Please enter valid count");
        }
        return availability;
    }

    private double setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Please enter valid price");
        }
        return price;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailability() {
        return availability;
    }

    public void sellInstrument(int count) {
        this.availability -= count;
        this.soldInstruments += count;
    }

    public Instrument getInstrument() {
        return name;
    }

    public void addCount(int count) {
        this.availability += count;
    }

    public String getType() {
        return String.valueOf(type);
    }

    public int getSoldInstruments() {
        return soldInstruments;
    }

    @Override
    public String toString() {
        return "MusicalInstrument{" +
                "instrument=" + name +
                ", price=" + price +
                ", availability=" + availability +
                ", type=" + type +
                ", soldInstruments=" + soldInstruments +
                '}';
    }
}

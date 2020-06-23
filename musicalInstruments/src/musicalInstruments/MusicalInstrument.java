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
        DAVUL("Davul"), DAYEREH("Dayereh"), DRUMS("Drums"),
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
    private int price;
    private InstrumentType type;

    public MusicalInstrument(Instrument name, InstrumentType type, int price) {
        this.name = name;
        this.type = type;
        this.price = setPrice(price);
    }

    private int setAvailability(int availability) {
        if (availability < 0) {
            throw new IllegalArgumentException("Please enter valid count");
        }
        return availability;
    }

    private int setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Please enter valid price");
        }
        return price;
    }

    public int getPrice() {
        return price;
    }

    public Instrument getInstrument() {
        return name;
    }

//    public void addCount(int count) {
//        this.availability += count;
//    }

    public String getType() {
        return String.valueOf(type);
    }


    @Override
    public String toString() {
        return "MusicalInstrument{" +
                "instrument=" + name +
                ", price=" + price +
//                ", availability=" + availability +
                ", type=" + type +
//                ", soldInstruments=" + soldInstruments +
                '}';
    }
}

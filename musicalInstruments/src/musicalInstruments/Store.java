package musicalInstruments;

import musicalInstruments.MusicalInstrument.*;

import java.util.*;

public class Store {

    private double cash;
    private TreeMap<Instrument, MusicalInstrument> musicalInstruments; //name->musical instrument

    public Store(double money) {
        this.cash = money;
        this.musicalInstruments = new TreeMap<>();
    }

    public void addInstrument(Instrument name, MusicalInstrument instrument) {
        this.musicalInstruments.put(name, instrument);
    }

    public void sellInstrument(Instrument instrument, int count) {
        if (!this.musicalInstruments.containsKey(instrument)) {
            System.out.println("There is no such instrument in the shop");
        } else if (this.musicalInstruments.get(instrument).getAvailability() == 0) {
            System.out.println("The " + instrument.getName() + " is not available at the moment");
        } else if (this.musicalInstruments.get(instrument).getAvailability() < count) {
            System.out.println("You cannot buy " + instrument.getName() + ". Only " + this.musicalInstruments.get(instrument).getAvailability() + " are available");
        } else {
            this.musicalInstruments.get(instrument).sellInstrument(count);
            this.cash += this.musicalInstruments.get(instrument).getPrice() * count;
            System.out.println(count + "  " + this.musicalInstruments.get(instrument).getInstrument() +
                    " are sold. Current availability: " + this.musicalInstruments.get(instrument).getAvailability());
            System.out.println("Current cash in the shop: " + this.cash);
        }
    }

    public void takeNewInstruments(Instrument name, int count) {
        if (!musicalInstruments.containsKey(name)) {
            System.out.println("There is no such instrument in the list");
            return;
        }
        if (count > 0) {
            musicalInstruments.get(name).addCount(count);
            System.out.println("Instruments are added. Current availability of " + musicalInstruments.get(name).getInstrument() + ": " + musicalInstruments.get(name).getAvailability());
        }
    }

    public void sortInstrumentsByType() {
        System.out.println();
        System.out.println("\t\t\t\t============SORTED INSTRUMENTS BY TYPE===============");
        HashMap<String, TreeSet<MusicalInstrument>> instrumentsByType = sortedByType(Comparator.comparing(MusicalInstrument::getInstrument));

        for (Map.Entry<String, TreeSet<MusicalInstrument>> entry : instrumentsByType.entrySet()) {
            System.out.println(entry.getKey());
            for (MusicalInstrument instrument : entry.getValue()) {
                System.out.println(instrument);
            }
        }
    }

    public void sortInstrumentsByName() {
        TreeSet<MusicalInstrument> instruments = createNewSet(Comparator.comparing(o -> o.getInstrument().getName()));
        System.out.println();
        System.out.println("\t\t\t\t============SORTED INSTRUMENTS BY NAME===============");

        for (MusicalInstrument instrument : instruments) {
            System.out.println(instrument);
        }
    }

    public void sortInstrumentsByPrice(String howToSort) {
        TreeSet<MusicalInstrument> instruments = createNewSet((o1, o2) -> {
            double difference = o1.getPrice() - o2.getPrice();
            if (difference > 0) {
                return 1;
            } else if (difference < 0) {
                return -1;
            }
            return 0;
        });
        System.out.println();
        System.out.println("\t\t\t\t============SORTED INSTRUMENTS BY PRICE===============");

        if (howToSort.equalsIgnoreCase("descending")) {
            for (MusicalInstrument instrument : instruments.descendingSet()) {
                System.out.println(instrument);
            }
        } else if (howToSort.equalsIgnoreCase("ascending")) {
            for (MusicalInstrument instrument : instruments) {
                System.out.println(instrument);
            }
        }
    }


    public void SortBySoldQuantity() {
        System.out.println();
        System.out.println("\t\t\t\t============SORTED INSTRUMENTS BY SOLD QUANTITY===============");
        TreeSet<MusicalInstrument> instruments = sortedSet();

        for (MusicalInstrument instrument : instruments.descendingSet()) {
            System.out.println(instrument.getInstrument() + " - " + instrument.getSoldInstruments());
        }
    }

    public void generateProfit() {
        TreeSet<MusicalInstrument> instruments = sortedSet();

        double profit = 0;
        for (MusicalInstrument instrument : instruments.descendingSet()) {
            profit += instrument.getSoldInstruments() * instrument.getPrice();
        }
        System.out.println("\t\t\t TOTAL PROFIT: " + profit);
    }

    public void bestSelling() {
        TreeSet<MusicalInstrument> instruments = sortedSet();
        int maxCount = instruments.last().getSoldInstruments();
        System.out.print("\t BEST SELLING: ");
        for (MusicalInstrument instrument : instruments.descendingSet()) {
            if (instrument.getSoldInstruments() == maxCount) {
                System.out.print(instrument.getInstrument() + " - " + maxCount + " ");
            }
            if (instrument.getSoldInstruments() < maxCount) {
                System.out.println();
                return;
            }
        }
    }

    public void worstSelling() {
        TreeSet<MusicalInstrument> instruments = sortedSet();
        int minCount = instruments.first().getSoldInstruments();
        System.out.print(("\t WORST SELLING: "));
        for (MusicalInstrument instrument : instruments) {
            if (instrument.getSoldInstruments() == minCount) {
                System.out.println(instrument.getInstrument() + " - " + minCount +" ");
            }
            if (instrument.getSoldInstruments() > minCount) {
                System.out.println();
                return;
            }
        }
    }

    public void noSelling() {
        System.out.println("\t\t\t ======= INSTRUMENTS WITH NO SELLING =========");
        for (MusicalInstrument instrument : musicalInstruments.values()) {
            if (instrument.getSoldInstruments() == 0) {
                System.out.println(instrument);
            }
        }
    }

    public void bestSellingType() {
        HashMap<String, TreeSet<MusicalInstrument>> instrumentsByType = sortedByType(Comparator.comparing(MusicalInstrument::getInstrument));
        TreeMap<String, Integer> soldType = new TreeMap<>();
        for (Map.Entry<String, TreeSet<MusicalInstrument>> entry : instrumentsByType.entrySet()) {
            int soldInstruments = 0;
            for (MusicalInstrument instrument : entry.getValue()) {
                soldInstruments += instrument.getSoldInstruments();
            }
            soldType.put(entry.getKey(), soldInstruments);
        }

        String type = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : soldType.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                type = entry.getKey();
            }
        }
        System.out.println("\t BEST SELLING TYPE: " + type + " - " + maxCount);
    }

    public void bestProfitType() {
        HashMap<String, TreeSet<MusicalInstrument>> instrumentsByType = sortedByType(Comparator.comparing(MusicalInstrument::getInstrument));
        TreeMap<String, Double> soldType = new TreeMap<>();
        for (Map.Entry<String, TreeSet<MusicalInstrument>> entry : instrumentsByType.entrySet()) {
            double profit = 0;
            for (MusicalInstrument instrument : entry.getValue()) {
                profit += instrument.getSoldInstruments() * instrument.getPrice();
            }
            soldType.put(entry.getKey(), profit);
        }

        String type = "";
        double maxProfit = 0;
        for (Map.Entry<String, Double> entry : soldType.entrySet()) {
            if (entry.getValue() > maxProfit) {
                maxProfit = entry.getValue();
                type = entry.getKey();
            }
        }
        System.out.println();
        System.out.println("\t TYPE WITH BEST PROFIT: " + type + " - " + maxProfit);
    }

    public void showAvailableInstruments() {
        System.out.println();
        System.out.println("\t\t\t\t============AVAILABLE INSTRUMENTS===============");
        for (MusicalInstrument instrument : musicalInstruments.values()) {
            if (instrument.getAvailability() > 0) {
                System.out.println(instrument);
            }
        }
    }

    private TreeSet<MusicalInstrument> createNewSet(Comparator<MusicalInstrument> comparator) {
        TreeSet<MusicalInstrument> instruments = new TreeSet<>(comparator);
        instruments.addAll(musicalInstruments.values());
        return instruments;
    }

    private TreeSet<MusicalInstrument> soldInstruments(Comparator<MusicalInstrument> comparator) {
        TreeSet<MusicalInstrument> instruments = new TreeSet<>(comparator);
        for (MusicalInstrument instrument : musicalInstruments.values()) {
            if (instrument.getSoldInstruments() > 0) {
                instruments.add(instrument);
            }
        }
        return instruments;
    }

    private HashMap<String, TreeSet<MusicalInstrument>> sortedByType(Comparator comparator) {
        HashMap<String, TreeSet<MusicalInstrument>> instrumentsByType = new HashMap<>();

        for (MusicalInstrument instrument : musicalInstruments.values()) {
            if (!instrumentsByType.containsKey(instrument.getType())) {
                instrumentsByType.put(instrument.getType(), new TreeSet<>(comparator));
            }
            instrumentsByType.get(instrument.getType()).add(instrument);
        }
        return instrumentsByType;

    }

    private TreeSet<MusicalInstrument> sortedSet() {
        TreeSet<MusicalInstrument> instruments = soldInstruments(((o1, o2) -> {
            if (o1.getSoldInstruments() - o2.getSoldInstruments() >= 0) {
                return 1;
            } else {
                return -1;
            }
        }));
        return instruments;
    }
}

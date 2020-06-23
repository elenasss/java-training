package musicalInstruments;

import musicalInstruments.MusicalInstrument.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MusicalStore extends Thread {

    private double cash;
    private TreeMap<Instrument, HashMap<MusicalInstrument, Integer>> musicalInstruments; //name-> musical instrument - quantity
    private HashMap<MusicalInstrument, Integer> soldItems; //name->musical instrument - sold quantity
    private Supplier supplier;
    private ConcurrentHashMap<Instrument, ConcurrentHashMap<MusicalInstrument, Integer>> catalogWithMusicalInstruments;
    private ArrayList<Instrument> catalog;
    private final int MONTHLY_DELIVERY = 5_000;
    private HashMap<MusicalInstrument, Integer> productsForMonthlySupply;
    private Client client;
    private Thread waitThread;

    public MusicalStore(double money) {
        this.cash = money;
        this.musicalInstruments = new TreeMap<>();
        this.soldItems = new HashMap<>();
        this.supplier = new Supplier(this);
        this.catalog = new ArrayList<>();
        this.catalogWithMusicalInstruments = new ConcurrentHashMap<>();
        Client.musicalStore = this;
    }

    public void addInstrument(Instrument name, MusicalInstrument instrument, int count) {
        if (!musicalInstruments.containsKey(name)) {
            this.musicalInstruments.put(name, new HashMap<>());
            this.catalogWithMusicalInstruments.put(name, new ConcurrentHashMap<>());
        }
        this.musicalInstruments.get(name).put(instrument, count);
        this.catalogWithMusicalInstruments.get(name).put(instrument, count);
        this.catalog.add(name);
    }

    public void sellInstrument(Instrument instrument, int quantity) {
        MusicalInstrument musicalInstrument = findInstrument(instrument);
        if (musicalInstrument == null) {
            System.out.println("There is no such instrument in the shop");
            return;
        }
        int currentQuantity = this.musicalInstruments.get(instrument).get(musicalInstrument);
        if (currentQuantity == 0) {
            System.out.println("The " + instrument.getName() + " is not available at the moment");
        } else if (currentQuantity < quantity) {
            System.out.println("You cannot buy " + instrument.getName() + ". Only " + this.musicalInstruments.get(instrument).get(musicalInstrument) + " are available");
        } else {
            currentQuantity = this.musicalInstruments.get(instrument).get(musicalInstrument) - quantity;
            this.musicalInstruments.get(instrument).put(musicalInstrument, currentQuantity);
            soldItems.put(musicalInstrument, quantity);
            this.cash += musicalInstrument.getPrice() * quantity;
            System.out.println(quantity + "  " + instrument.getName() + " are sold. Current availability: " + currentQuantity);
            System.out.println("Current cash in the shop: " + this.cash);
        }
    }

    public int sellInstruments(Instrument instrument, int quantity) {
        MusicalInstrument musicalInstrument = findInstrument(instrument);
        int currentQuantity = this.catalogWithMusicalInstruments.get(instrument).get(musicalInstrument);
        if (musicalInstrument == null) {
            System.out.println("There is no such instrument in the shop");
            return 0;
        }
        if (quantity <= 0) {
            System.out.println("Please enter a valid quantity");
            return 0;
        }
        if (currentQuantity >= quantity) {
            currentQuantity = this.catalogWithMusicalInstruments.get(instrument).get(musicalInstrument) - quantity;
            this.catalogWithMusicalInstruments.get(instrument).put(musicalInstrument, currentQuantity);
            soldItems.put(musicalInstrument, quantity);
            this.cash += musicalInstrument.getPrice() * quantity;
            System.out.println(quantity + "  " + instrument.getName() + " are sold. Current availability: " + currentQuantity);
            return 0;
        } else {
            System.out.println(quantity + " counts of " + instrument.getName() + " are not available at the moment. It will be ordered. Current availability: " + currentQuantity);
            return supplier.order(instrument, quantity);
        }
    }

    private MusicalInstrument findInstrument(Instrument name) {
        HashMap<MusicalInstrument, Integer> instruments = musicalInstruments.get(name);
        for (MusicalInstrument musicalInstrument : instruments.keySet()) {
            if (musicalInstrument.getInstrument() == name) {
                return musicalInstrument;
            }
        }
        return null;
    }

    public void receiveNewInstruments(Instrument name, int receivedCount) {
        if (!musicalInstruments.containsKey(name)) {
            musicalInstruments.put(name, new HashMap<>());
        }
        MusicalInstrument musicalInstrument = findInstrument(name);
        int count = musicalInstruments.get(name).get(musicalInstrument) + receivedCount;
        musicalInstruments.get(name).put(musicalInstrument, count);
        System.out.println("Instruments are added. Current availability of " + name + ": " + count);
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
        TreeSet<MusicalInstrument> musicalInstruments = createNewSet((o1, o2) -> {
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
            for (MusicalInstrument instrument : musicalInstruments.descendingSet()) {
                System.out.println(instrument);
            }
        } else if (howToSort.equalsIgnoreCase("ascending")) {
            for (MusicalInstrument instrument : musicalInstruments) {
                System.out.println(instrument);
            }
        }
    }

    public void showAvailableInstruments() {
        System.out.println();
        System.out.println("\t\t\t\t============AVAILABLE INSTRUMENTS===============");
        for (HashMap<MusicalInstrument, Integer> e : musicalInstruments.values()) {
            for (MusicalInstrument instrument : e.keySet()) {
                if (e.get(instrument) > 0) {
                    System.out.println(instrument.getInstrument().getName() + " -> count: " + e.get(instrument));
                }
            }
        }
    }

    public void SortBySoldQuantity() {
        SortedMap<Integer, HashSet<Instrument>> instrumentsBySoldQuantity = sortedBySells();

        System.out.println("\t\t\t\t============SORTED INSTRUMENTS BY SOLD QUANTITY===============");

        for (Map.Entry<Integer, HashSet<Instrument>> e : instrumentsBySoldQuantity.entrySet()) {
            for (Instrument instrument : e.getValue()) {
                System.out.println(instrument + " - " + (e.getKey()));
            }
        }
    }

    public void generateProfit() {
        double profit = 0;
        for (MusicalInstrument instrument : soldItems.keySet()) {
            profit += soldItems.get(instrument) * instrument.getPrice();
        }
        System.out.println("\t\t\t TOTAL PROFIT: " + profit);
    }

    public void bestSelling() {
        SortedMap<Integer, HashSet<Instrument>> instrumentsBySoldQuantity = sortedBySells();
        int maxSells = instrumentsBySoldQuantity.firstKey();
        System.out.println("\t BEST SOLD INSTRUMENTS: ");
        System.out.print(maxSells + " : ");
        HashSet<Instrument> bestSoldInstr = instrumentsBySoldQuantity.get(maxSells);
        for (Instrument instrument : bestSoldInstr) {
            System.out.print(instrument + " ");
        }
        System.out.println();
    }

    public void worstSelling() {
        SortedMap<Integer, HashSet<Instrument>> instrumentsBySoldQuantity = sortedBySells();
        int minSells = instrumentsBySoldQuantity.lastKey();
        System.out.println("\t WORST SOLD INSTRUMENTS: ");
        System.out.print(minSells + " : ");
        HashSet<Instrument> worstSoldInstr = instrumentsBySoldQuantity.get(minSells);
        for (Instrument instrument : worstSoldInstr) {
            System.out.print(instrument + " ");
        }
        System.out.println();
    }

    public void bestSellingType() {
        HashMap<String, Integer> soldByType = new HashMap<>();
        int soldTypeCount;
        for (MusicalInstrument musicalInstrument : soldItems.keySet()) {
            int soldCounts = soldItems.get(musicalInstrument);
            if (!soldByType.containsKey(musicalInstrument.getType())) {
                soldByType.put(musicalInstrument.getType(), soldCounts);
                continue;
            }
            soldTypeCount = soldByType.get(musicalInstrument.getType());
            soldByType.put(musicalInstrument.getType(), soldCounts + soldTypeCount);
        }
        SortedMap<Integer, Set<String>> sortedByType = sortByType(soldByType);
        System.out.println("\t BEST SELLING TYPE: " + sortedByType.get(sortedByType.firstKey()) + " - " + sortedByType.firstKey());
    }

    public void bestProfitType() {
        HashMap<String, Integer> soldByType = new HashMap<>(); //type - profit
        int currentProfit;
        for (MusicalInstrument musicalInstrument : soldItems.keySet()) {
            int profit = soldItems.get(musicalInstrument) * musicalInstrument.getPrice();
            if (!soldByType.containsKey(musicalInstrument.getType())) {
                soldByType.put(musicalInstrument.getType(), profit);
                continue;
            }
            currentProfit = soldByType.get(musicalInstrument.getType());
            soldByType.put(musicalInstrument.getType(), currentProfit + profit);
        }
        SortedMap<Integer, Set<String>> sortedByType = sortByType(soldByType);
        System.out.println("\t TYPE WITH BEST PROFIT: " + sortedByType.get(sortedByType.firstKey()) + " - " + sortedByType.firstKey());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(MONTHLY_DELIVERY);
            } catch (InterruptedException e) {
                System.out.println("Delivery wasn't successful");
            }
            System.out.println("CHECKING FOR MONTHLY SUPPLY");
            productsForMonthlySupply = new HashMap<>();
            for (ConcurrentHashMap<MusicalInstrument, Integer> e : catalogWithMusicalInstruments.values()) {
                for (MusicalInstrument instrument : e.keySet()) {
                    if (e.get(instrument) == 0) {
                        int count = new Random().nextInt(10) + 1;
                        System.out.println("Requesting delivery of " + instrument.getInstrument().getName() + " -> count: " + count);
                        productsForMonthlySupply.put(instrument, count);
                    }
                }
            }
            if (productsForMonthlySupply.isEmpty()) {
                System.out.println("No delivery is required");
                continue;
            }
            for (MusicalInstrument musicalInstrument : productsForMonthlySupply.keySet()) {
                addInstrument(musicalInstrument.getInstrument(), musicalInstrument, productsForMonthlySupply.get(musicalInstrument));
                System.out.println("MONTHLY SUPPLY: "+musicalInstrument.getInstrument().getName() + " is delivered. Count:" + productsForMonthlySupply.get(musicalInstrument));
            }
        }
    }

    public void monthlyDelivery() {
        this.start();
    }

    public ArrayList<Instrument> getCatalog() {
        return catalog;
    }

    private SortedMap<Integer, Set<String>> sortByType(HashMap<String, Integer> soldByType) {
        SortedMap<Integer, Set<String>> sortedByType = new TreeMap<>((o1, o2) -> o2 - o1);
        for (String s : soldByType.keySet()) {
            int sum = soldByType.get(s);
            if (!sortedByType.containsKey(sum)) {
                sortedByType.put(sum, new HashSet<>());
                sortedByType.get(sum).add(s);
            }
        }
        return sortedByType;
    }

    private TreeSet<MusicalInstrument> createNewSet(Comparator<MusicalInstrument> comparator) {
        TreeSet<MusicalInstrument> instruments = new TreeSet<>(comparator);
        for (HashMap<MusicalInstrument, Integer> value : musicalInstruments.values()) {
            instruments.addAll(value.keySet());
        }
        return instruments;
    }

    private HashMap<String, TreeSet<MusicalInstrument>> sortedByType(Comparator comparator) {
        HashMap<String, TreeSet<MusicalInstrument>> instrumentsByType = new HashMap<>();
        for (HashMap<MusicalInstrument, Integer> e : musicalInstruments.values()) {
            for (MusicalInstrument musicalInstrument : e.keySet()) {
                if (!instrumentsByType.containsKey(musicalInstrument.getType())) {
                    instrumentsByType.put(musicalInstrument.getType(), new TreeSet<>(comparator));
                }
                instrumentsByType.get(musicalInstrument.getType()).add(musicalInstrument);
            }
        }
        return instrumentsByType;
    }

    private SortedMap<Integer, HashSet<Instrument>> sortedBySells() {
        SortedMap<Integer, HashSet<Instrument>> instrumentsBySoldQuantity = new TreeMap<>((e1, e2) -> e2 - e1);
        for (MusicalInstrument musicalInstrument : soldItems.keySet()) {
            int soldCount = soldItems.get(musicalInstrument);
            if (!instrumentsBySoldQuantity.containsKey(soldCount)) {
                instrumentsBySoldQuantity.put(soldCount, new HashSet<>());
            }
            instrumentsBySoldQuantity.get(soldCount).add(musicalInstrument.getInstrument());
        }
        return instrumentsBySoldQuantity;
    }

    public HashMap<Instrument, Integer> receiveDelivery() {
        return supplier.deliver();
    }
}

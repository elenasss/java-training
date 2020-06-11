package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

public class Library<T extends ReadingObject> {

    enum ReadingType {
        BOOK,
        MAGAZINE,
        TEXTBOOK
    }

    Thread borrowThread;
    private final HashMap<ReadingType, HashMap<ReadingObject.IGroup, TreeSet<T>>> catalog;
    private final HashMap<T, TreeSet<HistoryLog>> historyLog;
    private int totalReadingObjectsCount = 0;

    public Library() {
        this.catalog = new HashMap<>();
        this.historyLog = new HashMap<>();
    }

    public void addReadingObject(T reading) {
        ReadingType type = reading.getType();
        ReadingObject.IGroup group = reading.getGroup(); //genre, category or theme

        if (!catalog.containsKey(type)) {
            catalog.put(type, new HashMap<>());
        }

        switch (type) {
            case BOOK:
                if (!catalog.get(type).containsKey(group)) {
                    catalog.get(type).put(group, new TreeSet<>((o1, o2) -> {
                        if (o1.getTitle().equals(o2.getTitle())) {
                            return (((Book) o2).getIssueDate().compareTo(((Book) o1).getIssueDate()));
                        }
                        return o1.getTitle().compareTo(o2.getTitle());
                    }));
                }
                break;

            case MAGAZINE:
                if (!catalog.get(type).containsKey(group)) {
                    catalog.get(type).put(group, new TreeSet<>((o1, o2) -> {
                        if (o1.getTitle().equals(o2.getTitle())) {
                            return ((Magazine) o1).getNumber() > ((Magazine) o2).getNumber() ? 1 : -1;
                        }
                        return o1.getTitle().compareTo(o2.getTitle());
                    }));
                }
                break;

            case TEXTBOOK:
                if (!catalog.get(type).containsKey(group)) {
                    catalog.get(type).put(group, new TreeSet<>(Comparator.comparing(ReadingObject::getTitle)));
                }
                break;
        }

        catalog.get(type).get(group).add(reading);
        totalReadingObjectsCount += 1;
    }

    public void printCatalog() {
        System.out.println("===================================== WELCOME TO THE LIBRARY =====================================");
        for (Entry<ReadingType, HashMap<ReadingObject.IGroup, TreeSet<T>>> e : catalog.entrySet()) {
            System.out.println("\t\t-------------------- " + e.getKey() + " ---------------");

            for (Entry<ReadingObject.IGroup, TreeSet<T>> e1 : e.getValue().entrySet()) {
                System.out.println("\t\t--- " + e1.getKey() + " ---");

                for (ReadingObject object : e1.getValue()) {
                    System.out.println(object.toString());
                }
            }
        }
    }


    public void borrowReadingObject(String client, ReadingType type, String title) {
        T item = searchForItem(type, title);
        if (item != null) {
            borrowItem(item, client);
        }
    }

    public void borrowReadingObject(String client, ReadingType type, String title, String issue) {
        T item = searchForItem(type, title, issue);
        if (item != null) {
            borrowItem(item, client);
        }
    }

    public void returnReadingObject(String client, ReadingType type, String title) {
        T item = searchForItem(type, title);
        if (item != null) {
            returnItem(client, item);
        }
    }

    public void returnReadingObject(String client, ReadingType type, String title, String issue) {
        T item = searchForItem(type, title, issue);
        if (item != null) {
            returnItem(client, item);
        }
    }

    private void borrowItem(T item, String client) {
        if (item.getRentPeriod() == 0) {
            System.out.println("Sorry, you cannot borrow magazines");
            return;
        }
        if (item.isBorrowed()) {
            System.out.println("the reading object is not available at the moment");
            return;
        }
        item.takeReadingObject();
        if (!historyLog.containsKey(item)) {
            historyLog.put(item, new TreeSet<>(Comparator.comparing(HistoryLog::getBorrowingTime)));
        }
        HistoryLog currentHistoryLog = new HistoryLog(client, item.getRentPeriod(), LocalDateTime.now());
        historyLog.get(item).add(currentHistoryLog);
        System.out.println(client + " borrowed " + item.getTitle());

        borrowThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(item.getRentPeriod() * 20); //по условие *1000
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
                currentHistoryLog.setOverDue(true);
                while (item.isBorrowed()) {
                    item.setInterest();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });
        borrowThread.start();
    }

    private void returnItem(String client, T item) {
        borrowThread.interrupt();
        item.returnReadingObject();
        System.out.println(client + " returned " + item.getTitle() + " and have to pay " + item.getTotalTax());
    }

    private T searchForItem(ReadingType type, String title) {
        if (type == ReadingType.TEXTBOOK) {
            HashMap<ReadingObject.IGroup, TreeSet<T>> list = this.catalog.get(type);
            for (TreeSet<T> items : list.values()) {
                for (T item : items) {
                    if (item.getTitle().equalsIgnoreCase(title)) {
                        return item;
                    }
                }
            }
            System.out.println("There is no such textbook in the library");
        } else {
            throw new NullPointerException("You should specify the issue date if you are searching for a book " +
                    "or issue number if you are searching for magazine");
        }
        return null;
    }

    private T searchForItem(ReadingType type, String title, String issue) {
        HashMap<ReadingObject.IGroup, TreeSet<T>> list = this.catalog.get(type);
        ArrayList<T> listOfItems = new ArrayList<>();

        for (TreeSet<T> items : list.values()) {
            for (T item : items) {
                if (item.getTitle().equalsIgnoreCase(title)) {
                    listOfItems.add(item);
                }
            }
        }
        if (listOfItems.size() == 1) {
            return listOfItems.get(0);
        } else if (listOfItems.size() > 1) {
            if (type == ReadingType.BOOK) {
                for (T item : listOfItems) {
                    if (((Book) item).getIssueDate().isEqual(LocalDate.parse(issue + "-01-01"))) {
                        return item;
                    }
                }
            } else if (type == ReadingType.MAGAZINE) {
                for (T item : listOfItems) {
                    if (((Magazine) item).getNumber() == Integer.parseInt(issue)) {
                        return item;
                    }
                }
            }
        } else {
            System.out.println("There is no such item in the library");
        }
        return null;
    }

    public void revision() {
        countOfAvailableItems();
        generateListOfBorrowedItems();
        generateOverdueItems();
    }

    private int countOfAvailableItems() {
        int count = 0;
        for (Entry<ReadingType, HashMap<ReadingObject.IGroup, TreeSet<T>>> e : this.catalog.entrySet()) {
            for (Entry<ReadingObject.IGroup, TreeSet<T>> genres : e.getValue().entrySet()) {
                for (T item : genres.getValue()) {
                    if (!item.isBorrowed()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void generateListOfBorrowedItems() {
        File borrowedItems = new File("borrowedItems.txt");
        try {
            borrowedItems.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<LocalDateTime, T> historyOfBorrowing = new TreeMap<>();

        for (Entry<T, TreeSet<HistoryLog>> e : historyLog.entrySet()) {
            if (e.getKey().isBorrowed()) {
                HistoryLog log = e.getValue().last();
                historyOfBorrowing.put(log.getBorrowingTime(), e.getKey());
            }
        }

        try (
                PrintWriter writer = new PrintWriter(borrowedItems)) {
            for (Entry<LocalDateTime, T> e : historyOfBorrowing.entrySet()) {
                writer.println(e.getValue().getLastBorrowed().toString() + " " + e.getValue());
            }
            writer.println("Total items borrowed: " + (totalReadingObjectsCount - countOfAvailableItems()));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void generateOverdueItems() {
        double totalOverdueTax = 0;
        HashMap<T, HashMap<String, Double>> overdueTaxes = new HashMap<>();

        for (Entry<T, TreeSet<HistoryLog>> e : historyLog.entrySet()) {
            T item = e.getKey();
            if (item.isBorrowed()) {
                for (HistoryLog hl : e.getValue()) {
                    if (hl.isOverDue()) {
                        if (!overdueTaxes.containsKey(item)) {
                            overdueTaxes.put(item, new HashMap<>());
                        }
                        overdueTaxes.get(item).put(hl.getClient(), item.getTotalTax());
                        totalOverdueTax += e.getKey().getTotalTax();
                    }
                }
            }
        }

        File overDueItems = new File("overdueItems.txt");
        try {
            overDueItems.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(overDueItems)) {
            writer.flush();
            for (Entry<T, HashMap<String, Double>> e : overdueTaxes.entrySet()) {
                for (Entry<String, Double> e1 : e.getValue().entrySet()) {
                    writer.println("Title: " + e.getKey().getTitle() + "; Client: "
                            + e1.getKey() + "; Tax to pay: " + e1.getValue());
                }
            }
            writer.println("Total overdue tax: " + totalOverdueTax);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
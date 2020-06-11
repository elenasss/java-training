package library;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeMap;

public abstract class ReadingObject {

    public interface IGroup {
        String getReadingGroup();
    }

    protected String title;
    private String publisher;
    private TreeMap<LocalDateTime, LocalDateTime> history;
    private Library.ReadingType type;
    private IGroup group;
    private boolean isBorrowed;
    private LocalDateTime lastBorrowed;

    public ReadingObject(String title, String publisher, Library.ReadingType type, IGroup group) {
        this.title = title;
        this.publisher = publisher;
        this.type = type;
        this.group = group;
        this.history = new TreeMap<>(Comparator.reverseOrder());
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public Library.ReadingType getType() {
        return type;
    }

    public LocalDateTime getLastBorrowed(){
        return lastBorrowed;
    }

    public IGroup getGroup() {
        return group;
    }

    public void takeReadingObject() {
        lastBorrowed = LocalDateTime.now();
        history.put(lastBorrowed, null);
        isBorrowed = true;
    }

    public void returnReadingObject() {
        history.put(lastBorrowed, LocalDateTime.now());
        isBorrowed = false;
    }

    public void setInterest(){
    }

    public double getTotalTax(){
        return 0;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public int getRentPeriod(){
        return 0;
    }

    public int getTax(){
        return 0;
    }

    @Override
    public String toString() {
        return String.format(
                "Type: %s; Name: %s, Publisher: %s",
                this.type, this.title, this.publisher);
    }
}
package library;

import java.time.LocalDate;

import static library.Library.ReadingType.BOOK;

public class Book extends ReadingObject {

    public enum Genre implements IGroup {
        ACTION,
        DRAMA,
        LOVE_NOVEL,
        FANTASY,
        SCIFI;

        @Override
        public String getReadingGroup() {
            return this.name();
        }
    }

    private String author;
    private LocalDate issueDate;
    private static final int TAX_BOOK = 2;
    private static final int BOOK_RENT_PERIOD = 300;
    private double interest;


    public Book(String name, String publisher, Genre genre, String author, LocalDate issueDate) {
        super(name, publisher, BOOK, genre);
        this.issueDate = setIssueDate(issueDate);
        this.author = author;
    }

    public Book(String name, String publisher, Genre genre, String author, String issuedYear) {
        super(name, publisher, BOOK, genre);
        this.issueDate = setIssueDate(issuedYear);
        this.author = author;
    }

    private LocalDate setIssueDate(String year) {
        return LocalDate.parse(year + "-01-01");
    }

    private LocalDate setIssueDate(LocalDate date) {
        return date;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public static int getTaxBook() {
        return TAX_BOOK;
    }

    @Override
    public void setInterest() {
        interest += TAX_BOOK * 0.01;
    }

    @Override
    public double getTotalTax() {
        return TAX_BOOK + interest;
    }


    @Override
    public int getRentPeriod() {
        return BOOK_RENT_PERIOD;
    }

    @Override
    public int getTax() {
        return TAX_BOOK;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(
                        "author: %s; issueDate: %s; theme: %s",
                        this.author, issueDate.toString(), getGroup());
    }
}

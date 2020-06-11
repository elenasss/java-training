package library;

import library.Library.ReadingType;


public class TextBook extends ReadingObject {

    public enum Theme implements IGroup {
        BIOLOGY,
        GEOGRAPHY,
        HISTORY,
        LITERATURE,
        MATH,
        PROGRAMMING;

        @Override
        public String getReadingGroup() {
            return this.name();
        }
    }

    private String author;
    private static final int TAX_TEXTBOOK = 3;
    private static final int TEXTBOOK_RENT_PERIOD = 150;
    private double interest;

    public TextBook(String name, String publisher, String author, Theme theme) {
        super(name, publisher, ReadingType.TEXTBOOK, theme);
        this.author = author;
    }


    @Override
    public void setInterest() {
        interest += TAX_TEXTBOOK * 0.01;
    }

    @Override
    public double getTotalTax() {
        return TAX_TEXTBOOK + interest;
    }

    @Override
    public int getRentPeriod() {
        return TEXTBOOK_RENT_PERIOD;
    }

    @Override
    public int getTax() {
        return TAX_TEXTBOOK;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(
                        "author: %s; theme: %s",
                        this.author, getGroup());
    }
}

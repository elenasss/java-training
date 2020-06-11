package library;

import library.Library.ReadingType;

import java.time.LocalDate;

public class Magazine extends ReadingObject {

    public enum Category implements IGroup {
        FASHION,
        MATHS,
        SCIENCE;

        @Override
        public String getReadingGroup() {
            return this.name();
        }
    }

    private int number;
    private LocalDate issueDate;

    public Magazine(String name, String publisher, Category category, int number, LocalDate issueDate) {
        super(name, publisher, ReadingType.MAGAZINE, category);
        this.number = number;
        this.issueDate = issueDate;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(
                        "category: %s; number: %s, issue date: %s",
                        getGroup(), this.number, issueDate.toString());
    }
}

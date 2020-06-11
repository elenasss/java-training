package library;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {

        Library library = createLibrary();

        library.printCatalog();

        try {
            library.borrowReadingObject("user1", Library.ReadingType.BOOK, "The Colour of Magic", "2019");
            Thread.sleep(1000);
            library.borrowReadingObject("user1", Library.ReadingType.BOOK, "The Colour of Magic", "2018");
            Thread.sleep(1000);
            library.borrowReadingObject("user4", Library.ReadingType.BOOK, "Na iztok ot raya", "2010");
            library.borrowReadingObject("user2", Library.ReadingType.MAGAZINE, "Krasiva", "5");
            Thread.sleep(1000);
            library.borrowReadingObject("user2", Library.ReadingType.TEXTBOOK, "Istoriya na UK");
            Thread.sleep(1000);
            library.borrowReadingObject("user3", Library.ReadingType.TEXTBOOK, "Java");

            Thread.sleep(8000);
            library.returnReadingObject("user4", Library.ReadingType.BOOK, "Na iztok ot raya", "2010");
            library.returnReadingObject("user2", Library.ReadingType.TEXTBOOK, "Istoriya na UK");
            library.borrowReadingObject("user5", Library.ReadingType.BOOK, "Na iztok ot raya", "2010");
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        library.revision();

        library.returnReadingObject("user1", Library.ReadingType.BOOK, "The Colour of Magic", "2019");
        library.returnReadingObject("user1", Library.ReadingType.BOOK, "The Colour of Magic", "2018");
        library.returnReadingObject("user3", Library.ReadingType.TEXTBOOK, "Java");
        library.returnReadingObject("user4", Library.ReadingType.BOOK, "Na iztok ot raya", "2010");
    }

    private static Library createLibrary() {
        Book book1 = new Book("The Colour of Magic", "Siela", Book.Genre.FANTASY, "Terry Pratchett", "2010");
        Book book2 = new Book("The Colour of Magic", "Siela", Book.Genre.FANTASY, "Terry Pratchett", "2019");
        Book book3 = new Book("The Colour of Magic", "Siela", Book.Genre.FANTASY, "Terry Pratchett", "2018");
        Book book4 = new Book("Na iztok ot raya", "Siela", Book.Genre.DRAMA, "John Steinbeck ", LocalDate.now());
        Book book5 = new Book("Otryad Sigma", "Sirma", Book.Genre.ACTION, "James Rollins ", LocalDate.now());
        Magazine spisanie1 = new Magazine("Krasiva", "Moda", Magazine.Category.FASHION, 13, LocalDate.now());
        Magazine spisanie2 = new Magazine("Krasiva", "Moda", Magazine.Category.FASHION, 5, LocalDate.now());
        Magazine spisanie3 = new Magazine("Nauka", "Novoto v naukata", Magazine.Category.SCIENCE, 5, LocalDate.now());
        Magazine spisanie5 = new Magazine("Nauka", "Novoto v naukata", Magazine.Category.SCIENCE, 2, LocalDate.now());
        Magazine spisanie4 = new Magazine("Krasiva", "Moda", Magazine.Category.FASHION, 1, LocalDate.now());
        TextBook uchebnik1 = new TextBook("Java", "Uchenicheski izdaniya", "Krasimir Baev", TextBook.Theme.PROGRAMMING);
        TextBook uchebnik2 = new TextBook("Nova Istoriya", "Uchenicheski izdaniya", "iliyana peeva", TextBook.Theme.HISTORY);
        TextBook uchebnik3 = new TextBook("Python", "Programirane", "Dragomir Baev", TextBook.Theme.PROGRAMMING);
        TextBook uchebnik4 = new TextBook("PHP", "Programirane", "Stiliyan Baev", TextBook.Theme.PROGRAMMING);
        TextBook uchebnik5 = new TextBook("Istoriya na UK", "Uchenicheski izdaniya", "iliyana peeva", TextBook.Theme.HISTORY);

        Library library = new Library();

        library.addReadingObject(book1);
        library.addReadingObject(book2);
        library.addReadingObject(book3);
        library.addReadingObject(book4);
        library.addReadingObject(book5);
        library.addReadingObject(spisanie1);
        library.addReadingObject(spisanie2);
        library.addReadingObject(spisanie3);
        library.addReadingObject(spisanie4);
        library.addReadingObject(spisanie5);
        library.addReadingObject(uchebnik1);
        library.addReadingObject(uchebnik2);
        library.addReadingObject(uchebnik3);
        library.addReadingObject(uchebnik4);
        library.addReadingObject(uchebnik5);

        return library;
    }
}

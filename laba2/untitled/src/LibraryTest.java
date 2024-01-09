import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    @Test
    void testAddItem() {
        Library library = new Library();
        Book book = new Book("Sample Book", "123456", "Author");
        library.add(book);
        assertTrue(library.getItems().contains(book));
    }
        @Test
    void testRegisterPatron() {
        Library library = new Library();
        Patron patron = new Patron("John Doe", "A123");
        library.registerPatron(patron);
        assertTrue(library.getPatrons().contains(patron));
    }

    @Test
    void testLendItem() {
        Library library = new Library();
        Book book = new Book("Sample Book", "123456", "Author");
        Patron patron = new Patron("John Doe", "A123");
        library.add(book);
        library.registerPatron(patron);
        library.lendItem(patron, book);
        assertTrue(book.isBorrowed());
        assertTrue(patron.getBorrowedItems().contains(book));
    }

    @Test
    void testReturnItem() {
        Library library = new Library();
        Book book = new Book("Sample Book", "123456", "Author");
        Patron patron = new Patron("John Doe", "A123");
        library.add(book);
        library.registerPatron(patron);
        library.lendItem(patron, book);
        library.returnItem(patron, book);
        assertFalse(book.isBorrowed());
        assertFalse(patron.getBorrowedItems().contains(book));
    }


}
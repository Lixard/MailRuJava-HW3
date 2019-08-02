import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LibraryTest {
    @NotNull
    private BooksFactory booksFactory = Mockito.mock(BooksFactory.class);
    private List<Book> library;


    public List<Book> libraryCreation() {
        library = Arrays.asList(
                new Book(new Author("Author1"), "Book1"),
                new Book(new Author("Author2"), "Book2"),
                new Book(new Author("Author3"), "Book3"),
                new Book(new Author("Author4"), "Book4"),
                new Book(new Author("Author5"), "Book5"));
        return library;
    }

    @Test(expected = OutOfMemoryError.class)
    public void creationException() {
        library = libraryCreation();
        Mockito.when(booksFactory.books()).thenReturn(library);
        new Library(library.size() - 1, booksFactory);
    }

    @Test
    public void cellsInTheCorrectOrder() {
        library = Arrays.asList(
                new Book(new Author("Author1"), "Book1"),
                new Book(new Author("Author2"), "Book2"),
                new Book(new Author("Author3"), "Book3"),
                new Book(new Author("Author4"), "Book4"),
                new Book(new Author("Author5"), "Book5"),
                null, null);
        Mockito.when(booksFactory.books()).thenReturn(library);
        Library lib = new Library(library.size(), booksFactory);
        for(int i = 0; i < library.size() - 2; i++) {
            Assert.assertEquals(i + ": " + library.get(i), lib.book(i));
        }
    }

    @Test
    public void correctBookInfo() {
        library = Collections.singletonList(
                new Book(new Author("Author1"), "Book1"));
        Mockito.when(booksFactory.books()).thenReturn(library);
        Library lib = new Library(library.size(),booksFactory);
        int index = 0;
        Assert.assertEquals(index + ": " + library.get(index), lib.book(index));
    }

    @Test(expected = NullPointerException.class)
    public void emptyBookCellException() {
        library = new ArrayList<>(1);
        Mockito.when(booksFactory.books()).thenReturn(library);
        Library lib = new Library(1, booksFactory);
        lib.book(0);
    }

    @Test
    public void correctBookReturns() {
       library = libraryCreation();
       Mockito.when(booksFactory.books()).thenReturn(library);
       Library lib = new Library(library.size(), booksFactory);
       int index = 3;
       Assert.assertEquals(index + ": " + library.get(index), lib.book(index));
    }

    @Test
    public void bookAddingInFirstFreeCell() {
        library = Arrays.asList(
                new Book(new Author("Author1"), "Book1"),
                null,
                new Book(new Author("Author3"), "Book3"),
                null,
                new Book(new Author("Author5"), "Book5"));
        Mockito.when(booksFactory.books()).thenReturn(library);
        Book book = new Book(new Author("Author2"), "Book2");
        Library lib = new Library(library.size(), booksFactory);

        lib.add(book);
        lib.add(book);
        library.set(1, book);
        library.set(3, book);

        for (int i = 0; i < library.size(); i++) {
            Assert.assertEquals(i + ": " + library.get(i), lib.book(i));
        }
    }

    @Test(expected = OutOfMemoryError.class)
    public void bookAddingInFullLibrary() {
        library = libraryCreation();
        Mockito.when(booksFactory.books()).thenReturn(library);
        Library lib = new Library(library.size(), booksFactory);
        Book book = new Book(new Author("Author6"), "Book6");
        lib.add(book);
    }

    @Test
    public void printToConsoleWorkingCorrectly() {
        library = libraryCreation();
        Mockito.when(booksFactory.books()).thenReturn(library);
        Library lib = new Library(library.size(), booksFactory);
        Assert.assertEquals(library.toString(), lib.printToConsole());
    }

}

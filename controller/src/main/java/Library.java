import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Library {

    private final ArrayList<Book> books;

    @Inject
    public Library(int capacity, BooksFactory booksFactory) {
        books = new ArrayList<>(capacity);
        if(booksFactory.books().size() > capacity) {
            throw new OutOfMemoryError("Capacity less then size collection!");
        }
        for (int i = 0; i < capacity; i++) {
            if (i < booksFactory.books().size()) {
                books.add((Book) booksFactory.books().toArray()[i]);
            } else {
                books.add(null);
            }
        }
    }

    public String printToConsole() {
        return books.toString();
    }

    @NotNull
    public String book(int index) {
        Book book = books.get(index);
        if (book == null) {
            throw new NullPointerException("This book doesn't exist!");
        }
        return index + ": " + book;
    }

    public void add(@NotNull Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) == null) {
                books.set(i, book);
                return;
            }
        }
        throw new OutOfMemoryError("Library is full!");
    }
}

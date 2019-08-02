import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public final class LibraryFactory {
    @NotNull
    private final BooksFactory booksFactory;
    @Inject
    public LibraryFactory(@NotNull BooksFactory booksFactory) {
        this.booksFactory = booksFactory;
    }

    Library createLibrary(int capacity) {
        return new Library(capacity, booksFactory);
    }
}

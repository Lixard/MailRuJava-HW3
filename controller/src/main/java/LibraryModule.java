import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class LibraryModule extends AbstractModule {
    @NotNull
    private final String path;

    @Inject
    public LibraryModule(@NotNull String path) {
        this.path = path;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(path));
    }
}

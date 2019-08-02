import com.google.inject.Inject;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public final class Book {
    @Getter
    @NotNull
    private final Author author;
    @Getter
    @NotNull
    private final String name;

    @Inject
    public Book(@NotNull Author author,@NotNull String name) {
        this.author = author;
        this.name = name;
    }

    @Override
    public @NotNull String toString() {
        return author + " name: " + name;
    }
}

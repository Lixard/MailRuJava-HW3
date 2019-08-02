import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public final class Author {
    @NotNull
    private final String name;

}

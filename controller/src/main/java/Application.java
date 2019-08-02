import com.google.inject.Guice;
import com.google.inject.Injector;

public final class Application {

    public static void main(String[] args) {
        final String path = args[0];
        final int capacity = Integer.parseInt(args[1]);

        final Injector injector = Guice.createInjector(new LibraryModule(path));
        final Library library = injector.getInstance(LibraryFactory.class).createLibrary(capacity);
        System.out.println(library.printToConsole());
    }
}

package pl.com.bottega.wzorce.singleton.generated;

public class SimpleSingleton {
    private static SimpleSingleton ourInstance = new SimpleSingleton();

    public static SimpleSingleton getInstance() {
        return ourInstance;
    }

    private SimpleSingleton() {
    }
}

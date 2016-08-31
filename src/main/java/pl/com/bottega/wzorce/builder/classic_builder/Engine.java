package pl.com.bottega.wzorce.builder.classic_builder;

public class Engine {

    private String type;

    public Engine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type='" + type + '\'' +
                '}';
    }
}

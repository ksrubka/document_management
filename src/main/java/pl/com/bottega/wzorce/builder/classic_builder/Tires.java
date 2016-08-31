package pl.com.bottega.wzorce.builder.classic_builder;

public class Tires {

    private int durability;
    private String type;

    public Tires(int durability, String type) {
        this.durability = durability;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tires{" +
                "durability=" + durability +
                '}';
    }
}

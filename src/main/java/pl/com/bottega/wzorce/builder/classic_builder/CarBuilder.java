package pl.com.bottega.wzorce.builder.classic_builder;

public interface CarBuilder {

    public void buildTires();
    public void buildEngine();
    public Car getCar();
}

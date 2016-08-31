package pl.com.bottega.wzorce.builder.classic_builder;

public class CarDirector {

    private CarBuilder carBuilder;

    public CarDirector(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public void makeCar() {
        carBuilder.buildTires();
        carBuilder.buildEngine();
    }

    public Car getCar() {
        return this.carBuilder.getCar();
    }
}

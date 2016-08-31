package pl.com.bottega.wzorce.builder.classic_builder;

public class RaceCarBuilder implements CarBuilder {

    private Car car;

    public RaceCarBuilder() {
        this.car = new Car();
    }

    @Override
    public void buildTires() {
        Tires tires = new Tires(50, "Slicks");
        car.setTires(tires);
    }

    @Override
    public void buildEngine() {
        Engine engine = new Engine("v8");
        car.setEngine(engine);
    }

    @Override
    public Car getCar() {
        return car;
    }
}


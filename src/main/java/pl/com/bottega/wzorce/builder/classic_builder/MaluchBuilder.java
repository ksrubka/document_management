package pl.com.bottega.wzorce.builder.classic_builder;

public class MaluchBuilder implements CarBuilder {

    private Car car;

    public MaluchBuilder() {
        this.car = new Car();
    }

    @Override
    public void buildTires() {
        Tires tires = new Tires(100, "Maluch tires");
        car.setTires(tires);
    }

    @Override
    public void buildEngine() {
        Engine engine = new Engine("Maluch Engine");
        car.setEngine(engine);
    }

    @Override
    public Car getCar() {
        return car;
    }
}

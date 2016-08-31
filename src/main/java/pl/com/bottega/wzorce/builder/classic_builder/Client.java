package pl.com.bottega.wzorce.builder.classic_builder;

public class Client {

    public static void main(String[] args) {
        CarBuilder carBuilder = new RaceCarBuilder();
        CarDirector director = new CarDirector(carBuilder);
        director.makeCar();

        Car car = director.getCar();
        System.out.println(car);
    }
}

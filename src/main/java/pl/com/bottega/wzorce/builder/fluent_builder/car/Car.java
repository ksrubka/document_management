package pl.com.bottega.wzorce.builder.fluent_builder.car;

public class Car {

    private String name;

    public Car(Builder builder) {
        this.name = builder.name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

    public static class Builder {

        private String name;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public static void main(String[] args) {
        Car car = new Car.Builder()
                .name("Mazda")
                .build();
        System.out.println(car);
    }
}

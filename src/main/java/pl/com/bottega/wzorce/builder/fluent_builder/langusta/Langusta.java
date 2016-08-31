package pl.com.bottega.wzorce.builder.fluent_builder.langusta;

public class Langusta {

    private String name;
    private String smile;
    private int growth;

    public Langusta(Builder builder) {
        this.name = builder.name;
        this.smile = builder.smile;
        this.growth = builder.growth;
    }

    public static class Builder {

        private final String name;
        private String smile;
        private int growth;

        public Builder(final String name) {
            this.name = name;
        }

        public Builder smile(final String smile) {
            this.smile = smile;
            return this;
        }

        public Builder growth(final int growth) {
            this.growth = growth;
            return this;
        }

        public Langusta build() {
            return new Langusta(this);
        }
    }

    @Override
    public String toString() {
        return "fluent_builder.langusta.Langusta{" +
                "name='" + name + '\'' +
                ", smile='" + smile + '\'' +
                ", growth=" + growth +
                '}';
    }
}

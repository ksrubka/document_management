package pl.com.bottega.wzorce.builder.fluent_builder.language;


public class Language {

    private String name;
    private int version;
    private boolean likable;

    public Language(Language.Builder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.likable = builder.likeable;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", version=" + version +
                ", likable=" + likable +
                '}';
    }

    public static class Builder {
        private String name;
        private int version;
        private boolean likeable;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public Language build() {
            return new Language(this);
        }

        public Builder likeable(boolean likeable) {
            this.likeable = likeable;
            return this;
        }
    }

    public static void main(String[] args) {
        Language java = new Language.Builder()
                .name("Java")
                .version(8)
                .likeable(true)
                .build();
        System.out.println(java);
    }
}

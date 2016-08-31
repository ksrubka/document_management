package pl.com.bottega.wzorce.builder.fluent_builder.langusta;

public class ProblematicLangusta {

    private String name;
    private String smile;
    private int growth;

    public ProblematicLangusta(String name) {
        this.name = name;
    }

    public ProblematicLangusta(String name, String smile, int growth) {
        this.name = name;
        this.smile = smile;
        this.growth = growth;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }


    public void setGrowth(int growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "ProblematicLangusta{" +
                "name='" + name + '\'' +
                ", smile='" + smile + '\'' +
                ", growth=" + growth +
                '}';
    }
}

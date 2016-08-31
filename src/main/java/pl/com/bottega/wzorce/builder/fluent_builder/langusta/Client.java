package pl.com.bottega.wzorce.builder.fluent_builder.langusta;

public class Client {

    public static void main(String[] args) {
        ProblematicLangusta problematicLangusta = new ProblematicLangusta("Langusta na Palmie", ":))", 5);
        System.out.println(problematicLangusta);

        ProblematicLangusta problematicLangusta1 = new ProblematicLangusta("Langusta i morze");
        problematicLangusta1.setGrowth(3);
        problematicLangusta1.setSmile(":|");
        System.out.println(problematicLangusta1);

        Langusta langusta = new Langusta.Builder("Fajna langusta")
                .smile(":o))")
                .growth(4)
                .build();
        System.out.println(langusta);
    }
}

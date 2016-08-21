package pl.com.bottega.documentmanagement.application;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
public class UnknownCommand extends SpringCommand {

    @Override
    public void execute() {
        System.out.println("Sorry, i don't understand :(");
    }
}

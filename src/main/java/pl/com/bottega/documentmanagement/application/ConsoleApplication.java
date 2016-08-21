package pl.com.bottega.documentmanagement.application;

import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
public abstract class ConsoleApplication {

    protected Scanner scanner =  new Scanner(System.in);

    public void run() {
        while(true) {
            printMenu();
            String cmd = getCommand();
            if (cmd.equals("quit"))
                return;
            execute(cmd);
        }
    }

    private void printMenu() {
        Collection<String> menuItems = menuItems();
        for (String item : menuItems)
            System.out.println(item);
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    protected void execute(String cmd) {
        CommandFactory commandFactory = commandFactory();
        Command command = commandFactory.createCommand(cmd);
        command.execute();
    }

    abstract CommandFactory commandFactory();

    protected abstract Collection<String> menuItems();
}

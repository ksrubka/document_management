package pl.com.bottega.wzorce.mars_rover;

import java.util.Scanner;

import static pl.com.bottega.wzorce.mars_rover.Direction.N;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class MarsRoverApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarsRover marsRover = new MarsRover(new Position(0, 0), N);
        while (true) {
            System.out.println("\nEnter command:\n'm' - move\n'rl' - rotate left\n'rr' - rotate right\n" +
                    "or 'quit' to quit application.");
            String command = scanner.nextLine();
            switch(command) {
                case "m":
                    marsRover.move();
                    System.out.println(marsRover.getPosition());
                    break;
                case "rl":
                    marsRover.rotateLeft();
                    System.out.println(marsRover.getPosition());
                    break;
                case "rr":
                    marsRover.rotateRight();
                    System.out.println(marsRover.getPosition());
                    break;
                case "quit":
                    return;
            }
        }
    }
}

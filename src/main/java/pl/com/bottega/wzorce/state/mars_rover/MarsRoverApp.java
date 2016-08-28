package pl.com.bottega.wzorce.state.mars_rover;

import pl.com.bottega.wzorce.state.mars_rover.state.NorthState;

import java.util.Scanner;

import static pl.com.bottega.wzorce.state.mars_rover.Direction.N;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class MarsRoverApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarsRover marsRover = new MarsRover();
        while (true) {
            System.out.println("\nEnter command:\n'm' - move\n'rl' - rotate left\n'rr' - rotate right\n" +
                    "or 'quit' to quit application.");
            String command = scanner.nextLine();
            switch(command) {
                case "m":
                    marsRover.move();
                    System.out.println(marsRover.position());
                    break;
                case "rl":
                    marsRover.rotateLeft();
                    System.out.println(marsRover.position());
                    break;
                case "rr":
                    marsRover.rotateRight();
                    System.out.println(marsRover.position());
                    break;
                case "quit":
                    return;
            }
        }
    }
}
